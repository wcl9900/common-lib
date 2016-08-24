package com.arta.lib.widget.tabbar;

import java.util.ArrayList;
import java.util.List;
import com.arta.lib.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 * 基于线性布局的TabBar选项卡,此选项卡可使用基于View的任何控件作为选项卡，子选项卡的控件id必须固定为： tabbarview_item (@id/tabbarview_item)；
 * 选项卡的选项监听器使用{@link OnTabViewSelectListener}接口实现。
 * @author 王春龙
 *
 */
public class TabBarView extends LinearLayout implements OnClickListener{
	
	private List<View> tabViewList;
	
	private OnTabViewSelectListener onTabSelectListener;
	
	private boolean isInit = false;
	
	private int defaultSelected = 0;
	
	private int currentItem = defaultSelected;
	
	private boolean selectEnable = true;
	
	private boolean repeatClickEnable = false;
	
	private int tabBarViewId = -1;
	
	public TabBarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(attrs);
		init();
	}

	public TabBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(attrs);
		init();
	}

	public TabBarView(Context context) {
		super(context);
		init();
	}

	private void initAttrs(AttributeSet attrs){
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TabBarView);
		tabBarViewId = ta.getResourceId(R.styleable.TabBarView_tabViewId, R.id.tabbarview_item);
		repeatClickEnable = ta.getBoolean(R.styleable.TabBarView_repeatClickEnable, repeatClickEnable);
		defaultSelected = ta.getInteger(R.styleable.TabBarView_defaultSelectIndex, defaultSelected);
		ta.recycle();
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
//		if(!isInit){
//
//			isInit = true;
//		}
	}
	
	public void setDefaultSelectedTab(int defaultSelected){
		this.defaultSelected = defaultSelected;
		this.currentItem = defaultSelected;
	}
	public void setClickSelectEnable(boolean selectEnable){
		this.selectEnable = selectEnable;
	}
	
	/**
	 * 设定已选选项卡是否可以重复点击
	 * @param enable
	 */
	public void setRepeatClickEnable(boolean enable){
		this.repeatClickEnable = enable;
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
		fireTabViewStateChange(null, currentItem = -1);
	}
	
	private int tabIndex = 0;
	private void init(){
		tabViewList = new ArrayList<View>();
		post(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < TabBarView.this.getChildCount(); i++){
					searchTabView(getChildAt(i));
				}
			}
		});
	}

	private void searchTabView(View v){
		if(v instanceof TabBarView){
			return;
		}
		if(v.getId() == tabBarViewId){
			if(defaultSelected == tabIndex){
				v.setSelected(true);
			}
			v.setOnClickListener(this);
			tabViewList.add(v);
			
			tabIndex++;
		}
		else if(v instanceof ViewGroup){
			ViewGroup vg = (ViewGroup)v;
			int childCount = vg.getChildCount();
			for(int index = 0; index < childCount; index++){
				searchTabView(vg.getChildAt(index));
			}
		}
	}
	
	public void setOnTabViewSelectListener(OnTabViewSelectListener onTabViewSelectListener){
		onTabSelectListener = onTabViewSelectListener;
	}

	@Override
	public void onClick(View v) {
		if(v == null){
			return;
		}
		if(!selectEnable){
			v.setSelected(tabViewList.indexOf(v) == currentItem);
			return;
		}
		
		selectTabView(v);
	}

	public void selectTabView(View tabView) {
		int index = tabViewList.indexOf(tabView);
		
		if(!repeatClickEnable && currentItem == index){
			return;
		}
		
		if(onTabSelectListener != null && onTabSelectListener instanceof OnTabViewSelectExpandListener){
			OnTabViewSelectExpandListener expandListener = (OnTabViewSelectExpandListener) onTabSelectListener;
			if(expandListener.onTabViewSelectedBeforeListener(this, tabView, index)) return;
		}
		
		fireTabViewStateChange(tabView, index);
		
		currentItem = index;
		
		if(onTabSelectListener != null && fireTabListenerEnable) {
			onTabSelectListener.onTabViewSelectedListener(this, tabView, index);
		}
	}
	
	private boolean fireTabListenerEnable = true;
	public void selectTabView(int tabIndex){
		selectTabView(tabIndex, true);
	}
	
	public void selectTabView(int tabIndex, boolean fireTabListener){
		if(tabIndex < 0 || tabViewList == null || tabIndex >= tabViewList.size()) return;
		this.fireTabListenerEnable = fireTabListener;
		selectTabView(tabViewList.get(tabIndex));
		this.fireTabListenerEnable = true;
	}
	
	private void fireTabViewStateChange(View tabView,int index){
		stateChange(tabView, index);
	}
	
	private void stateChange(View changeTabView, int index) {
		for(int i = 0; i < tabViewList.size(); i++){
			if(i == index){
				if(repeatClickEnable){
					changeTabView.setSelected(!changeTabView.isSelected());
				}
				else{
					changeTabView.setSelected(true);
				}
				continue;
			}
			tabViewList.get(i).setSelected(false);
		}
	}
}
