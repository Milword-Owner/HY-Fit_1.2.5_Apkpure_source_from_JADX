package com.previewlibrary.wight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.previewlibrary.C2517R;

public class BezierBannerView extends View implements ViewPager.OnPageChangeListener {
    public static int DIRECTION_LEFT = 1;
    public static int DIRECTION_RIGHT = 2;
    private static final String TAG = "com.previewlibrary.wight.BezierBannerView";
    private int MOVE_STEP_ONE;
    private int MOVE_STEP_TWO;
    Interpolator accelerateinterpolator;
    private boolean autoMove;
    float controlPointX;
    float controlPointY;
    private int count;
    private float distance;
    float endPointX;
    float endPointY;
    float mCenterPointX;
    float mCenterPointY;
    private float mChangeBgRadius;
    private float mChangeRadius;
    private Paint mCirclePaint;
    private Paint mCirclePaint2;
    private int mDrection;
    private float mNomarlRadius;
    private float mOriginProgress;
    private Path mPath;
    private Path mPath2;
    private float mProgress;
    private float mProgress2;
    private float mRadius;
    private int mSelectedColor;
    private int mSelectedIndex;
    float mStartX;
    float mStartY;
    private float mSupportChangeRadius;
    float mSupportCircleX;
    float mSupportCircleY;
    private float mSupport_Next_ChangeRadius;
    float mSupport_next_centerX;
    float mSupport_next_centerY;
    private int mUnSelectedColor;
    float mbgNextPointX;
    float mbgNextPointY;

    public float getValue(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageSelected(int i) {
    }

    public BezierBannerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BezierBannerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BezierBannerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPath = new Path();
        this.mPath2 = new Path();
        this.distance = 80.0f;
        this.mRadius = 30.0f;
        this.mNomarlRadius = 20.0f;
        this.autoMove = false;
        this.mProgress = 0.0f;
        this.mProgress2 = 0.0f;
        this.mSelectedIndex = 0;
        this.MOVE_STEP_ONE = 1;
        this.MOVE_STEP_TWO = 2;
        this.accelerateinterpolator = new AccelerateDecelerateInterpolator();
        initattrs(attributeSet);
        initPaint();
    }

    private void initPaint() {
        Paint paint = new Paint(1);
        paint.setColor(this.mSelectedColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        this.mCirclePaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setColor(this.mUnSelectedColor);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint2.setDither(true);
        this.mCirclePaint2 = paint2;
    }

    private void initattrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C2517R.styleable.BezierBannerView);
        this.mSelectedColor = obtainStyledAttributes.getColor(C2517R.styleable.BezierBannerView_selectedColor, -1);
        this.mUnSelectedColor = obtainStyledAttributes.getColor(C2517R.styleable.BezierBannerView_unSelectedColor, -5592406);
        this.mRadius = obtainStyledAttributes.getDimension(C2517R.styleable.BezierBannerView_selectedRaduis, this.mRadius);
        this.mNomarlRadius = obtainStyledAttributes.getDimension(C2517R.styleable.BezierBannerView_unSelectedRaduis, this.mNomarlRadius);
        this.distance = obtainStyledAttributes.getDimension(C2517R.styleable.BezierBannerView_spacing, this.distance);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        float f = this.mNomarlRadius;
        int i3 = this.count;
        int paddingLeft = (int) ((f * 2.0f * ((float) i3)) + ((this.mRadius - f) * 2.0f) + (((float) (i3 - 1)) * this.distance) + ((float) getPaddingLeft()) + ((float) getPaddingRight()));
        int paddingTop = (int) ((this.mRadius * 2.0f) + ((float) getPaddingTop()) + ((float) getPaddingBottom()));
        if (mode != 1073741824 && mode == Integer.MIN_VALUE) {
            size = Math.min(size, paddingLeft);
        }
        if (mode2 != 1073741824 && mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, paddingTop);
        }
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        canvas.save();
        canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
        for (int i2 = 0; i2 < this.count; i2++) {
            int i3 = this.mDrection;
            if (i3 == DIRECTION_RIGHT) {
                int i4 = this.mSelectedIndex;
                if (!(i2 == i4 || i2 == i4 + 1)) {
                    canvas.drawCircle(getCenterPointAt(i2), this.mRadius, this.mNomarlRadius, this.mCirclePaint2);
                }
            } else if (!(i3 != DIRECTION_LEFT || i2 == (i = this.mSelectedIndex) || i2 == i - 1)) {
                canvas.drawCircle(getCenterPointAt(i2), this.mRadius, this.mNomarlRadius, this.mCirclePaint2);
            }
        }
        canvas.drawCircle(this.mSupport_next_centerX, this.mSupport_next_centerY, this.mSupport_Next_ChangeRadius, this.mCirclePaint2);
        canvas.drawCircle(this.mbgNextPointX, this.mbgNextPointY, this.mChangeBgRadius, this.mCirclePaint2);
        canvas.drawPath(this.mPath2, this.mCirclePaint2);
        canvas.drawCircle(this.mSupportCircleX, this.mSupportCircleY, this.mSupportChangeRadius, this.mCirclePaint);
        canvas.drawCircle(this.mCenterPointX, this.mCenterPointY, this.mChangeRadius, this.mCirclePaint);
        canvas.drawPath(this.mPath, this.mCirclePaint);
        canvas.restore();
    }

    public void setProgress(float f) {
        if (f != 0.0f) {
            this.mOriginProgress = f;
            if (((double) f) <= 0.5d) {
                this.mProgress = f / 0.5f;
                this.mProgress2 = 0.0f;
            } else {
                this.mProgress2 = (f - 0.5f) / 0.5f;
                this.mProgress = 1.0f;
            }
            if (this.mDrection == DIRECTION_RIGHT) {
                moveToNext();
            } else {
                moveToPrivous();
            }
            invalidate();
        }
    }

    private void moveToNext() {
        this.mPath.reset();
        this.mPath2.reset();
        float interpolation = this.accelerateinterpolator.getInterpolation(this.mOriginProgress);
        this.mCenterPointX = getValue(getCenterPointAt(this.mSelectedIndex), getCenterPointAt(this.mSelectedIndex + 1) - this.mRadius, this.MOVE_STEP_TWO);
        float f = this.mRadius;
        this.mCenterPointY = f;
        this.mChangeRadius = getValue(f, 0.0f, interpolation);
        double radians = Math.toRadians((double) getValue(45.0f, 0.0f, this.MOVE_STEP_ONE));
        double sin = Math.sin(radians);
        double d = (double) this.mChangeRadius;
        Double.isNaN(d);
        float f2 = (float) (sin * d);
        double cos = Math.cos(radians);
        double d2 = (double) this.mChangeRadius;
        Double.isNaN(d2);
        float f3 = (float) (cos * d2);
        this.mSupportCircleX = getValue(getCenterPointAt(this.mSelectedIndex) + this.mRadius, getCenterPointAt(this.mSelectedIndex + 1), this.MOVE_STEP_ONE);
        float f4 = this.mRadius;
        this.mSupportCircleY = f4;
        this.mSupportChangeRadius = getValue(0.0f, f4, interpolation);
        double radians2 = Math.toRadians((double) getValue(0.0f, 45.0f, this.MOVE_STEP_TWO));
        double sin2 = Math.sin(radians2);
        double d3 = (double) this.mSupportChangeRadius;
        Double.isNaN(d3);
        double cos2 = Math.cos(radians2);
        double d4 = (double) this.mSupportChangeRadius;
        Double.isNaN(d4);
        float f5 = (float) (cos2 * d4);
        this.mStartX = this.mCenterPointX + f2;
        this.mStartY = this.mCenterPointY - f3;
        this.endPointX = this.mSupportCircleX - ((float) (sin2 * d3));
        this.endPointY = this.mRadius - f5;
        this.controlPointX = getValueForAll(getCenterPointAt(this.mSelectedIndex) + this.mRadius, getCenterPointAt(this.mSelectedIndex + 1) - this.mRadius);
        this.controlPointY = this.mRadius;
        this.mPath.moveTo(this.mStartX, this.mStartY);
        this.mPath.quadTo(this.controlPointX, this.controlPointY, this.endPointX, this.endPointY);
        this.mPath.lineTo(this.endPointX, this.mRadius + f5);
        this.mPath.quadTo(this.controlPointX, this.mRadius, this.mStartX, this.mStartY + (f3 * 2.0f));
        this.mPath.lineTo(this.mStartX, this.mStartY);
        this.mbgNextPointX = getValue(getCenterPointAt(this.mSelectedIndex + 1), getCenterPointAt(this.mSelectedIndex) + this.mNomarlRadius, this.MOVE_STEP_TWO);
        this.mbgNextPointY = this.mRadius;
        this.mChangeBgRadius = getValue(this.mNomarlRadius, 0.0f, interpolation);
        double radians3 = Math.toRadians((double) getValue(45.0f, 0.0f, this.MOVE_STEP_ONE));
        double sin3 = Math.sin(radians3);
        double d5 = (double) this.mChangeBgRadius;
        Double.isNaN(d5);
        float f6 = (float) (sin3 * d5);
        double cos3 = Math.cos(radians3);
        double d6 = (double) this.mChangeBgRadius;
        Double.isNaN(d6);
        float f7 = (float) (cos3 * d6);
        this.mSupport_next_centerX = getValue(getCenterPointAt(this.mSelectedIndex + 1) - this.mNomarlRadius, getCenterPointAt(this.mSelectedIndex), this.MOVE_STEP_ONE);
        this.mSupport_next_centerY = this.mRadius;
        this.mSupport_Next_ChangeRadius = getValue(0.0f, this.mNomarlRadius, interpolation);
        double radians4 = Math.toRadians((double) getValue(0.0f, 45.0f, this.MOVE_STEP_TWO));
        double sin4 = Math.sin(radians4);
        double d7 = (double) this.mSupport_Next_ChangeRadius;
        Double.isNaN(d7);
        float f8 = (float) (sin4 * d7);
        double cos4 = Math.cos(radians4);
        double d8 = (double) this.mSupport_Next_ChangeRadius;
        Double.isNaN(d8);
        float f9 = (float) (cos4 * d8);
        float f10 = this.mbgNextPointX - f6;
        float f11 = this.mbgNextPointY - f7;
        float f12 = this.mSupport_next_centerX + f8;
        float valueForAll = getValueForAll(getCenterPointAt(this.mSelectedIndex + 1) - this.mNomarlRadius, getCenterPointAt(this.mSelectedIndex) + this.mNomarlRadius);
        float f13 = this.mRadius;
        this.mPath2.moveTo(f10, f11);
        this.mPath2.quadTo(valueForAll, f13, f12, this.mSupport_next_centerY - f9);
        this.mPath2.lineTo(f12, this.mRadius + f9);
        this.mPath2.quadTo(valueForAll, f13, f10, (f7 * 2.0f) + f11);
        this.mPath2.lineTo(f10, f11);
    }

    private void moveToPrivous() {
        this.mPath.reset();
        this.mPath2.reset();
        float interpolation = this.accelerateinterpolator.getInterpolation(this.mOriginProgress);
        this.mCenterPointX = getValue(getCenterPointAt(this.mSelectedIndex), getCenterPointAt(this.mSelectedIndex - 1) + this.mRadius, this.MOVE_STEP_TWO);
        float f = this.mRadius;
        this.mCenterPointY = f;
        this.mChangeRadius = getValue(f, 0.0f, interpolation);
        double radians = Math.toRadians((double) getValue(45.0f, 0.0f, this.MOVE_STEP_ONE));
        double sin = Math.sin(radians);
        double d = (double) this.mChangeRadius;
        Double.isNaN(d);
        float f2 = (float) (sin * d);
        double cos = Math.cos(radians);
        double d2 = (double) this.mChangeRadius;
        Double.isNaN(d2);
        float f3 = (float) (cos * d2);
        this.mSupportCircleX = getValue(getCenterPointAt(this.mSelectedIndex) - this.mRadius, getCenterPointAt(this.mSelectedIndex - 1), this.MOVE_STEP_ONE);
        float f4 = this.mRadius;
        this.mSupportCircleY = f4;
        this.mSupportChangeRadius = getValue(0.0f, f4, interpolation);
        double radians2 = Math.toRadians((double) getValue(0.0f, 45.0f, this.MOVE_STEP_TWO));
        double sin2 = Math.sin(radians2);
        double d3 = (double) this.mSupportChangeRadius;
        Double.isNaN(d3);
        double cos2 = Math.cos(radians2);
        double d4 = (double) this.mSupportChangeRadius;
        Double.isNaN(d4);
        float f5 = (float) (cos2 * d4);
        this.mStartX = this.mCenterPointX - f2;
        this.mStartY = this.mCenterPointY - f3;
        this.endPointX = this.mSupportCircleX + ((float) (sin2 * d3));
        this.endPointY = this.mRadius - f5;
        this.controlPointX = getValueForAll(getCenterPointAt(this.mSelectedIndex) - this.mRadius, getCenterPointAt(this.mSelectedIndex - 1) + this.mRadius);
        this.controlPointY = this.mRadius;
        this.mPath.moveTo(this.mStartX, this.mStartY);
        this.mPath.quadTo(this.controlPointX, this.controlPointY, this.endPointX, this.endPointY);
        this.mPath.lineTo(this.endPointX, this.mRadius + f5);
        this.mPath.quadTo(this.controlPointX, this.mRadius, this.mStartX, this.mStartY + (f3 * 2.0f));
        this.mPath.lineTo(this.mStartX, this.mStartY);
        this.mbgNextPointX = getValue(getCenterPointAt(this.mSelectedIndex - 1), getCenterPointAt(this.mSelectedIndex) - this.mNomarlRadius, this.MOVE_STEP_TWO);
        this.mbgNextPointY = this.mRadius;
        this.mChangeBgRadius = getValue(this.mNomarlRadius, 0.0f, interpolation);
        double radians3 = Math.toRadians((double) getValue(45.0f, 0.0f, this.MOVE_STEP_ONE));
        double sin3 = Math.sin(radians3);
        double d5 = (double) this.mChangeBgRadius;
        Double.isNaN(d5);
        float f6 = (float) (sin3 * d5);
        double cos3 = Math.cos(radians3);
        double d6 = (double) this.mChangeBgRadius;
        Double.isNaN(d6);
        float f7 = (float) (cos3 * d6);
        this.mSupport_next_centerX = getValue(getCenterPointAt(this.mSelectedIndex - 1) + this.mNomarlRadius, getCenterPointAt(this.mSelectedIndex), this.MOVE_STEP_ONE);
        this.mSupport_next_centerY = this.mRadius;
        this.mSupport_Next_ChangeRadius = getValue(0.0f, this.mNomarlRadius, interpolation);
        double radians4 = Math.toRadians((double) getValue(0.0f, 45.0f, this.MOVE_STEP_TWO));
        double sin4 = Math.sin(radians4);
        double d7 = (double) this.mSupport_Next_ChangeRadius;
        Double.isNaN(d7);
        float f8 = (float) (sin4 * d7);
        double cos4 = Math.cos(radians4);
        double d8 = (double) this.mSupport_Next_ChangeRadius;
        Double.isNaN(d8);
        float f9 = (float) (cos4 * d8);
        float f10 = this.mbgNextPointX + f6;
        float f11 = this.mbgNextPointY - f7;
        float f12 = this.mSupport_next_centerX - f8;
        float valueForAll = getValueForAll(getCenterPointAt(this.mSelectedIndex - 1) + this.mNomarlRadius, getCenterPointAt(this.mSelectedIndex) - this.mNomarlRadius);
        float f13 = this.mRadius;
        this.mPath2.moveTo(f10, f11);
        this.mPath2.quadTo(valueForAll, f13, f12, this.mSupport_next_centerY - f9);
        this.mPath2.lineTo(f12, this.mRadius + f9);
        this.mPath2.quadTo(valueForAll, f13, f10, (f7 * 2.0f) + f11);
        this.mPath2.lineTo(f10, f11);
    }

    public float getValue(float f, float f2, int i) {
        float f3;
        float f4;
        if (i == this.MOVE_STEP_ONE) {
            f3 = f2 - f;
            f4 = this.mProgress;
        } else {
            f3 = f2 - f;
            f4 = this.mProgress2;
        }
        return f + (f3 * f4);
    }

    public float getValueForAll(float f, float f2) {
        return f + ((f2 - f) * this.mOriginProgress);
    }

    private float getCenterPointAt(int i) {
        if (i == 0) {
            return this.mRadius;
        }
        float f = this.distance;
        float f2 = this.mNomarlRadius;
        return (((float) i) * (f + (2.0f * f2))) + f2 + (this.mRadius - f2);
    }

    public void setDirection(int i) {
        this.mDrection = i;
    }

    public void resetProgress() {
        this.mProgress = 0.0f;
        this.mProgress2 = 0.0f;
        this.mOriginProgress = 0.0f;
    }

    public void attachToViewpager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(this);
        this.count = viewPager.getAdapter().getCount();
        this.mSelectedIndex = viewPager.getCurrentItem();
        moveToNext();
        this.mDrection = DIRECTION_RIGHT;
        invalidate();
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (f == 0.0f) {
            this.mSelectedIndex = i;
            Log.d(TAG, "到达");
            resetProgress();
        }
        float f2 = ((float) i) + f;
        int i3 = this.mSelectedIndex;
        if (f2 - ((float) i3) > 0.0f) {
            int i4 = DIRECTION_RIGHT;
            this.mDrection = i4;
            if (this.mDrection != i4 || f2 <= ((float) (i3 + 1))) {
                setProgress(f);
                return;
            }
            this.mSelectedIndex = i;
            Log.d(TAG, "向左快速滑动");
        } else if (f2 - ((float) i3) < 0.0f) {
            int i5 = DIRECTION_LEFT;
            this.mDrection = i5;
            if (this.mDrection != i5 || f2 >= ((float) (i3 - 1))) {
                setProgress(1.0f - f);
                return;
            }
            this.mSelectedIndex = i;
            Log.d(TAG, "向右快速滑动");
        }
    }
}
