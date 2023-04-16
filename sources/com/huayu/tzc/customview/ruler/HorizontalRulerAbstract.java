package com.huayu.tzc.customview.ruler;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import androidx.annotation.C0041Px;
import com.baidubce.http.StatusCodes;

public class HorizontalRulerAbstract extends AbstractInnerRuler {
    private final String TAG = "ruler";
    protected int mHalfWidth = 0;
    private float mLastX = 0.0f;

    public HorizontalRulerAbstract(Context context) {
        super(context, (RuleLayout) null);
    }

    public HorizontalRulerAbstract(Context context, RuleLayout ruleLayout) {
        super(context, ruleLayout);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        ViewGroup viewGroup = (ViewGroup) getParent();
        int action = motionEvent.getAction();
        if (action == 0) {
            if (!this.mOverScroller.isFinished()) {
                this.mOverScroller.abortAnimation();
            }
            this.mLastX = x;
            viewGroup.requestDisallowInterceptTouchEvent(true);
        } else if (action == 1) {
            this.mVelocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
            int xVelocity = (int) this.mVelocityTracker.getXVelocity();
            if (Math.abs(xVelocity) > this.mMinimumVelocity) {
                fling(-xVelocity);
            } else {
                scrollBackToCurrentScale();
            }
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
            }
            releaseEdgeEffects();
            viewGroup.requestDisallowInterceptTouchEvent(false);
        } else if (action == 2) {
            this.mLastX = x;
            scrollBy((int) (this.mLastX - x), 0);
        } else if (action == 3) {
            if (!this.mOverScroller.isFinished()) {
                this.mOverScroller.abortAnimation();
            }
            scrollBackToCurrentScale();
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
            }
            releaseEdgeEffects();
            viewGroup.requestDisallowInterceptTouchEvent(false);
        }
        return true;
    }

    private void fling(int i) {
        this.mOverScroller.fling(getScrollX(), 0, i, 0, this.mMinPosition - this.mEdgeLength, this.mMaxPosition + this.mEdgeLength, 0, 0);
        invalidate();
    }

    public void scrollTo(@C0041Px int i, @C0041Px int i2) {
        Log.i("ruler", "scrollTo x: " + i);
        if (i < this.mMinPosition) {
            goStartEdgeEffect(i);
            i = this.mMinPosition;
        }
        if (i > this.mMaxPosition) {
            goEndEdgeEffect(i);
            i = this.mMaxPosition;
        }
        if (i != getScrollX()) {
            super.scrollTo(i, i2);
        }
        this.mCurrentScale = scrollXtoScale(i);
        if (this.mRulerCallback != null) {
            this.mRulerCallback.onScaleChanging((float) Math.round(this.mCurrentScale));
        }
    }

    private void goStartEdgeEffect(int i) {
        if (this.mParent.canEdgeEffect()) {
            if (!this.mOverScroller.isFinished()) {
                this.mStartEdgeEffect.onAbsorb((int) this.mOverScroller.getCurrVelocity());
                this.mOverScroller.abortAnimation();
            } else {
                this.mStartEdgeEffect.onPull(((((float) (this.mMinPosition - i)) / ((float) this.mEdgeLength)) * 3.0f) + 0.3f);
                this.mStartEdgeEffect.setSize(0, getWidth());
            }
            postInvalidateOnAnimation();
        }
    }

    private void goEndEdgeEffect(int i) {
        if (this.mParent.canEdgeEffect()) {
            if (!this.mOverScroller.isFinished()) {
                this.mEndEdgeEffect.onAbsorb((int) this.mOverScroller.getCurrVelocity());
                this.mOverScroller.abortAnimation();
            } else {
                this.mEndEdgeEffect.onPull(((((float) (i - this.mMaxPosition)) / ((float) this.mEdgeLength)) * 3.0f) + 0.3f);
                this.mEndEdgeEffect.setSize(0, getWidth());
            }
            postInvalidateOnAnimation();
        }
    }

    private void releaseEdgeEffects() {
        if (this.mParent.canEdgeEffect()) {
            this.mStartEdgeEffect.onRelease();
            this.mEndEdgeEffect.onRelease();
        }
    }

    public void goToScale(float f) {
        this.mCurrentScale = (float) Math.round(f);
        scrollTo(scaleToScrollX(this.mCurrentScale), 0);
    }

    private float scrollXtoScale(int i) {
        return ((((float) (i - this.mMinPosition)) / ((float) this.mLength)) * ((float) this.mMaxLength)) + ((float) this.mParent.getMinScale());
    }

    private int scaleToScrollX(float f) {
        return (int) ((((f - ((float) this.mParent.getMinScale())) / ((float) this.mMaxLength)) * ((float) this.mLength)) + ((float) this.mMinPosition));
    }

    private float scaleToScrollFloatX(float f) {
        return (((f - ((float) this.mParent.getMinScale())) / ((float) this.mMaxLength)) * ((float) this.mLength) * 100.0f) + ((float) (this.mMinPosition * 100));
    }

    /* access modifiers changed from: protected */
    public void scrollBackToCurrentScale() {
        scrollBackToCurrentScale(Math.round(this.mCurrentScale));
    }

    /* access modifiers changed from: protected */
    public void scrollBackToCurrentScale(int i) {
        int round = Math.round((scaleToScrollFloatX((float) i) - ((float) (getScrollX() * 100))) / 100.0f);
        if (((float) round) > this.minScrollerPx) {
            this.mOverScroller.startScroll(getScrollX(), getScrollY(), round, 0, StatusCodes.INTERNAL_ERROR);
            invalidate();
            return;
        }
        scrollBy(round, 0);
    }

    public void refreshSize() {
        this.mLength = (this.mParent.getMaxScale() - this.mParent.getMinScale()) * this.mParent.getInterval();
        this.mHalfWidth = getWidth() / 2;
        this.mMinPosition = -this.mHalfWidth;
        this.mMaxPosition = this.mLength - this.mHalfWidth;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        refreshSize();
    }
}
