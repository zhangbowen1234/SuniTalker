package com.silver.chat.ui.contact;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.SSIMUserManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.CreatGroupBean;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private String groupName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_group;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @OnClick({R.id.title_left_back, R.id.image_seach,R.id.bt_determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.image_seach:

                break;
            case R.id.bt_determine:
                groupName = edNewGroup.getText().toString();

                getContactList();
                break;
        }
    }
    private String token;
    public void getContactList() {
        token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        if (token != null && !"".equals(token)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SSIMGroupManger.creatgroup(Common.version, token, new ResponseCallBack<BaseResponse<CreatGroupBean>>() {
                        @Override
                        public void onSuccess(BaseResponse<CreatGroupBean> creatGroupBeanBaseResponse) {
                            ToastUtils.showMessage(mContext, creatGroupBeanBaseResponse.getStatusMsg());
                            PreferenceUtil.getInstance(mContext).setString("groupName",groupName);
                        }

                        @Override
                        public void onFailed(BaseResponse<CreatGroupBean> creatGroupBeanBaseResponse) {
                            ToastUtils.showMessage(mContext, creatGroupBeanBaseResponse.getStatusMsg());
                        }

                        @Override
                        public void onError() {
                            ToastUtils.showMessage(mContext, "创建失败");
                        }
                    });
                }
            }).start();
        }
    }
}
