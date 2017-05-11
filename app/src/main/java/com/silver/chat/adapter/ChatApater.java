package com.silver.chat.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.entity.DataServer;
import com.silver.chat.util.ImageUtil;
import com.silver.chat.view.recycleview.BaseMultiItemQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.j256.ormlite.field.DataPersisterManager.clear;
import static java.util.Collections.addAll;

/**
 * 作者：Fandy on 2016/11/16 17:57
 * 邮箱：fandy618@hotmail.com
 */

public class ChatApater extends BaseMultiItemQuickAdapter<ChatBean, BaseViewHolder> {

    private List<ChatBean> chatBeen;
    public ChatApater(List<ChatBean> data) {
        super(data);
        chatBeen = data;
        addItemType(ChatBean.CHAT_SINGLR, R.layout.item_chat_record_single);
        addItemType(ChatBean.CHAT_GROUP, R.layout.item_chat_record_group);
        addItemType(ChatBean.CHAT_SYSTEM, R.layout.item_chat_record_system);
        addItemType(ChatBean.CHAT_DISCUSSION_GROUP, R.layout.item_chat_record_dicussion);
        addItemType(ChatBean.CHAT_GROUP_NOTICE, R.layout.item_chat_record_notice);
    }


    @Override
    protected void convert(final BaseViewHolder holder, ChatBean item , int position) {
        //item多布局
        switch (holder.getItemViewType()) {
            case ChatBean.CHAT_SINGLR:
                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name, "单人聊天=" + holder.getPosition());
                break;
            case ChatBean.CHAT_GROUP:
                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name ,"群消息=" + holder.getPosition());

                break;
            case ChatBean.CHAT_SYSTEM:
//                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name, "系统消息=" + holder.getPosition());
                break;
            case ChatBean.CHAT_DISCUSSION_GROUP:
                ImageView ivGroup = holder.getView(R.id.iv_avatar);
                ArrayList<String> list = new ArrayList<>();
                list.add(DataServer.IMAGE_URL1);
                list.add(DataServer.IMAGE_URL2);
                list.add(DataServer.IMAGE_URL3);
                list.add(DataServer.IMAGE_URL4);
                if (holder.getPosition() == 5){
                    list.remove(0);
                    ImageUtil.loadGroupAvatar(ivGroup, list);
                }else{
                    ImageUtil.loadGroupAvatar(ivGroup, list);
                }
                holder.setText(R.id.tv_name, "讨论组消息=" + holder.getPosition());
                break;
            case ChatBean.CHAT_GROUP_NOTICE:
//                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name, "群通知=" + holder.getPosition());
                break;
        }
        //置顶颜色
        ChatBean chatBean = chatBeen.get(position);
        if (chatBean.getTop() == 1) {
            holder.itemView.setBackgroundResource(R.color.topdialog);
        } else {
            holder.itemView.setBackgroundResource(R.color.translate);
        }

    }
    public void updateData(List<ChatBean> sessionList) {
        clear();
        addAll(sessionList);
    }
}
