package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/25-16:25
 * @Description:
 */
@EnableEurekaClient
@SpringBootApplication
public class StreamMqMain8801 {

    public static void main(String[] args) {
        SpringApplication.run(StreamMqMain8801.class, args);
    }
}
