package com.arta.lib.util;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

public class CommonUtil {

	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				res.getDisplayMetrics());
	}

	public static float dpToPx(Resources res, float dp) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				res.getDisplayMetrics());
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);

		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);

		return bitmap;
	}

	public static int getScreenWidth(Context context) {

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);

		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}

	public static int getScreenHeight(Context context) {

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);

		int height = wm.getDefaultDisplay().getHeight();
		return height;
	}

	/**
	 * 按比例设定基于活动窗口的View尺寸大小
	 * 
	 * @param context
	 * @param view
	 * @param bili
	 *            高/宽
	 * @param windowHeightPercent
	 *            view所占活动窗口比例
	 */
	public static void setViewHeightSize(Context context, View view,
			float bili, float windowHeightPercent) {
		int outHeight = (int) ((float) (CommonUtil.getScreenHeight(context) - getStatusBarHeight(context)) * windowHeightPercent);
		int outWidth = (int) (((float) outHeight) / bili);
		view.getLayoutParams().width = outWidth;
		view.getLayoutParams().height = outHeight;
	}

	/**
	 * 按比例设定基于屏幕宽度的View尺寸大小
	 * 
	 * @param context
	 * @param view
	 * @param bili
	 *            宽/高
	 * @param screenHeightPercent
	 *            view所占屏幕宽度比例
	 */
	public static void setViewWidthSize(Context context, View view, float bili,
			float screenWidthPercent) {
		int outWidth = (int) ((float) CommonUtil.getScreenWidth(context) * screenWidthPercent);
		int outHeight = (int) (((float) outWidth) / bili);
		view.getLayoutParams().width = outWidth;
		view.getLayoutParams().height = outHeight;
	}

	/**
	 * 获取状态栏高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	private static Rect windowBounds = null;

	/**
	 * 获取活动窗口尺寸
	 * 
	 * @param activity
	 * @return
	 */
	public static Rect getWindowSize(Activity activity) {
		if (windowBounds != null)
			return windowBounds;

		WindowManager wm = activity.getWindowManager();
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		int window_width = dm.widthPixels;
		int window_height = dm.heightPixels;
		if ((activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != WindowManager.LayoutParams.FLAG_FULLSCREEN) {
			window_height -= getStatusBarHeight(activity);
		}

		windowBounds = new Rect(0, 0, window_width, window_height);

		return windowBounds;
	}

	public void get(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		int h_screen = dm.heightPixels;

	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

}
