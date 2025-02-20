package com.example.ordersystem.common.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignTokenConfig {

    @Bean
    public RequestInterceptor requestInterceptor(){ //모든feign요청 낚아채서 token생성
        return request -> {
          String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
          request.header(HttpHeaders.AUTHORIZATION, token);
        };
    }
}
