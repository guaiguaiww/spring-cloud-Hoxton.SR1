package com.hww;

import com.netflix.hystrix.HystrixMetrics;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/21-18:55
 * @Description: @EnableEurekaClient:开启Eureka-Client，注册服务到注册中心
 * @EnableCircuitBreaker :开启熔断器
 */
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class EurekaHystrixMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaHystrixMain8001.class, args);
    }

    /**
     * 此配置  是为了服务监控而配置，与服务容错本身无关，springcloud升级后的坑
     * ServletRegistrationBean因为Springboot的默认路径不是"/hystrix.stream"
     * 只需要在自己的项目配置一个路径为"/hystrix.stream"的Servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;

    }
}
