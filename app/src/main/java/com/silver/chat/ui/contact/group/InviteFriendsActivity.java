package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.silver.chat.R;
import com.silver.chat.adapter.AddGroupMenAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.database.callback.EasyRun;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.AddGroupMemBean;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/11.
 */
public class InviteFriendsActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.image_seach)
    ImageView imageSeach;
    @BindView(R.id.rl_seach)
    RelativeLayout rlSeach;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.bt_determine)
    Button btDetermine;

    private AddGroupMenAdapter mAdapter;
    private List<ContactListBean> friendInfo;
    private LinearLayoutManager linearLayoutManager;
    private BaseDao<ContactListBean> mDao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invitefriends;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //联系人列表的adapter
                    mAdapter = new AddGroupMenAdapter( mContext, friendInfo);
                    recycler.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Override
    protected void initView() {
        super.initView();
        friendInfo = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(mContext);
        //设置布局管理器
        recycler.setLayoutManager(linearLayoutManager);
        mDao = DBHelper.get().dao(ContactListBean.class);
    }

    @Override
    protected void initData() {
        super.initData();
        QueryDbParent();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @OnClick({R.id.title_left_back, R.id.image_seach, R.id.rl_seach, R.id.bt_determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.image_seach:
                break;
            case R.id.rl_seach:
                break;
            case R.id.bt_determine:
                getAddMember();
                finish();
                break;
        }
    }
    private void getAddMember() {
        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId",-1);
        String groupAvatar = intent.getStringExtra("groupAvatar");
        String groupName = intent.getStringExtra("groupName");
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        AddGroupMemBean instance = AddGroupMemBean.getInstance();
        instance.setSourceId(PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, ""));
        instance.setSourceName(PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.NICKNAME,""));
        instance.setSourceAvatar(PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.AVATAR,""));
        instance.setComment("");
        instance.setTargetIds(mAdapter.stringList);
        instance.setGroupId(groupId+"");
        instance.setGroupName(groupName);
        instance.setGroupAvatar(groupAvatar);
        instance.setAppName(getApplicationContext().getPackageName());
        SSIMGroupManger.addGroupMemeber(mContext, Common.version, token, instance, new ResponseCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                ToastUtils.showMessage(mContext,"邀请成功");
            }

            @Override
            public void onFailed(BaseResponse baseResponse) {
                ToastUtils.showMessage(mContext,"邀请失败");
            }

            @Override
            public void onError() {
                ToastUtils.showMessage(mContext,"邀请出错");
            }
        });
    }

    /**
     * 查询数据库所有联系人
     */
    private void QueryDbParent() {
        mDao.asyncTask(new EasyRun<List<ContactListBean>>() {
            @Override
            public List<ContactListBean> run() throws Exception {
                return getSortData();
            }

            @Override
            public void onMainThread(List<ContactListBean> data) throws Exception {
                friendInfo = data;
                mHandler.sendEmptyMessage(0);
            }
        });

    }

    public List<ContactListBean> getSortData() {
        Log.e(" mDao.queryForAll():", mDao.queryForAll() + "");
        return mDao.query(WhereInfo.get().equal("userId", PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "")));
    }

}
