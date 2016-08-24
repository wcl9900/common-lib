package com.arta.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.arta.lib.adapter.AdapterConstant;
import com.arta.lib.adapter.treeview.BaseEntitySelectedTreeViewAdapter;
import com.arta.lib.adapter.treeview.OnEntityTreeViewClickListener;
import com.arta.lib.adapter.treeview.BaseEntityTreeViewAdapter.ViewHolderChild;
import com.arta.lib.adapter.treeview.BaseEntityTreeViewAdapter.ViewHolderGroup;
import com.arta.lib.widget.treeview.TreeView;

/**
 * 伸缩列表视图，当数据适配器为{@link BaseEntitySelectedTreeViewAdapter}可实现选中状态
 * @author 王春龙
 *
 */
public class DefaultTreeView extends TreeView implements OnChildClickListener{
	
	@SuppressWarnings("rawtypes")
	private OnEntityTreeViewClickListener treeViewClickListener;
	
	private ExpandableListAdapter adapter = null;
	
	public DefaultTreeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public DefaultTreeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DefaultTreeView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		super.setOnChildClickListener(this);
	}

	@Override
	public void setAdapter(ExpandableListAdapter adapter) {
		super.setAdapter(adapter);
		this.adapter = adapter;
	}

	/**
	 * 设定实体视图点击事件
	 * @param onEntityTreeViewClickListener
	 */
	public void setOnEntityTreeViewClickListener(OnEntityTreeViewClickListener<?, ?> onEntityTreeViewClickListener){
		this.treeViewClickListener = onEntityTreeViewClickListener;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
		boolean flag = super.onGroupClick(parent, v, groupPosition, id);

		boolean fireClick = false;
		if(adapter != null && adapter instanceof BaseEntitySelectedTreeViewAdapter){
			BaseEntitySelectedTreeViewAdapter a = (BaseEntitySelectedTreeViewAdapter)adapter;
			fireClick = a.setSelectedGroup(groupPosition);
		}
		
		if(treeViewClickListener != null && fireClick){
			ViewHolderGroup viewHolderGroup = (ViewHolderGroup) v.getTag(AdapterConstant.TAG_KEY);
			treeViewClickListener.onGroupClick(getContext(), parent, v, viewHolderGroup.groupEntity, viewHolderGroup.groupPosition);
		}
		
		
		return flag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		boolean fireClick = false;
		if(adapter != null && adapter instanceof BaseEntitySelectedTreeViewAdapter){
			BaseEntitySelectedTreeViewAdapter a = (BaseEntitySelectedTreeViewAdapter)adapter;
			fireClick = a.setSelectedChild(groupPosition, childPosition);
		}
		
		if(treeViewClickListener != null && fireClick){
			ViewHolderChild viewHolderChild = (ViewHolderChild) v.getTag(AdapterConstant.TAG_KEY);
			treeViewClickListener.onChildClick(getContext(), parent, v, viewHolderChild.groupEntity, viewHolderChild.childEntity, viewHolderChild.groupPosition, viewHolderChild.childPosition);
		}
		
		return true;
	}
}
