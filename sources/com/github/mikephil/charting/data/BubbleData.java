package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import java.util.List;

public class BubbleData extends BarLineScatterCandleBubbleData<IBubbleDataSet> {
    public BubbleData() {
    }

    public BubbleData(IBubbleDataSet... iBubbleDataSetArr) {
        super((T[]) iBubbleDataSetArr);
    }

    public BubbleData(List<IBubbleDataSet> list) {
        super(list);
    }

    public void setHighlightCircleWidth(float f) {
        for (IBubbleDataSet highlightCircleWidth : this.mDataSets) {
            highlightCircleWidth.setHighlightCircleWidth(f);
        }
    }
}
