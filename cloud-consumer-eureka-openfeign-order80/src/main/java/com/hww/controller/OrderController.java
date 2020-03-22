package com.hww.controller;

import com.hww.annotation.OperateSysLog;
import com.hww.entities.CommonResult;
import com.hww.entities.Payment;
import com.hww.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/20-17:32
 * @Description:
 */
@RestController
@Slf4j
public class OrderController {


    @Autowired
    private PaymentService paymentService;


    @OperateSysLog(moduleName = "支付服务", content = "OpenFeign的远程调用测试")
    @GetMapping("/consumer/payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return paymentService.getPaymentById(id);
    }

    /**
     * OpenFeign的客户端默认1s就要拿到结果
     *
     * @return
     */
    @OperateSysLog(moduleName = "支付服务", content = "OpenFeign的超时控制")
    @GetMapping("consumer/payment/paymentFeignTimeOut/{id}")
    public String paymentFeignTimeOut(@PathVariable("id")  String id) {
        return paymentService.paymentFeignTimeOut();
    }

}
