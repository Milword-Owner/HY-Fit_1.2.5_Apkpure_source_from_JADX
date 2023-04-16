package com.huayu.tzc.customview.calendar;

import java.util.Date;

public class DateBean {
    public static int ITEM_STATE_BEGIN_DATE = 1;
    public static int ITEM_STATE_END_DATE = 2;
    public static int ITEM_STATE_NORMAL = 4;
    public static int ITEM_STATE_SELECTED = 3;
    public static int item_type_day = 1;
    public static int item_type_month = 2;
    Date date;
    String day;
    public int itemState = ITEM_STATE_NORMAL;
    int itemType = 1;
    String monthStr;

    public int getItemState() {
        return this.itemState;
    }

    public void setItemState(int i) {
        this.itemState = i;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    public String getMonthStr() {
        return this.monthStr;
    }

    public void setMonthStr(String str) {
        this.monthStr = str;
    }

    public static int getItem_type_month() {
        return item_type_month;
    }

    public static void setItem_type_month(int i) {
        item_type_month = i;
    }

    public static int getItem_type_day() {
        return item_type_day;
    }

    public static void setItem_type_day(int i) {
        item_type_day = i;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String str) {
        this.day = str;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }
}
