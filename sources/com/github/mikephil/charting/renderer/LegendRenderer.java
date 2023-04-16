package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

public class LegendRenderer extends Renderer {
    protected List<LegendEntry> computedEntries = new ArrayList(16);
    protected Paint.FontMetrics legendFontMetrics = new Paint.FontMetrics();
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;
    private Path mLineFormPath = new Path();

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.mLegend = legend;
        this.mLegendLabelPaint = new Paint(1);
        this.mLegendLabelPaint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
        this.mLegendFormPaint = new Paint(1);
        this.mLegendFormPaint.setStyle(Paint.Style.FILL);
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: com.github.mikephil.charting.data.ChartData<?>} */
    /* JADX WARNING: type inference failed for: r7v2, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void computeLegend(com.github.mikephil.charting.data.ChartData<?> r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            boolean r2 = r2.isLegendCustom()
            if (r2 != 0) goto L_0x01c1
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            r2.clear()
            r3 = 0
        L_0x0012:
            int r4 = r19.getDataSetCount()
            if (r3 >= r4) goto L_0x01a7
            com.github.mikephil.charting.interfaces.datasets.IDataSet r4 = r1.getDataSetByIndex(r3)
            java.util.List r5 = r4.getColors()
            int r6 = r4.getEntryCount()
            boolean r7 = r4 instanceof com.github.mikephil.charting.interfaces.datasets.IBarDataSet
            if (r7 == 0) goto L_0x0096
            r7 = r4
            com.github.mikephil.charting.interfaces.datasets.IBarDataSet r7 = (com.github.mikephil.charting.interfaces.datasets.IBarDataSet) r7
            boolean r8 = r7.isStacked()
            if (r8 == 0) goto L_0x0096
            java.lang.String[] r6 = r7.getStackLabels()
            r8 = 0
        L_0x0036:
            int r9 = r5.size()
            if (r8 >= r9) goto L_0x0074
            int r9 = r7.getStackSize()
            if (r8 >= r9) goto L_0x0074
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r9 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r15 = new com.github.mikephil.charting.components.LegendEntry
            int r10 = r6.length
            int r10 = r8 % r10
            r11 = r6[r10]
            com.github.mikephil.charting.components.Legend$LegendForm r12 = r4.getForm()
            float r13 = r4.getFormSize()
            float r14 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r16 = r4.getFormLineDashEffect()
            java.lang.Object r10 = r5.get(r8)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r17 = r10.intValue()
            r10 = r15
            r2 = r15
            r15 = r16
            r16 = r17
            r10.<init>(r11, r12, r13, r14, r15, r16)
            r9.add(r2)
            int r8 = r8 + 1
            goto L_0x0036
        L_0x0074:
            java.lang.String r2 = r7.getLabel()
            if (r2 == 0) goto L_0x0093
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r12 = new com.github.mikephil.charting.components.LegendEntry
            java.lang.String r6 = r4.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r7 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            r8 = 2143289344(0x7fc00000, float:NaN)
            r9 = 2143289344(0x7fc00000, float:NaN)
            r10 = 0
            r11 = 1122867(0x112233, float:1.573472E-39)
            r5 = r12
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r2.add(r12)
        L_0x0093:
            r2 = r1
            goto L_0x01a2
        L_0x0096:
            boolean r2 = r4 instanceof com.github.mikephil.charting.interfaces.datasets.IPieDataSet
            if (r2 == 0) goto L_0x00fe
            r2 = r4
            com.github.mikephil.charting.interfaces.datasets.IPieDataSet r2 = (com.github.mikephil.charting.interfaces.datasets.IPieDataSet) r2
            r7 = 0
        L_0x009e:
            int r8 = r5.size()
            if (r7 >= r8) goto L_0x00dd
            if (r7 >= r6) goto L_0x00dd
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r8 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r15 = new com.github.mikephil.charting.components.LegendEntry
            com.github.mikephil.charting.data.Entry r9 = r2.getEntryForIndex(r7)
            com.github.mikephil.charting.data.PieEntry r9 = (com.github.mikephil.charting.data.PieEntry) r9
            java.lang.String r10 = r9.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r11 = r4.getForm()
            float r12 = r4.getFormSize()
            float r13 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r14 = r4.getFormLineDashEffect()
            java.lang.Object r9 = r5.get(r7)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r16 = r9.intValue()
            r9 = r15
            r1 = r15
            r15 = r16
            r9.<init>(r10, r11, r12, r13, r14, r15)
            r8.add(r1)
            int r7 = r7 + 1
            r1 = r19
            goto L_0x009e
        L_0x00dd:
            java.lang.String r1 = r2.getLabel()
            if (r1 == 0) goto L_0x01a0
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r1 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r2 = new com.github.mikephil.charting.components.LegendEntry
            java.lang.String r6 = r4.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r7 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            r8 = 2143289344(0x7fc00000, float:NaN)
            r9 = 2143289344(0x7fc00000, float:NaN)
            r10 = 0
            r11 = 1122867(0x112233, float:1.573472E-39)
            r5 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r1.add(r2)
            goto L_0x01a0
        L_0x00fe:
            boolean r1 = r4 instanceof com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
            if (r1 == 0) goto L_0x0153
            r1 = r4
            com.github.mikephil.charting.interfaces.datasets.ICandleDataSet r1 = (com.github.mikephil.charting.interfaces.datasets.ICandleDataSet) r1
            int r2 = r1.getDecreasingColor()
            r7 = 1122867(0x112233, float:1.573472E-39)
            if (r2 == r7) goto L_0x0153
            int r14 = r1.getDecreasingColor()
            int r1 = r1.getIncreasingColor()
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r5 = new com.github.mikephil.charting.components.LegendEntry
            r9 = 0
            com.github.mikephil.charting.components.Legend$LegendForm r10 = r4.getForm()
            float r11 = r4.getFormSize()
            float r12 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r13 = r4.getFormLineDashEffect()
            r8 = r5
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r2.add(r5)
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r12 = new com.github.mikephil.charting.components.LegendEntry
            java.lang.String r6 = r4.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r7 = r4.getForm()
            float r8 = r4.getFormSize()
            float r9 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r10 = r4.getFormLineDashEffect()
            r5 = r12
            r11 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r2.add(r12)
            goto L_0x01a0
        L_0x0153:
            r1 = 0
        L_0x0154:
            int r2 = r5.size()
            if (r1 >= r2) goto L_0x01a0
            if (r1 >= r6) goto L_0x01a0
            int r2 = r5.size()
            int r2 = r2 + -1
            if (r1 >= r2) goto L_0x016d
            int r2 = r6 + -1
            if (r1 >= r2) goto L_0x016d
            r2 = 0
            r9 = r2
            r2 = r19
            goto L_0x0178
        L_0x016d:
            r2 = r19
            com.github.mikephil.charting.interfaces.datasets.IDataSet r7 = r2.getDataSetByIndex(r3)
            java.lang.String r7 = r7.getLabel()
            r9 = r7
        L_0x0178:
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r7 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r15 = new com.github.mikephil.charting.components.LegendEntry
            com.github.mikephil.charting.components.Legend$LegendForm r10 = r4.getForm()
            float r11 = r4.getFormSize()
            float r12 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r13 = r4.getFormLineDashEffect()
            java.lang.Object r8 = r5.get(r1)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r14 = r8.intValue()
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r7.add(r15)
            int r1 = r1 + 1
            goto L_0x0154
        L_0x01a0:
            r2 = r19
        L_0x01a2:
            int r3 = r3 + 1
            r1 = r2
            goto L_0x0012
        L_0x01a7:
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            com.github.mikephil.charting.components.LegendEntry[] r1 = r1.getExtraEntries()
            if (r1 == 0) goto L_0x01ba
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r1 = r0.computedEntries
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            com.github.mikephil.charting.components.LegendEntry[] r2 = r2.getExtraEntries()
            java.util.Collections.addAll(r1, r2)
        L_0x01ba:
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            r1.setEntries(r2)
        L_0x01c1:
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            android.graphics.Typeface r1 = r1.getTypeface()
            if (r1 == 0) goto L_0x01ce
            android.graphics.Paint r2 = r0.mLegendLabelPaint
            r2.setTypeface(r1)
        L_0x01ce:
            android.graphics.Paint r1 = r0.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            float r2 = r2.getTextSize()
            r1.setTextSize(r2)
            android.graphics.Paint r1 = r0.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            int r2 = r2.getTextColor()
            r1.setColor(r2)
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            android.graphics.Paint r2 = r0.mLegendLabelPaint
            com.github.mikephil.charting.utils.ViewPortHandler r3 = r0.mViewPortHandler
            r1.calculateDimensions(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.LegendRenderer.computeLegend(com.github.mikephil.charting.data.ChartData):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x0277  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x016f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void renderLegend(android.graphics.Canvas r35) {
        /*
            r34 = this;
            r6 = r34
            r7 = r35
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            android.graphics.Typeface r0 = r0.getTypeface()
            if (r0 == 0) goto L_0x001a
            android.graphics.Paint r1 = r6.mLegendLabelPaint
            r1.setTypeface(r0)
        L_0x001a:
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.getTextSize()
            r0.setTextSize(r1)
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            int r1 = r1.getTextColor()
            r0.setColor(r1)
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            android.graphics.Paint$FontMetrics r1 = r6.legendFontMetrics
            float r8 = com.github.mikephil.charting.utils.Utils.getLineHeight(r0, r1)
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            android.graphics.Paint$FontMetrics r1 = r6.legendFontMetrics
            float r0 = com.github.mikephil.charting.utils.Utils.getLineSpacing(r0, r1)
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.getYEntrySpace()
            float r1 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r1)
            float r9 = r0 + r1
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            java.lang.String r1 = "ABC"
            int r0 = com.github.mikephil.charting.utils.Utils.calcTextHeight(r0, r1)
            float r0 = (float) r0
            r10 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r10
            float r11 = r8 - r0
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            com.github.mikephil.charting.components.LegendEntry[] r12 = r0.getEntries()
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            float r0 = r0.getFormToTextSpace()
            float r13 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            float r0 = r0.getXEntrySpace()
            float r14 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            com.github.mikephil.charting.components.Legend$LegendOrientation r0 = r0.getOrientation()
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r15 = r1.getHorizontalAlignment()
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r1 = r1.getVerticalAlignment()
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            com.github.mikephil.charting.components.Legend$LegendDirection r5 = r2.getDirection()
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            float r2 = r2.getFormSize()
            float r16 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2)
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            float r2 = r2.getStackSpace()
            float r4 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2)
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            float r2 = r2.getYOffset()
            com.github.mikephil.charting.components.Legend r3 = r6.mLegend
            float r3 = r3.getXOffset()
            int[] r17 = com.github.mikephil.charting.renderer.LegendRenderer.C16491.f1563x2787f53e
            int r18 = r15.ordinal()
            r10 = r17[r18]
            r17 = r4
            r4 = 2
            r20 = 0
            r21 = r14
            r14 = 1
            if (r10 == r14) goto L_0x014b
            if (r10 == r4) goto L_0x0129
            r4 = 3
            if (r10 == r4) goto L_0x00c9
            r26 = r8
            r14 = r9
            r7 = 0
            goto L_0x0164
        L_0x00c9:
            com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL
            if (r0 != r4) goto L_0x00d7
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.getChartWidth()
            r10 = 1073741824(0x40000000, float:2.0)
            float r4 = r4 / r10
            goto L_0x00e7
        L_0x00d7:
            r10 = 1073741824(0x40000000, float:2.0)
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.contentLeft()
            com.github.mikephil.charting.utils.ViewPortHandler r14 = r6.mViewPortHandler
            float r14 = r14.contentWidth()
            float r14 = r14 / r10
            float r4 = r4 + r14
        L_0x00e7:
            com.github.mikephil.charting.components.Legend$LegendDirection r10 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r5 != r10) goto L_0x00ed
            r10 = r3
            goto L_0x00ee
        L_0x00ed:
            float r10 = -r3
        L_0x00ee:
            float r4 = r4 + r10
            com.github.mikephil.charting.components.Legend$LegendOrientation r10 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL
            if (r0 != r10) goto L_0x0125
            r14 = r9
            double r9 = (double) r4
            com.github.mikephil.charting.components.Legend$LegendDirection r4 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            r24 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r5 != r4) goto L_0x010e
            com.github.mikephil.charting.components.Legend r4 = r6.mLegend
            float r4 = r4.mNeededWidth
            float r4 = -r4
            r26 = r8
            double r7 = (double) r4
            java.lang.Double.isNaN(r7)
            double r7 = r7 / r24
            double r3 = (double) r3
            java.lang.Double.isNaN(r3)
            double r7 = r7 + r3
            goto L_0x011f
        L_0x010e:
            r26 = r8
            com.github.mikephil.charting.components.Legend r4 = r6.mLegend
            float r4 = r4.mNeededWidth
            double r7 = (double) r4
            java.lang.Double.isNaN(r7)
            double r7 = r7 / r24
            double r3 = (double) r3
            java.lang.Double.isNaN(r3)
            double r7 = r7 - r3
        L_0x011f:
            java.lang.Double.isNaN(r9)
            double r9 = r9 + r7
            float r3 = (float) r9
            goto L_0x0163
        L_0x0125:
            r26 = r8
            r14 = r9
            goto L_0x0149
        L_0x0129:
            r26 = r8
            r14 = r9
            com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL
            if (r0 != r4) goto L_0x0137
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.getChartWidth()
            goto L_0x013d
        L_0x0137:
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.contentRight()
        L_0x013d:
            float r4 = r4 - r3
            com.github.mikephil.charting.components.Legend$LegendDirection r3 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r5 != r3) goto L_0x0149
            com.github.mikephil.charting.components.Legend r3 = r6.mLegend
            float r3 = r3.mNeededWidth
            float r3 = r4 - r3
            goto L_0x0163
        L_0x0149:
            r7 = r4
            goto L_0x0164
        L_0x014b:
            r26 = r8
            r14 = r9
            com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL
            if (r0 != r4) goto L_0x0153
            goto L_0x015a
        L_0x0153:
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.contentLeft()
            float r3 = r3 + r4
        L_0x015a:
            com.github.mikephil.charting.components.Legend$LegendDirection r4 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r5 != r4) goto L_0x0163
            com.github.mikephil.charting.components.Legend r4 = r6.mLegend
            float r4 = r4.mNeededWidth
            float r3 = r3 + r4
        L_0x0163:
            r7 = r3
        L_0x0164:
            int[] r3 = com.github.mikephil.charting.renderer.LegendRenderer.C16491.f1564x9c9dbef
            int r0 = r0.ordinal()
            r0 = r3[r0]
            r3 = 1
            if (r0 == r3) goto L_0x0277
            r4 = 2
            if (r0 == r4) goto L_0x0174
            goto L_0x03da
        L_0x0174:
            int[] r0 = com.github.mikephil.charting.renderer.LegendRenderer.C16491.f1565xc926f1ec
            int r1 = r1.ordinal()
            r0 = r0[r1]
            if (r0 == r3) goto L_0x01b4
            if (r0 == r4) goto L_0x019c
            r1 = 3
            if (r0 == r1) goto L_0x0185
            r0 = 0
            goto L_0x01c1
        L_0x0185:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.getChartHeight()
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            float r2 = r2.mNeededHeight
            float r2 = r2 / r1
            float r0 = r0 - r2
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.getYOffset()
            float r0 = r0 + r1
            goto L_0x01c1
        L_0x019c:
            com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r0 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER
            if (r15 != r0) goto L_0x01a7
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.getChartHeight()
            goto L_0x01ad
        L_0x01a7:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.contentBottom()
        L_0x01ad:
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.mNeededHeight
            float r1 = r1 + r2
            float r0 = r0 - r1
            goto L_0x01c1
        L_0x01b4:
            com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r0 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER
            if (r15 != r0) goto L_0x01ba
            r0 = 0
            goto L_0x01c0
        L_0x01ba:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.contentTop()
        L_0x01c0:
            float r0 = r0 + r2
        L_0x01c1:
            r15 = r0
            r9 = 0
            r10 = 0
            r18 = 0
        L_0x01c6:
            int r0 = r12.length
            if (r9 >= r0) goto L_0x03da
            r4 = r12[r9]
            com.github.mikephil.charting.components.Legend$LegendForm r0 = r4.form
            com.github.mikephil.charting.components.Legend$LegendForm r1 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            if (r0 == r1) goto L_0x01d4
            r19 = 1
            goto L_0x01d6
        L_0x01d4:
            r19 = 0
        L_0x01d6:
            float r0 = r4.formSize
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 == 0) goto L_0x01e1
            r21 = r16
            goto L_0x01e9
        L_0x01e1:
            float r0 = r4.formSize
            float r0 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            r21 = r0
        L_0x01e9:
            if (r19 == 0) goto L_0x021b
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r5 != r0) goto L_0x01f2
            float r0 = r7 + r10
            goto L_0x01f6
        L_0x01f2:
            float r0 = r21 - r10
            float r0 = r7 - r0
        L_0x01f6:
            r22 = r0
            float r3 = r15 + r11
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            r0 = r34
            r1 = r35
            r24 = r2
            r2 = r22
            r8 = r17
            r17 = r4
            r27 = r11
            r11 = r5
            r5 = r24
            r0.drawForm(r1, r2, r3, r4, r5)
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r11 != r0) goto L_0x0218
            float r0 = r22 + r21
            r22 = r0
        L_0x0218:
            r0 = r17
            goto L_0x0223
        L_0x021b:
            r27 = r11
            r8 = r17
            r11 = r5
            r0 = r4
            r22 = r7
        L_0x0223:
            java.lang.String r1 = r0.label
            if (r1 == 0) goto L_0x0266
            if (r19 == 0) goto L_0x0235
            if (r18 != 0) goto L_0x0235
            com.github.mikephil.charting.components.Legend$LegendDirection r1 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r11 != r1) goto L_0x0231
            r1 = r13
            goto L_0x0232
        L_0x0231:
            float r1 = -r13
        L_0x0232:
            float r1 = r22 + r1
            goto L_0x023b
        L_0x0235:
            if (r18 == 0) goto L_0x0239
            r1 = r7
            goto L_0x023b
        L_0x0239:
            r1 = r22
        L_0x023b:
            com.github.mikephil.charting.components.Legend$LegendDirection r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r2) goto L_0x0249
            android.graphics.Paint r2 = r6.mLegendLabelPaint
            java.lang.String r3 = r0.label
            int r2 = com.github.mikephil.charting.utils.Utils.calcTextWidth(r2, r3)
            float r2 = (float) r2
            float r1 = r1 - r2
        L_0x0249:
            if (r18 != 0) goto L_0x0255
            float r2 = r15 + r26
            java.lang.String r0 = r0.label
            r5 = r35
            r6.drawLabel(r5, r1, r2, r0)
            goto L_0x0261
        L_0x0255:
            r5 = r35
            float r2 = r26 + r14
            float r15 = r15 + r2
            float r2 = r15 + r26
            java.lang.String r0 = r0.label
            r6.drawLabel(r5, r1, r2, r0)
        L_0x0261:
            float r0 = r26 + r14
            float r15 = r15 + r0
            r10 = 0
            goto L_0x026e
        L_0x0266:
            r5 = r35
            float r21 = r21 + r8
            float r10 = r10 + r21
            r18 = 1
        L_0x026e:
            int r9 = r9 + 1
            r17 = r8
            r5 = r11
            r11 = r27
            goto L_0x01c6
        L_0x0277:
            r27 = r11
            r8 = r17
            r11 = r5
            r5 = r35
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            java.util.List r9 = r0.getCalculatedLineSizes()
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            java.util.List r10 = r0.getCalculatedLabelSizes()
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            java.util.List r4 = r0.getCalculatedLabelBreakPoints()
            int[] r0 = com.github.mikephil.charting.renderer.LegendRenderer.C16491.f1565xc926f1ec
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r3 = 1
            if (r0 == r3) goto L_0x02c0
            r1 = 2
            if (r0 == r1) goto L_0x02b3
            r1 = 3
            if (r0 == r1) goto L_0x02a3
            r2 = 0
            goto L_0x02c0
        L_0x02a3:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.getChartHeight()
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.mNeededHeight
            float r0 = r0 - r1
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            float r2 = r2 + r0
            goto L_0x02c0
        L_0x02b3:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.getChartHeight()
            float r0 = r0 - r2
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.mNeededHeight
            float r2 = r0 - r1
        L_0x02c0:
            int r1 = r12.length
            r0 = r2
            r17 = r7
            r2 = 0
            r3 = 0
        L_0x02c6:
            if (r2 >= r1) goto L_0x03da
            r18 = r8
            r8 = r12[r2]
            r20 = r1
            com.github.mikephil.charting.components.Legend$LegendForm r1 = r8.form
            com.github.mikephil.charting.components.Legend$LegendForm r5 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            if (r1 == r5) goto L_0x02d7
            r22 = 1
            goto L_0x02d9
        L_0x02d7:
            r22 = 0
        L_0x02d9:
            float r1 = r8.formSize
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 == 0) goto L_0x02e4
            r24 = r16
            goto L_0x02ec
        L_0x02e4:
            float r1 = r8.formSize
            float r1 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r1)
            r24 = r1
        L_0x02ec:
            int r1 = r4.size()
            if (r2 >= r1) goto L_0x0306
            java.lang.Object r1 = r4.get(r2)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0306
            float r1 = r26 + r14
            float r0 = r0 + r1
            r28 = r0
            r17 = r7
            goto L_0x0308
        L_0x0306:
            r28 = r0
        L_0x0308:
            int r0 = (r17 > r7 ? 1 : (r17 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x0335
            com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r0 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER
            if (r15 != r0) goto L_0x0335
            int r0 = r9.size()
            if (r3 >= r0) goto L_0x0335
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r0) goto L_0x0323
            java.lang.Object r0 = r9.get(r3)
            com.github.mikephil.charting.utils.FSize r0 = (com.github.mikephil.charting.utils.FSize) r0
            float r0 = r0.width
            goto L_0x032c
        L_0x0323:
            java.lang.Object r0 = r9.get(r3)
            com.github.mikephil.charting.utils.FSize r0 = (com.github.mikephil.charting.utils.FSize) r0
            float r0 = r0.width
            float r0 = -r0
        L_0x032c:
            r19 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r19
            float r17 = r17 + r0
            int r3 = r3 + 1
            goto L_0x0337
        L_0x0335:
            r19 = 1073741824(0x40000000, float:2.0)
        L_0x0337:
            r29 = r3
            java.lang.String r0 = r8.label
            if (r0 != 0) goto L_0x0340
            r30 = 1
            goto L_0x0342
        L_0x0340:
            r30 = 0
        L_0x0342:
            if (r22 == 0) goto L_0x036a
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r0) goto L_0x034a
            float r17 = r17 - r24
        L_0x034a:
            float r3 = r28 + r27
            com.github.mikephil.charting.components.Legend r5 = r6.mLegend
            r0 = r34
            r1 = r35
            r31 = r7
            r7 = r2
            r2 = r17
            r23 = 1
            r32 = r4
            r4 = r8
            r33 = r9
            r9 = r35
            r0.drawForm(r1, r2, r3, r4, r5)
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r11 != r0) goto L_0x0375
            float r17 = r17 + r24
            goto L_0x0375
        L_0x036a:
            r32 = r4
            r31 = r7
            r33 = r9
            r23 = 1
            r9 = r35
            r7 = r2
        L_0x0375:
            if (r30 != 0) goto L_0x03b7
            if (r22 == 0) goto L_0x0382
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r0) goto L_0x037f
            float r0 = -r13
            goto L_0x0380
        L_0x037f:
            r0 = r13
        L_0x0380:
            float r17 = r17 + r0
        L_0x0382:
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r0) goto L_0x0390
            java.lang.Object r0 = r10.get(r7)
            com.github.mikephil.charting.utils.FSize r0 = (com.github.mikephil.charting.utils.FSize) r0
            float r0 = r0.width
            float r17 = r17 - r0
        L_0x0390:
            r0 = r17
            float r1 = r28 + r26
            java.lang.String r2 = r8.label
            r6.drawLabel(r9, r0, r1, r2)
            com.github.mikephil.charting.components.Legend$LegendDirection r1 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r11 != r1) goto L_0x03a6
            java.lang.Object r1 = r10.get(r7)
            com.github.mikephil.charting.utils.FSize r1 = (com.github.mikephil.charting.utils.FSize) r1
            float r1 = r1.width
            float r0 = r0 + r1
        L_0x03a6:
            com.github.mikephil.charting.components.Legend$LegendDirection r1 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r1) goto L_0x03ae
            r1 = r21
            float r2 = -r1
            goto L_0x03b1
        L_0x03ae:
            r1 = r21
            r2 = r1
        L_0x03b1:
            float r0 = r0 + r2
            r17 = r0
            r0 = r18
            goto L_0x03c6
        L_0x03b7:
            r1 = r21
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r0) goto L_0x03c1
            r0 = r18
            float r4 = -r0
            goto L_0x03c4
        L_0x03c1:
            r0 = r18
            r4 = r0
        L_0x03c4:
            float r17 = r17 + r4
        L_0x03c6:
            int r2 = r7 + 1
            r8 = r0
            r21 = r1
            r5 = r9
            r1 = r20
            r0 = r28
            r3 = r29
            r7 = r31
            r4 = r32
            r9 = r33
            goto L_0x02c6
        L_0x03da:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.LegendRenderer.renderLegend(android.graphics.Canvas):void");
    }

    /* access modifiers changed from: protected */
    public void drawForm(Canvas canvas, float f, float f2, LegendEntry legendEntry, Legend legend) {
        if (legendEntry.formColor != 1122868 && legendEntry.formColor != 1122867 && legendEntry.formColor != 0) {
            int save = canvas.save();
            Legend.LegendForm legendForm = legendEntry.form;
            if (legendForm == Legend.LegendForm.DEFAULT) {
                legendForm = legend.getForm();
            }
            this.mLegendFormPaint.setColor(legendEntry.formColor);
            float convertDpToPixel = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? legend.getFormSize() : legendEntry.formSize);
            float f3 = convertDpToPixel / 2.0f;
            switch (legendForm) {
                case DEFAULT:
                case CIRCLE:
                    this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(f + f3, f2, f3, this.mLegendFormPaint);
                    break;
                case SQUARE:
                    this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(f, f2 - f3, f + convertDpToPixel, f2 + f3, this.mLegendFormPaint);
                    break;
                case LINE:
                    float convertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formLineWidth) ? legend.getFormLineWidth() : legendEntry.formLineWidth);
                    DashPathEffect formLineDashEffect = legendEntry.formLineDashEffect == null ? legend.getFormLineDashEffect() : legendEntry.formLineDashEffect;
                    this.mLegendFormPaint.setStyle(Paint.Style.STROKE);
                    this.mLegendFormPaint.setStrokeWidth(convertDpToPixel2);
                    this.mLegendFormPaint.setPathEffect(formLineDashEffect);
                    this.mLineFormPath.reset();
                    this.mLineFormPath.moveTo(f, f2);
                    this.mLineFormPath.lineTo(f + convertDpToPixel, f2);
                    canvas.drawPath(this.mLineFormPath, this.mLegendFormPaint);
                    break;
            }
            canvas.restoreToCount(save);
        }
    }

    /* renamed from: com.github.mikephil.charting.renderer.LegendRenderer$1 */
    static /* synthetic */ class C16491 {

        /* renamed from: $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment */
        static final /* synthetic */ int[] f1563x2787f53e = new int[Legend.LegendHorizontalAlignment.values().length];

        /* renamed from: $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation */
        static final /* synthetic */ int[] f1564x9c9dbef = new int[Legend.LegendOrientation.values().length];

        /* renamed from: $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment */
        static final /* synthetic */ int[] f1565xc926f1ec = new int[Legend.LegendVerticalAlignment.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(33:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|25|26|27|28|29|30|31|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|25|26|27|28|29|30|31|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(35:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|25|26|27|28|29|30|31|33|34|35|36|37|38|40) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00a2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00ac */
        static {
            /*
                com.github.mikephil.charting.components.Legend$LegendForm[] r0 = com.github.mikephil.charting.components.Legend.LegendForm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1562xfbec3b85 = r0
                r0 = 1
                int[] r1 = f1562xfbec3b85     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.github.mikephil.charting.components.Legend$LegendForm r2 = com.github.mikephil.charting.components.Legend.LegendForm.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f1562xfbec3b85     // Catch:{ NoSuchFieldError -> 0x001f }
                com.github.mikephil.charting.components.Legend$LegendForm r3 = com.github.mikephil.charting.components.Legend.LegendForm.EMPTY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f1562xfbec3b85     // Catch:{ NoSuchFieldError -> 0x002a }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.DEFAULT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = f1562xfbec3b85     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.CIRCLE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r3 = f1562xfbec3b85     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.SQUARE     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r3 = f1562xfbec3b85     // Catch:{ NoSuchFieldError -> 0x004b }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.LINE     // Catch:{ NoSuchFieldError -> 0x004b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                com.github.mikephil.charting.components.Legend$LegendOrientation[] r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f1564x9c9dbef = r3
                int[] r3 = f1564x9c9dbef     // Catch:{ NoSuchFieldError -> 0x005e }
                com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x005e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                int[] r3 = f1564x9c9dbef     // Catch:{ NoSuchFieldError -> 0x0068 }
                com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0068 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0068 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0068 }
            L_0x0068:
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment[] r3 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f1565xc926f1ec = r3
                int[] r3 = f1565xc926f1ec     // Catch:{ NoSuchFieldError -> 0x007b }
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.TOP     // Catch:{ NoSuchFieldError -> 0x007b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r3 = f1565xc926f1ec     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r3 = f1565xc926f1ec     // Catch:{ NoSuchFieldError -> 0x008f }
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.CENTER     // Catch:{ NoSuchFieldError -> 0x008f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment[] r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f1563x2787f53e = r3
                int[] r3 = f1563x2787f53e     // Catch:{ NoSuchFieldError -> 0x00a2 }
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.LEFT     // Catch:{ NoSuchFieldError -> 0x00a2 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a2 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x00a2 }
            L_0x00a2:
                int[] r0 = f1563x2787f53e     // Catch:{ NoSuchFieldError -> 0x00ac }
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.RIGHT     // Catch:{ NoSuchFieldError -> 0x00ac }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ac }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x00ac }
            L_0x00ac:
                int[] r0 = f1563x2787f53e     // Catch:{ NoSuchFieldError -> 0x00b6 }
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r1 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER     // Catch:{ NoSuchFieldError -> 0x00b6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b6 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b6 }
            L_0x00b6:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.LegendRenderer.C16491.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void drawLabel(Canvas canvas, float f, float f2, String str) {
        canvas.drawText(str, f, f2, this.mLegendLabelPaint);
    }
}
