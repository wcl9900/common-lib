package com.arta.lib.demo.widget.viewpagerindicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.arta.lib.demo.R;
import com.arta.lib.widget.viewpagerindicator.LinePageIndicator;

public class SampleLinesStyledLayout extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themed_lines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (LinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}