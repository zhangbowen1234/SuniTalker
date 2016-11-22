package com.silver.chat.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Fandy on 2016/11/17 16:43
 * 邮箱：fandy618@hotmail.com
 */

public class DataServer {
    public static final String IMAGE_URL1 = "http://atth.eduu.com/album/201203/12/1475134_1331559643qMzc.jpg";
    public static final String IMAGE_URL2 = "http://f.hiphotos.baidu.com/image/pic/item/b151f8198618367ac7d2a1e92b738bd4b31ce5af.jpg";
    public static final String IMAGE_URL3 = "http://scimg.jb51.net/allimg/151218/14-15121Q0513N38.jpg";
    public static final String IMAGE_URL4 = "http://img4q.duitang.com/uploads/item/201502/24/20150224143555_jJRzL.jpeg";

    public static List<ChatBean> getChatData() {
        List<ChatBean> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new ChatBean("user_id=" + i, "userName=" + i, IMAGE_URL1, ChatBean.CHAT_SINGLR));
            list.add(new ChatBean("user_id=" + i, "userName=" + i, IMAGE_URL2, ChatBean.CHAT_SYSTEM));
            list.add(new ChatBean("user_id=" + i, "userName=" + i, IMAGE_URL3, ChatBean.CHAT_GROUP));
        }
        return list;
    }
}
