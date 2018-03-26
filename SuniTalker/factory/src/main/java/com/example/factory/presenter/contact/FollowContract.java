package com.example.factory.presenter.contact;

import com.example.common.factory.presenter.BaseContract;
import com.example.factory.model.card.UserCard;

/**
 * 关注的契约（接口定义）
 * Created by bowen on 2018/3/26.
 */

public interface FollowContract {

    // 任务调度者
    interface Presenter extends BaseContract.Presenter{
        // 关注一个人
        void follow(String id);
    }

    interface View extends BaseContract.View<Presenter>{
        // 成功的情况下返回一个用户的信息
        void onFollowSucceed(UserCard userCard);
    }
}
