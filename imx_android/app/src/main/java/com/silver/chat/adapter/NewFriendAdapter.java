package com.silver.chat.adapter;

import com.silver.chat.R;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;
import com.ssim.android.model.notification.SSFriendNotification;

import java.util.List;

/**
 * Created by hibon on 2017/5/9.
 */

public class NewFriendAdapter extends BaseQuickAdapter<SSFriendNotification,BaseViewHolder> {

    public NewFriendAdapter(int layoutResId, List<SSFriendNotification> data) {
        super(layoutResId, data);
    }

    public NewFriendAdapter(List<SSFriendNotification> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SSFriendNotification item, int position) {
        helper.setText(R.id.add_user_name,item.getSourceName())
                .setText(R.id.friend_additional,item.getContent());


    }
}
