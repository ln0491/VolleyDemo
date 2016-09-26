package com.liu.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.liu.volleydemo.bean.WeixinSelected;
import com.liu.volleydemo.requestion.GsonRequest;
import com.liu.volleydemo.requestion.XMLRequest;
import com.liu.volleydemo.volley.MyVolley;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomActivity extends AppCompatActivity {

    @Bind(R.id.btnGetXml)
    Button       mBtnGetXml;
    @Bind(R.id.btnGetGson)
    Button       mBtnGetGson;
    @Bind(R.id.tvResult)
    TextView     mTvResult;
    @Bind(R.id.pb)
    ProgressBar  mPb;
    @Bind(R.id.activity_custom)
    LinearLayout mActivityCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnGetXml, R.id.btnGetGson})
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnGetXml:
                getXml();
                break;
            case R.id.btnGetGson:
                getGson();
                break;
        }
    }


    private void getXml() {


        final RequestQueue requestQueue = MyVolley.getRequestQueue(this);

        String url="http://op.juhe.cn/onebox/weather/query";
        String cityname="深圳";
        String appkey="9275deacf73ae4316f48caa251ec501e";
        String dtype="xml";
        url+="?cityname="+cityname+"&dtype="+dtype+"&key="+appkey;

        XMLRequest xmlRequest = new XMLRequest(url, new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser response) {


                try {
                    int eventType = response.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        if(eventType==XmlPullParser.START_TAG){
                            String nodeName = response.getName();
                            if ("item".equals(nodeName)) {
                                String pName = response.nextText();
                                Log.d("vivi", "pName is " + pName+"\n");
                            }
                        }
                        eventType = response.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                Log.d("vivi", "onErrorResponse: "+error.getMessage());
            }
        });

        requestQueue.add(xmlRequest);

    }

    private void getGson() {

        final RequestQueue requestQueue = MyVolley.getRequestQueue(this);

        String       url    ="http://v.juhe.cn/weixin/query";
        final String appkey ="24834287692b8d54cf634668ce5de0b4";


       GsonRequest<WeixinSelected> gsonRequest = new GsonRequest<WeixinSelected>(Request.Method.POST,url, WeixinSelected.class, new Response.Listener<WeixinSelected>() {
            @Override
            public void onResponse(WeixinSelected weixinSelected) {

                Log.d("vivi", "onResponse: "+weixinSelected.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                volleyError.printStackTrace();
                Log.d("vivi", "onErrorResponse: "+volleyError.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("key",appkey);
                return params;
            }
        };



        requestQueue.add(gsonRequest);
    }
}
