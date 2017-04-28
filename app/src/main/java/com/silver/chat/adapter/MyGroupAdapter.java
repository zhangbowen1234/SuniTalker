package com.silver.chat.adapter;

import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.entity.GroupEntity;
import com.silver.chat.util.ImageUtil;
import com.silver.chat.view.recycleview.BaseMultiItemQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * Created by joe on 2017/4/25.
 */
public class MyGroupAdapter extends BaseMultiItemQuickAdapter<GroupEntity,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyGroupAdapter(List data) {
        super(data);
        addItemType(GroupEntity.GROUP_TYPE, R.layout.item_group_type);
        addItemType(GroupEntity.GROUP_NAME,R.layout.item_group_name);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupEntity item) {
        switch (helper.getItemViewType()) {
            case GroupEntity.GROUP_TYPE:
                helper.setText(R.id.title_type,"");
                break;
            case GroupEntity.GROUP_NAME:
                helper.setText(R.id.title_type,"");
                ImageUtil.loadImg((ImageView) helper.getView(R.id.group_head), "");
        }
    }
}
