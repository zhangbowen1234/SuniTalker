package com.silver.chat.ui.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.NumberUtils;
import com.silver.chat.util.ScreenManager;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.MyLineEditText;

public class RegisterPhoneActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnAuthCode;
    private ImageView qq, weixin, xinlang;
    private TextView mReturnLast;
    private MyLineEditText mUserPhone;
    private String uPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_phone;
    }

    protected void initView() {

        mBtnAuthCode = (Button) findViewById(R.id.btn_auth_code);
        mUserPhone = (MyLineEditText) findViewById(R.id.user_phone);
        qq = (ImageView) findViewById(R.id.qq);
        weixin = (ImageView) findViewById(R.id.weixin);
        xinlang = (ImageView) findViewById(R.id.xinlang);
        mReturnLast = (TextView) findViewById(R.id.return_last);

        mBtnAuthCode.setOnClickListener(this);
        qq.setOnClickListener(this);
        weixin.setOnClickListener(this);
        xinlang.setOnClickListener(this);
        mReturnLast.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_auth_code:
                uPhone = mUserPhone.getText().toString();
                if (uPhone == null || "".equals(uPhone)) {
                    ToastUtils.showMessage(RegisterPhoneActivity.this, "手机号不能为空!");
                    return;
                }
                if (uPhone.length() != 11 || !(NumberUtils.isMobileNum(uPhone))) {
                    ToastUtils.showMessage(RegisterPhoneActivity.this, "手机号不正确!");
                    return;
                }

                Intent regPIntent = new Intent(this, RegisterPWDActivity.class);
                ScreenManager.getScreenManager().StartPage(RegisterPhoneActivity.this, regPIntent, true);
                break;

            case R.id.qq:
                ToastUtils.showMessage(this, "尚未开通此功能");
                break;

            case R.id.xinlang:
                ToastUtils.showMessage(this, "尚未开通此功能");
                break;

            case R.id.weixin:
                ToastUtils.showMessage(this, "尚未开通此功能");
                break;

            case R.id.return_last:
                // 点击登录重新回到登录页面
                ScreenManager.getScreenManager().goBlackPage();
                finish();
                break;
        }
    }


}