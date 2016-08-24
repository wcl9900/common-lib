package com.arta.lib.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.arta.lib.widget.listener.OnPageSplitItemManager;

/**
 * 分页GridView适配器
 * @author 王春龙
 *
 */
public class PageGridViewAdapter<T> extends BaseEntityViewAdapter<T>{

	private int currentPage;

	private int itemCounts;

	private OnPageSplitItemManager<T> onPageSplitItemManager;

	protected PageGridViewAdapter(Context context, List<T> entityList, int currentPage, int itemCounts, 
			OnPageSplitItemManager<T> onPageSplitItemManager){
		super(context, entityList, null);
		this.currentPage = currentPage;
		this.itemCounts = itemCounts;
		this.onPageSplitItemManager = onPageSplitItemManager;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}
	/**
	 * 设定元素个数
	 * @param itemCount
	 */
	public void setItemCount(int itemCount){
		this.itemCounts = itemCount;
	}
	
	@Override
	public int getCount() {
		int otherCount = entityList.size() - currentPage * itemCounts;
		if(otherCount > itemCounts){
			return itemCounts;
		}
		else {
			return otherCount;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int itemPosition = (currentPage * itemCounts) + position;
		ItemViewHolder holder = null;

		if(convertView == null){
			convertView = onPageSplitItemManager.getPageSplitItemView(context, entityList.get(itemPosition), itemPosition, currentPage);
			holder = new ItemViewHolder();
			convertView.setTag(AdapterConstant.TAG_KEY, holder);
		}

		holder = (ItemViewHolder) convertView.getTag(AdapterConstant.TAG_KEY);
		holder.entity = entityList.get(itemPosition);
		holder.view = convertView;
		holder.itemPosition = itemPosition;
		holder.pagePosition = currentPage;
		
		onPageSplitItemManager.updatePageSplitItemView(context, convertView, entityList.get(itemPosition), itemPosition, currentPage);

		return convertView;
	}

	public class ItemViewHolder{
		public View view;
		public T entity;
		public int itemPosition;
		public int pagePosition;
	}
}
