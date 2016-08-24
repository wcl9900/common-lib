package com.arta.lib.widget.datalodinglayout;

/**
 * 数据加载加载监听器
 * @author 王春龙
 *
 */
public interface OnDataLoadingListener {
	/**
	 * 数据加载失败重试
	 */
	public void onRetryLoad(DataLodingLayout dataLayout);
}
