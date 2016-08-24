package com.arta.lib.demo.widget.menudrawer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.arta.lib.demo.R;
import com.arta.lib.widget.menudrawer.MenuDrawer;
import com.arta.lib.widget.menudrawer.Position;

public class LeftOverlaySample extends Activity {

	private MenuDrawer mDrawer;
	private MenuAdapter mAdapter;
	private ListView mList;

	@Override
	protected void onCreate(Bundle inState) {
		super.onCreate(inState);
		mDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND);
//		mDrawer.setDropShadowColor(Color.YELLOW);
		mDrawer.setDropShadow(R.drawable.test);
		mDrawer.setDropShadowSize(100);
		// List<Object> items = new ArrayList<Object>();
		// items.add(new Item("Item 1", R.drawable.ic_action_refresh_dark));
		// items.add(new Item("Item 2", R.drawable.ic_action_select_all_dark));
		// items.add(new Category("Cat 1"));
		// items.add(new Item("Item 3", R.drawable.ic_action_refresh_dark));
		// items.add(new Item("Item 4", R.drawable.ic_action_select_all_dark));
		// items.add(new Category("Cat 2"));
		// items.add(new Item("Item 5", R.drawable.ic_action_refresh_dark));
		// items.add(new Item("Item 6", R.drawable.ic_action_select_all_dark));
		// items.add(new Category("Cat 3"));
		// items.add(new Item("Item 7", R.drawable.ic_action_refresh_dark));
		// items.add(new Item("Item 8", R.drawable.ic_action_select_all_dark));
		// items.add(new Category("Cat 4"));
		// items.add(new Item("Item 9", R.drawable.ic_action_refresh_dark));
		// items.add(new Item("Item 10", R.drawable.ic_action_select_all_dark));
		//
		// mList = new ListView(this);
		// mAdapter = new MenuAdapter(this, items);
		// mList.setAdapter(mAdapter);
		// mDrawer.setMenuView(mList);

		mDrawer.setMenuView(R.layout.menu_view);
		TextView content = new TextView(this);
		content.setText("This is a sample of an overlayed left drawer.");
		content.setGravity(Gravity.CENTER);
		mDrawer.setContentView(content);
		mDrawer.setSlideDrawable(R.drawable.ic_drawer);
		mDrawer.setDrawerIndicatorEnabled(true);
		mDrawer.peekDrawer(1000, 0);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mDrawer.toggleMenu();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
