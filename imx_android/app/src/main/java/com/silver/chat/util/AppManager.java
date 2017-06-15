package com.silver.chat.util;

import android.app.Activity;

import com.silver.chat.MainActivity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * 作者：Fandy on 2016/10/30 21:59
 * 邮箱：fandy618@hotmail.com
 */

public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishAllActivityAndExit() {
        if (null != activityStack) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    public void finishAllActivity() {
        if (null != activityStack) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                Activity activity = activityStack.get(i);
                if (null != activity && activity.getClass() != MainActivity.class) {
                    activity.finish();
                }
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    public void removeActivity(Activity activity) {
        if (activityStack.isEmpty())
            return;
        if (activity != null)
            activityStack.remove(activity);
    }
}
