package com.liu.volleydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liu.volleydemo.volley.MyVolley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.tvResult)
    TextView     mTvResult;
    @Bind(R.id.stringRequestGet)
    Button       mStringRequestGet;
    @Bind(R.id.stringRequestPost)
    Button       mStringRequestPost;
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;
    @Bind(R.id.jsonObjectRequestGet)
    Button       mJsonObjectRequestGet;
    @Bind(R.id.jsonObjectRequestPost)
    Button       mJsonObjectRequestPost;
    @Bind(R.id.jsonArrayRequestGet)
    Button       mJsonArrayRequestGet;
    @Bind(R.id.jsonArrayRequestPost)
    Button       mJsonArrayRequestPost;
    @Bind(R.id.getNetWorkPic)
    Button       mGetNetWorkPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {

        MyVolley.getRequestQueue(this);
    }

    @OnClick({R.id.stringRequestGet, R.id.stringRequestPost, R.id.jsonObjectRequestGet, R.id.jsonObjectRequestPost, R.id.jsonArrayRequestGet, R.id.jsonArrayRequestPost,R.id.getNetWorkPic})
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.stringRequestGet:
                stringRequestGet();
                break;
            case R.id.stringRequestPost:
                stringRequestPost();
                break;
            case R.id.jsonObjectRequestGet:
                jsonObjectRequestGet();
                break;
            case R.id.jsonObjectRequestPost:
                jsonObjectRequestPost();
                break;
            case R.id.jsonArrayRequestGet:
                jsonArrayRequestGet();
                break;
            case R.id.jsonArrayRequestPost:
                jsonArrayRequestPost();
            case R.id.getNetWorkPic:
                goPicActivity();
                break;
        }
    }

    private void goPicActivity() {
        Intent intent = new Intent(this,PicActivity.class);

        startActivity(intent);

    }


    private void jsonArrayRequestGet() {

        String url    = "http://v.juhe.cn/toutiao/index";
        String appKey = "97a25ce56b4d4563c86884738eed8502";

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        url += "?key=" + appKey;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("vivi", "onResponse: " + response.toString());

                mTvResult.setText("=====\n" + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("vivi", "onResponse: " + error.getMessage());

                mTvResult.setText(error.getMessage());
            }
        });


        requestQueue.add(jsonArrayRequest);



    }

    private void jsonArrayRequestPost() {

        String       url    = "http://v.juhe.cn/toutiao/index";
        final String appKey = "97a25ce56b4d4563c86884738eed8502";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("key", appKey);

                return params;
            }
        };

        requestQueue.add(jsonArrayRequest);
    }


    private void jsonObjectRequestGet() {
        String url    = "http://v.juhe.cn/weixin/query";
        String appKey = "24834287692b8d54cf634668ce5de0b4";
        url += "?key=" + appKey;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("vivi", "onResponse: " + response.toString());

                mTvResult.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("vivi", "onResponse: " + error.getMessage());

                mTvResult.setText(error.getMessage());
            }
        });
        //添加队列
        requestQueue.add(jsonObjectRequest);

    }

    private void jsonObjectRequestPost() {
        String       url    = "http://v.juhe.cn/weixin/query";
        final String appKey = "24834287692b8d54cf634668ce5de0b4";


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("vivi", "onResponse: " + response.toString());

                mTvResult.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("vivi", "onResponse: " + error.getMessage());

                mTvResult.setText(error.getMessage());
            }
        }) {

            /**
             * 重写这个方法添加POST请求的参数
             * @return
             * @throws AuthFailureError
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("key", appKey);

                return params;
            }
        };

        //添加队列
        requestQueue.add(jsonObjectRequest);

    }


    private void stringRequestGet() {

        String url    = "http://op.juhe.cn/onebox/weather/query";
        String appKey = "9275deacf73ae4316f48caa251ec501e";

        url += "?cityname=" + "深圳&key=" + appKey;

        //获取请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        /**
         * 第一个参数 Request.Method.GET
         * 第二个url 请求URL
         * 第三个  new Response.Listener<String> 请求成功回调接口
         * 第四个请求失败回调接口
         */
        //构建请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("vivi", "onResponse: " + response.toString());

                mTvResult.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("vivi", "onResponse: " + error.getMessage());

                mTvResult.setText(error.getMessage());
            }
        });

        //加入请求队列
        requestQueue.add(stringRequest);


    }


    private void stringRequestPost() {
        String       url    = "http://op.juhe.cn/onebox/weather/query";
        final String appKey = "9275deacf73ae4316f48caa251ec501e";

        //建立请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("vivi", "onResponse: " + response.toString());

                mTvResult.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
                Log.d("vivi", "onResponse: " + volleyError.getMessage());

                mTvResult.setText(volleyError.getMessage());
            }


        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("cityname", "深圳");
                params.put("key", appKey);

                return params;
            }
        };

        //添加到队列
        requestQueue.add(stringRequest);

    }


    @Override
    protected void onStop() {
        super.onStop();



    }
}
