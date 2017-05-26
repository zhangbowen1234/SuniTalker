package com.silver.chat.ui.contact;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.NewFriendAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.entity.ContactMemberBean;
import com.ssim.android.listener.SSNotificationListener;
import com.ssim.android.model.notification.SSFriendNotification;
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
    LinearLayoutManager linearLayoutManager;
    ArrayList<ContactMemberBean> newFriendList;
    private LinearLayout mAddFriend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_friend;
    }

    @Override
    protected void initView() {
        super.initView();
        mBack = (ImageView) findViewById(R.id.title_left_back);
        mNFRecyclerList = (RecyclerView) findViewById(R.id.new_friend_list);
        mAddFriend = (LinearLayout)findViewById(R.id.ll_add_friend);

        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newFriendList = new ArrayList<ContactMemberBean>();

        //设置布局管理器
        mNFRecyclerList.setLayoutManager(linearLayoutManager);
        NewFriendAdapter addFriend = new NewFriendAdapter(R.layout.item_new_friend,newFriendList);

    }

    @Override
    protected void initData() {

        AppContext.getInstance().instance.setNotificationListener(this);

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mAddFriend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_left_back:
                finish();
                break;
            case R.id.ll_add_friend:
                startActivity(AddFriendActivity.class);
                break;

        }
    }

    @Override
    public void receiveNotification(SSNotification ssNotification) {
        if (ssNotification instanceof SSAddFriendNotification){
            SSFriendNotification ssFriendNotification = (SSFriendNotification)ssNotification;

        }
    }
}
