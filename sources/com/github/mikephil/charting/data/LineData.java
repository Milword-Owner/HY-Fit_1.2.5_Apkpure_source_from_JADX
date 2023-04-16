package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.List;

public class LineData extends BarLineScatterCandleBubbleData<ILineDataSet> {
    public LineData() {
    }

    public LineData(ILineDataSet... iLineDataSetArr) {
        super((T[]) iLineDataSetArr);
    }

    public LineData(List<ILineDataSet> list) {
        super(list);
    }
}
