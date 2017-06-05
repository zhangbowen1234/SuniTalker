package com.silver.chat.ui.contact.group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.adapter.FriendInfoAdapter;
import com.silver.chat.adapter.GMemAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.database.callback.EasyRun;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.network.responsebean.SearchGroupBean;
import com.silver.chat.ui.contact.ContactChatActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.RoundImageView;
import com.silver.chat.view.WhewView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.string.no;

/**
 * Created by Joe on 2017/5/9.
 * 我的群组的详情
 */
public class GroupDetailActivity extends BaseActivity {
    @BindView(R.id.wv)
    WhewView wv;
    @BindView(R.id.my_photo)
    RoundImageView myPhoto;
    @BindView(R.id.tv_groupname)
    TextView tvGroupname;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.rl_group_nickname)
    RelativeLayout rlGroupNickname;
    @BindView(R.id.tv_group_mem_count)
    TextView tvGroupMemCount;
    @BindView(R.id.iv_header5)
    ImageView ivHeader5;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.iv_arrow_right)
    ImageView ivArrowRight;
    @BindView(R.id.iv_arrow_left)
    ImageView ivArrowLeft;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.iv_arrow_bottom)
    ImageView ivArrowBottom;
    public int privilege;
    @BindView(R.id.iv_conversation)
    ImageView ivConversation;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.rl_group_member)
    RelativeLayout rlGroupMem;
    @BindView(R.id.rv_group_memeber)
    RecyclerView rvGroupMemeber;

    private List<String> lists;
    private GroupLeftFragment groupLeftFragment;
    private GroupRightFragment groupRightFragment;
    private ArrayList<Fragment> fragments;
    //修改群名片开启activity的请求码
    private static final int REQUEST_CODE3 = 3;
    private String groupName;
    private String groupAvatar;
    private int groupId, groUserId;
    //群成员列表
    private List<GroupMemberBean> groupMemlists = new ArrayList<>();
    private GMemAdapter mAdapter;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //联系人列表的adapter
                    mAdapter = new GMemAdapter(GroupDetailActivity.this, groupMemlists);
                    rvGroupMemeber.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private BaseDao<GroupMemberBean> dao;
    private ArrayList<GroupMemberBean> lists1;
    private GroupMemberBean groupMemberBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupdetail;
    }

    @Override
    protected void initData() {
        tvGroupname.setText(groupName);
        lists = new ArrayList() {
        };
        String[] stringArray1 = getResources().getStringArray(R.array.group_qunzhu);
        String[] stringArray2 = getResources().getStringArray(R.array.group_commonmember);
        String[] stringArray3 = getResources().getStringArray(R.array.group_guanliyuan);

        if (privilege == 1) {
            lists = Arrays.asList(stringArray1);
        } else if (privilege == 2) {
            lists = Arrays.asList(stringArray2);
        } else {
            lists = Arrays.asList(stringArray3);
        }

        groupLeftFragment = new GroupLeftFragment(lists);
        groupRightFragment = new GroupRightFragment(lists);
        fragments = new ArrayList<>();
        fragments.add(groupLeftFragment);
        fragments.add(groupRightFragment);
        viewpager.setAdapter(new GroupAdapter(getSupportFragmentManager()));

    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        GroupBean groupbean = (GroupBean) intent.getSerializableExtra("groupbean");
        groupName = groupbean.getGroupName();
        privilege = groupbean.getPrivilege();
        groupId = groupbean.getGroupId();
        groupAvatar = groupbean.getAvatar();
        dao = DBHelper.get().dao(GroupMemberBean.class);
        getGroupMemberLocal();
        if(!(groupMemlists.size()>0)) {
            getGroupMemberNet();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvGroupMemeber.setLayoutManager(linearLayoutManager);
    }

    private void getGroupMemberLocal() {
        WhereInfo groupId = WhereInfo.get().equal("groupId", this.groupId);
        groupMemlists = dao.query(groupId);
        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected void initListener() {
        super.initListener();
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = viewpager.getCurrentItem();
                if (currentItem == 0) {
                    ivArrowRight.setVisibility(View.VISIBLE);
                    ivArrowLeft.setVisibility(View.GONE);

                } else if (currentItem == 1) {
                    ivArrowRight.setVisibility(View.GONE);
                    ivArrowLeft.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 获取群成员
     */
    private void getGroupMemberNet() {
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");

        SSIMGroupManger.getGroupMem(mContext, token, groupId + "", new ResponseCallBack<BaseResponse<ArrayList<GroupMemberBean>>>() {
            @Override
            public void onSuccess(BaseResponse<ArrayList<GroupMemberBean>> arrayListBaseResponse) {
                groupMemlists = arrayListBaseResponse.data;
                putLocal(groupMemlists);
                int groupMemCount = arrayListBaseResponse.data.size();
                tvGroupMemCount.setText("群成员(" + groupMemCount + ")");
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onFailed(BaseResponse<ArrayList<GroupMemberBean>> arrayListBaseResponse) {

            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 将群成员信息存放发哦本地数据库
     *
     * @param memLists
     */
    private void putLocal(List<GroupMemberBean> memLists) {
        lists1 = new ArrayList<>();
        for (int i = 0; i < memLists.size(); i++) {
            groupMemberBean = new GroupMemberBean();
            groupMemberBean.setPrivilege(memLists.get(i).getPrivilege());
            groupMemberBean.setAvatar(memLists.get(i).getAvatar());
            groupMemberBean.setGroupNickname(memLists.get(i).getGroupNickname());
            groupMemberBean.setNickName(memLists.get(i).getNickName());
            groupMemberBean.setImUserId(memLists.get(i).getImUserId());
            groupMemberBean.setUserId(memLists.get(i).getUserId());
            groupMemberBean.setGroupId(groupId);
            lists1.add(groupMemberBean);
        }
        dao.asyncTask(new EasyRun<List<GroupMemberBean>>() {

                          @Override
                          public List<GroupMemberBean> run() throws Exception {
                              dao.create(lists1);
                              return dao.queryForAll();
                          }

                          @Override
                          public void onMainThread(List<GroupMemberBean> data) throws Exception {
                              super.onMainThread(data);
                          }
                      }
        );
    }

    @OnClick({R.id.rl_group_member, R.id.wv, R.id.my_photo, R.id.tv_groupname, R.id.tv_nickname, R.id.iv_conversation, R.id.iv_qrcode, R.id.rl_group_nickname, R.id.tv_group_mem_count, R.id.iv_header5, R.id.iv_arrow, R.id.iv_arrow_right, R.id.iv_arrow_left, R.id.viewpager, R.id.iv_arrow_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wv:

            case R.id.my_photo:
                //startActivity();
                break;
            case R.id.tv_nickname:
            case R.id.rl_group_nickname:
                Intent intent3 = new Intent(this, ModifyNickNameActivity.class);
                startActivityForResult(intent3, REQUEST_CODE3);
                break;

            case R.id.iv_header5:
                Intent intent1 = new Intent(this, InviteFriendsActivity.class);
                List userIds = new ArrayList();
                for (int i = 0; i < mAdapter.data.size(); i++) {
                    int userId = mAdapter.data.get(i).getUserId();
                    userIds.add(userId);
                }
                intent1.putExtra("groupId", groupId);
                intent1.putExtra("groupName", groupName);
                intent1.putExtra("groupAvatar", groupAvatar);
                intent1.putExtra("friendId", (Serializable) userIds);
                startActivity(intent1);
                break;
            case R.id.iv_conversation:
                Intent intent4 = new Intent(this, GroupChatActivity.class);
                intent4.putExtra("groupName",groupName);
                intent4.putExtra("groupId",groupId+"");
                startActivity(intent4);
                break;
            case R.id.iv_qrcode:
                Intent intent2 = new Intent(this, GroupQRCodeActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_group_member:
            case R.id.iv_arrow:
                Intent intent = new Intent(this, GroupMemberActivity.class);
                intent.putExtra("privilege", privilege);
                intent.putExtra("groupid", groupId);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lists", (Serializable) groupMemlists);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.iv_arrow_right:
                viewpager.setCurrentItem(1);
                break;
            case R.id.iv_arrow_left:
                viewpager.setCurrentItem(0);
                break;
            case R.id.iv_arrow_bottom:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    class GroupAdapter extends FragmentPagerAdapter {

        public GroupAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
