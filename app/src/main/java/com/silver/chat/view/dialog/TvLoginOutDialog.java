package com.silver.chat.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.silver.chat.R;
import com.silver.chat.util.ConvertUtils;
import com.silver.chat.util.ScreenUtils;


/**
 * 返回登陆Dialog 弹窗
 */
public class TvLoginOutDialog {
    private Context context;
    private Dialog dialog;
    private Button btn_nag;
    private Button btn_pos;
    private Display display;

    public TvLoginOutDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public TvLoginOutDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_loginout_layout, null);

        // 获取自定义Dialog布局中的控件
        btn_nag = (Button) view.findViewById(R.id.btn_nag);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.TopDeleteDialogStyle);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = display.getWidth();
        window.setAttributes(layoutParams);
        window.setGravity(Gravity.CENTER);
        window.setLayout(ScreenUtils.getScreenWidth() - ConvertUtils.dp2px(100), (ViewGroup.LayoutParams.WRAP_CONTENT));
        return this;
    }


    public TvLoginOutDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public TvLoginOutDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public TvLoginOutDialog setPositiveButton(final View.OnClickListener listener) {
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public TvLoginOutDialog setNegativeButton(
            final View.OnClickListener listener) {
        btn_nag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public void show() {
        dialog.show();
    }
}
