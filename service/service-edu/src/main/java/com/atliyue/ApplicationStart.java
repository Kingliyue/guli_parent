package com.atliyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.atliyue")
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
public class ApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class);
    }
}
