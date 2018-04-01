package com.farmer.micro.common.message.core;

import com.farmer.micro.common.api.message.BaseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/16
 */
@Component
public class ActiveMqMessageSend implements ISendMessage{

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMqMessageSend.class);

    @Autowired
    private Queue testQueue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue messageQueue;

    @Autowired
    private Queue downloadQueue;

    @Autowired
    private Queue parserQueue;

    @Autowired
    private Queue saveQueue;

    @Autowired
    private Queue bloggerQueue;

    @Autowired
    private Queue tagDownloadQueue;

    @Autowired
    private Queue tagParserQueue;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void send(String messageStr, String destination) {

        LOGGER.debug("send message : {} to : {}",messageStr,destination);

        switch (destination) {

            case Constants.SAVE_QUEUE_NAME:
                jmsTemplate.convertAndSend(saveQueue,messageStr);
                break;
            case Constants.DOWNLOAD_QUEUE_NAME:
                jmsTemplate.convertAndSend(downloadQueue,messageStr);
                break;
            case Constants.PARSER_QUEUE_NAME:
                jmsTemplate.convertAndSend(parserQueue,messageStr);
                break;
            case Constants.BLOGGER_QUEUE_NAME:
                jmsTemplate.convertAndSend(bloggerQueue,messageStr);
                break;
            case Constants.TEST_QUEUE_NAME:
                jmsTemplate.convertAndSend(testQueue,messageStr);
                break;
            case Constants.BLOGGER_TAG_DOWNLOAD_QUEUE:
                jmsTemplate.convertAndSend(tagDownloadQueue,messageStr);
                break;
            case Constants.BLOGGER_TAG_PARSER_QUEUE:
                jmsTemplate.convertAndSend(tagParserQueue,messageStr);
                break;
            default:
                LOGGER.warn("message destination : {} wrong!",destination);
        }

    }

    @Override
    public void send(BaseMessage baseMessage, String destination) {

        String messageStr = null;
        try {
            messageStr = objectMapper.writeValueAsString(baseMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (null != messageStr) {
            send(messageStr,destination);
        }
    }
}
