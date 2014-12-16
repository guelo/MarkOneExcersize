package com.miguel.markoneexcerisize;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by miguelvargas on 12/15/14.
 */
public class ZOrderViewPager extends ViewPager {

	public ZOrderViewPager(Context context) {
		super(context);
		setChildrenDrawingOrderEnabled(true);
	}

	public ZOrderViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		setChildrenDrawingOrderEnabled(true);

	}

	@Override
	protected int getChildDrawingOrder(int childCount, int i) {
		int index = i;
		if (i>getCurrentItem())
			index--;
		if (i == getCurrentItem())
			index = childCount-1;

		return index;
	}
}
