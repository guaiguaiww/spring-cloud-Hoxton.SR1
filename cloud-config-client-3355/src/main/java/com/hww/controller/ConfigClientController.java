package com.hww.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/24-22:20
 * @Description: @RefreshScope:动态刷新
 * 还需要运维人员：执行post请求刷新：curl -X POST "http:localhost:3355/actuator/refresh"
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return configInfo;
    }
}
