package com.atliyue.ucenter.controller;

import com.atliyue.ucenter.service.MemberService;
import com.atliyue.ucenter.utils.ConstantVxtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

@Controller
@RequestMapping("ucenter/wx")
public class VxAdminController {
    @Autowired
    private MemberService memberService;
    @GetMapping("login")
    public String login(){
        String url = "https://open.weixin.qq.com/connect/qrconnect?" +
                "appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=atliyue" +
                "#wechat_redirect";
        String redirect_uri = ConstantVxtUtils.Redirect_Uri;
        String urlEncode ="";
        try {
             urlEncode = URLEncoder.encode(redirect_uri, "utf-8");

        }catch (Exception e){

        }
        String format = String.format(url, ConstantVxtUtils.App_ID, urlEncode);
        return "redirect:"+format;
    }
    @GetMapping("callback")
    public String callBack(String code, String state){
        System.out.println(code);
        return "";
    }


}
