package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/16-23:11
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderMainZk80 {
     public static void main(String[] args) {
             SpringApplication.run(OrderMainZk80.class,args);
         }
}
