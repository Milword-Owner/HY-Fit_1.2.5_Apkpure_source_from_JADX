package p005cn.sharesdk.framework.loopshare.watermark;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: cn.sharesdk.framework.loopshare.watermark.f */
/* compiled from: ThreadPoolUtils */
public class C0771f {

    /* renamed from: a */
    private static int f396a = 5;

    /* renamed from: b */
    private static int f397b = 100;

    /* renamed from: c */
    private static int f398c = 10000;

    /* renamed from: d */
    private static BlockingQueue<Runnable> f399d = new ArrayBlockingQueue(10);

    /* renamed from: e */
    private static ThreadFactory f400e = new ThreadFactory() {

        /* renamed from: a */
        private final AtomicInteger f402a = new AtomicInteger();

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "myThreadPool thread:" + this.f402a.getAndIncrement());
        }
    };

    /* renamed from: f */
    private static ThreadPoolExecutor f401f = new ThreadPoolExecutor(f396a, f397b, (long) f398c, TimeUnit.SECONDS, f399d, f400e);

    /* renamed from: a */
    public static void m496a(Runnable runnable) {
        f401f.execute(runnable);
    }
}
