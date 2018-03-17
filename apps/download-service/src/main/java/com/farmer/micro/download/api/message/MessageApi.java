package com.farmer.micro.download.api.message;

import com.farmer.micro.common.api.message.BaseMessage;
import com.farmer.micro.common.api.message.blogger.relation.BloggerRelationDownloadMessage;
import com.farmer.micro.common.message.core.Constants;
import com.farmer.micro.common.message.serialze.SerialzeUtil;
import com.farmer.micro.download.cnblogs.blogger.relation.BloggerRelationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/13
 */
@Component
public class MessageApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageApi.class);

    @Autowired
    private BloggerRelationService bloggerRelationService;

    @Autowired
    private SerialzeUtil serialzeUtil;

    @JmsListener(destination = Constants.TEST_QUEUE_NAME,id = ListenerConstants.Id.ID_1)
    public void receive(String messageStr) {

        LOGGER.debug("receive message : {}",messageStr);

        try {
            BloggerRelationDownloadMessage bloggerRelationDownloadMessage
                    = serialzeUtil.serialze(messageStr,BloggerRelationDownloadMessage.class);

            bloggerRelationService.handle(bloggerRelationDownloadMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
