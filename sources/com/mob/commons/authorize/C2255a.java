package com.mob.commons.authorize;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.facebook.internal.ServerProtocol;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2300e;
import com.mob.commons.C2308i;
import com.mob.commons.C2310j;
import com.mob.commons.C2312k;
import com.mob.commons.LockAction;
import com.mob.commons.MobProduct;
import com.mob.commons.MobProductCollector;
import com.mob.commons.p024b.C2273d;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.mob.commons.authorize.a */
/* compiled from: Authorizer */
public final class C2255a {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f2009a = C2310j.m2573b("devs.data.mob.com");

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final String mo29032a(final MobProduct mobProduct, final String str) {
        final String[] strArr = new String[1];
        C2300e.m2467a(C2300e.m2466a("comm/locks/.globalLock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                strArr[0] = C2255a.this.m2197a(mobProduct, C2255a.this.m2229g(), str);
                return false;
            }
        });
        return strArr[0];
    }

    /* renamed from: c */
    private File m2222c() {
        return ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.duid");
    }

    /* renamed from: d */
    private File m2226d() {
        File file = new File(ResHelper.getDataCache(MobSDK.getContext()), "comm/dbs/.duid");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007d  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String m2197a(com.mob.commons.MobProduct r11, boolean r12, java.lang.String r13) {
        /*
            r10 = this;
            monitor-enter(r10)
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ all -> 0x008d }
            com.mob.tools.utils.DeviceHelper r0 = com.mob.tools.utils.DeviceHelper.getInstance(r0)     // Catch:{ all -> 0x008d }
            boolean r1 = r0.getSdcardState()     // Catch:{ all -> 0x008d }
            java.util.HashMap r2 = r10.m2227e()     // Catch:{ all -> 0x008d }
            if (r2 == 0) goto L_0x0084
            int r3 = r2.size()     // Catch:{ all -> 0x008d }
            if (r3 <= 0) goto L_0x0084
            java.lang.String r3 = "duid"
            java.lang.Object r3 = r2.get(r3)     // Catch:{ all -> 0x008d }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x008d }
            long r4 = com.mob.commons.C2308i.m2501F()     // Catch:{ all -> 0x008d }
            boolean r6 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x008d }
            if (r6 == 0) goto L_0x002c
            goto L_0x002d
        L_0x002c:
            r13 = r3
        L_0x002d:
            boolean r3 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x008d }
            r6 = 1
            r7 = 0
            if (r3 == 0) goto L_0x0040
            java.lang.String r13 = r10.m2200a((java.lang.String) r13)     // Catch:{ all -> 0x008d }
            java.lang.String r12 = "duid"
            r2.put(r12, r13)     // Catch:{ all -> 0x008d }
        L_0x003e:
            r7 = 1
            goto L_0x007b
        L_0x0040:
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x008d }
            int r3 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r3 < 0) goto L_0x0059
            java.lang.String r12 = r10.m2223c((java.lang.String) r13)     // Catch:{ all -> 0x008d }
            boolean r0 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x008d }
            if (r0 != 0) goto L_0x007b
            java.lang.String r13 = "duid"
            r2.put(r13, r12)     // Catch:{ all -> 0x008d }
            r13 = r12
            goto L_0x003e
        L_0x0059:
            if (r1 == 0) goto L_0x007b
            java.lang.String r1 = r0.getWAbcd(r7)     // Catch:{ all -> 0x008d }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x008d }
            if (r3 == 0) goto L_0x0069
            r0.saveWabcd(r13, r7)     // Catch:{ all -> 0x008d }
            goto L_0x007b
        L_0x0069:
            boolean r3 = r13.equals(r1)     // Catch:{ all -> 0x008d }
            if (r3 != 0) goto L_0x007b
            if (r12 == 0) goto L_0x0078
            java.lang.String r12 = "duid"
            r2.put(r12, r1)     // Catch:{ all -> 0x008d }
            r13 = r1
            goto L_0x003e
        L_0x0078:
            r0.saveWabcd(r13, r7)     // Catch:{ all -> 0x008d }
        L_0x007b:
            if (r7 == 0) goto L_0x0080
            r10.m2209a((java.util.HashMap<java.lang.String, java.lang.Object>) r2, (boolean) r6)     // Catch:{ all -> 0x008d }
        L_0x0080:
            r10.m2210a((java.util.HashMap<java.lang.String, java.lang.Object>) r2, (boolean) r7, (com.mob.commons.MobProduct) r11)     // Catch:{ all -> 0x008d }
            goto L_0x008b
        L_0x0084:
            java.lang.String r13 = r10.m2200a((java.lang.String) r13)     // Catch:{ all -> 0x008d }
            r10.m2207a((java.lang.String) r13, (com.mob.commons.MobProduct) r11)     // Catch:{ all -> 0x008d }
        L_0x008b:
            monitor-exit(r10)
            return r13
        L_0x008d:
            r11 = move-exception
            monitor-exit(r10)
            goto L_0x0091
        L_0x0090:
            throw r11
        L_0x0091:
            goto L_0x0090
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.C2255a.m2197a(com.mob.commons.MobProduct, boolean, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public HashMap<String, Object> m2227e() {
        try {
            return m2202a(m2222c());
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    /* renamed from: a */
    private String m2200a(String str) {
        if (TextUtils.isEmpty(str) && DeviceAuthorizer.f2005a != null) {
            return DeviceAuthorizer.f2005a;
        }
        String b = m2218b(str);
        if (b == null) {
            return str == null ? m2201a(true) : str;
        }
        return b;
    }

    /* renamed from: b */
    private String m2218b(String str) {
        try {
            String wAbcd = DeviceHelper.getInstance(MobSDK.getContext()).getWAbcd(0);
            if (!TextUtils.isEmpty(wAbcd)) {
                return wAbcd;
            }
            return m2223c(str);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    /* renamed from: c */
    private String m2223c(String str) {
        try {
            if (C2262b.m2242I()) {
                return null;
            }
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            HashMap hashMap = new HashMap();
            hashMap.put("plat", 1);
            hashMap.put("imei", instance.getIMEI());
            hashMap.put("serialno", instance.getSerialno());
            hashMap.put("mac", instance.getMacAddress());
            hashMap.put("model", instance.getModel());
            hashMap.put("factory", instance.getManufacturer());
            hashMap.put("adsid", instance.getAdvertisingID());
            hashMap.put("oaid", C2273d.m2353c(MobSDK.getContext()));
            hashMap.put("imsi", instance.getIMSI());
            hashMap.put("androidid", instance.getAndroidID());
            hashMap.put("simserialno", instance.getSimSerialNumber());
            hashMap.put("duid", str);
            MobCommunicator mobCommunicator = new MobCommunicator(1024, "ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", "191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd");
            HashMap hashMap2 = (HashMap) mobCommunicator.requestSynchronized((HashMap<String, Object>) hashMap, f2009a + "/dgen", false);
            Object obj = hashMap2.get("dri");
            if (obj != null) {
                C2308i.m2545i(System.currentTimeMillis() + ((long) (((Integer) obj).intValue() * 60 * 60 * 1000)));
            }
            Object obj2 = hashMap2.get("duid");
            if (obj2 != null) {
                String valueOf = String.valueOf(obj2);
                if (instance.getSdcardState()) {
                    instance.saveWabcd(valueOf, 0);
                }
                return valueOf;
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            C2308i.m2545i(System.currentTimeMillis());
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e A[Catch:{ Throwable -> 0x0087, Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0040 A[Catch:{ Throwable -> 0x0087, Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0046 A[Catch:{ Throwable -> 0x0087, Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048 A[Catch:{ Throwable -> 0x0087, Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e A[Catch:{ Throwable -> 0x0087, Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0050 A[Catch:{ Throwable -> 0x0087, Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0056 A[Catch:{ Throwable -> 0x0087, Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0058 A[Catch:{ Throwable -> 0x0087, Throwable -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0062 A[Catch:{ Throwable -> 0x0087, Throwable -> 0x00cb }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String m2201a(boolean r12) {
        /*
            r11 = this;
            java.lang.String r0 = ":"
            android.content.Context r1 = com.mob.MobSDK.getContext()
            com.mob.tools.utils.DeviceHelper r1 = com.mob.tools.utils.DeviceHelper.getInstance(r1)
            r2 = 0
            boolean r3 = r1.getSdcardState()     // Catch:{ Throwable -> 0x00cb }
            r4 = 0
            java.lang.String r5 = r1.getWAbcd(r4)     // Catch:{ Throwable -> 0x00cb }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x00cb }
            if (r6 != 0) goto L_0x001b
            return r5
        L_0x001b:
            java.lang.String r5 = r1.getModel()     // Catch:{ Throwable -> 0x00cb }
            boolean r6 = com.mob.commons.C2212a.m1961c()     // Catch:{ Throwable -> 0x00cb }
            if (r6 == 0) goto L_0x0030
            boolean r6 = com.mob.commons.C2212a.m1963d()     // Catch:{ Throwable -> 0x00cb }
            if (r6 == 0) goto L_0x002c
            goto L_0x0030
        L_0x002c:
            r6 = r2
            r7 = r6
            r8 = r7
            goto L_0x003c
        L_0x0030:
            java.lang.String r6 = r1.getIMEI()     // Catch:{ Throwable -> 0x00cb }
            java.lang.String r7 = r1.getMacAddress()     // Catch:{ Throwable -> 0x00cb }
            java.lang.String r8 = r1.getSerialno()     // Catch:{ Throwable -> 0x00cb }
        L_0x003c:
            if (r5 != 0) goto L_0x0040
            r5 = r2
            goto L_0x0044
        L_0x0040:
            java.lang.String r5 = r5.trim()     // Catch:{ Throwable -> 0x00cb }
        L_0x0044:
            if (r7 != 0) goto L_0x0048
            r7 = r2
            goto L_0x004c
        L_0x0048:
            java.lang.String r7 = r7.trim()     // Catch:{ Throwable -> 0x00cb }
        L_0x004c:
            if (r6 != 0) goto L_0x0050
            r6 = r2
            goto L_0x0054
        L_0x0050:
            java.lang.String r6 = r6.trim()     // Catch:{ Throwable -> 0x00cb }
        L_0x0054:
            if (r8 != 0) goto L_0x0058
            r8 = r2
            goto L_0x005c
        L_0x0058:
            java.lang.String r8 = r8.trim()     // Catch:{ Throwable -> 0x00cb }
        L_0x005c:
            boolean r9 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x00cb }
            if (r9 == 0) goto L_0x009d
            android.content.Context r9 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x00cb }
            java.lang.String r9 = com.mob.commons.p024b.C2273d.m2353c(r9)     // Catch:{ Throwable -> 0x00cb }
            if (r9 != 0) goto L_0x006e
            r9 = r2
            goto L_0x0072
        L_0x006e:
            java.lang.String r9 = r9.trim()     // Catch:{ Throwable -> 0x00cb }
        L_0x0072:
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Throwable -> 0x00cb }
            if (r10 != 0) goto L_0x007a
            r6 = r9
            goto L_0x009d
        L_0x007a:
            java.lang.String r9 = r1.getAdvertisingID()     // Catch:{ Throwable -> 0x0087 }
            if (r9 != 0) goto L_0x0082
            r6 = r2
            goto L_0x008f
        L_0x0082:
            java.lang.String r6 = r9.trim()     // Catch:{ Throwable -> 0x0087 }
            goto L_0x008f
        L_0x0087:
            r9 = move-exception
            com.mob.tools.log.NLog r10 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x00cb }
            r10.mo29787w((java.lang.Throwable) r9)     // Catch:{ Throwable -> 0x00cb }
        L_0x008f:
            boolean r9 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x00cb }
            if (r9 == 0) goto L_0x009d
            java.util.UUID r6 = java.util.UUID.randomUUID()     // Catch:{ Throwable -> 0x00cb }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x00cb }
        L_0x009d:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00cb }
            r9.<init>()     // Catch:{ Throwable -> 0x00cb }
            r9.append(r5)     // Catch:{ Throwable -> 0x00cb }
            r9.append(r0)     // Catch:{ Throwable -> 0x00cb }
            r9.append(r6)     // Catch:{ Throwable -> 0x00cb }
            r9.append(r0)     // Catch:{ Throwable -> 0x00cb }
            r9.append(r7)     // Catch:{ Throwable -> 0x00cb }
            r9.append(r0)     // Catch:{ Throwable -> 0x00cb }
            r9.append(r8)     // Catch:{ Throwable -> 0x00cb }
            java.lang.String r0 = r9.toString()     // Catch:{ Throwable -> 0x00cb }
            byte[] r0 = com.mob.tools.utils.Data.SHA1((java.lang.String) r0)     // Catch:{ Throwable -> 0x00cb }
            java.lang.String r0 = com.mob.tools.utils.Data.byteToHex(r0)     // Catch:{ Throwable -> 0x00cb }
            if (r12 == 0) goto L_0x00ca
            if (r3 == 0) goto L_0x00ca
            r1.saveWabcd(r0, r4)     // Catch:{ Throwable -> 0x00cb }
        L_0x00ca:
            return r0
        L_0x00cb:
            r12 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29787w((java.lang.Throwable) r12)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.C2255a.m2201a(boolean):java.lang.String");
    }

    /* renamed from: a */
    private void m2210a(final HashMap<String, Object> hashMap, final boolean z, final MobProduct mobProduct) {
        new Thread() {
            public void run() {
                synchronized (C2255a.f2009a) {
                    try {
                        boolean z = z;
                        if (C2255a.this.m2215a((HashMap<String, Object>) hashMap) || C2255a.this.m2230h()) {
                            C2255a.this.m2208a((HashMap<String, Object>) hashMap, (String) hashMap.get("duid"));
                            z = true;
                        }
                        if (C2255a.this.m2216a((HashMap<String, Object>) hashMap, mobProduct)) {
                            z = true;
                        }
                        if (z) {
                            C2255a.this.m2225c((HashMap<String, Object>) hashMap);
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29769d(th);
                    }
                }
            }
        }.start();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m2215a(HashMap<String, Object> hashMap) {
        boolean z;
        HashMap hashMap2 = (HashMap) hashMap.get("deviceInfo");
        if (hashMap2 == null) {
            return true;
        }
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        Object obj = hashMap.get("duid");
        String n = C2308i.m2557n();
        if (TextUtils.isEmpty(n) && obj != null) {
            C2308i.m2546i(String.valueOf(obj));
        }
        if (!TextUtils.isEmpty(n) && obj != null && !n.equals(obj)) {
            return true;
        }
        Object obj2 = hashMap2.get("adsid");
        String str = null;
        try {
            str = instance.getAdvertisingID();
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        if (str != null && !str.equals(obj2)) {
            return true;
        }
        System.currentTimeMillis();
        if (C2262b.m2257X()) {
            Object obj3 = hashMap2.get("phoneno");
            String ln = instance.getLN();
            if (ln != null && !ln.equals(obj3)) {
                return true;
            }
        }
        Object obj4 = hashMap2.get("simserialno");
        String simSerialNumber = instance.getSimSerialNumber();
        if (simSerialNumber != null && !simSerialNumber.equals(obj4)) {
            return true;
        }
        Object obj5 = hashMap2.get("imei");
        String imei = instance.getIMEI();
        if (imei != null && !imei.equals(obj5)) {
            return true;
        }
        Object obj6 = hashMap2.get("serialno");
        String serialno = instance.getSerialno();
        if (serialno != null && !serialno.equals(obj6)) {
            return true;
        }
        Object obj7 = hashMap2.get("mac");
        String macAddress = instance.getMacAddress();
        if (macAddress != null && !macAddress.equals(obj7)) {
            return true;
        }
        Object obj8 = hashMap2.get("model");
        String model = instance.getModel();
        if (model != null && !model.equals(obj8)) {
            return true;
        }
        Object obj9 = hashMap2.get("factory");
        String manufacturer = instance.getManufacturer();
        if (manufacturer != null && !manufacturer.equals(obj9)) {
            return true;
        }
        Object obj10 = hashMap2.get("carrier");
        String carrier = instance.getCarrier();
        if (carrier != null && !carrier.equals(obj10)) {
            return true;
        }
        Object obj11 = hashMap2.get("imsi");
        String imsi = instance.getIMSI();
        if (imsi != null && !imsi.equals(obj11)) {
            return true;
        }
        Object obj12 = hashMap2.get("imsiArray");
        String[] queryIMSI = instance.queryIMSI();
        if (queryIMSI != null && queryIMSI.length > 0) {
            if (obj12 == null) {
                return true;
            }
            try {
                ArrayList arrayList = (ArrayList) obj12;
                if (arrayList.size() != queryIMSI.length) {
                    return true;
                }
                boolean z2 = false;
                for (String str2 : queryIMSI) {
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (str2.equals((String) it.next())) {
                                z2 = false;
                                break;
                            }
                        } else {
                            z2 = true;
                            break;
                        }
                    }
                }
                if (z2) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        Object obj13 = hashMap2.get("androidids");
        if (obj13 == null && (obj13 = hashMap2.get("androidid")) != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(obj13);
            obj13 = arrayList2;
        }
        if (obj13 != null && (obj13 instanceof ArrayList)) {
            String androidID = instance.getAndroidID();
            Iterator it2 = ((ArrayList) obj13).iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                Object next = it2.next();
                if (next != null && next.equals(androidID)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return true;
            }
            Object obj14 = hashMap2.get("sysver");
            String oSVersionName = instance.getOSVersionName();
            if (oSVersionName != null && !oSVersionName.equals(obj14)) {
                return true;
            }
            Object obj15 = hashMap2.get(Config.EVENT_HEAT_XP);
            boolean cx = instance.mo29988cx();
            if (obj15 != null && String.valueOf(cx ? 1 : 0).equals(String.valueOf(obj15))) {
                Object obj16 = hashMap2.get("breaked");
                boolean isRooted = instance.isRooted();
                if ((obj16 == null && isRooted) || (obj16 != null && !String.valueOf(obj16).equals(String.valueOf(isRooted)))) {
                    return true;
                }
                Object obj17 = hashMap2.get(C2312k.m2575a(69));
                String c = C2273d.m2353c(MobSDK.getContext());
                if ((obj17 != null || TextUtils.isEmpty(c)) && (obj17 == null || String.valueOf(obj17).equals(c))) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x0174 A[Catch:{ Throwable -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x018a A[Catch:{ Throwable -> 0x005f, Throwable -> 0x025e }] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0195  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01ab A[Catch:{ Throwable -> 0x01a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0202 A[Catch:{ Throwable -> 0x005f, Throwable -> 0x025e }] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0204 A[Catch:{ Throwable -> 0x005f, Throwable -> 0x025e }] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0214 A[Catch:{ Throwable -> 0x0220 }] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0215 A[Catch:{ Throwable -> 0x0220 }] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2220b(java.util.HashMap<java.lang.String, java.lang.Object> r15) {
        /*
            r14 = this;
            java.lang.String r0 = "imsiArray"
            java.lang.String r1 = "androidids"
            java.lang.String r2 = "deviceInfo"
            android.content.Context r3 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x025e }
            com.mob.tools.utils.DeviceHelper r3 = com.mob.tools.utils.DeviceHelper.getInstance(r3)     // Catch:{ Throwable -> 0x025e }
            java.lang.Object r4 = r15.get(r2)     // Catch:{ Throwable -> 0x025e }
            java.util.HashMap r4 = (java.util.HashMap) r4     // Catch:{ Throwable -> 0x025e }
            if (r4 != 0) goto L_0x001e
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ Throwable -> 0x025e }
            r4.<init>()     // Catch:{ Throwable -> 0x025e }
            r15.put(r2, r4)     // Catch:{ Throwable -> 0x025e }
        L_0x001e:
            java.lang.String r5 = com.mob.commons.C2308i.m2557n()     // Catch:{ Throwable -> 0x025e }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x025e }
            if (r6 != 0) goto L_0x002d
            java.lang.String r6 = "lduid"
            r4.put(r6, r5)     // Catch:{ Throwable -> 0x025e }
        L_0x002d:
            java.lang.String r5 = "plat"
            r6 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r6)     // Catch:{ Throwable -> 0x025e }
            r4.put(r5, r7)     // Catch:{ Throwable -> 0x025e }
            java.lang.String r5 = "deviceType"
            java.lang.String r7 = r3.getDeviceType()     // Catch:{ Throwable -> 0x025e }
            r4.put(r5, r7)     // Catch:{ Throwable -> 0x025e }
            java.lang.String r5 = r3.getOSVersionName()     // Catch:{ Throwable -> 0x025e }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x025e }
            if (r7 != 0) goto L_0x004f
            java.lang.String r7 = "sysver"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x025e }
        L_0x004f:
            java.lang.String r5 = r3.getModel()     // Catch:{ Throwable -> 0x005f }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x005f }
            if (r7 != 0) goto L_0x0067
            java.lang.String r7 = "model"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x005f }
            goto L_0x0067
        L_0x005f:
            r5 = move-exception
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r7.mo29787w((java.lang.Throwable) r5)     // Catch:{ Throwable -> 0x025e }
        L_0x0067:
            java.lang.String r5 = r3.getManufacturer()     // Catch:{ Throwable -> 0x0077 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x0077 }
            if (r7 != 0) goto L_0x007f
            java.lang.String r7 = "factory"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x0077 }
            goto L_0x007f
        L_0x0077:
            r5 = move-exception
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r7.mo29787w((java.lang.Throwable) r5)     // Catch:{ Throwable -> 0x025e }
        L_0x007f:
            boolean r5 = com.mob.commons.C2262b.m2257X()     // Catch:{ Throwable -> 0x025e }
            if (r5 == 0) goto L_0x0094
            java.lang.String r5 = r3.getLN()     // Catch:{ Throwable -> 0x025e }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x025e }
            if (r7 != 0) goto L_0x0094
            java.lang.String r7 = "phoneno"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x025e }
        L_0x0094:
            java.lang.String r5 = r3.getSimSerialNumber()     // Catch:{ Throwable -> 0x025e }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x025e }
            if (r7 != 0) goto L_0x00a3
            java.lang.String r7 = "simserialno"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x025e }
        L_0x00a3:
            r5 = 0
            java.lang.String r5 = r3.getAdvertisingID()     // Catch:{ Throwable -> 0x00a9 }
            goto L_0x00b1
        L_0x00a9:
            r7 = move-exception
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r8.mo29787w((java.lang.Throwable) r7)     // Catch:{ Throwable -> 0x025e }
        L_0x00b1:
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x025e }
            if (r7 != 0) goto L_0x00bc
            java.lang.String r7 = "adsid"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x025e }
        L_0x00bc:
            java.lang.String r5 = r3.getIMEI()     // Catch:{ Throwable -> 0x00cc }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x00cc }
            if (r7 != 0) goto L_0x00d4
            java.lang.String r7 = "imei"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x00cc }
            goto L_0x00d4
        L_0x00cc:
            r5 = move-exception
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r7.mo29787w((java.lang.Throwable) r5)     // Catch:{ Throwable -> 0x025e }
        L_0x00d4:
            java.lang.String r5 = r3.getSerialno()     // Catch:{ Throwable -> 0x00e4 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x00e4 }
            if (r7 != 0) goto L_0x00ec
            java.lang.String r7 = "serialno"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x00e4 }
            goto L_0x00ec
        L_0x00e4:
            r5 = move-exception
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r7.mo29787w((java.lang.Throwable) r5)     // Catch:{ Throwable -> 0x025e }
        L_0x00ec:
            java.lang.String r5 = r3.getMacAddress()     // Catch:{ Throwable -> 0x00fc }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x00fc }
            if (r7 != 0) goto L_0x0104
            java.lang.String r7 = "mac"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x00fc }
            goto L_0x0104
        L_0x00fc:
            r5 = move-exception
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r7.mo29787w((java.lang.Throwable) r5)     // Catch:{ Throwable -> 0x025e }
        L_0x0104:
            java.lang.String r5 = r3.getIMSI()     // Catch:{ Throwable -> 0x0114 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x0114 }
            if (r7 != 0) goto L_0x011c
            java.lang.String r7 = "imsi"
            r4.put(r7, r5)     // Catch:{ Throwable -> 0x0114 }
            goto L_0x011c
        L_0x0114:
            r5 = move-exception
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r7.mo29787w((java.lang.Throwable) r5)     // Catch:{ Throwable -> 0x025e }
        L_0x011c:
            java.lang.Object r5 = r4.get(r0)     // Catch:{ Throwable -> 0x025e }
            r7 = 0
            java.lang.String[] r8 = r3.queryIMSI()     // Catch:{ Throwable -> 0x0178 }
            if (r8 == 0) goto L_0x0180
            int r9 = r8.length     // Catch:{ Throwable -> 0x0178 }
            if (r9 <= 0) goto L_0x0180
            if (r5 != 0) goto L_0x012e
            r9 = 1
            goto L_0x012f
        L_0x012e:
            r9 = 0
        L_0x012f:
            if (r9 != 0) goto L_0x0172
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ Throwable -> 0x016a }
            int r10 = r5.size()     // Catch:{ Throwable -> 0x016a }
            int r9 = r8.length     // Catch:{ Throwable -> 0x016a }
            if (r10 == r9) goto L_0x013c
            r9 = 1
            goto L_0x013d
        L_0x013c:
            r9 = 0
        L_0x013d:
            if (r9 != 0) goto L_0x0172
            int r10 = r8.length     // Catch:{ Throwable -> 0x016a }
            r11 = r9
            r9 = 0
        L_0x0142:
            if (r9 >= r10) goto L_0x0168
            r11 = r8[r9]     // Catch:{ Throwable -> 0x0165 }
            java.util.Iterator r12 = r5.iterator()     // Catch:{ Throwable -> 0x0162 }
        L_0x014a:
            boolean r13 = r12.hasNext()     // Catch:{ Throwable -> 0x0162 }
            if (r13 == 0) goto L_0x015e
            java.lang.Object r13 = r12.next()     // Catch:{ Throwable -> 0x0162 }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ Throwable -> 0x0162 }
            boolean r13 = r11.equals(r13)     // Catch:{ Throwable -> 0x0162 }
            if (r13 == 0) goto L_0x014a
            r11 = 0
            goto L_0x015f
        L_0x015e:
            r11 = 1
        L_0x015f:
            int r9 = r9 + 1
            goto L_0x0142
        L_0x0162:
            r5 = move-exception
            r9 = 1
            goto L_0x016b
        L_0x0165:
            r5 = move-exception
            r9 = r11
            goto L_0x016b
        L_0x0168:
            r9 = r11
            goto L_0x0172
        L_0x016a:
            r5 = move-exception
        L_0x016b:
            com.mob.tools.log.NLog r10 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x0178 }
            r10.mo29769d(r5)     // Catch:{ Throwable -> 0x0178 }
        L_0x0172:
            if (r9 == 0) goto L_0x0180
            r4.put(r0, r8)     // Catch:{ Throwable -> 0x0178 }
            goto L_0x0180
        L_0x0178:
            r0 = move-exception
            com.mob.tools.log.NLog r5 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r5.mo29787w((java.lang.Throwable) r0)     // Catch:{ Throwable -> 0x025e }
        L_0x0180:
            java.lang.String r0 = r3.getCarrier()     // Catch:{ Throwable -> 0x025e }
            boolean r5 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x025e }
            if (r5 != 0) goto L_0x018f
            java.lang.String r5 = "carrier"
            r4.put(r5, r0)     // Catch:{ Throwable -> 0x025e }
        L_0x018f:
            java.lang.Object r0 = r4.get(r1)     // Catch:{ Throwable -> 0x025e }
            if (r0 != 0) goto L_0x01a9
            java.lang.String r0 = "androidid"
            java.lang.Object r0 = r4.get(r0)     // Catch:{ Throwable -> 0x01a7 }
            if (r0 == 0) goto L_0x01a9
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01a7 }
            r5.<init>()     // Catch:{ Throwable -> 0x01a7 }
            r5.add(r0)     // Catch:{ Throwable -> 0x01a7 }
            r0 = r5
            goto L_0x01a9
        L_0x01a7:
            r0 = move-exception
            goto L_0x01e8
        L_0x01a9:
            if (r0 == 0) goto L_0x01d8
            boolean r5 = r0 instanceof java.util.ArrayList     // Catch:{ Throwable -> 0x01a7 }
            if (r5 != 0) goto L_0x01b0
            goto L_0x01d8
        L_0x01b0:
            java.lang.String r5 = r3.getAndroidID()     // Catch:{ Throwable -> 0x01a7 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x01a7 }
            java.util.Iterator r8 = r0.iterator()     // Catch:{ Throwable -> 0x01a7 }
        L_0x01ba:
            boolean r9 = r8.hasNext()     // Catch:{ Throwable -> 0x01a7 }
            if (r9 == 0) goto L_0x01ce
            java.lang.Object r9 = r8.next()     // Catch:{ Throwable -> 0x01a7 }
            if (r9 == 0) goto L_0x01ba
            boolean r9 = r9.equals(r5)     // Catch:{ Throwable -> 0x01a7 }
            if (r9 == 0) goto L_0x01ba
            r8 = 1
            goto L_0x01cf
        L_0x01ce:
            r8 = 0
        L_0x01cf:
            if (r8 != 0) goto L_0x01ef
            r0.add(r5)     // Catch:{ Throwable -> 0x01a7 }
            r4.put(r1, r0)     // Catch:{ Throwable -> 0x01a7 }
            goto L_0x01ef
        L_0x01d8:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01a7 }
            r0.<init>()     // Catch:{ Throwable -> 0x01a7 }
            java.lang.String r5 = r3.getAndroidID()     // Catch:{ Throwable -> 0x01a7 }
            r0.add(r5)     // Catch:{ Throwable -> 0x01a7 }
            r4.put(r1, r0)     // Catch:{ Throwable -> 0x01a7 }
            goto L_0x01ef
        L_0x01e8:
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r1.mo29787w((java.lang.Throwable) r0)     // Catch:{ Throwable -> 0x025e }
        L_0x01ef:
            boolean r0 = r3.isRooted()     // Catch:{ Throwable -> 0x025e }
            java.lang.String r1 = "breaked"
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ Throwable -> 0x025e }
            r4.put(r1, r0)     // Catch:{ Throwable -> 0x025e }
            boolean r0 = r3.mo29988cx()     // Catch:{ Throwable -> 0x025e }
            if (r0 == 0) goto L_0x0204
            r0 = 1
            goto L_0x0205
        L_0x0204:
            r0 = 0
        L_0x0205:
            java.lang.String r1 = "xp"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x025e }
            r4.put(r1, r0)     // Catch:{ Throwable -> 0x025e }
            boolean r0 = r3.checkPad()     // Catch:{ Throwable -> 0x0220 }
            if (r0 == 0) goto L_0x0215
            goto L_0x0216
        L_0x0215:
            r6 = 0
        L_0x0216:
            java.lang.String r0 = "pad"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)     // Catch:{ Throwable -> 0x0220 }
            r4.put(r0, r1)     // Catch:{ Throwable -> 0x0220 }
            goto L_0x0228
        L_0x0220:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r1.mo29787w((java.lang.Throwable) r0)     // Catch:{ Throwable -> 0x025e }
        L_0x0228:
            boolean r0 = com.mob.commons.C2262b.m2257X()     // Catch:{ Throwable -> 0x025e }
            java.util.HashMap r0 = r3.getIInfo(r0)     // Catch:{ Throwable -> 0x025e }
            java.lang.String r1 = "sims"
            r4.put(r1, r0)     // Catch:{ Throwable -> 0x025e }
            java.lang.String r0 = "screensize"
            java.lang.String r1 = r3.getScreenSize()     // Catch:{ Throwable -> 0x023f }
            r4.put(r0, r1)     // Catch:{ Throwable -> 0x023f }
            goto L_0x0247
        L_0x023f:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x025e }
            r1.mo29787w((java.lang.Throwable) r0)     // Catch:{ Throwable -> 0x025e }
        L_0x0247:
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x025e }
            java.util.HashMap r0 = com.mob.commons.p024b.C2273d.m2348a((android.content.Context) r0)     // Catch:{ Throwable -> 0x025e }
            if (r0 == 0) goto L_0x025a
            int r1 = r0.size()     // Catch:{ Throwable -> 0x025e }
            if (r1 <= 0) goto L_0x025a
            r4.putAll(r0)     // Catch:{ Throwable -> 0x025e }
        L_0x025a:
            r15.put(r2, r4)     // Catch:{ Throwable -> 0x025e }
            goto L_0x0266
        L_0x025e:
            r15 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29787w((java.lang.Throwable) r15)
        L_0x0266:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.C2255a.m2220b(java.util.HashMap):void");
    }

    /* renamed from: f */
    private String m2228f() {
        return Config.FEED_LIST_MAPPING + "k." + "co" + "mm" + "on" + "ap" + ".s" + "dk";
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(20:5|(2:8|6)|9|10|11|13|14|(1:18)|19|(1:23)|24|25|(4:29|(2:32|30)|53|33)|34|35|36|(3:38|39|40)|(4:42|(1:44)|45|(1:47))|48|(2:50|56)(1:55)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00d8 */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ef A[Catch:{ Throwable -> 0x01ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01aa A[Catch:{ Throwable -> 0x01ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m2208a(java.util.HashMap<java.lang.String, java.lang.Object> r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "carrier"
            boolean r1 = com.mob.commons.C2262b.m2242I()     // Catch:{ Throwable -> 0x01ba }
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            com.mob.commons.l r1 = com.mob.commons.C2313l.m2576a()     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r1 = r1.mo29104b()     // Catch:{ Throwable -> 0x01ba }
            r8.m2220b((java.util.HashMap<java.lang.String, java.lang.Object>) r9)     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r2 = "deviceInfo"
            java.lang.Object r9 = r9.get(r2)     // Catch:{ Throwable -> 0x01ba }
            java.util.HashMap r9 = (java.util.HashMap) r9     // Catch:{ Throwable -> 0x01ba }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ Throwable -> 0x01ba }
            r2.<init>()     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r3 = "token"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01ba }
            java.util.Set r9 = r9.entrySet()     // Catch:{ Throwable -> 0x01ba }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Throwable -> 0x01ba }
        L_0x002e:
            boolean r1 = r9.hasNext()     // Catch:{ Throwable -> 0x01ba }
            if (r1 == 0) goto L_0x0046
            java.lang.Object r1 = r9.next()     // Catch:{ Throwable -> 0x01ba }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ Throwable -> 0x01ba }
            java.lang.Object r3 = r1.getKey()     // Catch:{ Throwable -> 0x01ba }
            java.lang.Object r1 = r1.getValue()     // Catch:{ Throwable -> 0x01ba }
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01ba }
            goto L_0x002e
        L_0x0046:
            java.lang.Object r9 = r2.get(r0)     // Catch:{ Throwable -> 0x0059 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Throwable -> 0x0059 }
            int r9 = com.mob.tools.utils.ResHelper.parseInt(r9)     // Catch:{ Throwable -> 0x0059 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0059 }
            r2.put(r0, r9)     // Catch:{ Throwable -> 0x0059 }
        L_0x0059:
            java.lang.String r9 = "androidids"
            java.lang.Object r9 = r2.remove(r9)     // Catch:{ Throwable -> 0x01ba }
            java.util.ArrayList r9 = (java.util.ArrayList) r9     // Catch:{ Throwable -> 0x01ba }
            if (r9 == 0) goto L_0x0078
            boolean r0 = r9.isEmpty()     // Catch:{ Throwable -> 0x01ba }
            if (r0 != 0) goto L_0x0078
            java.lang.String r0 = "androidid"
            int r1 = r9.size()     // Catch:{ Throwable -> 0x01ba }
            int r1 = r1 + -1
            java.lang.Object r9 = r9.get(r1)     // Catch:{ Throwable -> 0x01ba }
            r2.put(r0, r9)     // Catch:{ Throwable -> 0x01ba }
        L_0x0078:
            android.content.Context r9 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x01ba }
            com.mob.tools.utils.DeviceHelper r9 = com.mob.tools.utils.DeviceHelper.getInstance(r9)     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r0 = "duid"
            r2.put(r0, r10)     // Catch:{ Throwable -> 0x01ba }
            java.lang.String[] r0 = r9.queryIMEI()     // Catch:{ Throwable -> 0x01ba }
            if (r0 == 0) goto L_0x0093
            int r1 = r0.length     // Catch:{ Throwable -> 0x01ba }
            if (r1 <= 0) goto L_0x0093
            java.lang.String r1 = "imeiArray"
            r2.put(r1, r0)     // Catch:{ Throwable -> 0x01ba }
        L_0x0093:
            java.util.HashMap r0 = r9.listNetworkHardware()     // Catch:{ Throwable -> 0x00d8 }
            if (r0 == 0) goto L_0x00d8
            boolean r1 = r0.isEmpty()     // Catch:{ Throwable -> 0x00d8 }
            if (r1 != 0) goto L_0x00d8
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00d8 }
            r1.<init>()     // Catch:{ Throwable -> 0x00d8 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ Throwable -> 0x00d8 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Throwable -> 0x00d8 }
        L_0x00ac:
            boolean r3 = r0.hasNext()     // Catch:{ Throwable -> 0x00d8 }
            if (r3 == 0) goto L_0x00d3
            java.lang.Object r3 = r0.next()     // Catch:{ Throwable -> 0x00d8 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ Throwable -> 0x00d8 }
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ Throwable -> 0x00d8 }
            r4.<init>()     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r5 = "ss"
            java.lang.Object r6 = r3.getKey()     // Catch:{ Throwable -> 0x00d8 }
            r4.put(r5, r6)     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r5 = "mac"
            java.lang.Object r3 = r3.getValue()     // Catch:{ Throwable -> 0x00d8 }
            r4.put(r5, r3)     // Catch:{ Throwable -> 0x00d8 }
            r1.add(r4)     // Catch:{ Throwable -> 0x00d8 }
            goto L_0x00ac
        L_0x00d3:
            java.lang.String r0 = "macArray"
            r2.put(r0, r1)     // Catch:{ Throwable -> 0x00d8 }
        L_0x00d8:
            java.util.HashMap r0 = r9.getMemoryInfo()     // Catch:{ Throwable -> 0x01ba }
            java.util.HashMap r1 = r9.getSizeInfo()     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r3 = "total"
            if (r0 == 0) goto L_0x00ed
            java.lang.String r4 = "ram"
            java.lang.Object r0 = r0.get(r3)     // Catch:{ Throwable -> 0x01ba }
            r2.put(r4, r0)     // Catch:{ Throwable -> 0x01ba }
        L_0x00ed:
            if (r1 == 0) goto L_0x0115
            java.lang.String r0 = "sdcard"
            java.lang.Object r0 = r1.get(r0)     // Catch:{ Throwable -> 0x01ba }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x01ba }
            if (r0 == 0) goto L_0x0102
            java.lang.String r4 = "sdcardStorage"
            java.lang.Object r0 = r0.get(r3)     // Catch:{ Throwable -> 0x01ba }
            r2.put(r4, r0)     // Catch:{ Throwable -> 0x01ba }
        L_0x0102:
            java.lang.String r0 = "data"
            java.lang.Object r0 = r1.get(r0)     // Catch:{ Throwable -> 0x01ba }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x01ba }
            if (r0 == 0) goto L_0x0115
            java.lang.String r1 = "dataStorage"
            java.lang.Object r0 = r0.get(r3)     // Catch:{ Throwable -> 0x01ba }
            r2.put(r1, r0)     // Catch:{ Throwable -> 0x01ba }
        L_0x0115:
            java.lang.String r0 = "romImg"
            java.lang.String r9 = r9.getMIUIVersion()     // Catch:{ Throwable -> 0x01ba }
            r2.put(r0, r9)     // Catch:{ Throwable -> 0x01ba }
            com.mob.tools.utils.Hashon r9 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x01ba }
            r9.<init>()     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r0 = r9.fromHashMap(r2)     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r1 = r8.m2228f()     // Catch:{ Throwable -> 0x01ba }
            byte[] r0 = com.mob.tools.utils.Data.AES128Encode((java.lang.String) r1, (java.lang.String) r0)     // Catch:{ Throwable -> 0x01ba }
            r1 = 2
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r1)     // Catch:{ Throwable -> 0x01ba }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01ba }
            r3.<init>()     // Catch:{ Throwable -> 0x01ba }
            com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r2 = "m"
            r1.<init>(r2, r0)     // Catch:{ Throwable -> 0x01ba }
            r3.add(r1)     // Catch:{ Throwable -> 0x01ba }
            com.mob.tools.network.NetworkHelper$NetworkTimeOut r6 = new com.mob.tools.network.NetworkHelper$NetworkTimeOut     // Catch:{ Throwable -> 0x01ba }
            r6.<init>()     // Catch:{ Throwable -> 0x01ba }
            r0 = 30000(0x7530, float:4.2039E-41)
            r6.readTimout = r0     // Catch:{ Throwable -> 0x01ba }
            r6.connectionTimeout = r0     // Catch:{ Throwable -> 0x01ba }
            com.mob.tools.network.NetworkHelper r1 = new com.mob.tools.network.NetworkHelper     // Catch:{ Throwable -> 0x01ba }
            r1.<init>()     // Catch:{ Throwable -> 0x01ba }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01ba }
            r0.<init>()     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r2 = f2009a     // Catch:{ Throwable -> 0x01ba }
            r0.append(r2)     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r2 = "/dinfo"
            r0.append(r2)     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r2 = r0.toString()     // Catch:{ Throwable -> 0x01ba }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01ba }
            r5.<init>()     // Catch:{ Throwable -> 0x01ba }
            com.mob.tools.network.KVPair r0 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r4 = "User-Identity"
            java.lang.String r7 = com.mob.commons.MobProductCollector.getUserIdentity()     // Catch:{ Throwable -> 0x01ba }
            r0.<init>(r4, r7)     // Catch:{ Throwable -> 0x01ba }
            r5.add(r0)     // Catch:{ Throwable -> 0x01ba }
            com.mob.tools.network.KVPair r0 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x01ba }
            r4 = 68
            java.lang.String r4 = com.mob.commons.C2312k.m2575a(r4)     // Catch:{ Throwable -> 0x01ba }
            android.content.Context r7 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r7 = com.mob.commons.p024b.C2273d.m2354d(r7)     // Catch:{ Throwable -> 0x01ba }
            r0.<init>(r4, r7)     // Catch:{ Throwable -> 0x01ba }
            r5.add(r0)     // Catch:{ Throwable -> 0x01ba }
            r4 = 0
            java.lang.String r0 = r1.httpPost((java.lang.String) r2, (java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>>) r3, (com.mob.tools.network.KVPair<java.lang.String>) r4, (java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>>) r5, (com.mob.tools.network.NetworkHelper.NetworkTimeOut) r6)     // Catch:{ Throwable -> 0x01ba }
            java.util.HashMap r9 = r9.fromJson((java.lang.String) r0)     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r0 = "200"
            java.lang.String r1 = "status"
            java.lang.Object r9 = r9.get(r1)     // Catch:{ Throwable -> 0x01ba }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Throwable -> 0x01ba }
            boolean r9 = r0.equals(r9)     // Catch:{ Throwable -> 0x01ba }
            if (r9 == 0) goto L_0x01c2
            com.mob.commons.C2308i.m2546i((java.lang.String) r10)     // Catch:{ Throwable -> 0x01ba }
            long r9 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x01ba }
            long r0 = com.mob.commons.C2262b.m2245L()     // Catch:{ Throwable -> 0x01ba }
            long r9 = r9 + r0
            r8.m2203a((long) r9)     // Catch:{ Throwable -> 0x01ba }
            goto L_0x01c2
        L_0x01ba:
            r9 = move-exception
            com.mob.tools.log.NLog r10 = com.mob.tools.MobLog.getInstance()
            r10.mo29787w((java.lang.Throwable) r9)
        L_0x01c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.C2255a.m2208a(java.util.HashMap, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m2216a(HashMap<String, Object> hashMap, MobProduct mobProduct) {
        if (mobProduct == null) {
            mobProduct = new MobProduct() {
                public String getProductTag() {
                    return "COMMON";
                }

                public int getSdkver() {
                    return MobSDK.SDK_VERSION_CODE;
                }
            };
        }
        boolean z = false;
        try {
            HashMap hashMap2 = (HashMap) hashMap.get("appInfo");
            if (hashMap2 == null) {
                hashMap2 = new HashMap();
                hashMap.put("appInfo", hashMap2);
                z = true;
            }
            String packageName = DeviceHelper.getInstance(MobSDK.getContext()).getPackageName();
            HashMap hashMap3 = (HashMap) hashMap2.get(packageName);
            if (hashMap3 == null) {
                hashMap3 = new HashMap();
                hashMap2.put(packageName, hashMap3);
                z = true;
            }
            String str = (String) hashMap3.get(mobProduct.getProductTag());
            String appkey = MobSDK.getAppkey();
            if ((str == null || !str.equals(appkey)) && m2211a(mobProduct, hashMap)) {
                return true;
            }
            return z;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return false;
        }
    }

    /* renamed from: a */
    private boolean m2211a(MobProduct mobProduct, HashMap<String, Object> hashMap) throws Throwable {
        if (C2262b.m2242I()) {
            return false;
        }
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("product", mobProduct.getProductTag()));
        String str = (String) hashMap.get("duid");
        arrayList.add(new KVPair("appkey", MobSDK.getAppkey()));
        arrayList.add(new KVPair("duid", str));
        arrayList.add(new KVPair("apppkg", String.valueOf(instance.getPackageName())));
        arrayList.add(new KVPair("appver", String.valueOf(instance.getAppVersion())));
        arrayList.add(new KVPair("sdkver", String.valueOf(mobProduct.getSdkver())));
        arrayList.add(new KVPair("network", String.valueOf(instance.getDetailNetworkTypeForStatic())));
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
        arrayList2.add(new KVPair(C2312k.m2575a(68), C2273d.m2354d(MobSDK.getContext())));
        HashMap fromJson = new Hashon().fromJson(new NetworkHelper().httpPost(f2009a + "/dsign", (ArrayList<KVPair<String>>) arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) arrayList2, networkTimeOut));
        if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(String.valueOf(fromJson.get("reup")))) {
            m2208a(hashMap, str);
        }
        if (!"200".equals(String.valueOf(fromJson.get("status")))) {
            return false;
        }
        ((HashMap) ((HashMap) hashMap.get("appInfo")).get(instance.getPackageName())).put(mobProduct.getProductTag(), MobSDK.getAppkey());
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m2225c(final HashMap<String, Object> hashMap) {
        C2300e.m2467a(C2300e.m2466a("comm/locks/.globalLock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                C2255a.this.m2209a((HashMap<String, Object>) hashMap, false);
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0097 A[SYNTHETIC, Splitter:B:33:0x0097] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a0 A[SYNTHETIC, Splitter:B:37:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m2209a(java.util.HashMap<java.lang.String, java.lang.Object> r5, boolean r6) {
        /*
            r4 = this;
            java.lang.String r0 = "duid"
            r1 = 0
            if (r6 != 0) goto L_0x004d
            java.lang.Object r6 = r5.get(r0)     // Catch:{ Throwable -> 0x008d }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Throwable -> 0x008d }
            android.content.Context r2 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x008d }
            com.mob.tools.utils.DeviceHelper r2 = com.mob.tools.utils.DeviceHelper.getInstance(r2)     // Catch:{ Throwable -> 0x008d }
            boolean r3 = r2.getSdcardState()     // Catch:{ Throwable -> 0x008d }
            if (r3 == 0) goto L_0x004d
            java.util.HashMap r3 = r4.m2227e()     // Catch:{ Throwable -> 0x008d }
            if (r3 == 0) goto L_0x0039
            java.lang.Object r2 = r3.get(r0)     // Catch:{ Throwable -> 0x008d }
            java.lang.Object r2 = com.mob.tools.utils.ResHelper.forceCast(r2, r1)     // Catch:{ Throwable -> 0x008d }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x008d }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x008d }
            if (r3 != 0) goto L_0x004d
            boolean r6 = r2.equals(r6)     // Catch:{ Throwable -> 0x008d }
            if (r6 != 0) goto L_0x004d
            r5.put(r0, r2)     // Catch:{ Throwable -> 0x008d }
            goto L_0x004d
        L_0x0039:
            r3 = 0
            java.lang.String r2 = r2.getWAbcd(r3)     // Catch:{ Throwable -> 0x008d }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x008d }
            if (r3 != 0) goto L_0x004d
            boolean r6 = r2.equals(r6)     // Catch:{ Throwable -> 0x008d }
            if (r6 != 0) goto L_0x004d
            r5.put(r0, r2)     // Catch:{ Throwable -> 0x008d }
        L_0x004d:
            java.io.File r6 = r4.m2222c()     // Catch:{ Throwable -> 0x008d }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x008d }
            r0.<init>(r6)     // Catch:{ Throwable -> 0x008d }
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ Throwable -> 0x008d }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x008d }
            r2.writeObject(r5)     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.io.File r5 = r4.m2226d()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.lang.String r0 = r6.getAbsolutePath()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.lang.String r1 = r5.getAbsolutePath()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            boolean r0 = r0.equals(r1)     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            if (r0 != 0) goto L_0x007e
            r5.delete()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            java.lang.String r5 = r5.getAbsolutePath()     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
            com.mob.tools.utils.ResHelper.copyFile((java.lang.String) r6, (java.lang.String) r5)     // Catch:{ Throwable -> 0x0088, all -> 0x0085 }
        L_0x007e:
            r2.flush()     // Catch:{ Throwable -> 0x009d }
            r2.close()     // Catch:{ Throwable -> 0x009d }
            goto L_0x009d
        L_0x0085:
            r5 = move-exception
            r1 = r2
            goto L_0x009e
        L_0x0088:
            r5 = move-exception
            r1 = r2
            goto L_0x008e
        L_0x008b:
            r5 = move-exception
            goto L_0x009e
        L_0x008d:
            r5 = move-exception
        L_0x008e:
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x008b }
            r6.mo29787w((java.lang.Throwable) r5)     // Catch:{ all -> 0x008b }
            if (r1 == 0) goto L_0x009d
            r1.flush()     // Catch:{ Throwable -> 0x009d }
            r1.close()     // Catch:{ Throwable -> 0x009d }
        L_0x009d:
            return
        L_0x009e:
            if (r1 == 0) goto L_0x00a6
            r1.flush()     // Catch:{ Throwable -> 0x00a6 }
            r1.close()     // Catch:{ Throwable -> 0x00a6 }
        L_0x00a6:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.C2255a.m2209a(java.util.HashMap, boolean):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public boolean m2229g() {
        try {
            File c = m2222c();
            File d = m2226d();
            if (c.getPath().equals(d.getPath())) {
                return false;
            }
            if (!c.exists() || !c.isFile()) {
                if (d.exists() && d.isFile()) {
                    ResHelper.copyFile(d.getAbsolutePath(), c.getAbsolutePath());
                    return true;
                }
                return false;
            }
            if (!d.exists() || !d.isFile()) {
                ResHelper.copyFile(c.getAbsolutePath(), d.getAbsolutePath());
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: a */
    private void m2207a(final String str, final MobProduct mobProduct) {
        new Thread() {
            public void run() {
                synchronized (C2255a.f2009a) {
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put("duid", str);
                        C2255a.this.m2208a((HashMap<String, Object>) hashMap, str);
                        boolean unused = C2255a.this.m2216a((HashMap<String, Object>) hashMap, mobProduct);
                        C2255a.this.m2225c((HashMap<String, Object>) hashMap);
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29769d(th);
                    }
                }
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final String mo29033a(final boolean z, final boolean z2) {
        final String[] strArr = new String[1];
        C2300e.m2467a(C2300e.m2466a("comm/locks/.globalLock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                try {
                    if (!z) {
                        boolean unused = C2255a.this.m2229g();
                    }
                    HashMap c = C2255a.this.m2227e();
                    if (c == null) {
                        c = new HashMap();
                    }
                    String str = (String) c.get("duid");
                    if (str == null && z2) {
                        str = C2255a.this.m2201a(!z);
                        if (!z) {
                            c.put("duid", str);
                            C2255a.this.m2209a((HashMap<String, Object>) c, false);
                        }
                    }
                    strArr[0] = str;
                } catch (Throwable th) {
                    MobLog.getInstance().mo29787w(th);
                }
                return false;
            }
        });
        return strArr[0];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final String mo29031a() {
        try {
            HashMap<String, Object> e = m2227e();
            String str = e != null ? (String) e.get("duid") : null;
            return str == null ? m2200a((String) null) : str;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    /* renamed from: a */
    private void m2203a(long j) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.digap")));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public boolean m2230h() {
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.digap");
        if (dataCacheFile == null || !dataCacheFile.exists()) {
            m2203a(C2262b.m2245L() + C2262b.m2260a());
        } else {
            try {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(dataCacheFile));
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                if (readLong < C2262b.m2260a()) {
                    return true;
                }
                return false;
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050 A[Catch:{ Throwable -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057 A[Catch:{ Throwable -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066 A[Catch:{ Throwable -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0068 A[Catch:{ Throwable -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b A[Catch:{ Throwable -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00aa A[Catch:{ Throwable -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m2204a(android.content.Context r12) {
        /*
            java.lang.String r0 = "comm/dbs/.duid"
            java.lang.String r1 = "unknown"
            if (r12 != 0) goto L_0x0007
            return
        L_0x0007:
            java.io.File r2 = com.mob.tools.utils.ResHelper.getCacheRootFile(r12, r0)     // Catch:{ Throwable -> 0x00d8 }
            java.util.HashMap r3 = m2202a((java.io.File) r2)     // Catch:{ Throwable -> 0x00d8 }
            android.content.Context r4 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x00d8 }
            java.io.File r0 = com.mob.tools.utils.ResHelper.getDataCacheFile(r4, r0)     // Catch:{ Throwable -> 0x00d8 }
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x0024
            boolean r6 = r3.isEmpty()     // Catch:{ Throwable -> 0x00d8 }
            if (r6 == 0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            r6 = 1
            goto L_0x0029
        L_0x0024:
            java.util.HashMap r3 = m2202a((java.io.File) r0)     // Catch:{ Throwable -> 0x00d8 }
            r6 = 0
        L_0x0029:
            com.mob.tools.utils.DeviceHelper r7 = com.mob.tools.utils.DeviceHelper.getInstance(r12)     // Catch:{ Throwable -> 0x00d8 }
            if (r3 == 0) goto L_0x00a8
            java.lang.String r8 = "deviceInfo"
            java.lang.Object r3 = r3.get(r8)     // Catch:{ Throwable -> 0x00d8 }
            java.util.HashMap r3 = (java.util.HashMap) r3     // Catch:{ Throwable -> 0x00d8 }
            if (r3 == 0) goto L_0x00a8
            java.lang.String r8 = "model"
            java.lang.Object r8 = r3.get(r8)     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r9 = "factory"
            java.lang.Object r3 = r3.get(r9)     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x00d8 }
            boolean r9 = r1.equalsIgnoreCase(r8)     // Catch:{ Throwable -> 0x00d8 }
            r10 = 0
            if (r9 == 0) goto L_0x0051
            r8 = r10
        L_0x0051:
            boolean r9 = r1.equalsIgnoreCase(r3)     // Catch:{ Throwable -> 0x00d8 }
            if (r9 == 0) goto L_0x0058
            r3 = r10
        L_0x0058:
            if (r6 == 0) goto L_0x0068
            boolean r6 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Throwable -> 0x00d8 }
            if (r6 != 0) goto L_0x0066
            boolean r6 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x00d8 }
            if (r6 == 0) goto L_0x0068
        L_0x0066:
            r6 = 1
            goto L_0x0069
        L_0x0068:
            r6 = 0
        L_0x0069:
            if (r6 != 0) goto L_0x00a8
            java.lang.String r6 = r7.getModel()     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r9 = r7.getManufacturer()     // Catch:{ Throwable -> 0x00d8 }
            boolean r11 = r1.equalsIgnoreCase(r6)     // Catch:{ Throwable -> 0x00d8 }
            if (r11 == 0) goto L_0x007a
            r6 = r10
        L_0x007a:
            boolean r1 = r1.equalsIgnoreCase(r9)     // Catch:{ Throwable -> 0x00d8 }
            if (r1 == 0) goto L_0x0081
            r9 = r10
        L_0x0081:
            boolean r1 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x00d8 }
            if (r1 != 0) goto L_0x0093
            boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Throwable -> 0x00d8 }
            if (r1 != 0) goto L_0x0093
            boolean r1 = r6.equalsIgnoreCase(r8)     // Catch:{ Throwable -> 0x00d8 }
            if (r1 == 0) goto L_0x00a5
        L_0x0093:
            boolean r1 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Throwable -> 0x00d8 }
            if (r1 != 0) goto L_0x00a7
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x00d8 }
            if (r1 != 0) goto L_0x00a7
            boolean r1 = r9.equalsIgnoreCase(r3)     // Catch:{ Throwable -> 0x00d8 }
            if (r1 != 0) goto L_0x00a7
        L_0x00a5:
            r6 = 1
            goto L_0x00a8
        L_0x00a7:
            r6 = 0
        L_0x00a8:
            if (r6 == 0) goto L_0x00e0
            r2.delete()     // Catch:{ Throwable -> 0x00d8 }
            r0.delete()     // Catch:{ Throwable -> 0x00d8 }
            r7.removeWABCD()     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r0 = "comm/.di"
            java.io.File r0 = com.mob.tools.utils.ResHelper.getCacheRootFile(r12, r0)     // Catch:{ Throwable -> 0x00d8 }
            r0.delete()     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r0 = ".dk"
            java.io.File r0 = com.mob.tools.utils.ResHelper.getCacheRootFile(r12, r0)     // Catch:{ Throwable -> 0x00d8 }
            r0.delete()     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r0 = ".mcw"
            java.io.File r0 = com.mob.tools.utils.ResHelper.getCacheRootFile(r12, r0)     // Catch:{ Throwable -> 0x00d8 }
            r0.delete()     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r0 = ".slw"
            java.io.File r12 = com.mob.tools.utils.ResHelper.getCacheRootFile(r12, r0)     // Catch:{ Throwable -> 0x00d8 }
            r12.delete()     // Catch:{ Throwable -> 0x00d8 }
            goto L_0x00e0
        L_0x00d8:
            r12 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29787w((java.lang.Throwable) r12)
        L_0x00e0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.C2255a.m2204a(android.content.Context):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:24|(0)|28|29) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x003b */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0031 A[SYNTHETIC, Splitter:B:22:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0038 A[SYNTHETIC, Splitter:B:26:0x0038] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.HashMap<java.lang.String, java.lang.Object> m2202a(java.io.File r3) {
        /*
            r0 = 0
            boolean r1 = r3.exists()     // Catch:{ Throwable -> 0x003c }
            if (r1 == 0) goto L_0x003c
            boolean r1 = r3.isFile()     // Catch:{ Throwable -> 0x003c }
            if (r1 == 0) goto L_0x003c
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0026, all -> 0x0023 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x0026, all -> 0x0023 }
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0026, all -> 0x0023 }
            r3.<init>(r1)     // Catch:{ Throwable -> 0x0026, all -> 0x0023 }
            java.lang.Object r1 = r3.readObject()     // Catch:{ Throwable -> 0x0021 }
            java.util.HashMap r1 = (java.util.HashMap) r1     // Catch:{ Throwable -> 0x0021 }
            r3.close()     // Catch:{ Throwable -> 0x0020 }
        L_0x0020:
            return r1
        L_0x0021:
            r1 = move-exception
            goto L_0x0028
        L_0x0023:
            r1 = move-exception
            r3 = r0
            goto L_0x0036
        L_0x0026:
            r1 = move-exception
            r3 = r0
        L_0x0028:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0035 }
            r2.mo29787w((java.lang.Throwable) r1)     // Catch:{ all -> 0x0035 }
            if (r3 == 0) goto L_0x003c
            r3.close()     // Catch:{ Throwable -> 0x003c }
            goto L_0x003c
        L_0x0035:
            r1 = move-exception
        L_0x0036:
            if (r3 == 0) goto L_0x003b
            r3.close()     // Catch:{ Throwable -> 0x003b }
        L_0x003b:
            throw r1     // Catch:{ Throwable -> 0x003c }
        L_0x003c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.C2255a.m2202a(java.io.File):java.util.HashMap");
    }
}
