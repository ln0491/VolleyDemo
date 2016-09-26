package com.liu.volleydemo.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/9/26 0026 18:23
 */
public class JSONUtil {
    public JSONUtil(){
    }


    public static <T>T jsonToObject(String json,Class<T> clazz){

        T t = null;

       t= JSON.parseObject(json,clazz);

        return t;
    }


    public static List  jsonToList(String json,Class cls){
        List list = null;
        list = JSON.parseArray(json,cls);
        return list;
    }



    public static String jsonToString(Object obj){
        return JSON.toJSONString(obj);
    }




}
