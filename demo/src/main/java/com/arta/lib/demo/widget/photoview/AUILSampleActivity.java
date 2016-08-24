package com.arta.lib.demo.widget.photoview;

import android.app.Activity;
import android.os.Bundle;

import com.arta.lib.demo.R;

import uk.co.senab.photoview.PhotoView;

public class AUILSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoview_activity_simple);

        PhotoView photoView = (PhotoView) findViewById(R.id.iv_photo);
        photoView.setBackgroundResource(R.drawable.wallpaper);
    }
}
