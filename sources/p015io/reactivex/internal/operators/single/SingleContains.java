package p015io.reactivex.internal.operators.single;

import p015io.reactivex.SingleObserver;
import p015io.reactivex.SingleSource;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.exceptions.Exceptions;
import p015io.reactivex.functions.BiPredicate;

/* renamed from: io.reactivex.internal.operators.single.SingleContains */
public final class SingleContains<T> extends p015io.reactivex.Single<Boolean> {
    final BiPredicate<Object, Object> comparer;
    final SingleSource<T> source;
    final Object value;

    public SingleContains(SingleSource<T> singleSource, Object obj, BiPredicate<Object, Object> biPredicate) {
        this.source = singleSource;
        this.value = obj;
        this.comparer = biPredicate;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        this.source.subscribe(new Single(singleObserver));
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleContains$Single */
    final class Single implements SingleObserver<T> {

        /* renamed from: s */
        private final SingleObserver<? super Boolean> f2692s;

        Single(SingleObserver<? super Boolean> singleObserver) {
            this.f2692s = singleObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.f2692s.onSubscribe(disposable);
        }

        public void onSuccess(T t) {
            try {
                this.f2692s.onSuccess(Boolean.valueOf(SingleContains.this.comparer.test(t, SingleContains.this.value)));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.f2692s.onError(th);
            }
        }

        public void onError(Throwable th) {
            this.f2692s.onError(th);
        }
    }
}
