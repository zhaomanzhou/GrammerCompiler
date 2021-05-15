package cn.edu.nwafu.admin.controller;


import cn.edu.nwafu.admin.config.context.RequestContext;
import cn.edu.nwafu.admin.controller.vo.request.RegisterUserVo;
import cn.edu.nwafu.admin.controller.vo.response.UserVo;
import cn.edu.nwafu.admin.domain.User;
import cn.edu.nwafu.admin.exception.BusinessErrorEnum;
import cn.edu.nwafu.admin.service.EmailService;
import cn.edu.nwafu.admin.service.UserService;
import com.idofast.common.response.ServerResponse;
import com.idofast.common.response.error.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import javax.validation.constraints.Email;


@RestController
@RequestMapping("/user")
@Api(tags = "用户相关的api")
@CrossOrigin
@Slf4j
@Validated
public class UserController
{

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Validator validator;


    @ApiOperation(value = "获取注册验证码" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true,dataType = "string",example = "271832284@qq.com"),
    })
    @GetMapping("/vcode/register")
    public ServerResponse<String> registerForVcode(@Email(message = "邮箱格式不正确") @RequestParam("email") String email) throws Exception
    {
        if(StringUtils.isBlank(email))
        {
            throw new BusinessException("email不能为空");
        }
        boolean exist = userService.userExistByEmail(email);
        if(exist)
        {
            throw new BusinessException("用户已存在");
        }

        emailService.sendVcodeForRegister(email);
        return ServerResponse.success();
    }


    @ApiOperation(value = "用户注册" )
    @PostMapping("/register")
    public ServerResponse<String> reg(@Validated RegisterUserVo registerUserVo) throws BusinessException
    {
        User user = userService.registerUser(registerUserVo);
        return ServerResponse.success();
    }




    @ApiOperation(value = "登录" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = true,dataType = "string",example = "271832284@qq.com"),
            @ApiImplicitParam(name = "password", value = "密码",required = true,dataType = "string", example = "123456"),
    })
    @PostMapping(value = "/login")
    public ServerResponse<String> login(String email, String password) throws BusinessException {
        String token = userService.genTokenByAuthentication(email, password);
        return ServerResponse.success(token);
    }



    @ApiOperation("根据token获取当前用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),
    })
    @GetMapping(value = "/detail/token")
    public ServerResponse<UserVo> getUserInfo(String token) throws BusinessException {

        if(token == null){
            throw new BusinessException(BusinessErrorEnum.NEED_LOGIN);
        }

            User user = userService.getUserByToken(token);
            UserVo userVo = UserVo.convertUserToVo(user);
            return ServerResponse.success(userVo);

    }


    @ApiOperation(value = "根据id查询用户信息" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true,dataType = "Long"),
    })
    @GetMapping("/detail/id/")
    public ServerResponse<UserVo> queryById(Long id) throws BusinessException {
        User user = userService.getUserById(id);
        return ServerResponse.success(UserVo.convertUserToVo(user));
    }

    @ApiOperation(value = "重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "passwordOld", value = "用户原密码", required = true,dataType = "String"),
            @ApiImplicitParam(name = "passwordNew", value = "用户新密码", required = true,dataType = "String"),
    })
    @PostMapping(value = "/reset_password")
    public ServerResponse<String> resetPassword(String passwordOld,String passwordNew) throws BusinessException {
        User user = userService.getUserByToken(RequestContext.getToken());
        if(user == null){
            return ServerResponse.error("用户未登录");
        }
        userService.resetPassword(passwordOld, passwordNew, user);
        return ServerResponse.success();
    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping(value = "/update")
    public ServerResponse<String> update_information(UserVo userVoNew) throws BusinessException {
        User user = userService.getUserByToken(RequestContext.getToken());
        if(user == null){
            return ServerResponse.error("用户未登录");
        }

        return ServerResponse.success();
    }

    @ApiOperation(value = "注销用户")
    @PostMapping(value = "/delete")
    public ServerResponse<String>  LogOutUser() throws BusinessException{
        User user = userService.getUserByToken(RequestContext.getToken());
        if(user == null){
            return ServerResponse.error("用户未登录");
        }
        userService.deleteUser(user);
        return ServerResponse.success();
    }


}
