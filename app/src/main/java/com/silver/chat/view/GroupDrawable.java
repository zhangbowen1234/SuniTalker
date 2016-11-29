package com.silver.chat.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * 作者：Fandy on 2016/11/21 19:06
 * 邮箱：fandy618@hotmail.com
 */

public class GroupDrawable extends Drawable {

    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;
    private Bitmap[] mInputBitmaps;
    private Bitmap mSourceBitmap;
    // 间隔
    int interval = 2 / 2;

    public GroupDrawable(Bitmap... inputBitmaps) {
        mInputBitmaps = inputBitmaps;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mCenterX = getBounds().width() / 2;
        mCenterY = getBounds().height() / 2;
        generateSourceBitmap(getBounds());
    }

    @Override
    public void draw(Canvas canvas) {
        if (mSourceBitmap != null && !mSourceBitmap.isRecycled()) {
            canvas.drawBitmap(mSourceBitmap, 0, 0, mPaint);
        }
    }

    /**
     * 生成资源图片
     *
     * @param bounds
     */
    private void generateSourceBitmap(Rect bounds) {
        if (mInputBitmaps != null && mInputBitmaps.length > 0) {
            final int bitmapCount = mInputBitmaps.length;
            mSourceBitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mSourceBitmap);
            Paint drawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            drawPaint.setFilterBitmap(true);

            switch (bitmapCount) {
                case 1:
                    float radius = mCenterX;
                    drawBitmapWithCircle(canvas, mInputBitmaps[0], mCenterX, mCenterY, radius, drawPaint);
                    break;
                case 2:
                    drawTwoBitmap(canvas, drawPaint);
                    break;
                case 3:
                    drawThreeBitmap(canvas, drawPaint);
                    break;
                default:
                    drawFourBitmap(canvas, drawPaint);
                    break;
            }
        }
    }

    private void drawFourBitmap(Canvas canvas, Paint drawPaint) {
        float radius = mCenterX;
        float degree = (float) Math.toDegrees(Math.asin(interval / radius));
        float sweepAngle = 90F - 2 * degree;
        Path clipPath = new Path();
        RectF rectF = new RectF(getBounds());
        drawBitmapWithFourDegree(canvas, mInputBitmaps[0], clipPath, mCenterX + interval, mCenterY - interval, rectF, -90F + degree, sweepAngle
                , mCenterX + interval, mCenterY - interval, radius, drawPaint);

        drawBitmapWithFourDegree(canvas, mInputBitmaps[1], clipPath, mCenterX + interval, mCenterY + interval, rectF, 0F + degree, sweepAngle
                , mCenterX + interval, mCenterY + interval, radius, drawPaint);

        drawBitmapWithFourDegree(canvas, mInputBitmaps[2], clipPath, mCenterX - interval, mCenterY + interval, rectF, 90F + degree, sweepAngle
                , mCenterX - interval, mCenterY + interval, radius, drawPaint);

        drawBitmapWithFourDegree(canvas, mInputBitmaps[3], clipPath, mCenterX - interval, mCenterY - interval, rectF, 180F + degree, sweepAngle
                , mCenterX - interval, mCenterY - interval, radius, drawPaint);
    }

    /**
     * 三之一的圆
     *
     * @param canvas
     * @param drawPaint
     */
    private void drawThreeBitmap(Canvas canvas, Paint drawPaint) {
        float radius = mCenterX;
        float degree = (float) Math.toDegrees(Math.asin(interval / radius));
        float sweepAngle = 120F - 2 * degree;
        Path clipPath = new Path();
        RectF rectF = new RectF(getBounds());
        drawBitmapWithFourDegree(canvas, mInputBitmaps[0], clipPath, mCenterX + interval, mCenterY - interval, rectF, -90F + degree, sweepAngle
                , mCenterX + interval, mCenterY - interval, radius, drawPaint);

        drawBitmapWithFourDegree(canvas, mInputBitmaps[1], clipPath, mCenterX, mCenterY + interval, rectF, 30F + degree, sweepAngle
                , mCenterX, mCenterY + interval, radius, drawPaint);

        drawBitmapWithFourDegree(canvas, mInputBitmaps[2], clipPath, mCenterX - interval, mCenterY - interval, rectF, 150F + degree, sweepAngle
                , mCenterX - interval, mCenterY - interval, radius, drawPaint);
    }

    /**
     * 平分一半的圆
     *
     * @param canvas
     * @param drawPaint
     */
    private void drawTwoBitmap(Canvas canvas, Paint drawPaint) {
        float radius = mCenterX;
        float degree = (float) Math.toDegrees(Math.asin(interval / radius));
        float sweepAngle = 180F - 2 * degree;
        Path clipPath = new Path();
        RectF rectF = new RectF(getBounds());
        drawBitmapWithFourDegree(canvas, mInputBitmaps[0], clipPath, mCenterX - interval, mCenterY, rectF, 90F + degree, sweepAngle
                , mCenterX - interval, mCenterY, radius, drawPaint);
        //右图
        drawBitmapWithFourDegree(canvas, mInputBitmaps[1], clipPath, mCenterX + interval, mCenterY, rectF, -90F + degree, sweepAngle
                , mCenterX + interval, mCenterY, radius, drawPaint);
    }


    /**
     * 画四分之一的圆半角
     */
    private void drawBitmapWithFourDegree(Canvas canvas, Bitmap bitmap, Path clipPath, float x, float y, RectF rectF, float startAngle, float sweepAngle
            , int centerX, int centerY, float radius, Paint drawPaint) {
        clipPath.reset();
        clipPath.moveTo(x, y);
        clipPath.arcTo(rectF, startAngle, sweepAngle);
        clipPath.close();
        drawBitmapWithPath(canvas, bitmap, clipPath, centerX, centerY, radius, drawPaint);
    }

    /**
     * 使用Path画图
     */
    private void drawBitmapWithPath(Canvas canvas, Bitmap bitmap, Path path, int centerX, int centerY, float radius, Paint drawPaint) {
        if (bitmap == null) {
            return;
        }
        final int halfBitmapWidth = bitmap.getWidth() / 2;
        final int halfBitmapHeight = bitmap.getHeight() / 2;

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Matrix shaderMatrix = new Matrix();
        float minSize = bitmap.getWidth() > bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth();
        float scale = radius * 2 / minSize;
        // 先缩放
        shaderMatrix.setScale(scale, scale);
        // 由于缩放的中心点是(0,0)缩放之后,要让中心位移到需要的位置(具体可以用radius=100,minSize=400来验证一下)
        shaderMatrix.postTranslate(centerX - (halfBitmapWidth * scale), centerY - (halfBitmapHeight * scale));
        bitmapShader.setLocalMatrix(shaderMatrix);

        drawPaint.setShader(bitmapShader);
        canvas.drawPath(path, drawPaint);
        drawPaint.setShader(null);
    }

    /**
     * 画圆形图
     */
    private void drawBitmapWithCircle(Canvas canvas, Bitmap bitmap, int centerX, int centerY, float radius, Paint drawPaint) {
        final int halfBitmapWidth = bitmap.getWidth() / 2;
        final int halfBitmapHeight = bitmap.getHeight() / 2;

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Matrix shaderMatrix = new Matrix();
        float minSize = bitmap.getWidth() > bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth();
        float scale = radius * 2 / minSize;
        shaderMatrix.setScale(scale, scale);
        shaderMatrix.postTranslate(centerX - (halfBitmapWidth * scale), centerY - (halfBitmapHeight * scale));
        bitmapShader.setLocalMatrix(shaderMatrix);

        drawPaint.setShader(bitmapShader);
        canvas.drawCircle(centerX, centerY, radius, drawPaint);
        drawPaint.setShader(null);
    }

    public void recycle() {
        if (mInputBitmaps != null) {
            for (int i = 0; i < mInputBitmaps.length; i++) {
                Bitmap bitmap = mInputBitmaps[i];
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
            mInputBitmaps = null;
        }

        if (mSourceBitmap != null && !mSourceBitmap.isRecycled()) {
            mSourceBitmap.recycle();
            mSourceBitmap = null;
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
