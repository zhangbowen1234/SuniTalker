package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.adapter.FriendInfoAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.network.responsebean.FriendInfo;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.silver.chat.util.Utils.context;

/**
 * Created by bowen on 2017/5/8.
 */

public class CreatGroupActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.image_seach)
    ImageView imageSeach;
    @BindView(R.id.ed_new_group)
    EditText edNewGroup;
    @BindView(R.id.rv_new_group_contacts)
    RecyclerView rvNewGroupContacts;
    @BindView(R.id.bt_determine)
    Button btDetermine;
    private String Name;
    private int len;
    private FriendInfoAdapter mAdapter;
    private List<FriendInfo> friendInfo = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_group;
    }


    @Override
    protected void initData() {
        super.initData();
        mAdapter = new FriendInfoAdapter(mContext,friendInfo);
        getFriendInfo();
        //输入框
        getEditlisten();
    }

    @OnClick({R.id.title_left_back, R.id.image_seach, R.id.bt_determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.image_seach:

                break;
            case R.id.bt_determine:
                if (len != 0) {
                    Name = edNewGroup.getText().toString();
                    Intent intent = new Intent(mContext, GroupDiscussionActivity.class);
                    intent.putExtra("Name", Name);
                    startActivity(intent);
                } else {
                    ToastUtils.showMessage(mContext, "请填写群组名称");
                }
                break;
        }
    }

    private void getFriendInfo() {
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        if (token != null && !"".equals(token)) {
//            SSIMUserManger.contactList(Common.version,token, PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, ""),
//                    PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.FRIENDID,""), new ResponseCallBack<BaseResponse<List<FriendInfo>>>() {
//
//                        @Override
//                        public void onSuccess(BaseResponse<List<FriendInfo>> listBaseResponse) {
//                            ToastUtils.showMessage(mContext,listBaseResponse.getStatusMsg());
//                            Log.e("ContactList,onSuccess",listBaseResponse.data+"");
//                        }
//
//                        @Override
//                        public void onFailed(BaseResponse<List<FriendInfo>> listBaseResponse) {
//                            ToastUtils.showMessage(mContext,listBaseResponse.getStatusMsg());
//                            Log.e("ContactList_onFailed",listBaseResponse.data+"");
//                        }
//                        @Override
//                        public void onError() {
//                            ToastUtils.showMessage(mContext, "获取失败");
//                        }
//                    });
        }
    }

    public void getEditlisten() {
        edNewGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int mTextMaxlenght = 0;
                Editable editable = edNewGroup.getText();
                String str = editable.toString().trim();
                //得到最初字段的长度大小，用于光标位置的判断
                int selEndIndex = Selection.getSelectionEnd(editable);
                len = selEndIndex;
                // 取出每个字符进行判断，如果是字母数字和标点符号则为一个字符加1，
                //如果是汉字则为两个字符
                for (int i = 0; i < str.length(); i++) {
                    char charAt = str.charAt(i);
                    //32-122包含了空格，大小写字母，数字和一些常用的符号，
                    //如果在这个范围内则算一个字符，
                    //如果不在这个范围比如是汉字的话就是两个字符
                    if (charAt >= 32 && charAt <= 122) {
                        mTextMaxlenght++;
                    } else {
                        mTextMaxlenght += 2;
                    }
                    // 当最大字符大于20时，进行字段的截取，并进行提示字段的大小
                    if (mTextMaxlenght > 20) {
                        // 截取最大的字段
                        String newStr = str.substring(0, i);
                        edNewGroup.setText(newStr);
                        // 得到新字段的长度值
                        editable = edNewGroup.getText();
                        len = editable.length();
                        if (selEndIndex > len) {
                            selEndIndex = editable.length();
                        }
                        // 设置新光标所在的位置
                        Selection.setSelection(editable, selEndIndex);
                        ToastUtils.showMessage(context, "至多输入10个字");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
