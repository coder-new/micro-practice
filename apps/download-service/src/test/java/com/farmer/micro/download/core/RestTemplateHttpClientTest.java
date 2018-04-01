package com.farmer.micro.download.core;

import com.farmer.micro.common.util.file.FileUtil;
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

        String bloggerName = "nokiaguy";

        String url = "https://www.cnblogs.com/" + bloggerName + "/tag/";

        String body = restTemplateHttpClient.get(url);

        writeToFile(body,bloggerName);
    }

    private void writeToFile(String body,String bloggerName) {

        FileUtil.writeToFile(body,"C:\\Users\\aprim\\tempfile\\" + bloggerName + ".html");
    }
}
