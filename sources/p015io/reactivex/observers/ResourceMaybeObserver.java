package p015io.reactivex.observers;

import java.util.concurrent.atomic.AtomicReference;
import p015io.reactivex.MaybeObserver;
import p015io.reactivex.annotations.NonNull;
import p015io.reactivex.disposables.Disposable;
import p015io.reactivex.internal.disposables.DisposableHelper;
import p015io.reactivex.internal.disposables.ListCompositeDisposable;
import p015io.reactivex.internal.functions.ObjectHelper;
import p015io.reactivex.internal.util.EndConsumerHelper;

/* renamed from: io.reactivex.observers.ResourceMaybeObserver */
public abstract class ResourceMaybeObserver<T> implements MaybeObserver<T>, Disposable {
    private final ListCompositeDisposable resources = new ListCompositeDisposable();

    /* renamed from: s */
    private final AtomicReference<Disposable> f2756s = new AtomicReference<>();

    /* access modifiers changed from: protected */
    public void onStart() {
    }

    public final void add(@NonNull Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "resource is null");
        this.resources.add(disposable);
    }

    public final void onSubscribe(@NonNull Disposable disposable) {
        if (EndConsumerHelper.setOnce(this.f2756s, disposable, getClass())) {
            onStart();
        }
    }

    public final void dispose() {
        if (DisposableHelper.dispose(this.f2756s)) {
            this.resources.dispose();
        }
    }

    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.f2756s.get());
    }
}
