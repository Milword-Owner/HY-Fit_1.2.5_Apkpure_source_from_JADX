package p005cn.sharesdk.framework.loopshare;

/* renamed from: cn.sharesdk.framework.loopshare.LoopSharePasswordListener */
public interface LoopSharePasswordListener<T> {
    void onError(Throwable th);

    void onResult(T t);
}
