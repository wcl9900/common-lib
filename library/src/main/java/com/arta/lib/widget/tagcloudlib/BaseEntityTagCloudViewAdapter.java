package com.arta.lib.widget.tagcloudlib;

import java.util.List;

import com.arta.lib.adapter.AdapterConstant;
import com.arta.lib.adapter.BaseAdapterEntityViewManage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 通用适配器
 * @author 王春龙
 *
 * @param <T>数据实体类型
 */
public class BaseEntityTagCloudViewAdapter<T> extends TagsAdapter {
	
	protected List<T> entityList;
	
	protected Context context;
	
	protected BaseAdapterEntityViewManage<T> adapterItemManage;
	
	private int popularity = 7;
	
	/**
	 * 
	 * @param context
	 * @param entityList 数据实体列表
	 * @param adapterItemManage 生成子视图的管理器
	 */
	public BaseEntityTagCloudViewAdapter(Context context, List<T> entityList, BaseAdapterEntityViewManage<T> adapterItemManage){
		this.context = context;
		this.entityList = entityList;
		this.adapterItemManage = adapterItemManage;
	}
	
	public void setPopularity(int popularity){
		this.popularity = popularity;
	}
	
	@Override
	public int getCount() {
		return entityList.size();
	}

	@Override
	public Object getItem(int position) {
		return entityList.get(position);
	}

	public static class EntityViewHolder<T>{
		public T entity;
		public int position;
	}

	@Override
	public View getView(Context context, int position, ViewGroup parent) {
		EntityViewHolder<T> holder = null;
		View convertView = adapterItemManage.getAdapterItemView(context, entityList.get(position), position);
		holder = new EntityViewHolder<T>();
		convertView.setTag(AdapterConstant.TAG_KEY, holder);
		
		holder = (EntityViewHolder<T>) convertView.getTag(AdapterConstant.TAG_KEY);
		holder.entity = entityList.get(position);
		holder.position = position;
		convertView = adapterItemManage.updateAdapterItemView(context, convertView, entityList.get(position), position);

		return convertView;
	}

	@Override
	public int getPopularity(int position) {
		return position % popularity;
	}

	@Override
	public void onThemeColorChanged(View view, int themeColor) {
		if(view != null && view instanceof TextView)
		((TextView)view).setTextColor(themeColor);
	}
}
