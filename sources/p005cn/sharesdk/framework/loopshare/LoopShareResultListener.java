package p005cn.sharesdk.framework.loopshare;

/* renamed from: cn.sharesdk.framework.loopshare.LoopShareResultListener */
public interface LoopShareResultListener<T> {
    void onError(Throwable th);

    void onResult(T t);
}
