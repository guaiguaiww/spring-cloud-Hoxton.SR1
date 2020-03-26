package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/25-16:42
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMqMain8802 {

    public static void main(String[] args) {
        SpringApplication.run(StreamMqMain8802.class, args);
    }
}
