package com.silver.chat.ui.contact;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.network.SSIMFrendManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.AgreeFriendAddBody;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CircleImageView;
import com.ssim.android.listener.SSNotificationListener;
import com.ssim.android.model.notification.SSNotification;
import com.ssim.android.model.notification.friend.SSAddFriendNotification;

public class FriendApplyforActivity extends BaseActivity implements SSNotificationListener, View.OnClickListener {

    private ImageView mBack;
    private CircleImageView mFriendHead;
    private LinearLayout mLlFriendInfo ,mYseAdd ,mNoAdd;
    private TextView mFriendName ,mFriendPhone ,mAddTime ,mAdditionalMsg ;
    private String token,userId ,mNickName ,mAvatar ,sourceId;
    private AgreeFriendAddBody agreeFriendAddBody;

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
        agreeFriendAddBody = new AgreeFriendAddBody();


    }


    @Override
    protected void initData() {
        super.initData();
        sourceId = getIntent().getExtras().getString("sourceId");
        token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");
        mNickName = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.NICKNAME, "");
        mAvatar = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.AVATAR, "");

    }

    @Override
    protected void initListener() {
        super.initListener();
        AppContext.getInstance().instance.setNotificationListener(this);
        mYseAdd.setOnClickListener(this);

    }



    @Override
    public void receiveNotification(SSNotification ssNotification) {
        if (ssNotification instanceof SSAddFriendNotification){
            SSAddFriendNotification ssAddFriendNotification = (SSAddFriendNotification)ssNotification;

            String sourceId = ssAddFriendNotification.getSourceId();
            String content = ssAddFriendNotification.getContent();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yes_add_friend:
                agreeFriendAddBody.setAppName("innerapp");
                agreeFriendAddBody.setSourceId(userId);
                agreeFriendAddBody.setSourceName(mNickName);
                agreeFriendAddBody.setSourceAvatar(mAvatar);
                agreeFriendAddBody.setTargetId(sourceId);
                SSIMFrendManger.agreeFriend(mContext, token, agreeFriendAddBody, new ResponseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        Log.e("onSuccess", baseResponse.toString());
                        ToastUtils.showMessage(mContext,baseResponse.getStatusMsg());
                    }

                    @Override
                    public void onFailed(BaseResponse baseResponse) {
                        Log.e("onSuccess", baseResponse.toString());
                        ToastUtils.showMessage(mContext,baseResponse.getStatusMsg());
                    }

                    @Override
                    public void onError() {
                        Log.e("onError", "onError");
                        ToastUtils.showMessage(mContext,"连接服务器失败");
                    }
                });
                break;


        }
    }
}
