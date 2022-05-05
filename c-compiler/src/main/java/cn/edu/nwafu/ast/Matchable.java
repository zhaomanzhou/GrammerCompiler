package cn.edu.nwafu.ast;

import cn.edu.nwafu.utils.Result;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/6/1 8:36 下午
 */


public interface Matchable<T extends Node>
{
    Result compatible(T program);
}


