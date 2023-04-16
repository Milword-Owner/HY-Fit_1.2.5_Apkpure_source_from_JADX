package p005cn.sharesdk.facebook;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.util.HashMap;
import p005cn.sharesdk.framework.C0749g;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.authorize.RegisterView;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p015io.reactivex.annotations.SchedulerSupport;

/* renamed from: cn.sharesdk.facebook.k */
/* compiled from: WebShareActivity */
public class C0678k extends FakeActivity {

    /* renamed from: a */
    private String f132a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public PlatformActionListener f133b;

    /* renamed from: c */
    private C0665a f134c;

    /* renamed from: d */
    private RegisterView f135d;

    /* renamed from: e */
    private WebView f136e;

    /* renamed from: f */
    private boolean f137f;

    /* renamed from: g */
    private boolean f138g;

    /* renamed from: a */
    public void mo10235a(String str) {
        this.f132a = str;
    }

    /* renamed from: a */
    public void mo10234a(PlatformActionListener platformActionListener) {
        this.f133b = platformActionListener;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.f134c == null) {
            this.f134c = m86b();
            if (this.f134c == null) {
                this.f134c = new C0665a();
            }
        }
        this.f134c.mo10171a(activity);
    }

    /* renamed from: b */
    private C0665a m86b() {
        try {
            String string = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128).metaData.getString("FBWebShareAdapter");
            if (string != null) {
                if (string.length() > 0) {
                    Object newInstance = Class.forName(string).newInstance();
                    if (!(newInstance instanceof C0665a)) {
                        return null;
                    }
                    return (C0665a) newInstance;
                }
            }
            return null;
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            return null;
        }
    }

    public void onCreate() {
        this.f135d = mo10233a();
        try {
            int stringRes = ResHelper.getStringRes(getContext(), "ssdk_share_to_facebook");
            if (stringRes > 0) {
                this.f135d.mo10529c().getTvTitle().setText(stringRes);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            this.f135d.mo10529c().setVisibility(8);
        }
        this.f134c.mo10173a(this.f135d.mo10530d());
        this.f134c.mo10172a(this.f135d.mo10528b());
        this.f134c.mo10174a(this.f135d.mo10529c());
        this.f134c.mo10170a();
        disableScreenCapture();
        this.activity.setContentView(this.f135d);
        if (SchedulerSupport.NONE.equals(DeviceHelper.getInstance(this.activity).getDetailNetworkTypeForStatic())) {
            this.f137f = true;
            finish();
            this.f133b.onError((Platform) null, 0, new Throwable("failed to load webpage, network disconnected."));
            return;
        }
        this.f135d.mo10528b().loadUrl(this.f132a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public RegisterView mo10233a() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.mo10529c().getChildAt(registerView.mo10529c().getChildCount() - 1).setVisibility(8);
        registerView.mo10526a().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        try {
                            new Instrumentation().sendKeyDownUpSync(4);
                        } catch (Throwable th) {
                            SSDKLog.m645b().mo29769d(th);
                            C0678k.this.finish();
                            C0678k.this.f133b.onCancel((Platform) null, 0);
                        }
                    }
                }.start();
            }
        });
        this.f136e = registerView.mo10528b();
        WebSettings settings = this.f136e.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSavePassword(false);
        settings.setDatabasePath(this.activity.getDir("database", 0).getPath());
        this.f136e.setVerticalScrollBarEnabled(false);
        this.f136e.setHorizontalScrollBarEnabled(false);
        this.f136e.setWebViewClient(new C0749g() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str != null) {
                    try {
                        if (str.startsWith(ServerProtocol.DIALOG_REDIRECT_URI)) {
                            C0678k.this.m87b(str);
                        }
                    } catch (Exception e) {
                        SSDKLog.m645b().mo29768d(e.getMessage(), new Object[0]);
                    }
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        });
        return registerView;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m87b(String str) {
        String str2 = str == null ? "" : new String(str);
        Bundle urlToBundle = ResHelper.urlToBundle(str);
        if (urlToBundle == null) {
            this.f137f = true;
            finish();
            PlatformActionListener platformActionListener = this.f133b;
            platformActionListener.onError((Platform) null, 0, new Throwable("failed to parse callback uri: " + str2));
            return;
        }
        String string = urlToBundle.getString(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID);
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(string)) {
            hashMap.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID, string);
        }
        if (urlToBundle.containsKey(NativeProtocol.BRIDGE_ARG_ERROR_CODE) || urlToBundle.containsKey("error")) {
            if (this.f133b != null) {
                String string2 = urlToBundle.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
                if (!urlToBundle.containsKey(NativeProtocol.BRIDGE_ARG_ERROR_CODE) || !string2.equals("4201")) {
                    this.f133b.onError((Platform) null, 9, new Throwable(ResHelper.encodeUrl(urlToBundle)));
                } else {
                    this.f133b.onCancel((Platform) null, 9);
                }
            }
            this.f137f = true;
            finish();
            return;
        }
        this.f138g = true;
        finish();
        this.f133b.onComplete((Platform) null, 0, hashMap);
    }

    public void onStart() {
        C0665a aVar = this.f134c;
        if (aVar != null) {
            aVar.mo10176c();
        }
    }

    public void onPause() {
        C0665a aVar = this.f134c;
        if (aVar != null) {
            aVar.mo10177d();
        }
    }

    public void onResume() {
        C0665a aVar = this.f134c;
        if (aVar != null) {
            aVar.mo10178e();
        }
    }

    public void onStop() {
        C0665a aVar = this.f134c;
        if (aVar != null) {
            aVar.mo10179f();
        }
    }

    public void onRestart() {
        C0665a aVar = this.f134c;
        if (aVar != null) {
            aVar.mo10180g();
        }
    }

    public void onDestroy() {
        if (!this.f137f && !this.f138g) {
            this.f133b.onCancel((Platform) null, 0);
        }
        WebView webView = this.f136e;
        if (webView != null) {
            webView.setFocusable(false);
        }
        C0665a aVar = this.f134c;
        if (aVar != null) {
            aVar.mo10175b();
        }
    }

    public boolean onFinish() {
        C0665a aVar = this.f134c;
        if (aVar != null) {
            return aVar.mo10181h();
        }
        return super.onFinish();
    }
}
