package com.example.factory.presenter.contact;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.example.common.factory.data.DataSource;
import com.example.common.factory.presenter.BasePresenter;
import com.example.factory.data.helper.UserHelper;
import com.example.factory.model.card.UserCard;
import com.example.factory.model.db.AppDatabase;
import com.example.factory.model.db.User;
import com.example.factory.persistence.Account;
import com.example.factory.utils.DiffUiDataCallback;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人的Presenter实现
 * Created by bowen on 2018/3/27.
 */

public class ContactPresenter extends BasePresenter<ContactContract.View>
        implements ContactContract.Presenter {
    public ContactPresenter(ContactContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();

        // 加载本地数据库数据
        SQLite.select()
                .from(User.class) // 查询的表
                .where(User_Table.isFollow.eq(true)) // 查询的语句
                .and(User_Table.id.notEq(Account.getUserId())) // 查询的条件
                .orderBy(User_Table.name, true)  // 排序方式（正向按名字排序）
                .limit(100) // 每次查100条
                .async() // 异步查询
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<User>() { // 异步返回的Callback
                    @Override
                    public void onListQueryResult(QueryTransaction transaction, @NonNull List<User> tResult) {

                        getView().getRecyclerAdapter().replace(tResult);
                        getView().onAdapterDataChanged();
                    }
                })
                .execute();


        // 加载网络数据
        UserHelper.refreshContacts(new DataSource.Callback<List<UserCard>>() {
            @Override
            public void onDataNotAvailable(int strRes) {
                // 网络失败，因为本地有数据，不管错误
            }

            @Override
            public void onDataLoaded(final List<UserCard> userCards) {

                final List<User> users = new ArrayList<>();
                for (UserCard userCard : userCards) {
                    users.add(userCard.build());
                }
                // 丢到事务中保存数据库
                DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
                definition.beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (UserCard userCard : userCards) {
                            FlowManager.getModelAdapter(User.class).saveAll(users);
                        }
                    }
                }).build().execute();// 调用异步执行

                // 网络的数据往往是新的，我们需要直接刷新到界面
                List<User> old = getView().getRecyclerAdapter().getItems();

                // 会导致数据顺序全部为新的数据集合
//                getView().getRecyclerAdapter().replace(users);

                diff(old, users);
            }
        });

        // TODO 问题
        // 1.关注后虽然存储数据库，但是没有刷新联系人
        // 2.如果刷新数据库，或者从网络刷新，最终刷新的时候是全局刷新
        // 3.本地刷新和网络刷新，在添加到界面的时候会有可能冲突；导致数据显示异常
        // 4.如何识别已经在数据库中有这样的数据了

    }

    private void diff(List<User> oldList, List<User> newList) {
        DiffUiDataCallback callback = new DiffUiDataCallback<>(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 在对比完成后进行数据刷新
        getView().getRecyclerAdapter().replace(newList);

        // 尝试刷新
        result.dispatchUpdatesTo(getView().getRecyclerAdapter());
        getView().onAdapterDataChanged();
    }
}
