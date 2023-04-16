package p005cn.sharesdk.framework.utils.QRCodeUtil;

import android.graphics.Bitmap;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.QRCodeListener */
public interface QRCodeListener {
    void onError(Throwable th);

    void onSuccess(Bitmap bitmap);
}
