package com.silver.chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bowen on 2017/5/22.
 */

public class GMemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContexts;
    private List<GroupMemberBean> data = new ArrayList();

    public GMemAdapter(Context context, List<GroupMemberBean> data) {
        mContexts = context;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_gmem_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            GlideUtil.loadAvatar(((MyViewHolder) holder).mHeader,data.get(position).getAvatar());
            Log.e("onBindViewHolder: ", data.get(position).getAvatar());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView mHeader;
        MyViewHolder(final View view) {
            super(view);
            mHeader = (CircleImageView) view.findViewById(R.id.im_groupmem_image);
        }
    }
}
