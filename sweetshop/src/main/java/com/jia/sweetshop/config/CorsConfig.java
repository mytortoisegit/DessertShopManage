package com.jia.sweetshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  springboot  跨域设置 配置类
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 允许cookie
                .allowCredentials(true)
                // 允许访问方式
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // 允许设置Headers属性
                .allowedHeaders("*")
                // 允许跨域时间
                .maxAge(3600);



        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
