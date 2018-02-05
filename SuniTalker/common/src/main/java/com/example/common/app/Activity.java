package com.example.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bowen on 2018/1/28.
 */

public abstract class Activity extends AppCompatActivity {

    protected Unbinder mRootBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在界面未初始化之前调用的初始化窗口
        initWindows();
        if (initArgs(getIntent().getExtras())) {
            //得到界面Id并设置到Activity界面中
            int layoutId = getContentLayoutId();
            setContentView(layoutId);
            initWidget();
            initData();
        } else {
            finish();
        }

    }

    protected void initWindows() {

    }

    /**
     * 初始化相关参数
     *
     * @param bundle 参数bundle
     * @return 如果控件初始化返回true
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 得到当前界面的资源文件Id
     *
     * @return
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected void initWidget() {
        mRootBinder = ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        //当点击界面导航返回时，Finish当前界面
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        //得到当前Activity下的所有Fragment
        List<android.support.v4.app.Fragment> fragments = getSupportFragmentManager().getFragments();
        //判断是否为空
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                //判断是否为我们能够处理的Fragment类型
                if (fragment instanceof com.example.common.app.Fragment) {
                    //判断是否拦截了返回按钮
                    if (((com.example.common.app.Fragment) fragment).onBackPressed()) {
                        //如果有直接Return
                        return;
                    }
                }
            }
        }

        super.onBackPressed();
        finish();
    }
}
