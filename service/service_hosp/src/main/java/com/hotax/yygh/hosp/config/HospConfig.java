package com.hotax.yygh.hosp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Jeff 2022-04-03 15:08
 * @description:
 **/
@Configuration
@MapperScan("com.hotax.yygh.hosp.mapper")
public class HospConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("POST","GET","OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(false).maxAge(3600);
//    }
}
