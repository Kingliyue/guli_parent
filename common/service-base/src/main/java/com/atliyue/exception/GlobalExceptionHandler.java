package com.atliyue.exception;

import com.liyue.result.Result;
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
    public Result exception(Exception e){
        e.printStackTrace();
        return  Result.error().message("全局异常");
    }
    //自定义异常
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result myException(MyException e){
        return  Result.error()
                .code(e.getCode())
                .message(e.getMessage());
    }


}
