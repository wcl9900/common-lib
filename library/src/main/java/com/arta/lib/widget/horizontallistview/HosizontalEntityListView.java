package com.arta.lib.widget.horizontallistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.arta.lib.adapter.ItemSelectAdapter;
import com.arta.lib.adapter.BaseEntityViewAdapter.EntityViewHolder;
import com.arta.lib.widget.listener.OnEntityViewClickListener;

public class HosizontalEntityListView extends HorizontalListView  implements android.widget.AdapterView.OnItemClickListener{
	
	@SuppressWarnings("rawtypes")
	private OnEntityViewClickListener onEntityViewClickListener;
	
	private OnItemClickListener itemClickListener;

	public HosizontalEntityListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init(){
		super.setOnItemClickListener(this);
	}
	/**
	 * 设定Item选中项
	 * @param itemPosition
	 */
	public void setSelectItem(int itemPosition){
		if(getAdapter() != null && getAdapter() instanceof ItemSelectAdapter<?>){
			ItemSelectAdapter<?> adapter = ((ItemSelectAdapter<?>)getAdapter());
			if(!adapter.isClickRepeatEnable() && itemPosition == adapter.getSelectItemPosition()) return;
			((ItemSelectAdapter<?>)getAdapter()).setSelectItemPosition(itemPosition);
		}
		this.setSelection(itemPosition);
	}
	/**
	 * 获取Item选中位置
	 * @return
	 */
	public int getSelectedItemPosition(){
		if(getAdapter() != null && getAdapter() instanceof ItemSelectAdapter<?>){
			return ((ItemSelectAdapter<?>)getAdapter()).getSelectItemPosition();
		}
		else{
			return super.getSelectedItemPosition();
		}
	}
	@Override
	public void setOnItemClickListener(
			android.widget.AdapterView.OnItemClickListener listener) {
		this.itemClickListener = listener;;
	}
	
	/**
	 * 设定数据实体视图点击监听器
	 * @param listener
	 */
	public void setOnEntityViewClickListener(OnEntityViewClickListener<?> listener){
		this.onEntityViewClickListener = listener;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(this.getAdapter() != null && this.getAdapter() instanceof ItemSelectAdapter){
			ItemSelectAdapter adapter = ((ItemSelectAdapter)this.getAdapter());
			if(!adapter.isClickRepeatEnable() && position == adapter.getSelectItemPosition()) return;
			((ItemSelectAdapter)this.getAdapter()).setSelectItemPosition(position);
		}
		
		if(itemClickListener != null){
			itemClickListener.onItemClick(parent, view, position, id);
		}
		
		if(onEntityViewClickListener != null && view.getTag() != null && view.getTag() instanceof EntityViewHolder){
			EntityViewHolder holder = (EntityViewHolder) view.getTag();
			onEntityViewClickListener.onEntityViewClick(parent, view, holder.entity, holder.position);
		}
	}
}
