package com.atliyue.exception;

import com.liyue.result.Reselt;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 配置全局异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Reselt exception(Exception e){
        e.printStackTrace();
        return  Reselt.error().message("全局异常");
    }

}
