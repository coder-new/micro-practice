package com.farmer.micro.download.api.message;

import com.farmer.micro.download.core.Constants;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/13
 */
@Component
public class MessageApi {

    @JmsListener(destination = Constants.DOWNLOAD_QUEUE_NAME)
    public void receive(String messageStr) {


    }
}
