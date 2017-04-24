package com.silver.chat.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by hibon on 2016/11/3.
 */

/**
 * 自定义带边缘的EditText
 */
public class MyLineEditText extends EditText {
    private int mWidth,mHeight;

    public MyLineEditText(Context context) {
        super(context);
    }

    public MyLineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getWidth();
        mHeight =getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);

        canvas.drawLine(0, mHeight, mWidth, mHeight, paint);//bottom
        canvas.drawLine(0,mHeight-dip2px(getContext(),3),0,mHeight,paint);//left
        canvas.drawLine(mWidth,mHeight-dip2px(getContext(),3),mWidth,mHeight,paint);//right
    }


    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
