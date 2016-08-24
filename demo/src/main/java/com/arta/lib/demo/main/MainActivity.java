package com.arta.lib.demo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arta.lib.demo.R;
import com.arta.lib.demo.animation.activitytransition.ActivityTransitionMainActivity;
import com.arta.lib.demo.convenientbanner.ConvenientBannerMainActivity;
import com.arta.lib.demo.percent_support.PercentSupportExtendsMainActivity;
import com.arta.lib.demo.widget.aligntextview.example.AlignTextViewExample;
import com.arta.lib.demo.widget.animtabsview.AnimTabsViewMainActivity;
import com.arta.lib.demo.widget.asymmetricgridview.AsymmetricGridViewMainActivity;
import com.arta.lib.demo.widget.badgeview.BadgeViewMainActivity;
import com.arta.lib.demo.widget.circularprogressbutton.CircularProgressButtonMainActivity;
import com.arta.lib.demo.widget.cricle.CircleMainActivity;
import com.arta.lib.demo.widget.cricle.CircleMenuActivity;
import com.arta.lib.demo.widget.croppersample.CropperMainActivity;
import com.arta.lib.demo.widget.crouton.CroutonMainActivity;
import com.arta.lib.demo.widget.curl.CurlActivity;
import com.arta.lib.demo.widget.customfastscrollview.CustomFastScrollViewActivity;
import com.arta.lib.demo.widget.customimageshapeview.CustomShapeImageViewActivity;
import com.arta.lib.demo.widget.draggridview.DraggableGridViewSampleActivity;
import com.arta.lib.demo.widget.dragitemview.DragItemMainActivity;
import com.arta.lib.demo.widget.dragsortlistview.DragSortListViewMainActivity;
import com.arta.lib.demo.widget.expandablelayout.ExpandableLayoutMainActivity;
import com.arta.lib.demo.widget.expandabletextview.ExpandableTextviewDemoActivity;
import com.arta.lib.demo.widget.flipview.FlipViewMainActivity;
import com.arta.lib.demo.widget.flowtextview.FlowTextViewMainActivity;
import com.arta.lib.demo.widget.formedittextvalidator.FormEditTextValidatorActivity;
import com.arta.lib.demo.widget.gifview.GifViewTestActivity;
import com.arta.lib.demo.widget.glowpadview.glowpadview;
import com.arta.lib.demo.widget.gridviewwithhf.GridViewWithHFActivity;
import com.arta.lib.demo.widget.imagezoom.ImageViewZoomTestActivity;
import com.arta.lib.demo.widget.indexablelistview.IndexableListViewActivity;
import com.arta.lib.demo.widget.jumpingbeans.JumpingBeansMainActivity;
import com.arta.lib.demo.widget.kenburnsview.KenBurnsViewMainActivity;
import com.arta.lib.demo.widget.listviewanimations.ListViewAnimationsMainActivity;
import com.arta.lib.demo.widget.matchtextview.MatchTextViewActivity;
import com.arta.lib.demo.widget.menudrawer.SamplesActivity;
import com.arta.lib.demo.widget.miscwidgets.MiscWidgetsTestActivity;
import com.arta.lib.demo.widget.multiactiontextview.MultiActionTextviewMainActivity;
import com.arta.lib.demo.widget.multidirectionsdrawer.MultiDirectionSlidingDrawerMainActivity;
import com.arta.lib.demo.widget.multitouch.MutilTouchActivity;
import com.arta.lib.demo.widget.numberprogressbar.NumberProgressBarMainActivity;
import com.arta.lib.demo.widget.overscrollview.OverScrollViewActivity;
import com.arta.lib.demo.widget.page.PageTurnMainActivity;
import com.arta.lib.demo.widget.pageddragdropgrid.PagedDragdropGridActivity;
import com.arta.lib.demo.widget.pagerslidingtabstrip.PagerSlidingTabStripMainActivity;
import com.arta.lib.demo.widget.photoview.PhotoViewLauncherActivity;
import com.arta.lib.demo.widget.progresswheel.ProgressWheelMainActivity;
import com.arta.lib.demo.widget.pullrefreshlayout.PullRefreshLayoutDemoActivity;
import com.arta.lib.demo.widget.pulltorefresh.PullRefreshMainActivity;
import com.arta.lib.demo.widget.pulltorefreshandload.PullablePullRefreshViewMainActivity;
import com.arta.lib.demo.widget.pulltorefreshview.PullToRefreshViewActivity;
import com.arta.lib.demo.widget.residemenu.MenuActivity;
import com.arta.lib.demo.widget.roundimageview.RoundImageViewMainActivity;
import com.arta.lib.demo.widget.selectableroundedimageview.SelectableRoundedImageViewMainActivity;
import com.arta.lib.demo.widget.shimmer.ShimmerMainActivity;
import com.arta.lib.demo.widget.slideexpandable.SlideExpandableActivity;
import com.arta.lib.demo.widget.slidinguppanel.SlidingUpPanelDemoActivity;
import com.arta.lib.demo.widget.smoothprogressbar.SmoothProgressBarMainActivity;
import com.arta.lib.demo.widget.square_progressbar.SquareProgressBarMainActivity;
import com.arta.lib.demo.widget.staggeredgrid.StaggeredGridMainActivity;
import com.arta.lib.demo.widget.supertooltips.ToolTipsMainActivity;
import com.arta.lib.demo.widget.swipebacklayout.SwipeBackLayoutActivity;
import com.arta.lib.demo.widget.swipelayout.SwipeLayoutMainActivity;
import com.arta.lib.demo.widget.swipemenulistview.SwipeMenuListViewMainActivity;
import com.arta.lib.demo.widget.switchbutton.SwitchButtonMainActivity;
import com.arta.lib.demo.widget.tagcloud.TagCloudMainActivity;
import com.arta.lib.demo.widget.textdrawable.TextDrawableMainActivity;
import com.arta.lib.demo.widget.titanic.TitanicMainActivity;
import com.arta.lib.demo.widget.togglebutton.ToggleButtonMainActivity;
import com.arta.lib.demo.widget.treeview.TreeViewMainActivity;
import com.arta.lib.demo.widget.typefacehelper.TypefaceMainActivity;
import com.arta.lib.demo.widget.verificationcode.VerificationCodeActivity;
import com.arta.lib.demo.widget.viewpagerindicator.ListSamples;
import com.arta.lib.demo.widget.waveview.WaveViewMainActivity;
import com.arta.lib.demo.widget.wheel.WheelDemoActivity;
import com.arta.lib.demo.widget.xlistview.XListViewActivity;

import java.util.LinkedHashMap;

/**
 * common lib demo
 * @author 王春龙
 *
 */
public class MainActivity extends Activity implements OnItemClickListener{

	private ListView listview;

	@SuppressWarnings("rawtypes")
	private LinkedHashMap<String , Class> itemMap = new LinkedHashMap<String, Class>();

	private void initItemMap(){
		itemMap.put("ListViewAnimations",    		ListViewAnimationsMainActivity.class);
		itemMap.put("CustomFastScrollView",  		CustomFastScrollViewActivity.class);
		itemMap.put("IndexableListview",     		IndexableListViewActivity.class);
		itemMap.put("MenuDrawer" ,           		SamplesActivity.class);
		itemMap.put("ResideMenu" ,           		MenuActivity.class);
		itemMap.put("ViewPagerIndicator" ,   		ListSamples.class);
		itemMap.put("PullToRefresh" ,   	 		PullRefreshMainActivity.class);
		itemMap.put("DragSortListView" ,   	 		DragSortListViewMainActivity.class);
		itemMap.put("DragGridView" ,   	 	 		DraggableGridViewSampleActivity.class);
		itemMap.put("PhotoView" ,   	 	 		PhotoViewLauncherActivity.class);
		itemMap.put("CustomShapeImageView",  		CustomShapeImageViewActivity.class);
		itemMap.put("SmoothProgressBar",  	 		SmoothProgressBarMainActivity.class);
		itemMap.put("ProgressWheel",  	 	 		ProgressWheelMainActivity.class);
		itemMap.put("FlowTextView",  	 	 		FlowTextViewMainActivity.class);
		itemMap.put("FormEditTextValidatorActivity",FormEditTextValidatorActivity.class);
		itemMap.put("BadgeView",					BadgeViewMainActivity.class);
		itemMap.put("FlipView",						FlipViewMainActivity.class);
		itemMap.put("CircularProgressButton",		CircularProgressButtonMainActivity.class);
		itemMap.put("RoundImageView",		        RoundImageViewMainActivity.class);
		itemMap.put("Crouton",		                CroutonMainActivity.class);
		itemMap.put("ToolTips",		                ToolTipsMainActivity.class);
		itemMap.put("PagedDragdropGrid",		    PagedDragdropGridActivity.class);
		itemMap.put("AnimTabsView",		            AnimTabsViewMainActivity.class);
		itemMap.put("PagerSlidingTabStrip",		    PagerSlidingTabStripMainActivity.class);
		itemMap.put("ActivityTransition",		    ActivityTransitionMainActivity.class);
		itemMap.put("StaggeredGrid",		    	StaggeredGridMainActivity.class);
		itemMap.put("CircleMenu",		   			CircleMainActivity.class);
		itemMap.put("CircleMenu2",		   			CircleMenuActivity.class);
		itemMap.put("PageTurn",		   				PageTurnMainActivity.class);
		itemMap.put("glowpadview",		   			glowpadview.class);
		itemMap.put("Cropper",		   				CropperMainActivity.class);
		itemMap.put("MutilTouch",		   			MutilTouchActivity.class);
		itemMap.put("Titanic",		   				TitanicMainActivity.class);
		itemMap.put("JumpingBeans",		   			JumpingBeansMainActivity.class);
		itemMap.put("Page Curl",		   			CurlActivity.class);
		itemMap.put("SlidingUpPanel",		   		SlidingUpPanelDemoActivity.class);
		itemMap.put("Shimmer",		   				ShimmerMainActivity.class);
		itemMap.put("SquareProgressBar",		   	SquareProgressBarMainActivity.class);
		itemMap.put("TreeView",		   				TreeViewMainActivity.class);
		itemMap.put("GifView",		   				GifViewTestActivity.class);
		itemMap.put("NumberProgressBar",		   	NumberProgressBarMainActivity.class);
		itemMap.put("MultiDirectionSlidingDrawer",  MultiDirectionSlidingDrawerMainActivity.class);
		itemMap.put("MiscWidgets",  				MiscWidgetsTestActivity.class);
		itemMap.put("MultiActionTextview",  		MultiActionTextviewMainActivity.class);
		itemMap.put("SwipeBackLayout",  		    SwipeBackLayoutActivity.class);
		itemMap.put("Wheel",  		    			WheelDemoActivity.class);
		itemMap.put("KenBurnsView",  		    	KenBurnsViewMainActivity.class);
		itemMap.put("SlideExpandable",  		    SlideExpandableActivity.class);
		itemMap.put("SwipeLayout",  		        SwipeLayoutMainActivity.class);
		itemMap.put("ExpandableTextview",  		    ExpandableTextviewDemoActivity.class);
		itemMap.put("TextDrawable",  		        TextDrawableMainActivity.class);
		itemMap.put("ImageViewZoom",  		        ImageViewZoomTestActivity.class);
		itemMap.put("SelectableRoundedImageView",   SelectableRoundedImageViewMainActivity.class);
		itemMap.put("SwipeMenuListView",            SwipeMenuListViewMainActivity.class);
		itemMap.put("WaveView",                     WaveViewMainActivity.class);
		itemMap.put("ToggleButton",                 ToggleButtonMainActivity.class);
		itemMap.put("SwitchButton",                 SwitchButtonMainActivity.class);
		itemMap.put("DragItemView",                 DragItemMainActivity.class);
		itemMap.put("TypefaceHelper",               TypefaceMainActivity.class);
		itemMap.put("MatchTextView",                MatchTextViewActivity.class);
		itemMap.put("verificationCode",             VerificationCodeActivity.class);
		itemMap.put("PullToRefreshView",            PullToRefreshViewActivity.class);
		itemMap.put("AsymmetricGridView",           AsymmetricGridViewMainActivity.class);
		itemMap.put("ExpandableLayout",             ExpandableLayoutMainActivity.class);
		itemMap.put("PullRefreshLayout",            PullRefreshLayoutDemoActivity.class);
		itemMap.put("XListView",                    XListViewActivity.class);
		itemMap.put("PullablePullRefreshView",      PullablePullRefreshViewMainActivity.class);
		itemMap.put("GridViewWithHeaderFooter",     GridViewWithHFActivity.class);
		itemMap.put("OverScrollView",               OverScrollViewActivity.class);
		itemMap.put("ConvenientBanner",             ConvenientBannerMainActivity.class);
		itemMap.put("PercentSupportExtends",        PercentSupportExtendsMainActivity.class);
		itemMap.put("AlignTextView",                AlignTextViewExample.class);
		itemMap.put("TagCloud",                     TagCloudMainActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_main_layout);
		listview = (ListView)findViewById(R.id.demo_listview);
		initItemMap();

		String[] strs = new String[itemMap.size()];
		itemMap.keySet().toArray(strs);

		ArrayAdapter<String> ad = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, strs);
		listview.setAdapter(ad);
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent i = new Intent(this, itemMap.get(((TextView)view).getText()));
		startActivity(i);
	}
}
