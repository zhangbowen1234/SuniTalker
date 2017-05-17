package com.silver.chat.network.requestbean;

/**
 * Created by Joe on 2017/5/15.
 * 申请加群的请求体
 */

public class AskJoinGroup {

    /**
     * sourceId : 2
     * sourceName : Memory_G
     * sourceAvatar : http://swift.sspaas.net:8080/v1/AUTH_bbc95ed02b384596a0d3ec33c934cdb7/imicon/27092wuchun20173320033315.png
     * comment : 申请入群
     * targetId : 13
     * groupId : 62
     * groupName : 测试群5
     * groupAvatar : http://swift.sspaas.net:8080/v1/AUTH_bbc95ed02b384596a0d3ec33c934cdb7/imicon/99461wuchun20173810023827.png
     * appName : innerapp
     */

    private static AskJoinGroup mInstance;

    public static AskJoinGroup getInstance() {
        if (null == mInstance) {
            synchronized (AskJoinGroup.class) {
                if (null == mInstance) {
                    mInstance = new AskJoinGroup();
                }
            }
        }
        return mInstance;
    }


    private String sourceId;
    private String sourceName;
    private String sourceAvatar;
    private String comment;
    private String targetId;
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

    @Override
    public String toString() {
        return "AskJoinGroup{" +
                "sourceId='" + sourceId + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", sourceAvatar='" + sourceAvatar + '\'' +
                ", comment='" + comment + '\'' +
                ", targetId='" + targetId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupAvatar='" + groupAvatar + '\'' +
                ", appName='" + appName + '\'' +
                '}';
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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
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
}
