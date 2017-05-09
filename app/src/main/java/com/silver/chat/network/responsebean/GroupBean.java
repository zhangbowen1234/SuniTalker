package com.silver.chat.network.responsebean;

/**
 * Created by Joe on 2017/4/28.
 */

public class GroupBean {


    /**
     * userId : 0
     * avatar : AAAAAAA
     * groupName : XXXXX
     * GroupId : 25
     * groupRemark : WWW
     * createTime : 123456789
     * privilege : 1
     */

    private int userId;
    private String avatar;
    private String groupName;
    private int groupId;
    private String groupRemark;
    private long createTime;
    private int privilege;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int GroupId) {
        this.groupId = GroupId;
    }

    public String getGroupRemark() {
        return groupRemark;
    }

    public void setGroupRemark(String groupRemark) {
        this.groupRemark = groupRemark;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "GroupBean{" +
                "userId=" + userId +
                ", avatar='" + avatar + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupId=" + groupId +
                ", groupRemark='" + groupRemark + '\'' +
                ", createTime=" + createTime +
                ", privilege=" + privilege +
                '}';
    }
}
