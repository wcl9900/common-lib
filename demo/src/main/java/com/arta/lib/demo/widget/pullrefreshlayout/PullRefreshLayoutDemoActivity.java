package com.arta.lib.demo.widget.pullrefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.arta.lib.demo.R;
import com.arta.lib.widget.pullrefreshlayout.PullRefreshLayout;

public class PullRefreshLayoutDemoActivity extends Activity {

    PullRefreshLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pullrefreshlayout_activity_demo);

        String[] array = new String[50];
        for (int i = 0; i < array.length; i++) {
            array[i] = "string " + i;
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new android.widget.ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array));

        layout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 4000);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pullrefreshlayout_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_material:
                layout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);
                return true;
            case R.id.action_circles:
                layout.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES);
                return true;
            case R.id.action_water_drop:
                layout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);
                return true;
            case R.id.action_ring:
                layout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
