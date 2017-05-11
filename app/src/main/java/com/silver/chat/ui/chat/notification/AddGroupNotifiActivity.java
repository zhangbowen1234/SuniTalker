package com.silver.chat.ui.chat.notification;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class AddGroupNotifiActivity extends BaseActivity {

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
    @BindView(R.id.tv_applyjion)
    TextView tvApplyjion;
    @BindView(R.id.tv_group_name)
    TextView tvGroupName;
    @BindView(R.id.tv_notification_time)
    TextView tvNotificationTime;
    @BindView(R.id.tv_apply_remark)
    TextView tvApplyRemark;
    @BindView(R.id.tv_isagreen_group)
    TextView tvIsagreenGroup;
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.tv_no_agree)
    TextView tvNoAgree;
    @BindView(R.id.ll_isadd_group)
    LinearLayout llIsaddGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addgroup_notification;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.title_left_back, R.id.rl_jion_info, R.id.tv_agree, R.id.tv_no_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.rl_jion_info:
                //进入好友信息
                tvIsagreenGroup.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_agree:
                break;
            case R.id.tv_no_agree:
                break;
        }
    }
}
