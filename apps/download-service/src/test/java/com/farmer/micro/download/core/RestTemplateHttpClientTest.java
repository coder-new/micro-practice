package com.farmer.micro.download.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateHttpClientTest {

    @Autowired
    private RestTemplateHttpClient restTemplateHttpClient;

    @Test
    public void testGet() {

        String url = "https://www.cnblogs.com/NeverCtrl-C/";

        String body = restTemplateHttpClient.get(url);

        System.out.println(body);
    }
}
