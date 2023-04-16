package p015io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p015io.reactivex.Flowable;
import p015io.reactivex.Observable;
import p015io.reactivex.Observer;
import p015io.reactivex.disposables.Disposable;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableFromObservable */
public final class FlowableFromObservable<T> extends Flowable<T> {
    private final Observable<T> upstream;

    public FlowableFromObservable(Observable<T> observable) {
        this.upstream = observable;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.upstream.subscribe(new SubscriberObserver(subscriber));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableFromObservable$SubscriberObserver */
    static class SubscriberObserver<T> implements Observer<T>, Subscription {

        /* renamed from: d */
        private Disposable f2456d;

        /* renamed from: s */
        private final Subscriber<? super T> f2457s;

        public void request(long j) {
        }

        SubscriberObserver(Subscriber<? super T> subscriber) {
            this.f2457s = subscriber;
        }

        public void onComplete() {
            this.f2457s.onComplete();
        }

        public void onError(Throwable th) {
            this.f2457s.onError(th);
        }

        public void onNext(T t) {
            this.f2457s.onNext(t);
        }

        public void onSubscribe(Disposable disposable) {
            this.f2456d = disposable;
            this.f2457s.onSubscribe(this);
        }

        public void cancel() {
            this.f2456d.dispose();
        }
    }
}
