package com.arta.lib.widget;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;


//自己定义的Gallery
public class CoverFlow extends Gallery {

	private Camera mCamera = new Camera();
	private int mMaxRotationAngle = 20;
	private int mMaxZoom = -300;
	private int mCoveflowCenter;
	private boolean mAlphaMode = false;
	private boolean mCircleMode = false;

	public CoverFlow(Context context) {
		super(context);// Enable set transformation.  
		this.setStaticTransformationsEnabled(true);  
		// Enable set the children drawing order.  
		this.setChildrenDrawingOrderEnabled(true);  
	}

	public CoverFlow(Context context, AttributeSet attrs) {
		super(context, attrs);// Enable set transformation.  
		this.setStaticTransformationsEnabled(true);  
		// Enable set the children drawing order.  
		this.setChildrenDrawingOrderEnabled(true);  
	}

	public CoverFlow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);// Enable set transformation.  
		this.setStaticTransformationsEnabled(true);  
		// Enable set the children drawing order.  
		this.setChildrenDrawingOrderEnabled(true);  
	}

	public int getMaxRotationAngle() {
		return mMaxRotationAngle;
	}

	public void setMaxRotationAngle(int maxRotationAngle) {
		mMaxRotationAngle = maxRotationAngle;
	}

	public boolean getCircleMode() {
		return mCircleMode;
	}

	public void setCircleMode(boolean isCircle) {
		mCircleMode = isCircle;
	}

	public boolean getAlphaMode() {
		return mAlphaMode;
	}

	public void setAlphaMode(boolean isAlpha) {
		mAlphaMode = isAlpha;
	}

	public int getMaxZoom() {
		return mMaxZoom;
	}

	public void setMaxZoom(int maxZoom) {
		mMaxZoom = maxZoom;
	}

	private int getCenterOfCoverflow() {
		return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
				+ getPaddingLeft();
	}
	
	@Override  
	protected int getChildDrawingOrder(int childCount, int i)  
	{  
	    // Current selected index.  
	    int selectedIndex = getSelectedItemPosition() - getFirstVisiblePosition();  
	    if (selectedIndex < 0)   
	    {  
	        return i;  
	    }  
	      
	    if (i < selectedIndex)  
	    {  
	        return i;  
	    }  
	    else if (i >= selectedIndex)  
	    {  
	        return childCount - 1 - i + selectedIndex;  
	    }  
	    else  
	    {  
	        return i;  
	    }  
	}  
	
	private static int getCenterOfView(View view) {
		return view.getLeft() + view.getWidth() / 2;
	}
	//重写Garray方法 ，产生层叠和放大效果
	@Override
	protected boolean getChildStaticTransformation(View child, Transformation t) {
		final int childCenter = getCenterOfView(child);
		final int childWidth = child.getWidth();
		int rotationAngle = 0;
		t.clear();
		t.setTransformationType(Transformation.TYPE_MATRIX);
		if (childCenter == mCoveflowCenter) {
			transformImageBitmap(child, t, 0, 0);
		} else {
			rotationAngle = (int) (((float) (mCoveflowCenter - childCenter) / childWidth) * mMaxRotationAngle);
			// Log.d("test", "recanglenum:"+Math.floor ((mCoveflowCenter -
			// childCenter) / childWidth));
			if (Math.abs(rotationAngle) > mMaxRotationAngle) {
				rotationAngle = (rotationAngle < 0) ? -mMaxRotationAngle
						: mMaxRotationAngle;
			}
			transformImageBitmap(child, t, rotationAngle,
					(int) Math.floor((mCoveflowCenter - childCenter)/ (childWidth==0?1:childWidth)));
		}
		return true;
	}

	/**
	 * This is called during layout when the size of this view has changed. If
	 * you were just added to the view hierarchy, you're called with the old
	 * values of 0.
	 * 
	 * @param w
	 *            Current width of this view.
	 * @param h
	 *            Current height of this view.
	 * @param oldw
	 *            Old width of this view.
	 * @param oldh
	 *            Old height of this view.
	 */
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mCoveflowCenter = getCenterOfCoverflow();
		super.onSizeChanged(w, h, oldw, oldh);
	}

	/**
	 * Transform the Image Bitmap by the Angle passed
	 * 
	 * @param imageView
	 *            ImageView the ImageView whose bitmap we want to rotate
	 * @param t
	 *            transformation
	 * @param rotationAngle
	 *            the Angle by which to rotate the Bitmap
	 */
	private void transformImageBitmap(View child, Transformation t,
			int rotationAngle, int d) {
		mCamera.save();
		final Matrix imageMatrix = t.getMatrix();
		final int viewHeight = child.getLayoutParams().height;
		final int viewWidth = child.getLayoutParams().width;
		final int rotation = Math.abs(rotationAngle);
		mCamera.translate(0.0f, 0.0f, 100.0f);
		// As the angle of the view gets less, zoom in
		if (rotation <= mMaxRotationAngle) {
			float zoomAmount = (float) (mMaxZoom + (rotation * 1.5));
			mCamera.translate(0.0f, 0.0f, zoomAmount);
			if (mCircleMode) {
				if (rotation < 40)
					mCamera.translate(0.0f, 155, 0.0f);
				else
					mCamera.translate(0.0f, (255 - rotation * 2.5f), 0.0f);
			}
			if (mAlphaMode && child instanceof ImageView) {
				((ImageView)child).setAlpha((int) (255 - rotation * 2.5));
			}
		}
		mCamera.rotateY(rotationAngle);
		mCamera.getMatrix(imageMatrix);

		imageMatrix.postTranslate((viewWidth / 2), (viewHeight / 2));
		imageMatrix.preTranslate(-(viewWidth / 2), -(viewHeight / 2));
		mCamera.restore();
	}
}
