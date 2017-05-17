package com.silver.chat.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.silver.chat.R;
import com.silver.chat.network.responsebean.GroupMemberBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 2017/5/16.
 *
 *
 */
public class GroupMemAdapter extends BaseAdapter {
private List<GroupMemberBean> lists ;
    private  ArrayList<GroupMemberBean> list1;
    private  ArrayList<GroupMemberBean> list2;
    private  ArrayList<GroupMemberBean> list3;
    private Context context;
    public GroupMemAdapter(Context context,ArrayList<GroupMemberBean> lists) {
        this.lists = lists;
        this.context = context;
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            if(lists.get(i).getPrivilege() == 1) {
                list1.add(lists.get(i));
            }else if(lists.get(i).getPrivilege() == 2) {
                list2.add(lists.get(i));
            }else {
                list3.add(lists.get(i));
            }

        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 || position == list1.size()+1||position == list2.size()+list1.size()+2) {
            return 0;
        }else {
            return 1;

        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return lists.size()+3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(getItemViewType(position) == 0) {
            convertView = View.inflate(context, R.layout.item_groupmem_list,null);
        }else if(getItemViewType(position) == 1) {
            convertView = View.inflate(context,R.layout.item_groupmem_list1,null);
        }
        return null;
    }
}
