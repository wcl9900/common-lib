package com.arta.lib.demo.widget.animtabsview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arta.lib.demo.R;
import com.arta.lib.widget.animtabsview.AnimTabsView;

public class AnimTabsViewMainActivity extends FragmentActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animtabsview_activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private AnimTabsView mTabsView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.animtabsview_fragment_main, container, false);
			setupViews(rootView);
			return rootView;
		}
		
		private void setupViews(View rootView) {
			mTabsView = (AnimTabsView) rootView.findViewById(R.id.publiclisten_tab);
			mTabsView.addItem("�Ƽ�");
			mTabsView.addItem("���а�");
			mTabsView.addItem("�赥");
			mTabsView.addItem("DJ��Ŀ");

            mTabsView.setOnAnimTabsItemViewChangeListener(new AnimTabsView.IAnimTabsItemViewChangeListener() {
                @Override
                public void onChange(AnimTabsView tabsView, int oldPosition, int currentPosition) {
                }
            });
		}
	}

}
