package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class MobViewPager extends ViewGroup {
    private static final int DECELERATION = 10;
    private static final int SNAP_VELOCITY = 500;
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private ViewPagerAdapter adapter;
    private View currentPage;
    private int currentScreen;
    private int flingVelocity;
    private float lastMotionX;
    private float lastMotionY;
    private int maximumVelocity;
    private View nextPage;
    private int pageWidth;
    private View previousPage;
    private int screenCount;
    private Scroller scroller;
    private boolean skipScreen;
    private int touchSlop;
    private int touchState;
    private VelocityTracker velocityTracker;

    public MobViewPager(Context context) {
        this(context, (AttributeSet) null);
    }

    public MobViewPager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MobViewPager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.scroller = new Scroller(context, new Interpolator() {
            public float getInterpolation(float f) {
                return (2.0f - f) * f;
            }
        });
        this.touchState = 0;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public void setAdapter(ViewPagerAdapter viewPagerAdapter) {
        ViewPagerAdapter viewPagerAdapter2 = this.adapter;
        if (viewPagerAdapter2 != null) {
            viewPagerAdapter2.setMobViewPager((MobViewPager) null);
        }
        this.adapter = viewPagerAdapter;
        ViewPagerAdapter viewPagerAdapter3 = this.adapter;
        if (viewPagerAdapter3 != null) {
            viewPagerAdapter3.setMobViewPager(this);
        }
        if (viewPagerAdapter == null) {
            this.currentScreen = 0;
            removeAllViews();
            return;
        }
        this.screenCount = viewPagerAdapter.getCount();
        int i = this.screenCount;
        if (i <= 0) {
            this.currentScreen = 0;
            removeAllViews();
        } else if (i <= this.currentScreen) {
            scrollToScreenOnUIThread(i - 1, true);
        } else {
            removeAllViews();
            int i2 = this.currentScreen;
            if (i2 > 0) {
                this.previousPage = viewPagerAdapter.getView(i2 - 1, this.previousPage, this);
                addView(this.previousPage);
            }
            this.currentPage = viewPagerAdapter.getView(this.currentScreen, this.currentPage, this);
            addView(this.currentPage);
            int i3 = this.currentScreen;
            if (i3 < this.screenCount - 1) {
                this.nextPage = viewPagerAdapter.getView(i3 + 1, this.nextPage, this);
                addView(this.nextPage);
            }
        }
    }

    public int getCurrentScreen() {
        return this.currentScreen;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.adapter != null && this.screenCount > 0) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                getChildAt(i3).measure(makeMeasureSpec, makeMeasureSpec2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.adapter != null && this.screenCount > 0) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            int i7 = this.currentScreen;
            int i8 = i7 * i5;
            if (i7 > 0) {
                this.previousPage.layout(i8 - i5, 0, i8, i6);
            }
            int i9 = i8 + i5;
            this.currentPage.layout(i8, 0, i9, i6);
            if (this.currentScreen < this.screenCount - 1) {
                this.nextPage.layout(i9, 0, i5 + i9, i6);
            }
            if (this.pageWidth != getWidth()) {
                int i10 = this.pageWidth;
                this.pageWidth = getWidth();
                if (i10 != 0) {
                    adjustScroller();
                }
            }
        }
    }

    private void adjustScroller() {
        View view;
        this.skipScreen = true;
        if (this.currentPage != null && getFocusedChild() == (view = this.currentPage)) {
            view.clearFocus();
        }
        int width = (this.currentScreen * getWidth()) - getScrollX();
        this.scroller.abortAnimation();
        if (width != 0) {
            this.scroller.startScroll(getScrollX(), 0, width, 0, 0);
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.adapter != null && this.screenCount > 0) {
            long drawingTime = getDrawingTime();
            if (this.currentScreen > 0) {
                drawChild(canvas, this.previousPage, drawingTime);
            }
            drawChild(canvas, this.currentPage, drawingTime);
            if (this.currentScreen < this.screenCount - 1) {
                drawChild(canvas, this.nextPage, drawingTime);
            }
        }
    }

    public void computeScroll() {
        if (this.adapter != null && this.screenCount > 0) {
            if (this.scroller.computeScrollOffset()) {
                scrollTo(this.scroller.getCurrX(), this.scroller.getCurrY());
                postInvalidate();
            } else {
                int i = this.currentScreen;
                int currX = this.scroller.getCurrX();
                int width = getWidth();
                int i2 = currX / width;
                if (currX % width > width / 2) {
                    i2++;
                }
                this.currentScreen = Math.max(0, Math.min(i2, this.screenCount - 1));
                if (i != this.currentScreen) {
                    onScreenChange(i);
                }
            }
            if (this.adapter != null) {
                this.adapter.onScreenChanging(((float) getScrollX()) / ((float) getWidth()));
            }
        }
    }

    private void onScreenChange(int i) {
        if (this.adapter != null) {
            if (this.skipScreen && Math.abs(i - this.currentScreen) > 2) {
                removeAllViews();
                int i2 = this.currentScreen;
                if (i2 > 0) {
                    this.previousPage = this.adapter.getView(i2 - 1, this.previousPage, this);
                    addView(this.previousPage);
                }
                this.currentPage = this.adapter.getView(this.currentScreen, this.currentPage, this);
                addView(this.currentPage);
                int i3 = this.currentScreen;
                if (i3 < this.screenCount - 1) {
                    this.nextPage = this.adapter.getView(i3 + 1, this.nextPage, this);
                    addView(this.nextPage);
                }
            } else if (this.currentScreen > i) {
                for (int i4 = 0; i4 < this.currentScreen - i; i4++) {
                    int i5 = i + i4 + 1;
                    View view = this.previousPage;
                    this.previousPage = this.currentPage;
                    this.currentPage = this.nextPage;
                    if (getChildCount() >= 3) {
                        removeViewAt(0);
                    }
                    if (i5 < this.screenCount - 1) {
                        this.nextPage = this.adapter.getView(i5 + 1, view, this);
                        addView(this.nextPage);
                    } else {
                        this.nextPage = view;
                    }
                }
            } else {
                for (int i6 = 0; i6 < i - this.currentScreen; i6++) {
                    int i7 = (i - i6) - 1;
                    View view2 = this.nextPage;
                    this.nextPage = this.currentPage;
                    this.currentPage = this.previousPage;
                    if (getChildCount() >= 3) {
                        removeViewAt(2);
                    }
                    if (i7 > 0) {
                        this.previousPage = this.adapter.getView(i7 - 1, view2, this);
                        addView(this.previousPage, 0);
                    } else {
                        this.previousPage = view2;
                    }
                }
            }
            this.adapter.onScreenChange(this.currentScreen, i);
        }
    }

    public void scrollLeft(boolean z) {
        int i = this.currentScreen;
        if (i > 0) {
            scrollToScreen(i - 1, z);
        }
    }

    public void scrollRight(boolean z) {
        int i = this.currentScreen;
        if (i < this.screenCount - 1) {
            scrollToScreen(i + 1, z);
        }
    }

    public void scrollToScreen(final int i, final boolean z) {
        post(new Runnable() {
            public void run() {
                MobViewPager.this.scrollToScreenOnUIThread(i, z);
            }
        });
    }

    @Deprecated
    public void scrollToScreen(int i, boolean z, boolean z2) {
        scrollToScreen(i, z);
    }

    /* access modifiers changed from: private */
    public void scrollToScreenOnUIThread(int i, boolean z) {
        int i2;
        View view;
        this.skipScreen = z;
        if (this.currentPage != null && getFocusedChild() == (view = this.currentPage)) {
            view.clearFocus();
        }
        int width = (i * getWidth()) - getScrollX();
        this.scroller.abortAnimation();
        if (width != 0) {
            int i3 = 0;
            if (!z) {
                int abs = Math.abs(width) / 2;
                int i4 = this.flingVelocity;
                if (i4 != 0) {
                    int abs2 = Math.abs(i4);
                    double d = (double) abs2;
                    double sqrt = Math.sqrt((double) ((abs2 * abs2) - (Math.abs(width) * 20)));
                    Double.isNaN(d);
                    i3 = (int) (((d - sqrt) * 1000.0d) / 10.0d);
                }
                i2 = (i3 == 0 || i3 > abs) ? abs : i3;
            } else {
                i2 = 0;
            }
            this.scroller.startScroll(getScrollX(), 0, width, 0, i2);
        }
        invalidate();
    }

    public boolean dispatchUnhandledMove(View view, int i) {
        int i2;
        if (this.adapter == null) {
            return super.dispatchUnhandledMove(view, i);
        }
        if (i == 17) {
            int i3 = this.currentScreen;
            if (i3 > 0) {
                scrollToScreenOnUIThread(i3 - 1, false);
                return true;
            }
        } else if (i == 66 && (i2 = this.currentScreen) < this.screenCount - 1) {
            scrollToScreenOnUIThread(i2 + 1, false);
            return true;
        }
        return super.dispatchUnhandledMove(view, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        if (r0 != 3) goto L_0x004d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 2
            r2 = 1
            if (r0 != r1) goto L_0x000d
            int r3 = r4.touchState
            if (r3 == 0) goto L_0x000d
            return r2
        L_0x000d:
            android.view.VelocityTracker r3 = r4.velocityTracker
            if (r3 != 0) goto L_0x0017
            android.view.VelocityTracker r3 = android.view.VelocityTracker.obtain()
            r4.velocityTracker = r3
        L_0x0017:
            android.view.VelocityTracker r3 = r4.velocityTracker
            r3.addMovement(r5)
            r3 = 0
            if (r0 == 0) goto L_0x0038
            if (r0 == r2) goto L_0x002b
            if (r0 == r1) goto L_0x0027
            r5 = 3
            if (r0 == r5) goto L_0x002b
            goto L_0x004d
        L_0x0027:
            r4.handleInterceptMove(r5)
            goto L_0x004d
        L_0x002b:
            android.view.VelocityTracker r5 = r4.velocityTracker
            if (r5 == 0) goto L_0x0035
            r5.recycle()
            r5 = 0
            r4.velocityTracker = r5
        L_0x0035:
            r4.touchState = r3
            goto L_0x004d
        L_0x0038:
            float r0 = r5.getX()
            float r5 = r5.getY()
            r4.lastMotionX = r0
            r4.lastMotionY = r5
            android.widget.Scroller r5 = r4.scroller
            boolean r5 = r5.isFinished()
            r5 = r5 ^ r2
            r4.touchState = r5
        L_0x004d:
            int r5 = r4.touchState
            if (r5 == 0) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r2 = 0
        L_0x0053:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.MobViewPager.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private void handleInterceptMove(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int abs = (int) Math.abs(x - this.lastMotionX);
        if (((int) Math.abs(y - this.lastMotionY)) < abs && abs > this.touchSlop) {
            this.touchState = 1;
            this.lastMotionX = x;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        int i2;
        if (this.adapter == null) {
            return false;
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        if (action != 0) {
            if (action == 1) {
                if (this.touchState == 1) {
                    this.velocityTracker.computeCurrentVelocity(1000, (float) this.maximumVelocity);
                    this.flingVelocity = (int) this.velocityTracker.getXVelocity();
                    if (this.flingVelocity > 500 && (i2 = this.currentScreen) > 0) {
                        scrollToScreenOnUIThread(i2 - 1, false);
                    } else if (this.flingVelocity >= -500 || (i = this.currentScreen) >= this.screenCount - 1) {
                        int width = getWidth();
                        scrollToScreenOnUIThread((getScrollX() + (width / 2)) / width, false);
                    } else {
                        scrollToScreenOnUIThread(i + 1, false);
                    }
                    VelocityTracker velocityTracker2 = this.velocityTracker;
                    if (velocityTracker2 != null) {
                        velocityTracker2.recycle();
                        this.velocityTracker = null;
                    }
                }
                this.touchState = 0;
            } else if (action != 2) {
                if (action == 3) {
                    this.touchState = 0;
                }
            } else if (this.touchState == 1) {
                handleScrollMove(motionEvent);
            } else if (onInterceptTouchEvent(motionEvent) && this.touchState == 1) {
                handleScrollMove(motionEvent);
            }
        } else if (this.touchState != 0) {
            if (!this.scroller.isFinished()) {
                this.scroller.abortAnimation();
            }
            this.lastMotionX = x;
        }
        return true;
    }

    private void handleScrollMove(MotionEvent motionEvent) {
        int right;
        if (this.adapter != null) {
            float x = motionEvent.getX();
            int i = (int) (this.lastMotionX - x);
            this.lastMotionX = x;
            if (i < 0) {
                if (getScrollX() > 0) {
                    scrollBy(Math.max(-getScrollX(), i), 0);
                }
            } else if (i > 0 && getChildCount() != 0 && (right = (getChildAt(getChildCount() - 1).getRight() - getScrollX()) - getWidth()) > 0) {
                scrollBy(Math.min(right, i), 0);
            }
        }
    }
}
