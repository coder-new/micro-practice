package com.farmer.micro.download.core;

import com.farmer.micro.common.message.core.ActiveMqMessageSend;
import com.farmer.micro.common.message.core.Constants;
import com.farmer.micro.common.message.core.ISendMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {


    @Autowired
    private ActiveMqMessageSend activeMqMessageSend;

    @Test
    public void test() {

        activeMqMessageSend.send("test001", Constants.TEST_QUEUE_NAME);

        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
