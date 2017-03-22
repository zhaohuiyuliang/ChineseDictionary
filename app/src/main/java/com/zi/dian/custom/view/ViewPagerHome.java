package com.zi.dian.custom.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wangliang on 6/17/16.
 */
public class ViewPagerHome extends ViewPager {

    private boolean scrollable = true;
    private OnPageChangeListener mOnPageChangeListener;

    public ViewPagerHome(Context context) {
        super(context);
        setListener();
    }


    public ViewPagerHome(Context context, AttributeSet attrs) {
        super(context, attrs);
        setListener();
    }

    private void setListener() {
        setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mOnPageChangeListener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setPageSelected(OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
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

    public interface OnPageChangeListener {
        void onPageSelected(int position);
    }
}
