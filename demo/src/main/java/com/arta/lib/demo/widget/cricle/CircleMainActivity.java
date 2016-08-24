package com.arta.lib.demo.widget.cricle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.arta.lib.demo.R;
import com.arta.lib.widget.circle.CircleMenuView;

public class CircleMainActivity extends Activity implements OnClickListener{
	
	private Button btn;
	private CircleMenuView menuView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cricle_main);
		initViews();
		initData();
		setListeners();
	}
	
	private void initViews(){
		btn = (Button) findViewById(R.id.btn);
		menuView = (CircleMenuView) findViewById(R.id.menu);
	}
	
	private void initData(){
		
	}
	
	private void setListeners(){
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			if (menuView.isShown())
				menuView.in();
			else
				menuView.out();
			break;

		default:
			break;
		}
		
		if(v==CircleMenuView.childOne){
			Toast.makeText(this, "aaaaaaa", Toast.LENGTH_LONG).show();
		}
	}

}
