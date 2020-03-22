package com.hww.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/22-14:01
 * @Description:  Feign默认等待2s
 */
@Service
@FeignClient(value = "CLOUD-PROVIDER-SERVICE-HYSTRIX",fallback = OrderServiceFallBack.class)
public interface OrderService {

    @GetMapping("payment/hystrix/ok/{id}")
    String paymentInfo_ok(@PathVariable("id") Integer id);

    @GetMapping("payment/hystrix/timeout/{id}")
    String paymentInfo_timeout(@PathVariable("id") Integer id);


    @GetMapping("payment/hystrix/exception/{id}")
    String paymentInfo_exception(@PathVariable("id") Integer id);


}
