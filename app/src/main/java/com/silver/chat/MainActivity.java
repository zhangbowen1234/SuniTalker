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
import com.silver.chat.ui.contact.SearchContactActivity;
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
    private RelativeLayout mLlyMore;
    private TextView mTvScan;
    private TextView mTvSearch;
    private TextView mTvStartChat;
    private TextView mTvUnRead;
    private TextView mTvRead;
    private TextView mTvClear;
    private ImageView mIvBack;

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
        mLlyMore.setVisibility(View.VISIBLE);
        mTvScan.startAnimation(mButtonInAnimation);
        mTvSearch.startAnimation(mButtonInAnimation);
        mTvStartChat.startAnimation(mButtonInAnimation);
        mTvRead.startAnimation(mButtonInAnimation);
        mTvUnRead.startAnimation(mButtonInAnimation);
        mTvClear.startAnimation(mButtonInAnimation);
    }

    private void closePanelView() {
        mTvScan.startAnimation(mButtonOutAnimation);
        mTvSearch.startAnimation(mButtonOutAnimation);
        mTvStartChat.startAnimation(mButtonOutAnimation);
        mTvRead.startAnimation(mButtonOutAnimation);
        mTvUnRead.startAnimation(mButtonOutAnimation);
        mTvClear.startAnimation(mButtonOutAnimation);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvSearch.setOnClickListener(this);
        mIvMore.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mButtonOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLlyMore.setVisibility(View.GONE);
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
                if (position == 2) {
                    mIvMore.setVisibility(View.GONE);
                }else{
                    mIvMore.setVisibility(View.VISIBLE);
                }
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
                openPanelView();
                break;
            case R.id.iv_back:
                closePanelView();
                break;
        }
    }
}
