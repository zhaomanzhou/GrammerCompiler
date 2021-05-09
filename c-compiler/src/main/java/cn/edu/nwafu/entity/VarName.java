package cn.edu.nwafu.entity;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/8 6:02 下午
 */
public class VarName
{
    private String name;

    /**
     * 0 代表不是数组
     * -1 代表不知道长度
     */
    private long arrayLen;

    public VarName(String name, long arrayLen)
    {
        this.name = name;
        this.arrayLen = arrayLen;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getArrayLen()
    {
        return arrayLen;
    }

    public void setArrayLen(long arrayLen)
    {
        this.arrayLen = arrayLen;
    }
}
