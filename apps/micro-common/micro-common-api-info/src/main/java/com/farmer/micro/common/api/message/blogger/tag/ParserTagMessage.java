package com.farmer.micro.common.api.message.blogger.tag;

import com.farmer.micro.common.api.message.BaseMessage;

public class ParserTagMessage extends BaseMessage{

    private String body;

    private String bloggerName;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }
}
