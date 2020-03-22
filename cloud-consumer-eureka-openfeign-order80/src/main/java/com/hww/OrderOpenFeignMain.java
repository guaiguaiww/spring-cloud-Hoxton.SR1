package com.hww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/20-17:30
 * @Description:
 */
@EnableFeignClients
@SpringBootApplication
public class OrderOpenFeignMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignMain.class, args);
    }
}
