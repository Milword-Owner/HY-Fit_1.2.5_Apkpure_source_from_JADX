package p015io.reactivex.internal.operators.flowable;

import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import p015io.reactivex.FlowableSubscriber;
import p015io.reactivex.Single;
import p015io.reactivex.SingleObserver;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.exceptions.Exceptions;
import p015io.reactivex.functions.BiFunction;
import p015io.reactivex.internal.functions.ObjectHelper;
import p015io.reactivex.internal.subscriptions.SubscriptionHelper;
import p015io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableReduceSeedSingle */
public final class FlowableReduceSeedSingle<T, R> extends Single<R> {
    final BiFunction<R, ? super T, R> reducer;
    final R seed;
    final Publisher<T> source;

    public FlowableReduceSeedSingle(Publisher<T> publisher, R r, BiFunction<R, ? super T, R> biFunction) {
        this.source = publisher;
        this.seed = r;
        this.reducer = biFunction;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.source.subscribe(new ReduceSeedObserver(singleObserver, this.reducer, this.seed));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableReduceSeedSingle$ReduceSeedObserver */
    static final class ReduceSeedObserver<T, R> implements FlowableSubscriber<T>, Disposable {
        final SingleObserver<? super R> actual;
        final BiFunction<R, ? super T, R> reducer;

        /* renamed from: s */
        Subscription f2477s;
        R value;

        ReduceSeedObserver(SingleObserver<? super R> singleObserver, BiFunction<R, ? super T, R> biFunction, R r) {
            this.actual = singleObserver;
            this.value = r;
            this.reducer = biFunction;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f2477s, subscription)) {
                this.f2477s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(LongCompanionObject.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            R r = this.value;
            if (r != null) {
                try {
                    this.value = ObjectHelper.requireNonNull(this.reducer.apply(r, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.f2477s.cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.value != null) {
                this.value = null;
                this.f2477s = SubscriptionHelper.CANCELLED;
                this.actual.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            R r = this.value;
            if (r != null) {
                this.value = null;
                this.f2477s = SubscriptionHelper.CANCELLED;
                this.actual.onSuccess(r);
            }
        }

        public void dispose() {
            this.f2477s.cancel();
            this.f2477s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2477s == SubscriptionHelper.CANCELLED;
        }
    }
}
