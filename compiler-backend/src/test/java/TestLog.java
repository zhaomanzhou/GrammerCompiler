import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


@Slf4j
public class TestLog
{
    @Test
    public void test1() throws IOException
    {
        //Logger logger = LoggerFactory.getLogger(getClass());
        final Process exec = Runtime.getRuntime().exec("echo 11");
        System.out.println(exec.exitValue());

        log.info("ddd");
    }
}
