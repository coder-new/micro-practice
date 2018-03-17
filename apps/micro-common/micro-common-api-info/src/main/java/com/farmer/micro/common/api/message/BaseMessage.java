package com.farmer.micro.common.api.message;

import java.io.Serializable;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/16
 */
public class BaseMessage implements Serializable {

    private static final long serialVersionUID = 1001101278115368918L;


    private String messageId;

    private String messageType;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
