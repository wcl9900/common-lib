package com.arta.lib.demo.widget.dragsortlistview;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arta.lib.demo.R;

//import com.mobeta.android.demodslv.R;


public class DragSortListViewMainActivity extends ListActivity {

    //private ArrayAdapter<ActivityInfo> adapter;
    private MyAdapter adapter;

    private ArrayList<Class> mActivities = null;

    private String[] mActTitles;
    private String[] mActDescs;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dragsortlistview_launcher);
        
        mActivities = new ArrayList<Class>();
        mActivities.add(TestBedDSLV.class);
        mActivities.add(ArbItemSizeDSLV.class);
        mActivities.add(WarpDSLV.class);
        mActivities.add(BGHandle.class);
        mActivities.add(Sections.class);
        mActivities.add(CursorDSLV.class);
        mActivities.add(MultipleChoiceListView.class);
        mActivities.add(SingleChoiceListView.class);

        mActTitles = getResources().getStringArray(R.array.activity_titles);
        mActDescs = getResources().getStringArray(R.array.activity_descs);

        //adapter = new ArrayAdapter<ActivityInfo>(this,
        //  R.layout.launcher_item, R.id.text, mActivities);
        adapter = new MyAdapter();

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
	    Intent intent = new Intent();
	    intent.setClass(this, mActivities.get(position));
	    startActivity(intent);
    }

    private class MyAdapter extends ArrayAdapter<Class> {
      MyAdapter() {
        super(DragSortListViewMainActivity.this, R.layout.dragsortlistview_launcher_item, R.id.activity_title, mActivities);
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        TextView title = (TextView) v.findViewById(R.id.activity_title);
        TextView desc = (TextView) v.findViewById(R.id.activity_desc);

        title.setText(mActTitles[position]);
        desc.setText(mActDescs[position]);
        return v;
      }

    }

}
