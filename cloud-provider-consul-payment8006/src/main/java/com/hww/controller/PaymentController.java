package com.hww.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/15-0:26
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {



    @Value("${server.port}")
    private String port;

    /**
     * 自测
     * @return
     */
    @RequestMapping("/payment/consul")
    public String paymentWithConsul() {
        return "spring cloud with consul: " + port +" "+ UUID.randomUUID().toString();
    }

}
