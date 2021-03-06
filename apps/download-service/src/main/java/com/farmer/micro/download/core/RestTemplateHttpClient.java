package com.farmer.micro.download.core;

import com.farmer.micro.download.api.message.ListenerConstants;
import com.farmer.micro.download.cookie.CookieManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2017/12/16
 */
@Component
public class RestTemplateHttpClient {


    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateHttpClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @Autowired
    private CookieManager cookieManager;

    @Autowired
    private JmsListenerEndpointRegistry jmsListenerEndpointRegistry;

    public String get(String url) {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie",cookieManager.getCookie());

        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        int statusCode = responseEntity.getStatusCodeValue();
        if (404 == statusCode) {
            LOGGER.error("url : {} 404!",url);
            return null;
        } else if (200 != statusCode) {
            LOGGER.error("request url : {} error : {}!",url,responseEntity.getBody());
            return null;
        }

        return responseEntity.getBody();
    }

    public void asyncGet(String url,CallBackExecute callBackExecute) {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie",cookieManager.getCookie());

        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        ListenableFuture<ResponseEntity<String>> forEntity
                = asyncRestTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("get url : {} error : {}",url, ex.getMessage());
                jmsListenerEndpointRegistry
                        .getListenerContainer(ListenerConstants.Id.ID_1).stop();
            }

            @Override
            public void onSuccess(ResponseEntity<String> result) {

                int statusCodeValue = result.getStatusCodeValue();
                if (200 == statusCodeValue) {
                    callBackExecute.execute(result.getBody());
                } else {
                    LOGGER.error("get url : {} error,status : {}",url,statusCodeValue);
                    jmsListenerEndpointRegistry
                            .getListenerContainer(ListenerConstants.Id.ID_1).stop();
                }

            }
        });
    }
}
