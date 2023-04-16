package com.huayu.tzc.utils;

import android.util.Log;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.PropertyType;
import com.github.mikephil.charting.utils.Utils;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

public class DateUtil {
    public static final long DAY_MILL_SECONDS = 86400000;
    public static final long HOUR_MILL_SECONDS = 3600000;
    public static final long MINUTE_MILL_SECONDS = 60000;
    public static final long SEC_MILL_SECONDS = 1000;

    public static String getMonthToFirstDay(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(5, 1);
        return simpleDateFormat.format(instance.getTime());
    }

    public static String getMonthToEndDay(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(5, instance.getActualMaximum(5));
        return simpleDateFormat.format(instance.getTime());
    }

    public static Date getLastMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(2, instance.get(2) - 1);
        return instance.getTime();
    }

    public static Date getNextMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(2, instance.get(2) + 1);
        return instance.getTime();
    }

    public static boolean isToday(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        if (instance.get(1) == instance2.get(1) && instance.get(2) == instance2.get(2) && instance.get(5) == instance2.get(5)) {
            return true;
        }
        return false;
    }

    public static Date getDateBefore(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(5, instance.get(5) - i);
        return instance.getTime();
    }

    public static int todayWeek(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(7);
    }

    public static boolean isSameWeekWithToday(Date date, Date date2) {
        if (date == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(date2);
        instance2.setTime(date);
        if (instance.get(3) == instance2.get(3)) {
            return true;
        }
        return false;
    }

    public static boolean matchSameMonth(Date date, Date date2, Integer num) {
        if (num != null && 1 == num.intValue()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);
            if (simpleDateFormat.format(date).equals(simpleDateFormat.format(date2))) {
                return true;
            }
        }
        return false;
    }

    public static long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    public static String getCurrentTimeYMDHMS() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        return i + "-" + i2 + 1 + "-" + i3 + " " + i4 + Config.TRACE_TODAY_VISIT_SPLIT + i5 + Config.TRACE_TODAY_VISIT_SPLIT + i6;
    }

    public static String getCurrentTimeYMD() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        instance.get(11);
        instance.get(12);
        instance.get(13);
        return i + "-" + i2 + 1 + "-" + i3;
    }

    public static String dateToStamp(String str) throws ParseException {
        return String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(str).getTime());
    }

    public static String stampToDate(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date(new Long(str).longValue()));
    }

    public static String dateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
    }

    public static Date getNowDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        return simpleDateFormat.parse(simpleDateFormat.format(date), new ParsePosition(8));
    }

    public static Date getNowDateShort() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return simpleDateFormat.parse(simpleDateFormat.format(date), new ParsePosition(8));
    }

    public static String getStringDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date());
    }

    public static String getStringDateShort() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
    }

    public static String getTimeShort() {
        return new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date());
    }

    public static Date strToDateLong(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(str, new ParsePosition(0));
    }

    public static String dateToStrLong(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(date);
    }

    public static String dateToStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
    }

    public static Date strToDate(String str) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(str, new ParsePosition(0));
    }

    public static Date getNow() {
        return new Date();
    }

    public static Date getLastDate(long j) {
        return new Date(new Date().getTime() - (j * 122400000));
    }

    public static String getStringToday() {
        return new SimpleDateFormat("yyyyMMdd HHmmss", Locale.ENGLISH).format(new Date());
    }

    public static String getHour() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date()).substring(11, 13);
    }

    public static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date()).substring(14, 16);
    }

    public static String getUserDate(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(new Date());
    }

    public static String getTwoHour(String str, String str2) {
        String[] split = str.split(Config.TRACE_TODAY_VISIT_SPLIT);
        String[] split2 = str2.split(Config.TRACE_TODAY_VISIT_SPLIT);
        if (Integer.parseInt(split[0]) < Integer.parseInt(split2[0])) {
            return "0";
        }
        double parseDouble = (Double.parseDouble(split[0]) + (Double.parseDouble(split[1]) / 60.0d)) - (Double.parseDouble(split2[0]) + (Double.parseDouble(split2[1]) / 60.0d));
        if (parseDouble <= Utils.DOUBLE_EPSILON) {
            return "0";
        }
        return parseDouble + "";
    }

    public static boolean getTwoDay(String str, String str2, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date parse = simpleDateFormat.parse(str);
            Date parse2 = simpleDateFormat.parse(str2);
            if (date.getTime() < parse.getTime() || date.getTime() > parse2.getTime()) {
                return false;
            }
            return true;
        } catch (Exception unused) {
        }
    }

    public static boolean getTwoDay(Date date, Date date2) {
        Log.e("TAG", "getTwoDay: " + date2.getTime() + "  " + date.getTime());
        if (date2.getTime() > date.getTime()) {
            Log.e("TAG", "getTwoDay: false ");
            return false;
        }
        Log.e("TAG", "getTwoDay: true ");
        return true;
    }

    public static String getPreTime(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        try {
            Date parse = simpleDateFormat.parse(str);
            parse.setTime(((parse.getTime() / 1000) + ((long) (Integer.parseInt(str2) * 60))) * 1000);
            return simpleDateFormat.format(parse);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getNextDay(String str, String str2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date strToDate = strToDate(str);
            strToDate.setTime(((strToDate.getTime() / 1000) + ((long) (Integer.parseInt(str2) * 24 * 60 * 60))) * 1000);
            return simpleDateFormat.format(strToDate);
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isLeapYear(String str) {
        Date strToDate = strToDate(str);
        GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance();
        gregorianCalendar.setTime(strToDate);
        int i = gregorianCalendar.get(1);
        if (i % 400 == 0) {
            return true;
        }
        if (i % 4 != 0 || i % 100 == 0) {
            return false;
        }
        return true;
    }

    public static String getEDate(String str) {
        String[] split = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(str, new ParsePosition(0)).toString().split(" ");
        return split[2] + split[1].toUpperCase() + split[5].substring(2, 4);
    }

    public static String getEndDateOfMonth(String str) {
        String substring = str.substring(0, 8);
        int parseInt = Integer.parseInt(str.substring(5, 7));
        if (parseInt == 1 || parseInt == 3 || parseInt == 5 || parseInt == 7 || parseInt == 8 || parseInt == 10 || parseInt == 12) {
            return substring + "31";
        } else if (parseInt == 4 || parseInt == 6 || parseInt == 9 || parseInt == 11) {
            return substring + "30";
        } else if (isLeapYear(str)) {
            return substring + "29";
        } else {
            return substring + "28";
        }
    }

    public static boolean isSameWeekDates(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(date);
        instance2.setTime(date2);
        int i = instance.get(1) - instance2.get(1);
        if (i == 0) {
            if (instance.get(3) == instance2.get(3)) {
                return true;
            }
            return false;
        } else if (1 == i && 11 == instance2.get(2)) {
            if (instance.get(3) == instance2.get(3)) {
                return true;
            }
            return false;
        } else if (-1 == i && 11 == instance.get(2) && instance.get(3) == instance2.get(3)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getSeqWeek() {
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        String num = Integer.toString(instance.get(3));
        if (num.length() == 1) {
            num = "0" + num;
        }
        return Integer.toString(instance.get(1)) + num;
    }

    public static String getWeek(String str, String str2) {
        Date strToDate = strToDate(str);
        Calendar instance = Calendar.getInstance();
        instance.setTime(strToDate);
        if (str2.equals("1")) {
            instance.set(7, 2);
        } else if (str2.equals("2")) {
            instance.set(7, 3);
        } else if (str2.equals("3")) {
            instance.set(7, 4);
        } else if (str2.equals(PropertyType.PAGE_PROPERTRY)) {
            instance.set(7, 5);
        } else if (str2.equals("5")) {
            instance.set(7, 6);
        } else if (str2.equals("6")) {
            instance.set(7, 7);
        } else if (str2.equals("0")) {
            instance.set(7, 1);
        }
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(instance.getTime());
    }

    public static String getWeek(String str) {
        Date strToDate = strToDate(str);
        Calendar instance = Calendar.getInstance();
        instance.setTime(strToDate);
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(instance.getTime());
    }

    public static String getWeekStr(String str) {
        String week = getWeek(str);
        if ("1".equals(week)) {
            return "星期日";
        }
        if ("2".equals(week)) {
            return "星期一";
        }
        if ("3".equals(week)) {
            return "星期二";
        }
        if (PropertyType.PAGE_PROPERTRY.equals(week)) {
            return "星期三";
        }
        if ("5".equals(week)) {
            return "星期四";
        }
        if ("6".equals(week)) {
            return "星期五";
        }
        return "7".equals(week) ? "星期六" : week;
    }

    public static long getDays(String str, String str2) {
        Date date;
        if (str == null || str.equals("") || str2 == null || str2.equals("")) {
            return 0;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date2 = null;
        try {
            date = simpleDateFormat.parse(str);
            try {
                date2 = simpleDateFormat.parse(str2);
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            date = null;
        }
        if (date.getTime() > date2.getTime()) {
            return (date.getTime() - date2.getTime()) / DAY_MILL_SECONDS;
        }
        return (date2.getTime() - date.getTime()) / DAY_MILL_SECONDS;
    }

    public static long getDays(Date date, Date date2) {
        if (date.getTime() > date2.getTime()) {
            return (date.getTime() - date2.getTime()) / DAY_MILL_SECONDS;
        }
        return (date2.getTime() - date.getTime()) / DAY_MILL_SECONDS;
    }

    public static String getNowMonth(String str) {
        String str2 = str.substring(0, 8) + "01";
        Date strToDate = strToDate(str2);
        Calendar instance = Calendar.getInstance();
        instance.setTime(strToDate);
        int i = instance.get(7);
        return getNextDay(str2, (1 - i) + "");
    }

    public static String getNo(int i) {
        return getUserDate("yyyyMMddhhmmss") + getRandom(i);
    }

    public static String getRandom(int i) {
        Random random = new Random();
        String str = "";
        if (i == 0) {
            return str;
        }
        for (int i2 = 0; i2 < i; i2++) {
            str = str + random.nextInt(9);
        }
        return str;
    }

    public static Date getFirstDayOfWeek(int i, int i2) {
        GregorianCalendar gregorianCalendar = (GregorianCalendar) getFirstDayOfWeek(i).clone();
        gregorianCalendar.add(5, i2 * 7);
        return getFirstDayOfWeek(gregorianCalendar.getTime());
    }

    public static Date getLastDayOfWeek(int i, int i2) {
        GregorianCalendar gregorianCalendar = (GregorianCalendar) getFirstDayOfWeek(i).clone();
        gregorianCalendar.add(5, i2 * 7);
        return getLastDayOfWeek(gregorianCalendar.getTime());
    }

    public static Calendar getFirstDayOfWeek(int i) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(i, 0, 1, 0, 0, 0);
        int i2 = gregorianCalendar.get(7);
        if (i2 == 6 || i2 == 7 || i2 == 5) {
            while (gregorianCalendar.get(7) != 1) {
                gregorianCalendar.add(6, 1);
            }
        }
        return gregorianCalendar;
    }

    public static Date getFirstDayOfWeek(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setFirstDayOfWeek(1);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(7, gregorianCalendar.getFirstDayOfWeek());
        return gregorianCalendar.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setFirstDayOfWeek(1);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(7, gregorianCalendar.getFirstDayOfWeek() + 6);
        return gregorianCalendar.getTime();
    }

    public static boolean isLastDayInYearLastWeek(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int i = gregorianCalendar.get(7);
        return i == 5 || i == 6 || i == 7 || i == 4;
    }

    public static Date getLastDayOfYear(int i) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(i, 11, 31, 0, 0, 0);
        return gregorianCalendar.getTime();
    }

    public static boolean isEffectiveDate(Date date, Date date2, Date date3) {
        if (Math.abs(date.getTime() - date2.getTime()) < 1000 || Math.abs(date.getTime() - date3.getTime()) < 1000) {
            return true;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.setTime(date2);
        GregorianCalendar gregorianCalendar3 = new GregorianCalendar();
        gregorianCalendar3.setTime(date3);
        if (!gregorianCalendar.after(gregorianCalendar2) || !gregorianCalendar.before(gregorianCalendar3)) {
            return false;
        }
        return true;
    }

    public static String[] getWeekFirstAndLastDay(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return new String[]{simpleDateFormat.format(date), simpleDateFormat.format(new Date(date.getTime() + 518400000))};
    }
}
