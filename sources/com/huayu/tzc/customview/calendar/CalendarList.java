package com.huayu.tzc.customview.calendar;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huayu.tzc.C2128R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarList extends FrameLayout {
    CalendarAdapter adapter;
    private DateBean endDate;
    private Context mContext;
    OnDateSelected onDateSelected;
    RecyclerView recyclerView;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private DateBean startDate;

    public interface OnDateSelected {
        void selected(String str, String str2);
    }

    public CalendarList(Context context) {
        super(context);
        this.mContext = context;
        init(context);
    }

    public DateBean getStartDate() {
        return this.startDate;
    }

    public DateBean getEndDate() {
        return this.endDate;
    }

    public CalendarList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init(context);
    }

    public CalendarList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        init(context);
    }

    private void init(Context context) {
        addView(LayoutInflater.from(context).inflate(C2128R.C2133layout.item_calendar, this, false));
        this.recyclerView = (RecyclerView) findViewById(C2128R.C2131id.recyclerView);
        this.adapter = new CalendarAdapter(this.mContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int i) {
                return DateBean.item_type_month == CalendarList.this.adapter.data.get(i).getItemType() ? 7 : 1;
            }
        });
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.data.addAll(days());
        this.recyclerView.addItemDecoration(new MyItemD());
        this.adapter.setOnRecyclerviewItemClick(new CalendarAdapter.OnRecyclerviewItemClick() {
            public void onItemClick(View view, int i) {
                CalendarList calendarList = CalendarList.this;
                calendarList.onClick(calendarList.adapter.data.get(i));
            }
        });
    }

    /* access modifiers changed from: private */
    public void onClick(DateBean dateBean) {
        if (dateBean.getItemType() != DateBean.item_type_month && !TextUtils.isEmpty(dateBean.getDay())) {
            DateBean dateBean2 = this.startDate;
            if (dateBean2 == null) {
                this.startDate = dateBean;
                dateBean.setItemState(DateBean.ITEM_STATE_BEGIN_DATE);
            } else {
                DateBean dateBean3 = this.endDate;
                if (dateBean3 == null) {
                    if (dateBean2 != dateBean) {
                        if (dateBean.getDate().getTime() < this.startDate.getDate().getTime()) {
                            this.startDate.setItemState(DateBean.ITEM_STATE_NORMAL);
                            this.startDate = dateBean;
                            this.startDate.setItemState(DateBean.ITEM_STATE_BEGIN_DATE);
                        } else {
                            this.endDate = dateBean;
                            this.endDate.setItemState(DateBean.ITEM_STATE_END_DATE);
                            setState();
                            OnDateSelected onDateSelected2 = this.onDateSelected;
                            if (onDateSelected2 != null) {
                                onDateSelected2.selected(this.simpleDateFormat.format(this.startDate.getDate()), this.simpleDateFormat.format(this.endDate.getDate()));
                            }
                        }
                    }
                } else if (!(dateBean2 == null || dateBean3 == null)) {
                    clearState();
                    this.startDate.setItemState(DateBean.ITEM_STATE_NORMAL);
                    this.startDate = dateBean;
                    this.startDate.setItemState(DateBean.ITEM_STATE_BEGIN_DATE);
                    this.endDate.setItemState(DateBean.ITEM_STATE_NORMAL);
                    this.endDate = null;
                }
            }
            this.adapter.notifyDataSetChanged();
        }
    }

    private void setState() {
        if (this.endDate != null && this.startDate != null) {
            int indexOf = this.adapter.data.indexOf(this.endDate);
            for (int indexOf2 = this.adapter.data.indexOf(this.startDate) + 1; indexOf2 < indexOf; indexOf2++) {
                DateBean dateBean = this.adapter.data.get(indexOf2);
                if (!TextUtils.isEmpty(dateBean.getDay())) {
                    dateBean.setItemState(DateBean.ITEM_STATE_SELECTED);
                }
            }
        }
    }

    private void clearState() {
        if (this.endDate != null && this.startDate != null) {
            int indexOf = this.adapter.data.indexOf(this.endDate);
            for (int indexOf2 = this.adapter.data.indexOf(this.startDate) + 1; indexOf2 < indexOf; indexOf2++) {
                this.adapter.data.get(indexOf2).setItemState(DateBean.ITEM_STATE_NORMAL);
            }
        }
    }

    public static class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        ArrayList<DateBean> data = new ArrayList<>();
        private Context mContext;
        /* access modifiers changed from: private */
        public OnRecyclerviewItemClick onRecyclerviewItemClick;

        public interface OnRecyclerviewItemClick {
            void onItemClick(View view, int i);
        }

        public CalendarAdapter() {
        }

        public CalendarAdapter(Context context) {
            this.mContext = context;
        }

        public OnRecyclerviewItemClick getOnRecyclerviewItemClick() {
            return this.onRecyclerviewItemClick;
        }

        public void setOnRecyclerviewItemClick(OnRecyclerviewItemClick onRecyclerviewItemClick2) {
            this.onRecyclerviewItemClick = onRecyclerviewItemClick2;
        }

        public int getItemCount() {
            return this.data.size();
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (i == DateBean.item_type_day) {
                final DayViewHolder dayViewHolder = new DayViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C2128R.C2133layout.item_day, viewGroup, false));
                dayViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (CalendarAdapter.this.onRecyclerviewItemClick != null) {
                            CalendarAdapter.this.onRecyclerviewItemClick.onItemClick(view, dayViewHolder.getLayoutPosition());
                        }
                    }
                });
                return dayViewHolder;
            } else if (i != DateBean.item_type_month) {
                return null;
            } else {
                final MonthViewHolder monthViewHolder = new MonthViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C2128R.C2133layout.item_month, viewGroup, false));
                monthViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (CalendarAdapter.this.onRecyclerviewItemClick != null) {
                            CalendarAdapter.this.onRecyclerviewItemClick.onItemClick(view, monthViewHolder.getLayoutPosition());
                        }
                    }
                });
                return monthViewHolder;
            }
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof MonthViewHolder) {
                ((MonthViewHolder) viewHolder).tv_month.setText(this.data.get(i).getMonthStr());
                return;
            }
            DayViewHolder dayViewHolder = (DayViewHolder) viewHolder;
            dayViewHolder.tv_day.setText(this.data.get(i).getDay());
            dayViewHolder.tv_check_in_check_out.setVisibility(8);
            DateBean dateBean = this.data.get(i);
            if (dateBean.getItemState() == DateBean.ITEM_STATE_BEGIN_DATE || dateBean.getItemState() == DateBean.ITEM_STATE_END_DATE) {
                dayViewHolder.itemView.setBackgroundColor(Color.parseColor("#FF3A84FF"));
                dayViewHolder.tv_day.setTextColor(-1);
                dayViewHolder.tv_check_in_check_out.setVisibility(0);
                if (dateBean.getItemState() == DateBean.ITEM_STATE_END_DATE) {
                    dayViewHolder.tv_check_in_check_out.setText("●");
                } else {
                    dayViewHolder.tv_check_in_check_out.setText("●");
                }
            } else if (dateBean.getItemState() == DateBean.ITEM_STATE_SELECTED) {
                dayViewHolder.itemView.setBackgroundColor(Color.parseColor("#336C9EF1"));
            } else {
                dayViewHolder.itemView.setBackgroundColor(-1);
                dayViewHolder.tv_day.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
        }

        public int getItemViewType(int i) {
            return this.data.get(i).getItemType();
        }

        public class DayViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_check_in_check_out;
            public TextView tv_day;

            public DayViewHolder(@NonNull View view) {
                super(view);
                this.tv_day = (TextView) view.findViewById(C2128R.C2131id.tv_day);
                this.tv_check_in_check_out = (TextView) view.findViewById(C2128R.C2131id.tv_check_in_check_out);
            }
        }

        public class MonthViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_month;

            public MonthViewHolder(@NonNull View view) {
                super(view);
                this.tv_month = (TextView) view.findViewById(C2128R.C2131id.tv_month);
            }
        }
    }

    private List<DateBean> days() {
        ArrayList arrayList = new ArrayList();
        try {
            Calendar instance = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM");
            Date date = new Date();
            date.setTime(1591009617000L);
            instance.setTime(date);
            Date date2 = new Date(System.currentTimeMillis());
            Log.d("TAG", "startDate:" + date + "----------endDate:" + date2);
            Log.d("TAG", "startDate:" + simpleDateFormat2.format(date) + "----------endDate:" + simpleDateFormat2.format(date2));
            String format = simpleDateFormat2.format(date2);
            Date parse = simpleDateFormat2.parse(format);
            String format2 = simpleDateFormat2.format(date);
            instance.setTime(simpleDateFormat2.parse(format2));
            Log.d("TAG", "startDateStr:" + format2 + "---------endDate:" + simpleDateFormat2.format(parse));
            Log.d("TAG", "endDateStr:" + format + "---------endDate:" + simpleDateFormat2.format(parse));
            instance.set(5, 1);
            Calendar instance2 = Calendar.getInstance();
            while (instance.getTimeInMillis() <= parse.getTime()) {
                DateBean dateBean = new DateBean();
                dateBean.setDate(instance.getTime());
                dateBean.setMonthStr(simpleDateFormat3.format(dateBean.getDate()));
                dateBean.setItemType(DateBean.item_type_month);
                arrayList.add(dateBean);
                instance2.setTime(instance.getTime());
                instance2.set(5, 1);
                Date time = instance.getTime();
                instance2.add(2, 1);
                instance2.add(5, -1);
                Date time2 = instance2.getTime();
                instance2.set(5, 1);
                Log.d("TAG", "月份的开始日期:" + simpleDateFormat2.format(time) + "---------结束日期:" + simpleDateFormat2.format(time2));
                while (instance2.getTimeInMillis() <= time2.getTime()) {
                    if (instance2.get(5) == 1) {
                        switch (instance2.get(7)) {
                            case 1:
                                break;
                            case 2:
                                addDatePlaceholder(arrayList, 1, dateBean.getMonthStr());
                                break;
                            case 3:
                                addDatePlaceholder(arrayList, 2, dateBean.getMonthStr());
                                break;
                            case 4:
                                addDatePlaceholder(arrayList, 3, dateBean.getMonthStr());
                                break;
                            case 5:
                                addDatePlaceholder(arrayList, 4, dateBean.getMonthStr());
                                break;
                            case 6:
                                addDatePlaceholder(arrayList, 5, dateBean.getMonthStr());
                                break;
                            case 7:
                                addDatePlaceholder(arrayList, 6, dateBean.getMonthStr());
                                break;
                        }
                    }
                    DateBean dateBean2 = new DateBean();
                    dateBean2.setDate(instance2.getTime());
                    dateBean2.setDay(instance2.get(5) + "");
                    dateBean2.setMonthStr(dateBean.getMonthStr());
                    arrayList.add(dateBean2);
                    if (instance2.getTimeInMillis() == time2.getTime()) {
                        switch (instance2.get(7)) {
                            case 1:
                                addDatePlaceholder(arrayList, 6, dateBean.getMonthStr());
                                break;
                            case 2:
                                addDatePlaceholder(arrayList, 5, dateBean.getMonthStr());
                                break;
                            case 3:
                                addDatePlaceholder(arrayList, 4, dateBean.getMonthStr());
                                break;
                            case 4:
                                addDatePlaceholder(arrayList, 3, dateBean.getMonthStr());
                                break;
                            case 5:
                                addDatePlaceholder(arrayList, 2, dateBean.getMonthStr());
                                break;
                            case 6:
                                addDatePlaceholder(arrayList, 1, dateBean.getMonthStr());
                                break;
                        }
                    }
                    instance2.add(5, 1);
                }
                instance.add(2, 1);
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    private void addDatePlaceholder(List<DateBean> list, int i, String str) {
        for (int i2 = 0; i2 < i; i2++) {
            DateBean dateBean = new DateBean();
            dateBean.setMonthStr(str);
            list.add(dateBean);
        }
    }

    public void setOnDateSelected(OnDateSelected onDateSelected2) {
        this.onDateSelected = onDateSelected2;
    }
}
