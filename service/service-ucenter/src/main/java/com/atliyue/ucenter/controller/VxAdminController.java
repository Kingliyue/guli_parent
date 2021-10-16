package com.atliyue.ucenter.controller;

import com.atliyue.exception.MyException;
import com.atliyue.ucenter.service.MemberService;
import com.atliyue.ucenter.utils.ConstantVxtUtils;
import com.atliyue.ucenter.utils.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

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
        //state 标志
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "ppid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String url = String.format(URL, ConstantVxtUtils.App_ID,
                ConstantVxtUtils.App_Secret,code);
        try {
            //请求这个拼接好的地址，得到返回两个值 accsess_token 和 openid
            //使用httpclient发送请求，得到返回结果
            String accessTokenInfo = HttpClientUtils.get(url);

            //从accessTokenInfo字符串获取出来两个值 accsess_token 和 openid
            //把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
            //使用json转换工具 Gson
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String)mapAccessToken.get("access_token");
            String openid = (String)mapAccessToken.get("openid");

            //把扫描人信息添加数据库里面
            //判断数据表里面是否存在相同微信信息，根据openid判断

         //  Member member = memberService.getOpenIdMember(openid);
          /**  if(member == null) {//memeber是空，表没有相同微信数据，进行添加

                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                //发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);
                //获取返回userinfo字符串扫描人信息
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)userInfoMap.get("nickname");//昵称
                String headimgurl = (String)userInfoMap.get("headimgurl");//头像

                member = new Member();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }

            //使用jwt根据member对象生成token字符串
            String jwtToken = JwtUtil.getJwtToken(member.getId(), member.getNickname());
            //最后：返回首页面，通过路径传递token字符串
            return "redirect:http://localhost:3000?token="+jwtToken;**/
          return "";
        }catch(Exception e) {
            throw new MyException(20001,"登录失败");
        }
    }


}
