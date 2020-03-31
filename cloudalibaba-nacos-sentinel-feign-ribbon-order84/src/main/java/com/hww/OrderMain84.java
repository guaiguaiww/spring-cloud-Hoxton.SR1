package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/31-22:29
 * @Description:
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class OrderMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain84.class, args);
    }
}
