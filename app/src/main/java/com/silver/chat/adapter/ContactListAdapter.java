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
import com.silver.chat.ui.contact.NewFriendActivity;
import com.silver.chat.ui.contact.group.GroupChatActivity;
import com.silver.chat.ui.mine.FriendInfoActivity;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
/**
 *  Test:三
 */

public class ContactListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //枚举方式
    public enum ITEM_TYPE {
        NEW_FRIEND,
        GROUP_CHAT,
        H_CANTACT,
        CANTACT
    }

    private List<ContactListBean> contactList = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;

    public ContactListAdapter(Context context, List<ContactListBean> data) {
        contactList = data;
        Log.e("data", data + "");
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("viewType", viewType + "");
        if (viewType == ITEM_TYPE.NEW_FRIEND.ordinal()) {//枚举方式
            return new NewFriendViewHolder(mLayoutInflater.inflate(R.layout.new_friend_layout, parent, false));
        } else if (viewType == ITEM_TYPE.GROUP_CHAT.ordinal()) {
            return new GroupViewHolder(mLayoutInflater.inflate(R.layout.group_chat_layout, parent, false));
        } else if (viewType == ITEM_TYPE.H_CANTACT.ordinal()) {
            return new OftenContactViewHolder(mLayoutInflater.inflate(R.layout.item_often_contact_list, parent, false));
        } else if (viewType == ITEM_TYPE.CANTACT.ordinal()){
            return new ContactViewHolder(mLayoutInflater.inflate(R.layout.item_contact_list, parent, false));
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NewFriendViewHolder) {//新朋友
            ((NewFriendViewHolder) holder).mNewFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mLVHolder = new Intent(mContext, NewFriendActivity.class);
                    mContext.startActivity(mLVHolder);
                }
            });
        } else if (holder instanceof GroupViewHolder) {//群组
            ((GroupViewHolder) holder).mGroupChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mGroupIntent = new Intent(mContext, GroupChatActivity.class);
                    mContext.startActivity(mGroupIntent);
                }
            });
        }else if (holder instanceof OftenContactViewHolder) {//常用联系人
        } else if (holder instanceof ContactViewHolder) {//联系人
            ((ContactViewHolder) holder).mTextView.setText(contactList.get(position-3).getNickName());
            ((ContactViewHolder) holder).mContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mCantactIntent = new Intent(mContext, FriendInfoActivity.class);
                    mCantactIntent.putExtra("contactName", contactList.get(position-3).getNickName());
                    mCantactIntent.putExtra("sex", contactList.get(position-3).getSex()+"");
                    mCantactIntent.putExtra("signature", contactList.get(position-3).getSignature());
                    mCantactIntent.putExtra("friendId", contactList.get(position-3).getFriendId()+"");
                    mContext.startActivity(mCantactIntent);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        Log.e("getItemViewType__position", position + "");
        if (position == 0) {
            return ITEM_TYPE.NEW_FRIEND.ordinal();
        } else if (position == 1) {
            return ITEM_TYPE.GROUP_CHAT.ordinal();
        } else if (position == 2) {
            return ITEM_TYPE.H_CANTACT.ordinal();
        }else {
            return ITEM_TYPE.CANTACT.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return contactList == null ? 3 : contactList.size()+3;
    }

    /**
     * 新朋友Holder
     */
    public static class NewFriendViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mNewFriend;

        public NewFriendViewHolder(View itemView) {
            super(itemView);
            mNewFriend = (LinearLayout) itemView.findViewById(R.id.new_friend_btn);
        }
    }

    /**
     * 群组Holder
     */
    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mGroupChat;

        public GroupViewHolder(View itemView) {
            super(itemView);
            mGroupChat = (LinearLayout) itemView.findViewById(R.id.group_chat_btn);
        }

    }

    /**
     * (3)常用联系人Holder
     */
    public static class OftenContactViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mOftenContact;

        OftenContactViewHolder(View view) {
            super(view);
            mOftenContact = (RecyclerView) view.findViewById(R.id.horizontal_recyle_content);
        }
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