package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class XShapeRenderer implements IShapeRenderer {
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f, float f2, Paint paint) {
        float scatterShapeSize = iScatterDataSet.getScatterShapeSize() / 2.0f;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.convertDpToPixel(1.0f));
        float f3 = f - scatterShapeSize;
        float f4 = f + scatterShapeSize;
        Canvas canvas2 = canvas;
        float f5 = f2 - scatterShapeSize;
        float f6 = scatterShapeSize + f2;
        Paint paint2 = paint;
        canvas2.drawLine(f3, f5, f4, f6, paint2);
        canvas2.drawLine(f4, f5, f3, f6, paint2);
    }
}
