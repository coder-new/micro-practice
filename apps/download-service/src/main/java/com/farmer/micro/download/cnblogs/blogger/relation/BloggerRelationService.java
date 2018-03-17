package com.farmer.micro.download.cnblogs.blogger.relation;

import com.farmer.micro.download.core.RestTemplateHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BloggerRelationService {

    @Autowired
    private RestTemplateHttpClient restTemplateHttpClient;

    public void handle() {

        restTemplateHttpClient.get("http://www.cnblogs.com");
    }
}
