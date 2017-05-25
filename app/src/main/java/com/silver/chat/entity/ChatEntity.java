package com.silver.chat.entity;

import java.io.Serializable;

public class ChatEntity implements Serializable {

// getContent ggggg
// ==getLocalPath
// ==getSourceId 5
// ==getTargetId 9
// ==getClientMessageId 0
// ==getContentType TEXT
// ==getMessageTime 1495684355380
// ==getMsgId 211022
// ==getMsgStatus UNREAD
// ==getMsgType MSG_P2PCHAT

    public static final int RECEIVE = 0;
    public static final int SEND = 1;

    private String sourceId;
    private String targetId;
    private String localPath;
    private String clientMessageId;
    private String contentType;
    private String messageTime;
    private long msgId;
    private int msgType;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getClientMessageId() {
        return clientMessageId;
    }

    public void setClientMessageId(String clientMessageId) {
        this.clientMessageId = clientMessageId;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }


}
