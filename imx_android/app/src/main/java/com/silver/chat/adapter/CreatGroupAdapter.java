package com.silver.chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bowen on 2017/5/22.
 */

public class CreatGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContexts;
    private List<ContactListBean> data = new ArrayList();

    public CreatGroupAdapter(Context context, List<ContactListBean> data) {
        mContexts = context;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_creat_group_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            ((MyViewHolder) holder).mTextView.setText(data.get(position).getNickName());
            GlideUtil.loadAvatar(((MyViewHolder) holder).mHeader,data.get(position).getAvatar());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        CircleImageView mHeader;
        LinearLayout mContact;
        MyViewHolder(final View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_contact_name);
            mHeader = (CircleImageView) view.findViewById(R.id.im_contact_image);
            mContact = (LinearLayout) view.findViewById(R.id.contact_btn);
        }
    }
}
