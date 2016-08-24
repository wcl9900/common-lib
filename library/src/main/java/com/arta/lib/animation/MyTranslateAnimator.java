package com.arta.lib.animation;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;
/**
 * 属性视图平移动画
 * @author 王春龙
 *
 */
public class MyTranslateAnimator {
	
	private View view;
	
	private TransType transType = TransType.LEFT_TO_RGHT;
	
	private AnimatorListener animInListener;
	private AnimatorListener animOutListener;
	
	private Animator animatorIn;
	private Animator animatorOut;

	private Interpolator interpolatorIn;
	private Interpolator interpolatorOut;
	
	private int durationIn;
	private int durationOut;
	
	private float transDistance;
	
	private boolean initOut = false;
	
	private boolean isIn = true;
	
	public MyTranslateAnimator(View view, TransType transType, boolean initOut, float transDistance, int durationIn, int durationOut) {
		this.view = view;
		this.initOut = initOut;
		this.transDistance = transDistance;
		this.durationIn = durationIn;
		this.durationOut = durationOut;
		this.transType = transType;
		
		init();
	}
	
	private void init(){
		if(initOut){
			view.setVisibility(View.INVISIBLE);
			isIn = false;
		}
		else{
			view.setVisibility(View.VISIBLE);
			isIn = true;
		}
	}
	
	public void setAnimInListener(AnimatorListener animInListener){
		this.animInListener = animInListener;
	}
	
	public void setAnimOutListener(AnimatorListener animOutListener){
		this.animOutListener = animOutListener;
	}
	
	public void setInInterpolator(Interpolator interpolatorIn){
		this.interpolatorIn = interpolatorIn;
	}
	public void setInInterpolator(Context context, int interpolatorInRes){
		this.interpolatorIn = AnimationUtils.loadInterpolator(context, interpolatorInRes);
	}
	public void setOutInterpolator(Interpolator interpolatorOut){
		this.interpolatorOut = interpolatorOut;
	}
	public void setOutInterpolator(Context context, int interpolatorOutRes){
		this.interpolatorOut = AnimationUtils.loadInterpolator(context, interpolatorOutRes);
	}
	
	public boolean isIn(){
		return isIn;
	}
	
	public void toggle(){
		if(isIn()){
			transOut();
		}
		else{
			transIn();
		}
	}
	
	public void transIn(){
		if(isPlaying() || isIn) return;
		if(view.getVisibility() != View.VISIBLE){
			transVISIBLE();
		}
		getAnimator(true).start();
	}
	
	public void transOut(){
		if(isPlaying() || !isIn) return;
		getAnimator(false).start();
	}
	
	private void transVISIBLE(){
		view.setVisibility(View.VISIBLE);
		getTransDistance();
		if(transType == TransType.LEFT_TO_RGHT){
			view.setTranslationX(view.getTranslationX() - transDistance);
		}
		else if(transType == TransType.RIGHT_TO_LEFT){
			view.setTranslationX(view.getTranslationX() + transDistance);
		}
		else if(transType == TransType.DOWN_TO_UP){
			view.setTranslationY(view.getTranslationY() + transDistance);
		}
		else if(transType == TransType.UP_TO_DOWN){
			view.setTranslationY(view.getTranslationY() - transDistance);
		}
		view.requestLayout();
	}
	
	private boolean isPlaying(){
		boolean isPlaying = false;
		if(animatorIn != null){
			isPlaying = isPlaying || animatorIn.isRunning();
		}
		if(animatorOut != null){
			isPlaying = isPlaying || animatorOut.isRunning();
		}
		return isPlaying;
	}
	
	private Animator getAnimator(boolean in){
		String transDirection = "";
		float toVaue = 0;
		if(transType == TransType.LEFT_TO_RGHT || transType == TransType.RIGHT_TO_LEFT){
			transDirection = "translationX";
			if(transType == TransType.LEFT_TO_RGHT){
				toVaue = view.getTranslationX() + getTransDistance() * (in ? 1 : -1);
			}
			else{
				toVaue = view.getTranslationX() + getTransDistance() * (in ? -1 : 1);
			}
		}
		else {
			transDirection = "translationY";
			if(transType == TransType.UP_TO_DOWN){
				toVaue = view.getTranslationY() + getTransDistance() * (in ? 1 : -1);
			}
			else{
				toVaue = view.getTranslationY() + getTransDistance() * (in ? -1 : 1);
			}
		}
		
		Animator animator = null;
		if(in){
			animatorIn = ObjectAnimator.ofFloat(view, transDirection, toVaue);
			animatorIn.setDuration(durationIn);
			if(interpolatorIn != null){
				animatorIn.setInterpolator(interpolatorIn);
			}
			if(animInListener != null){
				animatorIn.addListener(animInListener);
			}
			animatorIn.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					isIn = true;
				}
			});
			animator = animatorIn;
		}
		else {
			animatorOut = ObjectAnimator.ofFloat(view, transDirection, toVaue);
			animatorOut.setDuration(durationOut);
			if(interpolatorOut != null){
				animatorOut.setInterpolator(interpolatorOut);
			}
			if(animOutListener != null){
				animatorOut.addListener(animOutListener);
			}
			animatorOut.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					isIn = false;
				}
			});
			animator = animatorOut;
		}
		
		return animator;
	}
	
	private float getTransDistance(){
		if((transDistance = Math.abs(transDistance)) > 1){
			return transDistance;
		}
		
		if(transType == TransType.LEFT_TO_RGHT){
			transDistance = view.getLeft() + (float)view.getWidth() * transDistance;
		}
		else if(transType == TransType.RIGHT_TO_LEFT){
			transDistance = ((View)view.getParent()).getWidth() - view.getRight() + (float)view.getWidth() * transDistance;
		}
		else if(transType == TransType.UP_TO_DOWN){
			transDistance = view.getTop() + (float)view.getHeight() * transDistance;
		}
		else if(transType == TransType.DOWN_TO_UP){
			transDistance = ((View)view.getParent()).getHeight() - view.getBottom() + (float)view.getHeight() * transDistance;
		}
		
		return transDistance;
	}
}
