package com.silver.chat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.ui.contact.GroupChatActivity;
import com.silver.chat.ui.contact.NewFriendActivity;
import com.silver.chat.ui.mine.FriendInfoActivity;
import com.silver.chat.view.CircleImageView;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.OnItemClickListener;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
/**
 *  Test:一
 */
//public class ContactListAdapter extends BaseQuickAdapter<ContactMemberBean, BaseViewHolder> {
//
//
//    public ContactListAdapter(int layoutResId, List<ContactMemberBean> data) {
//        super(layoutResId, data);
//
//    }
//
//    @Override
//    protected void convert(BaseViewHolder holper, ContactMemberBean item, int position) {
//        holper.setText(R.id.tv_contact_name, item.getContactName());
//    }
//
//}
/**
 *  Test:二
 */
//public class ContactListAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
//
//    /**
//     * Same as QuickAdapter#QuickAdapter(Context,int) but with
//     * some initialization data.
//     *
//     * @param data A new list is created out of this one to avoid mutable list
//     */
//    public ContactListAdapter(List data) {
//        super(data);
//        addItemType(ContactMemberBean.NEW_FRIEND, R.layout.new_friend_layout);
//        addItemType(ContactMemberBean.GROUP_CHAT, R.layout.group_chat_layout);
//        addItemType(ContactMemberBean.H_CANTACT, R.layout.item_horizontal_contact_list);
//        addItemType(ContactMemberBean.CANTACT, R.layout.item_contact_list);
//
//    }
//
//
//    @Override
//    protected void convert(BaseViewHolder helper, MultiItemEntity item, int position) {
//        if (position == ContactMemberBean.NEW_FRIEND) {
//
//        } else if (position == ContactMemberBean.GROUP_CHAT) {
//
//        } else if (position == ContactMemberBean.H_CANTACT) {
//
//        } else {
//
//        }
//    }
//}

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

    private static final int NEW_FRIEND = 0;
    private static final int GROUP_CHAT = 1;
    private static final int H_CANTACT = 2;
    private static final int CANTACT = 3;

    private List<ContactMemberBean> contactList;
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;

    public ContactListAdapter(Context context, List<ContactMemberBean> data) {
        contactList = data;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == ITEM_TYPE.NEW_FRIEND.ordinal()) {//枚举方式
        if (viewType == NEW_FRIEND) {
            return new NewFriendViewHolder(mLayoutInflater.inflate(R.layout.new_friend_layout, parent, false));
        } else if (viewType == GROUP_CHAT) {
            return new GroupViewHolder(mLayoutInflater.inflate(R.layout.group_chat_layout, parent, false));
        } else if (viewType == H_CANTACT) {
            return new OftenContactViewHolder(mLayoutInflater.inflate(R.layout.item_often_contact_list, parent, false));
        } else {
            return new ContactViewHolder(mLayoutInflater.inflate(R.layout.item_contact_list, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NewFriendViewHolder) {//新朋友
            ((NewFriendViewHolder)holder).mNewFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mLVHolder = new Intent(mContext,NewFriendActivity.class);
                    mContext.startActivity(mLVHolder);
                }
            });
        } else if (holder instanceof GroupViewHolder) {//群组
            ((GroupViewHolder) holder).mGroupChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mGroupIntent = new Intent(mContext,GroupChatActivity.class);
                    mContext.startActivity(mGroupIntent);
                }
            });
        }else if (holder instanceof OftenContactViewHolder) {//常用联系人
            //设置布局管理器及指定RecyclerView方向
            ((OftenContactViewHolder)holder).mOftenContact.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            //常用联系人列表的adapter
            OftenContactViewAdapter oftenContactViewAdapter = new OftenContactViewAdapter(R.layout.item_horizontal_contact_list,contactList);
            ((OftenContactViewHolder)holder).mOftenContact.setAdapter(oftenContactViewAdapter);
            ((OftenContactViewHolder)holder).mOftenContact.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                ContactMemberBean contactMemberBean = (ContactMemberBean) adapter.getItem(position);
//                ToastUtil.toastMessage(mContext, contactMemberBean.getContactName());
                Intent mCantactIntent = new Intent(mContext,FriendInfoActivity.class);
                mCantactIntent.putExtra("contactName",contactList.get(position).getContactName());
                mContext.startActivity(mCantactIntent);
            }
        });
        }else if (holder instanceof ContactViewHolder) {//联系人
            ((ContactViewHolder) holder).mTextView.setText(contactList.get(position).getContactName());

            ((ContactViewHolder) holder).mContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mCantactIntent = new Intent(mContext,FriendInfoActivity.class);
                    mCantactIntent.putExtra("contactName",contactList.get(position).getContactName());
                    mContext.startActivity(mCantactIntent);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return NEW_FRIEND;
        } else if (position == 1) {
            return GROUP_CHAT;
        } else if (position == 2) {
            return H_CANTACT;
        } else {
            return CANTACT;
        }
    }

    @Override
    public int getItemCount() {
        return contactList == null ? 0 : contactList.size();
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
     * 常用联系人Holder
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