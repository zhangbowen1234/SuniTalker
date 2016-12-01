package com.silver.chat.ui.mine;

import android.view.View;
import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;

/**
 * 作者：Fandy on 2016/12/1 09:51
 * 邮箱：fandy618@hotmail.com
 */

public class FriendInfoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_firend_info;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBack = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
