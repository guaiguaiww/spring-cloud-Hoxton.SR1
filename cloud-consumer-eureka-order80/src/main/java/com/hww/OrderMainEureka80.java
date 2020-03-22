package com.hww;

import com.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/15-14:23
 * @Description:
 * @EnableEurekaClient:开启为Eureka-client
 * @RibbonClient (name = "CLOUD-PAYMENT-SERVICE-EUREKA",configuration = MySelfRule.class):切换负载均衡策略
 * @SpringBootApplication spring会扫描当前包和子包
 */
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE-EUREKA",configuration = MySelfRule.class)
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class OrderMainEureka80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMainEureka80.class, args);
    }
}
