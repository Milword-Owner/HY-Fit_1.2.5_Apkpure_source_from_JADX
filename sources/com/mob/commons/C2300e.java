package com.mob.commons;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.utils.ResHelper;
import java.io.File;

/* renamed from: com.mob.commons.e */
/* compiled from: Locks */
public class C2300e {

    /* renamed from: a */
    public static final Object f2124a = new Object();

    /* renamed from: a */
    public static synchronized File m2466a(String str) {
        File dataCacheFile;
        synchronized (C2300e.class) {
            dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), str);
        }
        return dataCacheFile;
    }

    /* renamed from: a */
    public static boolean m2467a(File file, LockAction lockAction) {
        return m2468a(file, true, lockAction);
    }

    /* renamed from: b */
    private static String m2469b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.endsWith("comm/locks/.dhlock")) {
            return "comm/locks/.dhlock";
        }
        if (str.endsWith("comm/locks/.mrlock")) {
            return "comm/locks/.mrlock";
        }
        if (str.endsWith("comm/locks/.rc_lock")) {
            return "comm/locks/.rc_lock";
        }
        if (str.endsWith("comm/locks/.artc_lock")) {
            return "comm/locks/.artc_lock";
        }
        if (str.endsWith("comm/locks/.lesd_lock")) {
            return "comm/locks/.lesd_lock";
        }
        if (str.endsWith("comm/locks/.dic_lock")) {
            return "comm/locks/.dic_lock";
        }
        if (str.endsWith("comm/locks/.pkgs_lock")) {
            return "comm/locks/.pkgs_lock";
        }
        if (str.endsWith("comm/locks/.pkg_lock")) {
            return "comm/locks/.pkg_lock";
        }
        if (str.endsWith("comm/locks/.ss_lock")) {
            return "comm/locks/.ss_lock";
        }
        if (str.endsWith("comm/locks/.im_lock")) {
            return "comm/locks/.im_lock";
        }
        if (str.endsWith("comm/locks/.mph_lock")) {
            return "comm/locks/.mph_lock";
        }
        if (str.endsWith("comm/locks/.gm_lock")) {
            return "comm/locks/.gm_lock";
        }
        if (str.endsWith("comm/locks/.cz_lock")) {
            return "comm/locks/.cz_lock";
        }
        if (str.endsWith("comm/locks/.du_lock")) {
            return "comm/locks/.du_lock";
        }
        if (str.endsWith("comm/locks/.bs_lock")) {
            return "comm/locks/.bs_lock";
        }
        if (str.endsWith("comm/locks/.dy_lock")) {
            return "comm/locks/.dy_lock";
        }
        if (str.endsWith("comm/locks/.at_lock")) {
            return "comm/locks/.at_lock";
        }
        if (str.endsWith("comm/locks/.bt_lock")) {
            return "comm/locks/.bt_lock";
        }
        if (str.endsWith("comm/locks/.bts_lock")) {
            return "comm/locks/.bts_lock";
        }
        return str.endsWith("comm/locks/.dvcv_lock") ? "comm/locks/.dvcv_lock" : str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return true;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m2468a(java.io.File r2, boolean r3, com.mob.commons.LockAction r4) {
        /*
            java.io.File r0 = r2.getParentFile()     // Catch:{ Throwable -> 0x0042 }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x0042 }
            if (r0 != 0) goto L_0x0011
            java.io.File r0 = r2.getParentFile()     // Catch:{ Throwable -> 0x0042 }
            r0.mkdirs()     // Catch:{ Throwable -> 0x0042 }
        L_0x0011:
            boolean r0 = r2.exists()     // Catch:{ Throwable -> 0x0042 }
            if (r0 != 0) goto L_0x001a
            r2.createNewFile()     // Catch:{ Throwable -> 0x0042 }
        L_0x001a:
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ Throwable -> 0x0042 }
            java.lang.String r0 = m2469b(r2)     // Catch:{ Throwable -> 0x0042 }
            monitor-enter(r0)     // Catch:{ Throwable -> 0x0042 }
            com.mob.tools.utils.FileLocker r1 = new com.mob.tools.utils.FileLocker     // Catch:{ all -> 0x003f }
            r1.<init>()     // Catch:{ all -> 0x003f }
            r1.setLockFile(r2)     // Catch:{ all -> 0x003f }
            boolean r2 = r1.lock(r3)     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x003c
            boolean r2 = r4.run(r1)     // Catch:{ all -> 0x003f }
            if (r2 != 0) goto L_0x003a
            r1.release()     // Catch:{ all -> 0x003f }
        L_0x003a:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            goto L_0x004a
        L_0x003c:
            r2 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return r2
        L_0x003f:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r2     // Catch:{ Throwable -> 0x0042 }
        L_0x0042:
            r2 = move-exception
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()
            r3.mo29787w((java.lang.Throwable) r2)
        L_0x004a:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2300e.m2468a(java.io.File, boolean, com.mob.commons.LockAction):boolean");
    }
}
