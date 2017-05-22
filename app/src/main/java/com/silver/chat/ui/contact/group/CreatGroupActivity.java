package com.silver.chat.ui.contact.group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.adapter.FriendInfoAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.database.callback.EasyRun;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.ui.contact.CreatDiscussionActivity;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.silver.chat.util.Utils.context;

/**
 * Created by bowen on 2017/5/8.
 */

public class CreatGroupActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.image_seach)
    ImageView imageSeach;
    @BindView(R.id.ed_new_group)
    EditText edNewGroup;
    @BindView(R.id.rv_new_group_contacts)
    RecyclerView rvNewGroupContacts;
    @BindView(R.id.tv_determine)
    public TextView tvDetermine;
    private int len;
    private FriendInfoAdapter mAdapter;
    private List<ContactListBean> friendInfo;
    private LinearLayoutManager linearLayoutManager;

    private BaseDao<ContactListBean> mDao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_group;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //联系人列表的adapter
                    mAdapter = new FriendInfoAdapter((Activity) mContext, friendInfo);
                    rvNewGroupContacts.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void initView() {
        super.initView();
        friendInfo = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(mContext);
        //设置布局管理器
        rvNewGroupContacts.setLayoutManager(linearLayoutManager);
        mDao = DBHelper.get().dao(ContactListBean.class);
        //加载联系人列表
        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected void initData() {
        super.initData();
        //输入框
        getEditlisten();
        QueryDbParent();
    }

    @OnClick({R.id.title_left_back, R.id.image_seach, R.id.tv_determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.image_seach:

                break;
            case R.id.tv_determine:
                if (len != 0) {
                    String name = edNewGroup.getText().toString();
                    Intent intent = new Intent(mContext, CreatDiscussionActivity.class);
                    intent.putExtra("Name", name);
                    intent.putExtra("memeberlist", (Serializable) mAdapter.getSelectedList());
                    startActivity(intent);
                } else {
                    ToastUtils.showMessage(mContext, "请填写群组名称");
                }
                break;
        }
    }

    /**
     * 查询数据库所有联系人
     */
    private void QueryDbParent() {
        mDao.asyncTask(new EasyRun<List<ContactListBean>>() {
            @Override
            public List<ContactListBean> run() throws Exception {
                return getSortData();
            }

            @Override
            public void onMainThread(List<ContactListBean> data) throws Exception {
                friendInfo = data;
                mHandler.sendEmptyMessage(0);
            }
        });

    }

    public List<ContactListBean> getSortData() {
        Log.e(" mDao.queryForAll():", mDao.queryForAll() + "");
        return mDao.query(WhereInfo.get().equal("userId", PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.USERID, "")));
    }

    public void getEditlisten() {
        edNewGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int mTextMaxlenght = 0;
                Editable editable = edNewGroup.getText();
                String str = editable.toString().trim();
                //得到最初字段的长度大小，用于光标位置的判断
                int selEndIndex = Selection.getSelectionEnd(editable);
                len = selEndIndex;
                // 取出每个字符进行判断，如果是字母数字和标点符号则为一个字符加1，
                //如果是汉字则为两个字符
                for (int i = 0; i < str.length(); i++) {
                    char charAt = str.charAt(i);
                    //32-122包含了空格，大小写字母，数字和一些常用的符号，
                    //如果在这个范围内则算一个字符，
                    //如果不在这个范围比如是汉字的话就是两个字符
                    if (charAt >= 32 && charAt <= 122) {
                        mTextMaxlenght++;
                    } else {
                        mTextMaxlenght += 2;
                    }
                    // 当最大字符大于20时，进行字段的截取，并进行提示字段的大小
                    if (mTextMaxlenght > 20) {
                        // 截取最大的字段
                        String newStr = str.substring(0, i);
                        edNewGroup.setText(newStr);
                        // 得到新字段的长度值
                        editable = edNewGroup.getText();
                        len = editable.length();
                        if (selEndIndex > len) {
                            selEndIndex = editable.length();
                        }
                        // 设置新光标所在的位置
                        Selection.setSelection(editable, selEndIndex);
                        ToastUtils.showMessage(context, "至多输入10个字");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
