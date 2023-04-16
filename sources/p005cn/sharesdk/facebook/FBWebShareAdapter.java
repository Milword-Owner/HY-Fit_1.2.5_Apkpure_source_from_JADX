package p005cn.sharesdk.facebook;

import android.app.Activity;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import p005cn.sharesdk.framework.TitleLayout;

/* renamed from: cn.sharesdk.facebook.FBWebShareAdapter */
public class FBWebShareAdapter {
    private Activity activity;
    private boolean noTitle;
    private RelativeLayout rlBody;
    private TitleLayout title;
    private WebView webview;

    public void onCreate() {
    }

    public void onDestroy() {
    }

    public boolean onFinish() {
        return false;
    }

    public void onPause() {
    }

    public void onRestart() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    /* access modifiers changed from: package-private */
    public void setActivity(Activity activity2) {
        this.activity = activity2;
    }

    public Activity getActivity() {
        return this.activity;
    }

    /* access modifiers changed from: package-private */
    public void setTitleView(TitleLayout titleLayout) {
        this.title = titleLayout;
    }

    public TitleLayout getTitleLayout() {
        return this.title;
    }

    /* access modifiers changed from: package-private */
    public void setWebView(WebView webView) {
        this.webview = webView;
    }

    public WebView getWebBody() {
        return this.webview;
    }

    /* access modifiers changed from: package-private */
    public void setNotitle(boolean z) {
        this.noTitle = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isNotitle() {
        return this.noTitle;
    }

    /* access modifiers changed from: package-private */
    public void setBodyView(RelativeLayout relativeLayout) {
        this.rlBody = relativeLayout;
    }

    public RelativeLayout getBodyView() {
        return this.rlBody;
    }
}
