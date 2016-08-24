package com.arta.lib.widget.listener;

import android.view.View;

/**
 * 分页中子项目视图点击监听器
 * @author 王春龙
 *
 * @param <T> 数据实体类型
 */
public interface OnPageSplitItemClickListener<T>{
	/**
	 * 分页子视图点击
	 * @param view 被点击的子视图
	 * @param entity 当前视图所负载的数据实体
	 * @param position 当前视图所在全体子视图位置
	 * @param pagePosition 当前视图所在页视位置
	 */
	public void onPageSplitItemClick(View view, T entity, int position, int pagePosition);
}