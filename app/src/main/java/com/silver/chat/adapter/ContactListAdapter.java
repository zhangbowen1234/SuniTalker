package com.silver.chat.adapter;

import com.silver.chat.R;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class ContactListAdapter extends BaseQuickAdapter<ContactMemberBean, BaseViewHolder> {


    public ContactListAdapter(int layoutResId, List<ContactMemberBean> data) {
        super(layoutResId, data);
    }

//    public ContactListAdapter(List<ContactMemberBean> data) {
//        super(R.layout.item_contact_list, data);
//
//    }

    @Override
    protected void convert(BaseViewHolder holper, ContactMemberBean item) {
        holper.setText(R.id.tv_contact_name, item.getContactName());

    }

}
