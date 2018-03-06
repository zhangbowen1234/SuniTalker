package com.example.factory.net;

import com.example.factory.modle.api.RspModel;
import com.example.factory.modle.api.account.AccountRspModel;
import com.example.factory.modle.api.account.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;

/**
 * 网络请求的所有的接口
 *
 * Created by bowen on 2018/3/6.
 */

public interface RemoteService {

    /**
     * 网络请求一个注册接口
     * @param model  传入的是RegisterModel
     * @return 返回的是RspModel<AccountRspModel>
     */
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model) ;
}
