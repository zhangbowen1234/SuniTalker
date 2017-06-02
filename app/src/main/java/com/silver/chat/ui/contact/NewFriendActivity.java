package com.silver.chat.ui.contact;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.NewFriendAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.network.SSIMFrendManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.AgreeFriendAddBody;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.SimpleClickListener;
import com.ssim.android.engine.SSEngine;
import com.ssim.android.listener.SSNotificationListener;
import com.ssim.android.model.notification.SSFriendNotification;
import com.ssim.android.model.notification.SSGroupNotification;
import com.ssim.android.model.notification.SSNormalNotification;
import com.ssim.android.model.notification.SSNotification;
import com.ssim.android.model.notification.friend.SSAddFriendNotification;

import java.util.List;

/**
 * 作者：hibon on 2017/4/16 14:14
 * 新朋友
 */

public class NewFriendActivity extends BaseActivity implements View.OnClickListener, SSNotificationListener {

    private ImageView mBack;
    private RecyclerView mNFRecyclerList;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayout mAddFriend;
    private TextView agreeAdd;
    private String token;
    private AgreeFriendAddBody agreeFriendAddBody;
    private List<SSFriendNotification> friendNotificationList;
    private NewFriendAdapter addFriendAdatpter;
    private SSEngine instance;

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
//        friendNotificationList = new ArrayList<SSFriendNotification>();
        /*设置布局管理器*/
        mNFRecyclerList.setLayoutManager(linearLayoutManager);
        agreeFriendAddBody = new AgreeFriendAddBody();

    }

    @Override
    protected void initData() {
        super.initData();
        /*SDK入口对象*/
        instance = SSEngine.getInstance();
        token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        /*SDK中取得好友添加申请通知列表*/
        friendNotificationList = AppContext.getInstance().instance.getFriendNotificationList();
        addFriendAdatpter = new NewFriendAdapter(R.layout.item_new_friend, friendNotificationList);
        mNFRecyclerList.setAdapter(addFriendAdatpter);

    }

    @Override
    protected void initListener() {
        /* 通知的监听*/
        instance.setNotificationListener(this);
        mBack.setOnClickListener(this);
        mAddFriend.setOnClickListener(this);
        mNFRecyclerList.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                startActivity(FriendApplyforActivity.class);
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                SSIMFrendManger.agreeFriend(mContext, token, agreeFriendAddBody, new ResponseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        Log.e("onSuccess", baseResponse.toString());
                    }

                    @Override
                    public void onFailed(BaseResponse baseResponse) {
                        Log.e("onSuccess", baseResponse.toString());
                    }

                    @Override
                    public void onError() {
                        Log.e("onError", "onError");
                    }
                });

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });


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
     *
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
