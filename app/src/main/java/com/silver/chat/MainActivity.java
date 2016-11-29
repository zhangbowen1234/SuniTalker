package com.silver.chat;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

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
        mIvSearch = (ImageView) findViewById(R.id.iv_search);
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

    @Override
    protected void initListener() {
        super.initListener();
        mIvSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                if (mTabLayout.getSelectedTabPosition() == 0) {
                    startActivity(SearchChatRecordActivity.class);
                }
                if (mTabLayout.getSelectedTabPosition() ==1){
                    startActivity(SearchContactActivity.class);
                }
                break;

        }
    }
}
