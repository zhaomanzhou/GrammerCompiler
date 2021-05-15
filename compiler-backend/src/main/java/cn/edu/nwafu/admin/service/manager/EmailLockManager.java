package cn.edu.nwafu.admin.service.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailLockManager
{

    Cache<Object, Object> lockCache = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.MINUTES)
            .build();

    Cache<Object, Object> vcodeCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();
    /**
     * 判断是否能向该用户发送邮件，如果在两分钟内发送过则返回false
     * @return
     */
    public boolean canSendVerificationCode(String toUserEmail)
    {
        Object ifPresent = lockCache.getIfPresent(toUserEmail);
        return ifPresent == null;
    }

    /**
     * 获取验证码
     * @param userEmail
     * @return
     */
    public String getVerificationCode(String userEmail)
    {
        Object ifPresent = vcodeCache.getIfPresent(userEmail);
        if(ifPresent == null)
        {
            return null;
        }
        return ((String) ifPresent);
    }

    /**
     * 该用户发送邮件后，2分钟之内不允许发送
     * @param userEmail
     */
    public void lockUserForVerificationCode(String userEmail, String vcode)
    {
        lockCache.put(userEmail, vcode);
        vcodeCache.put(userEmail, vcode);
    }

}
