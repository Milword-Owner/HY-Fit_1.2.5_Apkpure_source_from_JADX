package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.PieHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;

public class PieChart extends PieRadarChartBase<PieData> {
    private float[] mAbsoluteAngles = new float[1];
    private CharSequence mCenterText = "";
    private MPPointF mCenterTextOffset = MPPointF.getInstance(0.0f, 0.0f);
    private float mCenterTextRadiusPercent = 100.0f;
    private RectF mCircleBox = new RectF();
    private float[] mDrawAngles = new float[1];
    private boolean mDrawCenterText = true;
    private boolean mDrawEntryLabels = true;
    private boolean mDrawHole = true;
    private boolean mDrawRoundedSlices = false;
    private boolean mDrawSlicesUnderHole = false;
    private float mHoleRadiusPercent = 50.0f;
    protected float mMaxAngle = 360.0f;
    private float mMinAngleForSlices = 0.0f;
    protected float mTransparentCircleRadiusPercent = 55.0f;
    private boolean mUsePercentValues = false;

    /* access modifiers changed from: protected */
    public float getRequiredBaseOffset() {
        return 0.0f;
    }

    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PieChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.mRenderer = new PieChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        this.mXAxis = null;
        this.mHighlighter = new PieHighlighter(this);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mData != null) {
            this.mRenderer.drawData(canvas);
            if (valuesToHighlight()) {
                this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
            }
            this.mRenderer.drawExtras(canvas);
            this.mRenderer.drawValues(canvas);
            this.mLegendRenderer.renderLegend(canvas);
            drawDescription(canvas);
            drawMarkers(canvas);
        }
    }

    public void calculateOffsets() {
        super.calculateOffsets();
        if (this.mData != null) {
            float diameter = getDiameter() / 2.0f;
            MPPointF centerOffsets = getCenterOffsets();
            float selectionShift = ((PieData) this.mData).getDataSet().getSelectionShift();
            this.mCircleBox.set((centerOffsets.f1568x - diameter) + selectionShift, (centerOffsets.f1569y - diameter) + selectionShift, (centerOffsets.f1568x + diameter) - selectionShift, (centerOffsets.f1569y + diameter) - selectionShift);
            MPPointF.recycleInstance(centerOffsets);
        }
    }

    /* access modifiers changed from: protected */
    public void calcMinMax() {
        calcAngles();
    }

    /* access modifiers changed from: protected */
    public float[] getMarkerPosition(Highlight highlight) {
        MPPointF centerCircleBox = getCenterCircleBox();
        float radius = getRadius();
        float f = (radius / 10.0f) * 3.6f;
        if (isDrawHoleEnabled()) {
            f = (radius - ((radius / 100.0f) * getHoleRadius())) / 2.0f;
        }
        float f2 = radius - f;
        float rotationAngle = getRotationAngle();
        int x = (int) highlight.getX();
        float f3 = this.mDrawAngles[x] / 2.0f;
        double d = (double) f2;
        double cos = Math.cos(Math.toRadians((double) (((this.mAbsoluteAngles[x] + rotationAngle) - f3) * this.mAnimator.getPhaseY())));
        Double.isNaN(d);
        double d2 = (double) centerCircleBox.f1568x;
        Double.isNaN(d2);
        float f4 = (float) ((cos * d) + d2);
        double sin = Math.sin(Math.toRadians((double) (((rotationAngle + this.mAbsoluteAngles[x]) - f3) * this.mAnimator.getPhaseY())));
        Double.isNaN(d);
        double d3 = d * sin;
        double d4 = (double) centerCircleBox.f1569y;
        Double.isNaN(d4);
        MPPointF.recycleInstance(centerCircleBox);
        return new float[]{f4, (float) (d3 + d4)};
    }

    private void calcAngles() {
        int entryCount = ((PieData) this.mData).getEntryCount();
        if (this.mDrawAngles.length != entryCount) {
            this.mDrawAngles = new float[entryCount];
        } else {
            for (int i = 0; i < entryCount; i++) {
                this.mDrawAngles[i] = 0.0f;
            }
        }
        if (this.mAbsoluteAngles.length != entryCount) {
            this.mAbsoluteAngles = new float[entryCount];
        } else {
            for (int i2 = 0; i2 < entryCount; i2++) {
                this.mAbsoluteAngles[i2] = 0.0f;
            }
        }
        float yValueSum = ((PieData) this.mData).getYValueSum();
        List dataSets = ((PieData) this.mData).getDataSets();
        float f = this.mMinAngleForSlices;
        boolean z = f != 0.0f && ((float) entryCount) * f <= this.mMaxAngle;
        float[] fArr = new float[entryCount];
        int i3 = 0;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i4 = 0;
        while (i3 < ((PieData) this.mData).getDataSetCount()) {
            IPieDataSet iPieDataSet = (IPieDataSet) dataSets.get(i3);
            float f4 = f2;
            for (int i5 = 0; i5 < iPieDataSet.getEntryCount(); i5++) {
                float calcAngle = calcAngle(Math.abs(((PieEntry) iPieDataSet.getEntryForIndex(i5)).getY()), yValueSum);
                if (z) {
                    float f5 = this.mMinAngleForSlices;
                    float f6 = calcAngle - f5;
                    if (f6 <= 0.0f) {
                        fArr[i4] = f5;
                        f4 += -f6;
                    } else {
                        fArr[i4] = calcAngle;
                        f3 += f6;
                    }
                }
                float[] fArr2 = this.mDrawAngles;
                fArr2[i4] = calcAngle;
                if (i4 == 0) {
                    this.mAbsoluteAngles[i4] = fArr2[i4];
                } else {
                    float[] fArr3 = this.mAbsoluteAngles;
                    fArr3[i4] = fArr3[i4 - 1] + fArr2[i4];
                }
                i4++;
            }
            i3++;
            f2 = f4;
        }
        if (z) {
            for (int i6 = 0; i6 < entryCount; i6++) {
                fArr[i6] = fArr[i6] - (((fArr[i6] - this.mMinAngleForSlices) / f3) * f2);
                if (i6 == 0) {
                    this.mAbsoluteAngles[0] = fArr[0];
                } else {
                    float[] fArr4 = this.mAbsoluteAngles;
                    fArr4[i6] = fArr4[i6 - 1] + fArr[i6];
                }
            }
            this.mDrawAngles = fArr;
        }
    }

    public boolean needsHighlight(int i) {
        if (!valuesToHighlight()) {
            return false;
        }
        for (Highlight x : this.mIndicesToHighlight) {
            if (((int) x.getX()) == i) {
                return true;
            }
        }
        return false;
    }

    private float calcAngle(float f) {
        return calcAngle(f, ((PieData) this.mData).getYValueSum());
    }

    private float calcAngle(float f, float f2) {
        return (f / f2) * this.mMaxAngle;
    }

    @Deprecated
    public XAxis getXAxis() {
        throw new RuntimeException("PieChart has no XAxis");
    }

    public int getIndexForAngle(float f) {
        float normalizedAngle = Utils.getNormalizedAngle(f - getRotationAngle());
        int i = 0;
        while (true) {
            float[] fArr = this.mAbsoluteAngles;
            if (i >= fArr.length) {
                return -1;
            }
            if (fArr[i] > normalizedAngle) {
                return i;
            }
            i++;
        }
    }

    public int getDataSetIndexForIndex(int i) {
        List dataSets = ((PieData) this.mData).getDataSets();
        for (int i2 = 0; i2 < dataSets.size(); i2++) {
            if (((IPieDataSet) dataSets.get(i2)).getEntryForXValue((float) i, Float.NaN) != null) {
                return i2;
            }
        }
        return -1;
    }

    public float[] getDrawAngles() {
        return this.mDrawAngles;
    }

    public float[] getAbsoluteAngles() {
        return this.mAbsoluteAngles;
    }

    public void setHoleColor(int i) {
        ((PieChartRenderer) this.mRenderer).getPaintHole().setColor(i);
    }

    public void setDrawSlicesUnderHole(boolean z) {
        this.mDrawSlicesUnderHole = z;
    }

    public boolean isDrawSlicesUnderHoleEnabled() {
        return this.mDrawSlicesUnderHole;
    }

    public void setDrawHoleEnabled(boolean z) {
        this.mDrawHole = z;
    }

    public boolean isDrawHoleEnabled() {
        return this.mDrawHole;
    }

    public void setCenterText(CharSequence charSequence) {
        if (charSequence == null) {
            this.mCenterText = "";
        } else {
            this.mCenterText = charSequence;
        }
    }

    public CharSequence getCenterText() {
        return this.mCenterText;
    }

    public void setDrawCenterText(boolean z) {
        this.mDrawCenterText = z;
    }

    public boolean isDrawCenterTextEnabled() {
        return this.mDrawCenterText;
    }

    /* access modifiers changed from: protected */
    public float getRequiredLegendOffset() {
        return this.mLegendRenderer.getLabelPaint().getTextSize() * 2.0f;
    }

    public float getRadius() {
        RectF rectF = this.mCircleBox;
        if (rectF == null) {
            return 0.0f;
        }
        return Math.min(rectF.width() / 2.0f, this.mCircleBox.height() / 2.0f);
    }

    public RectF getCircleBox() {
        return this.mCircleBox;
    }

    public MPPointF getCenterCircleBox() {
        return MPPointF.getInstance(this.mCircleBox.centerX(), this.mCircleBox.centerY());
    }

    public void setCenterTextTypeface(Typeface typeface) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTypeface(typeface);
    }

    public void setCenterTextSize(float f) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(Utils.convertDpToPixel(f));
    }

    public void setCenterTextSizePixels(float f) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(f);
    }

    public void setCenterTextOffset(float f, float f2) {
        this.mCenterTextOffset.f1568x = Utils.convertDpToPixel(f);
        this.mCenterTextOffset.f1569y = Utils.convertDpToPixel(f2);
    }

    public MPPointF getCenterTextOffset() {
        return MPPointF.getInstance(this.mCenterTextOffset.f1568x, this.mCenterTextOffset.f1569y);
    }

    public void setCenterTextColor(int i) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setColor(i);
    }

    public void setHoleRadius(float f) {
        this.mHoleRadiusPercent = f;
    }

    public float getHoleRadius() {
        return this.mHoleRadiusPercent;
    }

    public void setTransparentCircleColor(int i) {
        Paint paintTransparentCircle = ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle();
        int alpha = paintTransparentCircle.getAlpha();
        paintTransparentCircle.setColor(i);
        paintTransparentCircle.setAlpha(alpha);
    }

    public void setTransparentCircleRadius(float f) {
        this.mTransparentCircleRadiusPercent = f;
    }

    public float getTransparentCircleRadius() {
        return this.mTransparentCircleRadiusPercent;
    }

    public void setTransparentCircleAlpha(int i) {
        ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle().setAlpha(i);
    }

    @Deprecated
    public void setDrawSliceText(boolean z) {
        this.mDrawEntryLabels = z;
    }

    public void setDrawEntryLabels(boolean z) {
        this.mDrawEntryLabels = z;
    }

    public boolean isDrawEntryLabelsEnabled() {
        return this.mDrawEntryLabels;
    }

    public void setEntryLabelColor(int i) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setColor(i);
    }

    public void setEntryLabelTypeface(Typeface typeface) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setTypeface(typeface);
    }

    public void setEntryLabelTextSize(float f) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setTextSize(Utils.convertDpToPixel(f));
    }

    public void setDrawRoundedSlices(boolean z) {
        this.mDrawRoundedSlices = z;
    }

    public boolean isDrawRoundedSlicesEnabled() {
        return this.mDrawRoundedSlices;
    }

    public void setUsePercentValues(boolean z) {
        this.mUsePercentValues = z;
    }

    public boolean isUsePercentValuesEnabled() {
        return this.mUsePercentValues;
    }

    public void setCenterTextRadiusPercent(float f) {
        this.mCenterTextRadiusPercent = f;
    }

    public float getCenterTextRadiusPercent() {
        return this.mCenterTextRadiusPercent;
    }

    public float getMaxAngle() {
        return this.mMaxAngle;
    }

    public void setMaxAngle(float f) {
        if (f > 360.0f) {
            f = 360.0f;
        }
        if (f < 90.0f) {
            f = 90.0f;
        }
        this.mMaxAngle = f;
    }

    public float getMinAngleForSlices() {
        return this.mMinAngleForSlices;
    }

    public void setMinAngleForSlices(float f) {
        float f2 = this.mMaxAngle;
        if (f > f2 / 2.0f) {
            f = f2 / 2.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        this.mMinAngleForSlices = f;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.mRenderer != null && (this.mRenderer instanceof PieChartRenderer)) {
            ((PieChartRenderer) this.mRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }
}
