package com.hotax.yygh.cmn.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Jeff 2022-04-03 15:08
 * @description:
 **/
@Configuration
@MapperScan("com.hotax.yygh.cmn.mapper")
public class CmnConfig implements WebMvcConfigurer {

}
