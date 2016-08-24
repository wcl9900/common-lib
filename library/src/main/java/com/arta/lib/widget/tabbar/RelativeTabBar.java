package com.arta.lib.widget.tabbar;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
/**
 * 基于Relativie布局扩展为RelativeTabBar选项卡
 * @author 王春龙
 *
 */
public class RelativeTabBar extends RelativeLayout implements OnClickListener{
	
	private ITabButtonObserveListener tabButtonObserveListener; 
	
	private List<TabButton> tabButtonList;
	
	private List<OnTabSelectListener> tabSelectListenerList;
	
	private boolean isInit = false;

	private boolean selectEnable = true;
	
	private int defaultSelected = 0;
	
	private int currentItem = defaultSelected;
	
	public RelativeTabBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RelativeTabBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RelativeTabBar(Context context) {
		super(context);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if(!isInit){
			init();
			isInit = true;
		}
		super.onLayout(changed, l, t, r, b);
	}
	
	public void setDefaultSelectedTab(int defaultSelected){
		this.defaultSelected = defaultSelected;
		this.currentItem = defaultSelected;
	}

	public void setClickSelectEnable(boolean selectEnable){
		this.selectEnable = selectEnable;
	}
	/**
	 * 得到当前选择项
	 * @return
	 */
	public int getCurrentItem(){
		return currentItem;
	}
	
	private void init(){
		tabButtonObserveListener = new TabButtonObserveListener();
		tabButtonList = new ArrayList<TabButton>();
		for(int i = 0, tabIndex = 0; i<getChildCount(); i++){
			View v = getChildAt(i);
			if(v == null || !(v instanceof TabButton)) {
				continue;
			}
			
			TabButton tb = (TabButton)v;
			if(defaultSelected == tabIndex){
				tb.setTabToggle(true);
			}
			tb.setOnClickListener(this);
			tabButtonObserveListener.addObserveElement(tb);
			tabButtonList.add((TabButton)v);
			
			tabIndex++;
		}
	}
	
	public void setOnTabSelectedListener(OnTabSelectListener tabSelectListener){
		if(tabSelectListenerList == null){
			tabSelectListenerList = new ArrayList<OnTabSelectListener>();
		}
		if(!tabSelectListenerList.contains(tabSelectListener)){
			tabSelectListenerList.add(tabSelectListener);
		}
	}

	@Override
	public void onClick(View v) {
		if(v == null || !(v instanceof TabButton)){
			return;
		}

		if(!selectEnable){
			v.setSelected(tabButtonList.indexOf(v) == currentItem);
			return;
		}
		
		selectTabButton(v);
	}

	public void selectTabButton(View v) {
		
		TabButton tabButton = (TabButton)v;
		int index = tabButtonList.indexOf(v);
		
		if(currentItem == index) return;
		
		fireTabButtonStateChange((TabButton)v , index);

		currentItem = index;
		
		if(tabSelectListenerList == null || !fireTabListener) return;
		
		for(int i = 0; i < tabSelectListenerList.size();i++){
			tabSelectListenerList.get(i).TabSelectedListener(tabButton, index);
		}
	}
	/**
	 * 取消选择状态
	 */
	public void disSelected(){
		fireTabButtonStateChange(null, currentItem = -1);
	}
	
	private boolean fireTabListener = true;
	public void selectTab(int index){
		selectTab(index, true);
	}
	
	public void selectTab(int index, boolean fireTabListener){
		if(index < 0 || tabButtonList == null || index >= tabButtonList.size()) return;
		this.fireTabListener = fireTabListener;
		selectTabButton(tabButtonList.get(index));
		this.fireTabListener = true;
	}
	
	private void fireTabButtonStateChange(TabButton tabButton,int index){
		tabButtonObserveListener.stateChange(tabButton, index);
	}
}
