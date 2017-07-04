package com.silver.chat.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.util.DateUtils;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;
import com.ssim.android.model.chat.SSP2PMessage;

import java.util.List;


/**
 * Created by hibon on 2016/11/28.
 */
public class ChatMessageAdapter extends BaseQuickAdapter<SSP2PMessage, BaseViewHolder> {

    private final String mAvatar;

    public ChatMessageAdapter(int layoutResId, List<SSP2PMessage> data, String userAvatar) {
        super(layoutResId, data);
        mAvatar = userAvatar;
        /*将聊天信息List倒置排序*/
//        Collections.reverse(data);
    }


    @Override
    protected void convert(BaseViewHolder holper, SSP2PMessage item, int position) {

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
//        Log.e("item.getSourceId() :", "item.getMessageTime:" + item.getMessageTime() + ",getSourceId:" + item.getSourceId() + ",说:" + item.getContent());
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
//                if (hour.equals(msgHour) || hour == msgHour){
//                    if ( !minute.equals(msgMinute) || minute != msgMinute){
//                        /*小时:分*/
//                        timeView.setText(DateUtils.formatTimeSimple(item.getMessageTime()) + "");
//                    }
//                }else {
                     /*小时:分*/
                timeView.setText(DateUtils.formatTimeSimple(item.getMessageTime()) + "");
//                }
            } else {
                /*月:日:小时:分*/
                timeView.setText(DateUtils.formatMonthDateMdHm(item.getMessageTime()) + "");
            }
        } else {
            /*年:月:日:小时:分*/
            timeView.setText(DateUtils.formatDateAndTime_(item.getMessageTime()) + "");
        }
//        Log.e("year+小时", year + "/"+msgYear);
//        Log.e("monthAndDay+月日", monthAndDay + "/"+msgMonthAndDay);
//        Log.e("hour + minute", hour + "/"+msgHour+"==="+minute +"/"+msgMinute);


//        String msgTime = DateUtils.formatDateAndTime_(item.getMessageTime());
//        String yerarMonthDayHm = DateUtils.formatDateAndTime_(timestamp);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        try {
//            Date msgParse = dateFormat.parse(msgTime);
//            Date currentParse = dateFormat.parse(msgTime);
//            long diff = currentParse.getTime() - msgParse.getTime();
//            Log.e("diff",diff+"");
//
//            long days = diff / (1000 * 60 * 60 * 24);
//            long hours = (diff - days * (1000 * 60 * 60 * 24))
//                    / (1000 * 60 * 60);
//            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
//                    * (1000 * 60 * 60))
//                    / (1000 * 60);
//
//            Log.e("111",days + "天" + hours + "小时" + minutes + "分");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


//        timeView.setText(DateUtils.formatDateAndTime_(item.getMessageTime()) + "");
        String userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");
        if (userId.equals(item.getSourceId())) { //发送
            leftLayout.setVisibility(View.INVISIBLE);
            rightLayout.setVisibility(View.VISIBLE);
            rightMessageView.setText(item.getContent());
            GlideUtil.loadAvatar(rightPhotoView, PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.AVATAR, ""));
        } else { //接收
            leftLayout.setVisibility(View.VISIBLE);
            rightLayout.setVisibility(View.INVISIBLE);
            leftMessageView.setText(item.getContent());
            GlideUtil.loadAvatar(leftPhotoView, mAvatar);
//            Log.d(TAG, "convert2: "+mAvatar);
        }
    }

}
