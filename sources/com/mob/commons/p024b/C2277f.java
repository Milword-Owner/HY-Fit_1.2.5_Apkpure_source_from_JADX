package com.mob.commons.p024b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.mob.commons.b.f */
/* compiled from: IdSupplier */
public abstract class C2277f {

    /* renamed from: a */
    protected Context f2069a;

    /* renamed from: b */
    protected String f2070b;

    /* renamed from: c */
    private boolean f2071c = false;

    /* renamed from: d */
    private boolean f2072d = false;

    /* renamed from: e */
    private String f2073e = null;

    /* renamed from: f */
    private String f2074f = null;

    /* renamed from: g */
    private String f2075g = null;

    /* renamed from: h */
    private String f2076h = null;

    /* renamed from: com.mob.commons.b.f$c */
    /* compiled from: IdSupplier */
    public static class C2281c {

        /* renamed from: a */
        boolean f2081a;

        /* renamed from: b */
        String f2082b;

        /* renamed from: c */
        String f2083c;

        /* renamed from: d */
        String f2084d;

        /* renamed from: e */
        String f2085e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Intent mo29040a() {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C2281c mo29041a(IBinder iBinder) {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C2281c mo29050c() {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo29052d() {
        return 2500;
    }

    public C2277f(Context context) {
        this.f2069a = context;
        this.f2070b = context.getPackageName();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void mo29048a(String str) {
        this.f2073e = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public synchronized void mo29049b(String str) {
        this.f2074f = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public synchronized void mo29051c(String str) {
        this.f2076h = str;
    }

    /* renamed from: j */
    private synchronized long m2365j() {
        if (this.f2071c) {
            return -1;
        }
        long a = m2363a(mo29040a());
        this.f2071c = true;
        return a;
    }

    /* renamed from: a */
    private synchronized long m2363a(Intent intent) {
        long elapsedRealtime;
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        try {
            C2281c c = mo29050c();
            if (c == null) {
                c = m2364a(this.f2069a, intent);
            }
            if (c != null) {
                this.f2072d = c.f2081a;
                this.f2073e = c.f2082b;
                this.f2074f = c.f2083c;
                this.f2075g = c.f2084d;
                this.f2076h = c.f2085e;
            }
        } catch (Throwable th) {
            C2272c.m2344a().mo29044a(th);
        }
        elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime2;
        C2272c a = C2272c.m2344a();
        a.mo29044a("usedTime: " + elapsedRealtime);
        return elapsedRealtime;
    }

    /* renamed from: b */
    public synchronized String mo29045b() {
        m2365j();
        return this.f2074f;
    }

    /* renamed from: e */
    public synchronized String mo29053e() {
        m2365j();
        return this.f2073e;
    }

    /* renamed from: f */
    public synchronized String mo29054f() {
        m2365j();
        return this.f2075g;
    }

    /* renamed from: g */
    public synchronized String mo29055g() {
        m2365j();
        return this.f2076h;
    }

    /* renamed from: h */
    public synchronized boolean mo29056h() {
        m2365j();
        return this.f2072d;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0068 A[SYNTHETIC, Splitter:B:33:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006d A[Catch:{ Throwable -> 0x0070 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0074 A[SYNTHETIC, Splitter:B:41:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0079 A[Catch:{ Throwable -> 0x007c }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String mo29047a(java.lang.String r7, android.os.IBinder r8, java.lang.String r9, int r10, java.lang.String... r11) {
        /*
            r6 = this;
            r0 = 0
            android.os.Parcel r1 = android.os.Parcel.obtain()     // Catch:{ Throwable -> 0x003f, all -> 0x003b }
            android.os.Parcel r2 = android.os.Parcel.obtain()     // Catch:{ Throwable -> 0x0038, all -> 0x0035 }
            r1.writeInterfaceToken(r9)     // Catch:{ Throwable -> 0x0033 }
            r9 = 0
            if (r11 == 0) goto L_0x001e
            int r3 = r11.length     // Catch:{ Throwable -> 0x0033 }
            if (r3 <= 0) goto L_0x001e
            int r3 = r11.length     // Catch:{ Throwable -> 0x0033 }
            r4 = 0
        L_0x0014:
            if (r4 >= r3) goto L_0x001e
            r5 = r11[r4]     // Catch:{ Throwable -> 0x0033 }
            r1.writeString(r5)     // Catch:{ Throwable -> 0x0033 }
            int r4 = r4 + 1
            goto L_0x0014
        L_0x001e:
            r8.transact(r10, r1, r2, r9)     // Catch:{ Throwable -> 0x0033 }
            r2.readException()     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r7 = r2.readString()     // Catch:{ Throwable -> 0x0033 }
            if (r2 == 0) goto L_0x002d
            r2.recycle()     // Catch:{ Throwable -> 0x0032 }
        L_0x002d:
            if (r1 == 0) goto L_0x0032
            r1.recycle()     // Catch:{ Throwable -> 0x0032 }
        L_0x0032:
            return r7
        L_0x0033:
            r8 = move-exception
            goto L_0x0042
        L_0x0035:
            r7 = move-exception
            r2 = r0
            goto L_0x0072
        L_0x0038:
            r8 = move-exception
            r2 = r0
            goto L_0x0042
        L_0x003b:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x0072
        L_0x003f:
            r8 = move-exception
            r1 = r0
            r2 = r1
        L_0x0042:
            com.mob.commons.b.c r9 = com.mob.commons.p024b.C2272c.m2344a()     // Catch:{ all -> 0x0071 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r10.<init>()     // Catch:{ all -> 0x0071 }
            java.lang.String r11 = "getStringValue: "
            r10.append(r11)     // Catch:{ all -> 0x0071 }
            r10.append(r7)     // Catch:{ all -> 0x0071 }
            java.lang.String r7 = " failed! "
            r10.append(r7)     // Catch:{ all -> 0x0071 }
            java.lang.String r7 = r8.getMessage()     // Catch:{ all -> 0x0071 }
            r10.append(r7)     // Catch:{ all -> 0x0071 }
            java.lang.String r7 = r10.toString()     // Catch:{ all -> 0x0071 }
            r9.mo29044a(r7)     // Catch:{ all -> 0x0071 }
            if (r2 == 0) goto L_0x006b
            r2.recycle()     // Catch:{ Throwable -> 0x0070 }
        L_0x006b:
            if (r1 == 0) goto L_0x0070
            r1.recycle()     // Catch:{ Throwable -> 0x0070 }
        L_0x0070:
            return r0
        L_0x0071:
            r7 = move-exception
        L_0x0072:
            if (r2 == 0) goto L_0x0077
            r2.recycle()     // Catch:{ Throwable -> 0x007c }
        L_0x0077:
            if (r1 == 0) goto L_0x007c
            r1.recycle()     // Catch:{ Throwable -> 0x007c }
        L_0x007c:
            goto L_0x007e
        L_0x007d:
            throw r7
        L_0x007e:
            goto L_0x007d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p024b.C2277f.mo29047a(java.lang.String, android.os.IBinder, java.lang.String, int, java.lang.String[]):java.lang.String");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004c A[SYNTHETIC, Splitter:B:23:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0051 A[Catch:{ Throwable -> 0x0054 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0058 A[SYNTHETIC, Splitter:B:31:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005d A[Catch:{ Throwable -> 0x0060 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo29046a(java.lang.String r4, android.os.IBinder r5, java.lang.String r6, int r7) {
        /*
            r3 = this;
            r0 = 0
            r1 = 0
            android.os.Parcel r2 = android.os.Parcel.obtain()     // Catch:{ RemoteException -> 0x002c, all -> 0x0029 }
            android.os.Parcel r1 = android.os.Parcel.obtain()     // Catch:{ RemoteException -> 0x0026, all -> 0x0022 }
            r2.writeInterfaceToken(r6)     // Catch:{ RemoteException -> 0x0026, all -> 0x0022 }
            r5.transact(r7, r2, r1, r0)     // Catch:{ RemoteException -> 0x0026, all -> 0x0022 }
            r1.readException()     // Catch:{ RemoteException -> 0x0026, all -> 0x0022 }
            int r4 = r1.readInt()     // Catch:{ RemoteException -> 0x0026, all -> 0x0022 }
            if (r1 == 0) goto L_0x001c
            r1.recycle()     // Catch:{ Throwable -> 0x0021 }
        L_0x001c:
            if (r2 == 0) goto L_0x0021
            r2.recycle()     // Catch:{ Throwable -> 0x0021 }
        L_0x0021:
            return r4
        L_0x0022:
            r4 = move-exception
            r5 = r1
            r1 = r2
            goto L_0x0056
        L_0x0026:
            r5 = r1
            r1 = r2
            goto L_0x002d
        L_0x0029:
            r4 = move-exception
            r5 = r1
            goto L_0x0056
        L_0x002c:
            r5 = r1
        L_0x002d:
            com.mob.commons.b.c r6 = com.mob.commons.p024b.C2272c.m2344a()     // Catch:{ all -> 0x0055 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0055 }
            r7.<init>()     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = "getIntValue: "
            r7.append(r2)     // Catch:{ all -> 0x0055 }
            r7.append(r4)     // Catch:{ all -> 0x0055 }
            java.lang.String r4 = " failed! (remoteException)"
            r7.append(r4)     // Catch:{ all -> 0x0055 }
            java.lang.String r4 = r7.toString()     // Catch:{ all -> 0x0055 }
            r6.mo29044a(r4)     // Catch:{ all -> 0x0055 }
            if (r5 == 0) goto L_0x004f
            r5.recycle()     // Catch:{ Throwable -> 0x0054 }
        L_0x004f:
            if (r1 == 0) goto L_0x0054
            r1.recycle()     // Catch:{ Throwable -> 0x0054 }
        L_0x0054:
            return r0
        L_0x0055:
            r4 = move-exception
        L_0x0056:
            if (r5 == 0) goto L_0x005b
            r5.recycle()     // Catch:{ Throwable -> 0x0060 }
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.recycle()     // Catch:{ Throwable -> 0x0060 }
        L_0x0060:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p024b.C2277f.mo29046a(java.lang.String, android.os.IBinder, java.lang.String, int):int");
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public String mo29057i() {
        if (TextUtils.isEmpty(this.f2070b)) {
            return "";
        }
        return C2279a.m2382b("0x1008611" + this.f2070b + "0xdzfdweiwu");
    }

    /* renamed from: a */
    private C2281c m2364a(Context context, Intent intent) throws Throwable {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            C2280b bVar = new C2280b();
            if (intent != null) {
                try {
                    if (context.bindService(intent, bVar, 1)) {
                        IBinder a = bVar.mo29058a(mo29052d());
                        if (a != null) {
                            C2281c a2 = mo29041a(a);
                            try {
                                context.unbindService(bVar);
                            } catch (Throwable th) {
                                C2272c.m2344a().mo29044a(th);
                            }
                            return a2;
                        }
                        throw new Throwable("get binder " + intent.getComponent() + " failed!");
                    }
                } catch (Throwable th2) {
                    C2272c.m2344a().mo29044a(th2);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("bind service ");
            sb.append(intent == null ? "null" : intent.getComponent());
            sb.append(" failed!");
            throw new Throwable(sb.toString());
        }
        throw new Throwable("unable to invoke in main thread!");
        throw th;
    }

    /* renamed from: com.mob.commons.b.f$b */
    /* compiled from: IdSupplier */
    private class C2280b implements ServiceConnection {

        /* renamed from: a */
        boolean f2078a;

        /* renamed from: c */
        private final BlockingQueue<IBinder> f2080c;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private C2280b() {
            this.f2078a = false;
            this.f2080c = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f2080c.put(iBinder);
            } catch (Throwable unused) {
            }
        }

        /* renamed from: a */
        public IBinder mo29058a(long j) throws InterruptedException {
            if (!this.f2078a) {
                this.f2078a = true;
                BlockingQueue<IBinder> blockingQueue = this.f2080c;
                if (j <= 0) {
                    j = 1500;
                }
                return blockingQueue.poll(j, TimeUnit.MILLISECONDS);
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: com.mob.commons.b.f$a */
    /* compiled from: IdSupplier */
    private static class C2279a {

        /* renamed from: a */
        private static final char[] f2077a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        /* access modifiers changed from: private */
        /* renamed from: b */
        public static String m2382b(String str) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(str.getBytes("UTF-8"));
                byte[] digest = instance.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(f2077a[(b & 240) >> 4]);
                    sb.append(f2077a[b & 15]);
                }
                return sb.toString();
            } catch (Throwable th) {
                C2272c.m2344a().mo29044a(th);
                return str;
            }
        }
    }
}
