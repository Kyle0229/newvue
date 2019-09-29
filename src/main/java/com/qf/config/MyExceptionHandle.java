package com.qf.config;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 54110 on 2019-09-20.
 */
@ControllerAdvice //控制器增强
public class MyExceptionHandle {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public String ex(){
        return "error";
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public String ar(){
        return "arith";
    }
}
