package com.arta.lib.animation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
/**
 * 移动动画封装类，可以执行动画的移入移出，同时改变View视图的布局参数完成其属性的同步。
 * <p>此动画支持 左->右，右->左，上->下，下->上 四种方向动画。
 * @author 王春龙
 *
 */
public class MyTranslateAnimation {

	private TransType trans_type = TransType.LEFT_TO_RGHT;
	
	private AnimationListener inAnimationListener;
	private AnimationListener outAnimationListener;
	
	private int trans_distance = 0;
	
	private long duration_in = 0;
	private long duration_out = 0;
	
	private Animation anim_in;
	private Animation anim_out;
	
	private View view;
	
	private boolean isIn = true;
	
	private boolean isPlaying = false;
	
	private int parentWidth = 0;
	private int parentHeight = 0;
	
	private MarginLayoutParams marginLayoutParams;
	
	/**
	 * 
	 * @param view 执行动画的View
	 * @param trans_type 动画移动的类型
	 * @param trans_distance 当trans_distance>1时，按当前值移动；当trans_distance<1时，按当前View在父视图之外的百分比显示。
	 * @param duration_out 移出动画延迟时间
	 * @param duration_in 移入动画延迟时间
	 */
	public MyTranslateAnimation(View view , TransType trans_type , float trans_distance , long duration_out , long duration_in){
		this(view , trans_type , false ,trans_distance , duration_out , duration_in);
	}
	/**
	 * 
	 * @param view 执行动画的View
	 * @param trans_type 动画移动的类型
	 * @param initOut 初始化View的位置，initOut == true时，View初始化位置父视图外；initOut == false时，View初始化位置为父视图内部。
	 * @param trans_distance 当trans_distance>1时，按当前值移动；当trans_distance<1时，按当前View在父视图之外的百分比显示。
	 * @param duration_out 移出动画延迟时间
	 * @param duration_in 移入动画延迟时间
	 */
	public MyTranslateAnimation(View view , TransType trans_type ,boolean initOut, float trans_distance , long duration_out , long duration_in){
		this.view = view;
		
		this.trans_type = trans_type;
		
		this.duration_in = duration_in;
		this.duration_out = duration_out;
	
		initArgs();
		
		this.trans_distance = initTransDistance(trans_distance);
		
		if(initOut){
			view.setVisibility(View.INVISIBLE);
			requestLayout();
			view.setVisibility(View.VISIBLE);
		}
	}
	
	private void initArgs(){
		View parent = ((View)view.getParent());
		parentWidth = parent.getWidth();
		parentHeight = parent.getHeight();
		
		marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
	}
	
	private int initTransDistance(float trans_distance){
		if(Math.abs(trans_distance) > 1){
			return this.trans_distance = (int)trans_distance;
		}
		if(trans_distance == 1){
			trans_distance = 0.99f;
		}
		
		switch (trans_type) {
		case LEFT_TO_RGHT  :
			this.trans_distance = (int) (parentWidth - view.getRight() + (((float)view.getWidth()) * trans_distance));
			break;
			
		case RIGHT_TO_LEFT : 
			this.trans_distance = (int) (view.getLeft() + (((float)view.getWidth()) * trans_distance));
			break;
			
		case UP_TO_DOWN : 
			this.trans_distance = (int) (parentHeight - view.getBottom() + (((float)view.getHeight()) * trans_distance));
			break;
			
		case DOWN_TO_UP : 
			this.trans_distance = (int) (view.getTop() + (((float)view.getHeight()) * trans_distance));
			break;
			
		default:
			break;
		}
		
		return this.trans_distance;
	}
	
	public void setInInterlator(Context context , int inInterlatorID){
		getAnimation(true).setInterpolator(context, inInterlatorID);
	}

	public void setOutInterlator(Context context , int outInterlatorID){
		getAnimation(false).setInterpolator(context, outInterlatorID);
	}
	
	public Animation getInAnimation(){
		return getAnimation(true);
	}
	
	public Animation getOutAnimation(){
		return getAnimation(false);
	}
	
	/**
	 * 设定移入动画监听器
	 * @param inAnimationListener
	 */
	public void setInAnimationListener(AnimationListener inAnimationListener){
		this.inAnimationListener = inAnimationListener;
	}
	/**
	 * 设定移出动画监听器
	 * @param outAnimationListener
	 */
	public void setOutAnimationListener(AnimationListener outAnimationListener){
		this.outAnimationListener = outAnimationListener;
	}
	
	public void setView(View view){
		this.view = view;
	}
	
	public boolean isIn(){
		return isIn;
	}
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
	/**
	 * 执行移入动画
	 */
	public void playIn(){
		if(isPlaying || isIn) return;
		
		view.startAnimation(getInAnimation());
	}
	/**
	 * 执行移出动画
	 */
	public void playOut(){
		if(isPlaying || !isIn) return;
		
		view.startAnimation(getOutAnimation());
	}
	
	private Animation getAnimation(boolean isInAnim){
		if(isInAnim){
			if(anim_in == null){
				anim_in = getAnim(true);
				anim_in.setDuration(duration_in);
				anim_in.setFillAfter(true);
				
				anim_in.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						isPlaying = true;
						isIn = false;
						if(inAnimationListener != null){
							inAnimationListener.onAnimationStart(animation);
						}
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						if(inAnimationListener != null){
							inAnimationListener.onAnimationRepeat(animation);
						}
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						view.clearAnimation();
						requestLayout();
						
						if(inAnimationListener != null){
							inAnimationListener.onAnimationEnd(animation);
						}
						
						isPlaying = false;
					}
				});
			}
			
			return anim_in;
		}
		else{
			if(anim_out == null){
				anim_out = getAnim(false);
				anim_out.setDuration(duration_out);
				anim_out.setFillAfter(true);
				
				anim_out.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						isPlaying = true;
						isIn = true;
						if(outAnimationListener != null){
							outAnimationListener.onAnimationStart(animation);
						}
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						if(outAnimationListener != null){
							outAnimationListener.onAnimationRepeat(animation);
						}
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						view.clearAnimation();
						requestLayout();
						
						if(outAnimationListener != null){
							outAnimationListener.onAnimationEnd(animation);
						}
						
						isPlaying = false;
					}
				});
			}
			
			return anim_out;
		}
	}
	
	private Animation getAnim(boolean in){
		Animation anim = null;
		
		switch (trans_type) {
		case LEFT_TO_RGHT  : 
			anim = new TranslateAnimation(0, in ? -trans_distance : trans_distance, 0, 0);
			break;
			
		case RIGHT_TO_LEFT : 
			anim = new TranslateAnimation(0, in ? trans_distance : -trans_distance, 0, 0);
			break;
			
		case UP_TO_DOWN : 
			anim = new TranslateAnimation(0, 0, 0, in ? -trans_distance : trans_distance);
			break;
			
		case DOWN_TO_UP : 
			anim = new TranslateAnimation(0, 0, 0, in ? trans_distance : -trans_distance);
			break;
			
		default:
			break;
		}
		return anim;
	}
	
	private void requestLayout(){
		switch (trans_type) {
		case LEFT_TO_RGHT  : 
			requestLayout_LEFT_TO_RGHT();
			break;
			
		case RIGHT_TO_LEFT : 
			requestLayout_RIGHT_TO_LEFT();
			break;
			
		case UP_TO_DOWN : 
			requestLayout_UP_TO_DOWN();
			break;
			
		case DOWN_TO_UP : 
			requestLayout_DOWN_TO_UP();
			break;
			
		default:
			break;
		}
		
		isIn = !isIn;
	}
	
	private void requestLayout_LEFT_TO_RGHT(){
		int left = 0;
		if(isIn()){
			left = view.getLeft() + trans_distance;
		}
		else{
			left = view.getLeft() - trans_distance;
		}
		
		marginLayoutParams.leftMargin = left;
		marginLayoutParams.rightMargin = parentWidth - (left + view.getWidth());
		
		view.requestLayout();
	}
	
	private void requestLayout_RIGHT_TO_LEFT(){
		int left = 0;
		if(isIn()){
			left = view.getLeft() - trans_distance;
		}
		else{
			left = view.getLeft() + trans_distance;
		}

		marginLayoutParams.leftMargin = left;
		marginLayoutParams.rightMargin = parentWidth - (left + view.getWidth());
		
		view.requestLayout();
	}
	
	private void requestLayout_UP_TO_DOWN(){
		int top = 0;
		if(isIn()){
			top = view.getTop() + trans_distance;
		}
		else{
			top = view.getTop() - trans_distance;
		}
		
		marginLayoutParams.topMargin = top;
		marginLayoutParams.bottomMargin = parentHeight - (top + view.getHeight());
		
		view.requestLayout();
	}
	
	private void requestLayout_DOWN_TO_UP(){
		int top = 0;
		if(isIn()){
			top = view.getTop() - trans_distance;
		}
		else{
			top = view.getTop() + trans_distance;
		}
		
		marginLayoutParams.topMargin = top;
		marginLayoutParams.bottomMargin = parentHeight - (top + view.getHeight());
		
		view.requestLayout();
	}
}
