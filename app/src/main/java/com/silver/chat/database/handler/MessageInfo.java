package com.silver.chat.database.handler;

import android.os.Message;

import com.silver.chat.database.callback.EasyRun;


/**
 * Handler 信息体
 * @author : zhousf
 */

public class MessageInfo<T> {

    public int what;

    public EasyRun easyRun;

    public T model;

    public MessageInfo() {
    }

    public Message build(){
        Message msg = new Message();
        msg.what = this.what;
        msg.obj = this;
        return msg;
    }
}
