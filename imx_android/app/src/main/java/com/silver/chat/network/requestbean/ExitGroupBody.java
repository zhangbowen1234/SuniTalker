package com.silver.chat.network.requestbean;

import com.silver.chat.network.responsebean.DisscusBean;

import java.io.Serializable;

/**
 * Created by bowen .
 */

public class ExitGroupBody implements Serializable{


    /**
     * userId : 28
     * groupId : 63
     */

    private String userId;
    private String groupId;
    private static ExitGroupBody mInstance;

    public static ExitGroupBody getInstance() {
        if (null == mInstance) {
            synchronized (ExitGroupBody.class) {
                if (null == mInstance) {
                    mInstance = new ExitGroupBody();
                }
            }
        }
        return mInstance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "ExitGroupBody{" +
                "userId='" + userId + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
