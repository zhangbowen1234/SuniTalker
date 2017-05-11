package com.silver.chat.ui.contact.group;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/11.
 * 修改群名称的activity
 */
public class AlertGroupNameActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.activity_new_friend)
    RelativeLayout activityNewFriend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alertgroupname;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @OnClick({R.id.title_left_back, R.id.tv_finish, R.id.iv_delete, R.id.activity_new_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                break;
            case R.id.tv_finish:
                break;
            case R.id.iv_delete:
                break;
            case R.id.activity_new_friend:
                break;
        }
    }
}
