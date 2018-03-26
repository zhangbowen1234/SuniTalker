package com.example.factory.presenter.search;

import com.example.common.factory.presenter.BaseContract;
import com.example.factory.model.card.GroupCard;
import com.example.factory.model.card.UserCard;

import java.util.List;

/**
 * Created by bowen on 2018/3/21.
 */

public interface SearchContract {
    interface Presenter extends BaseContract.Presenter {
        // 搜索内容
        void search(String content);
    }

    // 搜索人的返回界面
    interface UserView extends BaseContract.View<Presenter> {
        void onSearchDone(List<UserCard> userCards);
    }

    // 搜索群的返回界面
    interface GroupView extends BaseContract.View<Presenter> {
        void onSearchDone(List<GroupCard> userCards);
    }
}
