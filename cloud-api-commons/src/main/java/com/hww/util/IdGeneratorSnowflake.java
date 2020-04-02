package com.hww.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/4/2-13:46
 * @Description:
 */
@Component
@Slf4j
public class IdGeneratorSnowflake {
    /**
     * workerId: 0~31
     */
    private long workerId = 0;
    private long dataCenterId = 1;

    private Snowflake snowflake = IdUtil.createSnowflake(workerId, dataCenterId);

    @PostConstruct
    public void init() {

        try {
            log.info("当前机器的localhostStr"+NetUtil.getLocalhostStr());
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId:{}", workerId);
        } catch (Exception e) {
            log.info("当前机器的workerId获取失败");
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }


    public synchronized long snowFlake() {
        return snowflake.nextId();
    }

    public synchronized long snowFlake(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }


    public static void main(String[] args) {

        IdGeneratorSnowflake idGeneratorSnowflake = new IdGeneratorSnowflake();

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            threadPool.submit(() -> {
                System.out.println(idGeneratorSnowflake.snowFlake());
            });
        }

        System.out.println(NetUtil.getLocalhostStr());
        System.out.println(NetUtil.getLocalMacAddress());
        System.out.println(NetUtil.ipv4ToLong(NetUtil.getLocalhostStr()));

        threadPool.shutdown();

    }

}
