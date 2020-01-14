package com.caix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.caix.dao")
public class SpringbootvueApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootvueApplication.class, args);
    }
}