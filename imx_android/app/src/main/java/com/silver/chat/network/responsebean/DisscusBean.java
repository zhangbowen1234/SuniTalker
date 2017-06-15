package com.silver.chat.network.responsebean;

import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by bowen.
 * 用户加入的群信息的请求体的bean
 */
@DatabaseTable(tableName = "disscus_bean")
public class DisscusBean implements Serializable{

    /**
     * discuGroupId : XXXXXXXXXXX
     * discuGroupName : SSSSSSSS
     * discuGroupAvatar : AAAAAAAAAA
     * createTime : 123456789
     * creatorId : 98756321
     */

    private String discuGroupId;
    private String discuGroupName;
    private String discuGroupAvatar;
    private long createTime;
    private long creatorId;

    private static DisscusBean mInstance;

    public static DisscusBean getInstance() {
        if (null == mInstance) {
            synchronized (DisscusBean.class) {
                if (null == mInstance) {
                    mInstance = new DisscusBean();
                }
            }
        }
        return mInstance;
    }

    public String getDiscuGroupId() {
        return discuGroupId;
    }

    public void setDiscuGroupId(String discuGroupId) {
        this.discuGroupId = discuGroupId;
    }

    public String getDiscuGroupName() {
        return discuGroupName;
    }

    public void setDiscuGroupName(String discuGroupName) {
        this.discuGroupName = discuGroupName;
    }

    public String getDiscuGroupAvatar() {
        return discuGroupAvatar;
    }

    public void setDiscuGroupAvatar(String discuGroupAvatar) {
        this.discuGroupAvatar = discuGroupAvatar;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return "DisscusBean{" +
                "discuGroupId='" + discuGroupId + '\'' +
                ", discuGroupName='" + discuGroupName + '\'' +
                ", discuGroupAvatar='" + discuGroupAvatar + '\'' +
                ", createTime=" + createTime +
                ", creatorId=" + creatorId +
                '}';
    }
}
