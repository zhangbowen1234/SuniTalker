package com.silver.chat.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by FanDy on 2016/8/24.
 */
public abstract class BasePagerFragment extends BaseFragment  {

    // 是否可见
    private boolean isVisible;
    // 是否初始化参数
    private boolean isPrepared;
    // 是否是第一次
    private boolean mHasLoadedOnce;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        onVisible();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示

        } else {// 重新显示到最前端中
            upDateUi();
        }
    }

    public void upDateUi() {

    }

    /**
     * fragment惰性加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    private void onInvisible() {

    }

    /**
     * 可见情况
     */
    public void onVisible() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        if (!mHasLoadedOnce) {
            getData();
            mHasLoadedOnce = true;
        }
    }

    /**
     * 防止viewpgaer预加载
     */
    protected abstract void getData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}

