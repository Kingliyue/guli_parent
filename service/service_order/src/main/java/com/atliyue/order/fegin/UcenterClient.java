package com.atliyue.order.fegin;


import com.atliyue.entity.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/ucenter/member")
@FeignClient(value = "edu-ucenter")
public interface UcenterClient {
    @GetMapping("getTokenInfo")
    Member getEntityByToken(String token);
}
