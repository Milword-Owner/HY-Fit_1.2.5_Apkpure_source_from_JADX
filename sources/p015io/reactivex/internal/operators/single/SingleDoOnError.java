package p015io.reactivex.internal.operators.single;

import p015io.reactivex.Single;
import p015io.reactivex.SingleObserver;
import p015io.reactivex.SingleSource;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.exceptions.CompositeException;
import p015io.reactivex.exceptions.Exceptions;
import p015io.reactivex.functions.Consumer;

/* renamed from: io.reactivex.internal.operators.single.SingleDoOnError */
public final class SingleDoOnError<T> extends Single<T> {
    final Consumer<? super Throwable> onError;
    final SingleSource<T> source;

    public SingleDoOnError(SingleSource<T> singleSource, Consumer<? super Throwable> consumer) {
        this.source = singleSource;
        this.onError = consumer;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new DoOnError(singleObserver));
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleDoOnError$DoOnError */
    final class DoOnError implements SingleObserver<T> {

        /* renamed from: s */
        private final SingleObserver<? super T> f2702s;

        DoOnError(SingleObserver<? super T> singleObserver) {
            this.f2702s = singleObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.f2702s.onSubscribe(disposable);
        }

        public void onSuccess(T t) {
            this.f2702s.onSuccess(t);
        }

        public void onError(Throwable th) {
            try {
                SingleDoOnError.this.onError.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.f2702s.onError(th);
        }
    }
}
