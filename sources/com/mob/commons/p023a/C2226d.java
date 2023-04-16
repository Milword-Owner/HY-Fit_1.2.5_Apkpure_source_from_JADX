package com.mob.commons.p023a;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2296d;
import com.mob.commons.C2300e;
import com.mob.commons.C2312k;
import com.mob.commons.LockAction;
import com.mob.commons.MobProduct;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.p024b.C2273d;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import java.io.Closeable;
import java.io.File;
import java.util.HashMap;

/* renamed from: com.mob.commons.a.d */
/* compiled from: BaseClt */
public class C2226d implements Handler.Callback {
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static HashMap<String, C2226d> f1956c = new HashMap<>();

    /* renamed from: d */
    private static HashMap<String, Object> f1957d = new HashMap<>();

    /* renamed from: a */
    private MobHandlerThread f1958a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public boolean f1959b = false;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Handler f1960e;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo28990b() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003f, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final synchronized void m2017a(java.lang.Class<? extends com.mob.commons.p023a.C2226d>... r6) {
        /*
            java.lang.Class<com.mob.commons.a.d> r0 = com.mob.commons.p023a.C2226d.class
            monitor-enter(r0)
            if (r6 == 0) goto L_0x003e
            int r1 = r6.length     // Catch:{ all -> 0x003b }
            if (r1 != 0) goto L_0x0009
            goto L_0x003e
        L_0x0009:
            int r1 = r6.length     // Catch:{ all -> 0x003b }
            r2 = 0
        L_0x000b:
            if (r2 >= r1) goto L_0x0039
            r3 = r6[r2]     // Catch:{ all -> 0x003b }
            if (r3 == 0) goto L_0x0036
            java.lang.String r4 = r3.getSimpleName()     // Catch:{ all -> 0x003b }
            java.util.HashMap<java.lang.String, com.mob.commons.a.d> r5 = f1956c     // Catch:{ all -> 0x003b }
            java.lang.Object r5 = r5.get(r4)     // Catch:{ all -> 0x003b }
            com.mob.commons.a.d r5 = (com.mob.commons.p023a.C2226d) r5     // Catch:{ all -> 0x003b }
            if (r5 != 0) goto L_0x0036
            java.lang.Object r3 = r3.newInstance()     // Catch:{ Throwable -> 0x002e }
            com.mob.commons.a.d r3 = (com.mob.commons.p023a.C2226d) r3     // Catch:{ Throwable -> 0x002e }
            java.util.HashMap<java.lang.String, com.mob.commons.a.d> r5 = f1956c     // Catch:{ Throwable -> 0x002e }
            r5.put(r4, r3)     // Catch:{ Throwable -> 0x002e }
            r3.mo29026h()     // Catch:{ Throwable -> 0x002e }
            goto L_0x0036
        L_0x002e:
            r3 = move-exception
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x003b }
            r4.mo29769d(r3)     // Catch:{ all -> 0x003b }
        L_0x0036:
            int r2 = r2 + 1
            goto L_0x000b
        L_0x0039:
            monitor-exit(r0)
            return
        L_0x003b:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        L_0x003e:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2226d.m2017a(java.lang.Class[]):void");
    }

    /* renamed from: h */
    private void mo29026h() {
        final File a = mo28993a();
        if (a != null) {
            this.f1958a = new MobHandlerThread() {
                public void run() {
                    try {
                        if (!C2300e.m2467a(a, new LockAction() {
                            public boolean run(FileLocker fileLocker) {
                                try {
                                    NLog instance = MobLog.getInstance();
                                    instance.mo29768d("synchronizeProcess success clt: " + C2226d.this.getClass().getSimpleName() + ", file: " + a.getPath() + ", pid: " + Process.myPid() + ", isStop: " + C2226d.this.f1959b, new Object[0]);
                                    if (!C2226d.this.f1959b) {
                                        boolean Z = C2262b.m2259Z();
                                        boolean b_ = C2226d.this.mo28994b_();
                                        NLog instance2 = MobLog.getInstance();
                                        instance2.mo29768d("Clt entrance. forb: " + Z + ", coll: " + b_, new Object[0]);
                                        if (!Z && b_) {
                                            C22271.this.m2033a();
                                        }
                                    }
                                } catch (Throwable th) {
                                    MobLog.getInstance().mo29769d(th);
                                }
                                return false;
                            }
                        })) {
                            NLog instance = MobLog.getInstance();
                            instance.mo29786w("synchronizeProcess failed clt: " + C2226d.this.getClass().getSimpleName() + ", file: " + a.getPath());
                            C2226d.f1956c.put(getClass().getSimpleName(), (Object) null);
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29769d(th);
                    }
                }

                /* access modifiers changed from: private */
                /* renamed from: a */
                public void m2033a() {
                    super.run();
                }

                /* access modifiers changed from: protected */
                public void onLooperPrepared(Looper looper) {
                    try {
                        Handler unused = C2226d.this.f1960e = new Handler(looper, C2226d.this);
                        C2226d.this.mo28995d();
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29769d(th);
                    }
                }
            };
            this.f1958a.start();
        }
    }

    public final boolean handleMessage(Message message) {
        if (C2262b.m2243J()) {
            mo29001e();
            return false;
        }
        mo28987a(message);
        return false;
    }

    /* renamed from: a */
    public static void m2016a(String str, File file, String str2, String str3) throws Throwable {
        Object obj;
        Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), C2312k.m2575a(8), new Object[0]);
        ReflectHelper.importClass(C2312k.m2575a(9), C2312k.m2575a(9));
        File parentFile = file.getParentFile();
        synchronized (f1957d) {
            obj = f1957d.get(str);
            if (obj == null) {
                obj = ReflectHelper.newInstance(C2312k.m2575a(9), file.getAbsolutePath(), parentFile.getAbsolutePath(), parentFile.getAbsolutePath(), invokeInstanceMethod);
                f1957d.put(str, obj);
            }
        }
        ResHelper.deleteFileAndFolder(parentFile);
        String authorize = DeviceAuthorizer.authorize((MobProduct) null);
        final Object invokeInstanceMethod2 = ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeInstanceMethod(obj, C2312k.m2575a(10), str2), C2312k.m2575a(11), str3, String.class);
        HashMap hashMap = new HashMap();
        hashMap.put("duid", authorize);
        hashMap.put("moid", C2273d.m2353c(MobSDK.getContext()));
        hashMap.put("sdkVersion", Integer.valueOf(MobSDK.SDK_VERSION_CODE));
        hashMap.put("appKey", MobSDK.getAppkey());
        hashMap.put("appSecret", MobSDK.getAppSecret());
        hashMap.put("domain", MobSDK.getDomain().getDomain());
        hashMap.put("forceHttps", Boolean.valueOf(MobSDK.checkForceHttps()));
        final String fromHashMap = new Hashon().fromHashMap(hashMap);
        ReflectHelper.invokeInstanceMethod(invokeInstanceMethod2, C2312k.m2575a(12), true);
        C2296d.m2449a().mo29071a(15);
        UIHandler.sendEmptyMessage(0, new Handler.Callback() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Object[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean handleMessage(android.os.Message r7) {
                /*
                    r6 = this;
                    r7 = 0
                    com.mob.commons.d r0 = com.mob.commons.C2296d.m2449a()     // Catch:{ Throwable -> 0x002e }
                    r1 = 16
                    r0.mo29071a((int) r1)     // Catch:{ Throwable -> 0x002e }
                    java.lang.Object r0 = r9     // Catch:{ Throwable -> 0x002e }
                    r1 = 13
                    java.lang.String r1 = com.mob.commons.C2312k.m2575a(r1)     // Catch:{ Throwable -> 0x002e }
                    r2 = 2
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x002e }
                    r3 = 0
                    r2[r7] = r3     // Catch:{ Throwable -> 0x002e }
                    r3 = 1
                    java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x002e }
                    java.lang.String r5 = r8     // Catch:{ Throwable -> 0x002e }
                    r4[r7] = r5     // Catch:{ Throwable -> 0x002e }
                    r2[r3] = r4     // Catch:{ Throwable -> 0x002e }
                    com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r1, r2)     // Catch:{ Throwable -> 0x002e }
                    com.mob.commons.d r0 = com.mob.commons.C2296d.m2449a()     // Catch:{ Throwable -> 0x002e }
                    r1 = 17
                    r0.mo29071a((int) r1)     // Catch:{ Throwable -> 0x002e }
                    goto L_0x0037
                L_0x002e:
                    r0 = move-exception
                    com.mob.commons.d r1 = com.mob.commons.C2296d.m2449a()
                    r2 = 7
                    r1.mo29072a(r2, r0)
                L_0x0037:
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p023a.C2226d.C22292.handleMessage(android.os.Message):boolean");
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final void mo29001e() {
        try {
            if (this.f1960e != null) {
                this.f1960e.removeCallbacksAndMessages((Object) null);
            }
            if (this.f1958a != null) {
                this.f1958a.quit();
            }
            this.f1960e = null;
            this.f1958a = null;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        mo28990b();
        this.f1959b = true;
        f1956c.put(getClass().getSimpleName(), (Object) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo28996a(int i) {
        Handler handler = this.f1960e;
        if (handler != null) {
            handler.removeMessages(i);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo28999b(int i) {
        Handler handler = this.f1960e;
        if (handler != null) {
            handler.sendEmptyMessage(i);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo28997a(int i, long j) {
        Handler handler = this.f1960e;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(i, j);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo29000b(Message message) {
        Handler handler = this.f1960e;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28998a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public HashMap<String, Object> mo29002f() {
        Location location;
        if (!C2262b.m2329x() || (location = DeviceHelper.getInstance(MobSDK.getContext()).getLocation(0, 0, true)) == null) {
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
        return hashMap;
    }
}
