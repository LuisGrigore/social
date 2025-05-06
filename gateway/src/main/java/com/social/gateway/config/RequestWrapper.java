package com.social.gateway.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.*;

public class RequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String> headerMap = new HashMap<>();

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        String headerValue = headerMap.get(name);
        return headerValue != null ? headerValue : super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> names = new HashSet<>(headerMap.keySet());
        names.addAll(Collections.list(super.getHeaderNames()));
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        String headerValue = headerMap.get(name);
        if (headerValue != null) {
            return Collections.enumeration(List.of(headerValue));
        }
        return super.getHeaders(name);
    }
}


