package com.example.factory.presenter.contact;

import com.example.common.factory.presenter.BaseContract;
import com.example.factory.model.db.User;


/**
 * Created by bowen on 2018/3/27.
 */

public interface ContactContract {
    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, User> {

    }
}
