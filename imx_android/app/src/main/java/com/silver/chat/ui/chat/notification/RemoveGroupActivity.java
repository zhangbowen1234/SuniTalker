package com.silver.chat.ui.chat.notification;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bowen on 2017/5/11.
 */

public class RemoveGroupActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_jion_info)
    ImageView ivJionInfo;
    @BindView(R.id.tv_register_phone)
    TextView tvRegisterPhone;
    @BindView(R.id.rl_jion_info)
    RelativeLayout rlJionInfo;
    @BindView(R.id.tv_notification_time)
    TextView tvNotificationTime;
    @BindView(R.id.tv_process_people)
    TextView tvProcessPeople;
    @BindView(R.id.tv_apply_again)
    TextView tvApplyAgain;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remove_group;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_left_back, R.id.rl_jion_info, R.id.tv_apply_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                break;
            case R.id.rl_jion_info:
                break;
            case R.id.tv_apply_again:
                break;
        }
    }
}
