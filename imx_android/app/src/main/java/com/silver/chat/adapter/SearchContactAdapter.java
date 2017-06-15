package com.silver.chat.adapter;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.silver.chat.R;
import com.silver.chat.util.DisplayUtil;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.BaseViewHolder;

import java.util.List;

/**
 * 作者：Fandy on 2016/11/29 09:53
 * 邮箱：fandy618@hotmail.com
 */

public class SearchContactAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public SearchContactAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {
        int positions = helper.getAdapterPosition();
        LinearLayout layout = helper.getView(R.id.llyout);
        //改变根布局margin
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int px = DisplayUtil.dip2px(mContext, 10);
        int px5 = DisplayUtil.dip2px(mContext, 5);
        if (positions == 0) {
            lp.setMargins(0, px, 0, px5);
            layout.setLayoutParams(lp);
        } else if (positions == getData().size() - 1) {
            lp.setMargins(0, px5, 0, px);
            layout.setLayoutParams(lp);
        } else {
            lp.setMargins(0, px5, 0, px5);
            layout.setLayoutParams(lp);
        }
    }
}
