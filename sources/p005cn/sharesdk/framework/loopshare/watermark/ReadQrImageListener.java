package p005cn.sharesdk.framework.loopshare.watermark;

/* renamed from: cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener */
public interface ReadQrImageListener {
    void onFailed(Throwable th, int i);

    void onSucessed(String str);
}
