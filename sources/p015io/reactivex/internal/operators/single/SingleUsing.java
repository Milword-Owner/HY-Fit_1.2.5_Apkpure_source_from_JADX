package p015io.reactivex.internal.operators.single;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import p015io.reactivex.Single;
import p015io.reactivex.SingleObserver;
import p015io.reactivex.SingleSource;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.exceptions.CompositeException;
import p015io.reactivex.exceptions.Exceptions;
import p015io.reactivex.functions.Consumer;
import p015io.reactivex.functions.Function;
import p015io.reactivex.internal.disposables.DisposableHelper;
import p015io.reactivex.internal.disposables.EmptyDisposable;
import p015io.reactivex.internal.functions.ObjectHelper;
import p015io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.single.SingleUsing */
public final class SingleUsing<T, U> extends Single<T> {
    final Consumer<? super U> disposer;
    final boolean eager;
    final Callable<U> resourceSupplier;
    final Function<? super U, ? extends SingleSource<? extends T>> singleFunction;

    public SingleUsing(Callable<U> callable, Function<? super U, ? extends SingleSource<? extends T>> function, Consumer<? super U> consumer, boolean z) {
        this.resourceSupplier = callable;
        this.singleFunction = function;
        this.disposer = consumer;
        this.eager = z;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        try {
            U call = this.resourceSupplier.call();
            try {
                ((SingleSource) ObjectHelper.requireNonNull(this.singleFunction.apply(call), "The singleFunction returned a null SingleSource")).subscribe(new UsingSingleObserver(singleObserver, call, this.eager, this.disposer));
                return;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
                return;
            }
            EmptyDisposable.error(th, (SingleObserver<?>) singleObserver);
            if (!this.eager) {
                this.disposer.accept(call);
            }
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            EmptyDisposable.error(th2, (SingleObserver<?>) singleObserver);
        }
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleUsing$UsingSingleObserver */
    static final class UsingSingleObserver<T, U> extends AtomicReference<Object> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -5331524057054083935L;
        final SingleObserver<? super T> actual;

        /* renamed from: d */
        Disposable f2716d;
        final Consumer<? super U> disposer;
        final boolean eager;

        UsingSingleObserver(SingleObserver<? super T> singleObserver, U u, boolean z, Consumer<? super U> consumer) {
            super(u);
            this.actual = singleObserver;
            this.eager = z;
            this.disposer = consumer;
        }

        public void dispose() {
            this.f2716d.dispose();
            this.f2716d = DisposableHelper.DISPOSED;
            disposeAfter();
        }

        public boolean isDisposed() {
            return this.f2716d.isDisposed();
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f2716d, disposable)) {
                this.f2716d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.f2716d = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.actual.onError(th);
                        return;
                    }
                } else {
                    return;
                }
            }
            this.actual.onSuccess(t);
            if (!this.eager) {
                disposeAfter();
            }
        }

        public void onError(Throwable th) {
            this.f2716d = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        th = new CompositeException(th, th2);
                    }
                } else {
                    return;
                }
            }
            this.actual.onError(th);
            if (!this.eager) {
                disposeAfter();
            }
        }

        /* access modifiers changed from: package-private */
        public void disposeAfter() {
            Object andSet = getAndSet(this);
            if (andSet != this) {
                try {
                    this.disposer.accept(andSet);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }
}
