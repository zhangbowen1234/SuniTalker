package com.silver.chat.entity;

/**
 * Created by Joe on 2017/4/28.
 */

public class GroupBean {
    private String url;
    private String groupName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "GroupBean{" +
                "url='" + url + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
