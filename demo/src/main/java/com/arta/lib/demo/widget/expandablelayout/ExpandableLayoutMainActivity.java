package com.arta.lib.demo.widget.expandablelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.arta.lib.demo.R;
import com.arta.lib.widget.expandablelayout.ExpandableLayoutListView;


public class ExpandableLayoutMainActivity extends Activity {

    private final String[] array = {"Hello", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandablelayout_activity_main);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.expandablelayout_view_row, R.id.header_text, array);
        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);
        expandableLayoutListView.setAdapter(arrayAdapter);
    }
}
