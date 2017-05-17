package com.silver.chat.network.responsebean;

import java.io.Serializable;

/**
 * Created by Joe on 2017/5/16.
 */
public class GroupMemberBean implements Serializable{

    /**
     * userId : 26
     * imUserId : 8
     * groupNickname : 哈哈哈啊
     * nickName : Memory-G15莣
     * avatar : http://swift.sspaas.net:8080/v1/AU34cdb7/imicon/27656wuchun20173817103805.png
     * privilege : 1
     */

    private int userId;
    private int imUserId;
    private String groupNickname;
    private String nickName;
    private String avatar;
    private int privilege;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getImUserId() {
        return imUserId;
    }

    public void setImUserId(int imUserId) {
        this.imUserId = imUserId;
    }

    public String getGroupNickname() {
        return groupNickname;
    }

    public void setGroupNickname(String groupNickname) {
        this.groupNickname = groupNickname;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "GroupMemberBean{" +
                "userId=" + userId +
                ", imUserId=" + imUserId +
                ", groupNickname='" + groupNickname + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", privilege=" + privilege +
                '}';
    }
}
