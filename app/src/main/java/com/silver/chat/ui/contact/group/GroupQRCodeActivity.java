package com.silver.chat.ui.contact.group;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/11.
 * 群组的二维码
 */

public class GroupQRCodeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupqrcode;
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

    @OnClick({R.id.tv_title, R.id.iv_scan, R.id.iv_back, R.id.iv_code, R.id.tv_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_scan:
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }
}
