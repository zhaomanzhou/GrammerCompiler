package cn.edu.nwafu.admin.repository;

import cn.edu.nwafu.admin.domain.EmailSendHistory;
import com.idofast.common.enums.EmailTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailSendHistoryRepositoryTest
{

    @Autowired
    private EmailSendHistoryRepository emailSendHistoryRepository;

    @Test
    public void testInsert()
    {
        EmailSendHistory history = new EmailSendHistory();
        history.setContent("23434");
        history.setEmailTypeEnum(EmailTypeEnum.REGISTER_VCODE);
        history.setReceiver("271832284@qq.com");
        emailSendHistoryRepository.save(history);
    }

}