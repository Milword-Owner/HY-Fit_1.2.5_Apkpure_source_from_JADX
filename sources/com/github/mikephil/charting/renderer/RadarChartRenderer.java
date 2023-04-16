package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class RadarChartRenderer extends LineRadarRenderer {
    protected RadarChart mChart;
    protected Path mDrawDataSetSurfacePathBuffer = new Path();
    protected Path mDrawHighlightCirclePathBuffer = new Path();
    protected Paint mHighlightCirclePaint;
    protected Paint mWebPaint;

    public void initBuffers() {
    }

    public RadarChartRenderer(RadarChart radarChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = radarChart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(2.0f);
        this.mHighlightPaint.setColor(Color.rgb(255, 187, 115));
        this.mWebPaint = new Paint(1);
        this.mWebPaint.setStyle(Paint.Style.STROKE);
        this.mHighlightCirclePaint = new Paint(1);
    }

    public Paint getWebPaint() {
        return this.mWebPaint;
    }

    public void drawData(Canvas canvas) {
        RadarData radarData = (RadarData) this.mChart.getData();
        int entryCount = ((IRadarDataSet) radarData.getMaxEntryCountSet()).getEntryCount();
        for (IRadarDataSet iRadarDataSet : radarData.getDataSets()) {
            if (iRadarDataSet.isVisible()) {
                drawDataSet(canvas, iRadarDataSet, entryCount);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawDataSet(Canvas canvas, IRadarDataSet iRadarDataSet, int i) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        Path path = this.mDrawDataSetSurfacePathBuffer;
        path.reset();
        boolean z = false;
        for (int i2 = 0; i2 < iRadarDataSet.getEntryCount(); i2++) {
            this.mRenderPaint.setColor(iRadarDataSet.getColor(i2));
            Utils.getPosition(centerOffsets, (((RadarEntry) iRadarDataSet.getEntryForIndex(i2)).getY() - this.mChart.getYChartMin()) * factor * phaseY, (((float) i2) * sliceAngle * phaseX) + this.mChart.getRotationAngle(), instance);
            if (!Float.isNaN(instance.f1568x)) {
                if (!z) {
                    path.moveTo(instance.f1568x, instance.f1569y);
                    z = true;
                } else {
                    path.lineTo(instance.f1568x, instance.f1569y);
                }
            }
        }
        if (iRadarDataSet.getEntryCount() > i) {
            path.lineTo(centerOffsets.f1568x, centerOffsets.f1569y);
        }
        path.close();
        if (iRadarDataSet.isDrawFilledEnabled()) {
            Drawable fillDrawable = iRadarDataSet.getFillDrawable();
            if (fillDrawable != null) {
                drawFilledPath(canvas, path, fillDrawable);
            } else {
                drawFilledPath(canvas, path, iRadarDataSet.getFillColor(), iRadarDataSet.getFillAlpha());
            }
        }
        this.mRenderPaint.setStrokeWidth(iRadarDataSet.getLineWidth());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        if (!iRadarDataSet.isDrawFilledEnabled() || iRadarDataSet.getFillAlpha() < 255) {
            canvas.drawPath(path, this.mRenderPaint);
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(instance);
    }

    public void drawValues(Canvas canvas) {
        float f;
        int i;
        float f2;
        int i2;
        int i3;
        RadarEntry radarEntry;
        ValueFormatter valueFormatter;
        IRadarDataSet iRadarDataSet;
        MPPointF mPPointF;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF instance2 = MPPointF.getInstance(0.0f, 0.0f);
        float convertDpToPixel = Utils.convertDpToPixel(5.0f);
        int i4 = 0;
        while (i4 < ((RadarData) this.mChart.getData()).getDataSetCount()) {
            IRadarDataSet iRadarDataSet2 = (IRadarDataSet) ((RadarData) this.mChart.getData()).getDataSetByIndex(i4);
            if (!shouldDrawValues(iRadarDataSet2)) {
                i = i4;
                f = phaseX;
            } else {
                applyValueTextStyle(iRadarDataSet2);
                ValueFormatter valueFormatter2 = iRadarDataSet2.getValueFormatter();
                MPPointF instance3 = MPPointF.getInstance(iRadarDataSet2.getIconsOffset());
                instance3.f1568x = Utils.convertDpToPixel(instance3.f1568x);
                instance3.f1569y = Utils.convertDpToPixel(instance3.f1569y);
                int i5 = 0;
                while (i5 < iRadarDataSet2.getEntryCount()) {
                    RadarEntry radarEntry2 = (RadarEntry) iRadarDataSet2.getEntryForIndex(i5);
                    MPPointF mPPointF2 = instance3;
                    float f3 = ((float) i5) * sliceAngle * phaseX;
                    Utils.getPosition(centerOffsets, (radarEntry2.getY() - this.mChart.getYChartMin()) * factor * phaseY, f3 + this.mChart.getRotationAngle(), instance);
                    if (iRadarDataSet2.isDrawValuesEnabled()) {
                        String radarLabel = valueFormatter2.getRadarLabel(radarEntry2);
                        float f4 = instance.f1568x;
                        radarEntry = radarEntry2;
                        float f5 = instance.f1569y - convertDpToPixel;
                        int valueTextColor = iRadarDataSet2.getValueTextColor(i5);
                        i2 = i5;
                        f2 = phaseX;
                        mPPointF = mPPointF2;
                        valueFormatter = valueFormatter2;
                        float f6 = f4;
                        iRadarDataSet = iRadarDataSet2;
                        float f7 = f5;
                        i3 = i4;
                        drawValue(canvas, radarLabel, f6, f7, valueTextColor);
                    } else {
                        radarEntry = radarEntry2;
                        i2 = i5;
                        iRadarDataSet = iRadarDataSet2;
                        i3 = i4;
                        f2 = phaseX;
                        mPPointF = mPPointF2;
                        valueFormatter = valueFormatter2;
                    }
                    if (radarEntry.getIcon() != null && iRadarDataSet.isDrawIconsEnabled()) {
                        Drawable icon = radarEntry.getIcon();
                        Utils.getPosition(centerOffsets, (radarEntry.getY() * factor * phaseY) + mPPointF.f1569y, f3 + this.mChart.getRotationAngle(), instance2);
                        instance2.f1569y += mPPointF.f1568x;
                        Utils.drawImage(canvas, icon, (int) instance2.f1568x, (int) instance2.f1569y, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    i5 = i2 + 1;
                    instance3 = mPPointF;
                    iRadarDataSet2 = iRadarDataSet;
                    valueFormatter2 = valueFormatter;
                    i4 = i3;
                    phaseX = f2;
                }
                i = i4;
                f = phaseX;
                MPPointF.recycleInstance(instance3);
            }
            i4 = i + 1;
            phaseX = f;
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(instance);
        MPPointF.recycleInstance(instance2);
    }

    public void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }

    public void drawExtras(Canvas canvas) {
        drawWeb(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawWeb(Canvas canvas) {
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        float rotationAngle = this.mChart.getRotationAngle();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidth());
        this.mWebPaint.setColor(this.mChart.getWebColor());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int skipWebLineCount = this.mChart.getSkipWebLineCount() + 1;
        int entryCount = ((IRadarDataSet) ((RadarData) this.mChart.getData()).getMaxEntryCountSet()).getEntryCount();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        for (int i = 0; i < entryCount; i += skipWebLineCount) {
            Utils.getPosition(centerOffsets, this.mChart.getYRange() * factor, (((float) i) * sliceAngle) + rotationAngle, instance);
            canvas.drawLine(centerOffsets.f1568x, centerOffsets.f1569y, instance.f1568x, instance.f1569y, this.mWebPaint);
        }
        MPPointF.recycleInstance(instance);
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidthInner());
        this.mWebPaint.setColor(this.mChart.getWebColorInner());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int i2 = this.mChart.getYAxis().mEntryCount;
        MPPointF instance2 = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF instance3 = MPPointF.getInstance(0.0f, 0.0f);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = 0;
            while (i4 < ((RadarData) this.mChart.getData()).getEntryCount()) {
                float yChartMin = (this.mChart.getYAxis().mEntries[i3] - this.mChart.getYChartMin()) * factor;
                Utils.getPosition(centerOffsets, yChartMin, (((float) i4) * sliceAngle) + rotationAngle, instance2);
                i4++;
                Utils.getPosition(centerOffsets, yChartMin, (((float) i4) * sliceAngle) + rotationAngle, instance3);
                canvas.drawLine(instance2.f1568x, instance2.f1569y, instance3.f1568x, instance3.f1569y, this.mWebPaint);
            }
        }
        MPPointF.recycleInstance(instance2);
        MPPointF.recycleInstance(instance3);
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        int i;
        Highlight[] highlightArr2 = highlightArr;
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        RadarData radarData = (RadarData) this.mChart.getData();
        int length = highlightArr2.length;
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            Highlight highlight = highlightArr2[i3];
            IRadarDataSet iRadarDataSet = (IRadarDataSet) radarData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iRadarDataSet != null && iRadarDataSet.isHighlightEnabled()) {
                RadarEntry radarEntry = (RadarEntry) iRadarDataSet.getEntryForIndex((int) highlight.getX());
                if (isInBoundsX(radarEntry, iRadarDataSet)) {
                    Utils.getPosition(centerOffsets, (radarEntry.getY() - this.mChart.getYChartMin()) * factor * this.mAnimator.getPhaseY(), (highlight.getX() * sliceAngle * this.mAnimator.getPhaseX()) + this.mChart.getRotationAngle(), instance);
                    highlight.setDraw(instance.f1568x, instance.f1569y);
                    drawHighlightLines(canvas, instance.f1568x, instance.f1569y, iRadarDataSet);
                    if (iRadarDataSet.isDrawHighlightCircleEnabled() && !Float.isNaN(instance.f1568x) && !Float.isNaN(instance.f1569y)) {
                        int highlightCircleStrokeColor = iRadarDataSet.getHighlightCircleStrokeColor();
                        if (highlightCircleStrokeColor == 1122867) {
                            highlightCircleStrokeColor = iRadarDataSet.getColor(i2);
                        }
                        if (iRadarDataSet.getHighlightCircleStrokeAlpha() < 255) {
                            highlightCircleStrokeColor = ColorTemplate.colorWithAlpha(highlightCircleStrokeColor, iRadarDataSet.getHighlightCircleStrokeAlpha());
                        }
                        float highlightCircleInnerRadius = iRadarDataSet.getHighlightCircleInnerRadius();
                        float highlightCircleOuterRadius = iRadarDataSet.getHighlightCircleOuterRadius();
                        int highlightCircleFillColor = iRadarDataSet.getHighlightCircleFillColor();
                        int i4 = highlightCircleFillColor;
                        i = i3;
                        drawHighlightCircle(canvas, instance, highlightCircleInnerRadius, highlightCircleOuterRadius, i4, highlightCircleStrokeColor, iRadarDataSet.getHighlightCircleStrokeWidth());
                        i3 = i + 1;
                        i2 = 0;
                    }
                }
            }
            i = i3;
            i3 = i + 1;
            i2 = 0;
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(instance);
    }

    public void drawHighlightCircle(Canvas canvas, MPPointF mPPointF, float f, float f2, int i, int i2, float f3) {
        canvas.save();
        float convertDpToPixel = Utils.convertDpToPixel(f2);
        float convertDpToPixel2 = Utils.convertDpToPixel(f);
        if (i != 1122867) {
            Path path = this.mDrawHighlightCirclePathBuffer;
            path.reset();
            path.addCircle(mPPointF.f1568x, mPPointF.f1569y, convertDpToPixel, Path.Direction.CW);
            if (convertDpToPixel2 > 0.0f) {
                path.addCircle(mPPointF.f1568x, mPPointF.f1569y, convertDpToPixel2, Path.Direction.CCW);
            }
            this.mHighlightCirclePaint.setColor(i);
            this.mHighlightCirclePaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, this.mHighlightCirclePaint);
        }
        if (i2 != 1122867) {
            this.mHighlightCirclePaint.setColor(i2);
            this.mHighlightCirclePaint.setStyle(Paint.Style.STROKE);
            this.mHighlightCirclePaint.setStrokeWidth(Utils.convertDpToPixel(f3));
            canvas.drawCircle(mPPointF.f1568x, mPPointF.f1569y, convertDpToPixel, this.mHighlightCirclePaint);
        }
        canvas.restore();
    }
}
