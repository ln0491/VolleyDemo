package com.liu.volleydemo.requestion;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/9/26 0026 18:11
 */
public class GsonRequest<T> extends Request<T> {
    private final Response.Listener<T> mListener;
    private       Gson                 mGson;
    private       Class<T>             mClazz;

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        mGson= new Gson();
        mClazz = clazz;
    }

    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    @Override
    protected void deliverResponse(T response) {

        mListener.onResponse(response);

    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            /**
             * 获取数据
             */
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            // 转化反回
            return Response.success(mGson.fromJson(jsonString, mClazz), HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }

    }

}
