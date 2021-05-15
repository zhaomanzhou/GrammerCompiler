package cn.edu.nwafu.oj;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 6:37 下午
 */
@Component
@ConfigurationProperties(prefix = "compiler")
@Data
public class CompilerConst
{
    private String userFolder;
    private String problemFolder;
}
