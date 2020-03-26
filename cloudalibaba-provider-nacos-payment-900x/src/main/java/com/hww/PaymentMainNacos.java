package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/26-15:22
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMainNacos {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMainNacos.class, args);
    }
}
