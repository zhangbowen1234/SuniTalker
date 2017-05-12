package com.silver.chat.ui.contact.group;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.view.AddFriendSearchLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joe on 2017/5/12.
 */
public class FindGroupActivity extends BaseActivity {
    @BindView(R.id.title_left_back)
    ImageView titleLeftBack;
    @BindView(R.id.image_seach)
    ImageView imageSeach;
    @BindView(R.id.rl_seach)
    RelativeLayout rlSeach;
    @BindView(R.id.ll_add_title)
    LinearLayout llAddTitle;
    @BindView(R.id.ed_search)
    AddFriendSearchLayout edSearch;
    @BindView(R.id.cancel_search)
    TextView cancelSearch;
    @BindView(R.id.ll_search_layout)
    LinearLayout llSearchLayout;
    @BindView(R.id.contact_text)
    LinearLayout contactText;
    @BindView(R.id.new_friend_list)
    RecyclerView newFriendList;
    @BindView(R.id.add_phone_friend)
    LinearLayout addPhoneFriend;
    @BindView(R.id.activity_new_friend)
    RelativeLayout activityNewFriend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_findgroup;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_left_back, R.id.image_seach, R.id.rl_seach, R.id.ll_add_title, R.id.ed_search, R.id.cancel_search, R.id.ll_search_layout, R.id.contact_text, R.id.new_friend_list, R.id.add_phone_friend, R.id.activity_new_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_back:
                finish();
                break;
            case R.id.image_seach:

            case R.id.rl_seach:
                llAddTitle.setVisibility(View.GONE);
                llSearchLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_add_title:
                break;
            case R.id.ed_search:
                break;
            case R.id.cancel_search:
                llAddTitle.setVisibility(View.VISIBLE);
                llSearchLayout.setVisibility(View.GONE);
                break;
            case R.id.ll_search_layout:
                break;
            case R.id.contact_text:
                break;
            case R.id.new_friend_list:
                break;
            case R.id.add_phone_friend:
                break;
            case R.id.activity_new_friend:
                break;
        }
    }
}
