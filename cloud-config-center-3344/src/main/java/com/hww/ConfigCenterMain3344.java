package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther hww
 * @create 2020-02-21 17:47
 * @EnableConfigServer :开启配置中心 ，动态修改配置后，需要手动
 * 全局通知：执行curl -X POST "http://localhost:3344/actuator/bus-refresh"
 * 定点通知：curl -X POST "http://localhost:3344/actuator/bus-refresh/cloud-config-client:3355"
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
