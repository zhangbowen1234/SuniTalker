package com.silver.chat.ui.contact.group;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/11.
 */
public class ModifyNickNameActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.activity_new_friend)
    RelativeLayout activityNewFriend;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify;
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


    @OnClick({R.id.title_left_back, R.id.activity_new_friend,R.id.iv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.activity_new_friend:
                break;
            case R.id.iv_delete:
                ToastUtil.toastMessage(mContext,"我被点击了");
                break;
        }
    }

}
