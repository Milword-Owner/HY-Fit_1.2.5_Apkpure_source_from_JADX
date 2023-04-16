package com.github.mikephil.charting.data;

import android.graphics.Color;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.util.ArrayList;
import java.util.List;

public class BarDataSet extends BarLineScatterCandleBubbleDataSet<BarEntry> implements IBarDataSet {
    private int mBarBorderColor = ViewCompat.MEASURED_STATE_MASK;
    private float mBarBorderWidth = 0.0f;
    private int mBarShadowColor = Color.rgb(215, 215, 215);
    private int mEntryCountStacks = 0;
    private int mHighLightAlpha = 120;
    private String[] mStackLabels = {"Stack"};
    private int mStackSize = 1;

    public BarDataSet(List<BarEntry> list, String str) {
        super(list, str);
        this.mHighLightColor = Color.rgb(0, 0, 0);
        calcStackSize(list);
        calcEntryCountIncludingStacks(list);
    }

    public DataSet<BarEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mValues.size(); i++) {
            arrayList.add(((BarEntry) this.mValues.get(i)).copy());
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, getLabel());
        copy(barDataSet);
        return barDataSet;
    }

    /* access modifiers changed from: protected */
    public void copy(BarDataSet barDataSet) {
        super.copy(barDataSet);
        barDataSet.mStackSize = this.mStackSize;
        barDataSet.mBarShadowColor = this.mBarShadowColor;
        barDataSet.mBarBorderWidth = this.mBarBorderWidth;
        barDataSet.mStackLabels = this.mStackLabels;
        barDataSet.mHighLightAlpha = this.mHighLightAlpha;
    }

    private void calcEntryCountIncludingStacks(List<BarEntry> list) {
        this.mEntryCountStacks = 0;
        for (int i = 0; i < list.size(); i++) {
            float[] yVals = list.get(i).getYVals();
            if (yVals == null) {
                this.mEntryCountStacks++;
            } else {
                this.mEntryCountStacks += yVals.length;
            }
        }
    }

    private void calcStackSize(List<BarEntry> list) {
        for (int i = 0; i < list.size(); i++) {
            float[] yVals = list.get(i).getYVals();
            if (yVals != null && yVals.length > this.mStackSize) {
                this.mStackSize = yVals.length;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void calcMinMax(BarEntry barEntry) {
        if (barEntry != null && !Float.isNaN(barEntry.getY())) {
            if (barEntry.getYVals() == null) {
                if (barEntry.getY() < this.mYMin) {
                    this.mYMin = barEntry.getY();
                }
                if (barEntry.getY() > this.mYMax) {
                    this.mYMax = barEntry.getY();
                }
            } else {
                if ((-barEntry.getNegativeSum()) < this.mYMin) {
                    this.mYMin = -barEntry.getNegativeSum();
                }
                if (barEntry.getPositiveSum() > this.mYMax) {
                    this.mYMax = barEntry.getPositiveSum();
                }
            }
            calcMinMaxX(barEntry);
        }
    }

    public int getStackSize() {
        return this.mStackSize;
    }

    public boolean isStacked() {
        return this.mStackSize > 1;
    }

    public int getEntryCountStacks() {
        return this.mEntryCountStacks;
    }

    public void setBarShadowColor(int i) {
        this.mBarShadowColor = i;
    }

    public int getBarShadowColor() {
        return this.mBarShadowColor;
    }

    public void setBarBorderWidth(float f) {
        this.mBarBorderWidth = f;
    }

    public float getBarBorderWidth() {
        return this.mBarBorderWidth;
    }

    public void setBarBorderColor(int i) {
        this.mBarBorderColor = i;
    }

    public int getBarBorderColor() {
        return this.mBarBorderColor;
    }

    public void setHighLightAlpha(int i) {
        this.mHighLightAlpha = i;
    }

    public int getHighLightAlpha() {
        return this.mHighLightAlpha;
    }

    public void setStackLabels(String[] strArr) {
        this.mStackLabels = strArr;
    }

    public String[] getStackLabels() {
        return this.mStackLabels;
    }
}
