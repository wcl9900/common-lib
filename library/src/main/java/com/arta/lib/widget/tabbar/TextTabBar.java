package com.arta.lib.widget.tabbar;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
/**
 * 基于LinearLayout布局扩展为TextTabBar选项卡
 * @author 王春龙
 *
 */
public class TextTabBar extends LinearLayout implements OnClickListener{
	
	private List<TextButton> TextButtonList;
	
	private List<OnTextTabSelectListener> tabSelectListenerList;
	
	private boolean isInit = false;
	
	private int defaultSelected = 0;
	
	private int currentItem = defaultSelected;
	
	private boolean selectEnable = true;
	
	public TextTabBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TextTabBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TextTabBar(Context context) {
		super(context);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(!isInit){
			init();
			isInit = true;
		}
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
	/**
	 * 取消选择状态
	 */
	public void disSelected(){
		fireTextButtonStateChange(null, currentItem = -1);
	}
	
	private void init(){
		TextButtonList = new ArrayList<TextButton>();
		for(int i = 0, tabIndex = 0; i<getChildCount(); i++){
			View v = getChildAt(i);
			if(v == null || !(v instanceof TextButton)) {
				Log.e("MyTabBar>>>>>>>>>>", "ChildView 不是 TextButton类型！！！！！！！");
				continue;
			}
			
			TextButton tb = (TextButton)v;
			if(defaultSelected == tabIndex){
				tb.setSelected(true);
			}
			tb.setOnClickListener(this);
			TextButtonList.add((TextButton)v);
			
			tabIndex++;
		}
	}
	
	public void setOnTabSelectedListener(OnTextTabSelectListener txetTabSelectListener){
		if(tabSelectListenerList == null){
			tabSelectListenerList = new ArrayList<OnTextTabSelectListener>();
		}
		if(!tabSelectListenerList.contains(txetTabSelectListener)){
			tabSelectListenerList.add(txetTabSelectListener);
		}
	}

	@Override
	public void onClick(View v) {
		if(v == null || !(v instanceof TextButton)){
			return;
		}
		if(!selectEnable){
			v.setSelected(TextButtonList.indexOf(v) == currentItem);
			return;
		}
		
		selectTextButton(v);
	}

	public void selectTextButton(View v) {
		TextButton TextButton = (TextButton)v;
		int index = TextButtonList.indexOf(v);
		
		if(currentItem == index) return;
		
		fireTextButtonStateChange((TextButton)v , index);
		
		currentItem = index;
		
		if(tabSelectListenerList == null || !fireTabListener) return;
		
		for(int i = 0; i < tabSelectListenerList.size();i++){
			tabSelectListenerList.get(i).textTabSelectedListener(TextButton, index);
		}
	}
	
	private boolean fireTabListener = true;
	public void selectTab(int index){
		selectTab(index, true);
	}
	
	public void selectTab(int index, boolean fireTabListener){
		if(index < 0 || TextButtonList == null || index >= TextButtonList.size()) return;
		this.fireTabListener = fireTabListener;
		selectTextButton(TextButtonList.get(index));
		this.fireTabListener = true;
	}
	
	private void fireTextButtonStateChange(TextButton TextButton,int index){
		stateChange(TextButton, index);
	}
	
	public void stateChange(TextButton changedButton, int index) {
		for(int i = 0; i < TextButtonList.size(); i++){
			if(i == index){
				changedButton.setSelected(true);
				continue;
			}
			TextButtonList.get(i).setSelected(false);
		}
	}
}
