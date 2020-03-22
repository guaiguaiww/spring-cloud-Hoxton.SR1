package com.hww.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/16-23:13
 * @Description: 在没有使用ribbon之前还是使用restTemplate做负载均衡
 */
@Configuration
public class ApplicationConfig {
    /**
     *  @LoadBalanced
     * 赋予RestTemplate负载均衡的能力
     * 使用RestTemplate进行rest操作的时候，会自动使用负载均衡策略，它内部会在RestTemplate中加入LoadBalancerInterceptor这个拦截器，这个拦截器的作用就是使用负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
