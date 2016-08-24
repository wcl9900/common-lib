package com.arta.lib.demo.widget.dragitemview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.arta.lib.util.ToastUtils;

import com.arta.lib.adapter.BaseAdapterEntityViewManage;
import com.arta.lib.adapter.BaseEntityViewAdapter;
import com.arta.lib.demo.R;
import com.arta.lib.widget.miscwidgets.widget.Panel;
import com.arta.lib.wrapper.DragItemViewWrapper;
import com.arta.lib.wrapper.DragItemViewWrapper.OnDragItemListener;
import com.arta.lib.wrapper.DragItemViewWrapper.onImageViewGetListener;

public class DragItemMainActivity extends Activity {
	private AbsListView dragGridView, dragGridView1;
	private List<String> entityList;
	private Panel leftPanel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
		
		setContentView(R.layout.drag_item_layout);
		
		leftPanel = (Panel)findViewById(R.id.leftPanel1);
		
		dragGridView = (AbsListView)findViewById(R.id.myDragGridView);
		DragItemViewWrapper dragItemViewWrapper = new DragItemViewWrapper(dragGridView);
		dragGridView1 = (AbsListView)findViewById(R.id.myDragGridView1);
		DragItemViewWrapper dragItemViewWrapper1 = new DragItemViewWrapper(dragGridView1);
		
		dragItemViewWrapper.setDragVibratorEnable(true);
		dragItemViewWrapper1.setDragVibratorEnable(true);
		
		onImageViewGetListener imageViewGetListener = new onImageViewGetListener() {
			
			@Override
			public ImageView getImageView(View itemView) {
				return (ImageView) itemView.findViewById(R.id.iv_drag_item);
			}
		};
		dragItemViewWrapper.setOnImageViewGetListener(imageViewGetListener);
		dragItemViewWrapper1.setOnImageViewGetListener(imageViewGetListener);
		
		OnDragItemListener listener = new OnDragItemListener() {
			
			@Override
			public void onDragItemStart(AbsListView absListView, View dragView, int position, MotionEvent event) {
				ToastUtils.show(getApplicationContext(), "drag start:"+position + " px:"+event.getRawX() + " py:"+event.getRawY());
			}
			
			@Override
			public void OnDragItemEnd(AbsListView absListView, View dragView, int position, MotionEvent event) {
				ToastUtils.show(getApplicationContext(), "drag end:"+position + " px:"+event.getRawX() + " py:"+event.getRawY());
			}

			@Override
			public void onDragItemStatic(AbsListView absListView,
					View itemView, int position, MotionEvent event) {
				ToastUtils.show(getApplicationContext(), "drag static:"+position + " px:"+event.getRawX() + " py:"+event.getRawY());
			}
		};
		dragItemViewWrapper.setOnDragListener(listener);
		dragItemViewWrapper1.setOnDragListener(listener);
		
		entityList = new ArrayList<String>();
		for(int i = 0; i< 30; i++){
			entityList.add(i+"");
		}
		dragGridView.setAdapter(new BaseEntityViewAdapter<String>(this, entityList, vm));
		dragGridView1.setAdapter(new BaseEntityViewAdapter<String>(this, entityList, vm));
		OnItemClickListener listener2 = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ToastUtils.show(getApplicationContext(), "position: "+position+"");
			}
		};
		dragGridView.setOnItemClickListener(listener2);
		dragGridView1.setOnItemClickListener(listener2);
	}
	
	private BaseAdapterEntityViewManage<String> vm = new BaseAdapterEntityViewManage<String>() {

		@Override
		public View getAdapterItemView(Context context, String entity,
				int position) {
			View inflate = LayoutInflater.from(context).inflate(R.layout.drag_item, null);
			((TextView)inflate.findViewById(R.id.tv_drag_item)).setText("pos:"+entity);
			ImageView iv = (ImageView) inflate.findViewById(R.id.iv_drag_item);
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setAdjustViewBounds(true);
			iv.setImageResource(R.drawable.drag_item_test_icon);
			return inflate;
		}

		@Override
		public View updateAdapterItemView(Context context, View updateView,
				String entity, int position) {
			View inflate = updateView;
			((TextView)inflate.findViewById(R.id.tv_drag_item)).setText("pos:"+entity);
			ImageView iv = (ImageView) inflate.findViewById(R.id.iv_drag_item);
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setAdjustViewBounds(true);
			iv.setImageResource(R.drawable.drag_item_test_icon);
			return inflate;
		}
	};
}
