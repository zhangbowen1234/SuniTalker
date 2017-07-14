package com.silver.chat.adapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lqr.emoji.MoonUtils;
import com.silver.chat.R;
import com.silver.chat.entity.ChatMessageBean;
import com.silver.chat.util.DateUtils;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.BubbleImageView;
import com.silver.chat.view.recycleview.BaseMultiItemQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by hibon on 2016/11/28.
 */
public class ChatMessageAdapter extends BaseMultiItemQuickAdapter<ChatMessageBean, BaseViewHolder> {

    private final String mAvatar;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new mChatList is created out of this one to avoid mutable mChatList
     */
    public ChatMessageAdapter(List<ChatMessageBean> data, String userAvatar) {
        super(data);
        this.mAvatar = userAvatar;
        //此处多条目暂时只包含文本消息和地理位置消息
        addItemType(1, R.layout.chat_message_item);
        addItemType(2, R.layout.chat_message_item_image);
        addItemType(8, R.layout.chat_message_item_location);
    }

    @Override
    protected void convert(BaseViewHolder holper, ChatMessageBean item, int position) {
        RelativeLayout leftLayout;
        RelativeLayout rightLayout;
        TextView leftMessageView;
        TextView rightMessageView;
        TextView timeView;
        ImageView leftPhotoView;
        ImageView rightPhotoView;
        BubbleImageView leftSendImg;
        BubbleImageView rightSendImg;
        leftLayout = holper.getView(R.id.chat_friend_left_layout);
        rightLayout = holper.getView(R.id.chat_user_right_layout);
        timeView = holper.getView(R.id.message_time);
        leftPhotoView = holper.getView(R.id.message_friend_userphoto);
        rightPhotoView = holper.getView(R.id.message_user_userphoto);
        leftMessageView = holper.getView(R.id.friend_message);
        rightMessageView = holper.getView(R.id.user_message);
        leftSendImg = holper.getView(R.id.friend_message_img);
        rightSendImg = holper.getView(R.id.user_message_img);
           /*获取当前系统时间的13位的时间戳*/
        long timestamp = System.currentTimeMillis();
        /*当前年份*/
        int year = DateUtils.formatYear(timestamp);
        /*当前月份*/
        String monthAndDay = DateUtils.formatMonthAndDay(timestamp);
        /*当前小时*/
        String hour = DateUtils.formatTimeHour(timestamp);
        /*当前分钟*/
        String minute = DateUtils.formatTimeMinute(timestamp);
        /*消息年份*/
        int msgYear = DateUtils.formatYear(item.getMessageTime());
        /*消息月份*/
        String msgMonthAndDay = DateUtils.formatMonthAndDay(item.getMessageTime());
        /*消息小时*/
        String msgHour = DateUtils.formatTimeHour(item.getMessageTime());
        /*消息分钟*/
        String msgMinute = DateUtils.formatTimeMinute(item.getMessageTime());

        if (year == msgYear) {
            if (monthAndDay.equals(msgMonthAndDay) || monthAndDay == msgMonthAndDay) {
                     /*小时:分*/
                timeView.setText(DateUtils.formatTimeSimple(item.getMessageTime()));
            } else {
                /*月:日:小时:分*/
                timeView.setText(DateUtils.formatMonthDateMdHm(item.getMessageTime()));
            }
        } else {
            /*年:月:日:小时:分*/
            timeView.setText(DateUtils.formatDateAndTime_(item.getMessageTime()));
        }
        String userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");
        if (userId.equals(item.getSourceId())) { //发送
            leftLayout.setVisibility(View.INVISIBLE);
            rightLayout.setVisibility(View.VISIBLE);
            GlideUtil.loadAvatar(rightPhotoView, PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.AVATAR, ""));
            switch (item.getContentType()) {
                case LOCATION:
                    try {
                        JSONObject jsonObject = new JSONObject(item.getContent());
                        String address = (String) jsonObject.get("address");
                        rightMessageView.setText(address);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case TEXT:
                    MoonUtils.identifyFaceExpression(mContext, rightMessageView, item.getContent(), 0b1);
                    break;
                case IMAGE:
                    GlideUtil.loadLuesuotu(rightSendImg, item.getContent());
                    break;
            }
        } else { //接收
            leftLayout.setVisibility(View.VISIBLE);
            rightLayout.setVisibility(View.INVISIBLE);
            GlideUtil.loadAvatar(leftPhotoView, mAvatar);
            switch (item.getContentType()) {
                case LOCATION:
                    try {
                        JSONObject jsonObject = new JSONObject(item.getContent());
                        String address = (String) jsonObject.get("address");
                        leftMessageView.setText(address);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case TEXT:
                    MoonUtils.identifyFaceExpression(mContext, leftMessageView, item.getContent(), 0b1);
                    break;
                case IMAGE:
                    GlideUtil.loadLuesuotu(leftSendImg, item.getContent());
                    break;
            }
        }

    }
}
