package com.arta.lib.downloadmanager;

import android.view.View;

import org.xutils.common.Callback;
import org.xutils.x;

import java.io.File;

/**
 * Created by wyouflf on 15/11/10.
 */
public abstract class DownloadViewHolder {

    protected DownloadInfo downloadInfo;

    public DownloadViewHolder(View view, DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
        if(view != null) {
            x.view().inject(this, view);
        }
    }
    public DownloadViewHolder(DownloadInfo downloadInfo) {
        this(null, downloadInfo);
    }

    public DownloadViewHolder() {
        this(null, null);
    }
    public final DownloadInfo getDownloadInfo() {
        return downloadInfo;
    }

    public void update(DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
    }

    public abstract void onWaiting();

    public abstract void onStarted();

    public abstract void onLoading(long total, long current);

    public abstract void onSuccess(File result);

    public abstract void onError(Throwable ex, boolean isOnCallback);

    public abstract void onCancelled(Callback.CancelledException cex);
}
