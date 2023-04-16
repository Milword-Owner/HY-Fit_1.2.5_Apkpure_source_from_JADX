package com.mob.commons;

import android.os.Handler;
import android.os.Message;
import com.baidu.mobstat.Config;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import p015io.reactivex.annotations.SchedulerSupport;

/* renamed from: com.mob.commons.d */
/* compiled from: DyLogUploader */
public class C2296d implements Handler.Callback {

    /* renamed from: a */
    private static final String f2114a = C2310j.m2573b("dfe.mic.mob.com/drl");

    /* renamed from: b */
    private static C2296d f2115b;

    /* renamed from: c */
    private MobCommunicator f2116c;

    /* renamed from: d */
    private SimpleDateFormat f2117d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");

    /* renamed from: e */
    private HashMap<String, Object> f2118e = new HashMap<>();

    /* renamed from: f */
    private Handler f2119f;

    /* renamed from: g */
    private String f2120g = null;

    /* renamed from: h */
    private int f2121h = -1;

    /* renamed from: a */
    public static synchronized C2296d m2449a() {
        C2296d dVar;
        synchronized (C2296d.class) {
            if (f2115b == null) {
                f2115b = new C2296d();
            }
            dVar = f2115b;
        }
        return dVar;
    }

    private C2296d() {
        try {
            this.f2120g = UUID.randomUUID().toString();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        this.f2119f = MobHandlerThread.newHandler("d", (Handler.Callback) this);
    }

    /* renamed from: b */
    private synchronized int m2453b() {
        return this.f2121h;
    }

    /* renamed from: a */
    public synchronized void mo29071a(int i) {
        this.f2121h = i;
        if (!(i == 1 || i == 4 || i == 17 || i == 18 || i == 19)) {
            if (i != 20) {
                if (i == 10) {
                }
            }
        }
        m2451a(8, (Throwable) null, "ld vr " + i);
    }

    /* renamed from: a */
    public synchronized void mo29072a(int i, Throwable th) {
        m2451a(i, th, (String) null);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m2451a(int r7, java.lang.Throwable r8, java.lang.String r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = 0
            if (r8 != 0) goto L_0x000e
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0052 }
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ all -> 0x0052 }
            r1.mo29768d(r9, r2)     // Catch:{ all -> 0x0052 }
            goto L_0x0015
        L_0x000e:
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0052 }
            r1.mo29769d(r8)     // Catch:{ all -> 0x0052 }
        L_0x0015:
            boolean r1 = com.mob.commons.C2262b.m2276aa()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x001d
            monitor-exit(r6)
            return
        L_0x001d:
            android.os.Message r1 = new android.os.Message     // Catch:{ all -> 0x0052 }
            r1.<init>()     // Catch:{ all -> 0x0052 }
            r2 = 1
            r1.what = r2     // Catch:{ all -> 0x0052 }
            r3 = 4
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0052 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0052 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0052 }
            r3[r0] = r4     // Catch:{ all -> 0x0052 }
            if (r8 != 0) goto L_0x0035
            r8 = r9
        L_0x0035:
            r3[r2] = r8     // Catch:{ all -> 0x0052 }
            r8 = 2
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0052 }
            r3[r8] = r7     // Catch:{ all -> 0x0052 }
            r7 = 3
            int r8 = r6.m2453b()     // Catch:{ all -> 0x0052 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0052 }
            r3[r7] = r8     // Catch:{ all -> 0x0052 }
            r1.obj = r3     // Catch:{ all -> 0x0052 }
            android.os.Handler r7 = r6.f2119f     // Catch:{ all -> 0x0052 }
            r7.sendMessage(r1)     // Catch:{ all -> 0x0052 }
            monitor-exit(r6)
            return
        L_0x0052:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2296d.m2451a(int, java.lang.Throwable, java.lang.String):void");
    }

    public boolean handleMessage(Message message) {
        String str;
        try {
            int i = message.what;
            if (i == 1) {
                if (this.f2118e.size() > 10) {
                    m2456c(this.f2118e);
                    this.f2118e.clear();
                }
                Object[] objArr = (Object[]) message.obj;
                this.f2118e.put(Config.FEED_LIST_MAPPING, this.f2120g);
                ArrayList arrayList = (ArrayList) this.f2118e.get("list");
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                HashMap hashMap = new HashMap();
                hashMap.put("ct", objArr[0]);
                if (objArr[1] instanceof Throwable) {
                    str = m2450a((Throwable) objArr[1]);
                } else {
                    str = String.valueOf(objArr[1]);
                }
                hashMap.put("mg", "[" + this.f2117d.format(objArr[0]) + "][" + objArr[2] + "][" + objArr[3] + "] " + str);
                hashMap.put("et", objArr[2]);
                hashMap.put("po", objArr[3]);
                arrayList.add(hashMap);
                this.f2118e.put("list", arrayList);
                m2455c();
            } else if (i == 2) {
                this.f2119f.removeMessages(2);
                m2457d();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return false;
    }

    /* renamed from: c */
    private void m2455c() {
        if (!C2262b.m2276aa()) {
            long j = !m2460f() ? 120000 : 0;
            if (j > 0) {
                this.f2119f.sendEmptyMessageDelayed(2, j);
            } else {
                this.f2119f.sendEmptyMessageDelayed(2, 10000);
            }
        }
    }

    /* renamed from: d */
    private void m2457d() {
        boolean z;
        File[] listFiles;
        if (this.f2118e.size() > 0) {
            z = m2452a(this.f2118e);
            if (!z) {
                m2456c(this.f2118e);
            }
            this.f2118e.clear();
        } else {
            z = true;
        }
        if (z) {
            File g = m2461g();
            if (g.exists() && g.isDirectory() && (listFiles = g.listFiles()) != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (m2452a((HashMap<String, Object>) (HashMap) ResHelper.readObjectFromFile(file.getAbsolutePath())) && !file.delete()) {
                        file.delete();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m2452a(HashMap<String, Object> hashMap) {
        try {
            return m2454b(hashMap);
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
    }

    /* renamed from: b */
    private boolean m2454b(HashMap<String, Object> hashMap) throws Throwable {
        if (hashMap == null || hashMap.isEmpty()) {
            return true;
        }
        HashMap<String, Object> ao = C2262b.m2290ao();
        ao.put("errors", hashMap);
        m2459e();
        HashMap hashMap2 = (HashMap) this.f2116c.requestSynchronized(ao, f2114a, false);
        if (hashMap2 == null || hashMap2.isEmpty()) {
            return true;
        }
        return false;
    }

    /* renamed from: e */
    private void m2459e() {
        if (this.f2116c == null) {
            this.f2116c = new MobCommunicator(1024, "ab0a0a6473d1891d388773574764b239d4ad80cb2fd3a83d81d03901c1548c13fee7c9692c326e6682b239d4c5d0021d1b607642c47ec29f10b0602908c3e6c9", "23c3c8cb41c47dd288cc7f4c218fbc7c839a34e0a0d1b2130e87b7914936b120a2d6570ee7ac66282328d50f2acfd82f2259957c89baea32547758db05de9cd7c6822304c8e45742f24bbbe41c1e12f09e18c6fab4d078065f2e5aaed94c900c66e8bbf8a120eefa7bd1fb52114d529250084f5f6f369ed4ce9645978dd30c51");
        }
    }

    /* renamed from: f */
    private boolean m2460f() {
        String networkType = DeviceHelper.getInstance(MobSDK.getContext()).getNetworkType();
        return networkType != null && !SchedulerSupport.NONE.equals(networkType);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0038 A[Catch:{ all -> 0x0031 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0040 A[SYNTHETIC, Splitter:B:32:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x004c A[SYNTHETIC, Splitter:B:40:0x004c] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m2450a(java.lang.Throwable r5) {
        /*
            r4 = this;
            java.lang.String r0 = ""
            if (r5 != 0) goto L_0x0005
            return r0
        L_0x0005:
            r1 = 0
            r2 = r5
        L_0x0007:
            if (r2 == 0) goto L_0x0013
            boolean r3 = r2 instanceof java.net.UnknownHostException     // Catch:{ Throwable -> 0x0033 }
            if (r3 == 0) goto L_0x000e
            return r0
        L_0x000e:
            java.lang.Throwable r2 = r2.getCause()     // Catch:{ Throwable -> 0x0033 }
            goto L_0x0007
        L_0x0013:
            java.io.StringWriter r0 = new java.io.StringWriter     // Catch:{ Throwable -> 0x0033 }
            r0.<init>()     // Catch:{ Throwable -> 0x0033 }
            java.io.PrintWriter r1 = new java.io.PrintWriter     // Catch:{ Throwable -> 0x002e, all -> 0x002b }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x002e, all -> 0x002b }
            r5.printStackTrace(r1)     // Catch:{ Throwable -> 0x002e, all -> 0x002b }
            r1.flush()     // Catch:{ Throwable -> 0x002e, all -> 0x002b }
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x002e, all -> 0x002b }
            r0.close()     // Catch:{ Throwable -> 0x002a }
        L_0x002a:
            return r5
        L_0x002b:
            r5 = move-exception
            r1 = r0
            goto L_0x004a
        L_0x002e:
            r5 = move-exception
            r1 = r0
            goto L_0x0034
        L_0x0031:
            r5 = move-exception
            goto L_0x004a
        L_0x0033:
            r5 = move-exception
        L_0x0034:
            boolean r0 = r5 instanceof java.lang.OutOfMemoryError     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0040
            java.lang.String r5 = "getStackTraceString oom"
            if (r1 == 0) goto L_0x003f
            r1.close()     // Catch:{ Throwable -> 0x003f }
        L_0x003f:
            return r5
        L_0x0040:
            java.lang.String r5 = r5.getMessage()     // Catch:{ all -> 0x0031 }
            if (r1 == 0) goto L_0x0049
            r1.close()     // Catch:{ Throwable -> 0x0049 }
        L_0x0049:
            return r5
        L_0x004a:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ Throwable -> 0x004f }
        L_0x004f:
            goto L_0x0051
        L_0x0050:
            throw r5
        L_0x0051:
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2296d.m2450a(java.lang.Throwable):java.lang.String");
    }

    /* renamed from: g */
    private File m2461g() {
        return new File(ResHelper.getDataCache(MobSDK.getContext()), ".dyl");
    }

    /* renamed from: c */
    private void m2456c(HashMap<String, Object> hashMap) {
        try {
            m2458d(hashMap);
        } catch (Throwable unused) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* renamed from: d */
    private void m2458d(HashMap<String, Object> hashMap) throws Throwable {
        File[] listFiles;
        File g = m2461g();
        if (!g.exists() || !g.isDirectory()) {
            g.mkdirs();
        }
        File file = new File(g, ".dyl_0");
        if (file.exists() && (listFiles = g.listFiles()) != null && listFiles.length > 0) {
            file = new File(g, ".dyl_" + 0);
            int i = 0;
            while (file.exists()) {
                i++;
                file = new File(g, ".dyl_" + i);
            }
        }
        ResHelper.saveObjectToFile(file.getPath(), hashMap);
    }
}
