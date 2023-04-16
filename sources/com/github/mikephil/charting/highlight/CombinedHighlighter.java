package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.CombinedDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.List;

public class CombinedHighlighter extends ChartHighlighter<CombinedDataProvider> implements IHighlighter {
    protected BarHighlighter barHighlighter;

    public CombinedHighlighter(CombinedDataProvider combinedDataProvider, BarDataProvider barDataProvider) {
        super(combinedDataProvider);
        this.barHighlighter = barDataProvider.getBarData() == null ? null : new BarHighlighter(barDataProvider);
    }

    /* access modifiers changed from: protected */
    public List<Highlight> getHighlightsAtXValue(float f, float f2, float f3) {
        this.mHighlightBuffer.clear();
        List<BarLineScatterCandleBubbleData> allData = ((CombinedDataProvider) this.mChart).getCombinedData().getAllData();
        for (int i = 0; i < allData.size(); i++) {
            ChartData chartData = allData.get(i);
            BarHighlighter barHighlighter2 = this.barHighlighter;
            if (barHighlighter2 == null || !(chartData instanceof BarData)) {
                int dataSetCount = chartData.getDataSetCount();
                for (int i2 = 0; i2 < dataSetCount; i2++) {
                    IDataSet dataSetByIndex = allData.get(i).getDataSetByIndex(i2);
                    if (dataSetByIndex.isHighlightEnabled()) {
                        for (Highlight next : buildHighlights(dataSetByIndex, i2, f, DataSet.Rounding.CLOSEST)) {
                            next.setDataIndex(i);
                            this.mHighlightBuffer.add(next);
                        }
                    }
                }
            } else {
                Highlight highlight = barHighlighter2.getHighlight(f2, f3);
                if (highlight != null) {
                    highlight.setDataIndex(i);
                    this.mHighlightBuffer.add(highlight);
                }
            }
        }
        return this.mHighlightBuffer;
    }
}
