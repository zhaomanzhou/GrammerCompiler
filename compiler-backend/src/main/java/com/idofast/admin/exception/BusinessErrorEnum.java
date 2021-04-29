package com.idofast.admin.exception;


import com.idofast.common.response.error.CommonError;

public enum BusinessErrorEnum implements CommonError
{

    UNKNOWN_ERROR(5000, "未知错误"),

    PARAMETER_VALIDATION_ERROR(1000, "参数不合法"),

    PARAMETER_EMPTY_ERROR(1001, "参数不能为空"),

    USER_NOT_EXIST(1002,"用户不存在"),

    USER_EXIST(1003,"用户已存在"),

    INVALID_USERNAME_OR_PASSWORD(1004, "用户名或密码错误"),

    USERNAME_EMPTY_ERROR(1005, "用户名不能为空"),

    REGISTER_FAILED(1006, "注册失败"),

    TOKEN_EXPIRED(1007,"token已过期"),

    INVALID_PASSWORD(1008,"密码错误"),

    LOGFF_USER(1009,"用户已注销"),

    NEED_LOGIN(401, "没有登录的用户，请先登录"),

    OUT_OF_AUTORITY(403, "非法权限操作"),

    NOTICE_NOT_EXIST(4004, "文章/公告 不存在"),

    ILLEGAL_ORDER_USER(40003,"非法操作，错误码40003")

    ;

    BusinessErrorEnum(int errorCode, String errorMessage)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private int errorCode;
    private String errorMessage;

    @Override
    public int getErrorCode()
    {
        return errorCode;
    }

    @Override
    public String getErrorMessage()
    {
        return errorMessage;
    }

    @Override
    public CommonError setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
        return this;
    }
}
