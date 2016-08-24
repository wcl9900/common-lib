package com.arta.lib.demo.widget.swipelayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.arta.lib.demo.R;
import com.arta.lib.demo.widget.swipelayout.adapter.GridViewAdapter;
import com.arta.lib.widget.swipelayout.implments.SwipeItemMangerImpl;

public class GridViewExample extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipelayout_gridview);
        final GridView gridView = (GridView)findViewById(R.id.gridview);
        final GridViewAdapter adapter = new GridViewAdapter(this);
        adapter.setMode(SwipeItemMangerImpl.Mode.Multiple);
        gridView.setAdapter(adapter);
        gridView.setSelected(false);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemLongClick","onItemLongClick:" + position);
                return false;
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemClick","onItemClick:" + position);
            }
        });


        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemSelected","onItemSelected:" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
