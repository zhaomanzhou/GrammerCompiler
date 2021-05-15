package cn.edu.nwafu.oj.io;

import cn.edu.nwafu.admin.exception.JudgeException;
import com.idofast.common.response.error.BusinessException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 5:03 下午
 */
@Component
public class JudgeHandler
{
    public void judge(String userFolder, String problemFolder) throws BusinessException, IOException, InterruptedException, JudgeException
    {
        File f = new File(problemFolder);
        if(!f.exists() || !f.isDirectory())
        {
            throw new BusinessException("problemFolder is not valid");
        }
        File[] files = f.listFiles();
        for(File subFile: files)
        {
            if(subFile.isDirectory())
            {

                ProcessBuilder pb = new ProcessBuilder(userFolder + "user.out");
                pb.redirectInput(new File(subFile.getAbsoluteFile() + "/a.in"));
                final Process start = pb.start();
                final boolean b = start.waitFor(7, TimeUnit.SECONDS);
                if(!b)
                {
                    start.destroy();

                    throw new JudgeException(Integer.parseInt(subFile.getName()), "timeout");
                }
                if(start.exitValue() != 0)
                {
                    start.destroy();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    IOUtils.copy(start.getErrorStream(), outputStream);
                    throw new JudgeException(Integer.parseInt(subFile.getName()), outputStream.toString());
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                IOUtils.copy(start.getInputStream(), outputStream);

                final byte[] bytes = Files.readAllBytes(Paths.get(subFile.getAbsolutePath(), "a.out"));
                String expect = new String(bytes);
                String actual = outputStream.toString();
                if(!expect.trim().equals(actual.trim()))
                {
                    start.destroy();
                    StringBuilder sb = new StringBuilder();
                    sb.append("input: ");
                    sb.append(new String(Files.readAllBytes(Paths.get((new File(subFile.getAbsoluteFile() + "/a.in").toURI())))) + "\n");
                    sb.append("Expect:\n");
                    sb.append(expect);
                    sb.append("\nActual:\n");
                    sb.append(actual);
                    throw new JudgeException(Integer.parseInt(subFile.getName()), sb.toString());
                }

                start.destroy();
            }
        }
        ProcessBuilder pb = new ProcessBuilder(userFolder + "user.out ");
    }
}
