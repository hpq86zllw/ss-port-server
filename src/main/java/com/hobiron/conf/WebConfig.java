package com.hobiron.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hobiron.interceptor.AuthKeyInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthKeyInterceptor(cacheManager)).addPathPatterns("/user/**");
    }

}
