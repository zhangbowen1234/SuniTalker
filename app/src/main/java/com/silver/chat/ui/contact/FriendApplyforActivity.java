package com.silver.chat.ui.contact;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.database.callback.EasyRun;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.network.SSIMFrendManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.requestbean.AgreeFriendAddBody;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.util.CharacterParser;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CircleImageView;
import com.ssim.android.listener.SSNotificationListener;
import com.ssim.android.model.notification.SSNotification;
import com.ssim.android.model.notification.friend.SSAddFriendNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hibon on 2016/11/16 14:14
 * 接受好友申请
 */
public class FriendApplyforActivity extends BaseActivity implements SSNotificationListener, View.OnClickListener {

    private ImageView mBack;
    private CircleImageView mFriendHead;
    private LinearLayout mLlFriendInfo, mYseAdd, mNoAdd;
    private TextView mFriendName, mFriendPhone, mAddTime, mAdditionalMsg;
    private String token, userId, mNickName, mAvatar, sourceId, sourceNickName, sourceAvatar,content;
    private AgreeFriendAddBody agreeFriendAddBody;
    private BaseDao<ContactListBean> mDao;
    /*汉字转换成拼音的类*/
    private CharacterParser characterParser;
    /*排序后的联系人集合*/
    private List<ContactListBean> mConList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_applyfor;
    }

    @Override
    protected void initView() {
        super.initView();
        mLlFriendInfo = (LinearLayout) findViewById(R.id.ll_friend_info);
        mBack = (ImageView) findViewById(R.id.title_left_back);
        mFriendHead = (CircleImageView) findViewById(R.id.add_friend_head);
        mFriendName = (TextView) findViewById(R.id.add_friend_name);
        mFriendPhone = (TextView) findViewById(R.id.friend_phone);
        mAddTime = (TextView) findViewById(R.id.add_time);
        mAdditionalMsg = (TextView) findViewById(R.id.additional_msg);
        mYseAdd = (LinearLayout) findViewById(R.id.yes_add_friend);
        mNoAdd = (LinearLayout) findViewById(R.id.no_add_friend);
        agreeFriendAddBody = new AgreeFriendAddBody();
        /*实例化汉字转拼音类*/
        characterParser = CharacterParser.getInstance();
        if (mConList == null) {
            mConList = new ArrayList<ContactListBean>();
        }


    }


    @Override
    protected void initData() {
        super.initData();
        sourceId = getIntent().getExtras().getString("sourceId");
        sourceNickName = getIntent().getExtras().getString("sourceNickName");
        sourceAvatar = getIntent().getExtras().getString("sourceAvatar");
        content = getIntent().getExtras().getString("content");
        token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");
        mNickName = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.NICKNAME, "");
        mAvatar = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.AVATAR, "");
        mDao = DBHelper.get().dao(ContactListBean.class);
        mFriendName.setText(sourceNickName);
        GlideUtil.loadAvatar(mFriendHead,sourceAvatar);
        mAdditionalMsg.setText(content);
    }

    @Override
    protected void initListener() {
        super.initListener();
        AppContext.getInstance().instance.setNotificationListener(this);
        mYseAdd.setOnClickListener(this);
        mBack.setOnClickListener(this);

    }


    @Override
    public void receiveNotification(SSNotification ssNotification) {
        if (ssNotification instanceof SSAddFriendNotification) {
            SSAddFriendNotification ssAddFriendNotification = (SSAddFriendNotification) ssNotification;

            String sourceId = ssAddFriendNotification.getSourceId();
            String content = ssAddFriendNotification.getContent();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes_add_friend:
                agreeFriendAddBody.setAppName("innerapp");
                agreeFriendAddBody.setSourceId(userId);
                agreeFriendAddBody.setSourceName(mNickName);
                agreeFriendAddBody.setSourceAvatar(mAvatar);
                agreeFriendAddBody.setTargetId(sourceId);
                SSIMFrendManger.agreeFriend(mContext, token, agreeFriendAddBody, new ResponseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        Log.e("onSuccess", baseResponse.toString());
                        ToastUtils.showMessage(mContext, "已同意");
                        ContactListBean sortModel = new ContactListBean();
                        sortModel.setNickName(sourceNickName);
                        sortModel.setAvatar(sourceAvatar);
                        sortModel.setFriendId(Integer.parseInt(sourceId));
                        String pinyin = characterParser.getSelling(sourceNickName);
                        String sortString = pinyin.substring(0, 1).toUpperCase();
                        // 正则表达式，判断首字母是否是英文字母
                        if (sortString.matches("[A-Z]")) {
                            sortModel.setSortLetters(sortString.toUpperCase());
                        } else {
                            sortModel.setSortLetters("#");
                        }
//                        Log.e("sortModel===", "" + sortModel);
                        sortModel.setUserId(PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, ""));
                        mConList.add(sortModel);
                        mDao.asyncTask(new EasyRun<List<ContactListBean>>() {
                            @Override
                            public List<ContactListBean> run() throws Exception {
                                //保存新数据
                                mDao.create(mConList);
                                List<ContactListBean> query = mDao.queryForAll();
                                Log.e("同意添加好友", "=="+query);
                                return null;
                            }

                            @Override
                            public void onMainThread(List<ContactListBean> data) throws Exception {
                            }
                        });
                    }

                    @Override
                    public void onFailed(BaseResponse baseResponse) {
                        Log.e("onSuccess", baseResponse.toString());
                        if (baseResponse.getStatusCode() ==0){
                            ToastUtils.showMessage(mContext,"已添加");
                        }else {
                            ToastUtils.showMessage(mContext, baseResponse.getStatusMsg());
                        }
                    }

                    @Override
                    public void onError() {
                        Log.e("onError", "onError");
                        ToastUtils.showMessage(mContext, "连接服务器失败");
                    }
                });
                break;
            case R.id.title_left_back:
                finish();
                break;

        }
    }
}
