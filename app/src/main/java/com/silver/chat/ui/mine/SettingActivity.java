package com.silver.chat.ui.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.ui.login.LoginActivity;
import com.silver.chat.ui.mine.setting.AboutActivity;
import com.silver.chat.ui.mine.setting.AccountActivity;
import com.silver.chat.ui.mine.setting.ChangeBackgroundActivity;
import com.silver.chat.view.dialog.TvLoginOutDialog;

/**
 * 作者：Fandy on 2016/12/9 10:38
 * 邮箱：fandy618@hotmail.com
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvMyAccount;
    private TextView mTvTheme;
    private TextView mTvAbout;
    private TextView mTvLoginOut;
    private ImageView mIvBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        super.initView();
        mTvMyAccount = (TextView) findViewById(R.id.tv_account);
        mTvTheme = (TextView) findViewById(R.id.tv_theme);
        mTvAbout = (TextView) findViewById(R.id.tv_about);
        mTvLoginOut = (TextView) findViewById(R.id.tv_login_out);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTvMyAccount.setOnClickListener(this);
        mTvTheme.setOnClickListener(this);
        mTvAbout.setOnClickListener(this);
        mTvLoginOut.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_account:
                startActivity(AccountActivity.class);
                break;
            case R.id.tv_theme:
                startActivity(ChangeBackgroundActivity.class);
                break;
            case R.id.tv_about:
                startActivity(AboutActivity.class);
                break;
            case R.id.tv_login_out:
                new TvLoginOutDialog(mContext).builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .setNegativeButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .setPositiveButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(LoginActivity.class);
                                finish();
                            }
                        }).show();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
