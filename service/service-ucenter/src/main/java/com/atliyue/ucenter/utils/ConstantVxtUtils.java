package com.atliyue.ucenter.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConstantVxtUtils implements InitializingBean {
    @Value("${com.liyue.vx.appid}")
    private String appid;
    @Value("${com.liyue.vx.appsecret}")
    private String appsecret;
    @Value("${com.liyue.vx.redirect_uri}")
    private String redirect_uri;

    public static String App_ID;
    public static String App_Secret;
    public static String Redirect_Uri;

    @Override
    public void afterPropertiesSet() throws Exception {
        App_ID= this.appid;
        App_Secret = this.appsecret;
        Redirect_Uri = this.redirect_uri;
    }
}
