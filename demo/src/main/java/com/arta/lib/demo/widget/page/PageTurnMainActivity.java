package com.arta.lib.demo.widget.page;

import com.arta.lib.widget.page.PageWidget;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class PageTurnMainActivity extends Activity {
	/** Called when the activity is first created. */
	private Paint mPaint;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels;     // 屏幕宽度（像素）
		int height = metric.heightPixels;   // 屏幕高度（像素）
		
		setContentView(new PageWidget(this,width,height));

	}

}