package com.liyue.controller;

import com.liyue.result.Result;
import com.liyue.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("msm")
public class MsmController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MsmService msmService;
    @GetMapping("send/{iphone}")
    public Result sendCode(@PathVariable("iphone") String iphone){
       String code = "0000";
       Boolean isSend =  msmService.send(iphone,code);
       if (isSend){
           redisTemplate.opsForValue().set(iphone,code,5, TimeUnit.SECONDS);
           return Result.ok();
       }else{
        return Result.error().message("发送短信失败");
       }
    }

}
