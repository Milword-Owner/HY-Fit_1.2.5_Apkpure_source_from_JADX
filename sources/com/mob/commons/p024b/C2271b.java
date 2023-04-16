package com.mob.commons.p024b;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.mob.commons.b.b */
/* compiled from: Caches */
public class C2271b {

    /* renamed from: a */
    private static C2271b f2043a;

    /* renamed from: b */
    private Context f2044b;

    /* renamed from: c */
    private HashMap<String, Object> f2045c = new HashMap<>();

    private C2271b(Context context) {
        this.f2044b = context;
        try {
            HashMap hashMap = (HashMap) m2339a(m2338a(context, ".msas"));
            if (hashMap != null && hashMap.size() > 0) {
                this.f2045c.putAll(hashMap);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static synchronized C2271b m2337a(Context context) {
        C2271b bVar;
        synchronized (C2271b.class) {
            if (f2043a == null) {
                f2043a = new C2271b(context);
            }
            bVar = f2043a;
        }
        return bVar;
    }

    /* renamed from: a */
    public synchronized HashMap<String, Object> mo29042a() {
        return this.f2045c;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0062, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean mo29043a(java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.String r6, boolean r7) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            r0.<init>()     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            if (r1 != 0) goto L_0x0015
            r1 = 69
            java.lang.String r1 = com.mob.commons.C2312k.m2575a(r1)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            r0.put(r1, r3)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
        L_0x0015:
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            if (r3 != 0) goto L_0x0024
            r3 = 75
            java.lang.String r3 = com.mob.commons.C2312k.m2575a(r3)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
        L_0x0024:
            boolean r3 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            if (r3 != 0) goto L_0x0033
            r3 = 70
            java.lang.String r3 = com.mob.commons.C2312k.m2575a(r3)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            r0.put(r3, r5)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
        L_0x0033:
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            if (r3 != 0) goto L_0x0042
            r3 = 71
            java.lang.String r3 = com.mob.commons.C2312k.m2575a(r3)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            r0.put(r3, r6)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
        L_0x0042:
            r3 = 74
            java.lang.String r3 = com.mob.commons.C2312k.m2575a(r3)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r7)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            android.content.Context r3 = r2.f2044b     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            java.lang.String r4 = ".msas"
            java.io.File r3 = m2338a((android.content.Context) r3, (java.lang.String) r4)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            boolean r3 = m2340a((java.io.File) r3, (java.lang.Object) r0)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            monitor-exit(r2)
            return r3
        L_0x005d:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x0060:
            r3 = 0
            monitor-exit(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p024b.C2271b.mo29043a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean):boolean");
    }

    /* renamed from: a */
    private static File m2338a(Context context, String str) {
        try {
            String b = m2341b(context);
            if (b == null) {
                return null;
            }
            File file = new File(b, str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            return file;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: b */
    private static String m2341b(Context context) {
        String str = context.getFilesDir().getAbsolutePath() + "/Mob/";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    /* renamed from: a */
    private static Object m2339a(File file) {
        try {
            if (!file.exists()) {
                return null;
            }
            ObjectInputStream objectInputStream = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m2340a(File file, Object obj) {
        try {
            if (file.exists()) {
                file.delete();
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
