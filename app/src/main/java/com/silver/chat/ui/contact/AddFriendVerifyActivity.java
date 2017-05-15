package com.silver.chat.ui.contact;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.network.SSIMFrendManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CircleImageView;

import java.net.URLEncoder;

/**
 * 作者：hibon on 2016/11/16 14:14
 * 添加好友认证
 */

public class AddFriendVerifyActivity extends BaseActivity implements View.OnClickListener {

    private String nickName, friendId;
    private ImageView mBack;
    private TextView mSend, mNickName, mTextCount,mTitle;
    private CircleImageView mFriendHead;
    private EditText mMsgVerify, mRemarksName;
    private String action;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_friend_verify;
    }

    @Override
    protected void initView() {
        super.initView();
        mBack = (ImageView) findViewById(R.id.title_left_back);
        mSend = (TextView) findViewById(R.id.send_btn);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mNickName = (TextView) findViewById(R.id.add_nick_name);
        mFriendHead = (CircleImageView) findViewById(R.id.friend_head);
        mMsgVerify = (EditText) findViewById(R.id.edit_msg_verify);
        mTextCount = (TextView) findViewById(R.id.text_count_show);
        mRemarksName = (EditText) findViewById(R.id.remarks_friend_name);

    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        action = intent.getAction();
        if(TextUtils.equals(action,"AddFriendActivity")) {
            nickName = intent.getStringExtra("nickName");
            friendId = intent.getStringExtra("friendId");
            mNickName.setText(nickName + "");
            mTitle.setText("添加好友验证");
        }else {
            nickName = intent.getStringExtra("groupName");
            friendId = intent.getStringExtra("groupId");
            mNickName.setText(nickName + "");
            mTitle.setText("添加群组验证");

        }

    }

    @Override
    protected void initListener() {
        super.initListener();
        mMsgVerify.addTextChangedListener(mTextWatcher);
        mBack.setOnClickListener(this);
        mSend.setOnClickListener(this);

    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //将输入的内容实时显示
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
            Log.e("sss", s.length() + "");
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = mMsgVerify.getSelectionStart();
            editEnd = mMsgVerify.getSelectionEnd();
            mTextCount.setText((20 - temp.length()) + "");
            if (temp.length() > 20) {
                ToastUtils.showMessage(mContext, "输入字数已超过限制");
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                mMsgVerify.setText(s);
                mMsgVerify.setSelection(tempSelection);
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.send_btn:
                /**
                 * 发送添加信息
                 */
                if(TextUtils.equals(action,"AddFriendActivity")) {
                    sendAddFriend();

                }else {
                    sendAddGroup();

                }
                break;

        }
    }

    /**
     * 请求添加群组
     */
    private void sendAddGroup() {
        //SSIMGroupManger.askJionGroup();

    }

    private void sendAddFriend() {
        String userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        String mMsgText = mMsgVerify.getText().toString();
        if (mMsgText != null || !mMsgText.equals("")) {
            String comment = toURLEncoded(mMsgText);
            SSIMFrendManger.goAddFriends(userId, friendId, comment, token, new ResponseCallBack<BaseResponse>() {
                @Override
                public void onSuccess(BaseResponse baseResponse) {
                    ToastUtils.showMessage(mContext, "申请已发出");
                    finish();
                }

                @Override
                public void onFailed(BaseResponse baseResponse) {
                    ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
                }

                @Override
                public void onError() {
                    ToastUtils.showMessage(mContext, "连接失败");
                }
            });
        } else {
            ToastUtils.showMessage(mContext, "请输入验证消息");
        }
    }

    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            Log.e("toURLEncoded error:", "" + paramString);
            return "";
        }
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
            Log.e("toURLEncoded error:", "" + paramString, localException);
        }
        return "";
    }


}
