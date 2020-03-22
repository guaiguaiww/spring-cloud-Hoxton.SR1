package com.hww.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/21-17:15
 * @Description:
 */
@Configuration
public class ApplicationConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
