package com.arta.lib.demo.widget.glowpadview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arta.lib.demo.R;
import com.arta.lib.widget.glowpadview.GlowPadView;
import com.arta.lib.widget.glowpadview.GlowPadView.OnTriggerListener;

public class glowpadview extends Activity implements OnTriggerListener {

	private GlowPadView mGlowPadView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.glowpadview_activity);

		mGlowPadView = (GlowPadView) findViewById(R.id.glow_pad_view);

		mGlowPadView.setOnTriggerListener(this);
		
		// uncomment this to make sure the glowpad doesn't vibrate on touch
		// mGlowPadView.setVibrateEnabled(false);
		
		// uncomment this to hide targets
		mGlowPadView.setShowTargetsOnIdle(true);
	}

	@Override
	public void onGrabbed(View v, int handle) {

	}

	@Override
	public void onReleased(View v, int handle) {
		mGlowPadView.ping();

	}

	@Override
	public void onTrigger(View v, int target) {
		final int resId = mGlowPadView.getResourceIdForTarget(target);
		switch (resId) {
		case R.drawable.ic_item_camera:
			Toast.makeText(this, "Camera selected", Toast.LENGTH_SHORT).show();
			break;

		case R.drawable.ic_item_google:
			Toast.makeText(this, "Google selected", Toast.LENGTH_SHORT).show();

			break;
		default:
			// Code should never reach here.
		}

	}

	@Override
	public void onGrabbedStateChange(View v, int handle) {

	}

	@Override
	public void onFinishFinalAnimation() {

	}

}
