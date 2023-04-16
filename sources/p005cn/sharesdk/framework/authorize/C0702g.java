package p005cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import p005cn.sharesdk.framework.ShareSDK;
import p005cn.sharesdk.framework.TitleLayout;
import p005cn.sharesdk.framework.authorize.ResizeLayout;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p015io.reactivex.annotations.SchedulerSupport;

/* renamed from: cn.sharesdk.framework.authorize.g */
/* compiled from: WebAuthorizeActivity */
public class C0702g extends C0696a implements Handler.Callback, ResizeLayout.OnResizeListener {

    /* renamed from: b */
    protected AuthorizeListener f198b;

    /* renamed from: c */
    private AuthorizeAdapter f199c;

    /* renamed from: d */
    private RegisterView f200d;

    /* renamed from: e */
    private WebView f201e;

    /* renamed from: a */
    public void mo10552a(AuthorizeListener authorizeListener) {
        this.f198b = authorizeListener;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.f199c == null) {
            this.f199c = m160c();
            if (this.f199c == null) {
                this.f199c = new AuthorizeAdapter();
            }
        }
        this.f199c.setActivity(activity);
    }

    /* renamed from: c */
    private AuthorizeAdapter m160c() {
        try {
            ActivityInfo activityInfo = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128);
            if (activityInfo.metaData != null) {
                if (!activityInfo.metaData.isEmpty()) {
                    String string = activityInfo.metaData.getString("AuthorizeAdapter");
                    if (string == null || string.length() <= 0) {
                        string = activityInfo.metaData.getString("Adapter");
                        if (string != null) {
                            if (string.length() <= 0) {
                            }
                        }
                    }
                    Object newInstance = Class.forName(string).newInstance();
                    if (!(newInstance instanceof AuthorizeAdapter)) {
                        return null;
                    }
                    return (AuthorizeAdapter) newInstance;
                }
            }
            return null;
        } catch (Throwable th) {
            SSDKLog.m645b().mo29787w(th);
            return null;
        }
    }

    public void onCreate() {
        if (this.f200d == null) {
            this.f200d = mo10553b();
            this.f200d.mo10533a(this);
            this.f200d.mo10527a(this.f199c.isNotitle());
            this.f199c.setBodyView(this.f200d.mo10530d());
            this.f199c.setWebView(this.f200d.mo10528b());
            TitleLayout c = this.f200d.mo10529c();
            this.f199c.setTitleView(c);
            String name = this.f181a.getPlatform().getName();
            this.f199c.setPlatformName(this.f181a.getPlatform().getName());
            try {
                Context context = getContext();
                c.getTvTitle().setText(ResHelper.getStringRes(context, "ssdk_" + name.toLowerCase()));
            } catch (Throwable unused) {
                SSDKLog.m645b().mo29787w(th);
            }
        }
        this.f199c.onCreate();
        AuthorizeAdapter authorizeAdapter = this.f199c;
        if (authorizeAdapter != null && !authorizeAdapter.isPopUpAnimationDisable()) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(550);
            scaleAnimation.setInterpolator(new C0706a());
            this.f200d.setAnimation(scaleAnimation);
        }
        disableScreenCapture();
        this.activity.setContentView(this.f200d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public RegisterView mo10553b() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.mo10526a().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        try {
                            new Instrumentation().sendKeyDownUpSync(4);
                        } catch (Throwable th) {
                            SSDKLog.m645b().mo29787w(th);
                            AuthorizeListener authorizeListener = C0702g.this.f181a.getAuthorizeListener();
                            if (authorizeListener != null) {
                                authorizeListener.onCancel();
                            }
                            C0702g.this.finish();
                        }
                    }
                }.start();
            }
        });
        this.f201e = registerView.mo10528b();
        WebSettings settings = this.f201e.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setSavePassword(false);
        this.f201e.setVerticalScrollBarEnabled(false);
        this.f201e.setHorizontalScrollBarEnabled(false);
        C0698c authorizeWebviewClient = this.f181a.getAuthorizeWebviewClient(this);
        String simpleName = authorizeWebviewClient != null ? authorizeWebviewClient.getClass().getSimpleName() : "";
        if ((!TextUtils.isEmpty(simpleName) && simpleName.equals("GooglePlusAuthorizeWebviewClient")) || ((!TextUtils.isEmpty(simpleName) && simpleName.contains("GooglePlus")) || simpleName.equals("YoutubeAuthorizeWebviewClient"))) {
            this.f201e.getSettings().setUserAgentString(((("Mozilla/5.0 (Linux; Android 5.1; m2 note Build/LMY47D) " + "AppleWebKit/537.36 (KHTML, like Gecko) ") + "Version/4.0 ") + "Chrome/40.0.2214.127 ") + "Mobile Safari/537.36");
        }
        this.f201e.setWebViewClient(authorizeWebviewClient);
        new Thread() {
            public void run() {
                try {
                    Message message = new Message();
                    message.what = 2;
                    if (SchedulerSupport.NONE.equals(DeviceHelper.getInstance(C0702g.this.activity).getDetailNetworkTypeForStatic())) {
                        message.arg1 = 1;
                        UIHandler.sendMessage(message, C0702g.this);
                        return;
                    }
                    if (ShareSDK.isRemoveCookieOnAuthorize()) {
                        CookieSyncManager.createInstance(C0702g.this.activity);
                        CookieManager.getInstance().removeAllCookie();
                    }
                    message.obj = C0702g.this.f181a.getAuthorizeUrl();
                    UIHandler.sendMessage(message, C0702g.this);
                } catch (Throwable th) {
                    SSDKLog.m645b().mo29787w(th);
                }
            }
        }.start();
        return registerView;
    }

    public boolean handleMessage(Message message) {
        if (message.what != 2) {
            return false;
        }
        if (message.arg1 == 1) {
            AuthorizeListener authorizeListener = this.f181a.getAuthorizeListener();
            if (authorizeListener == null) {
                return false;
            }
            authorizeListener.onError(new Throwable("Network error (platform: " + this.f181a.getPlatform().getName() + ")"));
            return false;
        }
        String str = (String) message.obj;
        if (TextUtils.isEmpty(str)) {
            finish();
            AuthorizeListener authorizeListener2 = this.f181a.getAuthorizeListener();
            if (authorizeListener2 == null) {
                return false;
            }
            authorizeListener2.onError(new Throwable("Authorize URL is empty (platform: " + this.f181a.getPlatform().getName() + ")"));
            return false;
        }
        this.f201e.loadUrl(str);
        return false;
    }

    public void OnResize(int i, int i2, int i3, int i4) {
        AuthorizeAdapter authorizeAdapter = this.f199c;
        if (authorizeAdapter != null) {
            authorizeAdapter.onResize(i, i2, i3, i4);
        }
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        AuthorizeListener authorizeListener;
        AuthorizeAdapter authorizeAdapter = this.f199c;
        boolean onKeyEvent = authorizeAdapter != null ? authorizeAdapter.onKeyEvent(i, keyEvent) : false;
        if (!onKeyEvent && i == 4 && keyEvent.getAction() == 0 && (authorizeListener = this.f181a.getAuthorizeListener()) != null) {
            authorizeListener.onCancel();
        }
        if (onKeyEvent) {
            return true;
        }
        return super.onKeyEvent(i, keyEvent);
    }

    public void onStart() {
        AuthorizeAdapter authorizeAdapter = this.f199c;
        if (authorizeAdapter != null) {
            authorizeAdapter.onStart();
        }
    }

    public void onPause() {
        AuthorizeAdapter authorizeAdapter = this.f199c;
        if (authorizeAdapter != null) {
            authorizeAdapter.onPause();
        }
    }

    public void onResume() {
        AuthorizeAdapter authorizeAdapter = this.f199c;
        if (authorizeAdapter != null) {
            authorizeAdapter.onResume();
        }
    }

    public void onStop() {
        AuthorizeAdapter authorizeAdapter = this.f199c;
        if (authorizeAdapter != null) {
            authorizeAdapter.onStop();
        }
    }

    public void onRestart() {
        AuthorizeAdapter authorizeAdapter = this.f199c;
        if (authorizeAdapter != null) {
            authorizeAdapter.onRestart();
        }
    }

    public boolean onFinish() {
        AuthorizeAdapter authorizeAdapter = this.f199c;
        if (authorizeAdapter != null) {
            return authorizeAdapter.onFinish();
        }
        WebView webView = this.f201e;
        if (webView != null) {
            webView.destroy();
            this.f201e.removeAllViews();
        }
        if (this.activity != null) {
            ((ViewGroup) this.activity.getWindow().getDecorView()).removeAllViews();
        }
        return super.onFinish();
    }

    public void onDestroy() {
        AuthorizeAdapter authorizeAdapter = this.f199c;
        if (authorizeAdapter != null) {
            authorizeAdapter.onDestroy();
        }
        WebView webView = this.f201e;
        if (webView != null) {
            webView.setFocusable(false);
        }
    }

    /* renamed from: cn.sharesdk.framework.authorize.g$a */
    /* compiled from: WebAuthorizeActivity */
    private static class C0706a implements Interpolator {

        /* renamed from: a */
        private float[] f205a;

        private C0706a() {
            this.f205a = new float[]{0.0f, 0.02692683f, 0.053847015f, 0.080753915f, 0.10764089f, 0.13450131f, 0.16132854f, 0.18811597f, 0.21485697f, 0.24154496f, 0.26817337f, 0.2947356f, 0.3212251f, 0.34763536f, 0.37395984f, 0.40019205f, 0.42632553f, 0.4523538f, 0.47827047f, 0.50406915f, 0.52974343f, 0.555287f, 0.5806936f, 0.60595685f, 0.6310707f, 0.65602875f, 0.68082494f, 0.70545316f, 0.72990733f, 0.75418144f, 0.7782694f, 0.8021654f, 0.8258634f, 0.8493577f, 0.8726424f, 0.89571184f, 0.9185602f, 0.94118196f, 0.9635715f, 0.9857233f, 1.0076319f, 1.0292919f, 1.0506978f, 1.0718446f, 1.0927268f, 1.1133395f, 1.1336775f, 1.1537358f, 1.1735094f, 1.1929934f, 1.1893399f, 1.1728106f, 1.1565471f, 1.1405534f, 1.1248333f, 1.1093911f, 1.0942302f, 1.0793544f, 1.0647675f, 1.050473f, 1.0364745f, 1.0227754f, 1.0093791f, 0.99628896f, 0.9835081f, 0.9710398f, 0.958887f, 0.9470527f, 0.93553996f, 0.9243516f, 0.91349024f, 0.90295863f, 0.90482706f, 0.9114033f, 0.91775465f, 0.9238795f, 0.9297765f, 0.93544406f, 0.9408808f, 0.94608533f, 0.95105654f, 0.955793f, 0.9602937f, 0.9645574f, 0.96858317f, 0.9723699f, 0.97591674f, 0.97922283f, 0.9822872f, 0.9851093f, 0.98768836f, 0.9900237f, 0.9921147f, 0.993961f, 0.99556196f, 0.9969173f, 0.9980267f, 0.99888986f, 0.99950653f, 0.9998766f, 1.0f};
        }

        public float getInterpolation(float f) {
            int i = (int) (f * 100.0f);
            if (i < 0) {
                i = 0;
            }
            if (i > 100) {
                i = 100;
            }
            return this.f205a[i];
        }
    }
}
