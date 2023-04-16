package com.mob.commons;

import android.os.SystemClock;
import com.mob.guard.MobGuard;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import p005cn.sharesdk.framework.ShareSDK;

public class MobProductCollector implements PublicMemberKeeper {
    public static final String[] MOB_PRODUCTS = {ShareSDK.SDK_TAG, "SMSSDK", "MOBLINK", "MOBPUSH", "SECVERIFY", "MOBADSDK", MobGuard.SDK_TAG, "GESVERIFY", "MOBAPM"};
    public static final String[] MOB_PRODUCTS_DEPRECATED = {"SHAREREC", "MOBAPI", "UMSSDK", "CMSSDK", "BBSSDK", "SHOPSDK", "PAYSDK", "MOBIM", "ANALYSDK", "MOBVERIFY"};
    public static final String[] MOB_SOLUTIONS = {"GROWSOLUTION"};

    /* renamed from: a */
    private static boolean f1916a = false;

    /* renamed from: b */
    private static final HashMap<String, MobProduct> f1917b = new HashMap<>();

    /* renamed from: c */
    private static final HashMap<String, MobSolution> f1918c = new HashMap<>();

    public static synchronized boolean registerProduct(MobProduct mobProduct) {
        synchronized (MobProductCollector.class) {
            if (mobProduct != null) {
                if (!f1917b.containsKey(mobProduct.getProductTag())) {
                    f1917b.put(mobProduct.getProductTag(), mobProduct);
                    return true;
                }
            }
            return false;
        }
    }

    public static synchronized void collect() {
        synchronized (MobProductCollector.class) {
            getProducts();
        }
    }

    public static void syncInit() {
        try {
            MOBLINK moblink = new MOBLINK();
            if (moblink instanceof MobProduct) {
                moblink.getProductTag();
            }
        } catch (Throwable unused) {
        }
    }

    public static synchronized ArrayList<MobProduct> getProducts() {
        ArrayList<MobProduct> arrayList;
        synchronized (MobProductCollector.class) {
            if (!f1916a) {
                f1917b.putAll(m1944a());
                f1916a = true;
            }
            arrayList = new ArrayList<>();
            arrayList.addAll(f1917b.values());
        }
        return arrayList;
    }

    /* renamed from: a */
    private static HashMap<String, MobProduct> m1944a() {
        Class<?> cls;
        HashMap<String, MobProduct> hashMap = new HashMap<>();
        for (Object next : C2306g.f2134a) {
            try {
                if (next instanceof String) {
                    cls = Class.forName(String.valueOf(next).trim());
                } else {
                    cls = (Class) next;
                }
                int i = 0;
                if (!MobProduct.class.isAssignableFrom(cls) || MobProduct.class.equals(cls)) {
                    if (MobSolution.class.isAssignableFrom(cls) && !MobSolution.class.equals(cls)) {
                        MobSolution mobSolution = (MobSolution) cls.newInstance();
                        String solutionTag = mobSolution.getSolutionTag();
                        String[] strArr = MOB_SOLUTIONS;
                        int length = strArr.length;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            String str = strArr[i];
                            if (str.equals(solutionTag)) {
                                f1918c.put(str, mobSolution);
                                break;
                            }
                            i++;
                        }
                    } else {
                        cls.newInstance();
                    }
                } else {
                    MobProduct mobProduct = (MobProduct) cls.newInstance();
                    String productTag = mobProduct.getProductTag();
                    String[] strArr2 = MOB_PRODUCTS;
                    int length2 = strArr2.length;
                    while (true) {
                        if (i >= length2) {
                            break;
                        }
                        String str2 = strArr2[i];
                        if (str2.equals(productTag)) {
                            hashMap.put(str2, mobProduct);
                            break;
                        }
                        i++;
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        }
        return hashMap;
    }

    public static synchronized String getUserIdentity() {
        String userIdentity;
        synchronized (MobProductCollector.class) {
            userIdentity = getUserIdentity(getProducts());
        }
        return userIdentity;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x00cb A[Catch:{ Throwable -> 0x0276 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x013c A[Catch:{ Throwable -> 0x0276 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x01a9 A[Catch:{ Throwable -> 0x0276 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x020a A[Catch:{ Throwable -> 0x0276 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String getUserIdentity(java.util.ArrayList<com.mob.commons.MobProduct> r18) {
        /*
            java.lang.Class<com.mob.commons.MobProductCollector> r1 = com.mob.commons.MobProductCollector.class
            monitor-enter(r1)
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0276 }
            com.mob.tools.utils.DeviceHelper r0 = com.mob.tools.utils.DeviceHelper.getInstance(r0)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = r0.getPackageName()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r3 = "utf-8"
            java.lang.String r2 = java.net.URLEncoder.encode(r2, r3)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r3 = r0.getAppVersionName()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r4 = "utf-8"
            java.lang.String r3 = java.net.URLEncoder.encode(r3, r4)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r4 = r0.getManufacturer()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r5 = "utf-8"
            java.lang.String r4 = java.net.URLEncoder.encode(r4, r5)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r5 = r0.getModel()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r6 = "utf-8"
            java.lang.String r5 = java.net.URLEncoder.encode(r5, r6)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r6 = r0.getMIUIVersion()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r7 = "utf-8"
            java.lang.String r6 = java.net.URLEncoder.encode(r6, r7)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r7 = r0.getOSVersionName()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r8 = "utf-8"
            java.lang.String r7 = java.net.URLEncoder.encode(r7, r8)     // Catch:{ Throwable -> 0x0276 }
            com.mob.commons.f r8 = com.mob.commons.C2305f.m2476a()     // Catch:{ Throwable -> 0x0276 }
            java.util.HashMap r8 = r8.mo29100b()     // Catch:{ Throwable -> 0x0276 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r9.<init>()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r10 = "APP/"
            r9.append(r10)     // Catch:{ Throwable -> 0x0276 }
            r9.append(r2)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = ";"
            r9.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r9.append(r3)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = r9.toString()     // Catch:{ Throwable -> 0x0276 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r3.<init>()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r9 = "SYS/Android;"
            r3.append(r9)     // Catch:{ Throwable -> 0x0276 }
            int r9 = r0.getOSVersionInt()     // Catch:{ Throwable -> 0x0276 }
            r3.append(r9)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r9 = ";"
            r3.append(r9)     // Catch:{ Throwable -> 0x0276 }
            r3.append(r7)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x0276 }
            boolean r7 = com.mob.commons.C2212a.m1961c()     // Catch:{ Throwable -> 0x0276 }
            if (r7 == 0) goto L_0x0097
            boolean r7 = com.mob.commons.C2212a.m1963d()     // Catch:{ Throwable -> 0x0276 }
            if (r7 == 0) goto L_0x0092
            goto L_0x0097
        L_0x0092:
            java.lang.String r7 = m1945b()     // Catch:{ Throwable -> 0x0276 }
            goto L_0x009b
        L_0x0097:
            java.lang.String r7 = r0.getDeviceKey()     // Catch:{ Throwable -> 0x0276 }
        L_0x009b:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r9.<init>()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r10 = "SDI/"
            r9.append(r10)     // Catch:{ Throwable -> 0x0276 }
            r9.append(r7)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r7 = r9.toString()     // Catch:{ Throwable -> 0x0276 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r9.<init>()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r10 = "FM/"
            r9.append(r10)     // Catch:{ Throwable -> 0x0276 }
            r9.append(r4)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r4 = ";"
            r9.append(r4)     // Catch:{ Throwable -> 0x0276 }
            r9.append(r5)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r4 = r9.toString()     // Catch:{ Throwable -> 0x0276 }
            boolean r5 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x0276 }
            if (r5 != 0) goto L_0x00df
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r5.<init>()     // Catch:{ Throwable -> 0x0276 }
            r5.append(r4)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r4 = ";"
            r5.append(r4)     // Catch:{ Throwable -> 0x0276 }
            r5.append(r6)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r4 = r5.toString()     // Catch:{ Throwable -> 0x0276 }
        L_0x00df:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r5.<init>()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r6 = "NE/"
            r5.append(r6)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r6 = r0.getNetworkTypeForStatic()     // Catch:{ Throwable -> 0x0276 }
            r5.append(r6)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r6 = ";"
            r5.append(r6)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r6 = r0.getCarrier()     // Catch:{ Throwable -> 0x0276 }
            r5.append(r6)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x0276 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r6.<init>()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r9 = "Lang/"
            r6.append(r9)     // Catch:{ Throwable -> 0x0276 }
            java.util.Locale r9 = java.util.Locale.getDefault()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r10 = "-r"
            java.lang.String r11 = "-"
            java.lang.String r9 = r9.replace(r10, r11)     // Catch:{ Throwable -> 0x0276 }
            r6.append(r9)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0276 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r9.<init>()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r10 = "CLV/"
            r9.append(r10)     // Catch:{ Throwable -> 0x0276 }
            int r10 = com.mob.MobSDK.SDK_VERSION_CODE     // Catch:{ Throwable -> 0x0276 }
            r9.append(r10)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r10 = "SDK/"
            boolean r11 = r18.isEmpty()     // Catch:{ Throwable -> 0x0276 }
            if (r11 != 0) goto L_0x019d
            int r11 = r18.size()     // Catch:{ Throwable -> 0x0276 }
            r13 = r10
            r10 = 0
        L_0x0142:
            if (r10 >= r11) goto L_0x019c
            r14 = r18
            java.lang.Object r15 = r14.get(r10)     // Catch:{ Throwable -> 0x0195 }
            com.mob.commons.MobProduct r15 = (com.mob.commons.MobProduct) r15     // Catch:{ Throwable -> 0x0195 }
            if (r10 == 0) goto L_0x0162
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0195 }
            r12.<init>()     // Catch:{ Throwable -> 0x0195 }
            r12.append(r13)     // Catch:{ Throwable -> 0x0195 }
            r17 = r11
            java.lang.String r11 = ","
            r12.append(r11)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r13 = r12.toString()     // Catch:{ Throwable -> 0x0197 }
            goto L_0x0164
        L_0x0162:
            r17 = r11
        L_0x0164:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0197 }
            r11.<init>()     // Catch:{ Throwable -> 0x0197 }
            r11.append(r13)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r12 = r15.getProductTag()     // Catch:{ Throwable -> 0x0197 }
            r11.append(r12)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r12 = ";"
            r11.append(r12)     // Catch:{ Throwable -> 0x0197 }
            int r12 = r15.getSdkver()     // Catch:{ Throwable -> 0x0197 }
            r11.append(r12)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r12 = ";"
            r11.append(r12)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r12 = r15.getProductTag()     // Catch:{ Throwable -> 0x0197 }
            java.lang.Object r12 = r8.get(r12)     // Catch:{ Throwable -> 0x0197 }
            r11.append(r12)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x0197 }
            r13 = r11
            goto L_0x0197
        L_0x0195:
            r17 = r11
        L_0x0197:
            int r10 = r10 + 1
            r11 = r17
            goto L_0x0142
        L_0x019c:
            r10 = r13
        L_0x019d:
            java.lang.String r8 = "DC/2"
            java.lang.String r11 = ""
            java.util.HashMap<java.lang.String, com.mob.commons.MobSolution> r12 = f1918c     // Catch:{ Throwable -> 0x0276 }
            boolean r12 = r12.isEmpty()     // Catch:{ Throwable -> 0x0276 }
            if (r12 != 0) goto L_0x01fe
            java.lang.String r11 = " P/"
            java.util.HashMap<java.lang.String, com.mob.commons.MobSolution> r12 = f1918c     // Catch:{ Throwable -> 0x0276 }
            java.util.Set r12 = r12.entrySet()     // Catch:{ Throwable -> 0x0276 }
            java.util.Iterator r12 = r12.iterator()     // Catch:{ Throwable -> 0x0276 }
            r16 = 0
        L_0x01b7:
            boolean r13 = r12.hasNext()     // Catch:{ Throwable -> 0x0276 }
            if (r13 == 0) goto L_0x01fe
            java.lang.Object r13 = r12.next()     // Catch:{ Throwable -> 0x0276 }
            java.util.Map$Entry r13 = (java.util.Map.Entry) r13     // Catch:{ Throwable -> 0x0276 }
            if (r16 == 0) goto L_0x01d6
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01b7 }
            r14.<init>()     // Catch:{ Throwable -> 0x01b7 }
            r14.append(r11)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r15 = ","
            r14.append(r15)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r11 = r14.toString()     // Catch:{ Throwable -> 0x01b7 }
        L_0x01d6:
            int r16 = r16 + 1
            java.lang.Object r13 = r13.getValue()     // Catch:{ Throwable -> 0x01b7 }
            com.mob.commons.MobSolution r13 = (com.mob.commons.MobSolution) r13     // Catch:{ Throwable -> 0x01b7 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01b7 }
            r14.<init>()     // Catch:{ Throwable -> 0x01b7 }
            r14.append(r11)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r15 = r13.getSolutionTag()     // Catch:{ Throwable -> 0x01b7 }
            r14.append(r15)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r15 = ";"
            r14.append(r15)     // Catch:{ Throwable -> 0x01b7 }
            int r13 = r13.getSolutionVer()     // Catch:{ Throwable -> 0x01b7 }
            r14.append(r13)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r11 = r14.toString()     // Catch:{ Throwable -> 0x01b7 }
            goto L_0x01b7
        L_0x01fe:
            java.lang.String r12 = ""
            java.lang.String r0 = r0.getTimezone()     // Catch:{ Throwable -> 0x0276 }
            boolean r13 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x0276 }
            if (r13 != 0) goto L_0x021b
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r12.<init>()     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r13 = "TZ_"
            r12.append(r13)     // Catch:{ Throwable -> 0x0276 }
            r12.append(r0)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x0276 }
        L_0x021b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0276 }
            r0.<init>()     // Catch:{ Throwable -> 0x0276 }
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = " "
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r3)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = " "
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r7)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = " "
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r4)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = " "
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r5)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = " "
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r6)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = " "
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r9)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = " "
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r10)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = " "
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r8)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r11)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r2 = " "
            r0.append(r2)     // Catch:{ Throwable -> 0x0276 }
            r0.append(r12)     // Catch:{ Throwable -> 0x0276 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0276 }
            monitor-exit(r1)
            return r0
        L_0x0274:
            r0 = move-exception
            goto L_0x0282
        L_0x0276:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0274 }
            r2.mo29787w((java.lang.Throwable) r0)     // Catch:{ all -> 0x0274 }
            java.lang.String r0 = ""
            monitor-exit(r1)
            return r0
        L_0x0282:
            monitor-exit(r1)
            goto L_0x0285
        L_0x0284:
            throw r0
        L_0x0285:
            goto L_0x0284
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.MobProductCollector.getUserIdentity(java.util.ArrayList):java.lang.String");
    }

    /* renamed from: b */
    private static String m1945b() {
        return m1943a(40);
    }

    /* renamed from: a */
    private static String m1943a(int i) {
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
}
