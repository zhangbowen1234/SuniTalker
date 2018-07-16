package com.example.factory.data.helper;

import android.text.TextUtils;
import android.util.Log;

import com.example.common.factory.data.DataSource;
import com.example.factory.Factory;
import com.example.factory.R;
import com.example.factory.model.api.RspModel;
import com.example.factory.model.api.account.AccountRspModel;
import com.example.factory.model.api.account.LoginModel;
import com.example.factory.model.api.account.RegisterModel;
import com.example.factory.model.db.User;
import com.example.factory.net.Network;
import com.example.factory.net.RemoteService;
import com.example.factory.persistence.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by bowen on 2018/3/6.
 */

public class AccountHelper {


    /**
     * 注册的接口，异步的调用
     *
     * @param model    传递一个注册的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void register(RegisterModel model, final DataSource.Callback<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.getRetrofit().create(RemoteService.class);

        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountRegister(model);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }
    /**
     * 登录的接口，异步的调用
     *
     * @param model    传递一个登录的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void login(LoginModel model, final DataSource.Callback<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.getRetrofit().create(RemoteService.class);

        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountLogin(model);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }

    /**
     * 对设备进行绑定的操作
     *
     * @param callback Callback
     */
    public static void bindPush(final DataSource.Callback<User> callback) {
        // 检查是否为空
        String pushId = Account.getPushId();
        if (TextUtils.isEmpty(pushId))
            return;

        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountBind(pushId);

        call.enqueue(new AccountRspCallback(callback));
        //抛出一个错误，其实是我们的绑定没有进行
        Account.setBind(true);
    }

    /**
     * 请求的回调部分封装
     */
    private static class AccountRspCallback implements Callback<RspModel<AccountRspModel>> {

        final DataSource.Callback<User> callback;

        public AccountRspCallback(DataSource.Callback<User> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<RspModel<AccountRspModel>> call,
                               Response<RspModel<AccountRspModel>> response) {
            // 请求成功返回
            // 从返回中得到我们的全局Model，内部是使用的Gson进行解析
            RspModel<AccountRspModel> rspModel = response.body();//rspModel为null
            if (rspModel.success()) {
                // 拿到实体
                AccountRspModel accountRspModel = rspModel.getResult();
                // 获取我的信息
                User user = accountRspModel.getUser();
                DbHelper.save(User.class,user);

                // 第一种，直接保存
                //user.save();

//                        // 第二种通过ModelAdapter保存（不止可以存一个还可以存一列即一个集合）
//                        FlowManager.getModelAdapter(User.class).save(user);
//
//                        //第三种，事务中
//                        DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
//                        definition.beginTransactionAsync(new ITransaction() {
//                            @Override
//                            public void execute(DatabaseWrapper databaseWrapper) {
//                                FlowManager.getModelAdapter(User.class).save(user);
//                            }
//                        }).build().execute();// 调用异步执行

                // 同步到XML持久化中
                Account.login(accountRspModel);

                // 判断绑定状态，是否绑定设备
                if (accountRspModel.isBind()) {
                    // 设置绑定状态为True
                    Account.setBind(true);
                    // 然后返回
                    if (callback != null)
                        callback.onDataLoaded(user);
                } else {
                    // 进行绑定唤起
                    bindPush(callback);
                }
            } else {
                // TODO 对返回的RdpModel中的失败的Code进行解析，解析到对应的String资源上面
//                    callback.onDataNotAvailable();
                Factory.decodeRspCode(rspModel, callback);
            }
        }

        @Override
        public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
            // 网络请求失败
            if (callback != null)
                callback.onDataNotAvailable(R.string.data_network_error);
            Log.e("onFailure: ", t.toString());
        }
    }
}
