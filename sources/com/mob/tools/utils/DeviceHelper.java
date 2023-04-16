package com.mob.tools.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.location.Location;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Size;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.baidu.mobstat.Config;
import com.baidubce.BceConfig;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.hjq.permissions.Permission;
import com.huayu.tzc.utils.DateUtil;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.BHelper;
import com.mob.tools.utils.ReflectHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p005cn.sharesdk.onekeyshare.OnekeyShare;
import p015io.reactivex.annotations.SchedulerSupport;

public class DeviceHelper {
    private static DeviceHelper deviceHelper;
    private String advertiseID;
    private ArrayList<String> bufIm = new ArrayList<>();
    private HashMap<String, String> bufMacs = new HashMap<>();
    private String bufModel;
    private String bufUiVersion;
    private BVS bvs;
    private String cacheDeviceKey = null;
    private Context context;
    private volatile boolean hasSdcardWritePermission = false;
    private String imei;
    private String[] invalidMacList;
    private Boolean isSmlt;

    /* renamed from: ln */
    private String f2349ln = "-1";
    private int sActCnt = -1;
    private List<Object> sActList;
    private String serialno;
    private String swVer = "-1";
    private String wifimac;

    public interface BtScanCallback {
        void onScan(ArrayList<HashMap<String, Object>> arrayList);
    }

    public static class BtWatcher {
        /* access modifiers changed from: protected */
        public void onBtConnectionChanged(boolean z, HashMap<String, Object> hashMap) {
        }

        /* access modifiers changed from: protected */
        public void onBtDisabled() {
        }

        /* access modifiers changed from: protected */
        public void onBtEnabled() {
        }

        /* access modifiers changed from: protected */
        public void onDeviceConnected(HashMap<String, Object> hashMap) {
        }

        /* access modifiers changed from: protected */
        public void onDeviceDisconnected(HashMap<String, Object> hashMap) {
        }
    }

    public int getPlatformCode() {
        return 1;
    }

    public static synchronized DeviceHelper getInstance(Context context2) {
        DeviceHelper deviceHelper2;
        synchronized (DeviceHelper.class) {
            if (deviceHelper == null && context2 != null) {
                deviceHelper = new DeviceHelper(context2);
            }
            deviceHelper2 = deviceHelper;
        }
        return deviceHelper2;
    }

    private DeviceHelper(Context context2) {
        this.context = context2.getApplicationContext();
        this.bvs = new BVS();
    }

    public boolean isRooted() {
        if ((Build.TAGS != null && Build.TAGS.contains("test-keys")) || checkRootFile() || checkRootApp() || checkRootSu() || checkRootRw() || checkRootProp()) {
            return true;
        }
        return false;
    }

    private boolean checkRootFile() {
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
            String[] strArr = {"/data/local/", "/data/local/bin/", "/data/local/xbin/", "/sbin/", "/su/bin/", "/system/bin/", "/system/bin/.ext/", "/system/bin/failsafe/", "/system/sd/xbin/", "/system/usr/we-need-root/", "/system/xbin/", "/system/sbin/", "/vendor/bin/", "/cache", "/data", "/dev"};
            for (String file : strArr) {
                if (new File(file, "su").exists()) {
                    return true;
                }
            }
            for (String file2 : strArr) {
                if (new File(file2, "busybox").exists()) {
                    return true;
                }
            }
            for (String file3 : strArr) {
                if (new File(file3, "magisk").exists()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    private boolean checkRootApp() {
        try {
            for (String isPackageInstalled : new String[]{"com.noshufou.android.su", "com.noshufou.android.su.elite", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su", "com.topjohnwu.magisk", "com.koushikdutta.rommanager", "com.koushikdutta.rommanager.license", "com.dimonvideo.luckypatcher", "com.chelpus.lackypatch", "com.ramdroid.appquarantine", "com.ramdroid.appquarantinepro", "com.android.vending.billing.InAppBillingService.COIN", "com.chelpus.luckypatcher"}) {
                if (deviceHelper.isPackageInstalled(isPackageInstalled)) {
                    return true;
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return false;
    }

    private boolean checkRootProp() {
        String[] split;
        try {
            InputStream invokeRuntimeExec = invokeRuntimeExec(new String[]{"getprop"});
            if (!(invokeRuntimeExec == null || (split = new Scanner(invokeRuntimeExec).useDelimiter("\\A").next().split("\n")) == null)) {
                HashMap hashMap = new HashMap();
                hashMap.put("ro.debuggable", "1");
                hashMap.put("ro.secure", "0");
                for (String str : split) {
                    for (String str2 : hashMap.keySet()) {
                        if (str != null && str.contains(str2)) {
                            if (str.contains("[" + hashMap.get(str2) + "]")) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return false;
    }

    private boolean checkRootRw() {
        String[] split;
        try {
            InputStream invokeRuntimeExec = invokeRuntimeExec(new String[]{"mount"});
            if (!(invokeRuntimeExec == null || (split = new Scanner(invokeRuntimeExec).useDelimiter("\\A").next().split("\n")) == null)) {
                String[] strArr = {"/system", "/system/bin", "/system/sbin", "/system/xbin", "/vendor/bin", "/sbin", "/etc"};
                for (String split2 : split) {
                    String[] split3 = split2.split(" ");
                    if (split3.length >= 4) {
                        String str = split3[1];
                        String str2 = split3[3];
                        for (String str3 : strArr) {
                            if (str != null && str.equalsIgnoreCase(str3)) {
                                for (String str4 : str2.split(",")) {
                                    if (str4 != null && str4.equalsIgnoreCase("rw")) {
                                        return true;
                                    }
                                }
                                continue;
                            }
                        }
                        continue;
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0044 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean checkRootSu() {
        /*
            r6 = this;
            java.lang.String r0 = "su"
            r1 = 1
            r2 = 0
            java.lang.String r3 = "which"
            java.lang.String[] r3 = new java.lang.String[]{r3, r0}     // Catch:{ Throwable -> 0x0022 }
            java.io.InputStream r3 = r6.invokeRuntimeExec(r3)     // Catch:{ Throwable -> 0x0022 }
            if (r3 == 0) goto L_0x0022
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0022 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0022 }
            r5.<init>(r3)     // Catch:{ Throwable -> 0x0022 }
            r4.<init>(r5)     // Catch:{ Throwable -> 0x0022 }
            java.lang.String r3 = r4.readLine()     // Catch:{ Throwable -> 0x0022 }
            if (r3 == 0) goto L_0x0022
            r3 = 1
            goto L_0x0023
        L_0x0022:
            r3 = 0
        L_0x0023:
            java.lang.String r4 = "/system/xbin/which"
            java.lang.String[] r0 = new java.lang.String[]{r4, r0}     // Catch:{ Throwable -> 0x0041 }
            java.io.InputStream r0 = r6.invokeRuntimeExec(r0)     // Catch:{ Throwable -> 0x0041 }
            if (r0 == 0) goto L_0x0041
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0041 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0041 }
            r5.<init>(r0)     // Catch:{ Throwable -> 0x0041 }
            r4.<init>(r5)     // Catch:{ Throwable -> 0x0041 }
            java.lang.String r0 = r4.readLine()     // Catch:{ Throwable -> 0x0041 }
            if (r0 == 0) goto L_0x0041
            r0 = 1
            goto L_0x0042
        L_0x0041:
            r0 = 0
        L_0x0042:
            if (r3 != 0) goto L_0x0048
            if (r0 == 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r1 = 0
        L_0x0048:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.checkRootSu():boolean");
    }

    private InputStream invokeRuntimeExec(String[] strArr) {
        try {
            return (InputStream) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(42)), Strings.getString(43), new Object[0]), Strings.getString(44), strArr), Strings.getString(45), new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    public String getSSID() {
        try {
            if (!"-1".equals(this.bvs.ssi)) {
                return this.bvs.ssi;
            }
            if (checkPermission("android.permission.ACCESS_WIFI_STATE")) {
                Object systemServiceSafe = getSystemServiceSafe("wifi");
                if (systemServiceSafe == null) {
                    this.bvs.ssi = null;
                    return null;
                }
                Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(2), new Object[0]);
                if (invokeInstanceMethod != null) {
                    String str = (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(3), new Object[0]);
                    this.bvs.ssi = str == null ? null : str.replace("\"", "");
                    return this.bvs.ssi;
                }
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public String getBssid() {
        try {
            if (!"-1".equals(this.bvs.bsi)) {
                return this.bvs.bsi;
            }
            if (checkPermission("android.permission.ACCESS_WIFI_STATE")) {
                Object systemServiceSafe = getSystemServiceSafe("wifi");
                if (systemServiceSafe == null) {
                    this.bvs.bsi = null;
                    return null;
                }
                Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(2), new Object[0]);
                if (invokeInstanceMethod != null) {
                    String str = (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(4), new Object[0]);
                    BVS bvs2 = this.bvs;
                    if (str == null) {
                        str = null;
                    }
                    bvs2.bsi = str;
                    return this.bvs.bsi;
                }
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    private String[] getInvalidMacList() {
        ArrayList arrayList;
        try {
            String AES128Decode = Data.AES128Decode(Strings.getString(76), (byte[]) ResHelper.readObjectFromFile(ResHelper.getDataCacheFile(this.context, ".mcli").getPath()));
            if (TextUtils.isEmpty(AES128Decode) || (arrayList = (ArrayList) new Hashon().fromJson(AES128Decode).get("list")) == null || arrayList.size() <= 0) {
                return null;
            }
            return (String[]) arrayList.toArray(new String[0]);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    private String getLocalWifiMac() {
        try {
            File cacheRootFile = ResHelper.getCacheRootFile(this.context, ".mcw");
            String AES128Decode = cacheRootFile.exists() ? Data.AES128Decode("1234567890abcdfg", (byte[]) ResHelper.readObjectFromFile(cacheRootFile.getPath())) : null;
            if (TextUtils.isEmpty(AES128Decode)) {
                AES128Decode = getWAbcd(2);
            }
            if (!TextUtils.isEmpty(AES128Decode) && AES128Decode.trim().matches("^[a-fA-F0-9]{2}(:[a-fA-F0-9]{2}){5}$")) {
                return AES128Decode.trim();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return null;
    }

    private void saveLocalWifiMac(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.trim().matches("^[a-fA-F0-9]{2}(:[a-fA-F0-9]{2}){5}$")) {
                File cacheRootFile = ResHelper.getCacheRootFile(this.context, ".mcw");
                ResHelper.saveObjectToFile(cacheRootFile.getPath(), Data.AES128Encode("1234567890abcdfg", str.trim()));
                saveWabcd(str.trim(), 2);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    private String getLocalSerial() {
        try {
            String AES128Decode = Data.AES128Decode(Strings.getString(76), (byte[]) ResHelper.readObjectFromFile(ResHelper.getCacheRootFile(this.context, ".slw").getPath()));
            if (!TextUtils.isEmpty(AES128Decode)) {
                return AES128Decode.trim();
            }
            return getWAbcd(3);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    private void saveLocalSerial(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !str.trim().equals("")) {
                File cacheRootFile = ResHelper.getCacheRootFile(this.context, ".slw");
                ResHelper.saveObjectToFile(cacheRootFile.getPath(), Data.AES128Encode(Strings.getString(76), str.trim()));
                saveWabcd(str.trim(), 3);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public String getMacAddress() {
        if (!TextUtils.isEmpty(this.wifimac)) {
            return this.wifimac;
        }
        String localWifiMac = getLocalWifiMac();
        if (!TextUtils.isEmpty(localWifiMac) && checkMacIsValid(localWifiMac)) {
            this.wifimac = localWifiMac;
            return localWifiMac;
        } else if (Build.VERSION.SDK_INT >= 23) {
            String wlanMac = getWlanMac();
            if (wlanMac == null || TextUtils.isEmpty(wlanMac.trim())) {
                return getWifiMac();
            }
            return wlanMac.trim();
        } else {
            String wifiMac = getWifiMac();
            if (wifiMac == null || !checkMacIsValid(wifiMac)) {
                return getWlanMac();
            }
            this.wifimac = wifiMac.trim();
            saveLocalWifiMac(this.wifimac);
            return this.wifimac;
        }
    }

    private String getWlanMac() {
        try {
            String hardwareAddressFromShell = getHardwareAddressFromShell("wlan0");
            if (hardwareAddressFromShell != null && checkMacIsValid(hardwareAddressFromShell)) {
                this.wifimac = hardwareAddressFromShell.trim();
                saveLocalWifiMac(this.wifimac);
                return this.wifimac;
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        try {
            return getValidNetworkHardwareAddress();
        } catch (Throwable th2) {
            MobLog.getInstance().mo29769d(th2);
            return null;
        }
    }

    private boolean checkMacIsValid(String str) {
        if (str != null) {
            try {
                if (!TextUtils.isEmpty(str.trim())) {
                    if (this.invalidMacList == null) {
                        this.invalidMacList = getInvalidMacList();
                    }
                    String[] strArr = this.invalidMacList;
                    if (strArr == null) {
                        strArr = new String[]{Strings.getString(70), Strings.getString(71)};
                    }
                    for (String str2 : strArr) {
                        if (str2 != null && str.trim().startsWith(str2.trim())) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        }
        return false;
    }

    private String getWifiMac() {
        Object invokeInstanceMethod;
        try {
            Object systemServiceSafe = getSystemServiceSafe("wifi");
            if (!(systemServiceSafe == null || (invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(2), new Object[0])) == null)) {
                String str = (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(5), new Object[0]);
                if (str == null) {
                    return null;
                }
                return str.trim();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return null;
    }

    private String getCurrentNetworkHardwareAddress() throws Throwable {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces == null) {
            return null;
        }
        for (T t : Collections.list(networkInterfaces)) {
            Enumeration<InetAddress> inetAddresses = t.getInetAddresses();
            if (inetAddresses != null) {
                for (T t2 : Collections.list(inetAddresses)) {
                    if (!t2.isLoopbackAddress() && (t2 instanceof Inet4Address)) {
                        byte[] hardwareAddress = Build.VERSION.SDK_INT >= 9 ? t.getHardwareAddress() : null;
                        if (hardwareAddress != null) {
                            return byteToHex(hardwareAddress);
                        }
                    }
                }
                continue;
            }
        }
        return null;
    }

    private String getValidNetworkHardwareAddress() throws Throwable {
        HashMap<String, String> listNetworkHardware = listNetworkHardware();
        if (listNetworkHardware != null && !listNetworkHardware.isEmpty()) {
            ArrayList arrayList = new ArrayList(listNetworkHardware.keySet());
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            ArrayList arrayList8 = new ArrayList();
            CharSequence charSequence = null;
            while (arrayList.size() > 0) {
                String trim = ((String) arrayList.remove(0)).trim();
                if (trim.equals("wlan0")) {
                    charSequence = "wlan0";
                } else if (trim.startsWith("wlan")) {
                    arrayList2.add(trim);
                } else if (trim.startsWith("eth")) {
                    arrayList3.add(trim);
                } else if (trim.startsWith("rev_rmnet")) {
                    arrayList4.add(trim);
                } else if (trim.startsWith("dummy")) {
                    arrayList5.add(trim);
                } else if (trim.startsWith("usbnet")) {
                    arrayList6.add(trim);
                } else if (trim.startsWith("rmnet_usb")) {
                    arrayList7.add(trim);
                } else {
                    arrayList8.add(trim);
                }
            }
            Collections.sort(arrayList2);
            Collections.sort(arrayList3);
            Collections.sort(arrayList4);
            Collections.sort(arrayList5);
            Collections.sort(arrayList6);
            Collections.sort(arrayList7);
            Collections.sort(arrayList8);
            if (!TextUtils.isEmpty(charSequence)) {
                arrayList.add(charSequence);
            }
            arrayList.addAll(arrayList2);
            if ("wifi".equals(getNetworkType())) {
                try {
                    String currentNetworkHardwareAddress = getCurrentNetworkHardwareAddress();
                    if (!TextUtils.isEmpty(currentNetworkHardwareAddress)) {
                        arrayList.add(currentNetworkHardwareAddress);
                    }
                } catch (Throwable unused) {
                }
            }
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str = listNetworkHardware.get((String) it.next());
                    if (str != null && checkMacIsValid(str)) {
                        this.wifimac = str.trim();
                        saveLocalWifiMac(this.wifimac);
                        return this.wifimac;
                    }
                }
            }
            arrayList.addAll(arrayList3);
            arrayList.addAll(arrayList4);
            arrayList.addAll(arrayList5);
            arrayList.addAll(arrayList6);
            arrayList.addAll(arrayList7);
            arrayList.addAll(arrayList8);
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                String str2 = listNetworkHardware.get((String) it2.next());
                if (str2 != null && checkMacIsValid(str2)) {
                    return str2.trim();
                }
            }
        }
        return null;
    }

    public HashMap<String, String> listNetworkHardware() throws Throwable {
        HashMap<String, String> hashMap = this.bufMacs;
        if (hashMap != null && !hashMap.isEmpty()) {
            return this.bufMacs;
        }
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces == null) {
            return null;
        }
        ArrayList<T> list = Collections.list(networkInterfaces);
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (T t : list) {
            byte[] hardwareAddress = Build.VERSION.SDK_INT >= 9 ? t.getHardwareAddress() : null;
            if (hardwareAddress != null) {
                hashMap2.put(t.getName(), byteToHex(hardwareAddress));
            }
        }
        this.bufMacs = hashMap2;
        return hashMap2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0072 A[SYNTHETIC, Splitter:B:14:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0082 A[SYNTHETIC, Splitter:B:25:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getHardwareAddressFromShell(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 42
            r1 = 0
            java.lang.String r0 = com.mob.tools.utils.Strings.getString(r0)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.lang.String r0 = com.mob.tools.utils.ReflectHelper.importClass(r0)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r2 = 43
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r0, r2, r4)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r2.<init>()     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r4 = 6
            java.lang.String r4 = com.mob.tools.utils.Strings.getString(r4)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r2.append(r4)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r2.append(r6)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r6 = 7
            java.lang.String r6 = com.mob.tools.utils.Strings.getString(r6)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r2.append(r6)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.lang.String r6 = r2.toString()     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r2 = 44
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r4[r3] = r6     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.lang.Object r6 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r4)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r0 = 45
            java.lang.String r0 = com.mob.tools.utils.Strings.getString(r0)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.lang.Object r6 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r6, r0, r2)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.io.InputStream r6 = (java.io.InputStream) r6     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r0.<init>(r6)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            r6.<init>(r0)     // Catch:{ Throwable -> 0x0067, all -> 0x0065 }
            java.lang.String r0 = r6.readLine()     // Catch:{ Throwable -> 0x0063 }
            r6.close()     // Catch:{ Throwable -> 0x0076 }
            goto L_0x0076
        L_0x0063:
            r0 = move-exception
            goto L_0x0069
        L_0x0065:
            r0 = move-exception
            goto L_0x0080
        L_0x0067:
            r0 = move-exception
            r6 = r1
        L_0x0069:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x007e }
            r2.mo29769d(r0)     // Catch:{ all -> 0x007e }
            if (r6 == 0) goto L_0x0075
            r6.close()     // Catch:{ Throwable -> 0x0075 }
        L_0x0075:
            r0 = r1
        L_0x0076:
            boolean r6 = android.text.TextUtils.isEmpty(r0)
            if (r6 == 0) goto L_0x007d
            r0 = r1
        L_0x007d:
            return r0
        L_0x007e:
            r0 = move-exception
            r1 = r6
        L_0x0080:
            if (r1 == 0) goto L_0x0085
            r1.close()     // Catch:{ Throwable -> 0x0085 }
        L_0x0085:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getHardwareAddressFromShell(java.lang.String):java.lang.String");
    }

    public String getBTMacFromProvider() {
        return Settings.Secure.getString(this.context.getContentResolver(), "bluetooth_address");
    }

    public String getBTMac() {
        if (Build.VERSION.SDK_INT >= 27) {
            return null;
        }
        try {
            if (!checkPermission("android.permission.BLUETOOTH")) {
                return null;
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (Build.VERSION.SDK_INT < 23) {
                return defaultAdapter.getAddress();
            }
            Object instanceField = ReflectHelper.getInstanceField(defaultAdapter, "mService");
            if (instanceField != null) {
                return (String) ReflectHelper.invokeInstanceMethod(instanceField, "getAddress", new Object[0]);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean isBT() {
        try {
            return BHelper.getInstance(this.context).isEnabled();
        } catch (Throwable unused) {
            return false;
        }
    }

    public String getModel() {
        if (!TextUtils.isEmpty(this.bufModel)) {
            return this.bufModel;
        }
        String str = Build.MODEL;
        if (!TextUtils.isEmpty(str)) {
            str = str.trim();
        }
        this.bufModel = str;
        return str;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getDeviceId() {
        String imei2 = getIMEI();
        return (!TextUtils.isEmpty(imei2) || Build.VERSION.SDK_INT < 9) ? imei2 : getSerialno();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0049 A[SYNTHETIC, Splitter:B:25:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007d A[Catch:{ Throwable -> 0x00ab }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getIMEI() {
        /*
            r6 = this;
            java.lang.String r0 = r6.imei
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x000b
            java.lang.String r0 = r6.imei
            return r0
        L_0x000b:
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r6.getSystemServiceSafe(r0)
            r1 = 0
            if (r0 != 0) goto L_0x0015
            return r1
        L_0x0015:
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            boolean r2 = r6.checkPermission(r2)     // Catch:{ Throwable -> 0x00ad }
            if (r2 == 0) goto L_0x003f
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00ad }
            r3 = 29
            if (r2 >= r3) goto L_0x003f
            r2 = 8
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x0033 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0033 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r3)     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0033 }
            goto L_0x0040
        L_0x0033:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00ad }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Throwable -> 0x00ad }
            r2.mo29786w((java.lang.String) r0)     // Catch:{ Throwable -> 0x00ad }
        L_0x003f:
            r0 = r1
        L_0x0040:
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x00ab }
            r3 = 1
            java.lang.String r4 = "comm/.di"
            if (r2 == 0) goto L_0x007d
            android.content.Context r2 = r6.context     // Catch:{ Throwable -> 0x00ab }
            java.io.File r2 = com.mob.tools.utils.ResHelper.getCacheRootFile(r2, r4)     // Catch:{ Throwable -> 0x00ab }
            if (r2 == 0) goto L_0x0078
            boolean r4 = r2.exists()     // Catch:{ Throwable -> 0x00ab }
            if (r4 != 0) goto L_0x0058
            goto L_0x0078
        L_0x0058:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00ab }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x00ab }
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x00ab }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00ab }
            java.lang.Object r3 = r2.readObject()     // Catch:{ Throwable -> 0x00ab }
            if (r3 == 0) goto L_0x0074
            boolean r4 = r3 instanceof char[]     // Catch:{ Throwable -> 0x00ab }
            if (r4 == 0) goto L_0x0074
            char[] r3 = (char[]) r3     // Catch:{ Throwable -> 0x00ab }
            char[] r3 = (char[]) r3     // Catch:{ Throwable -> 0x00ab }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x00ab }
        L_0x0074:
            r2.close()     // Catch:{ Throwable -> 0x00ab }
            return r1
        L_0x0078:
            java.lang.String r0 = r6.getWAbcd(r3)     // Catch:{ Throwable -> 0x00ab }
            return r0
        L_0x007d:
            r6.imei = r0     // Catch:{ Throwable -> 0x00ab }
            android.content.Context r1 = r6.context     // Catch:{ Throwable -> 0x00ab }
            java.io.File r1 = com.mob.tools.utils.ResHelper.getCacheRootFile(r1, r4)     // Catch:{ Throwable -> 0x00ab }
            if (r1 == 0) goto L_0x0090
            boolean r2 = r1.exists()     // Catch:{ Throwable -> 0x00ab }
            if (r2 == 0) goto L_0x0090
            r1.delete()     // Catch:{ Throwable -> 0x00ab }
        L_0x0090:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x00ab }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x00ab }
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ Throwable -> 0x00ab }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x00ab }
            char[] r2 = r0.toCharArray()     // Catch:{ Throwable -> 0x00ab }
            r1.writeObject(r2)     // Catch:{ Throwable -> 0x00ab }
            r1.flush()     // Catch:{ Throwable -> 0x00ab }
            r1.close()     // Catch:{ Throwable -> 0x00ab }
            r6.saveWabcd(r0, r3)     // Catch:{ Throwable -> 0x00ab }
            goto L_0x00b8
        L_0x00ab:
            r1 = move-exception
            goto L_0x00b1
        L_0x00ad:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x00b1:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29787w((java.lang.Throwable) r1)
        L_0x00b8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getIMEI():java.lang.String");
    }

    public String[] queryIMEI() {
        ArrayList arrayList;
        String str;
        try {
            arrayList = new ArrayList();
            if (checkPermission(Permission.READ_PHONE_STATE) && Build.VERSION.SDK_INT < 29) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe == null) {
                    return null;
                }
                String imei2 = getIMEI();
                if (TextUtils.isEmpty(imei2)) {
                    imei2 = "-1";
                }
                arrayList.add(imei2);
                if (this.bufIm == null || this.bufIm.isEmpty()) {
                    for (int i = 0; i <= 5; i++) {
                        try {
                            str = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(8), Integer.valueOf(i));
                        } catch (Throwable unused) {
                            str = null;
                        }
                        if (TextUtils.isEmpty(str)) {
                            str = "-1";
                        }
                        this.bufIm.add(str);
                        arrayList.add(str);
                    }
                } else {
                    arrayList.addAll(this.bufIm);
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        String[] split = Strings.getString(54).split(";");
        String[][] strArr = new String[split.length][];
        for (int i2 = 0; i2 < split.length; i2++) {
            strArr[i2] = split[i2].split(",");
        }
        for (String[] strArr2 : strArr) {
            for (String systemProperties : strArr[r5]) {
                for (String str2 : getSystemProperties(systemProperties).split(",")) {
                    if (!TextUtils.isEmpty(str2) && !arrayList.contains(str2)) {
                        arrayList.add(str2);
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return null;
    }

    private String getSystemProperties(String str) {
        try {
            Object invokeStaticMethod = ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(9)), Strings.getString(10), str);
            if (invokeStaticMethod != null) {
                return String.valueOf(invokeStaticMethod);
            }
            return "";
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getSerialno() {
        /*
            r7 = this;
            java.lang.String r0 = r7.serialno
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x000b
            java.lang.String r0 = r7.serialno
            return r0
        L_0x000b:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 9
            java.lang.String r3 = "unknown"
            r4 = 0
            if (r0 < r2) goto L_0x0046
            int r0 = android.os.Build.VERSION.SDK_INT
            r5 = 26
            if (r0 >= r5) goto L_0x0046
            java.lang.String r0 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x003e }
            java.lang.String r0 = com.mob.tools.utils.ReflectHelper.importClass(r0)     // Catch:{ Throwable -> 0x003e }
            r2 = 10
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x003e }
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x003e }
            r6 = 11
            java.lang.String r6 = com.mob.tools.utils.Strings.getString(r6)     // Catch:{ Throwable -> 0x003e }
            r5[r1] = r6     // Catch:{ Throwable -> 0x003e }
            r6 = 1
            r5[r6] = r3     // Catch:{ Throwable -> 0x003e }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r0, r2, r5)     // Catch:{ Throwable -> 0x003e }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x003e }
            goto L_0x0047
        L_0x003e:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r0)
        L_0x0046:
            r0 = r4
        L_0x0047:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0053
            boolean r2 = r3.equalsIgnoreCase(r0)
            if (r2 == 0) goto L_0x0055
        L_0x0053:
            java.lang.String r0 = android.os.Build.SERIAL
        L_0x0055:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0061
            boolean r2 = r3.equalsIgnoreCase(r0)
            if (r2 == 0) goto L_0x008d
        L_0x0061:
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            boolean r2 = r7.checkPermission(r2)     // Catch:{ Throwable -> 0x0080 }
            if (r2 == 0) goto L_0x008d
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0080 }
            r5 = 29
            if (r2 >= r5) goto L_0x008d
            java.lang.String r0 = "android.os.Build"
            java.lang.String r0 = com.mob.tools.utils.ReflectHelper.importClass(r0)     // Catch:{ Throwable -> 0x0080 }
            java.lang.String r2 = "getSerial"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0080 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r0, r2, r1)     // Catch:{ Throwable -> 0x0080 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0080 }
            goto L_0x008d
        L_0x0080:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            java.lang.String r0 = r0.getMessage()
            r1.mo29786w((java.lang.String) r0)
            r0 = r4
        L_0x008d:
            boolean r1 = r3.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x0094
            r0 = r4
        L_0x0094:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x00a5
            java.lang.String r1 = r7.getLocalSerial()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x00ae
            return r1
        L_0x00a5:
            r7.serialno = r0
            java.lang.String r0 = r0.trim()
            r7.saveLocalSerial(r0)
        L_0x00ae:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getSerialno():java.lang.String");
    }

    public String getDeviceData() {
        try {
            String str = getModel() + "|" + getOSVersionInt() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize();
            String deviceKey = getDeviceKey();
            if (deviceKey == null) {
                deviceKey = "";
            } else if (deviceKey.length() > 16) {
                deviceKey = deviceKey.substring(0, 16);
            }
            return Base64AES(str, deviceKey);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return "";
        }
    }

    public String getDeviceDataNotAES() {
        return getModel() + "|" + getOSVersionInt() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize();
    }

    public String Base64AES(String str, String str2) {
        try {
            String encodeToString = Base64.encodeToString(Data.AES128Encode(str2, str), 0);
            if (encodeToString.contains("\n")) {
                return encodeToString.replace("\n", "");
            }
            return encodeToString;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    public int getOSVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    public String getOSVersionName() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable unused) {
            return null;
        }
    }

    public String getOSLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String getAppLanguage() {
        return this.context.getResources().getConfiguration().locale.getLanguage();
    }

    public String getOSCountry() {
        return Locale.getDefault().getCountry();
    }

    public String getScreenSize() {
        int[] screenSize = ResHelper.getScreenSize(this.context);
        if (this.context.getResources().getConfiguration().orientation == 1) {
            return screenSize[0] + Config.EVENT_HEAT_X + screenSize[1];
        }
        return screenSize[1] + Config.EVENT_HEAT_X + screenSize[0];
    }

    public String getCarrier() {
        try {
            Object systemServiceSafe = getSystemServiceSafe("phone");
            if (systemServiceSafe == null) {
                return "-1";
            }
            String str = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(12), new Object[0]);
            if (TextUtils.isEmpty(str)) {
                return "-1";
            }
            return str;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return "-1";
        }
    }

    public String getCarrierName() {
        Object systemServiceSafe = getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return null;
        }
        try {
            if (checkPermission(Permission.READ_PHONE_STATE)) {
                String str = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(13), new Object[0]);
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                return str;
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return null;
    }

    public String getMCC() {
        String imsi = getIMSI();
        if (imsi == null || imsi.length() < 3) {
            return null;
        }
        return imsi.substring(0, 3);
    }

    public String getMNC() {
        String imsi = getIMSI();
        if (imsi == null || imsi.length() < 5) {
            return null;
        }
        return imsi.substring(3, 5);
    }

    public String getSimSerialNumber() {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                return "-1";
            }
            if (!"-2".equals(this.bvs.ssn)) {
                return this.bvs.ssn;
            }
            if (checkPermission(Permission.READ_PHONE_STATE)) {
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe == null) {
                    this.bvs.ssn = "-1";
                    return this.bvs.ssn;
                }
                this.bvs.ssn = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(14), new Object[0]);
                return this.bvs.ssn;
            }
            return "-1";
        } catch (Throwable th) {
            MobLog.getInstance().mo29786w(th.getMessage());
        }
    }

    public String getLN() {
        try {
            if (checkPermission(Permission.READ_PHONE_STATE)) {
                if (!"-1".equals(this.f2349ln) && isSensitiveDevice()) {
                    return this.f2349ln;
                }
                Object systemServiceSafe = getSystemServiceSafe("phone");
                if (systemServiceSafe == null) {
                    return this.f2349ln;
                }
                this.f2349ln = (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(15), new Object[0]);
                return this.f2349ln;
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return this.f2349ln;
    }

    public String getBluetoothName() {
        Object invokeStaticMethod;
        try {
            if (!"-1".equals(this.bvs.btnm)) {
                return this.bvs.btnm;
            }
            if (!checkPermission("android.permission.BLUETOOTH") || (invokeStaticMethod = ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(16)), Strings.getString(17), new Object[0])) == null) {
                return null;
            }
            this.bvs.btnm = (String) ReflectHelper.invokeInstanceMethod(invokeStaticMethod, Strings.getString(18), new Object[0]);
            return this.bvs.btnm;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public String getSignMD5() {
        try {
            return Data.MD5(this.context.getPackageManager().getPackageInfo(getPackageName(), 64).signatures[0].toByteArray());
        } catch (Exception e) {
            MobLog.getInstance().mo29787w((Throwable) e);
            return null;
        }
    }

    public String getSignMD5(String str) {
        try {
            return Data.MD5(this.context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray());
        } catch (Exception e) {
            MobLog.getInstance().mo29787w((Throwable) e);
            return null;
        }
    }

    public Object getSystemServiceSafe(String str) {
        try {
            return this.context.getSystemService(str);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    public String getNetworkType() {
        return NtFetcher.getInstance(this.context).getNtType();
    }

    public String getNetworkTypeForStatic() {
        String lowerCase = getNetworkType().toLowerCase();
        if (TextUtils.isEmpty(lowerCase) || SchedulerSupport.NONE.equals(lowerCase)) {
            return SchedulerSupport.NONE;
        }
        if (lowerCase.startsWith("5g") || lowerCase.startsWith("4g") || lowerCase.startsWith("3g") || lowerCase.startsWith("2g")) {
            return "cell";
        }
        if (lowerCase.startsWith("wifi")) {
            return "wifi";
        }
        return "other";
    }

    public String getDetailNetworkTypeForStatic() {
        try {
            String lowerCase = getNetworkType().toLowerCase();
            if (!TextUtils.isEmpty(lowerCase)) {
                if (!SchedulerSupport.NONE.equals(lowerCase)) {
                    if (lowerCase.startsWith("wifi")) {
                        return "wifi";
                    }
                    if (lowerCase.startsWith("5g")) {
                        return "5g";
                    }
                    if (lowerCase.startsWith("4g")) {
                        return "4g";
                    }
                    if (lowerCase.startsWith("3g")) {
                        return "3g";
                    }
                    if (lowerCase.startsWith("2g")) {
                        return "2g";
                    }
                    if (lowerCase.startsWith("bluetooth")) {
                        return "bluetooth";
                    }
                    return lowerCase;
                }
            }
            return SchedulerSupport.NONE;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return SchedulerSupport.NONE;
        }
    }

    public String getDeviceKey() {
        String str;
        if (!TextUtils.isEmpty(this.cacheDeviceKey)) {
            return this.cacheDeviceKey;
        }
        String str2 = null;
        try {
            str = getDeviceKeyWithDuid("comm/dbs/.duid");
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            str = null;
        }
        if (TextUtils.isEmpty(str) || str.length() < 40) {
            str = genDeviceKey();
        }
        if (TextUtils.isEmpty(str) || str.length() < 40) {
            try {
                str2 = getLocalDeviceKey();
            } catch (Throwable th2) {
                MobLog.getInstance().mo29787w(th2);
            }
            if (TextUtils.isEmpty(str2) || str2.length() < 40) {
                if (TextUtils.isEmpty(str2) || str2.length() < 40) {
                    str2 = getCharAndNumr(40);
                }
                if (str2 == null) {
                    return str2;
                }
                try {
                    str2 = str2.trim();
                    saveLocalDeviceKey(str2);
                    return str2;
                } catch (Throwable th3) {
                    MobLog.getInstance().mo29787w(th3);
                    return str2;
                }
            } else {
                this.cacheDeviceKey = str2.trim();
                return this.cacheDeviceKey;
            }
        } else {
            this.cacheDeviceKey = str.trim();
            return this.cacheDeviceKey;
        }
    }

    private String genDeviceKey() {
        try {
            String macAddress = getMacAddress();
            String deviceId = getDeviceId();
            String model = getModel();
            return Data.byteToHex(Data.SHA1(macAddress + Config.TRACE_TODAY_VISIT_SPLIT + deviceId + Config.TRACE_TODAY_VISIT_SPLIT + model));
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public String getCharAndNumr(int i) {
        long currentTimeMillis = System.currentTimeMillis() ^ SystemClock.elapsedRealtime();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(currentTimeMillis);
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            if ("char".equalsIgnoreCase(random.nextInt(2) % 2 == 0 ? "char" : "num")) {
                stringBuffer.insert(i2 + 1, (char) (random.nextInt(26) + 97));
            } else {
                stringBuffer.insert(stringBuffer.length(), random.nextInt(10));
            }
        }
        return stringBuffer.toString().substring(0, 40);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:24|(0)|28|29) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0045 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003b A[SYNTHETIC, Splitter:B:22:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0042 A[SYNTHETIC, Splitter:B:26:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getDeviceKeyWithDuid(java.lang.String r7) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r0 = ":"
            r1 = 0
            android.content.Context r2 = r6.context     // Catch:{ Throwable -> 0x0046 }
            java.io.File r7 = com.mob.tools.utils.ResHelper.getCacheRootFile(r2, r7)     // Catch:{ Throwable -> 0x0046 }
            if (r7 == 0) goto L_0x004e
            boolean r2 = r7.exists()     // Catch:{ Throwable -> 0x0046 }
            if (r2 == 0) goto L_0x004e
            boolean r2 = r7.isFile()     // Catch:{ Throwable -> 0x0046 }
            if (r2 == 0) goto L_0x004e
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            r2.<init>(r7)     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            java.io.ObjectInputStream r7 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            r7.<init>(r2)     // Catch:{ Throwable -> 0x0030, all -> 0x002d }
            java.lang.Object r2 = r7.readObject()     // Catch:{ Throwable -> 0x002b }
            java.util.HashMap r2 = (java.util.HashMap) r2     // Catch:{ Throwable -> 0x002b }
            r7.close()     // Catch:{ Throwable -> 0x004f }
            goto L_0x004f
        L_0x002b:
            r2 = move-exception
            goto L_0x0032
        L_0x002d:
            r2 = move-exception
            r7 = r1
            goto L_0x0040
        L_0x0030:
            r2 = move-exception
            r7 = r1
        L_0x0032:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x003f }
            r3.mo29787w((java.lang.Throwable) r2)     // Catch:{ all -> 0x003f }
            if (r7 == 0) goto L_0x004e
            r7.close()     // Catch:{ Throwable -> 0x004e }
            goto L_0x004e
        L_0x003f:
            r2 = move-exception
        L_0x0040:
            if (r7 == 0) goto L_0x0045
            r7.close()     // Catch:{ Throwable -> 0x0045 }
        L_0x0045:
            throw r2     // Catch:{ Throwable -> 0x0046 }
        L_0x0046:
            r7 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29787w((java.lang.Throwable) r7)
        L_0x004e:
            r2 = r1
        L_0x004f:
            if (r2 != 0) goto L_0x0052
            return r1
        L_0x0052:
            java.lang.String r7 = "deviceInfo"
            java.lang.Object r7 = r2.get(r7)
            java.util.HashMap r7 = (java.util.HashMap) r7
            if (r7 != 0) goto L_0x005d
            return r1
        L_0x005d:
            java.lang.String r2 = "mac"
            java.lang.Object r2 = r7.get(r2)     // Catch:{ Throwable -> 0x00aa }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x00aa }
            java.lang.String r3 = "imei"
            java.lang.Object r3 = r7.get(r3)     // Catch:{ Throwable -> 0x00aa }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x00aa }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x00aa }
            if (r4 == 0) goto L_0x0081
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00aa }
            r5 = 9
            if (r4 < r5) goto L_0x0081
            java.lang.String r3 = "serialno"
            java.lang.Object r3 = r7.get(r3)     // Catch:{ Throwable -> 0x00aa }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x00aa }
        L_0x0081:
            java.lang.String r4 = "model"
            java.lang.Object r7 = r7.get(r4)     // Catch:{ Throwable -> 0x00aa }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Throwable -> 0x00aa }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00aa }
            r4.<init>()     // Catch:{ Throwable -> 0x00aa }
            r4.append(r2)     // Catch:{ Throwable -> 0x00aa }
            r4.append(r0)     // Catch:{ Throwable -> 0x00aa }
            r4.append(r3)     // Catch:{ Throwable -> 0x00aa }
            r4.append(r0)     // Catch:{ Throwable -> 0x00aa }
            r4.append(r7)     // Catch:{ Throwable -> 0x00aa }
            java.lang.String r7 = r4.toString()     // Catch:{ Throwable -> 0x00aa }
            byte[] r7 = com.mob.tools.utils.Data.SHA1((java.lang.String) r7)     // Catch:{ Throwable -> 0x00aa }
            java.lang.String r1 = com.mob.tools.utils.Data.byteToHex(r7)     // Catch:{ Throwable -> 0x00aa }
            goto L_0x00b2
        L_0x00aa:
            r7 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29769d(r7)
        L_0x00b2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getDeviceKeyWithDuid(java.lang.String):java.lang.String");
    }

    private String getLocalDeviceKey() throws Throwable {
        File cacheRootFile;
        String str = null;
        if (!getSdcardState()) {
            return null;
        }
        File file = new File(getSdcardPath(), OnekeyShare.SHARESDK_TAG);
        if (file.exists()) {
            File file2 = new File(file, ".dk");
            if (file2.exists() && (cacheRootFile = ResHelper.getCacheRootFile(this.context, ".dk")) != null && file2.renameTo(cacheRootFile)) {
                file2.delete();
            }
        }
        File cacheRootFile2 = ResHelper.getCacheRootFile(this.context, ".dk");
        if (cacheRootFile2 != null && !cacheRootFile2.exists()) {
            return null;
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(cacheRootFile2));
        Object readObject = objectInputStream.readObject();
        if (readObject != null && (readObject instanceof char[])) {
            str = String.valueOf((char[]) readObject);
        }
        objectInputStream.close();
        return str;
    }

    private void saveLocalDeviceKey(String str) throws Throwable {
        if (getSdcardState()) {
            File cacheRootFile = ResHelper.getCacheRootFile(this.context, ".dk");
            if (cacheRootFile != null && cacheRootFile.exists()) {
                cacheRootFile.delete();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(cacheRootFile));
            objectOutputStream.writeObject(str.toCharArray());
            objectOutputStream.flush();
            objectOutputStream.close();
        }
    }

    public String getPackageName() {
        return this.context.getPackageName();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001e */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0022 A[SYNTHETIC, Splitter:B:14:0x0022] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032 A[Catch:{ Throwable -> 0x0029, Throwable -> 0x0039 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAppName() {
        /*
            r4 = this;
            android.content.Context r0 = r4.context     // Catch:{ Throwable -> 0x0039 }
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()     // Catch:{ Throwable -> 0x0039 }
            java.lang.String r1 = r0.name     // Catch:{ Throwable -> 0x0039 }
            if (r1 == 0) goto L_0x001e
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0039 }
            r3 = 25
            if (r2 < r3) goto L_0x001d
            java.lang.String r2 = ".*"
            boolean r2 = r1.endsWith(r2)     // Catch:{ Throwable -> 0x0039 }
            if (r2 != 0) goto L_0x001d
            com.mob.tools.utils.ReflectHelper.importClass(r1)     // Catch:{ Throwable -> 0x001e }
            r1 = 0
            goto L_0x001e
        L_0x001d:
            return r1
        L_0x001e:
            int r2 = r0.labelRes     // Catch:{ Throwable -> 0x0039 }
            if (r2 <= 0) goto L_0x0032
            android.content.Context r0 = r4.context     // Catch:{ Throwable -> 0x0029 }
            java.lang.String r1 = r0.getString(r2)     // Catch:{ Throwable -> 0x0029 }
            goto L_0x0038
        L_0x0029:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x0039 }
            r2.mo29787w((java.lang.Throwable) r0)     // Catch:{ Throwable -> 0x0039 }
            goto L_0x0038
        L_0x0032:
            java.lang.CharSequence r0 = r0.nonLocalizedLabel     // Catch:{ Throwable -> 0x0039 }
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0039 }
        L_0x0038:
            return r1
        L_0x0039:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.mo29787w((java.lang.Throwable) r0)
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getAppName():java.lang.String");
    }

    public String getAppName(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            PackageManager packageManager = this.context.getPackageManager();
            return packageManager.getPackageInfo(str, 1).applicationInfo.loadLabel(packageManager).toString();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public int getAppVersion() {
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= 28) {
                return (int) packageInfo.getLongVersionCode();
            }
            return packageInfo.versionCode;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    public String getAppVersionName() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return "1.0";
        }
    }

    public ArrayList<HashMap<String, String>> getIA(boolean z) {
        return getIA(z, true);
    }

    public ArrayList<HashMap<String, String>> getIA(boolean z, boolean z2) {
        return getAL(false, z, z2);
    }

    public ArrayList<HashMap<String, String>> getSA() {
        return getAL(true, true);
    }

    public ArrayList<HashMap<String, String>> getAA() {
        return getAL(false, true);
    }

    public synchronized ArrayList<HashMap<String, String>> getAL(boolean z, boolean z2) {
        return getAL(z, z2, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r6 = r2.getText(r7.packageName, r7.applicationInfo.labelRes, r7.applicationInfo);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008a, code lost:
        r6 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x007d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.String>> getAL(boolean r13, boolean r14, boolean r15) {
        /*
            r12 = this;
            monitor-enter(r12)
            java.util.ArrayList r0 = r12.getPL()     // Catch:{ Throwable -> 0x00f3 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00f3 }
            r1.<init>()     // Catch:{ Throwable -> 0x00f3 }
            android.content.Context r2 = r12.context     // Catch:{ Throwable -> 0x00f3 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Throwable -> 0x00f3 }
            r3 = 0
            if (r15 == 0) goto L_0x0018
            java.util.HashMap r4 = r12.getANS()     // Catch:{ Throwable -> 0x00f3 }
            goto L_0x0019
        L_0x0018:
            r4 = r3
        L_0x0019:
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Throwable -> 0x00f3 }
            r5 = 0
            r6 = 0
        L_0x001f:
            boolean r7 = r0.hasNext()     // Catch:{ Throwable -> 0x00f3 }
            if (r7 == 0) goto L_0x00ef
            java.lang.Object r7 = r0.next()     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Throwable -> 0x00f3 }
            android.content.pm.PackageInfo r7 = r2.getPackageInfo(r7, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x0039
        L_0x0030:
            r7 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00f3 }
            r8.mo29787w((java.lang.Throwable) r7)     // Catch:{ Throwable -> 0x00f3 }
            r7 = r3
        L_0x0039:
            if (r7 == 0) goto L_0x00e6
            if (r13 == 0) goto L_0x0044
            boolean r8 = r12.isSystemApp(r7)     // Catch:{ Throwable -> 0x00f3 }
            if (r8 != 0) goto L_0x004d
            goto L_0x001f
        L_0x0044:
            if (r14 != 0) goto L_0x004d
            boolean r8 = r12.isSystemApp(r7)     // Catch:{ Throwable -> 0x00f3 }
            if (r8 == 0) goto L_0x004d
            goto L_0x001f
        L_0x004d:
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ Throwable -> 0x00f3 }
            r8.<init>()     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r9 = "pkg"
            java.lang.String r10 = r7.packageName     // Catch:{ Throwable -> 0x00f3 }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x00f3 }
            if (r15 == 0) goto L_0x00a4
            if (r4 == 0) goto L_0x006a
            java.lang.String r9 = r7.packageName     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r9 = com.mob.tools.utils.Data.MD5((java.lang.String) r9)     // Catch:{ Throwable -> 0x00f3 }
            java.lang.Object r9 = r4.get(r9)     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Throwable -> 0x00f3 }
            goto L_0x0070
        L_0x006a:
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ Throwable -> 0x00f3 }
            r4.<init>()     // Catch:{ Throwable -> 0x00f3 }
            r9 = r3
        L_0x0070:
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Throwable -> 0x00f3 }
            if (r10 == 0) goto L_0x009f
            android.content.pm.ApplicationInfo r6 = r7.applicationInfo     // Catch:{ Throwable -> 0x007d }
            java.lang.CharSequence r6 = r6.loadLabel(r2)     // Catch:{ Throwable -> 0x007d }
            goto L_0x008b
        L_0x007d:
            java.lang.String r6 = r7.packageName     // Catch:{ Throwable -> 0x008a }
            android.content.pm.ApplicationInfo r9 = r7.applicationInfo     // Catch:{ Throwable -> 0x008a }
            int r9 = r9.labelRes     // Catch:{ Throwable -> 0x008a }
            android.content.pm.ApplicationInfo r10 = r7.applicationInfo     // Catch:{ Throwable -> 0x008a }
            java.lang.CharSequence r6 = r2.getText(r6, r9, r10)     // Catch:{ Throwable -> 0x008a }
            goto L_0x008b
        L_0x008a:
            r6 = r3
        L_0x008b:
            if (r6 != 0) goto L_0x0090
            java.lang.String r6 = r7.packageName     // Catch:{ Throwable -> 0x00f3 }
            goto L_0x0094
        L_0x0090:
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x00f3 }
        L_0x0094:
            r9 = r6
            java.lang.String r6 = r7.packageName     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r6 = com.mob.tools.utils.Data.MD5((java.lang.String) r6)     // Catch:{ Throwable -> 0x00f3 }
            r4.put(r6, r9)     // Catch:{ Throwable -> 0x00f3 }
            r6 = 1
        L_0x009f:
            java.lang.String r10 = "name"
            r8.put(r10, r9)     // Catch:{ Throwable -> 0x00f3 }
        L_0x00a4:
            java.lang.String r9 = "version"
            java.lang.String r10 = r7.versionName     // Catch:{ Throwable -> 0x00f3 }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r9 = "visible"
            java.lang.String r10 = r7.packageName     // Catch:{ Throwable -> 0x00f3 }
            android.content.Intent r10 = r2.getLaunchIntentForPackage(r10)     // Catch:{ Throwable -> 0x00f3 }
            if (r10 != 0) goto L_0x00b8
            java.lang.String r10 = "0"
            goto L_0x00ba
        L_0x00b8:
            java.lang.String r10 = "1"
        L_0x00ba:
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r9 = "enable"
            android.content.pm.ApplicationInfo r10 = r7.applicationInfo     // Catch:{ Throwable -> 0x00f3 }
            boolean r10 = r10.enabled     // Catch:{ Throwable -> 0x00f3 }
            if (r10 == 0) goto L_0x00c8
            java.lang.String r10 = "1"
            goto L_0x00ca
        L_0x00c8:
            java.lang.String r10 = "0"
        L_0x00ca:
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r9 = "firstInstallTime"
            long r10 = r7.firstInstallTime     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ Throwable -> 0x00f3 }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r9 = "lastUpdateTime"
            long r10 = r7.lastUpdateTime     // Catch:{ Throwable -> 0x00f3 }
            java.lang.String r7 = java.lang.String.valueOf(r10)     // Catch:{ Throwable -> 0x00f3 }
            r8.put(r9, r7)     // Catch:{ Throwable -> 0x00f3 }
            r1.add(r8)     // Catch:{ Throwable -> 0x00f3 }
        L_0x00e6:
            if (r15 == 0) goto L_0x001f
            if (r6 == 0) goto L_0x001f
            r12.saveANS(r4)     // Catch:{ Throwable -> 0x00f3 }
            goto L_0x001f
        L_0x00ef:
            monitor-exit(r12)
            return r1
        L_0x00f1:
            r13 = move-exception
            goto L_0x0102
        L_0x00f3:
            r13 = move-exception
            com.mob.tools.log.NLog r14 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x00f1 }
            r14.mo29787w((java.lang.Throwable) r13)     // Catch:{ all -> 0x00f1 }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ all -> 0x00f1 }
            r13.<init>()     // Catch:{ all -> 0x00f1 }
            monitor-exit(r12)
            return r13
        L_0x0102:
            monitor-exit(r12)
            goto L_0x0105
        L_0x0104:
            throw r13
        L_0x0105:
            goto L_0x0104
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getAL(boolean, boolean, boolean):java.util.ArrayList");
    }

    private HashMap<String, String> getANS() {
        try {
            return (HashMap) ResHelper.readObjectFromFile(ResHelper.getDataCacheFile(this.context, ".ans").getAbsolutePath());
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return null;
    }

    private void saveANS(HashMap<String, String> hashMap) {
        if (hashMap != null) {
            try {
                ResHelper.saveObjectToFile(ResHelper.getDataCacheFile(this.context, ".ans").getAbsolutePath(), hashMap);
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
    }

    private ArrayList<String> getPL() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(42)), Strings.getString(43), new Object[0]), Strings.getString(44), Strings.getString(20));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(45), new Object[0]), "utf-8"));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                String trim = readLine.trim();
                if (trim.length() > 8 && trim.substring(0, 8).equalsIgnoreCase("package:")) {
                    String trim2 = trim.substring(8).trim();
                    if (!TextUtils.isEmpty(trim2)) {
                        arrayList.add(trim2);
                    }
                }
            }
            bufferedReader.close();
            ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(51), new Object[0]);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        if (arrayList.isEmpty()) {
            try {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory(Strings.getString(74));
                for (ResolveInfo next : this.context.getPackageManager().queryIntentActivities(intent, 0)) {
                    if (!(next == null || next.activityInfo == null || TextUtils.isEmpty(next.activityInfo.packageName))) {
                        arrayList.add(next.activityInfo.packageName);
                    }
                }
            } catch (Throwable th2) {
                MobLog.getInstance().mo29787w(th2);
            }
        }
        return arrayList;
    }

    private boolean isSystemApp(PackageInfo packageInfo) {
        boolean z = (packageInfo.applicationInfo.flags & 1) == 1;
        boolean z2 = (packageInfo.applicationInfo.flags & 128) == 1;
        if (z || z2) {
            return true;
        }
        return false;
    }

    public String getNetworkOperator() {
        Object systemServiceSafe = getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return null;
        }
        try {
            return (String) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(21), new Object[0]);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    public boolean checkPermission(String str) throws Throwable {
        int i = -1;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                ReflectHelper.importClass("android.content.Context");
                Integer num = (Integer) ReflectHelper.invokeInstanceMethod(this.context, Strings.getString(22), str);
                if (num != null) {
                    i = num.intValue();
                }
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        } else {
            i = this.context.getPackageManager().checkPermission(str, getPackageName());
        }
        if (i == 0) {
            return true;
        }
        return false;
    }

    public boolean amIOnForeground() {
        try {
            if (Build.VERSION.SDK_INT > 27) {
                return !isBackground(this.context);
            }
            for (Object instanceField : ((Map) ReflectHelper.getInstanceField(currentActivityThread(), Strings.getString(23))).values()) {
                if (!((Boolean) ReflectHelper.getInstanceField(instanceField, Strings.getString(24))).booleanValue()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return false;
        }
    }

    private static boolean isBackground(Context context2) {
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context2.getApplicationContext().getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null && !runningAppProcesses.isEmpty()) {
                String packageName = context2.getPackageName();
                for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                    if (next.processName.equals(packageName)) {
                        if (next.importance == 400) {
                            return true;
                        }
                        return false;
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return false;
    }

    public boolean getSdcardState() {
        if (!this.hasSdcardWritePermission) {
            try {
                this.hasSdcardWritePermission = checkPermission(Permission.WRITE_EXTERNAL_STORAGE) && "mounted".equals(Environment.getExternalStorageState());
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
        return this.hasSdcardWritePermission;
    }

    public String getSdcardPath() {
        try {
            if (!TextUtils.isEmpty(this.bvs.sdp)) {
                return this.bvs.sdp;
            }
            if (Build.VERSION.SDK_INT < 29 || this.context.getApplicationInfo().targetSdkVersion < 29) {
                this.bvs.sdp = Environment.getExternalStorageDirectory().getAbsolutePath();
            } else {
                this.bvs.sdp = this.context.getExternalFilesDir((String) null).getAbsolutePath();
            }
            return this.bvs.sdp;
        } catch (Throwable unused) {
            return null;
        }
    }

    public String getAndroidID() {
        try {
            String string = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
            NLog instance = MobLog.getInstance();
            instance.mo29775i("getAndroidID === " + string, new Object[0]);
            return string;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return "";
        }
    }

    public String getAdvertisingID() throws Throwable {
        String str;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new Throwable("Do not call this function from the main thread !");
        } else if (!TextUtils.isEmpty(this.advertiseID)) {
            return this.advertiseID;
        } else {
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            GSConnection gSConnection = new GSConnection();
            try {
                this.context.bindService(intent, gSConnection, 1);
                IBinder takeBinder = gSConnection.takeBinder();
                if (takeBinder == null) {
                    str = this.advertiseID;
                } else {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    takeBinder.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    this.advertiseID = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    str = this.advertiseID;
                }
            } catch (Throwable th) {
                this.context.unbindService(gSConnection);
                throw th;
            }
            this.context.unbindService(gSConnection);
            return str;
        }
    }

    public void hideSoftInput(View view) {
        Object systemServiceSafe = getSystemServiceSafe("input_method");
        if (systemServiceSafe != null) {
            ((InputMethodManager) systemServiceSafe).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showSoftInput(View view) {
        Object systemServiceSafe = getSystemServiceSafe("input_method");
        if (systemServiceSafe != null) {
            ((InputMethodManager) systemServiceSafe).toggleSoftInputFromWindow(view.getWindowToken(), 2, 0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getIMSI() {
        /*
            r6 = this;
            com.mob.tools.utils.BVS r0 = r6.bvs
            java.lang.String r0 = r0.isi
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x000f
            com.mob.tools.utils.BVS r0 = r6.bvs
            java.lang.String r0 = r0.isi
            return r0
        L_0x000f:
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r6.getSystemServiceSafe(r0)
            r1 = 0
            if (r0 != 0) goto L_0x0019
            return r1
        L_0x0019:
            r2 = 0
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0037 }
            r4 = 29
            if (r3 >= r4) goto L_0x0043
            java.lang.String r3 = "android.permission.READ_PHONE_STATE"
            boolean r3 = r6.checkPermission(r3)     // Catch:{ Throwable -> 0x0037 }
            if (r3 == 0) goto L_0x0043
            r3 = 25
            java.lang.String r3 = com.mob.tools.utils.Strings.getString(r3)     // Catch:{ Throwable -> 0x0037 }
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0037 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r3, r4)     // Catch:{ Throwable -> 0x0037 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0037 }
            goto L_0x0044
        L_0x0037:
            r0 = move-exception
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()
            java.lang.String r0 = r0.getMessage()
            r3.mo29786w((java.lang.String) r0)
        L_0x0043:
            r0 = r1
        L_0x0044:
            r3 = 15
            if (r0 == 0) goto L_0x004e
            int r4 = r0.length()
            if (r4 >= r3) goto L_0x0061
        L_0x004e:
            java.lang.String[] r4 = r6.queryIMSI()
            if (r4 == 0) goto L_0x0061
            int r5 = r4.length
            if (r5 <= 0) goto L_0x0061
            r5 = r4[r2]
            int r5 = r5.length()
            if (r5 < r3) goto L_0x0061
            r0 = r4[r2]
        L_0x0061:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x0068
            return r1
        L_0x0068:
            com.mob.tools.utils.BVS r1 = r6.bvs
            r1.isi = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getIMSI():java.lang.String");
    }

    public String[] queryIMSI() {
        try {
            String systemProperties = getSystemProperties(Strings.getString(55));
            ArrayList arrayList = new ArrayList();
            for (String str : systemProperties.split(",")) {
                if (!TextUtils.isEmpty(str) && !arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
            if (arrayList.size() > 0) {
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    public String getIPAddress() {
        try {
            if (!checkPermission("android.permission.INTERNET")) {
                return "0.0.0.0";
            }
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
            return "0.0.0.0";
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return "0.0.0.0";
        }
    }

    public Location getLocation(int i, int i2, boolean z) {
        try {
            if (checkPermission(Permission.ACCESS_FINE_LOCATION) || (Build.VERSION.SDK_INT >= 29 && checkPermission(Permission.ACCESS_BACKGROUND_LOCATION))) {
                return LHelper.getInstance().getLocation(this.context, i, i2, z);
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public HashMap<String, String> ping(String str, int i, int i2) {
        float f;
        float f2;
        ArrayList arrayList = new ArrayList();
        try {
            String str2 = "ping -c " + i + " -s " + i2 + " " + str;
            int i3 = i2 + 8;
            Process process = (Process) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(42)), Strings.getString(43), new Object[0]), Strings.getString(44), str2);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream) ReflectHelper.invokeInstanceMethod(process, Strings.getString(45), new Object[0])));
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                if (readLine.startsWith(i3 + " bytes from")) {
                    if (readLine.endsWith("ms")) {
                        readLine = readLine.substring(0, readLine.length() - 2).trim();
                    } else if (readLine.endsWith("s")) {
                        readLine = readLine.substring(0, readLine.length() - 1).trim() + "000";
                    }
                    int indexOf = readLine.indexOf("time=");
                    if (indexOf > 0) {
                        arrayList.add(Float.valueOf(Float.parseFloat(readLine.substring(indexOf + 5).trim())));
                    }
                }
                readLine = bufferedReader.readLine();
            }
            process.waitFor();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        int size = arrayList.size();
        int size2 = i - arrayList.size();
        float f3 = 0.0f;
        if (size > 0) {
            float f4 = Float.MAX_VALUE;
            f = 0.0f;
            for (int i4 = 0; i4 < size; i4++) {
                float floatValue = ((Float) arrayList.get(i4)).floatValue();
                if (floatValue < f4) {
                    f4 = floatValue;
                }
                if (floatValue > f) {
                    f = floatValue;
                }
                f3 += floatValue;
            }
            f2 = f3 / ((float) size);
            f3 = f4;
        } else {
            f2 = 0.0f;
            f = 0.0f;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("address", str);
        hashMap.put("transmitted", String.valueOf(i));
        hashMap.put("received", String.valueOf(size));
        hashMap.put("loss", String.valueOf(size2));
        hashMap.put("min", String.valueOf(f3));
        hashMap.put("max", String.valueOf(f));
        hashMap.put("avg", String.valueOf(f2));
        return hashMap;
    }

    public int getCellId() {
        Object systemServiceSafe;
        Object invokeInstanceMethod;
        try {
            if (checkPermission(Permission.ACCESS_COARSE_LOCATION) && (systemServiceSafe = getSystemServiceSafe("phone")) != null && (invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0])) != null && !"CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(27), new Object[0]), -1)).intValue();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return -1;
    }

    public int getCellLac() {
        Object systemServiceSafe;
        Object invokeInstanceMethod;
        try {
            if (checkPermission(Permission.ACCESS_COARSE_LOCATION) && (systemServiceSafe = getSystemServiceSafe("phone")) != null && (invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0])) != null && !"CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(28), new Object[0]), -1)).intValue();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return -1;
    }

    public int getPsc() {
        Object systemServiceSafe;
        Object invokeInstanceMethod;
        try {
            if (checkPermission(Permission.ACCESS_COARSE_LOCATION) && (systemServiceSafe = getSystemServiceSafe("phone")) != null && (invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0])) != null && !"CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(63), new Object[0]), -1)).intValue();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCdmaLat() {
        /*
            r5 = this;
            r0 = -1
            java.lang.String r1 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r1 = r5.checkPermission(r1)     // Catch:{ Throwable -> 0x004b }
            if (r1 == 0) goto L_0x0053
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r5.getSystemServiceSafe(r1)     // Catch:{ Throwable -> 0x004b }
            if (r1 == 0) goto L_0x0053
            r2 = 26
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x004b }
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x004b }
            java.lang.Object r1 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r1, r2, r4)     // Catch:{ Throwable -> 0x004b }
            if (r1 == 0) goto L_0x0053
            java.lang.String r2 = "CdmaCellLocation"
            java.lang.Class r4 = r1.getClass()     // Catch:{ Throwable -> 0x004b }
            java.lang.String r4 = r4.getSimpleName()     // Catch:{ Throwable -> 0x004b }
            boolean r2 = r2.equals(r4)     // Catch:{ Throwable -> 0x004b }
            if (r2 == 0) goto L_0x0053
            r2 = 56
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x004b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x004b }
            java.lang.Object r1 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r1, r2, r3)     // Catch:{ Throwable -> 0x004b }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x004b }
            java.lang.Object r1 = com.mob.tools.utils.ResHelper.forceCast(r1, r2)     // Catch:{ Throwable -> 0x004b }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Throwable -> 0x004b }
            int r1 = r1.intValue()     // Catch:{ Throwable -> 0x004b }
            goto L_0x0054
        L_0x004b:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
        L_0x0053:
            r1 = -1
        L_0x0054:
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r2) goto L_0x005a
            goto L_0x005b
        L_0x005a:
            r0 = r1
        L_0x005b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getCdmaLat():int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCdmaLon() {
        /*
            r5 = this;
            r0 = -1
            java.lang.String r1 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r1 = r5.checkPermission(r1)     // Catch:{ Throwable -> 0x004b }
            if (r1 == 0) goto L_0x0053
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r5.getSystemServiceSafe(r1)     // Catch:{ Throwable -> 0x004b }
            if (r1 == 0) goto L_0x0053
            r2 = 26
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x004b }
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x004b }
            java.lang.Object r1 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r1, r2, r4)     // Catch:{ Throwable -> 0x004b }
            if (r1 == 0) goto L_0x0053
            java.lang.String r2 = "CdmaCellLocation"
            java.lang.Class r4 = r1.getClass()     // Catch:{ Throwable -> 0x004b }
            java.lang.String r4 = r4.getSimpleName()     // Catch:{ Throwable -> 0x004b }
            boolean r2 = r2.equals(r4)     // Catch:{ Throwable -> 0x004b }
            if (r2 == 0) goto L_0x0053
            r2 = 57
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x004b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x004b }
            java.lang.Object r1 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r1, r2, r3)     // Catch:{ Throwable -> 0x004b }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x004b }
            java.lang.Object r1 = com.mob.tools.utils.ResHelper.forceCast(r1, r2)     // Catch:{ Throwable -> 0x004b }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Throwable -> 0x004b }
            int r1 = r1.intValue()     // Catch:{ Throwable -> 0x004b }
            goto L_0x0054
        L_0x004b:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
        L_0x0053:
            r1 = -1
        L_0x0054:
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r2) goto L_0x005a
            goto L_0x005b
        L_0x005a:
            r0 = r1
        L_0x005b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getCdmaLon():int");
    }

    public int getCdmaBid() {
        Object systemServiceSafe;
        Object invokeInstanceMethod;
        try {
            if (checkPermission(Permission.ACCESS_COARSE_LOCATION) && (systemServiceSafe = getSystemServiceSafe("phone")) != null && (invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0])) != null && "CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(58), new Object[0]), -1)).intValue();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return -1;
    }

    public int getCdmaSid() {
        Object systemServiceSafe;
        Object invokeInstanceMethod;
        try {
            if (checkPermission(Permission.ACCESS_COARSE_LOCATION) && (systemServiceSafe = getSystemServiceSafe("phone")) != null && (invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0])) != null && "CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(59), new Object[0]), -1)).intValue();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return -1;
    }

    public int getCdmaNid() {
        Object systemServiceSafe;
        Object invokeInstanceMethod;
        try {
            if (checkPermission(Permission.ACCESS_COARSE_LOCATION) && (systemServiceSafe = getSystemServiceSafe("phone")) != null && (invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(26), new Object[0])) != null && "CdmaCellLocation".equals(invokeInstanceMethod.getClass().getSimpleName())) {
                return ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(60), new Object[0]), -1)).intValue();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return -1;
    }

    public ArrayList<HashMap<String, Object>> getNeighboringCellInfo() {
        Object systemServiceSafe;
        List list;
        try {
            if (!checkPermission(Permission.ACCESS_COARSE_LOCATION) || isScopedStorage() || (systemServiceSafe = getSystemServiceSafe("phone")) == null || (list = (List) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(61), new Object[0])) == null || list.size() <= 0) {
                return null;
            }
            ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
            for (Object next : list) {
                int intValue = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(27), new Object[0]), -1)).intValue();
                int intValue2 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(28), new Object[0]), -1)).intValue();
                int intValue3 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(62), new Object[0]), -1)).intValue();
                int intValue4 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(63), new Object[0]), -1)).intValue();
                int intValue5 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(next, Strings.getString(19), new Object[0]), -1)).intValue();
                if (!(intValue == -1 || intValue2 == -1)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("cell", Integer.valueOf(intValue));
                    hashMap.put("lac", Integer.valueOf(intValue2));
                    hashMap.put("rssi", Integer.valueOf(intValue3));
                    hashMap.put("psc", Integer.valueOf(intValue4));
                    hashMap.put("networkType", Integer.valueOf(intValue5));
                    arrayList.add(hashMap);
                }
            }
            if (arrayList.size() > 0) {
                return arrayList;
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public String getDeviceType() {
        try {
            UiModeManager uiModeManager = (UiModeManager) getSystemServiceSafe("uimode");
            if (uiModeManager == null) {
                return "UNDEFINED";
            }
            switch (uiModeManager.getCurrentModeType()) {
                case 1:
                    return "NO_UI";
                case 2:
                    return "DESK";
                case 3:
                    return "CAR";
                case 4:
                    return "TELEVISION";
                case 5:
                    return "APPLIANCE";
                case 6:
                    return "WATCH";
                case 7:
                    return "VRHEADSET";
                default:
                    return "UNDEFINED";
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return "UNDEFINED";
        }
    }

    public int cscreen() {
        if (((PowerManager) this.context.getSystemService("power")).isScreenOn()) {
            return ((KeyguardManager) this.context.getSystemService("keyguard")).inKeyguardRestrictedInputMode() ? 2 : 1;
        }
        return 0;
    }

    public List<Object[]> getIntentA(String str) {
        ArrayList arrayList = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        Uri parse = Uri.parse(str);
        intent.setData(parse);
        List<ResolveInfo> queryIntentActivities = this.context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities != null && !queryIntentActivities.isEmpty()) {
            arrayList = new ArrayList();
            for (ResolveInfo next : queryIntentActivities) {
                Intent intent2 = new Intent();
                intent2.setFlags(276824064);
                intent2.setData(parse);
                String signMD5 = getSignMD5(next.activityInfo.packageName);
                intent2.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                arrayList.add(new Object[]{next.activityInfo.packageName, next.activityInfo.name, intent2, signMD5, Boolean.valueOf(next.activityInfo.exported)});
            }
        }
        return arrayList;
    }

    public String getDefaultResolvePkg(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        ResolveInfo resolveActivity = this.context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            return null;
        }
        return resolveActivity.activityInfo.packageName;
    }

    public List<String> getResolvePkgs(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        List<ResolveInfo> queryIntentActivities = this.context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : queryIntentActivities) {
            if (!(next.activityInfo == null || next.activityInfo.packageName == null || arrayList.contains(next.activityInfo.packageName))) {
                arrayList.add(next.activityInfo.packageName);
            }
        }
        return arrayList;
    }

    public List<Object[]> getIntentSP(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        intent.setPackage(str);
        List<ResolveInfo> queryIntentServices = this.context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : queryIntentServices) {
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName(next.serviceInfo.packageName, next.serviceInfo.name));
            arrayList.add(new Object[]{next.serviceInfo.packageName, next.serviceInfo.name, intent2, getSignMD5(next.serviceInfo.packageName)});
        }
        return arrayList;
    }

    /* renamed from: gb */
    public String mo29991gb(Context context2) {
        try {
            if (Build.VERSION.SDK_INT < 11) {
                return (String) ((ClipboardManager) context2.getSystemService("clipboard")).getText();
            }
            ClipData primaryClip = ((ClipboardManager) context2.getSystemService("clipboard")).getPrimaryClip();
            if (primaryClip == null || primaryClip.getItemCount() <= 0) {
                return null;
            }
            return primaryClip.getItemAt(0).getText().toString();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    /* renamed from: cb */
    public int mo29981cb(Context context2, String str) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                ((ClipboardManager) context2.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("", str));
                return 1;
            }
            ((ClipboardManager) context2.getSystemService("clipboard")).setText(str);
            return 1;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 2;
        }
    }

    public int sap(Context context2, String str) throws Throwable {
        try {
            Intent launchIntentForPackage = context2.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return 0;
            }
            launchIntentForPackage.addFlags(276824064);
            context2.startActivity(launchIntentForPackage);
            return 1;
        } catch (ActivityNotFoundException e) {
            MobLog.getInstance().mo29769d(e);
            return 0;
        }
    }

    /* renamed from: ca */
    public int mo29980ca(Context context2, String str) {
        try {
            Intent launchIntentForPackage = context2.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                return mo29986cs(context2, launchIntentForPackage);
            }
            return 0;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 3;
        }
    }

    /* renamed from: sa */
    public int mo30121sa(Context context2, Intent intent) throws Throwable {
        try {
            context2.startActivity(intent);
            return 1;
        } catch (ActivityNotFoundException e) {
            MobLog.getInstance().mo29769d(e);
            return 0;
        }
    }

    public int saInUI(final Context context2, final Intent intent) {
        final int[] iArr = new int[1];
        UIHandler.sendEmptyMessage(0, new Handler.Callback() {
            public boolean handleMessage(Message message) {
                synchronized (iArr) {
                    try {
                        iArr[0] = DeviceHelper.this.mo30121sa(context2, intent);
                        iArr.notifyAll();
                    } catch (Throwable th) {
                        try {
                            iArr[0] = 2;
                            MobLog.getInstance().mo29769d(th);
                            iArr.notifyAll();
                        } catch (Throwable th2) {
                            iArr.notifyAll();
                            throw th2;
                        }
                    }
                }
                return false;
            }
        });
        synchronized (iArr) {
            try {
                iArr.wait();
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
        return iArr[0];
    }

    /* renamed from: sh */
    public int mo30127sh(Context context2) throws Throwable {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.HOME");
            intent.addCategory("android.intent.category.DEFAULT");
            context2.startActivity(intent);
            return 1;
        } catch (ActivityNotFoundException e) {
            MobLog.getInstance().mo29769d(e);
            return 0;
        }
    }

    /* renamed from: ih */
    public int mo30100ih(Context context2) throws Throwable {
        String topApp = getTopApp(context2);
        return (topApp == null || !getLauncherPackageNames(context2).contains(topApp)) ? 0 : 1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v21, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v22, resolved type: java.io.DataInputStream} */
    /* JADX WARNING: Can't wrap try/catch for region: R(19:15|16|17|18|19|20|21|22|23|24|(2:25|(1:27)(1:99))|28|29|(2:31|32)|33|34|35|36|37) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00e5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00e8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0111 A[SYNTHETIC, Splitter:B:56:0x0111] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0118 A[SYNTHETIC, Splitter:B:60:0x0118] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x011f A[SYNTHETIC, Splitter:B:64:0x011f] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0126 A[SYNTHETIC, Splitter:B:68:0x0126] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x012d A[SYNTHETIC, Splitter:B:72:0x012d] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0134 A[SYNTHETIC, Splitter:B:76:0x0134] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0155  */
    /* renamed from: ir */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo30103ir(android.content.Context r10, java.lang.String r11) throws java.lang.Throwable {
        /*
            r9 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 1
            r2 = 0
            r3 = 21
            if (r0 >= r3) goto L_0x0036
            java.lang.String r0 = "activity"
            java.lang.Object r10 = r10.getSystemService(r0)
            android.app.ActivityManager r10 = (android.app.ActivityManager) r10
            java.util.List r10 = r10.getRunningAppProcesses()
            if (r10 == 0) goto L_0x0035
            int r0 = r10.size()
            if (r0 <= 0) goto L_0x0035
            java.util.Iterator r10 = r10.iterator()
        L_0x0020:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0035
            java.lang.Object r0 = r10.next()
            android.app.ActivityManager$RunningAppProcessInfo r0 = (android.app.ActivityManager.RunningAppProcessInfo) r0
            java.lang.String r0 = r0.processName
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x0020
            return r1
        L_0x0035:
            return r2
        L_0x0036:
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 24
            if (r0 > r3) goto L_0x0138
            r0 = 42
            r3 = 0
            java.lang.String r0 = com.mob.tools.utils.Strings.getString(r0)     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            java.lang.String r0 = com.mob.tools.utils.ReflectHelper.importClass(r0)     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            r4 = 43
            java.lang.String r4 = com.mob.tools.utils.Strings.getString(r4)     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r0, r4, r5)     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            r4 = 44
            java.lang.String r4 = com.mob.tools.utils.Strings.getString(r4)     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            r6 = 46
            java.lang.String r6 = com.mob.tools.utils.Strings.getString(r6)     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            r5[r2] = r6     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r4, r5)     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            java.lang.Process r0 = (java.lang.Process) r0     // Catch:{ Throwable -> 0x0103, all -> 0x00fe }
            r4 = 128(0x80, float:1.794E-43)
            java.lang.String r4 = com.mob.tools.utils.Strings.getString(r4)     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            java.lang.Object r4 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r4, r5)     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            java.io.OutputStream r4 = (java.io.OutputStream) r4     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            r5 = 45
            java.lang.String r5 = com.mob.tools.utils.Strings.getString(r5)     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            java.lang.Object r5 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r5, r6)     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            java.io.InputStream r5 = (java.io.InputStream) r5     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            java.io.DataOutputStream r6 = new java.io.DataOutputStream     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            r6.<init>(r4)     // Catch:{ Throwable -> 0x00f9, all -> 0x00f6 }
            java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            r4.<init>(r5)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ec }
            r3.<init>()     // Catch:{ Throwable -> 0x00ec }
            java.lang.String r5 = "ps | grep "
            r3.append(r5)     // Catch:{ Throwable -> 0x00ec }
            r3.append(r11)     // Catch:{ Throwable -> 0x00ec }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00ec }
            byte[] r3 = r3.getBytes()     // Catch:{ Throwable -> 0x00ec }
            r6.write(r3)     // Catch:{ Throwable -> 0x00ec }
            java.lang.String r3 = "\n"
            r6.writeBytes(r3)     // Catch:{ Throwable -> 0x00ec }
            r6.flush()     // Catch:{ Throwable -> 0x00ec }
            java.lang.String r3 = "exit\n"
            r6.writeBytes(r3)     // Catch:{ Throwable -> 0x00ec }
            r6.flush()     // Catch:{ Throwable -> 0x00ec }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ec }
            r3.<init>()     // Catch:{ Throwable -> 0x00ec }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00ec }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00ec }
            r7.<init>(r4)     // Catch:{ Throwable -> 0x00ec }
            r5.<init>(r7)     // Catch:{ Throwable -> 0x00ec }
        L_0x00c6:
            java.lang.String r7 = r5.readLine()     // Catch:{ Throwable -> 0x00ec }
            if (r7 == 0) goto L_0x00d0
            r3.append(r7)     // Catch:{ Throwable -> 0x00ec }
            goto L_0x00c6
        L_0x00d0:
            r0.waitFor()     // Catch:{ Throwable -> 0x00ec }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00ec }
            java.lang.String r3 = r3.trim()     // Catch:{ Throwable -> 0x00ec }
            boolean r10 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x00ec }
            r10 = r10 ^ r1
            if (r0 == 0) goto L_0x00e5
            r0.destroy()     // Catch:{ Throwable -> 0x00e5 }
        L_0x00e5:
            r6.close()     // Catch:{ Throwable -> 0x00e8 }
        L_0x00e8:
            r4.close()     // Catch:{ Throwable -> 0x00eb }
        L_0x00eb:
            return r10
        L_0x00ec:
            r3 = move-exception
            goto L_0x0108
        L_0x00ee:
            r10 = move-exception
            r4 = r3
            goto L_0x0124
        L_0x00f1:
            r4 = move-exception
            r8 = r4
            r4 = r3
            r3 = r8
            goto L_0x0108
        L_0x00f6:
            r10 = move-exception
            r4 = r3
            goto L_0x0101
        L_0x00f9:
            r4 = move-exception
            r6 = r3
            r3 = r4
            r4 = r6
            goto L_0x0108
        L_0x00fe:
            r10 = move-exception
            r0 = r3
            r4 = r0
        L_0x0101:
            r6 = r4
            goto L_0x0124
        L_0x0103:
            r0 = move-exception
            r4 = r3
            r6 = r4
            r3 = r0
            r0 = r6
        L_0x0108:
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0123 }
            r5.mo29769d(r3)     // Catch:{ all -> 0x0123 }
            if (r0 == 0) goto L_0x0116
            r0.destroy()     // Catch:{ Throwable -> 0x0115 }
            goto L_0x0116
        L_0x0115:
        L_0x0116:
            if (r6 == 0) goto L_0x011d
            r6.close()     // Catch:{ Throwable -> 0x011c }
            goto L_0x011d
        L_0x011c:
        L_0x011d:
            if (r4 == 0) goto L_0x0138
            r4.close()     // Catch:{ Throwable -> 0x0138 }
            goto L_0x0138
        L_0x0123:
            r10 = move-exception
        L_0x0124:
            if (r0 == 0) goto L_0x012b
            r0.destroy()     // Catch:{ Throwable -> 0x012a }
            goto L_0x012b
        L_0x012a:
        L_0x012b:
            if (r6 == 0) goto L_0x0132
            r6.close()     // Catch:{ Throwable -> 0x0131 }
            goto L_0x0132
        L_0x0131:
        L_0x0132:
            if (r4 == 0) goto L_0x0137
            r4.close()     // Catch:{ Throwable -> 0x0137 }
        L_0x0137:
            throw r10
        L_0x0138:
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 27
            r4 = 2
            if (r0 >= r3) goto L_0x016b
            java.util.List r11 = r9.getIntentSP(r11)
            if (r11 == 0) goto L_0x016b
            int r0 = r11.size()
            if (r0 <= 0) goto L_0x016b
            java.util.Iterator r11 = r11.iterator()
        L_0x014f:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x016b
            java.lang.Object r0 = r11.next()
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r0 = r0[r4]
            android.content.Intent r0 = (android.content.Intent) r0
            int r0 = r9.mo29986cs(r10, r0)
            if (r0 != 0) goto L_0x0166
            return r2
        L_0x0166:
            if (r0 == r1) goto L_0x016a
            if (r0 != r4) goto L_0x014f
        L_0x016a:
            return r1
        L_0x016b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.mo30103ir(android.content.Context, java.lang.String):int");
    }

    private static List<String> getLauncherPackageNames(Context context2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context2.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addCategory("android.intent.category.DEFAULT");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 65536)) {
            arrayList.add(resolveInfo.activityInfo.packageName);
        }
        return arrayList;
    }

    public static String getTopApp(Context context2) throws Throwable {
        if (Build.VERSION.SDK_INT < 21) {
            return ((ActivityManager) context2.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getPackageName();
        }
        UsageStatsManager usageStatsManager = (UsageStatsManager) context2.getSystemService("usagestats");
        if (usageStatsManager == null) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(4, currentTimeMillis - DateUtil.HOUR_MILL_SECONDS, currentTimeMillis);
        if (queryUsageStats == null || queryUsageStats.isEmpty()) {
            return "";
        }
        int i = 0;
        for (int i2 = 0; i2 < queryUsageStats.size(); i2++) {
            if (queryUsageStats.get(i2).getLastTimeUsed() > queryUsageStats.get(i).getLastTimeUsed()) {
                i = i2;
            }
        }
        return queryUsageStats.get(i).getPackageName();
    }

    public Activity getTopActivity() {
        try {
            if (Build.VERSION.SDK_INT > 27) {
                return null;
            }
            Map map = (Map) ReflectHelper.getInstanceField(currentActivityThread(), Strings.getString(23));
            for (Object next : map.values()) {
                if (!((Boolean) ReflectHelper.getInstanceField(next, Strings.getString(29))).booleanValue()) {
                    return (Activity) ReflectHelper.getInstanceField(next, Strings.getString(30));
                }
            }
            for (Object next2 : map.values()) {
                if (!((Boolean) ReflectHelper.getInstanceField(next2, Strings.getString(24))).booleanValue()) {
                    return (Activity) ReflectHelper.getInstanceField(next2, Strings.getString(30));
                }
            }
            return null;
        } catch (Throwable unused) {
        }
    }

    public static Object currentActivityThread() {
        final C24712 r0 = new ReflectHelper.ReflectRunnable<Void, Object>() {
            public Object run(Void voidR) {
                try {
                    return ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(31)), Strings.getString(32), new Object[0]);
                } catch (Throwable th) {
                    MobLog.getInstance().mo29787w(th);
                    return null;
                }
            }
        };
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            return r0.run(null);
        }
        final Object obj = new Object();
        final Object[] objArr = new Object[1];
        synchronized (obj) {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() {
                /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
                    r0 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
                    r1.notify();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:25:0x003e, code lost:
                    r1 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
                    com.mob.tools.MobLog.getInstance().mo29787w(r1);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
                    r1 = th;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
                    r2 = com.mob.tools.MobLog.getInstance();
                 */
                /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x000f, B:13:0x0021, B:22:0x0036] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public boolean handleMessage(android.os.Message r5) {
                    /*
                        r4 = this;
                        java.lang.Object r5 = r1
                        monitor-enter(r5)
                        r0 = 0
                        java.lang.Object[] r1 = r2     // Catch:{ Throwable -> 0x0020 }
                        com.mob.tools.utils.ReflectHelper$ReflectRunnable r2 = r0     // Catch:{ Throwable -> 0x0020 }
                        r3 = 0
                        java.lang.Object r2 = r2.run(r3)     // Catch:{ Throwable -> 0x0020 }
                        r1[r0] = r2     // Catch:{ Throwable -> 0x0020 }
                        java.lang.Object r1 = r1     // Catch:{ Throwable -> 0x0015 }
                        r1.notify()     // Catch:{ Throwable -> 0x0015 }
                        goto L_0x0034
                    L_0x0015:
                        r1 = move-exception
                        com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x003c }
                    L_0x001a:
                        r2.mo29787w((java.lang.Throwable) r1)     // Catch:{ all -> 0x003c }
                        goto L_0x0034
                    L_0x001e:
                        r0 = move-exception
                        goto L_0x0036
                    L_0x0020:
                        r1 = move-exception
                        com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x001e }
                        r2.mo29787w((java.lang.Throwable) r1)     // Catch:{ all -> 0x001e }
                        java.lang.Object r1 = r1     // Catch:{ Throwable -> 0x002e }
                        r1.notify()     // Catch:{ Throwable -> 0x002e }
                        goto L_0x0034
                    L_0x002e:
                        r1 = move-exception
                        com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x003c }
                        goto L_0x001a
                    L_0x0034:
                        monitor-exit(r5)     // Catch:{ all -> 0x003c }
                        return r0
                    L_0x0036:
                        java.lang.Object r1 = r1     // Catch:{ Throwable -> 0x003e }
                        r1.notify()     // Catch:{ Throwable -> 0x003e }
                        goto L_0x0046
                    L_0x003c:
                        r0 = move-exception
                        goto L_0x0047
                    L_0x003e:
                        r1 = move-exception
                        com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x003c }
                        r2.mo29787w((java.lang.Throwable) r1)     // Catch:{ all -> 0x003c }
                    L_0x0046:
                        throw r0     // Catch:{ all -> 0x003c }
                    L_0x0047:
                        monitor-exit(r5)     // Catch:{ all -> 0x003c }
                        goto L_0x004a
                    L_0x0049:
                        throw r0
                    L_0x004a:
                        goto L_0x0049
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.C24723.handleMessage(android.os.Message):boolean");
                }
            });
            try {
                obj.wait();
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
        return objArr[0];
    }

    public static Context getApplication() {
        try {
            Object currentActivityThread = currentActivityThread();
            if (currentActivityThread != null) {
                return (Context) ReflectHelper.invokeInstanceMethod(currentActivityThread, Strings.getString(33), new Object[0]);
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    public HashMap<String, Object> getCurrentWifiInfo() {
        Object systemServiceSafe;
        Object invokeInstanceMethod;
        String str = null;
        try {
            if (!(!checkPermission("android.permission.ACCESS_WIFI_STATE") || (systemServiceSafe = getSystemServiceSafe("wifi")) == null || (invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(2), new Object[0])) == null)) {
                HashMap<String, Object> hashMap = new HashMap<>();
                try {
                    hashMap.put("bssid", (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(4), new Object[0]));
                } catch (Throwable unused) {
                }
                try {
                    String str2 = (String) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(3), new Object[0]);
                    if (str2 != null) {
                        str = str2.replace("\"", "");
                    }
                    hashMap.put("ssid", str);
                } catch (Throwable unused2) {
                }
                try {
                    hashMap.put("ip", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(79), new Object[0])).intValue()));
                } catch (Throwable unused3) {
                }
                try {
                    hashMap.put("wlanMac", getMacAddress());
                } catch (Throwable unused4) {
                }
                try {
                    hashMap.put("hidden", Boolean.valueOf(((Boolean) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(80), new Object[0])).booleanValue()));
                } catch (Throwable unused5) {
                }
                try {
                    hashMap.put("speed", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(81), new Object[0])).intValue()));
                } catch (Throwable unused6) {
                }
                try {
                    hashMap.put("networkId", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(60), new Object[0])).intValue()));
                } catch (Throwable unused7) {
                }
                try {
                    hashMap.put("level", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(62), new Object[0])).intValue()));
                } catch (Throwable unused8) {
                }
                try {
                    hashMap.put("frequency", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(invokeInstanceMethod, Strings.getString(82), new Object[0])).intValue()));
                } catch (Throwable unused9) {
                }
                return hashMap;
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:51|(7:53|54|55|(1:57)(1:58)|59|89|60)|88|62|63|64|65|66|(3:68|(1:70)(1:71)|72)|73|74|82) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x00f3 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x0115 */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f9 A[Catch:{ Throwable -> 0x0115 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> getAvailableWifiList() {
        /*
            r14 = this;
            r0 = 0
            java.lang.String r1 = "android.permission.ACCESS_WIFI_STATE"
            boolean r1 = r14.checkPermission(r1)     // Catch:{ Throwable -> 0x011b }
            if (r1 == 0) goto L_0x0123
            java.lang.String r1 = "wifi"
            java.lang.Object r1 = r14.getSystemServiceSafe(r1)     // Catch:{ Throwable -> 0x011b }
            if (r1 != 0) goto L_0x0012
            return r0
        L_0x0012:
            r2 = 34
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x011b }
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x011b }
            java.lang.Object r1 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r1, r2, r4)     // Catch:{ Throwable -> 0x011b }
            java.util.List r1 = (java.util.List) r1     // Catch:{ Throwable -> 0x011b }
            if (r1 != 0) goto L_0x0024
            return r0
        L_0x0024:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x011b }
            r4 = 27
            java.lang.String r5 = ","
            if (r2 <= r4) goto L_0x0041
            r2 = 72
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x011b }
            java.lang.String[] r2 = android.text.TextUtils.split(r2, r5)     // Catch:{ Throwable -> 0x011b }
            r4 = 73
            java.lang.String r4 = com.mob.tools.utils.Strings.getString(r4)     // Catch:{ Throwable -> 0x011b }
            java.lang.String[] r4 = android.text.TextUtils.split(r4, r5)     // Catch:{ Throwable -> 0x011b }
            goto L_0x0055
        L_0x0041:
            r2 = 35
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x011b }
            java.lang.String[] r2 = android.text.TextUtils.split(r2, r5)     // Catch:{ Throwable -> 0x011b }
            r4 = 36
            java.lang.String r4 = com.mob.tools.utils.Strings.getString(r4)     // Catch:{ Throwable -> 0x011b }
            java.lang.String[] r4 = android.text.TextUtils.split(r4, r5)     // Catch:{ Throwable -> 0x011b }
        L_0x0055:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Throwable -> 0x011b }
            r5.<init>()     // Catch:{ Throwable -> 0x011b }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x011b }
        L_0x005e:
            boolean r6 = r1.hasNext()     // Catch:{ Throwable -> 0x011b }
            if (r6 == 0) goto L_0x011a
            java.lang.Object r6 = r1.next()     // Catch:{ Throwable -> 0x011b }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ Throwable -> 0x011b }
            r7.<init>()     // Catch:{ Throwable -> 0x011b }
            int r8 = r2.length     // Catch:{ Throwable -> 0x011b }
            r10 = r0
            r9 = 0
        L_0x0070:
            if (r9 >= r8) goto L_0x00bb
            r11 = r2[r9]     // Catch:{ Throwable -> 0x011b }
            java.lang.String r11 = r11.trim()     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r12 = "SSID"
            boolean r12 = r12.equals(r11)     // Catch:{ Throwable -> 0x00b8 }
            if (r12 == 0) goto L_0x0093
            java.lang.Object r12 = com.mob.tools.utils.ReflectHelper.getInstanceField(r6, r11)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ Throwable -> 0x00b8 }
            boolean r10 = android.text.TextUtils.isEmpty(r12)     // Catch:{ Throwable -> 0x0091 }
            if (r10 == 0) goto L_0x008e
            r10 = r12
            goto L_0x00bb
        L_0x008e:
            r7.put(r11, r12)     // Catch:{ Throwable -> 0x0091 }
        L_0x0091:
            r10 = r12
            goto L_0x00b8
        L_0x0093:
            java.lang.String r12 = "capabilities"
            boolean r12 = r12.equals(r11)     // Catch:{ Throwable -> 0x00b8 }
            if (r12 == 0) goto L_0x00b1
            java.lang.Object r12 = com.mob.tools.utils.ReflectHelper.getInstanceField(r6, r11)     // Catch:{ Throwable -> 0x00b8 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ Throwable -> 0x00b8 }
            if (r12 == 0) goto L_0x00ad
            java.lang.String r13 = "[IBSS]"
            boolean r13 = r12.contains(r13)     // Catch:{ Throwable -> 0x00b8 }
            if (r13 == 0) goto L_0x00ad
            r10 = r0
            goto L_0x00bb
        L_0x00ad:
            r7.put(r11, r12)     // Catch:{ Throwable -> 0x00b8 }
            goto L_0x00b8
        L_0x00b1:
            java.lang.Object r12 = com.mob.tools.utils.ReflectHelper.getInstanceField(r6, r11)     // Catch:{ Throwable -> 0x00b8 }
            r7.put(r11, r12)     // Catch:{ Throwable -> 0x00b8 }
        L_0x00b8:
            int r9 = r9 + 1
            goto L_0x0070
        L_0x00bb:
            boolean r8 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Throwable -> 0x011b }
            if (r8 == 0) goto L_0x00c2
            goto L_0x005e
        L_0x00c2:
            int r8 = r4.length     // Catch:{ Throwable -> 0x011b }
            r9 = 0
        L_0x00c4:
            if (r9 >= r8) goto L_0x00de
            r10 = r4[r9]     // Catch:{ Throwable -> 0x011b }
            java.lang.String r10 = r10.trim()     // Catch:{ Throwable -> 0x00db }
            java.lang.Object r11 = com.mob.tools.utils.ReflectHelper.getInstanceField(r6, r10)     // Catch:{ Throwable -> 0x00db }
            if (r11 != 0) goto L_0x00d4
            r11 = r0
            goto L_0x00d8
        L_0x00d4:
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x00db }
        L_0x00d8:
            r7.put(r10, r11)     // Catch:{ Throwable -> 0x00db }
        L_0x00db:
            int r9 = r9 + 1
            goto L_0x00c4
        L_0x00de:
            r8 = 37
            java.lang.String r8 = com.mob.tools.utils.Strings.getString(r8)     // Catch:{ Throwable -> 0x00f3 }
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00f3 }
            java.lang.Object r8 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r6, r8, r9)     // Catch:{ Throwable -> 0x00f3 }
            r9 = 39
            java.lang.String r9 = com.mob.tools.utils.Strings.getString(r9)     // Catch:{ Throwable -> 0x00f3 }
            r7.put(r9, r8)     // Catch:{ Throwable -> 0x00f3 }
        L_0x00f3:
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0115 }
            r9 = 28
            if (r8 >= r9) goto L_0x0115
            r8 = 38
            java.lang.String r9 = com.mob.tools.utils.Strings.getString(r8)     // Catch:{ Throwable -> 0x0115 }
            java.lang.Object r6 = com.mob.tools.utils.ReflectHelper.getInstanceField(r6, r9)     // Catch:{ Throwable -> 0x0115 }
            java.util.List r6 = (java.util.List) r6     // Catch:{ Throwable -> 0x0115 }
            java.lang.String r8 = com.mob.tools.utils.Strings.getString(r8)     // Catch:{ Throwable -> 0x0115 }
            if (r6 != 0) goto L_0x010d
            r9 = r0
            goto L_0x0112
        L_0x010d:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0115 }
            r9.<init>(r6)     // Catch:{ Throwable -> 0x0115 }
        L_0x0112:
            r7.put(r8, r9)     // Catch:{ Throwable -> 0x0115 }
        L_0x0115:
            r5.add(r7)     // Catch:{ Throwable -> 0x011b }
            goto L_0x005e
        L_0x011a:
            return r5
        L_0x011b:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29787w((java.lang.Throwable) r1)
        L_0x0123:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getAvailableWifiList():java.util.ArrayList");
    }

    private int getWifiSecurity(String str) {
        if (str == null) {
            return 0;
        }
        if (str.contains("WEP")) {
            return 1;
        }
        if (str.contains("PSK")) {
            return 2;
        }
        return str.contains("EAP") ? 3 : 0;
    }

    public boolean scanWifiList() {
        Object systemServiceSafe;
        try {
            if (!checkPermission("android.permission.CHANGE_WIFI_STATE") || (systemServiceSafe = getSystemServiceSafe("wifi")) == null) {
                return false;
            }
            return ((Boolean) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(40), new Object[0])).booleanValue();
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return false;
    }

    public int getStatusBarHeight() {
        if (Build.VERSION.SDK_INT >= 28) {
            return -1;
        }
        try {
            return this.context.getResources().getDimensionPixelSize(((Integer) ReflectHelper.getStaticField(ReflectHelper.importClass("com.android.internal.R$dimen"), "status_bar_height")).intValue());
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return -1;
        }
    }

    public boolean isPackageInstalled(String str) {
        try {
            return this.context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Throwable unused) {
            return false;
        }
    }

    public List<Object[]> getIntent(String str) throws Throwable {
        ArrayList arrayList = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent();
        Uri parse = Uri.parse(str);
        intent.setData(parse);
        List<ResolveInfo> queryIntentServices = this.context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
            arrayList = new ArrayList();
            for (ResolveInfo next : queryIntentServices) {
                Intent intent2 = new Intent();
                intent2.setData(parse);
                intent2.setComponent(new ComponentName(next.serviceInfo.packageName, next.serviceInfo.name));
                arrayList.add(new Object[]{next.serviceInfo.packageName, next.serviceInfo.name, intent2});
            }
        }
        return arrayList;
    }

    /* renamed from: ss */
    public int mo30129ss(Context context2, Intent intent) throws Throwable {
        try {
            return context2.startService(intent) == null ? 0 : 1;
        } catch (SecurityException e) {
            MobLog.getInstance().mo29769d(e);
            return 2;
        }
    }

    /* renamed from: bs */
    public int mo29979bs(final Context context2, Intent intent) throws Throwable {
        final boolean[] zArr = {false};
        try {
            if (!context2.bindService(intent, new ServiceConnection() {
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    try {
                        synchronized (zArr) {
                            zArr[0] = true;
                            zArr.notifyAll();
                            context2.unbindService(this);
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29769d(th);
                    }
                }

                public void onServiceDisconnected(ComponentName componentName) {
                    try {
                        synchronized (zArr) {
                            zArr.notifyAll();
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29769d(th);
                    }
                }
            }, 1)) {
                return 0;
            }
            long j = 200;
            while (!zArr[0] && j > 0) {
                synchronized (zArr) {
                    j -= 20;
                    zArr.wait(20);
                }
            }
            if (zArr[0]) {
                return 1;
            }
            return 2;
        } catch (SecurityException e) {
            MobLog.getInstance().mo29769d(e);
            return 3;
        }
    }

    /* renamed from: cs */
    public int mo29986cs(Context context2, Intent intent) {
        boolean z;
        boolean z2;
        if (Build.VERSION.SDK_INT >= 26) {
            return 4;
        }
        try {
            ComponentName component = intent.getComponent();
            String packageName = component.getPackageName();
            String className = component.getClassName();
            List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context2.getSystemService("activity")).getRunningServices(1000);
            if (runningServices != null && !runningServices.isEmpty()) {
                Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
                z2 = false;
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    ActivityManager.RunningServiceInfo next = it.next();
                    String packageName2 = next.service.getPackageName();
                    String className2 = next.service.getClassName();
                    if (packageName2.equals(packageName)) {
                        if (className2.equals(className)) {
                            z = true;
                            z2 = true;
                            break;
                        }
                        z2 = true;
                    }
                }
            } else {
                z = false;
                z2 = false;
            }
            if (!z2) {
                return 0;
            }
            if (z) {
                return 1;
            }
            return 2;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        if (r5 == null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        r3.add(r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.lang.Object> getCPUInfo() {
        /*
            r9 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Throwable -> 0x007f }
            r2 = 41
            java.lang.String r2 = com.mob.tools.utils.Strings.getString(r2)     // Catch:{ Throwable -> 0x007f }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x007f }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x007f }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x007f }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Throwable -> 0x007f }
            r3.<init>()     // Catch:{ Throwable -> 0x007f }
            java.lang.String r4 = "processors"
            r0.put(r4, r3)     // Catch:{ Throwable -> 0x007f }
            r4 = 0
        L_0x0020:
            r5 = r4
        L_0x0021:
            java.lang.String r6 = r2.readLine()     // Catch:{ Throwable -> 0x007f }
            if (r6 == 0) goto L_0x0078
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x007f }
            if (r7 == 0) goto L_0x0033
            if (r5 == 0) goto L_0x0020
            r3.add(r5)     // Catch:{ Throwable -> 0x007f }
            goto L_0x0020
        L_0x0033:
            java.lang.String r6 = r6.trim()     // Catch:{ Throwable -> 0x007f }
            java.lang.String r7 = "processor"
            boolean r7 = r6.startsWith(r7)     // Catch:{ Throwable -> 0x007f }
            if (r7 == 0) goto L_0x0049
            if (r5 == 0) goto L_0x0044
            r3.add(r5)     // Catch:{ Throwable -> 0x007f }
        L_0x0044:
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ Throwable -> 0x007f }
            r5.<init>()     // Catch:{ Throwable -> 0x007f }
        L_0x0049:
            java.lang.String r7 = ":"
            java.lang.String[] r6 = r6.split(r7)     // Catch:{ Throwable -> 0x007f }
            if (r6 == 0) goto L_0x0021
            int r7 = r6.length     // Catch:{ Throwable -> 0x007f }
            r8 = 1
            if (r7 <= r8) goto L_0x0021
            r7 = 0
            if (r5 != 0) goto L_0x0068
            r7 = r6[r7]     // Catch:{ Throwable -> 0x007f }
            java.lang.String r7 = r7.trim()     // Catch:{ Throwable -> 0x007f }
            r6 = r6[r8]     // Catch:{ Throwable -> 0x007f }
            java.lang.String r6 = r6.trim()     // Catch:{ Throwable -> 0x007f }
            r0.put(r7, r6)     // Catch:{ Throwable -> 0x007f }
            goto L_0x0021
        L_0x0068:
            r7 = r6[r7]     // Catch:{ Throwable -> 0x007f }
            java.lang.String r7 = r7.trim()     // Catch:{ Throwable -> 0x007f }
            r6 = r6[r8]     // Catch:{ Throwable -> 0x007f }
            java.lang.String r6 = r6.trim()     // Catch:{ Throwable -> 0x007f }
            r5.put(r7, r6)     // Catch:{ Throwable -> 0x007f }
            goto L_0x0021
        L_0x0078:
            r2.close()     // Catch:{ Throwable -> 0x007f }
            r1.close()     // Catch:{ Throwable -> 0x007f }
            goto L_0x0087
        L_0x007f:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
        L_0x0087:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getCPUInfo():java.util.HashMap");
    }

    public ArrayList<ArrayList<String>> getTTYDriversInfo() {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        if (Build.VERSION.SDK_INT < 28) {
            try {
                FileReader fileReader = new FileReader(Strings.getString(52));
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (!TextUtils.isEmpty(readLine)) {
                        String[] split = readLine.trim().split(" ");
                        if (split.length > 1) {
                            ArrayList arrayList2 = new ArrayList();
                            for (String str : split) {
                                if (!TextUtils.isEmpty(str)) {
                                    arrayList2.add(str.trim());
                                }
                            }
                            arrayList.add(arrayList2);
                        }
                    }
                }
                bufferedReader.close();
                fileReader.close();
            } catch (Throwable th) {
                MobLog.getInstance().mo29768d(th.getMessage(), new Object[0]);
            }
        }
        return arrayList;
    }

    public void getBatteryState(final ReflectHelper.ReflectRunnable<HashMap<String, Object>, Void> reflectRunnable) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            C24745 r1 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    HashMap hashMap = new HashMap();
                    for (String str : intent.getExtras().keySet()) {
                        hashMap.put(str, intent.getExtras().get(str));
                    }
                    ReflectHelper.ReflectRunnable reflectRunnable = reflectRunnable;
                    if (reflectRunnable != null) {
                        reflectRunnable.run(hashMap);
                    }
                    try {
                        ReflectHelper.invokeInstanceMethod(context, "unregisterReceiver", new Object[]{this}, new Class[]{BroadcastReceiver.class});
                    } catch (Throwable unused) {
                    }
                }
            };
            ReflectHelper.invokeInstanceMethod(this.context, "registerReceiver", new Object[]{r1, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class});
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            if (reflectRunnable != null) {
                reflectRunnable.run(null);
            }
        }
    }

    public int getScreenBrightness() {
        try {
            return Settings.System.getInt(this.context.getContentResolver(), "screen_brightness");
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return -1;
        }
    }

    public int getScreenBrightnessMode() {
        try {
            return Settings.System.getInt(this.context.getContentResolver(), "screen_brightness_mode");
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return -1;
        }
    }

    public String getQemuKernel() {
        try {
            return (String) ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(Strings.getString(9)), Strings.getString(10), Strings.getString(53), "0");
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return "0";
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x004e */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0054 A[Catch:{ Throwable -> 0x0060 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, java.lang.Long>> getSizeInfo() {
        /*
            r15 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "data"
            java.lang.String r2 = "sdcard"
            java.lang.String[] r3 = new java.lang.String[]{r2, r1}
            int r4 = r3.length
            r5 = 0
        L_0x000f:
            java.lang.String r6 = "total"
            java.lang.String r7 = "free"
            java.lang.String r8 = "available"
            if (r5 >= r4) goto L_0x003b
            r9 = r3[r5]
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            r11 = -1
            java.lang.Long r13 = java.lang.Long.valueOf(r11)
            r10.put(r8, r13)
            java.lang.Long r8 = java.lang.Long.valueOf(r11)
            r10.put(r7, r8)
            java.lang.Long r7 = java.lang.Long.valueOf(r11)
            r10.put(r6, r7)
            r0.put(r9, r10)
            int r5 = r5 + 1
            goto L_0x000f
        L_0x003b:
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.lang.String r4 = r15.getSdcardPath()     // Catch:{ Throwable -> 0x004e }
            if (r4 == 0) goto L_0x004e
            android.os.StatFs r5 = new android.os.StatFs     // Catch:{ Throwable -> 0x004e }
            r5.<init>(r4)     // Catch:{ Throwable -> 0x004e }
            r3.put(r2, r5)     // Catch:{ Throwable -> 0x004e }
        L_0x004e:
            java.io.File r2 = android.os.Environment.getDataDirectory()     // Catch:{ Throwable -> 0x0060 }
            if (r2 == 0) goto L_0x0060
            android.os.StatFs r4 = new android.os.StatFs     // Catch:{ Throwable -> 0x0060 }
            java.lang.String r2 = r2.getPath()     // Catch:{ Throwable -> 0x0060 }
            r4.<init>(r2)     // Catch:{ Throwable -> 0x0060 }
            r3.put(r1, r4)     // Catch:{ Throwable -> 0x0060 }
        L_0x0060:
            java.util.Set r1 = r3.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0068:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00e1
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getValue()
            android.os.StatFs r3 = (android.os.StatFs) r3
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 18
            if (r4 > r5) goto L_0x00a3
            int r4 = r3.getAvailableBlocks()
            long r4 = (long) r4
            int r9 = r3.getBlockSize()
            long r9 = (long) r9
            long r4 = r4 * r9
            int r9 = r3.getFreeBlocks()
            long r9 = (long) r9
            int r11 = r3.getBlockSize()
            long r11 = (long) r11
            long r9 = r9 * r11
            int r11 = r3.getBlockCount()
            long r11 = (long) r11
            int r3 = r3.getBlockSize()
            long r13 = (long) r3
            goto L_0x00bf
        L_0x00a3:
            long r4 = r3.getAvailableBlocksLong()
            long r9 = r3.getBlockSizeLong()
            long r4 = r4 * r9
            long r9 = r3.getFreeBlocksLong()
            long r11 = r3.getBlockSizeLong()
            long r9 = r9 * r11
            long r11 = r3.getBlockCountLong()
            long r13 = r3.getBlockSizeLong()
        L_0x00bf:
            long r11 = r11 * r13
            java.lang.Object r2 = r2.getKey()
            java.lang.Object r2 = r0.get(r2)
            java.util.HashMap r2 = (java.util.HashMap) r2
            java.lang.Long r3 = java.lang.Long.valueOf(r4)
            r2.put(r8, r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r9)
            r2.put(r7, r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r11)
            r2.put(r6, r3)
            goto L_0x0068
        L_0x00e1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getSizeInfo():java.util.HashMap");
    }

    public HashMap<String, Long> getMemoryInfo() {
        HashMap<String, Long> hashMap = new HashMap<>();
        hashMap.put("available", -1L);
        hashMap.put(Config.EXCEPTION_MEMORY_TOTAL, -1L);
        hashMap.put("isLow", -1L);
        hashMap.put("threshold", -1L);
        try {
            Object systemServiceSafe = getSystemServiceSafe(Strings.getString(30));
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(64), memoryInfo);
            hashMap.put("available", Long.valueOf(memoryInfo.availMem));
            hashMap.put(Config.EXCEPTION_MEMORY_TOTAL, Long.valueOf(memoryInfo.totalMem));
            hashMap.put("isLow", Long.valueOf(memoryInfo.lowMemory ? 1 : 0));
            hashMap.put("threshold", Long.valueOf(memoryInfo.threshold));
        } catch (Throwable unused) {
        }
        return hashMap;
    }

    public Bitmap getWallPaper() {
        int i;
        try {
            WallpaperManager instance = WallpaperManager.getInstance(this.context);
            Drawable peekDrawable = instance.peekDrawable();
            if (peekDrawable == null && (peekDrawable = instance.getWallpaperInfo().loadThumbnail(this.context.getPackageManager())) == null) {
                return null;
            }
            if (peekDrawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) peekDrawable).getBitmap();
            }
            int i2 = 1;
            if (peekDrawable.getIntrinsicWidth() <= 0 || peekDrawable.getIntrinsicHeight() <= 0) {
                i = 1;
            } else {
                i2 = peekDrawable.getIntrinsicWidth();
                i = peekDrawable.getIntrinsicHeight();
            }
            Bitmap createBitmap = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            peekDrawable.setBounds(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
            peekDrawable.draw(canvas);
            return createBitmap;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public String getMIUIVersion() {
        if (!TextUtils.isEmpty(this.bufUiVersion)) {
            return this.bufUiVersion;
        }
        String systemProperties = getSystemProperties(Strings.getString(65));
        if (TextUtils.isEmpty(systemProperties)) {
            systemProperties = getSystemProperties(Strings.getString(66));
        }
        if (TextUtils.isEmpty(systemProperties)) {
            systemProperties = getSystemProperties(Strings.getString(67));
        }
        if (TextUtils.isEmpty(systemProperties)) {
            systemProperties = getSystemProperties(Strings.getString(135));
        }
        if (TextUtils.isEmpty(systemProperties)) {
            systemProperties = getSystemProperties(Strings.getString(136));
        }
        if (TextUtils.isEmpty(systemProperties)) {
            systemProperties = getSystemProperties(Strings.getString(69));
        }
        this.bufUiVersion = systemProperties;
        return systemProperties;
    }

    public boolean isFreeMeOS() {
        try {
            String systemProperties = getSystemProperties("ro.build.freeme.label");
            return !TextUtils.isEmpty(systemProperties) && systemProperties.equalsIgnoreCase("FREEMEOS");
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean isSSUIOS() {
        try {
            String systemProperties = getSystemProperties("ro.ssui.product");
            return !TextUtils.isEmpty(systemProperties) && !systemProperties.equalsIgnoreCase("unknown");
        } catch (Throwable unused) {
            return false;
        }
    }

    public int getAlbumCount() {
        Cursor query;
        try {
            if (!checkPermission(Permission.READ_EXTERNAL_STORAGE) || (query = this.context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[]) null, "bucket_display_name like ? or bucket_display_name like ? or bucket_display_name like ?", new String[]{"%Camera%", "%相机%", "%DCIM%"}, "datetaken")) == null) {
                return 0;
            }
            int count = query.getCount();
            query.close();
            return count;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public String getWAbcd(int i) {
        String[] split;
        HashMap<String, Object> mapFromOtherPlace;
        try {
            if (!getSdcardState()) {
                return null;
            }
            String sdcardPath = getSdcardPath();
            if (!TextUtils.isEmpty(sdcardPath) && (split = Strings.getString(75).split(",")) != null && split.length > 0) {
                for (String str : split) {
                    if (str != null) {
                        String trim = str.trim();
                        if (!TextUtils.isEmpty(trim)) {
                            File file = new File(sdcardPath + trim, ".mn_" + Strings.getString(137));
                            if (file.exists() && file.isFile() && (mapFromOtherPlace = getMapFromOtherPlace(file.getPath())) != null) {
                                String str2 = (String) mapFromOtherPlace.get(String.valueOf(i));
                                if (!TextUtils.isEmpty(str2)) {
                                    return str2.trim();
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public void saveWabcd(String str, int i) {
        String[] split;
        try {
            if (getSdcardState()) {
                String sdcardPath = getSdcardPath();
                if (!TextUtils.isEmpty(sdcardPath) && (split = Strings.getString(75).split(",")) != null && split.length > 0) {
                    for (String str2 : split) {
                        if (str2 != null) {
                            String trim = str2.trim();
                            if (!TextUtils.isEmpty(trim)) {
                                File file = new File(sdcardPath + trim, ".mn_" + Strings.getString(137));
                                HashMap<String, Object> hashMap = null;
                                if (file.exists() && file.isFile()) {
                                    hashMap = getMapFromOtherPlace(file.getPath());
                                }
                                if (hashMap == null) {
                                    hashMap = new HashMap<>();
                                }
                                hashMap.put(String.valueOf(i), str);
                                hashMap.put(Strings.getString(78), Data.MD5(getSortWabcd(hashMap) + Strings.getString(77)));
                                ResHelper.saveObjectToFile(file.getPath(), Data.AES128Encode(Strings.getString(76), new Hashon().fromHashMap(hashMap)));
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public void removeWABCD() {
        String[] split;
        try {
            if (getSdcardState()) {
                String sdcardPath = getSdcardPath();
                if (!TextUtils.isEmpty(sdcardPath) && (split = Strings.getString(75).split(",")) != null && split.length > 0) {
                    for (String str : split) {
                        if (str != null) {
                            String trim = str.trim();
                            if (!TextUtils.isEmpty(trim)) {
                                new File(sdcardPath + trim, ".mn_" + Strings.getString(137)).delete();
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    private HashMap<String, Object> getMapFromOtherPlace(String str) {
        try {
            String AES128Decode = Data.AES128Decode(Strings.getString(76), (byte[]) ResHelper.readObjectFromFile(str));
            if (TextUtils.isEmpty(AES128Decode)) {
                return null;
            }
            HashMap<String, Object> fromJson = new Hashon().fromJson(AES128Decode);
            String str2 = (String) fromJson.remove(Strings.getString(78));
            String sortWabcd = getSortWabcd(fromJson);
            String MD5 = Data.MD5(sortWabcd + Strings.getString(77));
            if (str2 == null || str2.equals(MD5)) {
                return fromJson;
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    private String getSortWabcd(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String str = (String) hashMap.get("0");
        if (str != null) {
            sb.append(str);
        }
        String str2 = (String) hashMap.get("1");
        if (str2 != null) {
            sb.append(str2);
        }
        String str3 = (String) hashMap.get("2");
        if (str3 != null) {
            sb.append(str3);
        }
        String str4 = (String) hashMap.get("3");
        if (str4 != null) {
            sb.append(str4);
        }
        return sb.toString();
    }

    public ArrayList<HashMap<String, Object>> getArpList() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String trim = readLine.trim();
                if (!trim.toUpperCase(Locale.US).contains("IP") && trim.length() >= 63) {
                    String trim2 = trim.substring(41, 63).trim();
                    if (!trim2.startsWith("00:00:00:00:00:00")) {
                        String trim3 = trim.substring(0, 17).trim();
                        String trim4 = trim.substring(29, 32).trim();
                        HashMap hashMap = new HashMap();
                        hashMap.put("ip", trim3);
                        hashMap.put("flag", trim4);
                        hashMap.put("mac", trim2);
                        arrayList.add(hashMap);
                    }
                }
            }
            bufferedReader.close();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        com.mob.tools.MobLog.getInstance().mo29769d(r1);
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r2 = new java.io.BufferedReader(new java.io.FileReader("/proc/" + android.os.Process.myPid() + "/maps"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r3 = r2.readLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007f, code lost:
        if (r3 == null) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0083, code lost:
        r1 = r3.toLowerCase().contains("xposed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0092, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0093, code lost:
        com.mob.tools.MobLog.getInstance().mo29769d(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009c, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009f, code lost:
        r7 = r2;
        r2 = r1;
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a3, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a5, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        com.mob.tools.MobLog.getInstance().mo29769d(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ad, code lost:
        if (r1 != null) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b3, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b4, code lost:
        com.mob.tools.MobLog.getInstance().mo29769d(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00bb, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00bc, code lost:
        if (r1 != null) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c2, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00c3, code lost:
        com.mob.tools.MobLog.getInstance().mo29769d(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ca, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0011 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004d A[ExcHandler: Throwable (r1v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:16:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00af A[SYNTHETIC, Splitter:B:46:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00be A[SYNTHETIC, Splitter:B:52:0x00be] */
    /* renamed from: cx */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo29988cx() {
        /*
            r8 = this;
            r0 = 0
            r1 = 1
            android.content.Context r2 = r8.context     // Catch:{ Throwable -> 0x0011 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Throwable -> 0x0011 }
            java.lang.String r3 = "de.robv.android.xposed.installer"
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r0)     // Catch:{ Throwable -> 0x0011 }
            if (r2 == 0) goto L_0x0011
            return r1
        L_0x0011:
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ Throwable -> 0x0019 }
            java.lang.String r3 = "test"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0019 }
            throw r2     // Catch:{ Throwable -> 0x0019 }
        L_0x0019:
            r2 = move-exception
            java.lang.StackTraceElement[] r2 = r2.getStackTrace()
            int r3 = r2.length
            r4 = 0
        L_0x0020:
            java.lang.String r5 = "de.robv.android.xposed.XposedBridge"
            if (r4 >= r3) goto L_0x0034
            r6 = r2[r4]
            java.lang.String r6 = r6.getClassName()
            boolean r5 = r6.contains(r5)
            if (r5 == 0) goto L_0x0031
            return r1
        L_0x0031:
            int r4 = r4 + 1
            goto L_0x0020
        L_0x0034:
            java.lang.ClassLoader r2 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ IllegalAccessException | InstantiationException -> 0x00cb, Throwable -> 0x004d }
            java.lang.String r3 = "de.robv.android.xposed.XposedHelpers"
            java.lang.Class r2 = r2.loadClass(r3)     // Catch:{ IllegalAccessException | InstantiationException -> 0x00cb, Throwable -> 0x004d }
            r2.newInstance()     // Catch:{ IllegalAccessException | InstantiationException -> 0x00cb, Throwable -> 0x004d }
            java.lang.ClassLoader r2 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ IllegalAccessException | InstantiationException -> 0x004c, Throwable -> 0x004d }
            java.lang.Class r2 = r2.loadClass(r5)     // Catch:{ IllegalAccessException | InstantiationException -> 0x004c, Throwable -> 0x004d }
            r2.newInstance()     // Catch:{ IllegalAccessException | InstantiationException -> 0x004c, Throwable -> 0x004d }
        L_0x004c:
            return r1
        L_0x004d:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a5 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00a5 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a5 }
            r4.<init>()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r5 = "/proc/"
            r4.append(r5)     // Catch:{ Throwable -> 0x00a5 }
            int r5 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x00a5 }
            r4.append(r5)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r5 = "/maps"
            r4.append(r5)     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x00a5 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00a5 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a5 }
            r1 = 0
        L_0x007b:
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x009e, all -> 0x009b }
            if (r3 == 0) goto L_0x008e
            if (r1 != 0) goto L_0x008e
            java.lang.String r1 = r3.toLowerCase()     // Catch:{ Throwable -> 0x009e, all -> 0x009b }
            java.lang.String r3 = "xposed"
            boolean r1 = r1.contains(r3)     // Catch:{ Throwable -> 0x009e, all -> 0x009b }
            goto L_0x007b
        L_0x008e:
            r2.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x009a
        L_0x0092:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r0)
        L_0x009a:
            return r1
        L_0x009b:
            r0 = move-exception
            r1 = r2
            goto L_0x00bc
        L_0x009e:
            r1 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x00a6
        L_0x00a3:
            r0 = move-exception
            goto L_0x00bc
        L_0x00a5:
            r2 = move-exception
        L_0x00a6:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x00a3 }
            r3.mo29769d(r2)     // Catch:{ all -> 0x00a3 }
            if (r1 == 0) goto L_0x00bb
            r1.close()     // Catch:{ IOException -> 0x00b3 }
            goto L_0x00bb
        L_0x00b3:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
        L_0x00bb:
            return r0
        L_0x00bc:
            if (r1 == 0) goto L_0x00ca
            r1.close()     // Catch:{ IOException -> 0x00c2 }
            goto L_0x00ca
        L_0x00c2:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
        L_0x00ca:
            throw r0
        L_0x00cb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.mo29988cx():boolean");
    }

    public HashMap<String, Object> getIInfo() {
        return getIInfo(false);
    }

    public HashMap<String, Object> getIInfo(boolean z) {
        try {
            Object systemServiceSafe = getSystemServiceSafe("phone");
            if (systemServiceSafe == null) {
                return null;
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            int i = 83;
            int i2 = 84;
            hashMap.put(Strings.getString(83), invokeInstanceMethod(systemServiceSafe, Strings.getString(84), new Object[0]));
            hashMap.put(Strings.getString(85), invokeInstanceMethod(systemServiceSafe, Strings.getString(86), new Object[0]));
            hashMap.put(Strings.getString(87), invokeInstanceMethod(systemServiceSafe, Strings.getString(88), new Object[0]));
            if (Build.VERSION.SDK_INT >= 23) {
                hashMap.put(Strings.getString(89), invokeInstanceMethod(systemServiceSafe, Strings.getString(90), new Object[0]));
            }
            if (!checkPermission(Permission.READ_PHONE_STATE)) {
                return hashMap;
            }
            if (Build.VERSION.SDK_INT < 29) {
                if (Build.VERSION.SDK_INT >= 26) {
                    hashMap.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(92), new Object[0]));
                    hashMap.put(Strings.getString(93), invokeInstanceMethod(systemServiceSafe, Strings.getString(94), new Object[0]));
                } else {
                    hashMap.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(95), new Object[0]));
                }
                hashMap.put(Strings.getString(96), invokeInstanceMethod(systemServiceSafe, Strings.getString(25), new Object[0]));
                hashMap.put(Strings.getString(99), invokeInstanceMethod(systemServiceSafe, Strings.getString(14), new Object[0]));
            }
            if (Build.VERSION.SDK_INT >= 24) {
                hashMap.put(Strings.getString(100), Integer.valueOf(getInstance(this.context).getDataNtType()));
            }
            if ("-1".equals(this.swVer) || !isSensitiveDevice()) {
                this.swVer = (String) invokeInstanceMethod(systemServiceSafe, Strings.getString(98), new Object[0]);
            }
            if (!"-1".equals(this.swVer) && !TextUtils.isEmpty(this.swVer)) {
                hashMap.put(Strings.getString(97), this.swVer);
            }
            if (Build.VERSION.SDK_INT >= 22) {
                Object systemServiceSafe2 = getSystemServiceSafe(Strings.getString(114));
                if (this.sActCnt == -1 || !isSensitiveDevice()) {
                    this.sActCnt = ((Integer) invokeInstanceMethod(systemServiceSafe2, Strings.getString(103), new Object[0])).intValue();
                }
                if (this.sActCnt != -1) {
                    hashMap.put(Strings.getString(102), Integer.valueOf(this.sActCnt));
                }
                if (this.sActList == null || !isSensitiveDevice()) {
                    this.sActList = (List) invokeInstanceMethod(systemServiceSafe2, Strings.getString(104), new Object[0]);
                }
                if (this.sActList != null) {
                    int size = this.sActList.size();
                    ArrayList arrayList = new ArrayList();
                    int i3 = 0;
                    while (i3 < size) {
                        Object obj = this.sActList.get(i3);
                        HashMap hashMap2 = new HashMap();
                        int intValue = ((Integer) invokeInstanceMethod(obj, Strings.getString(105), new Object[0])).intValue();
                        hashMap2.put(Strings.getString(106), invokeInstanceMethod(obj, Strings.getString(107), new Object[0]));
                        if (z) {
                            hashMap2.put(Strings.getString(108), invokeInstanceMethod(obj, Strings.getString(109), new Object[0]));
                        }
                        hashMap2.put(Strings.getString(110), invokeInstanceMethod(obj, Strings.getString(111), new Object[0]));
                        int intValue2 = ((Integer) invokeInstanceMethod(obj, Strings.getString(112), new Object[0])).intValue();
                        hashMap2.put(Strings.getString(i), invokeInstanceMethod(systemServiceSafe, Strings.getString(i2), new Object[]{Integer.valueOf(intValue2)}, new Class[]{Integer.TYPE}));
                        if (Build.VERSION.SDK_INT < 29) {
                            if (Build.VERSION.SDK_INT >= 26) {
                                hashMap2.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(92), new Object[]{Integer.valueOf(intValue2)}, new Class[]{Integer.TYPE}));
                                hashMap2.put(Strings.getString(93), invokeInstanceMethod(systemServiceSafe, Strings.getString(94), new Object[]{Integer.valueOf(intValue2)}, new Class[]{Integer.TYPE}));
                            } else if (Build.VERSION.SDK_INT >= 23) {
                                hashMap2.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(95), new Object[]{Integer.valueOf(intValue2)}, new Class[]{Integer.TYPE}));
                            } else {
                                hashMap2.put(Strings.getString(91), invokeInstanceMethod(systemServiceSafe, Strings.getString(95), new Object[0]));
                            }
                            hashMap2.put(Strings.getString(96), invokeInstanceMethod(systemServiceSafe, Strings.getString(25), new Object[]{Integer.valueOf(intValue)}, new Class[]{Integer.TYPE}));
                            hashMap2.put(Strings.getString(99), invokeInstanceMethod(systemServiceSafe, Strings.getString(14), new Object[]{Integer.valueOf(intValue)}, new Class[]{Integer.TYPE}));
                        }
                        arrayList.add(hashMap2);
                        i3++;
                        i = 83;
                        i2 = 84;
                    }
                    hashMap.put(Strings.getString(113), arrayList);
                }
            }
            return hashMap;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public <T> T invokeInstanceMethod(Object obj, String str, Object... objArr) {
        try {
            return ReflectHelper.invokeInstanceMethod(obj, str, objArr);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public <T> T invokeInstanceMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) {
        try {
            return ReflectHelper.invokeInstanceMethod(obj, str, objArr, clsArr);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public boolean checkPad() {
        return (this.context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public boolean checkADBModel(int i) {
        if (i == 0) {
            return false;
        }
        if (i == 1) {
            return usbEnable();
        }
        if (i == 16) {
            return devEnable();
        }
        if (i != 17) {
            return i == 273 && usbEnable() && devEnable();
        }
        if (usbEnable() || devEnable()) {
            return true;
        }
        return false;
    }

    public boolean usbEnable() {
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                if (Settings.Secure.getInt(this.context.getContentResolver(), "adb_enabled", 0) <= 0) {
                    return false;
                }
            } else if (Settings.Secure.getInt(this.context.getContentResolver(), "adb_enabled", 0) <= 0) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean devEnable() {
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                if (Settings.Secure.getInt(this.context.getContentResolver(), "development_settings_enabled", 0) <= 0) {
                    return false;
                }
            } else if (Settings.Secure.getInt(this.context.getContentResolver(), "development_settings_enabled", 0) <= 0) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean checkUA() {
        try {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
            if (((Intent) ReflectHelper.invokeInstanceMethod(this.context, "registerReceiver", new Object[]{null, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class})).getIntExtra("plugged", -1) == 2) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
    }

    public boolean vpn() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return false;
            }
            Iterator<T> it = Collections.list(networkInterfaces).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp()) {
                    if (networkInterface.getInterfaceAddresses().size() != 0) {
                        if ("tun0".equals(networkInterface.getName()) || "ppp0".equals(networkInterface.getName())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
    }

    public boolean debugable() {
        try {
            if ((this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 1).applicationInfo.flags & 2) != 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
    }

    public boolean isWifiProxy() {
        int i;
        String str;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                str = System.getProperty("http.proxyHost");
                String property = System.getProperty("http.proxyPort");
                if (property == null) {
                    property = "-1";
                }
                try {
                    i = Integer.parseInt(property);
                } catch (Throwable unused) {
                    i = -1;
                }
            } else {
                str = Proxy.getHost(this.context);
                i = Proxy.getPort(this.context);
            }
            if (TextUtils.isEmpty(str) || i == -1) {
                return false;
            }
            return true;
        } catch (Throwable unused2) {
            return false;
        }
    }

    public boolean isScopedStorage() {
        boolean z = Build.VERSION.SDK_INT >= 29;
        boolean z2 = this.context.getApplicationInfo().targetSdkVersion >= 29;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    public ArrayList<HashMap<String, Object>> getBondedBluetooth() {
        try {
            return BHelper.getInstance(this.context).getBondedDevice();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return new ArrayList<>();
        }
    }

    public void scanBtList(int i, final BtScanCallback btScanCallback) {
        try {
            BHelper.getInstance(this.context).findLEAndClassic(i, new BHelper.BScanCallback() {
                public void onScan(ArrayList<HashMap<String, Object>> arrayList) {
                    BtScanCallback btScanCallback = btScanCallback;
                    if (btScanCallback != null) {
                        btScanCallback.onScan(arrayList);
                    }
                }
            });
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public void unRegisterBtScanReceiver() {
        try {
            BHelper.getInstance(this.context).unRegisterBScanReceiver();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public void registerBtWatcher(String str, final BtWatcher btWatcher) {
        if (btWatcher != null) {
            try {
                BHelper.getInstance(this.context).registerBOperationReceiver(str, new BHelper.BOperationCallback() {
                    /* access modifiers changed from: protected */
                    public void onEnabled() {
                        btWatcher.onBtEnabled();
                    }

                    /* access modifiers changed from: protected */
                    public void onDisabled() {
                        btWatcher.onBtDisabled();
                    }

                    /* access modifiers changed from: protected */
                    public void onConnectionChanged(boolean z, HashMap<String, Object> hashMap) {
                        btWatcher.onBtConnectionChanged(z, hashMap);
                    }

                    /* access modifiers changed from: protected */
                    public void onDeviceConnected(HashMap<String, Object> hashMap) {
                        btWatcher.onDeviceConnected(hashMap);
                    }

                    /* access modifiers changed from: protected */
                    public void onDeviceDisconnected(HashMap<String, Object> hashMap) {
                        btWatcher.onDeviceDisconnected(hashMap);
                    }
                });
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        }
    }

    public void unRegisterBtWatcher(String str) {
        try {
            BHelper.getInstance(this.context).unRegisterBOperationReceiver(str);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public ArrayList<HashMap<String, String>> getCamResolution() {
        CameraManager cameraManager;
        String[] strArr;
        Size size;
        try {
            if (!checkPermission(Permission.CAMERA) || Build.VERSION.SDK_INT < 21 || (cameraManager = (CameraManager) this.context.getSystemService("camera")) == null || (strArr = (String[]) ReflectHelper.invokeInstanceMethod(cameraManager, Strings.getString(126), new Object[0])) == null || strArr.length <= 0) {
                return null;
            }
            ArrayList<HashMap<String, String>> arrayList = new ArrayList<>(strArr.length);
            for (String str : strArr) {
                if (str != null) {
                    CameraCharacteristics cameraCharacteristics = (CameraCharacteristics) ReflectHelper.invokeInstanceMethod(cameraManager, Strings.getString(127), str);
                    if (!(cameraCharacteristics == null || (size = (Size) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE)) == null)) {
                        HashMap hashMap = new HashMap();
                        hashMap.put(ViewHierarchyConstants.DIMENSION_WIDTH_KEY, String.valueOf(size.getWidth()));
                        hashMap.put(ViewHierarchyConstants.DIMENSION_HEIGHT_KEY, String.valueOf(size.getHeight()));
                        arrayList.add(hashMap);
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public String getTimezone() {
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            Settings.System.getConfiguration(this.context.getContentResolver(), configuration);
            Locale locale = configuration.locale;
            if (locale == null) {
                locale = Locale.getDefault();
            }
            Calendar instance = Calendar.getInstance(locale);
            if (instance != null) {
                return instance.getTimeZone().getID();
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:4|(2:5|6)|7|(2:9|10)|11|13|14|15|16|(1:21)(1:20)|22|23|24|25|26|28|29|30|32|33) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003a */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004e A[Catch:{ Throwable -> 0x0085 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0050 A[Catch:{ Throwable -> 0x0085 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.lang.Object> getSupport() {
        /*
            r4 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            android.content.Context r1 = r4.context     // Catch:{ Throwable -> 0x0085 }
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch:{ Throwable -> 0x0085 }
            if (r1 == 0) goto L_0x0085
            java.lang.String r2 = "mobile"
            java.lang.String r3 = "android.hardware.telephony"
            boolean r3 = r1.hasSystemFeature(r3)     // Catch:{ Throwable -> 0x001c }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Throwable -> 0x001c }
            r0.put(r2, r3)     // Catch:{ Throwable -> 0x001c }
        L_0x001c:
            java.lang.String r2 = "wifi"
            java.lang.String r3 = "android.hardware.wifi"
            boolean r3 = r1.hasSystemFeature(r3)     // Catch:{ Throwable -> 0x002b }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Throwable -> 0x002b }
            r0.put(r2, r3)     // Catch:{ Throwable -> 0x002b }
        L_0x002b:
            java.lang.String r2 = "gps"
            java.lang.String r3 = "android.hardware.location.gps"
            boolean r3 = r1.hasSystemFeature(r3)     // Catch:{ Throwable -> 0x003a }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Throwable -> 0x003a }
            r0.put(r2, r3)     // Catch:{ Throwable -> 0x003a }
        L_0x003a:
            android.content.Context r2 = r4.context     // Catch:{ Throwable -> 0x0085 }
            java.lang.String r3 = "phone"
            java.lang.Object r2 = r2.getSystemService(r3)     // Catch:{ Throwable -> 0x0085 }
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch:{ Throwable -> 0x0085 }
            java.lang.String r3 = "telephone"
            if (r2 == 0) goto L_0x0050
            int r2 = r2.getPhoneType()     // Catch:{ Throwable -> 0x0085 }
            if (r2 == 0) goto L_0x0050
            r2 = 1
            goto L_0x0051
        L_0x0050:
            r2 = 0
        L_0x0051:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Throwable -> 0x0085 }
            r0.put(r3, r2)     // Catch:{ Throwable -> 0x0085 }
            java.lang.String r2 = "nfc"
            java.lang.String r3 = "android.hardware.nfc"
            boolean r3 = r1.hasSystemFeature(r3)     // Catch:{ Throwable -> 0x0067 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Throwable -> 0x0067 }
            r0.put(r2, r3)     // Catch:{ Throwable -> 0x0067 }
        L_0x0067:
            java.lang.String r2 = "bluetooth"
            java.lang.String r3 = "android.hardware.bluetooth"
            boolean r3 = r1.hasSystemFeature(r3)     // Catch:{ Throwable -> 0x0076 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Throwable -> 0x0076 }
            r0.put(r2, r3)     // Catch:{ Throwable -> 0x0076 }
        L_0x0076:
            java.lang.String r2 = "otg"
            java.lang.String r3 = "android.hardware.usb.host"
            boolean r1 = r1.hasSystemFeature(r3)     // Catch:{ Throwable -> 0x0085 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Throwable -> 0x0085 }
            r0.put(r2, r1)     // Catch:{ Throwable -> 0x0085 }
        L_0x0085:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getSupport():java.util.HashMap");
    }

    public HashMap<String, String> getCPUFreq() {
        HashMap<String, String> hashMap = new HashMap<>();
        String readFile = readFile("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
        if (!TextUtils.isEmpty(readFile)) {
            hashMap.put("currentCpuHz", readFile);
        }
        String readFile2 = readFile("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
        if (!TextUtils.isEmpty(readFile2)) {
            hashMap.put("minCpuHz", readFile2);
        }
        String readFile3 = readFile("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
        if (!TextUtils.isEmpty(readFile3)) {
            hashMap.put("maxCpuHz", readFile3);
        }
        return hashMap;
    }

    public String readFile(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(str);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String trim = readLine.trim();
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(trim);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return sb.toString();
    }

    public String getCPUType() {
        try {
            return Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : Build.SUPPORTED_ABIS[0];
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public String getFlavor() {
        try {
            return getSystemProperties(Strings.getString(119));
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089 A[SYNTHETIC, Splitter:B:23:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0099 A[SYNTHETIC, Splitter:B:29:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b2 A[SYNTHETIC, Splitter:B:38:0x00b2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.lang.Object> getTraffic() {
        /*
            r11 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 1
            r3 = 29
            if (r1 >= r3) goto L_0x00a6
            r1 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x007c, all -> 0x0079 }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x007c, all -> 0x0079 }
            r5 = 120(0x78, float:1.68E-43)
            java.lang.String r5 = com.mob.tools.utils.Strings.getString(r5)     // Catch:{ Throwable -> 0x007c, all -> 0x0079 }
            r4.<init>(r5)     // Catch:{ Throwable -> 0x007c, all -> 0x0079 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x007c, all -> 0x0079 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0077 }
            r1.<init>()     // Catch:{ Throwable -> 0x0077 }
        L_0x0022:
            java.lang.String r4 = r3.readLine()     // Catch:{ Throwable -> 0x0077 }
            if (r4 == 0) goto L_0x0031
            r1.append(r4)     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r4 = "\n"
            r1.append(r4)     // Catch:{ Throwable -> 0x0077 }
            goto L_0x0022
        L_0x0031:
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0077 }
            if (r1 == 0) goto L_0x0073
            r4 = 2
            long[] r5 = new long[r4]     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r6 = "wlan0:"
            r11.getTrafficBytes(r1, r6, r5)     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r6 = "downFlowWifi"
            r7 = 0
            r8 = r5[r7]     // Catch:{ Throwable -> 0x0077 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ Throwable -> 0x0077 }
            r0.put(r6, r8)     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r6 = "upwardFlowWifi"
            r8 = r5[r2]     // Catch:{ Throwable -> 0x0077 }
            java.lang.Long r5 = java.lang.Long.valueOf(r8)     // Catch:{ Throwable -> 0x0077 }
            r0.put(r6, r5)     // Catch:{ Throwable -> 0x0077 }
            long[] r4 = new long[r4]     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r5 = "rmnet0:"
            r11.getTrafficBytes(r1, r5, r4)     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r1 = "downFlowCellular"
            r5 = r4[r7]     // Catch:{ Throwable -> 0x0077 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ Throwable -> 0x0077 }
            r0.put(r1, r5)     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r1 = "upwardFlowCellular"
            r5 = r4[r2]     // Catch:{ Throwable -> 0x0077 }
            java.lang.Long r4 = java.lang.Long.valueOf(r5)     // Catch:{ Throwable -> 0x0077 }
            r0.put(r1, r4)     // Catch:{ Throwable -> 0x0077 }
        L_0x0073:
            r3.close()     // Catch:{ Throwable -> 0x008d }
            goto L_0x00a6
        L_0x0077:
            r1 = move-exception
            goto L_0x0080
        L_0x0079:
            r0 = move-exception
            r3 = r1
            goto L_0x0097
        L_0x007c:
            r3 = move-exception
            r10 = r3
            r3 = r1
            r1 = r10
        L_0x0080:
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0096 }
            r4.mo29769d(r1)     // Catch:{ all -> 0x0096 }
            if (r3 == 0) goto L_0x00a6
            r3.close()     // Catch:{ Throwable -> 0x008d }
            goto L_0x00a6
        L_0x008d:
            r1 = move-exception
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()
            r3.mo29769d(r1)
            goto L_0x00a6
        L_0x0096:
            r0 = move-exception
        L_0x0097:
            if (r3 == 0) goto L_0x00a5
            r3.close()     // Catch:{ Throwable -> 0x009d }
            goto L_0x00a5
        L_0x009d:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
        L_0x00a5:
            throw r0
        L_0x00a6:
            android.content.Context r1 = r11.context     // Catch:{ Throwable -> 0x00e2 }
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch:{ Throwable -> 0x00e2 }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00e2 }
            r4 = 24
            if (r3 < r4) goto L_0x00ea
            android.content.Context r3 = r11.context     // Catch:{ Throwable -> 0x00d9 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ Throwable -> 0x00d9 }
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo(r3, r2)     // Catch:{ Throwable -> 0x00d9 }
            int r1 = r1.uid     // Catch:{ Throwable -> 0x00d9 }
            java.lang.String r2 = "upwardFlowApp"
            long r3 = android.net.TrafficStats.getUidTxBytes(r1)     // Catch:{ Throwable -> 0x00d9 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ Throwable -> 0x00d9 }
            r0.put(r2, r3)     // Catch:{ Throwable -> 0x00d9 }
            java.lang.String r2 = "downFlowApp"
            long r3 = android.net.TrafficStats.getUidRxBytes(r1)     // Catch:{ Throwable -> 0x00d9 }
            java.lang.Long r1 = java.lang.Long.valueOf(r3)     // Catch:{ Throwable -> 0x00d9 }
            r0.put(r2, r1)     // Catch:{ Throwable -> 0x00d9 }
            goto L_0x00ea
        L_0x00d9:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00e2 }
            r2.mo29787w((java.lang.Throwable) r1)     // Catch:{ Throwable -> 0x00e2 }
            goto L_0x00ea
        L_0x00e2:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
        L_0x00ea:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getTraffic():java.util.HashMap");
    }

    public String getBaseband() {
        try {
            return getSystemProperties(Strings.getString(116));
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public String getBoardFromSysProperty() {
        try {
            return getSystemProperties(Strings.getString(117));
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public String getBoardPlatform() {
        try {
            return getSystemProperties(Strings.getString(118));
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    private void getTrafficBytes(String str, String str2, long[] jArr) {
        try {
            int indexOf = str.indexOf(str2);
            if (indexOf != -1) {
                Matcher matcher = Pattern.compile(" \\d+ ").matcher(str.substring(indexOf));
                int i = 0;
                while (matcher.find()) {
                    if (i == 0) {
                        jArr[0] = Long.parseLong(matcher.group().trim());
                    } else if (i == 8) {
                        jArr[1] = Long.parseLong(matcher.group().trim());
                        return;
                    }
                    i++;
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public long getElapsedTime() {
        try {
            return SystemClock.elapsedRealtime();
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return 0;
        }
    }

    public int getDataNtType() {
        return NtFetcher.getInstance(this.context).getDtNtType();
    }

    public String getDefaultIMPkg() {
        try {
            String string = Settings.Secure.getString(this.context.getContentResolver(), "default_input_method");
            if (!TextUtils.isEmpty(string)) {
                return string.split(BceConfig.BOS_DELIMITER)[0];
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    public HashMap<String, Object> getDefaultIM() {
        HashMap<String, Object> hashMap;
        Throwable th;
        try {
            String defaultIMPkg = getDefaultIMPkg();
            String appName = getAppName(defaultIMPkg);
            hashMap = new HashMap<>();
            try {
                hashMap.put("name", appName);
                hashMap.put(Config.INPUT_DEF_PKG, defaultIMPkg);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            hashMap = null;
            th = th3;
            MobLog.getInstance().mo29769d(th);
            return hashMap;
        }
        return hashMap;
    }

    public ArrayList<HashMap<String, Object>> getIMList() {
        ArrayList<HashMap<String, Object>> arrayList;
        Throwable th;
        try {
            List<InputMethodInfo> inputMethodList = ((InputMethodManager) getSystemServiceSafe("input_method")).getInputMethodList();
            arrayList = new ArrayList<>();
            try {
                for (InputMethodInfo next : inputMethodList) {
                    if (next != null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("name", next.loadLabel(this.context.getPackageManager()));
                        hashMap.put(Config.INPUT_DEF_PKG, next.getPackageName());
                        arrayList.add(hashMap);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                MobLog.getInstance().mo29769d(th);
                return arrayList;
            }
        } catch (Throwable th3) {
            arrayList = null;
            th = th3;
            MobLog.getInstance().mo29769d(th);
            return arrayList;
        }
        return arrayList;
    }

    public String getBrand() {
        return Build.BRAND;
    }

    public boolean isSmlt() {
        try {
            if (this.isSmlt != null) {
                return this.isSmlt.booleanValue();
            }
            SmltHelper smltHelper = new SmltHelper();
            int i = smltHelper.checkBaseband(this.context) == 1 ? 1 : 0;
            if (smltHelper.checkBoard(this.context) == 1) {
                i++;
            }
            if (i >= 2) {
                Boolean bool = true;
                this.isSmlt = bool;
                return bool.booleanValue();
            }
            if (smltHelper.checkPlatform(this.context) == 1) {
                i++;
            }
            if (i >= 2) {
                Boolean bool2 = true;
                this.isSmlt = bool2;
                return bool2.booleanValue();
            }
            if (smltHelper.checkFlavor(this.context) == 1) {
                i++;
            }
            if (i >= 2) {
                Boolean bool3 = true;
                this.isSmlt = bool3;
                return bool3.booleanValue();
            }
            if (smltHelper.checkCgroup() == 1) {
                i++;
            }
            if (i >= 2) {
                Boolean bool4 = true;
                this.isSmlt = bool4;
                return bool4.booleanValue();
            }
            if (smltHelper.checkBluetooth(this.context) == 1) {
                i++;
            }
            if (i >= 2) {
                Boolean bool5 = true;
                this.isSmlt = bool5;
                return bool5.booleanValue();
            }
            if (smltHelper.checkImei(this.context) == 1) {
                i++;
            }
            if (i >= 2) {
                Boolean bool6 = true;
                this.isSmlt = bool6;
                return bool6.booleanValue();
            }
            if (smltHelper.checkCommonApp(this.context) == 1) {
                i++;
            }
            if (i >= 2) {
                Boolean bool7 = true;
                this.isSmlt = bool7;
                return bool7.booleanValue();
            }
            if (smltHelper.checkCpuInfo() == 1) {
                i++;
            }
            if (i >= 2) {
                Boolean bool8 = true;
                this.isSmlt = bool8;
                return bool8.booleanValue();
            }
            if (smltHelper.checkSensor(this.context) == 1) {
                i++;
            }
            if (i >= 2) {
                Boolean bool9 = true;
                this.isSmlt = bool9;
                return bool9.booleanValue();
            }
            Boolean bool10 = false;
            this.isSmlt = bool10;
            return bool10.booleanValue();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v14 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0051 A[SYNTHETIC, Splitter:B:25:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0061 A[SYNTHETIC, Splitter:B:31:0x0061] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getProcessor() {
        /*
            r6 = this;
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0046 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x0046 }
            r3 = 41
            java.lang.String r3 = com.mob.tools.utils.Strings.getString(r3)     // Catch:{ Throwable -> 0x0046 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0046 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0046 }
            java.lang.String r2 = "Processor\\s*:\\s*(.*)"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)     // Catch:{ Throwable -> 0x003f, all -> 0x003a }
        L_0x0017:
            java.lang.String r3 = r1.readLine()     // Catch:{ Throwable -> 0x003f, all -> 0x003a }
            if (r3 == 0) goto L_0x002d
            java.util.regex.Matcher r3 = r2.matcher(r3)     // Catch:{ Throwable -> 0x003f, all -> 0x003a }
            boolean r4 = r3.matches()     // Catch:{ Throwable -> 0x003f, all -> 0x003a }
            if (r4 == 0) goto L_0x0017
            r4 = 1
            java.lang.String r0 = r3.group(r4)     // Catch:{ Throwable -> 0x003f, all -> 0x003a }
            goto L_0x0017
        L_0x002d:
            r1.close()     // Catch:{ IOException -> 0x0031 }
            goto L_0x005e
        L_0x0031:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
            goto L_0x005e
        L_0x003a:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x005f
        L_0x003f:
            r2 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0048
        L_0x0044:
            r1 = move-exception
            goto L_0x005f
        L_0x0046:
            r2 = move-exception
            r1 = r0
        L_0x0048:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0044 }
            r3.mo29769d(r2)     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x005d
            r0.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x005d
        L_0x0055:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r0)
        L_0x005d:
            r0 = r1
        L_0x005e:
            return r0
        L_0x005f:
            if (r0 == 0) goto L_0x006d
            r0.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x006d
        L_0x0065:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r0)
        L_0x006d:
            goto L_0x006f
        L_0x006e:
            throw r1
        L_0x006f:
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getProcessor():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a5 A[SYNTHETIC, Splitter:B:36:0x00a5] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b4 A[SYNTHETIC, Splitter:B:42:0x00b4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.lang.Object> getDeviceMemUsage() {
        /*
            r8 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1 = 125(0x7d, float:1.75E-43)
            r2 = 0
            java.lang.String r1 = com.mob.tools.utils.Strings.getString(r1)     // Catch:{ Throwable -> 0x009b }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x009b }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x009b }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x009b }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x009b }
        L_0x0016:
            java.lang.String r1 = r3.readLine()     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            if (r1 == 0) goto L_0x008f
            java.lang.String r2 = "\\s+"
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            if (r1 == 0) goto L_0x0016
            int r2 = r1.length     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            r4 = 1
            if (r2 <= r4) goto L_0x0016
            r2 = 0
            r2 = r1[r2]     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            r1 = r1[r4]     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            long r4 = java.lang.Long.parseLong(r1)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            r6 = 1024(0x400, double:5.06E-321)
            long r4 = r4 * r6
            java.lang.String r1 = "MemTotal:"
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            if (r1 == 0) goto L_0x0047
            java.lang.String r1 = "totalMemorySize"
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            goto L_0x0016
        L_0x0047:
            java.lang.String r1 = "MemFree:"
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            if (r1 == 0) goto L_0x0059
            java.lang.String r1 = "freeMemorySize"
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            goto L_0x0016
        L_0x0059:
            java.lang.String r1 = "MemAvailable:"
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            if (r1 == 0) goto L_0x006b
            java.lang.String r1 = "availableMemorySize"
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            goto L_0x0016
        L_0x006b:
            java.lang.String r1 = "Active:"
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            if (r1 == 0) goto L_0x007d
            java.lang.String r1 = "activeMemorySize"
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            goto L_0x0016
        L_0x007d:
            java.lang.String r1 = "Inactive:"
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            if (r1 == 0) goto L_0x0016
            java.lang.String r1 = "inactiveMemorySize"
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x0095, all -> 0x0093 }
            goto L_0x0016
        L_0x008f:
            r3.close()     // Catch:{ Throwable -> 0x00a9 }
            goto L_0x00b1
        L_0x0093:
            r0 = move-exception
            goto L_0x00b2
        L_0x0095:
            r1 = move-exception
            r2 = r3
            goto L_0x009c
        L_0x0098:
            r0 = move-exception
            r3 = r2
            goto L_0x00b2
        L_0x009b:
            r1 = move-exception
        L_0x009c:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0098 }
            r3.mo29769d(r1)     // Catch:{ all -> 0x0098 }
            if (r2 == 0) goto L_0x00b1
            r2.close()     // Catch:{ Throwable -> 0x00a9 }
            goto L_0x00b1
        L_0x00a9:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
        L_0x00b1:
            return r0
        L_0x00b2:
            if (r3 == 0) goto L_0x00c0
            r3.close()     // Catch:{ Throwable -> 0x00b8 }
            goto L_0x00c0
        L_0x00b8:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r1)
        L_0x00c0:
            goto L_0x00c2
        L_0x00c1:
            throw r0
        L_0x00c2:
            goto L_0x00c1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getDeviceMemUsage():java.util.HashMap");
    }

    private String byteToHex(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x:", new Object[]{Byte.valueOf(bArr[i])}));
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075 A[Catch:{ Throwable -> 0x0118 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> getLocalIpInfo() {
        /*
            r11 = this;
            java.lang.String r0 = "android.permission.INTERNET"
            boolean r0 = r11.checkPermission(r0)     // Catch:{ Throwable -> 0x0118 }
            if (r0 == 0) goto L_0x0120
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0118 }
            r0.<init>()     // Catch:{ Throwable -> 0x0118 }
            java.util.Enumeration r1 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ Throwable -> 0x0118 }
            java.util.ArrayList r1 = java.util.Collections.list(r1)     // Catch:{ Throwable -> 0x0118 }
            java.util.HashMap r2 = r11.listNetworkHardware()     // Catch:{ Throwable -> 0x0118 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x0118 }
        L_0x001d:
            boolean r3 = r1.hasNext()     // Catch:{ Throwable -> 0x0118 }
            if (r3 == 0) goto L_0x0117
            java.lang.Object r3 = r1.next()     // Catch:{ Throwable -> 0x0118 }
            java.net.NetworkInterface r3 = (java.net.NetworkInterface) r3     // Catch:{ Throwable -> 0x0118 }
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ Throwable -> 0x0118 }
            r4.<init>()     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r5 = r3.getName()     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r6 = "name"
            r4.put(r6, r5)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r6 = "mac"
            if (r2 == 0) goto L_0x004f
            boolean r7 = r2.isEmpty()     // Catch:{ Throwable -> 0x0118 }
            if (r7 != 0) goto L_0x004f
            boolean r7 = r2.containsKey(r5)     // Catch:{ Throwable -> 0x0118 }
            if (r7 == 0) goto L_0x004f
            java.lang.Object r5 = r2.get(r5)     // Catch:{ Throwable -> 0x0118 }
            r4.put(r6, r5)     // Catch:{ Throwable -> 0x0118 }
            goto L_0x005a
        L_0x004f:
            byte[] r5 = r3.getHardwareAddress()     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r5 = r11.byteToHex(r5)     // Catch:{ Throwable -> 0x0118 }
            r4.put(r6, r5)     // Catch:{ Throwable -> 0x0118 }
        L_0x005a:
            java.util.List r3 = r3.getInterfaceAddresses()     // Catch:{ Throwable -> 0x0118 }
            if (r3 == 0) goto L_0x001d
            int r5 = r3.size()     // Catch:{ Throwable -> 0x0118 }
            if (r5 <= 0) goto L_0x001d
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0118 }
            r5.<init>()     // Catch:{ Throwable -> 0x0118 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x0118 }
        L_0x006f:
            boolean r6 = r3.hasNext()     // Catch:{ Throwable -> 0x0118 }
            if (r6 == 0) goto L_0x010d
            java.lang.Object r6 = r3.next()     // Catch:{ Throwable -> 0x0118 }
            java.net.InterfaceAddress r6 = (java.net.InterfaceAddress) r6     // Catch:{ Throwable -> 0x0118 }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ Throwable -> 0x0118 }
            r7.<init>()     // Catch:{ Throwable -> 0x0118 }
            java.net.InetAddress r8 = r6.getAddress()     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "haddr"
            java.lang.String r10 = r8.getHostAddress()     // Catch:{ Throwable -> 0x0118 }
            r7.put(r9, r10)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "hname"
            java.lang.String r10 = r8.getHostName()     // Catch:{ Throwable -> 0x0118 }
            r7.put(r9, r10)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "lp"
            boolean r10 = r8.isLoopbackAddress()     // Catch:{ Throwable -> 0x0118 }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ Throwable -> 0x0118 }
            r7.put(r9, r10)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "addr"
            byte[] r10 = r8.getAddress()     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r10 = r11.byteToHex(r10)     // Catch:{ Throwable -> 0x0118 }
            r7.put(r9, r10)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "len"
            byte[] r8 = r8.getAddress()     // Catch:{ Throwable -> 0x0118 }
            int r8 = r8.length     // Catch:{ Throwable -> 0x0118 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0118 }
            r7.put(r9, r8)     // Catch:{ Throwable -> 0x0118 }
            java.net.InetAddress r6 = r6.getBroadcast()     // Catch:{ Throwable -> 0x0118 }
            if (r6 == 0) goto L_0x0108
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ Throwable -> 0x0118 }
            r8.<init>()     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "haddrB"
            java.lang.String r10 = r6.getHostAddress()     // Catch:{ Throwable -> 0x0118 }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "hnameB"
            java.lang.String r10 = r6.getHostName()     // Catch:{ Throwable -> 0x0118 }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "lpB"
            boolean r10 = r6.isLoopbackAddress()     // Catch:{ Throwable -> 0x0118 }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ Throwable -> 0x0118 }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "addrB"
            byte[] r10 = r6.getAddress()     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r10 = r11.byteToHex(r10)     // Catch:{ Throwable -> 0x0118 }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r9 = "lenB"
            byte[] r6 = r6.getAddress()     // Catch:{ Throwable -> 0x0118 }
            int r6 = r6.length     // Catch:{ Throwable -> 0x0118 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Throwable -> 0x0118 }
            r8.put(r9, r6)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r6 = "broadcast"
            r7.put(r6, r8)     // Catch:{ Throwable -> 0x0118 }
        L_0x0108:
            r5.add(r7)     // Catch:{ Throwable -> 0x0118 }
            goto L_0x006f
        L_0x010d:
            java.lang.String r3 = "inets"
            r4.put(r3, r5)     // Catch:{ Throwable -> 0x0118 }
            r0.add(r4)     // Catch:{ Throwable -> 0x0118 }
            goto L_0x001d
        L_0x0117:
            return r0
        L_0x0118:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.mo29769d(r0)
        L_0x0120:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.DeviceHelper.getLocalIpInfo():java.util.ArrayList");
    }

    public boolean isFakePass(String str) {
        try {
            String importClass = ReflectHelper.importClass(Strings.getString(132));
            if (((Integer) ReflectHelper.invokeStaticMethod(importClass, Strings.getString(134), this.context, (String) ReflectHelper.invokeStaticMethod(importClass, Strings.getString(133), str), getPackageName())).intValue() == 1) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
    }

    public boolean isSensitiveDevice() {
        String manufacturer;
        boolean z;
        String str = "";
        try {
            manufacturer = getManufacturer();
            String mIUIVersion = getMIUIVersion();
            if (!TextUtils.isEmpty(mIUIVersion) && mIUIVersion.length() >= 3) {
                if (Integer.parseInt(mIUIVersion.substring(1)) >= 12) {
                    z = true;
                    if (!"xiaomi".equalsIgnoreCase(manufacturer) && z) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            String message = th.getMessage();
            if (message != null) {
                str = message;
            }
            MobLog.getInstance().mo29768d(str, new Object[0]);
            return false;
        }
        z = false;
        return !"xiaomi".equalsIgnoreCase(manufacturer) ? false : false;
    }

    private class GSConnection implements ServiceConnection {
        boolean got;
        private final BlockingQueue<IBinder> iBinders;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private GSConnection() {
            this.got = false;
            this.iBinders = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.iBinders.put(iBinder);
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }

        public IBinder takeBinder() throws InterruptedException {
            if (!this.got) {
                this.got = true;
                return this.iBinders.poll(1500, TimeUnit.MILLISECONDS);
            }
            throw new IllegalStateException();
        }
    }
}
