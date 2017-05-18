package com.silver.chat.network.requestbean;

import java.io.Serializable;

/**
 * Created by hibon on 2017/5/18.
 */

public class SetDiscuMsgRemindBody implements Serializable {

    private int userId;// 用户id
    private int groupId;//讨论组id
    private int notification;//讨论组消息提醒设置（1-接收消息;2-消息免打扰）

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "SetDiscuMsgRemindBody{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                ", notification=" + notification +
                '}';
    }
}
