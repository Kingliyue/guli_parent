package com.atliyue.oss.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "com.atliyue")
@Data
public class ConstantUtil implements InitializingBean {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.END_POINT = endpoint;
        this.ACCESS_KEY_ID = accessKeyId;
        this.ACCESS_KEY_SECRET = accessKeySecret;
        this.BUCKET_NAME =bucketName;
    }
}
