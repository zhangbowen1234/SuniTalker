package com.silver.chat.ui.chat.notification;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.GroupNotifiAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.OnItemClickListener;
import com.ssim.android.constant.SSPublishType;
import com.ssim.android.model.notification.SSGroupNotification;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bowen on 2017/5/11.
 */

public class GroupNotificationActivity extends BaseActivity {

    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.rv_group_notification)
    RecyclerView rvGroupNotification;
    private LinearLayoutManager linearLayoutManager;
    private GroupNotifiAdapter mAdapter;
    private List<SSGroupNotification> mList;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_notification;
    }

    @Override
    protected void initView() {
        super.initView();
        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器
        rvGroupNotification.setLayoutManager(linearLayoutManager);
        mList = AppContext.getInstance().instance.getGroupNotificationList();
        mAdapter = new GroupNotifiAdapter(R.layout.item_group_notification,mList);
//        rvGroupNotification.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initListener() {
        super.initListener();
        rvGroupNotification.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(AddGroupNotifiActivity.class);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.title_left_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.title_left_back:
                finish();
                break;
        }
    }
}
