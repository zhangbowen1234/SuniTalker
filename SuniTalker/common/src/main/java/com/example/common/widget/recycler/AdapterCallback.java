package com.example.common.widget.recycler;

/**
 * AdapterCallback对Data进行更新操作
 */
public interface AdapterCallback<Data> {
    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
