package com.arta.lib.widget.datalodinglayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.arta.lib.util.StringUtils;

import com.arta.lib.util.ResourceUtil;
import com.arta.lib.R;

/**
 * 数据加载视图状态切换布局控件，可支持 加载中,加载失败等显示,此控件中只能有一个子视图
 * @author 王春龙
 *
 */
public class DataLodingLayout extends FrameLayout {

	private ViewFlipper vf_content;
	
	private FrameLayout contentLayout;
	
	private TextView tv_loading;
	private TextView tv_fail;
	
	private ViewLoadType loadType = ViewLoadType.ContentView;
	
	private boolean loadSuccess = false;
	
	private boolean onceEnable = true;
	
	/**
	 * 默认数据加载切换布局，可同设定此静态变量定制数据加载切换布局
	 */
	public static int DEFAULT_DATA_LOADING_LAYOUT = R.layout.layout_data_loading_default;
	
	private OnDataLoadingListener loadingListener;
	
	public DataLodingLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(attrs);
	}

	public DataLodingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(attrs);
	}

	public DataLodingLayout(Context context) {
		super(context);
		initView(null);
	}

	private void initView(AttributeSet attrs){	
		int dataLayoutId = DEFAULT_DATA_LOADING_LAYOUT;
		if(attrs != null){
			TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DataLodingLayout);
			dataLayoutId = a.getResourceId(R.styleable.DataLodingLayout_define_dataLoading_layout, DEFAULT_DATA_LOADING_LAYOUT);
			a.recycle();
		}
		
		LayoutInflater.from(getContext()).inflate(dataLayoutId, DataLodingLayout.this);
		vf_content = (ViewFlipper) findViewById(R.id.data_loading_vf);

		contentLayout = (FrameLayout) vf_content.findViewById(R.id.data_loading_load_content);
		
		tv_loading = (TextView)vf_content.findViewById(R.id.data_loading_tv_loading_msg);
		tv_fail = (TextView)vf_content.findViewById(R.id.data_loading_tv_loading_fail);
		
		View load_fail = vf_content.findViewById(R.id.data_loading_load_failure);
		load_fail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(loadingListener != null){
					loadingListener.onRetryLoad(DataLodingLayout.this);
				}
			}
		});
		this.post(new Runnable() {	
			@Override
			public void run() {
				if(getChildCount() == 0) return;
				
				View content_view = null;
				for(int index = 0; index < getChildCount(); index++){
					if((content_view = getChildAt(index)) != vf_content){
						break;
					}
					else{
						content_view = null;
					}
				}
			
				if(content_view == null) return;
				DataLodingLayout.this.removeView(content_view);
				FrameLayout fl_content = (FrameLayout) vf_content.findViewById(R.id.data_loading_load_content);
				fl_content.addView(content_view);
			}
		});

	}
	
	/**
	 * 设定数据加载切换视图的样式布局，可根据不同的需求定制不同的布局，但布局中的资源Id必须使用预定义好的Id，样式布局可根据{@link R.layout.layout_data_loading_default}进行定制。
	 * @param layoutId
	 */
	@SuppressLint("CutPasteId")
	public void setDataLoadingStyleLayout(int layoutId){
		
		View dataView = null;
		int childCount = getChildCount();
		for(int i = 0; i < childCount; i++){
			if((dataView = getChildAt(i)).getId() != R.id.data_loading_vf){
				removeView(dataView);
				break;
			}
		}
		if(dataView == null){
			FrameLayout fl_content = (FrameLayout) vf_content.findViewById(R.id.data_loading_load_content);
			if(fl_content.getChildCount() != 0){
				dataView = fl_content.getChildAt(0);
				fl_content.removeView(dataView);
			}
		}
		
		this.removeAllViews();
		LayoutInflater.from(getContext()).inflate(layoutId, this);
		vf_content = (ViewFlipper) findViewById(R.id.data_loading_vf);
		contentLayout = (FrameLayout) vf_content.findViewById(R.id.data_loading_load_content);
		if(dataView != null){
			contentLayout.addView(dataView);
		}
	}
	
	/**
	 * 获取展示数据视图的父视图
	 * @return
	 */
	public FrameLayout getContentlayout(){
		return contentLayout;
	}
	
	/**
	 * 设定数据加载切换视图，是否只成功使用一次，也就是数据加载成功之后不能再进行视图的切换。
	 * @param onceEnable true：使用一次；false：可多次切换使用
	 */
	public void setOnceEnable(boolean onceEnable){
		this.onceEnable = onceEnable;
	}
	
	public void setOnDataLoadingListener(OnDataLoadingListener loadingListener){
		this.loadingListener = loadingListener;
	}
	
	public void setLoadingBackGroundColor(int color){
		vf_content.findViewById(R.id.data_loading_load_loading).setBackgroundColor(color);
	}

	public void setFailBackGroundColor(int color){
		vf_content.findViewById(R.id.data_loading_load_failure).setBackgroundColor(color);
	}

	public void setContentBackGroundColor(int color){
		vf_content.findViewById(R.id.data_loading_load_content).setBackgroundColor(color);
	}
	
	/**
	 * 判断当前数据加载视图类型
	 * @param loadType
	 * @return
	 */
	public boolean isViewLoadType(ViewLoadType loadType){
		return this.loadType.equals(loadType);
	}
	
	/**
	 * 加载视图类型
	 */
	public void showView(ViewLoadType viewLoadType){
		showView(viewLoadType, null);
	}

	/**
	 * 加载视图类型，设定提示信息
	 * @param viewLoadType
	 * @param msg
	 */
	public void showView(final ViewLoadType viewLoadType, final String msg){
		if (loadSuccess && onceEnable) return;
		
		this.loadType = viewLoadType;
		
		if(viewLoadType == ViewLoadType.ContentView){
			showType(0);
			loadSuccess = true;
		}
		else if(viewLoadType == ViewLoadType.LoadingView){
			post(new Runnable() {
				@Override
				public void run() {
					tv_loading.setText(StringUtils.isEmpty(msg) ? ResourceUtil.getString(getContext(), R.string.loading_layout_loading) : msg);
				}
			});
			showType(1);
		}
		else if(viewLoadType == ViewLoadType.FailureView){
			post(new Runnable() {
				@Override
				public void run() {
					tv_fail.setText(StringUtils.isEmpty(msg) ? ResourceUtil.getString(getContext(), R.string.loading_layout_fail) : msg);
				}
			});		
			showType(2);
		}
	}
	
	private void showType(int disPlay) {
		if(vf_content == null) return;
		vf_content.setDisplayedChild(disPlay);
	}
}
