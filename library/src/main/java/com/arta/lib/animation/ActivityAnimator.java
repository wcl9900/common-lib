package com.arta.lib.animation;


import android.app.Activity;

import com.arta.lib.R;

public class ActivityAnimator
{
	public void flipHorizontalAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.activitytransition_flip_horizontal_in, R.anim.activitytransition_flip_horizontal_out);
	}
	
	public void flipVerticalAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.activitytransition_flip_vertical_in, R.anim.activitytransition_flip_vertical_out);
	}
	
	public void fadeAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.activitytransition_fade_in, R.anim.activitytransition_fade_out);
	}
	
	public void disappearTopLeftAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.activitytransition_disappear_top_left_in, R.anim.activitytransition_disappear_top_left_out);
	}
	
	public void appearTopLeftAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.activitytransition_appear_top_left_in, R.anim.activitytransition_appear_top_left_out);
	}
	
	public void disappearBottomRightAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.activitytransition_disappear_bottom_right_in, R.anim.activitytransition_disappear_bottom_right_out);
	}
	
	public void appearBottomRightAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.activitytransition_appear_bottom_right_in, R.anim.activitytransition_appear_bottom_right_out);
	}
	
	public void unzoomAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.activitytransition_unzoom_in, R.anim.activitytransition_unzoom_out);
	}
}
