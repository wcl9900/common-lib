package com.arta.lib.demo.widget.swipemenulistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arta.lib.demo.R;

public class SwipeMenuListViewMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipemenulistview_activity_main);

	}
	
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.button1:
			startActivity(new Intent(this, SimpleActivity.class));
			break;
		case R.id.button2:
			startActivity(new Intent(this, DifferentMenuActivity.class));
			break;
		}
	}
}
