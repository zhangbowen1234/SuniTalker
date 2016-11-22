package com.silver.chat.entity;

import com.silver.chat.view.recycleview.entity.MultiItemEntity;

/**
 * 作者：Fandy on 2016/11/17 10:37
 * 邮箱：fandy618@hotmail.com
 */

public class ChatBean extends BaseBean implements MultiItemEntity {

    public static final int CHAT_SYSTEM = 1;
    public static final int CHAT_SINGLR = 2;
    public static final int CHAT_GROUP = 3;

    private String userId;
    private String userName;
    private String content;
    private int type;

    public ChatBean(String userId, String userName, String content, int type) {
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int getItemType() {
        return type;
    }
}
