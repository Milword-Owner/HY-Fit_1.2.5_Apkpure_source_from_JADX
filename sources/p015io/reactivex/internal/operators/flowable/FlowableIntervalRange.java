package p015io.reactivex.internal.operators.flowable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p015io.reactivex.Flowable;
import p015io.reactivex.Scheduler;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.exceptions.MissingBackpressureException;
import p015io.reactivex.internal.disposables.DisposableHelper;
import p015io.reactivex.internal.schedulers.TrampolineScheduler;
import p015io.reactivex.internal.subscriptions.SubscriptionHelper;
import p015io.reactivex.internal.util.BackpressureHelper;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableIntervalRange */
public final class FlowableIntervalRange extends Flowable<Long> {
    final long end;
    final long initialDelay;
    final long period;
    final Scheduler scheduler;
    final long start;
    final TimeUnit unit;

    public FlowableIntervalRange(long j, long j2, long j3, long j4, TimeUnit timeUnit, Scheduler scheduler2) {
        this.initialDelay = j3;
        this.period = j4;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
        this.start = j;
        this.end = j2;
    }

    public void subscribeActual(Subscriber<? super Long> subscriber) {
        IntervalRangeSubscriber intervalRangeSubscriber = new IntervalRangeSubscriber(subscriber, this.start, this.end);
        subscriber.onSubscribe(intervalRangeSubscriber);
        Scheduler scheduler2 = this.scheduler;
        if (scheduler2 instanceof TrampolineScheduler) {
            Scheduler.Worker createWorker = scheduler2.createWorker();
            intervalRangeSubscriber.setResource(createWorker);
            createWorker.schedulePeriodically(intervalRangeSubscriber, this.initialDelay, this.period, this.unit);
            return;
        }
        intervalRangeSubscriber.setResource(scheduler2.schedulePeriodicallyDirect(intervalRangeSubscriber, this.initialDelay, this.period, this.unit));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableIntervalRange$IntervalRangeSubscriber */
    static final class IntervalRangeSubscriber extends AtomicLong implements Subscription, Runnable {
        private static final long serialVersionUID = -2809475196591179431L;
        final Subscriber<? super Long> actual;
        long count;
        final long end;
        final AtomicReference<Disposable> resource = new AtomicReference<>();

        IntervalRangeSubscriber(Subscriber<? super Long> subscriber, long j, long j2) {
            this.actual = subscriber;
            this.count = j;
            this.end = j2;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this, j);
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this.resource);
        }

        public void run() {
            if (this.resource.get() != DisposableHelper.DISPOSED) {
                long j = get();
                if (j != 0) {
                    long j2 = this.count;
                    this.actual.onNext(Long.valueOf(j2));
                    if (j2 == this.end) {
                        if (this.resource.get() != DisposableHelper.DISPOSED) {
                            this.actual.onComplete();
                        }
                        DisposableHelper.dispose(this.resource);
                        return;
                    }
                    this.count = j2 + 1;
                    if (j != LongCompanionObject.MAX_VALUE) {
                        decrementAndGet();
                        return;
                    }
                    return;
                }
                Subscriber<? super Long> subscriber = this.actual;
                subscriber.onError(new MissingBackpressureException("Can't deliver value " + this.count + " due to lack of requests"));
                DisposableHelper.dispose(this.resource);
            }
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.setOnce(this.resource, disposable);
        }
    }
}
