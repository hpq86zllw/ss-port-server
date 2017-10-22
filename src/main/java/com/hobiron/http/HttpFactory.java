package com.hobiron.http;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import feign.Feign;
import feign.gson.GsonDecoder;

@Component
public class HttpFactory {

    @Cacheable("http")
    public Agent createAgent(String url) {
        return Feign.builder().decoder(new GsonDecoder()).target(Agent.class, url);
    }

    @Cacheable(cacheNames = "http", key = "#root.methodName")
    public WX createWX() {
        return Feign.builder().decoder(new GsonDecoder()).target(WX.class, "https://api.weixin.qq.com");
    }

}
