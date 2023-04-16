package p005cn.sharesdk.facebook;

import android.content.Intent;
import android.widget.LinearLayout;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.mob.tools.FakeActivity;
import com.mob.tools.log.NLog;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.facebook.b */
/* compiled from: FacebookOfficialAuth */
public class C0666b extends FakeActivity {

    /* renamed from: a */
    private CallbackManager f90a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public PlatformActionListener f91b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public Platform f92c;

    public C0666b(PlatformActionListener platformActionListener, Platform platform) {
        try {
            this.f90a = CallbackManager.Factory.create();
            this.f91b = platformActionListener;
            this.f92c = platform;
            SSDKLog.m645b().mo29786w("FacebookOfficialAuth constuction ");
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("FacebookOfficialAuth catch " + th, new Object[0]);
        }
    }

    public void onCreate() {
        SSDKLog.m645b().mo29786w("FacebookOfficialAuth onCreate ");
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m645b().mo29769d(e);
            NLog b = SSDKLog.m645b();
            b.mo29786w("FacebookOfficialAuth onCreate exception " + e.getMessage());
        }
        try {
            mo10182a();
            SSDKLog.m645b().mo29786w("FacebookOfficialAuth onCreate loginManager() ");
        } catch (Throwable th) {
            PlatformActionListener platformActionListener = this.f91b;
            if (platformActionListener != null) {
                platformActionListener.onError(this.f92c, 1, th);
            }
            NLog b2 = SSDKLog.m645b();
            b2.mo29786w("FacebookOfficialAuth onCreate catch: " + th);
            finish();
        }
    }

    /* renamed from: a */
    public void mo10182a() {
        SSDKLog.m645b().mo29786w("FacebookOfficialAuth loginManager");
        LoginManager.getInstance().logIn(this.activity, (Collection<String>) Collections.singleton("email"));
        LoginManager.getInstance().registerCallback(this.f90a, new FacebookCallback<LoginResult>() {
            /* renamed from: a */
            public void onSuccess(LoginResult loginResult) {
                String valueOf = String.valueOf(loginResult.getAccessToken().getToken());
                String valueOf2 = String.valueOf(loginResult.getAccessToken().getExpires());
                String valueOf3 = String.valueOf(loginResult.getAccessToken().getUserId());
                String valueOf4 = String.valueOf(loginResult.getAccessToken().getGraphDomain());
                String valueOf5 = String.valueOf(loginResult.getAccessToken().getPermissions());
                String valueOf6 = String.valueOf(loginResult.getAccessToken().getApplicationId());
                if (C0666b.this.f92c.getDb() != null) {
                    C0666b.this.f92c.getDb().putToken(valueOf);
                    C0666b.this.f92c.getDb().put("expires", valueOf2);
                    C0666b.this.f92c.getDb().putUserId(valueOf3);
                    C0666b.this.f92c.getDb().put("GraphDomain", valueOf4);
                    C0666b.this.f92c.getDb().put("Permissions", valueOf5);
                    C0666b.this.f92c.getDb().put("ApplicationId", valueOf6);
                }
                if (C0666b.this.f91b != null) {
                    C0666b.this.f91b.onComplete(C0666b.this.f92c, 1, (HashMap<String, Object>) null);
                }
                SSDKLog.m645b().mo29786w("FacebookOfficialAuth onSuccess finish");
                C0666b.this.finish();
            }

            public void onCancel() {
                if (C0666b.this.f91b != null) {
                    C0666b.this.f91b.onCancel(C0666b.this.f92c, 1);
                }
                SSDKLog.m645b().mo29786w("FacebookOfficialAuth onCancel finish");
                C0666b.this.finish();
            }

            public void onError(FacebookException facebookException) {
                if (C0666b.this.f91b != null) {
                    C0666b.this.f91b.onError(C0666b.this.f92c, 1, facebookException);
                }
                SSDKLog.m645b().mo29786w("FacebookOfficialAuth onError finish");
                C0666b.this.finish();
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.f90a.onActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
        SSDKLog.m645b().mo29786w("FacebookOfficialAuth onActivityResult");
    }

    public void onResume() {
        super.onResume();
        SSDKLog.m645b().mo29786w("FacebookOfficialAuth onResume");
    }

    public void onPause() {
        super.onPause();
        SSDKLog.m645b().mo29786w("FacebookOfficialAuth onPause");
    }

    public void onStop() {
        super.onStop();
        SSDKLog.m645b().mo29786w("FacebookOfficialAuth onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        SSDKLog.m645b().mo29786w("FacebookOfficialAuth onDestroy");
    }
}
