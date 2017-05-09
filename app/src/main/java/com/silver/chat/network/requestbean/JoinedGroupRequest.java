package com.silver.chat.network.requestbean;

import java.io.Serializable;

/**
 * Created by Joe on 2017/5/8.
 * 用户加入的群信息的请求体的bean
 */

public class JoinedGroupRequest implements Serializable {
    private int userId;
    private String groupName;
    private int groupId;
    private String groupRemark;
    private static JoinedGroupRequest mInstance;

    public static JoinedGroupRequest getInstance() {
        if (null == mInstance) {
            synchronized (JoinedGroupRequest.class) {
                if (null == mInstance) {
                    mInstance = new JoinedGroupRequest();
                }
            }
        }
        return mInstance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupRemark() {
        return groupRemark;
    }

    public void setGroupRemark(String groupRemark) {
        this.groupRemark = groupRemark;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
