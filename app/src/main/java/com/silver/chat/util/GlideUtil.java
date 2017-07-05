package com.silver.chat.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.silver.chat.R;

import static com.silver.chat.AppContext.appContext;

/**
 * Glide图片加载
 */

public class GlideUtil {
    /**
     * 加载头像
     *
     * @param v
     * @param url
     */
    public static void loadAvatar(ImageView v, String url) {
        Glide.with(appContext)
                .load(url)
                .asBitmap()
                .dontAnimate()
                //.animate(R.anim.basepopup_fade_in)//淡入动画效果
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.system_head)
                .placeholder(R.drawable.system_head)
                .into(v);
    }
    /**
     * Glide加载图片详情
     */
    public static void loadImageDetail(ImageView v, String url) {
        Glide.with(v.getContext())
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.loadingfailed)
                .placeholder(R.drawable.loadingfailed)
                .into(v);
    }
    /**
     * Glide加载略缩图
     */
    public static void loadLuesuotu(ImageView v, String url) {
        Glide.with(v.getContext())
                .load(url)
                .asBitmap()
                .animate(R.anim.basepopup_fade_in)//淡入动画效果
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.loadingfailed)
                .placeholder(R.drawable.loadingfailed)
                .thumbnail( 0.1f )//指定图片的缩放比例
                .into(v);
    }

}
