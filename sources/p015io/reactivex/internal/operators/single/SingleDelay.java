package p015io.reactivex.internal.operators.single;

import java.util.concurrent.TimeUnit;
import p015io.reactivex.Scheduler;
import p015io.reactivex.Single;
import p015io.reactivex.SingleObserver;
import p015io.reactivex.SingleSource;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.internal.disposables.SequentialDisposable;

/* renamed from: io.reactivex.internal.operators.single.SingleDelay */
public final class SingleDelay<T> extends Single<T> {
    final boolean delayError;
    final Scheduler scheduler;
    final SingleSource<? extends T> source;
    final long time;
    final TimeUnit unit;

    public SingleDelay(SingleSource<? extends T> singleSource, long j, TimeUnit timeUnit, Scheduler scheduler2, boolean z) {
        this.source = singleSource;
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
        this.delayError = z;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        singleObserver.onSubscribe(sequentialDisposable);
        this.source.subscribe(new Delay(sequentialDisposable, singleObserver));
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleDelay$Delay */
    final class Delay implements SingleObserver<T> {

        /* renamed from: s */
        final SingleObserver<? super T> f2693s;

        /* renamed from: sd */
        private final SequentialDisposable f2694sd;

        Delay(SequentialDisposable sequentialDisposable, SingleObserver<? super T> singleObserver) {
            this.f2694sd = sequentialDisposable;
            this.f2693s = singleObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.f2694sd.replace(disposable);
        }

        public void onSuccess(T t) {
            this.f2694sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnSuccess(t), SingleDelay.this.time, SingleDelay.this.unit));
        }

        public void onError(Throwable th) {
            this.f2694sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnError(th), SingleDelay.this.delayError ? SingleDelay.this.time : 0, SingleDelay.this.unit));
        }

        /* renamed from: io.reactivex.internal.operators.single.SingleDelay$Delay$OnSuccess */
        final class OnSuccess implements Runnable {
            private final T value;

            OnSuccess(T t) {
                this.value = t;
            }

            public void run() {
                Delay.this.f2693s.onSuccess(this.value);
            }
        }

        /* renamed from: io.reactivex.internal.operators.single.SingleDelay$Delay$OnError */
        final class OnError implements Runnable {

            /* renamed from: e */
            private final Throwable f2695e;

            OnError(Throwable th) {
                this.f2695e = th;
            }

            public void run() {
                Delay.this.f2693s.onError(this.f2695e);
            }
        }
    }
}
