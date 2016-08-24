package com.arta.lib.http;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

/**
 * <p>Describe:
 * <p>Author:王春龙
 * <p>CreateTime:2016/6/17
 */
public class HttpClient {
    private static HttpClient instance;
    public static HttpClient getInstance(){
        if(instance == null){
            synchronized (HttpClient.class){
                instance = new HttpClient();
            }
        }
        return instance;
    }


    public RequestCall post(Context context, String url, Callback callback){
        return post(context, new RequestParams(), url, callback);
    }

    public RequestCall post(Context context, RequestParams requestParams, String url, Callback callback){
        RequestCall build = OkHttpUtils.post()
                .tag(context)
                .url(url)
                .params(requestParams.getKeyValue())
                .build();
        build.execute(callback);
        return build;
    }

    public RequestCall get(Context context, String url, Callback callback){
        return get(context, new RequestParams(), url, callback);
    }

    public RequestCall get(Context context, RequestParams requestParams, String url, Callback callback){
        RequestCall build = OkHttpUtils.post()
                .tag(context)
                .url(url)
                .params(requestParams.getKeyValue())
                .build();
        build.execute(callback);
        return build;
    }
}
