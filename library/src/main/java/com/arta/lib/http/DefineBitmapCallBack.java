package com.arta.lib.http;

import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;

/**
 * <p>Describe:自定义CallBack,区分请求取消和请求失败操作
 * <p>Author:王春龙
 * <p>CreateTime:2016/6/17
 */
public abstract class DefineBitmapCallBack extends BitmapCallback {
    @Override
    public void onError(Call call, Exception e, int i) {
        if(call.isCanceled()){
            onCancel(call, e, i);
        }
        else {
            onHttpError(call, e, i);
        }
    }

    abstract public void onHttpError(Call call, Exception e, int i);

    public void onCancel(Call call, Exception e, int i){
    }
}
