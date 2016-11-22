package com.silver.chat.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.silver.chat.R;
import com.silver.chat.view.GroupDrawable;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Glide图片加载工具类
 */
public class ImageUtil {
    public static void loadImg(ImageView v, String url) {
        Glide.with(v.getContext())
                .load(getFuckUrl(url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(v);
    }

    public static void loadImg(ImageView v, int id) {
        Glide.with(v.getContext())
                .load(id)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(v);
    }

    public static void loadImg(ImageView v, byte[] bytes) {
        Glide.with(v.getContext())
                .load(bytes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(v);
    }

    /**
     * 设置群组图片
     *
     * @param view 对应imagview
     * @param list 对应图片url集合
     */
    public static void loadGroupAvatar(final ImageView view, final ArrayList<String> list) {
        final Bitmap[] bitmaps = new Bitmap[list.size()];
        final WeakHashMap<Integer, SoftReference<Bitmap>> hashMap = new WeakHashMap<>();
        for (int i = 0; i < (list.size() >= 4 ? 4 : list.size()); i++) {
            final int finalI = i;
            Glide.with(view.getContext())
                    .load(list.get(i))
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            hashMap.put(finalI, new SoftReference<>(resource));
                            judgeDoWork(hashMap, bitmaps, view);
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            super.onLoadFailed(e, errorDrawable);
                            Bitmap bitmap = BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.ic_notification);
                            hashMap.put(finalI, new SoftReference<>(bitmap));
                            judgeDoWork(hashMap, bitmaps, view);
                        }

                    });
        }
    }

    /**
     * 下载图片才设置背景
     *
     * @param hashMap
     */
    public static void judgeDoWork(WeakHashMap<Integer, SoftReference<Bitmap>> hashMap, Bitmap[] bitmaps, ImageView view) {
        if (hashMap.size() != bitmaps.length) return;
        for (int i = 0; i < hashMap.size(); i++) {
            bitmaps[i] = hashMap.get(i).get();
        }
        GroupDrawable fuckDrawable = new GroupDrawable(bitmaps);
        view.setImageDrawable(fuckDrawable);
    }


//    public static void loadRoundHeadImage(ImageView imageView, String url) {
//        Glide.with(imageView.getContext())
//                .load(getFuckUrl(url))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.drawable.icon_default_head)
//                .transform(new GlideRoundTransform(imageView.getContext()))
//                .placeholder(R.drawable.icon_default_head)
//                .crossFade()
//                .into(imageView);
//    }
//
//    public static void loadCircleHeadImg(ImageView v, String url) {
//        Glide.with(v.getContext())
//                .load(getFuckUrl(url))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.drawable.icon_default_head_circle)
//                .placeholder(R.drawable.icon_default_head_circle)
//                .transform(new GlideCircleTransform(v.getContext()))
//                .crossFade()
//                .into(v);
//    }

    public static String getFuckUrl(String url) {
        if (url != null && url.startsWith("http://ear.duomi.com/wp-content/themes/headlines/thumb.php?src=")) {
            url = url.substring(url.indexOf("=") + 1, url.indexOf("jpg") > 0 ? url.indexOf("jpg") + 3 : url.indexOf("png") > 0 ? url.indexOf("png") + 3 : url.length());
            url = url.replace("kxt.fm", "ear.duomi.com");
        }
        return url;
    }

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static String saveFile(Bitmap bm, String fileName) {
        String s = Environment.getExternalStorageDirectory().toString();
        File dirFile = new File(s + "/DCIM/Camera/");
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(s + "/DCIM/Camera/" + fileName);
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        try {
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCaptureFile.getAbsolutePath();
    }

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("e", "IOException");
        }
        return buffer;
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static void loadRoundAndBgImg(ImageView v, String url) {
        Glide.with(v.getContext())
                .load(getFuckUrl(url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.ic_launcher)
                .into(v);

   /*     Glide.with(v.getContext())
                .load(getFuckUrl(url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new LowPolyTransform(v.getContext()))
                .into(im_header);*/
    }

    public static String getUrlByIntent(Context mContext, Intent mdata) {
        Uri uri = mdata.getData();
        String scheme = uri.getScheme();
        String data = "";
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = mContext.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(
                            MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
