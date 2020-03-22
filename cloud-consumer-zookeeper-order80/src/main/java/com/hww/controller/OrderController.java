package com.hww.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/16-23:08
 * @Description: zookeeper也是配合ribbon一起使用，利用ribbon做的负载均衡
 */
@RestController
@Slf4j
public class OrderController {

    public static final String zk_service_url = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    private String paymentInfo() {
        log.info("develop");
        return restTemplate.getForObject(zk_service_url + "/payment/zk", String.class);
    }
}
