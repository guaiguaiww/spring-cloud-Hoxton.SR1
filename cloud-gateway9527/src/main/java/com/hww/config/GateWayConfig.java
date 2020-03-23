package com.hww.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/23-23:07
 * @Description: 采用硬编码实现网关配置
 */
@Configuration
public class GateWayConfig {
    /**
     * 配置了一个id为path_route_payment的路由规则，当访问localhost:9527/guonei会请求转发到http://news.baidu.com/guonei
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_payment", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();

    }


}
