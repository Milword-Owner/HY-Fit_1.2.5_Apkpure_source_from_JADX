package com.huayu.tzc.utils;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.facebook.internal.ServerProtocol;
import java.util.Calendar;
import java.util.TimeZone;

public class CalendarReminderUtils {
    private static String CALENDARS_ACCOUNT_NAME = "HY-Fit";
    private static String CALENDARS_ACCOUNT_TYPE = "com.huayu.tzc";
    private static String CALENDARS_DISPLAY_NAME = "HY-Fit";
    private static String CALENDARS_NAME = "HY-Fit";
    private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders";
    private static String CALENDER_URL = "content://com.android.calendar/calendars";

    @RequiresApi(api = 24)
    private static int checkAndAddCalendarAccount(Context context) {
        int checkCalendarAccount = checkCalendarAccount(context);
        if (checkCalendarAccount >= 0) {
            return checkCalendarAccount;
        }
        if (addCalendarAccount(context) >= 0) {
            return checkCalendarAccount(context);
        }
        return -1;
    }

    private static int checkCalendarAccount(Context context) {
        Cursor query = context.getContentResolver().query(Uri.parse(CALENDER_URL), (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null) {
            if (query != null) {
                query.close();
            }
            return -1;
        }
        try {
            if (query.getCount() > 0) {
                query.moveToFirst();
                return query.getInt(query.getColumnIndex("_id"));
            }
            if (query != null) {
                query.close();
            }
            return -1;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    private static long addCalendarAccount(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", CALENDARS_NAME);
        contentValues.put("account_name", CALENDARS_ACCOUNT_NAME);
        contentValues.put("account_type", CALENDARS_ACCOUNT_TYPE);
        contentValues.put("calendar_displayName", CALENDARS_DISPLAY_NAME);
        contentValues.put("visible", 1);
        contentValues.put("calendar_color", -16776961);
        contentValues.put("calendar_access_level", 700);
        contentValues.put("sync_events", 1);
        contentValues.put("calendar_timezone", timeZone.getID());
        contentValues.put("ownerAccount", CALENDARS_ACCOUNT_NAME);
        contentValues.put("canOrganizerRespond", 0);
        Uri insert = context.getContentResolver().insert(Uri.parse(CALENDER_URL).buildUpon().appendQueryParameter("caller_is_syncadapter", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE).appendQueryParameter("account_name", CALENDARS_ACCOUNT_NAME).appendQueryParameter("account_type", CALENDARS_ACCOUNT_TYPE).build(), contentValues);
        if (insert == null) {
            return -1;
        }
        return ContentUris.parseId(insert);
    }

    @RequiresApi(api = 24)
    public static void addCalendarEvent(Context context, String str, String str2, long j, int i) {
        int checkAndAddCalendarAccount;
        if (context != null && (checkAndAddCalendarAccount = checkAndAddCalendarAccount(context)) >= 0) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(j);
            long time = instance.getTime().getTime();
            instance.setTimeInMillis(600000 + time);
            long time2 = instance.getTime().getTime();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", str);
            contentValues.put("description", str2);
            contentValues.put("calendar_id", Integer.valueOf(checkAndAddCalendarAccount));
            contentValues.put("dtstart", Long.valueOf(time));
            contentValues.put("dtend", Long.valueOf(time2));
            contentValues.put("hasAlarm", 1);
            contentValues.put("eventTimezone", TimeZone.getDefault().getID());
            contentValues.put("rrule", "FREQ=DAILY;INTERVAL=1");
            Uri insert = context.getContentResolver().insert(Uri.parse(CALENDER_EVENT_URL), contentValues);
            if (insert != null) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", Long.valueOf(ContentUris.parseId(insert)));
                contentValues2.put("minutes", Integer.valueOf(i * 24 * 60));
                contentValues2.put("method", 1);
                if (context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), contentValues2) == null) {
                }
            }
        }
    }

    public static void deleteCalendarEvent(Context context, String str) {
        if (context != null) {
            Cursor query = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        while (!query.isAfterLast()) {
                            String string = query.getString(query.getColumnIndex("title"));
                            if (!TextUtils.isEmpty(str) && str.equals(string)) {
                                if (context.getContentResolver().delete(ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), (long) query.getInt(query.getColumnIndex("_id"))), (String) null, (String[]) null) == -1) {
                                    if (query == null) {
                                        return;
                                    }
                                    return;
                                }
                            }
                            query.moveToNext();
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } finally {
                    if (query != null) {
                        query.close();
                    }
                }
            } else if (query != null) {
                query.close();
            }
        }
    }
}
