package com.mob.tools;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;

public class MobHandlerThread extends Thread {
    private Looper looper;
    private int priority;
    private int tid;

    /* access modifiers changed from: protected */
    public void onLooperPrepared() {
    }

    /* access modifiers changed from: protected */
    public void onLooperPrepared(Looper looper2) {
    }

    @Deprecated
    public void realRun() {
    }

    public MobHandlerThread() {
        this.tid = -1;
        this.priority = 0;
    }

    public MobHandlerThread(int i) {
        this.tid = -1;
        this.priority = i;
    }

    public void run() {
        try {
            realRun();
            this.tid = Process.myTid();
            Looper.prepare();
            synchronized (this) {
                this.looper = Looper.myLooper();
                notifyAll();
            }
            Process.setThreadPriority(this.priority);
            onLooperPrepared(this.looper);
            onLooperPrepared();
            Looper.loop();
            this.tid = -1;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0009 A[LOOP:0: B:4:0x0009->B:19:0x0009, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Looper getLooper() {
        /*
            r1 = this;
            boolean r0 = r1.isAlive()
            if (r0 != 0) goto L_0x0008
            r0 = 0
            return r0
        L_0x0008:
            monitor-enter(r1)
        L_0x0009:
            boolean r0 = r1.isAlive()     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0017
            android.os.Looper r0 = r1.looper     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x0017
            r1.wait()     // Catch:{ Throwable -> 0x0009 }
            goto L_0x0009
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            android.os.Looper r0 = r1.looper
            return r0
        L_0x001b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001e:
            throw r0
        L_0x001f:
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.MobHandlerThread.getLooper():android.os.Looper");
    }

    public boolean quit() {
        Looper looper2 = getLooper();
        if (looper2 == null) {
            return false;
        }
        looper2.quit();
        return true;
    }

    public int getThreadId() {
        return this.tid;
    }

    public static Handler newHandler(Handler.Callback callback) {
        return newHandler((String) null, (Runnable) null, callback);
    }

    public static Handler newHandler(String str, Handler.Callback callback) {
        return newHandler(str, (Runnable) null, callback);
    }

    public static Handler newHandler(Runnable runnable, Handler.Callback callback) {
        return newHandler((String) null, runnable, callback);
    }

    public static Handler newHandler(String str, final Runnable runnable, final Handler.Callback callback) {
        final Handler[] handlerArr = new Handler[1];
        C24181 r0 = new MobHandlerThread() {
            public void run() {
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
                MobHandlerThread.super.run();
            }

            /* access modifiers changed from: protected */
            public void onLooperPrepared(Looper looper) {
                synchronized (handlerArr) {
                    handlerArr[0] = new Handler(looper, callback);
                    handlerArr.notifyAll();
                }
            }
        };
        synchronized (handlerArr) {
            try {
                r0.start();
                handlerArr.wait();
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
        return handlerArr[0];
    }
}
