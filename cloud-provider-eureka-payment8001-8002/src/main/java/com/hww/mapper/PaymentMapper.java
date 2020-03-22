package com.hww.mapper;

import com.hww.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/15-0:04
 * @Description:
 */
@Mapper
public interface PaymentMapper {

     int create(Payment payment);

      Payment getPaymentById(@Param("id") Long id);
}
