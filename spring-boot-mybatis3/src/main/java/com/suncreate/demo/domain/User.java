package com.suncreate.demo.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    private Long id;
    @JSONField(name="name")
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
