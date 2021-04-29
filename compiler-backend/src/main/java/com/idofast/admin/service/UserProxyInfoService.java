package com.idofast.admin.service;

import com.idofast.admin.domain.UserProxyInfo;
import com.idofast.admin.domain.dto.UserInfoLite;
import com.idofast.admin.repository.UserProxyInfoRepository;
import com.idofast.common.enums.DeletedEnum;
import com.idofast.common.response.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/31 12:32 上午
 */
@Service
public class UserProxyInfoService
{
    @Autowired
    private UserProxyInfoRepository userProxyInfoRepository;


    /**
     * 根据Id获取用户信息，如果不存在则新创建
     * @param id 用户的id
     * @param ifCreate 如果不存在是否新建
     */
    public UserProxyInfo selectById(Long id, boolean ifCreate) throws BusinessException
    {
        Optional<UserProxyInfo> byId = userProxyInfoRepository.findById(id);

        //TODO  报警
        if(byId.isEmpty())
        {
            if(ifCreate)
            {
                LocalDateTime now = LocalDateTime.now();
                UserProxyInfo information = UserProxyInfo.builder()
                        .id(id)
                        .level(0)
                        .speed(1024)
                        .totalData(1024)
                        .usedData(0)
                        .nextSettleDate(now.plusDays(1))
                        .expireDate(now.plusDays(1))
                        .packageId(0)
                        .namespace(0)
                        .totalActiveDay(1)
                        .deleted(DeletedEnum.NORMAL)
                        .build();
                userProxyInfoRepository.save(information);
                return information;
            }else
            {
                throw new BusinessException("用户信息不存在");
            }

        }else
        {
            return byId.get();
        }
    }


    public Page<UserInfoLite> getUserList(Pageable page)
    {
        return userProxyInfoRepository.queryUserInfo(page);
    }
}
