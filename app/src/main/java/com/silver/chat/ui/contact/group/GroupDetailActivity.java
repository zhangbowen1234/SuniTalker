package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.os.Bundle;
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
import com.silver.chat.adapter.GMemAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.network.responsebean.GroupMemberBean;
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
    LinearLayout rlGroupMem;
    @BindView(R.id.rv_group_memeber)
    RecyclerView rvGroupMemeber;

    private List<String> lists;
    private GroupLeftFragment groupLeftFragment;
    private GroupRightFragment groupRightFragment;
    private ArrayList<Fragment> fragments;
    //修改群名片开启activity的请求码
    private static final int REQUEST_CODE3 = 3;
    private String groupName, groupAvatar;
    private int groupId;
    //群成员列表
    private ArrayList<GroupMemberBean> groupMemlists = new ArrayList<>();
    private GMemAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupdetail;
    }

    @Override
    protected void initData() {
        tvGroupname.setText(groupName);
        lists = new ArrayList() {};
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
        getGroupMember();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter = new GMemAdapter(GroupDetailActivity.this,groupMemlists);
        rvGroupMemeber.setLayoutManager(linearLayoutManager);
        rvGroupMemeber.setAdapter(mAdapter);
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
    private void getGroupMember() {
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");

        SSIMGroupManger.getGroupMem(mContext, token, groupId + "", new ResponseCallBack<BaseResponse<ArrayList<GroupMemberBean>>>() {
            @Override
            public void onSuccess(BaseResponse<ArrayList<GroupMemberBean>> arrayListBaseResponse) {
                groupMemlists = arrayListBaseResponse.data;
                int groupMemCount = arrayListBaseResponse.data.size();
                tvGroupMemCount.setText("群成员(" + groupMemCount + ")");
            }

            @Override
            public void onFailed(BaseResponse<ArrayList<GroupMemberBean>> arrayListBaseResponse) {

            }

            @Override
            public void onError() {

            }
        });
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
                intent1.putExtra("groupId", groupId);
                intent1.putExtra("groupName", groupName);
                intent1.putExtra("groupAvatar", groupAvatar);
                startActivity(intent1);
                break;
            case R.id.iv_conversation:
                Intent intent4 = new Intent(this, ContactChatActivity.class);
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
