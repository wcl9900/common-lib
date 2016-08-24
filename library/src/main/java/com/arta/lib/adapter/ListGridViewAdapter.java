package com.arta.lib.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.arta.lib.adapter.PageGridViewAdapter.ItemViewHolder;
import com.arta.lib.widget.listener.OnPageSplitItemClickListener;
import com.arta.lib.widget.listener.OnPageSplitItemManager;

/**
 * ListGridView 视图适配器
 * @author 王春龙
 *
 * @param <T> 绑定的数据实体
 */
public class ListGridViewAdapter<T> extends BaseAdapter implements OnItemClickListener{
	
	private int pageItemColumn;
	private int pageItemCount;
	
	private List<T> entityList;
	
	private Context context;
	
	private OnPageSplitItemManager<T> onSplitPageItemManager;
	
	private LayoutInflater mInflater;
	
	private OnPageSplitItemClickListener<T> onPageSplitItemClickListener;
	
	private List<GridView> gridViewList;
	
	private int gridViewId = -1;
	
	public ListGridViewAdapter(Context context, List<T> entityList , int pageItemCount, OnPageSplitItemManager<T> onPageSplitItemManage){
		this(context, entityList, pageItemCount, onPageSplitItemManage, -1);
	}
	
	public ListGridViewAdapter(Context context, List<T> entityList , int pageItemCount, OnPageSplitItemManager<T> onPageSplitItemManage, int gridViewId){
		this(context, entityList, 1, pageItemCount, onPageSplitItemManage, gridViewId);
	}
	
	public ListGridViewAdapter(Context context, List<T> entityList , int pageItemRow, int pageItemColumn, OnPageSplitItemManager<T> onPageSplitItemManage){
		this(context, entityList, pageItemRow, pageItemColumn, onPageSplitItemManage, -1);
	}
	
	public ListGridViewAdapter(Context context, List<T> entityList , int pageItemRow, int pageItemColumn, OnPageSplitItemManager<T> onPageSplitItemManage, int gridViewId){
		this.context = context;
		this.entityList = entityList;
		
		this.pageItemColumn = pageItemColumn;
		this.pageItemCount = pageItemRow * pageItemColumn;
		if(this.pageItemCount <= 0){
			this.pageItemCount = 1;
			this.pageItemColumn = 1;
		}
		
		this.onSplitPageItemManager = onPageSplitItemManage;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		gridViewList = new ArrayList<GridView>();
		
		this.gridViewId = gridViewId;
	}
	
	public void setOnSplitPageItemClickListener(OnPageSplitItemClickListener<T> onSplitPageItemClickListener){
		this.onPageSplitItemClickListener = onSplitPageItemClickListener;
	}
	
	public void setOnSplitPageItemView(OnPageSplitItemManager<T> onSplitPageItemManager){
		this.onSplitPageItemManager = onSplitPageItemManager;
	}
	
	/**
	 * 设定每页显示的元素个数
	 * @param pageItemCount
	 */
	public void setPageItemCount(int pageItemCount){
		setPageItemCount(1, pageItemCount);
	}
	
	/**
	 * 
	 * @param pageItemRow 每页显示子项行数
	 * @param pageItemColumn 每页显示子项列数
	 */
	public void setPageItemCount(int pageItemRow, int pageItemColumn){
		this.pageItemCount = pageItemRow * pageItemColumn;
		this.pageItemColumn = pageItemColumn;
	}
	
	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GridView gridView  = null;
		if(gridViewList.size() <= position){
			if(gridViewId != -1){
				gridView  = (GridView)mInflater.inflate(gridViewId, null);
			}
			else{
				gridView = new GridView(context);
			}
			gridViewList.add(gridView);
			gridView.setOnItemClickListener(this);
			PageGridViewAdapter<T> pageGridViewAdapter = 
					new PageGridViewAdapter<T>(context, entityList, position, pageItemCount, onSplitPageItemManager);
			gridView.setAdapter(pageGridViewAdapter);
		}
		
		gridView = gridViewList.get(position);
		gridView.setNumColumns(pageItemColumn);
		
		if(gridView.getAdapter() instanceof PageGridViewAdapter){
			((PageGridViewAdapter<?>)gridView.getAdapter()).setItemCount(pageItemCount);
		}
		
		return gridView;
	}

	@Override
	public int getCount() {
		if(entityList == null){
			return 0;
		}
		int pageCount = entityList.size() / pageItemCount;
		return entityList.size() % pageItemCount == 0 ? pageCount : pageCount + 1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(onPageSplitItemClickListener != null && view.getTag(AdapterConstant.TAG_KEY) != null && view.getTag(AdapterConstant.TAG_KEY) instanceof ItemViewHolder){
			ItemViewHolder holder = (ItemViewHolder) view.getTag(AdapterConstant.TAG_KEY);
			onPageSplitItemClickListener.onPageSplitItemClick(holder.view, (T)holder.entity, holder.itemPosition, holder.pagePosition);
		}
	}

}
