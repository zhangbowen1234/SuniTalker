package com.silver.chat.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.silver.chat.R;
import com.silver.chat.util.AppManager;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtil;

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

    @Optional
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        view = this.getWindow().getDecorView();//getDecorView 获得window最顶层的View
        view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.purple_theme));
        ButterKnife.bind(this);
        mContext = this;
        ToastUtil.cancelToast();
        AppManager.getInstance().addActivity(this);
        initView();
        initListener();
        initData();
    }

    public static View getBackgroundView(){
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
