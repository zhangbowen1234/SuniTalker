package com.silver.chat.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Fandy on 2016/11/14 11:58
 * 邮箱：fandy618@hotmail.com
 */

public abstract class BaseFragment extends SupportFragment {
    protected BaseActivity mActivity;
    Unbinder bind;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(mActivity).inflate(getLayoutResourceId(), null);
        bind = ButterKnife.bind(this, view);
        initView(view);
        initListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected abstract int getLayoutResourceId();

    protected void initView(View view) {

    }

    protected void initListener() {

    }

    protected void initData() {

    }
    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        mActivity.startActivity(new Intent(mActivity, clz));
    }

    public void startActivity(Class<?> clz,Bundle bundle) {
        mActivity.startActivity(new Intent(mActivity,clz), new Bundle());
    }

}
