package com.atliyue.controller;

import com.atliyue.exception.MyException;
import com.atliyue.result.Result;
import com.atliyue.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MsmService msmService;
    @GetMapping("send/{iphone}")
    public Result sendCode(@PathVariable("iphone") String iphone){
        if(StringUtils.isEmpty(iphone)){
            throw new MyException(20001,"请输入手机号");
        }
       String code = "0000";
       Boolean isSend =  msmService.send(iphone,code);
       if (isSend){
           redisTemplate.opsForValue().set(iphone,code,5, TimeUnit.MINUTES);
           return Result.ok();
       }else{
        return Result.error().message("发送短信失败");
       }
    }

}
