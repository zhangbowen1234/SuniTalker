package com.silver.chat.entity;

import android.widget.Switch;

import com.silver.chat.view.recycleview.entity.MultiItemEntity;
import com.ssim.android.constant.SSMessageFormat;

import static com.ssim.android.constant.SSMessageFormat.*;

/**
 * Created by Joe on 2017/6/13.
 */
public class GroupMessageBean implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int LOCATION = 8;
    private SSMessageFormat contentType;
    private String sourceId;
    private String groupId;

    public GroupMessageBean(SSMessageFormat contentType) {
        this.contentType = contentType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    private String content;
    private long messageTime;


    /**
     *  消息类型枚举
     ERROR((byte)-1),
     TEXT((byte)1),
     IMAGE((byte)2),
     AUDIO((byte)3),
     VIDEO((byte)4),
     SYSTEM_NOTIFICATION((byte)5),
     FILES((byte)6),
     JSON((byte)7),
     LOCATION((byte)8),
     AT((byte)9),
     LOCATION_REAL_TIME((byte)10),
     NOTIFICATION((byte)100);

     * @return
     */
    @Override
    public int getItemType() {
      switch (contentType) {
          case ERROR:
              return -1;
          case TEXT:
              return 1;
          case IMAGE:
              return 2;
          case AUDIO:
              return 3;
          case VIDEO:
              return 4;
          case SYSTEM_NOTIFICATION:
              return 5;
          case FILES:
              return 6;
          case JSON:
              return 7;
          case LOCATION:
              return 8;
          case AT:
              return 9;
          case LOCATION_REAL_TIME:
              return 10;
          case NOTIFICATION:
              return 100;

      }
        return 0;
    }

    public SSMessageFormat getContentType() {
        return contentType;
    }

    public void setContentType(SSMessageFormat contentType) {
        this.contentType = contentType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
