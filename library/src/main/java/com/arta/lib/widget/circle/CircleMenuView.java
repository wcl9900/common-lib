package com.arta.lib.widget.circle;

import java.util.ArrayList;

import com.arta.lib.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class CircleMenuView extends ViewGroup{
	
	
	private static final int PADDING_L_R = 10;
	private static final int PADDING_T_B = 10;
	private static  int DURATION = 500;
	private  final int SMALL_RADIUS;
	
	private boolean isAnimating;
	private int currentIndex;
	private int startX,startY;
	private boolean isShown;
	
	public static Button childOne;
	private TextView childTwo;
	private TextView childThree;
	private TextView childFour;
	private TextView childFive;
	private TextView childSix;
	private TextView childSeven;
	
	private ArrayList<TextView> views = new ArrayList<TextView>();

	public CircleMenuView(Context context) {
		super(context);
		SMALL_RADIUS = (int) context.getResources().getDimension(R.dimen.radius);
		init(context);
	}

	public CircleMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		SMALL_RADIUS = (int) context.getResources().getDimension(R.dimen.radius);
		init(context);
	}

	public CircleMenuView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		SMALL_RADIUS = (int) context.getResources().getDimension(R.dimen.radius);
		init(context);
	}

	private void init(Context context) {
		childOne = new Button(context);
		childTwo = new TextView(context);
		childThree = new TextView(context);
		childFour = new TextView(context);
		childFive = new TextView(context);
		childSix = new TextView(context);
		childSeven = new TextView(context);

		childOne.setBackgroundResource(R.drawable.circle_bg);
		childTwo.setBackgroundResource(R.drawable.circle_bg);
		childThree.setBackgroundResource(R.drawable.circle_bg);
		childFour.setBackgroundResource(R.drawable.circle_bg);
		childFive.setBackgroundResource(R.drawable.circle_bg);
		childSix.setBackgroundResource(R.drawable.circle_bg);
		childSeven.setBackgroundResource(R.drawable.circle_bg);

		childOne.setText("1");
		childTwo.setText("2");
		childThree.setText("3");
		childFour.setText("4");
		childFive.setText("5");
		childSix.setText("6");
		childSeven.setText("7");
		
		childOne.setGravity(Gravity.CENTER);
		childTwo.setGravity(Gravity.CENTER);
		childThree.setGravity(Gravity.CENTER);
		childFour.setGravity(Gravity.CENTER);
		childFive.setGravity(Gravity.CENTER);
		childSix.setGravity(Gravity.CENTER);
		childSeven.setGravity(Gravity.CENTER);
		

		addView(childOne);
		addView(childTwo);
		addView(childThree);
		addView(childFour);
		addView(childFive);
		addView(childSix);
		addView(childSeven);
		
		views.add(childOne);
		views.add(childTwo);
		views.add(childThree);
		views.add(childFour);
		views.add(childFive);
		views.add(childSix);
		views.add(childSeven);
		
		childOne.setVisibility(View.INVISIBLE);
		childTwo.setVisibility(View.INVISIBLE);
		childThree.setVisibility(View.INVISIBLE);
		childFour.setVisibility(View.INVISIBLE);
		childFive.setVisibility(View.INVISIBLE);
		childSix.setVisibility(View.INVISIBLE);
		childSeven.setVisibility(View.INVISIBLE);
		
	}
		
	public void out(){
		
		if(isAnimating)
			return;
		
		isAnimating = true;
		
		int length = views.size();
		for (int i = 0; i < length; i++) {
			final View v = views.get(i);
			TranslateAnimation animation = new TranslateAnimation(startX - v.getLeft(), 0, startY - v.getTop(), 0);
			animation.setInterpolator(new OvershootInterpolator(1.5f));
			animation.setDuration(DURATION);
			v.startAnimation(animation);
			animation.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					currentIndex++;
					v.setVisibility(View.VISIBLE);
					if(currentIndex == views.size()){
						isAnimating = false;
						currentIndex = 0;
						isShown = true;
					}
				}
			});
		}
		
	}
	
	public void in(){
		
		if(isAnimating){
			return;
		}
		
		isAnimating = true;
		
		int length = views.size();
		for (int i = 0; i < length; i++) {
			final View v = views.get(i);
			TranslateAnimation animation = new TranslateAnimation( 0,startX - v.getLeft() - v.getWidth()/2,  0,startY - v.getTop() + v.getHeight()/2);
			animation.setInterpolator(new OvershootInterpolator(1.2f));
			if (i >= length/2 ) {
				animation.setDuration(DURATION-100);
			} else {
				animation.setDuration(DURATION);
			}
			v.startAnimation(animation);
			animation.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					currentIndex++;
					v.setVisibility(View.INVISIBLE);
					if(currentIndex == views.size()){
						isAnimating = false;
						currentIndex = 0;
						isShown = false;
					}
				}
			});
		}
	}
	
	public boolean isAnimating(){
		return isAnimating;
	}
	
	public boolean isShown(){
		return isShown;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int childCount = getChildCount();
		if (childCount == 0)
			return;

		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			if (child != null) {
				child.measure(
                        MeasureSpec.makeMeasureSpec(SMALL_RADIUS,
                                MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec( SMALL_RADIUS,
                                MeasureSpec.EXACTLY));
			}
		}

		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		
		//����ؼ��ĸ߶�h
		int r = getChildAt(0).getMeasuredWidth();
		int R = screenWidth/2-r;
		int h = R + r*2 + PADDING_T_B*2;
		setMeasuredDimension(screenWidth, h);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childCount = getChildCount();
		if (childCount == 0||getChildAt(0)==null)
			return;
		int width = getMeasuredWidth() - PADDING_L_R*2;
		int height = getMeasuredHeight() - PADDING_T_B*2;
		
		View childView = getChildAt(0);
		//���Բ�뾶
		int radiusBig = (width-childView.getMeasuredWidth())/2;
		//СԲ�뾶
		int radiusSmall = childView.getMeasuredWidth()/2;
		
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			if (child == null)
				break;
			
			if(i==0){
				int child_x = radiusSmall + PADDING_L_R;
				int child_y = height - radiusSmall;
				layout(child, child_x, child_y, radiusSmall);
			}else if(i==1){
				int child_x = (int) (width/2 - radiusBig*Math.cos(Math.PI/6)) + PADDING_L_R;
				int child_y = (int) (height-radiusSmall-radiusBig*Math.sin(Math.PI/6));
				
				layout(child, child_x, child_y, radiusSmall);
			}else if(i==2){
				int child_x = (int) (width/2 - radiusBig*Math.cos(Math.PI/3)) + PADDING_L_R;
				int child_y = (int) (height-radiusSmall-radiusBig*Math.sin(Math.PI/3));
				
				layout(child, child_x, child_y, radiusSmall);
			}else if(i==3){
				int child_x = width/2 + PADDING_L_R;
				int child_y = height - radiusSmall - radiusBig;
				
				layout(child, child_x, child_y, radiusSmall);
			}else if(i==4){
				
				int child_x = (int) (width/2+radiusBig*Math.cos(Math.PI/3)) + PADDING_L_R;
				int child_y = (int) (height - radiusSmall - radiusBig*Math.sin(Math.PI/3));
				
				layout(child, child_x, child_y, radiusSmall);
				
			}else if(i==5){
				
				int child_x = (int) (width/2+radiusBig*Math.cos(Math.PI/6)) + PADDING_L_R;
				int child_y = (int) (height - radiusSmall - radiusBig*Math.sin(Math.PI/6));
				
				layout(child, child_x, child_y, radiusSmall);
				
			}else if(i==6){
				
				int child_x = width - radiusSmall + PADDING_L_R;
				int child_y = height - radiusSmall;
				layout(child, child_x, child_y, radiusSmall);
			}
				
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		startX = getWidth()/2;
		startY = getHeight() - childOne.getWidth()/2 - PADDING_T_B;
	}
	
	
	private void layout(View view, int child_x, int child_y,int radiusSmall) {
		int child_l = getChildOffset_l(child_x, child_y, radiusSmall);
		int child_t = getChildOffset_t(child_x, child_y, radiusSmall);
		int child_r = getChildOffset_r(child_x, child_y, radiusSmall);
		int child_b = getChildOffset_b(child_x, child_y, radiusSmall);
		view.layout(child_l, child_t, child_r, child_b);
	}
	
	private int getChildOffset_l(int child_x,int child_y,int radius){
		return child_x - radius;
	}
	
	private int getChildOffset_t(int child_x,int child_y,int radius){
		return child_y - radius;
	}
	private int getChildOffset_r(int child_x,int child_y,int radius){
		return child_x + radius;
	}
	private int getChildOffset_b(int child_x,int child_y,int radius){
		return child_y + radius;
	}

	
	public boolean dispatchTouchEvent(MotionEvent event) {

		  switch (event.getAction()) {

		  case MotionEvent.ACTION_DOWN:

		   System.out.println("aaaaaaaaaaaaaaa");
		   break;

		  case MotionEvent.ACTION_MOVE:
		   System.out.println("bbbbbbbbbbbbbbbbb");
		   break;

		  case MotionEvent.ACTION_UP:
		   System.out.println("cccccccccccccccccc");
		   break;
		  }

		  return true;

		 }
	
	

}
