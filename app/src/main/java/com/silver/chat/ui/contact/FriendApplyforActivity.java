package com.silver.chat.ui.contact;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.view.CircleImageView;
import com.ssim.android.listener.SSNotificationListener;
import com.ssim.android.model.notification.SSNotification;
import com.ssim.android.model.notification.friend.SSAddFriendNotification;

public class FriendApplyforActivity extends BaseActivity implements SSNotificationListener {

    private ImageView mBack;
    private CircleImageView mFriendHead;
    private LinearLayout mLlFriendInfo ,mYseAdd ,mNoAdd;
    private TextView mFriendName ,mFriendPhone ,mAddTime ,mAdditionalMsg ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_applyfor;
    }

    @Override
    protected void initView() {
        super.initView();
        mLlFriendInfo = (LinearLayout) findViewById(R.id.ll_friend_info);
        mBack = (ImageView) findViewById(R.id.title_left_back);
        mFriendHead = (CircleImageView) findViewById(R.id.add_friend_head);
        mFriendName = (TextView) findViewById(R.id.add_friend_name);
        mFriendPhone = (TextView) findViewById(R.id.friend_phone);
        mAddTime = (TextView) findViewById(R.id.add_time);
        mAdditionalMsg = (TextView) findViewById(R.id.additional_msg);
        mYseAdd = (LinearLayout) findViewById(R.id.yes_add_friend);
        mNoAdd = (LinearLayout) findViewById(R.id.no_add_friend);



    }


    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initListener() {
        super.initListener();
        AppContext.getInstance().instance.setNotificationListener(this);
    }

    @Override
    public void receiveNotification(SSNotification ssNotification) {
        if (ssNotification instanceof SSAddFriendNotification){
            SSAddFriendNotification ssAddFriendNotification = (SSAddFriendNotification)ssNotification;

            String sourceId = ssAddFriendNotification.getSourceId();
            String content = ssAddFriendNotification.getContent();
        }
    }
}
