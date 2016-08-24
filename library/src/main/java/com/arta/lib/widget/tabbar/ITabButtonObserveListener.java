package com.arta.lib.widget.tabbar;

/**
 * 选项卡Tab按钮状态转变监听器
 * @author 王春龙
 *
 */
public interface ITabButtonObserveListener {
	
	public void stateChange(TabButton changedButton , int index);
	
	public void addObserveElement(TabButton tabButton);
}
