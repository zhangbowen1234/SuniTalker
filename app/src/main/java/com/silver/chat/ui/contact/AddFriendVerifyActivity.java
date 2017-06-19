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
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.AskJoinGroup;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CircleImageView;

import java.net.URLEncoder;

/**
 * 作者：hibon on 2016/11/16 14:14
 * 添加好友认证
 */

public class AddFriendVerifyActivity extends BaseActivity implements View.OnClickListener {

    private String nickName, friendId, action, groupAvatar, personAvatar, userName, token, userId;
    private ImageView mBack;
    private TextView mSend, mNickName, mTextCount, mTitle;
    private CircleImageView mFriendHead;
    private EditText mMsgVerify, mRemarksName;
    private int targetimid;
    //请求体常量
    private final String INNERAPP = "innerapp";
    //用户输入的验证信息
    private String verifyMsg;

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
        if (TextUtils.equals(action, "AddFriendActivity")) {
            nickName = intent.getStringExtra("nickName");
            friendId = intent.getStringExtra("friendId");
            mNickName.setText(nickName + "");
            mTitle.setText("添加好友验证");
        } else {
            nickName = intent.getStringExtra("groupName");
            friendId = intent.getIntExtra("groupId", -1) + "";
            targetimid = intent.getIntExtra("targetimid", -1);
            groupAvatar = intent.getStringExtra("groupAvatar");
            mNickName.setText(nickName + "");
            mTitle.setText("添加群组验证");
        }
        personAvatar = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.AVATAR, "");
        userName = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.NICKNAME, "");
        token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");

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
            verifyMsg = mMsgVerify.getText().toString();
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
                 * 根据上个跳转界面传递的数据的不
                 * 同来发送不同的添加信息
                 */
                if (verifyMsg != null) {
                    if (TextUtils.equals(action, "AddFriendActivity")) {
                    /*申请添加好友*/
                        sendAddFriend();
                    /**好友备注   暂时注掉等后台该接口*/
//                        remarksFdNm();
                    }else {
                        sendAddGroup();
                    }
                } else {
                    verifyMsg= "123";
                    if (TextUtils.equals(action, "AddFriendActivity")) {
                    /*申请添加好友*/
                        sendAddFriend();
                    /*好友备注*/
//                        remarksFdNm();
                    }else {
                        sendAddGroup();
                    }
//                    ToastUtil.toastMessage(mContext, "验证信息不能为空");
                }

                break;
        }
    }

    private void remarksFdNm() {

        if (mRemarksName.getText().toString() != null || !(mRemarksName.getText().toString()).equals("")) {
            String remarName = mRemarksName.getText().toString();
            SSIMFrendManger.revampFriendName(mContext, token, userId, friendId, remarName, new ResponseCallBack<BaseResponse>() {
                @Override
                public void onSuccess(BaseResponse baseResponse) {
                    Log.e(TAG, "修改备注成功");
                }

                @Override
                public void onFailed(BaseResponse baseResponse) {
                    Log.e(TAG, "修改备注失败");
                }

                @Override
                public void onError() {
                    Log.e(TAG, "备注网络异常");
                }
            });
        }
    }

    /**
     * 请求添加群组
     */
    private void sendAddGroup() {

        AskJoinGroup instance = AskJoinGroup.getInstance();
        instance.setSourceId(userId);
        instance.setSourceName(userName);
        instance.setSourceAvatar(personAvatar);
        instance.setComment(verifyMsg);
        instance.setTargetId(targetimid + "");
        instance.setGroupId(friendId + "");
        instance.setGroupName(nickName);
        instance.setGroupAvatar(groupAvatar);
        instance.setAppName(INNERAPP);
        //
        SSIMGroupManger.askJoinGroup(mContext, token, instance, new ResponseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                ToastUtil.toastMessage(mContext, baseResponse.getStatusMsg());
                finish();
            }

            @Override
            public void onFailed(BaseResponse baseResponse) {
                ToastUtil.toastMessage(mContext, baseResponse.getStatusMsg());

            }

            @Override
            public void onError() {
                ToastUtils.showMessage(mContext, "连接服务器失败");
            }
        });

    }

    private void sendAddFriend() {
        String  verify = toURLEncoded(verifyMsg);
        SSIMFrendManger.goAddFriends(mContext, userId, friendId, verify, token, new ResponseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                ToastUtils.showMessage(mContext, "申请已发出");
                finish();
            }
            @Override
            public void onFailed(BaseResponse baseResponse) {
                ToastUtils.showMessage(mContext, baseResponse.getStatusMsg()+"申请出错");
            }

            @Override
            public void onError() {
                ToastUtils.showMessage(mContext, "连接服务器失败");
            }
        });
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
