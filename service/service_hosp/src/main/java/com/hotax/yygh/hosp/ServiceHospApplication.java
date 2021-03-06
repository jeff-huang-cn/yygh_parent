package com.hotax.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Jeff 2022-04-03 14:06
 * @description:
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.hotax")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hotax")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}

