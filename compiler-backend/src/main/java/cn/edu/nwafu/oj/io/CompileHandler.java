package cn.edu.nwafu.oj.io;

import com.idofast.common.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 4:32 下午
 */
@Component
@Slf4j
public class CompileHandler
{

    public boolean compile(String userCodeFolder) throws InterruptedException, IOException, BusinessException
    {
        Process process = Runtime.getRuntime().exec("gcc "+userCodeFolder +"user.c -o "+userCodeFolder+"user.out");
        boolean b = process.waitFor(5, TimeUnit.SECONDS);
        if(!b)
        {
            throw new InterruptedIOException("gcc 编译超时");
        }
        final int i = process.exitValue();
        if(i == 0)
        {
            return true;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(process.getErrorStream(), outputStream);
        System.out.println(outputStream.toString());
        process.destroy();


        throw new BusinessException(outputStream.toString());

    }

}
