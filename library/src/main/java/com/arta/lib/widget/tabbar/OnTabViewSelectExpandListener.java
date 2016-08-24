package com.arta.lib.widget.tabbar;

import android.view.View;

/**
 * TabBarView 扩展选择监听，可支持“选择前”，“选择后”监听
 * @author 王春龙
 *
 */
public interface OnTabViewSelectExpandListener extends OnTabViewSelectListener {
	/**
	 * TabBarView选项卡选择前监听器
	 * @param tabView
	 * @param index
	 */
	public boolean onTabViewSelectedBeforeListener(TabBarView tabBarView, View tabView, int index);
}
