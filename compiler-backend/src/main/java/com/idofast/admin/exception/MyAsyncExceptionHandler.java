package com.idofast.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;


@Component
@Slf4j
public class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler
{

    @Override
    public void handleUncaughtException(
            Throwable throwable, Method method, Object... obj) {

        log.error("Error in async task, {} in method {} with parameter", throwable.getMessage(), method.getName());
        log.error("Parameter value - {" + StringUtils.join(obj, ", ") + "}", throwable);
    }

}