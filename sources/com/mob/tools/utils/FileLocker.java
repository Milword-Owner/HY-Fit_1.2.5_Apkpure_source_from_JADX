package com.mob.tools.utils;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;

public class FileLocker {
    private FileOutputStream fos;
    private FileLock lock;

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000b */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000f A[SYNTHETIC, Splitter:B:7:0x000f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setLockFile(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x000b }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x000b }
            r1.fos = r0     // Catch:{ Throwable -> 0x000b }
            goto L_0x0017
        L_0x0009:
            r2 = move-exception
            goto L_0x0019
        L_0x000b:
            java.io.FileOutputStream r2 = r1.fos     // Catch:{ all -> 0x0009 }
            if (r2 == 0) goto L_0x0017
            java.io.FileOutputStream r2 = r1.fos     // Catch:{ Throwable -> 0x0014 }
            r2.close()     // Catch:{ Throwable -> 0x0014 }
        L_0x0014:
            r2 = 0
            r1.fos = r2     // Catch:{ all -> 0x0009 }
        L_0x0017:
            monitor-exit(r1)
            return
        L_0x0019:
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.FileLocker.setLockFile(java.lang.String):void");
    }

    public synchronized boolean lock(boolean z) {
        return lock(z, z ? 1000 : 500, 16);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0025 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x0065 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x0070 */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0060 A[SYNTHETIC, Splitter:B:44:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x006b A[SYNTHETIC, Splitter:B:50:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean lock(boolean r9, long r10, long r12) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.io.FileOutputStream r0 = r8.fos     // Catch:{ all -> 0x0074 }
            r1 = 0
            if (r0 != 0) goto L_0x0008
            monitor-exit(r8)
            return r1
        L_0x0008:
            boolean r9 = r8.getLock(r9)     // Catch:{ Throwable -> 0x000e }
            monitor-exit(r8)
            return r9
        L_0x000e:
            r0 = move-exception
            r2 = 0
            int r4 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x0054
            boolean r4 = r0 instanceof java.nio.channels.OverlappingFileLockException     // Catch:{ all -> 0x0074 }
            if (r4 == 0) goto L_0x0054
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0074 }
            long r4 = r4 + r10
        L_0x001e:
            int r6 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x004d
            java.lang.Thread.sleep(r12)     // Catch:{ Throwable -> 0x0025 }
        L_0x0025:
            long r10 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Throwable -> 0x0030 }
            long r10 = r4 - r10
            boolean r9 = r8.getLock(r9)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x004e
        L_0x0030:
            r6 = move-exception
            boolean r6 = r6 instanceof java.nio.channels.OverlappingFileLockException     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x0043
            int r6 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r6 > 0) goto L_0x001e
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0074 }
            java.lang.String r7 = "OverlappingFileLockException and timeout"
            r6.mo29786w((java.lang.String) r7)     // Catch:{ all -> 0x0074 }
            goto L_0x001e
        L_0x0043:
            com.mob.tools.log.NLog r10 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0074 }
            r10.mo29787w((java.lang.Throwable) r0)     // Catch:{ all -> 0x0074 }
            r10 = -1
            goto L_0x001e
        L_0x004d:
            r9 = 0
        L_0x004e:
            int r12 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r12 <= 0) goto L_0x005b
            monitor-exit(r8)
            return r9
        L_0x0054:
            com.mob.tools.log.NLog r9 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x0074 }
            r9.mo29787w((java.lang.Throwable) r0)     // Catch:{ all -> 0x0074 }
        L_0x005b:
            java.nio.channels.FileLock r9 = r8.lock     // Catch:{ all -> 0x0074 }
            r10 = 0
            if (r9 == 0) goto L_0x0067
            java.nio.channels.FileLock r9 = r8.lock     // Catch:{ Throwable -> 0x0065 }
            r9.release()     // Catch:{ Throwable -> 0x0065 }
        L_0x0065:
            r8.lock = r10     // Catch:{ all -> 0x0074 }
        L_0x0067:
            java.io.FileOutputStream r9 = r8.fos     // Catch:{ all -> 0x0074 }
            if (r9 == 0) goto L_0x0072
            java.io.FileOutputStream r9 = r8.fos     // Catch:{ Throwable -> 0x0070 }
            r9.close()     // Catch:{ Throwable -> 0x0070 }
        L_0x0070:
            r8.fos = r10     // Catch:{ all -> 0x0074 }
        L_0x0072:
            monitor-exit(r8)
            return r1
        L_0x0074:
            r9 = move-exception
            monitor-exit(r8)
            goto L_0x0078
        L_0x0077:
            throw r9
        L_0x0078:
            goto L_0x0077
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.FileLocker.lock(boolean, long, long):boolean");
    }

    private boolean getLock(boolean z) throws Throwable {
        if (z) {
            this.lock = this.fos.getChannel().lock();
        } else {
            this.lock = this.fos.getChannel().tryLock();
        }
        return this.lock != null;
    }

    public synchronized void lock(Runnable runnable, boolean z) {
        if (lock(z) && runnable != null) {
            runnable.run();
        }
    }

    public synchronized void unlock() {
        if (this.lock != null) {
            try {
                this.lock.release();
            } catch (Throwable unused) {
            }
            this.lock = null;
        }
    }

    public synchronized void release() {
        if (this.fos != null) {
            unlock();
            try {
                this.fos.close();
            } catch (Throwable unused) {
            }
            this.fos = null;
        }
    }
}
