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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.adapter.GMemAdapter;
import com.silver.chat.adapter.GMemDeleteAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;

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
    RecyclerView listview;
    @BindView(R.id.bt_determine)
    Button btDetermine;
    private MyAdapter myAdapter;
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
                    mAdapter = new GMemDeleteAdapter(mContext,groupMemlists);
                    listview.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_delete_groupmem;
    }

    @Override
    protected void initView() {
        super.initView();
//        getGroupMember();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listview.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        super.initData();
        /*mCreatGroups = new ArrayList<>();
        mManagerGroups = new ArrayList<>();
        mJoinGroups = new ArrayList<>();*/
/*        getGroupMemInfo();
        myAdapter = new MyAdapter();
        listview.setAdapter(myAdapter);*/
    }

    /**
     * 获取群成员
     */
    private void getGroupMember() {
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
//        SSIMGroupManger.getGroupMem(mContext, token, groupId + "", new ResponseCallBack<BaseResponse<ArrayList<GroupMemberBean>>>() {
//            @Override
//            public void onSuccess(BaseResponse<ArrayList<GroupMemberBean>> arrayListBaseResponse) {
//                groupMemlists = arrayListBaseResponse.data;
//                mHandler.sendEmptyMessage(0);
//                Log.e( "groupMemlists: ", groupMemlists.toString());
//            }
//
//            @Override
//            public void onFailed(BaseResponse<ArrayList<GroupMemberBean>> arrayListBaseResponse) {
//
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });
    }
    /**
     * 根据字段区分群组信息
     *
     * @param groupBeanBaseResponse
     */
    private void distinguishGroupMemInfo(BaseResponse<ArrayList<GroupBean>> groupBeanBaseResponse) {
        ArrayList<GroupBean> data = groupBeanBaseResponse.data;
        for (int i = 0; i < data.size(); i++) {
            int privilege = data.get(i).getPrivilege();
            if (privilege == 1) {
                //mCreatGroups.add("");
            } else if (privilege == 2) {
                //mJoinGroups.add("");
            } else if (privilege == 3) {
                //mManagerGroups.add("");
            }

        }
        myAdapter.notifyDataSetChanged();
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


    class MyAdapter extends BaseAdapter {
        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0 || position == (mCreatGroups.size() + 1) || position == (mCreatGroups.size() + mManagerGroups.size() + 2)) {
                //群称谓的条目
                return 0;
            } else {
                //群成员的条目
                return 1;
            }
        }

        @Override
        public int getCount() {
            return mCreatGroups.size() + mJoinGroups.size() + mManagerGroups.size() + 3;
        }

        @Override
        public GroupBean getItem(int position) {
            if (position == 0 || position == mCreatGroups.size() + 1 || position == mManagerGroups.size() + mCreatGroups.size() + 2) {
                return null;
            }
            if (position < mCreatGroups.size() + 1) {
                return mCreatGroups.get(position - 1);
            } else if (mCreatGroups.size() < position && position < (mCreatGroups.size() + mManagerGroups.size() + 2)) {
                return mManagerGroups.get(position - mCreatGroups.size() - 2);
            } else {
                return mJoinGroups.get(position - mCreatGroups.size() - mManagerGroups.size() - 3);
            }
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        TextView tv;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int itemViewType = getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    convertView = new TextView(mContext);
                    tv = (TextView) convertView;
                    tv.setTextColor(Color.parseColor("#8affffff"));
                    tv.setTextSize(12);
                    tv.setPadding(20, 20, 20, 20);
                    if (position == 0) {
                        tv.setText("群主( " + mCreatGroups.size() + " 个)");
                    } else if (position == mCreatGroups.size() + 1) {
                        tv.setText("管理员( " + mManagerGroups.size() + " 个)");

                    } else if (position == mCreatGroups.size() + mManagerGroups.size() + 2) {
                        tv.setText("群成员( " + mJoinGroups.size() + " 个)");
                    }
                    break;
                case 1:
                    GroupChatActivity.ViewHolder holder;
                    if (convertView == null || convertView instanceof TextView) {
                        convertView = View.inflate(getApplicationContext(), R.layout.item_mygroup, null);
                        holder = new GroupChatActivity.ViewHolder();
                        holder.ivIcon = (CircleImageView) convertView.findViewById(R.id.im_group_image);
                        holder.tvName = (TextView) convertView.findViewById(R.id.tv_group_name);

                        convertView.setTag(holder);
                    } else {
                        holder = (GroupChatActivity.ViewHolder) convertView.getTag();
                    }
                    final GroupBean item = getItem(position);

                    //点击跳转群详情的界面
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(DeleteGroupMemActivity.this, GroupDetailActivity.class);
                            intent.putExtra("privilege", item.getPrivilege());
                            startActivity(intent);
                        }
                    });
                    holder.tvName.setText(item.getGroupName());
                    break;
                default:
                    break;

            }
            return convertView;
        }
    }

    static class ViewHolder {
        CircleImageView ivIcon;
        TextView tvName;

    }
}
