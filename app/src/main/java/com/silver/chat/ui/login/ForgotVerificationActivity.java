package com.silver.chat.ui.login;

import android.view.View;
import android.widget.Button;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.TimeCountUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.MyLineEditText;

/**
 * Created by hibon on 2016/11/14.
 * 忘记密码
 */
public class ForgotVerificationActivity extends BaseActivity implements View.OnClickListener {


    private MyLineEditText mAuthCode;
    TimeCountUtil timeCountUtil;//倒计时工具
    private Button mBtnAuthCode, mVerification;
    private String uAuthCode;

    private void TimePiece() {
        if (timeCountUtil == null) {
            timeCountUtil = new TimeCountUtil(this, 60000, 1000, mBtnAuthCode);
        }
        timeCountUtil.start();
    }

    protected void initView() {

        mAuthCode = (MyLineEditText) findViewById(R.id.edit_auth_code);
        mBtnAuthCode = (Button) findViewById(R.id.btn_auth_code);
        mVerification = (Button) findViewById(R.id.verification_bt_register);

        mVerification.setOnClickListener(this);
        //计时器
        TimePiece();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgot_verification;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verification_bt_register:

                uAuthCode = mAuthCode.getText().toString();

                if (uAuthCode == null || "".equals(uAuthCode)) {
                    ToastUtils.showMessage(ForgotVerificationActivity.this, "请输入验证码!");
                    return;
                }


                break;
            case R.id.btn_auth_code:

                //重新发送验证码并计时
                TimePiece();

                break;
        }


    }
}
