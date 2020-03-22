package com.hww.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/19-0:55
 * @Description: 自定义拦截器，实现负载均衡
 */
public class DefineHttpRequestInterceptor implements ClientHttpRequestInterceptor {


    private AtomicInteger count = new AtomicInteger(0);

    /**
     * 自旋锁
     * @return
     */
    public final int getAndIncrement() {
        int current;
        int next;
        for (; ; ) {
            current = this.count.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if (count.compareAndSet(current, next)) {
                System.out.println("第几次请求next = " + next);
                return next;
            }
        }
    }

    /**
     * Eureka的注册发现客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        URI originalUri = request.getURI();
        originalUri.getRawPath();
        String serviceName = originalUri.getHost();
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances.size() == 0) {
            return null;
        }
        int index = getAndIncrement() % instances.size();
        ServiceInstance serviceInstance = instances.get(index);
        URI uri = serviceInstance.getUri();
        StringBuilder sf = new StringBuilder(String.valueOf(uri));
        sf.append(originalUri.getRawPath());
        URI real =null;
        try {
             real = new URI(sf.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpRequest defineRequestWrapper = new DefineRequestWrapper(request, real);
        return execution.execute(defineRequestWrapper, body);
    }

}
