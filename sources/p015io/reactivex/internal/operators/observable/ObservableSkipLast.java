package p015io.reactivex.internal.operators.observable;

import java.util.ArrayDeque;
import p015io.reactivex.ObservableSource;
import p015io.reactivex.Observer;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.observable.ObservableSkipLast */
public final class ObservableSkipLast<T> extends AbstractObservableWithUpstream<T, T> {
    final int skip;

    public ObservableSkipLast(ObservableSource<T> observableSource, int i) {
        super(observableSource);
        this.skip = i;
    }

    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new SkipLastObserver(observer, this.skip));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableSkipLast$SkipLastObserver */
    static final class SkipLastObserver<T> extends ArrayDeque<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -3807491841935125653L;
        final Observer<? super T> actual;

        /* renamed from: s */
        Disposable f2644s;
        final int skip;

        SkipLastObserver(Observer<? super T> observer, int i) {
            super(i);
            this.actual = observer;
            this.skip = i;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f2644s, disposable)) {
                this.f2644s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2644s.dispose();
        }

        public boolean isDisposed() {
            return this.f2644s.isDisposed();
        }

        public void onNext(T t) {
            if (this.skip == size()) {
                this.actual.onNext(poll());
            }
            offer(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }
}
