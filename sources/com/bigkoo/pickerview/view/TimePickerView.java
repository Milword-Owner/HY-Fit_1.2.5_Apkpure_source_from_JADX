package com.bigkoo.pickerview.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.C1097R;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.ISelectTimeCallback;
import java.text.ParseException;
import java.util.Calendar;

public class TimePickerView extends BasePickerView implements View.OnClickListener {
    private static final String TAG_CANCEL = "cancel";
    private static final String TAG_SUBMIT = "submit";
    /* access modifiers changed from: private */
    public WheelTime wheelTime;

    public TimePickerView(PickerOptions pickerOptions) {
        super(pickerOptions.context);
        this.mPickerOptions = pickerOptions;
        initView(pickerOptions.context);
    }

    private void initView(Context context) {
        setDialogOutSideCancelable();
        initViews();
        initAnim();
        if (this.mPickerOptions.customListener == null) {
            LayoutInflater.from(context).inflate(C1097R.C1101layout.pickerview_time, this.contentContainer);
            TextView textView = (TextView) findViewById(C1097R.C1100id.tvTitle);
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(C1097R.C1100id.rv_topbar);
            Button button = (Button) findViewById(C1097R.C1100id.btnSubmit);
            Button button2 = (Button) findViewById(C1097R.C1100id.btnCancel);
            button.setTag(TAG_SUBMIT);
            button2.setTag(TAG_CANCEL);
            button.setOnClickListener(this);
            button2.setOnClickListener(this);
            button.setText(TextUtils.isEmpty(this.mPickerOptions.textContentConfirm) ? context.getResources().getString(C1097R.string.pickerview_submit) : this.mPickerOptions.textContentConfirm);
            button2.setText(TextUtils.isEmpty(this.mPickerOptions.textContentCancel) ? context.getResources().getString(C1097R.string.pickerview_cancel) : this.mPickerOptions.textContentCancel);
            textView.setText(TextUtils.isEmpty(this.mPickerOptions.textContentTitle) ? "" : this.mPickerOptions.textContentTitle);
            button.setTextColor(this.mPickerOptions.textColorConfirm);
            button2.setTextColor(this.mPickerOptions.textColorCancel);
            textView.setTextColor(this.mPickerOptions.textColorTitle);
            relativeLayout.setBackgroundColor(this.mPickerOptions.bgColorTitle);
            button.setTextSize((float) this.mPickerOptions.textSizeSubmitCancel);
            button2.setTextSize((float) this.mPickerOptions.textSizeSubmitCancel);
            textView.setTextSize((float) this.mPickerOptions.textSizeTitle);
        } else {
            this.mPickerOptions.customListener.customLayout(LayoutInflater.from(context).inflate(this.mPickerOptions.layoutRes, this.contentContainer));
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(C1097R.C1100id.timepicker);
        linearLayout.setBackgroundColor(this.mPickerOptions.bgColorWheel);
        initWheelTime(linearLayout);
    }

    private void initWheelTime(LinearLayout linearLayout) {
        this.wheelTime = new WheelTime(linearLayout, this.mPickerOptions.type, this.mPickerOptions.textGravity, this.mPickerOptions.textSizeContent);
        if (this.mPickerOptions.timeSelectChangeListener != null) {
            this.wheelTime.setSelectChangeCallback(new ISelectTimeCallback() {
                public void onTimeSelectChanged() {
                    try {
                        TimePickerView.this.mPickerOptions.timeSelectChangeListener.onTimeSelectChanged(WheelTime.dateFormat.parse(TimePickerView.this.wheelTime.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        this.wheelTime.setLunarMode(this.mPickerOptions.isLunarCalendar);
        if (!(this.mPickerOptions.startYear == 0 || this.mPickerOptions.endYear == 0 || this.mPickerOptions.startYear > this.mPickerOptions.endYear)) {
            setRange();
        }
        if (this.mPickerOptions.startDate == null || this.mPickerOptions.endDate == null) {
            if (this.mPickerOptions.startDate != null) {
                if (this.mPickerOptions.startDate.get(1) >= 1900) {
                    setRangDate();
                } else {
                    throw new IllegalArgumentException("The startDate can not as early as 1900");
                }
            } else if (this.mPickerOptions.endDate == null) {
                setRangDate();
            } else if (this.mPickerOptions.endDate.get(1) <= 2100) {
                setRangDate();
            } else {
                throw new IllegalArgumentException("The endDate should not be later than 2100");
            }
        } else if (this.mPickerOptions.startDate.getTimeInMillis() <= this.mPickerOptions.endDate.getTimeInMillis()) {
            setRangDate();
        } else {
            throw new IllegalArgumentException("startDate can't be later than endDate");
        }
        setTime();
        this.wheelTime.setLabels(this.mPickerOptions.label_year, this.mPickerOptions.label_month, this.mPickerOptions.label_day, this.mPickerOptions.label_hours, this.mPickerOptions.label_minutes, this.mPickerOptions.label_seconds);
        this.wheelTime.setTextXOffset(this.mPickerOptions.x_offset_year, this.mPickerOptions.x_offset_month, this.mPickerOptions.x_offset_day, this.mPickerOptions.x_offset_hours, this.mPickerOptions.x_offset_minutes, this.mPickerOptions.x_offset_seconds);
        setOutSideCancelable(this.mPickerOptions.cancelable);
        this.wheelTime.setCyclic(this.mPickerOptions.cyclic);
        this.wheelTime.setDividerColor(this.mPickerOptions.dividerColor);
        this.wheelTime.setDividerType(this.mPickerOptions.dividerType);
        this.wheelTime.setLineSpacingMultiplier(this.mPickerOptions.lineSpacingMultiplier);
        this.wheelTime.setTextColorOut(this.mPickerOptions.textColorOut);
        this.wheelTime.setTextColorCenter(this.mPickerOptions.textColorCenter);
        this.wheelTime.isCenterLabel(this.mPickerOptions.isCenterLabel);
    }

    public void setDate(Calendar calendar) {
        this.mPickerOptions.date = calendar;
        setTime();
    }

    private void setRange() {
        this.wheelTime.setStartYear(this.mPickerOptions.startYear);
        this.wheelTime.setEndYear(this.mPickerOptions.endYear);
    }

    private void setRangDate() {
        this.wheelTime.setRangDate(this.mPickerOptions.startDate, this.mPickerOptions.endDate);
        initDefaultSelectedDate();
    }

    private void initDefaultSelectedDate() {
        if (this.mPickerOptions.startDate == null || this.mPickerOptions.endDate == null) {
            if (this.mPickerOptions.startDate != null) {
                this.mPickerOptions.date = this.mPickerOptions.startDate;
            } else if (this.mPickerOptions.endDate != null) {
                this.mPickerOptions.date = this.mPickerOptions.endDate;
            }
        } else if (this.mPickerOptions.date == null || this.mPickerOptions.date.getTimeInMillis() < this.mPickerOptions.startDate.getTimeInMillis() || this.mPickerOptions.date.getTimeInMillis() > this.mPickerOptions.endDate.getTimeInMillis()) {
            this.mPickerOptions.date = this.mPickerOptions.startDate;
        }
    }

    private void setTime() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Calendar instance = Calendar.getInstance();
        if (this.mPickerOptions.date == null) {
            instance.setTimeInMillis(System.currentTimeMillis());
            i5 = instance.get(1);
            i = instance.get(2);
            i2 = instance.get(5);
            i3 = instance.get(11);
            i4 = instance.get(12);
            i6 = instance.get(13);
        } else {
            i5 = this.mPickerOptions.date.get(1);
            i = this.mPickerOptions.date.get(2);
            i2 = this.mPickerOptions.date.get(5);
            i3 = this.mPickerOptions.date.get(11);
            i4 = this.mPickerOptions.date.get(12);
            i6 = this.mPickerOptions.date.get(13);
        }
        int i7 = i3;
        int i8 = i2;
        int i9 = i;
        WheelTime wheelTime2 = this.wheelTime;
        wheelTime2.setPicker(i5, i9, i8, i7, i4, i6);
    }

    public void onClick(View view) {
        String str = (String) view.getTag();
        if (str.equals(TAG_SUBMIT)) {
            returnData();
        } else if (str.equals(TAG_CANCEL) && this.mPickerOptions.cancelListener != null) {
            this.mPickerOptions.cancelListener.onClick(view);
        }
        dismiss();
    }

    public void returnData() {
        if (this.mPickerOptions.timeSelectListener != null) {
            try {
                this.mPickerOptions.timeSelectListener.onTimeSelect(WheelTime.dateFormat.parse(this.wheelTime.getTime()), this.clickView);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void setTitleText(String str) {
        TextView textView = (TextView) findViewById(C1097R.C1100id.tvTitle);
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setLunarCalendar(boolean z) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.setTime(WheelTime.dateFormat.parse(this.wheelTime.getTime()));
            int i = instance.get(1);
            int i2 = instance.get(2);
            int i3 = instance.get(5);
            int i4 = instance.get(11);
            int i5 = instance.get(12);
            int i6 = instance.get(13);
            this.wheelTime.setLunarMode(z);
            this.wheelTime.setLabels(this.mPickerOptions.label_year, this.mPickerOptions.label_month, this.mPickerOptions.label_day, this.mPickerOptions.label_hours, this.mPickerOptions.label_minutes, this.mPickerOptions.label_seconds);
            this.wheelTime.setPicker(i, i2, i3, i4, i5, i6);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean isLunarCalendar() {
        return this.wheelTime.isLunarMode();
    }

    public boolean isDialog() {
        return this.mPickerOptions.isDialog;
    }
}
