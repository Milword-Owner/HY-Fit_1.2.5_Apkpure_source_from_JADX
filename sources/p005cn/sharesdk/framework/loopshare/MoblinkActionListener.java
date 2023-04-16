package p005cn.sharesdk.framework.loopshare;

/* renamed from: cn.sharesdk.framework.loopshare.MoblinkActionListener */
public interface MoblinkActionListener<T> {
    void onError(Throwable th);

    void onResult(T t);
}
