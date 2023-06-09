package p015io.reactivex;

import p015io.reactivex.annotations.Experimental;
import p015io.reactivex.annotations.NonNull;

@Experimental
/* renamed from: io.reactivex.ObservableConverter */
public interface ObservableConverter<T, R> {
    @NonNull
    R apply(@NonNull Observable<T> observable);
}
