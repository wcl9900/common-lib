package com.arta.lib.util;

import android.content.res.Resources;
import android.util.TypedValue;

public class CircleUtil {
 public static int dpToPx(Resources res, int dp) {
  return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
 }
 
 public static int spToPx(Resources res, int dp) {
  return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, res.getDisplayMetrics());
 }
}