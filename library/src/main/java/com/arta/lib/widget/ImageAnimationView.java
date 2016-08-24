package com.arta.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.arta.lib.R;

/**
 * 通过设定View视图的显隐来显示动画
 * @author 王春龙
 *
 */
public class ImageAnimationView extends ImageView {

	public enum AnimType{
		ZOOM_IN,
		ZOOM_OUT,
		LEFT_TO_RIGHT,
		RIGHT_TO_LEFT,
		UP_TO_DOWN,
		DOWN_TO_UP
	}
	
	private ScaleAnimation visibleAnim;
	private ScaleAnimation goneAnim;
	
	private AnimType animType_visible;
	private AnimType animType_gone;
	
	private int duration_visible = 500;
	private int duration_gone = 500;
	
	private Interpolator interpolator_visible;
	private Interpolator interpolator_gone;
	
	private AnimationListener visibleAnimListener;
	private AnimationListener goneAnimListener;
	
	private boolean isPlaying = false;

	private boolean hasShow = false;
	public ImageAnimationView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(attrs);
		initArgs();
	}

	public ImageAnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(attrs);
		initArgs();
	}

	public ImageAnimationView(Context context , AnimType animType_visible , AnimType animType_gone , int duration_visible,int duration_gone) {
		super(context);
		this.animType_visible = animType_visible;
		this.animType_gone = animType_gone;
		
		this.duration_visible = duration_visible;
		this.duration_gone = duration_gone;
		
		initArgs();
	}
	
	private void initAttrs(AttributeSet attrs){
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ImageAnimationView);
		
		duration_visible = ta.getInt(R.styleable.ImageAnimationView_duration_visible, duration_visible);
		duration_gone = ta.getInt(R.styleable.ImageAnimationView_duration_gone, duration_gone);
		
		int interpolator_visible_id = ta.getResourceId(R.styleable.ImageAnimationView_interpolator_visible, android.R.interpolator.linear);
		int interpolator_gone_id = ta.getResourceId(R.styleable.ImageAnimationView_interpolator_gone, android.R.interpolator.linear);
		interpolator_visible = AnimationUtils.loadInterpolator(getContext(), interpolator_visible_id);
		interpolator_gone = AnimationUtils.loadInterpolator(getContext(), interpolator_gone_id);

		animType_visible = getAnimType(ta.getInt(R.styleable.ImageAnimationView_animType_visible, 0));
		animType_gone = getAnimType(ta.getInt(R.styleable.ImageAnimationView_animType_gone, 1)); 
		
		ta.recycle();
	}
	
	private AnimType getAnimType(int value){
		switch (value) {
		case 0:
			return AnimType.ZOOM_IN;
		case 1:
			return AnimType.ZOOM_OUT;
		case 2:
			return AnimType.LEFT_TO_RIGHT;
		case 3:
			return AnimType.RIGHT_TO_LEFT;
		case 4:
			return AnimType.UP_TO_DOWN;
		case 5:
			return AnimType.DOWN_TO_UP;
		default:
			return AnimType.ZOOM_OUT;
		}
	}
	
	private void initArgs(){
		visibleAnim = initAnim(true);
		goneAnim = initAnim(false);
		
		initAnimListener();
	}
	
	private void initAnimListener(){
		visibleAnim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				isPlaying = true;
				ImageAnimationView.super.setVisibility(View.VISIBLE);
				ImageAnimationView.this.setEnabled(true);
				
				if(visibleAnimListener != null){
					visibleAnimListener.onAnimationStart(animation);
				}
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				if(visibleAnimListener != null){
					visibleAnimListener.onAnimationRepeat(animation);
				}	
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				ImageAnimationView.this.clearAnimation();
				isPlaying = false;
				
				if(visibleAnimListener != null){
					visibleAnimListener.onAnimationEnd(animation);
				}
			}
		});
		
		goneAnim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				isPlaying = true;
				ImageAnimationView.super.setVisibility(View.VISIBLE);
				ImageAnimationView.this.setEnabled(true);
				
				if(goneAnimListener != null){
					goneAnimListener.onAnimationStart(animation);
				}
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				if(goneAnimListener != null){
					goneAnimListener.onAnimationRepeat(animation);
				}
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				ImageAnimationView.super.setVisibility(View.INVISIBLE);
				ImageAnimationView.this.setEnabled(false);
				ImageAnimationView.this.clearAnimation();
				isPlaying = false;
				
				if(goneAnimListener != null){
					goneAnimListener.onAnimationEnd(animation);
				}
			}
		});
	}
	
	private ScaleAnimation initAnim(boolean visible){
		ScaleAnimation anim = null;
		
		switch (visible ? animType_visible : animType_gone) {
		
		case ZOOM_IN:
			anim = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF , 0.5f , Animation.RELATIVE_TO_SELF , 0.5f);
			break;
		
		case ZOOM_OUT:
			anim =  new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF , 0.5f , Animation.RELATIVE_TO_SELF , 0.5f);
			break;
		
		case LEFT_TO_RIGHT :
			anim = new ScaleAnimation(0, 1, 1, 1, 0, 0.5f);
			break;
		
		case RIGHT_TO_LEFT:
			anim = new ScaleAnimation(1, 0, 1, 1 , 1 ,0.5f);
			break;
		
		case UP_TO_DOWN:
			anim = new ScaleAnimation(1, 1, 0, 1, 0.5f, 0);
			break;
		
		case DOWN_TO_UP:
			anim = new ScaleAnimation(1, 1, 1, 0, 0.5f, 1);
			break;
		default:
			break;
		}

		Interpolator interpolator = visible ? interpolator_visible : interpolator_gone;
		
		anim.setDuration(visible ? duration_visible : duration_gone);
		anim.setFillAfter(true);
		if(interpolator != null){
			anim.setInterpolator(interpolator);
		}
		
		return anim;
	}
	
	public void setVisibleAnimListener(AnimationListener visibleAnimListener){
		this.visibleAnimListener = visibleAnimListener;
	}
	public void setGoneAnimListener(AnimationListener goneAnimListener){
		this.goneAnimListener = goneAnimListener;
	}
	
	public Animation getVisibleAnimation(){
		return this.visibleAnim;
	}
	
	public Animation getGoneAnimation(){
		return this.goneAnim;
	}
	/**
	 * 播放显示动画
	 */
	public void playInAnim(){
		this.startAnimation(visibleAnim);
	}
	
	/**
	 * 播放退出动画
	 */
	public void playOutAnim(){
		this.startAnimation(goneAnim);
	}
	
	@Override
	public void setVisibility(int visibility) {
		if(!hasShow){
			hasShow = !hasShow;
			super.setVisibility(visibility);
		}
		
		if(isPlaying) return;
		
		if(visibility == View.VISIBLE && this.getVisibility() != View.VISIBLE){
			this.startAnimation(visibleAnim);
			((View)this.getParent()).postInvalidate();
		}
		else if(visibility != View.VISIBLE && this.getVisibility() == View.VISIBLE){
			this.startAnimation(goneAnim);
			((View)this.getParent()).postInvalidate();
		}
	}
}
