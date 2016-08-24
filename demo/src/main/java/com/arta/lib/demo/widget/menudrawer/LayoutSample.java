package com.arta.lib.demo.widget.menudrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.arta.lib.demo.R;
import com.arta.lib.widget.menudrawer.MenuDrawer;

public class LayoutSample extends Activity implements View.OnClickListener {

    private MenuDrawer mDrawer;
    private TextView mContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutsample);
        mDrawer = (MenuDrawer) findViewById(R.id.drawer);
        mDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);

        mContentTextView = (TextView) findViewById(R.id.contentText);
        findViewById(R.id.item1).setOnClickListener(this);
        findViewById(R.id.item2).setOnClickListener(this);
    }

    /**
     * Click handler for top drawer items.
     */
    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        mContentTextView.setText(String.format("%s clicked.", tag));
        mDrawer.setActiveView(v);
    }
}
