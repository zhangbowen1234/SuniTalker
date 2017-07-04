package com.silver.chat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.ui.mine.UserInfoActivity;
import com.silver.chat.util.DisplayUtil;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.view.CircleImageView;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Fandy on 2016/11/29 09:53
 * 邮箱：fandy618@hotmail.com
 */

public class SearchContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ContactListBean> contactList = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;

    public SearchContactAdapter(Context context, List<ContactListBean> data) {
        contactList = data;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(mLayoutInflater.inflate(R.layout.item_contact_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ContactViewHolder) {//联系人
            ((ContactViewHolder) holder).mTextView.setText(contactList.get(position).getNickName());
            GlideUtil.loadAvatar(((ContactViewHolder)holder).mHeader,contactList.get(position).getAvatar());
            ((ContactViewHolder) holder).mContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mCantactIntent = new Intent(mContext, UserInfoActivity.class);
                    mCantactIntent.putExtra("contactName", contactList.get(position).getNickName());
                    mCantactIntent.putExtra("sex", contactList.get(position).getSex() + "");
                    mCantactIntent.putExtra("signature", contactList.get(position).getSignature());
                    mCantactIntent.putExtra("friendId", contactList.get(position).getFriendId() + "");
                    mContext.startActivity(mCantactIntent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
    /**
     * 联系人Holder
     */
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        CircleImageView mHeader;
        LinearLayout mContact;

        ContactViewHolder(final View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_contact_name);
            mHeader = (CircleImageView) view.findViewById(R.id.im_contact_image);
            mContact = (LinearLayout) view.findViewById(R.id.contact_btn);

        }
    }
}
