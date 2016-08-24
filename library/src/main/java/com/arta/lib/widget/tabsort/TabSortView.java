package com.arta.lib.widget.tabsort;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import com.arta.lib.R;

/**
 * 排序控件
 * @author 王春龙
 *
 */
public class TabSortView extends LinearLayout implements OnClickListener{
	
	public interface OnTabSortClickListener{
		/**
		 * 
		 * @param tabSortView
		 * @param tabSortButton 当前点击的排序按钮
		 * @param tabSortType 当前点击的排序按钮的排序类型
		 * @param position 当前点击的排序按钮的位置
		 */
		public void onSortButtonClick(TabSortView tabSortView, TabSortButton tabSortButton, TabSortType tabSortType, int position);
	}
	
	private Drawable drawable_default_no;
	private Drawable drawable_up_to_down;
	private Drawable drawable_down_to_up;
	
	private boolean initFinish = false;
	
	private List<TabSortButton> list_tabSortButton;
	
	private int default_button = -1;
	private TabSortType default_sort_type = TabSortType.DEFAULT_NO;
	
	private OnTabSortClickListener listener;
	
	private List<TabSortType> sortTypeList;
	
	public TabSortView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(attrs);
	}

	public TabSortView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(attrs);
	}

	public TabSortView(Context context) {
		super(context);
	}
	
	private void initAttrs(AttributeSet attrs){
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TabSortView);
		
		drawable_default_no = ta.getDrawable(R.styleable.TabSortView_drawable_default);
		drawable_up_to_down = ta.getDrawable(R.styleable.TabSortView_drawable_up);
		drawable_down_to_up = ta.getDrawable(R.styleable.TabSortView_drawable_down);
		
		default_button = ta.getInteger(R.styleable.TabSortView_selected_sortbutton, default_button);
		int sort_type = ta.getInt(R.styleable.TabSortView_selected_sortbutton_sorttype, default_sort_type.ordinal());
		for(TabSortType type : TabSortType.values()){
			if(type.ordinal() == sort_type){
				default_sort_type = type;
				break;
			}
		}
		
		ta.recycle();
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(!initFinish){
			initFinish = !initFinish;
			init();
		}
	}
	
	private void init(){
		sortTypeList = new ArrayList<TabSortType>();
		sortTypeList.add(TabSortType.DEFAULT_NO);
		sortTypeList.add(TabSortType.UP_TO_DOWN);
		sortTypeList.add(TabSortType.DOWN_TO_UP);

		list_tabSortButton = new ArrayList<TabSortButton>();
		
		int childCount = getChildCount();
		for(int i = 0, j = 0; i < childCount; i++){
			View child = getChildAt(i);
			if(!(child instanceof TabSortButton)){
				continue;
			}
			TabSortButton tabSortButton = (TabSortButton) child;
			if(j == default_button){
				if( default_sort_type == TabSortType.DEFAULT_NO){
					tabSortButton.setFlagDrawable(drawable_default_no, TabSortType.DEFAULT_NO);
				}
				else if(default_sort_type == TabSortType.UP_TO_DOWN){
					tabSortButton.setFlagDrawable(drawable_up_to_down, TabSortType.UP_TO_DOWN);
				}
				else if(default_sort_type == TabSortType.DOWN_TO_UP){
					tabSortButton.setFlagDrawable(drawable_down_to_up, TabSortType.DOWN_TO_UP);
				}
			}
			else{
				tabSortButton.setFlagDrawable(drawable_default_no, TabSortType.DEFAULT_NO);
			}
			tabSortButton.setOnClickListener(this);
			list_tabSortButton.add(tabSortButton);
			j++;
		}
	}

	public void setOnTabSortClickListener(OnTabSortClickListener listener){
		this.listener = listener;
	}
	
	/**
	 * 复位所有排序按钮为非排序状态
	 */
	public void resetAll(){
		for(TabSortButton btn : list_tabSortButton){
			btn.setFlagDrawable(drawable_default_no, TabSortType.DEFAULT_NO);
		}
	}
	
	@Override
	public void onClick(View v) {
		TabSortButton tabSortButton = (TabSortButton) v.getTag(TabSortConstant.TAB_SORT_BUTTON);
		for(TabSortButton sortButton : list_tabSortButton){
			if(tabSortButton != sortButton){
				sortButton.setFlagDrawable(drawable_default_no, TabSortType.DEFAULT_NO);
				continue;
			}
			if(sortButton.getSortType() == TabSortType.DOWN_TO_UP || sortButton.getSortType() == TabSortType.DEFAULT_NO){
				sortButton.setFlagDrawable(drawable_up_to_down, TabSortType.UP_TO_DOWN);
			}
			else{
				sortButton.setFlagDrawable(drawable_down_to_up, TabSortType.DOWN_TO_UP);
			}
		}
		
		if(listener != null){
			listener.onSortButtonClick(this, tabSortButton, tabSortButton.getSortType(), list_tabSortButton.indexOf(tabSortButton));
		}
	}
}
