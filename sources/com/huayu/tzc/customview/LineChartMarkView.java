package com.huayu.tzc.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.bean.Trend;
import java.util.List;

public class LineChartMarkView extends MarkerView {
    private List<Trend> trendList;
    private TextView tv_data = ((TextView) findViewById(C2128R.C2131id.tv_data));
    private TextView tv_date = ((TextView) findViewById(C2128R.C2131id.tv_date));

    public LineChartMarkView(Context context, List<Trend> list) {
        super(context, C2128R.C2133layout.makeview);
        this.trendList = list;
    }

    @SuppressLint({"SetTextI18n"})
    public void refreshContent(Entry entry, Highlight highlight) {
        this.tv_date.setText(this.trendList.get((int) entry.getX()).getMeasuredate());
        this.tv_data.setText(String.valueOf(entry.getY()));
        super.refreshContent(entry, highlight);
        super.refreshContent(entry, highlight);
    }

    public MPPointF getOffset() {
        return new MPPointF((float) (-(getWidth() / 2)), (float) ((-getHeight()) - 20));
    }
}
