package com.silver.chat.ui.mine.setting;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.SkinSettingManager;

/**
 * 作者：Fandy on 2016/12/9 11:56
 * 邮箱：fandy618@hotmail.com
 * <p>
 * 关于
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvBack;
    /**
     * 使用条款
     */
    private TextView mTvClause;
    /**
     * 版本信息
     */
    private TextView mTvVersion;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvClause = (TextView) findViewById(R.id.tv_clause);
        mTvVersion = (TextView) findViewById(R.id.tv_version);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvBack.setOnClickListener(this);
        mTvClause.setOnClickListener(this);
        mTvVersion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_clause:
                break;
            case R.id.tv_version:
                break;
        }
    }
}
