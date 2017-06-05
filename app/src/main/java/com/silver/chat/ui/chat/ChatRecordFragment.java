package com.silver.chat.ui.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Trace;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.library.listener.OnRecyclerItemClickListener;
import com.github.library.listener.OnRecyclerItemLongClickListener;
import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.ChatApater;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.base.Common;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.entity.DataServer;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.ui.chat.notification.AddGroupNotifiActivity;
import com.silver.chat.ui.chat.notification.GroupNotificationActivity;
import com.silver.chat.ui.contact.ContactChatActivity;
import com.silver.chat.util.DateUtils;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.dialog.TopDeleteDialog;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.OnItemClickListener;
import com.silver.chat.view.recycleview.pulltorefreshable.WSRecyclerView;
import com.ssim.android.constant.SSSessionType;
import com.ssim.android.listener.SSMessageReceiveListener;
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
import java.util.Objects;

import static android.R.attr.type;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.silver.chat.adapter.ChatApater.sourceId;
import static com.silver.chat.util.Utils.context;

/**
 * 作者：Fandy on 2016/11/14 14:14
 * 邮箱：fandy618@hotmail.com
 */

public class ChatRecordFragment extends BasePagerFragment implements SSNotificationListener{

    private WSRecyclerView mRecycleContent;
    private ChatApater mChatApater;
    private List<ChatBean> mList;
    public static String TOP_STATES = "TOP";
    private MyHandler mMyHandler;
    public String friendid;
    private SSSession sSSession;

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
        friendid = ChatApater.sourceId;
        sSSession = new SSSession();
    }

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        mList.addAll(ChatApater.getChatData(mActivity));
        mChatApater = new ChatApater(mList);
        mRecycleContent.setAdapter(mChatApater);
        mChatApater.addHeaderView(mRecycleContent.getRefreshView());
        mChatApater.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mChatApater.getItemViewType(position +1 ) == ChatBean.CHAT_SINGLR){
                    Intent mIntent = new Intent(mActivity,ContactChatActivity.class);
                    mIntent.putExtra("contactName",mList.get(position).getUserName());
                    mIntent.putExtra("friendId",mList.get(position).getUserId());
                    mIntent.putExtra("chatType", Common.PRIVAT);
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
        AppContext.getInstance().instance.setNotificationListener(this);
        getChatDat();
    }

    @Override
    public void receiveNotification(SSNotification ssNotification) {
        if (ssNotification instanceof SSFriendNotification){
            SSFriendNotification ssFriendNotification = (SSFriendNotification) ssNotification;
            String sourceId = ssFriendNotification.getSourceId();
            String content = ssFriendNotification.getContent();
            if (Objects.equals(friendid, sourceId)){
                ChatBean chatBean = new ChatBean();
                chatBean.setContent(content);
                mChatApater.addData(chatBean);
                mChatApater.notifyDataSetChanged();
            }
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
    private void getChatDat() {
        String userId = PreferenceUtil.getInstance(context).getString(PreferenceUtil.USERID, "");
        List<SSSession> sessionList = AppContext.getInstance().instance.getSessionList(userId);
        for (int i = 0; i < sessionList.size(); i++) {
            SSSessionType sessionType = sessionList.get(i).getSessionType();
            //获取好友聊天列表
            String SINGLR_avatar;
            String SINGLR_nickname;
            if (sessionType == SSSessionType.P2PCHAT) {
                sourceId = sessionList.get(i).getSourceId();
                BaseDao<ContactListBean> mDao = DBHelper.get().dao(ContactListBean.class);
                List<ContactListBean> friendId = mDao.query(WhereInfo.get().equal("friendId", sourceId));
                for (int j = 0; j < friendId.size(); j++) {
                    SINGLR_avatar = friendId.get(j).getAvatar();
                    SINGLR_nickname = friendId.get(j).getNickName();
//                    String contents= sessionList.get(j).getContent();
                    long sendTimes = sessionList.get(j).getSendTime();
//                    String Times = DateUtils.formatTimeSimple(sendTimes);
                    sSSession.setUserName(SINGLR_nickname);
                    sSSession.setUserAvatar(SINGLR_avatar);
                }
                //获取群组聊天列表
            }
        }
        sessionList.add(sSSession);
        Log.e("sSSession: ", sSSession.getUserName()+sSSession.getUserAvatar()+sSSession.getContent());
    }

}
