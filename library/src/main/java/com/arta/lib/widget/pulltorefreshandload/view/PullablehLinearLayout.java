package com.arta.lib.widget.pulltorefreshandload.view;

import com.arta.lib.widget.pulltorefreshandload.view.Pullable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PullablehLinearLayout extends LinearLayout implements Pullable {

	public PullablehLinearLayout(Context context) {
		super(context);
	}

	public PullablehLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullablehLinearLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean canPullDown() {
		if (getChildCount() == 0) {
			return true;

		} else if (getChildAt(0).getTop() >= 0) {
			// 滑到顶部
			return true;
		}
		return false;
	}

	@Override
	public boolean canPullUp() {
		if (getChildCount() == 0) {
			return true;

		} else if (getChildAt(getChildCount() - 1).getBottom() <= getMeasuredHeight()) {
			// 滑到底部了
			return true;
		}

		return false;
	}

}
