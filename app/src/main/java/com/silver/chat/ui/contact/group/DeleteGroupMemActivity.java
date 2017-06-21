package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.adapter.DeleteMemAdapter;
import com.silver.chat.adapter.GMemDeleteAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Joe on 2017/5/10.
 * 删除群成员的Activity
 */
public class DeleteGroupMemActivity extends BaseActivity {

    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.image_seach)
    ImageView imageSeach;
    @BindView(R.id.rl_seach)
    RelativeLayout rlSeach;
    @BindView(R.id.listview)
    RecyclerView recyclerView;
    @BindView(R.id.bt_determine)
    Button btDetermine;
    private ArrayList<GroupBean> mCreatGroups;
    private ArrayList<GroupBean> mManagerGroups;
    private ArrayList<GroupBean> mJoinGroups;
    private ArrayList<GroupMemberBean> groupMemlists = new ArrayList<>();
    private GMemDeleteAdapter mAdapter;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //联系人列表的adapter
                    mAdapter = new GMemDeleteAdapter(mContext, groupMemlists);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private int groupId;
    private List<GroupMemberBean> groupMemberBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_delete_groupmem;
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        groupId = intent.getIntExtra("groupId", -1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        super.initData();
        getGroupMember();
        recyclerView.setAdapter(new DeleteMemAdapter(mContext));

    }

    /**
     * 获取群成员
     */
    private void getGroupMember() {
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        BaseDao<GroupMemberBean> dao = DBHelper.get().dao(GroupMemberBean.class);
        groupMemberBeanList = dao.queryForAll(WhereInfo.get().equal("groupId", dao.queryForAll(WhereInfo.get().equal("groupId", this.groupId))));

    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
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
                break;
        }
    }

}
