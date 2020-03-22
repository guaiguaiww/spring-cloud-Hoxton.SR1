package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/14-23:44
 * @Description:
 * @EnableEurekaClient:开启Eureka-Client
 * @EnableDiscoveryClient:开启服务发现
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class PaymentMain8002 {

    public static void main(String[] args) {

        SpringApplication.run(PaymentMain8002.class, args);

    }
}
