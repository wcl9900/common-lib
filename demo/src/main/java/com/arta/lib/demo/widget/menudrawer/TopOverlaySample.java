package com.arta.lib.demo.widget.menudrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.arta.lib.demo.R;
import com.arta.lib.widget.menudrawer.MenuDrawer;
import com.arta.lib.widget.menudrawer.Position;

public class TopOverlaySample extends Activity {

    private MenuDrawer mDrawer;

    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);
        mDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY, Position.TOP);
        mDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        mDrawer.setMenuView(R.layout.menu_top);

        TextView content = new TextView(this);
        content.setText("This is a sample of an overlayed top drawer.");
        content.setGravity(Gravity.CENTER);
        mDrawer.setContentView(content);
    }
}
