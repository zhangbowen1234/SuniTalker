package com.silver.chat.entity;

import com.silver.chat.view.recycleview.entity.MultiItemEntity;
import com.ssim.android.constant.SSMessageFormat;

/**
 * Created by Joe on 2017/6/15.
 * 因为使用RecycleView框架所以此处实体类需要实现MulityItemEntity接口
 * 不能使用SDK中提供的SSGroupMessage实体类
 */

public class GroupMsgEntity implements MultiItemEntity{


    private String content;
    private String targetId;
    private SSMessageFormat contentType;
    private long messageTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public SSMessageFormat getContentType() {
        return contentType;
    }

    public void setContentType(SSMessageFormat contentType) {
        this.contentType = contentType;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
