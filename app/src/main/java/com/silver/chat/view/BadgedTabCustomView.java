package com.silver.chat.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;


public class BadgedTabCustomView extends RelativeLayout {

    ImageView ivTabIcon;
    TextView tvTabText;
    TextView tvTabSubText;
    ImageView tvTabCount;
    RippleLayout mRippleLayout;
    /**
     * 点击tab之后如果列表有新消息
     * 为true则tab还继续保持new nessage状态
     * 为false则tab不保持new message状态
     */
    private boolean isShowMessage;

    public BadgedTabCustomView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.tablayoutplus_custom_view, this);
        ivTabIcon = (ImageView) findViewById(R.id.ivTabIcon);
        tvTabText = (TextView) findViewById(R.id.tvTabText);
        tvTabSubText = (TextView) findViewById(R.id.tvTabSubText);
        tvTabCount = (ImageView) findViewById(R.id.tvTabCount);
        mRippleLayout = (RippleLayout) findViewById(R.id.ripplelayout);
        setTabText(null);
        setTabCount(false, 0);
    }


    @Nullable
    public void setTabCount(boolean isShowMessage, int count) {
        if (isShowMessage) {
            if (count > 0) {
                mRippleLayout.startRippleAnimation();
                mRippleLayout.setVisibility(VISIBLE);
                tvTabCount.setVisibility(VISIBLE);
            } else {
                mRippleLayout.stopRippleAnimation();
                mRippleLayout.setVisibility(GONE);
                tvTabCount.setVisibility(GONE);
            }
        } else {
            mRippleLayout.stopRippleAnimation();
            mRippleLayout.setVisibility(GONE);
            tvTabCount.setVisibility(GONE);
        }
//        if (count > 0) {
//            tvTabCount.setText(String.valueOf(count));
//            tvTabCount.setVisibility(VISIBLE);
//        } else {
//            tvTabCount.setText(null);
//            tvTabCount.setVisibility(GONE);
//        }
//        tvTabCount.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
    }

    public void setTabText(CharSequence text) {
        tvTabText.setText(text);
        tvTabText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
    }

    public void setTabSubText(CharSequence text) {
        tvTabSubText.setText(text);
        tvTabSubText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
    }

    public void setTabIcon(int resId) {
        ivTabIcon.setImageResource(resId);
        ivTabIcon.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
    }

    public void setTabIcon(Uri uri) {
        ivTabIcon.setImageURI(uri);
        ivTabIcon.setVisibility(uri != null ? View.VISIBLE : View.GONE);
    }

    public void setTabIcon(Drawable drawable) {
        ivTabIcon.setImageDrawable(drawable);
        ivTabIcon.setVisibility(drawable != null ? View.VISIBLE : View.GONE);
    }

    public void setTabIcon(Bitmap bm) {
        ivTabIcon.setImageBitmap(bm);
        ivTabIcon.setVisibility(bm != null ? View.VISIBLE : View.GONE);
    }

    public ImageView getTabIcon() {
        return ivTabIcon;
    }

    public TextView getTabText() {
        return tvTabText;
    }

    public TextView getTabSubText() {
        return tvTabSubText;
    }

    public ImageView getTabCount() {
        return tvTabCount;
    }
}