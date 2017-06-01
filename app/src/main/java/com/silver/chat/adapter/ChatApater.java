package com.silver.chat.adapter;

import android.content.Context;
import android.content.pm.PackageInstaller;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.base.Common;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.entity.DataServer;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.util.DateUtils;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.util.ImageUtil;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.recycleview.BaseMultiItemQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;
import com.ssim.android.constant.SSMessageStatus;
import com.ssim.android.constant.SSSessionType;
import com.ssim.android.model.session.SSSession;

import java.util.ArrayList;
import java.util.List;

import static com.j256.ormlite.field.DataPersisterManager.clear;
import static java.util.Collections.addAll;

/**
 * 作者：Fandy on 2016/11/16 17:57
 * 邮箱：fandy618@hotmail.com
 */

public class ChatApater extends BaseMultiItemQuickAdapter<ChatBean, BaseViewHolder> {

    private List<ChatBean> chatBeen;

    public ChatApater(List<ChatBean> data) {
        super(data);
        chatBeen = data;
        addItemType(ChatBean.CHAT_SINGLR, R.layout.item_chat_record_single);
        addItemType(ChatBean.CHAT_GROUP, R.layout.item_chat_record_group);
        addItemType(ChatBean.CHAT_SYSTEM, R.layout.item_chat_record_system);
        addItemType(ChatBean.CHAT_DISCUSSION_GROUP, R.layout.item_chat_record_dicussion);
        addItemType(ChatBean.CHAT_GROUP_NOTICE, R.layout.item_chat_record_notice);
    }


    @Override
    protected void convert(final BaseViewHolder holder, ChatBean item , int position) {
        //item多布局
        switch (holder.getItemViewType()) {
            case ChatBean.CHAT_SINGLR:
                GlideUtil.loadAvatar((ImageView) holder.getView(R.id.iv_avatar), item.getAvatar());
                holder.setText(R.id.tv_name, item.getUserName());
                holder.setText(R.id.tv_time, item.getSendTime());
                Log.e("avatar:", item.getAvatar() + item.getUserName()+item.getSendTime());
                break;
            case ChatBean.CHAT_GROUP:
                GlideUtil.loadAvatar((ImageView) holder.getView(R.id.iv_avatar), item.getAvatar());
                holder.setText(R.id.tv_name ,item.getUserName());
                Log.e("CHAT_GROUP:", item.getAvatar() + item.getUserName());
                break;
            case ChatBean.CHAT_SYSTEM:
//                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name, "系统消息=" + holder.getPosition());
                break;
            case ChatBean.CHAT_DISCUSSION_GROUP:
                ImageView ivGroup = holder.getView(R.id.iv_avatar);
                ArrayList<String> list = new ArrayList<>();
                list.add(DataServer.IMAGE_URL1);
                list.add(DataServer.IMAGE_URL2);
                list.add(DataServer.IMAGE_URL3);
                list.add(DataServer.IMAGE_URL4);
                if (holder.getPosition() == 5){
                    list.remove(0);
                    ImageUtil.loadGroupAvatar(ivGroup, list);
                }else{
                    ImageUtil.loadGroupAvatar(ivGroup, list);
                }
                holder.setText(R.id.tv_name, "讨论组消息=" + holder.getPosition());
                break;
            case ChatBean.CHAT_GROUP_NOTICE:
//                ImageUtil.loadImg((ImageView) holder.getView(R.id.iv_avatar), item.getContent());
                holder.setText(R.id.tv_name, "群通知=" + holder.getPosition());
                break;
        }
        //置顶颜色
        ChatBean chatBean = chatBeen.get(position - 1);
        if (chatBean.getTop() == 1) {
            holder.itemView.setBackgroundResource(R.color.topdialog);
        } else {
            holder.itemView.setBackgroundResource(R.color.translate);
        }
    }
    public void updateData(List<ChatBean> sessionList) {
        clear();
        addAll(sessionList);
    }
    public static List<ChatBean> getChatData(Context context) {
        String userId = PreferenceUtil.getInstance(context).getString(PreferenceUtil.USERID, "");
        List<SSSession> sessionList = AppContext.getInstance().instance.getsessionList(userId);
        List<ChatBean> list = new ArrayList<>();
        for (int i = 0; i < sessionList.size(); i++) {
            SSSessionType sessionType = sessionList.get(i).getSessionType();
            //获取好友聊天列表
            String SINGLR_avatar;
            String SINGLR_nickname;

            if (sessionType == SSSessionType.P2PCHAT) {
                String sourceId = sessionList.get(i).getSourceId();

                Log.e("sourceId:", sourceId);
                BaseDao<ContactListBean> mDao = DBHelper.get().dao(ContactListBean.class);
                List<ContactListBean> friendId = mDao.query(WhereInfo.get().equal("friendId", sourceId));
                Log.e("friendId:", friendId.toString());
                for (int j = 0; j < friendId.size(); j++) {
                    SINGLR_avatar = friendId.get(j).getAvatar();
                    SINGLR_nickname = friendId.get(j).getNickName();
                    long sendTimes = sessionList.get(j).getSendTime();
                    String s = DateUtils.formatTimeSimple(sendTimes);
                    Log.e("sendTimes:", sendTimes+"");
                    list.add(new ChatBean("user_id=", SINGLR_nickname, SINGLR_avatar, ChatBean.CHAT_SINGLR,s));
                }
                //获取群组聊天列表
            }else if (sessionType == SSSessionType.GROUPCHAT){
                String groupId = sessionList.get(i).getGroupId();
                Log.e("groupId:", groupId);
                BaseDao<GroupBean> mDao = DBHelper.get().dao(GroupBean.class);
                List<GroupBean> groupBeen = mDao.query(WhereInfo.get().equal("userId", userId));
                Log.e("groupBeen:", groupBeen.toString());
                for (int j = 0; j < groupBeen.size(); j++) {
                    SINGLR_avatar = groupBeen.get(j).getAvatar();
                    SINGLR_nickname = groupBeen.get(j).getGroupName();

//                    list.add(new ChatBean("user_id=", SINGLR_nickname, SINGLR_avatar, ChatBean.CHAT_GROUP));
                }
            }
        }
        return list;
    }
}
