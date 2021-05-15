package cn.edu.nwafu.admin.service;


import cn.edu.nwafu.admin.service.manager.EmailLockManager;
import cn.edu.nwafu.admin.service.manager.EmailManager;
import cn.edu.nwafu.admin.cache.EmailVCache;
import cn.edu.nwafu.admin.constant.EmailConstant;
import cn.edu.nwafu.admin.repository.UserRepository;
import cn.edu.nwafu.admin.util.EmailUtils;
import cn.edu.nwafu.admin.util.VcodeUtil;
import com.idofast.common.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService
{
    @Autowired
    EmailVCache emailVCache;
    @Autowired
    EmailUtils emailUtils;
    @Autowired
    EmailConstant emailConstant;


    @Autowired
    private EmailLockManager emailLockManager;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private EmailManager emailManager;



    public void sendVcodeForRegister(String toEmail) throws Exception
    {
        if(!emailLockManager.canSendVerificationCode(toEmail))
        {
            throw new BusinessException("操作过于频繁，请两分钟后尝试");
        }
        String vcode = VcodeUtil.generateVCode();

        emailManager.sendMailAndSaveToDb(toEmail, vcode);

    }

}
