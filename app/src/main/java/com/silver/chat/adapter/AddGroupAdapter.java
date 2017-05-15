package com.silver.chat.adapter;

import com.silver.chat.R;
import com.silver.chat.network.responsebean.SearchGroupBean;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * Created by Joe on 2017/5/15.
 */

public class AddGroupAdapter extends BaseQuickAdapter<SearchGroupBean.GroupsBean,BaseViewHolder>{
    public AddGroupAdapter(int layoutResId, List<SearchGroupBean.GroupsBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, SearchGroupBean.GroupsBean item, int position) {
        helper.setText(R.id.user_name,item.getGroupName());

    }
}
