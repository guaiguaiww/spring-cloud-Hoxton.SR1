package com.hww.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;


/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/22-20:32
 * @Description: 服务熔断
 */
@Service
public class PaymentServiceFuse {

    /**
     * HystrixCommandProperties
     *Description：10s钟内，10次请求，失败概率在60%,开启服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "fallback.enabled", value = "true"),//是否开启服务降级-默认为true
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启服务熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),//失败率达到多少跳闸
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")//超时时间
    })
    public String paymentCircuitBreaker(Integer id) {
        System.out.println("--------"+id);
        if (id < 0) {
            throw new RuntimeException("******** id 不能为负数");
        }
        String serialNUmber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " 调用成功流水号为： " + serialNUmber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id 不能为负数，请稍后重试 "+id;
    }

}
