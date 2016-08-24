package com.arta.lib.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayer;

/**
 * Unity视图
 * @author 王春龙
 *
 */
public class UnityView extends FrameLayout {

	private UnityPlayer unityPlayer;
	
	public UnityView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public UnityView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public UnityView(Context context,boolean show) {
		super(context);
		if(show){
			init();
		}
	}

	private void init(){
		unityPlayer = new DefineUnityPlayer((ContextWrapper) getContext());

		FrameLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		lp.gravity = Gravity.CENTER;
		
		this.addView(unityPlayer, lp);
	}

	/**
	 * 更新unity视图
	 */
	public void update(){
		clear();
		init();
	}
	
	/**
	 * 清除unity视图
	 */
	public void clear(){
		this.removeAllViews();
		unityPlayer = null;
	}

	public void quit ()
	{
		if(unityPlayer != null)
		unityPlayer.quit();
	}

	public void pause()
	{
		if(unityPlayer != null)
		unityPlayer.pause();
	}
	
	public void resume()
	{
		if(unityPlayer != null)
		unityPlayer.resume();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		if(unityPlayer != null)
		unityPlayer.configurationChanged(newConfig);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		if(unityPlayer != null)
		unityPlayer.windowFocusChanged(hasFocus);
	}

	// For some reason the multiple keyevent types is not supported by the ndk.
	// Force event injection by overriding dispatchKeyEvent().
	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		if (unityPlayer != null && event.getAction() == KeyEvent.ACTION_MULTIPLE)
			return unityPlayer.injectEvent(event);
		return super.dispatchKeyEvent(event);
	}

	// Pass any events not handled by (unfocused) views straight to UnityPlayer
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)     {
		if(unityPlayer != null)
		return unityPlayer.injectEvent(event);
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)   {
		if(unityPlayer != null)
		return unityPlayer.injectEvent(event);
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		if(unityPlayer != null)
		return unityPlayer.injectEvent(event);
		return super.onTouchEvent(event);
	}
	@Override
	public boolean onGenericMotionEvent(MotionEvent event)  {
		if(unityPlayer != null)
			return unityPlayer.injectEvent(event);
		return super.onGenericMotionEvent(event);
	}
}
