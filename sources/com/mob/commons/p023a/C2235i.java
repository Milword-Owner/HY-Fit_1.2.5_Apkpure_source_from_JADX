package com.mob.commons.p023a;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.hjq.permissions.Permission;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.C2300e;
import com.mob.commons.C2312k;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.mob.commons.a.i */
/* compiled from: DUClt */
public class C2235i extends C2226d {

    /* renamed from: a */
    private PackageManager f1967a;

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return C2262b.m2253T() > 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return C2300e.m2466a("comm/locks/.du_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        mo28996a(1);
        mo28997a(1, C2262b.m2253T() * 1000);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        if (message.what == 1) {
            long a = C2262b.m2260a();
            long i = m2058i();
            if (a >= i) {
                m2057h();
                long U = C2262b.m2254U() * 1000;
                m2050a(a + U);
                mo28997a(1, U);
                return;
            }
            mo28997a(1, i - a);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ee, code lost:
        if (r4 != null) goto L_0x00da;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00f4 A[SYNTHETIC, Splitter:B:31:0x00f4] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.Long> m2048a(java.lang.String r15) {
        /*
            r14 = this;
            java.lang.String r0 = "utf-8"
            java.lang.String r1 = " "
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r3 = 146(0x92, float:2.05E-43)
            r4 = 0
            java.lang.String r5 = com.mob.commons.C2312k.m2575a(r3)     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r5 = com.mob.tools.utils.ReflectHelper.importClass(r5)     // Catch:{ Throwable -> 0x00e6 }
            r6 = 147(0x93, float:2.06E-43)
            java.lang.String r7 = com.mob.commons.C2312k.m2575a(r6)     // Catch:{ Throwable -> 0x00e6 }
            r8 = 0
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00e6 }
            java.lang.Object r5 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r5, r7, r9)     // Catch:{ Throwable -> 0x00e6 }
            r7 = 148(0x94, float:2.07E-43)
            java.lang.String r9 = com.mob.commons.C2312k.m2575a(r7)     // Catch:{ Throwable -> 0x00e6 }
            r10 = 1
            java.lang.Object[] r11 = new java.lang.Object[r10]     // Catch:{ Throwable -> 0x00e6 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00e6 }
            r12.<init>()     // Catch:{ Throwable -> 0x00e6 }
            r13 = 151(0x97, float:2.12E-43)
            java.lang.String r13 = com.mob.commons.C2312k.m2575a(r13)     // Catch:{ Throwable -> 0x00e6 }
            r12.append(r13)     // Catch:{ Throwable -> 0x00e6 }
            r12.append(r1)     // Catch:{ Throwable -> 0x00e6 }
            r12.append(r15)     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x00e6 }
            r11[r8] = r12     // Catch:{ Throwable -> 0x00e6 }
            java.lang.Object r5 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r5, r9, r11)     // Catch:{ Throwable -> 0x00e6 }
            r9 = 149(0x95, float:2.09E-43)
            java.lang.String r11 = com.mob.commons.C2312k.m2575a(r9)     // Catch:{ Throwable -> 0x00e6 }
            java.lang.Object[] r12 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00e6 }
            java.lang.Object r5 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r5, r11, r12)     // Catch:{ Throwable -> 0x00e6 }
            java.io.InputStream r5 = (java.io.InputStream) r5     // Catch:{ Throwable -> 0x00e6 }
            java.io.InputStreamReader r11 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00e6 }
            r11.<init>(r5, r0)     // Catch:{ Throwable -> 0x00e6 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00e6 }
            r5.<init>(r11)     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r4 = r5.readLine()     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            boolean r11 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            if (r11 == 0) goto L_0x00bb
            java.lang.String r3 = com.mob.commons.C2312k.m2575a(r3)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.String r3 = com.mob.tools.utils.ReflectHelper.importClass(r3)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.String r4 = com.mob.commons.C2312k.m2575a(r6)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.Object r3 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r3, r4, r6)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.String r4 = com.mob.commons.C2312k.m2575a(r7)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.Object[] r6 = new java.lang.Object[r10]     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            r7.<init>()     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            r11 = 153(0x99, float:2.14E-43)
            java.lang.String r11 = com.mob.commons.C2312k.m2575a(r11)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            r7.append(r11)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            r7.append(r1)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            r7.append(r15)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.String r15 = r7.toString()     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            r6[r8] = r15     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.Object r15 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r3, r4, r6)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.String r1 = com.mob.commons.C2312k.m2575a(r9)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.Object[] r3 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.Object r15 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r15, r1, r3)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.io.InputStream r15 = (java.io.InputStream) r15     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            r1.<init>(r15, r0)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x00e1, all -> 0x00de }
            java.lang.String r15 = r4.readLine()     // Catch:{ Throwable -> 0x00e6 }
            goto L_0x00bd
        L_0x00bb:
            r15 = r4
            r4 = r5
        L_0x00bd:
            boolean r0 = android.text.TextUtils.isEmpty(r15)     // Catch:{ Throwable -> 0x00e6 }
            if (r0 != 0) goto L_0x00da
        L_0x00c3:
            java.lang.Object[] r15 = r14.m2056c(r15)     // Catch:{ Throwable -> 0x00e6 }
            if (r15 == 0) goto L_0x00d4
            r0 = r15[r8]     // Catch:{ Throwable -> 0x00e6 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x00e6 }
            r15 = r15[r10]     // Catch:{ Throwable -> 0x00e6 }
            java.lang.Long r15 = (java.lang.Long) r15     // Catch:{ Throwable -> 0x00e6 }
            r2.put(r0, r15)     // Catch:{ Throwable -> 0x00e6 }
        L_0x00d4:
            java.lang.String r15 = r4.readLine()     // Catch:{ Throwable -> 0x00e6 }
            if (r15 != 0) goto L_0x00c3
        L_0x00da:
            r4.close()     // Catch:{ Throwable -> 0x00f1 }
            goto L_0x00f1
        L_0x00de:
            r15 = move-exception
            r4 = r5
            goto L_0x00f2
        L_0x00e1:
            r15 = move-exception
            r4 = r5
            goto L_0x00e7
        L_0x00e4:
            r15 = move-exception
            goto L_0x00f2
        L_0x00e6:
            r15 = move-exception
        L_0x00e7:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x00e4 }
            r0.mo29769d(r15)     // Catch:{ all -> 0x00e4 }
            if (r4 == 0) goto L_0x00f1
            goto L_0x00da
        L_0x00f1:
            return r2
        L_0x00f2:
            if (r4 == 0) goto L_0x00f7
            r4.close()     // Catch:{ Throwable -> 0x00f7 }
        L_0x00f7:
            goto L_0x00f9
        L_0x00f8:
            throw r15
        L_0x00f9:
            goto L_0x00f8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2235i.m2048a(java.lang.String):java.util.HashMap");
    }

    /* renamed from: h */
    private void m2057h() {
        try {
            if (DeviceHelper.getInstance(MobSDK.getContext()).checkPermission(Permission.READ_EXTERNAL_STORAGE)) {
                String str = Environment.getExternalStorageDirectory() + C2312k.m2575a(16);
                if (new File(str).isDirectory()) {
                    HashMap<String, Long> a = m2048a(str);
                    if (a == null || a.isEmpty()) {
                        MobLog.getInstance().mo29768d("[%s] %s", "DUClt", "No subs");
                        return;
                    }
                    long a2 = C2262b.m2260a();
                    HashMap<String, Long> j = m2059j();
                    ArrayList arrayList = new ArrayList();
                    HashMap hashMap = new HashMap();
                    for (Map.Entry next : a.entrySet()) {
                        Long l = (Long) next.getValue();
                        File file = new File(str + ((String) next.getKey()));
                        if (m2053a(file)) {
                            arrayList.add(file.getName());
                            long a3 = m2046a(file.getAbsolutePath(), l);
                            Long l2 = j.get(file.getName());
                            long j2 = 0;
                            if (l2 != null) {
                                j2 = l2.longValue();
                            }
                            if (a3 > j2) {
                                j.put(file.getName(), Long.valueOf(a3));
                                hashMap.put(file.getName(), Long.valueOf(a3));
                            }
                        }
                    }
                    this.f1967a = null;
                    ArrayList arrayList2 = new ArrayList();
                    for (String next2 : j.keySet()) {
                        if (!arrayList.contains(next2)) {
                            arrayList2.add(next2);
                        }
                    }
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        j.remove((String) it.next());
                    }
                    m2051a(j);
                    long a4 = C2262b.m2260a();
                    if (!hashMap.isEmpty() || !arrayList2.isEmpty()) {
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("scanAt", Long.valueOf(a2));
                        hashMap2.put("update", hashMap);
                        hashMap2.put("delete", arrayList2);
                        HashMap hashMap3 = new HashMap();
                        hashMap3.put("type", "APP_DIR_ACTIVE");
                        hashMap3.put(ShareConstants.WEB_DIALOG_PARAM_DATA, hashMap2);
                        hashMap3.put("datetime", Long.valueOf(a4));
                        C2293c.m2435a().mo29068a(a4, (HashMap<String, Object>) hashMap3);
                    }
                    long a5 = C2262b.m2260a() - a2;
                    MobLog.getInstance().mo29775i("[%s] %s", "DUClt", "ttl: " + arrayList.size() + ", u: " + hashMap.size() + ", d: " + arrayList2.size() + ", dur: " + a5 + " ms");
                    return;
                }
                MobLog.getInstance().mo29768d("[%s] %s", "DUClt", "Can not read");
                return;
            }
            MobLog.getInstance().mo29768d("[%s] %s", "DUClt", "No permission");
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "DUClt", th.getMessage());
        }
    }

    /* renamed from: a */
    private long m2046a(String str, Long l) {
        System.currentTimeMillis();
        long longValue = l.longValue();
        new LinkedList();
        List<Long> b = m2055b(str);
        if (b != null && !b.isEmpty()) {
            for (Long next : b) {
                if (next.longValue() > longValue) {
                    longValue = next.longValue();
                }
            }
        }
        return longValue;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00fe, code lost:
        if (r4 != null) goto L_0x00ea;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0104 A[SYNTHETIC, Splitter:B:35:0x0104] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.Long> m2055b(java.lang.String r15) {
        /*
            r14 = this;
            java.lang.String r0 = "utf-8"
            java.lang.String r1 = " "
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 146(0x92, float:2.05E-43)
            r4 = 0
            java.lang.String r5 = com.mob.commons.C2312k.m2575a(r3)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r5 = com.mob.tools.utils.ReflectHelper.importClass(r5)     // Catch:{ Throwable -> 0x00f6 }
            r6 = 147(0x93, float:2.06E-43)
            java.lang.String r7 = com.mob.commons.C2312k.m2575a(r6)     // Catch:{ Throwable -> 0x00f6 }
            r8 = 0
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00f6 }
            java.lang.Object r5 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r5, r7, r9)     // Catch:{ Throwable -> 0x00f6 }
            r7 = 148(0x94, float:2.07E-43)
            java.lang.String r9 = com.mob.commons.C2312k.m2575a(r7)     // Catch:{ Throwable -> 0x00f6 }
            r10 = 1
            java.lang.Object[] r11 = new java.lang.Object[r10]     // Catch:{ Throwable -> 0x00f6 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00f6 }
            r12.<init>()     // Catch:{ Throwable -> 0x00f6 }
            r13 = 152(0x98, float:2.13E-43)
            java.lang.String r13 = com.mob.commons.C2312k.m2575a(r13)     // Catch:{ Throwable -> 0x00f6 }
            r12.append(r13)     // Catch:{ Throwable -> 0x00f6 }
            r12.append(r1)     // Catch:{ Throwable -> 0x00f6 }
            r12.append(r15)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x00f6 }
            r11[r8] = r12     // Catch:{ Throwable -> 0x00f6 }
            java.lang.Object r5 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r5, r9, r11)     // Catch:{ Throwable -> 0x00f6 }
            r9 = 149(0x95, float:2.09E-43)
            java.lang.String r11 = com.mob.commons.C2312k.m2575a(r9)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.Object[] r12 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00f6 }
            java.lang.Object r5 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r5, r11, r12)     // Catch:{ Throwable -> 0x00f6 }
            java.io.InputStream r5 = (java.io.InputStream) r5     // Catch:{ Throwable -> 0x00f6 }
            java.io.InputStreamReader r11 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00f6 }
            r11.<init>(r5, r0)     // Catch:{ Throwable -> 0x00f6 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00f6 }
            r5.<init>(r11)     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r4 = r5.readLine()     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            boolean r11 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            if (r11 == 0) goto L_0x00bb
            java.lang.String r3 = com.mob.commons.C2312k.m2575a(r3)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.String r3 = com.mob.tools.utils.ReflectHelper.importClass(r3)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.String r4 = com.mob.commons.C2312k.m2575a(r6)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.Object r3 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r3, r4, r6)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.String r4 = com.mob.commons.C2312k.m2575a(r7)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.Object[] r6 = new java.lang.Object[r10]     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            r7.<init>()     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            r11 = 154(0x9a, float:2.16E-43)
            java.lang.String r11 = com.mob.commons.C2312k.m2575a(r11)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            r7.append(r11)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            r7.append(r1)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            r7.append(r15)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.String r15 = r7.toString()     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            r6[r8] = r15     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.Object r15 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r3, r4, r6)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.String r1 = com.mob.commons.C2312k.m2575a(r9)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.Object[] r3 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.Object r15 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r15, r1, r3)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.io.InputStream r15 = (java.io.InputStream) r15     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            r1.<init>(r15, r0)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x00f1, all -> 0x00ee }
            java.lang.String r15 = r4.readLine()     // Catch:{ Throwable -> 0x00f6 }
            goto L_0x00bd
        L_0x00bb:
            r15 = r4
            r4 = r5
        L_0x00bd:
            boolean r0 = android.text.TextUtils.isEmpty(r15)     // Catch:{ Throwable -> 0x00f6 }
            if (r0 != 0) goto L_0x00ea
        L_0x00c3:
            java.lang.String r0 = "-"
            boolean r0 = r15.startsWith(r0)     // Catch:{ Throwable -> 0x00f6 }
            if (r0 != 0) goto L_0x00d3
            java.lang.String r0 = "d"
            boolean r0 = r15.startsWith(r0)     // Catch:{ Throwable -> 0x00f6 }
            if (r0 == 0) goto L_0x00e4
        L_0x00d3:
            java.lang.Object[] r15 = r14.m2056c(r15)     // Catch:{ Throwable -> 0x00f6 }
            if (r15 == 0) goto L_0x00e4
            r0 = r15[r8]     // Catch:{ Throwable -> 0x00f6 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x00f6 }
            r15 = r15[r10]     // Catch:{ Throwable -> 0x00f6 }
            java.lang.Long r15 = (java.lang.Long) r15     // Catch:{ Throwable -> 0x00f6 }
            r2.add(r15)     // Catch:{ Throwable -> 0x00f6 }
        L_0x00e4:
            java.lang.String r15 = r4.readLine()     // Catch:{ Throwable -> 0x00f6 }
            if (r15 != 0) goto L_0x00c3
        L_0x00ea:
            r4.close()     // Catch:{ Throwable -> 0x0101 }
            goto L_0x0101
        L_0x00ee:
            r15 = move-exception
            r4 = r5
            goto L_0x0102
        L_0x00f1:
            r15 = move-exception
            r4 = r5
            goto L_0x00f7
        L_0x00f4:
            r15 = move-exception
            goto L_0x0102
        L_0x00f6:
            r15 = move-exception
        L_0x00f7:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x00f4 }
            r0.mo29769d(r15)     // Catch:{ all -> 0x00f4 }
            if (r4 == 0) goto L_0x0101
            goto L_0x00ea
        L_0x0101:
            return r2
        L_0x0102:
            if (r4 == 0) goto L_0x0107
            r4.close()     // Catch:{ Throwable -> 0x0107 }
        L_0x0107:
            goto L_0x0109
        L_0x0108:
            throw r15
        L_0x0109:
            goto L_0x0108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2235i.m2055b(java.lang.String):java.util.List");
    }

    /* renamed from: a */
    private void m2050a(long j) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.dupdcd")));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "DUClt", th.getMessage());
        }
    }

    /* renamed from: i */
    private long m2058i() {
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.dupdcd");
        if (!dataCacheFile.exists()) {
            return 0;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(dataCacheFile));
            long readLong = dataInputStream.readLong();
            dataInputStream.close();
            return readLong;
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "DUClt", th.getMessage());
            return 0;
        }
    }

    /* renamed from: a */
    private void m2051a(HashMap<String, Long> hashMap) {
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.dudcd");
        try {
            byte[] a = m2054a(DeviceHelper.getInstance(MobSDK.getContext()).getModel(), hashMap);
            FileChannel channel = new FileOutputStream(dataCacheFile).getChannel();
            channel.write(ByteBuffer.wrap(a));
            channel.force(true);
            channel.close();
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "DUClt", th.getMessage());
        }
    }

    /* renamed from: a */
    private byte[] m2054a(String str, HashMap<String, Long> hashMap) {
        String fromHashMap = new Hashon().fromHashMap(hashMap);
        try {
            return Data.AES128Encode(str, fromHashMap);
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "DUClt", th.getMessage());
            return fromHashMap.getBytes();
        }
    }

    /* renamed from: j */
    private HashMap<String, Long> m2059j() {
        File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), "comm/dbs/.dudcd");
        if (dataCacheFile.exists()) {
            try {
                FileChannel channel = new FileInputStream(dataCacheFile).getChannel();
                ByteBuffer allocate = ByteBuffer.allocate((int) channel.size());
                while (channel.read(allocate) > 0) {
                }
                return m2049a(DeviceHelper.getInstance(MobSDK.getContext()).getModel(), allocate.array());
            } catch (Throwable th) {
                MobLog.getInstance().mo29770d(th, "[%s] %s", "DUClt", th.getMessage());
            }
        }
        return new HashMap<>();
    }

    /* renamed from: a */
    private HashMap<String, Long> m2049a(String str, byte[] bArr) {
        try {
            return new Hashon().fromJson(Data.AES128Decode(str, bArr));
        } catch (Throwable th) {
            MobLog.getInstance().mo29770d(th, "[%s] %s", "DUClt", th.getMessage());
            return new HashMap<>();
        }
    }

    /* renamed from: a */
    private boolean m2053a(File file) {
        if (file != null && file.isDirectory()) {
            PackageInfo packageInfo = null;
            try {
                if (this.f1967a == null) {
                    this.f1967a = MobSDK.getContext().getPackageManager();
                }
                packageInfo = this.f1967a.getPackageInfo(file.getName(), 0);
            } catch (Throwable th) {
                NLog instance = MobLog.getInstance();
                instance.mo29768d("[%s] %s", "DUClt", "Name not found: " + th.getMessage());
            }
            if (packageInfo == null || m2052a(packageInfo)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m2052a(PackageInfo packageInfo) {
        boolean z = (packageInfo.applicationInfo.flags & 1) == 1;
        boolean z2 = (packageInfo.applicationInfo.flags & 128) == 1;
        if (z || z2) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private long m2047a(String str, String str2) {
        try {
            return new SimpleDateFormat(str).parse(str2).getTime();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return 0;
        }
    }

    /* renamed from: c */
    private Object[] m2056c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Matcher matcher = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}").matcher(str);
            if (!matcher.find()) {
                return null;
            }
            Long valueOf = Long.valueOf(m2047a("yyyy-MM-dd HH:mm", matcher.group(0)));
            String substring = str.substring(matcher.end() + 1);
            if (valueOf.longValue() > System.currentTimeMillis()) {
                return null;
            }
            return new Object[]{substring, valueOf};
        } catch (Throwable th) {
            NLog instance = MobLog.getInstance();
            instance.mo29768d("Simple err: " + th.getMessage(), new Object[0]);
            return null;
        }
    }
}
