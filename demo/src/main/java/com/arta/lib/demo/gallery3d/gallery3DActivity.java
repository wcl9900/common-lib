package com.arta.lib.demo.gallery3d;

import com.arta.lib.demo.R;
import com.arta.lib.gallery3d.GalleryFlow;
import com.arta.lib.gallery3d.ImageAdapter;

import android.app.Activity;
import android.os.Bundle;

public class gallery3DActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.layout_gallery);
        
        Integer[] images = { R.drawable.img0001, R.drawable.img0030,
                R.drawable.img0100, R.drawable.img0130, R.drawable.img0200,
                R.drawable.img0230, R.drawable.img0300, R.drawable.img0330,
                R.drawable.img0354 };
        
        ImageAdapter adapter = new ImageAdapter(this, images);
        adapter.createReflectedImages();

        GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.Gallery01);
        galleryFlow.setAdapter(adapter);
        
}
}