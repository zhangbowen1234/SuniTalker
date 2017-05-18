package com.silver.chat.network.requestbean;

import java.io.Serializable;

/**
 * Created by hibon on 2017/5/18.
 */

public class AgreeFriendAddBody implements Serializable {
    private String sourceId; //当前用户id
    private String sourceName; //当前用户昵称
    private String sourceAvatar; //当前用户头像
    private String targetId; //要添加人的id
    private String appName; //innerapp

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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "AgreeFriendAddBody{" +
                "sourceId='" + sourceId + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", sourceAvatar='" + sourceAvatar + '\'' +
                ", targetId='" + targetId + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}
