package com.silver.chat.util;

import android.content.Context;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;

import java.io.Closeable;
import java.io.IOException;

import static com.silver.chat.util.Utils.context;

/**
 * Created by bowen on 2017/5/9.
 */

public class ChooseBackgroudUtils {
    /**
     * 切换UI背景
     */
    public static void choosebackgroud(Context context , String ChooseBackgroud) {
        if (ChooseBackgroud == "0"){
            BaseActivity.getBackgroundView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.purple_theme));
        }else if (ChooseBackgroud == "1"){
            BaseActivity.getBackgroundView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.black_theme));
        }else if (ChooseBackgroud == "2"){
            BaseActivity.getBackgroundView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.blue_theme));
        } else if (ChooseBackgroud == "3"){
            BaseActivity.getBackgroundView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green_theme));
        }else if (ChooseBackgroud == "4"){
            BaseActivity.getBackgroundView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red_theme));
        }
    }
}
