package com.silver.chat.util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.silver.chat.R;


/**
 * Created by FanDy on 2016/7/14.
 */
public class ToastUtil {

    private static Toast toast;

    public static void cancelToast() {
        if (toast != null)
            toast.cancel();
    }

    public static void toastMessage(Context context, int resId) {
        toastMessage(context, context.getResources().getText(resId));
    }

    public static void toastMessage(final Context context, final CharSequence sequence) {
        new Handler().post(new Runnable() {
            public void run() {
                toast = Toast.makeText(context, sequence, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, context.getResources().getDimensionPixelOffset(R.dimen.dp_64));
                toast.show();
            }
        });
    }

    public static void toastMessage(Context context, CharSequence charSequence, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, charSequence, duration);
        } else {
            toast.setDuration(duration);
            toast.setText(charSequence);
        }
        toast.show();
    }

    public static void toastMessageMiddle(Context context, int resId) {
        toastMessageMiddle(context, context.getResources().getText(resId));
    }

    public static void toastMessageMiddle(final Context context, final CharSequence sequence) {
        new Handler().post(new Runnable() {
            public void run() {
                toast = Toast.makeText(context, sequence, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
