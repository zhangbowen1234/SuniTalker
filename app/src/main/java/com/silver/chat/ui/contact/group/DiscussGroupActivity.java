package com.silver.chat.ui.contact.group;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.DisscusBean;
import com.silver.chat.ui.contact.SearchContactActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/8.
 */
public class DiscussGroupActivity extends BaseActivity{
    ImageView titleLeftBack;
    ImageView imageSeach;
    RecyclerView recyler;
    RelativeLayout rlSeach;
    ImageView ivDiscussGroup;
    private TextView tvTaoLunZu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discussgroup;
    }

    @Override
    protected void initView() {
        titleLeftBack = (ImageView) findViewById(R.id.title_left_back);
        imageSeach = (ImageView) findViewById(R.id.image_seach);
        recyler = (RecyclerView) findViewById(R.id.recyler);
        rlSeach = (RelativeLayout) findViewById(R.id.rl_seach);
        ivDiscussGroup = (ImageView)findViewById(R.id.iv_discussgroup);
        tvTaoLunZu = (TextView) findViewById(R.id.tv_taolunzu);

    }

    @Override
    protected void initData() {
        super.initData();
//        getNetDissGroupInfo();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }
    @OnClick({R.id.title_left_back,R.id.rl_seach, R.id.iv_discussgroup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.rl_seach:
                startActivity(SearchContactActivity.class);
                break;
            case R.id.iv_discussgroup:
                startActivity(GroupChatActivity.class);
                break;

        }
    }

    public void getNetDissGroupInfo(){
        /**
         * 接口不对
         */
        String userId = PreferenceUtil.getInstance(this).getString(PreferenceUtil.USERID, "");
        String token = PreferenceUtil.getInstance(this).getString(PreferenceUtil.TOKEN, "");

        SSIMGroupManger.getJoinDisGroupList(mContext, token, userId, new ResponseCallBack<BaseResponse<DisscusBean>>() {
            @Override
            public void onSuccess(BaseResponse arrayListBaseResponse) {
                ToastUtils.showMessage(mContext,"获取讨论组成功");
            }

            @Override
            public void onFailed(BaseResponse arrayListBaseResponse) {

            }

            @Override
            public void onError() {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
