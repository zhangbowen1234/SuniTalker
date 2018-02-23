package com.example.bowen.sunitalker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.bowen.sunitalker.frags.main.ActiveFragment;
import com.example.bowen.sunitalker.frags.main.ContactFragment;
import com.example.bowen.sunitalker.frags.main.GroupFragment;
import com.example.bowen.sunitalker.helper.NavHelper;
import com.example.common.app.Activity;
import com.example.common.widget.PortraitView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends Activity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.appbar)
    View mLayAppBar;

    @BindView(R.id.im_portrait)
    PortraitView mPortrait;

    @BindView(R.id.txt_title)
    TextView mTitle;

    @BindView(R.id.lay_container)
    FrameLayout mContainer;

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    private NavHelper mNavHelper;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        //初始化工具类
        mNavHelper = new NavHelper();
        //添加对底部按钮的监听
        mNavigation.setOnNavigationItemSelectedListener(this);

        Glide.with(this)
                .load(R.drawable.bg_src_morning)
                .centerCrop()
                .into(new ViewTarget<View, GlideDrawable>(mLayAppBar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    @OnClick(R.id.im_search)
    void onSearchMenuClick() {

    }

    @OnClick(R.id.btn_action)
    void onActionClick() {

    }

    boolean isFirst;

    /**
     * 当我们的底部导航呗点击的时候触发
     * @param item  MenuItem
     * @return  True  代表我们能够处理这个点击
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_home) {
            mTitle.setText(R.string.title_home);

            ActiveFragment activeFragment = null;

            if (isFirst){
                activeFragment = new ActiveFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.lay_container, activeFragment)
                        .commit();
                isFirst = false;
            }else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lay_container, activeFragment)
                        .commit();
            }
        } else if (item.getItemId() == R.id.action_group) {
            GroupFragment groupFragment = new GroupFragment();
            if (isFirst){
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.lay_container, groupFragment)
                        .commit();
                isFirst = false;
            }else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lay_container, groupFragment)
                        .commit();
            }

        } else if (item.getItemId() == R.id.action_contact) {
            ContactFragment contactFragment = new ContactFragment();
            if (isFirst){
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.lay_container, contactFragment)
                        .commit();
                isFirst = false;
            }else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lay_container, contactFragment)
                        .commit();
            }

        }

        Log.e("TAG: ", "size"+getSupportFragmentManager().getFragments().size());
        mTitle.setText(item.getTitle());
        return mNavHelper.perforimClickMenu(item.getItemId());
    }
}
