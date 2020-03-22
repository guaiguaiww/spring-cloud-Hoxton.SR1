package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/21-18:55
 * @Description: @EnableEurekaClient:开启Eureka-Client，注册服务到注册中心
 */
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class EurekaHystrixMain8001 {
    
     public static void main(String[] args) {
             SpringApplication.run(EurekaHystrixMain8001.class,args);
         }

}
