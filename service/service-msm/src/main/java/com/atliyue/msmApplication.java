package com.atliyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//去掉数据连接
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.atliyue")
public class msmApplication {
    public static void main(String[] args) {
        SpringApplication.run(msmApplication.class);
    }
}
