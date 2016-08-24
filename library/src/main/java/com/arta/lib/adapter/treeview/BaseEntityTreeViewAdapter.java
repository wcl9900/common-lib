package com.arta.lib.adapter.treeview;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.arta.lib.adapter.AdapterConstant;
import com.arta.lib.widget.treeview.BaseTreeViewAdapter;
import com.arta.lib.widget.treeview.TreeView;

/**
 * 树结构视图适配器
 * @author 王春龙
 *
 * @param <T1> 组视图数据实体
 * @param <T2> 子视图数据实体
 */
public class BaseEntityTreeViewAdapter<T1, T2> extends BaseTreeViewAdapter {

	private List<T1> groupEntityList;
	private List<List<T2>> childEntityList;
	
	private BaseAdapterEntityTreeViewManage<T1, T2> viewManage;
	
	protected ExpandableListView expandListView;
	
	public BaseEntityTreeViewAdapter(ExpandableListView expandListView, List<T1> groupEntityList, List<List<T2>> childEntityList, 
			BaseAdapterEntityTreeViewManage<T1, T2> viewManage) {
		super(expandListView instanceof TreeView ? (TreeView)expandListView : null);
		
		this.expandListView = expandListView;
		
		this.groupEntityList = groupEntityList;
		this.childEntityList = childEntityList;
		
		this.viewManage = viewManage;
	}

	@Override
	public void updateHeader(View header, int groupPosition, int childPosition,
			int alpha) {
		T1 groupEntity = groupEntityList.size() > groupPosition ? groupEntityList.get(groupPosition) : null;
		T2 childEntity = null;
		if(childEntityList.size() > groupPosition && groupPosition >= 0){
			if(childEntityList.get(groupPosition).size() > childPosition && childPosition >= 0){
				childEntity = childEntityList.get(groupPosition).get(childPosition);
			}
		}
		viewManage.updateHeaderView(expandListView.getContext(), expandListView, header, 
				groupEntity, childEntity, groupPosition, childPosition, alpha);
	}

	@Override
	public int getGroupCount() {
		return groupEntityList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if(groupPosition < childEntityList.size() && groupPosition >= 0){
			return childEntityList.get(groupPosition).size();
		}
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupEntityList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childEntityList.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if(groupPosition <0 || groupPosition >= getGroupCount()) return convertView;
		
		ViewHolderGroup viewHolderGroup = null;
		if(convertView == null){
			convertView = viewManage.getGroupView(expandListView.getContext(), expandListView,isExpanded, groupEntityList.get(groupPosition), groupPosition);
			viewHolderGroup = new ViewHolderGroup();
			convertView.setTag(AdapterConstant.TAG_KEY, viewHolderGroup);
		}
		
		viewHolderGroup = (ViewHolderGroup) convertView.getTag(AdapterConstant.TAG_KEY);		
		viewHolderGroup.groupPosition = groupPosition;
		viewHolderGroup.groupEntity = groupEntityList.get(groupPosition);
		
		viewManage.updateGroupView(expandListView.getContext(), expandListView,isExpanded, convertView, groupEntityList.get(groupPosition), groupPosition);

		return convertView;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolderChild viewHolderChild = null;
		T1 groupEntity = groupEntityList.get(groupPosition);
		T2 childEntity = childEntityList.get(groupPosition).get(childPosition);
		if(convertView == null){
			convertView = viewManage.getChildView(expandListView.getContext(), expandListView, childEntity, groupPosition, childPosition);
			viewHolderChild = new ViewHolderChild();
			convertView.setTag(AdapterConstant.TAG_KEY, viewHolderChild);
		}
		
		viewHolderChild = (ViewHolderChild) convertView.getTag(AdapterConstant.TAG_KEY);	
		viewHolderChild.groupPosition = groupPosition;
		viewHolderChild.childPosition = childPosition;
		viewHolderChild.childEntity = childEntity;
		viewHolderChild.groupEntity = groupEntity;
		
		viewManage.updateChildView(expandListView.getContext(), expandListView, convertView, childEntity, groupPosition, childPosition);
	
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	public class ViewHolderGroup{
		public int groupPosition;
		public T1 groupEntity;
	}
	
	public class ViewHolderChild{
		public int groupPosition;
		public int childPosition;
		public T1 groupEntity;
		public T2 childEntity;
	}
}
