package com.hww.controller;

import com.hww.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/21-19:16
 * @Description: tomcat里的默认线程数是10个
 */
@RestController
@Slf4j
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_ok(id);
    }


    @GetMapping("payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_timeout(id);

    }

    @GetMapping("payment/hystrix/exception/{id}")
    public String paymentInfo_exception(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_exception(id);

    }
}
