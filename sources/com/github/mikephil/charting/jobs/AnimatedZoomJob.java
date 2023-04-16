package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"NewApi"})
public class AnimatedZoomJob extends AnimatedViewPortJob implements Animator.AnimatorListener {
    private static ObjectPool<AnimatedZoomJob> pool;
    protected Matrix mOnAnimationUpdateMatrixBuffer = new Matrix();
    protected float xAxisRange;
    protected YAxis yAxis;
    protected float zoomCenterX;
    protected float zoomCenterY;
    protected float zoomOriginX;
    protected float zoomOriginY;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public void recycleSelf() {
    }

    static {
        AnimatedZoomJob animatedZoomJob = r0;
        AnimatedZoomJob animatedZoomJob2 = new AnimatedZoomJob((ViewPortHandler) null, (View) null, (Transformer) null, (YAxis) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
        pool = ObjectPool.create(8, animatedZoomJob);
    }

    public static AnimatedZoomJob getInstance(ViewPortHandler viewPortHandler, View view, Transformer transformer, YAxis yAxis2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, long j) {
        AnimatedZoomJob animatedZoomJob = pool.get();
        animatedZoomJob.mViewPortHandler = viewPortHandler;
        animatedZoomJob.xValue = f2;
        animatedZoomJob.yValue = f3;
        animatedZoomJob.mTrans = transformer;
        animatedZoomJob.view = view;
        animatedZoomJob.xOrigin = f4;
        animatedZoomJob.yOrigin = f5;
        animatedZoomJob.yAxis = yAxis2;
        animatedZoomJob.xAxisRange = f;
        animatedZoomJob.resetAnimator();
        animatedZoomJob.animator.setDuration(j);
        return animatedZoomJob;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @SuppressLint({"NewApi"})
    public AnimatedZoomJob(ViewPortHandler viewPortHandler, View view, Transformer transformer, YAxis yAxis2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, long j) {
        super(viewPortHandler, f2, f3, transformer, view, f4, f5, j);
        this.zoomCenterX = f6;
        this.zoomCenterY = f7;
        this.zoomOriginX = f8;
        this.zoomOriginY = f9;
        this.animator.addListener(this);
        this.yAxis = yAxis2;
        this.xAxisRange = f;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f = this.xOrigin + ((this.xValue - this.xOrigin) * this.phase);
        float f2 = this.yOrigin + ((this.yValue - this.yOrigin) * this.phase);
        Matrix matrix = this.mOnAnimationUpdateMatrixBuffer;
        this.mViewPortHandler.setZoom(f, f2, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, false);
        float scaleY = this.yAxis.mAxisRange / this.mViewPortHandler.getScaleY();
        float scaleX = this.xAxisRange / this.mViewPortHandler.getScaleX();
        float[] fArr = this.pts;
        float f3 = this.zoomOriginX;
        fArr[0] = f3 + (((this.zoomCenterX - (scaleX / 2.0f)) - f3) * this.phase);
        float[] fArr2 = this.pts;
        float f4 = this.zoomOriginY;
        fArr2[1] = f4 + (((this.zoomCenterY + (scaleY / 2.0f)) - f4) * this.phase);
        this.mTrans.pointValuesToPixel(this.pts);
        this.mViewPortHandler.translate(this.pts, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, true);
    }

    public void onAnimationEnd(Animator animator) {
        ((BarLineChartBase) this.view).calculateOffsets();
        this.view.postInvalidate();
    }

    /* access modifiers changed from: protected */
    public ObjectPool.Poolable instantiate() {
        return new AnimatedZoomJob((ViewPortHandler) null, (View) null, (Transformer) null, (YAxis) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
    }
}
