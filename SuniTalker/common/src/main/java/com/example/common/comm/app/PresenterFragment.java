package com.example.common.comm.app;

import android.content.Context;

import com.example.common.factory.presenter.BaseContract;

/**
 * Created by bowen on 2018/3/6.
 */

public abstract class PresenterFragment<Presenter extends BaseContract.Presenter> extends Fragment
        implements BaseContract.View<Presenter> {

    protected Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 在界面onAttach之后就触发初始化Presenter
        initPresenter();
    }

    /**
     * 初始化Presenter
     * @return Presenter
     */
    protected abstract Presenter initPresenter();

    @Override
    public void showError(int str) {
        // 显示错误
        Application.showToast(str);
    }

    @Override
    public void showLoading() {
        // TODO 显示一个Loading

    }

    @Override
    public void setPresenter(Presenter presenter) {
        // View 中赋值Presenter
        mPresenter = presenter;

    }

    @Override
    protected int getContentLayoutId() {
        return 0;
    }
}
