package com.arta.lib.widget.tabfoot;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
/**
 * tabBar 按钮，基于Button实现，过滤按钮被按压后的状态
 * @author 王春龙
 *
 */
public class TabFootButton extends ImageView {

	public TabFootButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TabFootButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TabFootButton(Context context) {
		super(context);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		if(this.isSelected()) return true;
//		
//		try{
//			Drawable drawable = this.getBackground();
//			if(drawable == null){
//				return super.onTouchEvent(event);
//			}
//			
//			BitmapDrawable bitmapDrawbale = null;
//			if(drawable instanceof BitmapDrawable){
//				bitmapDrawbale = (BitmapDrawable)drawable;
//			}
//			else if(drawable instanceof StateListDrawable){
//				bitmapDrawbale = (BitmapDrawable) ((StateListDrawable)drawable).getCurrent();
//			}
//
//			if(bitmapDrawbale != null && bitmapDrawbale.getBitmap().getPixel((int)event.getX(), (int)event.getY()) == 0) {
//				return false;
//			}
//			
//		}catch(Exception e){
//			return super.onTouchEvent(event);
//		}
//		
		return super.onTouchEvent(event);
	}
	/**
	 * 选中按钮
	 * @param select
	 */
	public void setTabToggle(boolean select){
		this.setSelected(select);
	}
	/**
	 * 按钮是否被选中
	 * @return
	 */
	public boolean isTabToggled(){
		return this.isSelected();
	}
}
