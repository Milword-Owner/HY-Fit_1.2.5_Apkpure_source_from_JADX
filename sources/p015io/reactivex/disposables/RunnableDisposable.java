package p015io.reactivex.disposables;

import p015io.reactivex.annotations.NonNull;

/* renamed from: io.reactivex.disposables.RunnableDisposable */
final class RunnableDisposable extends ReferenceDisposable<Runnable> {
    private static final long serialVersionUID = -8219729196779211169L;

    RunnableDisposable(Runnable runnable) {
        super(runnable);
    }

    /* access modifiers changed from: protected */
    public void onDisposed(@NonNull Runnable runnable) {
        runnable.run();
    }

    public String toString() {
        return "RunnableDisposable(disposed=" + isDisposed() + ", " + get() + ")";
    }
}
