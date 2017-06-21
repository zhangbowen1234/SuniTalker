package com.silver.chat.ui.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.MainActivity;
import com.silver.chat.R;
import com.silver.chat.adapter.CreatGroupAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.CreatGroupBean;
import com.silver.chat.network.responsebean.AddGroupMemBean;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.ssim.android.constant.HttpConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bowen on 2017/5/8.
 * 创建群聊讨论组
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
    @BindView(R.id.rv_creat_group)
    RecyclerView rvCreatGroup;
    @BindView(R.id.selected_contact_person)
    TextView selectedContactPerson;

    private List<ContactListBean> memeberlist = new ArrayList<>();
    private CreatGroupAdapter mAdapter;
    private GridLayoutManager gridLayoutManager;
    private String Name;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_discussiongroup;
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        Name = intent.getStringExtra("Name");
        memeberlist = (List) intent.getSerializableExtra("memeberlist");
        Log.e(TAG, memeberlist.toString());

        tvGroupName.setText(Name);
        selectedContactPerson.setText("已选联络人(" + memeberlist.size() + ")");
        gridLayoutManager = new GridLayoutManager(this, 4);
        mAdapter = new CreatGroupAdapter(mContext, memeberlist);
        rvCreatGroup.setLayoutManager(gridLayoutManager);
        rvCreatGroup.setAdapter(mAdapter);
    }


    @OnClick({R.id.title_left_back, R.id.bt_creat_group, R.id.bt_creat_discussion, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.bt_creat_group:
                getContactList(view);
//                startActivity(MainActivity.class);
                finish();
                break;
            case R.id.bt_creat_discussion:
                getContactList(view);
//                Intent intent = new Intent(mContext,MainActivity.class);
//                intent.putExtra("id",1);
//                startActivity(intent);
                finish();
                break;
            case R.id.bt_cancel:
                finish();
                break;
        }
    }


    public void getContactList(View view) {
        CreatGroupBean instance = CreatGroupBean.getInstance();
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        instance.setUserId(PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, ""));
        instance.setGroupName(tvGroupName.getText().toString());
        instance.setAddMethod(1);
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < memeberlist.size(); i++) {
            ls.add(memeberlist.get(i).getFriendId()+"");
        }
        instance.setFriendIds(ls);
        if (token != null && !"".equals(token)) {
            if (view.getId() == R.id.bt_creat_group) {
                SSIMGroupManger.getcreatgroup(mContext, Common.version, token, instance, new ResponseCallBack<BaseResponse<CreatGroupBean>>() {
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
            } else if (view.getId() == R.id.bt_creat_discussion) {
                SSIMGroupManger.getcreatdicugroup(mContext, Common.version, token, instance, new ResponseCallBack<BaseResponse<CreatGroupBean>>() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

}
