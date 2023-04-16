package p005cn.sharesdk.framework.authorize;

import android.os.Bundle;

/* renamed from: cn.sharesdk.framework.authorize.SSOListener */
public interface SSOListener {
    void onCancel();

    void onComplete(Bundle bundle);

    void onFailed(Throwable th);
}
