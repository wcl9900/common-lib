package com.arta.lib.demo.widget.gifview;

import android.app.Activity;
import android.os.Bundle;

import com.arta.lib.demo.R;

import pl.droidsonroids.gif.GifImageView;

public class GifViewTestActivity extends Activity {
	private GifImageView gv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
		setContentView(R.layout.gif_layout);
		gv = (GifImageView) findViewById(R.id.gifview);
		gv.setBackgroundResource(R.drawable.aa);
	}
}
