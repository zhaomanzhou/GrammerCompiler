package cn.edu.nwafu.utils;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 10:49 上午
 */
public class Result
{
    public boolean success;
    public String msg;

    public Result(boolean success, String msg)
    {
        this.success = success;
        this.msg = msg;
    }

    public Result()
    {
    }

    public static Result ofFalse(String msg)
    {
        return new Result(false, msg);
    }

    public static Result ofSuccess()
    {
        return new Result(true, null);
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        if(success)
        {
            return "true";
        }else
        {
            return "error: "  + msg;
        }

    }
}
