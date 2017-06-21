package com.silver.chat.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import com.silver.chat.AppContext;
import com.silver.chat.base.BaseApp;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.silver.chat.base.BaseApp.getMainThreadId;

/**
 * Created by hibon on 2017/5/8.
 */

public class UIUtils {
    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager
     * @param mRecyclerView
     * @param n
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager
     * @param n
     */
    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }
}
