package com.farmer.micro.common.api.message.blogger.relation;

import com.farmer.micro.common.api.message.BaseMessage;

public class BloggerRelationDownloadMessage extends BaseMessage{

    private String bloggerRelationPageUrl;

    private String bloggerName;

    private int pageIndex;

    private Boolean follower;

    public String getBloggerRelationPageUrl() {
        return bloggerRelationPageUrl;
    }

    public void setBloggerRelationPageUrl(String bloggerRelationPageUrl) {
        this.bloggerRelationPageUrl = bloggerRelationPageUrl;
    }

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Boolean getFollower() {
        return follower;
    }

    public void setFollower(Boolean follower) {
        this.follower = follower;
    }
}
