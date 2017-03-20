package com.zi.dian.custom.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wangliang on 6/17/16.
 */
public class ViewPagerHome extends ViewPager  {

    private boolean scrollable = true;

    public ViewPagerHome(Context context) {
        super(context);
    }

    public ViewPagerHome(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollable) {
            return true;
        }
        return super.onTouchEvent(ev);
    }


    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }
}
