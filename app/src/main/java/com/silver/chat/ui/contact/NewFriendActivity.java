package com.silver.chat.ui.contact;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.NewFriendAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.database.callback.EasyRun;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.network.SSIMFrendManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.AgreeFriendAddBody;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.SimpleClickListener;
import com.ssim.android.listener.SSNotificationListener;
import com.ssim.android.model.notification.SSFriendNotification;
import com.ssim.android.model.notification.SSGroupNotification;
import com.ssim.android.model.notification.SSNormalNotification;
import com.ssim.android.model.notification.SSNotification;
import com.ssim.android.model.notification.friend.SSAddFriendNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hibon on 2017/4/16 14:14
 * 新朋友
 */

public class NewFriendActivity extends BaseActivity implements View.OnClickListener, SSNotificationListener {

    private ImageView mBack;
    private RecyclerView mNFRecyclerList;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayout mAddFriend;
    private TextView agreeAdd;
    private String token,userId ,mNickName ,mAvatar;
    private AgreeFriendAddBody agreeFriendAddBody;
    private List<SSFriendNotification> friendNotificationList;
    private NewFriendAdapter addFriendAdatpter;
    private List<GroupMemberBean> mContactList;
    private BaseDao<GroupMemberBean> mDao;
    private String sourceId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_friend;
    }

    @Override
    protected void initView() {
        super.initView();
        mBack = (ImageView) findViewById(R.id.title_left_back);
        mNFRecyclerList = (RecyclerView) findViewById(R.id.new_friend_list);
        mAddFriend = (LinearLayout) findViewById(R.id.ll_add_friend);
        agreeAdd = (TextView) findViewById(R.id.agree_add_friend);
        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        /*设置布局管理器*/
        mNFRecyclerList.setLayoutManager(linearLayoutManager);
        agreeFriendAddBody = new AgreeFriendAddBody();
        mContactList = new ArrayList<GroupMemberBean>();
        friendNotificationList = new ArrayList<SSFriendNotification>();
        mDao = DBHelper.get().dao(GroupMemberBean.class);

    }

    @Override
    protected void initData() {
        super.initData();
        token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");
        mNickName = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.NICKNAME, "");
        mAvatar = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.AVATAR, "");


        /*SDK中取得好友添加申请通知列表*/
        friendNotificationList = AppContext.getInstance().instance.getFriendNotificationList();
        for (int i = 0; i < friendNotificationList.size(); i++) {
            sourceId = friendNotificationList.get(i).getSourceId();
            QueryDbParent();
//            if (mContactList != null){
//
//            }
        }
        addFriendAdatpter = new NewFriendAdapter(R.layout.item_new_friend, friendNotificationList);
        mNFRecyclerList.setAdapter(addFriendAdatpter);

    }

    /**
     * 查询群表中联系人
     */
    private void QueryDbParent() {
        mDao.asyncTask(new EasyRun<List<GroupMemberBean>>() {
            @Override
            public List<GroupMemberBean> run() throws Exception {
                return getSortData();
            }

            @Override
            public void onMainThread(List<GroupMemberBean> data) throws Exception {
                if (data.isEmpty()) {
                    //其次从网络获取数据
//                    httpContactList();
                } else {
                    mContactList = data;
                    String nickName = mContactList.get(0).getNickName();

                }
            }
        });

    }


    @Override
    protected void initListener() {
        /* 通知的监听*/
        AppContext.getInstance().instance.setNotificationListener(this);
        mBack.setOnClickListener(this);
        mAddFriend.setOnClickListener(this);
        mNFRecyclerList.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle mBundle = new Bundle();
                mBundle.putString("sourceId",sourceId);
                startActivity(FriendApplyforActivity.class);
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("aaaaa","66666666");
                switch (view.getId()){

                }
                agreeFriendAddBody.setAppName("innerapp");
                agreeFriendAddBody.setSourceId(userId);
                agreeFriendAddBody.setSourceName(mNickName);
                agreeFriendAddBody.setSourceAvatar(mAvatar);
                agreeFriendAddBody.setTargetId(sourceId);
                SSIMFrendManger.agreeFriend(mContext, token, agreeFriendAddBody, new ResponseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        Log.e("onSuccess", baseResponse.toString());
                        ToastUtils.showMessage(mContext,baseResponse.getStatusMsg());
                    }

                    @Override
                    public void onFailed(BaseResponse baseResponse) {
                        Log.e("onSuccess", baseResponse.toString());
                    }

                    @Override
                    public void onError() {
                        Log.e("onError", "onError");
                    }
                });

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.ll_add_friend:
                startActivity(AddFriendActivity.class);
                break;

        }
    }

    /**
     * 监听好友申请
     *
     * @param ssNotification
     */
    @Override
    public void receiveNotification(SSNotification ssNotification) {
        if (ssNotification instanceof SSAddFriendNotification) {
            /*好友申请添加好友的通知监听*/
            SSAddFriendNotification ssFriendNotification = (SSAddFriendNotification) ssNotification;
            String sourceId = ssFriendNotification.getSourceId();


        } else if (ssNotification instanceof SSGroupNotification) {
            SSGroupNotification ssGroupNotification = (SSGroupNotification) ssNotification;
        } else if (ssNotification instanceof SSNormalNotification) {
            SSNormalNotification ssNormalNotification = (SSNormalNotification) ssNotification;
        }
    }

    public List<GroupMemberBean> getSortData() {
        return mDao.query(WhereInfo.get().equal("userId", sourceId));
    }
}
