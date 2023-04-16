package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class YAxisRendererRadarChart extends YAxisRenderer {
    private RadarChart mChart;
    private Path mRenderLimitLinesPathBuffer = new Path();

    public YAxisRendererRadarChart(ViewPortHandler viewPortHandler, YAxis yAxis, RadarChart radarChart) {
        super(viewPortHandler, yAxis, (Transformer) null);
        this.mChart = radarChart;
    }

    /* access modifiers changed from: protected */
    public void computeAxisValues(float f, float f2) {
        int i;
        double d;
        double d2;
        boolean z;
        float f3 = f;
        float f4 = f2;
        int labelCount = this.mAxis.getLabelCount();
        double abs = (double) Math.abs(f4 - f3);
        if (labelCount == 0 || abs <= Utils.DOUBLE_EPSILON || Double.isInfinite(abs)) {
            this.mAxis.mEntries = new float[0];
            this.mAxis.mCenteredEntries = new float[0];
            this.mAxis.mEntryCount = 0;
            return;
        }
        double d3 = (double) labelCount;
        Double.isNaN(abs);
        Double.isNaN(d3);
        double roundToNextSignificant = (double) Utils.roundToNextSignificant(abs / d3);
        if (this.mAxis.isGranularityEnabled() && roundToNextSignificant < ((double) this.mAxis.getGranularity())) {
            roundToNextSignificant = (double) this.mAxis.getGranularity();
        }
        double roundToNextSignificant2 = (double) Utils.roundToNextSignificant(Math.pow(10.0d, (double) ((int) Math.log10(roundToNextSignificant))));
        Double.isNaN(roundToNextSignificant2);
        if (((int) (roundToNextSignificant / roundToNextSignificant2)) > 5) {
            Double.isNaN(roundToNextSignificant2);
            roundToNextSignificant = Math.floor(roundToNextSignificant2 * 10.0d);
        }
        boolean isCenterAxisLabelsEnabled = this.mAxis.isCenterAxisLabelsEnabled();
        if (this.mAxis.isForceLabelsEnabled()) {
            float f5 = ((float) abs) / ((float) (labelCount - 1));
            this.mAxis.mEntryCount = labelCount;
            if (this.mAxis.mEntries.length < labelCount) {
                this.mAxis.mEntries = new float[labelCount];
            }
            float f6 = f3;
            for (int i2 = 0; i2 < labelCount; i2++) {
                this.mAxis.mEntries[i2] = f6;
                f6 += f5;
            }
            i = labelCount;
        } else {
            if (roundToNextSignificant == Utils.DOUBLE_EPSILON) {
                d = Utils.DOUBLE_EPSILON;
            } else {
                double d4 = (double) f3;
                Double.isNaN(d4);
                d = Math.ceil(d4 / roundToNextSignificant) * roundToNextSignificant;
            }
            if (isCenterAxisLabelsEnabled) {
                d -= roundToNextSignificant;
            }
            if (roundToNextSignificant == Utils.DOUBLE_EPSILON) {
                d2 = 0.0d;
            } else {
                double d5 = (double) f4;
                Double.isNaN(d5);
                d2 = Utils.nextUp(Math.floor(d5 / roundToNextSignificant) * roundToNextSignificant);
            }
            if (roundToNextSignificant != Utils.DOUBLE_EPSILON) {
                z = isCenterAxisLabelsEnabled;
                for (double d6 = d; d6 <= d2; d6 += roundToNextSignificant) {
                    z++;
                }
            } else {
                z = isCenterAxisLabelsEnabled;
            }
            i = ((int) z) + 1;
            this.mAxis.mEntryCount = i;
            if (this.mAxis.mEntries.length < i) {
                this.mAxis.mEntries = new float[i];
            }
            for (int i3 = 0; i3 < i; i3++) {
                if (d == Utils.DOUBLE_EPSILON) {
                    d = 0.0d;
                }
                this.mAxis.mEntries[i3] = (float) d;
                d += roundToNextSignificant;
            }
        }
        if (roundToNextSignificant < 1.0d) {
            this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(roundToNextSignificant));
        } else {
            this.mAxis.mDecimals = 0;
        }
        if (isCenterAxisLabelsEnabled) {
            if (this.mAxis.mCenteredEntries.length < i) {
                this.mAxis.mCenteredEntries = new float[i];
            }
            float f7 = (this.mAxis.mEntries[1] - this.mAxis.mEntries[0]) / 2.0f;
            for (int i4 = 0; i4 < i; i4++) {
                this.mAxis.mCenteredEntries[i4] = this.mAxis.mEntries[i4] + f7;
            }
        }
        this.mAxis.mAxisMinimum = this.mAxis.mEntries[0];
        this.mAxis.mAxisMaximum = this.mAxis.mEntries[i - 1];
        this.mAxis.mAxisRange = Math.abs(this.mAxis.mAxisMaximum - this.mAxis.mAxisMinimum);
    }

    public void renderAxisLabels(Canvas canvas) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled()) {
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            MPPointF centerOffsets = this.mChart.getCenterOffsets();
            MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
            float factor = this.mChart.getFactor();
            int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
            for (int i2 = !this.mYAxis.isDrawBottomYLabelEntryEnabled(); i2 < i; i2++) {
                Utils.getPosition(centerOffsets, (this.mYAxis.mEntries[i2] - this.mYAxis.mAxisMinimum) * factor, this.mChart.getRotationAngle(), instance);
                canvas.drawText(this.mYAxis.getFormattedLabel(i2), instance.f1568x + 10.0f, instance.f1569y, this.mAxisLabelPaint);
            }
            MPPointF.recycleInstance(centerOffsets);
            MPPointF.recycleInstance(instance);
        }
    }

    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = this.mYAxis.getLimitLines();
        if (limitLines != null) {
            float sliceAngle = this.mChart.getSliceAngle();
            float factor = this.mChart.getFactor();
            MPPointF centerOffsets = this.mChart.getCenterOffsets();
            MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
            for (int i = 0; i < limitLines.size(); i++) {
                LimitLine limitLine = limitLines.get(i);
                if (limitLine.isEnabled()) {
                    this.mLimitLinePaint.setColor(limitLine.getLineColor());
                    this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
                    this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
                    float limit = (limitLine.getLimit() - this.mChart.getYChartMin()) * factor;
                    Path path = this.mRenderLimitLinesPathBuffer;
                    path.reset();
                    for (int i2 = 0; i2 < ((IRadarDataSet) ((RadarData) this.mChart.getData()).getMaxEntryCountSet()).getEntryCount(); i2++) {
                        Utils.getPosition(centerOffsets, limit, (((float) i2) * sliceAngle) + this.mChart.getRotationAngle(), instance);
                        if (i2 == 0) {
                            path.moveTo(instance.f1568x, instance.f1569y);
                        } else {
                            path.lineTo(instance.f1568x, instance.f1569y);
                        }
                    }
                    path.close();
                    canvas.drawPath(path, this.mLimitLinePaint);
                }
            }
            MPPointF.recycleInstance(centerOffsets);
            MPPointF.recycleInstance(instance);
        }
    }
}
