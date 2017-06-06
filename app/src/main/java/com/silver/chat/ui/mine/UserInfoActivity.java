package com.silver.chat.ui.mine;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.network.SSIMFrendManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.ui.contact.ContactChatActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.WhewView;

import java.util.List;

/**
 * 作者：Fandy on 2016/12/1 09:51
 * 邮箱：fandy618@hotmail.com
 */

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvName,mChat ,tvSex ,tvSign,mTvdelete ,mTvSilence;
    private ImageView mIvBack;
    private String contactName ,contactSex ,contactSignature,friendId;
    private WhewView whewView;
    private int tag = 0;
    private Dialog dialog = null;
    private String token ,userId;
    private BaseDao<ContactListBean> mDao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_firend_info;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        tvSign = (TextView) findViewById(R.id.tv_sign);
        mChat = (TextView)findViewById(R.id.tv_detail);
        whewView = (WhewView) findViewById(R.id.wv);
        mTvdelete = (TextView)findViewById(R.id.tv_delete);
        mTvSilence = (TextView)findViewById(R.id.tv_silence);

        // 执行动画
//        whewView.start();
    }
    @Override
    protected void initData() {
        Intent intent = getIntent();
        contactName = intent.getStringExtra("contactName");
        contactSex = intent.getStringExtra("sex");
        contactSignature = intent.getStringExtra("signature");
        friendId = intent.getStringExtra("friendId");
        tvName.setText(contactName);
        if (contactSex.equals(Common.Girl)){
            tvSex.setText("女");
        }else if (contactSex.equals(Common.Boy)){
            tvSex.setText("男");
        }else {
            tvSex.setText("保密");
        }
        tvSign.setText(contactSignature);
        token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        userId = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "");
        mDao = DBHelper.get().dao(ContactListBean.class);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvBack.setOnClickListener(this);
        mChat.setOnClickListener(this);
        mTvdelete.setOnClickListener(this);
        mTvSilence.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_detail:
                Intent mIntent = new Intent(mContext,ContactChatActivity.class);
                mIntent.putExtra("contactName",contactName);
                mIntent.putExtra("friendId",friendId);
                mIntent.putExtra("chatType",Common.PRIVAT);
                startActivity(mIntent);
                break;
            case R.id.tv_delete:
                //删除好友
                DeleteItemDialog(0);
                break;
            case R.id.ok_btn:
                SSIMFrendManger.deleteFriend(mContext,token, userId, friendId, "innerapp", new ResponseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        ToastUtils.showMessage(mContext,baseResponse.getStatusMsg());
                        List<ContactListBean> query = mDao.query(WhereInfo.get().equal("friendId",friendId));
                        //删除原始文件
                        mDao.delete(query);
                        finish();
                    }
                    @Override
                    public void onFailed(BaseResponse baseResponse) {
                        ToastUtils.showMessage(mContext,baseResponse.getStatusMsg());
                    }
                    @Override
                    public void onError() {
                        ToastUtils.showMessage(mContext,"请求失败");
                    }
                });

                dialog.dismiss();
                break;
            case R.id.cancel_btn:
                dialog.dismiss();
                break;
            case R.id.tv_silence:
                SSIMFrendManger.shieldFriend(mContext, token, userId, friendId, new ResponseCallBack<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        ToastUtils.showMessage(mContext,baseResponse.getStatusMsg());
                    }

                    @Override
                    public void onFailed(BaseResponse baseResponse) {
                        ToastUtils.showMessage(mContext,baseResponse.getStatusMsg());
                    }

                    @Override
                    public void onError() {
                        ToastUtils.showMessage(mContext,"请求失败");
                    }
                });
                break;
        }
    }

    private void DeleteItemDialog(int i) {
        tag = i;
        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(R.layout.dialog_item);
        dialog.show();
        // 将自定义的对话框布局写在这里
        System.out.println("=======自定义对话框设置完毕，下面开始设置对话框中的信息=========");
        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final TextView okBtn = (TextView) dialog.findViewById(R.id.ok_btn);
        final TextView cancel = (TextView) dialog.findViewById(R.id.cancel_btn);
        if (i == 0) {
            title.setText("是否删除好友？");
        }
        okBtn.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (whewView.isStarting()){
            //如果动画正在运行就停止，否则就继续执行
            whewView.stop();
        }
    }
}
