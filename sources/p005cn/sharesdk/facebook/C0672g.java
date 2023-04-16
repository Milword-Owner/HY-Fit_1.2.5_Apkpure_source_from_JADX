package p005cn.sharesdk.facebook;

import android.os.Bundle;
import android.webkit.WebView;
import com.facebook.AccessToken;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.mob.tools.utils.ResHelper;
import p005cn.sharesdk.framework.authorize.C0698c;
import p005cn.sharesdk.framework.authorize.C0702g;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.facebook.g */
/* compiled from: FacebookWebViewClient */
public class C0672g extends C0698c {
    public C0672g(C0702g gVar) {
        super(gVar);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        try {
            if (this.redirectUri != null && str.startsWith(this.redirectUri)) {
                webView.stopLoading();
                webView.postDelayed(new Runnable() {
                    public void run() {
                        C0672g.this.activity.finish();
                    }
                }, 500);
                onComplete(str);
                return true;
            }
        } catch (Exception e) {
            SSDKLog.m645b().mo29771e(e.getMessage(), new Object[0]);
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    /* access modifiers changed from: protected */
    public void onComplete(String str) {
        int i;
        Bundle urlToBundle = ResHelper.urlToBundle(str);
        String string = urlToBundle.getString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
        if (!(string == null || this.listener == null)) {
            string = "error_message ==>>" + string + "\nerror_code ==>>" + urlToBundle.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
            this.listener.onError(new Throwable(str));
        }
        if (string == null) {
            String string2 = urlToBundle.getString("access_token");
            String string3 = urlToBundle.containsKey(AccessToken.EXPIRES_IN_KEY) ? urlToBundle.getString(AccessToken.EXPIRES_IN_KEY) : "-1";
            if (this.listener != null) {
                Bundle bundle = new Bundle();
                bundle.putString("oauth_token", string2);
                bundle.putString("oauth_token_secret", "");
                try {
                    i = ResHelper.parseInt(string3);
                } catch (Throwable th) {
                    SSDKLog.m645b().mo29769d(th);
                    i = -1;
                }
                bundle.putInt("oauth_token_expires", i);
                this.listener.onComplete(bundle);
            }
        }
    }
}
