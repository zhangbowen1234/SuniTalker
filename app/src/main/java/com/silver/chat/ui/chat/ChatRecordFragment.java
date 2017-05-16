package com.silver.chat.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Trace;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.library.listener.OnRecyclerItemClickListener;
import com.github.library.listener.OnRecyclerItemLongClickListener;
import com.silver.chat.R;
import com.silver.chat.adapter.ChatApater;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.entity.DataServer;
import com.silver.chat.ui.chat.notification.AddGroupNotifiActivity;
import com.silver.chat.ui.chat.notification.GroupNotificationActivity;
import com.silver.chat.ui.contact.ContactChatActivity;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.dialog.TopDeleteDialog;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.OnItemClickListener;
import com.silver.chat.view.recycleview.pulltorefreshable.WSRecyclerView;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：Fandy on 2016/11/14 14:14
 * 邮箱：fandy618@hotmail.com
 */

public class ChatRecordFragment extends BasePagerFragment {

    private WSRecyclerView mRecycleContent;
    private ChatApater mChatApater;
    private List<ChatBean> mList;
    public static String TOP_STATES = "TOP";
    private MyHandler mMyHandler;

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
    }

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        mList.addAll(DataServer.getChatData());
        mChatApater = new ChatApater(mList);
        mRecycleContent.setAdapter(mChatApater);
        mChatApater.addHeaderView(mRecycleContent.getRefreshView());

        mChatApater.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mChatApater.getItemViewType(position) == ChatBean.CHAT_GROUP_NOTICE){
                    startActivity(GroupNotificationActivity.class);
                }else {
                    Intent mIntent = new Intent(mActivity, ContactChatActivity.class);
                    startActivity(mIntent);
                }
            }
        });
        mChatApater.setOnRecyclerItemLongClickListener(new OnRecyclerItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, final int position) {
                final ChatBean chatBean = mChatApater.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putInt(TOP_STATES, chatBean.getTop());
                TopDeleteDialog topDeleteDialog = new TopDeleteDialog(mActivity);
                topDeleteDialog.setArguments(bundle);//传参
                topDeleteDialog.builder()
                        .setCanceledOnTouchOutside(true)
                        .setTopTextview(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //置顶
                                chatBean.setTop(1);
                                chatBean.setTime(System.currentTimeMillis());
                                refreshView();
                            }
                        })
                        .setDeleteTextview(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mList.remove(mChatApater.getItem(position));
                                ToastUtils.showMessage(getActivity(), "删除成功");
                                mChatApater.notifyDataSetChanged();
                            }
                        })
                        .setNoTopTextview(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //取消置顶
                                chatBean.setTop(0);
                                chatBean.setTime(System.currentTimeMillis());
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

    private void refreshView() {
        //如果不调用sort方法，是不会进行排序的，也就不会调用compareTo
        Collections.sort(mList);
        mChatApater.notifyDataSetChanged();
    }

    @Override
    protected void getData() {

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
