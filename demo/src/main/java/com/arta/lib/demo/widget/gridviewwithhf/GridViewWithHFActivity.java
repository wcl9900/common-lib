package com.arta.lib.demo.widget.gridviewwithhf;


import java.util.ArrayList;
import java.util.List;

import com.arta.lib.adapter.BaseAdapterEntityViewManage;
import com.arta.lib.adapter.BaseEntityViewAdapter;
import com.arta.lib.demo.R;
import com.arta.lib.widget.gridviewwithhf.GridViewWithHeaderAndFooter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GridViewWithHFActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridviewwithhf);
		GridViewWithHeaderAndFooter gvhf = (GridViewWithHeaderAndFooter)findViewById(R.id.gvhf);
		Button btn_header = new Button(this);
		btn_header.setText("header");
		Button btn_footer = new Button(this);
		btn_footer.setText("footer");
		
		gvhf.addHeaderView(btn_header);
		gvhf.addFooterView(btn_footer);
		
		List<String> strs = new ArrayList<String>();
		for(int i = 0; i < 200; i++){
			strs.add(""+i);
		}
		gvhf.setAdapter(new BaseEntityViewAdapter<String>(this, strs, viewManage));
	}
	
	private BaseAdapterEntityViewManage<String> viewManage = new BaseAdapterEntityViewManage<String>() {
		
		@Override
		public View updateAdapterItemView(Context context, View updateView,
				String entity, int position) {
			Button btn = (Button)updateView;
			btn.setText(entity);
			return updateView;
		}
		
		@Override
		public View getAdapterItemView(Context context, String entity, int position) {
			return new Button(context);
		}
	};
}
