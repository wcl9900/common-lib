package com.arta.lib.downloadmanager;

import org.xutils.common.Callback;

import java.util.List;

/**
 * <p>Describe:
 * <p>Author:王春龙
 * <p>CreateTime:2016/11/1
 */
public class DownloadMultiViewHolder {
    public void onWaiting(List<String> urlWaiting){
    }

    public void onStarted(List<String> urlStarted){

    }

    public void onLoading(long total, long current){

    }

    public void onSuccess(List<String> finishedUrls, List<String> errorUrls){

    }

    public void onError(List<String> errorUrls, Throwable ex, boolean isOnCallback){

    }

    public void onCancelled(List<String> urls, Callback.CancelledException cex){

    }
}
