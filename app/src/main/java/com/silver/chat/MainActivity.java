package com.silver.chat;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.adapter.MainPagerAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.ui.chat.SearchChatRecordActivity;
import com.silver.chat.ui.contact.AddFriendActivity;
import com.silver.chat.ui.contact.CreatGroupActivity;
import com.silver.chat.ui.contact.SearchContactActivity;
import com.silver.chat.ui.mine.ScanActivity;
import com.silver.chat.ui.mine.SettingActivity;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.BadgedTabCustomView;
import com.silver.chat.view.TabLayoutPlus;

import static com.silver.chat.R.id.tabLayout;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayoutPlus mTabLayout;
    private MainPagerAdapter mPagerAdapter;
    private ImageView mIvSearch;
    private ImageView mIvMore;
    private Animation mButtonInAnimation;
    private Animation mButtonOutAnimation;
    private RelativeLayout mLlyMore,mLlyChatMore;
    private TextView mTvScan,mTvChatScan;
    private TextView mTvSearch,mTvChatSearch;
    private TextView mTvStartChat,mTvAddGroup;
    private TextView mTvUnRead,mTvAddFriend;
    private TextView mTvRead,mTvSearchGroup;
    private TextView mTvClear,mTvChatMore;
    private ImageView mIvBack,mIvMoreBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayoutPlus) findViewById(tabLayout);
        mIvSearch = (ImageView) findViewById(R.id.iv_toolbar_search);

        mLlyChatMore = (RelativeLayout) findViewById(R.id.lly_chat_more);
        mTvChatScan = (TextView) findViewById(R.id.tv_scan_chat);
        mTvChatSearch = (TextView) findViewById(R.id.tv_search_chat);
        mTvAddFriend = (TextView) findViewById(R.id.tv_add_friend);
        mTvAddGroup = (TextView) findViewById(R.id.tv_start_group_chat);
        mTvSearchGroup = (TextView) findViewById(R.id.tv_search_group);
        mTvChatMore = (TextView) findViewById(R.id.tv_more_chat);
        mIvMoreBack = (ImageView) findViewById(R.id.iv_back_chat);

        mLlyMore = (RelativeLayout) findViewById(R.id.lly_more);
        mIvMore = (ImageView) findViewById(R.id.iv_more);
        mTvScan = (TextView) findViewById(R.id.tv_scan);
        mTvSearch = (TextView) findViewById(R.id.tv_search);
        mTvStartChat = (TextView) findViewById(R.id.tv_start_chat);
        mTvUnRead = (TextView) findViewById(R.id.tv_unread);
        mTvRead = (TextView) findViewById(R.id.tv_read);
        mTvClear = (TextView) findViewById(R.id.tv_clear);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mButtonInAnimation = AnimationUtils.loadAnimation(this, R.anim.button_in);
        mButtonOutAnimation = AnimationUtils.loadAnimation(this, R.anim.button_out);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("chat");
    }

    @Override
    protected void initData() {
        super.initData();
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            BadgedTabCustomView customView = mTabLayout.getTabCustomViewAt(i);
            if (customView != null) {
                customView.setTabCount(true, i);
            }
        }
        mTabLayout.getTabCustomViewAt(1).setTabCount(true, 0);
    }

    private void openPanelView() {
        if (mTabLayout.getSelectedTabPosition() == 0){
            mLlyMore.setVisibility(View.VISIBLE);
            mTvScan.startAnimation(mButtonInAnimation);
            mTvSearch.startAnimation(mButtonInAnimation);
            mTvStartChat.startAnimation(mButtonInAnimation);
            mTvRead.startAnimation(mButtonInAnimation);
            mTvUnRead.startAnimation(mButtonInAnimation);
            mTvClear.startAnimation(mButtonInAnimation);
        } else if (mTabLayout.getSelectedTabPosition() == 1) {
            mLlyChatMore.setVisibility(View.VISIBLE);
            mTvChatScan.startAnimation(mButtonInAnimation);
            mTvChatSearch.startAnimation(mButtonInAnimation);
            mTvAddGroup.startAnimation(mButtonInAnimation);
            mTvAddFriend.startAnimation(mButtonInAnimation);
            mTvSearchGroup.startAnimation(mButtonInAnimation);
            mTvChatMore.startAnimation(mButtonInAnimation);
        }
    }

    private void closePanelView() {
        if (mTabLayout.getSelectedTabPosition() == 0){
            mTvScan.startAnimation(mButtonOutAnimation);
            mTvSearch.startAnimation(mButtonOutAnimation);
            mTvStartChat.startAnimation(mButtonOutAnimation);
            mTvRead.startAnimation(mButtonOutAnimation);
            mTvUnRead.startAnimation(mButtonOutAnimation);
            mTvClear.startAnimation(mButtonOutAnimation);
        } else if (mTabLayout.getSelectedTabPosition() == 1) {
            mTvChatScan.startAnimation(mButtonOutAnimation);
            mTvChatSearch.startAnimation(mButtonOutAnimation);
            mTvAddGroup.startAnimation(mButtonOutAnimation);
            mTvAddFriend.startAnimation(mButtonOutAnimation);
            mTvSearchGroup.startAnimation(mButtonOutAnimation);
            mTvChatMore.startAnimation(mButtonOutAnimation);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvMore.setOnClickListener(this);
        mIvSearch.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mTvScan.setOnClickListener(this);
        mTvStartChat.setOnClickListener(this);
        mTvRead.setOnClickListener(this);
        mTvUnRead.setOnClickListener(this);
        mTvClear.setOnClickListener(this);

        mTvChatScan.setOnClickListener(this);
        mTvChatSearch.setOnClickListener(this);
        mTvAddGroup.setOnClickListener(this);
        mTvAddFriend.setOnClickListener(this);
        mTvSearchGroup.setOnClickListener(this);
        mIvMoreBack.setOnClickListener(this);
        mTvChatMore.setOnClickListener(this);
        mButtonOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLlyMore.setVisibility(View.GONE);
                mLlyChatMore.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_search:
                if (mTabLayout.getSelectedTabPosition() == 0) {
                    startActivity(SearchChatRecordActivity.class);
                }
                if (mTabLayout.getSelectedTabPosition() == 1) {
                    startActivity(SearchContactActivity.class);
                }
                break;
            case R.id.iv_more:
                if (mTabLayout.getSelectedTabPosition() == 2){
                    startActivity(SettingActivity.class);
                }else {
                    openPanelView();
                }
                break;
            case R.id.iv_back:
                closePanelView();
                break;
            case R.id.iv_back_chat:
                closePanelView();
                break;
            case R.id.tv_scan:
                startActivity(ScanActivity.class);
                finish();
                break;
            case R.id.tv_search:
                ToastUtils.showMessage(mContext,"正在修改中...");
                break;
            case R.id.tv_start_chat:
                ToastUtils.showMessage(mContext,"正在修改中...");
                break;
            case R.id.tv_unread:
                ToastUtils.showMessage(mContext,"正在修改中...");
                break;
            case R.id.tv_read:
                ToastUtils.showMessage(mContext,"正在修改中...");
                break;
            case R.id.tv_clear:
                ToastUtils.showMessage(mContext,"正在修改中...");
                break;
            case R.id.tv_scan_chat:
                startActivity(ScanActivity.class);
                finish();
                break;
            case R.id.tv_search_chat:
                ToastUtils.showMessage(mContext,"正在修改中...");
                break;
            case R.id.tv_add_friend:
//                ToastUtils.showMessage(mContext,"正在修改中...");
                startActivity(AddFriendActivity.class);
                break;
            case R.id.tv_search_group:
                ToastUtils.showMessage(mContext,"正在修改中...");
                break;
            case R.id.tv_start_group_chat:
                startActivity(CreatGroupActivity.class);
                finish();
                break;
            case R.id.tv_more_chat:
                ToastUtils.showMessage(mContext,"正在修改中...");
                break;
        }
    }


}
