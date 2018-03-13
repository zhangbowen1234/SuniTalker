package com.example.factory.presenter.account;


import com.example.common.factory.presenter.BasePresenter;

/**
 * 登录的逻辑实现
 */
public class LoginPresenter extends BasePresenter<LoginContract.View>  // 限制，登录契约的view
        implements LoginContract.Presenter { // 限制登录的 Presenter

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String phone, String password) {

    }

}
