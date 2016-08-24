package com.arta.lib.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
/**
 * 资源工具类
 * @author 王春龙
 *
 */
public class ResourceUtil {
	/**
	* 获取本地图片并指定高度和宽度
	*/
	public static Bitmap getNativeImage(String imagePath)
	{
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 获取这个图片的宽和高
		Bitmap myBitmap = BitmapFactory.decodeFile(imagePath, options); //此时返回myBitmap为空
		//计算缩放比
		int be = (int)(options.outHeight / (float)200);
		int ys = options.outHeight % 200;//求余数
		float fe = ys / (float)200;
		if (fe >= 0.5)
		be = be + 1;
		if (be <= 0)
		be = 1;
		options.inSampleSize = be;
		//重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false
		options.inJustDecodeBounds = false;
		myBitmap = BitmapFactory.decodeFile(imagePath, options);
		return myBitmap;
	}

	/** 
	* 读取本地资源的图片
	* @param context 
	* @param resId 
	* @return 
	*/
	public static Bitmap getResBitmap(Context context, int resId)
	{
		return getResBitmap(context , resId , 1);
	}
	
	public static Bitmap getResBitmap(Context context, int resId, Config config){
		return getResBitmap(context, resId, 1, config);
	}
	
	public static Bitmap getResBitmap(Context context, int resId, int compressSize, Config config){
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = config;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		opt.inSampleSize = compressSize;
		
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}
	
	/**
	 * 压缩读取资源图片
	 * @param context
	 * @param resId
	 * @param compressSize
	 * @return
	 */
	public static Bitmap getResBitmap(Context context, int resId , int compressSize)
	{
		return getResBitmap(context, resId, compressSize, Config.RGB_565);
	}
	
	public static BitmapDrawable getResBitmapDrawable(Context context, int resId , int compressSize){
		return new BitmapDrawable(context.getResources(), getResBitmap(context , resId , compressSize));
	}
	
	public static BitmapDrawable getResBitmapDrawable(Context context, int resId ){
		return new BitmapDrawable(context.getResources(), getResBitmap(context , resId , 1));
	}
	/**
	 * 预读取资源图片大小
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Rect readBitmapBounds(Context context, int resId){
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		opt.inJustDecodeBounds = true;
		
		BitmapFactory.decodeStream(context.getResources().openRawResource(resId), null, opt);
		
		return new Rect(0 , 0 , opt.outWidth , opt.outHeight);
	}
	/** 
	* 读取本地资源的图片 或者SDCard中的图片
	* @param imagePath 
	* 图片在SDCard中的路径
	* @return 
	*/
	public static Bitmap getSDCardImg(String imagePath)
	{
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		//获取资源图片 
		return BitmapFactory.decodeFile(imagePath, opt);
	}
	
	/**
	 * 读取asset资源文件夹下的图片
	 * @param context
	 * @param assetPath
	 * @return
	 */
	public static Bitmap getAssetBitmap(Context context , String assetPath){
		return getAssetBitmap(context, assetPath, 1);
	}
	
	/**
	 * 压缩读取资源图片
	 * @param context
	 * @param assetPath
	 * @param compressSize
	 * @param config
	 * @return
	 */
	public static Bitmap getAssetBitmap(Context context , String assetPath, int compressSize, Config config){
		InputStream is;
		try {
			is = context.getResources().getAssets().open(assetPath);
			return getAssetBitmap(context, is, compressSize, config);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 压缩读取资源图片
	 * @param context
	 * @param assetPath
	 * @param compressSize
	 * @return
	 */
	public static Bitmap getAssetBitmap(Context context , String assetPath,int compressSize){
		return getAssetBitmap(context, assetPath, compressSize, Bitmap.Config.RGB_565);
	}
	
	public static BitmapDrawable getAssetBitmapDrawable(Context context , String assetPath){
		return new BitmapDrawable(context.getResources(), getAssetBitmap(context, assetPath));
	}
	
	public static BitmapDrawable getAssetBitmapDrawable(Context context , String assetPath , int compressSize){
		return new BitmapDrawable(context.getResources(), getAssetBitmap(context, assetPath , compressSize));
	}
	
	public static Bitmap getAssetBitmap(Context context , InputStream is, Config config){
		return getAssetBitmap(context, is, 1, config);
	}
	
	public static Bitmap getAssetBitmap(Context context , InputStream is){
		return getAssetBitmap(context, is, 1, Config.RGB_565);
	}
	public static Bitmap getAssetBitmap(Context context , InputStream is, int compressSize){
		return getAssetBitmap(context, is, compressSize, Config.RGB_565);
	}
	/**
	 * 获取字符串资源
	 * 
	 * @param context
	 * @param id
	 * @return
	 */
	public static String getString(Context context, int id) {
		return context.getResources().getString(id);
	}
	/**
	 * 压缩读取资源图片
	 * @param context
	 * @param is
	 * @param compressSize
	 * @param config
	 * @return
	 */
	public static Bitmap getAssetBitmap(Context context , InputStream is, int compressSize, Config config){
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = config;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		opt.inSampleSize = compressSize;
		
		Bitmap bm = null;
		try {
			bm = BitmapFactory.decodeStream(is, null, opt);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return bm;
	}
	
	
	/**
	 * 释放视图资源，主要释放图片资源
	 * @param view
	 */
	public static void recycleView(View view){
		if(view == null) return;
		
		Drawable drawable = null;
		
		if(view.getBackground() != null){
			drawable = view.getBackground();
		}
		else if(view instanceof ImageView){
			drawable = ((ImageView)view).getDrawable();
		}
		
		if(drawable != null){
			drawable.setCallback(null);
			
			if(drawable instanceof BitmapDrawable){
				Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
				if(bitmap != null && !bitmap.isRecycled()){
					drawable = null;
					view = null;
					
					bitmap.recycle();
					
					bitmap = null;
					//System.gc();
				}
			}
			else if(drawable instanceof StateListDrawable){
				StateListDrawable sld = (StateListDrawable)drawable;
				int[] states = sld.getState();
				for(int i = 0;i < states.length;i++){
					sld.selectDrawable(i);
					
					if(sld.getCurrent() == null) continue;
					
					sld.getCurrent().setCallback(null);
					
					if(!(sld.getCurrent() instanceof BitmapDrawable)){
						continue;
					}
					
					Bitmap bmp = ((BitmapDrawable)sld.getCurrent()).getBitmap();
					if(bmp != null && !bmp.isRecycled()){
						bmp.recycle();
						bmp = null;
						//System.gc();
					}
				}
			}
		}
		
		if(view instanceof ViewGroup){
			ViewGroup vg = (ViewGroup)view;
			int childCount = vg.getChildCount();
			for(int i = 0 ; i < childCount; i++){
				ResourceUtil.recycleView(vg.getChildAt(i));
			}
		}
	}
}
