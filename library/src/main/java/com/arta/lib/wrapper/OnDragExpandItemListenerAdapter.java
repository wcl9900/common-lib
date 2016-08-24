package com.arta.lib.wrapper;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;

import com.arta.lib.wrapper.DragItemViewWrapper.OnDragExpandItemListener;

/**
 * 拖动列表二级列表拖动监听适配器
 * @author 王春龙
 *
 */
public class OnDragExpandItemListenerAdapter implements
		OnDragExpandItemListener {

	@Override
	public void onDragItemStart(AbsListView absListView, View itemView,
			boolean isGroup, int groupPosition, int childPosition,
			MotionEvent event) {
		
	}

	@Override
	public void onDragItemStatic(AbsListView absListView, View itemView,
			boolean isGroup, int groupPosition, int childPosition,
			MotionEvent event) {
		
	}

	@Override
	public void onDragItemEnd(AbsListView absListView, View itemView,
			boolean isGroup, int groupPosition, int childPosition,
			MotionEvent event) {
		
	}

}
