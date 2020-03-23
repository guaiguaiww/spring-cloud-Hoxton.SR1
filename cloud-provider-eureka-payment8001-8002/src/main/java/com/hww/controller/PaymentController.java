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
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private DiscoveryClient discoveryClient;

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



    @GetMapping(value = "/payment/testAnnotation")
    public CommonResult<Payment> testAnnotation(Long id,String userName) {
        Payment payment = paymentService.getPaymentById(id);
        if (null != payment) {
            return new CommonResult<>(200, "get-successfully,service-port:" + port, payment);
        } else {
            return new CommonResult<>(200, "get-failed", null);
        }
    }

    /**
     * 配合主程序上面的@EnableDiscoveryClient注解使用
     * @return
     */
    @GetMapping("/payment/discovery")
    public Object getDiscovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(item -> {
            log.info("注册在Eureka-Server上的服务有:" + item);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE-EUREKA");
        instances.forEach(item -> {
            log.info("CLOUD-PAYMENT-SERVICE服务列表实例：" + item.getHost() + "," + item.getPort() + "," + item.getUri() + "," + item.getServiceId());
        });
        return this.discoveryClient;
    }

    /**
     * 测试OpenFeign的超时控制
     * @return
     */

    @GetMapping("/payment/paymentFeignTimeOut")
    public String paymentFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;

    }

}
