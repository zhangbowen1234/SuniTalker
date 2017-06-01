package com.silver.chat.ui.contact.group;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.database.callback.EasyRun;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.ui.contact.SearchContactActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.CircleImageView;
import com.ssim.android.engine.SSEngine;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by joe on 2017/4/25.
 * 我的群的activity
 */

public class GroupChatActivity extends BaseActivity {

    private ImageView titleLeftBack;
    private ImageView imageSeach;
    private ExpandableListView listView;
    private RelativeLayout rlSeach;
    private ArrayList<GroupBean> mCreatGroups;
    private ArrayList<GroupBean> mManagerGroups;
    private ArrayList<GroupBean> mJoinGroups;
    private ImageView ivMyGroup;
    private MyAdapter myAdapter;
    private TextView tvChatCount;
    private int selectPosition;
    private BaseDao<GroupBean> mDao;
    private List<GroupBean> data;
    private ArrayList<GroupBean> datas;
    private ArrayList<GroupBean> lists;
    private GroupBean groupBean;

    @Override
    protected void initView() {
        titleLeftBack = (ImageView) findViewById(R.id.title_left_back);
        imageSeach = (ImageView) findViewById(R.id.image_seach);
        listView = (ExpandableListView) findViewById(R.id.listview);
        rlSeach = (RelativeLayout) findViewById(R.id.rl_seach);
        ivMyGroup = (ImageView) findViewById(R.id.iv_mygroup);
        tvChatCount = (TextView) findViewById(R.id.tv_chatcount);
    }

    @Override
    protected void initData() {
        super.initData();
        datas = new ArrayList<>();
        mCreatGroups = new ArrayList<>();
        mManagerGroups = new ArrayList<>();
        mJoinGroups = new ArrayList<>();
        myAdapter = new MyAdapter();
        mDao = DBHelper.get().dao(GroupBean.class);

        //获取群组数据信息数据库为空去网络获取
        getLocalGroupInfo();
        if (!(data.size() > 0) && data == null) {
            getNetGroupInfo();
        }
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.expandGroup(0);
        //设置parentGroup的展开与否的指示箭头
//        listView.setGroupIndicator(this.getResources().getDrawable(R.drawable.indicator));
    }


    /**
     * 查询本地数据库群组数据
     */
    private void getLocalGroupInfo() {
        WhereInfo userId = WhereInfo.get().equal("userId", PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, ""));
        data = mDao.query(userId);
        quFenData();

    }

    @Override
    protected void initListener() {
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // 判断是否展开
                if (listView.isGroupExpanded(groupPosition)) {
                    // 已经展开 去关闭
                    listView.collapseGroup(groupPosition);
                } else {
                    // 已经关闭 去展开
                    // 展开前 关闭上一次展开的条目
                    listView.collapseGroup(selectPosition);
                    // 展开
                    listView.expandGroup(groupPosition);
                    // 展开之后 记录一下 当前展开的索引值 方便下一次关闭
                    selectPosition = groupPosition;
                    // 置顶 当前这条数据
                    listView.setSelection(groupPosition);
                }

                return true;// 自己处理 就返回true 这时系统的展开事件就没有了
            }
        });
    }

    /**
     * 请求网络获取群组信息
     */
    private void getNetGroupInfo() {
        String userId = PreferenceUtil.getInstance(this).getString(PreferenceUtil.USERID, "");
        String token = PreferenceUtil.getInstance(this).getString(PreferenceUtil.TOKEN, "");
        int i = Integer.parseInt(userId);
        JoinedGroupRequest request = JoinedGroupRequest.getInstance();
        request.setUserId(i);
        SSIMGroupManger.getJoinGroupList(mContext, Common.version, request, token, new ResponseCallBack<BaseResponse<ArrayList<GroupBean>>>() {


            @Override
            public void onSuccess(BaseResponse<ArrayList<GroupBean>> arrayListBaseResponse) {
                data = arrayListBaseResponse.data;
                //将数据放入数据库中
                putLocal(data);
                quFenData();
            }

            @Override
            public void onFailed(BaseResponse<ArrayList<GroupBean>> arrayListBaseResponse) {
            }

            @Override
            public void onError() {


            }
        });


    }

    private void putLocal(final List<GroupBean> datass) {
        lists = new ArrayList<>();
        for (int i = 0; i < datass.size(); i++) {
            groupBean = new GroupBean();
            groupBean.setGroupName(datass.get(i).getGroupName());
            groupBean.setAvatar(datass.get(i).getAvatar());
            groupBean.setCreateTime(datass.get(i).getCreateTime());
            groupBean.setGroupId(datass.get(i).getGroupId());
            groupBean.setGroupRemark(datass.get(i).getGroupRemark());
            groupBean.setPrivilege(datass.get(i).getPrivilege());
            groupBean.setUserId(datass.get(i).getUserId());
            lists.add(groupBean);
        }

        mDao.asyncTask(new EasyRun<List<GroupBean>>() {
            @Override
            public List<GroupBean> run() throws Exception {
                mDao.create(lists);
                return mDao.queryForAll();
            }

            @Override
            public void onMainThread(List<GroupBean> data) throws Exception {
                super.onMainThread(data);

            }
        });
    }


    /**
     * 根据字段区分群组信息
     */
    private void quFenData() {
        for (int i = 0; i < data.size(); i++) {
            int privilege = data.get(i).getPrivilege();
            if (privilege == 1) {
                mCreatGroups.add(data.get(i));
            } else if (privilege == 2) {
                mJoinGroups.add(data.get(i));
            } else if (privilege == 3) {
                mManagerGroups.add(data.get(i));
            }

        }
        tvChatCount.setText("(" + (mCreatGroups.size() + mManagerGroups.size() + mJoinGroups.size()) + ")个群聊");
        myAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.title_left_back, R.id.rl_seach, R.id.iv_mygroup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.rl_seach:
                startActivity(SearchContactActivity.class);
                break;
            case R.id.iv_mygroup:
                startActivity(DiscussGroupActivity.class);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupchat;
    }


    class MyAdapter extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return 3;
        }

        @Override
        public int getChildrenCount(int groupPosition) {

            if (groupPosition == 0) {
                return mCreatGroups.size();
            } else if (groupPosition == 1) {
                mManagerGroups.size();
            } else if (groupPosition == 2) {
                return mJoinGroups.size();
            }
            return 0;
        }

        TextView tv;

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            convertView = new TextView(mContext);
            tv = (TextView) convertView;
            tv.setTextColor(Color.parseColor("#8affffff"));
            tv.setTextSize(12);
            tv.setPadding(20, 20, 20, 20);
            if (groupPosition == 0) {
                tv.setText("我创建的群( " + mCreatGroups.size() + " 个)");
            } else if (groupPosition == 1) {
                tv.setText("我管理的群( " + mManagerGroups.size() + " 个)");

            } else if (groupPosition == 2) {
                tv.setText("我加入的群( " + mJoinGroups.size() + " 个)");
            }
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.item_mygroup, null);
                holder = new ViewHolder();
                holder.ivIcon = (CircleImageView) convertView.findViewById(R.id.im_group_image);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_group_name);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final GroupBean item = (GroupBean) getChild(groupPosition, childPosition);
            //点击跳转群详情的界面
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GroupChatActivity.this, GroupDetailActivity.class);
                    intent.putExtra("groupbean", item);
                    startActivity(intent);
                }
            });
            holder.tvName.setText(item.getGroupName());


            return convertView;

        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            if (groupPosition == 0) {
                return mCreatGroups.get(childPosition);
            } else if (groupPosition == 1) {
                mManagerGroups.get(childPosition);
            } else if (groupPosition == 2) {
                return mJoinGroups.get(childPosition);
            }
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        /**
         * 是否有一个稳定的id   一般用不到
         *
         * @return
         */
        @Override
        public boolean hasStableIds() {
            return false;
        }

        /**
         * 孩子是否可以点击 返回true代表可以点击
         *
         * @param groupPosition
         * @param childPosition
         * @return
         */
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    static class ViewHolder {
        CircleImageView ivIcon;
        TextView tvName;

    }

}
