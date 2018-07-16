package com.example.common.factory.data;

import java.util.List;

public interface DbDataSource<Data> extends DataSource {

    /**
     * 有一个基本的数据源加载方法
     * @param callback 传递一个callback回调，一般回调到Presenter
     */
    void load(SucceedCallback<List<Data>> callback);
}
