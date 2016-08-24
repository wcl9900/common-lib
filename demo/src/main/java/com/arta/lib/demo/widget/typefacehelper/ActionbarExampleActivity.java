package com.arta.lib.demo.widget.typefacehelper;

import static com.arta.lib.widget.typefacehelper.TypefaceHelper.typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.arta.lib.demo.R;
import com.arta.lib.widget.typefacehelper.ActionBarHelper;

public class ActionbarExampleActivity extends ActionBarActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//
//		// For use with appcompat library:
//		ActionBarHelper.setTitle(getSupportActionBar(), typeface(this, R.string.app_name));
//
//		// Without appcompat library:
//		// ActionBarHelper.setTitle(getActionBar(), typeface(this, R.string.app_name));
	}
}
