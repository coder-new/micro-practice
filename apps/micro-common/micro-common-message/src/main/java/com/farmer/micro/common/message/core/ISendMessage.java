package com.farmer.micro.common.message.core;

import com.farmer.micro.common.api.message.BaseMessage;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/16
 */
public interface ISendMessage {

    void send(String messageStr,String destination);

    void send(BaseMessage baseMessage,String destination);
}
