package com.arta.lib.demo.widget.pulltorefreshview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.arta.lib.demo.R;
import com.arta.lib.widget.pulltorefreshview.library.PullToRefreshView;

public class PullToRefreshViewActivity extends Activity {

    public static final int REFRESH_DELAY = 2000;

    private PullToRefreshView mPullToRefreshView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pulltorefreshview_activity_pull_to_refresh);

        Map<String, Integer> map;
        List<Map<String, Integer>> sampleList = new ArrayList();

        int[] icons = {
                R.drawable.pulltorefreshview_icon_1,
                R.drawable.pulltorefreshview_icon_2,
                R.drawable.pulltorefreshview_icon_3};

        int[] colors = {
                R.color.saffron,
                R.color.eggplant,
                R.color.sienna};

        for (int i = 0; i < icons.length; i++) {
            map = new HashMap();
            map.put(SampleAdapter.KEY_ICON, icons[i]);
            map.put(SampleAdapter.KEY_COLOR, colors[i]);
            sampleList.add(map);
        }

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new SampleAdapter(this, R.layout.pulltorefreshview_list_item, sampleList));

        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });
    }

    class SampleAdapter extends ArrayAdapter<Map<String, Integer>> {

        public static final String KEY_ICON = "icon";
        public static final String KEY_COLOR = "color";

        private final LayoutInflater mInflater;
        private final List<Map<String, Integer>> mData;

        public SampleAdapter(Context context, int layoutResourceId, List<Map<String, Integer>> data) {
            super(context, layoutResourceId, data);
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.pulltorefreshview_list_item, parent, false);
                viewHolder.imageViewIcon = (ImageView) convertView.findViewById(R.id.image_view_icon);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.imageViewIcon.setImageResource(mData.get(position).get(KEY_ICON));
            convertView.setBackgroundResource(mData.get(position).get(KEY_COLOR));

            return convertView;
        }

        class ViewHolder {
            ImageView imageViewIcon;
        }

    }

}
