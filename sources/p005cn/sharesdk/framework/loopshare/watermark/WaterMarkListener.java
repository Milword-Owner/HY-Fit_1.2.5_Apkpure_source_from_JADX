package p005cn.sharesdk.framework.loopshare.watermark;

/* renamed from: cn.sharesdk.framework.loopshare.watermark.WaterMarkListener */
public interface WaterMarkListener {
    void onCancel();

    void onEnd(int i);

    void onFailed(String str, int i);

    void onProgress(int i);

    void onStart();
}
