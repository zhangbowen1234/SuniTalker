package com.silver.chat.ui.login;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMLoginManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.ForgetPasswordBean;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.util.PreferenceUtil;
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
    private Button mBtnAuthCode, mVerification,returnLast;
    private TextView tv_send_sms_phone;
    private String uSetP, uASetP, uPhone,uAuthCode;

    private void TimePiece() {
        if (timeCountUtil == null) {
            timeCountUtil = new TimeCountUtil(this, 60000, 1000, mBtnAuthCode);
        }
        timeCountUtil.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgot_verification;
    }


    protected void initView() {
        mAuthCode = (MyLineEditText) findViewById(R.id.edit_auth_code);
        mBtnAuthCode = (Button) findViewById(R.id.btn_auth_code);
        mVerification = (Button) findViewById(R.id.verification_bt_register);
        tv_send_sms_phone = (TextView) findViewById(R.id.tv_send_sms_phone);
        returnLast = (Button) findViewById(R.id.return_last);

        uSetP = getIntent().getStringExtra("newPwd");
        uASetP = getIntent().getStringExtra("reNewPwd");
        uPhone = getIntent().getStringExtra("uPhone");
        tv_send_sms_phone.setText("已发送短信至" + uPhone);

        //计时器
        TimePiece();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBtnAuthCode.setOnClickListener(this);
        mVerification.setOnClickListener(this);
        returnLast.setOnClickListener(this);
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
                goForgetPwd();
                break;
            case R.id.btn_auth_code:
                //重新发送验证码并计时
                TimePiece();
                /**
                 * 重新获取验证码
                 */
                sendSmsCode(uPhone);
                break;
            case R.id.return_last:
                finish();
                break;
        }
    }
    private void goForgetPwd() {
        ForgetPasswordBean instance = ForgetPasswordBean.getInstance();
        instance.setSmsCode(uAuthCode);
        instance.setNewPwd(uSetP);
        instance.setReNewPwd(uASetP);
        instance.setPhone(uPhone);
        SSIMLoginManger.forgetpwd(mContext,Common.version, instance, new ResponseCallBack<BaseResponse<ForgetPasswordBean>>() {
            @Override
            public void onSuccess(BaseResponse<ForgetPasswordBean> forgetPasswordBeanBaseResponse) {
                ToastUtils.showMessage(mContext, forgetPasswordBeanBaseResponse.getStatusMsg());
                PreferenceUtil.getInstance(mContext).setString("phone", uPhone);
                PreferenceUtil.getInstance(mContext).setString("pwd", uSetP);
                finish();
            }

            @Override
            public void onFailed(BaseResponse<ForgetPasswordBean> forgetPasswordBeanBaseResponse) {
                ToastUtils.showMessage(mContext, forgetPasswordBeanBaseResponse.getStatusMsg());
            }

            @Override
            public void onError() {
                ToastUtils.showMessage(mContext, "请求失败");
            }
        });
    }
    private void sendSmsCode(String uPhone) {
        SSIMLoginManger.userReginstCode(mContext,Common.version, uPhone, Common.RegType, new ResponseCallBack<BaseResponse>() {


            @Override
            public void onSuccess(BaseResponse baseResponse) {
                Log.e(TAG, baseResponse.getStatusMsg());
                ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
            }

            @Override
            public void onFailed(BaseResponse baseResponse) {
                ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
            }

            @Override
            public void onError() {
                Log.e(TAG, "onError");
                ToastUtils.showMessage(mContext, "网络连接错误");
            }
        });
    }

}
