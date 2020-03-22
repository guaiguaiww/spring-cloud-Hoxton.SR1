package com.hww.service;

import org.springframework.stereotype.Component;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/22-17:01
 * @Description: 服务降级处理方法
 */
@Component
public class OrderServiceFallBack implements OrderService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "paymentInfo_ok----服务降级";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "paymentInfo_timeout----服务降级";
    }

    @Override
    public String paymentInfo_exception(Integer id) {
        return "paymentInfo_exception----服务降级";
    }
}
