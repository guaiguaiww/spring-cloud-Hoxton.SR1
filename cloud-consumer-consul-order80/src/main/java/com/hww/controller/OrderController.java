package com.hww.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/16-23:08
 * @Description:
 */
@RestController
@Slf4j
public class OrderController {

    public static final String CONSUL_SERVICE_URL = "http://cloud-payment-service-consul";



    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    private String paymentInfo() {
        return restTemplate.getForObject(CONSUL_SERVICE_URL + "/payment/consul", String.class);
    }
}
