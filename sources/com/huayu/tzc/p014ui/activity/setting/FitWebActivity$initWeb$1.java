package com.huayu.tzc.p014ui.activity.setting;

import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.RequiresApi;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.bean.FitBit;
import com.tencent.mmkv.MMKV;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0017J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0017¨\u0006\f"}, mo21895d2 = {"com/huayu/tzc/ui/activity/setting/FitWebActivity$initWeb$1", "Landroid/webkit/WebViewClient;", "onReceivedError", "", "view", "Landroid/webkit/WebView;", "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "shouldOverrideUrlLoading", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.FitWebActivity$initWeb$1 */
/* compiled from: FitWebActivity.kt */
public final class FitWebActivity$initWeb$1 extends WebViewClient {
    final /* synthetic */ FitWebActivity this$0;

    FitWebActivity$initWeb$1(FitWebActivity fitWebActivity) {
        this.this$0 = fitWebActivity;
    }

    @RequiresApi(21)
    public boolean shouldOverrideUrlLoading(@Nullable WebView webView, @Nullable WebResourceRequest webResourceRequest) {
        String access$getTAG$p = this.this$0.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("shouldOverrideUrlLoading: ");
        Uri uri = null;
        sb.append(String.valueOf(webResourceRequest != null ? webResourceRequest.getUrl() : null));
        Log.e(access$getTAG$p, sb.toString());
        if (StringsKt.contains$default((CharSequence) String.valueOf(webResourceRequest != null ? webResourceRequest.getUrl() : null), (CharSequence) "access_token", false, 2, (Object) null)) {
            if (webResourceRequest != null) {
                uri = webResourceRequest.getUrl();
            }
            List split$default = StringsKt.split$default((CharSequence) StringsKt.replace$default(String.valueOf(uri), "bfs://HY-Fit#access_token=", "", false, 4, (Object) null), new String[]{"&"}, false, 0, 6, (Object) null);
            if (split$default.size() >= 2) {
                FitBit fitBit = (FitBit) MMKV.defaultMMKV().decodeParcelable("fitbit", FitBit.class, new FitBit());
                fitBit.setFitToken((String) split$default.get(0));
                fitBit.setFitId(StringsKt.replace$default((String) split$default.get(1), "user_id=", "", false, 4, (Object) null));
                fitBit.setConnectFitBit(true);
                MMKV.defaultMMKV().encode("fitbit", (Parcelable) fitBit);
                ToastUtils.show((CharSequence) "ok");
                this.this$0.setResult(-1);
                this.this$0.finish();
            }
        }
        return super.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    @RequiresApi(23)
    public void onReceivedError(@Nullable WebView webView, @Nullable WebResourceRequest webResourceRequest, @Nullable WebResourceError webResourceError) {
        if (webView == null) {
            Intrinsics.throwNpe();
        }
        String url = webView.getUrl();
        Intrinsics.checkExpressionValueIsNotNull(url, "view!!.url");
        if (StringsKt.contains$default((CharSequence) url, (CharSequence) "access_token", false, 2, (Object) null)) {
            String access$getTAG$p = this.this$0.getTAG();
            Log.e(access$getTAG$p, "shouldOverrideUrlLoading: error " + webView.getUrl());
            String access$getTAG$p2 = this.this$0.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("shouldOverrideUrlLoading: ");
            if (webResourceError == null) {
                Intrinsics.throwNpe();
            }
            sb.append(webResourceError.getDescription().toString());
            Log.e(access$getTAG$p2, sb.toString());
            String access$getTAG$p3 = this.this$0.getTAG();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("shouldOverrideUrlLoading: ");
            if (webResourceRequest == null) {
                Intrinsics.throwNpe();
            }
            sb2.append(webResourceRequest.getUrl().toString());
            Log.e(access$getTAG$p3, sb2.toString());
        }
    }
}
