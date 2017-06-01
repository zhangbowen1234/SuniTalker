package com.silver.chat.adapter;

import com.silver.chat.R;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;
import com.ssim.android.model.chat.SSP2PMessage;

import java.util.List;

/**
 * Created by hibon on 2017/5/9.
 */

public class NewFriendAdapter extends BaseQuickAdapter<SSP2PMessage,BaseViewHolder> {

    public NewFriendAdapter(int layoutResId, List<SSP2PMessage> data) {
        super(layoutResId, data);
    }

    public NewFriendAdapter(List<SSP2PMessage> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SSP2PMessage item, int position) {
        helper.setText(R.id.add_user_name,item.getSourceId());
    }
}
