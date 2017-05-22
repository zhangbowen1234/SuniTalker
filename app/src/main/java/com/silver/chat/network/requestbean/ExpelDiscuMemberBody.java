package com.silver.chat.network.requestbean;

import java.io.Serializable;

/**
 * Created by hibon on 2017/5/18.
 */

public class ExpelDiscuMemberBody implements Serializable{

    private String managerId;//管理员id
    private String userId; //用户id
    private String discuGroupId;//讨论组id

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

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
        return "ExpelDiscuMemberBody{" +
                "managerId='" + managerId + '\'' +
                ", userId='" + userId + '\'' +
                ", discuGroupId='" + discuGroupId + '\'' +
                '}';
    }
}
