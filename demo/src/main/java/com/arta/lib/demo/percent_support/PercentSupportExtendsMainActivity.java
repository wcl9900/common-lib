package com.arta.lib.demo.percent_support;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arta.lib.demo.R;


public class PercentSupportExtendsMainActivity extends Activity
{
    private ListView mListView;
    private LayoutInflater mInflater;

    private static final int LISTVIEW_POS = 6;

    private String[] mTitle = {
            "PercentLinearLayout",
            "PercentW or PercentH",
            "PercentRelativeLayout 1",
            "PercentFrameLayout",
            "PercentRelativeLayout 2",
            "PercentLinearLayout in ScrollView2",
            "PercentInListView",
            "PercentPadding",
            "PercentScreen[Width|Height]"
    };
    private int[] mContentIds = {
            R.layout.percent_support_extends_view5,
            R.layout.percent_support_extends_view1,
            R.layout.percent_support_extends_view2,
            R.layout.percent_support_extends_view3,
            R.layout.percent_support_extends_view4,
            R.layout.percent_support_extends_view6,
            -1,
            R.layout.percent_support_extends_view7_padding,
            R.layout.percent_support_extends_percent_sc_sw
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percent_support_extends_activity_category);

        mInflater = LayoutInflater.from(this);
        mListView = (ListView) findViewById(R.id.id_listview);

        mListView.setAdapter(new ArrayAdapter<String>(this, -1, mTitle)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = mInflater.inflate(R.layout.percent_support_extends_item_category, parent, false);
                }
                TextView tv = (TextView) convertView.findViewById(R.id.id_title);
                tv.setText(getItem(position));
                return convertView;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case LISTVIEW_POS:
                        Intent intent = new Intent(PercentSupportExtendsMainActivity.this, ListViewTestActivity.class) ;
                        startActivity(intent);
                        return ;
                }


                Intent intent = new Intent(PercentSupportExtendsMainActivity.this, ItemActivity.class);
                intent.putExtra("contentId", mContentIds[position]);
                intent.putExtra("title",mTitle[position]);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
