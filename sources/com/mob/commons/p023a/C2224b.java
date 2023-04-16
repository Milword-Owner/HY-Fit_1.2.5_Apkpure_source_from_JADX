package com.mob.commons.p023a;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Message;
import com.mob.commons.C2262b;
import com.mob.commons.C2300e;
import java.io.File;

/* renamed from: com.mob.commons.a.b */
/* compiled from: ArtClt */
public class C2224b extends C2226d {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return C2300e.m2466a("comm/locks/.artc_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return C2262b.m2234A() > 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        mo28999b(1);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        if (message.what == 1) {
            long A = C2262b.m2234A();
            if (A <= 0 || !m2003a(A)) {
                mo29001e();
            } else {
                mo28997a(1, A);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m2003a(long r20) {
        /*
            r19 = this;
            r1 = 0
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0205 }
            com.mob.tools.utils.DeviceHelper r0 = com.mob.tools.utils.DeviceHelper.getInstance(r0)     // Catch:{ Throwable -> 0x0205 }
            java.lang.String r2 = "usagestats"
            java.lang.Object r0 = r0.getSystemServiceSafe(r2)     // Catch:{ Throwable -> 0x0205 }
            if (r0 != 0) goto L_0x0012
            return r1
        L_0x0012:
            java.lang.String r2 = "android.app.usage.UsageStatsManager"
            com.mob.tools.utils.ReflectHelper.importClass(r2)     // Catch:{ Throwable -> 0x0205 }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0205 }
            java.lang.String r3 = "queryUsageStats"
            r4 = 21
            r5 = 2
            r6 = 3
            r8 = 1
            if (r2 < r4) goto L_0x004f
            java.lang.Object[] r2 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x0205 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0205 }
            r2[r1] = r9     // Catch:{ Throwable -> 0x0205 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0205 }
            r2[r8] = r9     // Catch:{ Throwable -> 0x0205 }
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0205 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ Throwable -> 0x0205 }
            r2[r5] = r9     // Catch:{ Throwable -> 0x0205 }
            java.lang.Class[] r9 = new java.lang.Class[r6]     // Catch:{ Throwable -> 0x0205 }
            java.lang.Class r10 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x0205 }
            r9[r1] = r10     // Catch:{ Throwable -> 0x0205 }
            java.lang.Class r10 = java.lang.Long.TYPE     // Catch:{ Throwable -> 0x0205 }
            r9[r8] = r10     // Catch:{ Throwable -> 0x0205 }
            java.lang.Class r10 = java.lang.Long.TYPE     // Catch:{ Throwable -> 0x0205 }
            r9[r5] = r10     // Catch:{ Throwable -> 0x0205 }
            java.lang.Object r2 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r3, r2, r9)     // Catch:{ Throwable -> 0x0205 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ Throwable -> 0x0205 }
            goto L_0x0050
        L_0x004f:
            r2 = 0
        L_0x0050:
            int r9 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0205 }
            r10 = 28
            if (r9 >= r10) goto L_0x00a4
            if (r2 == 0) goto L_0x005e
            boolean r9 = r2.isEmpty()     // Catch:{ Throwable -> 0x0205 }
            if (r9 == 0) goto L_0x00a4
        L_0x005e:
            java.lang.String r9 = "mService"
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.getInstanceField(r0, r9)     // Catch:{ Throwable -> 0x0205 }
            r9 = 4
            java.lang.Object[] r11 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0205 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0205 }
            r11[r1] = r12     // Catch:{ Throwable -> 0x0205 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0205 }
            r11[r8] = r12     // Catch:{ Throwable -> 0x0205 }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0205 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ Throwable -> 0x0205 }
            r11[r5] = r12     // Catch:{ Throwable -> 0x0205 }
            java.lang.String r12 = "com.android.settings"
            r11[r6] = r12     // Catch:{ Throwable -> 0x0205 }
            java.lang.Class[] r9 = new java.lang.Class[r9]     // Catch:{ Throwable -> 0x0205 }
            java.lang.Class r12 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x0205 }
            r9[r1] = r12     // Catch:{ Throwable -> 0x0205 }
            java.lang.Class r12 = java.lang.Long.TYPE     // Catch:{ Throwable -> 0x0205 }
            r9[r8] = r12     // Catch:{ Throwable -> 0x0205 }
            java.lang.Class r12 = java.lang.Long.TYPE     // Catch:{ Throwable -> 0x0205 }
            r9[r5] = r12     // Catch:{ Throwable -> 0x0205 }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r9[r6] = r5     // Catch:{ Throwable -> 0x0205 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r3, r11, r9)     // Catch:{ Throwable -> 0x0205 }
            if (r0 == 0) goto L_0x00a4
            java.lang.String r2 = "getList"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0205 }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r2, r3)     // Catch:{ Throwable -> 0x0205 }
            r2 = r0
            java.util.List r2 = (java.util.List) r2     // Catch:{ Throwable -> 0x0205 }
        L_0x00a4:
            if (r2 == 0) goto L_0x0202
            boolean r0 = r2.isEmpty()     // Catch:{ Throwable -> 0x0205 }
            if (r0 == 0) goto L_0x00ae
            goto L_0x0202
        L_0x00ae:
            long r5 = com.mob.commons.C2308i.m2529e()     // Catch:{ Throwable -> 0x0205 }
            long r11 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x0205 }
            r13 = 0
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 <= 0) goto L_0x00c1
            int r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x00c1
            return r8
        L_0x00c1:
            int r0 = r2.size()     // Catch:{ Throwable -> 0x0205 }
            java.lang.String r3 = "android.app.usage.UsageStats"
            com.mob.tools.utils.ReflectHelper.importClass(r3)     // Catch:{ Throwable -> 0x0205 }
            android.content.Context r3 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0205 }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ Throwable -> 0x0205 }
            int r0 = r0 - r8
            r5 = 0
            r6 = 0
        L_0x00d5:
            if (r0 < 0) goto L_0x0198
            java.lang.Object r9 = r2.get(r0)     // Catch:{ Throwable -> 0x0205 }
            int r11 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0205 }
            if (r11 < r4) goto L_0x018a
            java.lang.String r11 = "getLastTimeUsed"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0205 }
            java.lang.Object r11 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r9, r11, r12)     // Catch:{ Throwable -> 0x0205 }
            java.lang.Long r11 = (java.lang.Long) r11     // Catch:{ Throwable -> 0x0205 }
            long r11 = r11.longValue()     // Catch:{ Throwable -> 0x0205 }
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 <= 0) goto L_0x018a
            java.lang.String r15 = "getPackageName"
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0205 }
            java.lang.Object r4 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r9, r15, r4)     // Catch:{ Throwable -> 0x0205 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Throwable -> 0x0205 }
            r15 = r19
            boolean r16 = r15.m2004a(r3, r4)     // Catch:{ Throwable -> 0x01fe }
            if (r16 == 0) goto L_0x0105
            goto L_0x018c
        L_0x0105:
            if (r6 != 0) goto L_0x010c
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ Throwable -> 0x01fe }
            r6.<init>()     // Catch:{ Throwable -> 0x01fe }
        L_0x010c:
            java.lang.Object r16 = r6.get(r4)     // Catch:{ Throwable -> 0x01fe }
            java.lang.Long r16 = (java.lang.Long) r16     // Catch:{ Throwable -> 0x01fe }
            if (r16 == 0) goto L_0x011d
            long r16 = r16.longValue()     // Catch:{ Throwable -> 0x01fe }
            int r18 = (r16 > r11 ? 1 : (r16 == r11 ? 0 : -1))
            if (r18 <= 0) goto L_0x011d
            goto L_0x018c
        L_0x011d:
            java.lang.Long r7 = java.lang.Long.valueOf(r11)     // Catch:{ Throwable -> 0x01fe }
            r6.put(r4, r7)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r7 = "getFirstTimeStamp"
            java.lang.Object[] r13 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01fe }
            java.lang.Object r7 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r9, r7, r13)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r13 = "getLastTimeStamp"
            java.lang.Object[] r14 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01fe }
            java.lang.Object r13 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r9, r13, r14)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r14 = "getTotalTimeInForeground"
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01fe }
            java.lang.Object r8 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r9, r14, r8)     // Catch:{ Throwable -> 0x01fe }
            int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x01fe }
            if (r14 >= r10) goto L_0x014d
            java.lang.String r14 = "mLaunchCount"
            java.lang.Object r14 = com.mob.tools.utils.ReflectHelper.getInstanceField(r9, r14)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r10 = "mLastEvent"
            java.lang.Object r9 = com.mob.tools.utils.ReflectHelper.getInstanceField(r9, r10)     // Catch:{ Throwable -> 0x01fe }
            goto L_0x014f
        L_0x014d:
            r9 = 0
            r14 = 0
        L_0x014f:
            java.util.HashMap r10 = new java.util.HashMap     // Catch:{ Throwable -> 0x01fe }
            r10.<init>()     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r1 = "packageName"
            r10.put(r1, r4)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r1 = "firstTimeStamp"
            r10.put(r1, r7)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r1 = "lastTimeStamp"
            r10.put(r1, r13)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r1 = "lastTimeUsed"
            java.lang.Long r7 = java.lang.Long.valueOf(r11)     // Catch:{ Throwable -> 0x01fe }
            r10.put(r1, r7)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r1 = "totalTimeInForeground"
            r10.put(r1, r8)     // Catch:{ Throwable -> 0x01fe }
            if (r14 == 0) goto L_0x0178
            java.lang.String r1 = "launchCount"
            r10.put(r1, r14)     // Catch:{ Throwable -> 0x01fe }
        L_0x0178:
            if (r9 == 0) goto L_0x017f
            java.lang.String r1 = "lastEvent"
            r10.put(r1, r9)     // Catch:{ Throwable -> 0x01fe }
        L_0x017f:
            if (r5 != 0) goto L_0x0186
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ Throwable -> 0x01fe }
            r5.<init>()     // Catch:{ Throwable -> 0x01fe }
        L_0x0186:
            r5.put(r4, r10)     // Catch:{ Throwable -> 0x01fe }
            goto L_0x018c
        L_0x018a:
            r15 = r19
        L_0x018c:
            int r0 = r0 + -1
            r1 = 0
            r4 = 21
            r8 = 1
            r10 = 28
            r13 = 0
            goto L_0x00d5
        L_0x0198:
            r15 = r19
            if (r5 == 0) goto L_0x0200
            boolean r0 = r5.isEmpty()     // Catch:{ Throwable -> 0x01fe }
            if (r0 == 0) goto L_0x01a3
            goto L_0x0200
        L_0x01a3:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01fe }
            r0.<init>()     // Catch:{ Throwable -> 0x01fe }
            java.util.Set r1 = r5.entrySet()     // Catch:{ Throwable -> 0x01fe }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x01fe }
        L_0x01b0:
            boolean r2 = r1.hasNext()     // Catch:{ Throwable -> 0x01fe }
            if (r2 == 0) goto L_0x01c4
            java.lang.Object r2 = r1.next()     // Catch:{ Throwable -> 0x01fe }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ Throwable -> 0x01fe }
            java.lang.Object r2 = r2.getValue()     // Catch:{ Throwable -> 0x01fe }
            r0.add(r2)     // Catch:{ Throwable -> 0x01fe }
            goto L_0x01b0
        L_0x01c4:
            int r1 = r0.size()     // Catch:{ Throwable -> 0x01fe }
            if (r1 <= 0) goto L_0x020f
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Throwable -> 0x01fe }
            r1.<init>()     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r2 = "type"
            java.lang.String r3 = "XM_APP_RUNTIMES"
            r1.put(r2, r3)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r2 = "list"
            r1.put(r2, r0)     // Catch:{ Throwable -> 0x01fe }
            java.lang.String r0 = "datetime"
            long r2 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x01fe }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x01fe }
            r1.put(r0, r2)     // Catch:{ Throwable -> 0x01fe }
            com.mob.commons.c r0 = com.mob.commons.C2293c.m2435a()     // Catch:{ Throwable -> 0x01fe }
            long r2 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x01fe }
            r0.mo29068a((long) r2, (java.util.HashMap<java.lang.String, java.lang.Object>) r1)     // Catch:{ Throwable -> 0x01fe }
            long r0 = com.mob.commons.C2262b.m2260a()     // Catch:{ Throwable -> 0x01fe }
            long r0 = r0 + r20
            com.mob.commons.C2308i.m2522c((long) r0)     // Catch:{ Throwable -> 0x01fe }
            r0 = 1
            return r0
        L_0x01fe:
            r0 = move-exception
            goto L_0x0208
        L_0x0200:
            r1 = 0
            return r1
        L_0x0202:
            r15 = r19
            return r1
        L_0x0205:
            r0 = move-exception
            r15 = r19
        L_0x0208:
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.mo29769d(r0)
        L_0x020f:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2224b.m2003a(long):boolean");
    }

    /* renamed from: a */
    private boolean m2004a(PackageManager packageManager, String str) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            boolean z = (packageInfo.applicationInfo.flags & 1) == 1;
            boolean z2 = (packageInfo.applicationInfo.flags & 128) == 1;
            if (z || z2) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }
}
