package com.mob.commons.p023a;

import android.os.Message;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2310j;
import com.mob.tools.MobLog;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.SharePrefrenceHelper;
import java.io.File;
import java.util.HashMap;

/* renamed from: com.mob.commons.a.s */
/* compiled from: VplClt */
public class C2253s extends C2226d {

    /* renamed from: a */
    private SharePrefrenceHelper f2004a;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return mo29026h();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return C2262b.m2237D() > 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        mo28999b(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        if (message.what == 0) {
            long D = C2262b.m2237D();
            if (D > 0) {
                try {
                    Thread.sleep(D * 1000);
                    HashMap<String, Object> k = mo29029k();
                    if (!(k == null || k.isEmpty() || m2183b(k) == null)) {
                        mo29025a((HashMap<String, Object>) null);
                    }
                } catch (Throwable unused) {
                }
                m2185m();
                this.f2004a = null;
                mo28997a(0, C2262b.m2238E() * 1000);
            }
        }
    }

    /* renamed from: l */
    private synchronized void m2184l() {
        if (this.f2004a == null) {
            this.f2004a = new SharePrefrenceHelper(MobSDK.getContext());
            this.f2004a.open("vpl_cache");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:14|15|16|(1:18)(1:19)|20|(1:22)(1:23)|24|(1:(1:27)(4:28|30|31|32))|29|30|31|32|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0100 */
    /* renamed from: m */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2185m() {
        /*
            r17 = this;
            r1 = r17
            java.lang.String r0 = "pkgs"
            java.lang.String r2 = "lastVplId"
            java.lang.String r3 = "lastVplAt"
            java.lang.String r4 = "networktype"
            java.lang.String r5 = "appver"
            java.lang.String r6 = "apppkg"
            android.content.Context r7 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0157 }
            com.mob.tools.utils.DeviceHelper r7 = com.mob.tools.utils.DeviceHelper.getInstance(r7)     // Catch:{ Throwable -> 0x0157 }
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ Throwable -> 0x0157 }
            r8.<init>()     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r9 = com.mob.MobSDK.getAppkey()     // Catch:{ Throwable -> 0x0157 }
            r10 = 0
            java.lang.String r10 = com.mob.commons.authorize.DeviceAuthorizer.authorize(r10)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r11 = "appkey"
            r8.put(r11, r9)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r11 = r7.getPackageName()     // Catch:{ Throwable -> 0x0157 }
            r8.put(r6, r11)     // Catch:{ Throwable -> 0x0157 }
            int r11 = r7.getAppVersion()     // Catch:{ Throwable -> 0x0157 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ Throwable -> 0x0157 }
            r8.put(r5, r11)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r11 = "duid"
            r8.put(r11, r10)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r11 = "plat"
            int r12 = r7.getPlatformCode()     // Catch:{ Throwable -> 0x0157 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ Throwable -> 0x0157 }
            r8.put(r11, r12)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r11 = r7.getNetworkType()     // Catch:{ Throwable -> 0x0157 }
            r8.put(r4, r11)     // Catch:{ Throwable -> 0x0157 }
            long r11 = r17.mo29027i()     // Catch:{ Throwable -> 0x0157 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ Throwable -> 0x0157 }
            r8.put(r3, r11)     // Catch:{ Throwable -> 0x0157 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0157 }
            r11.<init>()     // Catch:{ Throwable -> 0x0157 }
            r11.append(r9)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r9 = ":"
            r11.append(r9)     // Catch:{ Throwable -> 0x0157 }
            r11.append(r10)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r9 = r11.toString()     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r10 = "utf-8"
            byte[] r9 = r9.getBytes(r10)     // Catch:{ Throwable -> 0x0157 }
            r10 = 2
            java.lang.String r9 = android.util.Base64.encodeToString(r9, r10)     // Catch:{ Throwable -> 0x0157 }
            r8.put(r2, r9)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r10 = "v.data.mob.com/vpl"
            java.lang.String r10 = com.mob.commons.C2310j.m2571a(r10)     // Catch:{ Throwable -> 0x0157 }
            java.lang.Object r10 = m2182a(r8, r10)     // Catch:{ Throwable -> 0x0157 }
            java.util.HashMap r10 = (java.util.HashMap) r10     // Catch:{ Throwable -> 0x0157 }
            if (r10 == 0) goto L_0x0156
            int r11 = r10.size()     // Catch:{ Throwable -> 0x0157 }
            if (r11 != 0) goto L_0x0097
            goto L_0x0156
        L_0x0097:
            r17.mo29028j()     // Catch:{ Throwable -> 0x0157 }
            java.lang.Object r10 = r10.get(r0)     // Catch:{ Throwable -> 0x0157 }
            java.util.List r10 = (java.util.List) r10     // Catch:{ Throwable -> 0x0157 }
            if (r10 == 0) goto L_0x015f
            int r11 = r10.size()     // Catch:{ Throwable -> 0x0157 }
            if (r11 <= 0) goto L_0x015f
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0157 }
            r11.<init>()     // Catch:{ Throwable -> 0x0157 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ Throwable -> 0x0157 }
        L_0x00b1:
            boolean r12 = r10.hasNext()     // Catch:{ Throwable -> 0x0157 }
            if (r12 == 0) goto L_0x0104
            java.lang.Object r12 = r10.next()     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ Throwable -> 0x0157 }
            java.util.HashMap r13 = new java.util.HashMap     // Catch:{ Throwable -> 0x0157 }
            r13.<init>()     // Catch:{ Throwable -> 0x0157 }
            r13.put(r6, r12)     // Catch:{ Throwable -> 0x0157 }
            android.content.Context r14 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0100 }
            android.content.pm.PackageManager r14 = r14.getPackageManager()     // Catch:{ Throwable -> 0x0100 }
            r15 = 0
            android.content.pm.PackageInfo r12 = r14.getPackageInfo(r12, r15)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r14 = r12.versionName     // Catch:{ Throwable -> 0x0100 }
            r13.put(r5, r14)     // Catch:{ Throwable -> 0x0100 }
            android.content.pm.ApplicationInfo r14 = r12.applicationInfo     // Catch:{ Throwable -> 0x0100 }
            int r14 = r14.flags     // Catch:{ Throwable -> 0x0100 }
            r15 = 1
            r14 = r14 & r15
            if (r14 != r15) goto L_0x00e1
            r14 = 1
            goto L_0x00e2
        L_0x00e1:
            r14 = 0
        L_0x00e2:
            android.content.pm.ApplicationInfo r12 = r12.applicationInfo     // Catch:{ Throwable -> 0x0100 }
            int r12 = r12.flags     // Catch:{ Throwable -> 0x0100 }
            r12 = r12 & 128(0x80, float:1.794E-43)
            if (r12 != r15) goto L_0x00ec
            r12 = 1
            goto L_0x00ed
        L_0x00ec:
            r12 = 0
        L_0x00ed:
            java.lang.String r15 = "issys"
            if (r14 != 0) goto L_0x00f7
            if (r12 == 0) goto L_0x00f4
            goto L_0x00f7
        L_0x00f4:
            r16 = 0
            goto L_0x00f9
        L_0x00f7:
            r16 = 1
        L_0x00f9:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r16)     // Catch:{ Throwable -> 0x0100 }
            r13.put(r15, r12)     // Catch:{ Throwable -> 0x0100 }
        L_0x0100:
            r11.add(r13)     // Catch:{ Throwable -> 0x0157 }
            goto L_0x00b1
        L_0x0104:
            r8.remove(r4)     // Catch:{ Throwable -> 0x0157 }
            r8.remove(r3)     // Catch:{ Throwable -> 0x0157 }
            r8.remove(r2)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r2 = "mac"
            java.lang.String r3 = r7.getMacAddress()     // Catch:{ Throwable -> 0x0157 }
            r8.put(r2, r3)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r2 = "model"
            java.lang.String r3 = r7.getModel()     // Catch:{ Throwable -> 0x0157 }
            r8.put(r2, r3)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r2 = "imei"
            java.lang.String r3 = r7.getIMEI()     // Catch:{ Throwable -> 0x0157 }
            r8.put(r2, r3)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r2 = "serialno"
            java.lang.String r3 = r7.getSerialno()     // Catch:{ Throwable -> 0x0157 }
            r8.put(r2, r3)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r2 = "datetime"
            long r3 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x0157 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ Throwable -> 0x0157 }
            r8.put(r2, r3)     // Catch:{ Throwable -> 0x0157 }
            java.lang.String r2 = "id"
            r8.put(r2, r9)     // Catch:{ Throwable -> 0x0157 }
            r8.put(r0, r11)     // Catch:{ Throwable -> 0x0157 }
            java.lang.Object r0 = r1.m2183b(r8)     // Catch:{ Throwable -> 0x0157 }
            if (r0 != 0) goto L_0x0150
            java.lang.Object r0 = r1.m2183b(r8)     // Catch:{ Throwable -> 0x0157 }
        L_0x0150:
            if (r0 != 0) goto L_0x015f
            r1.mo29025a((java.util.HashMap<java.lang.String, java.lang.Object>) r8)     // Catch:{ Throwable -> 0x0157 }
            goto L_0x015f
        L_0x0156:
            return
        L_0x0157:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.mo29769d(r0)
        L_0x015f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2253s.m2185m():void");
    }

    /* renamed from: b */
    private Object m2183b(HashMap<String, Object> hashMap) {
        try {
            hashMap.put("cplAt", Long.valueOf(C2262b.m2260a()));
            return m2182a(hashMap, C2310j.m2571a("v.data.mob.com/cpl"));
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    /* renamed from: a */
    private static Object m2182a(HashMap<String, Object> hashMap, String str) throws Throwable {
        if (C2262b.m2242I()) {
            return null;
        }
        return new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1").requestSynchronized(hashMap, str, false);
    }

    /* renamed from: h */
    public File mo29026h() {
        File file = new File(ResHelper.getDataCache(MobSDK.getContext()), ".vpl_lock");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    /* renamed from: i */
    public synchronized long mo29027i() {
        m2184l();
        return this.f2004a.getLong("LAST_UPLOAD_TIME");
    }

    /* renamed from: j */
    public synchronized void mo29028j() {
        m2184l();
        this.f2004a.putLong("LAST_UPLOAD_TIME", Long.valueOf(C2262b.m2260a()));
    }

    /* renamed from: k */
    public synchronized HashMap<String, Object> mo29029k() {
        m2184l();
        return (HashMap) this.f2004a.get("LAST_FAILED_DATA");
    }

    /* renamed from: a */
    public synchronized void mo29025a(HashMap<String, Object> hashMap) {
        m2184l();
        if (hashMap == null) {
            this.f2004a.remove("LAST_FAILED_DATA");
        } else {
            this.f2004a.put("LAST_FAILED_DATA", hashMap);
        }
    }
}
