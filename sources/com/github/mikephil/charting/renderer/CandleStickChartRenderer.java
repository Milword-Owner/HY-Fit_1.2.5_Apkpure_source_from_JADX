package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class CandleStickChartRenderer extends LineScatterCandleRadarRenderer {
    private float[] mBodyBuffers = new float[4];
    protected CandleDataProvider mChart;
    private float[] mCloseBuffers = new float[4];
    private float[] mOpenBuffers = new float[4];
    private float[] mRangeBuffers = new float[4];
    private float[] mShadowBuffers = new float[8];

    public void drawExtras(Canvas canvas) {
    }

    public void initBuffers() {
    }

    public CandleStickChartRenderer(CandleDataProvider candleDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = candleDataProvider;
    }

    public void drawData(Canvas canvas) {
        for (ICandleDataSet iCandleDataSet : this.mChart.getCandleData().getDataSets()) {
            if (iCandleDataSet.isVisible()) {
                drawDataSet(canvas, iCandleDataSet);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawDataSet(Canvas canvas, ICandleDataSet iCandleDataSet) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        ICandleDataSet iCandleDataSet2 = iCandleDataSet;
        Transformer transformer = this.mChart.getTransformer(iCandleDataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        float barSpace = iCandleDataSet.getBarSpace();
        boolean showCandleBar = iCandleDataSet.getShowCandleBar();
        this.mXBounds.set(this.mChart, iCandleDataSet2);
        this.mRenderPaint.setStrokeWidth(iCandleDataSet.getShadowWidth());
        for (int i6 = this.mXBounds.min; i6 <= this.mXBounds.range + this.mXBounds.min; i6++) {
            CandleEntry candleEntry = (CandleEntry) iCandleDataSet2.getEntryForIndex(i6);
            if (candleEntry == null) {
                Canvas canvas2 = canvas;
            } else {
                float x = candleEntry.getX();
                float open = candleEntry.getOpen();
                float close = candleEntry.getClose();
                float high = candleEntry.getHigh();
                float low = candleEntry.getLow();
                if (showCandleBar) {
                    float[] fArr = this.mShadowBuffers;
                    fArr[0] = x;
                    fArr[2] = x;
                    fArr[4] = x;
                    fArr[6] = x;
                    if (open > close) {
                        fArr[1] = high * phaseY;
                        fArr[3] = open * phaseY;
                        fArr[5] = low * phaseY;
                        fArr[7] = close * phaseY;
                    } else if (open < close) {
                        fArr[1] = high * phaseY;
                        fArr[3] = close * phaseY;
                        fArr[5] = low * phaseY;
                        fArr[7] = open * phaseY;
                    } else {
                        fArr[1] = high * phaseY;
                        fArr[3] = open * phaseY;
                        fArr[5] = low * phaseY;
                        fArr[7] = fArr[3];
                    }
                    transformer.pointValuesToPixel(this.mShadowBuffers);
                    if (!iCandleDataSet.getShadowColorSameAsCandle()) {
                        Paint paint = this.mRenderPaint;
                        if (iCandleDataSet.getShadowColor() == 1122867) {
                            i2 = iCandleDataSet2.getColor(i6);
                        } else {
                            i2 = iCandleDataSet.getShadowColor();
                        }
                        paint.setColor(i2);
                    } else if (open > close) {
                        Paint paint2 = this.mRenderPaint;
                        if (iCandleDataSet.getDecreasingColor() == 1122867) {
                            i5 = iCandleDataSet2.getColor(i6);
                        } else {
                            i5 = iCandleDataSet.getDecreasingColor();
                        }
                        paint2.setColor(i5);
                    } else if (open < close) {
                        Paint paint3 = this.mRenderPaint;
                        if (iCandleDataSet.getIncreasingColor() == 1122867) {
                            i4 = iCandleDataSet2.getColor(i6);
                        } else {
                            i4 = iCandleDataSet.getIncreasingColor();
                        }
                        paint3.setColor(i4);
                    } else {
                        Paint paint4 = this.mRenderPaint;
                        if (iCandleDataSet.getNeutralColor() == 1122867) {
                            i3 = iCandleDataSet2.getColor(i6);
                        } else {
                            i3 = iCandleDataSet.getNeutralColor();
                        }
                        paint4.setColor(i3);
                    }
                    this.mRenderPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawLines(this.mShadowBuffers, this.mRenderPaint);
                    float[] fArr2 = this.mBodyBuffers;
                    fArr2[0] = (x - 0.5f) + barSpace;
                    fArr2[1] = close * phaseY;
                    fArr2[2] = (x + 0.5f) - barSpace;
                    fArr2[3] = open * phaseY;
                    transformer.pointValuesToPixel(fArr2);
                    if (open > close) {
                        if (iCandleDataSet.getDecreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(iCandleDataSet2.getColor(i6));
                        } else {
                            this.mRenderPaint.setColor(iCandleDataSet.getDecreasingColor());
                        }
                        this.mRenderPaint.setStyle(iCandleDataSet.getDecreasingPaintStyle());
                        float[] fArr3 = this.mBodyBuffers;
                        canvas.drawRect(fArr3[0], fArr3[3], fArr3[2], fArr3[1], this.mRenderPaint);
                    } else if (open < close) {
                        if (iCandleDataSet.getIncreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(iCandleDataSet2.getColor(i6));
                        } else {
                            this.mRenderPaint.setColor(iCandleDataSet.getIncreasingColor());
                        }
                        this.mRenderPaint.setStyle(iCandleDataSet.getIncreasingPaintStyle());
                        float[] fArr4 = this.mBodyBuffers;
                        canvas.drawRect(fArr4[0], fArr4[1], fArr4[2], fArr4[3], this.mRenderPaint);
                    } else {
                        if (iCandleDataSet.getNeutralColor() == 1122867) {
                            this.mRenderPaint.setColor(iCandleDataSet2.getColor(i6));
                        } else {
                            this.mRenderPaint.setColor(iCandleDataSet.getNeutralColor());
                        }
                        float[] fArr5 = this.mBodyBuffers;
                        canvas.drawLine(fArr5[0], fArr5[1], fArr5[2], fArr5[3], this.mRenderPaint);
                    }
                } else {
                    Canvas canvas3 = canvas;
                    float[] fArr6 = this.mRangeBuffers;
                    fArr6[0] = x;
                    fArr6[1] = high * phaseY;
                    fArr6[2] = x;
                    fArr6[3] = low * phaseY;
                    float[] fArr7 = this.mOpenBuffers;
                    fArr7[0] = (x - 0.5f) + barSpace;
                    float f = open * phaseY;
                    fArr7[1] = f;
                    fArr7[2] = x;
                    fArr7[3] = f;
                    float[] fArr8 = this.mCloseBuffers;
                    fArr8[0] = (0.5f + x) - barSpace;
                    float f2 = close * phaseY;
                    fArr8[1] = f2;
                    fArr8[2] = x;
                    fArr8[3] = f2;
                    transformer.pointValuesToPixel(fArr6);
                    transformer.pointValuesToPixel(this.mOpenBuffers);
                    transformer.pointValuesToPixel(this.mCloseBuffers);
                    if (open > close) {
                        if (iCandleDataSet.getDecreasingColor() == 1122867) {
                            i = iCandleDataSet2.getColor(i6);
                        } else {
                            i = iCandleDataSet.getDecreasingColor();
                        }
                    } else if (open < close) {
                        if (iCandleDataSet.getIncreasingColor() == 1122867) {
                            i = iCandleDataSet2.getColor(i6);
                        } else {
                            i = iCandleDataSet.getIncreasingColor();
                        }
                    } else if (iCandleDataSet.getNeutralColor() == 1122867) {
                        i = iCandleDataSet2.getColor(i6);
                    } else {
                        i = iCandleDataSet.getNeutralColor();
                    }
                    this.mRenderPaint.setColor(i);
                    float[] fArr9 = this.mRangeBuffers;
                    Canvas canvas4 = canvas;
                    canvas4.drawLine(fArr9[0], fArr9[1], fArr9[2], fArr9[3], this.mRenderPaint);
                    float[] fArr10 = this.mOpenBuffers;
                    canvas4.drawLine(fArr10[0], fArr10[1], fArr10[2], fArr10[3], this.mRenderPaint);
                    float[] fArr11 = this.mCloseBuffers;
                    canvas4.drawLine(fArr11[0], fArr11[1], fArr11[2], fArr11[3], this.mRenderPaint);
                }
            }
        }
    }

    public void drawValues(Canvas canvas) {
        ICandleDataSet iCandleDataSet;
        float f;
        CandleEntry candleEntry;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getCandleData().getDataSets();
            for (int i = 0; i < dataSets.size(); i++) {
                ICandleDataSet iCandleDataSet2 = (ICandleDataSet) dataSets.get(i);
                if (shouldDrawValues(iCandleDataSet2) && iCandleDataSet2.getEntryCount() >= 1) {
                    applyValueTextStyle(iCandleDataSet2);
                    Transformer transformer = this.mChart.getTransformer(iCandleDataSet2.getAxisDependency());
                    this.mXBounds.set(this.mChart, iCandleDataSet2);
                    float[] generateTransformedValuesCandle = transformer.generateTransformedValuesCandle(iCandleDataSet2, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    float convertDpToPixel = Utils.convertDpToPixel(5.0f);
                    ValueFormatter valueFormatter = iCandleDataSet2.getValueFormatter();
                    MPPointF instance = MPPointF.getInstance(iCandleDataSet2.getIconsOffset());
                    instance.f1568x = Utils.convertDpToPixel(instance.f1568x);
                    instance.f1569y = Utils.convertDpToPixel(instance.f1569y);
                    int i2 = 0;
                    while (i2 < generateTransformedValuesCandle.length) {
                        float f2 = generateTransformedValuesCandle[i2];
                        float f3 = generateTransformedValuesCandle[i2 + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(f2)) {
                            break;
                        }
                        if (!this.mViewPortHandler.isInBoundsLeft(f2) || !this.mViewPortHandler.isInBoundsY(f3)) {
                            iCandleDataSet = iCandleDataSet2;
                        } else {
                            int i3 = i2 / 2;
                            CandleEntry candleEntry2 = (CandleEntry) iCandleDataSet2.getEntryForIndex(this.mXBounds.min + i3);
                            if (iCandleDataSet2.isDrawValuesEnabled()) {
                                candleEntry = candleEntry2;
                                f = f3;
                                float f4 = f3 - convertDpToPixel;
                                iCandleDataSet = iCandleDataSet2;
                                drawValue(canvas, valueFormatter.getCandleLabel(candleEntry2), f2, f4, iCandleDataSet2.getValueTextColor(i3));
                            } else {
                                candleEntry = candleEntry2;
                                f = f3;
                                iCandleDataSet = iCandleDataSet2;
                            }
                            if (candleEntry.getIcon() != null && iCandleDataSet.isDrawIconsEnabled()) {
                                Drawable icon = candleEntry.getIcon();
                                Utils.drawImage(canvas, icon, (int) (f2 + instance.f1568x), (int) (f + instance.f1569y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        }
                        i2 += 2;
                        iCandleDataSet2 = iCandleDataSet;
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
        CandleData candleData = this.mChart.getCandleData();
        for (Highlight highlight : highlightArr) {
            ICandleDataSet iCandleDataSet = (ICandleDataSet) candleData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iCandleDataSet != null && iCandleDataSet.isHighlightEnabled()) {
                CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(candleEntry, iCandleDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iCandleDataSet.getAxisDependency()).getPixelForValues(candleEntry.getX(), ((candleEntry.getLow() * this.mAnimator.getPhaseY()) + (candleEntry.getHigh() * this.mAnimator.getPhaseY())) / 2.0f);
                    highlight.setDraw((float) pixelForValues.f1566x, (float) pixelForValues.f1567y);
                    drawHighlightLines(canvas, (float) pixelForValues.f1566x, (float) pixelForValues.f1567y, iCandleDataSet);
                }
            }
        }
    }
}
