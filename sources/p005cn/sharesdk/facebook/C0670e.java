package p005cn.sharesdk.facebook;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.LinearLayout;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.mob.tools.FakeActivity;
import com.mob.tools.log.NLog;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.facebook.e */
/* compiled from: FacebookOfficialShareWebPage */
public class C0670e extends FakeActivity implements FacebookCallback<Sharer.Result> {

    /* renamed from: a */
    private ShareDialog f106a;

    /* renamed from: b */
    private CallbackManager f107b;

    /* renamed from: c */
    private PlatformActionListener f108c;

    /* renamed from: d */
    private Platform f109d;

    public C0670e(Platform platform, PlatformActionListener platformActionListener) {
        try {
            this.f109d = platform;
            this.f108c = platformActionListener;
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("FacebookOfficialShare catch " + th, new Object[0]);
        }
    }

    public void onCreate() {
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m645b().mo29769d(e);
        }
        SSDKLog.m645b().mo29786w("FacebookOfficialHelper onCreate");
        this.f107b = CallbackManager.Factory.create();
        this.f106a = new ShareDialog(this.activity);
        this.f106a.registerCallback(this.f107b, this);
        Intent intent = this.activity.getIntent();
        String stringExtra = intent.getStringExtra(Facebook.PARAMS_LINKURL);
        String stringExtra2 = intent.getStringExtra(Facebook.PARAMS_HASHTAG);
        String stringExtra3 = intent.getStringExtra(Facebook.PARAMS_QUOTE);
        NLog b = SSDKLog.m645b();
        b.mo29786w("Share params url is: " + stringExtra + " hashtag: " + stringExtra2 + " quote: " + stringExtra3);
        mo10202a(stringExtra, stringExtra2, stringExtra3);
    }

    /* renamed from: a */
    public void mo10202a(String str, String str2, String str3) {
        ShareLinkContent shareLinkContent;
        try {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    shareLinkContent = ((ShareLinkContent.Builder) ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentUrl(Uri.parse(str))).setShareHashtag(new ShareHashtag.Builder().setHashtag(str2).build())).setQuote(str3).build();
                } else if (!TextUtils.isEmpty(str2)) {
                    shareLinkContent = ((ShareLinkContent.Builder) ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentUrl(Uri.parse(str))).setShareHashtag(new ShareHashtag.Builder().setHashtag(str2).build())).build();
                } else if (!TextUtils.isEmpty(str3)) {
                    shareLinkContent = ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentUrl(Uri.parse(str))).setQuote(str3).build();
                } else {
                    shareLinkContent = ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentUrl(Uri.parse(str))).build();
                }
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    if (this.f106a != null) {
                        this.f106a.show(shareLinkContent);
                    } else if (this.f108c != null) {
                        this.f108c.onError(this.f109d, 9, new Throwable("shareDialog is null"));
                        finish();
                    }
                } else if (this.f108c != null) {
                    this.f108c.onError(this.f109d, 9, new Throwable("ShareDialog.canShow(ShareLinkContent.class) is false, are you login first?"));
                    finish();
                }
            } else if (this.f108c != null) {
                this.f108c.onError(this.f109d, 9, new Throwable("share link params is null"));
                finish();
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29786w("shareLinkOfficial catch ");
            PlatformActionListener platformActionListener = this.f108c;
            if (platformActionListener != null) {
                platformActionListener.onError(this.f109d, 9, th);
            }
            finish();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.f107b.onActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    /* renamed from: a */
    public void onSuccess(Sharer.Result result) {
        PlatformActionListener platformActionListener = this.f108c;
        if (platformActionListener != null) {
            platformActionListener.onComplete(this.f109d, 9, (HashMap<String, Object>) null);
        }
        finish();
    }

    public void onCancel() {
        PlatformActionListener platformActionListener = this.f108c;
        if (platformActionListener != null) {
            platformActionListener.onCancel(this.f109d, 9);
        }
        finish();
    }

    public void onError(FacebookException facebookException) {
        PlatformActionListener platformActionListener = this.f108c;
        if (platformActionListener != null) {
            platformActionListener.onError(this.f109d, 9, facebookException);
        }
        finish();
    }

    public void onResume() {
        super.onResume();
        SSDKLog.m645b().mo29786w("FacebookOfficialShareWebPage onResume");
    }

    public void onPause() {
        super.onPause();
        SSDKLog.m645b().mo29786w("FacebookOfficialShareWebPage onPause");
    }

    public void onStop() {
        super.onStop();
        SSDKLog.m645b().mo29786w("FacebookOfficialShareWebPage onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        SSDKLog.m645b().mo29786w("FacebookOfficialShareWebPage onDestroy");
    }
}
