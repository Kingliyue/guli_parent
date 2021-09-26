package com.atliyue.ucenter.controller;


import com.atliyue.ucenter.service.MemberService;
import com.atliyue.ucenter.vo.Ucenter;
import com.liyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author liYue
 * @since 2021-09-26
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录
     * @param ucenter
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody Ucenter ucenter){
        final String jwtToken = memberService.login(ucenter);
        return Result.ok().data("token",jwtToken);
    }
    //注册



}

