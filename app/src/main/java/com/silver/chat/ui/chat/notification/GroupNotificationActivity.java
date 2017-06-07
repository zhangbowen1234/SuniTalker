package com.silver.chat.ui.chat.notification;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.GroupNotifiAdapter;
import com.silver.chat.adapter.NewFriendAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.OnItemClickListener;
import com.ssim.android.constant.SSPublishType;
import com.ssim.android.engine.SSEngine;
import com.ssim.android.listener.SSNotificationListener;
import com.ssim.android.model.notification.SSFriendNotification;
import com.ssim.android.model.notification.SSGroupNotification;
import com.ssim.android.model.notification.SSNotification;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.silver.chat.adapter.ChatApater.sourceId;

/**
 * Created by bowen on 2017/5/11.
 */

public class GroupNotificationActivity extends BaseActivity implements SSNotificationListener {

    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.rv_group_notification)
    RecyclerView rvGroupNotification;
    private LinearLayoutManager linearLayoutManager;
    private GroupNotifiAdapter mAdapter;
    private List<SSGroupNotification> groupNotificationList;
    private List<SSGroupNotification> mUserList;
    private BaseDao<GroupMemberBean> mDao;
    private String sourceId, content;
    private SSGroupNotification mSSGroupNotification;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_notification;
    }

    @Override
    protected void initView() {
        super.initView();
        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器
        rvGroupNotification.setLayoutManager(linearLayoutManager);
//        mAdapter = new GroupNotifiAdapter(R.layout.item_group_notification,mUserList);
//        rvGroupNotification.setAdapter(mAdapter);
        if (mUserList == null) {
            mUserList = new ArrayList<>();
        }
        mDao = DBHelper.get().dao(GroupMemberBean.class);
    }

    @Override
    protected void initData() {
        super.initData();
     /*SDK中取得群通知列表*/
        groupNotificationList= SSEngine.getInstance().getGroupNotificationList();
        QueryDbParent();
        if (mAdapter == null) {
            mAdapter = new GroupNotifiAdapter(R.layout.item_group_notification, mUserList);
        }
        rvGroupNotification.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        /* 通知的监听*/
        AppContext.getInstance().instance.setNotificationListener(this);

        rvGroupNotification.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(AddGroupNotifiActivity.class);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.title_left_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.title_left_back:
                finish();
                break;
        }
    }
    /**
     * 监听好友申请
     *
     * @param ssNotification
     */
    @Override
    public void receiveNotification(SSNotification ssNotification) {
        if (ssNotification instanceof SSGroupNotification) {
         /*群通知监听*/
            SSGroupNotification ssGroupNotiFication = (SSGroupNotification) ssNotification;
            String content = ssGroupNotiFication.getContent();
            String sourceId = ssGroupNotiFication.getSourceId();
            Log.e("群通知:", sourceId + "/" + content);
            groupNotificationList.add(ssGroupNotiFication);
            /*查询数据库*/
            QueryDbParent();
        }
    }
    private void QueryDbParent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < groupNotificationList.size(); i++) {
                    sourceId = groupNotificationList.get(i).getSourceId();
                    content = groupNotificationList.get(i).getContent();
                    Log.e("sourceId=content", sourceId + "/" + content);
                    List<GroupMemberBean> userInfoList = mDao.query(WhereInfo.get().equal("userId", sourceId));
                    Log.e("onMainThread", "data+" + userInfoList);
                    if (userInfoList.isEmpty()){
                        /*其次从网络获取数据*/
//                         httpContactList();
                        Log.e("isEmpty", "data+" + userInfoList);
                    }else {
                        for (int j = 0; j < userInfoList.size(); j++) {
                            String mDaoUserId = String.valueOf(userInfoList.get(j).getUserId());
                            Log.e("mDao_nickName", userInfoList.get(j).getNickName());
                            if (mDaoUserId.equals(sourceId)) {
                                mSSGroupNotification = new SSGroupNotification();
                                mSSGroupNotification.setContent(content);
                                mSSGroupNotification.setSourceAvatar(userInfoList.get(j).getAvatar());
                                mSSGroupNotification.setSourceName(userInfoList.get(j).getNickName());
                                mUserList.add(mSSGroupNotification);
                                Log.e("a", sourceId + "/" + mDaoUserId + "/" + content);
                            } else {
                                Log.e("!= userId", "data+" + userInfoList);
                            /*其次从网络获取数据*/
//                         httpContactList();
                            }
                            mAdapter.setNewData(mUserList);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }).start();
    }
}
