package com.silver.chat.network.requestbean;

import java.io.Serializable;

/**
 * Created by hibon on 2017/5/18.
 */

public class SetGroupManagerBody implements Serializable {

    private int groupOwnerId;//群主id
    private int groupManager;//群成员id
    private int groupId;  //群id
    private static SetGroupManagerBody mInstance;

    public static SetGroupManagerBody getInstance() {
        if (null == mInstance) {
            synchronized (SetGroupManagerBody.class) {
                if (null == mInstance) {
                    mInstance = new SetGroupManagerBody();
                }
            }
        }
        return mInstance;
    }
    public int getGroupOwnerId() {
        return groupOwnerId;
    }

    public void setGroupOwnerId(int groupOwnerId) {
        this.groupOwnerId = groupOwnerId;
    }

    public int getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(int groupManager) {
        this.groupManager = groupManager;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "SetGroupManagerBody{" +
                "groupOwnerId=" + groupOwnerId +
                ", groupManager=" + groupManager +
                ", groupId=" + groupId +
                '}';
    }
}
