package com.hotax.yygh.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Jeff 2022-04-25 22:57
 * @description:
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.hotax")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hotax")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
