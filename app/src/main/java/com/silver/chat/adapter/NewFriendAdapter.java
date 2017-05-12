package com.silver.chat.adapter;

import com.silver.chat.R;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * Created by hibon on 2017/5/9.
 */

public class NewFriendAdapter extends BaseQuickAdapter<ContactMemberBean,BaseViewHolder> {

    public NewFriendAdapter(int layoutResId, List<ContactMemberBean> data) {
        super(layoutResId, data);
    }

    public NewFriendAdapter(List<ContactMemberBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactMemberBean item, int position) {
        helper.setText(R.id.add_user_name,item.getNickName());
    }
}
