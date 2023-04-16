package com.mob.guard;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.mob.MobSDK;
import com.mob.commons.GuardMsg;
import com.mob.commons.MOBGUARD;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.List;

/* renamed from: com.mob.guard.c */
public class C2332c extends Thread {

    /* renamed from: a */
    public static boolean f2198a = false;

    /* renamed from: b */
    public static volatile boolean f2199b = false;

    /* renamed from: e */
    private static int f2200e;

    /* renamed from: c */
    private File f2201c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public Context f2202d;

    /* renamed from: f */
    private String f2203f;

    /* renamed from: g */
    private int f2204g = 1;

    /* renamed from: h */
    private String f2205h;

    /* renamed from: i */
    private int f2206i = 0;

    public C2332c() {
        try {
            this.f2202d = MobSDK.getContext();
            NLog b = C2335e.m2675b();
            b.mo29768d("liveService onStartCommand:" + MobSDK.getContext().getPackageName(), new Object[0]);
        } catch (Throwable unused) {
        }
    }

    public void run() {
        try {
            MobSDK.init(MobSDK.getContext());
            C2335e.m2675b().mo29768d("[Guard]{GuardTask}I'm pulling up..................", new Object[0]);
            String a = C2341i.m2692a();
            GuardMsg guardMsg = new GuardMsg();
            if (TextUtils.isEmpty(a)) {
                this.f2205h = DeviceAuthorizer.authorize(new MOBGUARD());
                guardMsg.setId("g_" + this.f2205h);
                C2341i.m2694a("g_" + this.f2205h);
                guardMsg.setVersion(0);
                guardMsg.setTimestamp(System.currentTimeMillis());
                guardMsg.setHostPkgName(MobSDK.getContext().getPackageName());
                NLog b = C2335e.m2675b();
                b.mo29768d("[Guard]mobguard is first automatically generated data:" + new Hashon().fromObject(guardMsg), new Object[0]);
                C2341i.m2700c(guardMsg.toJson());
            } else {
                guardMsg.toObj(a, guardMsg);
                NLog b2 = C2335e.m2675b();
                b2.mo29768d("[Guard]mobguard last automatically generated data:" + a, new Object[0]);
            }
            m2664a(guardMsg);
        } catch (Throwable th) {
            C2335e.m2675b().mo29769d(th);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        r14.f2206i = 2;
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:?, code lost:
        r14.f2206i = 2;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:111:0x0325 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:99:0x02cd */
    /* JADX WARNING: Removed duplicated region for block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d A[Catch:{ Throwable -> 0x0376, Throwable -> 0x03b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x010b A[Catch:{ Throwable -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01c5 A[Catch:{ Throwable -> 0x0207 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0215 A[Catch:{ Throwable -> 0x0376, Throwable -> 0x03b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0226 A[Catch:{ Throwable -> 0x0376, Throwable -> 0x03b3 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2664a(com.mob.commons.GuardMsg r15) {
        /*
            r14 = this;
            java.lang.String r0 = "pkgname"
            java.lang.String r1 = "com.mob.intent.MOB_GUARD_SERVICE"
            android.content.Context r2 = r14.f2202d     // Catch:{ Throwable -> 0x03b3 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Throwable -> 0x03b3 }
            android.content.Context r3 = r14.f2202d     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ Throwable -> 0x03b3 }
            r4 = 128(0x80, float:1.794E-43)
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r4)     // Catch:{ Throwable -> 0x03b3 }
            android.content.pm.ApplicationInfo r2 = r2.applicationInfo     // Catch:{ Throwable -> 0x03b3 }
            android.os.Bundle r2 = r2.metaData     // Catch:{ Throwable -> 0x03b3 }
            r3 = 0
            if (r2 == 0) goto L_0x0031
            boolean r4 = r2.isEmpty()     // Catch:{ Throwable -> 0x03b3 }
            if (r4 != 0) goto L_0x0031
            java.lang.String r4 = "disable_mob_a_guard"
            java.lang.Object r2 = r2.get(r4)     // Catch:{ Throwable -> 0x03b3 }
            if (r2 != 0) goto L_0x002c
            goto L_0x0031
        L_0x002c:
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x03b3 }
            goto L_0x0032
        L_0x0031:
            r2 = r3
        L_0x0032:
            java.lang.String r4 = "true"
            boolean r2 = r4.equals(r2)     // Catch:{ Throwable -> 0x03b3 }
            if (r2 == 0) goto L_0x003b
            return
        L_0x003b:
            android.content.Intent r2 = new android.content.Intent     // Catch:{ Throwable -> 0x03b3 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x03b3 }
            android.content.Context r4 = r14.f2202d     // Catch:{ Throwable -> 0x03b3 }
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ Throwable -> 0x03b3 }
            r5 = 0
            java.util.List r2 = r4.queryIntentServices(r2, r5)     // Catch:{ Throwable -> 0x03b3 }
            if (r2 == 0) goto L_0x03bb
            com.mob.tools.log.NLog r4 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x03b3 }
            r6.<init>()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r7 = "liveService--list:"
            r6.append(r7)     // Catch:{ Throwable -> 0x03b3 }
            r6.append(r2)     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.Object[] r7 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x03b3 }
            r4.mo29768d(r6, r7)     // Catch:{ Throwable -> 0x03b3 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Throwable -> 0x03b3 }
            r4.<init>()     // Catch:{ Throwable -> 0x03b3 }
            android.content.Context r6 = r14.f2202d     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ Throwable -> 0x03b3 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Throwable -> 0x03b3 }
        L_0x0076:
            boolean r7 = r2.hasNext()     // Catch:{ Throwable -> 0x03b3 }
            if (r7 == 0) goto L_0x00a6
            java.lang.Object r7 = r2.next()     // Catch:{ Throwable -> 0x03b3 }
            android.content.pm.ResolveInfo r7 = (android.content.pm.ResolveInfo) r7     // Catch:{ Throwable -> 0x03b3 }
            android.content.pm.ServiceInfo r8 = r7.serviceInfo     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r8 = r8.packageName     // Catch:{ Throwable -> 0x03b3 }
            android.content.pm.ServiceInfo r9 = r7.serviceInfo     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r9 = r9.name     // Catch:{ Throwable -> 0x03b3 }
            android.content.pm.ServiceInfo r7 = r7.serviceInfo     // Catch:{ Throwable -> 0x03b3 }
            boolean r7 = r7.exported     // Catch:{ Throwable -> 0x03b3 }
            if (r7 == 0) goto L_0x0076
            java.lang.Class<com.mob.guard.MobGuardUpService> r7 = com.mob.guard.MobGuardUpService.class
            java.lang.String r7 = r7.getName()     // Catch:{ Throwable -> 0x03b3 }
            boolean r7 = r7.equals(r9)     // Catch:{ Throwable -> 0x03b3 }
            if (r7 == 0) goto L_0x0076
            boolean r7 = r6.equals(r8)     // Catch:{ Throwable -> 0x03b3 }
            if (r7 != 0) goto L_0x0076
            r4.add(r8)     // Catch:{ Throwable -> 0x03b3 }
            goto L_0x0076
        L_0x00a6:
            int r2 = r4.size()     // Catch:{ Throwable -> 0x03b3 }
            if (r2 != 0) goto L_0x00b8
            com.mob.tools.log.NLog r15 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r0 = "liveService--pkgList size 0"
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x03b3 }
            r15.mo29768d(r0, r1)     // Catch:{ Throwable -> 0x03b3 }
            return
        L_0x00b8:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r5)     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r6 = "[Guard]{GuardTask}syncIdState is,"
            r7 = 1
            if (r15 == 0) goto L_0x00d6
            java.lang.String r8 = r15.getId()     // Catch:{ Throwable -> 0x0207 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Throwable -> 0x0207 }
            if (r8 != 0) goto L_0x00d6
            java.lang.String r8 = r15.getId()     // Catch:{ Throwable -> 0x0207 }
            java.lang.Object r4 = com.mob.guard.C2334d.m2669a((java.util.List<java.lang.String>) r4, (java.lang.String) r8)     // Catch:{ Throwable -> 0x0207 }
            java.util.HashMap r4 = (java.util.HashMap) r4     // Catch:{ Throwable -> 0x0207 }
            goto L_0x00de
        L_0x00d6:
            java.lang.String r8 = r14.f2205h     // Catch:{ Throwable -> 0x0207 }
            java.lang.Object r4 = com.mob.guard.C2334d.m2669a((java.util.List<java.lang.String>) r4, (java.lang.String) r8)     // Catch:{ Throwable -> 0x0207 }
            java.util.HashMap r4 = (java.util.HashMap) r4     // Catch:{ Throwable -> 0x0207 }
        L_0x00de:
            r3 = r4
            com.mob.tools.log.NLog r4 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x0207 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0207 }
            r8.<init>()     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r9 = "[Guard]listMap is :"
            r8.append(r9)     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r9 = r3.toString()     // Catch:{ Throwable -> 0x0207 }
            r8.append(r9)     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x0207 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0207 }
            r4.mo29768d(r8, r9)     // Catch:{ Throwable -> 0x0207 }
            java.util.Set r4 = r3.entrySet()     // Catch:{ Throwable -> 0x0207 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Throwable -> 0x0207 }
        L_0x0105:
            boolean r8 = r4.hasNext()     // Catch:{ Throwable -> 0x0207 }
            if (r8 == 0) goto L_0x01bb
            java.lang.Object r8 = r4.next()     // Catch:{ Throwable -> 0x0207 }
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8     // Catch:{ Throwable -> 0x0207 }
            java.lang.Object r9 = r8.getKey()     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r10 = "workId"
            boolean r9 = r9.equals(r10)     // Catch:{ Throwable -> 0x0207 }
            if (r9 == 0) goto L_0x0125
            java.lang.Object r9 = r8.getValue()     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Throwable -> 0x0207 }
            r14.f2203f = r9     // Catch:{ Throwable -> 0x0207 }
        L_0x0125:
            java.lang.Object r9 = r8.getKey()     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r10 = "deviceState"
            boolean r9 = r9.equals(r10)     // Catch:{ Throwable -> 0x0207 }
            if (r9 == 0) goto L_0x0159
            java.lang.Object r9 = r8.getValue()     // Catch:{ Throwable -> 0x0207 }
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ Throwable -> 0x0207 }
            int r9 = r9.intValue()     // Catch:{ Throwable -> 0x0207 }
            r14.f2204g = r9     // Catch:{ Throwable -> 0x0207 }
            com.mob.tools.log.NLog r9 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x0207 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0207 }
            r10.<init>()     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r11 = "[Guard]{GuardTask}deviceState is,"
            r10.append(r11)     // Catch:{ Throwable -> 0x0207 }
            int r11 = r14.f2204g     // Catch:{ Throwable -> 0x0207 }
            r10.append(r11)     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r10 = r10.toString()     // Catch:{ Throwable -> 0x0207 }
            java.lang.Object[] r11 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0207 }
            r9.mo29768d(r10, r11)     // Catch:{ Throwable -> 0x0207 }
        L_0x0159:
            java.lang.Object r9 = r8.getKey()     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r10 = "syncIdState"
            boolean r9 = r9.equals(r10)     // Catch:{ Throwable -> 0x0207 }
            if (r9 == 0) goto L_0x0196
            java.lang.Object r9 = r8.getValue()     // Catch:{ Throwable -> 0x0207 }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ Throwable -> 0x0207 }
            boolean r9 = r9.booleanValue()     // Catch:{ Throwable -> 0x0207 }
            f2198a = r9     // Catch:{ Throwable -> 0x0207 }
            com.mob.tools.log.NLog r9 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x0207 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0207 }
            r10.<init>()     // Catch:{ Throwable -> 0x0207 }
            r10.append(r6)     // Catch:{ Throwable -> 0x0207 }
            boolean r11 = f2198a     // Catch:{ Throwable -> 0x0207 }
            r10.append(r11)     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r10 = r10.toString()     // Catch:{ Throwable -> 0x0207 }
            java.lang.Object[] r11 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0207 }
            r9.mo29768d(r10, r11)     // Catch:{ Throwable -> 0x0207 }
            boolean r9 = f2198a     // Catch:{ Throwable -> 0x0207 }
            if (r9 == 0) goto L_0x0193
            com.mob.guard.C2341i.m2695a((boolean) r7)     // Catch:{ Throwable -> 0x0207 }
            goto L_0x0196
        L_0x0193:
            com.mob.guard.C2341i.m2695a((boolean) r5)     // Catch:{ Throwable -> 0x0207 }
        L_0x0196:
            java.lang.Object r9 = r8.getKey()     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r10 = "pollPackState"
            boolean r9 = r9.equals(r10)     // Catch:{ Throwable -> 0x0207 }
            if (r9 == 0) goto L_0x0105
            java.lang.Object r8 = r8.getValue()     // Catch:{ Throwable -> 0x0207 }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ Throwable -> 0x0207 }
            boolean r2 = r8.booleanValue()     // Catch:{ Throwable -> 0x01b8 }
            if (r2 == 0) goto L_0x01b2
            com.mob.guard.C2341i.m2698b((boolean) r7)     // Catch:{ Throwable -> 0x01b8 }
            goto L_0x01b5
        L_0x01b2:
            com.mob.guard.C2341i.m2698b((boolean) r5)     // Catch:{ Throwable -> 0x01b8 }
        L_0x01b5:
            r2 = r8
            goto L_0x0105
        L_0x01b8:
            r4 = move-exception
            r2 = r8
            goto L_0x0208
        L_0x01bb:
            f2199b = r7     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r4 = r14.f2203f     // Catch:{ Throwable -> 0x0207 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x0207 }
            if (r4 != 0) goto L_0x01ca
            java.lang.String r4 = r14.f2203f     // Catch:{ Throwable -> 0x0207 }
            com.mob.guard.C2341i.m2706f(r4)     // Catch:{ Throwable -> 0x0207 }
        L_0x01ca:
            com.mob.tools.log.NLog r4 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x0207 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0207 }
            r8.<init>()     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r9 = "[Guard]{GuardTask}deviceState ,mWorkId is ,"
            r8.append(r9)     // Catch:{ Throwable -> 0x0207 }
            int r9 = r14.f2204g     // Catch:{ Throwable -> 0x0207 }
            r8.append(r9)     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r9 = ", "
            r8.append(r9)     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r9 = r14.f2203f     // Catch:{ Throwable -> 0x0207 }
            r8.append(r9)     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x0207 }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0207 }
            r4.mo29768d(r8, r9)     // Catch:{ Throwable -> 0x0207 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0207 }
            r4.<init>()     // Catch:{ Throwable -> 0x0207 }
            int r8 = r14.f2204g     // Catch:{ Throwable -> 0x0207 }
            r4.append(r8)     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r8 = ""
            r4.append(r8)     // Catch:{ Throwable -> 0x0207 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0207 }
            com.mob.guard.C2341i.m2704e(r4)     // Catch:{ Throwable -> 0x0207 }
            goto L_0x020f
        L_0x0207:
            r4 = move-exception
        L_0x0208:
            com.mob.tools.log.NLog r8 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x03b3 }
            r8.mo29769d(r4)     // Catch:{ Throwable -> 0x03b3 }
        L_0x020f:
            boolean r4 = r14.m2666a()     // Catch:{ Throwable -> 0x03b3 }
            if (r4 == 0) goto L_0x0226
            java.io.File r15 = r14.f2201c     // Catch:{ Throwable -> 0x03b3 }
            r15.delete()     // Catch:{ Throwable -> 0x03b3 }
            com.mob.tools.log.NLog r15 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r0 = "[Guard]pulled for the first time........."
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x03b3 }
            r15.mo29768d(r0, r1)     // Catch:{ Throwable -> 0x03b3 }
            return
        L_0x0226:
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x03b3 }
            long r10 = com.mob.guard.C2328b.f2194b     // Catch:{ Throwable -> 0x03b3 }
            long r8 = r8 - r10
            r10 = 3000(0xbb8, double:1.482E-320)
            int r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r4 >= 0) goto L_0x024e
            long r10 = r10 - r8
            java.lang.Thread.sleep(r10)     // Catch:{ Throwable -> 0x03b3 }
            boolean r4 = r14.m2666a()     // Catch:{ Throwable -> 0x03b3 }
            if (r4 == 0) goto L_0x024e
            java.io.File r15 = r14.f2201c     // Catch:{ Throwable -> 0x03b3 }
            r15.delete()     // Catch:{ Throwable -> 0x03b3 }
            com.mob.tools.log.NLog r15 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r0 = "[Guard]pulled for the two time........."
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x03b3 }
            r15.mo29768d(r0, r1)     // Catch:{ Throwable -> 0x03b3 }
            return
        L_0x024e:
            boolean r2 = r2.booleanValue()     // Catch:{ Throwable -> 0x03b3 }
            if (r2 != 0) goto L_0x0255
            return
        L_0x0255:
            java.lang.String r2 = "guardList"
            java.lang.Object r2 = r3.get(r2)     // Catch:{ Throwable -> 0x03b3 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ Throwable -> 0x03b3 }
            com.mob.tools.log.NLog r3 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x03b3 }
            r4.<init>()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r8 = "[Guard]{GuardTask}The server releases the package that needs to be pulled up:"
            r4.append(r8)     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r8 = r2.toString()     // Catch:{ Throwable -> 0x03b3 }
            r4.append(r8)     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.Object[] r8 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x03b3 }
            r3.mo29768d(r4, r8)     // Catch:{ Throwable -> 0x03b3 }
            if (r2 == 0) goto L_0x03b2
            int r3 = r2.size()     // Catch:{ Throwable -> 0x03b3 }
            if (r3 != 0) goto L_0x0285
            goto L_0x03b2
        L_0x0285:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Throwable -> 0x03b3 }
            r3.<init>()     // Catch:{ Throwable -> 0x03b3 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Throwable -> 0x03b3 }
            r4.<init>()     // Catch:{ Throwable -> 0x03b3 }
            r8 = 0
        L_0x0290:
            int r9 = r2.size()     // Catch:{ Throwable -> 0x03b3 }
            if (r8 >= r9) goto L_0x0389
            java.lang.Object r9 = r2.get(r8)     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Throwable -> 0x03b3 }
            android.content.Context r10 = r14.f2202d     // Catch:{ Throwable -> 0x03b3 }
            boolean r10 = m2667a((android.content.Context) r10, (java.lang.String) r9)     // Catch:{ Throwable -> 0x03b3 }
            if (r10 != 0) goto L_0x0382
            android.content.Intent r10 = new android.content.Intent     // Catch:{ Throwable -> 0x03b3 }
            r10.<init>(r1)     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r11 = "SERVICE_REWORK"
            r10.putExtra(r11, r7)     // Catch:{ Throwable -> 0x03b3 }
            android.content.Context r11 = r14.f2202d     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r11 = r11.getPackageName()     // Catch:{ Throwable -> 0x03b3 }
            r10.putExtra(r0, r11)     // Catch:{ Throwable -> 0x03b3 }
            java.lang.Class<com.mob.guard.MobGuardUpService> r11 = com.mob.guard.MobGuardUpService.class
            java.lang.String r11 = r11.getName()     // Catch:{ Throwable -> 0x03b3 }
            r10.setClassName(r9, r11)     // Catch:{ Throwable -> 0x03b3 }
            r11 = 2
            android.content.Context r12 = r14.f2202d     // Catch:{ SecurityException -> 0x02cd }
            com.mob.guard.c$1 r13 = new com.mob.guard.c$1     // Catch:{ SecurityException -> 0x02cd }
            r13.<init>()     // Catch:{ SecurityException -> 0x02cd }
            boolean r12 = r12.bindService(r10, r13, r7)     // Catch:{ SecurityException -> 0x02cd }
            goto L_0x02d0
        L_0x02cd:
            r14.f2206i = r11     // Catch:{ Throwable -> 0x03b3 }
            r12 = 0
        L_0x02d0:
            if (r12 == 0) goto L_0x02d4
            r12 = 0
            goto L_0x02d5
        L_0x02d4:
            r12 = 1
        L_0x02d5:
            r14.f2206i = r12     // Catch:{ Throwable -> 0x03b3 }
            int r12 = r14.f2206i     // Catch:{ Throwable -> 0x03b3 }
            if (r12 != 0) goto L_0x02fb
            f2200e = r11     // Catch:{ Throwable -> 0x03b3 }
            com.mob.tools.log.NLog r10 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x03b3 }
            r11.<init>()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r12 = "[Guard]{GuardTask}up ac by bindservice,"
            r11.append(r12)     // Catch:{ Throwable -> 0x03b3 }
            r11.append(r9)     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.Object[] r12 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x03b3 }
            r10.mo29768d(r11, r12)     // Catch:{ Throwable -> 0x03b3 }
            r4.add(r9)     // Catch:{ Throwable -> 0x03b3 }
            goto L_0x0327
        L_0x02fb:
            android.content.Context r12 = r14.f2202d     // Catch:{ Throwable -> 0x0325 }
            android.content.ComponentName r10 = r12.startService(r10)     // Catch:{ Throwable -> 0x0325 }
            if (r10 == 0) goto L_0x0327
            r14.f2206i = r5     // Catch:{ Throwable -> 0x0325 }
            f2200e = r11     // Catch:{ Throwable -> 0x0325 }
            com.mob.tools.log.NLog r10 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x0325 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0325 }
            r12.<init>()     // Catch:{ Throwable -> 0x0325 }
            java.lang.String r13 = "[Guard]{GuardTask}up ac by startservice,"
            r12.append(r13)     // Catch:{ Throwable -> 0x0325 }
            r12.append(r9)     // Catch:{ Throwable -> 0x0325 }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x0325 }
            java.lang.Object[] r13 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0325 }
            r10.mo29768d(r12, r13)     // Catch:{ Throwable -> 0x0325 }
            r4.add(r9)     // Catch:{ Throwable -> 0x0325 }
            goto L_0x0327
        L_0x0325:
            r14.f2206i = r11     // Catch:{ Throwable -> 0x03b3 }
        L_0x0327:
            int r10 = r14.f2206i     // Catch:{ Throwable -> 0x03b3 }
            if (r10 == 0) goto L_0x0385
            android.content.ComponentName r10 = new android.content.ComponentName     // Catch:{ Throwable -> 0x0376 }
            java.lang.Class<com.mob.MobTranUpActivity> r11 = com.mob.MobTranUpActivity.class
            java.lang.String r11 = r11.getName()     // Catch:{ Throwable -> 0x0376 }
            r10.<init>(r9, r11)     // Catch:{ Throwable -> 0x0376 }
            android.content.Intent r11 = new android.content.Intent     // Catch:{ Throwable -> 0x0376 }
            r11.<init>()     // Catch:{ Throwable -> 0x0376 }
            r12 = 1350565888(0x50800000, float:1.71798692E10)
            r11.addFlags(r12)     // Catch:{ Throwable -> 0x0376 }
            r11.setComponent(r10)     // Catch:{ Throwable -> 0x0376 }
            android.content.Context r10 = r14.f2202d     // Catch:{ Throwable -> 0x0376 }
            java.lang.String r10 = r10.getPackageName()     // Catch:{ Throwable -> 0x0376 }
            r11.putExtra(r0, r10)     // Catch:{ Throwable -> 0x0376 }
            com.mob.tools.log.NLog r10 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x0376 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0376 }
            r12.<init>()     // Catch:{ Throwable -> 0x0376 }
            java.lang.String r13 = "[Guard]{GuardTask}up ac by startActivity,"
            r12.append(r13)     // Catch:{ Throwable -> 0x0376 }
            r12.append(r9)     // Catch:{ Throwable -> 0x0376 }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x0376 }
            java.lang.Object[] r13 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0376 }
            r10.mo29768d(r12, r13)     // Catch:{ Throwable -> 0x0376 }
            android.content.Context r10 = r14.f2202d     // Catch:{ Throwable -> 0x0376 }
            r10.startActivity(r11)     // Catch:{ Throwable -> 0x0376 }
            f2200e = r7     // Catch:{ Throwable -> 0x0376 }
            r4.add(r9)     // Catch:{ Throwable -> 0x0376 }
            r10 = 300(0x12c, double:1.48E-321)
            java.lang.Thread.sleep(r10)     // Catch:{ Throwable -> 0x0376 }
            goto L_0x0385
        L_0x0376:
            r10 = move-exception
            com.mob.tools.log.NLog r11 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x03b3 }
            r11.mo29769d(r10)     // Catch:{ Throwable -> 0x03b3 }
            r3.add(r9)     // Catch:{ Throwable -> 0x03b3 }
            goto L_0x0385
        L_0x0382:
            r4.add(r9)     // Catch:{ Throwable -> 0x03b3 }
        L_0x0385:
            int r8 = r8 + 1
            goto L_0x0290
        L_0x0389:
            boolean r0 = f2198a     // Catch:{ Throwable -> 0x03b3 }
            if (r0 == 0) goto L_0x0390
            r14.m2665a((java.util.List<java.lang.String>) r2, (com.mob.commons.GuardMsg) r15)     // Catch:{ Throwable -> 0x03b3 }
        L_0x0390:
            com.mob.tools.log.NLog r15 = com.mob.guard.C2335e.m2675b()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x03b3 }
            r0.<init>()     // Catch:{ Throwable -> 0x03b3 }
            r0.append(r6)     // Catch:{ Throwable -> 0x03b3 }
            boolean r1 = f2198a     // Catch:{ Throwable -> 0x03b3 }
            r0.append(r1)     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x03b3 }
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x03b3 }
            r15.mo29768d(r0, r1)     // Catch:{ Throwable -> 0x03b3 }
            int r15 = f2200e     // Catch:{ Throwable -> 0x03b3 }
            java.lang.String r0 = r14.f2203f     // Catch:{ Throwable -> 0x03b3 }
            com.mob.guard.C2334d.m2670a(r4, r3, r15, r0)     // Catch:{ Throwable -> 0x03b3 }
            goto L_0x03bb
        L_0x03b2:
            return
        L_0x03b3:
            r15 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.guard.C2335e.m2675b()
            r0.mo29769d(r15)
        L_0x03bb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.guard.C2332c.m2664a(com.mob.commons.GuardMsg):void");
    }

    /* renamed from: a */
    private boolean m2666a() {
        this.f2201c = ResHelper.getDataCacheFile(this.f2202d, ".mmgd");
        File file = this.f2201c;
        if (file == null || !file.exists()) {
            return false;
        }
        C2335e.m2675b().mo29768d("[Guard] I am the one being pulled............", new Object[0]);
        return true;
    }

    /* renamed from: a */
    private static boolean m2667a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            boolean z = (packageInfo.applicationInfo.flags & 1) == 0 && (packageInfo.applicationInfo.flags & 128) == 0;
            boolean z2 = (packageInfo.applicationInfo.flags & 2097152) == 0;
            if (!z || !z2) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            C2335e.m2675b().mo29772e(e);
            return false;
        }
    }

    /* renamed from: a */
    private void m2665a(List<String> list, GuardMsg guardMsg) {
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                if (guardMsg != null) {
                    C2335e.m2675b().mo29768d("[Guard]{GuardTask}start to broadcast all app.........................", new Object[0]);
                    for (int i = 0; i < list.size(); i++) {
                        if (!list.get(i).equals(MobSDK.getContext().getPackageName())) {
                            ComponentName componentName = new ComponentName(list.get(i), MobGuardCommonIdBCReceiver.class.getName());
                            guardMsg.setHostPkgName(MobSDK.getContext().getPackageName());
                            guardMsg.setTimestamp(System.currentTimeMillis());
                            guardMsg.setSynchronousPublish(false);
                            Intent intent = new Intent("com.mlive.id");
                            intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, guardMsg.toJson());
                            intent.setComponent(componentName);
                            this.f2202d.sendBroadcast(intent);
                        }
                    }
                }
            } catch (Throwable th) {
                C2335e.m2675b().mo29772e(th);
            }
        }
    }
}
