package com.hww.service;

import com.hww.entities.CommonResult;
import com.hww.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/20-17:33
 * @Description: OpenFeign是配合ribbon一起使用，利用ribbon做负载均衡的。
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE-EUREKA")
public interface PaymentService {

    @RequestMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 测试OpenFeign的超时控制
     * @return
     */
    @GetMapping("/payment/paymentFeignTimeOut")
    String paymentFeignTimeOut();
}
