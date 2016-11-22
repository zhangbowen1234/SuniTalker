package com.silver.chat.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * 作者：Fandy on 2016/11/14 11:51
 * 邮箱：fandy618@hotmail.com
 */

public class SupportFragment extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    private boolean mIsHidden = true;   // 用于记录Fragment show/hide 状态

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mIsHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden()) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }

    }

    /**
     * 仅在内存重启后有意义(saveInstanceState!=null时)
     *
     * @return Fragment状态 hide : show
     */
    public boolean isSupportHidden() {
        return mIsHidden;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }
}