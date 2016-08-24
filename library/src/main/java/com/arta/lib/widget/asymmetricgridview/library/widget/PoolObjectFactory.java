package com.arta.lib.widget.asymmetricgridview.library.widget;

import android.view.View;

public interface PoolObjectFactory<T extends View> {
    public T createObject();
}
