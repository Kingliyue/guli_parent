package com.liyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//去掉数据连接
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class msmApplication {
    public static void main(String[] args) {
        SpringApplication.run(msmApplication.class);
    }
}
