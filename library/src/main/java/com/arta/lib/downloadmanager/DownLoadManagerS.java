package com.arta.lib.downloadmanager;

import com.arta.lib.util.FileUtils;

import org.xutils.common.Callback;
import org.xutils.ex.DbException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Describe:多文件下载管理器
 * <p>Author:王春龙
 * <p>CreateTime:2016/11/1
 */
public class DownLoadManagerS {
    private static DownLoadManagerS instance;

    private DownloadManager downloadManager;

    private List<DownloadInfo> downloadInfoList;

    private List<String> urlStarted = new ArrayList<String>();
    private List<String> urlLoading = new ArrayList<String>();
    private List<String> urlWaiting = new ArrayList<String>();
    private List<String> urlError = new ArrayList<String>();
    private List<String> urlFinished = new ArrayList<String>();
    private List<String> urlCancelled = new ArrayList<String>();
    List<List<String>> urlListList = new ArrayList<List<String>>();

    public static DownLoadManagerS getInstance(){
        synchronized (DownLoadManagerS.class) {
            if (instance == null) {
                instance = new DownLoadManagerS();
            }
        }
        return instance;
    }
    private DownLoadManagerS(){
        downloadManager = DownloadManager.getInstance();
        urlListList.add(urlStarted);
        urlListList.add(urlLoading);
        urlListList.add(urlWaiting);
        urlListList.add(urlError);
        urlListList.add(urlFinished);
        urlListList.add(urlCancelled);
    }

    public void startDownload(final List<String> urlList, String saveDir, boolean autoResume, boolean autoRename, final DownloadMultiViewHolder downloadMultiViewHolder){
        if(urlList == null) return;
        clearList();

        for (int i = 0; i < urlList.size(); i++){
            final String url = urlList.get(i);
            String saveDirTemp = saveDir + (saveDir.endsWith("/") ? "":"/") + FileUtils.getFileName(url);
            try {
                DownloadInfo downloadInfoTemp = downloadManager.getDownloadInfo(url);
                if(downloadInfoTemp != null) {
                    if (!downloadInfoList.contains(downloadInfoTemp)) {
                        downloadInfoList.add(downloadInfoTemp);
                    }
                    if (downloadInfoTemp.getState() == DownloadState.FINISHED) {
                        fireSuccess(url, urlList, downloadMultiViewHolder);
                        continue;
                    }
                }
                downloadManager.startDownload(url, FileUtils.getFileNameWithoutExtension(url),
                        saveDirTemp, autoResume, autoRename, new DefaultDownloadViewHolder(null, downloadInfoTemp){
                            @Override
                            public void onWaiting() {
                                super.onWaiting();
                                fireWaiting(url, downloadMultiViewHolder);
                            }

                            @Override
                            public void onStarted() {
                                super.onStarted();
                                fireStarted(url, downloadMultiViewHolder);
                            }

                            @Override
                            public void onLoading(long total, long current) {
                                super.onLoading(total, current);
                                addUrlIntoUrlList(url, urlLoading);
                            }

                            @Override
                            public void onSuccess(File result) {
                                super.onSuccess(result);
                                fireSuccess(url, urlList, downloadMultiViewHolder);
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                super.onError(ex, isOnCallback);
                                fireError(ex, isOnCallback, url, downloadMultiViewHolder);
                            }

                            @Override
                            public void onCancelled(Callback.CancelledException cex) {
                                super.onCancelled(cex);
                                fireCancelled(cex, url, downloadMultiViewHolder);
                            }
                        });
                DownloadInfo downloadInfo = downloadManager.getDownloadInfo(url);
                if(!downloadInfoList.contains(downloadInfo)){
                    downloadInfoList.add(downloadInfo);
                }
            } catch (DbException e) {
                e.printStackTrace();
                DownloadInfo errorInfo = new DownloadInfo();
                errorInfo.setUrl(url);
                errorInfo.setState(DownloadState.ERROR);
                if(!downloadInfoList.contains(errorInfo)) {
                    downloadInfoList.add(errorInfo);
                }
                fireError(e, true, url, downloadMultiViewHolder);
            }
        }
    }

    public void remove(List<String> urls){
        if(urls == null) return;
        for (String url : urls){
            try {
                downloadManager.removeDownload(url);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopDownload(List<String> urls){
        if(urls == null) return;
        for(String url : urls){
            downloadManager.stopDownload(url);
        }
    }

    private void clearList() {
        if(downloadInfoList == null){
            downloadInfoList = new ArrayList<DownloadInfo>();
        }
        downloadInfoList.clear();

        if(urlWaiting == null){
            urlWaiting = new ArrayList<String>();
        }
        urlWaiting.clear();

        if(urlStarted == null){
            urlStarted = new ArrayList<String>();
        }
        urlStarted.clear();

        if(urlLoading == null){
            urlLoading = new ArrayList<String>();
        }
        urlLoading.clear();

        if(urlFinished == null){
            urlFinished = new ArrayList<String>();
        }
        urlFinished.clear();

        if(urlCancelled == null){
            urlCancelled = new ArrayList<String>();
        }
        urlCancelled.clear();

        if(urlError == null){
            urlError = new ArrayList<String>();
        }
        urlError.clear();
    }

    private void fireError(Throwable ex, boolean isOnCallback, String url, DownloadMultiViewHolder downloadMultiViewHolder) {
        addUrlIntoUrlList(url, urlError);
        downloadMultiViewHolder.onError(urlError, ex, isOnCallback);
    }

    private void fireStarted(String url, DownloadMultiViewHolder downloadMultiViewHolder) {
        addUrlIntoUrlList(url, urlStarted);
        if(downloadMultiViewHolder != null){
            downloadMultiViewHolder.onStarted(urlStarted);
        }
    }

    private void fireWaiting(String url, DownloadMultiViewHolder downloadMultiViewHolder) {
        addUrlIntoUrlList(url, urlWaiting);
        if(downloadMultiViewHolder != null){
            downloadMultiViewHolder.onWaiting(urlWaiting);
        }
    }

    private void fireSuccess(String url, List<String> urlList, DownloadMultiViewHolder downloadMultiViewHolder) {
        addUrlIntoUrlList(url, urlFinished);
        if(downloadMultiViewHolder != null){
            downloadMultiViewHolder.onLoading(urlList.size(), urlFinished.size());
            if (urlFinished.size() == urlList.size()) {
                downloadMultiViewHolder.onSuccess(urlFinished, urlError);
            }
        }
    }

    private void fireCancelled(Callback.CancelledException cex, String url, DownloadMultiViewHolder downloadMultiViewHolder) {
        addUrlIntoUrlList(url, urlCancelled);
        downloadMultiViewHolder.onCancelled(urlCancelled, cex);
    }

    private void addUrlIntoUrlList(String url, List<String> urlList){
        for(List<String> list : urlListList){
            if(list == urlList){
                if(!list.contains(url)){
                    list.add(url);
                }
            }
            else {
                if(list.contains(url)){
                    list.remove(url);
                }
            }
        }
    }
}
