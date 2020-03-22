package com.hww.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/21-19:02
 * @Description:
 */
@Service
public class PaymentService {


    public String paymentInfo_ok(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_ok,id: " + id + " success";
    }

    public String paymentInfo_timeout(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_timeout,id: " + id + " success time_out";
    }

    public String paymentInfo_exception(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_exception,id: " + id + " success time_out";
    }

}
