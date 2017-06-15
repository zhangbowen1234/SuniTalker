package com.silver.chat.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.silver.chat.R;
import com.silver.chat.util.AppManager;
import com.silver.chat.util.SkinSettingManager;
import com.silver.chat.util.ToastUtil;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Optional;

/**
 * 作者：Fandy on 2016/11/14 10:38
 * 邮箱：fandy618@hotmail.com
 */

public abstract class BaseActivity extends AppCompatActivity {
    static View view = null;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    private int[] layouts = {R.id.ll_common_bg_view};

    @Optional
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        ToastUtil.cancelToast();
        AppManager.getInstance().addActivity(this);
        initView();
        initListener();
        initData();
    }

    public static View getBackgroundView() {
        return view;
    }

    protected void initView() {

    }

    protected void initListener() {

    }

    protected void initData() {

    }

    /**
     * 获取资源布局id
     *
     * @return 资源布局id
     */
    protected abstract int getLayoutId();

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        LinearLayout[] layout = new LinearLayout[layouts.length];
        for (int i = 0; i < layouts.length; i++) {
            layout[i] = (LinearLayout) findViewById(layouts[i]);
            SkinSettingManager mSettingManager = new SkinSettingManager(BaseActivity.this, layout[i]);
            mSettingManager.initSkins();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


}
