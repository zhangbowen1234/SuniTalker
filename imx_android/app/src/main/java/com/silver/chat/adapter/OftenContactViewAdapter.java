package com.silver.chat.adapter;

import com.silver.chat.R;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * Created by hibon on 2017/5/8.
 */

public class OftenContactViewAdapter extends BaseQuickAdapter<ContactMemberBean,BaseViewHolder>{

    public OftenContactViewAdapter(int layoutResId,List<ContactMemberBean> data) {
        super(layoutResId ,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactMemberBean item, int position) {
        helper.setText(R.id.tv_contact_name,item.getNickName());
    }
}
