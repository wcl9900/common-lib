package com.arta.lib.demo.widget.pulltorefreshandload;

import android.app.Activity;
import android.os.Bundle;

import com.arta.lib.demo.R;
import com.arta.lib.widget.pulltorefreshandload.PullToRefreshLayout;

public class PullableImageViewActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pulltorefreshandload_activity_imageview);
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
				.setOnRefreshListener(new MyListener());
	}
}
