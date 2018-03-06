package com.example.factory.data.helper;

import com.example.common.factory.data.DataSource;
import com.example.factory.R;
import com.example.factory.modle.api.RspModel;
import com.example.factory.modle.api.account.AccountRspModel;
import com.example.factory.modle.api.account.RegisterModel;
import com.example.factory.modle.db.User;
import com.example.factory.net.Network;
import com.example.factory.net.RemoteService;

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
        call.enqueue(new Callback<RspModel<AccountRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<AccountRspModel>> call, Response<RspModel<AccountRspModel>> response) {
                // 请求成功返回
                // 从返回中得到我们的全局Model，内部是使用的Gson进行解析
                RspModel<AccountRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    // 拿到实体
                    AccountRspModel accountRspModel = rspModel.getResult();
                    if (accountRspModel.isBind()){
                        User user = accountRspModel.getUser();
                        // 进行的是数据写入和缓存绑定
                        // 然后返回
                        callback.onDataLoaded(user);
                    }else {
                        bindPush(callback);
                    }
                } else {
                    // TODO 对返回的RdpModel中的失败的Code进行解析，解析到对应的String资源上面
//                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
                // 网络请求失败
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    /**
     * 对设备进行绑定的操作
     * @param callback  Callback
     */
    public static void bindPush(final DataSource.Callback<User> callback){

    }
}
