package p015io.reactivex.internal.operators.observable;

import java.util.concurrent.atomic.AtomicInteger;
import p015io.reactivex.Observable;
import p015io.reactivex.ObservableSource;
import p015io.reactivex.Observer;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.exceptions.Exceptions;
import p015io.reactivex.functions.BiPredicate;
import p015io.reactivex.internal.disposables.ArrayCompositeDisposable;
import p015io.reactivex.internal.queue.SpscLinkedArrayQueue;

/* renamed from: io.reactivex.internal.operators.observable.ObservableSequenceEqual */
public final class ObservableSequenceEqual<T> extends Observable<Boolean> {
    final int bufferSize;
    final BiPredicate<? super T, ? super T> comparer;
    final ObservableSource<? extends T> first;
    final ObservableSource<? extends T> second;

    public ObservableSequenceEqual(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, BiPredicate<? super T, ? super T> biPredicate, int i) {
        this.first = observableSource;
        this.second = observableSource2;
        this.comparer = biPredicate;
        this.bufferSize = i;
    }

    public void subscribeActual(Observer<? super Boolean> observer) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(observer, this.bufferSize, this.first, this.second, this.comparer);
        observer.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe();
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableSequenceEqual$EqualCoordinator */
    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -6178010334400373240L;
        final Observer<? super Boolean> actual;
        volatile boolean cancelled;
        final BiPredicate<? super T, ? super T> comparer;
        final ObservableSource<? extends T> first;
        final EqualObserver<T>[] observers;
        final ArrayCompositeDisposable resources = new ArrayCompositeDisposable(2);
        final ObservableSource<? extends T> second;

        /* renamed from: v1 */
        T f2636v1;

        /* renamed from: v2 */
        T f2637v2;

        EqualCoordinator(Observer<? super Boolean> observer, int i, ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, BiPredicate<? super T, ? super T> biPredicate) {
            this.actual = observer;
            this.first = observableSource;
            this.second = observableSource2;
            this.comparer = biPredicate;
            EqualObserver<T>[] equalObserverArr = new EqualObserver[2];
            this.observers = equalObserverArr;
            equalObserverArr[0] = new EqualObserver<>(this, 0, i);
            equalObserverArr[1] = new EqualObserver<>(this, 1, i);
        }

        /* access modifiers changed from: package-private */
        public boolean setDisposable(Disposable disposable, int i) {
            return this.resources.setResource(i, disposable);
        }

        /* access modifiers changed from: package-private */
        public void subscribe() {
            EqualObserver<T>[] equalObserverArr = this.observers;
            this.first.subscribe(equalObserverArr[0]);
            this.second.subscribe(equalObserverArr[1]);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
                if (getAndIncrement() == 0) {
                    EqualObserver<T>[] equalObserverArr = this.observers;
                    equalObserverArr[0].queue.clear();
                    equalObserverArr[1].queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void cancel(SpscLinkedArrayQueue<T> spscLinkedArrayQueue, SpscLinkedArrayQueue<T> spscLinkedArrayQueue2) {
            this.cancelled = true;
            spscLinkedArrayQueue.clear();
            spscLinkedArrayQueue2.clear();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            Throwable th;
            Throwable th2;
            if (getAndIncrement() == 0) {
                EqualObserver<T>[] equalObserverArr = this.observers;
                EqualObserver<T> equalObserver = equalObserverArr[0];
                SpscLinkedArrayQueue<T> spscLinkedArrayQueue = equalObserver.queue;
                EqualObserver<T> equalObserver2 = equalObserverArr[1];
                SpscLinkedArrayQueue<T> spscLinkedArrayQueue2 = equalObserver2.queue;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = equalObserver.done;
                    if (!z || (th2 = equalObserver.error) == null) {
                        boolean z2 = equalObserver2.done;
                        if (!z2 || (th = equalObserver2.error) == null) {
                            if (this.f2636v1 == null) {
                                this.f2636v1 = spscLinkedArrayQueue.poll();
                            }
                            boolean z3 = this.f2636v1 == null;
                            if (this.f2637v2 == null) {
                                this.f2637v2 = spscLinkedArrayQueue2.poll();
                            }
                            boolean z4 = this.f2637v2 == null;
                            if (z && z2 && z3 && z4) {
                                this.actual.onNext(true);
                                this.actual.onComplete();
                                return;
                            } else if (!z || !z2 || z3 == z4) {
                                if (!z3 && !z4) {
                                    try {
                                        if (!this.comparer.test(this.f2636v1, this.f2637v2)) {
                                            cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                                            this.actual.onNext(false);
                                            this.actual.onComplete();
                                            return;
                                        }
                                        this.f2636v1 = null;
                                        this.f2637v2 = null;
                                    } catch (Throwable th3) {
                                        Exceptions.throwIfFatal(th3);
                                        cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                                        this.actual.onError(th3);
                                        return;
                                    }
                                }
                                if ((z3 || z4) && (i = addAndGet(-i)) == 0) {
                                    return;
                                }
                            } else {
                                cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                                this.actual.onNext(false);
                                this.actual.onComplete();
                                return;
                            }
                        } else {
                            cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                            this.actual.onError(th);
                            return;
                        }
                    } else {
                        cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                        this.actual.onError(th2);
                        return;
                    }
                }
                spscLinkedArrayQueue.clear();
                spscLinkedArrayQueue2.clear();
            }
        }
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableSequenceEqual$EqualObserver */
    static final class EqualObserver<T> implements Observer<T> {
        volatile boolean done;
        Throwable error;
        final int index;
        final EqualCoordinator<T> parent;
        final SpscLinkedArrayQueue<T> queue;

        EqualObserver(EqualCoordinator<T> equalCoordinator, int i, int i2) {
            this.parent = equalCoordinator;
            this.index = i;
            this.queue = new SpscLinkedArrayQueue<>(i2);
        }

        public void onSubscribe(Disposable disposable) {
            this.parent.setDisposable(disposable, this.index);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            this.parent.drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            this.parent.drain();
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }
    }
}
