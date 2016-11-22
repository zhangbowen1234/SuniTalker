package com.silver.chat.ui.mine;

import android.os.Bundle;

import com.silver.chat.R;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.ui.contact.ContactFragment;

/**
 * 作者：Fandy on 2016/11/14 14:14
 * 邮箱：fandy618@hotmail.com
 */

public class MineFragment extends BasePagerFragment {
    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_mine;
    }
}
