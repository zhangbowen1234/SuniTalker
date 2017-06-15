package com.silver.chat.network.requestbean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by hibon on 2017/5/18.
 */

public class AddFriendDiscuBody implements Serializable {

    private String sourceId;
    private String sourceName;
    private String sourceAvatar;
    private String comment;
    private String[] targetIds;
    private String groupId;
    private String groupName;
    private String groupAvatar;
    private String appName;


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

    public String[] getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
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

    @Override
    public String toString() {
        return "AddFriendDiscuBody{" +
                "sourceId='" + sourceId + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", sourceAvatar='" + sourceAvatar + '\'' +
                ", comment='" + comment + '\'' +
                ", targetIds=" + Arrays.toString(targetIds) +
                ", groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupAvatar='" + groupAvatar + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}
