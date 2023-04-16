package p015io.reactivex.internal.operators.observable;

import p015io.reactivex.Maybe;
import p015io.reactivex.MaybeObserver;
import p015io.reactivex.Observable;
import p015io.reactivex.ObservableSource;
import p015io.reactivex.Observer;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.internal.disposables.DisposableHelper;
import p015io.reactivex.internal.fuseable.FuseToObservable;
import p015io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.observable.ObservableElementAtMaybe */
public final class ObservableElementAtMaybe<T> extends Maybe<T> implements FuseToObservable<T> {
    final long index;
    final ObservableSource<T> source;

    public ObservableElementAtMaybe(ObservableSource<T> observableSource, long j) {
        this.source = observableSource;
        this.index = j;
    }

    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new ElementAtObserver(maybeObserver, this.index));
    }

    public Observable<T> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableElementAt(this.source, this.index, null, false));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableElementAtMaybe$ElementAtObserver */
    static final class ElementAtObserver<T> implements Observer<T>, Disposable {
        final MaybeObserver<? super T> actual;
        long count;
        boolean done;
        final long index;

        /* renamed from: s */
        Disposable f2598s;

        ElementAtObserver(MaybeObserver<? super T> maybeObserver, long j) {
            this.actual = maybeObserver;
            this.index = j;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f2598s, disposable)) {
                this.f2598s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2598s.dispose();
        }

        public boolean isDisposed() {
            return this.f2598s.isDisposed();
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.count;
                if (j == this.index) {
                    this.done = true;
                    this.f2598s.dispose();
                    this.actual.onSuccess(t);
                    return;
                }
                this.count = j + 1;
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }
    }
}
