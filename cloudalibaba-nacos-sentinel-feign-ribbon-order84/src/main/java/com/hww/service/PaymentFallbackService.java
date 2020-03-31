package com.hww.service;

import com.hww.entities.CommonResult;
import com.hww.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @auther hww
 * @create 2020-02-25 18:30
 * 测试发现当服务宕机会自动降级
 * */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
