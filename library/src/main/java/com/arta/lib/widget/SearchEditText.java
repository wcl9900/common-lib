package com.arta.lib.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class SearchEditText extends EditText implements  
OnFocusChangeListener, TextWatcher { 
	/**
	 * 小图标点击事件监听器
	 * @author 王春龙
	 *
	 */
	public interface OnIconClickListener{
		public void OnIconClick(EditText editText, String text);
	} 
	
	private Drawable mClearDrawable; 
	private boolean hasFoucs;
	
	private OnIconClickListener onIconClickListener;
	
	public SearchEditText(Context context) { 
		this(context, null);
	} 

	public SearchEditText(Context context, AttributeSet attrs) { 
		this(context, attrs, android.R.attr.editTextStyle);

	} 

	public SearchEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public void setOnIconClickListener(OnIconClickListener onIconClickListener){
		this.onIconClickListener = onIconClickListener;
	}
	/**
	 * event.getX() 获取相对应自身左上角的X坐标
	 * event.getY() 获取相对应自身左上角的Y坐标
	 * getWidth() 获取控件的宽度
	 * getTotalPaddingRight() 获取删除图标左边缘到控件右边缘的距离
	 * getPaddingRight() 获取删除图标右边缘到控件右边缘的距离
	 * getWidth() - getTotalPaddingRight() 计算删除图标左边缘到控件左边缘的距离
	 * getWidth() - getPaddingRight() 计算删除图标右边缘到控件左边缘的距离
	 */

	@Override 
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {
				//判断触摸点是否在水平范围内
				boolean isInnerWidth = event.getX() > (getWidth() - getTotalPaddingRight())
						&& (event.getX() < ((getWidth() - getPaddingRight()-10)));
				//获取删除图标的边界，返回一个Rect对象
				Rect rect = getCompoundDrawables()[2].getBounds();
				//获取删除图标的高度
				int height = rect.height();
				int y = (int) event.getY();
				//计算图标底部到控件底部的距离
				int distance = (getHeight() - height) /2;
				//判断触摸点是否在竖直范围内(可能会有点误差)
				//触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
				boolean isInnerHeight = (y > distance) && (y < (distance + height));

				if(isInnerWidth && isInnerHeight) {
					InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);  
					imm.hideSoftInputFromWindow(getWindowToken(), 0);
					if(onIconClickListener != null){
						onIconClickListener.OnIconClick(this, this.getEditableText().toString());
					}
					return true;
				}
			}
		}
		return super.onTouchEvent(event);
	}

	@Override 
	public void onFocusChange(View v, boolean hasFocus) { 
		this.hasFoucs = hasFocus;
		if (hasFocus) { 
			setClearIconVisible(getText().length() > 0); 
		} else { 
			setClearIconVisible(false); 
		} 
	} 

	protected void setClearIconVisible(boolean visible) { 
		Drawable right = visible ? mClearDrawable : null; 
		setCompoundDrawables(getCompoundDrawables()[0], 
				getCompoundDrawables()[1], right, getCompoundDrawables()[3]); 
	} 

	@Override 
	public void onTextChanged(CharSequence s, int start, int count, 
			int after) { 
		if(hasFoucs){
			setClearIconVisible(s.length() > 0);
		}
	} 

	@Override 
	public void beforeTextChanged(CharSequence s, int start, int count, 
			int after) { 

	} 

	@Override 
	public void afterTextChanged(Editable s) { 

	} 

}
