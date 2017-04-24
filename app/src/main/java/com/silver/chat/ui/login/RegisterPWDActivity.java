package com.silver.chat.ui.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.ScreenManager;
import com.silver.chat.util.TimeCountUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.MyLineEditText;


/**
 * Created by hibon on 2016/11/14.
 * 注册账号
 */
public class RegisterPWDActivity extends BaseActivity implements View.OnClickListener {

    private EditText mSetPwd, mAgainSetPwd;
    private MyLineEditText mAuthCode;
    private Button mBtnReg, mBtnAuthCode, mBtnRegPhone,mBtnAuthCodeOther;
    private TextView mReturnLast,tv_send_number;
    private String uSetP, uASetP, uAuthC;
    TimeCountUtil timeCountUtil;//倒计时工具
//    private final int charMaxNum = 6; // 允许输入的字数


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_pwd;
    }

    private void TimePiece() {
        if (timeCountUtil == null) {
            timeCountUtil = new TimeCountUtil(this, 60000, 1000, mBtnAuthCode);
        }
        timeCountUtil.start();
    }

    protected void initView() {
        mSetPwd = (EditText) findViewById(R.id.set_pwd);
        mAgainSetPwd = (EditText) findViewById(R.id.again_set_pwd);
        mAuthCode = (MyLineEditText) findViewById(R.id.edit_auth_code);
        mBtnReg = (Button) findViewById(R.id.btn_register_id);
        mBtnAuthCode = (Button) findViewById(R.id.btn_auth_code);
        mReturnLast = (TextView) findViewById(R.id.return_last);
        mBtnAuthCodeOther = (Button) findViewById(R.id.btn_auth_code_other);
        tv_send_number = (TextView) findViewById(R.id.tv_send_number);


        mBtnReg.setOnClickListener(this);
        mBtnAuthCode.setOnClickListener(this);
        mReturnLast.setOnClickListener(this);
        mBtnAuthCodeOther.setOnClickListener(this);

//        mAuthCode.addTextChangedListener(new EditChangedListener());
        //计时器
        TimePiece();
    }

//    class EditChangedListener implements TextWatcher {
//        private CharSequence temp; // 监听前的文本
//        private int editStart; // 光标开始位置
//        private int editEnd; // 光标结束位置
//
//        // 输入文本之前的状态
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            temp = s;
//        }
//
//        // 输入文字中的状态，count是一次性输入字符数
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            if (charMaxNum - s.length() <= 6) {
////              tip.setText(还能输入 + (charMaxNum - s.length()) + 字符);
//
//            }
//        }
//
//        // 输入文字后的状态
//        @Override
//        public void afterTextChanged(Editable s) {
//            /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
//            editStart = mAuthCode.getSelectionStart();
//            editEnd = mAuthCode.getSelectionEnd();
//            if (temp.length() > charMaxNum) {
//                s.delete(editStart - 1, editEnd);
//                mAuthCode.setText(s);
//                mAuthCode.setSelection(s.length());
//
//
////
//            }
//        }
//    };


    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btn_register_id:

                uSetP = mSetPwd.getText().toString();
                uASetP = mAgainSetPwd.getText().toString();
                uAuthC = mAuthCode.getText().toString();
//                String autoCode = "123456";

                if (uSetP == null || "".equals(uSetP)) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "密码不能为空!");
                    return;
                }
                if (uSetP.length() < 6 || uSetP.length() > 16) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (uASetP == null || "".equals(uASetP)) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (uASetP.length() < 6 || uASetP.length() > 16) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入6-16位字母或数字!");
                    return;
                }
                if (!(uSetP.equals(uASetP))) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "两次密码不一致!");
                    return;
                }
                if (uAuthC == null || "".equals(uAuthC)) {
                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入验证码!");
                    return;
                }
//                if (!autoCode.equals(uAuthC)) {
//                    ToastUtils.showMessage(RegisterPWDActivity.this, "请输入正确的验证码!");
//                    return;
//                }
                break;
            case R.id.btn_auth_code:

                //重新发送验证码并计时
                TimePiece();

                break;
            case R.id.btn_auth_code_other:
                ScreenManager.getScreenManager().goBlackPage();
                finish();
                break;
            case R.id.return_last:
                ScreenManager.getScreenManager().goBlackPage();
                finish();
                break;
        }

    }


}
