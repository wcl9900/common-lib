package com.arta.lib.util;

import java.lang.reflect.Field;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;

/**
 * 视图处理工具类
 * @author 王春龙
 *
 */
public class ViewUtils {
	/**
	 * 清除带滚动条视图的边缘拉伸效果
	 * @param view
	 */
	public static void clearEdgeGlow(View view){
		try{
			String className = null;
			if(view instanceof AbsListView){
				className = AbsListView.class.getName();
			}
			else if(view instanceof ScrollView){
				className = ScrollView.class.getName();
			}else{
				return;
			}
			Class<?> c = (Class<?>) Class.forName(className);
			Field egtField = c.getDeclaredField("mEdgeGlowTop");
			Field egbBottom = c.getDeclaredField("mEdgeGlowBottom");
			egtField.setAccessible(true);
			egbBottom.setAccessible(true);
			Object egtObject = egtField.get(view);
			Object egbObject = egbBottom.get(view);
			
			Class<?> cc = (Class<?>) Class.forName(egtObject.getClass().getName());
			Field mGlow = cc.getDeclaredField("mGlow");
			mGlow.setAccessible(true);
			int color = Color.TRANSPARENT;
			mGlow.set(egtObject, new ColorDrawable(color));
			mGlow.set(egbObject, new ColorDrawable(color));
			
			Field mEdge = cc.getDeclaredField("mEdge");
			mEdge.setAccessible(true);
			mEdge.set(egtObject, new ColorDrawable(color));
			mEdge.set(egbObject, new ColorDrawable(color));
		}catch(Exception e){
			
		}
	}
}
