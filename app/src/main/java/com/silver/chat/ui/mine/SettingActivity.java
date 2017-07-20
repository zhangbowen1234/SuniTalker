package com.silver.chat.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silver.chat.MainActivity;
import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMLoginManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.ui.login.LoginActivity;
import com.silver.chat.ui.mine.setting.AboutActivity;
import com.silver.chat.ui.mine.setting.AccountActivity;
import com.silver.chat.ui.mine.setting.ChangeBackgroundActivity;
import com.silver.chat.util.AppManager;
import com.silver.chat.util.NetUtils;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
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
                                String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
                                if (NetUtils.isConnected(mContext)) {//是否联网
                                    if (token != null && !"".equals(token)) {
                                        SSIMLoginManger.outLogin(mContext, Common.version, token, new ResponseCallBack<BaseResponse>() {
                                            @Override
                                            public void onSuccess(BaseResponse baseResponse) {
                                                PreferenceUtil.getInstance(mContext).setFirst(false);
                                                PreferenceUtil.getInstance(mContext).setLog(false);
                                                Bundle loginBundle = new Bundle();
                                                loginBundle.putInt("type", Common.LoginType);
                                                startActivity(LoginActivity.class, loginBundle);
                                                AppManager.getInstance().finishActivity(MainActivity.class);
                                                finish();
                                                ToastUtils.showMessage(mContext, "退出成功");
                                            }

                                            @Override
                                            public void onFailed(BaseResponse baseResponse) {
                                                Toast.makeText(mContext, baseResponse.getStatusMsg(), Toast.LENGTH_SHORT).show();
                                                PreferenceUtil.getInstance(mContext).setFirst(false);
                                                PreferenceUtil.getInstance(mContext).setLog(false);
                                                Bundle loginBundle = new Bundle();
                                                loginBundle.putInt("type", Common.LoginType);
                                                startActivity(LoginActivity.class, loginBundle);
                                                AppManager.getInstance().finishActivity(MainActivity.class);
                                                finish();
                                                ToastUtils.showMessage(mContext, "退出成功");
                                            }

                                            @Override
                                            public void onError() {
                                                Toast.makeText(mContext, "连接异常", Toast.LENGTH_SHORT).show();
                                                PreferenceUtil.getInstance(mContext).setFirst(false);
                                                PreferenceUtil.getInstance(mContext).setLog(false);
                                                Bundle loginBundle = new Bundle();
                                                loginBundle.putInt("type", Common.LoginType);
                                                startActivity(LoginActivity.class, loginBundle);
                                                finish();
                                                ToastUtils.showMessage(mContext, "退出成功");
                                            }
                                        });
                                    }
                                } else {
                                    ToastUtils.showMessage(mContext, "请检查网络!");
                                }
                            }
                        }).show();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
