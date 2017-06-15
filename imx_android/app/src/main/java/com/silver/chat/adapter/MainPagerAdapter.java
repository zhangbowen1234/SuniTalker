package com.silver.chat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.silver.chat.ui.chat.ChatRecordFragment;
import com.silver.chat.ui.contact.ContactFragment;
import com.silver.chat.ui.mine.MineFragment;

/**
 * 作者：Fandy on 2016/11/14 14:05
 * 邮箱：fandy618@hotmail.com
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"会话","联系人","个人"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = ChatRecordFragment.newInstance();
        } else if (position == 1) {
            fragment = ContactFragment.newInstance(false);
        } else if (position == 2) {
            fragment = MineFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0) {
            ChatRecordFragment f = (ChatRecordFragment) super.instantiateItem(container, position);
            return f;
        } else if (position == 1) {
            ContactFragment f = (ContactFragment) super.instantiateItem(container, position);
            return f;
        } else if (position == 2) {
            MineFragment f = (MineFragment) super.instantiateItem(container, position);
            return f;
        } else {
            return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
