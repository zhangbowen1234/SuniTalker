package com.silver.chat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 2017/5/16.
 */
public class GroupMemAdapter extends BaseAdapter {
    private List<GroupMemberBean> lists;
    private ArrayList<GroupMemberBean> list1;
    private ArrayList<GroupMemberBean> list2;
    private ArrayList<GroupMemberBean> list3;
    private Context mContext;

    public GroupMemAdapter(Context context, ArrayList<GroupMemberBean> lists) {
        this.lists = lists;
        this.mContext = context;
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getPrivilege() == 1) {
                list1.add(lists.get(i));
            } else if (lists.get(i).getPrivilege() == 2) {
                list2.add(lists.get(i));
            } else {
                list3.add(lists.get(i));
            }

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == list1.size() + 1 || position == list3.size() + list1.size() + 2) {
            return 0;
        } else {
            return 1;

        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return lists.size() + 3;
    }

    @Override
    public GroupMemberBean getItem(int position) {
        if(position > 0 && position <list1.size()+1) {
            return list1.get(position-1);
        }else if(position > list1.size() && position < list1.size()+list3.size()+2) {
            return list3.get(position-2 - list1.size());
        }else {
            return list2.get(position-3 - list1.size() - list3.size());
        }

    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    TextView tv;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == 0) {
            convertView = new TextView(mContext);
            tv = (TextView) convertView;
            tv.setTextColor(Color.parseColor("#8affffff"));
            tv.setTextSize(12);
            tv.setPadding(20, 20, 20, 20);
            if (position == 0) {
                tv.setText("群主");
            } else if (position == list1.size() + 1) {
                tv.setText("管理员("+list3.size()+")");

            } else if (position == list1.size() + list3.size() + 2) {
                tv.setText("群成员(" + list2.size() + ")");
            }
        } else {
            if (getItemViewType(position) == 1) {
                ViewHolder viewHolder = null;
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.item_groupmem_list1, null);
                    viewHolder = new ViewHolder();
                    viewHolder.ivHead = (CircleImageView) convertView.findViewById(R.id.im_group_image);
                    viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_group_name);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder= (ViewHolder) convertView.getTag();
                }
                viewHolder.tvName.setText(getItem(position).getNickName());
                GlideUtil.loadAvatar(viewHolder.ivHead,getItem(position).getAvatar());
            }
        }
        return convertView;
    }
    static class ViewHolder{
        CircleImageView ivHead;
        TextView tvName;

    }
}
