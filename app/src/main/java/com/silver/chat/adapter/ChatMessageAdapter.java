package com.silver.chat.adapter;

import com.silver.chat.database.ChatEntity;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ChatMessageAdapter extends BaseQuickAdapter<ChatEntity, BaseViewHolder> {


    public ChatMessageAdapter(int layoutResId, List<ChatEntity> data) {
        super(layoutResId, data);
    }

    public ChatMessageAdapter(List<ChatEntity> data) {
        super(data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ChatEntity item) {

    }
}
