package com.mob.commons.p023a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.constant.PermissionConstants;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.C2300e;
import com.mob.commons.C2308i;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;
import org.json.JSONObject;

/* renamed from: com.mob.commons.a.k */
/* compiled from: DvcClt */
public class C2237k extends C2226d {

    /* renamed from: a */
    private Hashon f1968a;

    /* renamed from: b */
    private Random f1969b;

    /* renamed from: c */
    private BroadcastReceiver f1970c;

    /* renamed from: d */
    private BroadcastReceiver f1971d;

    /* renamed from: e */
    private BroadcastReceiver f1972e;

    /* renamed from: f */
    private DeviceHelper f1973f = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: g */
    private boolean f1974g = false;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public boolean f1975h = false;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public boolean f1976i = false;

    C2237k() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return C2300e.m2466a("comm/locks/.dic_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        mo28999b(1);
        mo28999b(2);
        Message obtain = Message.obtain();
        obtain.arg1 = -1;
        obtain.what = 6;
        mo29000b(obtain);
        mo28999b(3);
        mo28999b(5);
        mo28999b(7);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ce, code lost:
        if (m2079m() != false) goto L_0x00d0;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo28987a(android.os.Message r8) {
        /*
            r7 = this;
            int r0 = r8.what
            r1 = 4
            r2 = 120(0x78, float:1.68E-43)
            r3 = 1
            switch(r0) {
                case 1: goto L_0x0133;
                case 2: goto L_0x0122;
                case 3: goto L_0x00f6;
                case 4: goto L_0x00b8;
                case 5: goto L_0x0073;
                case 6: goto L_0x0046;
                case 7: goto L_0x0026;
                case 8: goto L_0x0021;
                case 9: goto L_0x0018;
                case 10: goto L_0x0010;
                case 11: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x013c
        L_0x000b:
            r7.m2086t()
            goto L_0x013c
        L_0x0010:
            r7.f1974g = r3
            r8 = 0
            r7.m2070b((java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>) r8)
            goto L_0x013c
        L_0x0018:
            java.lang.Object r8 = r8.obj
            android.os.Parcelable r8 = (android.os.Parcelable) r8
            r7.m2065a((android.os.Parcelable) r8)
            goto L_0x013c
        L_0x0021:
            r7.m2081o()
            goto L_0x013c
        L_0x0026:
            boolean r8 = com.mob.commons.C2262b.m2329x()
            if (r8 == 0) goto L_0x0038
            r7.m2080n()     // Catch:{ Throwable -> 0x0030 }
            goto L_0x0038
        L_0x0030:
            r8 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29787w((java.lang.Throwable) r8)
        L_0x0038:
            r8 = 7
            long r0 = com.mob.commons.C2262b.m2331z()
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r2
            r7.mo28997a((int) r8, (long) r0)
            goto L_0x013c
        L_0x0046:
            boolean r8 = com.mob.commons.C2262b.m2322q()
            long r0 = com.mob.commons.C2262b.m2323r()
            int r1 = (int) r0
            r0 = 6
            if (r8 == 0) goto L_0x0064
            r7.f1976i = r3
            r7.m2072c((int) r1)
            com.mob.tools.utils.DeviceHelper r8 = r7.f1973f
            r8.scanWifiList()
            int r1 = r1 * 1000
            long r1 = (long) r1
            r7.mo28997a((int) r0, (long) r1)
            goto L_0x013c
        L_0x0064:
            r7.m2082p()
            int r8 = com.mob.commons.C2262b.m2324s()
            int r8 = r8 * 1000
            long r1 = (long) r8
            r7.mo28997a((int) r0, (long) r1)
            goto L_0x013c
        L_0x0073:
            r8 = 0
            r7.f1974g = r8
            boolean r8 = com.mob.commons.C2262b.m2318m()
            if (r8 == 0) goto L_0x00ab
            com.mob.tools.utils.DeviceHelper r8 = r7.f1973f     // Catch:{ Throwable -> 0x00a1 }
            java.lang.String r0 = "android.permission.CHANGE_WIFI_STATE"
            boolean r8 = r8.checkPermission(r0)     // Catch:{ Throwable -> 0x00a1 }
            r0 = 10
            if (r8 == 0) goto L_0x009c
            com.mob.tools.utils.DeviceHelper r8 = r7.f1973f     // Catch:{ Throwable -> 0x00a1 }
            java.lang.String r1 = "android.permission.ACCESS_WIFI_STATE"
            boolean r8 = r8.checkPermission(r1)     // Catch:{ Throwable -> 0x00a1 }
            if (r8 == 0) goto L_0x009c
            r7.m2083q()     // Catch:{ Throwable -> 0x00a1 }
            r1 = 5000(0x1388, double:2.4703E-320)
            r7.mo28997a((int) r0, (long) r1)     // Catch:{ Throwable -> 0x00a1 }
            goto L_0x013c
        L_0x009c:
            r7.mo28999b((int) r0)     // Catch:{ Throwable -> 0x00a1 }
            goto L_0x013c
        L_0x00a1:
            r8 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29787w((java.lang.Throwable) r8)
            goto L_0x013c
        L_0x00ab:
            r8 = 5
            int r0 = com.mob.commons.C2262b.m2319n()
            int r0 = r0 * 1000
            long r0 = (long) r0
            r7.mo28997a((int) r8, (long) r0)
            goto L_0x013c
        L_0x00b8:
            boolean r8 = com.mob.commons.C2262b.m2316k()
            if (r8 == 0) goto L_0x013c
            long r3 = com.mob.commons.C2308i.m2525d()
            long r5 = com.mob.commons.C2262b.m2260a()
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 < 0) goto L_0x00d0
            boolean r8 = r7.m2079m()     // Catch:{ Throwable -> 0x00d4 }
            if (r8 == 0) goto L_0x00dc
        L_0x00d0:
            r7.m2078l()     // Catch:{ Throwable -> 0x00d4 }
            goto L_0x00dc
        L_0x00d4:
            r8 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29787w((java.lang.Throwable) r8)
        L_0x00dc:
            java.util.Random r8 = r7.f1969b
            if (r8 != 0) goto L_0x00e7
            java.util.Random r8 = new java.util.Random
            r8.<init>()
            r7.f1969b = r8
        L_0x00e7:
            java.util.Random r8 = r7.f1969b
            int r8 = r8.nextInt(r2)
            int r8 = r8 + 180
            int r8 = r8 * 1000
            long r2 = (long) r8
            r7.mo28997a((int) r1, (long) r2)
            goto L_0x013c
        L_0x00f6:
            boolean r8 = com.mob.commons.C2262b.m2316k()
            if (r8 == 0) goto L_0x013c
            r7.m2078l()     // Catch:{ Throwable -> 0x0100 }
            goto L_0x0108
        L_0x0100:
            r8 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29787w((java.lang.Throwable) r8)
        L_0x0108:
            java.util.Random r8 = r7.f1969b
            if (r8 != 0) goto L_0x0113
            java.util.Random r8 = new java.util.Random
            r8.<init>()
            r7.f1969b = r8
        L_0x0113:
            java.util.Random r8 = r7.f1969b
            int r8 = r8.nextInt(r2)
            int r8 = r8 + 180
            int r8 = r8 * 1000
            long r2 = (long) r8
            r7.mo28997a((int) r1, (long) r2)
            goto L_0x013c
        L_0x0122:
            boolean r8 = com.mob.commons.C2262b.m2320o()
            if (r8 == 0) goto L_0x012f
            r7.m2075i()
            r7.m2076j()
            goto L_0x013c
        L_0x012f:
            r7.m2077k()
            goto L_0x013c
        L_0x0133:
            boolean r8 = com.mob.commons.C2262b.m2315j()
            if (r8 == 0) goto L_0x013c
            r7.m2074h()
        L_0x013c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2237k.mo28987a(android.os.Message):void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo28990b() {
        m2077k();
        m2082p();
        m2085s();
    }

    /* renamed from: h */
    private void m2074h() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("phonename", this.f1973f.getBluetoothName());
            hashMap.put("signmd5", this.f1973f.getSignMD5());
            hashMap.put("boardname", Build.BOARD);
            hashMap.put("devicename", Build.DEVICE);
            hashMap.put("displayid", Build.DISPLAY);
            hashMap.put("fingerprint", Build.FINGERPRINT);
            if (Build.VERSION.SDK_INT >= 14) {
                hashMap.put("radiover", Build.getRadioVersion());
            } else {
                hashMap.put("radiover", (Object) null);
            }
            hashMap.put("density", Float.valueOf(ResHelper.getDensity(MobSDK.getContext())));
            hashMap.put("densitydpi", Integer.valueOf(ResHelper.getDensityDpi(MobSDK.getContext())));
            hashMap.put("btm", this.f1973f.getBTMac());
            hashMap.put("btmp", this.f1973f.getBTMacFromProvider());
            boolean z = true;
            hashMap.put("bt", Integer.valueOf(this.f1973f.isBT() ? 1 : 0));
            hashMap.put("cameraResolutions", this.f1973f.getCamResolution());
            hashMap.put("timezone", this.f1973f.getTimezone());
            hashMap.put("cpuType", this.f1973f.getCPUType());
            hashMap.put("flavor", this.f1973f.getFlavor());
            hashMap.put("features", this.f1973f.getSupport());
            hashMap.put("defaultInputMethod", this.f1973f.getDefaultIM());
            hashMap.put("inputMethods", this.f1973f.getIMList());
            hashMap.put("brand", this.f1973f.getBrand());
            hashMap.put("isSimulator", Boolean.valueOf(this.f1973f.isSmlt()));
            hashMap.put("ipInfo", this.f1973f.getLocalIpInfo());
            String MD5 = Data.MD5(m2088v().fromHashMap(hashMap));
            String a = C2308i.m2506a();
            if (C2262b.m2260a() < C2308i.m2558o()) {
                z = false;
            }
            if (a == null || !a.equals(MD5) || z) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("type", "DEVEXT");
                hashMap2.put(ShareConstants.WEB_DIALOG_PARAM_DATA, hashMap);
                hashMap2.put("datetime", Long.valueOf(C2262b.m2260a()));
                C2293c.m2435a().mo29068a(C2262b.m2260a(), (HashMap<String, Object>) hashMap2);
                C2308i.m2510a(MD5);
                C2308i.m2531e(C2262b.m2260a() - 1702967296);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: i */
    private void m2075i() {
        ArrayList<HashMap<String, Object>> availableWifiList;
        synchronized (this) {
            HashMap hashMap = new HashMap();
            try {
                String bssid = this.f1973f.getBssid();
                if (!TextUtils.isEmpty(bssid) && (availableWifiList = this.f1973f.getAvailableWifiList()) != null && !availableWifiList.isEmpty()) {
                    Iterator<HashMap<String, Object>> it = availableWifiList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            HashMap next = it.next();
                            Object obj = next.get("BSSID");
                            if (obj != null && String.valueOf(obj).equals(bssid)) {
                                hashMap.putAll(next);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    hashMap.remove("BSSID");
                    hashMap.remove("SSID");
                }
                HashMap<String, Object> currentWifiInfo = this.f1973f.getCurrentWifiInfo();
                if (currentWifiInfo != null) {
                    hashMap.putAll(currentWifiInfo);
                }
                String ssid = this.f1973f.getSSID();
                hashMap.put("ssid", ssid);
                hashMap.put("bssid", bssid);
                HashMap hashMap2 = new HashMap();
                hashMap2.put("type", "WIFI_INFO");
                hashMap2.put(ShareConstants.WEB_DIALOG_PARAM_DATA, hashMap);
                hashMap2.put(Config.CELL_LOCATION, mo29002f());
                long a = C2262b.m2260a();
                hashMap2.put("datetime", Long.valueOf(a));
                C2293c.m2435a().mo29068a(C2262b.m2260a(), (HashMap<String, Object>) hashMap2);
                C2308i.m2508a(a);
                TreeMap treeMap = new TreeMap();
                treeMap.put("ssid", ssid);
                treeMap.put("bssid", bssid);
                C2308i.m2517b(Data.MD5(new JSONObject(treeMap).toString()));
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
            mo28997a(2, (long) (C2262b.m2321p() * 1000));
        }
    }

    /* renamed from: j */
    private void m2076j() {
        if (this.f1970c == null) {
            this.f1970c = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null && "android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
                        try {
                            Parcelable parcelableExtra = intent.getParcelableExtra("networkInfo");
                            if (parcelableExtra != null) {
                                Message message = new Message();
                                message.what = 9;
                                message.obj = parcelableExtra;
                                C2237k.this.mo29000b(message);
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().mo29787w(th);
                        }
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            try {
                ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), "registerReceiver", new Object[]{this.f1970c, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class});
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    private void m2065a(Parcelable parcelable) {
        try {
            if (((NetworkInfo) parcelable).isConnected()) {
                TreeMap treeMap = new TreeMap();
                treeMap.put("ssid", this.f1973f.getSSID());
                treeMap.put("bssid", this.f1973f.getBssid());
                String MD5 = Data.MD5(new JSONObject(treeMap).toString());
                String b = C2308i.m2514b();
                if ((b == null || !b.equals(MD5)) && C2262b.m2320o()) {
                    m2075i();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: k */
    private void m2077k() {
        if (this.f1970c != null) {
            try {
                ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), "unregisterReceiver", new Object[]{this.f1970c}, new Class[]{BroadcastReceiver.class});
            } catch (Throwable unused) {
            }
            this.f1970c = null;
        }
    }

    /* renamed from: l */
    private void m2078l() throws Throwable {
        int i;
        try {
            i = Integer.parseInt(this.f1973f.getCarrier());
        } catch (Throwable unused) {
            i = -1;
        }
        int cellLac = this.f1973f.getCellLac();
        int cellId = this.f1973f.getCellId();
        int psc = this.f1973f.getPsc();
        HashMap hashMap = null;
        if (!(i == -1 || cellLac == -1 || cellId == -1)) {
            hashMap = new HashMap();
            hashMap.put("lac", Integer.valueOf(cellLac));
            hashMap.put("cell", Integer.valueOf(cellId));
            if (psc != -1) {
                hashMap.put("psc", Integer.valueOf(psc));
            }
        }
        int cdmaBid = this.f1973f.getCdmaBid();
        int cdmaSid = this.f1973f.getCdmaSid();
        int cdmaNid = this.f1973f.getCdmaNid();
        int cdmaLat = this.f1973f.getCdmaLat();
        int cdmaLon = this.f1973f.getCdmaLon();
        if (!(i == -1 || cdmaBid == -1 || cdmaSid == -1 || cdmaNid == -1)) {
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            hashMap.put("bid", Integer.valueOf(cdmaBid));
            hashMap.put("sid", Integer.valueOf(cdmaSid));
            hashMap.put("nid", Integer.valueOf(cdmaNid));
            if (cdmaLat != -1) {
                hashMap.put("lat", Integer.valueOf(cdmaLat));
            }
            if (cdmaLon != -1) {
                hashMap.put("lon", Integer.valueOf(cdmaLon));
            }
        }
        if (hashMap != null) {
            hashMap.put("carrier", Integer.valueOf(i));
            hashMap.put("simopname", this.f1973f.getCarrierName());
            ArrayList<HashMap<String, Object>> neighboringCellInfo = this.f1973f.getNeighboringCellInfo();
            if (neighboringCellInfo != null && neighboringCellInfo.size() > 0) {
                hashMap.put("nearby", neighboringCellInfo);
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", "BSINFO");
            hashMap2.put(Config.CELL_LOCATION, mo29002f());
            hashMap2.put(ShareConstants.WEB_DIALOG_PARAM_DATA, hashMap);
            hashMap2.put("datetime", Long.valueOf(C2262b.m2260a()));
            C2293c.m2435a().mo29068a(C2262b.m2260a(), (HashMap<String, Object>) hashMap2);
            C2308i.m2523c(Data.MD5(m2088v().fromHashMap(hashMap)));
        }
        C2308i.m2516b(C2262b.m2260a() + (((long) C2262b.m2317l()) * 1000));
    }

    /* renamed from: m */
    private boolean m2079m() throws Throwable {
        int i;
        try {
            i = Integer.parseInt(this.f1973f.getCarrier());
        } catch (Throwable unused) {
            i = -1;
        }
        int cellLac = this.f1973f.getCellLac();
        int cellId = this.f1973f.getCellId();
        if (i == -1 || cellLac == -1 || cellId == -1) {
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("carrier", Integer.valueOf(i));
        hashMap.put("simopname", this.f1973f.getCarrierName());
        hashMap.put("lac", Integer.valueOf(cellLac));
        hashMap.put("cell", Integer.valueOf(cellId));
        String MD5 = Data.MD5(m2088v().fromHashMap(hashMap));
        String c = C2308i.m2520c();
        if (c == null || !c.equals(MD5)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private void m2067a(ArrayList<HashMap<String, Object>> arrayList, int i) {
        Location location;
        HashMap<String, Object> a;
        if (i == 1) {
            location = this.f1973f.getLocation(30, 0, true);
        } else {
            location = this.f1973f.getLocation(0, 15, true);
        }
        if (location != null && (a = m2064a(location)) != null && !a.isEmpty()) {
            a.put("location_type", Integer.valueOf(i));
            if (arrayList != null && !arrayList.isEmpty()) {
                a.put("wifiList", arrayList);
            }
            HashMap hashMap = new HashMap();
            hashMap.put("type", PermissionConstants.LOCATION);
            hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, a);
            hashMap.put("datetime", Long.valueOf(C2262b.m2260a()));
            C2293c.m2435a().mo29068a(C2262b.m2260a(), (HashMap<String, Object>) hashMap);
        }
    }

    /* renamed from: n */
    private void m2080n() {
        HashMap<String, Object> a;
        Location location = this.f1973f.getLocation(0, 0, true);
        if (location != null && (a = m2064a(location)) != null && !a.isEmpty()) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", "O_LOCATION");
            hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, a);
            hashMap.put("datetime", Long.valueOf(C2262b.m2260a()));
            TreeMap treeMap = new TreeMap();
            treeMap.put("latitude", Double.valueOf(location.getLatitude()));
            treeMap.put("longitude", Double.valueOf(location.getLongitude()));
            String MD5 = Data.MD5(new JSONObject(treeMap).toString());
            String q = C2308i.m2560q();
            long r = C2308i.m2561r();
            long a2 = C2262b.m2260a();
            if (TextUtils.isEmpty(q) || !q.equals(MD5) || a2 >= r) {
                MobLog.getInstance().mo29768d("o_loc: yes", new Object[0]);
                C2293c.m2435a().mo29068a(C2262b.m2260a(), (HashMap<String, Object>) hashMap);
                C2308i.m2549j(MD5);
                C2308i.m2539g(a2 + (C2262b.m2330y() * 1000));
                return;
            }
            MobLog.getInstance().mo29768d("o_loc: no", new Object[0]);
        }
    }

    /* renamed from: a */
    private HashMap<String, Object> m2064a(Location location) {
        if (location == null) {
            return null;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("accuracy", Float.valueOf(location.getAccuracy()));
        if (Build.VERSION.SDK_INT >= 26 && location.hasVerticalAccuracy()) {
            hashMap.put("verticalAccuracy", Float.valueOf(location.getVerticalAccuracyMeters()));
        }
        hashMap.put("latitude", Double.valueOf(location.getLatitude()));
        hashMap.put("longitude", Double.valueOf(location.getLongitude()));
        hashMap.put("ltime", Long.valueOf(location.getTime()));
        hashMap.put("provider", location.getProvider());
        hashMap.put("altitude", Double.valueOf(location.getAltitude()));
        hashMap.put("bearing", Float.valueOf(location.getBearing()));
        hashMap.put("speed", Float.valueOf(location.getSpeed()));
        String ssid = this.f1973f.getSSID();
        String bssid = this.f1973f.getBssid();
        if (!TextUtils.isEmpty(bssid)) {
            hashMap.put("cur_bssid", bssid);
        }
        if (!TextUtils.isEmpty(ssid)) {
            hashMap.put("cur_ssid", ssid);
        }
        return hashMap;
    }

    /* renamed from: c */
    private void m2072c(int i) {
        if (this.f1971d == null) {
            this.f1971d = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (C2237k.this.f1976i && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                        boolean unused = C2237k.this.f1976i = false;
                        C2237k.this.mo28999b(8);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            try {
                ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), "registerReceiver", new Object[]{this.f1971d, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class});
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: o */
    private void m2081o() {
        try {
            ArrayList<HashMap<String, Object>> u = m2087u();
            if (u != null && !u.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                Iterator<HashMap<String, Object>> it = u.iterator();
                while (it.hasNext()) {
                    Object obj = it.next().get("BSSID");
                    if (obj != null) {
                        arrayList.add(String.valueOf(obj));
                    }
                }
                Collections.sort(arrayList);
                String MD5 = Data.MD5(TextUtils.join("", arrayList));
                String i = C2308i.m2544i();
                long p = C2308i.m2559p();
                long a = C2262b.m2260a();
                NLog instance = MobLog.getInstance();
                instance.mo29768d("wiHashLast: " + i, new Object[0]);
                NLog instance2 = MobLog.getInstance();
                instance2.mo29768d("wiHash: " + MD5, new Object[0]);
                if (i == null || !i.equals(MD5) || p < a) {
                    m2066a(u);
                    C2308i.m2540g(MD5);
                    C2308i.m2535f(C2262b.m2260a() + ((long) (C2262b.m2324s() * 1000)));
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: a */
    private void m2066a(ArrayList<HashMap<String, Object>> arrayList) {
        synchronized (this) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "WIFI_SCAN_LIST");
                hashMap.put("list", arrayList);
                hashMap.put(Config.CELL_LOCATION, mo29002f());
                hashMap.put("datetime", Long.valueOf(C2262b.m2260a()));
                C2293c.m2435a().mo29068a(C2262b.m2260a(), (HashMap<String, Object>) hashMap);
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
    }

    /* renamed from: p */
    private void m2082p() {
        if (this.f1971d != null) {
            try {
                ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), "unregisterReceiver", new Object[]{this.f1971d}, new Class[]{BroadcastReceiver.class});
            } catch (Throwable unused) {
            }
            this.f1971d = null;
        }
    }

    /* renamed from: b */
    private void m2070b(ArrayList<HashMap<String, Object>> arrayList) {
        this.f1975h = false;
        if (C2262b.m2318m()) {
            try {
                m2067a(arrayList, 2);
                m2067a(arrayList, 1);
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
        mo28997a(5, (long) (C2262b.m2319n() * 1000));
    }

    /* renamed from: q */
    private void m2083q() {
        m2084r();
        this.f1975h = true;
        this.f1973f.scanWifiList();
    }

    /* renamed from: r */
    private void m2084r() {
        if (this.f1972e == null) {
            this.f1972e = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (C2237k.this.f1975h && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                        C2237k.this.mo28999b(11);
                        C2237k.this.m2085s();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            try {
                ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), "registerReceiver", new Object[]{this.f1972e, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class});
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: s */
    public void m2085s() {
        if (this.f1972e != null) {
            try {
                ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), "unregisterReceiver", new Object[]{this.f1972e}, new Class[]{BroadcastReceiver.class});
            } catch (Throwable unused) {
            }
            this.f1972e = null;
        }
    }

    /* renamed from: t */
    private void m2086t() {
        try {
            mo28996a(10);
            if (!this.f1974g) {
                m2070b(m2087u());
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: u */
    private ArrayList<HashMap<String, Object>> m2087u() {
        ArrayList<String> t;
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        try {
            ArrayList<HashMap<String, Object>> availableWifiList = this.f1973f.getAvailableWifiList();
            if (!(availableWifiList == null || availableWifiList.isEmpty() || (t = C2262b.m2325t()) == null)) {
                if (!t.isEmpty()) {
                    String bssid = this.f1973f.getBssid();
                    Iterator<HashMap<String, Object>> it = availableWifiList.iterator();
                    while (it.hasNext()) {
                        HashMap next = it.next();
                        Object obj = next.get("BSSID");
                        if (obj != null && String.valueOf(obj).equals(bssid)) {
                            next.put("___curConn", true);
                            bssid = null;
                        }
                        HashMap hashMap = new HashMap();
                        Iterator<String> it2 = t.iterator();
                        while (it2.hasNext()) {
                            String next2 = it2.next();
                            Object obj2 = next.get(next2);
                            if (obj2 != null) {
                                hashMap.put(next2, obj2);
                            }
                        }
                        arrayList.add(hashMap);
                    }
                    return arrayList;
                }
            }
            return arrayList;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: v */
    private Hashon m2088v() {
        if (this.f1968a == null) {
            this.f1968a = new Hashon();
        }
        return this.f1968a;
    }
}
