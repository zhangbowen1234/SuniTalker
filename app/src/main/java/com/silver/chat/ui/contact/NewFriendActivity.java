package com.silver.chat.ui.contact;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.NewFriendAdapter;
import com.silver.chat.base.BaseActivity;
import com.ssim.android.listener.SSNotificationListener;
import com.ssim.android.model.chat.SSP2PMessage;
import com.ssim.android.model.notification.SSGroupNotification;
import com.ssim.android.model.notification.SSNormalNotification;
import com.ssim.android.model.notification.SSNotification;
import com.ssim.android.model.notification.friend.SSAddFriendNotification;

import java.util.ArrayList;

/**
 * 作者：hibon on 2017/4/16 14:14
 * 新朋友
 */

public class NewFriendActivity extends BaseActivity implements View.OnClickListener, SSNotificationListener {

    private ImageView mBack;
    private RecyclerView mNFRecyclerList;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<SSP2PMessage> newFriendList;
    private LinearLayout mAddFriend;
    private TextView agreeAdd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_friend;
    }

    @Override
    protected void initView() {
        super.initView();
        mBack = (ImageView) findViewById(R.id.title_left_back);
        mNFRecyclerList = (RecyclerView) findViewById(R.id.new_friend_list);
        mAddFriend = (LinearLayout) findViewById(R.id.ll_add_friend);
        agreeAdd = (TextView) findViewById(R.id.agree_add_friend);

        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newFriendList = new ArrayList<SSP2PMessage>();

        /*设置布局管理器*/
        mNFRecyclerList.setLayoutManager(linearLayoutManager);
        NewFriendAdapter addFriend = new NewFriendAdapter(R.layout.item_new_friend, newFriendList);

    }

    @Override
    protected void initData() {
//        AppContext.getInstance().instance.

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mAddFriend.setOnClickListener(this);
//        mNFRecyclerList.addOnItemTouchListener(this);
        /* 通知的监听*/
        AppContext.getInstance().instance.setNotificationListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.ll_add_friend:
                startActivity(AddFriendActivity.class);
                break;

        }
    }

    /**
     * 监听好友申请
     * @param ssNotification
     */
    @Override
    public void receiveNotification(SSNotification ssNotification) {
        if (ssNotification instanceof SSAddFriendNotification) {
            /*好友申请添加好友的通知监听*/
            SSAddFriendNotification ssFriendNotification = (SSAddFriendNotification) ssNotification;
            String sourceId = ssFriendNotification.getSourceId();


        } else if (ssNotification instanceof SSGroupNotification) {
            SSGroupNotification ssGroupNotification = (SSGroupNotification) ssNotification;
        } else if (ssNotification instanceof SSNormalNotification) {
            SSNormalNotification ssNormalNotification = (SSNormalNotification) ssNotification;
        }
    }

}
