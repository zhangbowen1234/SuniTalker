package com.silver.chat.network.responsebean;

import java.util.List;

/**
 * Created by bowen on 2017/5/8.
 */

public class CreatGroupBean {

    /**
     * userId : 1
     * groupName : 测试群1
     * describe : 新建群组
     * addMethod : 1
     * friendIds : ["3","4","5"]
     * comment : 邀请进群
     */

    private String userId;
    private String groupName;
    private String describe;
    private int addMethod;
    private String comment;
    private List<String> friendIds;

    private static CreatGroupBean mInstance;

    public static CreatGroupBean getInstance() {
        if (null == mInstance) {
            synchronized (CreatGroupBean.class) {
                if (null == mInstance) {
                    mInstance = new CreatGroupBean();
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getAddMethod() {
        return addMethod;
    }

    public void setAddMethod(int addMethod) {
        this.addMethod = addMethod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(List<String> friendIds) {
        this.friendIds = friendIds;
    }
    @Override
    public String toString() {
        return "CreatGroupBean{" +
                "userId='" + userId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", describe='" + describe + '\'' +
                ", addMethod='" + addMethod + '\'' +
                ", friendIds='" + friendIds + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
