package com.example.bowen.sunitalker.helper;

import android.app.FragmentManager;
import android.content.Context;
import android.util.SparseArray;
import android.widget.RelativeLayout;

/**
 * 完成对Fragment的调度与重用问题，
 * 达到最优的Fragment切换
 * <p>
 * Created by bowen on 2018/2/23.
 */

public class NavHelper<T> {
    //所有的Tab集合
    private final SparseArray<Tab<T>> tabs = new SparseArray<>();
    //
    private final Context context;
    private final int containerId;
    private final FragmentManager fragmentManager;
    private OnTabChangedListener<T> listener;
    //当前的一个选择的Tab
    private Tab<T> currentTab;

    public NavHelper(Context context, int containerId, FragmentManager fragmentManager, OnTabChangedListener<T> listener) {
        this.context = context;
        this.containerId = containerId;
        this.fragmentManager = fragmentManager;
        this.listener = listener;
    }

    /**
     * 添加Tab
     *
     * @param menuId Tab对应的菜单Id
     * @param tab    Tab
     */
    public NavHelper<T> add(int menuId, Tab<T> tab) {
        tabs.put(menuId, tab);
        return this;
    }

    /**
     * 获取当前显示的Tab
     *
     * @return 当前的Tab
     */
    public Tab<T> getCurrentTab() {
        return currentTab;
    }

    /**
     * 执行点击菜单的操作
     *
     * @param menuId 菜单的id
     * @return 是否能够处理这个点击
     */
    public boolean perforimClickMenu(int menuId) {
        //集合中寻找点击的菜单对应的Tab，
        //如果有则进行处理
        Tab<T> tab = tabs.get(menuId);
        if (tab != null) {
            return true;
        }
        return false;
    }

    /**
     * 进行真实的Tab选择操作
     *
     * @param tab Tab
     */
    private void doSelect(Tab<T> tab) {
        Tab<T> oldTab = null;

        if (currentTab != null) {
            oldTab = currentTab;
            if (oldTab == tab) {
                //如果说当前的Tab就是点击的Tab，那么我们不做处理
                notifyReselect(tab);
                return;
            }
        }
        //赋值并调用切换方法
        currentTab = tab;
        doTabChanged(currentTab,oldTab);
    }

    private void doTabChanged(Tab<T> newTab,Tab<T> oldTab){

    }

    private void notifyReselect(Tab<T> tab){
        // TODO 二次点击Tab所做的操作
    }
    /**
     * 我们的所有的Tab基础属性
     *
     * @param <T> 泛型的额外参数
     */
    public static class Tab<T> {
        //Fragment对应的信息
        public Class<?> clx;
        //额外的字段，用户自己设定需要使用
        public T extra;
    }

    /**
     * 定义时间处理完成后的回调接口
     *
     * @param <T>
     */
    public interface OnTabChangedListener<T> {
        void onTabChanged(Tab<T> newTab, Tab<T> oldTab);
    }
}
