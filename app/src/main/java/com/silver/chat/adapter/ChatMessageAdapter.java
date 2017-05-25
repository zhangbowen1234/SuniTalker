package com.silver.chat.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.entity.ApplicationData;
import com.silver.chat.entity.User;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;
import com.ssim.android.model.chat.SSP2PMessage;

import java.util.List;

/**
 * Created by hibon on 2016/11/28.
 */
public class ChatMessageAdapter extends BaseQuickAdapter<SSP2PMessage, BaseViewHolder> {


    public ChatMessageAdapter(int layoutResId, List<SSP2PMessage> data) {
        super(layoutResId, data);
    }

    public ChatMessageAdapter(List<SSP2PMessage> data) {
        super(data);
    }


    @Override
    protected void convert(BaseViewHolder holper, SSP2PMessage item,int position) {

        RelativeLayout leftLayout;
        RelativeLayout rightLayout;
        TextView leftMessageView;
        TextView rightMessageView;
        TextView timeView;
        ImageView leftPhotoView;
        ImageView rightPhotoView;

        leftLayout = holper.getView(R.id.chat_friend_left_layout);
        rightLayout = holper.getView(R.id.chat_user_right_layout);
        timeView = holper.getView(R.id.message_time);
        leftPhotoView = holper.getView(R.id.message_friend_userphoto);
        rightPhotoView = holper.getView(R.id.message_user_userphoto);
        leftMessageView = holper.getView(R.id.friend_message);
        rightMessageView = holper.getView(R.id.user_message);

        User user = ApplicationData.getInstance().getUserInfo();
        timeView.setText(item.getMessageTime()+"");
        if (item.getSourceId() == PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID,"")) {
            leftLayout.setVisibility(View.GONE);
            rightLayout.setVisibility(View.VISIBLE);

            rightMessageView.setText(item.getContent());
        } else if (item.getSourceId() != PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID,"")) {// 本身作为接收方
            leftLayout.setVisibility(View.VISIBLE);
            rightLayout.setVisibility(View.GONE);
//            Bitmap photo = ApplicationData.getInstance().getFriendPhotoMap().get(item.getSourceId());
//            if (photo != null)
//                leftPhotoView.setImageBitmap(photo);
            leftMessageView.setText(item.getContent());
        }
    }

}
