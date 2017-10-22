package com.hobiron.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hobiron.http.HttpFactory;
import com.hobiron.http.WX;

@Configuration
public class HttpConfig {

    @Autowired
    private HttpFactory httpFactory;

    @Bean
    public WX wx() {
        return httpFactory.createWX();
    }

}
