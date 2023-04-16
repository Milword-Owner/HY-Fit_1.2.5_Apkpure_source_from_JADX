package p005cn.sharesdk.framework.authorize;

import android.webkit.WebView;
import p005cn.sharesdk.framework.C0749g;

/* renamed from: cn.sharesdk.framework.authorize.c */
/* compiled from: AuthorizeWebviewClient */
public abstract class C0698c extends C0749g {
    /* access modifiers changed from: protected */
    public C0702g activity;
    protected AuthorizeListener listener;
    protected String redirectUri;

    /* access modifiers changed from: protected */
    public abstract void onComplete(String str);

    public C0698c(C0702g gVar) {
        this.activity = gVar;
        AuthorizeHelper a = gVar.mo10536a();
        this.redirectUri = a.getRedirectUri();
        this.listener = a.getAuthorizeListener();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        webView.stopLoading();
        AuthorizeListener authorizeListener = this.activity.mo10536a().getAuthorizeListener();
        this.activity.finish();
        if (authorizeListener != null) {
            authorizeListener.onError(new Throwable(str + " (" + i + "): " + str2));
        }
    }
}
