package com.arta.lib.demo.widget.flowtextview;

import android.app.Activity;
import android.os.Bundle;

import com.arta.lib.demo.R;
import com.arta.lib.widget.flowtextview.FlowTextView;

public class FlowTextViewMainActivity extends Activity {
	private FlowTextView ftv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flowtextview_layout);
		ftv = (FlowTextView)findViewById(R.id.ftv);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<1000;i++){
			sb.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
					"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n");
		}
		ftv.setText(sb.toString());
		ftv.invalidate();
	}
}
