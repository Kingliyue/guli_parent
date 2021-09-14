package com.atliyue.mcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.atliyue")
public class mcsApplication {
    public static void main(String[] args) {
            SpringApplication.run(mcsApplication.class);

    }
}
