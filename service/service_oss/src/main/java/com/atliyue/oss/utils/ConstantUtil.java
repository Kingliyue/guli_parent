package com.atliyue.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件的属性
 * // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
 * String endpoint = "yourEndpoint";
 * // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
 * String accessKeyId = "yourAccessKeyId";
 * String accessKeySecret = "yourAccessKeySecret";
 */
@Component
@Order(0)
public class ConstantUtil implements InitializingBean {
    @Value("${com.atliyue.endpoint}")
    private String endpoint;
    @Value("${com.atliyue.accessKeyId}")
    private String accessKeyId;
    @Value("${com.atliyue.accessKeySecret}")
    private String accessKeySecret;
    @Value("${com.atliyue.bucketName}")
    private String bucketName;

    public static  String END_POINT ;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = this.endpoint;
        ACCESS_KEY_ID = this.accessKeyId;
        ACCESS_KEY_SECRET = this.accessKeySecret;
        BUCKET_NAME =this.bucketName;
    }


}
