package com.silver.chat.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.github.library.listener.OnRecyclerItemClickListener;
import com.github.library.listener.OnRecyclerItemLongClickListener;
import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.ChatApater;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.base.Common;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.ui.chat.notification.GroupNotificationActivity;
import com.silver.chat.ui.contact.ContactChatActivity;
import com.silver.chat.ui.contact.group.GroupChatActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.dialog.TopDeleteDialog;
import com.silver.chat.view.recycleview.pulltorefreshable.WSRecyclerView;
import com.ssim.android.constant.SSSessionTopLevel;
import com.ssim.android.listener.SSConnectListener;
import com.ssim.android.listener.SSMessageReceiveListener;
import com.ssim.android.listener.SSMessageSendListener;
import com.ssim.android.listener.SSNotificationListener;
import com.ssim.android.model.chat.SSMessage;
import com.ssim.android.model.chat.SSP2PMessage;
import com.ssim.android.model.notification.SSFriendNotification;
import com.ssim.android.model.notification.SSNotification;
import com.ssim.android.model.session.SSSession;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.silver.chat.adapter.ChatApater.sessionList;
import static com.silver.chat.adapter.ChatApater.sourceId;

/**
 * 作者：Fandy on 2016/11/14 14:14
 * 邮箱：fandy618@hotmail.com
 */

public class ChatRecordFragment extends BasePagerFragment implements SSNotificationListener {//,SSMessageReceiveListener,SSConnectListener,SSMessageSendListener

    private WSRecyclerView mRecycleContent;
    private ChatApater mChatApater;
    private List<ChatBean> mList;
    public static String TOP_STATES = "TOP";
    private MyHandler mMyHandler;
//    public String friendid;

    public static ChatRecordFragment newInstance() {
        Bundle args = new Bundle();
        ChatRecordFragment fragment = new ChatRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mMyHandler = new MyHandler(this);
        mRecycleContent = (WSRecyclerView) view.findViewById(R.id.recyle_content);
        mRecycleContent.setLayoutManager(new LinearLayoutManager(mActivity));
//        friendid = ChatApater.sourceId;
    }

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        mList.addAll(ChatApater.getChatData(mActivity));
        if (mList.size() != 0){
            int position = PreferenceUtil.getInstance(mActivity).getInt("position", 0);
            mList.add(0, mList.get(position));
            mList.remove(position + 1);
        }
        mChatApater = new ChatApater(mList);

        mRecycleContent.setAdapter(mChatApater);
        mChatApater.addHeaderView(mRecycleContent.getRefreshView());
        mChatApater.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mChatApater.getItemViewType(position + 1) == ChatBean.CHAT_SINGLR) {
                    Intent mIntent = new Intent(mActivity, ContactChatActivity.class);
                    mIntent.putExtra("contactName", mList.get(position).getUserName());
                    mIntent.putExtra("friendId", mList.get(position).getUserId());
                    mIntent.putExtra("chatType", Common.PRIVAT);
                    startActivity(mIntent);
//                    startActivity(GroupNotificationActivity.class);
                } else if (mChatApater.getItemViewType(position + 1) == ChatBean.CHAT_GROUP) {
                    Intent mIntent = new Intent(mActivity, GroupChatActivity.class);
                    mIntent.putExtra("groupName", mList.get(position).getGroupName());
                    mIntent.putExtra("groupId", mList.get(position).getGroupId());
//                    mIntent.putExtra("chatType", Common.PRIVAT);
                    startActivity(mIntent);
                } else if (mChatApater.getItemViewType(position + 1) == ChatBean.CHAT_GROUP_NOTICE) {
                    startActivity(GroupNotificationActivity.class);
                }
            }
        });
        mChatApater.setOnRecyclerItemLongClickListener(new OnRecyclerItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, final int position) {
                final ChatBean chatBean = mChatApater.getItem(position);
//                Bundle bundle = new Bundle();
//                bundle.putInt(TOP_STATES, chatBean.getTop());
                TopDeleteDialog topDeleteDialog = new TopDeleteDialog(mActivity);
//                topDeleteDialog.setArguments(bundle);//传参
                topDeleteDialog.builder()
                        .setCanceledOnTouchOutside(true)
                        .setTopTextview(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //置顶
                                PreferenceUtil.getInstance(mActivity).setInt("position", position);
//                                chatBean.setTop(1);
//                                chatBean.setTime(System.currentTimeMillis());
                                mList.add(0, chatBean);
                                mList.remove(position + 1);
                                refreshView();
                                SSSessionTopLevel ssSessionTopLevel = SSSessionTopLevel.LEVEL_HIGH;
                                sessionList.get(position).setTopLevel(ssSessionTopLevel);
                                Log.e("onClick: ", (position + 1) +"");
                            }
                        })
                        .setDeleteTextview(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mList.remove(mChatApater.getItem(position));
                                AppContext.getInstance().instance.delSessionById(sourceId);
                                Log.e( "setDelete: ", sourceId);
                                ToastUtils.showMessage(getActivity(), "删除成功");
                                mChatApater.notifyDataSetChanged();
                            }
                        })
                        .setNoTopTextview(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //取消置顶
//                                chatBean.setTop(0);
//                                chatBean.setTime(System.currentTimeMillis());
                                SSSessionTopLevel ssSessionTopLevel = SSSessionTopLevel.DEFAULT;
                                sessionList.get(position).setTopLevel(ssSessionTopLevel);
                                refreshView();
                            }
                        }).show();
                return false;
            }
        });
        mRecycleContent.setOnRefreshCompleteListener(new WSRecyclerView.OnRefreshCompleteListener() {
            @Override
            public void onRefreshComplete() {
                mMyHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mChatApater.setData(mList);
                        mRecycleContent.refreshComplete();
                    }
                }, 3000);
            }
        });

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_chat_record;
    }

    public void refreshView() {
        //如果不调用sort方法，是不会进行排序的，也就不会调用compareTo
        Collections.sort(mList);
        mChatApater.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        super.initListener();
         /*收到消息监听*/
//        AppContext.getInstance().instance.setMsgRcvListener((SSMessageReceiveListener) mActivity);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void receiveNotification(SSNotification ssNotification) {

    }

    /**
     * 处理新收到的消息
     */
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    /*显示新收到的消息*/
//                    if (receiveMsg != null) {
                        mList.addAll(ChatApater.getChatData(mActivity));
                        mChatApater.setNewData(mList);
                        mChatApater.notifyDataSetChanged();
//                    }
                    break;
            }
        }
    };

    private static class MyHandler extends Handler {
        private WeakReference<ChatRecordFragment> activityWeakReference;

        public MyHandler(ChatRecordFragment fragment) {
            activityWeakReference = new WeakReference<ChatRecordFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ChatRecordFragment fragment = activityWeakReference.get();
            if (fragment == null) {
                return;
            }
        }
    }
}
