package com.huayu.tzc.p014ui.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.common.internal.ImagesContract;
import com.hjq.language.MultiLanguages;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.presenter.NotPresenter;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0002J\b\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/WebActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "getLayoutId", "", "getPresenter", "getYsString", "", "index", "initView", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.WebActivity */
/* compiled from: WebActivity.kt */
public final class WebActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;

    private final String getYsString(int i) {
        return i != 0 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 6 ? "http://fit.tenswall.com/assets/privacy_en.html" : "http://fit.tenswall.com/assets/privacy_jp.html" : "http://fit.tenswall.com/assets/privacy_de.html" : "http://fit.tenswall.com/assets/privacy_fr.html" : "http://fit.tenswall.com/assets/privacy_it.html" : "http://fit.tenswall.com/assets/privacy_sp.html" : Constant.YSURL;
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.activity_web;
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        int decodeInt = MMKV.defaultMMKV().decodeInt("language", 7);
        WebView webView = (WebView) _$_findCachedViewById(C2128R.C2131id.webView);
        Intrinsics.checkExpressionValueIsNotNull(webView, "webView");
        WebSettings settings = webView.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings, "webSettings");
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        WebView webView2 = (WebView) _$_findCachedViewById(C2128R.C2131id.webView);
        Intrinsics.checkExpressionValueIsNotNull(webView2, "webView");
        webView2.setWebChromeClient(new WebChromeClient());
        WebView webView3 = (WebView) _$_findCachedViewById(C2128R.C2131id.webView);
        Intrinsics.checkExpressionValueIsNotNull(webView3, "webView");
        webView3.setWebViewClient(new WebActivity$initView$1());
        if (decodeInt == 7) {
            Locale appLanguage = MultiLanguages.getAppLanguage(this);
            Intrinsics.checkExpressionValueIsNotNull(appLanguage, ImagesContract.LOCAL);
            String language = appLanguage.getLanguage();
            if (language != null) {
                int hashCode = language.hashCode();
                if (hashCode != 3383) {
                    if (hashCode == 3886 && language.equals("zh")) {
                        decodeInt = 0;
                    }
                } else if (language.equals("ja")) {
                    decodeInt = 6;
                }
            }
        }
        ((WebView) _$_findCachedViewById(C2128R.C2131id.webView)).loadUrl(getYsString(decodeInt));
    }
}
