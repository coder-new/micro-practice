package com.farmer.micro.download.config;

import com.farmer.micro.download.api.message.ListenerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/13
 */
@Configuration
public class RestTemplateConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateConfig.class);

    @Bean
    public ResponseErrorHandler responseErrorHandler(JmsListenerEndpointRegistry jmsListenerEndpointRegistry) {

        return new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {

                int statusCode = response.getRawStatusCode();
                if (200 != statusCode) {
                    return true;
                }

                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

                int statusCode = response.getRawStatusCode();
                if (404 != statusCode) {
                    jmsListenerEndpointRegistry
                            .getListenerContainer(ListenerConstants.Id.ID_2).stop();
                }
            }
        };
    }

    @Bean
    public RestTemplate restTemplate(ResponseErrorHandler responseErrorHandler) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(responseErrorHandler);
        return restTemplate;
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate() {

        return new AsyncRestTemplate();
    }
}
