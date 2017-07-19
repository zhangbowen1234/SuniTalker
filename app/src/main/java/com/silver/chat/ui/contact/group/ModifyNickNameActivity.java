package com.silver.chat.ui.contact.group;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMGroupManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.silver.chat.util.Utils.context;

/**
 * Created by Joe on 2017/5/11.
 */
public class ModifyNickNameActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.tv_modify_groupName)
    TextView tvModifyGroupName;

    private int groupId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify;
    }

    @Override
    protected void initView() {
        super.initView();
        groupId = getIntent().getIntExtra("groupId",-1);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }


    @OnClick({R.id.title_left_back, R.id.tv_modify_groupName, R.id.iv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.tv_modify_groupName:
                String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
                String userId = PreferenceUtil.getInstance(context).getString(PreferenceUtil.USERID, "");
                String groupNickname = etNickname.getText().toString();
//                SSIMGroupManger.modifyGroupNickName(mContext, Common.version, token, userId, groupId, "haha", new ResponseCallBack<BaseResponse>() {
//                    @Override
//                    public void onSuccess(BaseResponse baseResponse) {
//                        ToastUtils.showMessage(mContext, "修改成功");
//                    }
//
//                    @Override
//                    public void onFailed(BaseResponse baseResponse) {
//                        ToastUtils.showMessage(mContext, "修改失败");
//                    }
//
//                    @Override
//                    public void onError() {
//                        ToastUtils.showMessage(mContext, "修改出错");
//                    }
//                });
                finish();
                break;
            case R.id.iv_delete:
                ToastUtil.toastMessage(mContext, "我被点击了");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
