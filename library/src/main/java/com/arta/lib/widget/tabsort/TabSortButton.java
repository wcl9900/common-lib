package com.arta.lib.widget.tabsort;

import com.arta.lib.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 排序控件按钮
 * @author 王春龙
 *
 */
public class TabSortButton extends LinearLayout{
	
	private View clcikView;
	
	private ImageView ivSortFlag;
	
	private boolean initFinish = false;
	
	private TabSortType sortType = TabSortType.DEFAULT_NO;
	
	public TabSortButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TabSortButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TabSortButton(Context context) {
		super(context);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(!initFinish){
			initFinish = !initFinish;
			clcikView = findViewById(R.id.tabsort_handle);
			ivSortFlag = (ImageView) findViewById(R.id.tabsort_flag);
			clcikView.setTag(TabSortConstant.TAB_SORT_BUTTON, this);
		}
	}
	
	@Override
	public void setOnClickListener(OnClickListener l) {
		clcikView.setOnClickListener(l);
	}

	public void setFlagDrawable(Drawable drawable, TabSortType sortType){
		ivSortFlag.setImageDrawable(drawable);
		this.sortType = sortType;
	}
	
	/**
	 * 获取排序类型
	 * @return
	 */
	public TabSortType getSortType(){
		return sortType;
	}
}
