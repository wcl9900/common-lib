package com.arta.lib.demo.widget.matchtextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;

import com.arta.lib.demo.R;
import com.arta.lib.widget.matchview.MatchDialog;
import com.arta.lib.widget.matchview.MatchTextView;

public class MatchTextViewActivity extends Activity{
	
	private SeekBar mSeekBar;
	private MatchTextView mMatchTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_matchtextview);
		
		mMatchTextView = (MatchTextView) findViewById(R.id.mMatchTextView);
		mSeekBar = (SeekBar) findViewById(R.id.mSeekBar);
		mSeekBar.setProgress(100);
		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				mMatchTextView.setProgress(progress * 1f / 100);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

		findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MatchDialog matchDialog = new MatchDialog();
				// getSupportFragmentManager().beginTransaction().add(matchDialog,
				// "matchDialog").commit();
			}
		});
		
	}

}
