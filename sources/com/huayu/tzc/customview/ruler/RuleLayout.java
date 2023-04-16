package com.huayu.tzc.customview.ruler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import com.huayu.tzc.C2128R;

public class RuleLayout extends ViewGroup {
    private boolean isShowText = true;
    private AbstractInnerRuler mAbstractInnerRuler;
    private int mBigScaleLength = 60;
    private int mBigScaleWidth = 5;
    private boolean mCanEdgeEffect = true;
    private Context mContext;
    private int mCount = 10;
    private float mCurrentScale = 0.0f;
    @ColorInt
    private int mEdgeColor = Color.parseColor("#00000000");
    private float mFactor = 0.1f;
    private int mInterval = 18;
    private int mMaxScale = 2000;
    private int mMinScale = 464;
    private int mOutLineWidth = 0;
    private int mPaddingBottom = 0;
    private int mPaddingLeft = 0;
    private int mPaddingRight = 0;
    private int mPaddingStartAndEnd = 0;
    private int mPaddingTop = 0;
    private Drawable mRulerBackGround;
    private int mRulerBackGroundColor = Color.parseColor("#00000000");
    @ColorInt
    private int mScaleColor = getResources().getColor(C2128R.C2129color.color_9E9E9E);
    private int mSmallScaleLength = 30;
    private int mSmallScaleWidth = 3;
    @ColorInt
    private int mTextColor = getResources().getColor(C2128R.C2129color.color_white);
    private int mTextMarginHead = 120;
    private int mTextSize = 28;
    private Path path = new Path();

    public RuleLayout(Context context) {
        super(context);
        initRuler(context);
    }

    public RuleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttrs(context, attributeSet);
        initRuler(context);
    }

    public RuleLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttrs(context, attributeSet);
        initRuler(context);
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2128R.styleable.RuleLayout, 0, 0);
        this.mMinScale = obtainStyledAttributes.getInteger(9, this.mMinScale);
        this.mMaxScale = obtainStyledAttributes.getInteger(8, this.mMaxScale);
        this.mSmallScaleWidth = obtainStyledAttributes.getDimensionPixelSize(18, this.mSmallScaleWidth);
        this.mSmallScaleLength = obtainStyledAttributes.getDimensionPixelSize(17, this.mSmallScaleLength);
        this.mBigScaleWidth = obtainStyledAttributes.getDimensionPixelSize(1, this.mBigScaleWidth);
        this.mBigScaleLength = obtainStyledAttributes.getDimensionPixelSize(0, this.mBigScaleLength);
        this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(11, this.mTextSize);
        this.mTextMarginHead = obtainStyledAttributes.getDimensionPixelSize(19, this.mTextMarginHead);
        this.mInterval = obtainStyledAttributes.getDimensionPixelSize(16, this.mInterval);
        this.mTextColor = obtainStyledAttributes.getColor(10, this.mTextColor);
        this.mScaleColor = obtainStyledAttributes.getColor(15, this.mScaleColor);
        this.mCurrentScale = obtainStyledAttributes.getFloat(4, (float) ((this.mMaxScale + this.mMinScale) / 2));
        this.mCount = obtainStyledAttributes.getInt(3, this.mCount);
        this.mPaddingStartAndEnd = obtainStyledAttributes.getDimensionPixelSize(13, this.mPaddingStartAndEnd);
        this.mRulerBackGround = obtainStyledAttributes.getDrawable(14);
        if (this.mRulerBackGround == null) {
            this.mRulerBackGroundColor = obtainStyledAttributes.getColor(14, this.mRulerBackGroundColor);
        }
        this.mCanEdgeEffect = obtainStyledAttributes.getBoolean(2, this.mCanEdgeEffect);
        this.mEdgeColor = obtainStyledAttributes.getColor(5, this.mEdgeColor);
        this.mFactor = obtainStyledAttributes.getFloat(6, this.mFactor);
        this.mOutLineWidth = obtainStyledAttributes.getDimensionPixelOffset(12, this.mOutLineWidth);
        this.isShowText = obtainStyledAttributes.getBoolean(7, true);
        obtainStyledAttributes.recycle();
    }

    private void initRuler(Context context) {
        this.mContext = context;
        this.mAbstractInnerRuler = new TopHeadRulerAbstract(context, this);
        paddingHorizontal();
        this.mAbstractInnerRuler.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        addView(this.mAbstractInnerRuler);
        setWillNotDraw(false);
        initRulerBackground();
    }

    private void initRulerBackground() {
        Drawable drawable = this.mRulerBackGround;
        if (drawable != null) {
            this.mAbstractInnerRuler.setBackground(drawable);
        } else {
            this.mAbstractInnerRuler.setBackgroundColor(this.mRulerBackGroundColor);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            this.mAbstractInnerRuler.layout(this.mPaddingLeft, this.mPaddingTop, (i3 - i) - this.mPaddingRight, (i4 - i2) - this.mPaddingBottom);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.path.moveTo(getPointerX() - 20.0f, 0.0f);
        this.path.lineTo(getPointerX() + 20.0f, 0.0f);
        this.path.lineTo(getPointerX(), 30.0f);
        this.path.close();
        canvas.drawPath(this.path, this.mAbstractInnerRuler.mPointerPaint);
    }

    private void paddingHorizontal() {
        int i = this.mPaddingStartAndEnd;
        this.mPaddingLeft = i;
        this.mPaddingRight = i;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
    }

    private void paddingVertical() {
        int i = this.mPaddingStartAndEnd;
        this.mPaddingTop = i;
        this.mPaddingBottom = i;
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
    }

    public void setCallback(RulerCallback rulerCallback) {
        this.mAbstractInnerRuler.setRulerCallback(rulerCallback);
    }

    public void refreshRuler() {
        this.mAbstractInnerRuler.init(this.mContext);
        this.mAbstractInnerRuler.refreshSize();
    }

    public int getEdgeColor() {
        return this.mEdgeColor;
    }

    public void setCanEdgeEffect(boolean z) {
        this.mCanEdgeEffect = z;
    }

    public float getFactor() {
        return this.mFactor;
    }

    public void setFactor(float f) {
        this.mFactor = f;
        this.mAbstractInnerRuler.postInvalidate();
    }

    public float getOutLineWidth() {
        return (float) this.mOutLineWidth;
    }

    public void setOutLineWidth(int i) {
        this.mOutLineWidth = i;
        this.mAbstractInnerRuler.postInvalidate();
    }

    public boolean canEdgeEffect() {
        return this.mCanEdgeEffect;
    }

    public float getCurrentScale() {
        return this.mCurrentScale;
    }

    public void setCurrentScale(float f) {
        Log.e("s", "setCurrentScale: " + f);
        this.mCurrentScale = f;
        this.mAbstractInnerRuler.setCurrentScale(f);
    }

    public int getMinScale() {
        return this.mMinScale;
    }

    public void setMinScale(int i) {
        this.mMinScale = i;
    }

    public int getMaxScale() {
        return this.mMaxScale;
    }

    public void setMaxScale(int i) {
        this.mMaxScale = i;
    }

    public int getBigScaleLength() {
        return this.mBigScaleLength;
    }

    public void setBigScaleLength(int i) {
        this.mBigScaleLength = i;
    }

    public int getBigScaleWidth() {
        return this.mBigScaleWidth;
    }

    public void setBigScaleWidth(int i) {
        this.mBigScaleWidth = i;
    }

    public int getSmallScaleLength() {
        return this.mSmallScaleLength;
    }

    public void setSmallScaleLength(int i) {
        this.mSmallScaleLength = i;
    }

    public int getSmallScaleWidth() {
        return this.mSmallScaleWidth;
    }

    public void setSmallScaleWidth(int i) {
        this.mSmallScaleWidth = i;
    }

    public void setTextMarginTop(int i) {
        this.mTextMarginHead = i;
    }

    public int getTextMarginHead() {
        return this.mTextMarginHead;
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public void setTextSize(int i) {
        this.mTextSize = i;
    }

    public int getInterval() {
        return this.mInterval;
    }

    public void setInterval(int i) {
        this.mInterval = i;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public int getScaleColor() {
        return this.mScaleColor;
    }

    public int getCount() {
        return this.mCount;
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public boolean isShowText() {
        return this.isShowText;
    }

    public void setShowText(boolean z) {
        this.isShowText = z;
    }

    public float getPointerX() {
        return (float) (getWidth() / 2);
    }
}
