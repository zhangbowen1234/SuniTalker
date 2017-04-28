package com.silver.chat.ui.contact;

import android.graphics.Color;
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
import com.silver.chat.entity.DataServer;
import com.silver.chat.entity.GroupBean;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by joe on 2017/4/25.
 */

public class GroupChatActivity extends BaseActivity {

    ImageView titleLeftBack;
    ImageView imageSeach;
    ListView listView;
    RelativeLayout rlSeach;
    private ArrayList<GroupBean> mCreatGroups;
    private ArrayList<GroupBean> mManagerGroups;
    private ArrayList<GroupBean> mJoinGroups;
    private GoogleApiClient client;

    @Override
    protected void initView() {
        titleLeftBack = (ImageView) findViewById(R.id.title_left_back);
        imageSeach = (ImageView) findViewById(R.id.image_seach);
        listView = (ListView) findViewById(R.id.listview);
        rlSeach = (RelativeLayout) findViewById(R.id.rl_seach);



    }

    @Override
    protected void initData() {
        super.initData();
        mCreatGroups = new ArrayList<>();
        mManagerGroups = new ArrayList<>();
        mJoinGroups = new ArrayList<>();
        GroupBean groupBean = new GroupBean();
        for (int i = 0; i < 1; i++) {
            groupBean.setGroupName("今天中午吃了" + i +"碗牛肉面");
            mCreatGroups.add(groupBean);
            mJoinGroups.add(groupBean);
        }

        listView.setAdapter(new MyAdapter());
    }

    @OnClick({R.id.title_left_back,R.id.rl_seach})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.rl_seach:
                startActivity(SearchContactActivity.class);

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
