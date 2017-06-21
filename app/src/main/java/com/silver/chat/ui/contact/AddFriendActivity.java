package com.silver.chat.ui.contact;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.AppContext;
import com.silver.chat.R;
import com.silver.chat.adapter.AddFriendAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMFrendManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.SearchIdBean;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.AddFriendSearchLayout;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.OnItemClickListener;

import java.util.ArrayList;
/**
 * 作者：hibon on 2016/11/16 14:14
 * 添加好友
 */
public class AddFriendActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLLAddTitle;
    private RelativeLayout mClickSeach;
    private LinearLayout mAddPhoneFriend,mLlSearchLayout;
    private TextView mCancelSearch;
    private AddFriendSearchLayout mSearchContent;
    private ImageView mBack;
    private ArrayList<SearchIdBean> mSearchList;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AddFriendAdapter mAddAadpter;
    private LinearLayout mTextTitle;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_friend;
    }

    @Override
    protected void initView() {
        super.initView();
        mLLAddTitle = (LinearLayout) findViewById(R.id.ll_add_title);
        mLlSearchLayout = (LinearLayout) findViewById(R.id.ll_search_layout);
        mClickSeach = (RelativeLayout) findViewById(R.id.rl_seach);
        mCancelSearch = (TextView) findViewById(R.id.cancel_search);
        mSearchContent = (AddFriendSearchLayout) findViewById(R.id.ed_search);
        mBack = (ImageView) findViewById(R.id.title_left_back);
        mRecyclerView = (RecyclerView)findViewById(R.id.new_friend_list);
        mTextTitle = (LinearLayout)findViewById(R.id.contact_text);

    }

    @Override
    protected void initData() {
        super.initData();
        mSearchList = new ArrayList<SearchIdBean>();
        linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAddAadpter = new AddFriendAdapter(R.layout.search_user_item ,mSearchList);
        mRecyclerView.setAdapter(mAddAadpter);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mClickSeach.setOnClickListener(this);
        mCancelSearch.setOnClickListener(this);
        mBack.setOnClickListener(this);
        /**
         * 搜索用户
         */
        mSearchContent.setOnSearchClickListener(new AddFriendSearchLayout.OnSearchClickListener() {
            @Override
            public void doSearch() {
                httpGetSearchList();
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchIdBean searchIdBean = (SearchIdBean) adapter.getItem(position);
                Intent mIntent = new Intent(mContext, AddFriendVerifyActivity.class);
                mIntent.setAction("AddFriendActivity");
                mIntent.putExtra("nickName", searchIdBean.getNickName());
                mIntent.putExtra("friendId",searchIdBean.getUserId());
                startActivity(mIntent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_seach:
                mLLAddTitle.setVisibility(View.GONE);
                mLlSearchLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.cancel_search:
                mLLAddTitle.setVisibility(View.VISIBLE);
                mLlSearchLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.title_left_back:
                finish();
                break;

        }
    }
    private void httpGetSearchList(){
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        String condition = mSearchContent.getContent();
        SSIMFrendManger.searchUser(mContext,Common.Phone, condition, "0", "1000", token, new ResponseCallBack<BaseResponse<ArrayList<SearchIdBean>>>() {
            @Override
            public void onSuccess(BaseResponse<ArrayList<SearchIdBean>> listBaseResponse) {
                if (listBaseResponse.data!= null){
                    mTextTitle.setVisibility(View.VISIBLE);
                }
                mAddAadpter.setNewData(listBaseResponse.data);
                mAddAadpter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(BaseResponse<ArrayList<SearchIdBean>> listBaseResponse) {
                ToastUtils.showMessage(mContext,listBaseResponse.getStatusMsg());
            }

            @Override
            public void onError() {
                ToastUtils.showMessage(mContext, "连接服务器失败");
            }
        });


    }

}
