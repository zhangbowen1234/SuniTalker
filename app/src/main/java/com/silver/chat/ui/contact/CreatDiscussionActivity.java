package com.silver.chat.ui.contact;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.requestbean.CreatGroupBean;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bowen on 2017/5/8.
 */

public class CreatDiscussionActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.tv_group_name)
    TextView tvGroupName;
    @BindView(R.id.bt_creat_group)
    TextView btCreatGroup;
    @BindView(R.id.bt_creat_discussion)
    TextView btCreatDiscussion;
    @BindView(R.id.bt_cancel)
    TextView btCancel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_discussiongroup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        tvGroupName.setText(Name);
    }

    @OnClick({R.id.title_left_back, R.id.bt_creat_group, R.id.bt_creat_discussion, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.bt_creat_group:
                getContactList(view);
                finish();
                break;
            case R.id.bt_creat_discussion:
                getContactList(view);
                finish();
                break;
            case R.id.bt_cancel:
                finish();
                break;
        }
    }

    public void getContactList(View view) {
        CreatGroupBean instance = CreatGroupBean.getInstance();
        instance.setUserId(PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, ""));
        instance.setGroupName(tvGroupName.getText().toString());
        instance.setAddMethod(1);
//        instance.setDescribe("新建群组");
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        if (token != null && !"".equals(token)) {
            if (view.getId() == R.id.bt_creat_group){
                SSIMGroupManger.getcreatgroup(Common.version, token, instance, new ResponseCallBack<BaseResponse<CreatGroupBean>>() {
                    @Override
                    public void onSuccess(BaseResponse<CreatGroupBean> creatGroupBeanBaseResponse) {
                        ToastUtils.showMessage(mContext, creatGroupBeanBaseResponse.getStatusMsg());
                        Log.e("onSuccess", creatGroupBeanBaseResponse.toString() + "");
                    }

                    @Override
                    public void onFailed(BaseResponse<CreatGroupBean> creatGroupBeanBaseResponse) {
                        Log.e("ContactList_onFailed", creatGroupBeanBaseResponse.toString() + "");
                        ToastUtils.showMessage(mContext, creatGroupBeanBaseResponse.getStatusMsg());
                    }

                    @Override
                    public void onError() {
                        ToastUtils.showMessage(mContext, "创建失败");
                    }
                });
            }else if (view.getId() == R.id.bt_creat_discussion){
                SSIMGroupManger.getcreatdicugroup(Common.version, token, instance, new ResponseCallBack<BaseResponse<CreatGroupBean>>() {
                    @Override
                    public void onSuccess(BaseResponse<CreatGroupBean> creatGroupBeanBaseResponse) {
                        ToastUtils.showMessage(mContext, creatGroupBeanBaseResponse.getStatusMsg());
                        Log.e("onSuccess", creatGroupBeanBaseResponse.toString() + "");
                    }

                    @Override
                    public void onFailed(BaseResponse<CreatGroupBean> creatGroupBeanBaseResponse) {
                        Log.e("ContactList_onFailed", creatGroupBeanBaseResponse.toString() + "");
                        ToastUtils.showMessage(mContext, creatGroupBeanBaseResponse.getStatusMsg());
                    }

                    @Override
                    public void onError() {
                        ToastUtils.showMessage(mContext, "创建失败");
                    }
                });
            }
        }
    }
}
