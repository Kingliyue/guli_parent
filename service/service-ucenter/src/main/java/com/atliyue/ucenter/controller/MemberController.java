package com.atliyue.ucenter.controller;


import com.atliyue.ucenter.entity.Member;
import com.atliyue.ucenter.service.MemberService;
import com.atliyue.ucenter.vo.Ucenter;
import com.liyue.result.Result;
import com.liyue.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @PostMapping("register")
    public Result register(@RequestBody Ucenter ucenter){
        memberService.register(ucenter);
        return Result.ok();
    }
    //获取token中的参数值；
    @GetMapping("getToken")
    public Result getEntityOfToken(HttpServletRequest httpServletRequest){
        String memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(httpServletRequest);
        Member member = memberService.getById(memberIdByJwtToken);
        return Result.ok().data("member",member);
    }

}
