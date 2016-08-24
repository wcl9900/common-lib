package com.arta.lib.demo.widget.typefacehelper;

import static com.arta.lib.widget.typefacehelper.TypefaceHelper.typeface;
import android.app.Activity;
import android.os.Bundle;

import com.arta.lib.demo.R;

public class SimpleExampleActivity extends Activity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.typeface_activity_sample);
		typeface(this);
	}
}
