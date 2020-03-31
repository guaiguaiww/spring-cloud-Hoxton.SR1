package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/28-16:28
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosSentinelServiceMain8401 {
    public static void main(String[] args) {
        SpringApplication.run(NacosSentinelServiceMain8401.class, args);
    }
}
