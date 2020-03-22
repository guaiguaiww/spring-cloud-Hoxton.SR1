package com.hww.controller;

import com.hww.entities.CommonResult;
import com.hww.entities.Payment;
import com.hww.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/15-0:26
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);

        if (result > 0) {
            return new CommonResult<>(200, "created-successfully,service-port:" + port, result);
        } else {
            return new CommonResult<>(200, "create-failed", result);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if (null != payment) {
            return new CommonResult<>(200, "get-successfully,service-port:" + port, payment);
        } else {
            return new CommonResult<>(200, "get-failed", null);
        }
    }


    /**
     * 自测
     * @return
     */
    @RequestMapping("/payment/zk")
    public String paymentWithZookeeper() {
        return "spring cloud with zookeeper: " + port +" "+ UUID.randomUUID().toString();
    }


}
