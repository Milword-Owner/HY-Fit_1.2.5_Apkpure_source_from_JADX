package p005cn.sharesdk.framework.authorize;

import android.os.Bundle;

/* renamed from: cn.sharesdk.framework.authorize.AuthorizeListener */
public interface AuthorizeListener {
    void onCancel();

    void onComplete(Bundle bundle);

    void onError(Throwable th);
}
