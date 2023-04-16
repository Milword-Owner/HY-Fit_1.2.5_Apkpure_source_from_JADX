package com.scwang.smart.refresh.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.utils.Utils;
import com.scwang.smart.refresh.layout.api.RefreshComponent;
import com.scwang.smart.refresh.layout.api.RefreshContent;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.DimensionStatus;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.kernel.C2564R;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshInitializer;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnMultiListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.ScrollBoundaryDecider;
import com.scwang.smart.refresh.layout.util.SmartUtil;
import com.scwang.smart.refresh.layout.wrapper.RefreshContentWrapper;

@SuppressLint({"RestrictedApi"})
public class SmartRefreshLayout extends ViewGroup implements RefreshLayout, NestedScrollingParent {
    protected static ViewGroup.MarginLayoutParams sDefaultMarginLP = new ViewGroup.MarginLayoutParams(-1, -1);
    protected static DefaultRefreshFooterCreator sFooterCreator;
    protected static DefaultRefreshHeaderCreator sHeaderCreator;
    protected static DefaultRefreshInitializer sRefreshInitializer;
    protected Runnable animationRunnable;
    protected boolean mAttachedToWindow;
    protected int mCurrentVelocity;
    protected boolean mDisableContentWhenLoading;
    protected boolean mDisableContentWhenRefresh;
    protected char mDragDirection;
    protected float mDragRate;
    protected boolean mEnableAutoLoadMore;
    protected boolean mEnableClipFooterWhenFixedBehind;
    protected boolean mEnableClipHeaderWhenFixedBehind;
    protected boolean mEnableDisallowIntercept;
    protected boolean mEnableFooterFollowWhenNoMoreData;
    protected boolean mEnableFooterTranslationContent;
    protected boolean mEnableHeaderTranslationContent;
    protected boolean mEnableLoadMore;
    protected boolean mEnableLoadMoreWhenContentNotFull;
    protected boolean mEnableNestedScrolling;
    protected boolean mEnableOverScrollBounce;
    protected boolean mEnableOverScrollDrag;
    protected boolean mEnablePreviewInEditMode;
    protected boolean mEnablePureScrollMode;
    protected boolean mEnableRefresh;
    protected boolean mEnableScrollContentWhenLoaded;
    protected boolean mEnableScrollContentWhenRefreshed;
    protected MotionEvent mFalsifyEvent;
    protected int mFixedFooterViewId;
    protected int mFixedHeaderViewId;
    protected int mFloorDuration;
    protected int mFooterBackgroundColor;
    protected int mFooterHeight;
    protected DimensionStatus mFooterHeightStatus;
    protected int mFooterInsetStart;
    protected boolean mFooterLocked;
    protected float mFooterMaxDragRate;
    protected boolean mFooterNeedTouchEventWhenLoading;
    protected boolean mFooterNoMoreData;
    protected boolean mFooterNoMoreDataEffective;
    protected int mFooterTranslationViewId;
    protected float mFooterTriggerRate;
    protected Handler mHandler;
    protected int mHeaderBackgroundColor;
    protected int mHeaderHeight;
    protected DimensionStatus mHeaderHeightStatus;
    protected int mHeaderInsetStart;
    protected float mHeaderMaxDragRate;
    protected boolean mHeaderNeedTouchEventWhenRefreshing;
    protected int mHeaderTranslationViewId;
    protected float mHeaderTriggerRate;
    protected boolean mIsBeingDragged;
    protected RefreshKernel mKernel;
    protected long mLastOpenTime;
    protected int mLastSpinner;
    protected float mLastTouchX;
    protected float mLastTouchY;
    protected OnLoadMoreListener mLoadMoreListener;
    protected boolean mManualFooterTranslationContent;
    protected boolean mManualHeaderTranslationContent;
    protected boolean mManualLoadMore;
    protected int mMaximumVelocity;
    protected int mMinimumVelocity;
    protected NestedScrollingChildHelper mNestedChild;
    protected boolean mNestedInProgress;
    protected NestedScrollingParentHelper mNestedParent;
    protected OnMultiListener mOnMultiListener;
    protected Paint mPaint;
    protected int[] mParentOffsetInWindow;
    protected int[] mPrimaryColors;
    protected int mReboundDuration;
    protected Interpolator mReboundInterpolator;
    protected RefreshContent mRefreshContent;
    protected RefreshComponent mRefreshFooter;
    protected RefreshComponent mRefreshHeader;
    protected OnRefreshListener mRefreshListener;
    protected int mScreenHeightPixels;
    protected ScrollBoundaryDecider mScrollBoundaryDecider;
    protected Scroller mScroller;
    protected int mSpinner;
    protected RefreshState mState;
    protected boolean mSuperDispatchTouchEvent;
    protected int mTotalUnconsumed;
    protected int mTouchSlop;
    protected int mTouchSpinner;
    protected float mTouchX;
    protected float mTouchY;
    protected float mTwoLevelBottomPullUpToCloseRate;
    protected VelocityTracker mVelocityTracker;
    protected boolean mVerticalPermit;
    protected RefreshState mViceState;
    protected ValueAnimator reboundAnimator;

    @NonNull
    public ViewGroup getLayout() {
        return this;
    }

    public SmartRefreshLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFloorDuration = 300;
        this.mReboundDuration = 300;
        this.mDragRate = 0.5f;
        this.mDragDirection = 'n';
        this.mFixedHeaderViewId = -1;
        this.mFixedFooterViewId = -1;
        this.mHeaderTranslationViewId = -1;
        this.mFooterTranslationViewId = -1;
        this.mEnableRefresh = true;
        this.mEnableLoadMore = false;
        this.mEnableClipHeaderWhenFixedBehind = true;
        this.mEnableClipFooterWhenFixedBehind = true;
        this.mEnableHeaderTranslationContent = true;
        this.mEnableFooterTranslationContent = true;
        this.mEnableFooterFollowWhenNoMoreData = false;
        this.mEnablePreviewInEditMode = true;
        this.mEnableOverScrollBounce = true;
        this.mEnableOverScrollDrag = false;
        this.mEnableAutoLoadMore = true;
        this.mEnablePureScrollMode = false;
        this.mEnableScrollContentWhenLoaded = true;
        this.mEnableScrollContentWhenRefreshed = true;
        this.mEnableLoadMoreWhenContentNotFull = true;
        this.mEnableNestedScrolling = true;
        this.mDisableContentWhenRefresh = false;
        this.mDisableContentWhenLoading = false;
        this.mFooterNoMoreData = false;
        this.mFooterNoMoreDataEffective = false;
        this.mManualLoadMore = false;
        this.mManualHeaderTranslationContent = false;
        this.mManualFooterTranslationContent = false;
        this.mParentOffsetInWindow = new int[2];
        this.mNestedChild = new NestedScrollingChildHelper(this);
        this.mNestedParent = new NestedScrollingParentHelper(this);
        this.mHeaderHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mFooterHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mHeaderMaxDragRate = 2.5f;
        this.mFooterMaxDragRate = 2.5f;
        this.mHeaderTriggerRate = 1.0f;
        this.mFooterTriggerRate = 1.0f;
        this.mTwoLevelBottomPullUpToCloseRate = 0.16666667f;
        this.mKernel = new RefreshKernelImpl();
        this.mState = RefreshState.None;
        this.mViceState = RefreshState.None;
        this.mLastOpenTime = 0;
        this.mHeaderBackgroundColor = 0;
        this.mFooterBackgroundColor = 0;
        this.mFooterLocked = false;
        this.mVerticalPermit = false;
        this.mFalsifyEvent = null;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mScroller = new Scroller(context);
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mScreenHeightPixels = context.getResources().getDisplayMetrics().heightPixels;
        this.mReboundInterpolator = new SmartUtil(SmartUtil.INTERPOLATOR_VISCOUS_FLUID);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mFooterHeight = SmartUtil.dp2px(60.0f);
        this.mHeaderHeight = SmartUtil.dp2px(100.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2564R.styleable.SmartRefreshLayout);
        if (!obtainStyledAttributes.hasValue(C2564R.styleable.SmartRefreshLayout_android_clipToPadding)) {
            super.setClipToPadding(false);
        }
        if (!obtainStyledAttributes.hasValue(C2564R.styleable.SmartRefreshLayout_android_clipChildren)) {
            super.setClipChildren(false);
        }
        DefaultRefreshInitializer defaultRefreshInitializer = sRefreshInitializer;
        if (defaultRefreshInitializer != null) {
            defaultRefreshInitializer.initialize(context, this);
        }
        this.mDragRate = obtainStyledAttributes.getFloat(C2564R.styleable.SmartRefreshLayout_srlDragRate, this.mDragRate);
        this.mHeaderMaxDragRate = obtainStyledAttributes.getFloat(C2564R.styleable.SmartRefreshLayout_srlHeaderMaxDragRate, this.mHeaderMaxDragRate);
        this.mFooterMaxDragRate = obtainStyledAttributes.getFloat(C2564R.styleable.SmartRefreshLayout_srlFooterMaxDragRate, this.mFooterMaxDragRate);
        this.mHeaderTriggerRate = obtainStyledAttributes.getFloat(C2564R.styleable.SmartRefreshLayout_srlHeaderTriggerRate, this.mHeaderTriggerRate);
        this.mFooterTriggerRate = obtainStyledAttributes.getFloat(C2564R.styleable.SmartRefreshLayout_srlFooterTriggerRate, this.mFooterTriggerRate);
        this.mEnableRefresh = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableRefresh, this.mEnableRefresh);
        this.mReboundDuration = obtainStyledAttributes.getInt(C2564R.styleable.SmartRefreshLayout_srlReboundDuration, this.mReboundDuration);
        this.mEnableLoadMore = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableLoadMore, this.mEnableLoadMore);
        this.mHeaderHeight = obtainStyledAttributes.getDimensionPixelOffset(C2564R.styleable.SmartRefreshLayout_srlHeaderHeight, this.mHeaderHeight);
        this.mFooterHeight = obtainStyledAttributes.getDimensionPixelOffset(C2564R.styleable.SmartRefreshLayout_srlFooterHeight, this.mFooterHeight);
        this.mHeaderInsetStart = obtainStyledAttributes.getDimensionPixelOffset(C2564R.styleable.SmartRefreshLayout_srlHeaderInsetStart, this.mHeaderInsetStart);
        this.mFooterInsetStart = obtainStyledAttributes.getDimensionPixelOffset(C2564R.styleable.SmartRefreshLayout_srlFooterInsetStart, this.mFooterInsetStart);
        this.mDisableContentWhenRefresh = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlDisableContentWhenRefresh, this.mDisableContentWhenRefresh);
        this.mDisableContentWhenLoading = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlDisableContentWhenLoading, this.mDisableContentWhenLoading);
        this.mEnableHeaderTranslationContent = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent, this.mEnableHeaderTranslationContent);
        this.mEnableFooterTranslationContent = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent, this.mEnableFooterTranslationContent);
        this.mEnablePreviewInEditMode = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnablePreviewInEditMode, this.mEnablePreviewInEditMode);
        this.mEnableAutoLoadMore = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableAutoLoadMore, this.mEnableAutoLoadMore);
        this.mEnableOverScrollBounce = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableOverScrollBounce, this.mEnableOverScrollBounce);
        this.mEnablePureScrollMode = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnablePureScrollMode, this.mEnablePureScrollMode);
        this.mEnableScrollContentWhenLoaded = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenLoaded, this.mEnableScrollContentWhenLoaded);
        this.mEnableScrollContentWhenRefreshed = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenRefreshed, this.mEnableScrollContentWhenRefreshed);
        this.mEnableLoadMoreWhenContentNotFull = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableLoadMoreWhenContentNotFull, this.mEnableLoadMoreWhenContentNotFull);
        this.mEnableFooterFollowWhenNoMoreData = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenLoadFinished, this.mEnableFooterFollowWhenNoMoreData);
        this.mEnableFooterFollowWhenNoMoreData = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenNoMoreData, this.mEnableFooterFollowWhenNoMoreData);
        this.mEnableClipHeaderWhenFixedBehind = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableClipHeaderWhenFixedBehind, this.mEnableClipHeaderWhenFixedBehind);
        this.mEnableClipFooterWhenFixedBehind = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableClipFooterWhenFixedBehind, this.mEnableClipFooterWhenFixedBehind);
        this.mEnableOverScrollDrag = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableOverScrollDrag, this.mEnableOverScrollDrag);
        this.mFixedHeaderViewId = obtainStyledAttributes.getResourceId(C2564R.styleable.SmartRefreshLayout_srlFixedHeaderViewId, this.mFixedHeaderViewId);
        this.mFixedFooterViewId = obtainStyledAttributes.getResourceId(C2564R.styleable.SmartRefreshLayout_srlFixedFooterViewId, this.mFixedFooterViewId);
        this.mHeaderTranslationViewId = obtainStyledAttributes.getResourceId(C2564R.styleable.SmartRefreshLayout_srlHeaderTranslationViewId, this.mHeaderTranslationViewId);
        this.mFooterTranslationViewId = obtainStyledAttributes.getResourceId(C2564R.styleable.SmartRefreshLayout_srlFooterTranslationViewId, this.mFooterTranslationViewId);
        this.mEnableNestedScrolling = obtainStyledAttributes.getBoolean(C2564R.styleable.SmartRefreshLayout_srlEnableNestedScrolling, this.mEnableNestedScrolling);
        this.mNestedChild.setNestedScrollingEnabled(this.mEnableNestedScrolling);
        this.mManualLoadMore = this.mManualLoadMore || obtainStyledAttributes.hasValue(C2564R.styleable.SmartRefreshLayout_srlEnableLoadMore);
        this.mManualHeaderTranslationContent = this.mManualHeaderTranslationContent || obtainStyledAttributes.hasValue(C2564R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent);
        this.mManualFooterTranslationContent = this.mManualFooterTranslationContent || obtainStyledAttributes.hasValue(C2564R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent);
        this.mHeaderHeightStatus = obtainStyledAttributes.hasValue(C2564R.styleable.SmartRefreshLayout_srlHeaderHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mHeaderHeightStatus;
        this.mFooterHeightStatus = obtainStyledAttributes.hasValue(C2564R.styleable.SmartRefreshLayout_srlFooterHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mFooterHeightStatus;
        int color = obtainStyledAttributes.getColor(C2564R.styleable.SmartRefreshLayout_srlAccentColor, 0);
        int color2 = obtainStyledAttributes.getColor(C2564R.styleable.SmartRefreshLayout_srlPrimaryColor, 0);
        if (color2 != 0) {
            if (color != 0) {
                this.mPrimaryColors = new int[]{color2, color};
            } else {
                this.mPrimaryColors = new int[]{color2};
            }
        } else if (color != 0) {
            this.mPrimaryColors = new int[]{0, color};
        }
        if (this.mEnablePureScrollMode && !this.mManualLoadMore && !this.mEnableLoadMore) {
            this.mEnableLoadMore = true;
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFinishInflate() {
        /*
            r11 = this;
            super.onFinishInflate()
            int r0 = super.getChildCount()
            r1 = 3
            if (r0 > r1) goto L_0x009e
            r2 = -1
            r3 = 0
            r4 = 0
            r5 = -1
            r6 = 0
        L_0x000f:
            r7 = 2
            r8 = 1
            if (r4 >= r0) goto L_0x0033
            android.view.View r9 = super.getChildAt(r4)
            boolean r10 = com.scwang.smart.refresh.layout.util.SmartUtil.isContentView(r9)
            if (r10 == 0) goto L_0x0024
            if (r6 < r7) goto L_0x0021
            if (r4 != r8) goto L_0x0024
        L_0x0021:
            r5 = r4
            r6 = 2
            goto L_0x0030
        L_0x0024:
            boolean r7 = r9 instanceof com.scwang.smart.refresh.layout.api.RefreshComponent
            if (r7 != 0) goto L_0x0030
            if (r6 >= r8) goto L_0x0030
            if (r4 <= 0) goto L_0x002d
            goto L_0x002e
        L_0x002d:
            r8 = 0
        L_0x002e:
            r5 = r4
            r6 = r8
        L_0x0030:
            int r4 = r4 + 1
            goto L_0x000f
        L_0x0033:
            if (r5 < 0) goto L_0x004d
            com.scwang.smart.refresh.layout.wrapper.RefreshContentWrapper r4 = new com.scwang.smart.refresh.layout.wrapper.RefreshContentWrapper
            android.view.View r6 = super.getChildAt(r5)
            r4.<init>(r6)
            r11.mRefreshContent = r4
            if (r5 != r8) goto L_0x0048
            if (r0 != r1) goto L_0x0046
            r1 = 0
            goto L_0x004f
        L_0x0046:
            r1 = 0
            goto L_0x004e
        L_0x0048:
            if (r0 != r7) goto L_0x004d
            r1 = -1
            r7 = 1
            goto L_0x004f
        L_0x004d:
            r1 = -1
        L_0x004e:
            r7 = -1
        L_0x004f:
            r4 = 0
        L_0x0050:
            if (r4 >= r0) goto L_0x009d
            android.view.View r5 = super.getChildAt(r4)
            if (r4 == r1) goto L_0x008b
            if (r4 == r7) goto L_0x0065
            if (r1 != r2) goto L_0x0065
            com.scwang.smart.refresh.layout.api.RefreshComponent r6 = r11.mRefreshHeader
            if (r6 != 0) goto L_0x0065
            boolean r6 = r5 instanceof com.scwang.smart.refresh.layout.api.RefreshHeader
            if (r6 == 0) goto L_0x0065
            goto L_0x008b
        L_0x0065:
            if (r4 == r7) goto L_0x006d
            if (r7 != r2) goto L_0x009a
            boolean r6 = r5 instanceof com.scwang.smart.refresh.layout.api.RefreshFooter
            if (r6 == 0) goto L_0x009a
        L_0x006d:
            boolean r6 = r11.mEnableLoadMore
            if (r6 != 0) goto L_0x0078
            boolean r6 = r11.mManualLoadMore
            if (r6 != 0) goto L_0x0076
            goto L_0x0078
        L_0x0076:
            r6 = 0
            goto L_0x0079
        L_0x0078:
            r6 = 1
        L_0x0079:
            r11.mEnableLoadMore = r6
            boolean r6 = r5 instanceof com.scwang.smart.refresh.layout.api.RefreshFooter
            if (r6 == 0) goto L_0x0082
            com.scwang.smart.refresh.layout.api.RefreshFooter r5 = (com.scwang.smart.refresh.layout.api.RefreshFooter) r5
            goto L_0x0088
        L_0x0082:
            com.scwang.smart.refresh.layout.wrapper.RefreshFooterWrapper r6 = new com.scwang.smart.refresh.layout.wrapper.RefreshFooterWrapper
            r6.<init>(r5)
            r5 = r6
        L_0x0088:
            r11.mRefreshFooter = r5
            goto L_0x009a
        L_0x008b:
            boolean r6 = r5 instanceof com.scwang.smart.refresh.layout.api.RefreshHeader
            if (r6 == 0) goto L_0x0092
            com.scwang.smart.refresh.layout.api.RefreshHeader r5 = (com.scwang.smart.refresh.layout.api.RefreshHeader) r5
            goto L_0x0098
        L_0x0092:
            com.scwang.smart.refresh.layout.wrapper.RefreshHeaderWrapper r6 = new com.scwang.smart.refresh.layout.wrapper.RefreshHeaderWrapper
            r6.<init>(r5)
            r5 = r6
        L_0x0098:
            r11.mRefreshHeader = r5
        L_0x009a:
            int r4 = r4 + 1
            goto L_0x0050
        L_0x009d:
            return
        L_0x009e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "最多只支持3个子View，Most only support three sub view"
            r0.<init>(r1)
            goto L_0x00a7
        L_0x00a6:
            throw r0
        L_0x00a7:
            goto L_0x00a6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smart.refresh.layout.SmartRefreshLayout.onFinishInflate():void");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        RefreshComponent refreshComponent;
        DefaultRefreshHeaderCreator defaultRefreshHeaderCreator;
        super.onAttachedToWindow();
        boolean z = true;
        this.mAttachedToWindow = true;
        if (!isInEditMode()) {
            if (this.mRefreshHeader == null && (defaultRefreshHeaderCreator = sHeaderCreator) != null) {
                RefreshHeader createRefreshHeader = defaultRefreshHeaderCreator.createRefreshHeader(getContext(), this);
                if (createRefreshHeader != null) {
                    setRefreshHeader(createRefreshHeader);
                } else {
                    throw new RuntimeException("DefaultRefreshHeaderCreator can not return null");
                }
            }
            if (this.mRefreshFooter == null) {
                DefaultRefreshFooterCreator defaultRefreshFooterCreator = sFooterCreator;
                if (defaultRefreshFooterCreator != null) {
                    RefreshFooter createRefreshFooter = defaultRefreshFooterCreator.createRefreshFooter(getContext(), this);
                    if (createRefreshFooter != null) {
                        setRefreshFooter(createRefreshFooter);
                    } else {
                        throw new RuntimeException("DefaultRefreshFooterCreator can not return null");
                    }
                }
            } else {
                if (!this.mEnableLoadMore && this.mManualLoadMore) {
                    z = false;
                }
                this.mEnableLoadMore = z;
            }
            if (this.mRefreshContent == null) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    RefreshComponent refreshComponent2 = this.mRefreshHeader;
                    if ((refreshComponent2 == null || childAt != refreshComponent2.getView()) && ((refreshComponent = this.mRefreshFooter) == null || childAt != refreshComponent.getView())) {
                        this.mRefreshContent = new RefreshContentWrapper(childAt);
                    }
                }
            }
            if (this.mRefreshContent == null) {
                int dp2px = SmartUtil.dp2px(20.0f);
                TextView textView = new TextView(getContext());
                textView.setTextColor(-39424);
                textView.setGravity(17);
                textView.setTextSize(20.0f);
                textView.setText(C2564R.string.srl_content_empty);
                super.addView(textView, 0, new LayoutParams(-1, -1));
                this.mRefreshContent = new RefreshContentWrapper(textView);
                this.mRefreshContent.getView().setPadding(dp2px, dp2px, dp2px, dp2px);
            }
            View findViewById = findViewById(this.mFixedHeaderViewId);
            View findViewById2 = findViewById(this.mFixedFooterViewId);
            this.mRefreshContent.setScrollBoundaryDecider(this.mScrollBoundaryDecider);
            this.mRefreshContent.setEnableLoadMoreWhenContentNotFull(this.mEnableLoadMoreWhenContentNotFull);
            this.mRefreshContent.setUpComponent(this.mKernel, findViewById, findViewById2);
            if (this.mSpinner != 0) {
                notifyStateChanged(RefreshState.None);
                RefreshContent refreshContent = this.mRefreshContent;
                this.mSpinner = 0;
                refreshContent.moveSpinner(0, this.mHeaderTranslationViewId, this.mFooterTranslationViewId);
            }
        }
        int[] iArr = this.mPrimaryColors;
        if (iArr != null) {
            RefreshComponent refreshComponent3 = this.mRefreshHeader;
            if (refreshComponent3 != null) {
                refreshComponent3.setPrimaryColors(iArr);
            }
            RefreshComponent refreshComponent4 = this.mRefreshFooter;
            if (refreshComponent4 != null) {
                refreshComponent4.setPrimaryColors(this.mPrimaryColors);
            }
        }
        RefreshContent refreshContent2 = this.mRefreshContent;
        if (refreshContent2 != null) {
            super.bringChildToFront(refreshContent2.getView());
        }
        RefreshComponent refreshComponent5 = this.mRefreshHeader;
        if (refreshComponent5 != null && refreshComponent5.getSpinnerStyle().front) {
            super.bringChildToFront(this.mRefreshHeader.getView());
        }
        RefreshComponent refreshComponent6 = this.mRefreshFooter;
        if (refreshComponent6 != null && refreshComponent6.getSpinnerStyle().front) {
            super.bringChildToFront(this.mRefreshFooter.getView());
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = i;
        int i8 = i2;
        boolean z = isInEditMode() && this.mEnablePreviewInEditMode;
        int childCount = super.getChildCount();
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = super.getChildAt(i10);
            if (childAt.getVisibility() != 8 && !"GONE".equals(childAt.getTag(C2564R.C2565id.srl_tag))) {
                RefreshComponent refreshComponent = this.mRefreshHeader;
                if (refreshComponent != null && refreshComponent.getView() == childAt) {
                    View view = this.mRefreshHeader.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    int childMeasureSpec = ViewGroup.getChildMeasureSpec(i7, marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, layoutParams.width);
                    int i11 = this.mHeaderHeight;
                    if (this.mHeaderHeightStatus.ordinal < DimensionStatus.XmlLayoutUnNotify.ordinal) {
                        if (layoutParams.height > 0) {
                            i11 = layoutParams.height + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                            if (this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                                this.mHeaderHeight = layoutParams.height + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                                this.mHeaderHeightStatus = DimensionStatus.XmlExactUnNotify;
                            }
                        } else if (layoutParams.height == -2 && (this.mRefreshHeader.getSpinnerStyle() != SpinnerStyle.MatchLayout || !this.mHeaderHeightStatus.notified)) {
                            int max = Math.max((View.MeasureSpec.getSize(i2) - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, 0);
                            view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(max, Integer.MIN_VALUE));
                            int measuredHeight = view.getMeasuredHeight();
                            if (measuredHeight > 0) {
                                if (measuredHeight != max && this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                                    this.mHeaderHeight = measuredHeight + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                                    this.mHeaderHeightStatus = DimensionStatus.XmlWrapUnNotify;
                                }
                                i11 = -1;
                            }
                        }
                    }
                    if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                        i11 = View.MeasureSpec.getSize(i2);
                        i6 = -1;
                        i5 = 0;
                    } else {
                        if (!this.mRefreshHeader.getSpinnerStyle().scale || z) {
                            i5 = 0;
                        } else {
                            i5 = 0;
                            i11 = Math.max(0, isEnableRefreshOrLoadMore(this.mEnableRefresh) ? this.mSpinner : 0);
                        }
                        i6 = -1;
                    }
                    if (i11 != i6) {
                        view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((i11 - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, i5), 1073741824));
                    }
                    if (!this.mHeaderHeightStatus.notified) {
                        this.mHeaderHeightStatus = this.mHeaderHeightStatus.notified();
                        RefreshComponent refreshComponent2 = this.mRefreshHeader;
                        RefreshKernel refreshKernel = this.mKernel;
                        int i12 = this.mHeaderHeight;
                        refreshComponent2.onInitialized(refreshKernel, i12, (int) (this.mHeaderMaxDragRate * ((float) i12)));
                    }
                    if (z && isEnableRefreshOrLoadMore(this.mEnableRefresh)) {
                        i9 += view.getMeasuredHeight();
                    }
                }
                RefreshComponent refreshComponent3 = this.mRefreshFooter;
                if (refreshComponent3 != null && refreshComponent3.getView() == childAt) {
                    View view2 = this.mRefreshFooter.getView();
                    ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : sDefaultMarginLP;
                    int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i7, marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin, layoutParams2.width);
                    int i13 = this.mFooterHeight;
                    if (this.mFooterHeightStatus.ordinal < DimensionStatus.XmlLayoutUnNotify.ordinal) {
                        if (layoutParams2.height > 0) {
                            i13 = layoutParams2.height + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                            if (this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                                this.mFooterHeight = layoutParams2.height + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                                this.mFooterHeightStatus = DimensionStatus.XmlExactUnNotify;
                            }
                        } else if (layoutParams2.height == -2 && (this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.MatchLayout || !this.mFooterHeightStatus.notified)) {
                            int max2 = Math.max((View.MeasureSpec.getSize(i2) - marginLayoutParams2.bottomMargin) - marginLayoutParams2.topMargin, 0);
                            view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(max2, Integer.MIN_VALUE));
                            int measuredHeight2 = view2.getMeasuredHeight();
                            if (measuredHeight2 > 0) {
                                if (measuredHeight2 != max2 && this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                                    this.mFooterHeight = measuredHeight2 + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                                    this.mFooterHeightStatus = DimensionStatus.XmlWrapUnNotify;
                                }
                                i13 = -1;
                            }
                        }
                    }
                    if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                        i13 = View.MeasureSpec.getSize(i2);
                        i4 = -1;
                        i3 = 0;
                    } else {
                        if (!this.mRefreshFooter.getSpinnerStyle().scale || z) {
                            i3 = 0;
                        } else {
                            i3 = 0;
                            i13 = Math.max(0, isEnableRefreshOrLoadMore(this.mEnableLoadMore) ? -this.mSpinner : 0);
                        }
                        i4 = -1;
                    }
                    if (i13 != i4) {
                        view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.max((i13 - marginLayoutParams2.bottomMargin) - marginLayoutParams2.topMargin, i3), 1073741824));
                    }
                    if (!this.mFooterHeightStatus.notified) {
                        this.mFooterHeightStatus = this.mFooterHeightStatus.notified();
                        RefreshComponent refreshComponent4 = this.mRefreshFooter;
                        RefreshKernel refreshKernel2 = this.mKernel;
                        int i14 = this.mFooterHeight;
                        refreshComponent4.onInitialized(refreshKernel2, i14, (int) (this.mFooterMaxDragRate * ((float) i14)));
                    }
                    if (z && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                        i9 += view2.getMeasuredHeight();
                    }
                }
                RefreshContent refreshContent = this.mRefreshContent;
                if (refreshContent != null && refreshContent.getView() == childAt) {
                    View view3 = this.mRefreshContent.getView();
                    ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : sDefaultMarginLP;
                    view3.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingLeft() + getPaddingRight() + marginLayoutParams3.leftMargin + marginLayoutParams3.rightMargin, layoutParams3.width), ViewGroup.getChildMeasureSpec(i8, getPaddingTop() + getPaddingBottom() + marginLayoutParams3.topMargin + marginLayoutParams3.bottomMargin + ((!z || !(this.mRefreshHeader != null && isEnableRefreshOrLoadMore(this.mEnableRefresh) && isEnableTranslationContent(this.mEnableHeaderTranslationContent, this.mRefreshHeader))) ? 0 : this.mHeaderHeight) + ((!z || !(this.mRefreshFooter != null && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && isEnableTranslationContent(this.mEnableFooterTranslationContent, this.mRefreshFooter))) ? 0 : this.mFooterHeight), layoutParams3.height));
                    i9 += view3.getMeasuredHeight();
                }
            }
        }
        super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), i7), View.resolveSize(i9, i8));
        this.mLastTouchX = ((float) getMeasuredWidth()) / 2.0f;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        int childCount = super.getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = super.getChildAt(i6);
            if (childAt.getVisibility() != 8 && !"GONE".equals(childAt.getTag(C2564R.C2565id.srl_tag))) {
                RefreshContent refreshContent = this.mRefreshContent;
                boolean z2 = true;
                if (refreshContent != null && refreshContent.getView() == childAt) {
                    boolean z3 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh) && this.mRefreshHeader != null;
                    View view = this.mRefreshContent.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    int i7 = marginLayoutParams.leftMargin + paddingLeft;
                    int i8 = marginLayoutParams.topMargin + paddingTop;
                    int measuredWidth = view.getMeasuredWidth() + i7;
                    int measuredHeight = view.getMeasuredHeight() + i8;
                    if (z3 && isEnableTranslationContent(this.mEnableHeaderTranslationContent, this.mRefreshHeader)) {
                        int i9 = this.mHeaderHeight;
                        i8 += i9;
                        measuredHeight += i9;
                    }
                    view.layout(i7, i8, measuredWidth, measuredHeight);
                }
                RefreshComponent refreshComponent = this.mRefreshHeader;
                if (refreshComponent != null && refreshComponent.getView() == childAt) {
                    boolean z4 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh);
                    View view2 = this.mRefreshHeader.getView();
                    ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : sDefaultMarginLP;
                    int i10 = marginLayoutParams2.leftMargin;
                    int i11 = marginLayoutParams2.topMargin + this.mHeaderInsetStart;
                    int measuredWidth2 = view2.getMeasuredWidth() + i10;
                    int measuredHeight2 = view2.getMeasuredHeight() + i11;
                    if (!z4 && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                        int i12 = this.mHeaderHeight;
                        i11 -= i12;
                        measuredHeight2 -= i12;
                    }
                    view2.layout(i10, i11, measuredWidth2, measuredHeight2);
                }
                RefreshComponent refreshComponent2 = this.mRefreshFooter;
                if (refreshComponent2 != null && refreshComponent2.getView() == childAt) {
                    if (!isInEditMode() || !this.mEnablePreviewInEditMode || !isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                        z2 = false;
                    }
                    View view3 = this.mRefreshFooter.getView();
                    ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : sDefaultMarginLP;
                    SpinnerStyle spinnerStyle = this.mRefreshFooter.getSpinnerStyle();
                    int i13 = marginLayoutParams3.leftMargin;
                    int measuredHeight3 = (marginLayoutParams3.topMargin + getMeasuredHeight()) - this.mFooterInsetStart;
                    if (this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && this.mEnableFooterFollowWhenNoMoreData && this.mRefreshContent != null && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                        View view4 = this.mRefreshContent.getView();
                        ViewGroup.LayoutParams layoutParams4 = view4.getLayoutParams();
                        measuredHeight3 = view4.getMeasuredHeight() + paddingTop + paddingTop + (layoutParams4 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams4).topMargin : 0);
                    }
                    if (spinnerStyle == SpinnerStyle.MatchLayout) {
                        measuredHeight3 = marginLayoutParams3.topMargin - this.mFooterInsetStart;
                    } else {
                        if (z2 || spinnerStyle == SpinnerStyle.FixedFront || spinnerStyle == SpinnerStyle.FixedBehind) {
                            i5 = this.mFooterHeight;
                        } else if (spinnerStyle.scale && this.mSpinner < 0) {
                            i5 = Math.max(isEnableRefreshOrLoadMore(this.mEnableLoadMore) ? -this.mSpinner : 0, 0);
                        }
                        measuredHeight3 -= i5;
                    }
                    view3.layout(i13, measuredHeight3, view3.getMeasuredWidth() + i13, view3.getMeasuredHeight() + measuredHeight3);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mManualLoadMore = true;
        this.animationRunnable = null;
        ValueAnimator valueAnimator = this.reboundAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.reboundAnimator.removeAllUpdateListeners();
            this.reboundAnimator.setDuration(0);
            this.reboundAnimator.cancel();
            this.reboundAnimator = null;
        }
        if (this.mRefreshHeader != null && this.mState == RefreshState.Refreshing) {
            this.mRefreshHeader.onFinish(this, false);
        }
        if (this.mRefreshFooter != null && this.mState == RefreshState.Loading) {
            this.mRefreshFooter.onFinish(this, false);
        }
        if (this.mSpinner != 0) {
            this.mKernel.moveSpinner(0, true);
        }
        if (this.mState != RefreshState.None) {
            notifyStateChanged(RefreshState.None);
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        this.mFooterLocked = false;
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        Paint paint;
        Paint paint2;
        RefreshContent refreshContent = this.mRefreshContent;
        View view2 = refreshContent != null ? refreshContent.getView() : null;
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent != null && refreshComponent.getView() == view) {
            if (!isEnableRefreshOrLoadMore(this.mEnableRefresh) || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (view2 != null) {
                int max = Math.max(view2.getTop() + view2.getPaddingTop() + this.mSpinner, view.getTop());
                int i = this.mHeaderBackgroundColor;
                if (!(i == 0 || (paint2 = this.mPaint) == null)) {
                    paint2.setColor(i);
                    if (this.mRefreshHeader.getSpinnerStyle().scale) {
                        max = view.getBottom();
                    } else if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                        max = view.getBottom() + this.mSpinner;
                    }
                    canvas.drawRect(0.0f, (float) view.getTop(), (float) getWidth(), (float) max, this.mPaint);
                }
                if ((this.mEnableClipHeaderWhenFixedBehind && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) || this.mRefreshHeader.getSpinnerStyle().scale) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), max);
                    boolean drawChild = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return drawChild;
                }
            }
        }
        RefreshComponent refreshComponent2 = this.mRefreshFooter;
        if (refreshComponent2 != null && refreshComponent2.getView() == view) {
            if (!isEnableRefreshOrLoadMore(this.mEnableLoadMore) || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (view2 != null) {
                int min = Math.min((view2.getBottom() - view2.getPaddingBottom()) + this.mSpinner, view.getBottom());
                int i2 = this.mFooterBackgroundColor;
                if (!(i2 == 0 || (paint = this.mPaint) == null)) {
                    paint.setColor(i2);
                    if (this.mRefreshFooter.getSpinnerStyle().scale) {
                        min = view.getTop();
                    } else if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate) {
                        min = view.getTop() + this.mSpinner;
                    }
                    canvas.drawRect(0.0f, (float) min, (float) getWidth(), (float) view.getBottom(), this.mPaint);
                }
                if ((this.mEnableClipFooterWhenFixedBehind && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) || this.mRefreshFooter.getSpinnerStyle().scale) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), min, view.getRight(), view.getBottom());
                    boolean drawChild2 = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return drawChild2;
                }
            }
        }
        return super.drawChild(canvas, view, j);
    }

    public void computeScroll() {
        this.mScroller.getCurrY();
        if (this.mScroller.computeScrollOffset()) {
            int finalY = this.mScroller.getFinalY();
            if ((finalY >= 0 || ((!this.mEnableRefresh && !this.mEnableOverScrollDrag) || !this.mRefreshContent.canRefresh())) && (finalY <= 0 || ((!this.mEnableLoadMore && !this.mEnableOverScrollDrag) || !this.mRefreshContent.canLoadMore()))) {
                this.mVerticalPermit = true;
                invalidate();
                return;
            }
            if (this.mVerticalPermit) {
                animSpinnerBounce(finalY > 0 ? -this.mScroller.getCurrVelocity() : this.mScroller.getCurrVelocity());
            }
            this.mScroller.forceFinished(true);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0114, code lost:
        if (r6 != 3) goto L_0x0327;
     */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x02bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r23) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            int r6 = r23.getActionMasked()
            r2 = 6
            r10 = 0
            r11 = 1
            if (r6 != r2) goto L_0x000f
            r3 = 1
            goto L_0x0010
        L_0x000f:
            r3 = 0
        L_0x0010:
            if (r3 == 0) goto L_0x0017
            int r4 = r23.getActionIndex()
            goto L_0x0018
        L_0x0017:
            r4 = -1
        L_0x0018:
            int r5 = r23.getPointerCount()
            r7 = 0
            r8 = 0
            r9 = 0
            r12 = 0
        L_0x0020:
            if (r8 >= r5) goto L_0x0032
            if (r4 != r8) goto L_0x0025
            goto L_0x002f
        L_0x0025:
            float r13 = r1.getX(r8)
            float r9 = r9 + r13
            float r13 = r1.getY(r8)
            float r12 = r12 + r13
        L_0x002f:
            int r8 = r8 + 1
            goto L_0x0020
        L_0x0032:
            if (r3 == 0) goto L_0x0036
            int r5 = r5 + -1
        L_0x0036:
            float r3 = (float) r5
            float r9 = r9 / r3
            float r8 = r12 / r3
            if (r6 == r2) goto L_0x003f
            r2 = 5
            if (r6 != r2) goto L_0x004c
        L_0x003f:
            boolean r2 = r0.mIsBeingDragged
            if (r2 == 0) goto L_0x004c
            float r2 = r0.mTouchY
            float r3 = r0.mLastTouchY
            float r3 = r8 - r3
            float r2 = r2 + r3
            r0.mTouchY = r2
        L_0x004c:
            r0.mLastTouchX = r9
            r0.mLastTouchY = r8
            boolean r2 = r0.mNestedInProgress
            r3 = 2
            if (r2 == 0) goto L_0x00a8
            int r2 = r0.mTotalUnconsumed
            boolean r1 = super.dispatchTouchEvent(r23)
            if (r6 != r3) goto L_0x00a7
            int r3 = r0.mTotalUnconsumed
            if (r2 != r3) goto L_0x00a7
            float r2 = r0.mLastTouchX
            int r2 = (int) r2
            int r3 = r22.getWidth()
            float r4 = r0.mLastTouchX
            if (r3 != 0) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r11 = r3
        L_0x006e:
            float r5 = (float) r11
            float r4 = r4 / r5
            boolean r5 = r0.mEnableRefresh
            boolean r5 = r0.isEnableRefreshOrLoadMore(r5)
            if (r5 == 0) goto L_0x008c
            int r5 = r0.mSpinner
            if (r5 <= 0) goto L_0x008c
            com.scwang.smart.refresh.layout.api.RefreshComponent r5 = r0.mRefreshHeader
            if (r5 == 0) goto L_0x008c
            boolean r5 = r5.isSupportHorizontalDrag()
            if (r5 == 0) goto L_0x008c
            com.scwang.smart.refresh.layout.api.RefreshComponent r5 = r0.mRefreshHeader
            r5.onHorizontalDrag(r4, r2, r3)
            goto L_0x00a7
        L_0x008c:
            boolean r5 = r0.mEnableLoadMore
            boolean r5 = r0.isEnableRefreshOrLoadMore(r5)
            if (r5 == 0) goto L_0x00a7
            int r5 = r0.mSpinner
            if (r5 >= 0) goto L_0x00a7
            com.scwang.smart.refresh.layout.api.RefreshComponent r5 = r0.mRefreshFooter
            if (r5 == 0) goto L_0x00a7
            boolean r5 = r5.isSupportHorizontalDrag()
            if (r5 == 0) goto L_0x00a7
            com.scwang.smart.refresh.layout.api.RefreshComponent r5 = r0.mRefreshFooter
            r5.onHorizontalDrag(r4, r2, r3)
        L_0x00a7:
            return r1
        L_0x00a8:
            boolean r2 = r22.isEnabled()
            if (r2 == 0) goto L_0x0372
            boolean r2 = r0.mEnableRefresh
            if (r2 != 0) goto L_0x00ba
            boolean r2 = r0.mEnableLoadMore
            if (r2 != 0) goto L_0x00ba
            boolean r2 = r0.mEnableOverScrollDrag
            if (r2 == 0) goto L_0x0372
        L_0x00ba:
            boolean r2 = r0.mHeaderNeedTouchEventWhenRefreshing
            if (r2 == 0) goto L_0x00d0
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            boolean r2 = r2.isOpening
            if (r2 != 0) goto L_0x00ca
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            boolean r2 = r2.isFinishing
            if (r2 == 0) goto L_0x00d0
        L_0x00ca:
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            boolean r2 = r2.isHeader
            if (r2 != 0) goto L_0x0372
        L_0x00d0:
            boolean r2 = r0.mFooterNeedTouchEventWhenLoading
            if (r2 == 0) goto L_0x00e8
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            boolean r2 = r2.isOpening
            if (r2 != 0) goto L_0x00e0
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            boolean r2 = r2.isFinishing
            if (r2 == 0) goto L_0x00e8
        L_0x00e0:
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            boolean r2 = r2.isFooter
            if (r2 == 0) goto L_0x00e8
            goto L_0x0372
        L_0x00e8:
            boolean r2 = r0.interceptAnimatorByAction(r6)
            if (r2 != 0) goto L_0x0371
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            boolean r2 = r2.isFinishing
            if (r2 != 0) goto L_0x0371
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            com.scwang.smart.refresh.layout.constant.RefreshState r4 = com.scwang.smart.refresh.layout.constant.RefreshState.Loading
            if (r2 != r4) goto L_0x00fe
            boolean r2 = r0.mDisableContentWhenLoading
            if (r2 != 0) goto L_0x0371
        L_0x00fe:
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            com.scwang.smart.refresh.layout.constant.RefreshState r4 = com.scwang.smart.refresh.layout.constant.RefreshState.Refreshing
            if (r2 != r4) goto L_0x010a
            boolean r2 = r0.mDisableContentWhenRefresh
            if (r2 == 0) goto L_0x010a
            goto L_0x0371
        L_0x010a:
            r2 = 104(0x68, float:1.46E-43)
            if (r6 == 0) goto L_0x032c
            r4 = 0
            if (r6 == r11) goto L_0x02de
            r5 = 3
            if (r6 == r3) goto L_0x0118
            if (r6 == r5) goto L_0x02f9
            goto L_0x0327
        L_0x0118:
            float r3 = r0.mTouchX
            float r9 = r9 - r3
            float r3 = r0.mTouchY
            float r3 = r8 - r3
            android.view.VelocityTracker r6 = r0.mVelocityTracker
            r6.addMovement(r1)
            boolean r6 = r0.mIsBeingDragged
            if (r6 != 0) goto L_0x01f1
            boolean r6 = r0.mEnableDisallowIntercept
            if (r6 != 0) goto L_0x01f1
            char r6 = r0.mDragDirection
            if (r6 == r2) goto L_0x01f1
            com.scwang.smart.refresh.layout.api.RefreshContent r12 = r0.mRefreshContent
            if (r12 == 0) goto L_0x01f1
            r12 = 118(0x76, float:1.65E-43)
            if (r6 == r12) goto L_0x016f
            float r6 = java.lang.Math.abs(r3)
            int r13 = r0.mTouchSlop
            float r13 = (float) r13
            int r6 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r6 < 0) goto L_0x0150
            float r6 = java.lang.Math.abs(r9)
            float r13 = java.lang.Math.abs(r3)
            int r6 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r6 >= 0) goto L_0x0150
            goto L_0x016f
        L_0x0150:
            float r6 = java.lang.Math.abs(r9)
            int r13 = r0.mTouchSlop
            float r13 = (float) r13
            int r6 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r6 < 0) goto L_0x01f1
            float r6 = java.lang.Math.abs(r9)
            float r13 = java.lang.Math.abs(r3)
            int r6 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r6 <= 0) goto L_0x01f1
            char r6 = r0.mDragDirection
            if (r6 == r12) goto L_0x01f1
            r0.mDragDirection = r2
            goto L_0x01f1
        L_0x016f:
            r0.mDragDirection = r12
            int r2 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r2 <= 0) goto L_0x0193
            int r2 = r0.mSpinner
            if (r2 < 0) goto L_0x0189
            boolean r2 = r0.mEnableOverScrollDrag
            if (r2 != 0) goto L_0x0181
            boolean r2 = r0.mEnableRefresh
            if (r2 == 0) goto L_0x0193
        L_0x0181:
            com.scwang.smart.refresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            boolean r2 = r2.canRefresh()
            if (r2 == 0) goto L_0x0193
        L_0x0189:
            r0.mIsBeingDragged = r11
            int r2 = r0.mTouchSlop
            float r2 = (float) r2
            float r2 = r8 - r2
            r0.mTouchY = r2
            goto L_0x01bd
        L_0x0193:
            int r2 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r2 >= 0) goto L_0x01bd
            int r2 = r0.mSpinner
            if (r2 > 0) goto L_0x01b5
            boolean r2 = r0.mEnableOverScrollDrag
            if (r2 != 0) goto L_0x01a3
            boolean r2 = r0.mEnableLoadMore
            if (r2 == 0) goto L_0x01bd
        L_0x01a3:
            com.scwang.smart.refresh.layout.constant.RefreshState r2 = r0.mState
            com.scwang.smart.refresh.layout.constant.RefreshState r6 = com.scwang.smart.refresh.layout.constant.RefreshState.Loading
            if (r2 != r6) goto L_0x01ad
            boolean r2 = r0.mFooterLocked
            if (r2 != 0) goto L_0x01b5
        L_0x01ad:
            com.scwang.smart.refresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            boolean r2 = r2.canLoadMore()
            if (r2 == 0) goto L_0x01bd
        L_0x01b5:
            r0.mIsBeingDragged = r11
            int r2 = r0.mTouchSlop
            float r2 = (float) r2
            float r2 = r2 + r8
            r0.mTouchY = r2
        L_0x01bd:
            boolean r2 = r0.mIsBeingDragged
            if (r2 == 0) goto L_0x01f1
            float r2 = r0.mTouchY
            float r3 = r8 - r2
            boolean r2 = r0.mSuperDispatchTouchEvent
            if (r2 == 0) goto L_0x01cf
            r1.setAction(r5)
            super.dispatchTouchEvent(r23)
        L_0x01cf:
            com.scwang.smart.refresh.layout.api.RefreshKernel r2 = r0.mKernel
            int r6 = r0.mSpinner
            if (r6 > 0) goto L_0x01df
            if (r6 != 0) goto L_0x01dc
            int r6 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r6 <= 0) goto L_0x01dc
            goto L_0x01df
        L_0x01dc:
            com.scwang.smart.refresh.layout.constant.RefreshState r6 = com.scwang.smart.refresh.layout.constant.RefreshState.PullUpToLoad
            goto L_0x01e1
        L_0x01df:
            com.scwang.smart.refresh.layout.constant.RefreshState r6 = com.scwang.smart.refresh.layout.constant.RefreshState.PullDownToRefresh
        L_0x01e1:
            r2.setState(r6)
            android.view.ViewParent r2 = r22.getParent()
            boolean r6 = r2 instanceof android.view.ViewGroup
            if (r6 == 0) goto L_0x01f1
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r2.requestDisallowInterceptTouchEvent(r11)
        L_0x01f1:
            boolean r2 = r0.mIsBeingDragged
            if (r2 == 0) goto L_0x02cc
            int r2 = (int) r3
            int r6 = r0.mTouchSpinner
            int r2 = r2 + r6
            com.scwang.smart.refresh.layout.constant.RefreshState r6 = r0.mViceState
            boolean r6 = r6.isHeader
            if (r6 == 0) goto L_0x0205
            if (r2 < 0) goto L_0x0211
            int r6 = r0.mLastSpinner
            if (r6 < 0) goto L_0x0211
        L_0x0205:
            com.scwang.smart.refresh.layout.constant.RefreshState r6 = r0.mViceState
            boolean r6 = r6.isFooter
            if (r6 == 0) goto L_0x02c7
            if (r2 > 0) goto L_0x0211
            int r6 = r0.mLastSpinner
            if (r6 <= 0) goto L_0x02c7
        L_0x0211:
            r0.mLastSpinner = r2
            long r20 = r23.getEventTime()
            android.view.MotionEvent r1 = r0.mFalsifyEvent
            if (r1 != 0) goto L_0x0236
            r16 = 0
            float r1 = r0.mTouchX
            float r17 = r1 + r9
            float r1 = r0.mTouchY
            r19 = 0
            r12 = r20
            r14 = r20
            r18 = r1
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r12, r14, r16, r17, r18, r19)
            r0.mFalsifyEvent = r1
            android.view.MotionEvent r1 = r0.mFalsifyEvent
            super.dispatchTouchEvent(r1)
        L_0x0236:
            r16 = 2
            float r1 = r0.mTouchX
            float r17 = r1 + r9
            float r1 = r0.mTouchY
            float r6 = (float) r2
            float r18 = r1 + r6
            r19 = 0
            r12 = r20
            r14 = r20
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r12, r14, r16, r17, r18, r19)
            super.dispatchTouchEvent(r1)
            boolean r6 = r0.mFooterLocked
            if (r6 == 0) goto L_0x025f
            int r6 = r0.mTouchSlop
            float r6 = (float) r6
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x025f
            int r3 = r0.mSpinner
            if (r3 >= 0) goto L_0x025f
            r0.mFooterLocked = r10
        L_0x025f:
            if (r2 <= 0) goto L_0x0280
            boolean r3 = r0.mEnableOverScrollDrag
            if (r3 != 0) goto L_0x0269
            boolean r3 = r0.mEnableRefresh
            if (r3 == 0) goto L_0x0280
        L_0x0269:
            com.scwang.smart.refresh.layout.api.RefreshContent r3 = r0.mRefreshContent
            boolean r3 = r3.canRefresh()
            if (r3 == 0) goto L_0x0280
            r0.mLastTouchY = r8
            r0.mTouchY = r8
            r0.mTouchSpinner = r10
            com.scwang.smart.refresh.layout.api.RefreshKernel r2 = r0.mKernel
            com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.PullDownToRefresh
            r2.setState(r3)
        L_0x027e:
            r2 = 0
            goto L_0x02a0
        L_0x0280:
            if (r2 >= 0) goto L_0x02a0
            boolean r3 = r0.mEnableOverScrollDrag
            if (r3 != 0) goto L_0x028a
            boolean r3 = r0.mEnableLoadMore
            if (r3 == 0) goto L_0x02a0
        L_0x028a:
            com.scwang.smart.refresh.layout.api.RefreshContent r3 = r0.mRefreshContent
            boolean r3 = r3.canLoadMore()
            if (r3 == 0) goto L_0x02a0
            r0.mLastTouchY = r8
            r0.mTouchY = r8
            r0.mTouchSpinner = r10
            com.scwang.smart.refresh.layout.api.RefreshKernel r2 = r0.mKernel
            com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.PullUpToLoad
            r2.setState(r3)
            goto L_0x027e
        L_0x02a0:
            com.scwang.smart.refresh.layout.constant.RefreshState r3 = r0.mViceState
            boolean r3 = r3.isHeader
            if (r3 == 0) goto L_0x02a8
            if (r2 < 0) goto L_0x02b0
        L_0x02a8:
            com.scwang.smart.refresh.layout.constant.RefreshState r3 = r0.mViceState
            boolean r3 = r3.isFooter
            if (r3 == 0) goto L_0x02b8
            if (r2 <= 0) goto L_0x02b8
        L_0x02b0:
            int r1 = r0.mSpinner
            if (r1 == 0) goto L_0x02b7
            r0.moveSpinnerInfinitely(r7)
        L_0x02b7:
            return r11
        L_0x02b8:
            android.view.MotionEvent r3 = r0.mFalsifyEvent
            if (r3 == 0) goto L_0x02c4
            r0.mFalsifyEvent = r4
            r1.setAction(r5)
            super.dispatchTouchEvent(r1)
        L_0x02c4:
            r1.recycle()
        L_0x02c7:
            float r1 = (float) r2
            r0.moveSpinnerInfinitely(r1)
            return r11
        L_0x02cc:
            boolean r2 = r0.mFooterLocked
            if (r2 == 0) goto L_0x0327
            int r2 = r0.mTouchSlop
            float r2 = (float) r2
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x0327
            int r2 = r0.mSpinner
            if (r2 >= 0) goto L_0x0327
            r0.mFooterLocked = r10
            goto L_0x0327
        L_0x02de:
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r2.addMovement(r1)
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r3 = 1000(0x3e8, float:1.401E-42)
            int r5 = r0.mMaximumVelocity
            float r5 = (float) r5
            r2.computeCurrentVelocity(r3, r5)
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            float r2 = r2.getYVelocity()
            int r2 = (int) r2
            r0.mCurrentVelocity = r2
            r0.startFlingIfNeed(r7)
        L_0x02f9:
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r2.clear()
            r2 = 110(0x6e, float:1.54E-43)
            r0.mDragDirection = r2
            android.view.MotionEvent r2 = r0.mFalsifyEvent
            if (r2 == 0) goto L_0x031d
            r2.recycle()
            r0.mFalsifyEvent = r4
            long r4 = r23.getEventTime()
            float r7 = r0.mTouchX
            r9 = 0
            r2 = r4
            android.view.MotionEvent r2 = android.view.MotionEvent.obtain(r2, r4, r6, r7, r8, r9)
            super.dispatchTouchEvent(r2)
            r2.recycle()
        L_0x031d:
            r22.overSpinner()
            boolean r2 = r0.mIsBeingDragged
            if (r2 == 0) goto L_0x0327
            r0.mIsBeingDragged = r10
            return r11
        L_0x0327:
            boolean r1 = super.dispatchTouchEvent(r23)
            return r1
        L_0x032c:
            r0.mCurrentVelocity = r10
            android.view.VelocityTracker r3 = r0.mVelocityTracker
            r3.addMovement(r1)
            android.widget.Scroller r3 = r0.mScroller
            r3.forceFinished(r11)
            r0.mTouchX = r9
            r0.mTouchY = r8
            r0.mLastSpinner = r10
            int r3 = r0.mSpinner
            r0.mTouchSpinner = r3
            r0.mIsBeingDragged = r10
            r0.mEnableDisallowIntercept = r10
            boolean r3 = super.dispatchTouchEvent(r23)
            r0.mSuperDispatchTouchEvent = r3
            com.scwang.smart.refresh.layout.constant.RefreshState r3 = r0.mState
            com.scwang.smart.refresh.layout.constant.RefreshState r4 = com.scwang.smart.refresh.layout.constant.RefreshState.TwoLevel
            if (r3 != r4) goto L_0x0369
            float r3 = r0.mTouchY
            int r4 = r22.getMeasuredHeight()
            float r4 = (float) r4
            r5 = 1065353216(0x3f800000, float:1.0)
            float r6 = r0.mTwoLevelBottomPullUpToCloseRate
            float r5 = r5 - r6
            float r4 = r4 * r5
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 >= 0) goto L_0x0369
            r0.mDragDirection = r2
            boolean r1 = r0.mSuperDispatchTouchEvent
            return r1
        L_0x0369:
            com.scwang.smart.refresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            if (r2 == 0) goto L_0x0370
            r2.onActionDown(r1)
        L_0x0370:
            return r11
        L_0x0371:
            return r10
        L_0x0372:
            boolean r1 = super.dispatchTouchEvent(r23)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smart.refresh.layout.SmartRefreshLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        View scrollableView = this.mRefreshContent.getScrollableView();
        if ((Build.VERSION.SDK_INT >= 21 || !(scrollableView instanceof AbsListView)) && ViewCompat.isNestedScrollingEnabled(scrollableView)) {
            this.mEnableDisallowIntercept = z;
            super.requestDisallowInterceptTouchEvent(z);
        }
    }

    /* access modifiers changed from: protected */
    public boolean startFlingIfNeed(float f) {
        if (f == 0.0f) {
            f = (float) this.mCurrentVelocity;
        }
        if (Build.VERSION.SDK_INT > 27 && this.mRefreshContent != null) {
            getScaleY();
            View view = this.mRefreshContent.getView();
            if (getScaleY() == -1.0f && view.getScaleY() == -1.0f) {
                f = -f;
            }
        }
        if (Math.abs(f) > ((float) this.mMinimumVelocity)) {
            if (((float) this.mSpinner) * f < 0.0f) {
                if (this.mState == RefreshState.Refreshing || this.mState == RefreshState.Loading || (this.mSpinner < 0 && this.mFooterNoMoreData)) {
                    this.animationRunnable = new FlingRunnable(f).start();
                    return true;
                } else if (this.mState.isReleaseToOpening) {
                    return true;
                }
            }
            if ((f < 0.0f && ((this.mEnableOverScrollBounce && (this.mEnableLoadMore || this.mEnableOverScrollDrag)) || ((this.mState == RefreshState.Loading && this.mSpinner >= 0) || (this.mEnableAutoLoadMore && isEnableRefreshOrLoadMore(this.mEnableLoadMore))))) || (f > 0.0f && ((this.mEnableOverScrollBounce && this.mEnableRefresh) || this.mEnableOverScrollDrag || (this.mState == RefreshState.Refreshing && this.mSpinner <= 0)))) {
                this.mVerticalPermit = false;
                this.mScroller.fling(0, 0, 0, (int) (-f), 0, 0, -2147483647, Integer.MAX_VALUE);
                this.mScroller.computeScrollOffset();
                invalidate();
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean interceptAnimatorByAction(int i) {
        if (i == 0) {
            if (this.reboundAnimator != null) {
                if (this.mState.isFinishing || this.mState == RefreshState.TwoLevelReleased || this.mState == RefreshState.RefreshReleased || this.mState == RefreshState.LoadReleased) {
                    return true;
                }
                if (this.mState == RefreshState.PullDownCanceled) {
                    this.mKernel.setState(RefreshState.PullDownToRefresh);
                } else if (this.mState == RefreshState.PullUpCanceled) {
                    this.mKernel.setState(RefreshState.PullUpToLoad);
                }
                this.reboundAnimator.setDuration(0);
                this.reboundAnimator.cancel();
                this.reboundAnimator = null;
            }
            this.animationRunnable = null;
        }
        if (this.reboundAnimator != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void notifyStateChanged(RefreshState refreshState) {
        RefreshState refreshState2 = this.mState;
        if (refreshState2 != refreshState) {
            this.mState = refreshState;
            this.mViceState = refreshState;
            RefreshComponent refreshComponent = this.mRefreshHeader;
            RefreshComponent refreshComponent2 = this.mRefreshFooter;
            OnMultiListener onMultiListener = this.mOnMultiListener;
            if (refreshComponent != null) {
                refreshComponent.onStateChanged(this, refreshState2, refreshState);
            }
            if (refreshComponent2 != null) {
                refreshComponent2.onStateChanged(this, refreshState2, refreshState);
            }
            if (onMultiListener != null) {
                onMultiListener.onStateChanged(this, refreshState2, refreshState);
            }
            if (refreshState == RefreshState.LoadFinish) {
                this.mFooterLocked = false;
            }
        } else if (this.mViceState != refreshState2) {
            this.mViceState = refreshState2;
        }
    }

    /* access modifiers changed from: protected */
    public void setStateDirectLoading(boolean z) {
        if (this.mState != RefreshState.Loading) {
            this.mLastOpenTime = System.currentTimeMillis();
            this.mFooterLocked = true;
            notifyStateChanged(RefreshState.Loading);
            OnLoadMoreListener onLoadMoreListener = this.mLoadMoreListener;
            if (onLoadMoreListener != null) {
                if (z) {
                    onLoadMoreListener.onLoadMore(this);
                }
            } else if (this.mOnMultiListener == null) {
                finishLoadMore(2000);
            }
            RefreshComponent refreshComponent = this.mRefreshFooter;
            if (refreshComponent != null) {
                int i = this.mFooterHeight;
                refreshComponent.onStartAnimator(this, i, (int) (this.mFooterMaxDragRate * ((float) i)));
            }
            OnMultiListener onMultiListener = this.mOnMultiListener;
            if (onMultiListener != null && (this.mRefreshFooter instanceof RefreshFooter)) {
                if (z) {
                    onMultiListener.onLoadMore(this);
                }
                int i2 = this.mFooterHeight;
                this.mOnMultiListener.onFooterStartAnimator((RefreshFooter) this.mRefreshFooter, i2, (int) (this.mFooterMaxDragRate * ((float) i2)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setStateLoading(final boolean z) {
        C25471 r0 = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (animator == null || animator.getDuration() != 0) {
                    SmartRefreshLayout.this.setStateDirectLoading(z);
                }
            }
        };
        notifyStateChanged(RefreshState.LoadReleased);
        ValueAnimator animSpinner = this.mKernel.animSpinner(-this.mFooterHeight);
        if (animSpinner != null) {
            animSpinner.addListener(r0);
        }
        RefreshComponent refreshComponent = this.mRefreshFooter;
        if (refreshComponent != null) {
            int i = this.mFooterHeight;
            refreshComponent.onReleased(this, i, (int) (this.mFooterMaxDragRate * ((float) i)));
        }
        OnMultiListener onMultiListener = this.mOnMultiListener;
        if (onMultiListener != null) {
            RefreshComponent refreshComponent2 = this.mRefreshFooter;
            if (refreshComponent2 instanceof RefreshFooter) {
                int i2 = this.mFooterHeight;
                onMultiListener.onFooterReleased((RefreshFooter) refreshComponent2, i2, (int) (this.mFooterMaxDragRate * ((float) i2)));
            }
        }
        if (animSpinner == null) {
            r0.onAnimationEnd((Animator) null);
        }
    }

    /* access modifiers changed from: protected */
    public void setStateRefreshing(final boolean z) {
        C25492 r0 = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (animator == null || animator.getDuration() != 0) {
                    SmartRefreshLayout.this.mLastOpenTime = System.currentTimeMillis();
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.Refreshing);
                    if (SmartRefreshLayout.this.mRefreshListener != null) {
                        if (z) {
                            SmartRefreshLayout.this.mRefreshListener.onRefresh(SmartRefreshLayout.this);
                        }
                    } else if (SmartRefreshLayout.this.mOnMultiListener == null) {
                        SmartRefreshLayout.this.finishRefresh(3000);
                    }
                    if (SmartRefreshLayout.this.mRefreshHeader != null) {
                        RefreshComponent refreshComponent = SmartRefreshLayout.this.mRefreshHeader;
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        refreshComponent.onStartAnimator(smartRefreshLayout, smartRefreshLayout.mHeaderHeight, (int) (SmartRefreshLayout.this.mHeaderMaxDragRate * ((float) SmartRefreshLayout.this.mHeaderHeight)));
                    }
                    if (SmartRefreshLayout.this.mOnMultiListener != null && (SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                        if (z) {
                            SmartRefreshLayout.this.mOnMultiListener.onRefresh(SmartRefreshLayout.this);
                        }
                        SmartRefreshLayout.this.mOnMultiListener.onHeaderStartAnimator((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, SmartRefreshLayout.this.mHeaderHeight, (int) (SmartRefreshLayout.this.mHeaderMaxDragRate * ((float) SmartRefreshLayout.this.mHeaderHeight)));
                    }
                }
            }
        };
        notifyStateChanged(RefreshState.RefreshReleased);
        ValueAnimator animSpinner = this.mKernel.animSpinner(this.mHeaderHeight);
        if (animSpinner != null) {
            animSpinner.addListener(r0);
        }
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent != null) {
            int i = this.mHeaderHeight;
            refreshComponent.onReleased(this, i, (int) (this.mHeaderMaxDragRate * ((float) i)));
        }
        OnMultiListener onMultiListener = this.mOnMultiListener;
        if (onMultiListener != null) {
            RefreshComponent refreshComponent2 = this.mRefreshHeader;
            if (refreshComponent2 instanceof RefreshHeader) {
                int i2 = this.mHeaderHeight;
                onMultiListener.onHeaderReleased((RefreshHeader) refreshComponent2, i2, (int) (this.mHeaderMaxDragRate * ((float) i2)));
            }
        }
        if (animSpinner == null) {
            r0.onAnimationEnd((Animator) null);
        }
    }

    /* access modifiers changed from: protected */
    public void setViceState(RefreshState refreshState) {
        if (this.mState.isDragging && this.mState.isHeader != refreshState.isHeader) {
            notifyStateChanged(RefreshState.None);
        }
        if (this.mViceState != refreshState) {
            this.mViceState = refreshState;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isEnableTranslationContent(boolean z, @Nullable RefreshComponent refreshComponent) {
        return z || this.mEnablePureScrollMode || refreshComponent == null || refreshComponent.getSpinnerStyle() == SpinnerStyle.FixedBehind;
    }

    /* access modifiers changed from: protected */
    public boolean isEnableRefreshOrLoadMore(boolean z) {
        return z && !this.mEnablePureScrollMode;
    }

    protected class FlingRunnable implements Runnable {
        float mDamping = 0.98f;
        int mFrame = 0;
        int mFrameDelay = 10;
        long mLastTime = AnimationUtils.currentAnimationTimeMillis();
        int mOffset;
        long mStartTime = 0;
        float mVelocity;

        FlingRunnable(float f) {
            this.mVelocity = f;
            this.mOffset = SmartRefreshLayout.this.mSpinner;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
            if (r0.isEnableRefreshOrLoadMore(r0.mEnableLoadMore) != false) goto L_0x0034;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
            if (r0.isEnableRefreshOrLoadMore(r0.mEnableLoadMore) != false) goto L_0x0058;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
            if (r10.this$0.mSpinner >= (-r10.this$0.mFooterHeight)) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0073, code lost:
            if (r10.this$0.mSpinner > r10.this$0.mHeaderHeight) goto L_0x0075;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Runnable start() {
            /*
                r10 = this;
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r0 = r0.mState
                boolean r0 = r0.isFinishing
                r1 = 0
                if (r0 == 0) goto L_0x000a
                return r1
            L_0x000a:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r0 = r0.mSpinner
                if (r0 == 0) goto L_0x00db
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r0 = r0.mState
                boolean r0 = r0.isOpening
                if (r0 != 0) goto L_0x0034
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r0 = r0.mFooterNoMoreData
                if (r0 == 0) goto L_0x0075
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r0 = r0.mEnableFooterFollowWhenNoMoreData
                if (r0 == 0) goto L_0x0075
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r0 = r0.mFooterNoMoreDataEffective
                if (r0 == 0) goto L_0x0075
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r2 = r0.mEnableLoadMore
                boolean r0 = r0.isEnableRefreshOrLoadMore(r2)
                if (r0 == 0) goto L_0x0075
            L_0x0034:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r0 = r0.mState
                com.scwang.smart.refresh.layout.constant.RefreshState r2 = com.scwang.smart.refresh.layout.constant.RefreshState.Loading
                if (r0 == r2) goto L_0x0058
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r0 = r0.mFooterNoMoreData
                if (r0 == 0) goto L_0x0063
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r0 = r0.mEnableFooterFollowWhenNoMoreData
                if (r0 == 0) goto L_0x0063
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r0 = r0.mFooterNoMoreDataEffective
                if (r0 == 0) goto L_0x0063
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r2 = r0.mEnableLoadMore
                boolean r0 = r0.isEnableRefreshOrLoadMore(r2)
                if (r0 == 0) goto L_0x0063
            L_0x0058:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r0 = r0.mSpinner
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mFooterHeight
                int r2 = -r2
                if (r0 < r2) goto L_0x0075
            L_0x0063:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r0 = r0.mState
                com.scwang.smart.refresh.layout.constant.RefreshState r2 = com.scwang.smart.refresh.layout.constant.RefreshState.Refreshing
                if (r0 != r2) goto L_0x00db
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r0 = r0.mSpinner
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mHeaderHeight
                if (r0 <= r2) goto L_0x00db
            L_0x0075:
                r0 = 0
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r3 = r3.mSpinner
                float r4 = r10.mVelocity
            L_0x0080:
                int r5 = r3 * r2
                if (r5 <= 0) goto L_0x00db
                double r4 = (double) r4
                float r6 = r10.mDamping
                double r6 = (double) r6
                int r0 = r0 + 1
                int r8 = r10.mFrameDelay
                int r8 = r8 * r0
                float r8 = (float) r8
                r9 = 1092616192(0x41200000, float:10.0)
                float r8 = r8 / r9
                double r8 = (double) r8
                double r6 = java.lang.Math.pow(r6, r8)
                java.lang.Double.isNaN(r4)
                double r4 = r4 * r6
                float r4 = (float) r4
                int r5 = r10.mFrameDelay
                float r5 = (float) r5
                r6 = 1065353216(0x3f800000, float:1.0)
                float r5 = r5 * r6
                r7 = 1148846080(0x447a0000, float:1000.0)
                float r5 = r5 / r7
                float r5 = r5 * r4
                float r7 = java.lang.Math.abs(r5)
                int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
                if (r6 >= 0) goto L_0x00d7
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r0 = r0.mState
                boolean r0 = r0.isOpening
                if (r0 == 0) goto L_0x00d6
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r0 = r0.mState
                com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.Refreshing
                if (r0 != r3) goto L_0x00c7
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r0 = r0.mHeaderHeight
                if (r2 > r0) goto L_0x00d6
            L_0x00c7:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r0 = r0.mState
                com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.Refreshing
                if (r0 == r3) goto L_0x00db
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r0 = r0.mFooterHeight
                int r0 = -r0
                if (r2 >= r0) goto L_0x00db
            L_0x00d6:
                return r1
            L_0x00d7:
                float r2 = (float) r2
                float r2 = r2 + r5
                int r2 = (int) r2
                goto L_0x0080
            L_0x00db:
                long r0 = android.view.animation.AnimationUtils.currentAnimationTimeMillis()
                r10.mStartTime = r0
                com.scwang.smart.refresh.layout.SmartRefreshLayout r0 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                android.os.Handler r0 = r0.mHandler
                int r1 = r10.mFrameDelay
                long r1 = (long) r1
                r0.postDelayed(r10, r1)
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.scwang.smart.refresh.layout.SmartRefreshLayout.FlingRunnable.start():java.lang.Runnable");
        }

        public void run() {
            if (SmartRefreshLayout.this.animationRunnable == this && !SmartRefreshLayout.this.mState.isFinishing) {
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                double d = (double) this.mVelocity;
                double pow = Math.pow((double) this.mDamping, (double) (((float) (currentAnimationTimeMillis - this.mStartTime)) / (1000.0f / ((float) this.mFrameDelay))));
                Double.isNaN(d);
                this.mVelocity = (float) (d * pow);
                float f = this.mVelocity * ((((float) (currentAnimationTimeMillis - this.mLastTime)) * 1.0f) / 1000.0f);
                if (Math.abs(f) > 1.0f) {
                    this.mLastTime = currentAnimationTimeMillis;
                    this.mOffset = (int) (((float) this.mOffset) + f);
                    if (SmartRefreshLayout.this.mSpinner * this.mOffset > 0) {
                        SmartRefreshLayout.this.mKernel.moveSpinner(this.mOffset, true);
                        SmartRefreshLayout.this.mHandler.postDelayed(this, (long) this.mFrameDelay);
                        return;
                    }
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.animationRunnable = null;
                    smartRefreshLayout.mKernel.moveSpinner(0, true);
                    SmartUtil.fling(SmartRefreshLayout.this.mRefreshContent.getScrollableView(), (int) (-this.mVelocity));
                    if (SmartRefreshLayout.this.mFooterLocked && f > 0.0f) {
                        SmartRefreshLayout.this.mFooterLocked = false;
                        return;
                    }
                    return;
                }
                SmartRefreshLayout.this.animationRunnable = null;
            }
        }
    }

    protected class BounceRunnable implements Runnable {
        int mFrame = 0;
        int mFrameDelay = 10;
        long mLastTime;
        float mOffset = 0.0f;
        int mSmoothDistance;
        float mVelocity;

        BounceRunnable(float f, int i) {
            this.mVelocity = f;
            this.mSmoothDistance = i;
            this.mLastTime = AnimationUtils.currentAnimationTimeMillis();
            SmartRefreshLayout.this.mHandler.postDelayed(this, (long) this.mFrameDelay);
            if (f > 0.0f) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
            } else {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
            }
        }

        public void run() {
            if (SmartRefreshLayout.this.animationRunnable == this && !SmartRefreshLayout.this.mState.isFinishing) {
                if (Math.abs(SmartRefreshLayout.this.mSpinner) < Math.abs(this.mSmoothDistance)) {
                    double d = (double) this.mVelocity;
                    int i = this.mFrame + 1;
                    this.mFrame = i;
                    double pow = Math.pow(0.949999988079071d, (double) (i * 2));
                    Double.isNaN(d);
                    this.mVelocity = (float) (d * pow);
                } else if (this.mSmoothDistance != 0) {
                    double d2 = (double) this.mVelocity;
                    int i2 = this.mFrame + 1;
                    this.mFrame = i2;
                    double pow2 = Math.pow(0.44999998807907104d, (double) (i2 * 2));
                    Double.isNaN(d2);
                    this.mVelocity = (float) (d2 * pow2);
                } else {
                    double d3 = (double) this.mVelocity;
                    int i3 = this.mFrame + 1;
                    this.mFrame = i3;
                    double pow3 = Math.pow(0.8500000238418579d, (double) (i3 * 2));
                    Double.isNaN(d3);
                    this.mVelocity = (float) (d3 * pow3);
                }
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float f = this.mVelocity * ((((float) (currentAnimationTimeMillis - this.mLastTime)) * 1.0f) / 1000.0f);
                if (Math.abs(f) >= 1.0f) {
                    this.mLastTime = currentAnimationTimeMillis;
                    this.mOffset += f;
                    SmartRefreshLayout.this.moveSpinnerInfinitely(this.mOffset);
                    SmartRefreshLayout.this.mHandler.postDelayed(this, (long) this.mFrameDelay);
                    return;
                }
                if (SmartRefreshLayout.this.mViceState.isDragging && SmartRefreshLayout.this.mViceState.isHeader) {
                    SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownCanceled);
                } else if (SmartRefreshLayout.this.mViceState.isDragging && SmartRefreshLayout.this.mViceState.isFooter) {
                    SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpCanceled);
                }
                SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                smartRefreshLayout.animationRunnable = null;
                if (Math.abs(smartRefreshLayout.mSpinner) >= Math.abs(this.mSmoothDistance)) {
                    SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                    smartRefreshLayout2.animSpinner(this.mSmoothDistance, 0, smartRefreshLayout2.mReboundInterpolator, Math.min(Math.max((int) SmartUtil.px2dp(Math.abs(SmartRefreshLayout.this.mSpinner - this.mSmoothDistance)), 30), 100) * 10);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public ValueAnimator animSpinner(int i, int i2, Interpolator interpolator, int i3) {
        if (this.mSpinner == i) {
            return null;
        }
        ValueAnimator valueAnimator = this.reboundAnimator;
        if (valueAnimator != null) {
            valueAnimator.setDuration(0);
            this.reboundAnimator.cancel();
            this.reboundAnimator = null;
        }
        this.animationRunnable = null;
        this.reboundAnimator = ValueAnimator.ofInt(new int[]{this.mSpinner, i});
        this.reboundAnimator.setDuration((long) i3);
        this.reboundAnimator.setInterpolator(interpolator);
        this.reboundAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (animator == null || animator.getDuration() != 0) {
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.reboundAnimator = null;
                    if (smartRefreshLayout.mSpinner == 0 && SmartRefreshLayout.this.mState != RefreshState.None && !SmartRefreshLayout.this.mState.isOpening && !SmartRefreshLayout.this.mState.isDragging) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                    } else if (SmartRefreshLayout.this.mState != SmartRefreshLayout.this.mViceState) {
                        SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                        smartRefreshLayout2.setViceState(smartRefreshLayout2.mState);
                    }
                }
            }
        });
        this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), false);
            }
        });
        this.reboundAnimator.setStartDelay((long) i2);
        this.reboundAnimator.start();
        return this.reboundAnimator;
    }

    /* access modifiers changed from: protected */
    public void animSpinnerBounce(float f) {
        if (this.reboundAnimator != null) {
            return;
        }
        if (f > 0.0f && (this.mState == RefreshState.Refreshing || this.mState == RefreshState.TwoLevel)) {
            this.animationRunnable = new BounceRunnable(f, this.mHeaderHeight);
        } else if (f < 0.0f && (this.mState == RefreshState.Loading || ((this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) || (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && this.mState != RefreshState.Refreshing)))) {
            this.animationRunnable = new BounceRunnable(f, -this.mFooterHeight);
        } else if (this.mSpinner == 0 && this.mEnableOverScrollBounce) {
            this.animationRunnable = new BounceRunnable(f, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void overSpinner() {
        if (this.mState == RefreshState.TwoLevel) {
            if (this.mCurrentVelocity > -1000 && this.mSpinner > getHeight() / 2) {
                ValueAnimator animSpinner = this.mKernel.animSpinner(getHeight());
                if (animSpinner != null) {
                    animSpinner.setDuration((long) this.mFloorDuration);
                }
            } else if (this.mIsBeingDragged) {
                this.mKernel.finishTwoLevel();
            }
        } else if (this.mState == RefreshState.Loading || (this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && this.mSpinner < 0 && isEnableRefreshOrLoadMore(this.mEnableLoadMore))) {
            int i = this.mSpinner;
            int i2 = this.mFooterHeight;
            if (i < (-i2)) {
                this.mKernel.animSpinner(-i2);
            } else if (i > 0) {
                this.mKernel.animSpinner(0);
            }
        } else if (this.mState == RefreshState.Refreshing) {
            int i3 = this.mSpinner;
            int i4 = this.mHeaderHeight;
            if (i3 > i4) {
                this.mKernel.animSpinner(i4);
            } else if (i3 < 0) {
                this.mKernel.animSpinner(0);
            }
        } else if (this.mState == RefreshState.PullDownToRefresh) {
            this.mKernel.setState(RefreshState.PullDownCanceled);
        } else if (this.mState == RefreshState.PullUpToLoad) {
            this.mKernel.setState(RefreshState.PullUpCanceled);
        } else if (this.mState == RefreshState.ReleaseToRefresh) {
            this.mKernel.setState(RefreshState.Refreshing);
        } else if (this.mState == RefreshState.ReleaseToLoad) {
            this.mKernel.setState(RefreshState.Loading);
        } else if (this.mState == RefreshState.ReleaseToTwoLevel) {
            this.mKernel.setState(RefreshState.TwoLevelReleased);
        } else if (this.mState == RefreshState.RefreshReleased) {
            if (this.reboundAnimator == null) {
                this.mKernel.animSpinner(this.mHeaderHeight);
            }
        } else if (this.mState == RefreshState.LoadReleased) {
            if (this.reboundAnimator == null) {
                this.mKernel.animSpinner(-this.mFooterHeight);
            }
        } else if (this.mState != RefreshState.LoadFinish && this.mSpinner != 0) {
            this.mKernel.animSpinner(0);
        }
    }

    /* access modifiers changed from: protected */
    public void moveSpinnerInfinitely(float f) {
        float f2 = (!this.mNestedInProgress || this.mEnableLoadMoreWhenContentNotFull || f >= 0.0f || this.mRefreshContent.canLoadMore()) ? f : 0.0f;
        if (f2 > ((float) (this.mScreenHeightPixels * 5)) && getTag() == null && getTag(C2564R.C2565id.srl_tag) == null) {
            float f3 = this.mLastTouchY;
            int i = this.mScreenHeightPixels;
            if (f3 < ((float) i) / 6.0f && this.mLastTouchX < ((float) i) / 16.0f) {
                Toast.makeText(getContext(), "你这么死拉，臣妾做不到啊！", 0).show();
                setTag(C2564R.C2565id.srl_tag, "你这么死拉，臣妾做不到啊！");
            }
        }
        if (this.mState == RefreshState.TwoLevel && f2 > 0.0f) {
            this.mKernel.moveSpinner(Math.min((int) f2, getMeasuredHeight()), true);
        } else if (this.mState == RefreshState.Refreshing && f2 >= 0.0f) {
            int i2 = this.mHeaderHeight;
            if (f2 < ((float) i2)) {
                this.mKernel.moveSpinner((int) f2, true);
            } else {
                double d = (double) ((this.mHeaderMaxDragRate - 1.0f) * ((float) i2));
                int max = Math.max((this.mScreenHeightPixels * 4) / 3, getHeight());
                int i3 = this.mHeaderHeight;
                double d2 = (double) (max - i3);
                double max2 = (double) Math.max(0.0f, (f2 - ((float) i3)) * this.mDragRate);
                Double.isNaN(max2);
                double d3 = -max2;
                if (d2 == Utils.DOUBLE_EPSILON) {
                    d2 = 1.0d;
                }
                Double.isNaN(d);
                this.mKernel.moveSpinner(((int) Math.min(d * (1.0d - Math.pow(100.0d, d3 / d2)), max2)) + this.mHeaderHeight, true);
            }
        } else if (f2 < 0.0f && (this.mState == RefreshState.Loading || ((this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) || (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore))))) {
            int i4 = this.mFooterHeight;
            if (f2 > ((float) (-i4))) {
                this.mKernel.moveSpinner((int) f2, true);
            } else {
                double d4 = (double) ((this.mFooterMaxDragRate - 1.0f) * ((float) i4));
                int max3 = Math.max((this.mScreenHeightPixels * 4) / 3, getHeight());
                int i5 = this.mFooterHeight;
                double d5 = (double) (max3 - i5);
                double d6 = (double) (-Math.min(0.0f, (((float) i5) + f2) * this.mDragRate));
                Double.isNaN(d6);
                double d7 = -d6;
                if (d5 == Utils.DOUBLE_EPSILON) {
                    d5 = 1.0d;
                }
                Double.isNaN(d4);
                this.mKernel.moveSpinner(((int) (-Math.min(d4 * (1.0d - Math.pow(100.0d, d7 / d5)), d6))) - this.mFooterHeight, true);
            }
        } else if (f2 >= 0.0f) {
            double d8 = (double) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight));
            double max4 = (double) Math.max(this.mScreenHeightPixels / 2, getHeight());
            double max5 = (double) Math.max(0.0f, this.mDragRate * f2);
            Double.isNaN(max5);
            double d9 = -max5;
            if (max4 == Utils.DOUBLE_EPSILON) {
                max4 = 1.0d;
            }
            Double.isNaN(d8);
            this.mKernel.moveSpinner((int) Math.min(d8 * (1.0d - Math.pow(100.0d, d9 / max4)), max5), true);
        } else {
            double d10 = (double) (this.mFooterMaxDragRate * ((float) this.mFooterHeight));
            double max6 = (double) Math.max(this.mScreenHeightPixels / 2, getHeight());
            double d11 = (double) (-Math.min(0.0f, this.mDragRate * f2));
            Double.isNaN(d11);
            double d12 = -d11;
            if (max6 == Utils.DOUBLE_EPSILON) {
                max6 = 1.0d;
            }
            Double.isNaN(d10);
            this.mKernel.moveSpinner((int) (-Math.min(d10 * (1.0d - Math.pow(100.0d, d12 / max6)), d11)), true);
        }
        if (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && f2 < 0.0f && this.mState != RefreshState.Refreshing && this.mState != RefreshState.Loading && this.mState != RefreshState.LoadFinish) {
            if (this.mDisableContentWhenLoading) {
                this.animationRunnable = null;
                this.mKernel.animSpinner(-this.mFooterHeight);
            }
            setStateDirectLoading(false);
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    if (SmartRefreshLayout.this.mLoadMoreListener != null) {
                        SmartRefreshLayout.this.mLoadMoreListener.onLoadMore(SmartRefreshLayout.this);
                    } else if (SmartRefreshLayout.this.mOnMultiListener == null) {
                        SmartRefreshLayout.this.finishLoadMore(2000);
                    }
                    OnMultiListener onMultiListener = SmartRefreshLayout.this.mOnMultiListener;
                    if (onMultiListener != null) {
                        onMultiListener.onLoadMore(SmartRefreshLayout.this);
                    }
                }
            }, (long) this.mReboundDuration);
        }
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int backgroundColor = 0;
        public SpinnerStyle spinnerStyle = null;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2564R.styleable.SmartRefreshLayout_Layout);
            this.backgroundColor = obtainStyledAttributes.getColor(C2564R.styleable.SmartRefreshLayout_Layout_layout_srlBackgroundColor, this.backgroundColor);
            if (obtainStyledAttributes.hasValue(C2564R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle)) {
                this.spinnerStyle = SpinnerStyle.values[obtainStyledAttributes.getInt(C2564R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle, SpinnerStyle.Translate.ordinal)];
            }
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }
    }

    public int getNestedScrollAxes() {
        return this.mNestedParent.getNestedScrollAxes();
    }

    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i) {
        if (!(isEnabled() && isNestedScrollingEnabled() && (i & 2) != 0) || (!this.mEnableOverScrollDrag && !this.mEnableRefresh && !this.mEnableLoadMore)) {
            return false;
        }
        return true;
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        this.mNestedParent.onNestedScrollAccepted(view, view2, i);
        this.mNestedChild.startNestedScroll(i & 2);
        this.mTotalUnconsumed = this.mSpinner;
        this.mNestedInProgress = true;
        interceptAnimatorByAction(0);
    }

    public void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr) {
        int i3;
        int i4 = this.mTotalUnconsumed;
        if (i2 * i4 > 0) {
            if (Math.abs(i2) > Math.abs(this.mTotalUnconsumed)) {
                i3 = this.mTotalUnconsumed;
                this.mTotalUnconsumed = 0;
            } else {
                this.mTotalUnconsumed -= i2;
                i3 = i2;
            }
            moveSpinnerInfinitely((float) this.mTotalUnconsumed);
        } else if (i2 <= 0 || !this.mFooterLocked) {
            i3 = 0;
        } else {
            this.mTotalUnconsumed = i4 - i2;
            moveSpinnerInfinitely((float) this.mTotalUnconsumed);
            i3 = i2;
        }
        this.mNestedChild.dispatchNestedPreScroll(i, i2 - i3, iArr, (int[]) null);
        iArr[1] = iArr[1] + i3;
    }

    public void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4) {
        ViewParent parent;
        ScrollBoundaryDecider scrollBoundaryDecider;
        ScrollBoundaryDecider scrollBoundaryDecider2;
        boolean dispatchNestedScroll = this.mNestedChild.dispatchNestedScroll(i, i2, i3, i4, this.mParentOffsetInWindow);
        int i5 = i4 + this.mParentOffsetInWindow[1];
        if ((i5 < 0 && ((this.mEnableRefresh || this.mEnableOverScrollDrag) && (this.mTotalUnconsumed != 0 || (scrollBoundaryDecider2 = this.mScrollBoundaryDecider) == null || scrollBoundaryDecider2.canRefresh(this.mRefreshContent.getView())))) || (i5 > 0 && ((this.mEnableLoadMore || this.mEnableOverScrollDrag) && (this.mTotalUnconsumed != 0 || (scrollBoundaryDecider = this.mScrollBoundaryDecider) == null || scrollBoundaryDecider.canLoadMore(this.mRefreshContent.getView()))))) {
            if (this.mViceState == RefreshState.None || this.mViceState.isOpening) {
                this.mKernel.setState(i5 > 0 ? RefreshState.PullUpToLoad : RefreshState.PullDownToRefresh);
                if (!dispatchNestedScroll && (parent = getParent()) != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            }
            int i6 = this.mTotalUnconsumed - i5;
            this.mTotalUnconsumed = i6;
            moveSpinnerInfinitely((float) i6);
        }
        if (this.mFooterLocked && i2 < 0) {
            this.mFooterLocked = false;
        }
    }

    public boolean onNestedPreFling(@NonNull View view, float f, float f2) {
        return (this.mFooterLocked && f2 > 0.0f) || startFlingIfNeed(-f2) || this.mNestedChild.dispatchNestedPreFling(f, f2);
    }

    public boolean onNestedFling(@NonNull View view, float f, float f2, boolean z) {
        return this.mNestedChild.dispatchNestedFling(f, f2, z);
    }

    public void onStopNestedScroll(@NonNull View view) {
        this.mNestedParent.onStopNestedScroll(view);
        this.mNestedInProgress = false;
        this.mTotalUnconsumed = 0;
        overSpinner();
        this.mNestedChild.stopNestedScroll();
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mEnableNestedScrolling = z;
        this.mNestedChild.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mEnableNestedScrolling && (this.mEnableOverScrollDrag || this.mEnableRefresh || this.mEnableLoadMore);
    }

    public RefreshLayout setHeaderHeight(float f) {
        return setHeaderHeightPx(SmartUtil.dp2px(f));
    }

    public RefreshLayout setHeaderHeightPx(int i) {
        if (i != this.mHeaderHeight && this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mHeaderHeight = i;
            if (this.mRefreshHeader == null || !this.mAttachedToWindow || !this.mHeaderHeightStatus.notified) {
                this.mHeaderHeightStatus = DimensionStatus.CodeExactUnNotify;
            } else {
                SpinnerStyle spinnerStyle = this.mRefreshHeader.getSpinnerStyle();
                if (spinnerStyle != SpinnerStyle.MatchLayout && !spinnerStyle.scale) {
                    View view = this.mRefreshHeader.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    int i2 = 0;
                    view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(Math.max((this.mHeaderHeight - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, 0), 1073741824));
                    int i3 = marginLayoutParams.leftMargin;
                    int i4 = marginLayoutParams.topMargin + this.mHeaderInsetStart;
                    if (spinnerStyle == SpinnerStyle.Translate) {
                        i2 = this.mHeaderHeight;
                    }
                    int i5 = i4 - i2;
                    view.layout(i3, i5, view.getMeasuredWidth() + i3, view.getMeasuredHeight() + i5);
                }
                this.mHeaderHeightStatus = DimensionStatus.CodeExact;
                RefreshComponent refreshComponent = this.mRefreshHeader;
                RefreshKernel refreshKernel = this.mKernel;
                int i6 = this.mHeaderHeight;
                refreshComponent.onInitialized(refreshKernel, i6, (int) (this.mHeaderMaxDragRate * ((float) i6)));
            }
        }
        return this;
    }

    public RefreshLayout setFooterHeight(float f) {
        return setFooterHeightPx(SmartUtil.dp2px(f));
    }

    public RefreshLayout setFooterHeightPx(int i) {
        if (i != this.mFooterHeight && this.mFooterHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mFooterHeight = i;
            if (this.mRefreshFooter == null || !this.mAttachedToWindow || !this.mFooterHeightStatus.notified) {
                this.mFooterHeightStatus = DimensionStatus.CodeExactUnNotify;
            } else {
                SpinnerStyle spinnerStyle = this.mRefreshFooter.getSpinnerStyle();
                if (spinnerStyle != SpinnerStyle.MatchLayout && !spinnerStyle.scale) {
                    View view = this.mRefreshFooter.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    int i2 = 0;
                    view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(Math.max((this.mFooterHeight - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, 0), 1073741824));
                    int i3 = marginLayoutParams.leftMargin;
                    int measuredHeight = (marginLayoutParams.topMargin + getMeasuredHeight()) - this.mFooterInsetStart;
                    if (spinnerStyle != SpinnerStyle.Translate) {
                        i2 = this.mFooterHeight;
                    }
                    int i4 = measuredHeight - i2;
                    view.layout(i3, i4, view.getMeasuredWidth() + i3, view.getMeasuredHeight() + i4);
                }
                this.mFooterHeightStatus = DimensionStatus.CodeExact;
                RefreshComponent refreshComponent = this.mRefreshFooter;
                RefreshKernel refreshKernel = this.mKernel;
                int i5 = this.mFooterHeight;
                refreshComponent.onInitialized(refreshKernel, i5, (int) (this.mFooterMaxDragRate * ((float) i5)));
            }
        }
        return this;
    }

    public RefreshLayout setHeaderInsetStart(float f) {
        this.mHeaderInsetStart = SmartUtil.dp2px(f);
        return this;
    }

    public RefreshLayout setHeaderInsetStartPx(int i) {
        this.mHeaderInsetStart = i;
        return this;
    }

    public RefreshLayout setFooterInsetStart(float f) {
        this.mFooterInsetStart = SmartUtil.dp2px(f);
        return this;
    }

    public RefreshLayout setFooterInsetStartPx(int i) {
        this.mFooterInsetStart = i;
        return this;
    }

    public RefreshLayout setDragRate(float f) {
        this.mDragRate = f;
        return this;
    }

    public RefreshLayout setHeaderMaxDragRate(float f) {
        this.mHeaderMaxDragRate = f;
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent == null || !this.mAttachedToWindow) {
            this.mHeaderHeightStatus = this.mHeaderHeightStatus.unNotify();
        } else {
            RefreshKernel refreshKernel = this.mKernel;
            int i = this.mHeaderHeight;
            refreshComponent.onInitialized(refreshKernel, i, (int) (this.mHeaderMaxDragRate * ((float) i)));
        }
        return this;
    }

    public RefreshLayout setFooterMaxDragRate(float f) {
        this.mFooterMaxDragRate = f;
        RefreshComponent refreshComponent = this.mRefreshFooter;
        if (refreshComponent == null || !this.mAttachedToWindow) {
            this.mFooterHeightStatus = this.mFooterHeightStatus.unNotify();
        } else {
            RefreshKernel refreshKernel = this.mKernel;
            int i = this.mFooterHeight;
            refreshComponent.onInitialized(refreshKernel, i, (int) (((float) i) * this.mFooterMaxDragRate));
        }
        return this;
    }

    public RefreshLayout setHeaderTriggerRate(float f) {
        this.mHeaderTriggerRate = f;
        return this;
    }

    public RefreshLayout setFooterTriggerRate(float f) {
        this.mFooterTriggerRate = f;
        return this;
    }

    public RefreshLayout setReboundInterpolator(@NonNull Interpolator interpolator) {
        this.mReboundInterpolator = interpolator;
        return this;
    }

    public RefreshLayout setReboundDuration(int i) {
        this.mReboundDuration = i;
        return this;
    }

    public RefreshLayout setEnableLoadMore(boolean z) {
        this.mManualLoadMore = true;
        this.mEnableLoadMore = z;
        return this;
    }

    public RefreshLayout setEnableRefresh(boolean z) {
        this.mEnableRefresh = z;
        return this;
    }

    public RefreshLayout setEnableHeaderTranslationContent(boolean z) {
        this.mEnableHeaderTranslationContent = z;
        this.mManualHeaderTranslationContent = true;
        return this;
    }

    public RefreshLayout setEnableFooterTranslationContent(boolean z) {
        this.mEnableFooterTranslationContent = z;
        this.mManualFooterTranslationContent = true;
        return this;
    }

    public RefreshLayout setEnableAutoLoadMore(boolean z) {
        this.mEnableAutoLoadMore = z;
        return this;
    }

    public RefreshLayout setEnableOverScrollBounce(boolean z) {
        this.mEnableOverScrollBounce = z;
        return this;
    }

    public RefreshLayout setEnablePureScrollMode(boolean z) {
        this.mEnablePureScrollMode = z;
        return this;
    }

    public RefreshLayout setEnableScrollContentWhenLoaded(boolean z) {
        this.mEnableScrollContentWhenLoaded = z;
        return this;
    }

    public RefreshLayout setEnableScrollContentWhenRefreshed(boolean z) {
        this.mEnableScrollContentWhenRefreshed = z;
        return this;
    }

    public RefreshLayout setEnableLoadMoreWhenContentNotFull(boolean z) {
        this.mEnableLoadMoreWhenContentNotFull = z;
        RefreshContent refreshContent = this.mRefreshContent;
        if (refreshContent != null) {
            refreshContent.setEnableLoadMoreWhenContentNotFull(z);
        }
        return this;
    }

    public RefreshLayout setEnableOverScrollDrag(boolean z) {
        this.mEnableOverScrollDrag = z;
        return this;
    }

    public RefreshLayout setEnableFooterFollowWhenNoMoreData(boolean z) {
        this.mEnableFooterFollowWhenNoMoreData = z;
        return this;
    }

    public RefreshLayout setEnableClipHeaderWhenFixedBehind(boolean z) {
        this.mEnableClipHeaderWhenFixedBehind = z;
        return this;
    }

    public RefreshLayout setEnableClipFooterWhenFixedBehind(boolean z) {
        this.mEnableClipFooterWhenFixedBehind = z;
        return this;
    }

    public RefreshLayout setEnableNestedScroll(boolean z) {
        setNestedScrollingEnabled(z);
        return this;
    }

    public RefreshLayout setFixedHeaderViewId(int i) {
        this.mFixedHeaderViewId = i;
        return this;
    }

    public RefreshLayout setFixedFooterViewId(int i) {
        this.mFixedFooterViewId = i;
        return this;
    }

    public RefreshLayout setHeaderTranslationViewId(int i) {
        this.mHeaderTranslationViewId = i;
        return this;
    }

    public RefreshLayout setFooterTranslationViewId(int i) {
        this.mFooterTranslationViewId = i;
        return this;
    }

    public RefreshLayout setDisableContentWhenRefresh(boolean z) {
        this.mDisableContentWhenRefresh = z;
        return this;
    }

    public RefreshLayout setDisableContentWhenLoading(boolean z) {
        this.mDisableContentWhenLoading = z;
        return this;
    }

    public RefreshLayout setRefreshHeader(@NonNull RefreshHeader refreshHeader) {
        return setRefreshHeader(refreshHeader, 0, 0);
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [android.view.ViewGroup$LayoutParams] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.scwang.smart.refresh.layout.api.RefreshLayout setRefreshHeader(@androidx.annotation.NonNull com.scwang.smart.refresh.layout.api.RefreshHeader r3, int r4, int r5) {
        /*
            r2 = this;
            com.scwang.smart.refresh.layout.api.RefreshComponent r0 = r2.mRefreshHeader
            if (r0 == 0) goto L_0x000b
            android.view.View r0 = r0.getView()
            super.removeView(r0)
        L_0x000b:
            r2.mRefreshHeader = r3
            r0 = 0
            r2.mHeaderBackgroundColor = r0
            r2.mHeaderNeedTouchEventWhenRefreshing = r0
            com.scwang.smart.refresh.layout.constant.DimensionStatus r1 = com.scwang.smart.refresh.layout.constant.DimensionStatus.DefaultUnNotify
            r2.mHeaderHeightStatus = r1
            if (r4 != 0) goto L_0x0019
            r4 = -1
        L_0x0019:
            if (r5 != 0) goto L_0x001c
            r5 = -2
        L_0x001c:
            com.scwang.smart.refresh.layout.SmartRefreshLayout$LayoutParams r1 = new com.scwang.smart.refresh.layout.SmartRefreshLayout$LayoutParams
            r1.<init>((int) r4, (int) r5)
            android.view.View r3 = r3.getView()
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            boolean r4 = r3 instanceof com.scwang.smart.refresh.layout.SmartRefreshLayout.LayoutParams
            if (r4 == 0) goto L_0x0030
            r1 = r3
            com.scwang.smart.refresh.layout.SmartRefreshLayout$LayoutParams r1 = (com.scwang.smart.refresh.layout.SmartRefreshLayout.LayoutParams) r1
        L_0x0030:
            com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r2.mRefreshHeader
            com.scwang.smart.refresh.layout.constant.SpinnerStyle r3 = r3.getSpinnerStyle()
            boolean r3 = r3.front
            if (r3 == 0) goto L_0x0048
            com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r2.mRefreshHeader
            android.view.View r3 = r3.getView()
            int r4 = r2.getChildCount()
            super.addView(r3, r4, r1)
            goto L_0x0051
        L_0x0048:
            com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r2.mRefreshHeader
            android.view.View r3 = r3.getView()
            super.addView(r3, r0, r1)
        L_0x0051:
            int[] r3 = r2.mPrimaryColors
            if (r3 == 0) goto L_0x005c
            com.scwang.smart.refresh.layout.api.RefreshComponent r4 = r2.mRefreshHeader
            if (r4 == 0) goto L_0x005c
            r4.setPrimaryColors(r3)
        L_0x005c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smart.refresh.layout.SmartRefreshLayout.setRefreshHeader(com.scwang.smart.refresh.layout.api.RefreshHeader, int, int):com.scwang.smart.refresh.layout.api.RefreshLayout");
    }

    public RefreshLayout setRefreshFooter(@NonNull RefreshFooter refreshFooter) {
        return setRefreshFooter(refreshFooter, 0, 0);
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [android.view.ViewGroup$LayoutParams] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.scwang.smart.refresh.layout.api.RefreshLayout setRefreshFooter(@androidx.annotation.NonNull com.scwang.smart.refresh.layout.api.RefreshFooter r3, int r4, int r5) {
        /*
            r2 = this;
            com.scwang.smart.refresh.layout.api.RefreshComponent r0 = r2.mRefreshFooter
            if (r0 == 0) goto L_0x000b
            android.view.View r0 = r0.getView()
            super.removeView(r0)
        L_0x000b:
            r2.mRefreshFooter = r3
            r0 = 0
            r2.mFooterLocked = r0
            r2.mFooterBackgroundColor = r0
            r2.mFooterNoMoreDataEffective = r0
            r2.mFooterNeedTouchEventWhenLoading = r0
            com.scwang.smart.refresh.layout.constant.DimensionStatus r1 = com.scwang.smart.refresh.layout.constant.DimensionStatus.DefaultUnNotify
            r2.mFooterHeightStatus = r1
            boolean r1 = r2.mManualLoadMore
            if (r1 == 0) goto L_0x0025
            boolean r1 = r2.mEnableLoadMore
            if (r1 == 0) goto L_0x0023
            goto L_0x0025
        L_0x0023:
            r1 = 0
            goto L_0x0026
        L_0x0025:
            r1 = 1
        L_0x0026:
            r2.mEnableLoadMore = r1
            if (r4 != 0) goto L_0x002b
            r4 = -1
        L_0x002b:
            if (r5 != 0) goto L_0x002e
            r5 = -2
        L_0x002e:
            com.scwang.smart.refresh.layout.SmartRefreshLayout$LayoutParams r1 = new com.scwang.smart.refresh.layout.SmartRefreshLayout$LayoutParams
            r1.<init>((int) r4, (int) r5)
            android.view.View r3 = r3.getView()
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            boolean r4 = r3 instanceof com.scwang.smart.refresh.layout.SmartRefreshLayout.LayoutParams
            if (r4 == 0) goto L_0x0042
            r1 = r3
            com.scwang.smart.refresh.layout.SmartRefreshLayout$LayoutParams r1 = (com.scwang.smart.refresh.layout.SmartRefreshLayout.LayoutParams) r1
        L_0x0042:
            com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r2.mRefreshFooter
            com.scwang.smart.refresh.layout.constant.SpinnerStyle r3 = r3.getSpinnerStyle()
            boolean r3 = r3.front
            if (r3 == 0) goto L_0x005a
            com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r2.mRefreshFooter
            android.view.View r3 = r3.getView()
            int r4 = r2.getChildCount()
            super.addView(r3, r4, r1)
            goto L_0x0063
        L_0x005a:
            com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r2.mRefreshFooter
            android.view.View r3 = r3.getView()
            super.addView(r3, r0, r1)
        L_0x0063:
            int[] r3 = r2.mPrimaryColors
            if (r3 == 0) goto L_0x006e
            com.scwang.smart.refresh.layout.api.RefreshComponent r4 = r2.mRefreshFooter
            if (r4 == 0) goto L_0x006e
            r4.setPrimaryColors(r3)
        L_0x006e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smart.refresh.layout.SmartRefreshLayout.setRefreshFooter(com.scwang.smart.refresh.layout.api.RefreshFooter, int, int):com.scwang.smart.refresh.layout.api.RefreshLayout");
    }

    public RefreshLayout setRefreshContent(@NonNull View view) {
        return setRefreshContent(view, 0, 0);
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [android.view.ViewGroup$LayoutParams] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.scwang.smart.refresh.layout.api.RefreshLayout setRefreshContent(@androidx.annotation.NonNull android.view.View r2, int r3, int r4) {
        /*
            r1 = this;
            com.scwang.smart.refresh.layout.api.RefreshContent r0 = r1.mRefreshContent
            if (r0 == 0) goto L_0x000b
            android.view.View r0 = r0.getView()
            super.removeView(r0)
        L_0x000b:
            r0 = -1
            if (r3 != 0) goto L_0x000f
            r3 = -1
        L_0x000f:
            if (r4 != 0) goto L_0x0012
            r4 = -1
        L_0x0012:
            com.scwang.smart.refresh.layout.SmartRefreshLayout$LayoutParams r0 = new com.scwang.smart.refresh.layout.SmartRefreshLayout$LayoutParams
            r0.<init>((int) r3, (int) r4)
            android.view.ViewGroup$LayoutParams r3 = r2.getLayoutParams()
            boolean r4 = r3 instanceof com.scwang.smart.refresh.layout.SmartRefreshLayout.LayoutParams
            if (r4 == 0) goto L_0x0022
            r0 = r3
            com.scwang.smart.refresh.layout.SmartRefreshLayout$LayoutParams r0 = (com.scwang.smart.refresh.layout.SmartRefreshLayout.LayoutParams) r0
        L_0x0022:
            int r3 = r1.getChildCount()
            super.addView(r2, r3, r0)
            com.scwang.smart.refresh.layout.wrapper.RefreshContentWrapper r3 = new com.scwang.smart.refresh.layout.wrapper.RefreshContentWrapper
            r3.<init>(r2)
            r1.mRefreshContent = r3
            boolean r2 = r1.mAttachedToWindow
            if (r2 == 0) goto L_0x0055
            int r2 = r1.mFixedHeaderViewId
            android.view.View r2 = r1.findViewById(r2)
            int r3 = r1.mFixedFooterViewId
            android.view.View r3 = r1.findViewById(r3)
            com.scwang.smart.refresh.layout.api.RefreshContent r4 = r1.mRefreshContent
            com.scwang.smart.refresh.layout.listener.ScrollBoundaryDecider r0 = r1.mScrollBoundaryDecider
            r4.setScrollBoundaryDecider(r0)
            com.scwang.smart.refresh.layout.api.RefreshContent r4 = r1.mRefreshContent
            boolean r0 = r1.mEnableLoadMoreWhenContentNotFull
            r4.setEnableLoadMoreWhenContentNotFull(r0)
            com.scwang.smart.refresh.layout.api.RefreshContent r4 = r1.mRefreshContent
            com.scwang.smart.refresh.layout.api.RefreshKernel r0 = r1.mKernel
            r4.setUpComponent(r0, r2, r3)
        L_0x0055:
            com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r1.mRefreshHeader
            if (r2 == 0) goto L_0x006a
            com.scwang.smart.refresh.layout.constant.SpinnerStyle r2 = r2.getSpinnerStyle()
            boolean r2 = r2.front
            if (r2 == 0) goto L_0x006a
            com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r1.mRefreshHeader
            android.view.View r2 = r2.getView()
            super.bringChildToFront(r2)
        L_0x006a:
            com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r1.mRefreshFooter
            if (r2 == 0) goto L_0x007f
            com.scwang.smart.refresh.layout.constant.SpinnerStyle r2 = r2.getSpinnerStyle()
            boolean r2 = r2.front
            if (r2 == 0) goto L_0x007f
            com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r1.mRefreshFooter
            android.view.View r2 = r2.getView()
            super.bringChildToFront(r2)
        L_0x007f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smart.refresh.layout.SmartRefreshLayout.setRefreshContent(android.view.View, int, int):com.scwang.smart.refresh.layout.api.RefreshLayout");
    }

    @Nullable
    public RefreshFooter getRefreshFooter() {
        RefreshComponent refreshComponent = this.mRefreshFooter;
        if (refreshComponent instanceof RefreshFooter) {
            return (RefreshFooter) refreshComponent;
        }
        return null;
    }

    @Nullable
    public RefreshHeader getRefreshHeader() {
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent instanceof RefreshHeader) {
            return (RefreshHeader) refreshComponent;
        }
        return null;
    }

    @NonNull
    public RefreshState getState() {
        return this.mState;
    }

    public RefreshLayout setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
        return this;
    }

    public RefreshLayout setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mLoadMoreListener = onLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || (!this.mManualLoadMore && onLoadMoreListener != null);
        return this;
    }

    public RefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        this.mRefreshListener = onRefreshLoadMoreListener;
        this.mLoadMoreListener = onRefreshLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || (!this.mManualLoadMore && onRefreshLoadMoreListener != null);
        return this;
    }

    public RefreshLayout setOnMultiListener(OnMultiListener onMultiListener) {
        this.mOnMultiListener = onMultiListener;
        return this;
    }

    public RefreshLayout setPrimaryColors(@ColorInt int... iArr) {
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent != null) {
            refreshComponent.setPrimaryColors(iArr);
        }
        RefreshComponent refreshComponent2 = this.mRefreshFooter;
        if (refreshComponent2 != null) {
            refreshComponent2.setPrimaryColors(iArr);
        }
        this.mPrimaryColors = iArr;
        return this;
    }

    public RefreshLayout setPrimaryColorsId(@ColorRes int... iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = ContextCompat.getColor(getContext(), iArr[i]);
        }
        setPrimaryColors(iArr2);
        return this;
    }

    public RefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider) {
        this.mScrollBoundaryDecider = scrollBoundaryDecider;
        RefreshContent refreshContent = this.mRefreshContent;
        if (refreshContent != null) {
            refreshContent.setScrollBoundaryDecider(scrollBoundaryDecider);
        }
        return this;
    }

    public RefreshLayout setNoMoreData(boolean z) {
        if (this.mState == RefreshState.Refreshing && z) {
            finishRefreshWithNoMoreData();
        } else if (this.mState == RefreshState.Loading && z) {
            finishLoadMoreWithNoMoreData();
        } else if (this.mFooterNoMoreData != z) {
            this.mFooterNoMoreData = z;
            RefreshComponent refreshComponent = this.mRefreshFooter;
            if (refreshComponent instanceof RefreshFooter) {
                if (((RefreshFooter) refreshComponent).setNoMoreData(z)) {
                    this.mFooterNoMoreDataEffective = true;
                    if (this.mFooterNoMoreData && this.mEnableFooterFollowWhenNoMoreData && this.mSpinner > 0 && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && isEnableTranslationContent(this.mEnableRefresh, this.mRefreshHeader)) {
                        this.mRefreshFooter.getView().setTranslationY((float) this.mSpinner);
                    }
                } else {
                    this.mFooterNoMoreDataEffective = false;
                    new RuntimeException("Footer:" + this.mRefreshFooter + " NoMoreData is not supported.(不支持NoMoreData，请使用[ClassicsFooter]或者[自定义Footer并实现setNoMoreData方法且返回true])").printStackTrace();
                }
            }
        }
        return this;
    }

    public RefreshLayout resetNoMoreData() {
        return setNoMoreData(false);
    }

    public RefreshLayout finishRefresh() {
        return finishRefresh(true);
    }

    public RefreshLayout finishLoadMore() {
        return finishLoadMore(true);
    }

    public RefreshLayout finishRefresh(int i) {
        return finishRefresh(i, true, Boolean.FALSE);
    }

    public RefreshLayout finishRefresh(boolean z) {
        if (z) {
            return finishRefresh(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) << 16, true, Boolean.FALSE);
        }
        return finishRefresh(0, false, (Boolean) null);
    }

    public RefreshLayout finishRefresh(int i, final boolean z, final Boolean bool) {
        final int i2 = i >> 16;
        int i3 = (i << 16) >> 16;
        C25536 r1 = new Runnable() {
            int count = 0;

            public void run() {
                ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
                if (this.count == 0) {
                    if (SmartRefreshLayout.this.mState == RefreshState.None && SmartRefreshLayout.this.mViceState == RefreshState.Refreshing) {
                        SmartRefreshLayout.this.mViceState = RefreshState.None;
                    } else if (SmartRefreshLayout.this.reboundAnimator != null && SmartRefreshLayout.this.mState.isHeader && (SmartRefreshLayout.this.mState.isDragging || SmartRefreshLayout.this.mState == RefreshState.RefreshReleased)) {
                        SmartRefreshLayout.this.reboundAnimator.setDuration(0);
                        SmartRefreshLayout.this.reboundAnimator.cancel();
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        smartRefreshLayout.reboundAnimator = null;
                        if (smartRefreshLayout.mKernel.animSpinner(0) == null) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                        } else {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownCanceled);
                        }
                    } else if (!(SmartRefreshLayout.this.mState != RefreshState.Refreshing || SmartRefreshLayout.this.mRefreshHeader == null || SmartRefreshLayout.this.mRefreshContent == null)) {
                        this.count++;
                        SmartRefreshLayout.this.mHandler.postDelayed(this, (long) i2);
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshFinish);
                        if (bool == Boolean.FALSE) {
                            SmartRefreshLayout.this.setNoMoreData(false);
                        }
                    }
                    if (bool == Boolean.TRUE) {
                        SmartRefreshLayout.this.setNoMoreData(true);
                        return;
                    }
                    return;
                }
                int onFinish = SmartRefreshLayout.this.mRefreshHeader.onFinish(SmartRefreshLayout.this, z);
                if (SmartRefreshLayout.this.mOnMultiListener != null && (SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                    SmartRefreshLayout.this.mOnMultiListener.onHeaderFinish((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, z);
                }
                if (onFinish < Integer.MAX_VALUE) {
                    if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (SmartRefreshLayout.this.mIsBeingDragged) {
                            SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                            smartRefreshLayout2.mTouchY = smartRefreshLayout2.mLastTouchY;
                            SmartRefreshLayout smartRefreshLayout3 = SmartRefreshLayout.this;
                            smartRefreshLayout3.mTouchSpinner = 0;
                            smartRefreshLayout3.mIsBeingDragged = false;
                            long j = currentTimeMillis;
                            boolean unused = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, j, 0, smartRefreshLayout3.mLastTouchX, (SmartRefreshLayout.this.mLastTouchY + ((float) SmartRefreshLayout.this.mSpinner)) - ((float) (SmartRefreshLayout.this.mTouchSlop * 2)), 0));
                            SmartRefreshLayout smartRefreshLayout4 = SmartRefreshLayout.this;
                            boolean unused2 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, j, 2, smartRefreshLayout4.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + ((float) SmartRefreshLayout.this.mSpinner), 0));
                        }
                        if (SmartRefreshLayout.this.mNestedInProgress) {
                            SmartRefreshLayout smartRefreshLayout5 = SmartRefreshLayout.this;
                            smartRefreshLayout5.mTotalUnconsumed = 0;
                            boolean unused3 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 1, smartRefreshLayout5.mLastTouchX, SmartRefreshLayout.this.mLastTouchY, 0));
                            SmartRefreshLayout smartRefreshLayout6 = SmartRefreshLayout.this;
                            smartRefreshLayout6.mNestedInProgress = false;
                            smartRefreshLayout6.mTouchSpinner = 0;
                        }
                    }
                    if (SmartRefreshLayout.this.mSpinner > 0) {
                        SmartRefreshLayout smartRefreshLayout7 = SmartRefreshLayout.this;
                        ValueAnimator animSpinner = smartRefreshLayout7.animSpinner(0, onFinish, smartRefreshLayout7.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                        if (SmartRefreshLayout.this.mEnableScrollContentWhenRefreshed) {
                            animatorUpdateListener = SmartRefreshLayout.this.mRefreshContent.scrollContentWhenFinished(SmartRefreshLayout.this.mSpinner);
                        }
                        if (animSpinner != null && animatorUpdateListener != null) {
                            animSpinner.addUpdateListener(animatorUpdateListener);
                        }
                    } else if (SmartRefreshLayout.this.mSpinner < 0) {
                        SmartRefreshLayout smartRefreshLayout8 = SmartRefreshLayout.this;
                        smartRefreshLayout8.animSpinner(0, onFinish, smartRefreshLayout8.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                    } else {
                        SmartRefreshLayout.this.mKernel.moveSpinner(0, false);
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                    }
                }
            }
        };
        if (i3 > 0) {
            this.mHandler.postDelayed(r1, (long) i3);
        } else {
            r1.run();
        }
        return this;
    }

    public RefreshLayout finishRefreshWithNoMoreData() {
        return finishRefresh(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) << 16, true, Boolean.TRUE);
    }

    public RefreshLayout finishLoadMore(int i) {
        return finishLoadMore(i, true, false);
    }

    public RefreshLayout finishLoadMore(boolean z) {
        return finishLoadMore(z ? Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) << 16 : 0, z, false);
    }

    public RefreshLayout finishLoadMore(int i, final boolean z, final boolean z2) {
        final int i2 = i >> 16;
        int i3 = (i << 16) >> 16;
        C25547 r1 = new Runnable() {
            int count = 0;

            public void run() {
                boolean z = true;
                if (this.count == 0) {
                    if (SmartRefreshLayout.this.mState == RefreshState.None && SmartRefreshLayout.this.mViceState == RefreshState.Loading) {
                        SmartRefreshLayout.this.mViceState = RefreshState.None;
                    } else if (SmartRefreshLayout.this.reboundAnimator != null && ((SmartRefreshLayout.this.mState.isDragging || SmartRefreshLayout.this.mState == RefreshState.LoadReleased) && SmartRefreshLayout.this.mState.isFooter)) {
                        SmartRefreshLayout.this.reboundAnimator.setDuration(0);
                        SmartRefreshLayout.this.reboundAnimator.cancel();
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        smartRefreshLayout.reboundAnimator = null;
                        if (smartRefreshLayout.mKernel.animSpinner(0) == null) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                        } else {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpCanceled);
                        }
                    } else if (!(SmartRefreshLayout.this.mState != RefreshState.Loading || SmartRefreshLayout.this.mRefreshFooter == null || SmartRefreshLayout.this.mRefreshContent == null)) {
                        this.count++;
                        SmartRefreshLayout.this.mHandler.postDelayed(this, (long) i2);
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadFinish);
                        return;
                    }
                    if (z2) {
                        SmartRefreshLayout.this.setNoMoreData(true);
                        return;
                    }
                    return;
                }
                int onFinish = SmartRefreshLayout.this.mRefreshFooter.onFinish(SmartRefreshLayout.this, z);
                if (SmartRefreshLayout.this.mOnMultiListener != null && (SmartRefreshLayout.this.mRefreshFooter instanceof RefreshFooter)) {
                    SmartRefreshLayout.this.mOnMultiListener.onFooterFinish((RefreshFooter) SmartRefreshLayout.this.mRefreshFooter, z);
                }
                if (onFinish < Integer.MAX_VALUE) {
                    if (!z2 || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || SmartRefreshLayout.this.mSpinner >= 0 || !SmartRefreshLayout.this.mRefreshContent.canLoadMore()) {
                        z = false;
                    }
                    final int max = SmartRefreshLayout.this.mSpinner - (z ? Math.max(SmartRefreshLayout.this.mSpinner, -SmartRefreshLayout.this.mFooterHeight) : 0);
                    if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (SmartRefreshLayout.this.mIsBeingDragged) {
                            SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                            smartRefreshLayout2.mTouchY = smartRefreshLayout2.mLastTouchY;
                            SmartRefreshLayout smartRefreshLayout3 = SmartRefreshLayout.this;
                            smartRefreshLayout3.mTouchSpinner = smartRefreshLayout3.mSpinner - max;
                            SmartRefreshLayout smartRefreshLayout4 = SmartRefreshLayout.this;
                            smartRefreshLayout4.mIsBeingDragged = false;
                            int i = smartRefreshLayout4.mEnableFooterTranslationContent ? max : 0;
                            SmartRefreshLayout smartRefreshLayout5 = SmartRefreshLayout.this;
                            float f = (float) i;
                            long j = currentTimeMillis;
                            long j2 = currentTimeMillis;
                            boolean unused = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(j, j2, 0, smartRefreshLayout5.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f + ((float) (SmartRefreshLayout.this.mTouchSlop * 2)), 0));
                            SmartRefreshLayout smartRefreshLayout6 = SmartRefreshLayout.this;
                            boolean unused2 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(j, j2, 2, smartRefreshLayout6.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f, 0));
                        }
                        if (SmartRefreshLayout.this.mNestedInProgress) {
                            SmartRefreshLayout smartRefreshLayout7 = SmartRefreshLayout.this;
                            smartRefreshLayout7.mTotalUnconsumed = 0;
                            boolean unused3 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 1, smartRefreshLayout7.mLastTouchX, SmartRefreshLayout.this.mLastTouchY, 0));
                            SmartRefreshLayout smartRefreshLayout8 = SmartRefreshLayout.this;
                            smartRefreshLayout8.mNestedInProgress = false;
                            smartRefreshLayout8.mTouchSpinner = 0;
                        }
                    }
                    SmartRefreshLayout.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
                            ValueAnimator valueAnimator;
                            if (!SmartRefreshLayout.this.mEnableScrollContentWhenLoaded || max >= 0) {
                                animatorUpdateListener = null;
                            } else {
                                animatorUpdateListener = SmartRefreshLayout.this.mRefreshContent.scrollContentWhenFinished(SmartRefreshLayout.this.mSpinner);
                                if (animatorUpdateListener != null) {
                                    animatorUpdateListener.onAnimationUpdate(ValueAnimator.ofInt(new int[]{0, 0}));
                                }
                            }
                            C25561 r2 = new AnimatorListenerAdapter() {
                                public void onAnimationEnd(Animator animator) {
                                    if (animator == null || animator.getDuration() != 0) {
                                        SmartRefreshLayout.this.mFooterLocked = false;
                                        if (z2) {
                                            SmartRefreshLayout.this.setNoMoreData(true);
                                        }
                                        if (SmartRefreshLayout.this.mState == RefreshState.LoadFinish) {
                                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                        }
                                    }
                                }
                            };
                            if (SmartRefreshLayout.this.mSpinner > 0) {
                                valueAnimator = SmartRefreshLayout.this.mKernel.animSpinner(0);
                            } else {
                                if (animatorUpdateListener != null || SmartRefreshLayout.this.mSpinner == 0) {
                                    if (SmartRefreshLayout.this.reboundAnimator != null) {
                                        SmartRefreshLayout.this.reboundAnimator.setDuration(0);
                                        SmartRefreshLayout.this.reboundAnimator.cancel();
                                        SmartRefreshLayout.this.reboundAnimator = null;
                                    }
                                    SmartRefreshLayout.this.mKernel.moveSpinner(0, false);
                                    SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                                } else if (!z2 || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData) {
                                    valueAnimator = SmartRefreshLayout.this.mKernel.animSpinner(0);
                                } else if (SmartRefreshLayout.this.mSpinner >= (-SmartRefreshLayout.this.mFooterHeight)) {
                                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                } else {
                                    valueAnimator = SmartRefreshLayout.this.mKernel.animSpinner(-SmartRefreshLayout.this.mFooterHeight);
                                }
                                valueAnimator = null;
                            }
                            if (valueAnimator != null) {
                                valueAnimator.addListener(r2);
                            } else {
                                r2.onAnimationEnd((Animator) null);
                            }
                        }
                    }, SmartRefreshLayout.this.mSpinner < 0 ? (long) onFinish : 0);
                }
            }
        };
        if (i3 > 0) {
            this.mHandler.postDelayed(r1, (long) i3);
        } else {
            r1.run();
        }
        return this;
    }

    public RefreshLayout finishLoadMoreWithNoMoreData() {
        return finishLoadMore(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) << 16, true, true);
    }

    public RefreshLayout closeHeaderOrFooter() {
        if (this.mState == RefreshState.None && (this.mViceState == RefreshState.Refreshing || this.mViceState == RefreshState.Loading)) {
            this.mViceState = RefreshState.None;
        }
        if (this.mState == RefreshState.Refreshing) {
            finishRefresh();
        } else if (this.mState == RefreshState.Loading) {
            finishLoadMore();
        } else if (this.mKernel.animSpinner(0) == null) {
            notifyStateChanged(RefreshState.None);
        } else if (this.mState.isHeader) {
            notifyStateChanged(RefreshState.PullDownCanceled);
        } else {
            notifyStateChanged(RefreshState.PullUpCanceled);
        }
        return this;
    }

    public boolean autoRefresh() {
        int i = this.mAttachedToWindow ? 0 : 400;
        int i2 = this.mReboundDuration;
        int i3 = this.mHeaderHeight;
        float f = ((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) i3) * 1.0f;
        if (i3 == 0) {
            i3 = 1;
        }
        return autoRefresh(i, i2, f / ((float) i3), false);
    }

    public boolean autoRefresh(int i) {
        int i2 = this.mReboundDuration;
        int i3 = this.mHeaderHeight;
        float f = ((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) i3) * 1.0f;
        if (i3 == 0) {
            i3 = 1;
        }
        return autoRefresh(i, i2, f / ((float) i3), false);
    }

    public boolean autoRefreshAnimationOnly() {
        int i = this.mAttachedToWindow ? 0 : 400;
        int i2 = this.mReboundDuration;
        int i3 = this.mHeaderHeight;
        float f = ((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) i3) * 1.0f;
        if (i3 == 0) {
            i3 = 1;
        }
        return autoRefresh(i, i2, f / ((float) i3), true);
    }

    public boolean autoRefresh(int i, final int i2, final float f, final boolean z) {
        if (this.mState != RefreshState.None || !isEnableRefreshOrLoadMore(this.mEnableRefresh)) {
            return false;
        }
        C25578 r0 = new Runnable() {
            public void run() {
                if (SmartRefreshLayout.this.mViceState == RefreshState.Refreshing) {
                    if (SmartRefreshLayout.this.reboundAnimator != null) {
                        SmartRefreshLayout.this.reboundAnimator.setDuration(0);
                        SmartRefreshLayout.this.reboundAnimator.cancel();
                        SmartRefreshLayout.this.reboundAnimator = null;
                    }
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mLastTouchX = ((float) smartRefreshLayout.getMeasuredWidth()) / 2.0f;
                    SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
                    SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                    smartRefreshLayout2.reboundAnimator = ValueAnimator.ofInt(new int[]{smartRefreshLayout2.mSpinner, (int) (((float) SmartRefreshLayout.this.mHeaderHeight) * f)});
                    SmartRefreshLayout.this.reboundAnimator.setDuration((long) i2);
                    SmartRefreshLayout.this.reboundAnimator.setInterpolator(new SmartUtil(SmartUtil.INTERPOLATOR_VISCOUS_FLUID));
                    SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            if (SmartRefreshLayout.this.reboundAnimator != null && SmartRefreshLayout.this.mRefreshHeader != null) {
                                SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                            }
                        }
                    });
                    SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            if (animator == null || animator.getDuration() != 0) {
                                SmartRefreshLayout.this.reboundAnimator = null;
                                if (SmartRefreshLayout.this.mRefreshHeader != null) {
                                    if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToRefresh) {
                                        SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToRefresh);
                                    }
                                    SmartRefreshLayout.this.setStateRefreshing(!z);
                                    return;
                                }
                                SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                            }
                        }
                    });
                    SmartRefreshLayout.this.reboundAnimator.start();
                }
            }
        };
        setViceState(RefreshState.Refreshing);
        if (i > 0) {
            this.mHandler.postDelayed(r0, (long) i);
            return true;
        }
        r0.run();
        return true;
    }

    public boolean autoLoadMore() {
        int i = this.mReboundDuration;
        int i2 = this.mFooterHeight;
        float f = ((float) i2) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f) * 1.0f;
        if (i2 == 0) {
            i2 = 1;
        }
        return autoLoadMore(0, i, f / ((float) i2), false);
    }

    public boolean autoLoadMore(int i) {
        int i2 = this.mReboundDuration;
        int i3 = this.mFooterHeight;
        float f = ((float) i3) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f) * 1.0f;
        if (i3 == 0) {
            i3 = 1;
        }
        return autoLoadMore(i, i2, f / ((float) i3), false);
    }

    public boolean autoLoadMoreAnimationOnly() {
        int i = this.mReboundDuration;
        int i2 = this.mFooterHeight;
        float f = ((float) i2) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f) * 1.0f;
        if (i2 == 0) {
            i2 = 1;
        }
        return autoLoadMore(0, i, f / ((float) i2), true);
    }

    public boolean autoLoadMore(int i, final int i2, final float f, final boolean z) {
        if (this.mState != RefreshState.None || !isEnableRefreshOrLoadMore(this.mEnableLoadMore) || this.mFooterNoMoreData) {
            return false;
        }
        C25609 r0 = new Runnable() {
            public void run() {
                if (SmartRefreshLayout.this.mViceState == RefreshState.Loading) {
                    if (SmartRefreshLayout.this.reboundAnimator != null) {
                        SmartRefreshLayout.this.reboundAnimator.setDuration(0);
                        SmartRefreshLayout.this.reboundAnimator.cancel();
                        SmartRefreshLayout.this.reboundAnimator = null;
                    }
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mLastTouchX = ((float) smartRefreshLayout.getMeasuredWidth()) / 2.0f;
                    SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
                    SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                    smartRefreshLayout2.reboundAnimator = ValueAnimator.ofInt(new int[]{smartRefreshLayout2.mSpinner, -((int) (((float) SmartRefreshLayout.this.mFooterHeight) * f))});
                    SmartRefreshLayout.this.reboundAnimator.setDuration((long) i2);
                    SmartRefreshLayout.this.reboundAnimator.setInterpolator(new SmartUtil(SmartUtil.INTERPOLATOR_VISCOUS_FLUID));
                    SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            if (SmartRefreshLayout.this.reboundAnimator != null && SmartRefreshLayout.this.mRefreshFooter != null) {
                                SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                            }
                        }
                    });
                    SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            if (animator == null || animator.getDuration() != 0) {
                                SmartRefreshLayout.this.reboundAnimator = null;
                                if (SmartRefreshLayout.this.mRefreshFooter != null) {
                                    if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToLoad) {
                                        SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToLoad);
                                    }
                                    SmartRefreshLayout.this.setStateLoading(!z);
                                    return;
                                }
                                SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                            }
                        }
                    });
                    SmartRefreshLayout.this.reboundAnimator.start();
                }
            }
        };
        setViceState(RefreshState.Loading);
        if (i > 0) {
            this.mHandler.postDelayed(r0, (long) i);
            return true;
        }
        r0.run();
        return true;
    }

    public static void setDefaultRefreshHeaderCreator(@NonNull DefaultRefreshHeaderCreator defaultRefreshHeaderCreator) {
        sHeaderCreator = defaultRefreshHeaderCreator;
    }

    public static void setDefaultRefreshFooterCreator(@NonNull DefaultRefreshFooterCreator defaultRefreshFooterCreator) {
        sFooterCreator = defaultRefreshFooterCreator;
    }

    public static void setDefaultRefreshInitializer(@NonNull DefaultRefreshInitializer defaultRefreshInitializer) {
        sRefreshInitializer = defaultRefreshInitializer;
    }

    public boolean isRefreshing() {
        return this.mState == RefreshState.Refreshing;
    }

    public boolean isLoading() {
        return this.mState == RefreshState.Loading;
    }

    public class RefreshKernelImpl implements RefreshKernel {
        public RefreshKernelImpl() {
        }

        @NonNull
        public RefreshLayout getRefreshLayout() {
            return SmartRefreshLayout.this;
        }

        @NonNull
        public RefreshContent getRefreshContent() {
            return SmartRefreshLayout.this.mRefreshContent;
        }

        public RefreshKernel setState(@NonNull RefreshState refreshState) {
            switch (refreshState) {
                case None:
                    if (SmartRefreshLayout.this.mState != RefreshState.None && SmartRefreshLayout.this.mSpinner == 0) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                        return null;
                    } else if (SmartRefreshLayout.this.mSpinner == 0) {
                        return null;
                    } else {
                        animSpinner(0);
                        return null;
                    }
                case PullDownToRefresh:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        if (smartRefreshLayout.isEnableRefreshOrLoadMore(smartRefreshLayout.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownToRefresh);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.PullDownToRefresh);
                    return null;
                case PullUpToLoad:
                    SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                    if (!smartRefreshLayout2.isEnableRefreshOrLoadMore(smartRefreshLayout2.mEnableLoadMore) || SmartRefreshLayout.this.mState.isOpening || SmartRefreshLayout.this.mState.isFinishing || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData && SmartRefreshLayout.this.mFooterNoMoreDataEffective)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullUpToLoad);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpToLoad);
                    return null;
                case PullDownCanceled:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout3 = SmartRefreshLayout.this;
                        if (smartRefreshLayout3.isEnableRefreshOrLoadMore(smartRefreshLayout3.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownCanceled);
                            setState(RefreshState.None);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.PullDownCanceled);
                    return null;
                case PullUpCanceled:
                    SmartRefreshLayout smartRefreshLayout4 = SmartRefreshLayout.this;
                    if (!smartRefreshLayout4.isEnableRefreshOrLoadMore(smartRefreshLayout4.mEnableLoadMore) || SmartRefreshLayout.this.mState.isOpening || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData && SmartRefreshLayout.this.mFooterNoMoreDataEffective)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullUpCanceled);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpCanceled);
                    setState(RefreshState.None);
                    return null;
                case ReleaseToRefresh:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout5 = SmartRefreshLayout.this;
                        if (smartRefreshLayout5.isEnableRefreshOrLoadMore(smartRefreshLayout5.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToRefresh);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToRefresh);
                    return null;
                case ReleaseToLoad:
                    SmartRefreshLayout smartRefreshLayout6 = SmartRefreshLayout.this;
                    if (!smartRefreshLayout6.isEnableRefreshOrLoadMore(smartRefreshLayout6.mEnableLoadMore) || SmartRefreshLayout.this.mState.isOpening || SmartRefreshLayout.this.mState.isFinishing || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData && SmartRefreshLayout.this.mFooterNoMoreDataEffective)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToLoad);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToLoad);
                    return null;
                case ReleaseToTwoLevel:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout7 = SmartRefreshLayout.this;
                        if (smartRefreshLayout7.isEnableRefreshOrLoadMore(smartRefreshLayout7.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToTwoLevel);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToTwoLevel);
                    return null;
                case RefreshReleased:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout8 = SmartRefreshLayout.this;
                        if (smartRefreshLayout8.isEnableRefreshOrLoadMore(smartRefreshLayout8.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshReleased);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.RefreshReleased);
                    return null;
                case LoadReleased:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout9 = SmartRefreshLayout.this;
                        if (smartRefreshLayout9.isEnableRefreshOrLoadMore(smartRefreshLayout9.mEnableLoadMore)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadReleased);
                            return null;
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.LoadReleased);
                    return null;
                case Refreshing:
                    SmartRefreshLayout.this.setStateRefreshing(true);
                    return null;
                case Loading:
                    SmartRefreshLayout.this.setStateLoading(true);
                    return null;
                default:
                    SmartRefreshLayout.this.notifyStateChanged(refreshState);
                    return null;
            }
        }

        public RefreshKernel startTwoLevel(boolean z) {
            if (z) {
                C25631 r4 = new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        if (animator == null || animator.getDuration() != 0) {
                            SmartRefreshLayout.this.mKernel.setState(RefreshState.TwoLevel);
                        }
                    }
                };
                ValueAnimator animSpinner = animSpinner(SmartRefreshLayout.this.getMeasuredHeight());
                if (animSpinner == null || animSpinner != SmartRefreshLayout.this.reboundAnimator) {
                    r4.onAnimationEnd((Animator) null);
                } else {
                    animSpinner.setDuration((long) SmartRefreshLayout.this.mFloorDuration);
                    animSpinner.addListener(r4);
                }
            } else if (animSpinner(0) == null) {
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
            }
            return this;
        }

        public RefreshKernel finishTwoLevel() {
            if (SmartRefreshLayout.this.mState == RefreshState.TwoLevel) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.TwoLevelFinish);
                if (SmartRefreshLayout.this.mSpinner == 0) {
                    moveSpinner(0, false);
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                } else {
                    animSpinner(0).setDuration((long) SmartRefreshLayout.this.mFloorDuration);
                }
            }
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:46:0x00d6  */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x00ed  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.scwang.smart.refresh.layout.api.RefreshKernel moveSpinner(int r19, boolean r20) {
            /*
                r18 = this;
                r0 = r18
                r1 = r19
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                if (r2 != r1) goto L_0x002b
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                if (r2 == 0) goto L_0x001a
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                boolean r2 = r2.isSupportHorizontalDrag()
                if (r2 != 0) goto L_0x002b
            L_0x001a:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshFooter
                if (r2 == 0) goto L_0x002a
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshFooter
                boolean r2 = r2.isSupportHorizontalDrag()
                if (r2 != 0) goto L_0x002b
            L_0x002a:
                return r0
            L_0x002b:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r9 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r10 = r9.mSpinner
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                r2.mSpinner = r1
                if (r20 == 0) goto L_0x00b3
                com.scwang.smart.refresh.layout.constant.RefreshState r2 = r2.mViceState
                boolean r2 = r2.isDragging
                if (r2 != 0) goto L_0x0043
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r2 = r2.mViceState
                boolean r2 = r2.isOpening
                if (r2 == 0) goto L_0x00b3
            L_0x0043:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                float r2 = (float) r2
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r3 = r3.mHeaderHeight
                float r3 = (float) r3
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                float r4 = r4.mHeaderTriggerRate
                float r3 = r3 * r4
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L_0x0069
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r2 = r2.mState
                com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.ReleaseToTwoLevel
                if (r2 == r3) goto L_0x00b3
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshKernel r2 = r2.mKernel
                com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.ReleaseToRefresh
                r2.setState(r3)
                goto L_0x00b3
            L_0x0069:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                int r2 = -r2
                float r2 = (float) r2
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r3 = r3.mFooterHeight
                float r3 = (float) r3
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                float r4 = r4.mFooterTriggerRate
                float r3 = r3 * r4
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L_0x008e
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r2 = r2.mFooterNoMoreData
                if (r2 != 0) goto L_0x008e
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshKernel r2 = r2.mKernel
                com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.ReleaseToLoad
                r2.setState(r3)
                goto L_0x00b3
            L_0x008e:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                if (r2 >= 0) goto L_0x00a4
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r2 = r2.mFooterNoMoreData
                if (r2 != 0) goto L_0x00a4
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshKernel r2 = r2.mKernel
                com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.PullUpToLoad
                r2.setState(r3)
                goto L_0x00b3
            L_0x00a4:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                if (r2 <= 0) goto L_0x00b3
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshKernel r2 = r2.mKernel
                com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.PullDownToRefresh
                r2.setState(r3)
            L_0x00b3:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshContent r2 = r2.mRefreshContent
                r11 = 1
                r12 = 0
                if (r2 == 0) goto L_0x0199
                if (r1 < 0) goto L_0x00d2
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r3 = r2.mEnableHeaderTranslationContent
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r4 = r4.mRefreshHeader
                boolean r2 = r2.isEnableTranslationContent(r3, r4)
                if (r2 == 0) goto L_0x00ce
                r2 = r1
            L_0x00cc:
                r3 = 1
                goto L_0x00d4
            L_0x00ce:
                if (r10 >= 0) goto L_0x00d2
                r2 = 0
                goto L_0x00cc
            L_0x00d2:
                r2 = 0
                r3 = 0
            L_0x00d4:
                if (r1 > 0) goto L_0x00eb
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r5 = r4.mEnableFooterTranslationContent
                com.scwang.smart.refresh.layout.SmartRefreshLayout r6 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r6 = r6.mRefreshFooter
                boolean r4 = r4.isEnableTranslationContent(r5, r6)
                if (r4 == 0) goto L_0x00e7
                r2 = r1
            L_0x00e5:
                r3 = 1
                goto L_0x00eb
            L_0x00e7:
                if (r10 <= 0) goto L_0x00eb
                r2 = 0
                goto L_0x00e5
            L_0x00eb:
                if (r3 == 0) goto L_0x0199
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshContent r3 = r3.mRefreshContent
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r4 = r4.mHeaderTranslationViewId
                com.scwang.smart.refresh.layout.SmartRefreshLayout r5 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r5 = r5.mFooterTranslationViewId
                r3.moveSpinner(r2, r4, r5)
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r3 = r3.mFooterNoMoreData
                if (r3 == 0) goto L_0x013c
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r3 = r3.mFooterNoMoreDataEffective
                if (r3 == 0) goto L_0x013c
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r3 = r3.mEnableFooterFollowWhenNoMoreData
                if (r3 == 0) goto L_0x013c
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r3.mRefreshFooter
                boolean r3 = r3 instanceof com.scwang.smart.refresh.layout.api.RefreshFooter
                if (r3 == 0) goto L_0x013c
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r3.mRefreshFooter
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r3 = r3.getSpinnerStyle()
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r4 = com.scwang.smart.refresh.layout.constant.SpinnerStyle.Translate
                if (r3 != r4) goto L_0x013c
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r4 = r3.mEnableLoadMore
                boolean r3 = r3.isEnableRefreshOrLoadMore(r4)
                if (r3 == 0) goto L_0x013c
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r3.mRefreshFooter
                android.view.View r3 = r3.getView()
                int r4 = java.lang.Math.max(r12, r2)
                float r4 = (float) r4
                r3.setTranslationY(r4)
            L_0x013c:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r3 = r3.mEnableClipHeaderWhenFixedBehind
                if (r3 == 0) goto L_0x0156
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r3.mRefreshHeader
                if (r3 == 0) goto L_0x0156
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r3.mRefreshHeader
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r3 = r3.getSpinnerStyle()
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r4 = com.scwang.smart.refresh.layout.constant.SpinnerStyle.FixedBehind
                if (r3 != r4) goto L_0x0156
                r3 = 1
                goto L_0x0157
            L_0x0156:
                r3 = 0
            L_0x0157:
                if (r3 != 0) goto L_0x0162
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r3 = r3.mHeaderBackgroundColor
                if (r3 == 0) goto L_0x0160
                goto L_0x0162
            L_0x0160:
                r3 = 0
                goto L_0x0163
            L_0x0162:
                r3 = 1
            L_0x0163:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r4 = r4.mEnableClipFooterWhenFixedBehind
                if (r4 == 0) goto L_0x017d
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r4 = r4.mRefreshFooter
                if (r4 == 0) goto L_0x017d
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r4 = r4.mRefreshFooter
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r4 = r4.getSpinnerStyle()
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r5 = com.scwang.smart.refresh.layout.constant.SpinnerStyle.FixedBehind
                if (r4 != r5) goto L_0x017d
                r4 = 1
                goto L_0x017e
            L_0x017d:
                r4 = 0
            L_0x017e:
                if (r4 != 0) goto L_0x0189
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r4 = r4.mFooterBackgroundColor
                if (r4 == 0) goto L_0x0187
                goto L_0x0189
            L_0x0187:
                r4 = 0
                goto L_0x018a
            L_0x0189:
                r4 = 1
            L_0x018a:
                if (r3 == 0) goto L_0x0190
                if (r2 >= 0) goto L_0x0196
                if (r10 > 0) goto L_0x0196
            L_0x0190:
                if (r4 == 0) goto L_0x0199
                if (r2 <= 0) goto L_0x0196
                if (r10 >= 0) goto L_0x0199
            L_0x0196:
                r9.invalidate()
            L_0x0199:
                r13 = 1065353216(0x3f800000, float:1.0)
                r14 = 1073741824(0x40000000, float:2.0)
                if (r1 >= 0) goto L_0x01a1
                if (r10 <= 0) goto L_0x02dc
            L_0x01a1:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                if (r2 == 0) goto L_0x02dc
                int r8 = java.lang.Math.max(r1, r12)
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r15 = r2.mHeaderHeight
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mHeaderHeight
                float r2 = (float) r2
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                float r3 = r3.mHeaderMaxDragRate
                float r2 = r2 * r3
                int r7 = (int) r2
                float r2 = (float) r8
                float r2 = r2 * r13
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r3 = r3.mHeaderHeight
                if (r3 != 0) goto L_0x01c6
                r3 = 1
                goto L_0x01ca
            L_0x01c6:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r3 = r3.mHeaderHeight
            L_0x01ca:
                float r3 = (float) r3
                float r16 = r2 / r3
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r3 = r2.mEnableRefresh
                boolean r2 = r2.isEnableRefreshOrLoadMore(r3)
                if (r2 != 0) goto L_0x01e6
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r2 = r2.mState
                com.scwang.smart.refresh.layout.constant.RefreshState r3 = com.scwang.smart.refresh.layout.constant.RefreshState.RefreshFinish
                if (r2 != r3) goto L_0x01e2
                if (r20 != 0) goto L_0x01e2
                goto L_0x01e6
            L_0x01e2:
                r17 = r7
                goto L_0x02b3
            L_0x01e6:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                if (r10 == r2) goto L_0x028a
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r2 = r2.getSpinnerStyle()
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r3 = com.scwang.smart.refresh.layout.constant.SpinnerStyle.Translate
                if (r2 != r3) goto L_0x0226
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                android.view.View r2 = r2.getView()
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r3 = r3.mSpinner
                float r3 = (float) r3
                r2.setTranslationY(r3)
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mHeaderBackgroundColor
                if (r2 == 0) goto L_0x027a
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                android.graphics.Paint r2 = r2.mPaint
                if (r2 == 0) goto L_0x027a
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r3 = r2.mEnableHeaderTranslationContent
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r4 = r4.mRefreshHeader
                boolean r2 = r2.isEnableTranslationContent(r3, r4)
                if (r2 != 0) goto L_0x027a
                r9.invalidate()
                goto L_0x027a
            L_0x0226:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r2 = r2.getSpinnerStyle()
                boolean r2 = r2.scale
                if (r2 == 0) goto L_0x027a
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                android.view.View r2 = r2.getView()
                android.view.ViewGroup$LayoutParams r3 = r2.getLayoutParams()
                boolean r4 = r3 instanceof android.view.ViewGroup.MarginLayoutParams
                if (r4 == 0) goto L_0x0245
                android.view.ViewGroup$MarginLayoutParams r3 = (android.view.ViewGroup.MarginLayoutParams) r3
                goto L_0x0247
            L_0x0245:
                android.view.ViewGroup$MarginLayoutParams r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.sDefaultMarginLP
            L_0x0247:
                int r4 = r2.getMeasuredWidth()
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r14)
                com.scwang.smart.refresh.layout.SmartRefreshLayout r5 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r5 = r5.mSpinner
                int r6 = r3.bottomMargin
                int r5 = r5 - r6
                int r6 = r3.topMargin
                int r5 = r5 - r6
                int r5 = java.lang.Math.max(r5, r12)
                int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r14)
                r2.measure(r4, r5)
                int r4 = r3.leftMargin
                int r3 = r3.topMargin
                com.scwang.smart.refresh.layout.SmartRefreshLayout r5 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r5 = r5.mHeaderInsetStart
                int r3 = r3 + r5
                int r5 = r2.getMeasuredWidth()
                int r5 = r5 + r4
                int r6 = r2.getMeasuredHeight()
                int r6 = r6 + r3
                r2.layout(r4, r3, r5, r6)
            L_0x027a:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                r3 = r20
                r4 = r16
                r5 = r8
                r6 = r15
                r17 = r7
                r2.onMoving(r3, r4, r5, r6, r7)
                goto L_0x028c
            L_0x028a:
                r17 = r7
            L_0x028c:
                if (r20 == 0) goto L_0x02b3
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                boolean r2 = r2.isSupportHorizontalDrag()
                if (r2 == 0) goto L_0x02b3
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                float r2 = r2.mLastTouchX
                int r2 = (int) r2
                int r3 = r9.getWidth()
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                float r4 = r4.mLastTouchX
                if (r3 != 0) goto L_0x02a9
                r5 = 1
                goto L_0x02aa
            L_0x02a9:
                r5 = r3
            L_0x02aa:
                float r5 = (float) r5
                float r4 = r4 / r5
                com.scwang.smart.refresh.layout.SmartRefreshLayout r5 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r5 = r5.mRefreshHeader
                r5.onHorizontalDrag(r4, r2, r3)
            L_0x02b3:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                if (r10 == r2) goto L_0x02dc
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.listener.OnMultiListener r2 = r2.mOnMultiListener
                if (r2 == 0) goto L_0x02dc
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshHeader
                boolean r2 = r2 instanceof com.scwang.smart.refresh.layout.api.RefreshHeader
                if (r2 == 0) goto L_0x02dc
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.listener.OnMultiListener r2 = r2.mOnMultiListener
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r3.mRefreshHeader
                com.scwang.smart.refresh.layout.api.RefreshHeader r3 = (com.scwang.smart.refresh.layout.api.RefreshHeader) r3
                r4 = r20
                r5 = r16
                r6 = r8
                r7 = r15
                r8 = r17
                r2.onHeaderMoving(r3, r4, r5, r6, r7, r8)
            L_0x02dc:
                if (r1 <= 0) goto L_0x02e0
                if (r10 >= 0) goto L_0x0416
            L_0x02e0:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshFooter
                if (r2 == 0) goto L_0x0416
                int r1 = java.lang.Math.min(r1, r12)
                int r7 = -r1
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r8 = r1.mFooterHeight
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r1 = r1.mFooterHeight
                float r1 = (float) r1
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                float r2 = r2.mFooterMaxDragRate
                float r1 = r1 * r2
                int r15 = (int) r1
                float r1 = (float) r7
                float r1 = r1 * r13
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mFooterHeight
                if (r2 != 0) goto L_0x0306
                r2 = 1
                goto L_0x030a
            L_0x0306:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mFooterHeight
            L_0x030a:
                float r2 = (float) r2
                float r13 = r1 / r2
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r2 = r1.mEnableLoadMore
                boolean r1 = r1.isEnableRefreshOrLoadMore(r2)
                if (r1 != 0) goto L_0x0321
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.constant.RefreshState r1 = r1.mState
                com.scwang.smart.refresh.layout.constant.RefreshState r2 = com.scwang.smart.refresh.layout.constant.RefreshState.LoadFinish
                if (r1 != r2) goto L_0x03ef
                if (r20 != 0) goto L_0x03ef
            L_0x0321:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r1 = r1.mSpinner
                if (r10 == r1) goto L_0x03c9
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r1 = r1.mRefreshFooter
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r1 = r1.getSpinnerStyle()
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r2 = com.scwang.smart.refresh.layout.constant.SpinnerStyle.Translate
                if (r1 != r2) goto L_0x0361
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r1 = r1.mRefreshFooter
                android.view.View r1 = r1.getView()
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                float r2 = (float) r2
                r1.setTranslationY(r2)
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r1 = r1.mFooterBackgroundColor
                if (r1 == 0) goto L_0x03bc
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                android.graphics.Paint r1 = r1.mPaint
                if (r1 == 0) goto L_0x03bc
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                boolean r2 = r1.mEnableFooterTranslationContent
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r3 = r3.mRefreshFooter
                boolean r1 = r1.isEnableTranslationContent(r2, r3)
                if (r1 != 0) goto L_0x03bc
                r9.invalidate()
                goto L_0x03bc
            L_0x0361:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r1 = r1.mRefreshFooter
                com.scwang.smart.refresh.layout.constant.SpinnerStyle r1 = r1.getSpinnerStyle()
                boolean r1 = r1.scale
                if (r1 == 0) goto L_0x03bc
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r1 = r1.mRefreshFooter
                android.view.View r1 = r1.getView()
                android.view.ViewGroup$LayoutParams r2 = r1.getLayoutParams()
                boolean r3 = r2 instanceof android.view.ViewGroup.MarginLayoutParams
                if (r3 == 0) goto L_0x0380
                android.view.ViewGroup$MarginLayoutParams r2 = (android.view.ViewGroup.MarginLayoutParams) r2
                goto L_0x0382
            L_0x0380:
                android.view.ViewGroup$MarginLayoutParams r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.sDefaultMarginLP
            L_0x0382:
                int r3 = r1.getMeasuredWidth()
                int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r14)
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r4 = r4.mSpinner
                int r4 = -r4
                int r5 = r2.bottomMargin
                int r4 = r4 - r5
                int r5 = r2.topMargin
                int r4 = r4 - r5
                int r4 = java.lang.Math.max(r4, r12)
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r14)
                r1.measure(r3, r4)
                int r3 = r2.leftMargin
                int r2 = r2.topMargin
                int r4 = r9.getMeasuredHeight()
                int r2 = r2 + r4
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r4 = r4.mFooterInsetStart
                int r2 = r2 - r4
                int r4 = r1.getMeasuredHeight()
                int r4 = r2 - r4
                int r5 = r1.getMeasuredWidth()
                int r5 = r5 + r3
                r1.layout(r3, r4, r5, r2)
            L_0x03bc:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r1 = r1.mRefreshFooter
                r2 = r20
                r3 = r13
                r4 = r7
                r5 = r8
                r6 = r15
                r1.onMoving(r2, r3, r4, r5, r6)
            L_0x03c9:
                if (r20 == 0) goto L_0x03ef
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r1 = r1.mRefreshFooter
                boolean r1 = r1.isSupportHorizontalDrag()
                if (r1 == 0) goto L_0x03ef
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                float r1 = r1.mLastTouchX
                int r1 = (int) r1
                int r2 = r9.getWidth()
                com.scwang.smart.refresh.layout.SmartRefreshLayout r3 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                float r3 = r3.mLastTouchX
                if (r2 != 0) goto L_0x03e5
                goto L_0x03e6
            L_0x03e5:
                r11 = r2
            L_0x03e6:
                float r4 = (float) r11
                float r3 = r3 / r4
                com.scwang.smart.refresh.layout.SmartRefreshLayout r4 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r4 = r4.mRefreshFooter
                r4.onHorizontalDrag(r3, r1, r2)
            L_0x03ef:
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                int r1 = r1.mSpinner
                if (r10 == r1) goto L_0x0416
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.listener.OnMultiListener r1 = r1.mOnMultiListener
                if (r1 == 0) goto L_0x0416
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r1 = r1.mRefreshFooter
                boolean r1 = r1 instanceof com.scwang.smart.refresh.layout.api.RefreshFooter
                if (r1 == 0) goto L_0x0416
                com.scwang.smart.refresh.layout.SmartRefreshLayout r1 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.listener.OnMultiListener r1 = r1.mOnMultiListener
                com.scwang.smart.refresh.layout.SmartRefreshLayout r2 = com.scwang.smart.refresh.layout.SmartRefreshLayout.this
                com.scwang.smart.refresh.layout.api.RefreshComponent r2 = r2.mRefreshFooter
                com.scwang.smart.refresh.layout.api.RefreshFooter r2 = (com.scwang.smart.refresh.layout.api.RefreshFooter) r2
                r3 = r20
                r4 = r13
                r5 = r7
                r6 = r8
                r7 = r15
                r1.onFooterMoving(r2, r3, r4, r5, r6, r7)
            L_0x0416:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.scwang.smart.refresh.layout.SmartRefreshLayout.RefreshKernelImpl.moveSpinner(int, boolean):com.scwang.smart.refresh.layout.api.RefreshKernel");
        }

        public ValueAnimator animSpinner(int i) {
            SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
            return smartRefreshLayout.animSpinner(i, 0, smartRefreshLayout.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
        }

        public RefreshKernel requestDrawBackgroundFor(@NonNull RefreshComponent refreshComponent, int i) {
            if (SmartRefreshLayout.this.mPaint == null && i != 0) {
                SmartRefreshLayout.this.mPaint = new Paint();
            }
            if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                SmartRefreshLayout.this.mHeaderBackgroundColor = i;
            } else if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshFooter)) {
                SmartRefreshLayout.this.mFooterBackgroundColor = i;
            }
            return this;
        }

        public RefreshKernel requestNeedTouchEventFor(@NonNull RefreshComponent refreshComponent, boolean z) {
            if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                SmartRefreshLayout.this.mHeaderNeedTouchEventWhenRefreshing = z;
            } else if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshFooter)) {
                SmartRefreshLayout.this.mFooterNeedTouchEventWhenLoading = z;
            }
            return this;
        }

        public RefreshKernel requestDefaultTranslationContentFor(@NonNull RefreshComponent refreshComponent, boolean z) {
            if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                if (!SmartRefreshLayout.this.mManualHeaderTranslationContent) {
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mManualHeaderTranslationContent = true;
                    smartRefreshLayout.mEnableHeaderTranslationContent = z;
                }
            } else if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshFooter) && !SmartRefreshLayout.this.mManualFooterTranslationContent) {
                SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                smartRefreshLayout2.mManualFooterTranslationContent = true;
                smartRefreshLayout2.mEnableFooterTranslationContent = z;
            }
            return this;
        }

        public RefreshKernel requestRemeasureHeightFor(@NonNull RefreshComponent refreshComponent) {
            if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                if (SmartRefreshLayout.this.mHeaderHeightStatus.notified) {
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mHeaderHeightStatus = smartRefreshLayout.mHeaderHeightStatus.unNotify();
                }
            } else if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshFooter) && SmartRefreshLayout.this.mFooterHeightStatus.notified) {
                SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                smartRefreshLayout2.mFooterHeightStatus = smartRefreshLayout2.mFooterHeightStatus.unNotify();
            }
            return this;
        }

        public RefreshKernel requestFloorDuration(int i) {
            SmartRefreshLayout.this.mFloorDuration = i;
            return this;
        }

        public RefreshKernel requestFloorBottomPullUpToCloseRate(float f) {
            SmartRefreshLayout.this.mTwoLevelBottomPullUpToCloseRate = f;
            return this;
        }
    }
}
