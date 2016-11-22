package com.silver.chat.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Toast 工具类
 * 
 * @author v-zhenghp
 * 
 */

public class ToastUtils {
	private static Handler handler = new Handler(Looper.getMainLooper());
	private static Toast toast = null;
	private static Object object = new Object();

	/**
	 * 普通文本显示 默认显示为Toast.LENGTH_SHORT
	 * 
	 * @param context
	 * @param text
	 */

	public static void showMessage(final Context context, final String text) {
		showMessage(context, text, Toast.LENGTH_SHORT);
	}

	/**
	 * 默认显示为Toast.LENGTH_SHORT
	 * 
	 * @param context
	 * @param msg
	 */

	public static void showMessage(final Context context, final int msg) {
		showMessage(context, msg, Toast.LENGTH_SHORT);
	}

	/**
	 * 在长文本显示
	 * 
	 * @param context
	 * @param msg
	 */

	public static void showMessageLong(final Context context, final String msg) {
		showMessage(context, msg, Toast.LENGTH_LONG);
	}

	/**
	 * Toast发送文本
	 * 
	 * @param context
	 * @param msg
	 * @param len
	 */
	public static void showMessage(final Context context, final int msg,
			final int len) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						synchronized (object) {
							if (toast != null) {
								// toast.cancel();
								toast.setText(msg);
								toast.setDuration(len);
							} else {
								toast = Toast.makeText(context, msg, len);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}

	/**
	 * Toast文本显示
	 * 
	 * @param context
	 * @param msg
	 * @param len
	 */

	public static void showMessage(final Context context, final String msg,
			final int len) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						synchronized (object) {
							if (toast != null) {
								// toast.cancel();
								toast.setText(msg);
								toast.setDuration(len);
							} else {
								toast = Toast.makeText(context, msg, len);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}

	/**
	 * 关闭当前Toast
	 */
	public static void cancelCurrentToast() {
		if (toast != null) {
			toast.cancel();
		}
	}




	/**
	 * 弹出一个Toast ，时间模式Toast.LENGTH_SHORT
	 *
	 * @param mContext Context
	 * @param string  文本
	 */
	public static void showShort(Context mContext, String string) {
		if (toast == null) {
			toast = Toast.makeText(mContext, string, Toast.LENGTH_SHORT);
		} else {
			toast.setText(string);
			toast.setDuration(Toast.LENGTH_SHORT);
		}
		toast.show();
	}

	/**
	 * 弹出一个Toast ，时间模式Toast.LENGTH_SHORT
	 *
	 * @param mContext  Context
	 * @param stringId 文本ID
	 */
	public static void showShort(Context mContext, int stringId) {
		if (toast == null) {
			toast = Toast.makeText(mContext, stringId, Toast.LENGTH_SHORT);
		} else {
			toast.setText(stringId);
			toast.setDuration(Toast.LENGTH_SHORT);
		}
		toast.show();
	}

	/**
	 * 弹出一个Toast ，时间模式Toast.LENGTH_LONG
	 *
	 * @param mContext Context
	 * @param string  文本
	 */
	public static void showLong(Context mContext, String string) {
		if (toast == null) {
			toast = Toast.makeText(mContext, string, Toast.LENGTH_LONG);
		} else {
			toast.setText(string);
			toast.setDuration(Toast.LENGTH_LONG);
		}
		toast.show();
	}

	/**
	 * 弹出一个Toast ，时间模式Toast.LENGTH_SHORT
	 *
	 * @param mContext  Context
	 * @param stringId 文本ID
	 */
	public static void showLong(Context mContext, int stringId) {
		if (toast == null) {
			toast = Toast.makeText(mContext, stringId, Toast.LENGTH_LONG);
		} else {
			toast.setText(stringId);
			toast.setDuration(Toast.LENGTH_LONG);
		}
		toast.show();
	}

}
