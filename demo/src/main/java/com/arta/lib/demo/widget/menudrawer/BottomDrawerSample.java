package com.arta.lib.demo.widget.menudrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.arta.lib.demo.R;
import com.arta.lib.widget.menudrawer.MenuDrawer;
import com.arta.lib.widget.menudrawer.Position;

/**
 * Sample class illustrating how to add a menu drawer above the content area.
 */
public class BottomDrawerSample extends Activity implements OnClickListener {

    private MenuDrawer mMenuDrawer;
    private TextView mContentTextView;

    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);

        mMenuDrawer = MenuDrawer.attach(this, Position.BOTTOM);
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        mMenuDrawer.setContentView(R.layout.activity_bottommenu);
        mMenuDrawer.setMenuView(R.layout.menu_bottom);

        mContentTextView = (TextView) findViewById(R.id.contentText);
        findViewById(R.id.item1).setOnClickListener(this);
        findViewById(R.id.item2).setOnClickListener(this);
    }

    /**
     * Click handler for bottom drawer items.
     */
    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        mContentTextView.setText(String.format("%s clicked.", tag));
        mMenuDrawer.setActiveView(v);
    }
}
