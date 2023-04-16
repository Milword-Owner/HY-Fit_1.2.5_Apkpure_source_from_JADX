package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import com.google.android.material.C1722R;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;

final class CalendarStyle {
    @NonNull
    final CalendarItemStyle day;
    @NonNull
    final CalendarItemStyle invalidDay;
    @NonNull
    final Paint rangeFill = new Paint();
    @NonNull
    final CalendarItemStyle selectedDay;
    @NonNull
    final CalendarItemStyle selectedYear;
    @NonNull
    final CalendarItemStyle todayDay;
    @NonNull
    final CalendarItemStyle todayYear;
    @NonNull
    final CalendarItemStyle year;

    CalendarStyle(@NonNull Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(context, C1722R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), C1722R.styleable.MaterialCalendar);
        this.day = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(C1722R.styleable.MaterialCalendar_dayStyle, 0));
        this.invalidDay = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(C1722R.styleable.MaterialCalendar_dayInvalidStyle, 0));
        this.selectedDay = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(C1722R.styleable.MaterialCalendar_daySelectedStyle, 0));
        this.todayDay = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(C1722R.styleable.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, C1722R.styleable.MaterialCalendar_rangeFillColor);
        this.year = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(C1722R.styleable.MaterialCalendar_yearStyle, 0));
        this.selectedYear = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(C1722R.styleable.MaterialCalendar_yearSelectedStyle, 0));
        this.todayYear = CalendarItemStyle.create(context, obtainStyledAttributes.getResourceId(C1722R.styleable.MaterialCalendar_yearTodayStyle, 0));
        this.rangeFill.setColor(colorStateList.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
