package com.arta.lib.widget.listener;

import android.content.Context;
import android.view.View;

/**
 * 根据分页子项目中的数据实体生成相应的视图
 * @author 王春龙
 *
 * @param <T> 需要展示的数据实体，如：广告实体类。
 */
public interface OnPageSplitItemManager<T>{
	/**
	 * 生成分页子项视图
	 * @param context
	 * @param entity 生成视图所需的数据实体
	 * @param itemPosition 生成视图所在的位置
	 * @param itemPosition 生成视图所在页的位置
	 * @return
	 */
	public View getPageSplitItemView(Context context, T entity, int itemPosition, int pagePosition);
	
	/**
	 * 更新分页子项视图
	 * @param context 
	 * @param updateItemView 要更新的视图
	 * @param entity 需要更新使用的数据实体
	 * @param itemPosition 更新视图所在位置
	 * @param pagePosition 更新视图所在页位置
	 * @return
	 */
	public View updatePageSplitItemView(Context context, View updateItemView, T entity, int itemPosition, int pagePosition);
}