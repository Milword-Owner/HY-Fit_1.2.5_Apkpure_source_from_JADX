package com.mob.commons;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.p024b.C2273d;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.MobRSA;
import com.mob.tools.utils.ResHelper;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

/* renamed from: com.mob.commons.l */
/* compiled from: TokenFetcher */
public class C2313l {

    /* renamed from: a */
    private static final String f2145a = (C2310j.m2572b() + "/openid");

    /* renamed from: b */
    private static C2313l f2146b;

    /* renamed from: c */
    private String f2147c;

    /* renamed from: d */
    private DeviceHelper f2148d = DeviceHelper.getInstance(this.f2149e);

    /* renamed from: e */
    private Context f2149e = MobSDK.getContext();

    /* renamed from: f */
    private TreeMap<String, Object> f2150f;

    private C2313l() {
    }

    /* renamed from: a */
    public static C2313l m2576a() {
        if (f2146b == null) {
            synchronized (C2313l.class) {
                if (f2146b == null) {
                    f2146b = new C2313l();
                }
            }
        }
        return f2146b;
    }

    /* renamed from: b */
    public String mo29104b() {
        if (!C2262b.m2287al()) {
            return null;
        }
        NLog instance = MobLog.getInstance();
        instance.mo29768d("[%s] %s", "TokenFetcher", "Mem token: " + this.f2147c);
        if (TextUtils.isEmpty(this.f2147c)) {
            synchronized (C2313l.class) {
                if (TextUtils.isEmpty(this.f2147c)) {
                    String c = m2585c();
                    return c;
                }
            }
        }
        return this.f2147c;
    }

    /* renamed from: c */
    private String m2585c() {
        this.f2150f = new TreeMap<>();
        String str = null;
        try {
            String k = m2594k();
            boolean a = m2581a(m2595l());
            NLog instance = MobLog.getInstance();
            instance.mo29768d("[%s] %s", "TokenFetcher", "cachedToken: " + k);
            if (TextUtils.isEmpty(k)) {
                str = m2578a(this.f2150f);
            } else {
                NLog instance2 = MobLog.getInstance();
                instance2.mo29768d("[%s] %s", "TokenFetcher", "isChanged: " + a);
                if (!a) {
                    str = k;
                } else {
                    str = m2578a(this.f2150f);
                }
            }
            f2146b.f2147c = str;
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "TokenFetcher", "Sync token from cache & net error");
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:194:0x0447 A[Catch:{ Throwable -> 0x045a }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01d7 A[Catch:{ Throwable -> 0x045a }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m2581a(java.util.HashMap<java.lang.String, java.lang.Object> r19) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            java.lang.String r2 = "pkg"
            java.lang.String r3 = "TokenFetcher"
            java.lang.String r4 = "[%s] %s"
            r6 = 0
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "factory"
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String r10 = r10.getManufacturer()     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "model"
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String r10 = r10.getModel()     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "sysver"
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            int r10 = r10.getOSVersionInt()     // Catch:{ Throwable -> 0x045a }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "imei"
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String r10 = r10.getIMEI()     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "serialNo"
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String r10 = r10.getSerialno()     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "adsid"
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String r10 = r10.getAdvertisingID()     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            com.mob.tools.utils.DeviceHelper r9 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = r9.getPackageName()     // Catch:{ Throwable -> 0x045a }
            r8.put(r2, r9)     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "appver"
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String r10 = r10.getAppVersionName()     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            long r8 = com.mob.commons.C2308i.m2502G()     // Catch:{ Throwable -> 0x045a }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x008a
            java.util.TreeMap<java.lang.String, java.lang.Object> r10 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r11 = "firstLaunchTime"
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ Throwable -> 0x045a }
            r10.put(r11, r8)     // Catch:{ Throwable -> 0x045a }
        L_0x008a:
            long r8 = r18.m2587d()     // Catch:{ Throwable -> 0x045a }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x009f
            java.util.TreeMap<java.lang.String, java.lang.Object> r10 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r11 = "appInstallTime"
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ Throwable -> 0x045a }
            r10.put(r11, r8)     // Catch:{ Throwable -> 0x045a }
        L_0x009f:
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "deviceId"
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String r10 = r10.getDeviceKey()     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "duid"
            java.lang.String r10 = com.mob.commons.authorize.DeviceAuthorizer.authorizeForOnce()     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r8 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = "androidId"
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String r10 = r10.getAndroidID()     // Catch:{ Throwable -> 0x045a }
            r8.put(r9, r10)     // Catch:{ Throwable -> 0x045a }
            java.lang.String r8 = r18.m2588e()     // Catch:{ Throwable -> 0x045a }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Throwable -> 0x045a }
            if (r9 != 0) goto L_0x00d5
            java.util.TreeMap<java.lang.String, java.lang.Object> r9 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r10 = "mdId"
            r9.put(r10, r8)     // Catch:{ Throwable -> 0x045a }
        L_0x00d5:
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ Throwable -> 0x045a }
            java.util.TreeMap<java.lang.String, java.lang.Object> r9 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            r8.<init>(r9)     // Catch:{ Throwable -> 0x045a }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x045a }
            java.lang.String r8 = com.mob.tools.utils.Data.MD5((java.lang.String) r8)     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = r18.m2591h()     // Catch:{ Throwable -> 0x045a }
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Throwable -> 0x045a }
            if (r10 != 0) goto L_0x00f5
            java.util.TreeMap<java.lang.String, java.lang.Object> r10 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r11 = "moId"
            r10.put(r11, r9)     // Catch:{ Throwable -> 0x045a }
        L_0x00f5:
            java.lang.String r9 = r18.m2592i()     // Catch:{ Throwable -> 0x045a }
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Throwable -> 0x045a }
            if (r10 != 0) goto L_0x0106
            java.util.TreeMap<java.lang.String, java.lang.Object> r10 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r11 = "mvaId"
            r10.put(r11, r9)     // Catch:{ Throwable -> 0x045a }
        L_0x0106:
            if (r0 == 0) goto L_0x0117
            boolean r9 = r19.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r9 != 0) goto L_0x0117
            java.lang.String r9 = "maaid"
            java.lang.Object r9 = r0.get(r9)     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Throwable -> 0x045a }
            goto L_0x011b
        L_0x0117:
            java.lang.String r9 = r18.m2593j()     // Catch:{ Throwable -> 0x045a }
        L_0x011b:
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Throwable -> 0x045a }
            if (r10 != 0) goto L_0x0128
            java.util.TreeMap<java.lang.String, java.lang.Object> r10 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            java.lang.String r11 = "maaId"
            r10.put(r11, r9)     // Catch:{ Throwable -> 0x045a }
        L_0x0128:
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.util.HashMap r10 = r10.listNetworkHardware()     // Catch:{ Throwable -> 0x045a }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Throwable -> 0x045a }
            r11.<init>()     // Catch:{ Throwable -> 0x045a }
            java.lang.String r12 = "mac"
            java.lang.String r13 = "macArray"
            if (r10 == 0) goto L_0x0171
            boolean r14 = r10.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r14 != 0) goto L_0x0171
            java.util.Set r10 = r10.entrySet()     // Catch:{ Throwable -> 0x045a }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ Throwable -> 0x045a }
        L_0x0147:
            boolean r14 = r10.hasNext()     // Catch:{ Throwable -> 0x045a }
            if (r14 == 0) goto L_0x016c
            java.lang.Object r14 = r10.next()     // Catch:{ Throwable -> 0x045a }
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14     // Catch:{ Throwable -> 0x045a }
            java.util.HashMap r15 = new java.util.HashMap     // Catch:{ Throwable -> 0x045a }
            r15.<init>()     // Catch:{ Throwable -> 0x045a }
            java.lang.String r7 = "ss"
            java.lang.Object r5 = r14.getKey()     // Catch:{ Throwable -> 0x045a }
            r15.put(r7, r5)     // Catch:{ Throwable -> 0x045a }
            java.lang.Object r5 = r14.getValue()     // Catch:{ Throwable -> 0x045a }
            r15.put(r12, r5)     // Catch:{ Throwable -> 0x045a }
            r11.add(r15)     // Catch:{ Throwable -> 0x045a }
            goto L_0x0147
        L_0x016c:
            java.util.TreeMap<java.lang.String, java.lang.Object> r5 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            r5.put(r13, r11)     // Catch:{ Throwable -> 0x045a }
        L_0x0171:
            com.mob.tools.utils.DeviceHelper r5 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String[] r5 = r5.queryIMEI()     // Catch:{ Throwable -> 0x045a }
            java.lang.String r7 = "imeiArray"
            if (r5 == 0) goto L_0x0183
            int r10 = r5.length     // Catch:{ Throwable -> 0x045a }
            if (r10 <= 0) goto L_0x0183
            java.util.TreeMap<java.lang.String, java.lang.Object> r10 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            r10.put(r7, r5)     // Catch:{ Throwable -> 0x045a }
        L_0x0183:
            com.mob.tools.utils.DeviceHelper r10 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.lang.String[] r10 = r10.queryIMSI()     // Catch:{ Throwable -> 0x045a }
            java.lang.String r14 = "imsiArray"
            if (r10 == 0) goto L_0x0195
            int r15 = r10.length     // Catch:{ Throwable -> 0x045a }
            if (r15 <= 0) goto L_0x0195
            java.util.TreeMap<java.lang.String, java.lang.Object> r15 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            r15.put(r14, r10)     // Catch:{ Throwable -> 0x045a }
        L_0x0195:
            com.mob.tools.utils.DeviceHelper r15 = r1.f2148d     // Catch:{ Throwable -> 0x045a }
            java.util.ArrayList r15 = r15.getIA(r6)     // Catch:{ Throwable -> 0x045a }
            java.lang.String r6 = "al"
            if (r15 == 0) goto L_0x01ad
            boolean r16 = r15.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r16 != 0) goto L_0x01ad
            r16 = r2
            java.util.TreeMap<java.lang.String, java.lang.Object> r2 = r1.f2150f     // Catch:{ Throwable -> 0x045a }
            r2.put(r6, r15)     // Catch:{ Throwable -> 0x045a }
            goto L_0x01af
        L_0x01ad:
            r16 = r2
        L_0x01af:
            java.util.TreeMap r2 = new java.util.TreeMap     // Catch:{ Throwable -> 0x045a }
            r2.<init>()     // Catch:{ Throwable -> 0x045a }
            r17 = r12
            java.lang.String r12 = "generalMd5"
            r2.put(r12, r8)     // Catch:{ Throwable -> 0x045a }
            java.lang.String r12 = "maaid"
            r2.put(r12, r9)     // Catch:{ Throwable -> 0x045a }
            r2.put(r13, r11)     // Catch:{ Throwable -> 0x045a }
            r2.put(r7, r5)     // Catch:{ Throwable -> 0x045a }
            r2.put(r14, r10)     // Catch:{ Throwable -> 0x045a }
            r2.put(r6, r15)     // Catch:{ Throwable -> 0x045a }
            r1.m2584b((java.util.TreeMap<java.lang.String, java.lang.Object>) r2)     // Catch:{ Throwable -> 0x045a }
            if (r0 == 0) goto L_0x0447
            boolean r2 = r19.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r2 != 0) goto L_0x0447
            java.lang.String r2 = "generalMd5"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x045a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x045a }
            boolean r2 = r8.equals(r2)     // Catch:{ Throwable -> 0x045a }
            if (r2 != 0) goto L_0x01f8
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r5[r2] = r3     // Catch:{ Throwable -> 0x045a }
            java.lang.String r2 = "generalMd5 changed"
            r6 = 1
            r5[r6] = r2     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r5)     // Catch:{ Throwable -> 0x045a }
            return r6
        L_0x01f8:
            java.lang.Object r2 = r0.get(r13)     // Catch:{ Throwable -> 0x045a }
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ Throwable -> 0x045a }
            boolean r8 = r11.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r8 != 0) goto L_0x02b6
            java.lang.String r8 = "macArray changed"
            if (r2 == 0) goto L_0x02a5
            boolean r9 = r2.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r9 == 0) goto L_0x0210
            goto L_0x02a5
        L_0x0210:
            int r9 = r2.size()     // Catch:{ Throwable -> 0x045a }
            int r12 = r11.size()     // Catch:{ Throwable -> 0x045a }
            if (r9 == r12) goto L_0x022b
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r5[r2] = r3     // Catch:{ Throwable -> 0x045a }
            r2 = 1
            r5[r2] = r8     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r5)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x022b:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Throwable -> 0x045a }
            r9.<init>()     // Catch:{ Throwable -> 0x045a }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ Throwable -> 0x045a }
        L_0x0234:
            boolean r12 = r11.hasNext()     // Catch:{ Throwable -> 0x045a }
            if (r12 == 0) goto L_0x0257
            java.lang.Object r12 = r11.next()     // Catch:{ Throwable -> 0x045a }
            java.util.HashMap r12 = (java.util.HashMap) r12     // Catch:{ Throwable -> 0x045a }
            if (r12 == 0) goto L_0x0252
            boolean r13 = r12.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r13 != 0) goto L_0x0252
            r13 = r17
            java.lang.Object r12 = r12.get(r13)     // Catch:{ Throwable -> 0x045a }
            r9.add(r12)     // Catch:{ Throwable -> 0x045a }
            goto L_0x0254
        L_0x0252:
            r13 = r17
        L_0x0254:
            r17 = r13
            goto L_0x0234
        L_0x0257:
            r13 = r17
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Throwable -> 0x045a }
            r11.<init>()     // Catch:{ Throwable -> 0x045a }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Throwable -> 0x045a }
        L_0x0262:
            boolean r12 = r2.hasNext()     // Catch:{ Throwable -> 0x045a }
            if (r12 == 0) goto L_0x027e
            java.lang.Object r12 = r2.next()     // Catch:{ Throwable -> 0x045a }
            java.util.HashMap r12 = (java.util.HashMap) r12     // Catch:{ Throwable -> 0x045a }
            if (r12 == 0) goto L_0x0262
            boolean r17 = r12.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r17 != 0) goto L_0x0262
            java.lang.Object r12 = r12.get(r13)     // Catch:{ Throwable -> 0x045a }
            r11.add(r12)     // Catch:{ Throwable -> 0x045a }
            goto L_0x0262
        L_0x027e:
            java.util.Iterator r2 = r9.iterator()     // Catch:{ Throwable -> 0x045a }
        L_0x0282:
            boolean r9 = r2.hasNext()     // Catch:{ Throwable -> 0x045a }
            if (r9 == 0) goto L_0x02b6
            java.lang.Object r9 = r2.next()     // Catch:{ Throwable -> 0x045a }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Throwable -> 0x045a }
            boolean r9 = r11.contains(r9)     // Catch:{ Throwable -> 0x045a }
            if (r9 != 0) goto L_0x0282
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r5[r2] = r3     // Catch:{ Throwable -> 0x045a }
            r2 = 1
            r5[r2] = r8     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r5)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x02a5:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r5[r2] = r3     // Catch:{ Throwable -> 0x045a }
            r2 = 1
            r5[r2] = r8     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r5)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x02b6:
            java.lang.Object r2 = r0.get(r7)     // Catch:{ Throwable -> 0x045a }
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ Throwable -> 0x045a }
            if (r5 == 0) goto L_0x0315
            int r7 = r5.length     // Catch:{ Throwable -> 0x045a }
            if (r7 <= 0) goto L_0x0315
            java.lang.String r7 = "imeiArray changed"
            if (r2 == 0) goto L_0x0304
            boolean r8 = r2.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r8 == 0) goto L_0x02cc
            goto L_0x0304
        L_0x02cc:
            int r8 = r2.size()     // Catch:{ Throwable -> 0x045a }
            int r9 = r5.length     // Catch:{ Throwable -> 0x045a }
            if (r8 == r9) goto L_0x02e4
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r5[r2] = r3     // Catch:{ Throwable -> 0x045a }
            r2 = 1
            r5[r2] = r7     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r5)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x02e4:
            int r8 = r5.length     // Catch:{ Throwable -> 0x045a }
            r9 = 0
        L_0x02e6:
            if (r9 >= r8) goto L_0x0315
            r11 = r5[r9]     // Catch:{ Throwable -> 0x045a }
            boolean r11 = r2.contains(r11)     // Catch:{ Throwable -> 0x045a }
            if (r11 != 0) goto L_0x0301
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r5[r2] = r3     // Catch:{ Throwable -> 0x045a }
            r2 = 1
            r5[r2] = r7     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r5)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x0301:
            int r9 = r9 + 1
            goto L_0x02e6
        L_0x0304:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r5[r2] = r3     // Catch:{ Throwable -> 0x045a }
            r2 = 1
            r5[r2] = r7     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r5)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x0315:
            java.lang.Object r2 = r0.get(r14)     // Catch:{ Throwable -> 0x045a }
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ Throwable -> 0x045a }
            if (r10 == 0) goto L_0x0374
            int r5 = r10.length     // Catch:{ Throwable -> 0x045a }
            if (r5 <= 0) goto L_0x0374
            java.lang.String r5 = "imsiArray changed"
            if (r2 == 0) goto L_0x0363
            boolean r7 = r2.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r7 == 0) goto L_0x032b
            goto L_0x0363
        L_0x032b:
            int r7 = r2.size()     // Catch:{ Throwable -> 0x045a }
            int r8 = r10.length     // Catch:{ Throwable -> 0x045a }
            if (r7 == r8) goto L_0x0343
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r6[r2] = r3     // Catch:{ Throwable -> 0x045a }
            r2 = 1
            r6[r2] = r5     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r6)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x0343:
            int r7 = r10.length     // Catch:{ Throwable -> 0x045a }
            r8 = 0
        L_0x0345:
            if (r8 >= r7) goto L_0x0374
            r9 = r10[r8]     // Catch:{ Throwable -> 0x045a }
            boolean r9 = r2.contains(r9)     // Catch:{ Throwable -> 0x045a }
            if (r9 != 0) goto L_0x0360
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r6[r2] = r3     // Catch:{ Throwable -> 0x045a }
            r2 = 1
            r6[r2] = r5     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r6)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x0360:
            int r8 = r8 + 1
            goto L_0x0345
        L_0x0363:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r6[r2] = r3     // Catch:{ Throwable -> 0x045a }
            r2 = 1
            r6[r2] = r5     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r6)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x0374:
            java.lang.Object r0 = r0.get(r6)     // Catch:{ Throwable -> 0x045a }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x045a }
            if (r15 == 0) goto L_0x0434
            boolean r2 = r15.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r2 != 0) goto L_0x0434
            java.lang.String r2 = "al changed"
            if (r0 == 0) goto L_0x0423
            boolean r5 = r0.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r5 == 0) goto L_0x038e
            goto L_0x0423
        L_0x038e:
            int r5 = r0.size()     // Catch:{ Throwable -> 0x045a }
            int r6 = r15.size()     // Catch:{ Throwable -> 0x045a }
            if (r5 == r6) goto L_0x03a9
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x045a }
            r5 = 0
            r6[r5] = r3     // Catch:{ Throwable -> 0x045a }
            r5 = 1
            r6[r5] = r2     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r6)     // Catch:{ Throwable -> 0x045a }
            return r5
        L_0x03a9:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Throwable -> 0x045a }
            r5.<init>()     // Catch:{ Throwable -> 0x045a }
            java.util.Iterator r6 = r15.iterator()     // Catch:{ Throwable -> 0x045a }
        L_0x03b2:
            boolean r7 = r6.hasNext()     // Catch:{ Throwable -> 0x045a }
            if (r7 == 0) goto L_0x03d5
            java.lang.Object r7 = r6.next()     // Catch:{ Throwable -> 0x045a }
            java.util.HashMap r7 = (java.util.HashMap) r7     // Catch:{ Throwable -> 0x045a }
            if (r7 == 0) goto L_0x03d0
            boolean r8 = r7.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r8 != 0) goto L_0x03d0
            r8 = r16
            java.lang.Object r7 = r7.get(r8)     // Catch:{ Throwable -> 0x045a }
            r5.add(r7)     // Catch:{ Throwable -> 0x045a }
            goto L_0x03d2
        L_0x03d0:
            r8 = r16
        L_0x03d2:
            r16 = r8
            goto L_0x03b2
        L_0x03d5:
            r8 = r16
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Throwable -> 0x045a }
            r6.<init>()     // Catch:{ Throwable -> 0x045a }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Throwable -> 0x045a }
        L_0x03e0:
            boolean r7 = r0.hasNext()     // Catch:{ Throwable -> 0x045a }
            if (r7 == 0) goto L_0x03fc
            java.lang.Object r7 = r0.next()     // Catch:{ Throwable -> 0x045a }
            java.util.HashMap r7 = (java.util.HashMap) r7     // Catch:{ Throwable -> 0x045a }
            if (r7 == 0) goto L_0x03e0
            boolean r9 = r7.isEmpty()     // Catch:{ Throwable -> 0x045a }
            if (r9 != 0) goto L_0x03e0
            java.lang.Object r7 = r7.get(r8)     // Catch:{ Throwable -> 0x045a }
            r6.add(r7)     // Catch:{ Throwable -> 0x045a }
            goto L_0x03e0
        L_0x03fc:
            java.util.Iterator r0 = r5.iterator()     // Catch:{ Throwable -> 0x045a }
        L_0x0400:
            boolean r5 = r0.hasNext()     // Catch:{ Throwable -> 0x045a }
            if (r5 == 0) goto L_0x0434
            java.lang.Object r5 = r0.next()     // Catch:{ Throwable -> 0x045a }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Throwable -> 0x045a }
            boolean r5 = r6.contains(r5)     // Catch:{ Throwable -> 0x045a }
            if (r5 != 0) goto L_0x0400
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x045a }
            r5 = 0
            r6[r5] = r3     // Catch:{ Throwable -> 0x045a }
            r5 = 1
            r6[r5] = r2     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r6)     // Catch:{ Throwable -> 0x045a }
            return r5
        L_0x0423:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x045a }
            r5 = 0
            r6[r5] = r3     // Catch:{ Throwable -> 0x045a }
            r5 = 1
            r6[r5] = r2     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r6)     // Catch:{ Throwable -> 0x045a }
            return r5
        L_0x0434:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r5[r2] = r3     // Catch:{ Throwable -> 0x045a }
            java.lang.String r6 = "No changes"
            r7 = 1
            r5[r7] = r6     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r5)     // Catch:{ Throwable -> 0x045a }
            return r2
        L_0x0447:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x045a }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x045a }
            r2 = 0
            r5[r2] = r3     // Catch:{ Throwable -> 0x045a }
            java.lang.String r2 = "No openids cache, treat as changed"
            r6 = 1
            r5[r6] = r2     // Catch:{ Throwable -> 0x045a }
            r0.mo29768d(r4, r5)     // Catch:{ Throwable -> 0x045a }
            return r6
        L_0x045a:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r5[r6] = r3
            java.lang.String r3 = "Fetch token from server error."
            r7 = 1
            r5[r7] = r3
            r2.mo29773e(r0, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2313l.m2581a(java.util.HashMap):boolean");
    }

    /* renamed from: d */
    private long m2587d() {
        PackageInfo packageInfo;
        try {
            PackageManager packageManager = this.f2149e.getPackageManager();
            if (packageManager == null || (packageInfo = packageManager.getPackageInfo(this.f2148d.getPackageName(), 0)) == null || Build.VERSION.SDK_INT < 9) {
                return 0;
            }
            return packageInfo.firstInstallTime;
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "TokenFetcher", "Get ins time error");
            return 0;
        }
    }

    /* renamed from: e */
    private String m2588e() {
        String f = m2589f();
        if (TextUtils.isEmpty(f) && this.f2148d.getSdcardState()) {
            f = m2590g();
            if (!TextUtils.isEmpty(f)) {
                m2580a(f);
            }
        }
        return f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005a A[SYNTHETIC, Splitter:B:24:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0073 A[SYNTHETIC, Splitter:B:31:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m2589f() {
        /*
            r13 = this;
            java.lang.String r0 = "Close stream error"
            java.lang.String r1 = "TokenFetcher"
            java.lang.String r2 = "[%s] %s"
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 2
            android.content.Context r7 = r13.f2149e     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            java.lang.String r8 = ".mdid"
            java.io.File r7 = r13.m2577a((android.content.Context) r7, (java.lang.String) r8)     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            if (r7 == 0) goto L_0x002e
            boolean r8 = r7.exists()     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            if (r8 == 0) goto L_0x002e
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            r8.<init>(r7)     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            java.io.DataInputStream r7 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            r7.<init>(r8)     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            java.lang.String r3 = r7.readUTF()     // Catch:{ Throwable -> 0x002c }
            r12 = r7
            r7 = r3
            r3 = r12
            goto L_0x002f
        L_0x002c:
            r8 = move-exception
            goto L_0x0049
        L_0x002e:
            r7 = r3
        L_0x002f:
            if (r3 == 0) goto L_0x0043
            r3.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0043
        L_0x0035:
            r3 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r1
            r6[r4] = r0
            r8.mo29770d(r3, r2, r6)
        L_0x0043:
            r3 = r7
            goto L_0x006c
        L_0x0045:
            r7 = move-exception
            goto L_0x0071
        L_0x0047:
            r8 = move-exception
            r7 = r3
        L_0x0049:
            com.mob.tools.log.NLog r9 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x006d }
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch:{ all -> 0x006d }
            r10[r5] = r1     // Catch:{ all -> 0x006d }
            java.lang.String r11 = "Read mdid cache error"
            r10[r4] = r11     // Catch:{ all -> 0x006d }
            r9.mo29770d(r8, r2, r10)     // Catch:{ all -> 0x006d }
            if (r7 == 0) goto L_0x006c
            r7.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x006c
        L_0x005e:
            r7 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r1
            r6[r4] = r0
            r8.mo29770d(r7, r2, r6)
        L_0x006c:
            return r3
        L_0x006d:
            r3 = move-exception
            r12 = r7
            r7 = r3
            r3 = r12
        L_0x0071:
            if (r3 == 0) goto L_0x0085
            r3.close()     // Catch:{ IOException -> 0x0077 }
            goto L_0x0085
        L_0x0077:
            r3 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r1
            r6[r4] = r0
            r8.mo29770d(r3, r2, r6)
        L_0x0085:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2313l.m2589f():java.lang.String");
    }

    /* renamed from: g */
    private String m2590g() {
        try {
            return Data.byteToHex(Data.SHA1(UUID.randomUUID().toString()));
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "TokenFetcher", "Generate mdid error");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005b A[SYNTHETIC, Splitter:B:24:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0073 A[SYNTHETIC, Splitter:B:29:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2580a(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = "Close stream error"
            java.lang.String r1 = "TokenFetcher"
            java.lang.String r2 = "[%s] %s"
            r3 = 1
            r4 = 0
            r5 = 2
            r6 = 0
            android.content.Context r7 = r10.f2149e     // Catch:{ Throwable -> 0x0049 }
            java.lang.String r8 = ".mdid"
            java.io.File r7 = r10.m2577a((android.content.Context) r7, (java.lang.String) r8)     // Catch:{ Throwable -> 0x0049 }
            if (r7 == 0) goto L_0x002f
            boolean r8 = r7.exists()     // Catch:{ Throwable -> 0x0049 }
            if (r8 == 0) goto L_0x002f
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0049 }
            r8.<init>(r7)     // Catch:{ Throwable -> 0x0049 }
            java.io.DataOutputStream r7 = new java.io.DataOutputStream     // Catch:{ Throwable -> 0x0049 }
            r7.<init>(r8)     // Catch:{ Throwable -> 0x0049 }
            r7.writeUTF(r11)     // Catch:{ Throwable -> 0x002c, all -> 0x0029 }
            r6 = r7
            goto L_0x002f
        L_0x0029:
            r11 = move-exception
            r6 = r7
            goto L_0x0071
        L_0x002c:
            r11 = move-exception
            r6 = r7
            goto L_0x004a
        L_0x002f:
            if (r6 == 0) goto L_0x0070
            r6.flush()     // Catch:{ IOException -> 0x0038 }
            r6.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0070
        L_0x0038:
            r11 = move-exception
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r1
            r5[r3] = r0
            r6.mo29770d(r11, r2, r5)
            goto L_0x0070
        L_0x0047:
            r11 = move-exception
            goto L_0x0071
        L_0x0049:
            r11 = move-exception
        L_0x004a:
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0047 }
            java.lang.Object[] r8 = new java.lang.Object[r5]     // Catch:{ all -> 0x0047 }
            r8[r4] = r1     // Catch:{ all -> 0x0047 }
            java.lang.String r9 = "Cache mdid error"
            r8[r3] = r9     // Catch:{ all -> 0x0047 }
            r7.mo29770d(r11, r2, r8)     // Catch:{ all -> 0x0047 }
            if (r6 == 0) goto L_0x0070
            r6.flush()     // Catch:{ IOException -> 0x0062 }
            r6.close()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0070
        L_0x0062:
            r11 = move-exception
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r1
            r5[r3] = r0
            r6.mo29770d(r11, r2, r5)
        L_0x0070:
            return
        L_0x0071:
            if (r6 == 0) goto L_0x0088
            r6.flush()     // Catch:{ IOException -> 0x007a }
            r6.close()     // Catch:{ IOException -> 0x007a }
            goto L_0x0088
        L_0x007a:
            r6 = move-exception
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r1
            r5[r3] = r0
            r7.mo29770d(r6, r2, r5)
        L_0x0088:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2313l.m2580a(java.lang.String):void");
    }

    /* renamed from: h */
    private String m2591h() {
        return C2273d.m2353c(this.f2149e);
    }

    /* renamed from: i */
    private String m2592i() {
        try {
            String authorizeForOnce = DeviceAuthorizer.authorizeForOnce();
            String signMD5 = this.f2148d.getSignMD5();
            return Data.byteToHex(Data.SHA1(authorizeForOnce + signMD5));
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "TokenFetcher", "Generate mvaid error");
            return null;
        }
    }

    /* renamed from: j */
    private String m2593j() {
        try {
            String packageName = this.f2148d.getPackageName();
            String uuid = UUID.randomUUID().toString();
            return Data.byteToHex(Data.SHA1(packageName + uuid));
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "TokenFetcher", "Generate mvaid error");
            return null;
        }
    }

    /* renamed from: a */
    private File m2577a(Context context, String str) {
        try {
            if ((Build.VERSION.SDK_INT >= 29 && context.getApplicationInfo().targetSdkVersion >= 29) || !this.f2148d.getSdcardState()) {
                return null;
            }
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (TextUtils.isEmpty(absolutePath)) {
                return null;
            }
            String str2 = absolutePath + "/Mob/";
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(str2, str);
            if (!file2.exists()) {
                file2.createNewFile();
            }
            return file2;
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "TokenFetcher", "Get MDID error");
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m2578a(java.util.TreeMap<java.lang.String, java.lang.Object> r18) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            java.lang.String r2 = "appInstallTime"
            java.lang.String r3 = "firstLaunchTime"
            java.lang.String r4 = "appver"
            java.lang.String r5 = "pkg"
            java.lang.String r6 = "adsid"
            java.lang.String r7 = "serialNo"
            java.lang.String r8 = "imei"
            java.lang.String r9 = "sysver"
            java.lang.String r10 = "model"
            java.lang.String r11 = "factory"
            java.lang.String r12 = "TokenFetcher"
            java.lang.String r13 = "[%s] %s"
            if (r0 == 0) goto L_0x01e0
            boolean r15 = r18.isEmpty()
            if (r15 != 0) goto L_0x01e0
            r16 = 0
            java.util.HashMap r15 = new java.util.HashMap     // Catch:{ Throwable -> 0x01cc }
            r15.<init>()     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r14 = r0.get(r11)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r11, r14)     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r11 = r0.get(r10)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r10, r11)     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r10 = r0.get(r9)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r9, r10)     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r9 = r0.get(r8)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r8, r9)     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r8 = r0.get(r7)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r7, r8)     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r7 = r0.get(r6)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r6, r7)     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r6 = r0.get(r5)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r5, r6)     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r5 = r0.get(r4)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r4, r5)     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r4 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r3, r4)     // Catch:{ Throwable -> 0x01cc }
            java.lang.Object r3 = r0.get(r2)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "deviceId"
            java.lang.String r3 = "deviceId"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "duid"
            java.lang.String r3 = "duid"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "mdId"
            java.lang.String r3 = "mdId"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "moId"
            java.lang.String r3 = "moId"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "mvaId"
            java.lang.String r3 = "mvaId"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "maaId"
            java.lang.String r3 = "maaId"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "androidId"
            java.lang.String r3 = "androidId"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "macArray"
            java.lang.String r3 = "macArray"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "imeiArray"
            java.lang.String r3 = "imeiArray"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "imsiArray"
            java.lang.String r3 = "imsiArray"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "al"
            java.lang.String r3 = "al"
            java.lang.Object r0 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            r15.put(r2, r0)     // Catch:{ Throwable -> 0x01cc }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01cc }
            r0.<init>()     // Catch:{ Throwable -> 0x01cc }
            com.mob.tools.network.KVPair r2 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r3 = "appkey"
            java.lang.String r4 = com.mob.MobSDK.getAppkey()     // Catch:{ Throwable -> 0x01cc }
            r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x01cc }
            r0.add(r2)     // Catch:{ Throwable -> 0x01cc }
            com.mob.tools.utils.Hashon r2 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x01cc }
            r2.<init>()     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = r2.fromHashMap(r15)     // Catch:{ Throwable -> 0x01cc }
            com.mob.tools.network.KVPair r3 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r4 = "m"
            java.lang.String r2 = r1.m2583b((java.lang.String) r2)     // Catch:{ Throwable -> 0x01cc }
            r3.<init>(r4, r2)     // Catch:{ Throwable -> 0x01cc }
            r0.add(r3)     // Catch:{ Throwable -> 0x01cc }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01cc }
            r2.<init>()     // Catch:{ Throwable -> 0x01cc }
            com.mob.tools.network.KVPair r3 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r4 = "User-Identity"
            java.lang.String r5 = com.mob.commons.MobProductCollector.getUserIdentity()     // Catch:{ Throwable -> 0x01cc }
            r3.<init>(r4, r5)     // Catch:{ Throwable -> 0x01cc }
            r2.add(r3)     // Catch:{ Throwable -> 0x01cc }
            com.mob.tools.network.KVPair r3 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x01cc }
            r4 = 68
            java.lang.String r4 = com.mob.commons.C2312k.m2575a(r4)     // Catch:{ Throwable -> 0x01cc }
            android.content.Context r5 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r5 = com.mob.commons.p024b.C2273d.m2354d(r5)     // Catch:{ Throwable -> 0x01cc }
            r3.<init>(r4, r5)     // Catch:{ Throwable -> 0x01cc }
            r2.add(r3)     // Catch:{ Throwable -> 0x01cc }
            com.mob.tools.network.NetworkHelper$NetworkTimeOut r8 = new com.mob.tools.network.NetworkHelper$NetworkTimeOut     // Catch:{ Throwable -> 0x01cc }
            r8.<init>()     // Catch:{ Throwable -> 0x01cc }
            r3 = 30000(0x7530, float:4.2039E-41)
            r8.readTimout = r3     // Catch:{ Throwable -> 0x01cc }
            r3 = 30000(0x7530, float:4.2039E-41)
            r8.connectionTimeout = r3     // Catch:{ Throwable -> 0x01cc }
            com.mob.tools.network.NetworkHelper r3 = new com.mob.tools.network.NetworkHelper     // Catch:{ Throwable -> 0x01cc }
            r3.<init>()     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r4 = f2145a     // Catch:{ Throwable -> 0x01cc }
            r6 = 0
            r5 = r0
            r7 = r2
            java.lang.String r3 = r3.httpPost((java.lang.String) r4, (java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>>) r5, (com.mob.tools.network.KVPair<java.lang.String>) r6, (java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>>) r7, (com.mob.tools.network.NetworkHelper.NetworkTimeOut) r8)     // Catch:{ Throwable -> 0x01cc }
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x01cc }
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x01cc }
            r6[r16] = r12     // Catch:{ Throwable -> 0x01cc }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01cc }
            r5.<init>()     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r7 = "Request: "
            r5.append(r7)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r7 = f2145a     // Catch:{ Throwable -> 0x01cc }
            r5.append(r7)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r7 = "\nvaluesEn: "
            r5.append(r7)     // Catch:{ Throwable -> 0x01cc }
            r5.append(r0)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r0 = "\nheaders: "
            r5.append(r0)     // Catch:{ Throwable -> 0x01cc }
            r5.append(r2)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r0 = "\nResponse: "
            r5.append(r0)     // Catch:{ Throwable -> 0x01cc }
            r5.append(r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r0 = r5.toString()     // Catch:{ Throwable -> 0x01cc }
            r2 = 1
            r6[r2] = r0     // Catch:{ Throwable -> 0x01cc }
            r4.mo29768d(r13, r6)     // Catch:{ Throwable -> 0x01cc }
            com.mob.tools.utils.Hashon r0 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x01cc }
            r0.<init>()     // Catch:{ Throwable -> 0x01cc }
            java.util.HashMap r0 = r0.fromJson((java.lang.String) r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r2 = "200"
            java.lang.String r3 = "code"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x01cc }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x01cc }
            boolean r2 = r2.equals(r3)     // Catch:{ Throwable -> 0x01cc }
            if (r2 == 0) goto L_0x01e0
            java.lang.String r2 = "data"
            java.lang.Object r0 = r0.get(r2)     // Catch:{ Throwable -> 0x01cc }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x01cc }
            if (r0 == 0) goto L_0x01e0
            java.lang.String r2 = "token"
            java.lang.Object r0 = r0.get(r2)     // Catch:{ Throwable -> 0x01cc }
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x01cc }
            com.mob.commons.l r0 = f2146b     // Catch:{ Throwable -> 0x01c9 }
            r0.f2147c = r2     // Catch:{ Throwable -> 0x01c9 }
            r1.m2586c(r2)     // Catch:{ Throwable -> 0x01c9 }
            r14 = r2
            goto L_0x01e1
        L_0x01c9:
            r0 = move-exception
            r14 = r2
            goto L_0x01ce
        L_0x01cc:
            r0 = move-exception
            r14 = 0
        L_0x01ce:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r16] = r12
            java.lang.String r4 = "Fetch token from server error."
            r5 = 1
            r3[r5] = r4
            r2.mo29773e(r0, r13, r3)
            goto L_0x01e1
        L_0x01e0:
            r14 = 0
        L_0x01e1:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2313l.m2578a(java.util.TreeMap):java.lang.String");
    }

    /* renamed from: b */
    private String m2583b(String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        Random random = new Random();
        dataOutputStream.writeLong(random.nextLong());
        dataOutputStream.writeLong(random.nextLong());
        dataOutputStream.flush();
        dataOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(byteArrayOutputStream2));
        bufferedOutputStream.write(str.getBytes("utf-8"));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        byte[] AES128Encode = Data.AES128Encode(byteArray, byteArrayOutputStream2.toByteArray());
        byte[] encode = new MobRSA(1024).encode(byteArray, new BigInteger("ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", 16), new BigInteger("191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd", 16));
        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream3);
        dataOutputStream2.writeInt(encode.length);
        dataOutputStream2.write(encode);
        dataOutputStream2.writeInt(AES128Encode.length);
        dataOutputStream2.write(AES128Encode);
        dataOutputStream2.flush();
        dataOutputStream2.close();
        return Base64.encodeToString(byteArrayOutputStream3.toByteArray(), 2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064 A[SYNTHETIC, Splitter:B:22:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007c A[SYNTHETIC, Splitter:B:27:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2586c(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = "Close stream error"
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = "TokenFetcher"
            r5 = 0
            r3[r5] = r4
            r6 = 1
            java.lang.String r7 = "Write token cache"
            r3[r6] = r7
            java.lang.String r7 = "[%s] %s"
            r1.mo29768d(r7, r3)
            r1 = 0
            android.content.Context r3 = r10.f2149e     // Catch:{ Throwable -> 0x0052 }
            java.lang.String r8 = ".optn"
            java.io.File r3 = com.mob.tools.utils.ResHelper.getDataCacheFile(r3, r8)     // Catch:{ Throwable -> 0x0052 }
            if (r3 == 0) goto L_0x0038
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0052 }
            r8.<init>(r3)     // Catch:{ Throwable -> 0x0052 }
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ Throwable -> 0x0052 }
            r3.<init>(r8)     // Catch:{ Throwable -> 0x0052 }
            r3.writeUTF(r11)     // Catch:{ Throwable -> 0x0035, all -> 0x0032 }
            r1 = r3
            goto L_0x0038
        L_0x0032:
            r11 = move-exception
            r1 = r3
            goto L_0x007a
        L_0x0035:
            r11 = move-exception
            r1 = r3
            goto L_0x0053
        L_0x0038:
            if (r1 == 0) goto L_0x0079
            r1.flush()     // Catch:{ IOException -> 0x0041 }
            r1.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0079
        L_0x0041:
            r11 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r5] = r4
            r2[r6] = r0
            r1.mo29770d(r11, r7, r2)
            goto L_0x0079
        L_0x0050:
            r11 = move-exception
            goto L_0x007a
        L_0x0052:
            r11 = move-exception
        L_0x0053:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0050 }
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ all -> 0x0050 }
            r8[r5] = r4     // Catch:{ all -> 0x0050 }
            java.lang.String r9 = "Cache token error"
            r8[r6] = r9     // Catch:{ all -> 0x0050 }
            r3.mo29770d(r11, r7, r8)     // Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x0079
            r1.flush()     // Catch:{ IOException -> 0x006b }
            r1.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x0079
        L_0x006b:
            r11 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r5] = r4
            r2[r6] = r0
            r1.mo29770d(r11, r7, r2)
        L_0x0079:
            return
        L_0x007a:
            if (r1 == 0) goto L_0x0091
            r1.flush()     // Catch:{ IOException -> 0x0083 }
            r1.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0091
        L_0x0083:
            r1 = move-exception
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r5] = r4
            r2[r6] = r0
            r3.mo29770d(r1, r7, r2)
        L_0x0091:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2313l.m2586c(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005a A[SYNTHETIC, Splitter:B:24:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0073 A[SYNTHETIC, Splitter:B:31:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* renamed from: k */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m2594k() {
        /*
            r13 = this;
            java.lang.String r0 = "Close stream error"
            java.lang.String r1 = "TokenFetcher"
            java.lang.String r2 = "[%s] %s"
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 2
            android.content.Context r7 = r13.f2149e     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            java.lang.String r8 = ".optn"
            java.io.File r7 = com.mob.tools.utils.ResHelper.getDataCacheFile(r7, r8)     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            if (r7 == 0) goto L_0x002e
            boolean r8 = r7.exists()     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            if (r8 == 0) goto L_0x002e
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            r8.<init>(r7)     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            java.io.DataInputStream r7 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            r7.<init>(r8)     // Catch:{ Throwable -> 0x0047, all -> 0x0045 }
            java.lang.String r3 = r7.readUTF()     // Catch:{ Throwable -> 0x002c }
            r12 = r7
            r7 = r3
            r3 = r12
            goto L_0x002f
        L_0x002c:
            r8 = move-exception
            goto L_0x0049
        L_0x002e:
            r7 = r3
        L_0x002f:
            if (r3 == 0) goto L_0x0043
            r3.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0043
        L_0x0035:
            r3 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r1
            r6[r4] = r0
            r8.mo29770d(r3, r2, r6)
        L_0x0043:
            r3 = r7
            goto L_0x006c
        L_0x0045:
            r7 = move-exception
            goto L_0x0071
        L_0x0047:
            r8 = move-exception
            r7 = r3
        L_0x0049:
            com.mob.tools.log.NLog r9 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x006d }
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch:{ all -> 0x006d }
            r10[r5] = r1     // Catch:{ all -> 0x006d }
            java.lang.String r11 = "Read token cache error"
            r10[r4] = r11     // Catch:{ all -> 0x006d }
            r9.mo29770d(r8, r2, r10)     // Catch:{ all -> 0x006d }
            if (r7 == 0) goto L_0x006c
            r7.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x006c
        L_0x005e:
            r7 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r1
            r6[r4] = r0
            r8.mo29770d(r7, r2, r6)
        L_0x006c:
            return r3
        L_0x006d:
            r3 = move-exception
            r12 = r7
            r7 = r3
            r3 = r12
        L_0x0071:
            if (r3 == 0) goto L_0x0085
            r3.close()     // Catch:{ IOException -> 0x0077 }
            goto L_0x0085
        L_0x0077:
            r3 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r1
            r6[r4] = r0
            r8.mo29770d(r3, r2, r6)
        L_0x0085:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2313l.m2594k():java.lang.String");
    }

    /* renamed from: b */
    private void m2584b(TreeMap<String, Object> treeMap) {
        FileChannel fileChannel = null;
        try {
            File dataCacheFile = ResHelper.getDataCacheFile(this.f2149e, ".opdn");
            byte[] a = m2582a("1234567890abcdfi", treeMap);
            if (a != null && a.length > 0) {
                fileChannel = new FileOutputStream(dataCacheFile).getChannel();
                fileChannel.write(ByteBuffer.wrap(a));
                fileChannel.force(true);
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    MobLog.getInstance().mo29770d(e, "[%s] %s", "TokenFetcher", "Close stream error");
                }
            }
        } catch (Throwable th) {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e2) {
                    MobLog.getInstance().mo29770d(e2, "[%s] %s", "TokenFetcher", "Close stream error");
                }
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0072 A[SYNTHETIC, Splitter:B:29:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0088 A[SYNTHETIC, Splitter:B:35:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* renamed from: l */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.Object> m2595l() {
        /*
            r13 = this;
            java.lang.String r0 = "Close stream error"
            java.lang.String r1 = "TokenFetcher"
            java.lang.String r2 = "[%s] %s"
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 2
            android.content.Context r7 = r13.f2149e     // Catch:{ Throwable -> 0x005f, all -> 0x005a }
            java.lang.String r8 = ".opdn"
            java.io.File r7 = com.mob.tools.utils.ResHelper.getDataCacheFile(r7, r8)     // Catch:{ Throwable -> 0x005f, all -> 0x005a }
            if (r7 == 0) goto L_0x0043
            boolean r8 = r7.exists()     // Catch:{ Throwable -> 0x005f, all -> 0x005a }
            if (r8 == 0) goto L_0x0043
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x005f, all -> 0x005a }
            r8.<init>(r7)     // Catch:{ Throwable -> 0x005f, all -> 0x005a }
            java.nio.channels.FileChannel r7 = r8.getChannel()     // Catch:{ Throwable -> 0x005f, all -> 0x005a }
            long r8 = r7.size()     // Catch:{ Throwable -> 0x0041 }
            int r9 = (int) r8     // Catch:{ Throwable -> 0x0041 }
            java.nio.ByteBuffer r8 = java.nio.ByteBuffer.allocate(r9)     // Catch:{ Throwable -> 0x0041 }
        L_0x002c:
            int r9 = r7.read(r8)     // Catch:{ Throwable -> 0x0041 }
            if (r9 <= 0) goto L_0x0033
            goto L_0x002c
        L_0x0033:
            byte[] r8 = r8.array()     // Catch:{ Throwable -> 0x0041 }
            java.lang.String r9 = "1234567890abcdfi"
            java.util.HashMap r3 = r13.m2579a((java.lang.String) r9, (byte[]) r8)     // Catch:{ Throwable -> 0x0041 }
            r12 = r7
            r7 = r3
            r3 = r12
            goto L_0x0044
        L_0x0041:
            r8 = move-exception
            goto L_0x0061
        L_0x0043:
            r7 = r3
        L_0x0044:
            if (r3 == 0) goto L_0x0058
            r3.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x0058
        L_0x004a:
            r3 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r1
            r6[r4] = r0
            r8.mo29770d(r3, r2, r6)
        L_0x0058:
            r3 = r7
            goto L_0x0084
        L_0x005a:
            r7 = move-exception
            r12 = r7
            r7 = r3
            r3 = r12
            goto L_0x0086
        L_0x005f:
            r8 = move-exception
            r7 = r3
        L_0x0061:
            com.mob.tools.log.NLog r9 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0085 }
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch:{ all -> 0x0085 }
            r10[r5] = r1     // Catch:{ all -> 0x0085 }
            java.lang.String r11 = "Read openid cache error"
            r10[r4] = r11     // Catch:{ all -> 0x0085 }
            r9.mo29770d(r8, r2, r10)     // Catch:{ all -> 0x0085 }
            if (r7 == 0) goto L_0x0084
            r7.close()     // Catch:{ IOException -> 0x0076 }
            goto L_0x0084
        L_0x0076:
            r7 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r1
            r6[r4] = r0
            r8.mo29770d(r7, r2, r6)
        L_0x0084:
            return r3
        L_0x0085:
            r3 = move-exception
        L_0x0086:
            if (r7 == 0) goto L_0x009a
            r7.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x009a
        L_0x008c:
            r7 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r1
            r6[r4] = r0
            r8.mo29770d(r7, r2, r6)
        L_0x009a:
            goto L_0x009c
        L_0x009b:
            throw r3
        L_0x009c:
            goto L_0x009b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2313l.m2595l():java.util.HashMap");
    }

    /* renamed from: a */
    private byte[] m2582a(String str, TreeMap<String, Object> treeMap) {
        try {
            return Data.AES128Encode(str, new JSONObject(treeMap).toString());
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "TokenFetcher", "Encypt data error");
            return null;
        }
    }

    /* renamed from: a */
    private HashMap<String, Object> m2579a(String str, byte[] bArr) {
        try {
            return new Hashon().fromJson(Data.AES128Decode(str, bArr));
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "TokenFetcher", "Decrypt data error");
            return new HashMap<>();
        }
    }
}
