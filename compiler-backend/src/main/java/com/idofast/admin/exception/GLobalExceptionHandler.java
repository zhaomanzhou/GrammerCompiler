package com.idofast.admin.exception;

import com.idofast.common.response.ServerResponse;
import com.idofast.common.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class GLobalExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ServerResponse<Object> BusinessExceptionHandler(BusinessException e) {
        log.info("Business Exception ", e);
        return ServerResponse.error(e.getErrorCode(), e.getErrorMessage());
    }

    //在请求方法加校验的会抛出次异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ServerResponse<Object> paramExceptionHandler(ConstraintViolationException e)
    {
        Set<ConstraintViolation<?>> errorSet = e.getConstraintViolations();
        String errors = errorSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        log.info("validate Exception", e);
        //TODO仅为调试
        return ServerResponse.error(1000, errors);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ServerResponse<Object> bindExceptionHandler(BindException e)
    {
        String errors = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(";"));
        log.info("validate Exception", e);
        //TODO仅为调试
        return ServerResponse.error(1000, errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ServerResponse<Object> unknownExceptionHandler(Exception e)
    {
        log.info("Unknown Exception", e);
        //TODO仅为调试
        return ServerResponse.error(5000, e.getMessage());
    }

}
