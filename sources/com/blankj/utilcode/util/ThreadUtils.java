package com.blankj.utilcode.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.CallSuper;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import java.lang.Thread;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class ThreadUtils {
    /* access modifiers changed from: private */
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public static final Map<Task, ExecutorService> TASK_POOL_MAP = new ConcurrentHashMap();
    private static final Timer TIMER = new Timer();
    private static final byte TYPE_CACHED = -2;
    private static final byte TYPE_CPU = -8;
    private static final byte TYPE_IO = -4;
    private static final Map<Integer, Map<Integer, ExecutorService>> TYPE_PRIORITY_POOLS = new HashMap();
    private static final byte TYPE_SINGLE = -1;
    private static Executor sDeliver;

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static Handler getMainHandler() {
        return HANDLER;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            HANDLER.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long j) {
        HANDLER.postDelayed(runnable, j);
    }

    public static ExecutorService getFixedPool(@IntRange(from = 1) int i) {
        return getPoolByTypeAndPriority(i);
    }

    public static ExecutorService getFixedPool(@IntRange(from = 1) int i, @IntRange(from = 1, mo670to = 10) int i2) {
        return getPoolByTypeAndPriority(i, i2);
    }

    public static ExecutorService getSinglePool() {
        return getPoolByTypeAndPriority(-1);
    }

    public static ExecutorService getSinglePool(@IntRange(from = 1, mo670to = 10) int i) {
        return getPoolByTypeAndPriority(-1, i);
    }

    public static ExecutorService getCachedPool() {
        return getPoolByTypeAndPriority(-2);
    }

    public static ExecutorService getCachedPool(@IntRange(from = 1, mo670to = 10) int i) {
        return getPoolByTypeAndPriority(-2, i);
    }

    public static ExecutorService getIoPool() {
        return getPoolByTypeAndPriority(-4);
    }

    public static ExecutorService getIoPool(@IntRange(from = 1, mo670to = 10) int i) {
        return getPoolByTypeAndPriority(-4, i);
    }

    public static ExecutorService getCpuPool() {
        return getPoolByTypeAndPriority(-8);
    }

    public static ExecutorService getCpuPool(@IntRange(from = 1, mo670to = 10) int i) {
        return getPoolByTypeAndPriority(-8, i);
    }

    public static <T> void executeByFixed(@IntRange(from = 1) int i, Task<T> task) {
        execute(getPoolByTypeAndPriority(i), task);
    }

    public static <T> void executeByFixed(@IntRange(from = 1) int i, Task<T> task, @IntRange(from = 1, mo670to = 10) int i2) {
        execute(getPoolByTypeAndPriority(i, i2), task);
    }

    public static <T> void executeByFixedWithDelay(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(i), task, j, timeUnit);
    }

    public static <T> void executeByFixedWithDelay(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i2) {
        executeWithDelay(getPoolByTypeAndPriority(i, i2), task, j, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(i), task, 0, j, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i2) {
        executeAtFixedRate(getPoolByTypeAndPriority(i, i2), task, 0, j, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(i), task, j, j2, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i2) {
        executeAtFixedRate(getPoolByTypeAndPriority(i, i2), task, j, j2, timeUnit);
    }

    public static <T> void executeBySingle(Task<T> task) {
        execute(getPoolByTypeAndPriority(-1), task);
    }

    public static <T> void executeBySingle(Task<T> task, @IntRange(from = 1, mo670to = 10) int i) {
        execute(getPoolByTypeAndPriority(-1, i), task);
    }

    public static <T> void executeBySingleWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-1), task, j, timeUnit);
    }

    public static <T> void executeBySingleWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeWithDelay(getPoolByTypeAndPriority(-1, i), task, j, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, 0, j, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, i), task, 0, j, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, j, j2, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByCached(Task<T> task) {
        execute(getPoolByTypeAndPriority(-2), task);
    }

    public static <T> void executeByCached(Task<T> task, @IntRange(from = 1, mo670to = 10) int i) {
        execute(getPoolByTypeAndPriority(-2, i), task);
    }

    public static <T> void executeByCachedWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-2), task, j, timeUnit);
    }

    public static <T> void executeByCachedWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeWithDelay(getPoolByTypeAndPriority(-2, i), task, j, timeUnit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, 0, j, timeUnit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, i), task, 0, j, timeUnit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, j, j2, timeUnit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByIo(Task<T> task) {
        execute(getPoolByTypeAndPriority(-4), task);
    }

    public static <T> void executeByIo(Task<T> task, @IntRange(from = 1, mo670to = 10) int i) {
        execute(getPoolByTypeAndPriority(-4, i), task);
    }

    public static <T> void executeByIoWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-4), task, j, timeUnit);
    }

    public static <T> void executeByIoWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeWithDelay(getPoolByTypeAndPriority(-4, i), task, j, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, 0, j, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, i), task, 0, j, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, j, j2, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByCpu(Task<T> task) {
        execute(getPoolByTypeAndPriority(-8), task);
    }

    public static <T> void executeByCpu(Task<T> task, @IntRange(from = 1, mo670to = 10) int i) {
        execute(getPoolByTypeAndPriority(-8, i), task);
    }

    public static <T> void executeByCpuWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-8), task, j, timeUnit);
    }

    public static <T> void executeByCpuWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeWithDelay(getPoolByTypeAndPriority(-8, i), task, j, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, 0, j, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, i), task, 0, j, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, j, j2, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, mo670to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByCustom(ExecutorService executorService, Task<T> task) {
        execute(executorService, task);
    }

    public static <T> void executeByCustomWithDelay(ExecutorService executorService, Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(executorService, task, j, timeUnit);
    }

    public static <T> void executeByCustomAtFixRate(ExecutorService executorService, Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(executorService, task, 0, j, timeUnit);
    }

    public static <T> void executeByCustomAtFixRate(ExecutorService executorService, Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(executorService, task, j, j2, timeUnit);
    }

    public static void cancel(Task task) {
        if (task != null) {
            task.cancel();
        }
    }

    public static void cancel(Task... taskArr) {
        if (taskArr != null && taskArr.length != 0) {
            for (Task task : taskArr) {
                if (task != null) {
                    task.cancel();
                }
            }
        }
    }

    public static void cancel(List<Task> list) {
        if (list != null && list.size() != 0) {
            for (Task next : list) {
                if (next != null) {
                    next.cancel();
                }
            }
        }
    }

    public static void cancel(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor4Util) {
            for (Map.Entry next : TASK_POOL_MAP.entrySet()) {
                if (next.getValue() == executorService) {
                    cancel((Task) next.getKey());
                }
            }
            return;
        }
        Log.e("ThreadUtils", "The executorService is not ThreadUtils's pool.");
    }

    public static void setDeliver(Executor executor) {
        sDeliver = executor;
    }

    private static <T> void execute(ExecutorService executorService, Task<T> task) {
        execute(executorService, task, 0, 0, (TimeUnit) null);
    }

    private static <T> void executeWithDelay(ExecutorService executorService, Task<T> task, long j, TimeUnit timeUnit) {
        execute(executorService, task, j, 0, timeUnit);
    }

    private static <T> void executeAtFixedRate(ExecutorService executorService, Task<T> task, long j, long j2, TimeUnit timeUnit) {
        execute(executorService, task, j, j2, timeUnit);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r7 != 0) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        if (r5 != 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        r3.execute(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        TIMER.schedule(new com.blankj.utilcode.util.ThreadUtils.C11781(), r9.toMillis(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        com.blankj.utilcode.util.ThreadUtils.Task.access$000(r4, true);
        TIMER.scheduleAtFixedRate(new com.blankj.utilcode.util.ThreadUtils.C11792(), r9.toMillis(r5), r9.toMillis(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> void execute(final java.util.concurrent.ExecutorService r3, final com.blankj.utilcode.util.ThreadUtils.Task<T> r4, long r5, long r7, java.util.concurrent.TimeUnit r9) {
        /*
            java.util.Map<com.blankj.utilcode.util.ThreadUtils$Task, java.util.concurrent.ExecutorService> r0 = TASK_POOL_MAP
            monitor-enter(r0)
            java.util.Map<com.blankj.utilcode.util.ThreadUtils$Task, java.util.concurrent.ExecutorService> r1 = TASK_POOL_MAP     // Catch:{ all -> 0x004f }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x004f }
            if (r1 == 0) goto L_0x0014
            java.lang.String r3 = "ThreadUtils"
            java.lang.String r4 = "Task can only be executed once."
            android.util.Log.e(r3, r4)     // Catch:{ all -> 0x004f }
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            return
        L_0x0014:
            java.util.Map<com.blankj.utilcode.util.ThreadUtils$Task, java.util.concurrent.ExecutorService> r1 = TASK_POOL_MAP     // Catch:{ all -> 0x004f }
            r1.put(r4, r3)     // Catch:{ all -> 0x004f }
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            r0 = 0
            int r2 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r2 != 0) goto L_0x0037
            int r7 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r7 != 0) goto L_0x0028
            r3.execute(r4)
            goto L_0x004e
        L_0x0028:
            com.blankj.utilcode.util.ThreadUtils$1 r7 = new com.blankj.utilcode.util.ThreadUtils$1
            r7.<init>(r3, r4)
            java.util.Timer r3 = TIMER
            long r4 = r9.toMillis(r5)
            r3.schedule(r7, r4)
            goto L_0x004e
        L_0x0037:
            r0 = 1
            r4.setSchedule(r0)
            com.blankj.utilcode.util.ThreadUtils$2 r0 = new com.blankj.utilcode.util.ThreadUtils$2
            r0.<init>(r3, r4)
            java.util.Timer r3 = TIMER
            long r5 = r9.toMillis(r5)
            long r7 = r9.toMillis(r7)
            r4 = r0
            r3.scheduleAtFixedRate(r4, r5, r7)
        L_0x004e:
            return
        L_0x004f:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ThreadUtils.execute(java.util.concurrent.ExecutorService, com.blankj.utilcode.util.ThreadUtils$Task, long, long, java.util.concurrent.TimeUnit):void");
    }

    private static ExecutorService getPoolByTypeAndPriority(int i) {
        return getPoolByTypeAndPriority(i, 5);
    }

    private static ExecutorService getPoolByTypeAndPriority(int i, int i2) {
        ExecutorService executorService;
        synchronized (TYPE_PRIORITY_POOLS) {
            Map map = TYPE_PRIORITY_POOLS.get(Integer.valueOf(i));
            if (map == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                executorService = ThreadPoolExecutor4Util.createPool(i, i2);
                concurrentHashMap.put(Integer.valueOf(i2), executorService);
                TYPE_PRIORITY_POOLS.put(Integer.valueOf(i), concurrentHashMap);
            } else {
                executorService = (ExecutorService) map.get(Integer.valueOf(i2));
                if (executorService == null) {
                    executorService = ThreadPoolExecutor4Util.createPool(i, i2);
                    map.put(Integer.valueOf(i2), executorService);
                }
            }
        }
        return executorService;
    }

    static final class ThreadPoolExecutor4Util extends ThreadPoolExecutor {
        private final AtomicInteger mSubmittedCount = new AtomicInteger();
        private LinkedBlockingQueue4Util mWorkQueue;

        /* access modifiers changed from: private */
        public static ExecutorService createPool(int i, int i2) {
            int i3 = i;
            int i4 = i2;
            if (i3 == -8) {
                return new ThreadPoolExecutor4Util(ThreadUtils.CPU_COUNT + 1, (ThreadUtils.CPU_COUNT * 2) + 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), new UtilsThreadFactory("cpu", i4));
            } else if (i3 == -4) {
                return new ThreadPoolExecutor4Util((ThreadUtils.CPU_COUNT * 2) + 1, (ThreadUtils.CPU_COUNT * 2) + 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("io", i4));
            } else {
                if (i3 == -2) {
                    return new ThreadPoolExecutor4Util(0, 128, 60, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), new UtilsThreadFactory("cached", i4));
                } else if (i3 == -1) {
                    return new ThreadPoolExecutor4Util(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("single", i4));
                } else {
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    LinkedBlockingQueue4Util linkedBlockingQueue4Util = new LinkedBlockingQueue4Util();
                    return new ThreadPoolExecutor4Util(i, i, 0, timeUnit, linkedBlockingQueue4Util, new UtilsThreadFactory("fixed(" + i3 + ")", i4));
                }
            }
        }

        ThreadPoolExecutor4Util(int i, int i2, long j, TimeUnit timeUnit, LinkedBlockingQueue4Util linkedBlockingQueue4Util, ThreadFactory threadFactory) {
            super(i, i2, j, timeUnit, linkedBlockingQueue4Util, threadFactory);
            ThreadPoolExecutor4Util unused = linkedBlockingQueue4Util.mPool = this;
            this.mWorkQueue = linkedBlockingQueue4Util;
        }

        private int getSubmittedCount() {
            return this.mSubmittedCount.get();
        }

        /* access modifiers changed from: protected */
        public void afterExecute(Runnable runnable, Throwable th) {
            this.mSubmittedCount.decrementAndGet();
            super.afterExecute(runnable, th);
        }

        public void execute(@NonNull Runnable runnable) {
            if (runnable == null) {
                throw new NullPointerException("Argument 'command' of type Runnable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            } else if (!isShutdown()) {
                this.mSubmittedCount.incrementAndGet();
                try {
                    super.execute(runnable);
                } catch (RejectedExecutionException unused) {
                    Log.e("ThreadUtils", "This will not happen!");
                    this.mWorkQueue.offer(runnable);
                } catch (Throwable unused2) {
                    this.mSubmittedCount.decrementAndGet();
                }
            }
        }
    }

    private static final class LinkedBlockingQueue4Util extends LinkedBlockingQueue<Runnable> {
        private int mCapacity = Integer.MAX_VALUE;
        /* access modifiers changed from: private */
        public volatile ThreadPoolExecutor4Util mPool;

        LinkedBlockingQueue4Util() {
        }

        LinkedBlockingQueue4Util(boolean z) {
            if (z) {
                this.mCapacity = 0;
            }
        }

        LinkedBlockingQueue4Util(int i) {
            this.mCapacity = i;
        }

        public boolean offer(@NonNull Runnable runnable) {
            if (runnable == null) {
                throw new NullPointerException("Argument 'runnable' of type Runnable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            } else if (this.mCapacity > size() || this.mPool == null || this.mPool.getPoolSize() >= this.mPool.getMaximumPoolSize()) {
                return super.offer(runnable);
            } else {
                return false;
            }
        }
    }

    static final class UtilsThreadFactory extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private static final long serialVersionUID = -9209200509960368598L;
        private final boolean isDaemon;
        private final String namePrefix;
        private final int priority;

        UtilsThreadFactory(String str, int i) {
            this(str, i, false);
        }

        UtilsThreadFactory(String str, int i, boolean z) {
            this.namePrefix = str + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
            this.priority = i;
            this.isDaemon = z;
        }

        public Thread newThread(@NonNull Runnable runnable) {
            if (runnable != null) {
                C11861 r0 = new Thread(runnable, this.namePrefix + getAndIncrement()) {
                    public void run() {
                        try {
                            super.run();
                        } catch (Throwable th) {
                            Log.e("ThreadUtils", "Request threw uncaught throwable", th);
                        }
                    }
                };
                r0.setDaemon(this.isDaemon);
                r0.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    public void uncaughtException(Thread thread, Throwable th) {
                        System.out.println(th);
                    }
                });
                r0.setPriority(this.priority);
                return r0;
            }
            throw new NullPointerException("Argument 'r' of type Runnable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static abstract class SimpleTask<T> extends Task<T> {
        public void onCancel() {
            Log.e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        public void onFail(Throwable th) {
            Log.e("ThreadUtils", "onFail: ", th);
        }
    }

    public static abstract class Task<T> implements Runnable {
        private static final int CANCELLED = 4;
        private static final int COMPLETING = 3;
        private static final int EXCEPTIONAL = 2;
        private static final int INTERRUPTED = 5;
        private static final int NEW = 0;
        private static final int RUNNING = 1;
        private static final int TIMEOUT = 6;
        private Executor deliver;
        private volatile boolean isSchedule;
        /* access modifiers changed from: private */
        public OnTimeoutListener mTimeoutListener;
        private long mTimeoutMillis;
        private Timer mTimer;
        private volatile Thread runner;
        private final AtomicInteger state = new AtomicInteger(0);

        public interface OnTimeoutListener {
            void onTimeout();
        }

        public abstract T doInBackground() throws Throwable;

        public abstract void onCancel();

        public abstract void onFail(Throwable th);

        public abstract void onSuccess(T t);

        public void run() {
            if (this.isSchedule) {
                if (this.runner == null) {
                    if (this.state.compareAndSet(0, 1)) {
                        this.runner = Thread.currentThread();
                        if (this.mTimeoutListener != null) {
                            Log.w("ThreadUtils", "Scheduled task doesn't support timeout.");
                        }
                    } else {
                        return;
                    }
                } else if (this.state.get() != 1) {
                    return;
                }
            } else if (this.state.compareAndSet(0, 1)) {
                this.runner = Thread.currentThread();
                if (this.mTimeoutListener != null) {
                    this.mTimer = new Timer();
                    this.mTimer.schedule(new TimerTask() {
                        public void run() {
                            if (!Task.this.isDone() && Task.this.mTimeoutListener != null) {
                                Task.this.timeout();
                                Task.this.mTimeoutListener.onTimeout();
                            }
                        }
                    }, this.mTimeoutMillis);
                }
            } else {
                return;
            }
            try {
                final Object doInBackground = doInBackground();
                if (this.isSchedule) {
                    if (this.state.get() == 1) {
                        getDeliver().execute(new Runnable() {
                            public void run() {
                                Task.this.onSuccess(doInBackground);
                            }
                        });
                    }
                } else if (this.state.compareAndSet(1, 3)) {
                    getDeliver().execute(new Runnable() {
                        public void run() {
                            Task.this.onSuccess(doInBackground);
                            Task.this.onDone();
                        }
                    });
                }
            } catch (InterruptedException unused) {
                this.state.compareAndSet(4, 5);
            } catch (Throwable th) {
                if (this.state.compareAndSet(1, 2)) {
                    getDeliver().execute(new Runnable() {
                        public void run() {
                            Task.this.onFail(th);
                            Task.this.onDone();
                        }
                    });
                }
            }
        }

        public void cancel() {
            cancel(true);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
            if (r3.runner == null) goto L_0x0020;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
            r3.runner.interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
            getDeliver().execute(new com.blankj.utilcode.util.ThreadUtils.Task.C11855(r3));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
            if (r4 == false) goto L_0x0020;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void cancel(boolean r4) {
            /*
                r3 = this;
                java.util.concurrent.atomic.AtomicInteger r0 = r3.state
                monitor-enter(r0)
                java.util.concurrent.atomic.AtomicInteger r1 = r3.state     // Catch:{ all -> 0x002d }
                int r1 = r1.get()     // Catch:{ all -> 0x002d }
                r2 = 1
                if (r1 <= r2) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return
            L_0x000e:
                java.util.concurrent.atomic.AtomicInteger r1 = r3.state     // Catch:{ all -> 0x002d }
                r2 = 4
                r1.set(r2)     // Catch:{ all -> 0x002d }
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                if (r4 == 0) goto L_0x0020
                java.lang.Thread r4 = r3.runner
                if (r4 == 0) goto L_0x0020
                java.lang.Thread r4 = r3.runner
                r4.interrupt()
            L_0x0020:
                java.util.concurrent.Executor r4 = r3.getDeliver()
                com.blankj.utilcode.util.ThreadUtils$Task$5 r0 = new com.blankj.utilcode.util.ThreadUtils$Task$5
                r0.<init>()
                r4.execute(r0)
                return
            L_0x002d:
                r4 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ThreadUtils.Task.cancel(boolean):void");
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
            if (r3.runner == null) goto L_0x001e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
            r3.runner.interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
            onDone();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void timeout() {
            /*
                r3 = this;
                java.util.concurrent.atomic.AtomicInteger r0 = r3.state
                monitor-enter(r0)
                java.util.concurrent.atomic.AtomicInteger r1 = r3.state     // Catch:{ all -> 0x0022 }
                int r1 = r1.get()     // Catch:{ all -> 0x0022 }
                r2 = 1
                if (r1 <= r2) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x0022 }
                return
            L_0x000e:
                java.util.concurrent.atomic.AtomicInteger r1 = r3.state     // Catch:{ all -> 0x0022 }
                r2 = 6
                r1.set(r2)     // Catch:{ all -> 0x0022 }
                monitor-exit(r0)     // Catch:{ all -> 0x0022 }
                java.lang.Thread r0 = r3.runner
                if (r0 == 0) goto L_0x001e
                java.lang.Thread r0 = r3.runner
                r0.interrupt()
            L_0x001e:
                r3.onDone()
                return
            L_0x0022:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0022 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ThreadUtils.Task.timeout():void");
        }

        public boolean isCanceled() {
            return this.state.get() >= 4;
        }

        public boolean isDone() {
            return this.state.get() > 1;
        }

        public Task<T> setDeliver(Executor executor) {
            this.deliver = executor;
            return this;
        }

        public Task<T> setTimeout(long j, OnTimeoutListener onTimeoutListener) {
            this.mTimeoutMillis = j;
            this.mTimeoutListener = onTimeoutListener;
            return this;
        }

        /* access modifiers changed from: private */
        public void setSchedule(boolean z) {
            this.isSchedule = z;
        }

        private Executor getDeliver() {
            Executor executor = this.deliver;
            return executor == null ? ThreadUtils.getGlobalDeliver() : executor;
        }

        /* access modifiers changed from: protected */
        @CallSuper
        public void onDone() {
            ThreadUtils.TASK_POOL_MAP.remove(this);
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer = null;
                this.mTimeoutListener = null;
            }
        }
    }

    public static class SyncValue<T> {
        private AtomicBoolean mFlag = new AtomicBoolean();
        private CountDownLatch mLatch = new CountDownLatch(1);
        private T mValue;

        public void setValue(T t) {
            if (this.mFlag.compareAndSet(false, true)) {
                this.mValue = t;
                this.mLatch.countDown();
            }
        }

        public T getValue() {
            if (!this.mFlag.get()) {
                try {
                    this.mLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.mValue;
        }
    }

    /* access modifiers changed from: private */
    public static Executor getGlobalDeliver() {
        if (sDeliver == null) {
            sDeliver = new Executor() {
                public void execute(@NonNull Runnable runnable) {
                    if (runnable != null) {
                        ThreadUtils.runOnUiThread(runnable);
                        return;
                    }
                    throw new NullPointerException("Argument 'command' of type Runnable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
                }
            };
        }
        return sDeliver;
    }
}
