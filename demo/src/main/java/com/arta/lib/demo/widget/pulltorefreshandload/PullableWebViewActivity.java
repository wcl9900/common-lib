package com.arta.lib.demo.widget.pulltorefreshandload;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.arta.lib.demo.R;
import com.arta.lib.widget.pulltorefreshandload.PullToRefreshLayout;

public class PullableWebViewActivity extends Activity
{
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pulltorefreshandload_activity_webview);
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
				.setOnRefreshListener(new MyListener());
		webView = (WebView) findViewById(R.id.content_view);
		webView.loadUrl("http://blog.csdn.net/zhongkejingwang");
	}
}
