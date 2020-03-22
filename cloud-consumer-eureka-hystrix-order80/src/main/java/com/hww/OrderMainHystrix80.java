package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/22-14:01
 * @Description: hystrix消费端服务端都可以使用
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderMainHystrix80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainHystrix80.class, args);
    }
}
