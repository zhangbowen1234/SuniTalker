package com.silver.chat.entity;

import com.silver.chat.view.recycleview.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 作者：Fandy on 2016/11/17 10:37
 * 邮箱：fandy618@hotmail.com
 */

public class ChatBean extends BaseBean implements MultiItemEntity,Serializable, Comparable {
    /**
     * 系统消息
     */
    public static final int CHAT_SYSTEM = 1;
    /**
     * 个人消息
     */
    public static final int CHAT_SINGLR = 2;
    /**
     * 群消息
     */
    public static final int CHAT_GROUP = 3;
    /**
     * 讨论组消息
     */
    public static final int CHAT_DISCUSSION_GROUP = 4;
    /**
     * 群通知
     */
    public static final int CHAT_GROUP_NOTICE = 5;
    /**
     * 是否置顶
     */
    public int top;

    /**
     * 置顶时间
     **/
    public long time;

    /**
     * 头像
     */
    public String avatar;

    private String userId;
    private String userName;
    private String content;
    private int type;
    private String sendTime;

    public ChatBean(String userId, String userName, String avatar, int type,String sendTime) {
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.type = type;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return type;
    }
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int compareTo(Object another) {
        if (another == null || !(another instanceof ChatBean)) {
            return -1;
        }

        ChatBean otherSession = (ChatBean) another;
        /**置顶判断 ArrayAdapter是按照升序从上到下排序的，就是默认的自然排序
         * 如果是相等的情况下返回0，包括都置顶或者都不置顶，返回0的情况下要
         * 再做判断，拿它们置顶时间进行判断
         * 如果是不相等的情况下，otherSession是置顶的，则当前session是非置顶的，应该在otherSession下面，所以返回1
         *  同样，session是置顶的，则当前otherSession是非置顶的，应该在otherSession上面，所以返回-1
         * */
        int result = 0 - (top - otherSession.getTop());
        if (result == 0) {
            result = 0 - compareToTime(time, otherSession.getTime());
        }
        return result;
    }
    /**
     * 根据时间对比
     * */
    public static int compareToTime(long lhs, long rhs) {
        Calendar cLhs = Calendar.getInstance();
        Calendar cRhs = Calendar.getInstance();
        cLhs.setTimeInMillis(lhs);
        cRhs.setTimeInMillis(rhs);
        return cLhs.compareTo(cRhs);
    }
}
