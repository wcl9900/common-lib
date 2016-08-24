package com.arta.lib.demo.widget.customimageshapeview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.arta.lib.demo.R;
import com.arta.lib.widget.customshapeimageview.CustomShapeImageView;
import com.arta.lib.widget.customshapeimageview.CustomShapeSquareImageView;

public class CustomShapeImageViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customshapeimageview_activity_samples);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new SvgImagesAdapter(this));
    }

    private class SvgImagesAdapter extends BaseAdapter {
        private List<Integer> mSvgRawResourceIds = new ArrayList<Integer>();

        private Context mContext;

        public SvgImagesAdapter(Context context) {
            mContext = context;

            mSvgRawResourceIds.add(com.arta.lib.R.raw.customshapeimageview_shape_star);
            mSvgRawResourceIds.add(com.arta.lib.R.raw.customshapeimageview_shape_heart);
            mSvgRawResourceIds.add(com.arta.lib.R.raw.customshapeimageview_shape_flower);
            mSvgRawResourceIds.add(com.arta.lib.R.raw.customshapeimageview_shape_star_2);
            mSvgRawResourceIds.add(com.arta.lib.R.raw.customshapeimageview_shape_star_3);
            mSvgRawResourceIds.add(com.arta.lib.R.raw.customshapeimageview_shape_circle_2);
            mSvgRawResourceIds.add(com.arta.lib.R.raw.customshapeimageview_shape_5);
        }

        @Override
        public int getCount() {
            return mSvgRawResourceIds.size();
        }

        @Override
        public Integer getItem(int i) {
            return mSvgRawResourceIds.get(i);
        }

        @Override
        public long getItemId(int i) {
            return mSvgRawResourceIds.get(i);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return new CustomShapeSquareImageView(mContext, R.drawable.customshapeimageview_sample_1,CustomShapeImageView.Shape.SVG, getItem(i));// It is just a sample ;)
        }

    }

}
