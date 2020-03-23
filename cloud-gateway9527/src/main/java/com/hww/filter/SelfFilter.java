package com.hww.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/24-1:43
 * @Description: 自定义日志过滤器
 */
@Slf4j
@Component
//@Order(0)
public class SelfFilter implements GlobalFilter,Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("coming in SelfFilter " + new Date());
        ServerHttpRequest request = exchange.getRequest();
        String username = request.getQueryParams().getFirst("username");
        if (username ==null ){
            log.info("，用户为空，非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        //传递给后面的过滤器进行验证
        return chain.filter(exchange);
    }

    /**
     * 过滤器顺序，数字越小优先级越高 ,也可以使用注解@Order(0)
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
