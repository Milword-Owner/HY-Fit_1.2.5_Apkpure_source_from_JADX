package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class ScatterChartRenderer extends LineScatterCandleRadarRenderer {
    protected ScatterDataProvider mChart;
    float[] mPixelBuffer = new float[2];

    public void drawExtras(Canvas canvas) {
    }

    public void initBuffers() {
    }

    public ScatterChartRenderer(ScatterDataProvider scatterDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = scatterDataProvider;
    }

    public void drawData(Canvas canvas) {
        for (IScatterDataSet iScatterDataSet : this.mChart.getScatterData().getDataSets()) {
            if (iScatterDataSet.isVisible()) {
                drawDataSet(canvas, iScatterDataSet);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawDataSet(Canvas canvas, IScatterDataSet iScatterDataSet) {
        int i;
        IScatterDataSet iScatterDataSet2 = iScatterDataSet;
        if (iScatterDataSet.getEntryCount() >= 1) {
            ViewPortHandler viewPortHandler = this.mViewPortHandler;
            Transformer transformer = this.mChart.getTransformer(iScatterDataSet.getAxisDependency());
            float phaseY = this.mAnimator.getPhaseY();
            IShapeRenderer shapeRenderer = iScatterDataSet.getShapeRenderer();
            if (shapeRenderer == null) {
                Log.i("MISSING", "There's no IShapeRenderer specified for ScatterDataSet");
                return;
            }
            int min = (int) Math.min(Math.ceil((double) (((float) iScatterDataSet.getEntryCount()) * this.mAnimator.getPhaseX())), (double) ((float) iScatterDataSet.getEntryCount()));
            int i2 = 0;
            while (i2 < min) {
                Entry entryForIndex = iScatterDataSet2.getEntryForIndex(i2);
                this.mPixelBuffer[0] = entryForIndex.getX();
                this.mPixelBuffer[1] = entryForIndex.getY() * phaseY;
                transformer.pointValuesToPixel(this.mPixelBuffer);
                if (viewPortHandler.isInBoundsRight(this.mPixelBuffer[0])) {
                    if (!viewPortHandler.isInBoundsLeft(this.mPixelBuffer[0]) || !viewPortHandler.isInBoundsY(this.mPixelBuffer[1])) {
                        i = i2;
                    } else {
                        this.mRenderPaint.setColor(iScatterDataSet2.getColor(i2 / 2));
                        ViewPortHandler viewPortHandler2 = this.mViewPortHandler;
                        float[] fArr = this.mPixelBuffer;
                        i = i2;
                        shapeRenderer.renderShape(canvas, iScatterDataSet, viewPortHandler2, fArr[0], fArr[1], this.mRenderPaint);
                    }
                    i2 = i + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void drawValues(Canvas canvas) {
        IScatterDataSet iScatterDataSet;
        Entry entry;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getScatterData().getDataSets();
            for (int i = 0; i < this.mChart.getScatterData().getDataSetCount(); i++) {
                IScatterDataSet iScatterDataSet2 = (IScatterDataSet) dataSets.get(i);
                if (shouldDrawValues(iScatterDataSet2) && iScatterDataSet2.getEntryCount() >= 1) {
                    applyValueTextStyle(iScatterDataSet2);
                    this.mXBounds.set(this.mChart, iScatterDataSet2);
                    float[] generateTransformedValuesScatter = this.mChart.getTransformer(iScatterDataSet2.getAxisDependency()).generateTransformedValuesScatter(iScatterDataSet2, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet2.getScatterShapeSize());
                    ValueFormatter valueFormatter = iScatterDataSet2.getValueFormatter();
                    MPPointF instance = MPPointF.getInstance(iScatterDataSet2.getIconsOffset());
                    instance.f1568x = Utils.convertDpToPixel(instance.f1568x);
                    instance.f1569y = Utils.convertDpToPixel(instance.f1569y);
                    int i2 = 0;
                    while (i2 < generateTransformedValuesScatter.length && this.mViewPortHandler.isInBoundsRight(generateTransformedValuesScatter[i2])) {
                        if (this.mViewPortHandler.isInBoundsLeft(generateTransformedValuesScatter[i2])) {
                            int i3 = i2 + 1;
                            if (this.mViewPortHandler.isInBoundsY(generateTransformedValuesScatter[i3])) {
                                int i4 = i2 / 2;
                                Entry entryForIndex = iScatterDataSet2.getEntryForIndex(this.mXBounds.min + i4);
                                if (iScatterDataSet2.isDrawValuesEnabled()) {
                                    String pointLabel = valueFormatter.getPointLabel(entryForIndex);
                                    float f = generateTransformedValuesScatter[i2];
                                    float f2 = generateTransformedValuesScatter[i3] - convertDpToPixel;
                                    entry = entryForIndex;
                                    float f3 = f2;
                                    iScatterDataSet = iScatterDataSet2;
                                    drawValue(canvas, pointLabel, f, f3, iScatterDataSet2.getValueTextColor(i4 + this.mXBounds.min));
                                } else {
                                    entry = entryForIndex;
                                    iScatterDataSet = iScatterDataSet2;
                                }
                                if (entry.getIcon() != null && iScatterDataSet.isDrawIconsEnabled()) {
                                    Drawable icon = entry.getIcon();
                                    Utils.drawImage(canvas, icon, (int) (generateTransformedValuesScatter[i2] + instance.f1568x), (int) (generateTransformedValuesScatter[i3] + instance.f1569y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                                i2 += 2;
                                iScatterDataSet2 = iScatterDataSet;
                            }
                        }
                        iScatterDataSet = iScatterDataSet2;
                        i2 += 2;
                        iScatterDataSet2 = iScatterDataSet;
                    }
                    MPPointF.recycleInstance(instance);
                }
            }
        }
    }

    public void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        ScatterData scatterData = this.mChart.getScatterData();
        for (Highlight highlight : highlightArr) {
            IScatterDataSet iScatterDataSet = (IScatterDataSet) scatterData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iScatterDataSet != null && iScatterDataSet.isHighlightEnabled()) {
                Entry entryForXValue = iScatterDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(entryForXValue, iScatterDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iScatterDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues.f1566x, (float) pixelForValues.f1567y);
                    drawHighlightLines(canvas, (float) pixelForValues.f1566x, (float) pixelForValues.f1567y, iScatterDataSet);
                }
            }
        }
    }
}
