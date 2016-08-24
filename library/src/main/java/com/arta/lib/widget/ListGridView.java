package com.arta.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.arta.lib.adapter.ListGridViewAdapter;
import com.arta.lib.widget.listener.OnPageSplitItemClickListener;

/**
 * item子项为GridView的列表视图,需要使用{@link ListGridViewAdapter}适配器实现该效果，使用普通适配器为通常样式列表。
 * @author 王春龙
 *
 */
public class ListGridView extends ListView {

	private OnPageSplitItemClickListener<?> onPageSplitItemClickListener;
	
	public ListGridView(Context context) {
		super(context);
	}

	public ListGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setOnSplitPageItemClickListener(OnPageSplitItemClickListener<?> onPageSplitItemClickListener){
		if(this.getAdapter() != null && this.getAdapter() instanceof ListGridViewAdapter){
			((ListGridViewAdapter)this.getAdapter()).setOnSplitPageItemClickListener(onPageSplitItemClickListener);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setAdapter(ListAdapter adapter) {
		if(adapter != null && adapter instanceof ListGridViewAdapter && onPageSplitItemClickListener != null){
		((ListGridViewAdapter)adapter).setOnSplitPageItemClickListener(onPageSplitItemClickListener);
		}
		super.setAdapter(adapter);
	}
}
