package com.arta.lib.adapter.wrapper;

import com.arta.lib.adapter.BaseEntityPageAdapter;
import com.arta.lib.widget.listener.OnEntityViewPagerClickListener;
import com.arta.lib.widget.viewpagerindicator.PageIndicator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
/**
 * ViewPager 视图包装类。可用于对ViewPager进行基于{@link BaseEntityPageAdapter}适配器的数据绑定；可以设定
 * 指示器{@link PageIndicator}；可以使用实体数据监听器{@link OnEntityViewPagerClickListener}等操作。
 * @author ML
 *
 * @param <T>
 */
public class ViewPagerEntityWrapper<T>{
	
	private ViewPager viewPager;
	
	private PageIndicator pageIndictor;
	
	private OnEntityViewPagerClickListener<T> onEntityViewPagerClickListener;
	
	public ViewPagerEntityWrapper(ViewPager viewPager){
		this.viewPager = viewPager;
	}
	
	public ViewPager getViewPager(){
		return this.viewPager;
	}
	
	@SuppressWarnings("unchecked")
	public void setAdapter(PagerAdapter adapter){
		this.viewPager.setAdapter(adapter);
		if(this.pageIndictor != null){
			this.pageIndictor.setViewPager(viewPager);
		}
		if(adapter != null && adapter instanceof BaseEntityPageAdapter){
			((BaseEntityPageAdapter<T>)adapter).setOnEntityViewPagerClickListener(onEntityViewPagerClickListener);
		}
	}
	
	public void setIndcitor(PageIndicator indictor){
		this.pageIndictor = indictor;
		if(this.viewPager != null && this.viewPager.getAdapter() != null){
			this.pageIndictor.setViewPager(viewPager);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setOnEntityViewPagerClickListener(OnEntityViewPagerClickListener<T> onEntityViewPagerClickListener){
		this.onEntityViewPagerClickListener = onEntityViewPagerClickListener;
		if(viewPager != null && viewPager.getAdapter() != null && viewPager.getAdapter() instanceof BaseEntityPageAdapter){
			((BaseEntityPageAdapter<T>)viewPager.getAdapter()).setOnEntityViewPagerClickListener(onEntityViewPagerClickListener);
		}
	}
}
