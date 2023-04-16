package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

public class Flow extends VirtualLayout {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private ConstraintWidget[] mAlignedBiggestElementsInCols = null;
    private ConstraintWidget[] mAlignedBiggestElementsInRows = null;
    private int[] mAlignedDimensions = null;
    private ArrayList<WidgetsList> mChainList = new ArrayList<>();
    /* access modifiers changed from: private */
    public ConstraintWidget[] mDisplayedWidgets;
    /* access modifiers changed from: private */
    public int mDisplayedWidgetsCount = 0;
    /* access modifiers changed from: private */
    public float mFirstHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mFirstHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mFirstVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mFirstVerticalStyle = -1;
    /* access modifiers changed from: private */
    public int mHorizontalAlign = 2;
    /* access modifiers changed from: private */
    public float mHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mHorizontalGap = 0;
    /* access modifiers changed from: private */
    public int mHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mLastHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mLastHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mLastVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mLastVerticalStyle = -1;
    private int mMaxElementsWrap = -1;
    private int mOrientation = 0;
    /* access modifiers changed from: private */
    public int mVerticalAlign = 2;
    /* access modifiers changed from: private */
    public float mVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mVerticalGap = 0;
    /* access modifiers changed from: private */
    public int mVerticalStyle = -1;
    private int mWrapMode = 0;

    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Flow flow = (Flow) constraintWidget;
        this.mHorizontalStyle = flow.mHorizontalStyle;
        this.mVerticalStyle = flow.mVerticalStyle;
        this.mFirstHorizontalStyle = flow.mFirstHorizontalStyle;
        this.mFirstVerticalStyle = flow.mFirstVerticalStyle;
        this.mLastHorizontalStyle = flow.mLastHorizontalStyle;
        this.mLastVerticalStyle = flow.mLastVerticalStyle;
        this.mHorizontalBias = flow.mHorizontalBias;
        this.mVerticalBias = flow.mVerticalBias;
        this.mFirstHorizontalBias = flow.mFirstHorizontalBias;
        this.mFirstVerticalBias = flow.mFirstVerticalBias;
        this.mLastHorizontalBias = flow.mLastHorizontalBias;
        this.mLastVerticalBias = flow.mLastVerticalBias;
        this.mHorizontalGap = flow.mHorizontalGap;
        this.mVerticalGap = flow.mVerticalGap;
        this.mHorizontalAlign = flow.mHorizontalAlign;
        this.mVerticalAlign = flow.mVerticalAlign;
        this.mWrapMode = flow.mWrapMode;
        this.mMaxElementsWrap = flow.mMaxElementsWrap;
        this.mOrientation = flow.mOrientation;
    }

    public void setOrientation(int i) {
        this.mOrientation = i;
    }

    public void setFirstHorizontalStyle(int i) {
        this.mFirstHorizontalStyle = i;
    }

    public void setFirstVerticalStyle(int i) {
        this.mFirstVerticalStyle = i;
    }

    public void setLastHorizontalStyle(int i) {
        this.mLastHorizontalStyle = i;
    }

    public void setLastVerticalStyle(int i) {
        this.mLastVerticalStyle = i;
    }

    public void setHorizontalStyle(int i) {
        this.mHorizontalStyle = i;
    }

    public void setVerticalStyle(int i) {
        this.mVerticalStyle = i;
    }

    public void setHorizontalBias(float f) {
        this.mHorizontalBias = f;
    }

    public void setVerticalBias(float f) {
        this.mVerticalBias = f;
    }

    public void setFirstHorizontalBias(float f) {
        this.mFirstHorizontalBias = f;
    }

    public void setFirstVerticalBias(float f) {
        this.mFirstVerticalBias = f;
    }

    public void setLastHorizontalBias(float f) {
        this.mLastHorizontalBias = f;
    }

    public void setLastVerticalBias(float f) {
        this.mLastVerticalBias = f;
    }

    public void setHorizontalAlign(int i) {
        this.mHorizontalAlign = i;
    }

    public void setVerticalAlign(int i) {
        this.mVerticalAlign = i;
    }

    public void setWrapMode(int i) {
        this.mWrapMode = i;
    }

    public void setHorizontalGap(int i) {
        this.mHorizontalGap = i;
    }

    public void setVerticalGap(int i) {
        this.mVerticalGap = i;
    }

    public void setMaxElementsWrap(int i) {
        this.mMaxElementsWrap = i;
    }

    /* access modifiers changed from: private */
    public final int getWidgetWidth(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (constraintWidget.mMatchConstraintDefaultWidth == 0) {
                return 0;
            }
            if (constraintWidget.mMatchConstraintDefaultWidth == 2) {
                int i2 = (int) (constraintWidget.mMatchConstraintPercentWidth * ((float) i));
                if (i2 != constraintWidget.getWidth()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i2, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                }
                return i2;
            } else if (constraintWidget.mMatchConstraintDefaultWidth == 1) {
                return constraintWidget.getWidth();
            } else {
                if (constraintWidget.mMatchConstraintDefaultWidth == 3) {
                    return (int) ((((float) constraintWidget.getHeight()) * constraintWidget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return constraintWidget.getWidth();
    }

    /* access modifiers changed from: private */
    public final int getWidgetHeight(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (constraintWidget.mMatchConstraintDefaultHeight == 0) {
                return 0;
            }
            if (constraintWidget.mMatchConstraintDefaultHeight == 2) {
                int i2 = (int) (constraintWidget.mMatchConstraintPercentHeight * ((float) i));
                if (i2 != constraintWidget.getHeight()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i2);
                }
                return i2;
            } else if (constraintWidget.mMatchConstraintDefaultHeight == 1) {
                return constraintWidget.getHeight();
            } else {
                if (constraintWidget.mMatchConstraintDefaultHeight == 3) {
                    return (int) ((((float) constraintWidget.getWidth()) * constraintWidget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return constraintWidget.getHeight();
    }

    /* JADX WARNING: type inference failed for: r11v2 */
    /* JADX WARNING: type inference failed for: r11v3 */
    /* JADX WARNING: type inference failed for: r11v6 */
    /* JADX WARNING: type inference failed for: r11v7 */
    /* JADX WARNING: type inference failed for: r11v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measure(int r20, int r21, int r22, int r23) {
        /*
            r19 = this;
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            int r0 = r6.mWidgetsCount
            r11 = 0
            if (r0 <= 0) goto L_0x001c
            boolean r0 = r19.measureChildren()
            if (r0 != 0) goto L_0x001c
            r6.setMeasure(r11, r11)
            r6.needsCallbackFromSolver(r11)
            return
        L_0x001c:
            int r12 = r19.getPaddingLeft()
            int r13 = r19.getPaddingRight()
            int r14 = r19.getPaddingTop()
            int r15 = r19.getPaddingBottom()
            r0 = 2
            int[] r5 = new int[r0]
            int r1 = r8 - r12
            int r1 = r1 - r13
            int r2 = r6.mOrientation
            r4 = 1
            if (r2 != r4) goto L_0x003a
            int r1 = r10 - r14
            int r1 = r1 - r15
        L_0x003a:
            r16 = r1
            int r1 = r6.mOrientation
            r2 = -1
            if (r1 != 0) goto L_0x004e
            int r1 = r6.mHorizontalStyle
            if (r1 != r2) goto L_0x0047
            r6.mHorizontalStyle = r11
        L_0x0047:
            int r1 = r6.mVerticalStyle
            if (r1 != r2) goto L_0x005a
            r6.mVerticalStyle = r11
            goto L_0x005a
        L_0x004e:
            int r1 = r6.mHorizontalStyle
            if (r1 != r2) goto L_0x0054
            r6.mHorizontalStyle = r11
        L_0x0054:
            int r1 = r6.mVerticalStyle
            if (r1 != r2) goto L_0x005a
            r6.mVerticalStyle = r11
        L_0x005a:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r1 = r6.mWidgets
            r2 = 0
            r3 = 0
        L_0x005e:
            int r11 = r6.mWidgetsCount
            r0 = 8
            if (r2 >= r11) goto L_0x0074
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r11 = r6.mWidgets
            r11 = r11[r2]
            int r11 = r11.getVisibility()
            if (r11 != r0) goto L_0x0070
            int r3 = r3 + 1
        L_0x0070:
            int r2 = r2 + 1
            r0 = 2
            goto L_0x005e
        L_0x0074:
            int r2 = r6.mWidgetsCount
            if (r3 <= 0) goto L_0x0096
            int r1 = r6.mWidgetsCount
            int r1 = r1 - r3
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r1 = new androidx.constraintlayout.solver.widgets.ConstraintWidget[r1]
            r2 = 0
            r3 = 0
        L_0x007f:
            int r11 = r6.mWidgetsCount
            if (r2 >= r11) goto L_0x0095
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r11 = r6.mWidgets
            r11 = r11[r2]
            int r4 = r11.getVisibility()
            if (r4 == r0) goto L_0x0091
            r1[r3] = r11
            int r3 = r3 + 1
        L_0x0091:
            int r2 = r2 + 1
            r4 = 1
            goto L_0x007f
        L_0x0095:
            r2 = r3
        L_0x0096:
            r6.mDisplayedWidgets = r1
            r6.mDisplayedWidgetsCount = r2
            int r0 = r6.mWrapMode
            if (r0 == 0) goto L_0x00c4
            r4 = 1
            if (r0 == r4) goto L_0x00b7
            r3 = 2
            if (r0 == r3) goto L_0x00aa
            r17 = r5
            r11 = 1
        L_0x00a7:
            r18 = 0
            goto L_0x00d1
        L_0x00aa:
            int r3 = r6.mOrientation
            r0 = r19
            r11 = 1
            r4 = r16
            r17 = r5
            r0.measureAligned(r1, r2, r3, r4, r5)
            goto L_0x00a7
        L_0x00b7:
            r17 = r5
            r11 = 1
            int r3 = r6.mOrientation
            r0 = r19
            r4 = r16
            r0.measureChainWrap(r1, r2, r3, r4, r5)
            goto L_0x00a7
        L_0x00c4:
            r17 = r5
            r11 = 1
            int r3 = r6.mOrientation
            r0 = r19
            r4 = r16
            r0.measureNoWrap(r1, r2, r3, r4, r5)
            goto L_0x00a7
        L_0x00d1:
            r0 = r17[r18]
            int r0 = r0 + r12
            int r0 = r0 + r13
            r1 = r17[r11]
            int r1 = r1 + r14
            int r1 = r1 + r15
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r7 != r3) goto L_0x00e1
            r0 = r8
            goto L_0x00ec
        L_0x00e1:
            if (r7 != r2) goto L_0x00e8
            int r0 = java.lang.Math.min(r0, r8)
            goto L_0x00ec
        L_0x00e8:
            if (r7 != 0) goto L_0x00eb
            goto L_0x00ec
        L_0x00eb:
            r0 = 0
        L_0x00ec:
            if (r9 != r3) goto L_0x00f0
            r1 = r10
            goto L_0x00fb
        L_0x00f0:
            if (r9 != r2) goto L_0x00f7
            int r1 = java.lang.Math.min(r1, r10)
            goto L_0x00fb
        L_0x00f7:
            if (r9 != 0) goto L_0x00fa
            goto L_0x00fb
        L_0x00fa:
            r1 = 0
        L_0x00fb:
            r6.setMeasure(r0, r1)
            r6.setWidth(r0)
            r6.setHeight(r1)
            int r0 = r6.mWidgetsCount
            if (r0 <= 0) goto L_0x0109
            goto L_0x010a
        L_0x0109:
            r11 = 0
        L_0x010a:
            r6.needsCallbackFromSolver(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.measure(int, int, int, int):void");
    }

    private class WidgetsList {
        /* access modifiers changed from: private */
        public ConstraintWidget biggest = null;
        int biggestDimension = 0;
        private ConstraintAnchor mBottom;
        private int mCount = 0;
        private int mHeight = 0;
        private ConstraintAnchor mLeft;
        private int mMax = 0;
        private int mNbMatchConstraintsWidgets = 0;
        private int mOrientation = 0;
        private int mPaddingBottom = 0;
        private int mPaddingLeft = 0;
        private int mPaddingRight = 0;
        private int mPaddingTop = 0;
        private ConstraintAnchor mRight;
        private int mStartIndex = 0;
        private ConstraintAnchor mTop;
        private int mWidth = 0;

        public WidgetsList(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2) {
            this.mOrientation = i;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = Flow.this.getPaddingLeft();
            this.mPaddingTop = Flow.this.getPaddingTop();
            this.mPaddingRight = Flow.this.getPaddingRight();
            this.mPaddingBottom = Flow.this.getPaddingBottom();
            this.mMax = i2;
        }

        public void setup(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2, int i3, int i4, int i5, int i6) {
            this.mOrientation = i;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = i2;
            this.mPaddingTop = i3;
            this.mPaddingRight = i4;
            this.mPaddingBottom = i5;
            this.mMax = i6;
        }

        public void clear() {
            this.biggestDimension = 0;
            this.biggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
        }

        public void setStartIndex(int i) {
            this.mStartIndex = i;
        }

        public int getWidth() {
            if (this.mOrientation == 0) {
                return this.mWidth - Flow.this.mHorizontalGap;
            }
            return this.mWidth;
        }

        public int getHeight() {
            if (this.mOrientation == 1) {
                return this.mHeight - Flow.this.mVerticalGap;
            }
            return this.mHeight;
        }

        public void add(ConstraintWidget constraintWidget) {
            int i = 0;
            if (this.mOrientation == 0) {
                int access$200 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    access$200 = 0;
                }
                int access$000 = Flow.this.mHorizontalGap;
                if (constraintWidget.getVisibility() != 8) {
                    i = access$000;
                }
                this.mWidth += access$200 + i;
                int access$300 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (this.biggest == null || this.biggestDimension < access$300) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = access$300;
                    this.mHeight = access$300;
                }
            } else {
                int access$2002 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                int access$3002 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    access$3002 = 0;
                }
                int access$100 = Flow.this.mVerticalGap;
                if (constraintWidget.getVisibility() != 8) {
                    i = access$100;
                }
                this.mHeight += access$3002 + i;
                if (this.biggest == null || this.biggestDimension < access$2002) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = access$2002;
                    this.mWidth = access$2002;
                }
            }
            this.mCount++;
        }

        /* JADX WARNING: Removed duplicated region for block: B:55:0x00e0  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void createConstraints(boolean r17, int r18, boolean r19) {
            /*
                r16 = this;
                r0 = r16
                int r1 = r0.mCount
                r2 = 0
                r3 = 0
            L_0x0006:
                if (r3 >= r1) goto L_0x0027
                int r4 = r0.mStartIndex
                int r4 = r4 + r3
                androidx.constraintlayout.solver.widgets.Flow r5 = androidx.constraintlayout.solver.widgets.Flow.this
                int r5 = r5.mDisplayedWidgetsCount
                if (r4 < r5) goto L_0x0014
                goto L_0x0027
            L_0x0014:
                androidx.constraintlayout.solver.widgets.Flow r4 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r4 = r4.mDisplayedWidgets
                int r5 = r0.mStartIndex
                int r5 = r5 + r3
                r4 = r4[r5]
                if (r4 == 0) goto L_0x0024
                r4.resetAnchors()
            L_0x0024:
                int r3 = r3 + 1
                goto L_0x0006
            L_0x0027:
                if (r1 == 0) goto L_0x035e
                androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.biggest
                if (r3 != 0) goto L_0x002f
                goto L_0x035e
            L_0x002f:
                if (r19 == 0) goto L_0x0035
                if (r18 != 0) goto L_0x0035
                r4 = 1
                goto L_0x0036
            L_0x0035:
                r4 = 0
            L_0x0036:
                r5 = -1
                r6 = 0
                r7 = -1
                r8 = -1
            L_0x003a:
                if (r6 >= r1) goto L_0x0067
                if (r17 == 0) goto L_0x0042
                int r9 = r1 + -1
                int r9 = r9 - r6
                goto L_0x0043
            L_0x0042:
                r9 = r6
            L_0x0043:
                int r10 = r0.mStartIndex
                int r10 = r10 + r9
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mDisplayedWidgetsCount
                if (r10 < r11) goto L_0x004f
                goto L_0x0067
            L_0x004f:
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = r10.mDisplayedWidgets
                int r11 = r0.mStartIndex
                int r11 = r11 + r9
                r9 = r10[r11]
                int r9 = r9.getVisibility()
                if (r9 != 0) goto L_0x0064
                if (r7 != r5) goto L_0x0063
                r7 = r6
            L_0x0063:
                r8 = r6
            L_0x0064:
                int r6 = r6 + 1
                goto L_0x003a
            L_0x0067:
                r6 = 0
                int r9 = r0.mOrientation
                if (r9 != 0) goto L_0x01ec
                androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r0.biggest
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                int r10 = r10.mVerticalStyle
                r9.setVerticalChainStyle(r10)
                int r10 = r0.mPaddingTop
                if (r18 <= 0) goto L_0x0082
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mVerticalGap
                int r10 = r10 + r11
            L_0x0082:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mTop
                r11.connect(r12, r10)
                if (r19 == 0) goto L_0x0094
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r9.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mBottom
                int r12 = r0.mPaddingBottom
                r10.connect(r11, r12)
            L_0x0094:
                if (r18 <= 0) goto L_0x00a1
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mTop
                androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r10.mOwner
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r10.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mTop
                r10.connect(r11, r2)
            L_0x00a1:
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                int r10 = r10.mVerticalAlign
                r11 = 3
                if (r10 != r11) goto L_0x00db
                boolean r10 = r9.hasBaseline()
                if (r10 != 0) goto L_0x00db
                r10 = 0
            L_0x00b1:
                if (r10 >= r1) goto L_0x00db
                if (r17 == 0) goto L_0x00b9
                int r12 = r1 + -1
                int r12 = r12 - r10
                goto L_0x00ba
            L_0x00b9:
                r12 = r10
            L_0x00ba:
                int r13 = r0.mStartIndex
                int r13 = r13 + r12
                androidx.constraintlayout.solver.widgets.Flow r14 = androidx.constraintlayout.solver.widgets.Flow.this
                int r14 = r14.mDisplayedWidgetsCount
                if (r13 < r14) goto L_0x00c6
                goto L_0x00db
            L_0x00c6:
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r13 = r13.mDisplayedWidgets
                int r14 = r0.mStartIndex
                int r14 = r14 + r12
                r12 = r13[r14]
                boolean r13 = r12.hasBaseline()
                if (r13 == 0) goto L_0x00d8
                goto L_0x00dc
            L_0x00d8:
                int r10 = r10 + 1
                goto L_0x00b1
            L_0x00db:
                r12 = r9
            L_0x00dc:
                r10 = r6
                r6 = 0
            L_0x00de:
                if (r6 >= r1) goto L_0x035e
                if (r17 == 0) goto L_0x00e6
                int r13 = r1 + -1
                int r13 = r13 - r6
                goto L_0x00e7
            L_0x00e6:
                r13 = r6
            L_0x00e7:
                int r14 = r0.mStartIndex
                int r14 = r14 + r13
                androidx.constraintlayout.solver.widgets.Flow r15 = androidx.constraintlayout.solver.widgets.Flow.this
                int r15 = r15.mDisplayedWidgetsCount
                if (r14 < r15) goto L_0x00f4
                goto L_0x035e
            L_0x00f4:
                androidx.constraintlayout.solver.widgets.Flow r14 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r14 = r14.mDisplayedWidgets
                int r15 = r0.mStartIndex
                int r15 = r15 + r13
                r14 = r14[r15]
                if (r6 != 0) goto L_0x010a
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r15 = r14.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mLeft
                int r3 = r0.mPaddingLeft
                r14.connect((androidx.constraintlayout.solver.widgets.ConstraintAnchor) r15, (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r11, (int) r3)
            L_0x010a:
                if (r13 != 0) goto L_0x014d
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mHorizontalStyle
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                float r11 = r11.mHorizontalBias
                int r13 = r0.mStartIndex
                if (r13 != 0) goto L_0x0131
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mFirstHorizontalStyle
                if (r13 == r5) goto L_0x0131
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mFirstHorizontalStyle
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                float r11 = r11.mFirstHorizontalBias
                goto L_0x0147
            L_0x0131:
                if (r19 == 0) goto L_0x0147
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mLastHorizontalStyle
                if (r13 == r5) goto L_0x0147
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mLastHorizontalStyle
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                float r11 = r11.mLastHorizontalBias
            L_0x0147:
                r14.setHorizontalChainStyle(r3)
                r14.setHorizontalBiasPercent(r11)
            L_0x014d:
                int r3 = r1 + -1
                if (r6 != r3) goto L_0x015a
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
                int r13 = r0.mPaddingRight
                r14.connect((androidx.constraintlayout.solver.widgets.ConstraintAnchor) r3, (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r11, (int) r13)
            L_0x015a:
                if (r10 == 0) goto L_0x0185
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mRight
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mHorizontalGap
                r3.connect(r11, r13)
                if (r6 != r7) goto L_0x0172
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mLeft
                int r11 = r0.mPaddingLeft
                r3.setGoneMargin(r11)
            L_0x0172:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r14.mLeft
                r3.connect(r11, r2)
                r3 = 1
                int r11 = r8 + 1
                if (r6 != r11) goto L_0x0185
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r10.mRight
                int r10 = r0.mPaddingRight
                r3.setGoneMargin(r10)
            L_0x0185:
                if (r14 == r9) goto L_0x01e5
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mVerticalAlign
                r10 = 3
                if (r3 != r10) goto L_0x01a6
                boolean r3 = r12.hasBaseline()
                if (r3 == 0) goto L_0x01a6
                if (r14 == r12) goto L_0x01a6
                boolean r3 = r14.hasBaseline()
                if (r3 == 0) goto L_0x01a6
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBaseline
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r12.mBaseline
                r3.connect(r11, r2)
                goto L_0x01e6
            L_0x01a6:
                androidx.constraintlayout.solver.widgets.Flow r3 = androidx.constraintlayout.solver.widgets.Flow.this
                int r3 = r3.mVerticalAlign
                if (r3 == 0) goto L_0x01dd
                r11 = 1
                if (r3 == r11) goto L_0x01d5
                if (r4 == 0) goto L_0x01c6
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mTop
                int r13 = r0.mPaddingTop
                r3.connect(r11, r13)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mBottom
                int r13 = r0.mPaddingBottom
                r3.connect(r11, r13)
                goto L_0x01e6
            L_0x01c6:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mTop
                r3.connect(r11, r2)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mBottom
                r3.connect(r11, r2)
                goto L_0x01e6
            L_0x01d5:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mBottom
                r3.connect(r11, r2)
                goto L_0x01e6
            L_0x01dd:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r14.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mTop
                r3.connect(r11, r2)
                goto L_0x01e6
            L_0x01e5:
                r10 = 3
            L_0x01e6:
                int r6 = r6 + 1
                r10 = r14
                r11 = 3
                goto L_0x00de
            L_0x01ec:
                androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r0.biggest
                androidx.constraintlayout.solver.widgets.Flow r9 = androidx.constraintlayout.solver.widgets.Flow.this
                int r9 = r9.mHorizontalStyle
                r3.setHorizontalChainStyle(r9)
                int r9 = r0.mPaddingLeft
                if (r18 <= 0) goto L_0x0202
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                int r10 = r10.mHorizontalGap
                int r9 = r9 + r10
            L_0x0202:
                if (r17 == 0) goto L_0x0224
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
                r10.connect(r11, r9)
                if (r19 == 0) goto L_0x0216
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r3.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mLeft
                int r11 = r0.mPaddingRight
                r9.connect(r10, r11)
            L_0x0216:
                if (r18 <= 0) goto L_0x0243
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.mRight
                androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r9.mOwner
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mRight
                r9.connect(r10, r2)
                goto L_0x0243
            L_0x0224:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mLeft
                r10.connect(r11, r9)
                if (r19 == 0) goto L_0x0236
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r3.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r0.mRight
                int r11 = r0.mPaddingRight
                r9.connect(r10, r11)
            L_0x0236:
                if (r18 <= 0) goto L_0x0243
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r0.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintWidget r9 = r9.mOwner
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r10 = r3.mLeft
                r9.connect(r10, r2)
            L_0x0243:
                r9 = r6
                r6 = 0
            L_0x0245:
                if (r6 >= r1) goto L_0x035e
                int r10 = r0.mStartIndex
                int r10 = r10 + r6
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mDisplayedWidgetsCount
                if (r10 < r11) goto L_0x0254
                goto L_0x035e
            L_0x0254:
                androidx.constraintlayout.solver.widgets.Flow r10 = androidx.constraintlayout.solver.widgets.Flow.this
                androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = r10.mDisplayedWidgets
                int r11 = r0.mStartIndex
                int r11 = r11 + r6
                r10 = r10[r11]
                if (r6 != 0) goto L_0x02ab
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mTop
                int r13 = r0.mPaddingTop
                r10.connect((androidx.constraintlayout.solver.widgets.ConstraintAnchor) r11, (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r12, (int) r13)
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mVerticalStyle
                androidx.constraintlayout.solver.widgets.Flow r12 = androidx.constraintlayout.solver.widgets.Flow.this
                float r12 = r12.mVerticalBias
                int r13 = r0.mStartIndex
                if (r13 != 0) goto L_0x028f
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mFirstVerticalStyle
                if (r13 == r5) goto L_0x028f
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mFirstVerticalStyle
                androidx.constraintlayout.solver.widgets.Flow r12 = androidx.constraintlayout.solver.widgets.Flow.this
                float r12 = r12.mFirstVerticalBias
                goto L_0x02a5
            L_0x028f:
                if (r19 == 0) goto L_0x02a5
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mLastVerticalStyle
                if (r13 == r5) goto L_0x02a5
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mLastVerticalStyle
                androidx.constraintlayout.solver.widgets.Flow r12 = androidx.constraintlayout.solver.widgets.Flow.this
                float r12 = r12.mLastVerticalBias
            L_0x02a5:
                r10.setVerticalChainStyle(r11)
                r10.setVerticalBiasPercent(r12)
            L_0x02ab:
                int r11 = r1 + -1
                if (r6 != r11) goto L_0x02b8
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r0.mBottom
                int r13 = r0.mPaddingBottom
                r10.connect((androidx.constraintlayout.solver.widgets.ConstraintAnchor) r11, (androidx.constraintlayout.solver.widgets.ConstraintAnchor) r12, (int) r13)
            L_0x02b8:
                if (r9 == 0) goto L_0x02e3
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mTop
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r9.mBottom
                androidx.constraintlayout.solver.widgets.Flow r13 = androidx.constraintlayout.solver.widgets.Flow.this
                int r13 = r13.mVerticalGap
                r11.connect(r12, r13)
                if (r6 != r7) goto L_0x02d0
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r10.mTop
                int r12 = r0.mPaddingTop
                r11.setGoneMargin(r12)
            L_0x02d0:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r9.mBottom
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r12 = r10.mTop
                r11.connect(r12, r2)
                r11 = 1
                int r12 = r8 + 1
                if (r6 != r12) goto L_0x02e3
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r9.mBottom
                int r11 = r0.mPaddingBottom
                r9.setGoneMargin(r11)
            L_0x02e3:
                if (r10 == r3) goto L_0x0358
                r9 = 2
                if (r17 == 0) goto L_0x0315
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mHorizontalAlign
                if (r11 == 0) goto L_0x030d
                r12 = 1
                if (r11 == r12) goto L_0x0305
                if (r11 == r9) goto L_0x02f6
                goto L_0x0358
            L_0x02f6:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x0358
            L_0x0305:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                goto L_0x0358
            L_0x030d:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x0358
            L_0x0315:
                androidx.constraintlayout.solver.widgets.Flow r11 = androidx.constraintlayout.solver.widgets.Flow.this
                int r11 = r11.mHorizontalAlign
                if (r11 == 0) goto L_0x034f
                r12 = 1
                if (r11 == r12) goto L_0x0347
                if (r11 == r9) goto L_0x0323
                goto L_0x0359
            L_0x0323:
                if (r4 == 0) goto L_0x0338
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mLeft
                int r13 = r0.mPaddingLeft
                r9.connect(r11, r13)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r0.mRight
                int r13 = r0.mPaddingRight
                r9.connect(r11, r13)
                goto L_0x0359
            L_0x0338:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x0359
            L_0x0347:
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mRight
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mRight
                r9.connect(r11, r2)
                goto L_0x0359
            L_0x034f:
                r12 = 1
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r9 = r10.mLeft
                androidx.constraintlayout.solver.widgets.ConstraintAnchor r11 = r3.mLeft
                r9.connect(r11, r2)
                goto L_0x0359
            L_0x0358:
                r12 = 1
            L_0x0359:
                int r6 = r6 + 1
                r9 = r10
                goto L_0x0245
            L_0x035e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.WidgetsList.createConstraints(boolean, int, boolean):void");
        }

        public void measureMatchConstraints(int i) {
            int i2 = this.mNbMatchConstraintsWidgets;
            if (i2 != 0) {
                int i3 = this.mCount;
                int i4 = i / i2;
                int i5 = 0;
                while (i5 < i3 && this.mStartIndex + i5 < Flow.this.mDisplayedWidgetsCount) {
                    ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i5];
                    if (this.mOrientation == 0) {
                        if (constraintWidget != null && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                            Flow.this.measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                        }
                    } else if (constraintWidget != null && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                        Flow.this.measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                    }
                    i5++;
                }
                recomputeDimensions();
            }
        }

        private void recomputeDimensions() {
            this.mWidth = 0;
            this.mHeight = 0;
            this.biggest = null;
            this.biggestDimension = 0;
            int i = this.mCount;
            int i2 = 0;
            while (i2 < i && this.mStartIndex + i2 < Flow.this.mDisplayedWidgetsCount) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i2];
                if (this.mOrientation == 0) {
                    int width = constraintWidget.getWidth();
                    int access$000 = Flow.this.mHorizontalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        access$000 = 0;
                    }
                    this.mWidth += width + access$000;
                    int access$300 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    if (this.biggest == null || this.biggestDimension < access$300) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = access$300;
                        this.mHeight = access$300;
                    }
                } else {
                    int access$200 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                    int access$3002 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    int access$100 = Flow.this.mVerticalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        access$100 = 0;
                    }
                    this.mHeight += access$3002 + access$100;
                    if (this.biggest == null || this.biggestDimension < access$200) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = access$200;
                        this.mWidth = access$200;
                    }
                }
                i2++;
            }
        }
    }

    private void measureChainWrap(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        int i7 = i;
        int i8 = i3;
        if (i7 != 0) {
            this.mChainList.clear();
            WidgetsList widgetsList = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
            this.mChainList.add(widgetsList);
            if (i2 == 0) {
                WidgetsList widgetsList2 = widgetsList;
                i4 = 0;
                int i9 = 0;
                int i10 = 0;
                while (i10 < i7) {
                    ConstraintWidget constraintWidget = constraintWidgetArr[i10];
                    int widgetWidth = getWidgetWidth(constraintWidget, i8);
                    if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        i4++;
                    }
                    int i11 = i4;
                    boolean z = (i9 == i8 || (this.mHorizontalGap + i9) + widgetWidth > i8) && widgetsList2.biggest != null;
                    if (!z && i10 > 0 && (i6 = this.mMaxElementsWrap) > 0 && i10 % i6 == 0) {
                        z = true;
                    }
                    if (z) {
                        WidgetsList widgetsList3 = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                        widgetsList3.setStartIndex(i10);
                        this.mChainList.add(widgetsList3);
                        i9 = widgetWidth;
                        widgetsList2 = widgetsList3;
                    } else {
                        i9 = i10 > 0 ? i9 + this.mHorizontalGap + widgetWidth : widgetWidth;
                    }
                    widgetsList2.add(constraintWidget);
                    i10++;
                    i4 = i11;
                }
            } else {
                WidgetsList widgetsList4 = widgetsList;
                int i12 = 0;
                int i13 = 0;
                int i14 = 0;
                while (i14 < i7) {
                    ConstraintWidget constraintWidget2 = constraintWidgetArr[i14];
                    int widgetHeight = getWidgetHeight(constraintWidget2, i8);
                    if (constraintWidget2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        i4++;
                    }
                    int i15 = i4;
                    boolean z2 = (i13 == i8 || (this.mVerticalGap + i13) + widgetHeight > i8) && widgetsList4.biggest != null;
                    if (!z2 && i14 > 0 && (i5 = this.mMaxElementsWrap) > 0 && i14 % i5 == 0) {
                        z2 = true;
                    }
                    if (z2) {
                        WidgetsList widgetsList5 = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                        widgetsList5.setStartIndex(i14);
                        this.mChainList.add(widgetsList5);
                        i13 = widgetHeight;
                        widgetsList4 = widgetsList5;
                    } else {
                        i13 = i14 > 0 ? i13 + this.mVerticalGap + widgetHeight : widgetHeight;
                    }
                    widgetsList4.add(constraintWidget2);
                    i14++;
                    i12 = i15;
                }
            }
            int size = this.mChainList.size();
            ConstraintAnchor constraintAnchor = this.mLeft;
            ConstraintAnchor constraintAnchor2 = this.mTop;
            ConstraintAnchor constraintAnchor3 = this.mRight;
            ConstraintAnchor constraintAnchor4 = this.mBottom;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            boolean z3 = getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (i4 > 0 && z3) {
                for (int i16 = 0; i16 < size; i16++) {
                    WidgetsList widgetsList6 = this.mChainList.get(i16);
                    if (i2 == 0) {
                        widgetsList6.measureMatchConstraints(i8 - widgetsList6.getWidth());
                    } else {
                        widgetsList6.measureMatchConstraints(i8 - widgetsList6.getHeight());
                    }
                }
            }
            ConstraintAnchor constraintAnchor5 = constraintAnchor3;
            int i17 = paddingTop;
            int i18 = paddingRight;
            int i19 = paddingBottom;
            int i20 = 0;
            ConstraintAnchor constraintAnchor6 = constraintAnchor;
            int i21 = paddingLeft;
            int i22 = 0;
            ConstraintAnchor constraintAnchor7 = constraintAnchor4;
            ConstraintAnchor constraintAnchor8 = constraintAnchor2;
            int i23 = 0;
            while (i20 < size) {
                WidgetsList widgetsList7 = this.mChainList.get(i20);
                if (i2 == 0) {
                    if (i20 < size - 1) {
                        constraintAnchor7 = this.mChainList.get(i20 + 1).biggest.mTop;
                        i19 = 0;
                    } else {
                        constraintAnchor7 = this.mBottom;
                        i19 = getPaddingBottom();
                    }
                    constraintAnchor8 = widgetsList7.biggest.mBottom;
                    widgetsList7.setup(i2, constraintAnchor6, constraintAnchor8, constraintAnchor5, constraintAnchor7, i21, i17, i18, i19, i3);
                    i22 = Math.max(i22, widgetsList7.getWidth());
                    i23 += widgetsList7.getHeight();
                    if (i20 > 0) {
                        i23 += this.mVerticalGap;
                    }
                    i17 = 0;
                } else {
                    if (i20 < size - 1) {
                        constraintAnchor5 = this.mChainList.get(i20 + 1).biggest.mLeft;
                        i18 = 0;
                    } else {
                        constraintAnchor5 = this.mRight;
                        i18 = getPaddingRight();
                    }
                    constraintAnchor6 = widgetsList7.biggest.mRight;
                    widgetsList7.setup(i2, constraintAnchor6, constraintAnchor8, constraintAnchor5, constraintAnchor7, i21, i17, i18, i19, i3);
                    i22 += widgetsList7.getWidth();
                    i23 = Math.max(i23, widgetsList7.getHeight());
                    if (i20 > 0) {
                        i22 += this.mHorizontalGap;
                    }
                    i21 = 0;
                }
                i20++;
                int i24 = i3;
            }
            iArr[0] = i22;
            iArr[1] = i23;
        }
    }

    private void measureNoWrap(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        WidgetsList widgetsList;
        int i4 = i;
        if (i4 != 0) {
            if (this.mChainList.size() == 0) {
                widgetsList = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                this.mChainList.add(widgetsList);
            } else {
                WidgetsList widgetsList2 = this.mChainList.get(0);
                widgetsList2.clear();
                widgetsList = widgetsList2;
                int i5 = i2;
                widgetsList.setup(i5, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), i3);
            }
            for (int i6 = 0; i6 < i4; i6++) {
                widgetsList.add(constraintWidgetArr[i6]);
            }
            iArr[0] = widgetsList.getWidth();
            iArr[1] = widgetsList.getHeight();
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x012e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x006b  */
    private void measureAligned(androidx.constraintlayout.solver.widgets.ConstraintWidget[] r17, int r18, int r19, int r20, int[] r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = 0
            if (r3 != 0) goto L_0x0030
            int r6 = r0.mMaxElementsWrap
            if (r6 > 0) goto L_0x002d
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x0014:
            if (r6 >= r2) goto L_0x002e
            if (r6 <= 0) goto L_0x001b
            int r9 = r0.mHorizontalGap
            int r7 = r7 + r9
        L_0x001b:
            r9 = r1[r6]
            if (r9 != 0) goto L_0x0020
            goto L_0x002a
        L_0x0020:
            int r9 = r0.getWidgetWidth(r9, r4)
            int r7 = r7 + r9
            if (r7 <= r4) goto L_0x0028
            goto L_0x002e
        L_0x0028:
            int r8 = r8 + 1
        L_0x002a:
            int r6 = r6 + 1
            goto L_0x0014
        L_0x002d:
            r8 = r6
        L_0x002e:
            r6 = 0
            goto L_0x0052
        L_0x0030:
            int r6 = r0.mMaxElementsWrap
            if (r6 > 0) goto L_0x0051
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x0037:
            if (r6 >= r2) goto L_0x0050
            if (r6 <= 0) goto L_0x003e
            int r9 = r0.mVerticalGap
            int r7 = r7 + r9
        L_0x003e:
            r9 = r1[r6]
            if (r9 != 0) goto L_0x0043
            goto L_0x004d
        L_0x0043:
            int r9 = r0.getWidgetHeight(r9, r4)
            int r7 = r7 + r9
            if (r7 <= r4) goto L_0x004b
            goto L_0x0050
        L_0x004b:
            int r8 = r8 + 1
        L_0x004d:
            int r6 = r6 + 1
            goto L_0x0037
        L_0x0050:
            r6 = r8
        L_0x0051:
            r8 = 0
        L_0x0052:
            int[] r7 = r0.mAlignedDimensions
            if (r7 != 0) goto L_0x005b
            r7 = 2
            int[] r7 = new int[r7]
            r0.mAlignedDimensions = r7
        L_0x005b:
            r7 = 1
            if (r6 != 0) goto L_0x0060
            if (r3 == r7) goto L_0x0064
        L_0x0060:
            if (r8 != 0) goto L_0x0067
            if (r3 != 0) goto L_0x0067
        L_0x0064:
            r9 = r6
        L_0x0065:
            r6 = 1
            goto L_0x0069
        L_0x0067:
            r9 = r6
            r6 = 0
        L_0x0069:
            if (r6 != 0) goto L_0x012e
            if (r3 != 0) goto L_0x0077
            float r9 = (float) r2
            float r10 = (float) r8
            float r9 = r9 / r10
            double r9 = (double) r9
            double r9 = java.lang.Math.ceil(r9)
            int r9 = (int) r9
            goto L_0x0080
        L_0x0077:
            float r8 = (float) r2
            float r10 = (float) r9
            float r8 = r8 / r10
            double r10 = (double) r8
            double r10 = java.lang.Math.ceil(r10)
            int r8 = (int) r10
        L_0x0080:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = r0.mAlignedBiggestElementsInCols
            r11 = 0
            if (r10 == 0) goto L_0x008d
            int r12 = r10.length
            if (r12 >= r8) goto L_0x0089
            goto L_0x008d
        L_0x0089:
            java.util.Arrays.fill(r10, r11)
            goto L_0x0091
        L_0x008d:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = new androidx.constraintlayout.solver.widgets.ConstraintWidget[r8]
            r0.mAlignedBiggestElementsInCols = r10
        L_0x0091:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = r0.mAlignedBiggestElementsInRows
            if (r10 == 0) goto L_0x009d
            int r12 = r10.length
            if (r12 >= r9) goto L_0x0099
            goto L_0x009d
        L_0x0099:
            java.util.Arrays.fill(r10, r11)
            goto L_0x00a1
        L_0x009d:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r10 = new androidx.constraintlayout.solver.widgets.ConstraintWidget[r9]
            r0.mAlignedBiggestElementsInRows = r10
        L_0x00a1:
            r10 = 0
        L_0x00a2:
            if (r10 >= r8) goto L_0x00ea
            r11 = 0
        L_0x00a5:
            if (r11 >= r9) goto L_0x00e7
            int r12 = r11 * r8
            int r12 = r12 + r10
            if (r3 != r7) goto L_0x00af
            int r12 = r10 * r9
            int r12 = r12 + r11
        L_0x00af:
            int r13 = r1.length
            if (r12 < r13) goto L_0x00b3
            goto L_0x00e4
        L_0x00b3:
            r12 = r1[r12]
            if (r12 != 0) goto L_0x00b8
            goto L_0x00e4
        L_0x00b8:
            int r13 = r0.getWidgetWidth(r12, r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r14 = r0.mAlignedBiggestElementsInCols
            r15 = r14[r10]
            if (r15 == 0) goto L_0x00ca
            r14 = r14[r10]
            int r14 = r14.getWidth()
            if (r14 >= r13) goto L_0x00ce
        L_0x00ca:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r13 = r0.mAlignedBiggestElementsInCols
            r13[r10] = r12
        L_0x00ce:
            int r13 = r0.getWidgetHeight(r12, r4)
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r14 = r0.mAlignedBiggestElementsInRows
            r15 = r14[r11]
            if (r15 == 0) goto L_0x00e0
            r14 = r14[r11]
            int r14 = r14.getHeight()
            if (r14 >= r13) goto L_0x00e4
        L_0x00e0:
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r13 = r0.mAlignedBiggestElementsInRows
            r13[r11] = r12
        L_0x00e4:
            int r11 = r11 + 1
            goto L_0x00a5
        L_0x00e7:
            int r10 = r10 + 1
            goto L_0x00a2
        L_0x00ea:
            r10 = 0
            r11 = 0
        L_0x00ec:
            if (r10 >= r8) goto L_0x0101
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r12 = r0.mAlignedBiggestElementsInCols
            r12 = r12[r10]
            if (r12 == 0) goto L_0x00fe
            if (r10 <= 0) goto L_0x00f9
            int r13 = r0.mHorizontalGap
            int r11 = r11 + r13
        L_0x00f9:
            int r12 = r0.getWidgetWidth(r12, r4)
            int r11 = r11 + r12
        L_0x00fe:
            int r10 = r10 + 1
            goto L_0x00ec
        L_0x0101:
            r10 = 0
            r12 = 0
        L_0x0103:
            if (r10 >= r9) goto L_0x0118
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r13 = r0.mAlignedBiggestElementsInRows
            r13 = r13[r10]
            if (r13 == 0) goto L_0x0115
            if (r10 <= 0) goto L_0x0110
            int r14 = r0.mVerticalGap
            int r12 = r12 + r14
        L_0x0110:
            int r13 = r0.getWidgetHeight(r13, r4)
            int r12 = r12 + r13
        L_0x0115:
            int r10 = r10 + 1
            goto L_0x0103
        L_0x0118:
            r21[r5] = r11
            r21[r7] = r12
            if (r3 != 0) goto L_0x0126
            if (r11 <= r4) goto L_0x0065
            if (r8 <= r7) goto L_0x0065
            int r8 = r8 + -1
            goto L_0x0069
        L_0x0126:
            if (r12 <= r4) goto L_0x0065
            if (r9 <= r7) goto L_0x0065
            int r9 = r9 + -1
            goto L_0x0069
        L_0x012e:
            int[] r1 = r0.mAlignedDimensions
            r1[r5] = r8
            r1[r7] = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.measureAligned(androidx.constraintlayout.solver.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    private void createAlignedConstraints(boolean z) {
        ConstraintWidget constraintWidget;
        if (this.mAlignedDimensions != null && this.mAlignedBiggestElementsInCols != null && this.mAlignedBiggestElementsInRows != null) {
            for (int i = 0; i < this.mDisplayedWidgetsCount; i++) {
                this.mDisplayedWidgets[i].resetAnchors();
            }
            int[] iArr = this.mAlignedDimensions;
            int i2 = iArr[0];
            int i3 = iArr[1];
            ConstraintWidget constraintWidget2 = null;
            for (int i4 = 0; i4 < i2; i4++) {
                ConstraintWidget constraintWidget3 = this.mAlignedBiggestElementsInCols[z ? (i2 - i4) - 1 : i4];
                if (!(constraintWidget3 == null || constraintWidget3.getVisibility() == 8)) {
                    if (i4 == 0) {
                        constraintWidget3.connect(constraintWidget3.mLeft, this.mLeft, getPaddingLeft());
                        constraintWidget3.setHorizontalChainStyle(this.mHorizontalStyle);
                        constraintWidget3.setHorizontalBiasPercent(this.mHorizontalBias);
                    }
                    if (i4 == i2 - 1) {
                        constraintWidget3.connect(constraintWidget3.mRight, this.mRight, getPaddingRight());
                    }
                    if (i4 > 0) {
                        constraintWidget3.connect(constraintWidget3.mLeft, constraintWidget2.mRight, this.mHorizontalGap);
                        constraintWidget2.connect(constraintWidget2.mRight, constraintWidget3.mLeft, 0);
                    }
                    constraintWidget2 = constraintWidget3;
                }
            }
            for (int i5 = 0; i5 < i3; i5++) {
                ConstraintWidget constraintWidget4 = this.mAlignedBiggestElementsInRows[i5];
                if (!(constraintWidget4 == null || constraintWidget4.getVisibility() == 8)) {
                    if (i5 == 0) {
                        constraintWidget4.connect(constraintWidget4.mTop, this.mTop, getPaddingTop());
                        constraintWidget4.setVerticalChainStyle(this.mVerticalStyle);
                        constraintWidget4.setVerticalBiasPercent(this.mVerticalBias);
                    }
                    if (i5 == i3 - 1) {
                        constraintWidget4.connect(constraintWidget4.mBottom, this.mBottom, getPaddingBottom());
                    }
                    if (i5 > 0) {
                        constraintWidget4.connect(constraintWidget4.mTop, constraintWidget2.mBottom, this.mVerticalGap);
                        constraintWidget2.connect(constraintWidget2.mBottom, constraintWidget4.mTop, 0);
                    }
                    constraintWidget2 = constraintWidget4;
                }
            }
            for (int i6 = 0; i6 < i2; i6++) {
                for (int i7 = 0; i7 < i3; i7++) {
                    int i8 = (i7 * i2) + i6;
                    if (this.mOrientation == 1) {
                        i8 = (i6 * i3) + i7;
                    }
                    ConstraintWidget[] constraintWidgetArr = this.mDisplayedWidgets;
                    if (!(i8 >= constraintWidgetArr.length || (constraintWidget = constraintWidgetArr[i8]) == null || constraintWidget.getVisibility() == 8)) {
                        ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInCols[i6];
                        ConstraintWidget constraintWidget6 = this.mAlignedBiggestElementsInRows[i7];
                        if (constraintWidget != constraintWidget5) {
                            constraintWidget.connect(constraintWidget.mLeft, constraintWidget5.mLeft, 0);
                            constraintWidget.connect(constraintWidget.mRight, constraintWidget5.mRight, 0);
                        }
                        if (constraintWidget != constraintWidget6) {
                            constraintWidget.connect(constraintWidget.mTop, constraintWidget6.mTop, 0);
                            constraintWidget.connect(constraintWidget.mBottom, constraintWidget6.mBottom, 0);
                        }
                    }
                }
            }
        }
    }

    public void addToSolver(LinearSystem linearSystem, boolean z) {
        super.addToSolver(linearSystem, z);
        boolean isRtl = getParent() != null ? ((ConstraintWidgetContainer) getParent()).isRtl() : false;
        int i = this.mWrapMode;
        if (i != 0) {
            if (i == 1) {
                int size = this.mChainList.size();
                int i2 = 0;
                while (i2 < size) {
                    this.mChainList.get(i2).createConstraints(isRtl, i2, i2 == size + -1);
                    i2++;
                }
            } else if (i == 2) {
                createAlignedConstraints(isRtl);
            }
        } else if (this.mChainList.size() > 0) {
            this.mChainList.get(0).createConstraints(isRtl, 0, true);
        }
        needsCallbackFromSolver(false);
    }
}
