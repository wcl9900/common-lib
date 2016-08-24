package com.arta.lib.demo.widget.treeview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;

import com.arta.lib.util.ToastUtils;

import com.arta.lib.demo.R;
import com.arta.lib.widget.treeview.TreeView;

public class TreeViewMainActivity extends Activity {
	private TreeView treeView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treeview_activity_main);

		initView();
	}

	private void initView() {
		treeView = (TreeView) findViewById(R.id.tree_view);
		treeView.setHeaderView(getLayoutInflater().inflate(
				R.layout.treeview_list_head_view, treeView, false));
		final TreeViewAdapter treeViewAdapter = new TreeViewAdapter(this,
				treeView);
		treeView.setAdapter(treeViewAdapter);
		// 处理全部展开
		for (int i = 0; i < treeViewAdapter.getGroupCount(); i++) {
			treeView.expandGroup(i);
		}

		// 处理全部展开以后不关闭
		treeView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				treeView.expandGroup(groupPosition);
			}
		});

		treeView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				ToastUtils.show(getBaseContext(), "group:" + groupPosition);
				return false;
			}
		});

		treeView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				ToastUtils.show(getBaseContext(), "group pos:" + groupPosition
						+ "child pos:" + childPosition);
				return false;
			}
		});
	}
}
