package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/11.
 * 群资料
 */
public class GroupDataActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.tv_grouphead)
    TextView tvGrouphead;
    @BindView(R.id.iv_grouphead)
    ImageView ivGrouphead;
    @BindView(R.id.rl_group_head)
    RelativeLayout rlGroupHead;
    @BindView(R.id.tv_groupname)
    TextView tvGroupname;
    @BindView(R.id.tv_groupname1)
    TextView tvGroupname1;
    @BindView(R.id.rl_group_name)
    RelativeLayout rlGroupName;
    @BindView(R.id.tv_groupintroduce)
    TextView tvGroupintroduce;
    @BindView(R.id.tv_creattime)
    TextView tvCreattime;
    @BindView(R.id.activity_new_friend)
    RelativeLayout activityNewFriend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupdata;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @OnClick({R.id.title_left_back, R.id.tv_grouphead, R.id.iv_grouphead, R.id.rl_group_head, R.id.tv_groupname, R.id.tv_groupname1, R.id.rl_group_name, R.id.tv_groupintroduce, R.id.tv_creattime, R.id.activity_new_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.tv_grouphead:
                break;
            case R.id.iv_grouphead:
                break;
            case R.id.rl_group_head:
                break;
            case R.id.tv_groupname:
                break;
            case R.id.tv_groupname1:
                break;
            case R.id.rl_group_name:
                Intent intent = new Intent(mContext, AlertGroupNameActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_groupintroduce:
                break;
            case R.id.tv_creattime:
                break;
            case R.id.activity_new_friend:
                break;
        }
    }
}
