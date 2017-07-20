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
import com.ssim.android.constant.SSConversationTopLevel;
import com.ssim.android.engine.SSEngine;
import com.ssim.android.listener.SSMessageReceiveListener;
import com.ssim.android.model.chat.SSMessage;
import com.ssim.android.model.chat.SSP2PMessage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.silver.chat.adapter.ChatApater.conversationList;
import static com.silver.chat.adapter.ChatApater.sourceId;

/**
 * bowen
 */

public class ChatRecordFragment extends BasePagerFragment implements SSMessageReceiveListener {//,SSMessageReceiveListener,SSMessageSendListener

    private WSRecyclerView mRecycleContent;
    private ChatApater mChatApater;
    private List<ChatBean> mList;
    public static String TOP_STATES = "TOP";
    private MyHandler mMyHandler;
//    public String friendid;
    private SSMessage ssMessage;

    public static ChatRecordFragment newInstance() {
        Bundle args = new Bundle();
        ChatRecordFragment fragment = new ChatRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_chat_record;
    }

    @Override
    public void onResume() {
        super.onResume();
        mList.clear();
        mList.addAll(ChatApater.getChatData(mActivity));
        mChatApater.notifyDataSetChanged();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mMyHandler = new MyHandler(this);
        mRecycleContent = (WSRecyclerView) view.findViewById(R.id.recyle_content);
        mRecycleContent.setLayoutManager(new LinearLayoutManager(mActivity));
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
                    mIntent.putExtra("userAvatar", mList.get(position).getAvatar());
                    mIntent.putExtra("chatType", Common.PRIVAT);
                    startActivity(mIntent);
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
                                SSConversationTopLevel levelHigh = SSConversationTopLevel.LEVEL_HIGH;
                                conversationList.get(position).setTopLevel(levelHigh);
                                Log.e("onClick: ", (position + 1) +"");
                            }
                        })
                        .setDeleteTextview(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mList.remove(mChatApater.getItem(position));
                                AppContext.getInstance().instance.delConversationById(sourceId);
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
                                SSConversationTopLevel levelHigh = SSConversationTopLevel.DEFAULT;
                                conversationList.get(position).setTopLevel(levelHigh);
                                refreshView();
                            }
                        }).show();
                return false;
            }
        });
        /*刷新会话列表*/
        if (mList == null && mList.size() == 0){
            mRecycleContent.refreshComplete();
        }else {
            mRecycleContent.setOnRefreshCompleteListener(new WSRecyclerView.OnRefreshCompleteListener() {
                @Override
                public void onRefreshComplete() {
                    mMyHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mList.clear();
                            mList.addAll(ChatApater.getChatData(mActivity));
                            mChatApater.notifyDataSetChanged();
                            mRecycleContent.refreshComplete();
                        }
                    }, 1500);
                }
            });
        }
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
        SSEngine.getInstance().setMsgRcvListener(this);
    }

    @Override
    protected void getData() {
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
                    if (ssMessage != null) {
                        mList.clear();
                        mList.addAll(ChatApater.getChatData(mActivity));
                        mChatApater.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

    @Override
    public void receiveMsg(SSMessage ssMessage) {
        if (ssMessage instanceof SSP2PMessage) {
            SSP2PMessage receiveMsg = (SSP2PMessage) ssMessage;
            String sourceId = receiveMsg.getSourceId();
            Log.e("appContext_receiveMsg", sourceId + ":" + receiveMsg.getContent());
            mHandler.sendEmptyMessage(0);
        }
    }


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
