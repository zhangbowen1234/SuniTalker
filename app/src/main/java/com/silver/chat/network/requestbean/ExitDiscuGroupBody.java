package com.silver.chat.network.requestbean;

import java.io.Serializable;

/**
 * Created by hibon on 2017/5/18.
 */

public class ExitDiscuGroupBody implements Serializable{

    private String userId;//用户id
    private String discuGroupId;//讨论组ID

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiscuGroupId() {
        return discuGroupId;
    }

    public void setDiscuGroupId(String discuGroupId) {
        this.discuGroupId = discuGroupId;
    }

    @Override
    public String toString() {
        return "ExitDiscuGroupBody{" +
                "userId='" + userId + '\'' +
                ", discuGroupId='" + discuGroupId + '\'' +
                '}';
    }
}
