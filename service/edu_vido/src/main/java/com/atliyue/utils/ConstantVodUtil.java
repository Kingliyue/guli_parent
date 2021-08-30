package com.atliyue.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantVodUtil implements InitializingBean {

    @Value("${com.atliyue.accessKeyId}")
    private String accessKeyId;
    @Value("${com.atliyue.accessKeySecret}")
    private String accessKeySecret;

    public static String accessKey_id;
    public static String accessKey_Secret;
    @Override
    public void afterPropertiesSet() throws Exception {
        accessKey_id =this.accessKeyId;
        accessKey_Secret = this.accessKeySecret;
    }
}
