package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.baidu.mobstat.C0976bl;
import com.baidu.mobstat.C0985bs;
import com.baidu.mobstat.util.CuidUtil;
import com.hjq.permissions.Permission;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.bw */
public class C0991bw {

    /* renamed from: a */
    private static String f1283a;

    /* renamed from: b */
    private static String f1284b;

    /* renamed from: c */
    private static String f1285c;

    /* renamed from: d */
    private static final Pattern f1286d = Pattern.compile("\\s*|\t|\r|\n");

    /* renamed from: a */
    public static String m1434a(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return "";
            }
            Object obj = null;
            if (applicationInfo.metaData != null) {
                obj = applicationInfo.metaData.get(str);
            }
            if (obj != null) {
                return obj.toString();
            }
            C0955bb c = C0955bb.m1194c();
            c.mo11624a("can't find information in AndroidManifest.xml for key " + str);
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m1431a(int i, Context context) {
        try {
            return C0976bl.C0978b.m1333c(i, m1432a(context).getBytes());
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m1432a(Context context) {
        return f1286d.matcher(C0994bx.m1483a(context)).replaceAll("");
    }

    /* renamed from: b */
    public static String m1437b(int i, Context context) {
        return C0976bl.C0978b.m1333c(i, CuidUtil.getOaid(context).getBytes());
    }

    /* renamed from: c */
    public static String m1443c(int i, final Context context) {
        String v = C0982bp.m1357a().mo11717v(context);
        if (TextUtils.isEmpty(v)) {
            v = C0982bp.m1357a().mo11718w(context);
            C0999ca.m1493a().mo11730a(context, new C0997bz() {
                /* renamed from: a */
                public void mo11725a(String str) {
                    if (!TextUtils.isEmpty(str)) {
                        C0982bp.m1357a().mo11710o(context, str);
                    }
                }
            });
        }
        return C0976bl.C0978b.m1333c(i, v.getBytes());
    }

    /* renamed from: d */
    public static String m1447d(int i, Context context) {
        return C0976bl.C0978b.m1333c(i, CuidUtil.getIid(context).getBytes());
    }

    /* renamed from: e */
    public static String m1449e(int i, Context context) {
        return C0976bl.C0978b.m1333c(i, CuidUtil.getGaid(context).getBytes());
    }

    /* renamed from: f */
    public static String m1451f(int i, Context context) {
        return C0976bl.C0978b.m1333c(i, CuidUtil.getCuid3(context).getBytes());
    }

    /* renamed from: g */
    public static String m1452g(int i, Context context) {
        return C0976bl.C0978b.m1333c(i, CuidUtil.getSsaid(context).getBytes());
    }

    /* renamed from: b */
    public static String m1438b(Context context) {
        return C0985bs.C0986a.m1411a(m1432a(context).getBytes()).toUpperCase(Locale.US);
    }

    /* renamed from: c */
    public static int m1441c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = m1448e(context);
        } catch (Exception unused) {
        }
        return displayMetrics.widthPixels;
    }

    /* renamed from: d */
    public static int m1445d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = m1448e(context);
        } catch (Exception unused) {
        }
        return displayMetrics.heightPixels;
    }

    /* renamed from: e */
    public static DisplayMetrics m1448e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* renamed from: f */
    public static int m1450f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return 1;
        }
    }

    /* renamed from: g */
    public static String m1453g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: h */
    public static String m1455h(Context context) {
        try {
            return (String) context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: i */
    public static String m1457i(Context context) {
        CellLocation cellLocation;
        String format = String.format("%s_%s_%s", new Object[]{0, 0, 0});
        try {
            if ((!C0980bn.m1352e(context, Permission.ACCESS_FINE_LOCATION) && !C0980bn.m1352e(context, Permission.ACCESS_COARSE_LOCATION)) || (cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation()) == null) {
                return format;
            }
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                return String.format("%s_%s_%s", new Object[]{String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(gsmCellLocation.getCid())}), String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(gsmCellLocation.getLac())}), 0});
            }
            String[] split = cellLocation.toString().replace("[", "").replace("]", "").split(",");
            return String.format("%s_%s_%s", new Object[]{split[0], split[3], split[4]});
        } catch (Exception unused) {
        }
        return format;
    }

    /* renamed from: j */
    public static String m1459j(Context context) {
        Location lastKnownLocation;
        try {
            if (!C0980bn.m1352e(context, Permission.ACCESS_FINE_LOCATION) || (lastKnownLocation = ((LocationManager) context.getSystemService("location")).getLastKnownLocation("gps")) == null) {
                return "";
            }
            return String.format("%s_%s_%s", new Object[]{Long.valueOf(lastKnownLocation.getTime()), Double.valueOf(lastKnownLocation.getLongitude()), Double.valueOf(lastKnownLocation.getLatitude())});
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: h */
    public static String m1454h(int i, Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        String l = m1463l(context);
        if (TextUtils.isEmpty(l)) {
            return "";
        }
        return C0976bl.C0978b.m1333c(i, l.getBytes());
    }

    /* renamed from: k */
    public static String m1461k(Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        if (Build.VERSION.SDK_INT < 23) {
            return m1463l(context);
        }
        return m1446d();
    }

    /* renamed from: l */
    public static String m1463l(Context context) {
        WifiInfo connectionInfo;
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        try {
            if (C0980bn.m1352e(context, "android.permission.ACCESS_WIFI_STATE") && (connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo()) != null) {
                String macAddress = connectionInfo.getMacAddress();
                if (!TextUtils.isEmpty(macAddress)) {
                    return macAddress;
                }
            }
        } catch (Exception unused) {
        }
        return "";
    }

    @TargetApi(9)
    /* renamed from: d */
    private static String m1446d() {
        if (Build.VERSION.SDK_INT < 9) {
            return "";
        }
        try {
            for (T t : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (t.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = t.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    /* renamed from: a */
    private static String m1430a(byte b) {
        String str = "00" + Integer.toHexString(b) + Config.TRACE_TODAY_VISIT_SPLIT;
        return str.substring(str.length() - 3);
    }

    /* renamed from: i */
    public static String m1456i(int i, Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        String j = m1458j(i, context);
        String c = !TextUtils.isEmpty(j) ? C0976bl.C0978b.m1333c(i, j.getBytes()) : null;
        if (TextUtils.isEmpty(c)) {
            return "";
        }
        return c;
    }

    /* renamed from: j */
    public static String m1458j(int i, Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        String a = m1429a();
        String k = TextUtils.isEmpty(a) ? m1460k(i, context) : a;
        if (TextUtils.isEmpty(k)) {
            return "";
        }
        return k;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: k */
    public static String m1460k(int i, Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                while (true) {
                    if (!inetAddresses.hasMoreElements()) {
                        break;
                    }
                    InetAddress nextElement2 = inetAddresses.nextElement();
                    if (!nextElement2.isAnyLocalAddress() && (nextElement2 instanceof Inet4Address)) {
                        if (!nextElement2.isLoopbackAddress()) {
                            if (nextElement2.isSiteLocalAddress()) {
                                bArr = nextElement.getHardwareAddress();
                            } else if (!nextElement2.isLinkLocalAddress()) {
                                bArr = nextElement.getHardwareAddress();
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        if (bArr != null) {
            for (byte a : bArr) {
                stringBuffer.append(m1430a(a));
            }
            return stringBuffer.substring(0, stringBuffer.length() - 1).replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        String h = m1454h(i, context);
        return h != null ? h.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "") : h;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x005a A[SYNTHETIC, Splitter:B:27:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0061 A[SYNTHETIC, Splitter:B:34:0x0061] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1429a() {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 20
            r2 = 0
            char[] r1 = new char[r1]     // Catch:{ Exception -> 0x005e, all -> 0x0056 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x005e, all -> 0x0056 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x005e, all -> 0x0056 }
            java.lang.String r5 = "/sys/class/net/eth0/address"
            r4.<init>(r5)     // Catch:{ Exception -> 0x005e, all -> 0x0056 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x005e, all -> 0x0056 }
        L_0x0016:
            int r4 = r3.read(r1)     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            r5 = -1
            if (r4 == r5) goto L_0x003e
            int r5 = r1.length     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            r6 = 13
            if (r4 != r5) goto L_0x002f
            int r5 = r1.length     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            int r5 = r5 + -1
            char r5 = r1[r5]     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            if (r5 == r6) goto L_0x002f
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            r4.print(r1)     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            goto L_0x0016
        L_0x002f:
            r5 = 0
        L_0x0030:
            if (r5 >= r4) goto L_0x0016
            char r7 = r1[r5]     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            if (r7 == r6) goto L_0x003b
            char r7 = r1[r5]     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            r0.append(r7)     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
        L_0x003b:
            int r5 = r5 + 1
            goto L_0x0030
        L_0x003e:
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            java.lang.String r0 = r0.trim()     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            java.lang.String r1 = ":"
            java.lang.String r4 = ""
            java.lang.String r0 = r0.replaceAll(r1, r4)     // Catch:{ Exception -> 0x0054, all -> 0x0052 }
            r3.close()     // Catch:{ Exception -> 0x0051 }
        L_0x0051:
            return r0
        L_0x0052:
            r0 = move-exception
            goto L_0x0058
        L_0x0054:
            goto L_0x005f
        L_0x0056:
            r0 = move-exception
            r3 = r2
        L_0x0058:
            if (r3 == 0) goto L_0x005d
            r3.close()     // Catch:{ Exception -> 0x005d }
        L_0x005d:
            throw r0
        L_0x005e:
            r3 = r2
        L_0x005f:
            if (r3 == 0) goto L_0x0064
            r3.close()     // Catch:{ Exception -> 0x0064 }
        L_0x0064:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0991bw.m1429a():java.lang.String");
    }

    /* renamed from: a */
    public static String m1433a(Context context, int i) {
        String m = m1465m(context);
        return TextUtils.isEmpty(m) ? "" : C0976bl.C0978b.m1333c(i, m.getBytes());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r0 = r0.getName();
     */
    /* renamed from: m */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1465m(android.content.Context r0) {
        /*
            android.bluetooth.BluetoothAdapter r0 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()     // Catch:{ Exception -> 0x000d }
            if (r0 == 0) goto L_0x000d
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x000d }
            if (r0 == 0) goto L_0x000d
            return r0
        L_0x000d:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0991bw.m1465m(android.content.Context):java.lang.String");
    }

    /* renamed from: l */
    public static String m1462l(int i, Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        String n = m1467n(context);
        if (TextUtils.isEmpty(n)) {
            return "";
        }
        return C0976bl.C0978b.m1333c(i, n.getBytes());
    }

    @SuppressLint({"NewApi"})
    /* renamed from: n */
    public static String m1467n(Context context) {
        BluetoothAdapter defaultAdapter;
        String address;
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        String str = Build.BRAND;
        if ("4.1.1".equals(Build.VERSION.RELEASE) && "TCT".equals(str)) {
            return "";
        }
        try {
            if (!C0980bn.m1352e(context, "android.permission.BLUETOOTH") || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null || (address = defaultAdapter.getAddress()) == null) {
                return "";
            }
            return address;
        } catch (Exception unused) {
        }
    }

    /* renamed from: o */
    public static String m1468o(Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        String p = m1469p(context);
        if (TextUtils.isEmpty(p)) {
            return "";
        }
        return C0976bl.C0977a.m1326a(p.getBytes());
    }

    /* renamed from: m */
    public static String m1464m(int i, Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        String p = m1469p(context);
        if (TextUtils.isEmpty(p)) {
            return "";
        }
        return C0976bl.C0978b.m1334d(i, p.getBytes());
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0060 A[SYNTHETIC, Splitter:B:33:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b0 A[EDGE_INSN: B:58:0x00b0->B:46:0x00b0 ?: BREAK  
    EDGE_INSN: B:59:0x00b0->B:46:0x00b0 ?: BREAK  
    EDGE_INSN: B:60:0x00b0->B:46:0x00b0 ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b6 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b7  */
    /* renamed from: p */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1469p(android.content.Context r15) {
        /*
            java.lang.String r0 = ""
            if (r15 != 0) goto L_0x0005
            return r0
        L_0x0005:
            java.lang.String r1 = "android.permission.ACCESS_WIFI_STATE"
            boolean r1 = com.baidu.mobstat.C0980bn.m1352e(r15, r1)
            if (r1 != 0) goto L_0x000e
            return r0
        L_0x000e:
            r1 = 0
            java.lang.String r2 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r2 = com.baidu.mobstat.C0980bn.m1352e(r15, r2)     // Catch:{ Exception -> 0x0026 }
            if (r2 == 0) goto L_0x0026
            java.lang.String r2 = "location"
            java.lang.Object r2 = r15.getSystemService(r2)     // Catch:{ Exception -> 0x0026 }
            android.location.LocationManager r2 = (android.location.LocationManager) r2     // Catch:{ Exception -> 0x0026 }
            java.lang.String r3 = "gps"
            boolean r2 = r2.isProviderEnabled(r3)     // Catch:{ Exception -> 0x0026 }
            goto L_0x0027
        L_0x0026:
            r2 = 0
        L_0x0027:
            r3 = 0
            java.lang.String r4 = "wifi"
            java.lang.Object r4 = r15.getSystemService(r4)     // Catch:{ Throwable -> 0x0039 }
            android.net.wifi.WifiManager r4 = (android.net.wifi.WifiManager) r4     // Catch:{ Throwable -> 0x0039 }
            android.net.wifi.WifiInfo r5 = r4.getConnectionInfo()     // Catch:{ Throwable -> 0x0039 }
            java.util.List r4 = r4.getScanResults()     // Catch:{ Throwable -> 0x003a }
            goto L_0x003b
        L_0x0039:
            r5 = r3
        L_0x003a:
            r4 = r3
        L_0x003b:
            if (r4 == 0) goto L_0x004b
            int r6 = r4.size()
            if (r6 == 0) goto L_0x004b
            com.baidu.mobstat.bw$2 r6 = new com.baidu.mobstat.bw$2
            r6.<init>()
            java.util.Collections.sort(r4, r6)
        L_0x004b:
            org.json.JSONArray r6 = new org.json.JSONArray
            r6.<init>()
            r7 = 0
        L_0x0051:
            r8 = 1
            java.lang.String r9 = "|"
            if (r4 == 0) goto L_0x00b0
            int r10 = r4.size()
            if (r7 >= r10) goto L_0x00b0
            r10 = 30
            if (r7 >= r10) goto L_0x00b0
            java.lang.Object r11 = r4.get(r7)     // Catch:{ Exception -> 0x00ad }
            android.net.wifi.ScanResult r11 = (android.net.wifi.ScanResult) r11     // Catch:{ Exception -> 0x00ad }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ad }
            r12.<init>()     // Catch:{ Exception -> 0x00ad }
            java.lang.String r13 = r11.BSSID     // Catch:{ Exception -> 0x00ad }
            r12.append(r13)     // Catch:{ Exception -> 0x00ad }
            r12.append(r9)     // Catch:{ Exception -> 0x00ad }
            java.lang.String r13 = r11.SSID     // Catch:{ Exception -> 0x00ad }
            java.lang.String r14 = "\\|"
            java.lang.String r13 = r13.replaceAll(r14, r0)     // Catch:{ Exception -> 0x00ad }
            int r14 = r13.length()     // Catch:{ Exception -> 0x00ad }
            if (r14 <= r10) goto L_0x0085
            java.lang.String r13 = r13.substring(r1, r10)     // Catch:{ Exception -> 0x00ad }
        L_0x0085:
            r12.append(r13)     // Catch:{ Exception -> 0x00ad }
            r12.append(r9)     // Catch:{ Exception -> 0x00ad }
            int r10 = r11.level     // Catch:{ Exception -> 0x00ad }
            r12.append(r10)     // Catch:{ Exception -> 0x00ad }
            r12.append(r9)     // Catch:{ Exception -> 0x00ad }
            if (r5 == 0) goto L_0x00a2
            java.lang.String r9 = r11.BSSID     // Catch:{ Exception -> 0x00ad }
            java.lang.String r10 = r5.getBSSID()     // Catch:{ Exception -> 0x00ad }
            boolean r9 = r9.equals(r10)     // Catch:{ Exception -> 0x00ad }
            if (r9 == 0) goto L_0x00a2
            goto L_0x00a3
        L_0x00a2:
            r8 = 0
        L_0x00a3:
            r12.append(r8)     // Catch:{ Exception -> 0x00ad }
            java.lang.String r8 = r12.toString()     // Catch:{ Exception -> 0x00ad }
            r6.put(r8)     // Catch:{ Exception -> 0x00ad }
        L_0x00ad:
            int r7 = r7 + 1
            goto L_0x0051
        L_0x00b0:
            int r4 = r6.length()
            if (r4 != 0) goto L_0x00b7
            return r3
        L_0x00b7:
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ee }
            r4.<init>()     // Catch:{ Exception -> 0x00ee }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00ee }
            r4.append(r10)     // Catch:{ Exception -> 0x00ee }
            r4.append(r9)     // Catch:{ Exception -> 0x00ee }
            if (r2 == 0) goto L_0x00ce
            r1 = 1
        L_0x00ce:
            r4.append(r1)     // Catch:{ Exception -> 0x00ee }
            r4.append(r9)     // Catch:{ Exception -> 0x00ee }
            java.lang.String r15 = m1459j(r15)     // Catch:{ Exception -> 0x00ee }
            r4.append(r15)     // Catch:{ Exception -> 0x00ee }
            java.lang.String r15 = "ap-list"
            r3.put(r15, r6)     // Catch:{ Exception -> 0x00ee }
            java.lang.String r15 = "meta-data"
            java.lang.String r1 = r4.toString()     // Catch:{ Exception -> 0x00ee }
            r3.put(r15, r1)     // Catch:{ Exception -> 0x00ee }
            java.lang.String r15 = r3.toString()     // Catch:{ Exception -> 0x00ee }
            return r15
        L_0x00ee:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0991bw.m1469p(android.content.Context):java.lang.String");
    }

    /* renamed from: q */
    public static boolean m1470q(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: r */
    public static String m1471r(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "";
            }
            String typeName = activeNetworkInfo.getTypeName();
            return (typeName.equals("WIFI") || activeNetworkInfo.getSubtypeName() == null) ? typeName : activeNetworkInfo.getSubtypeName();
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: s */
    public static boolean m1472s(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (!C0980bn.m1352e(context, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isAvailable();
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: t */
    public static String m1473t(Context context) {
        return context != null ? context.getPackageName() : "";
    }

    /* renamed from: n */
    public static String m1466n(int i, Context context) {
        String t = m1473t(context);
        if (TextUtils.isEmpty(t)) {
            return "";
        }
        try {
            return C0976bl.C0978b.m1333c(i, t.getBytes());
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: z */
    private static String m1479z(Context context) {
        String str = f1283a;
        if (str == null) {
            try {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
                int i = 0;
                while (true) {
                    if (runningAppProcesses == null || i >= runningAppProcesses.size()) {
                        break;
                    }
                    ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
                    if (runningAppProcessInfo != null && runningAppProcessInfo.pid == Process.myPid()) {
                        str = runningAppProcessInfo.processName;
                        break;
                    }
                    i++;
                }
            } catch (Exception unused) {
            }
            if (str == null) {
                str = "";
            }
            f1283a = str;
        }
        return str;
    }

    /* renamed from: b */
    private static String m1439b(Context context, String str) {
        int lastIndexOf;
        int i;
        if (str != null && (lastIndexOf = str.lastIndexOf(58)) > 0 && (i = lastIndexOf + 1) < str.length()) {
            return str.substring(i);
        }
        return null;
    }

    /* renamed from: c */
    private static String m1444c(Context context, String str) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        String str2 = applicationInfo.processName;
        if (str2 == null || str2.equals(str)) {
            return null;
        }
        return str;
    }

    /* renamed from: u */
    public static String m1474u(Context context) {
        String str = f1284b;
        if (str == null) {
            String z = m1479z(context);
            String b = m1439b(context, z);
            if (TextUtils.isEmpty(b)) {
                b = m1444c(context, z);
            }
            str = b == null ? "" : b;
            f1284b = str;
        }
        return str;
    }

    /* renamed from: v */
    public static String m1475v(Context context) {
        ServiceInfo[] serviceInfoArr;
        String str;
        String z = m1479z(context);
        if (z == null) {
            return "";
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
        } catch (Exception unused) {
        }
        if (packageInfo == null || (serviceInfoArr = packageInfo.services) == null) {
            return "";
        }
        int length = serviceInfoArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str = "";
                break;
            }
            ServiceInfo serviceInfo = serviceInfoArr[i];
            if (z.equals(serviceInfo.processName)) {
                str = serviceInfo.name;
                break;
            }
            i++;
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    /* renamed from: w */
    public static boolean m1476w(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: x */
    public static String m1477x(Context context) {
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Config.MODEL, memoryInfo.availMem);
            jSONObject.put("l", memoryInfo.lowMemory);
            jSONObject.put("t", memoryInfo.threshold);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_mem", jSONArray);
            jSONObject2.put("meta-data", sb.toString());
            return C0976bl.C0977a.m1326a(jSONObject2.toString().getBytes());
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m1436b() {
        String str;
        String str2 = f1285c;
        if (str2 != null) {
            return str2;
        }
        if (!TextUtils.isEmpty(m1440b("ro.miui.ui.version.name"))) {
            str = "miui";
        } else if (!TextUtils.isEmpty(m1440b("ro.build.version.opporom"))) {
            str = "coloros";
        } else if (!TextUtils.isEmpty(m1440b("ro.build.version.emui"))) {
            str = "emui";
        } else if (!TextUtils.isEmpty(m1440b("ro.vivo.os.version"))) {
            str = "funtouch";
        } else {
            str = !TextUtils.isEmpty(m1440b("ro.smartisan.version")) ? "smartisan" : "";
        }
        if (TextUtils.isEmpty(str)) {
            String b = m1440b("ro.build.display.id");
            if (!TextUtils.isEmpty(b) && b.contains("Flyme")) {
                str = "flyme";
            }
        }
        f1285c = str;
        return f1285c;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r5.destroy();
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005e, code lost:
        if (r5 == null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0061, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        if (r5 == null) goto L_0x0061;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004a A[SYNTHETIC, Splitter:B:22:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0059 A[SYNTHETIC, Splitter:B:31:0x0059] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m1440b(java.lang.String r5) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0055, all -> 0x0046 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0055, all -> 0x0046 }
            r2.<init>()     // Catch:{ Exception -> 0x0055, all -> 0x0046 }
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch:{ Exception -> 0x0055, all -> 0x0046 }
            r2.append(r5)     // Catch:{ Exception -> 0x0055, all -> 0x0046 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x0055, all -> 0x0046 }
            java.lang.Process r5 = r1.exec(r5)     // Catch:{ Exception -> 0x0055, all -> 0x0046 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            java.io.InputStream r3 = r5.getInputStream()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            r3 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            java.lang.String r0 = r1.readLine()     // Catch:{ Exception -> 0x003d, all -> 0x0037 }
            r1.close()     // Catch:{ Exception -> 0x0031 }
        L_0x0031:
            if (r5 == 0) goto L_0x0061
        L_0x0033:
            r5.destroy()
            goto L_0x0061
        L_0x0037:
            r0 = move-exception
            r4 = r1
            r1 = r5
            r5 = r0
            r0 = r4
            goto L_0x0048
        L_0x003d:
            goto L_0x0057
        L_0x003f:
            r1 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
            goto L_0x0048
        L_0x0044:
            r1 = r0
            goto L_0x0057
        L_0x0046:
            r5 = move-exception
            r1 = r0
        L_0x0048:
            if (r0 == 0) goto L_0x004f
            r0.close()     // Catch:{ Exception -> 0x004e }
            goto L_0x004f
        L_0x004e:
        L_0x004f:
            if (r1 == 0) goto L_0x0054
            r1.destroy()
        L_0x0054:
            throw r5
        L_0x0055:
            r5 = r0
            r1 = r5
        L_0x0057:
            if (r1 == 0) goto L_0x005e
            r1.close()     // Catch:{ Exception -> 0x005d }
            goto L_0x005e
        L_0x005d:
        L_0x005e:
            if (r5 == 0) goto L_0x0061
            goto L_0x0033
        L_0x0061:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0991bw.m1440b(java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    public static Boolean m1442c() {
        Object invoke;
        try {
            Class<?> cls = Class.forName("com.baidu.disasterrecovery.MtjAdapter");
            if (cls == null || (invoke = cls.getDeclaredMethod("shouldUploadOther", new Class[0]).invoke((Object) null, new Object[0])) == null || !(invoke instanceof Boolean)) {
                return true;
            }
            return (Boolean) invoke;
        } catch (Exception unused) {
            return true;
        }
    }

    /* renamed from: y */
    public static String m1478y(Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        try {
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            return !TextUtils.isEmpty(deviceId) ? C0976bl.C0978b.m1333c(1, deviceId.getBytes()) : deviceId;
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m1435a(String str) {
        return C0976bl.C0978b.m1333c(1, str.getBytes());
    }
}
