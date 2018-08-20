package com.example.maqian.navtabbartest;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Edward on 2018/8/20.
 */

public class MyNavgationBar extends NavigationTabBar {
    public MyNavgationBar(Context context) {
        super(context);
    }

    public MyNavgationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNavgationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setViewPager(ViewPager viewPager, int index) {
        setViewPager(viewPager);
        mIndex = index;
        if (mIsViewPagerMode) mViewPager.setCurrentItem(index, false);
        postInvalidate();
    }
}
