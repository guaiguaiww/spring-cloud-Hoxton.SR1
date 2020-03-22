package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/14-23:44
 * @Description:
 * @EnableEurekaClient:开启Eureka-Client
 * @EnableDiscoveryClient:是为使用Zookeeper或者consul作为服务中心的提供服务注册
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentMain8004 {

    public static void main(String[] args) {

        SpringApplication.run(PaymentMain8004.class, args);

    }
}
