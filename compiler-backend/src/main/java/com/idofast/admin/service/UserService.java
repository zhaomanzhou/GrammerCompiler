package com.idofast.admin.service;


import com.idofast.admin.constant.TokenHashConst;
import com.idofast.admin.controller.vo.request.RegisterUserVo;
import com.idofast.admin.domain.User;
import com.idofast.admin.event.event.UserRegisterEvent;
import com.idofast.admin.event.publisher.EventPublisher;
import com.idofast.admin.exception.BusinessErrorEnum;
import com.idofast.admin.repository.UserRepository;
import com.idofast.admin.service.manager.EmailLockManager;
import com.idofast.common.common.JwtToken;
import com.idofast.common.common.RedisPrefixConst;
import com.idofast.common.enums.DeletedEnum;
import com.idofast.common.enums.UserStatusEnum;
import com.idofast.common.response.error.BusinessException;
import com.idofast.common.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailLockManager emailLockManager;


    @Autowired
    private EventPublisher eventPublisher;

    @Value("${token.expiration}")
    private int expiration;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 是否存在该邮箱的帐号
     */
    public boolean userExistByEmail(String email)
    {
        User user = new User();
        user.setStatus(UserStatusEnum.NORMAL);
        user.setEmail(email);
        Optional<User> one = userRepository.findOne(Example.of(user));
        return one.isPresent();
    }

    /**
     * 注册用户
     */
    @Transactional
    public User registerUser(RegisterUserVo registerUserVo) throws BusinessException
    {
        String email = registerUserVo.getEmail();
        String verificationCode = emailLockManager.getVerificationCode(email);
        if(verificationCode == null)
        {
            throw new BusinessException("验证码已失效");
        }
        if(!verificationCode.equals(registerUserVo.getVcode()))
        {
            throw new BusinessException("验证码错误");
        }
        User user = registerUserVo.convertToUserDo();
        User save = userRepository.save(user);
        log.info("用户{}注册成功", user.getEmail());
        eventPublisher.publishEvent(new UserRegisterEvent(this, user.getId()));

        return save;
    }


    /**
     * 根据邮箱和密码生产token
     */
    public String genTokenByAuthentication(String email, String password) throws BusinessException
    {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password))
        {
            throw new BusinessException("邮箱或密码不能为空");
        }
        User userForSearch = new User();
        userForSearch.setEmail(email);
        userForSearch.setDeleted(DeletedEnum.NORMAL );
        Optional<User> one = userRepository.findOne(Example.of(userForSearch));
        if(!one.isPresent())
        {
            log.warn("A user {} which is not exist tried to login", email);
            throw new BusinessException(BusinessErrorEnum.INVALID_USERNAME_OR_PASSWORD);
        }
        User userInDb = one.get();
        String passwordInDb = userInDb.getPassword();


        String md5Password = MD5Util.MD5EncodeUtf8(password);

        if (!md5Password.equals(passwordInDb))
        {
            log.warn("A user {} with invalid password {} tried to login", email, password);
            throw new BusinessException(BusinessErrorEnum.INVALID_USERNAME_OR_PASSWORD);
        }

        String token = null;
        try
        {
            token = JwtToken.createToken();
        } catch (Exception e)
        {
            log.warn(String.valueOf(e.getCause()));
            throw new BusinessException("token创建失败");
        }

        redisTemplate.opsForHash().put(RedisPrefixConst.withTokenPrefix(token), TokenHashConst.USER, userInDb);
        redisTemplate.expire(RedisPrefixConst.withTokenPrefix(token), expiration, TimeUnit.MINUTES);
        return token;
    }


    /**
     * 根据token获取用户
     */
    public User getUserByToken(String token) throws BusinessException
    {
        if (StringUtils.isEmpty(token))
        {
            throw new BusinessException("token不能为空");
        }

        Object o = redisTemplate.opsForHash().get(RedisPrefixConst.TOKEN_PREFIX + token, TokenHashConst.USER);
        if (o == null)
        {
            throw new BusinessException(BusinessErrorEnum.TOKEN_EXPIRED);
        }
        User user = (User) o;
        return user;
    }

    /**
     * 根据id获取用户
     * @param userId
     * @return
     * @throws BusinessException
     */
    public User getUserById(Long userId) throws BusinessException
    {
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty())
        {
            throw new BusinessException(BusinessErrorEnum.USER_NOT_EXIST);
        }
        return userById.get();
    }


    /**
     * 重置密码
     */
    public boolean resetPassword(String passwordOld, String passwordNew, User user) throws BusinessException
    {
        //Md5加密旧密码
        String passwordOldMd5 = MD5Util.MD5EncodeUtf8(passwordOld);
        if (!user.getPassword().equals(passwordOldMd5))
        {
            throw new BusinessException(BusinessErrorEnum.INVALID_PASSWORD);
        }
        User userToUpdate = new User();
        userToUpdate.setId(user.getId());
        userToUpdate.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        userRepository.save(userToUpdate);

        return true;
    }


    /**
     * 注销用户
     */
    public boolean deleteUser(User user) throws BusinessException
    {
        User userToLogout = new User();
        userToLogout.setId(user.getId());
        userToLogout.setDeleted(DeletedEnum.DELETED);
        userRepository.save(userToLogout);
        return true;
    }
}
