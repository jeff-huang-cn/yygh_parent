package com.hotax.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Jeff 2022-04-04 11:33
 * @description:
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.hotax")
public class SerivceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(SerivceCmnApplication.class, args);
    }
}
