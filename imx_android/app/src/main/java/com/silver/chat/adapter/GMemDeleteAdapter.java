package com.silver.chat.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.view.CircleImageView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bowen on 2017/5/9.
 */

public class GMemDeleteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GroupMemberBean> friendList;
    private LayoutInflater inflater;
    private Context context;
    private boolean isChoose = false;
    public HashMap<Integer,Boolean> ivMap = new HashMap<>();
    private final Drawable unSelectCircle,selectCircle;

    public GMemDeleteAdapter(Context activity, List<GroupMemberBean> data) {
        this.friendList = data;
        this.context = activity;
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < friendList.size(); i++) {
            ivMap.put(i,isChoose);
        }
        unSelectCircle = context.getResources().getDrawable(R.drawable.check_box);
        selectCircle = context.getResources().getDrawable(R.drawable.ic_check);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_friendinfo, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder){
            ((MyViewHolder) holder).mTextView.setText(friendList.get(position).getNickName());
            GlideUtil.loadAvatar(((MyViewHolder) holder).mHeader,friendList.get(position).getAvatar());
            ((MyViewHolder) holder).mContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivMap.put(position,!ivMap.get(position));
                    ((MyViewHolder) holder).mImageView.setImageDrawable(ivMap.get(position)?selectCircle:unSelectCircle);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        CircleImageView mHeader;
        LinearLayout mContact;
        ImageView mImageView;
        MyViewHolder(final View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_contact_name);
            mHeader = (CircleImageView) view.findViewById(R.id.im_contact_image);
            mContact = (LinearLayout) view.findViewById(R.id.friendinfo_btn);
            mImageView = (ImageView) view.findViewById(R.id.iv_friendinfo_choose);
        }
    }
}
