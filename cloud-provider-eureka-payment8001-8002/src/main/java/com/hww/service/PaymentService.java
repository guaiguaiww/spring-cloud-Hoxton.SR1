package com.hww.service;

import com.hww.entities.Payment;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/15-0:22
 * @Description:
 */

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
