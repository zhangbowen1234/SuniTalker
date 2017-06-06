package com.silver.chat.adapter;

import android.view.LayoutInflater;
import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;
import com.ssim.android.constant.SSPublishType;
import com.ssim.android.model.notification.SSGroupNotification;

import java.util.List;

/**
 * Created by bowen on 2017/5/11.
 */

public class GroupNotifiAdapter extends BaseQuickAdapter<SSGroupNotification,BaseViewHolder> {
    private List<SSGroupNotification> mList;

    public GroupNotifiAdapter(int layoutResId, List<SSGroupNotification> data) {
        super(layoutResId, data);
        this.mList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, SSGroupNotification item, int position) {
        for (int i = 0; i < mList.size(); i++) {
            SSPublishType notificationType = mList.get(i).getNotificationType();
            if (notificationType == SSPublishType.NOTIFICATION_QUIT_GROUP){
                String groupId = mList.get(i).getGroupId();//群id
                String groupName = mList.get(i).getGroupName();//群名称
                String sourceId = mList.get(i).getSourceId();//操作者id
                helper.setText(R.id.tv_name,item.getUserName());
                helper.setText(R.id.iv_agree,item.getAgreeType());
                helper.setText(R.id.tv_invite_group,"邀请你加入  " + item.getGroupName());
                GlideUtil.loadAvatar((ImageView) helper.getView(R.id.iv_avatar), item.getSourceAvatar());
//        helper.setText(R.id.tv_process_message,item.getNickName());
            }

        }

//        helper.setOnItemClickListener(R.id.rl_jion_info, new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(mContext,AddGroupNotifiActivity.class);
//
//            }
//        });
    }
}
