package com.farmer.micro.download.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/13
 */
@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate() {

        return new AsyncRestTemplate();
    }
}
