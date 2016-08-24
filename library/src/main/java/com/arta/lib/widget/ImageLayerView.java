package com.arta.lib.widget;

import com.arta.lib.util.ScreenUtils;

import com.arta.lib.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * 带有蒙版的图片视图
 * @author 王春龙
 *
 */
public class ImageLayerView extends FrameLayout {

	private ImageView iv_image;
	private ImageView iv_layer;
	
	private Drawable drawable_image;
	private Drawable drawable_layer;
	
	private ScaleType scaleType = ScaleType.FIT_CENTER;
	
	private int maxWidth = 0;
	private int maxHeight = 0;
	
	private boolean adjustViewBoundsEnable = false;
	
	private int imageWidth = LayoutParams.MATCH_PARENT;
	private int imageHeight = LayoutParams.MATCH_PARENT;
	
	private int imagePaddingLeft = 0, imagePaddingRight = 0, imagePaddingTop = 0, imagePaddingBottom = 0;
	private int layerPaddingLeft = 0, layerPaddingRight = 0, layerPaddingTop = 0, layerPaddingBottom = 0;
	
	public ImageLayerView(Context context) {
		super(context);
		init(null);
	}
	
	public ImageLayerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public ImageLayerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	private void init(AttributeSet attrs){
		initAttrs(attrs);
		initView();
	}
	private void initAttrs(AttributeSet attrs){
		if(attrs == null) return;
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ImageLayerView);
		
		drawable_image = ta.getDrawable(R.styleable.ImageLayerView_image);
		drawable_layer = ta.getDrawable(R.styleable.ImageLayerView_layer);
		
		int scaleType_int = ta.getInteger(R.styleable.ImageLayerView_scaleType, scaleType.ordinal());
		for(ScaleType type : ScaleType.values()){
			if(type.ordinal() == scaleType_int){
				scaleType = type;
				break;
			}
		}
		adjustViewBoundsEnable = ta.getBoolean(R.styleable.ImageLayerView_adjustViewBounds, adjustViewBoundsEnable);
		maxWidth = ta.getDimensionPixelSize(R.styleable.ImageLayerView_maxWidth, maxWidth);
		maxHeight = ta.getDimensionPixelSize(R.styleable.ImageLayerView_maxHeight, maxHeight);
		
		TypedValue tv = new TypedValue();
		ta.getValue(R.styleable.ImageLayerView_imageWidth, tv);
		if(tv.type == TypedValue.TYPE_DIMENSION){
			imageWidth = (int) tv.getDimension(getResources().getDisplayMetrics());
		}
		else if(tv.type == TypedValue.TYPE_INT_DEC){
			imageWidth = ta.getInteger(R.styleable.ImageLayerView_imageWidth, imageWidth);
		}
		
		ta.getValue(R.styleable.ImageLayerView_imageHeight, tv);
		if(tv.type == TypedValue.TYPE_DIMENSION){
			imageHeight = (int) tv.getDimension(getResources().getDisplayMetrics());
		}
		else if(tv.type == TypedValue.TYPE_INT_DEC){
			imageHeight = ta.getInteger(R.styleable.ImageLayerView_imageHeight, imageHeight);
		}
		
		imagePaddingLeft = ta.getDimensionPixelSize(R.styleable.ImageLayerView_imagePaddingLeft, imagePaddingLeft);
		imagePaddingRight = ta.getDimensionPixelSize(R.styleable.ImageLayerView_imagePaddingRight, imagePaddingRight);
		imagePaddingTop = ta.getDimensionPixelSize(R.styleable.ImageLayerView_imagePaddingTop, imagePaddingTop);
		imagePaddingBottom = ta.getDimensionPixelSize(R.styleable.ImageLayerView_imagePaddingLeft, imagePaddingBottom);
		
		layerPaddingLeft = ta.getDimensionPixelSize(R.styleable.ImageLayerView_layerPaddingLeft, layerPaddingLeft);
		layerPaddingRight = ta.getDimensionPixelSize(R.styleable.ImageLayerView_layerPaddingRight, layerPaddingRight);
		layerPaddingTop = ta.getDimensionPixelSize(R.styleable.ImageLayerView_layerPaddingTop, layerPaddingTop);
		layerPaddingBottom = ta.getDimensionPixelSize(R.styleable.ImageLayerView_layerPaddingLeft, layerPaddingBottom);
		
		ta.recycle();
	}
	private void initView(){
		iv_image = new ImageView(getContext());
		iv_layer = new ImageView(getContext());
		
		iv_image.setPadding(imagePaddingLeft, imagePaddingTop, imagePaddingRight, imagePaddingBottom);
		iv_layer.setPadding(layerPaddingLeft, layerPaddingTop, layerPaddingRight, layerPaddingBottom);
		
		setScaleType(scaleType);
		
		if(imageWidth >= 0){
			imageWidth = (int) ScreenUtils.dpToPxInt(getContext(), imageWidth);
		}
		if(imageHeight >= 0){
			imageHeight = (int)ScreenUtils.dpToPxInt(getContext(), imageHeight);
		}
		LayoutParams lp = new LayoutParams(imageWidth, imageHeight);

		if(drawable_image != null){
			setImageDrawable(drawable_image);
		}
		if(drawable_layer != null){
			setImageLayerDrawable(drawable_layer);
		}
		
		iv_image.setAdjustViewBounds(adjustViewBoundsEnable);
		iv_layer.setAdjustViewBounds(adjustViewBoundsEnable);
		if(adjustViewBoundsEnable){	
			iv_image.setMaxWidth(maxWidth);
			iv_image.setMaxHeight(maxHeight);
			
			iv_layer.setMaxWidth(maxWidth);
			iv_layer.setMaxHeight(maxHeight);

		}
		
		this.addView(iv_image, lp);
		this.addView(iv_layer, lp);
	}
	/**
	 * 视图缩放样式
	 * @param scaleType
	 */
	public void setScaleType(ScaleType scaleType){
		if(scaleType == null) return;
		
		iv_image.setScaleType(scaleType);
		iv_layer.setScaleType(scaleType);
	}
	/**
	 * 设定源图片
	 * @param drawable 图片视图
	 */
	public void setImageDrawable(Drawable drawable){
		iv_image.setImageDrawable(drawable);
	}
	/**
	 * 设定源图片
	 * @param resId 图片视图id
	 */
	public void setImageResource(int resId){
		iv_image.setImageResource(resId);
	}
	/**
	 * 设定蒙版图片视图
	 * @param drawable 图片视图
	 */
	public void setImageLayerDrawable(Drawable drawable){
		iv_layer.setImageDrawable(drawable);
	}
	/**
	 * 设定蒙版图片视图
	 * @param resId 图片视图id
	 */
	public void setImageLayerResource(int resId){
		iv_layer.setImageResource(resId);
	}
	
	/**
	 * 获取源图片资源视图
	 * @return
	 */
	public ImageView getImageView(){
		return this.iv_image;
	}
	/**
	 * 获取蒙版图片视图
	 * @return
	 */
	public ImageView getLayerView(){
		return this.iv_layer;
	}
	
	@Override
	public void setSelected(boolean selected) {
		iv_layer.setSelected(selected);
	}
	
	@Override
	public void setPressed(boolean pressed) {
		iv_layer.setPressed(pressed);
	}
}
