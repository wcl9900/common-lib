package com.arta.lib.demo.widget.expandabletextview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arta.lib.demo.R;
import com.arta.lib.widget.expandabletextview.ExpandableTextView;

/**
 * Demo Activity which demos the features of the ExpandableTextView.
 *
 * @author Manabu-GT
 */
public class ExpandableTextviewDemoActivity extends FragmentActivity{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandabletextview_activity_demo);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expandabletextview_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return Demo1Fragment.newInstance();
            } else {
                return Demo2Fragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_demo1);
                case 1:
                    return getString(R.string.title_demo2);
            }
            return null;
        }
    }

    public static class Demo1Fragment extends Fragment {

        public static Demo1Fragment newInstance() {
            Demo1Fragment fragment = new Demo1Fragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.expandabletextview_fragment_demo1, container, false);

            ((TextView) rootView.findViewById(R.id.sample1).findViewById(R.id.title)).setText("Sample 1");
            ((TextView) rootView.findViewById(R.id.sample2).findViewById(R.id.title)).setText("Sample 2");

            ExpandableTextView expTv1 = (ExpandableTextView) rootView.findViewById(R.id.sample1)
                    .findViewById(R.id.expand_text_view);
            final ExpandableTextView expTv2 = (ExpandableTextView) rootView.findViewById(R.id.sample2)
                    .findViewById(R.id.expand_text_view);

            expTv1.setText(getString(R.string.dummy_text1));
            expTv2.setText(getString(R.string.dummy_text2));

            return rootView;
        }
    }

    public static class Demo2Fragment extends ListFragment {

        public static Demo2Fragment newInstance() {
            Demo2Fragment fragment = new Demo2Fragment();
            return fragment;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            SampleTextListAdapter adapter = new SampleTextListAdapter(getActivity());
            setListAdapter(adapter);
        }
    }
}
