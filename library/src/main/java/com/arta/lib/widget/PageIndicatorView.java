package com.arta.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.arta.lib.R;

/**
 * 基于AdapterView的指示器，支持圆点和矩形条两种样式
 * @author 王春龙
 *
 */
public class PageIndicatorView extends View{
    private String TAG = "PageIndicatorView";
    
    private AdapterView<?> adapterView;
    
    private int selected_color = Color.YELLOW;
    private int unselected_color = Color.GRAY;
    
    private int DRAW_STYLE_CIRCLE = 0;
    private int DRAW_STYLE_RECT = 1;
    private int drawStyle = DRAW_STYLE_RECT;
    
    private float circle_unselected_radius = 20;
    private float circle_selected_radius = 20;
    
    private float indictor_unselected_width = 50;
    private float indictor_unselected_height = 15;
    private float indictor_selected_width = 50;
    private float indictor_selected_height = 15;
    
    private float space = 8;
    
    private Paint paint ;
    private int mCurrentPage = -1;
    private int mTotalPage = 0;

    public PageIndicatorView(Context context) {
        super(context);
        init();
    }

    public PageIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttrs(attrs);
    }

    public void setAdapterView(AdapterView<?> adapterView){
    	if(adapterView == null || adapterView.getAdapter() == null) return;
    	this.adapterView = adapterView;
    	mTotalPage = adapterView.getAdapter().getCount();
    	mCurrentPage = adapterView.getSelectedItemPosition();
    	((BaseAdapter)adapterView.getAdapter()).registerDataSetObserver(dataObserer);
    	this.invalidate();
    }
    
    private DataSetObserver dataObserer = new DataSetObserver() {
		@Override
		public void onChanged() {
	    	mTotalPage = adapterView.getAdapter().getCount();
	    	mCurrentPage = adapterView.getSelectedItemPosition();
	    	PageIndicatorView.this.invalidate();
		}
	};
    
    public void setTotalPage(int nPageNum) {
        mTotalPage = nPageNum;
        if (mCurrentPage >= mTotalPage)
            mCurrentPage = mTotalPage - 1;
    }
    
    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        paint.setStyle(Paint.Style.FILL);
    }
    
    private void initAttrs(AttributeSet attrs){
		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.PageIndicatorView);
		
		selected_color = a.getColor(R.styleable.PageIndicatorView_piv_selectedColor, selected_color);
		unselected_color = a.getColor(R.styleable.PageIndicatorView_piv_unselectedColor, unselected_color);
		space = a.getDimension(R.styleable.PageIndicatorView_piv_spacing, space);
		drawStyle = a.getInt(R.styleable.PageIndicatorView_piv_style, drawStyle);
		
		if(drawStyle ==DRAW_STYLE_CIRCLE){
			circle_unselected_radius = a.getDimension(R.styleable.PageIndicatorView_piv_circle_unselected_radius, circle_unselected_radius);
			circle_selected_radius = a.getDimension(R.styleable.PageIndicatorView_piv_circle_selected_radius, circle_selected_radius);
		}
		
		indictor_unselected_width = a.getDimension(R.styleable.PageIndicatorView_piv_indictor_unselected_width, indictor_unselected_width);
		indictor_unselected_height = a.getDimension(R.styleable.PageIndicatorView_piv_indictor_unselected_height, indictor_unselected_height);
		indictor_selected_width = a.getDimension(R.styleable.PageIndicatorView_piv_indictor_selected_width, indictor_selected_width);
		indictor_selected_height = a.getDimension(R.styleable.PageIndicatorView_piv_indictor_selected_height, indictor_selected_height);
		
		a.recycle();
    }
    
    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int nPageIndex) {
        if (nPageIndex < 0 || nPageIndex >= mTotalPage)
            return;

        if (mCurrentPage != nPageIndex) {
            mCurrentPage = nPageIndex;
            this.invalidate();
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//    	if(event.getAction() == MotionEvent.ACTION_DOWN){
//    		Rect r = new Rect();
//            this.getDrawingRect(r);
//    		int rx = r.left; 
//    		int x = (int) event.getX();
//    		int y = (int) event.getY();
//    		if(y >= r.top && y <= r.bottom){
//	    		for(int i =0;i < mTotalPage; i++){
//	    			if(x >= rx && x <= (rx + indictor_width)){
//	    				((FancyCoverFlow)adapterView).setSelection(i, true);
//	    				break;
//	    			}
//	    			rx += indictor_width + space;
//	    		}
//    		}
//    	}
//    	return super.onTouchEvent(event);
//    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect r = new Rect();
        this.getDrawingRect(r);

        int iconWidth = (int) indictor_unselected_width;
        int iconHeight = (int) indictor_unselected_height;
        
        if(r.width() < (iconWidth * mTotalPage + space * (mTotalPage - 1))){
        	iconWidth = (int) ((r.width() - space * (mTotalPage - 1)) / mTotalPage);
        }
        if(r.height() < iconHeight){
        	iconHeight = r.height();
        }
        
        int x = (int) ((r.width() - (iconWidth * mTotalPage + space * (mTotalPage - 1))) / 2);
        int y = (r.height() - iconHeight) / 2;

        for (int i = 0; i < mTotalPage; i++) {

            Rect r1 = new Rect();
            r1.left = x;
            r1.top = y;
            r1.right = x + iconWidth;
            r1.bottom = y + iconHeight;
            
        	if (i == mCurrentPage) {
            	paint.setColor(selected_color);
            	r1.top = (int) ((r.height() - indictor_selected_height) / 2.0f);
                r1.bottom = (int) (r1.top + indictor_selected_height);
            }
            else{
            	paint.setColor(unselected_color);
            }
            
            if(drawStyle == DRAW_STYLE_RECT){
            	canvas.drawRect(r1, paint);
            }
            else if(drawStyle == DRAW_STYLE_CIRCLE){
            	canvas.drawCircle(r1.left, r1.top, i == mCurrentPage ? circle_selected_radius : circle_unselected_radius, paint);
            }
            
            x += iconWidth + space;
        }
    }

}