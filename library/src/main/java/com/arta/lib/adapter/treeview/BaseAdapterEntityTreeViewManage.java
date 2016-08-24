package com.arta.lib.adapter.treeview;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

/**
 * 伸缩树列表视图管理器
 * @author 王春龙
 *
 * @param <T1> 组视图数据实体
 * @param <T2> 子视图数据实体
 */
public interface BaseAdapterEntityTreeViewManage<T1, T2> {
	/**
	 * 获取组视图
	 * @param context
	 * @param expandListView
	 * @param isExpanded
	 * @param groupEntity
	 * @param groupPosition
	 * @return
	 */
	public View getGroupView(Context context, ExpandableListView expandListView,boolean isExpanded, T1 groupEntity, int groupPosition);
	/**
	 * 更新组视图
	 * @param context
	 * @param expandListView
	 * @param isExpanded
	 * @param groupEntity
	 * @param groupPosition
	 */
	public void updateGroupView(Context context, ExpandableListView expandListView, boolean isExpanded,  View updateGroup,T1 groupEntity, int groupPosition);
	
	/**
	 * 获取子视图
	 * @param context
	 * @param expandListView
	 * @param childEntity
	 * @param groupPosition
	 * @param childPosition
	 * @return
	 */
	public View getChildView(Context context, ExpandableListView expandListView, T2 childEntity, int groupPosition, int childPosition);
	/**
	 * 更新子视图
	 * @param context
	 * @param expandListView
	 * @param childEntity
	 * @param groupPosition
	 * @param childPosition
	 */
	public void updateChildView(Context context, ExpandableListView expandListView, View updateChild, T2 childEntity, int groupPosition, int childPosition);
	
	/**
	 * 更新头视图
	 * @param context
	 * @param expandListView
	 * @param groupEntity
	 * @param childEntity
	 * @param groupPosition
	 * @param childPosition
	 * @param alpha
	 */
	public void updateHeaderView(Context context, ExpandableListView expandListView, View header, T1 groupEntity, T2 childEntity, int groupPosition, int childPosition, int alpha);
}
