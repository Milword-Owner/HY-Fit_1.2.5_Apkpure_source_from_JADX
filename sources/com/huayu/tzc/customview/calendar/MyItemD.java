package com.huayu.tzc.customview.calendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.huayu.tzc.customview.calendar.CalendarList;

public class MyItemD extends RecyclerView.ItemDecoration {
    Paint colorPaint = new Paint();
    Paint linePaint = new Paint();
    Paint paint = new Paint();

    public MyItemD() {
        this.paint.setColor(Color.parseColor("#ffffff"));
        this.paint.setStyle(Paint.Style.FILL);
        this.colorPaint.setColor(Color.parseColor("#ff6600"));
        this.colorPaint.setAntiAlias(true);
        this.linePaint.setAntiAlias(true);
        this.linePaint.setColor(Color.parseColor("#dddddd"));
    }

    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        String str;
        int i;
        RecyclerView recyclerView2 = recyclerView;
        super.onDrawOver(canvas, recyclerView, state);
        if (recyclerView.getChildCount() > 0) {
            float f = recyclerView.getContext().getResources().getDisplayMetrics().density;
            int i2 = (int) ((((float) 50) * f) + 0.5f);
            CalendarList.CalendarAdapter calendarAdapter = (CalendarList.CalendarAdapter) recyclerView.getAdapter();
            String monthStr = calendarAdapter.data.get(recyclerView2.getChildAdapterPosition(recyclerView2.getChildAt(0))).getMonthStr();
            int i3 = 0;
            while (true) {
                if (i3 >= recyclerView.getChildCount()) {
                    str = "";
                    i = 0;
                    break;
                }
                View childAt = recyclerView2.getChildAt(i3);
                if (2 == recyclerView2.getChildViewHolder(childAt).getItemViewType()) {
                    str = calendarAdapter.data.get(recyclerView2.getChildAdapterPosition(childAt)).getMonthStr();
                    i = childAt.getTop();
                    break;
                }
                i3++;
            }
            int i4 = 0 - ((str.equals(monthStr) || i >= i2) ? 0 : i2 - i);
            int i5 = i4 + i2;
            canvas.drawRect((float) recyclerView.getLeft(), (float) i4, (float) recyclerView.getRight(), (float) i5, this.paint);
            this.colorPaint.setTextAlign(Paint.Align.CENTER);
            this.colorPaint.setTextSize((f * 15.0f) + 0.5f);
            canvas.drawText(monthStr, (float) (recyclerView.getRight() / 2), (float) (i5 / 2), this.colorPaint);
        }
    }
}
