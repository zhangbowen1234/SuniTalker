package com.silver.chat.adapter;

import com.silver.chat.R;
import com.silver.chat.network.responsebean.SearchIdBean;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * Created by lenovo on 2017/5/10.
 */

public class AddFriendAdapter extends BaseQuickAdapter<SearchIdBean,BaseViewHolder> {
    public AddFriendAdapter(int layoutResId, List<SearchIdBean> data) {
        super(layoutResId, data);
    }

    public AddFriendAdapter(List<SearchIdBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchIdBean item, int position) {
        helper.setText(R.id.user_name,item.getNickName());
    }
}
