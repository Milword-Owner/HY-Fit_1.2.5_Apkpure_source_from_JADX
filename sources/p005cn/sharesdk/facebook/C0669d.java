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
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;
import com.mob.tools.FakeActivity;
import com.mob.tools.log.NLog;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.facebook.d */
/* compiled from: FacebookOfficialShareVideo */
public class C0669d extends FakeActivity implements FacebookCallback<Sharer.Result> {

    /* renamed from: a */
    private ShareDialog f100a;

    /* renamed from: b */
    private CallbackManager f101b;

    /* renamed from: c */
    private PlatformActionListener f102c;

    /* renamed from: d */
    private Platform f103d;

    /* renamed from: e */
    private Uri f104e;

    /* renamed from: f */
    private String f105f;

    public C0669d(Platform platform, PlatformActionListener platformActionListener) {
        try {
            this.f103d = platform;
            this.f102c = platformActionListener;
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("FacebookOfficialShare catch " + th, new Object[0]);
        }
    }

    /* renamed from: a */
    public void mo10197a(Uri uri) {
        this.f104e = uri;
    }

    /* renamed from: a */
    public void mo10200a(String str) {
        this.f105f = str;
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
        this.f101b = CallbackManager.Factory.create();
        this.f100a = new ShareDialog(this.activity);
        this.f100a.registerCallback(this.f101b, this);
        mo10198a(this.f104e, this.f105f);
    }

    /* renamed from: a */
    public void mo10198a(Uri uri, String str) {
        ShareVideoContent shareVideoContent;
        if (uri != null) {
            try {
                ShareVideo build = new ShareVideo.Builder().setLocalUrl(uri).build();
                if (TextUtils.isEmpty(str)) {
                    shareVideoContent = new ShareVideoContent.Builder().setVideo(build).build();
                } else {
                    shareVideoContent = ((ShareVideoContent.Builder) new ShareVideoContent.Builder().setVideo(build).setShareHashtag(new ShareHashtag.Builder().setHashtag(str).build())).setContentTitle("contentTitle").setContentDescription("contentText").build();
                }
                if (ShareDialog.canShow(ShareVideoContent.class)) {
                    if (this.f100a != null) {
                        this.f100a.show(shareVideoContent);
                    } else if (this.f102c != null) {
                        this.f102c.onError(this.f103d, 9, new Throwable("shareDialog is null"));
                        finish();
                    }
                } else if (this.f102c != null) {
                    this.f102c.onError(this.f103d, 9, new Throwable("ShareDialog.canShow(ShareVideoContent.class) is false, are you login first?"));
                    finish();
                }
            } catch (Throwable th) {
                SSDKLog.m645b().mo29786w("shareVideoOfficial catch ");
                PlatformActionListener platformActionListener = this.f102c;
                if (platformActionListener != null) {
                    platformActionListener.onError(this.f103d, 9, th);
                }
                finish();
            }
        } else if (this.f102c != null) {
            this.f102c.onError(this.f103d, 9, new Throwable("share video paramas is null"));
            finish();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.f101b.onActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    /* renamed from: a */
    public void onSuccess(Sharer.Result result) {
        PlatformActionListener platformActionListener = this.f102c;
        if (platformActionListener != null) {
            platformActionListener.onComplete(this.f103d, 9, (HashMap<String, Object>) null);
        }
        finish();
    }

    public void onCancel() {
        PlatformActionListener platformActionListener = this.f102c;
        if (platformActionListener != null) {
            platformActionListener.onCancel(this.f103d, 9);
        }
        finish();
    }

    public void onError(FacebookException facebookException) {
        PlatformActionListener platformActionListener = this.f102c;
        if (platformActionListener != null) {
            platformActionListener.onError(this.f103d, 9, facebookException);
        }
        finish();
    }

    public void onResume() {
        super.onResume();
        SSDKLog.m645b().mo29786w("FacebookOfficialShareVideo onResume");
    }

    public void onPause() {
        super.onPause();
        SSDKLog.m645b().mo29786w("FacebookOfficialShareVideo onPause");
    }

    public void onStop() {
        super.onStop();
        SSDKLog.m645b().mo29786w("FacebookOfficialShareVideo onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        SSDKLog.m645b().mo29786w("FacebookOfficialShareVideo onDestroy");
    }
}
