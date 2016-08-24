package com.arta.lib.http;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Describe:数据请求表单数据
 * <p>Author:王春龙
 * <p>CreateTime:2016/6/17
 */
public class RequestParams {
    private HashMap<String, String> keyValues;

    public RequestParams() {
        keyValues = new HashMap<String, String>();
    }

    public void put(String key, String value){
        keyValues.put(key, value);
    }

    public void put(String key, int value){
        keyValues.put(key, value+"");
    }

    public Map<String, String> getKeyValue(){
        return keyValues;
    }
}
