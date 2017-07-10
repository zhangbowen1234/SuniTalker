package com.silver.chat.ui.contact.group;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silver.chat.MainActivity;
import com.silver.chat.R;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMLoginManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.ui.login.LoginActivity;
import com.silver.chat.util.AppManager;
import com.silver.chat.util.NetUtils;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.dialog.TvLoginOutDialog;

import java.util.List;
import butterknife.BindView;
/**
 * Created by Joe on 2017/5/10.
 */

public class GroupRightFragment extends BasePagerFragment {
    @BindView(R.id.grideview)
    GridView grideview;
    //单页最多条目个数
    public final int CHILD_NUM = 6;
    public List<String> mList;
    private GrideViewAdapter grideViewAdapter;
    private AlertDialog.Builder builder;

    public GroupRightFragment(List list) {
        this.mList = list;
    }
    public GroupRightFragment() {

    }

    @Override
    protected void getData() {
        grideViewAdapter = new GrideViewAdapter();
        grideview.setAdapter(grideViewAdapter);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_group_left;
    }

    class GrideViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size() % CHILD_NUM;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.item_grideview_group, null);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.tv_group_operat);
                holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(mList.get(CHILD_NUM+position));
            //根据集合数据的不同展示不同图片
            switch (holder.textView.getText().toString()) {

                case "消息免扰":
                    holder.imageView.setImageResource(R.drawable.group_message_free);
                    final ViewHolder finalHolder = holder;
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showExcuxeDialog(finalHolder);
                        }
                    });
                    break;
                case "置顶聊天":
                    holder.imageView.setImageResource(R.drawable.ic_stick_white);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mActivity, "聊天已经置顶", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case "投诉":
                    holder.imageView.setImageResource(R.drawable.group_complaint);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mActivity, "投诉成功", Toast.LENGTH_SHORT).show();

                        }
                    });
                    break;
                case "清空记录":
                    holder.imageView.setImageResource(R.drawable.group_empty_record);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mActivity, "记录已经清空", Toast.LENGTH_SHORT).show();

                        }
                    });
                    break;
                case "更多":
                    holder.imageView.setImageResource(R.drawable.group_more);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mActivity, "我是更多", Toast.LENGTH_SHORT).show();


                        }
                    });
                    break;
            }
            return convertView;
        }
    }
    //消息免扰的Dialog
    private void showExcuxeDialog(final ViewHolder holder) {
        new TvLoginOutDialog(getActivity()).builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setTitle("消息免扰")
                .setNegativeButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mActivity, "取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.imageView.setImageResource(R.drawable.group_message_click);
                        Toast.makeText(mActivity,"确定",Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
    static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

}
