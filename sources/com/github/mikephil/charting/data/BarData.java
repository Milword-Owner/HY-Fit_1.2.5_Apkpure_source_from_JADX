package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.util.List;

public class BarData extends BarLineScatterCandleBubbleData<IBarDataSet> {
    private float mBarWidth = 0.85f;

    public BarData() {
    }

    public BarData(IBarDataSet... iBarDataSetArr) {
        super((T[]) iBarDataSetArr);
    }

    public BarData(List<IBarDataSet> list) {
        super(list);
    }

    public void setBarWidth(float f) {
        this.mBarWidth = f;
    }

    public float getBarWidth() {
        return this.mBarWidth;
    }

    public void groupBars(float f, float f2, float f3) {
        BarEntry barEntry;
        if (this.mDataSets.size() > 1) {
            int entryCount = ((IBarDataSet) getMaxEntryCountSet()).getEntryCount();
            float f4 = f2 / 2.0f;
            float f5 = f3 / 2.0f;
            float f6 = this.mBarWidth / 2.0f;
            float groupWidth = getGroupWidth(f2, f3);
            for (int i = 0; i < entryCount; i++) {
                float f7 = f + f4;
                for (IBarDataSet iBarDataSet : this.mDataSets) {
                    float f8 = f7 + f5 + f6;
                    if (i < iBarDataSet.getEntryCount() && (barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i)) != null) {
                        barEntry.setX(f8);
                    }
                    f7 = f8 + f6 + f5;
                }
                float f9 = f7 + f4;
                float f10 = groupWidth - (f9 - f);
                if (f10 > 0.0f || f10 < 0.0f) {
                    f9 += f10;
                }
                f = f9;
            }
            notifyDataChanged();
            return;
        }
        throw new RuntimeException("BarData needs to hold at least 2 BarDataSets to allow grouping.");
    }

    public float getGroupWidth(float f, float f2) {
        return (((float) this.mDataSets.size()) * (this.mBarWidth + f2)) + f;
    }
}
