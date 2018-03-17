package com.farmer.micro.common.message.core;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/16
 */
@EnableJms
@Configuration
public class QueueConfig {

    @Bean
    public Queue messageQueue() {
        ActiveMQQueue queue = new ActiveMQQueue(Constants.QUEUE_NAME);
        return queue;
    }

    @Bean("downloadQueue")
    public Queue downloadQueue() {

        ActiveMQQueue queue = new ActiveMQQueue(Constants.DOWNLOAD_QUEUE_NAME);
        return queue;
    }

    @Bean("parserQueue")
    public Queue parserQueue() {

        ActiveMQQueue queue = new ActiveMQQueue(Constants.PARSER_QUEUE_NAME);
        return queue;
    }

    @Bean("saveQueue")
    public Queue saveQueue() {

        ActiveMQQueue queue = new ActiveMQQueue(Constants.SAVE_QUEUE_NAME);
        return queue;
    }

    @Bean("bloggerQueue")
    public Queue bloggerQueue() {

        ActiveMQQueue queue = new ActiveMQQueue(Constants.BLOGGER_QUEUE_NAME);
        return queue;
    }

}