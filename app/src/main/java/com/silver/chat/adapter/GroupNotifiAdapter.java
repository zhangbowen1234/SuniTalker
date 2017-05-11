package com.silver.chat.adapter;

import android.view.LayoutInflater;

import com.silver.chat.R;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * Created by bowen on 2017/5/11.
 */

public class GroupNotifiAdapter extends BaseQuickAdapter<ContactMemberBean,BaseViewHolder> {
    private LayoutInflater inflater;
    private List<ContactMemberBean> mList;

    public GroupNotifiAdapter(int layoutResId, List<ContactMemberBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactMemberBean item, int position) {
        helper.setText(R.id.tv_name,item.getContactName());
        helper.setText(R.id.iv_agree,item.getContactName());
        helper.setText(R.id.tv_invite_group,item.getContactName());
        helper.setText(R.id.tv_process_message,item.getContactName());
//        helper.setOnItemClickListener(R.id.rl_jion_info, new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(mContext,AddGroupNotifiActivity.class);
//
//            }
//        });
    }
}
