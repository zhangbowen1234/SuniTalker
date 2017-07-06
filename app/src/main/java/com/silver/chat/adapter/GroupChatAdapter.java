package com.silver.chat.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lqr.emoji.MoonUtils;
import com.silver.chat.R;
import com.silver.chat.base.Common;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.entity.GroupMessageBean;
import com.silver.chat.network.SSIMLoginManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.GroupMemberBean;
import com.silver.chat.network.responsebean.UserInfoBean;
import com.silver.chat.util.DateUtils;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.util.UIUtils;
import com.silver.chat.view.BubbleImageView;
import com.silver.chat.view.recycleview.BaseMultiItemQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Joe on 2017/6/13.
 */

public class GroupChatAdapter extends BaseMultiItemQuickAdapter<GroupMessageBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new mChatList is created out of this one to avoid mutable mChatList
     */
    public GroupChatAdapter(List<GroupMessageBean> data) {
        super(data);
        //此处多条目暂时只包含文本消息和地理位置消息
        addItemType(1, R.layout.chat_message_item);
        addItemType(2, R.layout.chat_message_item_image);
        addItemType(8, R.layout.chat_message_item_location);
    }

    @Override
    protected void convert(BaseViewHolder holper, GroupMessageBean item, int position) {
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
                timeView.setText(DateUtils.formatTimeSimple(item.getMessageTime()) + "");
            } else {
                /*月:日:小时:分*/
                timeView.setText(DateUtils.formatMonthDateMdHm(item.getMessageTime()) + "");
            }
        } else {
            /*年:月:日:小时:分*/
            timeView.setText(DateUtils.formatDateAndTime_(item.getMessageTime()) + "");
        }
        String userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");

        if (userId.equals(item.getSourceId())) { //发送
            leftLayout.setVisibility(View.INVISIBLE);
            rightLayout.setVisibility(View.VISIBLE);
//            setUserAvatar(rightPhotoView);
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
                    MoonUtils.identifyFaceExpression(mContext,rightMessageView,item.getContent(), 0b1);
                    break;
                case IMAGE:
                    GlideUtil.loadLuesuotu(rightSendImg, item.getContent());
                    break;
            }
        } else { //接收
            leftLayout.setVisibility(View.VISIBLE);
            rightLayout.setVisibility(View.INVISIBLE);
            String sourceId = item.getSourceId();
            List<GroupMemberBean> sourceIdList = DBHelper.get().dao(GroupMemberBean.class).query(WhereInfo.get().equal("userId", sourceId));
            if (sourceIdList.size()>0){
                String avatar = sourceIdList.get(0).getAvatar();
                GlideUtil.loadAvatar(leftPhotoView,avatar);
            }
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
                    MoonUtils.identifyFaceExpression(mContext,leftMessageView,item.getContent(), 0b1);
                    break;
                case IMAGE:
                    GlideUtil.loadLuesuotu(leftSendImg, item.getContent());
                    break;
            }
        }


    }



    /**
     *设置用户头像
     * @param rightPhotoView
     */
    private void setUserAvatar(final ImageView rightPhotoView) {
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        SSIMLoginManger.getUserInfo(mContext, Common.version, token, new ResponseCallBack<BaseResponse<UserInfoBean>>() {

            @Override
            public void onSuccess(BaseResponse<UserInfoBean> userInfoBeanBaseResponse) {
                String avatar = userInfoBeanBaseResponse.data.getAvatar();
                GlideUtil.loadAvatar(rightPhotoView,avatar);
            }

            @Override
            public void onFailed(BaseResponse<UserInfoBean> userInfoBeanBaseResponse) {
                ToastUtil.toastMessage(mContext,userInfoBeanBaseResponse.getStatusMsg());
            }

            @Override
            public void onError() {
            }
        });
    }

}
