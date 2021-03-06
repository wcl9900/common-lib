package com.arta.lib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 默认的抽象适配器视图Item管理器
 * @author 王春龙
 *
 * @param <T> 子视图所需实体数据
 */
public abstract class  DefaultBaseAdapterEntityViewManage<T> implements
		BaseAdapterEntityViewManage<T> {

	private int resId;
	
	public DefaultBaseAdapterEntityViewManage(int resId) {
		this.resId = resId;
	}
	
	@Override
	public View getAdapterItemView(Context context, T entity, int position) {
		return LayoutInflater.from(context).inflate(resId, null);
	}

	@Override
	public View updateAdapterItemView(Context context, View updateView,
			T entity, int position) {
		updateItemView(context, updateView, entity, position);
		return updateView;
	}
	
	/**
	 * 更新子视图
	 * @param context
	 * @param updateView
	 * @param entity
	 * @param position
	 */
	abstract public void updateItemView(Context context, View updateView,
			T entity, int position) ;
}
