package com.huayu.tzc.customview.ruler;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.TypedValue;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.EdgeEffect;
import android.widget.OverScroller;

public abstract class AbstractInnerRuler extends View {
    protected static final int MIN_SCROLLER_DP = 1;
    protected static final int SCALE_TO_PX_FACTOR = 100;
    protected Paint mBigScalePaint;
    protected Context mContext;
    protected int mCount = 10;
    protected float mCurrentScale = 0.0f;
    protected int mDrawOffset;
    protected int mEdgeLength;
    protected EdgeEffect mEndEdgeEffect;
    protected int mLength;
    protected int mMaxLength = 0;
    protected int mMaxPosition = 0;
    protected int mMaximumVelocity;
    protected int mMinPosition = 0;
    protected int mMinimumVelocity;
    protected OverScroller mOverScroller;
    protected RuleLayout mParent;
    protected Paint mPointerPaint;
    protected RulerCallback mRulerCallback;
    protected Paint mSmallScalePaint;
    protected EdgeEffect mStartEdgeEffect;
    protected Paint mTextPaint;
    protected VelocityTracker mVelocityTracker;
    protected float minScrollerPx = 1.0f;
    private float value = 0.001f;

    /* access modifiers changed from: protected */
    public abstract void goToScale(float f);

    public abstract void refreshSize();

    /* access modifiers changed from: protected */
    public abstract void scrollBackToCurrentScale();

    /* access modifiers changed from: protected */
    public abstract void scrollBackToCurrentScale(int i);

    public AbstractInnerRuler(Context context, RuleLayout ruleLayout) {
        super(context);
        this.mParent = ruleLayout;
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        this.mMaxLength = this.mParent.getMaxScale() - this.mParent.getMinScale();
        this.mCurrentScale = this.mParent.getCurrentScale();
        this.mCount = this.mParent.getCount();
        this.mDrawOffset = (this.mCount * this.mParent.getInterval()) / 2;
        this.minScrollerPx = TypedValue.applyDimension(1, 1.0f, context.getResources().getDisplayMetrics());
        initPaints();
        this.mOverScroller = new OverScroller(this.mContext);
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        initEdgeEffects();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                AbstractInnerRuler.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                AbstractInnerRuler abstractInnerRuler = AbstractInnerRuler.this;
                abstractInnerRuler.goToScale(abstractInnerRuler.mCurrentScale);
            }
        });
        setLayerType(0, (Paint) null);
    }

    private void initPaints() {
        this.mSmallScalePaint = new Paint();
        this.mSmallScalePaint.setStrokeWidth((float) this.mParent.getSmallScaleWidth());
        this.mSmallScalePaint.setColor(this.mParent.getScaleColor());
        this.mSmallScalePaint.setStrokeCap(Paint.Cap.ROUND);
        this.mBigScalePaint = new Paint();
        this.mBigScalePaint.setColor(this.mParent.getScaleColor());
        this.mBigScalePaint.setStrokeWidth((float) this.mParent.getBigScaleWidth());
        this.mBigScalePaint.setStrokeCap(Paint.Cap.ROUND);
        this.mTextPaint = new Paint();
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setColor(this.mParent.getTextColor());
        this.mTextPaint.setTextSize((float) this.mParent.getTextSize());
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mPointerPaint = new Paint();
        this.mPointerPaint.setAntiAlias(true);
        this.mPointerPaint.setColor(Color.parseColor("#3983FF"));
    }

    public void initEdgeEffects() {
        if (!this.mParent.canEdgeEffect()) {
            return;
        }
        if (this.mStartEdgeEffect == null || this.mEndEdgeEffect == null) {
            this.mStartEdgeEffect = new EdgeEffect(this.mContext);
            this.mEndEdgeEffect = new EdgeEffect(this.mContext);
            if (Build.VERSION.SDK_INT >= 21) {
                this.mStartEdgeEffect.setColor(this.mParent.getEdgeColor());
                this.mEndEdgeEffect.setColor(this.mParent.getEdgeColor());
            }
            this.mEdgeLength = this.mParent.getInterval() * this.mParent.getCount();
        }
    }

    public void computeScroll() {
        if (this.mOverScroller.computeScrollOffset()) {
            scrollTo(this.mOverScroller.getCurrX(), this.mOverScroller.getCurrY());
            if (!this.mOverScroller.computeScrollOffset()) {
                int round = Math.round(this.mCurrentScale);
                if (Math.abs(this.mCurrentScale - ((float) round)) > this.value) {
                    scrollBackToCurrentScale(round);
                }
            }
            postInvalidate();
        }
    }

    public void setRulerCallback(RulerCallback rulerCallback) {
        this.mRulerCallback = rulerCallback;
    }

    public float getCurrentScale() {
        return this.mCurrentScale;
    }

    public void setCurrentScale(float f) {
        this.mCurrentScale = f;
        goToScale(this.mCurrentScale);
    }
}
