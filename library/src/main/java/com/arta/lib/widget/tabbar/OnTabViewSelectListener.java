package com.arta.lib.widget.tabbar;

import android.view.View;

/**
 * TabBarView选项卡选择监听器
 * @author 王春龙
 *
 */
public interface OnTabViewSelectListener{
	/**
	 * TabBarView选项卡选择监听器
	 * @param tabView
	 * @param index
	 */
	public void onTabViewSelectedListener(TabBarView tabBarView, View tabView, int index);
}
