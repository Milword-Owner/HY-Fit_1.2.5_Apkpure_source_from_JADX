package p015io.reactivex.internal.operators.observable;

import p015io.reactivex.Notification;
import p015io.reactivex.ObservableSource;
import p015io.reactivex.Observer;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.observable.ObservableMaterialize */
public final class ObservableMaterialize<T> extends AbstractObservableWithUpstream<T, Notification<T>> {
    public ObservableMaterialize(ObservableSource<T> observableSource) {
        super(observableSource);
    }

    public void subscribeActual(Observer<? super Notification<T>> observer) {
        this.source.subscribe(new MaterializeObserver(observer));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableMaterialize$MaterializeObserver */
    static final class MaterializeObserver<T> implements Observer<T>, Disposable {
        final Observer<? super Notification<T>> actual;

        /* renamed from: s */
        Disposable f2617s;

        MaterializeObserver(Observer<? super Notification<T>> observer) {
            this.actual = observer;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f2617s, disposable)) {
                this.f2617s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2617s.dispose();
        }

        public boolean isDisposed() {
            return this.f2617s.isDisposed();
        }

        public void onNext(T t) {
            this.actual.onNext(Notification.createOnNext(t));
        }

        public void onError(Throwable th) {
            this.actual.onNext(Notification.createOnError(th));
            this.actual.onComplete();
        }

        public void onComplete() {
            this.actual.onNext(Notification.createOnComplete());
            this.actual.onComplete();
        }
    }
}
