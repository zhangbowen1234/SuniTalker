package com.silver.chat.ui.contact;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.entity.GroupBean;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.SSIMUserManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.JoinedGroupRequest;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by joe on 2017/4/25.
 */

public class GroupChatActivity extends BaseActivity {

    private ImageView titleLeftBack;
    private ImageView imageSeach;
    private ListView listView;
    private RelativeLayout rlSeach;
    private ArrayList<GroupBean> mCreatGroups;
    private ArrayList<GroupBean> mManagerGroups;
    private ArrayList<GroupBean> mJoinGroups;
    private GoogleApiClient client;
    private ImageView ivMyGroup;

    @Override
    protected void initView() {
        titleLeftBack = (ImageView) findViewById(R.id.title_left_back);
        imageSeach = (ImageView) findViewById(R.id.image_seach);
        listView = (ListView) findViewById(R.id.listview);
        rlSeach = (RelativeLayout) findViewById(R.id.rl_seach);
        ivMyGroup = (ImageView)findViewById(R.id.iv_mygroup);



    }

    @Override
    protected void initData() {
        super.initData();
        mCreatGroups = new ArrayList<>();
        mManagerGroups = new ArrayList<>();
        mJoinGroups = new ArrayList<>();
        getGroupInfo();
        listView.setAdapter(new MyAdapter());
    }

    /**
     * 请求网络获取群组信息
     */
    private void getGroupInfo() {
        String userId = PreferenceUtil.getInstance(this).getString(PreferenceUtil.USERID, "");
        int i = Integer.parseInt(userId);
        JoinedGroupRequest request = JoinedGroupRequest.getInstance();
        request.setUserId(i);
        SSIMGroupManger.getJoinGroupList(Common.version, request, new ResponseCallBack<BaseResponse<GroupBean>>() {
            @Override
            public void onSuccess(BaseResponse<GroupBean> groupBeanBaseResponse) {
                Log.e(TAG, groupBeanBaseResponse.toString());
                distinguishGroupInfo(groupBeanBaseResponse);
            }

            @Override
            public void onFailed(BaseResponse<GroupBean> groupBeanBaseResponse) {

            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 根据字段区分群组信息
     * @param groupBeanBaseResponse
     */
    private void distinguishGroupInfo(BaseResponse<GroupBean> groupBeanBaseResponse) {
        GroupBean data = groupBeanBaseResponse.data;
        int privilege = data.getPrivilege();
        if(privilege == 1) {
            mCreatGroups.add(data);
        }else if(privilege == 2) {
            mJoinGroups.add(data);
        }else if(privilege == 3) {
            mManagerGroups.add(data);
        }
    }


    @OnClick({R.id.title_left_back,R.id.rl_seach, R.id.iv_mygroup})
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
    protected int getLayoutId() {
        return R.layout.activity_groupchat;
    }







    class MyAdapter extends BaseAdapter {
        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0 || position == (mCreatGroups.size() + 1) || position == (mCreatGroups.size() + mManagerGroups.size() + 2)) {
                //群类型的条目
                return 0;
            } else {
                //群信息的条目
                return 1;
            }
        }

        @Override
        public int getCount() {
            return mCreatGroups.size() + mJoinGroups.size()+mManagerGroups.size()+3;
        }

        @Override
        public GroupBean getItem(int position) {
            if (position == 0 || position == mCreatGroups.size() + 1 ||position == mManagerGroups.size()+mCreatGroups.size()+2) {
                return null;
            }
            if (position < mCreatGroups.size() + 1) {
                return mCreatGroups.get(position - 1);
            } else if(mCreatGroups.size()< position && position < (mCreatGroups.size()+ mManagerGroups.size()+2)){
                return mManagerGroups.get(position-mCreatGroups.size() - 2);
            }else{
                return mJoinGroups.get(position-mCreatGroups.size()  - mManagerGroups.size() - 3);
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
                    tv.setPadding(20,20,20,20);
                    if(position == 0) {
                        tv.setText("我创建的群( " + mCreatGroups.size() + " 个)");
                    }else if(position == mCreatGroups.size() + 1) {
                        tv.setText("我管理的群( " + mManagerGroups.size() + " 个)");

                    }else if(position == mCreatGroups.size() + mManagerGroups.size() + 2) {
                        tv.setText("我加入的群( " + mJoinGroups.size() + " 个)");

                    }
                    break;
                case 1:
                   ViewHolder holder;
                    if (convertView == null || convertView instanceof TextView) {
                        convertView = View.inflate(getApplicationContext(), R.layout.item_mygroup, null);
                        holder = new ViewHolder();
                        holder.ivIcon = (CircleImageView) convertView.findViewById(R.id.im_group_image);
                        holder.tvName = (TextView) convertView.findViewById(R.id.tv_group_name);

                        convertView.setTag(holder);
                    } else {
                        holder = (ViewHolder) convertView.getTag();
                    }
                    GroupBean item = getItem(position);
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
