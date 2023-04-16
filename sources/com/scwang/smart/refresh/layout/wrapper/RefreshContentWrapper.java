package com.scwang.smart.refresh.layout.wrapper;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingParent;
import androidx.viewpager.widget.ViewPager;
import com.scwang.smart.refresh.layout.api.RefreshContent;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.kernel.C2564R;
import com.scwang.smart.refresh.layout.listener.CoordinatorLayoutListener;
import com.scwang.smart.refresh.layout.listener.ScrollBoundaryDecider;
import com.scwang.smart.refresh.layout.simple.SimpleBoundaryDecider;
import com.scwang.smart.refresh.layout.util.DesignUtil;
import com.scwang.smart.refresh.layout.util.SmartUtil;
import java.util.LinkedList;
import java.util.List;

public class RefreshContentWrapper implements RefreshContent, CoordinatorLayoutListener, ValueAnimator.AnimatorUpdateListener {
    protected SimpleBoundaryDecider mBoundaryAdapter = new SimpleBoundaryDecider();
    protected View mContentView;
    protected boolean mEnableLoadMore = true;
    protected boolean mEnableRefresh = true;
    protected View mFixedFooter;
    protected View mFixedHeader;
    protected int mLastSpinner = 0;
    protected View mOriginalContentView;
    protected View mScrollableView;

    public RefreshContentWrapper(@NonNull View view) {
        this.mScrollableView = view;
        this.mOriginalContentView = view;
        this.mContentView = view;
    }

    /* access modifiers changed from: protected */
    public void findScrollableView(View view, RefreshKernel refreshKernel) {
        boolean isInEditMode = this.mContentView.isInEditMode();
        View view2 = null;
        while (true) {
            if (view2 != null && (!(view2 instanceof NestedScrollingParent) || (view2 instanceof NestedScrollingChild))) {
                break;
            }
            view = findScrollableViewInternal(view, view2 == null);
            if (view == view2) {
                break;
            }
            if (!isInEditMode) {
                DesignUtil.checkCoordinatorLayout(view, refreshKernel, this);
            }
            view2 = view;
        }
        if (view2 != null) {
            this.mScrollableView = view2;
        }
    }

    public void onCoordinatorUpdate(boolean z, boolean z2) {
        this.mEnableRefresh = z;
        this.mEnableLoadMore = z2;
    }

    /* access modifiers changed from: protected */
    public View findScrollableViewInternal(View view, boolean z) {
        LinkedList linkedList = new LinkedList();
        List list = linkedList;
        list.add(view);
        View view2 = null;
        while (list.size() > 0 && view2 == null) {
            View view3 = (View) linkedList.poll();
            if (view3 != null) {
                if ((z || view3 != view) && SmartUtil.isContentView(view3)) {
                    view2 = view3;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        list.add(viewGroup.getChildAt(i));
                    }
                }
            }
        }
        return view2 == null ? view : view2;
    }

    /* access modifiers changed from: protected */
    public View findScrollableViewByPoint(View view, PointF pointF, View view2) {
        if ((view instanceof ViewGroup) && pointF != null) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            PointF pointF2 = new PointF();
            while (childCount > 0) {
                View childAt = viewGroup.getChildAt(childCount - 1);
                if (!SmartUtil.isTransformedTouchPointInView(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    childCount--;
                } else if (!(childAt instanceof ViewPager) && SmartUtil.isContentView(childAt)) {
                    return childAt;
                } else {
                    pointF.offset(pointF2.x, pointF2.y);
                    View findScrollableViewByPoint = findScrollableViewByPoint(childAt, pointF, view2);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return findScrollableViewByPoint;
                }
            }
        }
        return view2;
    }

    @NonNull
    public View getView() {
        return this.mContentView;
    }

    @NonNull
    public View getScrollableView() {
        return this.mScrollableView;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveSpinner(int r6, int r7, int r8) {
        /*
            r5 = this;
            r0 = 1
            r1 = -1
            r2 = 0
            r3 = 0
            if (r7 == r1) goto L_0x0021
            android.view.View r4 = r5.mOriginalContentView
            android.view.View r7 = r4.findViewById(r7)
            if (r7 == 0) goto L_0x0021
            if (r6 <= 0) goto L_0x0016
            float r4 = (float) r6
            r7.setTranslationY(r4)
            r7 = 1
            goto L_0x0022
        L_0x0016:
            float r4 = r7.getTranslationY()
            int r4 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r4 <= 0) goto L_0x0021
            r7.setTranslationY(r3)
        L_0x0021:
            r7 = 0
        L_0x0022:
            if (r8 == r1) goto L_0x003f
            android.view.View r1 = r5.mOriginalContentView
            android.view.View r8 = r1.findViewById(r8)
            if (r8 == 0) goto L_0x003f
            if (r6 >= 0) goto L_0x0034
            float r7 = (float) r6
            r8.setTranslationY(r7)
            r7 = 1
            goto L_0x003f
        L_0x0034:
            float r0 = r8.getTranslationY()
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x003f
            r8.setTranslationY(r3)
        L_0x003f:
            if (r7 != 0) goto L_0x0048
            android.view.View r7 = r5.mOriginalContentView
            float r8 = (float) r6
            r7.setTranslationY(r8)
            goto L_0x004d
        L_0x0048:
            android.view.View r7 = r5.mOriginalContentView
            r7.setTranslationY(r3)
        L_0x004d:
            android.view.View r7 = r5.mFixedHeader
            if (r7 == 0) goto L_0x0059
            int r8 = java.lang.Math.max(r2, r6)
            float r8 = (float) r8
            r7.setTranslationY(r8)
        L_0x0059:
            android.view.View r7 = r5.mFixedFooter
            if (r7 == 0) goto L_0x0065
            int r6 = java.lang.Math.min(r2, r6)
            float r6 = (float) r6
            r7.setTranslationY(r6)
        L_0x0065:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smart.refresh.layout.wrapper.RefreshContentWrapper.moveSpinner(int, int, int):void");
    }

    public boolean canRefresh() {
        return this.mEnableRefresh && this.mBoundaryAdapter.canRefresh(this.mContentView);
    }

    public boolean canLoadMore() {
        return this.mEnableLoadMore && this.mBoundaryAdapter.canLoadMore(this.mContentView);
    }

    public void onActionDown(MotionEvent motionEvent) {
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        pointF.offset((float) (-this.mContentView.getLeft()), (float) (-this.mContentView.getTop()));
        View view = this.mScrollableView;
        View view2 = this.mContentView;
        if (view != view2) {
            this.mScrollableView = findScrollableViewByPoint(view2, pointF, view);
        }
        if (this.mScrollableView == this.mContentView) {
            this.mBoundaryAdapter.mActionEvent = null;
        } else {
            this.mBoundaryAdapter.mActionEvent = pointF;
        }
    }

    public void setUpComponent(RefreshKernel refreshKernel, View view, View view2) {
        findScrollableView(this.mContentView, refreshKernel);
        if (view != null || view2 != null) {
            this.mFixedHeader = view;
            this.mFixedFooter = view2;
            FrameLayout frameLayout = new FrameLayout(this.mContentView.getContext());
            int indexOfChild = refreshKernel.getRefreshLayout().getLayout().indexOfChild(this.mContentView);
            refreshKernel.getRefreshLayout().getLayout().removeView(this.mContentView);
            frameLayout.addView(this.mContentView, 0, new ViewGroup.LayoutParams(-1, -1));
            refreshKernel.getRefreshLayout().getLayout().addView(frameLayout, indexOfChild, this.mContentView.getLayoutParams());
            this.mContentView = frameLayout;
            if (view != null) {
                view.setTag(C2564R.C2565id.srl_tag, "fixed-top");
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                int indexOfChild2 = viewGroup.indexOfChild(view);
                viewGroup.removeView(view);
                layoutParams.height = SmartUtil.measureViewHeight(view);
                viewGroup.addView(new Space(this.mContentView.getContext()), indexOfChild2, layoutParams);
                frameLayout.addView(view, 1, layoutParams);
            }
            if (view2 != null) {
                view2.setTag(C2564R.C2565id.srl_tag, "fixed-bottom");
                ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
                ViewGroup viewGroup2 = (ViewGroup) view2.getParent();
                int indexOfChild3 = viewGroup2.indexOfChild(view2);
                viewGroup2.removeView(view2);
                FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(layoutParams2);
                layoutParams2.height = SmartUtil.measureViewHeight(view2);
                viewGroup2.addView(new Space(this.mContentView.getContext()), indexOfChild3, layoutParams2);
                layoutParams3.gravity = 80;
                frameLayout.addView(view2, 1, layoutParams3);
            }
        }
    }

    public void setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider) {
        if (scrollBoundaryDecider instanceof SimpleBoundaryDecider) {
            this.mBoundaryAdapter = (SimpleBoundaryDecider) scrollBoundaryDecider;
        } else {
            this.mBoundaryAdapter.boundary = scrollBoundaryDecider;
        }
    }

    public void setEnableLoadMoreWhenContentNotFull(boolean z) {
        this.mBoundaryAdapter.mEnableLoadMoreWhenContentNotFull = z;
    }

    public ValueAnimator.AnimatorUpdateListener scrollContentWhenFinished(int i) {
        View view = this.mScrollableView;
        if (view == null || i == 0) {
            return null;
        }
        if ((i >= 0 || !view.canScrollVertically(1)) && (i <= 0 || !this.mScrollableView.canScrollVertically(-1))) {
            return null;
        }
        this.mLastSpinner = i;
        return this;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        try {
            float scaleY = ((float) (intValue - this.mLastSpinner)) * this.mScrollableView.getScaleY();
            if (this.mScrollableView instanceof AbsListView) {
                SmartUtil.scrollListBy((AbsListView) this.mScrollableView, (int) scaleY);
            } else {
                this.mScrollableView.scrollBy(0, (int) scaleY);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.mLastSpinner = intValue;
    }
}
