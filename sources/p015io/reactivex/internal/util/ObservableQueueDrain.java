package p015io.reactivex.internal.util;

import p015io.reactivex.Observer;

/* renamed from: io.reactivex.internal.util.ObservableQueueDrain */
public interface ObservableQueueDrain<T, U> {
    void accept(Observer<? super U> observer, T t);

    boolean cancelled();

    boolean done();

    boolean enter();

    Throwable error();

    int leave(int i);
}
