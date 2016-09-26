package com.liu.volleydemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PicActivity extends AppCompatActivity {

    @Bind(R.id.btnGetnetPic)
    Button    mBtnGetnetPic;

      @Bind(R.id.btnGetnetPic2)
    Button    mBtnGetnetPic2;
    @Bind(R.id.btnGetnetPic3)
    Button    mBtnGetnetPic3;


    @Bind(R.id.iv1)
    ImageView mIv1;
    @Bind(R.id.iv2)
    ImageView mIv2;

    @Bind(R.id.networkIv)
    NetworkImageView mNetworkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnGetnetPic,R.id.btnGetnetPic2,R.id.btnGetnetPic3})
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnGetnetPic:

                getPic();
                break;
            case R.id.btnGetnetPic2:
                getPic2();
                break;case
                    R.id.btnGetnetPic3:
                getPic3();
                break;
        }
    }

    private void getPic3() {
        //创建请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //设置默认资源
        mNetworkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        //设置加载失败的资源
        mNetworkImageView.setErrorImageResId(R.mipmap.ic_launcher);


        //创建ImageLoader
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {

                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        //设置资源  setImageUrl()方法接收两个参数，第一个参数用于指定图片的URL地址，第二个参数则是前面创建好的ImageLoader对象。
        mNetworkImageView.setImageUrl("http://img0.imgtn.bdimg.com/it/u=560246235,1189337337&fm=21&gp=0.jpg",imageLoader);

    }

    /**
     * 使用ImageLoader来加载图片
     */
    private void getPic2() {

        //创建请求队列
         RequestQueue requestQueue = Volley.newRequestQueue(this);

        //创建ImageLoader
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {

                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

        /**
         * 第一个要显示在哪个控件
         * 第二个 默认的资源图片
         * 第三个 错误的资源图片
         */
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(mIv2,R.mipmap.ic_launcher,R.mipmap.ic_launcher);


        //调用ImageLoader的get()方法来加载图片
        imageLoader.get("http://www.people.com.cn/mediafile/pic/20151010/79/16111083488036070527.jpg",imageListener,300,400);

    }

    private void getPic() {

        String url ="http://android.tgbus.com/news/UploadFiles_8153/201208/2012081013531630.jpg";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        /**
         * ImageRequest的构造函数接收六个参数，
         * 第一个参数就是图片的URL地址，
         * 第二个参数是图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中。
         * 第三第四个参数分别用于指定允许图片最大的宽度和高度，如果指定的网络图片的宽度或高度大于这里的最大值，则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。
         * 第五个参数用于指定图片的颜色属性，Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小。
         * 第六个参数是图片请求失败的回调
         */
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {

                mIv1.setImageBitmap(bitmap);


            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("vivi", "onErrorResponse: "+error.getMessage());
            }
        });

        requestQueue.add(imageRequest);

    }
}
