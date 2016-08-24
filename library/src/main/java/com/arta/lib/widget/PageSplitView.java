package com.arta.lib.widget;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.arta.lib.adapter.PageSplitAdapter;
import com.arta.lib.widget.listener.OnPageSplitItemClickListener;

/**
 * 带有分页功能的视图，需要使用{@link PageSplitAdapter} 适配器实现该功能。
 * @author 王春龙
 *
 */
public class PageSplitView extends ViewPager {
	
	private OnPageSplitItemClickListener<?> onPageSplitItemClickListener;
	
	public PageSplitView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public PageSplitView(Context context) {
		super(context);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setOnSplitPageItemClickListener(OnPageSplitItemClickListener<?> onPageSplitItemClickListener){
		if(this.getAdapter() != null && this.getAdapter() instanceof PageSplitAdapter){
			((PageSplitAdapter)this.getAdapter()).setOnSplitPageItemClickListener(onPageSplitItemClickListener);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setAdapter(PagerAdapter adapter) {
		if(adapter != null && adapter instanceof PageSplitAdapter && onPageSplitItemClickListener != null){
			((PageSplitAdapter)adapter).setOnSplitPageItemClickListener(onPageSplitItemClickListener);
		}
		super.setAdapter(adapter);
	}
}
