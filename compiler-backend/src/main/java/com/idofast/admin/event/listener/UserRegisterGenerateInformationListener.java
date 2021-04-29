package com.idofast.admin.event.listener;

import com.idofast.admin.domain.UserProxyInfo;
import com.idofast.admin.event.event.UserRegisterEvent;
import com.idofast.admin.repository.UserProxyInfoRepository;
import com.idofast.common.enums.DeletedEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/30 11:30 下午
 */
@Component
public class UserRegisterGenerateInformationListener implements ApplicationListener<UserRegisterEvent>
{

    @Autowired
    private UserProxyInfoRepository userProxyInfoRepository;

//    @Async
    @Override
    public void onApplicationEvent(UserRegisterEvent event)
    {
        LocalDateTime now = LocalDateTime.now();
        Long id = event.getId();
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
                .totalActiveDay(0)
                .deleted(DeletedEnum.NORMAL)
                .build();
        userProxyInfoRepository.save(information);
    }
}
