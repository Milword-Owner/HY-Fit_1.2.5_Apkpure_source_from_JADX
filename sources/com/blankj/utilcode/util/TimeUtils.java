package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.exifinterface.media.ExifInterface;
import com.blankj.utilcode.constant.TimeConstants;
import com.huayu.tzc.utils.DateUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class TimeUtils {
    private static final String[] CHINESE_ZODIAC = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
    private static final ThreadLocal<Map<String, SimpleDateFormat>> SDF_THREAD_LOCAL = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        /* access modifiers changed from: protected */
        public Map<String, SimpleDateFormat> initialValue() {
            return new HashMap();
        }
    };
    private static final String[] ZODIAC = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
    private static final int[] ZODIAC_FLAGS = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};

    private static long timeSpan2Millis(long j, int i) {
        return j * ((long) i);
    }

    private static SimpleDateFormat getDefaultFormat() {
        return getSafeDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @SuppressLint({"SimpleDateFormat"})
    public static SimpleDateFormat getSafeDateFormat(String str) {
        Map map = SDF_THREAD_LOCAL.get();
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) map.get(str);
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str);
        map.put(str, simpleDateFormat2);
        return simpleDateFormat2;
    }

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String millis2String(long j) {
        return millis2String(j, (DateFormat) getDefaultFormat());
    }

    public static String millis2String(long j, @NonNull String str) {
        if (str != null) {
            return millis2String(j, (DateFormat) getSafeDateFormat(str));
        }
        throw new NullPointerException("Argument 'pattern' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String millis2String(long j, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return dateFormat.format(new Date(j));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static long string2Millis(String str) {
        return string2Millis(str, (DateFormat) getDefaultFormat());
    }

    public static long string2Millis(String str, @NonNull String str2) {
        if (str2 != null) {
            return string2Millis(str, (DateFormat) getSafeDateFormat(str2));
        }
        throw new NullPointerException("Argument 'pattern' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static long string2Millis(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            try {
                return dateFormat.parse(str).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                return -1;
            }
        } else {
            throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static Date string2Date(String str) {
        return string2Date(str, (DateFormat) getDefaultFormat());
    }

    public static Date string2Date(String str, @NonNull String str2) {
        if (str2 != null) {
            return string2Date(str, (DateFormat) getSafeDateFormat(str2));
        }
        throw new NullPointerException("Argument 'pattern' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Date string2Date(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            try {
                return dateFormat.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static String date2String(Date date) {
        return date2String(date, (DateFormat) getDefaultFormat());
    }

    public static String date2String(Date date, @NonNull String str) {
        if (str != null) {
            return getSafeDateFormat(str).format(date);
        }
        throw new NullPointerException("Argument 'pattern' of type String (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String date2String(Date date, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return dateFormat.format(date);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static long date2Millis(Date date) {
        return date.getTime();
    }

    public static Date millis2Date(long j) {
        return new Date(j);
    }

    public static long getTimeSpan(String str, String str2, int i) {
        return getTimeSpan(str, str2, getDefaultFormat(), i);
    }

    public static long getTimeSpan(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return millis2TimeSpan(string2Millis(str, dateFormat) - string2Millis(str2, dateFormat), i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static long getTimeSpan(Date date, Date date2, int i) {
        return millis2TimeSpan(date2Millis(date) - date2Millis(date2), i);
    }

    public static long getTimeSpan(long j, long j2, int i) {
        return millis2TimeSpan(j - j2, i);
    }

    public static String getFitTimeSpan(String str, String str2, int i) {
        return millis2FitTimeSpan(string2Millis(str, (DateFormat) getDefaultFormat()) - string2Millis(str2, (DateFormat) getDefaultFormat()), i);
    }

    public static String getFitTimeSpan(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return millis2FitTimeSpan(string2Millis(str, dateFormat) - string2Millis(str2, dateFormat), i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getFitTimeSpan(Date date, Date date2, int i) {
        return millis2FitTimeSpan(date2Millis(date) - date2Millis(date2), i);
    }

    public static String getFitTimeSpan(long j, long j2, int i) {
        return millis2FitTimeSpan(j - j2, i);
    }

    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    public static String getNowString() {
        return millis2String(System.currentTimeMillis(), (DateFormat) getDefaultFormat());
    }

    public static String getNowString(@NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return millis2String(System.currentTimeMillis(), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static long getTimeSpanByNow(String str, int i) {
        return getTimeSpan(str, getNowString(), getDefaultFormat(), i);
    }

    public static long getTimeSpanByNow(String str, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return getTimeSpan(str, getNowString(dateFormat), dateFormat, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static long getTimeSpanByNow(Date date, int i) {
        return getTimeSpan(date, new Date(), i);
    }

    public static long getTimeSpanByNow(long j, int i) {
        return getTimeSpan(j, System.currentTimeMillis(), i);
    }

    public static String getFitTimeSpanByNow(String str, int i) {
        return getFitTimeSpan(str, getNowString(), getDefaultFormat(), i);
    }

    public static String getFitTimeSpanByNow(String str, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return getFitTimeSpan(str, getNowString(dateFormat), dateFormat, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getFitTimeSpanByNow(Date date, int i) {
        return getFitTimeSpan(date, getNowDate(), i);
    }

    public static String getFitTimeSpanByNow(long j, int i) {
        return getFitTimeSpan(j, System.currentTimeMillis(), i);
    }

    public static String getFriendlyTimeSpanByNow(String str) {
        return getFriendlyTimeSpanByNow(str, getDefaultFormat());
    }

    public static String getFriendlyTimeSpanByNow(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getFriendlyTimeSpanByNow(string2Millis(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getFriendlyTimeSpanByNow(Date date) {
        return getFriendlyTimeSpanByNow(date.getTime());
    }

    public static String getFriendlyTimeSpanByNow(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis < 0) {
            return String.format("%tc", new Object[]{Long.valueOf(j)});
        } else if (currentTimeMillis < 1000) {
            return "刚刚";
        } else {
            if (currentTimeMillis < DateUtil.MINUTE_MILL_SECONDS) {
                return String.format(Locale.getDefault(), "%d秒前", new Object[]{Long.valueOf(currentTimeMillis / 1000)});
            } else if (currentTimeMillis < DateUtil.HOUR_MILL_SECONDS) {
                return String.format(Locale.getDefault(), "%d分钟前", new Object[]{Long.valueOf(currentTimeMillis / DateUtil.MINUTE_MILL_SECONDS)});
            } else {
                long weeOfToday = getWeeOfToday();
                if (j >= weeOfToday) {
                    return String.format("今天%tR", new Object[]{Long.valueOf(j)});
                } else if (j >= weeOfToday - DateUtil.DAY_MILL_SECONDS) {
                    return String.format("昨天%tR", new Object[]{Long.valueOf(j)});
                } else {
                    return String.format("%tF", new Object[]{Long.valueOf(j)});
                }
            }
        }
    }

    private static long getWeeOfToday() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    public static long getMillis(long j, long j2, int i) {
        return j + timeSpan2Millis(j2, i);
    }

    public static long getMillis(String str, long j, int i) {
        return getMillis(str, getDefaultFormat(), j, i);
    }

    public static long getMillis(String str, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return string2Millis(str, dateFormat) + timeSpan2Millis(j, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static long getMillis(Date date, long j, int i) {
        return date2Millis(date) + timeSpan2Millis(j, i);
    }

    public static String getString(long j, long j2, int i) {
        return getString(j, (DateFormat) getDefaultFormat(), j2, i);
    }

    public static String getString(long j, @NonNull DateFormat dateFormat, long j2, int i) {
        if (dateFormat != null) {
            return millis2String(j + timeSpan2Millis(j2, i), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getString(String str, long j, int i) {
        return getString(str, (DateFormat) getDefaultFormat(), j, i);
    }

    public static String getString(String str, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return millis2String(string2Millis(str, dateFormat) + timeSpan2Millis(j, i), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getString(Date date, long j, int i) {
        return getString(date, (DateFormat) getDefaultFormat(), j, i);
    }

    public static String getString(Date date, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return millis2String(date2Millis(date) + timeSpan2Millis(j, i), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Date getDate(long j, long j2, int i) {
        return millis2Date(j + timeSpan2Millis(j2, i));
    }

    public static Date getDate(String str, long j, int i) {
        return getDate(str, getDefaultFormat(), j, i);
    }

    public static Date getDate(String str, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return millis2Date(string2Millis(str, dateFormat) + timeSpan2Millis(j, i));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Date getDate(Date date, long j, int i) {
        return millis2Date(date2Millis(date) + timeSpan2Millis(j, i));
    }

    public static long getMillisByNow(long j, int i) {
        return getMillis(getNowMills(), j, i);
    }

    public static String getStringByNow(long j, int i) {
        return getStringByNow(j, getDefaultFormat(), i);
    }

    public static String getStringByNow(long j, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return getString(getNowMills(), dateFormat, j, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Date getDateByNow(long j, int i) {
        return getDate(getNowMills(), j, i);
    }

    public static boolean isToday(String str) {
        return isToday(string2Millis(str, (DateFormat) getDefaultFormat()));
    }

    public static boolean isToday(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return isToday(string2Millis(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isToday(Date date) {
        return isToday(date.getTime());
    }

    public static boolean isToday(long j) {
        long weeOfToday = getWeeOfToday();
        return j >= weeOfToday && j < weeOfToday + DateUtil.DAY_MILL_SECONDS;
    }

    public static boolean isLeapYear(String str) {
        return isLeapYear(string2Date(str, (DateFormat) getDefaultFormat()));
    }

    public static boolean isLeapYear(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return isLeapYear(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isLeapYear(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return isLeapYear(instance.get(1));
    }

    public static boolean isLeapYear(long j) {
        return isLeapYear(millis2Date(j));
    }

    public static boolean isLeapYear(int i) {
        return (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
    }

    public static String getChineseWeek(String str) {
        return getChineseWeek(string2Date(str, (DateFormat) getDefaultFormat()));
    }

    public static String getChineseWeek(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getChineseWeek(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getChineseWeek(Date date) {
        return new SimpleDateFormat(ExifInterface.LONGITUDE_EAST, Locale.CHINA).format(date);
    }

    public static String getChineseWeek(long j) {
        return getChineseWeek(new Date(j));
    }

    public static String getUSWeek(String str) {
        return getUSWeek(string2Date(str, (DateFormat) getDefaultFormat()));
    }

    public static String getUSWeek(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getUSWeek(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getUSWeek(Date date) {
        return new SimpleDateFormat("EEEE", Locale.US).format(date);
    }

    public static String getUSWeek(long j) {
        return getUSWeek(new Date(j));
    }

    public static boolean isAm() {
        return Calendar.getInstance().get(9) == 0;
    }

    public static boolean isAm(String str) {
        return getValueByCalendarField(str, getDefaultFormat(), 9) == 0;
    }

    public static boolean isAm(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getValueByCalendarField(str, dateFormat, 9) == 0;
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isAm(Date date) {
        return getValueByCalendarField(date, 9) == 0;
    }

    public static boolean isAm(long j) {
        return getValueByCalendarField(j, 9) == 0;
    }

    public static boolean isPm() {
        return !isAm();
    }

    public static boolean isPm(String str) {
        return !isAm(str);
    }

    public static boolean isPm(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return !isAm(str, dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean isPm(Date date) {
        return !isAm(date);
    }

    public static boolean isPm(long j) {
        return !isAm(j);
    }

    public static int getValueByCalendarField(int i) {
        return Calendar.getInstance().get(i);
    }

    public static int getValueByCalendarField(String str, int i) {
        return getValueByCalendarField(string2Date(str, (DateFormat) getDefaultFormat()), i);
    }

    public static int getValueByCalendarField(String str, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return getValueByCalendarField(string2Date(str, dateFormat), i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static int getValueByCalendarField(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(i);
    }

    public static int getValueByCalendarField(long j, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(i);
    }

    public static String getChineseZodiac(String str) {
        return getChineseZodiac(string2Date(str, (DateFormat) getDefaultFormat()));
    }

    public static String getChineseZodiac(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getChineseZodiac(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getChineseZodiac(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return CHINESE_ZODIAC[instance.get(1) % 12];
    }

    public static String getChineseZodiac(long j) {
        return getChineseZodiac(millis2Date(j));
    }

    public static String getChineseZodiac(int i) {
        return CHINESE_ZODIAC[i % 12];
    }

    public static String getZodiac(String str) {
        return getZodiac(string2Date(str, (DateFormat) getDefaultFormat()));
    }

    public static String getZodiac(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return getZodiac(string2Date(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getZodiac(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return getZodiac(instance.get(2) + 1, instance.get(5));
    }

    public static String getZodiac(long j) {
        return getZodiac(millis2Date(j));
    }

    public static String getZodiac(int i, int i2) {
        String[] strArr = ZODIAC;
        int i3 = i - 1;
        if (i2 < ZODIAC_FLAGS[i3]) {
            i3 = (i + 10) % 12;
        }
        return strArr[i3];
    }

    private static long millis2TimeSpan(long j, int i) {
        return j / ((long) i);
    }

    static String millis2FitTimeSpan(long j, int i) {
        if (i <= 0) {
            return null;
        }
        int min = Math.min(i, 5);
        String[] strArr = {"天", "小时", "分钟", "秒", "毫秒"};
        if (j == 0) {
            return 0 + strArr[min - 1];
        }
        StringBuilder sb = new StringBuilder();
        if (j < 0) {
            sb.append("-");
            j = -j;
        }
        int[] iArr = {TimeConstants.DAY, 3600000, TimeConstants.MIN, 1000, 1};
        for (int i2 = 0; i2 < min; i2++) {
            if (j >= ((long) iArr[i2])) {
                long j2 = j / ((long) iArr[i2]);
                j -= ((long) iArr[i2]) * j2;
                sb.append(j2);
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }
}
