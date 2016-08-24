package com.arta.lib.widget.listener;

import android.view.View;

/**
 * 树视图item选项状态装饰器
 * @author 王春龙
 *
 */
public interface OnTreeItemViewDecorateListener {
	/**
	 * group Item选中状态装饰
	 * @param view
	 */
	public void OnGroupItemViewSelected(View view);
	/**
	 * group Item默认状态装饰
	 * @param view
	 */
	public void OnGroupItemViewDefault(View view);
	/**
	 * child Item选中状态装饰
	 * @param view
	 */
	public void OnChildItemViewSelected(View view);
	/**
	 * child Item默认状态装饰
	 * @param view
	 */
	public void OnChildItemViewDefault(View view);
}
