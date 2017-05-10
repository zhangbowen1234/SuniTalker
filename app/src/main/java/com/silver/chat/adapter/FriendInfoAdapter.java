package com.silver.chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.silver.chat.R;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.network.responsebean.FriendInfo;
import com.silver.chat.view.CircleImageView;

import java.util.List;

/**
 * Created by bowen on 2017/5/9.
 */

public class FriendInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FriendInfo> friendList;
    private final Context mContext;
    private LayoutInflater inflater;

    public FriendInfoAdapter(Context context, List<FriendInfo> data) {
        friendList = data;
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_friendinfo, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            ((MyViewHolder) holder).mTextView.setText(friendList.get(position).getLoginName());
            Glide.with(mContext).load(friendList.get(position).getAvatar()).into(((MyViewHolder) holder).mHeader);
        }

    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    /**
     * 联系人Holder
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
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
