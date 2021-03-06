package com.atliyue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.atliyue")
@MapperScan("com.atliyue.ucenter.mapper")
@EnableFeignClients
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class);
    }
}
