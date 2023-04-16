package com.mcxtzhang.swipemenulib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class CstViewPager extends ViewPager {
    private static final String TAG = "zxt/CstViewPager";
    private int mLastX;
    private int mLastY;

    public CstViewPager(Context context) {
        super(context);
    }

    public CstViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        boolean z = action != 0 && action != 1 && action == 2 && isHorizontalScroll(x, y) && (!isReactFirstPage() || !isScrollRight(x)) && (!isReachLastPage() || !isScrollLeft(x));
        this.mLastX = x;
        this.mLastY = y;
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (z || onInterceptTouchEvent) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    private boolean isHorizontalScroll(int i, int i2) {
        return Math.abs(i2 - this.mLastY) < Math.abs(i - this.mLastX);
    }

    private boolean isReachLastPage() {
        PagerAdapter adapter = getAdapter();
        return adapter != null && adapter.getCount() - 1 == getCurrentItem();
    }

    private boolean isReactFirstPage() {
        return getCurrentItem() == 0;
    }

    private boolean isScrollLeft(int i) {
        return i - this.mLastX < 0;
    }

    private boolean isScrollRight(int i) {
        return i - this.mLastX > 0;
    }
}
