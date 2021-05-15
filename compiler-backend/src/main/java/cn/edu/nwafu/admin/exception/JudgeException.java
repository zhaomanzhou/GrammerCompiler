package cn.edu.nwafu.admin.exception;

import lombok.Data;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 6:55 下午
 */
@Data
public class JudgeException extends Exception
{
    //错误的题目编号
    private int proNo;

    //错误信息
    private String msg;

    public JudgeException(int proNo, String msg)
    {
        super(msg);
        this.proNo = proNo;
        this.msg = msg;
    }


}
