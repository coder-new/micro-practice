package com.farmer.micro.common.message.core;

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
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue downloadQueue;

    @Autowired
    private Queue parserQueue;

    @Autowired
    private Queue saveQueue;

    @Autowired
    private Queue bloggerQueue;

    @Override
    public void send(String messageStr, String destination) {

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
            default:
                LOGGER.warn("message destination : {} wrong!",destination);
        }

    }
}
