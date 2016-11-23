package com.silver.chat.adapter;

import android.support.v7.widget.RecyclerView;

import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class ContactListAdapter extends BaseQuickAdapter {



    public ContactListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public ContactListAdapter(List data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
