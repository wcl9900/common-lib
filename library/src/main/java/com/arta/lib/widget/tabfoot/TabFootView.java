package com.arta.lib.widget.tabfoot;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.arta.lib.util.ScreenUtils;

import com.arta.lib.R;
/**
 * Tab选项卡底部选择信息显示控件,扩展自LinerLayout布局
 * @author 王春龙
 *
 */
public class TabFootView extends LinearLayout implements OnClickListener,AnimationListener{
	/**
	 * 选项卡脚底动画显示监听接口
	 * @author 王春龙
	 *
	 */
	public interface OnTabFootSelectListener {
		/**
		 * tab_foot标签被选中时触发的事件
		 * @param tabFootButton
		 * @param index
		 */
		public void tabFootSelected(TabFootButton tabFootButton,int index);
	}
	
	private int title_drawable_resId = -1;
	private int tab_drawable_resId = -1;
	
	private int tab_count;
	
	private int tab_divider;
	
	private ImageView title_view;
	
	private List<TabFootButton> tab_btn_list;
	
	private OnTabFootSelectListener tabFootSelectListener;
	
	private int defaultSelection = 0;
	
	private int tab_anim_duration = 700;
	
	private int tab_title_foot_divider = 2;
	
	private int end = 0;
	
	private int currentSelected = 0;
	
	private boolean hasAdjustPosition = false;
	
	private boolean tabClickEnable = true;
	
	public TabFootView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(attrs);
		initView();
	}

	public TabFootView(Context context, AttributeSet attrs) {
		super(context,attrs);		
		initAttrs(attrs);
		initView();
	}

	public TabFootView(Context context) {
		super(context);
	}
	
	private void initAttrs(AttributeSet attrs){
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TabFootView);
		
		TypedValue tv = new TypedValue();
		
		ta.getValue(R.styleable.TabFootView_title_background,tv);
		title_drawable_resId = tv.resourceId;
		
		ta.getValue(R.styleable.TabFootView_tab_backgroud,tv);
		tab_drawable_resId = tv.resourceId;
		
		tab_count = ta.getInt(R.styleable.TabFootView_tab_count, 0);
		tab_divider = (int) ScreenUtils.dpToPx(getContext(), (float)(ta.getInt(R.styleable.TabFootView_tab_divider, 5)));
		
		tab_title_foot_divider = ta.getInt(R.styleable.TabFootView_tab_title_foot_divider , tab_title_foot_divider);
		
		tab_anim_duration = ta.getInt(R.styleable.TabFootView_tab_anim_duration, tab_anim_duration);
		
		tabClickEnable = ta.getBoolean(R.styleable.TabFootView_tabClickEnable, tabClickEnable);
		
		ta.recycle();
	}
	
	private void initView(){
		setOrientation(LinearLayout.VERTICAL);
		
		title_view = new ImageView(getContext());
		LayoutParams title_lp = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		title_lp.bottomMargin = tab_title_foot_divider;
		
		if(title_drawable_resId == -1){
			title_lp.width = 0;
			title_lp.height = 0;
		}
		else{
			title_view.setBackgroundResource(title_drawable_resId);
		}
		
		this.addView(title_view, title_lp);
		
		LinearLayout tab_layout = new LinearLayout(getContext());
		LayoutParams tab_lp = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

		this.addView(tab_layout, tab_lp);
		
		tab_btn_list = new ArrayList<TabFootButton>(tab_count);
		for(int i = 0;i < tab_count;i++){
			TabFootButton btn = new TabFootButton(getContext());
			btn.setScaleType(ScaleType.FIT_CENTER);
			
			if(tab_drawable_resId != -1){
				btn.setBackgroundResource(tab_drawable_resId);
			}
			
			if(i == defaultSelection){
				btn.setTabToggle(true);
			}
			
			btn.setOnClickListener(this);
			
			LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			if(i != 0){
				lp.leftMargin = tab_divider;
			}
			tab_layout.addView(btn, lp);
			
			tab_btn_list.add(btn);
		}
	}
	/**
	 * 设定是否可启用点击选择脚标
	 * @param tabClickEnable
	 */
	public void setTabClickEnable(boolean tabClickEnable){
		this.tabClickEnable = tabClickEnable;
	}
	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		if(!hasAdjustPosition){
			adjustPosition();
			hasAdjustPosition = true;
		}
		super.onWindowFocusChanged(hasWindowFocus);
	}
	
	private void adjustPosition(){
		if(tab_btn_list.size() > 0 && title_view.getWidth() != 0){
			TabFootButton tb = tab_btn_list.get(0);
			int title_view_left = tb.getLeft() + tb.getWidth() / 2 - title_view.getWidth() / 2;
			LayoutParams lp = (LayoutParams) title_view.getLayoutParams();
			lp.leftMargin = title_view_left;
			title_view.setLayoutParams(lp);
		}
	}
	
	@Override
	public void onClick(View v) {
		if(!tabClickEnable) return;
		animTabFoot(v);
	}

	private void animTabFoot(View v) {
		if(v != null && !(v instanceof TabFootButton)) return;
		if(tab_btn_list.indexOf(v) == currentSelected) return;
		
		currentSelected = tab_btn_list.indexOf(v);
		
		titleAnimation((TabFootButton)v);
		
		fireTabFootSelected((TabFootButton)v, currentSelected);
	}

	private void setSelectedItem() {
		for(int i = 0; i < tab_btn_list.size();i++){
			if( i == currentSelected){
				tab_btn_list.get(i).setTabToggle(true);
			}
			else if(tab_btn_list.get(i).isTabToggled()){
				tab_btn_list.get(i).setTabToggle(false);
			}
		}
	}
	
	private void fireTabFootSelected(TabFootButton tfb,int index){
		if(tabFootSelectListener != null){
			tabFootSelectListener.tabFootSelected(tfb, index);
		}
	}
	
	public void setOnTabFootSelectListener(OnTabFootSelectListener tabFootSelectListener){
		this.tabFootSelectListener = tabFootSelectListener;
	}
	
	public int getCurrentSelected(){
		return this.currentSelected;
	}
	
	public int getItemCount(){
		return tab_btn_list.size();
	}
	
	public void setSelectTabFoot(int index){
		if(index > tab_btn_list.size() - 1) return;
		animTabFoot(tab_btn_list.get(index));
	}
	
	private void titleAnimation(TabFootButton selectedFootButton){
		int start = title_view.getLeft();
		end = selectedFootButton.getLeft() + selectedFootButton.getWidth() / 2 - title_view.getWidth() / 2;
		
		TranslateAnimation ta = new TranslateAnimation(0, end - start, 0, 0);
		ta.setAnimationListener(this);
		ta.setDuration(tab_anim_duration);
		title_view.startAnimation(ta);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		setSelectedItem();
		
		title_view.clearAnimation();
		LayoutParams lp = (LayoutParams) title_view.getLayoutParams();
		lp.leftMargin = end;
		title_view.setLayoutParams(lp);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}
	@Override
	public void onAnimationStart(Animation animation) {
	}

}
