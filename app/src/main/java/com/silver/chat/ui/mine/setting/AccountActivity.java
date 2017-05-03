package com.silver.chat.ui.mine.setting;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;

/**
 * 作者：Fandy on 2016/12/9 11:34
 * 邮箱：fandy618@hotmail.com
 * <p>
 * 账户
 */

public class AccountActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 返回
     */
    private ImageView mIvBack;
    /**
     * 完成
     */
    private TextView mTvFinish;
    /**
     * 头像
     */
    private RelativeLayout mRlyAvatar;
    private ImageView mIvAvatar;
    /**
     * 名字
     */
    private LinearLayout mLlyName;
    private TextView mTvName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvFinish = (TextView) findViewById(R.id.iv_save);
        mRlyAvatar = (RelativeLayout) findViewById(R.id.rly_avatar);
        mIvAvatar = (ImageView) findViewById(R.id.iv_avatar);
        mLlyName = (LinearLayout) findViewById(R.id.lly_name);
        mTvName = (TextView) findViewById(R.id.tv_name);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvBack.setOnClickListener(this);
        mTvFinish.setOnClickListener(this);
        mRlyAvatar.setOnClickListener(this);
        mLlyName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_save:
                finish();
                break;
            case R.id.rly_avatar:
                break;
            case R.id.lly_name:
                break;
        }
    }
}
