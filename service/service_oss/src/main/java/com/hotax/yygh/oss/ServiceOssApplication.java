package com.hotax.yygh.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

/**
 * @author: Jeff 2022-05-02 17:54
 * @description:
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 不自动加载数据配置信息
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.hotax"})
public class ServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class, args);
    }
}
