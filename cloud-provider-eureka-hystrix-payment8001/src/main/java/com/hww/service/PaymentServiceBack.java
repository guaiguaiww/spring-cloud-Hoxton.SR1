package com.hww.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/21-19:02
 * @Description: 服务降级
 */
@Service
public class PaymentServiceBack {


    public String paymentInfo_ok(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_ok,id: " + id + " success";
    }

    /**
     * @param id
     * @return fallbackMethod:服务降级回调的方法
     * commandProperties：设置hystrix的属性
     * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")-3s超时，触发服务降级
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String paymentInfo_timeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_timeout,id: " + id + " success time_out";
    }

    public String paymentInfo_timeout_handler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_timeout_handler,id: " + id + " success ";
    }

    /**

     * @param id
     * @return fallbackMethod:服务降级回调的方法
     * commandProperties：设置hystrix的属性
     * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")-3s超时，触发服务降级
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_exception_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_exception(Integer id) {
        int age = 10 / 0;
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_exception,id: " + id + " success time_out";
    }
    public String paymentInfo_exception_handler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_exception_handler,id: " + id + " success ";
    }

}
