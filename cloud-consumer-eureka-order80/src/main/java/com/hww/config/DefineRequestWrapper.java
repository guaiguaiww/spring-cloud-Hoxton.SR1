package com.hww.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.net.URI;


/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/19-9:27
 * @Description: 主要是用于改变uri地址的
 */
public class DefineRequestWrapper extends HttpRequestWrapper {

    private URI uri;

    /**
     * Create a new {@code HttpRequest} wrapping the given request object.
     *
     * @param request the request object to be wrapped
     */
    public DefineRequestWrapper(HttpRequest request, URI uri) {
        super(request);
        this.uri=uri;
    }

    @Override
    public URI getURI() {
        return uri;
    }
}
