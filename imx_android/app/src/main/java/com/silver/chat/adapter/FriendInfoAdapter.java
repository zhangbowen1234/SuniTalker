package com.silver.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.silver.chat.R;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.ui.contact.group.CreatGroupActivity;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.R.id.list;
import static com.silver.chat.util.Utils.context;

/**
 * Created by bowen on 2017/5/9.
 */

public class FriendInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CreatGroupActivity mcontent;
    private List<ContactListBean> friendList;
    private LayoutInflater inflater;
    private boolean isChoose = false;
    public HashMap<Integer,Boolean> ivMap = new HashMap<>();
    private final Drawable unSelectCircle,selectCircle;

    public FriendInfoAdapter(Activity activity, List<ContactListBean> data) {
        this.friendList = data;
        this.mcontent = (CreatGroupActivity) activity;
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
                    List selectItems = getSelectItems();
                    mcontent.tvDetermine.setText("确定(" + selectItems.size() + ")");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public List<ContactListBean> getSelectedList() {
        List<ContactListBean> lists = new ArrayList<>();
        List selectItems = getSelectItems();
        for (int i = 0; i < selectItems.size(); i++) {
            lists.add(friendList.get((Integer) selectItems.get(i)));

        }
        return lists;
    };
    /**
     * 统计选中状态的数量
     */

    public List getSelectItems() {
        ArrayList<Integer> list = new ArrayList<>();
        for (Iterator<Map.Entry<Integer, Boolean>> it = ivMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, Boolean> entry = it.next();
            if (entry.getValue()) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

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
