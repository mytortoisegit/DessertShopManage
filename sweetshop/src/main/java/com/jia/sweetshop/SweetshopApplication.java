package com.jia.sweetshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 所有mapper 中不需要再单独添加@mapper 注解
@MapperScan("com.jia.sweetshop.mappers")
public class SweetshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweetshopApplication.class, args);
    }

}
