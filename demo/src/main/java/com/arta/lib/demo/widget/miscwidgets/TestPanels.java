package com.arta.lib.demo.widget.miscwidgets;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.arta.lib.demo.R;
import com.arta.lib.widget.miscwidgets.interpolator.BackInterpolator;
import com.arta.lib.widget.miscwidgets.interpolator.BounceInterpolator;
import com.arta.lib.widget.miscwidgets.interpolator.EasingType.Type;
import com.arta.lib.widget.miscwidgets.interpolator.ElasticInterpolator;
import com.arta.lib.widget.miscwidgets.interpolator.ExpoInterpolator;
import com.arta.lib.widget.miscwidgets.widget.Panel;
import com.arta.lib.widget.miscwidgets.widget.Panel.OnPanelListener;

public class TestPanels extends Activity implements OnPanelListener {

	private Panel bottomPanel;
	private Panel topPanel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miscwidgets_panel_main);

        Panel panel;
        
        topPanel = panel = (Panel) findViewById(R.id.topPanel);
        panel.setOnPanelListener(this);
        panel.setInterpolator(new BounceInterpolator(Type.OUT));
        
        panel = (Panel) findViewById(R.id.leftPanel1);
        panel.setOnPanelListener(this);
        panel.setInterpolator(new BackInterpolator(Type.OUT, 2));

        panel = (Panel) findViewById(R.id.leftPanel2);
        panel.setOnPanelListener(this);
        panel.setInterpolator(new BackInterpolator(Type.OUT, 2));

        panel = (Panel) findViewById(R.id.rightPanel);
        panel.setOnPanelListener(this);
        panel.setInterpolator(new ExpoInterpolator(Type.OUT));

        bottomPanel = panel = (Panel) findViewById(R.id.bottomPanel);
        panel.setOnPanelListener(this);
        panel.setInterpolator(new ElasticInterpolator(Type.OUT, 1.0f, 0.3f));
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_T) {
    		topPanel.setOpen(!topPanel.isOpen(), false);
    		return false;
    	}
    	if (keyCode == KeyEvent.KEYCODE_B) {
    		bottomPanel.setOpen(!bottomPanel.isOpen(), true);
    		return false;
    	}
    	return super.onKeyDown(keyCode, event);
    }

	public void onPanelClosed(Panel panel) {
		String panelName = getResources().getResourceEntryName(panel.getId());
		Log.d("TestPanels", "Panel [" + panelName + "] closed");
	}
	public void onPanelOpened(Panel panel) {
		String panelName = getResources().getResourceEntryName(panel.getId());
		Log.d("TestPanels", "Panel [" + panelName + "] opened");
	}
}
