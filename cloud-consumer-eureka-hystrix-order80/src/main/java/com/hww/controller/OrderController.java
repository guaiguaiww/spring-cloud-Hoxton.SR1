package com.hww.controller;

import com.hww.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/22-14:05
 * @Description:
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "order_payment_global_fallback")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 使用jmeter压测后出现卡顿的原因：tomcat里面的工作线程已经被挤占完毕
     *
     * @param id
     * @return
     */
    @GetMapping("consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        return orderService.paymentInfo_ok(id);
    }

    /**
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @GetMapping("consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id) {
         try {
                     TimeUnit.SECONDS.sleep(5);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
        return orderService.paymentInfo_timeout(id);
    }

    @GetMapping("consumer/payment/hystrix/exception/{id}")
    @HystrixCommand
    public String paymentInfo_exception(@PathVariable("id") Integer id) {
        String result = orderService.paymentInfo_exception(id);
        int age = 10 / 0;
        return result;
    }

    /**
     * 定制服务降级处理
     * @return
     */
    public String paymentInfo_timeout_handler(Integer id) {
        return "80消费者：对方系统超时，请稍后重试："+id ;
    }

    /**
     * 全局服务降级处理方法
     * @return
     */
    public String order_payment_global_fallback() {
        return "全局异常处理：80消费者,请稍后重试：" ;
    }
}
