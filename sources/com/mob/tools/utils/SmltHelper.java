package com.mob.tools.utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.appevents.codeless.internal.Constants;
import com.hjq.permissions.Permission;
import com.mob.tools.MobLog;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SmltHelper {
    private static final int[] applist1 = {92, 80, 82, 17, 84, 74, 88, 80, 74, 17, 94, 81, 91, 77, 80, 86, 91, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 78, 78, 79, 86, 82, 19, 92, 80, 82, 17, 71, 86, 82, 94, 83, 94, 70, 94, 17, 75, 86, 81, 88, 17, 94, 81, 91, 77, 80, 86, 91, 19, 92, 80, 82, 17, 86, 89, 83, 70, 75, 90, 84, 17, 86, 81, 79, 74, 75, 82, 90, 75, 87, 80, 91, 19, 92, 80, 82, 17, 92, 83, 90, 94, 81, 82, 94, 76, 75, 90, 77, 17, 82, 88, 74, 94, 77, 91, 96, 92, 81, 19, 92, 80, 82, 17, 110, 74, 81, 94, 77, 19, 92, 80, 82, 17, 94, 74, 75, 80, 81, 94, 73, 86, 17, 82, 86, 81, 86, 82, 94, 79, 19, 92, 80, 82, 17, 91, 76, 86, 17, 94, 81, 75, 17, 76, 90, 77, 73, 90, 77, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 81, 90, 72, 76, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 82, 75, 84, 83, 80, 88, 88, 90, 77, 19, 92, 80, 82, 17, 76, 76, 17, 94, 81, 91, 77, 80, 86, 91, 17, 94, 77, 75, 86, 92, 83, 90, 17, 73, 86, 91, 90, 80, 19, 92, 80, 82, 17, 94, 92, 87, 86, 90, 73, 80, 17, 73, 86, 79, 76, 87, 80, 79, 19, 92, 81, 17, 72, 79, 76, 17, 82, 80, 89, 89, 86, 92, 90, 96, 90, 81, 88, 19, 92, 80, 82, 17, 87, 94, 79, 79, 70, 90, 83, 90, 82, 90, 81, 75, 76, 17, 126, 81, 91, 77, 80, 86, 91, 126, 81, 86, 82, 94, 83, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 94, 75, 92, 86, 17, 76, 90, 77, 73, 86, 92, 90, 19, 92, 80, 82, 17, 75, 82, 77, 86, 17, 94, 79, 79, 17, 82, 94, 86, 81, 19, 92, 80, 82, 17, 78, 86, 87, 80, 80, 17, 94, 79, 79, 76, 75, 80, 77, 90, 19, 92, 80, 82, 17, 106, 124, 114, 80, 93, 86, 83, 90, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 90, 81, 88, 86, 81, 90, 90, 77, 82, 80, 91, 90, 19, 92, 80, 82, 17, 78, 74, 94, 83, 92, 80, 82, 82, 17, 72, 89, 91, 17, 76, 90, 77, 73, 86, 92, 90};
    private static final int[] applist2 = {92, 80, 82, 17, 114, 80, 93, 86, 83, 90, 107, 86, 92, 84, 90, 75, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 79, 77, 80, 73, 86, 91, 90, 77, 76, 17, 91, 77, 82, 19, 92, 80, 82, 17, 78, 75, 86, 17, 76, 90, 77, 73, 86, 92, 90, 17, 92, 80, 83, 80, 77, 76, 90, 77, 73, 86, 92, 90, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 94, 81, 91, 77, 80, 86, 91, 17, 78, 78, 91, 80, 72, 81, 83, 80, 94, 91, 90, 77, 19, 92, 80, 82, 17, 91, 86, 94, 81, 79, 86, 81, 88, 17, 73, 14, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 89, 72, 84, 17, 79, 83, 74, 88, 86, 81, 19, 92, 80, 82, 17, 88, 77, 90, 90, 81, 79, 80, 86, 81, 75, 17, 94, 81, 91, 77, 80, 86, 91, 17, 82, 92, 14, 15, 15, 7, 9, 17, 94, 92, 75, 86, 73, 86, 75, 70, 19, 92, 80, 82, 17, 78, 74, 94, 83, 92, 80, 82, 82, 17, 75, 86, 82, 90, 76, 90, 77, 73, 86, 92, 90, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 19, 92, 80, 82, 17, 78, 74, 94, 83, 92, 80, 82, 82, 17, 78, 92, 77, 86, 83, 82, 76, 88, 75, 74, 81, 81, 90, 83, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 82, 80, 93, 86, 83, 90, 78, 78, 19, 92, 80, 82, 17, 76, 76, 17, 94, 81, 91, 77, 80, 86, 91, 17, 74, 88, 92, 17, 94, 72, 90, 82, 90, 19, 92, 80, 82, 17, 92, 83, 90, 94, 81, 82, 94, 76, 75, 90, 77, 17, 76, 91, 84, 19, 92, 80, 82, 17, 71, 74, 81, 82, 90, 81, 88, 17, 79, 86, 81, 91, 74, 80, 91, 74, 80, 19, 92, 80, 82, 17, 72, 74, 93, 94, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 83, 93, 76, 17, 90, 82, 13, 17, 74, 86, 19, 92, 80, 82, 17, 76, 86, 81, 94, 17, 72, 90, 86, 93, 80, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 80, 82, 94, 92, 79, 19, 92, 80, 82, 17, 94, 82, 94, 79, 17, 94, 81, 91, 77, 80, 86, 91, 17, 83, 80, 92, 94, 75, 86, 80, 81, 19, 92, 81, 17, 84, 74, 72, 80, 17, 79, 83, 94, 70, 90, 77};
    private static final int[] applist3 = {92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 82, 75, 75, 19, 92, 80, 82, 17, 78, 86, 70, 86, 17, 73, 86, 91, 90, 80, 19, 92, 80, 82, 17, 88, 80, 80, 88, 83, 90, 17, 94, 81, 91, 77, 80, 86, 91, 17, 72, 90, 93, 73, 86, 90, 72, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 84, 94, 77, 94, 80, 84, 90, 19, 92, 75, 77, 86, 79, 17, 94, 81, 91, 77, 80, 86, 91, 17, 73, 86, 90, 72, 19, 92, 80, 82, 17, 88, 80, 80, 88, 83, 90, 17, 94, 81, 91, 77, 80, 86, 91, 17, 88, 76, 89, 19, 92, 80, 82, 17, 93, 94, 86, 91, 74, 17, 81, 90, 75, 91, 86, 76, 84, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 94, 81, 91, 77, 80, 86, 91, 78, 78, 82, 94, 86, 83, 19, 92, 80, 82, 17, 94, 81, 91, 77, 80, 86, 91, 17, 83, 80, 92, 94, 75, 86, 80, 81, 17, 89, 74, 76, 90, 91, 19, 80, 77, 88, 17, 92, 80, 91, 90, 94, 74, 77, 80, 77, 94, 17, 93, 83, 74, 90, 75, 80, 80, 75, 87, 19, 92, 80, 82, 17, 82, 75, 17, 82, 75, 71, 71, 17, 82, 75, 71, 71, 19, 92, 80, 82, 17, 85, 86, 81, 88, 91, 80, 81, 88, 17, 94, 79, 79, 17, 82, 94, 83, 83, 19, 92, 80, 82, 17, 78, 74, 94, 83, 92, 80, 82, 82, 17, 86, 81, 75, 90, 77, 89, 94, 92, 90, 79, 90, 77, 82, 86, 76, 76, 86, 80, 81, 76, 19, 92, 80, 82, 17, 93, 94, 86, 91, 74, 17, 94, 79, 79, 76, 90, 94, 77, 92, 87, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 72, 86, 89, 86, 82, 94, 81, 94, 88, 90, 77, 19, 92, 80, 82, 17, 93, 94, 86, 91, 74, 17, 87, 94, 80, 84, 94, 81, 19, 92, 80, 82, 17, 73, 86, 73, 80, 17, 90, 94, 76, 70, 76, 87, 94, 77, 90, 19, 92, 80, 82, 17, 75, 94, 80, 93, 94, 80, 17, 75, 94, 80, 93, 94, 80, 19, 92, 80, 82, 17, 92, 80, 83, 80, 77, 80, 76, 17, 72, 90, 94, 75, 87, 90, 77, 19, 80, 77, 88, 17, 76, 86, 82, 94, 83, 83, 86, 94, 81, 92, 90, 17, 80, 79, 90, 81, 82, 80, 93, 86, 83, 90, 94, 79, 86, 17, 76, 90, 77, 73, 86, 92, 90};
    private static final int[] applist4 = {92, 80, 82, 17, 78, 74, 94, 83, 92, 80, 82, 82, 17, 83, 80, 92, 94, 75, 86, 80, 81, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 93, 94, 75, 75, 90, 77, 70, 72, 94, 77, 81, 86, 81, 88, 19, 92, 80, 82, 17, 93, 94, 86, 91, 74, 17, 125, 94, 86, 91, 74, 114, 94, 79, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 78, 78, 82, 74, 76, 86, 92, 19, 92, 80, 82, 17, 76, 73, 80, 71, 17, 79, 86, 92, 80, 19, 92, 80, 82, 17, 93, 93, 84, 17, 86, 78, 80, 80, 17, 89, 90, 90, 91, 93, 94, 92, 84, 19, 92, 80, 82, 17, 70, 80, 74, 84, 74, 17, 79, 87, 80, 81, 90, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 78, 78, 79, 86, 82, 76, 90, 92, 74, 77, 90, 19, 92, 80, 82, 17, 93, 94, 86, 91, 74, 17, 76, 90, 94, 77, 92, 87, 93, 80, 71, 19, 92, 80, 82, 17, 76, 94, 81, 84, 74, 94, 86, 17, 82, 90, 86, 75, 74, 94, 81, 19, 92, 80, 82, 17, 78, 74, 94, 83, 92, 80, 82, 82, 17, 76, 87, 74, 75, 91, 80, 72, 81, 83, 86, 76, 75, 81, 90, 77, 19, 92, 80, 82, 17, 78, 86, 87, 80, 80, 12, 9, 15, 17, 82, 80, 93, 86, 83, 90, 76, 94, 89, 90, 19, 92, 80, 82, 17, 86, 89, 83, 70, 75, 90, 84, 17, 76, 79, 90, 90, 92, 87, 92, 83, 80, 74, 91, 19, 92, 80, 82, 17, 90, 88, 17, 94, 81, 91, 77, 80, 86, 91, 17, 126, 83, 86, 79, 94, 70, 120, 79, 87, 80, 81, 90, 19, 92, 80, 82, 17, 88, 80, 80, 88, 83, 90, 17, 94, 81, 91, 77, 80, 86, 91, 17, 76, 70, 81, 92, 94, 91, 94, 79, 75, 90, 77, 76, 17, 92, 80, 81, 75, 94, 92, 75, 76, 19, 92, 80, 82, 17, 88, 80, 80, 88, 83, 90, 17, 94, 81, 91, 77, 80, 86, 91, 17, 76, 70, 81, 92, 94, 91, 94, 79, 75, 90, 77, 76, 17, 92, 94, 83, 90, 81, 91, 94, 77, 19, 92, 80, 82, 17, 93, 94, 80, 89, 90, 81, 88, 17, 82, 85, 17, 73, 86, 91, 90, 80, 79, 83, 74, 88, 86, 81, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 82, 82, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 75, 87, 90, 77, 82, 94, 83, 82, 94, 81, 94, 88, 90, 77, 19, 92, 80, 82, 17, 76, 91, 74, 17, 91, 86, 91, 86, 17, 79, 76, 81, 88, 90, 77};
    private static final int[] applist5 = {92, 80, 82, 17, 76, 75, 80, 77, 82, 17, 76, 75, 80, 77, 82, 88, 94, 82, 90, 19, 92, 80, 82, 17, 76, 76, 17, 94, 81, 91, 77, 80, 86, 91, 17, 74, 88, 92, 17, 83, 86, 73, 90, 19, 80, 77, 88, 17, 92, 80, 91, 90, 94, 74, 77, 80, 77, 94, 17, 86, 82, 76, 19, 92, 80, 82, 17, 75, 82, 94, 83, 83, 17, 72, 86, 77, 90, 83, 90, 76, 76, 19, 92, 80, 82, 17, 93, 89, 88, 94, 82, 90, 17, 94, 79, 79, 19, 92, 80, 82, 17, 78, 74, 86, 92, 86, 81, 92, 17, 92, 81, 90, 17, 124, 113, 122, 108, 90, 77, 73, 86, 92, 90, 19, 92, 80, 82, 17, 93, 94, 86, 91, 74, 17, 82, 94, 79, 17, 83, 80, 92, 94, 75, 86, 80, 81, 19, 92, 80, 82, 17, 76, 76, 17, 94, 81, 91, 77, 80, 86, 91, 17, 94, 77, 75, 86, 92, 83, 90, 17, 83, 86, 75, 90, 19, 92, 80, 82, 17, 76, 81, 91, 94, 17, 72, 86, 89, 86, 83, 80, 92, 94, 75, 86, 81, 88, 19, 92, 80, 82, 17, 76, 80, 87, 74, 17, 86, 81, 79, 74, 75, 82, 90, 75, 87, 80, 91, 17, 76, 80, 88, 80, 74, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 75, 82, 88, 79, 17, 76, 88, 94, 82, 90, 19, 92, 80, 82, 17, 78, 74, 94, 83, 92, 80, 82, 82, 17, 78, 92, 80, 82, 96, 78, 82, 86, 19, 92, 80, 82, 17, 82, 90, 91, 86, 94, 75, 90, 84, 17, 70, 88, 79, 76, 19, 92, 80, 82, 17, 75, 90, 81, 92, 90, 81, 75, 17, 78, 78, 83, 86, 73, 90, 19, 92, 80, 82, 17, 76, 76, 17, 94, 81, 91, 77, 80, 86, 91, 17, 94, 77, 75, 86, 92, 83, 90, 17, 81, 90, 72, 76, 19, 92, 80, 82, 17, 92, 87, 86, 81, 94, 82, 72, 80, 77, 83, 91, 17, 82, 94, 86, 81, 19, 92, 80, 82, 17, 88, 80, 80, 88, 83, 90, 17, 94, 81, 91, 77, 80, 86, 91, 17, 88, 76, 89, 17, 83, 80, 88, 86, 81, 19, 92, 80, 82, 17, 82, 90, 86, 75, 74, 17, 82, 90, 86, 70, 94, 81, 92, 94, 82, 90, 77, 94, 19, 92, 80, 82, 17, 76, 82, 86, 83, 90, 17, 88, 86, 89, 82, 94, 84, 90, 77};

    public int checkBaseband(Context context) {
        try {
            String baseband = DeviceHelper.getInstance(context).getBaseband();
            if (TextUtils.isEmpty(baseband) || baseband.contains("1.0.0.0")) {
                return 1;
            }
            return 0;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    public int checkBoard(Context context) {
        try {
            String boardFromSysProperty = DeviceHelper.getInstance(context).getBoardFromSysProperty();
            if (TextUtils.isEmpty(boardFromSysProperty) || boardFromSysProperty.indexOf(Constants.PLATFORM) > -1 || boardFromSysProperty.indexOf("goldfish") > -1) {
                return 1;
            }
            return 0;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    public int checkPlatform(Context context) {
        try {
            String boardPlatform = DeviceHelper.getInstance(context).getBoardPlatform();
            if (TextUtils.isEmpty(boardPlatform) || boardPlatform.indexOf(Constants.PLATFORM) > -1 || boardPlatform.indexOf("sdk_gphone") > -1) {
                return 1;
            }
            return 0;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    public int checkFlavor(Context context) {
        try {
            if (DeviceHelper.getInstance(context).getFlavor().indexOf("vbox") > -1) {
                return 1;
            }
            return 0;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    public int checkCgroup() {
        try {
            InputStream invokeRuntimeExec = invokeRuntimeExec(new String[]{"cat", "/proc/self/cgroup"});
            if (invokeRuntimeExec == null || !TextUtils.isEmpty(new BufferedReader(new InputStreamReader(invokeRuntimeExec)).readLine())) {
                return 0;
            }
            return 1;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    @SuppressLint({"MissingPermission"})
    public int checkBluetooth(Context context) {
        try {
            if (!DeviceHelper.getInstance(context).checkPermission("android.permission.BLUETOOTH")) {
                return 0;
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || DeviceHelper.getInstance(context).getBluetoothName() == null || defaultAdapter.getAddress() == null) {
                return 1;
            }
            return 0;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    public int checkImei(Context context) {
        try {
            if (!DeviceHelper.getInstance(context).checkPermission(Permission.READ_PHONE_STATE)) {
                return -1;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                return -1;
            }
            String imei = DeviceHelper.getInstance(context).getIMEI();
            if (TextUtils.isEmpty(imei)) {
                return 0;
            }
            if (imei.contains("*") || zeroCount(imei) == imei.length()) {
                return 1;
            }
            return 0;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    private int zeroCount(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '0') {
                i++;
            }
        }
        return i;
    }

    public int checkCommonApp(Context context) {
        int i;
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(getTargetSystemPkgList(applist1));
            arrayList.addAll(getTargetSystemPkgList(applist2));
            arrayList.addAll(getTargetSystemPkgList(applist3));
            arrayList.addAll(getTargetSystemPkgList(applist4));
            arrayList.addAll(getTargetSystemPkgList(applist5));
            i = 0;
            for (String packageInfo : arrayList) {
                try {
                    PackageInfo packageInfo2 = null;
                    try {
                        packageInfo2 = context.getPackageManager().getPackageInfo(packageInfo, 0);
                    } catch (Throwable unused) {
                    }
                    if (packageInfo2 != null) {
                        if ((packageInfo2.applicationInfo.flags & 1) == 0) {
                            i++;
                        }
                    }
                } catch (Throwable unused2) {
                }
            }
        } catch (Throwable unused3) {
            i = 0;
        }
        if (i == 0) {
            return 1;
        }
        return 0;
    }

    private List<String> getTargetSystemPkgList(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        try {
            for (String add : decode(iArr).split(",")) {
                arrayList.add(add);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return arrayList;
    }

    public int checkCpuInfo() {
        String readCpuInfo = readCpuInfo();
        return (readCpuInfo.indexOf("intel") > -1 || readCpuInfo.indexOf("amd") > -1) ? 1 : 0;
    }

    private String readCpuInfo() {
        try {
            InputStream invokeRuntimeExec = invokeRuntimeExec(new String[]{"/system/bin/cat", Strings.getString(41)});
            if (invokeRuntimeExec == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(invokeRuntimeExec, "utf-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString().toLowerCase();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return "";
        }
    }

    public int checkSensor(Context context) {
        if (BVS.HAS_SS != null) {
            return BVS.HAS_SS.intValue();
        }
        int i = 0;
        try {
            Sensor defaultSensor = ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(1);
            Sensor defaultSensor2 = ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(2);
            Sensor defaultSensor3 = ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(5);
            if (defaultSensor == null && defaultSensor2 == null && defaultSensor3 == null) {
                i = 1;
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        BVS.HAS_SS = Integer.valueOf(i);
        return i;
    }

    private String decode(int[] iArr) {
        int length = iArr.length;
        char[] cArr = new char[length];
        int min = Math.min(length, 63);
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (iArr[i] ^ min);
        }
        return String.valueOf(cArr);
    }

    private InputStream invokeRuntimeExec(String[] strArr) {
        try {
            return (InputStream) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(42)), Strings.getString(43), new Object[0]), Strings.getString(44), strArr), Strings.getString(45), new Object[0]);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }
}
