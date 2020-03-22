package com.hww.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/15-14:35
 * @Description:
 */
@Configuration
@Slf4j
public class ApplicationConfig {
    /**
     *  @LoadBalanced：赋予RestTemplate负载均衡的能力
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    /**
     * 实现ribbon一样的负载均衡
     * @return
     */
    @Bean
    @Qualifier("define")
    public RestTemplate restTemplateDefine() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors=new ArrayList<>();
        interceptors.add(defineHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        log.info(""+restTemplate.getInterceptors().size());
        return restTemplate;
    }

    @Bean
    public DefineHttpRequestInterceptor defineHttpRequestInterceptor(){
        return new DefineHttpRequestInterceptor();
    }
}
