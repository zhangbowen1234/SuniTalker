package com.silver.chat.adapter;

import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.entity.DataServer;
import com.silver.chat.util.ImageUtil;
import com.silver.chat.view.recycleview.BaseMultiItemQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Fandy on 2016/11/16 17:57
 * 邮箱：fandy618@hotmail.com
 */

public class SearchChatRecordApater extends BaseMultiItemQuickAdapter<ChatBean, BaseViewHolder> {

    public SearchChatRecordApater(List<ChatBean> data) {
        super(data);
        addItemType(ChatBean.CHAT_SINGLR, R.layout.item_search_chat_record_single);
        addItemType(ChatBean.CHAT_GROUP, R.layout.item_search_chat_record_group);
        addItemType(ChatBean.CHAT_SYSTEM, R.layout.item_search_chat_record_system);
        addItemType(ChatBean.CHAT_DISCUSSION_GROUP, R.layout.item_chat_record_dicussion);
        addItemType(ChatBean.CHAT_GROUP_NOTICE, R.layout.item_chat_record_notice);
    }

    @Override
    protected void convert(BaseViewHolder holder, ChatBean item, int position) {
        switch (holder.getItemViewType()) {
            case ChatBean.CHAT_SINGLR:
                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name, "单人聊天=" + holder.getPosition());
                break;
            case ChatBean.CHAT_GROUP:
                ImageView ivGroup = holder.getView(R.id.iv_avatar);
                ArrayList<String> list = new ArrayList<>();
                list.add(DataServer.IMAGE_URL1);
                list.add(DataServer.IMAGE_URL2);
                list.add(DataServer.IMAGE_URL3);
                list.add(DataServer.IMAGE_URL4);
                if (holder.getPosition() == 5) {
                    list.remove(0);
                    ImageUtil.loadGroupAvatar(ivGroup, list);
                } else {
                    ImageUtil.loadGroupAvatar(ivGroup, list);
                }
                holder.setText(R.id.tv_name, "群组聊天=" + holder.getPosition());
                break;
            case ChatBean.CHAT_SYSTEM:
                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name, "系统消息=" + holder.getPosition());
                break;
            case ChatBean.CHAT_DISCUSSION_GROUP:
                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name, "讨论组消息=" + holder.getPosition());
                break;
            case ChatBean.CHAT_GROUP_NOTICE:
                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name, "群通知=" + holder.getPosition());
                break;
        }
    }

}
