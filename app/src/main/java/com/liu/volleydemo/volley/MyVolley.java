package com.liu.volleydemo.volley;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * @Description: 单例
 * @AUTHOR 刘楠  Create By 2016/9/26 0026 15:39
 */
public class MyVolley {

    private static RequestQueue mRequestQueue;
    private static ImageLoader  mImageLoader;

    private MyVolley() {

    }
    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            init(context);
        }
        return mRequestQueue;
    }

    private static void init(Context context) {

        mRequestQueue = Volley.newRequestQueue(context);

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取内存大小
        int memoryClass = activityManager.getMemoryClass();
        Log.d("vivi", "init: memoryClass"+memoryClass);
        //使用1/8的内存空间来做为缓存
        int maxCacheSize = 1024*1024*memoryClass/8;

        Log.d("vivi", "init: maxCacheSize "+maxCacheSize);

        //初始化ImageLoad同时初始化自定义的ImageCache
        mImageLoader = new ImageLoader(mRequestQueue,new BitmapLruCache(maxCacheSize));

    }

    public static ImageLoader getImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }
}
