package com.blankj.utilcode.constant;

import android.annotation.SuppressLint;
import android.os.Build;
import com.hjq.permissions.Permission;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint({"InlinedApi"})
public final class PermissionConstants {
    public static final String ACTIVITY_RECOGNITION = "ACTIVITY_RECOGNITION";
    public static final String CALENDAR = "CALENDAR";
    public static final String CAMERA = "CAMERA";
    public static final String CONTACTS = "CONTACTS";
    private static final String[] GROUP_ACTIVITY_RECOGNITION = {Permission.ACTIVITY_RECOGNITION};
    private static final String[] GROUP_CALENDAR = {Permission.READ_CALENDAR, Permission.WRITE_CALENDAR};
    private static final String[] GROUP_CAMERA = {Permission.CAMERA};
    private static final String[] GROUP_CONTACTS = {Permission.READ_CONTACTS, Permission.WRITE_CONTACTS, Permission.GET_ACCOUNTS};
    private static final String[] GROUP_LOCATION = {Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_BACKGROUND_LOCATION};
    private static final String[] GROUP_MICROPHONE = {Permission.RECORD_AUDIO};
    private static final String[] GROUP_PHONE = {Permission.READ_PHONE_STATE, Permission.READ_PHONE_NUMBERS, Permission.CALL_PHONE, Permission.READ_CALL_LOG, Permission.WRITE_CALL_LOG, Permission.ADD_VOICEMAIL, Permission.USE_SIP, Permission.PROCESS_OUTGOING_CALLS, Permission.ANSWER_PHONE_CALLS};
    private static final String[] GROUP_PHONE_BELOW_O = {Permission.READ_PHONE_STATE, Permission.READ_PHONE_NUMBERS, Permission.CALL_PHONE, Permission.READ_CALL_LOG, Permission.WRITE_CALL_LOG, Permission.ADD_VOICEMAIL, Permission.USE_SIP, Permission.PROCESS_OUTGOING_CALLS};
    private static final String[] GROUP_SENSORS = {Permission.BODY_SENSORS};
    private static final String[] GROUP_SMS = {Permission.SEND_SMS, Permission.RECEIVE_SMS, Permission.READ_SMS, Permission.RECEIVE_WAP_PUSH, Permission.RECEIVE_MMS};
    private static final String[] GROUP_STORAGE = {Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE};
    public static final String LOCATION = "LOCATION";
    public static final String MICROPHONE = "MICROPHONE";
    public static final String PHONE = "PHONE";
    public static final String SENSORS = "SENSORS";
    public static final String SMS = "SMS";
    public static final String STORAGE = "STORAGE";

    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionGroup {
    }

    public static String[] getPermissions(String str) {
        if (str == null) {
            return new String[0];
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1611296843:
                if (str.equals(LOCATION)) {
                    c = 3;
                    break;
                }
                break;
            case -1596608551:
                if (str.equals(SENSORS)) {
                    c = 6;
                    break;
                }
                break;
            case -1166291365:
                if (str.equals(STORAGE)) {
                    c = 8;
                    break;
                }
                break;
            case 82233:
                if (str.equals(SMS)) {
                    c = 7;
                    break;
                }
                break;
            case 76105038:
                if (str.equals(PHONE)) {
                    c = 5;
                    break;
                }
                break;
            case 140654183:
                if (str.equals(ACTIVITY_RECOGNITION)) {
                    c = 9;
                    break;
                }
                break;
            case 215175251:
                if (str.equals(CONTACTS)) {
                    c = 2;
                    break;
                }
                break;
            case 604302142:
                if (str.equals(CALENDAR)) {
                    c = 0;
                    break;
                }
                break;
            case 1856013610:
                if (str.equals(MICROPHONE)) {
                    c = 4;
                    break;
                }
                break;
            case 1980544805:
                if (str.equals(CAMERA)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return GROUP_CALENDAR;
            case 1:
                return GROUP_CAMERA;
            case 2:
                return GROUP_CONTACTS;
            case 3:
                return GROUP_LOCATION;
            case 4:
                return GROUP_MICROPHONE;
            case 5:
                if (Build.VERSION.SDK_INT < 26) {
                    return GROUP_PHONE_BELOW_O;
                }
                return GROUP_PHONE;
            case 6:
                return GROUP_SENSORS;
            case 7:
                return GROUP_SMS;
            case 8:
                return GROUP_STORAGE;
            case 9:
                return GROUP_ACTIVITY_RECOGNITION;
            default:
                return new String[]{str};
        }
    }
}
