package com.silver.chat.network.responsebean;

import com.silver.chat.network.requestbean.CreatGroupBean;

import java.util.List;

/**
 * Created by bowen on 2017/5/25.
 */

public class AddGroupMemBean {
    /**
     * sourceId : 1
     * sourceName : xiaoming
     * sourceAvatar : 2
     * comment : 1
     * targetIds : ["1","2","3"]
     * groupId : 1
     * groupName : 1
     * groupAvatar : 1
     * appName : innerapp
     */

    private String sourceId;
    private String sourceName;
    private String sourceAvatar;
    private String comment;
    private String groupId;
    private String groupName;
    private String groupAvatar;
    private String appName;
    private List<String> targetIds;
    private static AddGroupMemBean mInstance;

    public static AddGroupMemBean getInstance() {
        if (null == mInstance) {
            synchronized (AddGroupMemBean.class) {
                if (null == mInstance) {
                    mInstance = new AddGroupMemBean();
                }
            }
        }
        return mInstance;
    }
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceAvatar() {
        return sourceAvatar;
    }

    public void setSourceAvatar(String sourceAvatar) {
        this.sourceAvatar = sourceAvatar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupAvatar() {
        return groupAvatar;
    }

    public void setGroupAvatar(String groupAvatar) {
        this.groupAvatar = groupAvatar;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<String> getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(List<String> targetIds) {
        this.targetIds = targetIds;
    }

    @Override
    public String toString() {
        return "AddGroupMemBean{" +
                "sourceId='" + sourceId + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", sourceAvatar='" + sourceAvatar + '\'' +
                ", comment='" + comment + '\'' +
                ", groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupAvatar='" + groupAvatar + '\'' +
                ", appName='" + appName + '\'' +
                ", targetIds=" + targetIds +
                '}';
    }
}
