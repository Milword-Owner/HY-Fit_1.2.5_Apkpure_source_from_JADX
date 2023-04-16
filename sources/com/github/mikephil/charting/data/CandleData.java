package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import java.util.List;

public class CandleData extends BarLineScatterCandleBubbleData<ICandleDataSet> {
    public CandleData() {
    }

    public CandleData(List<ICandleDataSet> list) {
        super(list);
    }

    public CandleData(ICandleDataSet... iCandleDataSetArr) {
        super((T[]) iCandleDataSetArr);
    }
}
