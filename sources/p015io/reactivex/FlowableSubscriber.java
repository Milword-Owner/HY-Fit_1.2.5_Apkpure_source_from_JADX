package p015io.reactivex;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p015io.reactivex.annotations.Beta;
import p015io.reactivex.annotations.NonNull;

@Beta
/* renamed from: io.reactivex.FlowableSubscriber */
public interface FlowableSubscriber<T> extends Subscriber<T> {
    void onSubscribe(@NonNull Subscription subscription);
}
