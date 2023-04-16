package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;
import com.github.mikephil.charting.utils.Utils;
import com.mob.tools.utils.ResHelper;

public class MobDrawerLayout extends ViewGroup {
    private static final int SNAP_VELOCITY = 500;
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private FrameLayout bodyContainer;
    private FrameLayout drawerContainer;
    private double drawerWidth;
    private float lastMotionX;
    private float lastMotionY;
    private OnDrawerStateChangeListener listener;
    private boolean lockScroll;
    private int maximumVelocity;
    private boolean opened;
    private Paint paint;
    private Scroller scroller;
    private int touchSlop;
    private int touchState;
    private DrawerType type;
    private VelocityTracker velocityTracker;

    public enum DrawerType {
        LEFT_COVER,
        RIGHT_COVER,
        LEFT_BOTTOM,
        RIGHT_BOTTOM,
        LEFT_PUSH,
        RIGHT_PUSH
    }

    public interface OnDrawerStateChangeListener {
        void onClosing(MobDrawerLayout mobDrawerLayout, int i);

        void onOpening(MobDrawerLayout mobDrawerLayout, int i);
    }

    public MobDrawerLayout(Context context) {
        super(context);
        init(context);
    }

    public MobDrawerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public MobDrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.scroller = SmoothScroller.DEFAULT.getScroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.type = DrawerType.LEFT_COVER;
        this.drawerWidth = 0.8d;
        this.touchState = 0;
        this.paint = new Paint();
        C24351 r0 = new View.OnClickListener() {
            public void onClick(View view) {
            }
        };
        this.bodyContainer = new FrameLayout(context);
        this.bodyContainer.setOnClickListener(r0);
        this.drawerContainer = new FrameLayout(context);
        this.drawerContainer.setOnClickListener(r0);
        addView(this.bodyContainer);
        addView(this.drawerContainer);
    }

    public void setDrawerType(DrawerType drawerType) {
        if (drawerType == null) {
            drawerType = DrawerType.LEFT_COVER;
        }
        if (this.type != drawerType) {
            this.type = drawerType;
            int i = C24362.$SwitchMap$com$mob$tools$gui$MobDrawerLayout$DrawerType[drawerType.ordinal()];
            if (i == 1 || i == 2) {
                this.drawerContainer.bringToFront();
            } else {
                this.bodyContainer.bringToFront();
            }
            postInvalidate();
        }
    }

    public void setDrawerWidth(double d) {
        if (d < Utils.DOUBLE_EPSILON) {
            d = 0.800000011920929d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        if (this.drawerWidth != d) {
            this.drawerWidth = d;
            postInvalidate();
        }
    }

    public void open(boolean z) {
        switchDrawer(true, z);
    }

    public void open() {
        open(false);
    }

    public void close(boolean z) {
        switchDrawer(false, z);
    }

    public void close() {
        close(false);
    }

    public boolean isOpened() {
        return this.opened;
    }

    public void setOnDrawerStateChangeListener(OnDrawerStateChangeListener onDrawerStateChangeListener) {
        this.listener = onDrawerStateChangeListener;
    }

    public void setBody(View view) {
        if (!ResHelper.isEqual(this.bodyContainer.getChildCount() == 0 ? null : this.bodyContainer.getChildAt(0), view)) {
            this.bodyContainer.removeAllViews();
            this.bodyContainer.addView(view);
        }
    }

    public void setDrawer(View view) {
        if (!ResHelper.isEqual(this.drawerContainer.getChildCount() == 0 ? null : this.drawerContainer.getChildAt(0), view)) {
            this.drawerContainer.removeAllViews();
            this.drawerContainer.addView(view);
        }
    }

    public void setLockScroll(boolean z) {
        this.lockScroll = z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.bodyContainer.measure(makeMeasureSpec, makeMeasureSpec2);
        double d = (double) measuredWidth;
        double d2 = this.drawerWidth;
        Double.isNaN(d);
        this.drawerContainer.measure(View.MeasureSpec.makeMeasureSpec((int) (d * d2), 1073741824), makeMeasureSpec2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        double d = (double) i5;
        double d2 = this.drawerWidth;
        Double.isNaN(d);
        int i7 = (int) (d * d2);
        if (isOpened()) {
            switch (this.type) {
                case LEFT_COVER:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(0, 0, i7, i6);
                    return;
                case RIGHT_COVER:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(i5 - i7, 0, i5, i6);
                    return;
                case LEFT_BOTTOM:
                    this.bodyContainer.layout(i7, 0, i5 + i7, i6);
                    this.drawerContainer.layout(0, 0, i7, i6);
                    return;
                case LEFT_PUSH:
                    this.bodyContainer.layout(i7, 0, i5 + i7, i6);
                    this.drawerContainer.layout(0, 0, i7, i6);
                    return;
                case RIGHT_BOTTOM:
                    int i8 = -i7;
                    int i9 = i5 - i7;
                    this.bodyContainer.layout(i8, 0, i9, i6);
                    this.drawerContainer.layout(i9, 0, i5, i6);
                    return;
                case RIGHT_PUSH:
                    int i10 = -i7;
                    int i11 = i5 - i7;
                    this.bodyContainer.layout(i10, 0, i11, i6);
                    this.drawerContainer.layout(i11, 0, i5, i6);
                    return;
                default:
                    return;
            }
        } else {
            switch (this.type) {
                case LEFT_COVER:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(-i7, 0, 0, i6);
                    return;
                case RIGHT_COVER:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(i5, 0, i7 + i5, i6);
                    return;
                case LEFT_BOTTOM:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(0, 0, i7, i6);
                    return;
                case LEFT_PUSH:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(-i7, 0, 0, i6);
                    return;
                case RIGHT_BOTTOM:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(i5 - i7, 0, i5, i6);
                    return;
                case RIGHT_PUSH:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(i5, 0, i7 + i5, i6);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        FrameLayout frameLayout;
        FrameLayout frameLayout2;
        long drawingTime = getDrawingTime();
        int i = C24362.$SwitchMap$com$mob$tools$gui$MobDrawerLayout$DrawerType[this.type.ordinal()];
        if (i == 1 || i == 2 || i == 6) {
            frameLayout2 = this.bodyContainer;
            frameLayout = this.drawerContainer;
        } else {
            frameLayout2 = this.drawerContainer;
            frameLayout = this.bodyContainer;
        }
        drawChild(canvas, frameLayout2, drawingTime);
        drawChild(canvas, frameLayout, drawingTime);
        drawShadow(canvas);
    }

    private void drawShadow(Canvas canvas) {
        int i = C24362.$SwitchMap$com$mob$tools$gui$MobDrawerLayout$DrawerType[this.type.ordinal()];
        if (i == 1) {
            int right = this.drawerContainer.getRight();
            if (right > 0) {
                float f = (float) right;
                float f2 = (float) (right + 25);
                this.paint.setShader(new LinearGradient(f, 0.0f, f2, 0.0f, Integer.MIN_VALUE, 0, Shader.TileMode.CLAMP));
                canvas.drawRect(f, 0.0f, f2, (float) getHeight(), this.paint);
            }
        } else if (i == 2) {
            int left = this.drawerContainer.getLeft();
            if (left < getWidth()) {
                float f3 = (float) (left - 25);
                float f4 = (float) left;
                this.paint.setShader(new LinearGradient(f3, 0.0f, f4, 0.0f, 0, Integer.MIN_VALUE, Shader.TileMode.CLAMP));
                canvas.drawRect(f3, 0.0f, f4, (float) getHeight(), this.paint);
            }
        } else if (i == 5 || i == 6) {
            int right2 = this.bodyContainer.getRight();
            if (right2 < getWidth()) {
                float f5 = (float) right2;
                float f6 = (float) (right2 + 25);
                this.paint.setShader(new LinearGradient(f5, 0.0f, f6, 0.0f, Integer.MIN_VALUE, 0, Shader.TileMode.CLAMP));
                canvas.drawRect(f5, 0.0f, f6, (float) getHeight(), this.paint);
            }
        } else {
            int left2 = this.bodyContainer.getLeft();
            if (left2 > 0) {
                float f7 = (float) (left2 - 25);
                float f8 = (float) left2;
                this.paint.setShader(new LinearGradient(f7, 0.0f, f8, 0.0f, 0, Integer.MIN_VALUE, Shader.TileMode.CLAMP));
                canvas.drawRect(f7, 0.0f, f8, (float) getHeight(), this.paint);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0071, code lost:
        r2 = r2 * r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void switchDrawer(boolean r9, boolean r10) {
        /*
            r8 = this;
            android.widget.FrameLayout r0 = r8.bodyContainer
            r0.clearFocus()
            android.widget.FrameLayout r0 = r8.drawerContainer
            r0.clearFocus()
            int[] r0 = com.mob.tools.gui.MobDrawerLayout.C24362.$SwitchMap$com$mob$tools$gui$MobDrawerLayout$DrawerType
            com.mob.tools.gui.MobDrawerLayout$DrawerType r1 = r8.type
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 0
            r2 = 0
            switch(r0) {
                case 1: goto L_0x005d;
                case 2: goto L_0x0044;
                case 3: goto L_0x0031;
                case 4: goto L_0x005d;
                case 5: goto L_0x001d;
                case 6: goto L_0x0044;
                default: goto L_0x001a;
            }
        L_0x001a:
            r9 = 0
            r3 = 0
            goto L_0x0075
        L_0x001d:
            android.widget.FrameLayout r0 = r8.bodyContainer
            int r0 = r0.getLeft()
            if (r9 == 0) goto L_0x0073
            int r9 = r8.getWidth()
            int r9 = -r9
            double r2 = (double) r9
            double r4 = r8.drawerWidth
            java.lang.Double.isNaN(r2)
            goto L_0x0071
        L_0x0031:
            android.widget.FrameLayout r0 = r8.bodyContainer
            int r0 = r0.getLeft()
            if (r9 == 0) goto L_0x0073
            int r9 = r8.getWidth()
            double r2 = (double) r9
            double r4 = r8.drawerWidth
            java.lang.Double.isNaN(r2)
            goto L_0x0071
        L_0x0044:
            int r0 = r8.getWidth()
            android.widget.FrameLayout r4 = r8.drawerContainer
            int r4 = r4.getLeft()
            if (r9 == 0) goto L_0x0058
            double r2 = (double) r0
            double r5 = r8.drawerWidth
            java.lang.Double.isNaN(r2)
            double r2 = r2 * r5
        L_0x0058:
            int r9 = (int) r2
            int r9 = r0 - r9
            r3 = r4
            goto L_0x0075
        L_0x005d:
            android.widget.FrameLayout r0 = r8.drawerContainer
            int r0 = r0.getLeft()
            if (r9 == 0) goto L_0x0066
            goto L_0x0073
        L_0x0066:
            int r9 = r8.getWidth()
            int r9 = -r9
            double r2 = (double) r9
            double r4 = r8.drawerWidth
            java.lang.Double.isNaN(r2)
        L_0x0071:
            double r2 = r2 * r4
        L_0x0073:
            int r9 = (int) r2
            r3 = r0
        L_0x0075:
            android.widget.Scroller r0 = r8.scroller
            r0.abortAnimation()
            if (r3 == r9) goto L_0x008d
            android.widget.Scroller r2 = r8.scroller
            r4 = 0
            int r5 = r9 - r3
            r6 = 0
            if (r10 == 0) goto L_0x0086
            r7 = 0
            goto L_0x008a
        L_0x0086:
            r1 = 100
            r7 = 100
        L_0x008a:
            r2.startScroll(r3, r4, r5, r6, r7)
        L_0x008d:
            r8.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.MobDrawerLayout.switchDrawer(boolean, boolean):void");
    }

    public void computeScroll() {
        if (this.scroller.computeScrollOffset()) {
            switch (this.type) {
                case LEFT_COVER:
                case RIGHT_COVER:
                    int currX = this.scroller.getCurrX();
                    double width = (double) getWidth();
                    double d = this.drawerWidth;
                    Double.isNaN(width);
                    this.drawerContainer.layout(currX, 0, ((int) (width * d)) + currX, getHeight());
                    break;
                case LEFT_BOTTOM:
                case RIGHT_BOTTOM:
                    int currX2 = this.scroller.getCurrX();
                    this.bodyContainer.layout(currX2, 0, getWidth() + currX2, getHeight());
                    break;
                case LEFT_PUSH:
                    int width2 = getWidth();
                    int currX3 = this.scroller.getCurrX();
                    double d2 = (double) width2;
                    double d3 = this.drawerWidth;
                    Double.isNaN(d2);
                    int i = ((int) (d2 * d3)) + currX3;
                    this.drawerContainer.layout(currX3, 0, i, getHeight());
                    this.bodyContainer.layout(i, 0, width2 + i, getHeight());
                    break;
                case RIGHT_PUSH:
                    int width3 = getWidth();
                    int currX4 = this.scroller.getCurrX();
                    double d4 = (double) width3;
                    double d5 = this.drawerWidth;
                    Double.isNaN(d4);
                    this.bodyContainer.layout(currX4 - width3, 0, currX4, getHeight());
                    this.drawerContainer.layout(currX4, 0, ((int) (d4 * d5)) + currX4, getHeight());
                    break;
            }
            postInvalidate();
            if (this.listener != null && this.scroller.getFinalX() != this.scroller.getStartX()) {
                int currX5 = ((this.scroller.getCurrX() - this.scroller.getStartX()) * 100) / (this.scroller.getFinalX() - this.scroller.getStartX());
                if (this.opened) {
                    this.listener.onClosing(this, currX5);
                } else {
                    this.listener.onOpening(this, currX5);
                }
            }
        } else if (isClose()) {
            this.opened = false;
        } else {
            this.opened = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x001c A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isClose() {
        /*
            r4 = this;
            int[] r0 = com.mob.tools.gui.MobDrawerLayout.C24362.$SwitchMap$com$mob$tools$gui$MobDrawerLayout$DrawerType
            com.mob.tools.gui.MobDrawerLayout$DrawerType r1 = r4.type
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 1
            r2 = 0
            switch(r0) {
                case 1: goto L_0x0034;
                case 2: goto L_0x0027;
                case 3: goto L_0x001e;
                case 4: goto L_0x001e;
                case 5: goto L_0x0010;
                case 6: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x003d
        L_0x0010:
            android.widget.FrameLayout r0 = r4.bodyContainer
            int r0 = r0.getRight()
            int r3 = r4.getWidth()
            if (r0 != r3) goto L_0x003d
        L_0x001c:
            r2 = 1
            goto L_0x003d
        L_0x001e:
            android.widget.FrameLayout r0 = r4.bodyContainer
            int r0 = r0.getLeft()
            if (r0 != 0) goto L_0x003d
            goto L_0x001c
        L_0x0027:
            android.widget.FrameLayout r0 = r4.drawerContainer
            int r0 = r0.getLeft()
            int r3 = r4.getWidth()
            if (r0 != r3) goto L_0x003d
            goto L_0x001c
        L_0x0034:
            android.widget.FrameLayout r0 = r4.drawerContainer
            int r0 = r0.getRight()
            if (r0 != 0) goto L_0x003d
            goto L_0x001c
        L_0x003d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.MobDrawerLayout.isClose():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r0 != 3) goto L_0x0081;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            android.view.VelocityTracker r0 = r3.velocityTracker
            if (r0 != 0) goto L_0x000a
            android.view.VelocityTracker r0 = android.view.VelocityTracker.obtain()
            r3.velocityTracker = r0
        L_0x000a:
            android.view.VelocityTracker r0 = r3.velocityTracker
            r0.addMovement(r4)
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == 0) goto L_0x006a
            if (r0 == r1) goto L_0x0044
            r2 = 2
            if (r0 == r2) goto L_0x001f
            r4 = 3
            if (r0 == r4) goto L_0x0044
            goto L_0x0081
        L_0x001f:
            int r0 = r3.touchState
            if (r0 == r1) goto L_0x002d
            boolean r0 = r3.onInterceptTouchEvent(r4)
            if (r0 == 0) goto L_0x0081
            int r0 = r3.touchState
            if (r0 != r1) goto L_0x0081
        L_0x002d:
            float r4 = r4.getX()
            float r0 = r3.lastMotionX
            float r0 = r0 - r4
            int r0 = (int) r0
            if (r0 >= 0) goto L_0x003c
            int r0 = -r0
            r3.dragToRight(r0)
            goto L_0x0041
        L_0x003c:
            if (r0 <= 0) goto L_0x0041
            r3.dragToLeft(r0)
        L_0x0041:
            r3.lastMotionX = r4
            goto L_0x0081
        L_0x0044:
            int r4 = r3.touchState
            if (r4 != r1) goto L_0x0066
            android.view.VelocityTracker r4 = r3.velocityTracker
            if (r4 == 0) goto L_0x0066
            r0 = 1000(0x3e8, float:1.401E-42)
            int r2 = r3.maximumVelocity
            float r2 = (float) r2
            r4.computeCurrentVelocity(r0, r2)
            android.view.VelocityTracker r4 = r3.velocityTracker
            float r4 = r4.getXVelocity()
            int r4 = (int) r4
            r3.computeDrag(r4)
            android.view.VelocityTracker r4 = r3.velocityTracker
            r4.recycle()
            r4 = 0
            r3.velocityTracker = r4
        L_0x0066:
            r4 = 0
            r3.touchState = r4
            goto L_0x0081
        L_0x006a:
            int r0 = r3.touchState
            if (r0 == 0) goto L_0x0081
            android.widget.Scroller r0 = r3.scroller
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x007b
            android.widget.Scroller r0 = r3.scroller
            r0.abortAnimation()
        L_0x007b:
            float r4 = r4.getX()
            r3.lastMotionX = r4
        L_0x0081:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.MobDrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        if (r0 != 3) goto L_0x0072;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.lockScroll
            r1 = 0
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r6.getAction()
            r2 = 2
            r3 = 1
            if (r0 != r2) goto L_0x0013
            int r4 = r5.touchState
            if (r4 == 0) goto L_0x0013
            return r3
        L_0x0013:
            android.view.VelocityTracker r4 = r5.velocityTracker
            if (r4 != 0) goto L_0x001d
            android.view.VelocityTracker r4 = android.view.VelocityTracker.obtain()
            r5.velocityTracker = r4
        L_0x001d:
            android.view.VelocityTracker r4 = r5.velocityTracker
            r4.addMovement(r6)
            if (r0 == 0) goto L_0x005d
            if (r0 == r3) goto L_0x0050
            if (r0 == r2) goto L_0x002c
            r6 = 3
            if (r0 == r6) goto L_0x0050
            goto L_0x0072
        L_0x002c:
            float r0 = r6.getX()
            float r6 = r6.getY()
            float r2 = r5.lastMotionX
            float r2 = r0 - r2
            float r2 = java.lang.Math.abs(r2)
            int r2 = (int) r2
            float r4 = r5.lastMotionY
            float r6 = r6 - r4
            float r6 = java.lang.Math.abs(r6)
            int r6 = (int) r6
            if (r6 >= r2) goto L_0x0072
            int r6 = r5.touchSlop
            if (r2 <= r6) goto L_0x0072
            r5.touchState = r3
            r5.lastMotionX = r0
            goto L_0x0072
        L_0x0050:
            android.view.VelocityTracker r6 = r5.velocityTracker
            if (r6 == 0) goto L_0x005a
            r6.recycle()
            r6 = 0
            r5.velocityTracker = r6
        L_0x005a:
            r5.touchState = r1
            goto L_0x0072
        L_0x005d:
            float r0 = r6.getX()
            r5.lastMotionX = r0
            float r6 = r6.getY()
            r5.lastMotionY = r6
            android.widget.Scroller r6 = r5.scroller
            boolean r6 = r6.isFinished()
            r6 = r6 ^ r3
            r5.touchState = r6
        L_0x0072:
            int r6 = r5.touchState
            if (r6 == 0) goto L_0x0077
            r1 = 1
        L_0x0077:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.MobDrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private void dragToRight(int i) {
        switch (this.type) {
            case LEFT_COVER:
                int left = this.drawerContainer.getLeft();
                if (left < 0) {
                    int i2 = i + left;
                    if (i2 > 0) {
                        i2 = 0;
                    }
                    double width = (double) getWidth();
                    double d = this.drawerWidth;
                    Double.isNaN(width);
                    this.drawerContainer.layout(i2, 0, ((int) (width * d)) + i2, getHeight());
                    return;
                }
                return;
            case RIGHT_COVER:
                int left2 = this.drawerContainer.getLeft();
                int width2 = getWidth();
                if (left2 < width2) {
                    int i3 = i + left2;
                    if (i3 > width2) {
                        i3 = width2;
                    }
                    double width3 = (double) getWidth();
                    double d2 = this.drawerWidth;
                    Double.isNaN(width3);
                    this.drawerContainer.layout(i3, 0, ((int) (width3 * d2)) + i3, getHeight());
                    return;
                }
                return;
            case LEFT_BOTTOM:
                int left3 = this.bodyContainer.getLeft();
                double width4 = (double) getWidth();
                double d3 = this.drawerWidth;
                Double.isNaN(width4);
                int i4 = (int) (width4 * d3);
                if (left3 < i4) {
                    int i5 = i + left3;
                    if (i5 > i4) {
                        i5 = i4;
                    }
                    this.bodyContainer.layout(i5, 0, getWidth() + i5, getHeight());
                    return;
                }
                return;
            case LEFT_PUSH:
                int left4 = this.drawerContainer.getLeft();
                if (left4 < 0) {
                    int i6 = i + left4;
                    if (i6 > 0) {
                        i6 = 0;
                    }
                    double width5 = (double) getWidth();
                    double d4 = this.drawerWidth;
                    Double.isNaN(width5);
                    int i7 = ((int) (width5 * d4)) + i6;
                    this.drawerContainer.layout(i6, 0, i7, getHeight());
                    this.bodyContainer.layout(i7, 0, getWidth() + i7, getHeight());
                    return;
                }
                return;
            case RIGHT_BOTTOM:
                int left5 = this.bodyContainer.getLeft();
                if (left5 < 0) {
                    int i8 = i + left5;
                    if (i8 > 0) {
                        i8 = 0;
                    }
                    this.bodyContainer.layout(i8, 0, getWidth() + i8, getHeight());
                    return;
                }
                return;
            case RIGHT_PUSH:
                int left6 = this.bodyContainer.getLeft();
                if (left6 < 0) {
                    int i9 = i + left6;
                    if (i9 > 0) {
                        i9 = 0;
                    }
                    int width6 = getWidth() + i9;
                    double width7 = (double) getWidth();
                    double d5 = this.drawerWidth;
                    Double.isNaN(width7);
                    this.bodyContainer.layout(i9, 0, width6, getHeight());
                    this.drawerContainer.layout(width6, 0, ((int) (width7 * d5)) + width6, getHeight());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void dragToLeft(int i) {
        switch (this.type) {
            case LEFT_COVER:
                int right = this.drawerContainer.getRight();
                if (right > 0) {
                    int i2 = right - i;
                    if (i2 < 0) {
                        i2 = 0;
                    }
                    double width = (double) getWidth();
                    double d = this.drawerWidth;
                    Double.isNaN(width);
                    this.drawerContainer.layout(i2 - ((int) (width * d)), 0, i2, getHeight());
                    return;
                }
                return;
            case RIGHT_COVER:
                int right2 = this.drawerContainer.getRight();
                int width2 = getWidth();
                if (right2 > width2) {
                    int i3 = right2 - i;
                    if (i3 < width2) {
                        i3 = width2;
                    }
                    double width3 = (double) getWidth();
                    double d2 = this.drawerWidth;
                    Double.isNaN(width3);
                    this.drawerContainer.layout(i3 - ((int) (width3 * d2)), 0, i3, getHeight());
                    return;
                }
                return;
            case LEFT_BOTTOM:
                int left = this.bodyContainer.getLeft();
                if (left > 0) {
                    int i4 = left - i;
                    if (i4 < 0) {
                        i4 = 0;
                    }
                    this.bodyContainer.layout(i4, 0, getWidth() + i4, getHeight());
                    return;
                }
                return;
            case LEFT_PUSH:
                int right3 = this.drawerContainer.getRight();
                if (right3 > 0) {
                    int i5 = right3 - i;
                    if (i5 < 0) {
                        i5 = 0;
                    }
                    double width4 = (double) getWidth();
                    double d3 = this.drawerWidth;
                    Double.isNaN(width4);
                    this.drawerContainer.layout(i5 - ((int) (width4 * d3)), 0, i5, getHeight());
                    this.bodyContainer.layout(i5, 0, getWidth() + i5, getHeight());
                    return;
                }
                return;
            case RIGHT_BOTTOM:
                int left2 = this.bodyContainer.getLeft();
                double d4 = (double) (-getWidth());
                double d5 = this.drawerWidth;
                Double.isNaN(d4);
                int i6 = (int) (d4 * d5);
                if (left2 > i6) {
                    int i7 = left2 - i;
                    if (i7 < i6) {
                        i7 = i6;
                    }
                    this.bodyContainer.layout(i7, 0, getWidth() + i7, getHeight());
                    return;
                }
                return;
            case RIGHT_PUSH:
                int right4 = this.drawerContainer.getRight();
                int width5 = getWidth();
                if (right4 > width5) {
                    int i8 = right4 - i;
                    if (i8 < width5) {
                        i8 = width5;
                    }
                    double width6 = (double) getWidth();
                    double d6 = this.drawerWidth;
                    Double.isNaN(width6);
                    int i9 = i8 - ((int) (width6 * d6));
                    this.drawerContainer.layout(i9, 0, i8, getHeight());
                    this.bodyContainer.layout(i9 - getWidth(), 0, i9, getHeight());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void computeDrag(int i) {
        if (i >= 500) {
            switch (this.type) {
                case LEFT_COVER:
                case LEFT_BOTTOM:
                case LEFT_PUSH:
                    open();
                    return;
                case RIGHT_COVER:
                case RIGHT_BOTTOM:
                case RIGHT_PUSH:
                    close();
                    return;
                default:
                    return;
            }
        } else if (i <= -500) {
            switch (this.type) {
                case LEFT_COVER:
                case LEFT_BOTTOM:
                case LEFT_PUSH:
                    close();
                    return;
                case RIGHT_COVER:
                case RIGHT_BOTTOM:
                case RIGHT_PUSH:
                    open();
                    return;
                default:
                    return;
            }
        } else {
            int i2 = 0;
            switch (this.type) {
                case LEFT_COVER:
                case LEFT_PUSH:
                    i2 = this.drawerContainer.getRight();
                    break;
                case RIGHT_COVER:
                case RIGHT_PUSH:
                    i2 = getWidth() - this.drawerContainer.getLeft();
                    break;
                case LEFT_BOTTOM:
                    i2 = this.bodyContainer.getLeft();
                    break;
                case RIGHT_BOTTOM:
                    i2 = -this.bodyContainer.getLeft();
                    break;
            }
            double width = (double) getWidth();
            double d = this.drawerWidth;
            Double.isNaN(width);
            if (i2 >= ((int) (width * d)) / 2) {
                open();
            } else {
                close();
            }
        }
    }
}
