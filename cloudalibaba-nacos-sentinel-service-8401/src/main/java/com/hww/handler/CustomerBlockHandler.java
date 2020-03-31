package com.hww.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hww.entities.CommonResult;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/31-17:48
 * @Description:
 */
public class CustomerBlockHandler {

    public static CommonResult handleException1(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 自定义异常处理1");
    }

    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 自定义异常处理2");
    }
}
