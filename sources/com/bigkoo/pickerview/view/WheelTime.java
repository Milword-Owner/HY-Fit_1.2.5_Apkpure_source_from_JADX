package com.bigkoo.pickerview.view;

import android.view.View;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.PropertyType;
import com.bigkoo.pickerview.C1097R;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.NumericWheelAdapter;
import com.bigkoo.pickerview.listener.ISelectTimeCallback;
import com.bigkoo.pickerview.utils.ChinaDate;
import com.bigkoo.pickerview.utils.LunarCalendar;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class WheelTime {
    private static final int DEFAULT_END_DAY = 31;
    private static final int DEFAULT_END_MONTH = 12;
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_DAY = 1;
    private static final int DEFAULT_START_MONTH = 1;
    private static final int DEFAULT_START_YEAR = 1900;
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /* access modifiers changed from: private */
    public int currentYear;
    private int dividerColor;
    private WheelView.DividerType dividerType;
    /* access modifiers changed from: private */
    public int endDay = 31;
    /* access modifiers changed from: private */
    public int endMonth = 12;
    /* access modifiers changed from: private */
    public int endYear = DEFAULT_END_YEAR;
    private int gravity;
    private boolean isLunarCalendar = false;
    private float lineSpacingMultiplier;
    /* access modifiers changed from: private */
    public ISelectTimeCallback mSelectChangeCallback;
    /* access modifiers changed from: private */
    public int startDay = 1;
    /* access modifiers changed from: private */
    public int startMonth = 1;
    /* access modifiers changed from: private */
    public int startYear = 1900;
    private int textColorCenter;
    private int textColorOut;
    private int textSize;
    private boolean[] type;
    private View view;
    /* access modifiers changed from: private */
    public WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_minutes;
    /* access modifiers changed from: private */
    public WheelView wv_month;
    private WheelView wv_seconds;
    /* access modifiers changed from: private */
    public WheelView wv_year;

    public WheelTime(View view2, boolean[] zArr, int i, int i2) {
        this.view = view2;
        this.type = zArr;
        this.gravity = i;
        this.textSize = i2;
    }

    public void setLunarMode(boolean z) {
        this.isLunarCalendar = z;
    }

    public boolean isLunarMode() {
        return this.isLunarCalendar;
    }

    public void setPicker(int i, int i2, int i3) {
        setPicker(i, i2, i3, 0, 0, 0);
    }

    public void setPicker(int i, int i2, int i3, int i4, int i5, int i6) {
        if (this.isLunarCalendar) {
            int[] solarToLunar = LunarCalendar.solarToLunar(i, i2 + 1, i3);
            setLunar(solarToLunar[0], solarToLunar[1] - 1, solarToLunar[2], solarToLunar[3] == 1, i4, i5, i6);
            return;
        }
        setSolar(i, i2, i3, i4, i5, i6);
    }

    private void setLunar(int i, int i2, int i3, boolean z, int i4, int i5, int i6) {
        this.wv_year = (WheelView) this.view.findViewById(C1097R.C1100id.year);
        this.wv_year.setAdapter(new ArrayWheelAdapter(ChinaDate.getYears(this.startYear, this.endYear)));
        this.wv_year.setLabel("");
        this.wv_year.setCurrentItem(i - this.startYear);
        this.wv_year.setGravity(this.gravity);
        this.wv_month = (WheelView) this.view.findViewById(C1097R.C1100id.month);
        this.wv_month.setAdapter(new ArrayWheelAdapter(ChinaDate.getMonths(i)));
        this.wv_month.setLabel("");
        int leapMonth = ChinaDate.leapMonth(i);
        if (leapMonth == 0 || (i2 <= leapMonth - 1 && !z)) {
            this.wv_month.setCurrentItem(i2);
        } else {
            this.wv_month.setCurrentItem(i2 + 1);
        }
        this.wv_month.setGravity(this.gravity);
        this.wv_day = (WheelView) this.view.findViewById(C1097R.C1100id.day);
        if (ChinaDate.leapMonth(i) == 0) {
            this.wv_day.setAdapter(new ArrayWheelAdapter(ChinaDate.getLunarDays(ChinaDate.monthDays(i, i2))));
        } else {
            this.wv_day.setAdapter(new ArrayWheelAdapter(ChinaDate.getLunarDays(ChinaDate.leapDays(i))));
        }
        this.wv_day.setLabel("");
        this.wv_day.setCurrentItem(i3 - 1);
        this.wv_day.setGravity(this.gravity);
        this.wv_hours = (WheelView) this.view.findViewById(C1097R.C1100id.hour);
        this.wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
        this.wv_hours.setCurrentItem(i4);
        this.wv_hours.setGravity(this.gravity);
        this.wv_minutes = (WheelView) this.view.findViewById(C1097R.C1100id.min);
        this.wv_minutes.setAdapter(new NumericWheelAdapter(0, 59));
        this.wv_minutes.setCurrentItem(i5);
        this.wv_minutes.setGravity(this.gravity);
        this.wv_seconds = (WheelView) this.view.findViewById(C1097R.C1100id.second);
        this.wv_seconds.setAdapter(new NumericWheelAdapter(0, 59));
        this.wv_seconds.setCurrentItem(i5);
        this.wv_seconds.setGravity(this.gravity);
        this.wv_year.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(int i) {
                int i2;
                int access$000 = i + WheelTime.this.startYear;
                WheelTime.this.wv_month.setAdapter(new ArrayWheelAdapter(ChinaDate.getMonths(access$000)));
                if (ChinaDate.leapMonth(access$000) == 0 || WheelTime.this.wv_month.getCurrentItem() <= ChinaDate.leapMonth(access$000) - 1) {
                    WheelTime.this.wv_month.setCurrentItem(WheelTime.this.wv_month.getCurrentItem());
                } else {
                    WheelTime.this.wv_month.setCurrentItem(WheelTime.this.wv_month.getCurrentItem() + 1);
                }
                if (ChinaDate.leapMonth(access$000) == 0 || WheelTime.this.wv_month.getCurrentItem() <= ChinaDate.leapMonth(access$000) - 1) {
                    WheelTime.this.wv_day.setAdapter(new ArrayWheelAdapter(ChinaDate.getLunarDays(ChinaDate.monthDays(access$000, WheelTime.this.wv_month.getCurrentItem() + 1))));
                    i2 = ChinaDate.monthDays(access$000, WheelTime.this.wv_month.getCurrentItem() + 1);
                } else if (WheelTime.this.wv_month.getCurrentItem() == ChinaDate.leapMonth(access$000) + 1) {
                    WheelTime.this.wv_day.setAdapter(new ArrayWheelAdapter(ChinaDate.getLunarDays(ChinaDate.leapDays(access$000))));
                    i2 = ChinaDate.leapDays(access$000);
                } else {
                    WheelTime.this.wv_day.setAdapter(new ArrayWheelAdapter(ChinaDate.getLunarDays(ChinaDate.monthDays(access$000, WheelTime.this.wv_month.getCurrentItem()))));
                    i2 = ChinaDate.monthDays(access$000, WheelTime.this.wv_month.getCurrentItem());
                }
                int i3 = i2 - 1;
                if (WheelTime.this.wv_day.getCurrentItem() > i3) {
                    WheelTime.this.wv_day.setCurrentItem(i3);
                }
                if (WheelTime.this.mSelectChangeCallback != null) {
                    WheelTime.this.mSelectChangeCallback.onTimeSelectChanged();
                }
            }
        });
        this.wv_month.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(int i) {
                int i2;
                int currentItem = WheelTime.this.wv_year.getCurrentItem() + WheelTime.this.startYear;
                if (ChinaDate.leapMonth(currentItem) == 0 || i <= ChinaDate.leapMonth(currentItem) - 1) {
                    int i3 = i + 1;
                    WheelTime.this.wv_day.setAdapter(new ArrayWheelAdapter(ChinaDate.getLunarDays(ChinaDate.monthDays(currentItem, i3))));
                    i2 = ChinaDate.monthDays(currentItem, i3);
                } else if (WheelTime.this.wv_month.getCurrentItem() == ChinaDate.leapMonth(currentItem) + 1) {
                    WheelTime.this.wv_day.setAdapter(new ArrayWheelAdapter(ChinaDate.getLunarDays(ChinaDate.leapDays(currentItem))));
                    i2 = ChinaDate.leapDays(currentItem);
                } else {
                    WheelTime.this.wv_day.setAdapter(new ArrayWheelAdapter(ChinaDate.getLunarDays(ChinaDate.monthDays(currentItem, i))));
                    i2 = ChinaDate.monthDays(currentItem, i);
                }
                int i4 = i2 - 1;
                if (WheelTime.this.wv_day.getCurrentItem() > i4) {
                    WheelTime.this.wv_day.setCurrentItem(i4);
                }
                if (WheelTime.this.mSelectChangeCallback != null) {
                    WheelTime.this.mSelectChangeCallback.onTimeSelectChanged();
                }
            }
        });
        setChangedListener(this.wv_day);
        setChangedListener(this.wv_hours);
        setChangedListener(this.wv_minutes);
        setChangedListener(this.wv_seconds);
        boolean[] zArr = this.type;
        if (zArr.length == 6) {
            int i7 = 8;
            this.wv_year.setVisibility(zArr[0] ? 0 : 8);
            this.wv_month.setVisibility(this.type[1] ? 0 : 8);
            this.wv_day.setVisibility(this.type[2] ? 0 : 8);
            this.wv_hours.setVisibility(this.type[3] ? 0 : 8);
            this.wv_minutes.setVisibility(this.type[4] ? 0 : 8);
            WheelView wheelView = this.wv_seconds;
            if (this.type[5]) {
                i7 = 0;
            }
            wheelView.setVisibility(i7);
            setContentTextSize();
            return;
        }
        throw new RuntimeException("type[] length is not 6");
    }

    private void setSolar(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        String[] strArr = {PropertyType.PAGE_PROPERTRY, "6", "9", "11"};
        final List asList = Arrays.asList(new String[]{"1", "3", "5", "7", "8", "10", "12"});
        final List asList2 = Arrays.asList(strArr);
        this.currentYear = i;
        this.wv_year = (WheelView) this.view.findViewById(C1097R.C1100id.year);
        this.wv_year.setAdapter(new NumericWheelAdapter(this.startYear, this.endYear));
        this.wv_year.setCurrentItem(i - this.startYear);
        this.wv_year.setGravity(this.gravity);
        this.wv_month = (WheelView) this.view.findViewById(C1097R.C1100id.month);
        int i9 = this.startYear;
        int i10 = this.endYear;
        if (i9 == i10) {
            this.wv_month.setAdapter(new NumericWheelAdapter(this.startMonth, this.endMonth));
            this.wv_month.setCurrentItem((i2 + 1) - this.startMonth);
        } else if (i == i9) {
            this.wv_month.setAdapter(new NumericWheelAdapter(this.startMonth, 12));
            this.wv_month.setCurrentItem((i2 + 1) - this.startMonth);
        } else if (i == i10) {
            this.wv_month.setAdapter(new NumericWheelAdapter(1, this.endMonth));
            this.wv_month.setCurrentItem(i2);
        } else {
            this.wv_month.setAdapter(new NumericWheelAdapter(1, 12));
            this.wv_month.setCurrentItem(i2);
        }
        this.wv_month.setGravity(this.gravity);
        this.wv_day = (WheelView) this.view.findViewById(C1097R.C1100id.day);
        if (this.startYear == this.endYear && this.startMonth == this.endMonth) {
            int i11 = i2 + 1;
            if (asList.contains(String.valueOf(i11))) {
                if (this.endDay > 31) {
                    this.endDay = 31;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, this.endDay));
            } else if (asList2.contains(String.valueOf(i11))) {
                if (this.endDay > 30) {
                    this.endDay = 30;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, this.endDay));
            } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                if (this.endDay > 28) {
                    this.endDay = 28;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, this.endDay));
            } else {
                if (this.endDay > 29) {
                    this.endDay = 29;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, this.endDay));
            }
            this.wv_day.setCurrentItem(i3 - this.startDay);
        } else if (i == this.startYear && (i8 = i2 + 1) == this.startMonth) {
            if (asList.contains(String.valueOf(i8))) {
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, 31));
            } else if (asList2.contains(String.valueOf(i8))) {
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, 30));
            } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, 28));
            } else {
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, 29));
            }
            this.wv_day.setCurrentItem(i3 - this.startDay);
        } else if (i == this.endYear && (i7 = i2 + 1) == this.endMonth) {
            if (asList.contains(String.valueOf(i7))) {
                if (this.endDay > 31) {
                    this.endDay = 31;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(1, this.endDay));
            } else if (asList2.contains(String.valueOf(i7))) {
                if (this.endDay > 30) {
                    this.endDay = 30;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(1, this.endDay));
            } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                if (this.endDay > 28) {
                    this.endDay = 28;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(1, this.endDay));
            } else {
                if (this.endDay > 29) {
                    this.endDay = 29;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(1, this.endDay));
            }
            this.wv_day.setCurrentItem(i3 - 1);
        } else {
            int i12 = i2 + 1;
            if (asList.contains(String.valueOf(i12))) {
                this.wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (asList2.contains(String.valueOf(i12))) {
                this.wv_day.setAdapter(new NumericWheelAdapter(1, 30));
            } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                this.wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            } else {
                this.wv_day.setAdapter(new NumericWheelAdapter(1, 29));
            }
            this.wv_day.setCurrentItem(i3 - 1);
        }
        this.wv_day.setGravity(this.gravity);
        this.wv_hours = (WheelView) this.view.findViewById(C1097R.C1100id.hour);
        this.wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
        this.wv_hours.setCurrentItem(i4);
        this.wv_hours.setGravity(this.gravity);
        this.wv_minutes = (WheelView) this.view.findViewById(C1097R.C1100id.min);
        this.wv_minutes.setAdapter(new NumericWheelAdapter(0, 59));
        this.wv_minutes.setCurrentItem(i5);
        this.wv_minutes.setGravity(this.gravity);
        this.wv_seconds = (WheelView) this.view.findViewById(C1097R.C1100id.second);
        this.wv_seconds.setAdapter(new NumericWheelAdapter(0, 59));
        this.wv_seconds.setCurrentItem(i6);
        this.wv_seconds.setGravity(this.gravity);
        this.wv_year.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(int i) {
                int access$000 = i + WheelTime.this.startYear;
                int unused = WheelTime.this.currentYear = access$000;
                int currentItem = WheelTime.this.wv_month.getCurrentItem();
                if (WheelTime.this.startYear == WheelTime.this.endYear) {
                    WheelTime.this.wv_month.setAdapter(new NumericWheelAdapter(WheelTime.this.startMonth, WheelTime.this.endMonth));
                    if (currentItem > WheelTime.this.wv_month.getAdapter().getItemsCount() - 1) {
                        currentItem = WheelTime.this.wv_month.getAdapter().getItemsCount() - 1;
                        WheelTime.this.wv_month.setCurrentItem(currentItem);
                    }
                    int access$700 = currentItem + WheelTime.this.startMonth;
                    if (WheelTime.this.startMonth == WheelTime.this.endMonth) {
                        WheelTime wheelTime = WheelTime.this;
                        wheelTime.setReDay(access$000, access$700, wheelTime.startDay, WheelTime.this.endDay, asList, asList2);
                    } else if (access$700 == WheelTime.this.startMonth) {
                        WheelTime wheelTime2 = WheelTime.this;
                        wheelTime2.setReDay(access$000, access$700, wheelTime2.startDay, 31, asList, asList2);
                    } else if (access$700 == WheelTime.this.endMonth) {
                        WheelTime wheelTime3 = WheelTime.this;
                        wheelTime3.setReDay(access$000, access$700, 1, wheelTime3.endDay, asList, asList2);
                    } else {
                        WheelTime.this.setReDay(access$000, access$700, 1, 31, asList, asList2);
                    }
                } else if (access$000 == WheelTime.this.startYear) {
                    WheelTime.this.wv_month.setAdapter(new NumericWheelAdapter(WheelTime.this.startMonth, 12));
                    if (currentItem > WheelTime.this.wv_month.getAdapter().getItemsCount() - 1) {
                        currentItem = WheelTime.this.wv_month.getAdapter().getItemsCount() - 1;
                        WheelTime.this.wv_month.setCurrentItem(currentItem);
                    }
                    int access$7002 = currentItem + WheelTime.this.startMonth;
                    if (access$7002 == WheelTime.this.startMonth) {
                        WheelTime wheelTime4 = WheelTime.this;
                        wheelTime4.setReDay(access$000, access$7002, wheelTime4.startDay, 31, asList, asList2);
                    } else {
                        WheelTime.this.setReDay(access$000, access$7002, 1, 31, asList, asList2);
                    }
                } else if (access$000 == WheelTime.this.endYear) {
                    WheelTime.this.wv_month.setAdapter(new NumericWheelAdapter(1, WheelTime.this.endMonth));
                    if (currentItem > WheelTime.this.wv_month.getAdapter().getItemsCount() - 1) {
                        currentItem = WheelTime.this.wv_month.getAdapter().getItemsCount() - 1;
                        WheelTime.this.wv_month.setCurrentItem(currentItem);
                    }
                    int i2 = 1 + currentItem;
                    if (i2 == WheelTime.this.endMonth) {
                        WheelTime wheelTime5 = WheelTime.this;
                        wheelTime5.setReDay(access$000, i2, 1, wheelTime5.endDay, asList, asList2);
                    } else {
                        WheelTime.this.setReDay(access$000, i2, 1, 31, asList, asList2);
                    }
                } else {
                    WheelTime.this.wv_month.setAdapter(new NumericWheelAdapter(1, 12));
                    WheelTime wheelTime6 = WheelTime.this;
                    wheelTime6.setReDay(access$000, 1 + wheelTime6.wv_month.getCurrentItem(), 1, 31, asList, asList2);
                }
                if (WheelTime.this.mSelectChangeCallback != null) {
                    WheelTime.this.mSelectChangeCallback.onTimeSelectChanged();
                }
            }
        });
        this.wv_month.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(int i) {
                int i2 = i + 1;
                if (WheelTime.this.startYear == WheelTime.this.endYear) {
                    int access$700 = (i2 + WheelTime.this.startMonth) - 1;
                    if (WheelTime.this.startMonth == WheelTime.this.endMonth) {
                        WheelTime wheelTime = WheelTime.this;
                        wheelTime.setReDay(wheelTime.currentYear, access$700, WheelTime.this.startDay, WheelTime.this.endDay, asList, asList2);
                    } else if (WheelTime.this.startMonth == access$700) {
                        WheelTime wheelTime2 = WheelTime.this;
                        wheelTime2.setReDay(wheelTime2.currentYear, access$700, WheelTime.this.startDay, 31, asList, asList2);
                    } else if (WheelTime.this.endMonth == access$700) {
                        WheelTime wheelTime3 = WheelTime.this;
                        wheelTime3.setReDay(wheelTime3.currentYear, access$700, 1, WheelTime.this.endDay, asList, asList2);
                    } else {
                        WheelTime wheelTime4 = WheelTime.this;
                        wheelTime4.setReDay(wheelTime4.currentYear, access$700, 1, 31, asList, asList2);
                    }
                } else if (WheelTime.this.currentYear == WheelTime.this.startYear) {
                    int access$7002 = (i2 + WheelTime.this.startMonth) - 1;
                    if (access$7002 == WheelTime.this.startMonth) {
                        WheelTime wheelTime5 = WheelTime.this;
                        wheelTime5.setReDay(wheelTime5.currentYear, access$7002, WheelTime.this.startDay, 31, asList, asList2);
                    } else {
                        WheelTime wheelTime6 = WheelTime.this;
                        wheelTime6.setReDay(wheelTime6.currentYear, access$7002, 1, 31, asList, asList2);
                    }
                } else if (WheelTime.this.currentYear != WheelTime.this.endYear) {
                    WheelTime wheelTime7 = WheelTime.this;
                    wheelTime7.setReDay(wheelTime7.currentYear, i2, 1, 31, asList, asList2);
                } else if (i2 == WheelTime.this.endMonth) {
                    WheelTime wheelTime8 = WheelTime.this;
                    wheelTime8.setReDay(wheelTime8.currentYear, WheelTime.this.wv_month.getCurrentItem() + 1, 1, WheelTime.this.endDay, asList, asList2);
                } else {
                    WheelTime wheelTime9 = WheelTime.this;
                    wheelTime9.setReDay(wheelTime9.currentYear, WheelTime.this.wv_month.getCurrentItem() + 1, 1, 31, asList, asList2);
                }
                if (WheelTime.this.mSelectChangeCallback != null) {
                    WheelTime.this.mSelectChangeCallback.onTimeSelectChanged();
                }
            }
        });
        setChangedListener(this.wv_day);
        setChangedListener(this.wv_hours);
        setChangedListener(this.wv_minutes);
        setChangedListener(this.wv_seconds);
        boolean[] zArr = this.type;
        if (zArr.length == 6) {
            int i13 = 8;
            this.wv_year.setVisibility(zArr[0] ? 0 : 8);
            this.wv_month.setVisibility(this.type[1] ? 0 : 8);
            this.wv_day.setVisibility(this.type[2] ? 0 : 8);
            this.wv_hours.setVisibility(this.type[3] ? 0 : 8);
            this.wv_minutes.setVisibility(this.type[4] ? 0 : 8);
            WheelView wheelView = this.wv_seconds;
            if (this.type[5]) {
                i13 = 0;
            }
            wheelView.setVisibility(i13);
            setContentTextSize();
            return;
        }
        throw new IllegalArgumentException("type[] length is not 6");
    }

    private void setChangedListener(WheelView wheelView) {
        if (this.mSelectChangeCallback != null) {
            wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(int i) {
                    WheelTime.this.mSelectChangeCallback.onTimeSelectChanged();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setReDay(int i, int i2, int i3, int i4, List<String> list, List<String> list2) {
        int currentItem = this.wv_day.getCurrentItem();
        if (list.contains(String.valueOf(i2))) {
            int i5 = 31;
            if (i4 <= 31) {
                i5 = i4;
            }
            this.wv_day.setAdapter(new NumericWheelAdapter(i3, i5));
        } else if (list2.contains(String.valueOf(i2))) {
            int i6 = 30;
            if (i4 <= 30) {
                i6 = i4;
            }
            this.wv_day.setAdapter(new NumericWheelAdapter(i3, i6));
        } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
            int i7 = 28;
            if (i4 <= 28) {
                i7 = i4;
            }
            this.wv_day.setAdapter(new NumericWheelAdapter(i3, i7));
        } else {
            int i8 = 29;
            if (i4 <= 29) {
                i8 = i4;
            }
            this.wv_day.setAdapter(new NumericWheelAdapter(i3, i8));
        }
        if (currentItem > this.wv_day.getAdapter().getItemsCount() - 1) {
            this.wv_day.setCurrentItem(this.wv_day.getAdapter().getItemsCount() - 1);
        }
    }

    private void setContentTextSize() {
        this.wv_day.setTextSize((float) this.textSize);
        this.wv_month.setTextSize((float) this.textSize);
        this.wv_year.setTextSize((float) this.textSize);
        this.wv_hours.setTextSize((float) this.textSize);
        this.wv_minutes.setTextSize((float) this.textSize);
        this.wv_seconds.setTextSize((float) this.textSize);
    }

    private void setTextColorOut() {
        this.wv_day.setTextColorOut(this.textColorOut);
        this.wv_month.setTextColorOut(this.textColorOut);
        this.wv_year.setTextColorOut(this.textColorOut);
        this.wv_hours.setTextColorOut(this.textColorOut);
        this.wv_minutes.setTextColorOut(this.textColorOut);
        this.wv_seconds.setTextColorOut(this.textColorOut);
    }

    private void setTextColorCenter() {
        this.wv_day.setTextColorCenter(this.textColorCenter);
        this.wv_month.setTextColorCenter(this.textColorCenter);
        this.wv_year.setTextColorCenter(this.textColorCenter);
        this.wv_hours.setTextColorCenter(this.textColorCenter);
        this.wv_minutes.setTextColorCenter(this.textColorCenter);
        this.wv_seconds.setTextColorCenter(this.textColorCenter);
    }

    private void setDividerColor() {
        this.wv_day.setDividerColor(this.dividerColor);
        this.wv_month.setDividerColor(this.dividerColor);
        this.wv_year.setDividerColor(this.dividerColor);
        this.wv_hours.setDividerColor(this.dividerColor);
        this.wv_minutes.setDividerColor(this.dividerColor);
        this.wv_seconds.setDividerColor(this.dividerColor);
    }

    private void setDividerType() {
        this.wv_day.setDividerType(this.dividerType);
        this.wv_month.setDividerType(this.dividerType);
        this.wv_year.setDividerType(this.dividerType);
        this.wv_hours.setDividerType(this.dividerType);
        this.wv_minutes.setDividerType(this.dividerType);
        this.wv_seconds.setDividerType(this.dividerType);
    }

    private void setLineSpacingMultiplier() {
        this.wv_day.setLineSpacingMultiplier(this.lineSpacingMultiplier);
        this.wv_month.setLineSpacingMultiplier(this.lineSpacingMultiplier);
        this.wv_year.setLineSpacingMultiplier(this.lineSpacingMultiplier);
        this.wv_hours.setLineSpacingMultiplier(this.lineSpacingMultiplier);
        this.wv_minutes.setLineSpacingMultiplier(this.lineSpacingMultiplier);
        this.wv_seconds.setLineSpacingMultiplier(this.lineSpacingMultiplier);
    }

    public void setLabels(String str, String str2, String str3, String str4, String str5, String str6) {
        if (!this.isLunarCalendar) {
            if (str != null) {
                this.wv_year.setLabel(str);
            } else {
                this.wv_year.setLabel(this.view.getContext().getString(C1097R.string.pickerview_year));
            }
            if (str2 != null) {
                this.wv_month.setLabel(str2);
            } else {
                this.wv_month.setLabel(this.view.getContext().getString(C1097R.string.pickerview_month));
            }
            if (str3 != null) {
                this.wv_day.setLabel(str3);
            } else {
                this.wv_day.setLabel(this.view.getContext().getString(C1097R.string.pickerview_day));
            }
            if (str4 != null) {
                this.wv_hours.setLabel(str4);
            } else {
                this.wv_hours.setLabel(this.view.getContext().getString(C1097R.string.pickerview_hours));
            }
            if (str5 != null) {
                this.wv_minutes.setLabel(str5);
            } else {
                this.wv_minutes.setLabel(this.view.getContext().getString(C1097R.string.pickerview_minutes));
            }
            if (str6 != null) {
                this.wv_seconds.setLabel(str6);
            } else {
                this.wv_seconds.setLabel(this.view.getContext().getString(C1097R.string.pickerview_seconds));
            }
        }
    }

    public void setTextXOffset(int i, int i2, int i3, int i4, int i5, int i6) {
        this.wv_year.setTextXOffset(i);
        this.wv_month.setTextXOffset(i2);
        this.wv_day.setTextXOffset(i3);
        this.wv_hours.setTextXOffset(i4);
        this.wv_minutes.setTextXOffset(i5);
        this.wv_seconds.setTextXOffset(i6);
    }

    public void setCyclic(boolean z) {
        this.wv_year.setCyclic(z);
        this.wv_month.setCyclic(z);
        this.wv_day.setCyclic(z);
        this.wv_hours.setCyclic(z);
        this.wv_minutes.setCyclic(z);
        this.wv_seconds.setCyclic(z);
    }

    public String getTime() {
        if (this.isLunarCalendar) {
            return getLunarTime();
        }
        StringBuilder sb = new StringBuilder();
        if (this.currentYear == this.startYear) {
            int currentItem = this.wv_month.getCurrentItem();
            int i = this.startMonth;
            if (currentItem + i == i) {
                sb.append(this.wv_year.getCurrentItem() + this.startYear);
                sb.append("-");
                sb.append(this.wv_month.getCurrentItem() + this.startMonth);
                sb.append("-");
                sb.append(this.wv_day.getCurrentItem() + this.startDay);
                sb.append(" ");
                sb.append(this.wv_hours.getCurrentItem());
                sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
                sb.append(this.wv_minutes.getCurrentItem());
                sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
                sb.append(this.wv_seconds.getCurrentItem());
            } else {
                sb.append(this.wv_year.getCurrentItem() + this.startYear);
                sb.append("-");
                sb.append(this.wv_month.getCurrentItem() + this.startMonth);
                sb.append("-");
                sb.append(this.wv_day.getCurrentItem() + 1);
                sb.append(" ");
                sb.append(this.wv_hours.getCurrentItem());
                sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
                sb.append(this.wv_minutes.getCurrentItem());
                sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
                sb.append(this.wv_seconds.getCurrentItem());
            }
        } else {
            sb.append(this.wv_year.getCurrentItem() + this.startYear);
            sb.append("-");
            sb.append(this.wv_month.getCurrentItem() + 1);
            sb.append("-");
            sb.append(this.wv_day.getCurrentItem() + 1);
            sb.append(" ");
            sb.append(this.wv_hours.getCurrentItem());
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(this.wv_minutes.getCurrentItem());
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(this.wv_seconds.getCurrentItem());
        }
        return sb.toString();
    }

    private String getLunarTime() {
        boolean z;
        int i;
        int currentItem;
        StringBuilder sb = new StringBuilder();
        int currentItem2 = this.wv_year.getCurrentItem() + this.startYear;
        if (ChinaDate.leapMonth(currentItem2) == 0) {
            currentItem = this.wv_month.getCurrentItem();
        } else if ((this.wv_month.getCurrentItem() + 1) - ChinaDate.leapMonth(currentItem2) <= 0) {
            currentItem = this.wv_month.getCurrentItem();
        } else if ((this.wv_month.getCurrentItem() + 1) - ChinaDate.leapMonth(currentItem2) == 1) {
            i = this.wv_month.getCurrentItem();
            z = true;
            int[] lunarToSolar = LunarCalendar.lunarToSolar(currentItem2, i, this.wv_day.getCurrentItem() + 1, z);
            sb.append(lunarToSolar[0]);
            sb.append("-");
            sb.append(lunarToSolar[1]);
            sb.append("-");
            sb.append(lunarToSolar[2]);
            sb.append(" ");
            sb.append(this.wv_hours.getCurrentItem());
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(this.wv_minutes.getCurrentItem());
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(this.wv_seconds.getCurrentItem());
            return sb.toString();
        } else {
            i = this.wv_month.getCurrentItem();
            z = false;
            int[] lunarToSolar2 = LunarCalendar.lunarToSolar(currentItem2, i, this.wv_day.getCurrentItem() + 1, z);
            sb.append(lunarToSolar2[0]);
            sb.append("-");
            sb.append(lunarToSolar2[1]);
            sb.append("-");
            sb.append(lunarToSolar2[2]);
            sb.append(" ");
            sb.append(this.wv_hours.getCurrentItem());
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(this.wv_minutes.getCurrentItem());
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(this.wv_seconds.getCurrentItem());
            return sb.toString();
        }
        i = currentItem + 1;
        z = false;
        int[] lunarToSolar22 = LunarCalendar.lunarToSolar(currentItem2, i, this.wv_day.getCurrentItem() + 1, z);
        sb.append(lunarToSolar22[0]);
        sb.append("-");
        sb.append(lunarToSolar22[1]);
        sb.append("-");
        sb.append(lunarToSolar22[2]);
        sb.append(" ");
        sb.append(this.wv_hours.getCurrentItem());
        sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
        sb.append(this.wv_minutes.getCurrentItem());
        sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
        sb.append(this.wv_seconds.getCurrentItem());
        return sb.toString();
    }

    public View getView() {
        return this.view;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(int i) {
        this.startYear = i;
    }

    public int getEndYear() {
        return this.endYear;
    }

    public void setEndYear(int i) {
        this.endYear = i;
    }

    public void setRangDate(Calendar calendar, Calendar calendar2) {
        if (calendar == null && calendar2 != null) {
            int i = calendar2.get(1);
            int i2 = calendar2.get(2) + 1;
            int i3 = calendar2.get(5);
            int i4 = this.startYear;
            if (i > i4) {
                this.endYear = i;
                this.endMonth = i2;
                this.endDay = i3;
            } else if (i == i4) {
                int i5 = this.startMonth;
                if (i2 > i5) {
                    this.endYear = i;
                    this.endMonth = i2;
                    this.endDay = i3;
                } else if (i2 == i5 && i3 > this.startDay) {
                    this.endYear = i;
                    this.endMonth = i2;
                    this.endDay = i3;
                }
            }
        } else if (calendar != null && calendar2 == null) {
            int i6 = calendar.get(1);
            int i7 = calendar.get(2) + 1;
            int i8 = calendar.get(5);
            int i9 = this.endYear;
            if (i6 < i9) {
                this.startMonth = i7;
                this.startDay = i8;
                this.startYear = i6;
            } else if (i6 == i9) {
                int i10 = this.endMonth;
                if (i7 < i10) {
                    this.startMonth = i7;
                    this.startDay = i8;
                    this.startYear = i6;
                } else if (i7 == i10 && i8 < this.endDay) {
                    this.startMonth = i7;
                    this.startDay = i8;
                    this.startYear = i6;
                }
            }
        } else if (calendar != null && calendar2 != null) {
            this.startYear = calendar.get(1);
            this.endYear = calendar2.get(1);
            this.startMonth = calendar.get(2) + 1;
            this.endMonth = calendar2.get(2) + 1;
            this.startDay = calendar.get(5);
            this.endDay = calendar2.get(5);
        }
    }

    public void setLineSpacingMultiplier(float f) {
        this.lineSpacingMultiplier = f;
        setLineSpacingMultiplier();
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
        setDividerColor();
    }

    public void setDividerType(WheelView.DividerType dividerType2) {
        this.dividerType = dividerType2;
        setDividerType();
    }

    public void setTextColorCenter(int i) {
        this.textColorCenter = i;
        setTextColorCenter();
    }

    public void setTextColorOut(int i) {
        this.textColorOut = i;
        setTextColorOut();
    }

    public void isCenterLabel(boolean z) {
        this.wv_day.isCenterLabel(z);
        this.wv_month.isCenterLabel(z);
        this.wv_year.isCenterLabel(z);
        this.wv_hours.isCenterLabel(z);
        this.wv_minutes.isCenterLabel(z);
        this.wv_seconds.isCenterLabel(z);
    }

    public void setSelectChangeCallback(ISelectTimeCallback iSelectTimeCallback) {
        this.mSelectChangeCallback = iSelectTimeCallback;
    }
}
