package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.huayu.tzc.utils.DateUtil;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class BitmapProcessor {
    private static final int CAPACITY = 3;
    private static final int MAX_CACHE_SIZE = 50;
    private static final int MAX_CACHE_TIME = 60000;
    private static final int MAX_REQ_TIME = 20000;
    private static final int MAX_SIZE = 100;
    private static final int OVERFLOW_SIZE = 120;
    private static final int SCAN_INTERVAL = 20000;
    /* access modifiers changed from: private */
    public static File cacheDir;
    /* access modifiers changed from: private */
    public static CachePool<String, SoftReference<Bitmap>> cachePool = new CachePool<>(50);
    private static ManagerThread manager;
    /* access modifiers changed from: private */
    public static ArrayList<ImageReq> netReqTPS = new ArrayList<>();
    /* access modifiers changed from: private */
    public static ArrayList<ImageReq> reqList = new ArrayList<>();
    /* access modifiers changed from: private */
    public static NetworkHelper.NetworkTimeOut timeout = new NetworkHelper.NetworkTimeOut();
    /* access modifiers changed from: private */
    public static boolean work;
    /* access modifiers changed from: private */
    public static WorkerThread[] workerList = new WorkerThread[3];

    public interface BitmapCallback {
        void onImageGot(String str, Bitmap bitmap);
    }

    static {
        NetworkHelper.NetworkTimeOut networkTimeOut = timeout;
        networkTimeOut.connectionTimeout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
        networkTimeOut.readTimout = 20000 - networkTimeOut.connectionTimeout;
    }

    public static synchronized void prepare(Context context) {
        synchronized (BitmapProcessor.class) {
            cacheDir = new File(ResHelper.getImageCachePath(context));
        }
    }

    public static synchronized void start() {
        synchronized (BitmapProcessor.class) {
            if (!work) {
                work = true;
                manager = new ManagerThread();
            }
        }
    }

    public static synchronized void stop() {
        synchronized (BitmapProcessor.class) {
            if (work) {
                work = false;
                synchronized (reqList) {
                    reqList.clear();
                    cachePool.clear();
                }
                manager.quit();
            }
        }
    }

    public static synchronized void process(String str, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, (BitmapDesiredOptions) null, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, bitmapDesiredOptions, true, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, boolean z, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, bitmapDesiredOptions, z, true, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, boolean z, boolean z2, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, bitmapDesiredOptions, z, z2, 0, bitmapCallback);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r1 = new com.mob.tools.gui.BitmapProcessor.ImageReq();
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$002(r1, r8);
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$102(r1, r9);
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$302(r1, r10);
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$402(r1, r12);
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$502(r1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0075, code lost:
        if (r14 == null) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0077, code lost:
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$200(r1).add(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007e, code lost:
        r8 = reqList;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0080, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        reqList.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x008e, code lost:
        if (reqList.size() <= 120) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0098, code lost:
        if (reqList.size() <= 100) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009a, code lost:
        reqList.remove(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a0, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        start();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a5, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void process(java.lang.String r8, com.mob.tools.gui.BitmapProcessor.BitmapDesiredOptions r9, boolean r10, boolean r11, long r12, com.mob.tools.gui.BitmapProcessor.BitmapCallback r14) {
        /*
            java.lang.Class<com.mob.tools.gui.BitmapProcessor> r0 = com.mob.tools.gui.BitmapProcessor.class
            monitor-enter(r0)
            if (r8 != 0) goto L_0x0007
            monitor-exit(r0)
            return
        L_0x0007:
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r1 = reqList     // Catch:{ all -> 0x00ac }
            monitor-enter(r1)     // Catch:{ all -> 0x00ac }
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r2 = reqList     // Catch:{ all -> 0x00a9 }
            int r2 = r2.size()     // Catch:{ all -> 0x00a9 }
            r3 = 0
            r4 = 0
        L_0x0012:
            if (r4 >= r2) goto L_0x0060
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r5 = reqList     // Catch:{ all -> 0x00a9 }
            java.lang.Object r5 = r5.get(r4)     // Catch:{ all -> 0x00a9 }
            com.mob.tools.gui.BitmapProcessor$ImageReq r5 = (com.mob.tools.gui.BitmapProcessor.ImageReq) r5     // Catch:{ all -> 0x00a9 }
            java.lang.String r6 = r5.url     // Catch:{ all -> 0x00a9 }
            boolean r6 = r6.equals(r8)     // Catch:{ all -> 0x00a9 }
            com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r7 = r5.bitmapDesiredOptions     // Catch:{ all -> 0x00a9 }
            if (r7 != 0) goto L_0x002c
            if (r9 == 0) goto L_0x003c
        L_0x002c:
            com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r7 = r5.bitmapDesiredOptions     // Catch:{ all -> 0x00a9 }
            if (r7 == 0) goto L_0x003e
            com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r7 = r5.bitmapDesiredOptions     // Catch:{ all -> 0x00a9 }
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x00a9 }
            if (r7 == 0) goto L_0x003e
        L_0x003c:
            r7 = 1
            goto L_0x003f
        L_0x003e:
            r7 = 0
        L_0x003f:
            if (r6 == 0) goto L_0x005d
            if (r7 == 0) goto L_0x005d
            if (r14 == 0) goto L_0x0057
            java.util.ArrayList r8 = r5.callbacks     // Catch:{ all -> 0x00a9 }
            int r8 = r8.indexOf(r14)     // Catch:{ all -> 0x00a9 }
            r9 = -1
            if (r8 != r9) goto L_0x0057
            java.util.ArrayList r8 = r5.callbacks     // Catch:{ all -> 0x00a9 }
            r8.add(r14)     // Catch:{ all -> 0x00a9 }
        L_0x0057:
            start()     // Catch:{ all -> 0x00a9 }
            monitor-exit(r1)     // Catch:{ all -> 0x00a9 }
            monitor-exit(r0)
            return
        L_0x005d:
            int r4 = r4 + 1
            goto L_0x0012
        L_0x0060:
            monitor-exit(r1)     // Catch:{ all -> 0x00a9 }
            com.mob.tools.gui.BitmapProcessor$ImageReq r1 = new com.mob.tools.gui.BitmapProcessor$ImageReq     // Catch:{ all -> 0x00ac }
            r1.<init>()     // Catch:{ all -> 0x00ac }
            java.lang.String unused = r1.url = r8     // Catch:{ all -> 0x00ac }
            com.mob.tools.gui.BitmapProcessor.BitmapDesiredOptions unused = r1.bitmapDesiredOptions = r9     // Catch:{ all -> 0x00ac }
            boolean unused = r1.useRamCache = r10     // Catch:{ all -> 0x00ac }
            long unused = r1.diskCacheTime = r12     // Catch:{ all -> 0x00ac }
            boolean unused = r1.useDiskCache = r11     // Catch:{ all -> 0x00ac }
            if (r14 == 0) goto L_0x007e
            java.util.ArrayList r8 = r1.callbacks     // Catch:{ all -> 0x00ac }
            r8.add(r14)     // Catch:{ all -> 0x00ac }
        L_0x007e:
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r8 = reqList     // Catch:{ all -> 0x00ac }
            monitor-enter(r8)     // Catch:{ all -> 0x00ac }
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r9 = reqList     // Catch:{ all -> 0x00a6 }
            r9.add(r1)     // Catch:{ all -> 0x00a6 }
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r9 = reqList     // Catch:{ all -> 0x00a6 }
            int r9 = r9.size()     // Catch:{ all -> 0x00a6 }
            r10 = 120(0x78, float:1.68E-43)
            if (r9 <= r10) goto L_0x00a0
        L_0x0090:
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r9 = reqList     // Catch:{ all -> 0x00a6 }
            int r9 = r9.size()     // Catch:{ all -> 0x00a6 }
            r10 = 100
            if (r9 <= r10) goto L_0x00a0
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r9 = reqList     // Catch:{ all -> 0x00a6 }
            r9.remove(r3)     // Catch:{ all -> 0x00a6 }
            goto L_0x0090
        L_0x00a0:
            monitor-exit(r8)     // Catch:{ all -> 0x00a6 }
            start()     // Catch:{ all -> 0x00ac }
            monitor-exit(r0)
            return
        L_0x00a6:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x00a6 }
            throw r9     // Catch:{ all -> 0x00ac }
        L_0x00a9:
            r8 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00a9 }
            throw r8     // Catch:{ all -> 0x00ac }
        L_0x00ac:
            r8 = move-exception
            monitor-exit(r0)
            goto L_0x00b0
        L_0x00af:
            throw r8
        L_0x00b0:
            goto L_0x00af
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.BitmapProcessor.process(java.lang.String, com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions, boolean, boolean, long, com.mob.tools.gui.BitmapProcessor$BitmapCallback):void");
    }

    /* access modifiers changed from: private */
    public static String getCacheKey(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        if (bitmapDesiredOptions == null) {
            return str;
        }
        return str + bitmapDesiredOptions.toString();
    }

    public static Bitmap getBitmapFromCache(String str) {
        return getBitmapFromCache(str, (BitmapDesiredOptions) null);
    }

    public static Bitmap getBitmapFromCache(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        CachePool<String, SoftReference<Bitmap>> cachePool2 = cachePool;
        if (cachePool2 == null || str == null || cachePool2.get(getCacheKey(str, bitmapDesiredOptions)) == null) {
            return null;
        }
        return (Bitmap) cachePool.get(getCacheKey(str, bitmapDesiredOptions)).get();
    }

    public static void removeBitmapFromRamCache(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        CachePool<String, SoftReference<Bitmap>> cachePool2 = cachePool;
        if (cachePool2 != null) {
            cachePool2.put(getCacheKey(str, bitmapDesiredOptions), null);
        }
    }

    public static void deleteCachedFile(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        removeBitmapFromRamCache(str, bitmapDesiredOptions);
        try {
            new File(cacheDir, Data.MD5(str)).delete();
        } catch (Throwable unused) {
        }
    }

    public static void deleteCacheDir(boolean z) {
        if (z) {
            deleteCacheDir();
        } else {
            new Thread() {
                public void run() {
                    BitmapProcessor.deleteCacheDir();
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public static synchronized void deleteCacheDir() {
        synchronized (BitmapProcessor.class) {
            File file = new File(cacheDir.getPath());
            if (file.isDirectory()) {
                String[] list = file.list();
                for (String file2 : list) {
                    new File(file, file2).delete();
                }
            }
        }
    }

    public static long getCacheSizeInByte() {
        long j = 0;
        for (File length : new File(cacheDir.getPath()).listFiles()) {
            j += length.length();
        }
        return j;
    }

    public static String getCacheSizeText() {
        float cacheSizeInByte = (float) getCacheSizeInByte();
        if (cacheSizeInByte < 1024.0f) {
            return String.format(Locale.CHINA, "%.02f", new Object[]{Float.valueOf(cacheSizeInByte)}) + " B";
        }
        float f = cacheSizeInByte / 1024.0f;
        if (f < 1000.0f) {
            return String.format(Locale.CHINA, "%.02f", new Object[]{Float.valueOf(f)}) + " KB";
        }
        return String.format(Locale.CHINA, "%.02f", new Object[]{Float.valueOf(f / 1204.0f)}) + " MB";
    }

    private static class ManagerThread implements Handler.Callback {
        private Handler handler = MobHandlerThread.newHandler((Runnable) new Runnable() {
            public void run() {
                int i = 0;
                while (i < BitmapProcessor.workerList.length) {
                    if (BitmapProcessor.workerList[i] == null) {
                        BitmapProcessor.workerList[i] = new WorkerThread();
                        WorkerThread workerThread = BitmapProcessor.workerList[i];
                        workerThread.setName("worker " + i);
                        boolean unused = BitmapProcessor.workerList[i].localType = i == 0;
                        BitmapProcessor.workerList[i].start();
                    }
                    i++;
                }
            }
        }, (Handler.Callback) this);

        public ManagerThread() {
            this.handler.sendEmptyMessageDelayed(1, 20000);
        }

        public boolean handleMessage(Message message) {
            if (BitmapProcessor.cachePool != null) {
                BitmapProcessor.cachePool.trimBeforeTime(System.currentTimeMillis() - DateUtil.MINUTE_MILL_SECONDS);
            }
            int size = BitmapProcessor.cachePool == null ? 0 : BitmapProcessor.cachePool.size();
            NLog instance = MobLog.getInstance();
            instance.mo29768d(">>>> BitmapProcessor.cachePool: " + size, new Object[0]);
            int size2 = BitmapProcessor.reqList == null ? 0 : BitmapProcessor.reqList.size();
            NLog instance2 = MobLog.getInstance();
            instance2.mo29768d(">>>> BitmapProcessor.reqList: " + size2, new Object[0]);
            if (BitmapProcessor.work) {
                this.handler.sendEmptyMessageDelayed(1, 20000);
            }
            return false;
        }

        public void quit() {
            this.handler.removeMessages(1);
            this.handler.getLooper().quit();
            for (int i = 0; i < BitmapProcessor.workerList.length; i++) {
                if (BitmapProcessor.workerList[i] != null) {
                    BitmapProcessor.workerList[i].interrupt();
                    BitmapProcessor.workerList[i] = null;
                }
            }
        }
    }

    private static class WorkerThread extends Thread {
        /* access modifiers changed from: private */
        public ImageReq curReq;
        /* access modifiers changed from: private */
        public boolean localType;

        private WorkerThread() {
        }

        public void run() {
            while (BitmapProcessor.work) {
                try {
                    if (this.localType) {
                        doLocalTask();
                    } else {
                        doNetworkTask();
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().mo29787w(th);
                }
            }
        }

        private void doLocalTask() throws Throwable {
            Bitmap bitmap;
            ImageReq imageReq;
            SoftReference softReference;
            synchronized (BitmapProcessor.reqList) {
                bitmap = null;
                imageReq = BitmapProcessor.reqList.size() > 0 ? (ImageReq) BitmapProcessor.reqList.remove(0) : null;
            }
            if (imageReq != null) {
                if (imageReq.useRamCache && (softReference = (SoftReference) BitmapProcessor.cachePool.get(BitmapProcessor.getCacheKey(imageReq.url, imageReq.bitmapDesiredOptions))) != null) {
                    bitmap = (Bitmap) softReference.get();
                }
                if (bitmap != null) {
                    this.curReq = imageReq;
                    WorkerThread unused = this.curReq.worker = this;
                    imageReq.throwComplete(bitmap);
                } else if (!imageReq.useDiskCache || BitmapProcessor.cacheDir == null || !new File(BitmapProcessor.cacheDir, Data.MD5(imageReq.url)).exists()) {
                    synchronized (BitmapProcessor.reqList) {
                        if (BitmapProcessor.netReqTPS.size() > 100) {
                            synchronized (BitmapProcessor.reqList) {
                                while (BitmapProcessor.reqList.size() > 0) {
                                    BitmapProcessor.reqList.remove(0);
                                }
                            }
                            BitmapProcessor.netReqTPS.remove(0);
                        }
                    }
                    BitmapProcessor.netReqTPS.add(imageReq);
                } else {
                    doTask(imageReq);
                }
            } else {
                try {
                    Thread.sleep(30);
                } catch (Throwable unused2) {
                }
            }
        }

        private void doNetworkTask() throws Throwable {
            Bitmap bitmap;
            ImageReq imageReq;
            SoftReference softReference;
            synchronized (BitmapProcessor.netReqTPS) {
                bitmap = null;
                imageReq = BitmapProcessor.netReqTPS.size() > 0 ? (ImageReq) BitmapProcessor.netReqTPS.remove(0) : null;
            }
            if (imageReq == null) {
                synchronized (BitmapProcessor.reqList) {
                    if (BitmapProcessor.reqList.size() > 0) {
                        imageReq = (ImageReq) BitmapProcessor.reqList.remove(0);
                    }
                }
            }
            if (imageReq != null) {
                if (imageReq.useRamCache && (softReference = (SoftReference) BitmapProcessor.cachePool.get(BitmapProcessor.getCacheKey(imageReq.url, imageReq.bitmapDesiredOptions))) != null) {
                    bitmap = (Bitmap) softReference.get();
                }
                if (bitmap != null) {
                    this.curReq = imageReq;
                    WorkerThread unused = this.curReq.worker = this;
                    imageReq.throwComplete(bitmap);
                    return;
                }
                doTask(imageReq);
                return;
            }
            try {
                Thread.sleep(30);
            } catch (Throwable unused2) {
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x009c A[Catch:{ Throwable -> 0x00da }] */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x00be A[Catch:{ Throwable -> 0x00da }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void doTask(final com.mob.tools.gui.BitmapProcessor.ImageReq r11) throws java.lang.Throwable {
            /*
                r10 = this;
                r0 = 0
                r10.curReq = r11     // Catch:{ Throwable -> 0x00da }
                com.mob.tools.gui.BitmapProcessor$ImageReq r1 = r10.curReq     // Catch:{ Throwable -> 0x00da }
                com.mob.tools.gui.BitmapProcessor.WorkerThread unused = r1.worker = r10     // Catch:{ Throwable -> 0x00da }
                java.lang.String r1 = r11.url     // Catch:{ Throwable -> 0x00da }
                java.lang.String r1 = com.mob.tools.utils.Data.MD5((java.lang.String) r1)     // Catch:{ Throwable -> 0x00da }
                java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00da }
                java.io.File r3 = com.mob.tools.gui.BitmapProcessor.cacheDir     // Catch:{ Throwable -> 0x00da }
                r2.<init>(r3, r1)     // Catch:{ Throwable -> 0x00da }
                boolean r3 = r11.useDiskCache     // Catch:{ Throwable -> 0x00da }
                if (r3 == 0) goto L_0x0043
                long r3 = r11.diskCacheTime     // Catch:{ Throwable -> 0x00da }
                r5 = 0
                int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r7 <= 0) goto L_0x0043
                boolean r3 = r2.exists()     // Catch:{ Throwable -> 0x00da }
                if (r3 == 0) goto L_0x0043
                long r3 = r2.lastModified()     // Catch:{ Throwable -> 0x00da }
                long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00da }
                long r7 = r11.diskCacheTime     // Catch:{ Throwable -> 0x00da }
                long r3 = r3 + r7
                int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r7 >= 0) goto L_0x0043
                r2.delete()     // Catch:{ Throwable -> 0x00da }
            L_0x0043:
                boolean r3 = r11.useDiskCache     // Catch:{ Throwable -> 0x00da }
                if (r3 == 0) goto L_0x00c4
                java.io.File r3 = com.mob.tools.gui.BitmapProcessor.cacheDir     // Catch:{ Throwable -> 0x00da }
                if (r3 == 0) goto L_0x00c4
                boolean r3 = r2.exists()     // Catch:{ Throwable -> 0x00da }
                if (r3 == 0) goto L_0x00c4
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r3 = r11.bitmapDesiredOptions     // Catch:{ Throwable -> 0x00da }
                if (r3 == 0) goto L_0x0092
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r3 = r11.bitmapDesiredOptions     // Catch:{ Throwable -> 0x00da }
                java.lang.String r4 = ""
                boolean r3 = r3.equals(r4)     // Catch:{ Throwable -> 0x00da }
                if (r3 == 0) goto L_0x0068
                goto L_0x0092
            L_0x0068:
                java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00da }
                java.io.File r3 = com.mob.tools.gui.BitmapProcessor.cacheDir     // Catch:{ Throwable -> 0x00da }
                r2.<init>(r3, r1)     // Catch:{ Throwable -> 0x00da }
                java.lang.String r4 = r2.getAbsolutePath()     // Catch:{ Throwable -> 0x00da }
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = r11.bitmapDesiredOptions     // Catch:{ Throwable -> 0x00da }
                int r5 = r1.desiredWidth     // Catch:{ Throwable -> 0x00da }
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = r11.bitmapDesiredOptions     // Catch:{ Throwable -> 0x00da }
                int r6 = r1.desiredHeight     // Catch:{ Throwable -> 0x00da }
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = r11.bitmapDesiredOptions     // Catch:{ Throwable -> 0x00da }
                int r7 = r1.quality     // Catch:{ Throwable -> 0x00da }
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = r11.bitmapDesiredOptions     // Catch:{ Throwable -> 0x00da }
                long r8 = r1.maxBytes     // Catch:{ Throwable -> 0x00da }
                android.graphics.Bitmap r1 = com.mob.tools.utils.BitmapHelper.getBitmapByCompressQuality(r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x00da }
                goto L_0x009a
            L_0x0092:
                java.lang.String r1 = r2.getAbsolutePath()     // Catch:{ Throwable -> 0x00da }
                android.graphics.Bitmap r1 = com.mob.tools.utils.BitmapHelper.getBitmap(r1)     // Catch:{ Throwable -> 0x00da }
            L_0x009a:
                if (r1 == 0) goto L_0x00be
                boolean r2 = r11.useRamCache     // Catch:{ Throwable -> 0x00da }
                if (r2 == 0) goto L_0x00ba
                com.mob.tools.gui.CachePool r2 = com.mob.tools.gui.BitmapProcessor.cachePool     // Catch:{ Throwable -> 0x00da }
                java.lang.String r3 = r11.url     // Catch:{ Throwable -> 0x00da }
                com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r4 = r11.bitmapDesiredOptions     // Catch:{ Throwable -> 0x00da }
                java.lang.String r3 = com.mob.tools.gui.BitmapProcessor.getCacheKey(r3, r4)     // Catch:{ Throwable -> 0x00da }
                java.lang.ref.SoftReference r4 = new java.lang.ref.SoftReference     // Catch:{ Throwable -> 0x00da }
                r4.<init>(r1)     // Catch:{ Throwable -> 0x00da }
                r2.put(r3, r4)     // Catch:{ Throwable -> 0x00da }
            L_0x00ba:
                r11.throwComplete(r1)     // Catch:{ Throwable -> 0x00da }
                goto L_0x00c1
            L_0x00be:
                r11.throwError()     // Catch:{ Throwable -> 0x00da }
            L_0x00c1:
                r10.curReq = r0     // Catch:{ Throwable -> 0x00da }
                goto L_0x00e7
            L_0x00c4:
                com.mob.tools.network.NetworkHelper r2 = new com.mob.tools.network.NetworkHelper     // Catch:{ Throwable -> 0x00da }
                r2.<init>()     // Catch:{ Throwable -> 0x00da }
                java.lang.String r3 = r11.url     // Catch:{ Throwable -> 0x00da }
                com.mob.tools.gui.BitmapProcessor$WorkerThread$1 r4 = new com.mob.tools.gui.BitmapProcessor$WorkerThread$1     // Catch:{ Throwable -> 0x00da }
                r4.<init>(r1, r11)     // Catch:{ Throwable -> 0x00da }
                com.mob.tools.network.NetworkHelper$NetworkTimeOut r1 = com.mob.tools.gui.BitmapProcessor.timeout     // Catch:{ Throwable -> 0x00da }
                r2.rawGet((java.lang.String) r3, (com.mob.tools.network.RawNetworkCallback) r4, (com.mob.tools.network.NetworkHelper.NetworkTimeOut) r1)     // Catch:{ Throwable -> 0x00da }
                goto L_0x00e7
            L_0x00da:
                r1 = move-exception
                com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
                r2.mo29787w((java.lang.Throwable) r1)
                r11.throwError()
                r10.curReq = r0
            L_0x00e7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.BitmapProcessor.WorkerThread.doTask(com.mob.tools.gui.BitmapProcessor$ImageReq):void");
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Can't wrap try/catch for region: R(9:21|20|23|24|(1:26)|27|28|17|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
            r6 = th;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0047 */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x004d A[Catch:{ all -> 0x0045 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void saveFile(java.io.InputStream r5, java.io.File r6) {
            /*
                r4 = this;
                r0 = 0
                boolean r1 = r6.exists()     // Catch:{ Throwable -> 0x0047 }
                if (r1 == 0) goto L_0x000a
                r6.delete()     // Catch:{ Throwable -> 0x0047 }
            L_0x000a:
                java.io.File r1 = r6.getParentFile()     // Catch:{ Throwable -> 0x0047 }
                boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x0047 }
                if (r1 != 0) goto L_0x001b
                java.io.File r1 = r6.getParentFile()     // Catch:{ Throwable -> 0x0047 }
                r1.mkdirs()     // Catch:{ Throwable -> 0x0047 }
            L_0x001b:
                r6.createNewFile()     // Catch:{ Throwable -> 0x0047 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0047 }
                r1.<init>(r6)     // Catch:{ Throwable -> 0x0047 }
                r0 = 256(0x100, float:3.59E-43)
                byte[] r0 = new byte[r0]     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
                int r2 = r5.read(r0)     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
            L_0x002b:
                if (r2 <= 0) goto L_0x0036
                r3 = 0
                r1.write(r0, r3, r2)     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
                int r2 = r5.read(r0)     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
                goto L_0x002b
            L_0x0036:
                r1.flush()     // Catch:{ Throwable -> 0x0043, all -> 0x0040 }
                r1.close()     // Catch:{ Throwable -> 0x0054 }
            L_0x003c:
                r5.close()     // Catch:{ Throwable -> 0x0054 }
                goto L_0x0054
            L_0x0040:
                r6 = move-exception
                r0 = r1
                goto L_0x0055
            L_0x0043:
                r0 = r1
                goto L_0x0047
            L_0x0045:
                r6 = move-exception
                goto L_0x0055
            L_0x0047:
                boolean r1 = r6.exists()     // Catch:{ all -> 0x0045 }
                if (r1 == 0) goto L_0x0050
                r6.delete()     // Catch:{ all -> 0x0045 }
            L_0x0050:
                r0.close()     // Catch:{ Throwable -> 0x0054 }
                goto L_0x003c
            L_0x0054:
                return
            L_0x0055:
                r0.close()     // Catch:{ Throwable -> 0x005b }
                r5.close()     // Catch:{ Throwable -> 0x005b }
            L_0x005b:
                goto L_0x005d
            L_0x005c:
                throw r6
            L_0x005d:
                goto L_0x005c
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.BitmapProcessor.WorkerThread.saveFile(java.io.InputStream, java.io.File):void");
        }

        public void interrupt() {
            try {
                super.interrupt();
            } catch (Throwable unused) {
            }
        }
    }

    private static class PatchInputStream extends FilterInputStream {

        /* renamed from: in */
        InputStream f2346in;

        protected PatchInputStream(InputStream inputStream) {
            super(inputStream);
            this.f2346in = inputStream;
        }

        public long skip(long j) throws IOException {
            long j2 = 0;
            while (j2 < j) {
                long skip = this.f2346in.skip(j - j2);
                if (skip == 0) {
                    break;
                }
                j2 += skip;
            }
            return j2;
        }
    }

    public static class ImageReq {
        /* access modifiers changed from: private */
        public BitmapDesiredOptions bitmapDesiredOptions;
        /* access modifiers changed from: private */
        public ArrayList<BitmapCallback> callbacks = new ArrayList<>();
        /* access modifiers changed from: private */
        public long diskCacheTime = 0;
        private long reqTime = System.currentTimeMillis();
        /* access modifiers changed from: private */
        public String url;
        /* access modifiers changed from: private */
        public boolean useDiskCache = true;
        /* access modifiers changed from: private */
        public boolean useRamCache = true;
        /* access modifiers changed from: private */
        public WorkerThread worker;

        /* access modifiers changed from: private */
        public void throwComplete(Bitmap bitmap) {
            Iterator<BitmapCallback> it = this.callbacks.iterator();
            while (it.hasNext()) {
                it.next().onImageGot(this.url, bitmap);
            }
            this.callbacks.clear();
        }

        /* access modifiers changed from: private */
        public void throwError() {
            Iterator<BitmapCallback> it = this.callbacks.iterator();
            while (it.hasNext()) {
                it.next().onImageGot(this.url, (Bitmap) null);
            }
            this.callbacks.clear();
        }

        public String toString() {
            return "url=" + this.url + "time=" + this.reqTime + "worker=" + this.worker.getName() + " (" + this.worker.getId() + "";
        }
    }

    public static class BitmapDesiredOptions {
        public int desiredHeight = 0;
        public int desiredWidth = 0;
        public long maxBytes = 0;
        public int quality = 0;

        public boolean equals(Object obj) {
            return super.equals(obj) || (obj != null && obj.toString().equals(toString()));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            int i = this.desiredWidth;
            if (i > 0) {
                sb.append(i);
            }
            int i2 = this.desiredHeight;
            if (i2 > 0) {
                sb.append(i2);
            }
            long j = this.maxBytes;
            if (j > 0) {
                sb.append(j);
            }
            int i3 = this.quality;
            if (i3 > 0) {
                sb.append(i3);
            }
            return sb.toString();
        }
    }
}
