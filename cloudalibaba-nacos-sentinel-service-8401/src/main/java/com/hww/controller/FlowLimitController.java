package com.hww.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/28-16:29
 * @Description:
 */
@RestController
@Slf4j
public class FlowLimitController {


    /**
     * 测试流控模式，效果--快速失败
     *
     * @return
     */
    @GetMapping("/testA")
    public String testA() {
        log.info(Thread.currentThread().getName() + "\t" + "...testA");
        return "------testA";
    }

    /**
     * 测试流控模式，效果--关联失败
     *
     * @return
     */
    @GetMapping("/testB")
    @SentinelResource("testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "------testB";
    }


    /**
     * 测试降级规则---平均响应时间
     *
     * @return
     */
    @GetMapping("/testD")
    public String testRT() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "\t" + "...testRT");
        return "------testD";
    }


    /**
     * 测试降级规则---异常比例
     *
     * @return
     */
    @GetMapping("/testE")
    public String testE() {
        int age = 10 / 0;
        log.info(Thread.currentThread().getName() + "\t" + "...testE");
        return "------testE";
    }


    /**
     * 测试热点key
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2)
    {
        //int age = 10/0;
        return "------testHotKey";
    }
    public String deal_testHotKey (String p1, String p2, BlockException exception)
    {
        return "------deal_testHotKey,o(╥﹏╥)o";  //sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
    }

}