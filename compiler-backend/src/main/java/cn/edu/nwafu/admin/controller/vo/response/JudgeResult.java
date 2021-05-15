package cn.edu.nwafu.admin.controller.vo.response;

import cn.edu.nwafu.utils.Result;
import lombok.Data;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/11 11:07 上午
 */
@Data
public class JudgeResult
{
    /**
     * 仅供前端使用，判断这个代码有没有处理完毕
     */
    public boolean ok;
    public Result compile ;
    public Result structure;
    public Result judge;

    public Integer current;

    public String msg;

}
