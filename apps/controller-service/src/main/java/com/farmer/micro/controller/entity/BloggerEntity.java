package com.farmer.micro.controller.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "spider_blogger")
public class BloggerEntity {

    @Id
    private Integer id;

    private String bloggerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }
}
