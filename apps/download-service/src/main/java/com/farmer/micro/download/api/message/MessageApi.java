package com.farmer.micro.download.api.message;

import com.farmer.micro.common.message.core.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/13
 */
@Component
public class MessageApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageApi.class);

    @JmsListener(destination = Constants.TEST_QUEUE_NAME)
    public void receive(String messageStr) {

        LOGGER.debug("receive message : {}",messageStr);
    }
}
