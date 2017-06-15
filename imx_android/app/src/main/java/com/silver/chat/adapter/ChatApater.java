package com.silver.chat.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.entity.ChatBean;
import com.silver.chat.entity.DataServer;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.GroupBean;
import com.silver.chat.ui.chat.ChatRecordFragment;
import com.silver.chat.util.DateUtils;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.util.ImageUtil;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.view.recycleview.BaseMultiItemQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;
import com.ssim.android.constant.SSSessionTopLevel;
import com.ssim.android.constant.SSSessionType;
import com.ssim.android.model.notification.SSNotification;
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
    public static String sourceId;
    public static List<SSSession> sessionList;

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
                holder.setText(R.id.tv_content,item.getContent());
                Log.e("avatar:", item.getContent() + item.getUserName()+item.getSendTime());
                break;
            case ChatBean.CHAT_GROUP:
                GlideUtil.loadAvatar((ImageView) holder.getView(R.id.iv_group_avatar), item.getGroupAvatar());
                holder.setText(R.id.tv_group_name ,item.getGroupName());
                holder.setText(R.id.tv_group_time, item.getSendTime());
                holder.setText(R.id.tv_group_content,item.getContent());
                Log.e("CHAT_GROUP:", item.getGroupAvatar() + item.getGroupName()+item.getContent());
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
                holder.setText(R.id.tv_name, "群通知=" + holder.getPosition());
                break;
        }
        //置顶颜色
//        ChatBean chatBean = chatBeen.get(position - 1);
//        if (chatBean.getTop() == 1) {
//            holder.itemView.setBackgroundResource(R.color.topdialog);
//        } else{
//            holder.itemView.setBackgroundResource(R.color.translate);
//        }
    }
    public void updateData(List<ChatBean> sessionList) {
        clear();
        addAll(sessionList);
    }
    /**
     * 获取聊天列表
     */
    public static List<ChatBean> getChatData(Context context) {
        String userId = PreferenceUtil.getInstance(context).getString(PreferenceUtil.USERID, "");
        sessionList = AppContext.getInstance().instance.getSessionList(userId);
        List<ChatBean> list = new ArrayList<>();

        for (int i = 0; i < sessionList.size(); i++) {
            SSSessionType sessionType = sessionList.get(i).getSessionType();
            String friendAvatar, friendNickname, groupName, groupAvatar, groupId, contents, times;
            if (sessionType == SSSessionType.P2PCHAT) {
                //好友聊天列表
                sourceId = sessionList.get(i).getSourceId();
                BaseDao<ContactListBean> mDao = DBHelper.get().dao(ContactListBean.class);
                List<ContactListBean> friendId = mDao.query(WhereInfo.get().equal("friendId", sourceId));
                Log.e("friendId:", friendId.toString());
                for (int j = 0; j < friendId.size(); j++) {
                    friendAvatar = friendId.get(j).getAvatar();
                    friendNickname = friendId.get(j).getNickName();
                    contents= sessionList.get(i).getContent();
                    times = DateUtils.formatTimeSimple(sessionList.get(i).getSendTime());
                    Log.e("sendTimes:", times+contents);
                    list.add(new ChatBean(sourceId, friendNickname, friendAvatar, ChatBean.CHAT_SINGLR,times,contents));
                }
                //获取群组聊天列表
            }else if (sessionType == SSSessionType.GROUPCHAT){
                groupId = sessionList.get(i).getGroupId();
                BaseDao<GroupBean> mDao = DBHelper.get().dao(GroupBean.class);
                List<GroupBean> groupBeen = mDao.query(WhereInfo.get().equal("groupId", groupId));
                Log.e("groupBeen:", groupBeen.toString());
                for (int j = 0; j < groupBeen.size(); j++) {
                    groupAvatar = groupBeen.get(j).getAvatar();
                    groupName = groupBeen.get(j).getGroupName();
                    contents = sessionList.get(i).getContent();
                    times = DateUtils.formatTimeSimple(sessionList.get(i).getSendTime());
//                    ssSessionTopLevel = sessionList.get(i).getTopLevel();
                    Log.e("groupAvatar:", groupBeen.get(j).getAvatar()+groupBeen.get(j).getGroupName()+sessionList.get(i).getContent());
                    list.add(new ChatBean(contents, ChatBean.CHAT_GROUP, times, groupId, groupName, groupAvatar));
                }
            }else if (sessionType == SSSessionType.GROUPNOTI){
                //群通知
                contents = sessionList.get(i).getContent();
            }
        }
        Log.e("list:", list.size()+"");
        return list;
    }
}
