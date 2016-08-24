package com.arta.lib.demo.widget.viewpagerindicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.arta.lib.demo.R;
import com.arta.lib.widget.viewpagerindicator.TitlePageIndicator;

public class SampleTitlesStyledTheme extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //The look of this sample is set via a style in the manifest
        setContentView(R.layout.simple_titles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}