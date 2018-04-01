package com.farmer.micro.common.api.message.blogger.tag;

import com.farmer.micro.common.api.message.BaseMessage;

public class DownTagMessage extends BaseMessage{

    private String bloggerName;

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }
}
