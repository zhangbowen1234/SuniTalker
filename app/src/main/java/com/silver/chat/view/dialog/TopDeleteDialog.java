package com.silver.chat.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.ui.chat.ChatRecordFragment;
import com.silver.chat.util.ConvertUtils;
import com.silver.chat.util.ScreenUtils;

import static com.silver.chat.R.id.dialog;
import static com.silver.chat.util.Utils.context;

/**
 * Created by 张博文 on 2017/4/26.
 * 删除置顶Dialog 弹窗
 */

public class TopDeleteDialog extends DialogFragment{
    private TextView tv_delete_dialog,tv_top_dialog,tv_no_top_dialog;
    private Display display;
    private Context context;
    private Dialog dialog;

    public TopDeleteDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public TopDeleteDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_top_delete, null);
        // 获取自定义Dialog布局中的控件
        tv_delete_dialog = (TextView) view.findViewById(R.id.tv_delete_dialog);
        tv_top_dialog = (TextView) view.findViewById(R.id.tv_top_dialog);
        tv_no_top_dialog = (TextView) view.findViewById(R.id.tv_no_top_dialog);
//        Bundle bundle = getArguments();
//        int isTop = bundle.getInt(ChatRecordFragment.TOP_STATES);
//        //判断是否已经置顶
//        if (isTop == 1){
//            tv_top_dialog.setVisibility(View.GONE);
//            tv_no_top_dialog.setVisibility(View.VISIBLE);
//        }else if (isTop == 0){
//            tv_top_dialog.setVisibility(View.VISIBLE);
//            tv_no_top_dialog.setVisibility(View.GONE);
//        }
        tv_no_top_dialog.setVisibility(View.GONE);
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

    public TopDeleteDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public TopDeleteDialog setTopTextview(final View.OnClickListener listener) {
        tv_top_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }
    public TopDeleteDialog setDeleteTextview(final View.OnClickListener listener) {
        tv_delete_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }
    public TopDeleteDialog setNoTopTextview(final View.OnClickListener listener) {
        tv_no_top_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public TopDeleteDialog show() {
        dialog.show();
        return null;
    }
}
