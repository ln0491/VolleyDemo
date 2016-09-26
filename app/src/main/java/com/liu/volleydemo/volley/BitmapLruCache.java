package com.liu.volleydemo.volley;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/9/26 0026 15:34
 */
public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {


    public BitmapLruCache(int maxSize) {
        //或者下面计算
        // long l = Runtime.getRuntime().maxMemory() / 10;
        super(maxSize);

    }



    @Override
    protected int sizeOf(String key, Bitmap value) {
        //返回宽度与高度字节乖的结果大小
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {
        //根据KEY 获取BITMAP
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

        //保存图片key-url,vaule-bitmap
        put(url,bitmap);
    }
}
