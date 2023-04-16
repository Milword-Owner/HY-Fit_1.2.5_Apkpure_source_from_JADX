package com.mob.tools.gui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class PullToRequestView extends RelativeLayout {
    private static final int FAULT_TOLERANCE_RANGE = 10;
    private static final long MIN_REF_TIME = 1000;
    private PullToRequestAdatper adapter;
    private View bodyView;
    private float downY;
    private int footerHeight;
    private View footerView;
    private int headerHeight;
    private View headerView;
    private long pullTime;
    private boolean pullingDownLock;
    private boolean pullingUpLock;
    private int state;
    private Runnable stopAct;

    /* renamed from: top  reason: collision with root package name */
    private int f2838top;

    public PullToRequestView(Context context) {
        super(context);
        init();
    }

    public PullToRequestView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PullToRequestView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.stopAct = new Runnable() {
            public void run() {
                PullToRequestView.this.reversePulling();
            }
        };
    }

    public void setAdapter(PullToRequestAdatper pullToRequestAdatper) {
        this.adapter = pullToRequestAdatper;
        removeAllViews();
        this.bodyView = (View) pullToRequestAdatper.getBodyView();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(9);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        addView(this.bodyView, layoutParams);
        this.headerView = pullToRequestAdatper.getHeaderView();
        this.headerView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.headerView.measure(0, 0);
        this.headerHeight = this.headerView.getMeasuredHeight();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, this.headerHeight);
        layoutParams2.addRule(9);
        layoutParams2.addRule(11);
        layoutParams2.addRule(10);
        layoutParams2.topMargin = -this.headerHeight;
        addView(this.headerView, layoutParams2);
        this.footerView = pullToRequestAdatper.getFooterView();
        this.footerView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.footerView.measure(0, 0);
        this.footerHeight = this.footerView.getMeasuredHeight();
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, this.headerHeight);
        layoutParams3.addRule(9);
        layoutParams3.addRule(11);
        layoutParams3.addRule(12);
        layoutParams3.bottomMargin = -this.headerHeight;
        addView(this.footerView, layoutParams3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        if (r0 != 3) goto L_0x01a0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            int r0 = r8.getAction()
            if (r0 == 0) goto L_0x019a
            r1 = -1
            r2 = 1
            r3 = 100
            r4 = 0
            if (r0 == r2) goto L_0x0128
            r5 = 2
            if (r0 == r5) goto L_0x0015
            r5 = 3
            if (r0 == r5) goto L_0x0128
            goto L_0x01a0
        L_0x0015:
            float r0 = r8.getY()
            int r5 = r7.state
            r6 = 1073741824(0x40000000, float:2.0)
            if (r5 == r1) goto L_0x0108
            if (r5 == r2) goto L_0x00eb
            int r1 = r7.f2838top
            if (r1 <= 0) goto L_0x0051
            float r1 = (float) r1
            float r2 = r7.downY
            float r2 = r0 - r2
            float r2 = r2 / r6
            float r1 = r1 + r2
            int r1 = (int) r1
            r7.f2838top = r1
            int r1 = r7.f2838top
            if (r1 >= 0) goto L_0x0035
            r7.f2838top = r4
        L_0x0035:
            int r1 = r7.f2838top
            int r1 = -r1
            r7.scrollTo(r4, r1)
            com.mob.tools.gui.PullToRequestAdatper r1 = r7.adapter
            if (r1 == 0) goto L_0x004b
            int r2 = r7.headerHeight
            if (r2 == 0) goto L_0x004b
            int r4 = r7.f2838top
            int r4 = r4 * 100
            int r4 = r4 / r2
            r1.onPullDown(r4)
        L_0x004b:
            android.view.MotionEvent r8 = r7.getCancelEvent(r8)
            goto L_0x0124
        L_0x0051:
            if (r1 >= 0) goto L_0x0080
            float r1 = (float) r1
            float r2 = r7.downY
            float r2 = r0 - r2
            float r2 = r2 / r6
            float r1 = r1 + r2
            int r1 = (int) r1
            r7.f2838top = r1
            int r1 = r7.f2838top
            if (r1 <= 0) goto L_0x0063
            r7.f2838top = r4
        L_0x0063:
            int r1 = r7.f2838top
            int r1 = -r1
            r7.scrollTo(r4, r1)
            com.mob.tools.gui.PullToRequestAdatper r1 = r7.adapter
            if (r1 == 0) goto L_0x007a
            int r2 = r7.footerHeight
            if (r2 == 0) goto L_0x007a
            int r4 = r7.f2838top
            int r4 = -r4
            int r4 = r4 * 100
            int r4 = r4 / r2
            r1.onPullUp(r4)
        L_0x007a:
            android.view.MotionEvent r8 = r7.getCancelEvent(r8)
            goto L_0x0124
        L_0x0080:
            float r1 = r7.downY
            float r2 = r0 - r1
            r5 = 1092616192(0x41200000, float:10.0)
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 <= 0) goto L_0x00b8
            boolean r1 = r7.canPullDown()
            if (r1 == 0) goto L_0x0124
            int r1 = r7.f2838top
            float r1 = (float) r1
            float r2 = r7.downY
            float r2 = r0 - r2
            float r2 = r2 / r6
            float r1 = r1 + r2
            int r1 = (int) r1
            r7.f2838top = r1
            int r1 = r7.f2838top
            int r1 = -r1
            r7.scrollTo(r4, r1)
            com.mob.tools.gui.PullToRequestAdatper r1 = r7.adapter
            if (r1 == 0) goto L_0x00b3
            int r2 = r7.headerHeight
            if (r2 == 0) goto L_0x00b3
            int r4 = r7.f2838top
            int r4 = -r4
            int r4 = r4 * 100
            int r4 = r4 / r2
            r1.onPullUp(r4)
        L_0x00b3:
            android.view.MotionEvent r8 = r7.getCancelEvent(r8)
            goto L_0x0124
        L_0x00b8:
            float r1 = r1 - r0
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x0124
            boolean r1 = r7.canPullUp()
            if (r1 == 0) goto L_0x0124
            int r1 = r7.f2838top
            float r1 = (float) r1
            float r2 = r7.downY
            float r2 = r0 - r2
            float r2 = r2 / r6
            float r1 = r1 + r2
            int r1 = (int) r1
            r7.f2838top = r1
            int r1 = r7.f2838top
            int r1 = -r1
            r7.scrollTo(r4, r1)
            com.mob.tools.gui.PullToRequestAdatper r1 = r7.adapter
            if (r1 == 0) goto L_0x00e6
            int r2 = r7.footerHeight
            if (r2 == 0) goto L_0x00e6
            int r4 = r7.f2838top
            int r4 = -r4
            int r4 = r4 * 100
            int r4 = r4 / r2
            r1.onPullUp(r4)
        L_0x00e6:
            android.view.MotionEvent r8 = r7.getCancelEvent(r8)
            goto L_0x0124
        L_0x00eb:
            int r1 = r7.f2838top
            float r1 = (float) r1
            float r2 = r7.downY
            float r2 = r0 - r2
            float r2 = r2 / r6
            float r1 = r1 + r2
            int r1 = (int) r1
            r7.f2838top = r1
            int r1 = r7.f2838top
            if (r1 >= 0) goto L_0x00fd
            r7.f2838top = r4
        L_0x00fd:
            int r1 = r7.f2838top
            int r1 = -r1
            r7.scrollTo(r4, r1)
            android.view.MotionEvent r8 = r7.getCancelEvent(r8)
            goto L_0x0124
        L_0x0108:
            int r1 = r7.f2838top
            float r1 = (float) r1
            float r2 = r7.downY
            float r2 = r0 - r2
            float r2 = r2 / r6
            float r1 = r1 + r2
            int r1 = (int) r1
            r7.f2838top = r1
            int r1 = r7.f2838top
            if (r1 <= 0) goto L_0x011a
            r7.f2838top = r4
        L_0x011a:
            int r1 = r7.f2838top
            int r1 = -r1
            r7.scrollTo(r4, r1)
            android.view.MotionEvent r8 = r7.getCancelEvent(r8)
        L_0x0124:
            r7.downY = r0
            goto L_0x01a0
        L_0x0128:
            int r0 = r7.state
            if (r0 == r1) goto L_0x018e
            if (r0 == 0) goto L_0x013d
            if (r0 == r2) goto L_0x0132
            goto L_0x01a0
        L_0x0132:
            int r0 = r7.headerHeight
            r7.f2838top = r0
            int r0 = r7.f2838top
            int r0 = -r0
            r7.scrollTo(r4, r0)
            goto L_0x01a0
        L_0x013d:
            int r0 = r7.f2838top
            int r1 = r7.headerHeight
            if (r0 <= r1) goto L_0x015a
            r7.f2838top = r1
            int r0 = r7.f2838top
            int r0 = -r0
            r7.scrollTo(r4, r0)
            com.mob.tools.gui.PullToRequestAdatper r0 = r7.adapter
            if (r0 == 0) goto L_0x0152
            r0.onPullDown(r3)
        L_0x0152:
            r7.performFresh()
            android.view.MotionEvent r8 = r7.getCancelEvent(r8)
            goto L_0x01a0
        L_0x015a:
            int r1 = r7.footerHeight
            int r2 = -r1
            if (r0 >= r2) goto L_0x0177
            int r0 = -r1
            r7.f2838top = r0
            int r0 = r7.f2838top
            int r0 = -r0
            r7.scrollTo(r4, r0)
            com.mob.tools.gui.PullToRequestAdatper r0 = r7.adapter
            if (r0 == 0) goto L_0x016f
            r0.onPullUp(r3)
        L_0x016f:
            r7.performRequestNext()
            android.view.MotionEvent r8 = r7.getCancelEvent(r8)
            goto L_0x01a0
        L_0x0177:
            if (r0 == 0) goto L_0x01a0
            r7.scrollTo(r4, r4)
            com.mob.tools.gui.PullToRequestAdatper r0 = r7.adapter
            if (r0 == 0) goto L_0x018b
            int r1 = r7.f2838top
            if (r1 <= 0) goto L_0x0188
            r0.onPullDown(r4)
            goto L_0x018b
        L_0x0188:
            r0.onPullUp(r4)
        L_0x018b:
            r7.f2838top = r4
            goto L_0x01a0
        L_0x018e:
            int r0 = r7.footerHeight
            int r0 = -r0
            r7.f2838top = r0
            int r0 = r7.f2838top
            int r0 = -r0
            r7.scrollTo(r4, r0)
            goto L_0x01a0
        L_0x019a:
            float r0 = r8.getY()
            r7.downY = r0
        L_0x01a0:
            boolean r8 = super.dispatchTouchEvent(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.PullToRequestView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    private MotionEvent getCancelEvent(MotionEvent motionEvent) {
        return MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState());
    }

    public void performPullingDown(boolean z) {
        this.f2838top = this.headerHeight;
        scrollTo(0, -this.f2838top);
        if (z) {
            performFresh();
        }
    }

    /* access modifiers changed from: protected */
    public void performFresh() {
        this.pullTime = System.currentTimeMillis();
        this.state = 1;
        PullToRequestAdatper pullToRequestAdatper = this.adapter;
        if (pullToRequestAdatper != null) {
            pullToRequestAdatper.onRefresh();
        }
    }

    public void performPullingUp(boolean z) {
        this.f2838top = -this.footerHeight;
        scrollTo(0, -this.f2838top);
        if (z) {
            performRequestNext();
        }
    }

    private void performRequestNext() {
        this.pullTime = System.currentTimeMillis();
        this.state = -1;
        PullToRequestAdatper pullToRequestAdatper = this.adapter;
        if (pullToRequestAdatper != null) {
            pullToRequestAdatper.onRequestNext();
        }
    }

    /* access modifiers changed from: private */
    public void reversePulling() {
        this.f2838top = 0;
        scrollTo(0, 0);
        this.state = 0;
        PullToRequestAdatper pullToRequestAdatper = this.adapter;
        if (pullToRequestAdatper != null) {
            pullToRequestAdatper.onReversed();
        }
    }

    public void stopPulling() {
        long currentTimeMillis = System.currentTimeMillis() - this.pullTime;
        if (currentTimeMillis < 1000) {
            postDelayed(this.stopAct, 1000 - currentTimeMillis);
        } else {
            post(this.stopAct);
        }
    }

    public void lockPullingDown() {
        this.pullingDownLock = true;
    }

    public void lockPullingUp() {
        this.pullingUpLock = true;
    }

    public void releasePullingDownLock() {
        this.pullingDownLock = false;
    }

    public void releasePullingUpLock() {
        this.pullingUpLock = false;
    }

    private boolean canPullDown() {
        return !this.pullingDownLock && this.adapter.isPullDownReady() && this.state == 0;
    }

    private boolean canPullUp() {
        return !this.pullingUpLock && this.adapter.isPullUpReady() && this.state == 0;
    }
}
