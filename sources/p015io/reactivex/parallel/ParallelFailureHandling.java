package p015io.reactivex.parallel;

import p015io.reactivex.annotations.Experimental;
import p015io.reactivex.functions.BiFunction;

@Experimental
/* renamed from: io.reactivex.parallel.ParallelFailureHandling */
public enum ParallelFailureHandling implements BiFunction<Long, Throwable, ParallelFailureHandling> {
    STOP,
    ERROR,
    SKIP,
    RETRY;

    public ParallelFailureHandling apply(Long l, Throwable th) {
        return this;
    }
}
