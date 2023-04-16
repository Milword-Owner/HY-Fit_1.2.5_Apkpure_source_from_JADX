package com.mob.mobapm.core.p028k;

import android.app.ActivityManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.mobapm.core.C2358e;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2377c;
import com.mob.tools.log.NLog;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.mob.mobapm.core.k.a */
public class C2368a extends Thread {
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static int f2276d;

    /* renamed from: a */
    private Handler f2277a = new C2369a(Looper.getMainLooper());
    /* access modifiers changed from: private */

    /* renamed from: b */
    public boolean f2278b = true;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public int f2279c = 0;

    /* renamed from: com.mob.mobapm.core.k.a$a */
    class C2369a extends Handler {
        C2369a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int unused = C2368a.f2276d = 0;
            int unused2 = C2368a.this.f2279c = 0;
            boolean unused3 = C2368a.this.f2278b = true;
        }
    }

    /* renamed from: com.mob.mobapm.core.k.a$b */
    class C2370b implements Runnable {
        C2370b() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x007b A[SYNTHETIC, Splitter:B:22:0x007b] */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x010e A[Catch:{ all -> 0x0112 }] */
        /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r12 = this;
                java.lang.String r0 = "stack"
                java.lang.String r1 = "desc"
                r2 = 0
                long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0112 }
                java.util.HashMap r5 = new java.util.HashMap     // Catch:{ all -> 0x0112 }
                r5.<init>()     // Catch:{ all -> 0x0112 }
                java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x0112 }
                r6.<init>()     // Catch:{ all -> 0x0112 }
                com.mob.mobapm.core.k.a r7 = com.mob.mobapm.core.p028k.C2368a.this     // Catch:{ all -> 0x0112 }
                java.util.Map r7 = r7.m2801c()     // Catch:{ all -> 0x0112 }
                java.lang.String r8 = "type"
                com.mob.mobapm.bean.ExceptionType r9 = com.mob.mobapm.bean.ExceptionType.STUCK     // Catch:{ all -> 0x0112 }
                java.lang.String r9 = r9.name     // Catch:{ all -> 0x0112 }
                r6.put(r8, r9)     // Catch:{ all -> 0x0112 }
                java.lang.String r8 = "happenTime"
                java.lang.Long r9 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0112 }
                r6.put(r8, r9)     // Catch:{ all -> 0x0112 }
                java.lang.String r8 = "threadName"
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0112 }
                r9.<init>()     // Catch:{ all -> 0x0112 }
                android.os.Looper r10 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0112 }
                java.lang.Thread r10 = r10.getThread()     // Catch:{ all -> 0x0112 }
                long r10 = r10.getId()     // Catch:{ all -> 0x0112 }
                r9.append(r10)     // Catch:{ all -> 0x0112 }
                java.lang.String r10 = "_"
                r9.append(r10)     // Catch:{ all -> 0x0112 }
                android.os.Looper r10 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0112 }
                java.lang.Thread r10 = r10.getThread()     // Catch:{ all -> 0x0112 }
                java.lang.String r10 = r10.getName()     // Catch:{ all -> 0x0112 }
                r9.append(r10)     // Catch:{ all -> 0x0112 }
                java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0112 }
                r6.put(r8, r9)     // Catch:{ all -> 0x0112 }
                java.lang.String r8 = "errType"
                java.lang.String r9 = "ANR_EXCEPTION"
                r6.put(r8, r9)     // Catch:{ all -> 0x0112 }
                java.lang.String r8 = "errDesc"
                r9 = 0
                if (r7 == 0) goto L_0x0073
                boolean r10 = r7.containsKey(r1)     // Catch:{ all -> 0x0112 }
                if (r10 == 0) goto L_0x0073
                java.lang.Object r1 = r7.get(r1)     // Catch:{ all -> 0x0112 }
                goto L_0x0074
            L_0x0073:
                r1 = r9
            L_0x0074:
                r6.put(r8, r1)     // Catch:{ all -> 0x0112 }
                java.lang.String r1 = "stackDetail"
                if (r7 == 0) goto L_0x0085
                boolean r8 = r7.containsKey(r0)     // Catch:{ all -> 0x0112 }
                if (r8 == 0) goto L_0x0085
                java.lang.Object r9 = r7.get(r0)     // Catch:{ all -> 0x0112 }
            L_0x0085:
                r6.put(r1, r9)     // Catch:{ all -> 0x0112 }
                java.lang.String r0 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0112 }
                r5.put(r0, r6)     // Catch:{ all -> 0x0112 }
                com.mob.mobapm.p026b.C2345a.m2720d(r5)     // Catch:{ all -> 0x0112 }
                java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x0112 }
                r0.<init>()     // Catch:{ all -> 0x0112 }
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0112 }
                r1.<init>()     // Catch:{ all -> 0x0112 }
                r1.add(r6)     // Catch:{ all -> 0x0112 }
                java.lang.String r3 = "bundleName"
                android.content.Context r4 = com.mob.MobSDK.getContext()     // Catch:{ all -> 0x0112 }
                java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x0112 }
                r0.put(r3, r4)     // Catch:{ all -> 0x0112 }
                java.lang.String r3 = "uploadTime"
                long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0112 }
                java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0112 }
                r0.put(r3, r4)     // Catch:{ all -> 0x0112 }
                java.lang.String r3 = "errorStack"
                r0.put(r3, r1)     // Catch:{ all -> 0x0112 }
                com.mob.tools.log.NLog r1 = com.mob.mobapm.p030d.C2373a.m2807a()     // Catch:{ all -> 0x0112 }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0112 }
                r3.<init>()     // Catch:{ all -> 0x0112 }
                java.lang.String r4 = "APM: upload anr Object: "
                r3.append(r4)     // Catch:{ all -> 0x0112 }
                r3.append(r0)     // Catch:{ all -> 0x0112 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0112 }
                java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ all -> 0x0112 }
                r1.mo29768d(r3, r4)     // Catch:{ all -> 0x0112 }
                boolean r1 = com.mob.mobapm.core.C2356c.f2253g     // Catch:{ all -> 0x0112 }
                java.lang.Object r0 = com.mob.mobapm.core.C2357d.m2771a(r0, r1)     // Catch:{ all -> 0x0112 }
                com.mob.tools.log.NLog r1 = com.mob.mobapm.p030d.C2373a.m2807a()     // Catch:{ all -> 0x0112 }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0112 }
                r3.<init>()     // Catch:{ all -> 0x0112 }
                java.lang.String r4 = "APM: upload anr result. object:"
                r3.append(r4)     // Catch:{ all -> 0x0112 }
                r3.append(r0)     // Catch:{ all -> 0x0112 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0112 }
                java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ all -> 0x0112 }
                r1.mo29768d(r3, r4)     // Catch:{ all -> 0x0112 }
                boolean r1 = r0 instanceof java.util.HashMap     // Catch:{ all -> 0x0112 }
                if (r1 == 0) goto L_0x0111
                java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ all -> 0x0112 }
                java.lang.String r1 = "code"
                java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0112 }
                java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x0112 }
                int r0 = r0.intValue()     // Catch:{ all -> 0x0112 }
                r1 = 200(0xc8, float:2.8E-43)
                if (r0 != r1) goto L_0x0111
                com.mob.mobapm.p026b.C2345a.m2712a((java.util.HashMap<java.lang.String, java.lang.Object>) r6)     // Catch:{ all -> 0x0112 }
            L_0x0111:
                return
            L_0x0112:
                r0 = move-exception
                com.mob.tools.log.NLog r1 = com.mob.mobapm.p030d.C2373a.m2807a()
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "APM: upload anr error:"
                r3.append(r4)
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r1.mo29768d(r0, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.mobapm.core.p028k.C2368a.C2370b.run():void");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public Map<String, Object> m2801c() {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces != null && !allStackTraces.isEmpty()) {
            Thread thread = Looper.getMainLooper().getThread();
            if (allStackTraces.containsKey(thread)) {
                return C2377c.m2817a(allStackTraces.get(thread));
            }
        }
        return null;
    }

    /* renamed from: d */
    private void m2802d() {
        int i = 0;
        do {
            try {
                List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = ((ActivityManager) MobSDK.getContext().getSystemService("activity")).getProcessesInErrorState();
                if (processesInErrorState != null) {
                    if (!processesInErrorState.isEmpty()) {
                        Iterator<ActivityManager.ProcessErrorStateInfo> it = processesInErrorState.iterator();
                        if (!it.hasNext() || it.next().condition != 2) {
                            Thread.sleep(500);
                        } else {
                            if (this.f2279c < 2) {
                                this.f2279c = 2;
                            }
                            C2358e.m2781b().mo29255a(new C2370b());
                            return;
                        }
                    }
                }
            } catch (InterruptedException e) {
                NLog a = C2373a.m2807a();
                a.mo29768d("APM: find anr stack interrupted: " + e, new Object[0]);
            }
            i++;
        } while (i < 50);
        this.f2278b = true;
    }

    /* renamed from: b */
    public void mo29267b() {
        while (!isInterrupted()) {
            f2276d++;
            if (this.f2278b) {
                this.f2277a.sendEmptyMessage(0);
            }
            try {
                Thread.sleep(Config.BPLUS_DELAY_TIME);
                if (f2276d != 0 && this.f2279c <= 1) {
                    this.f2279c = 1;
                    m2802d();
                }
            } catch (InterruptedException e) {
                C2373a.m2807a().mo29768d("APM: processs stack error: " + e, new Object[0]);
            }
        }
    }

    public void run() {
        super.run();
        mo29266a();
    }

    /* renamed from: a */
    public void mo29266a() {
        mo29267b();
    }
}
