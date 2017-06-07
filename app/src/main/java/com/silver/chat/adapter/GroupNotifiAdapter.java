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

    public GroupNotifiAdapter(int layoutResId, List<SSGroupNotification> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SSGroupNotification item, int position) {
        helper.setText(R.id.tv_name,item.getUserName());
        helper.setText(R.id.tv_invite_group,item.getContent());
//        GlideUtil.loadAvatar((ImageView) helper.getView(R.id.iv_avatar), item.getSourceAvatar());
    }
}
