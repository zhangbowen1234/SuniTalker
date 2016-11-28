package com.silver.chat.ui.mine;

import android.os.Bundle;
import android.view.View;

import com.silver.chat.R;
import com.silver.chat.base.BasePagerFragment;

/**
 * 作者：zhenghp on 2016/11/24
 */

public class MineFragment extends BasePagerFragment {



    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);

    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_mine;
    }
}
