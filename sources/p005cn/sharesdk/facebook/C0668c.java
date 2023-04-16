package p005cn.sharesdk.facebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.LinearLayout;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.mob.tools.FakeActivity;
import com.mob.tools.log.NLog;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.facebook.c */
/* compiled from: FacebookOfficialShareImage */
public class C0668c extends FakeActivity implements FacebookCallback<Sharer.Result> {

    /* renamed from: a */
    private ShareDialog f94a;

    /* renamed from: b */
    private CallbackManager f95b;

    /* renamed from: c */
    private PlatformActionListener f96c;

    /* renamed from: d */
    private Platform f97d;

    /* renamed from: e */
    private Bitmap f98e;

    /* renamed from: f */
    private String f99f;

    public C0668c(Platform platform, PlatformActionListener platformActionListener) {
        try {
            this.f97d = platform;
            this.f96c = platformActionListener;
            SSDKLog.m645b().mo29786w("Facebook doShare official FacebookOfficialShareImage construction");
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29786w("Facebook doShare official FacebookOfficialShareImage catch:  " + th);
        }
    }

    /* renamed from: a */
    public void mo10193a(Bitmap bitmap) {
        this.f98e = bitmap;
    }

    /* renamed from: a */
    public void mo10196a(String str) {
        this.f99f = str;
    }

    public void onCreate() {
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m645b().mo29769d(e);
            NLog b = SSDKLog.m645b();
            b.mo29786w("Facebook doShare official FacebookOfficialShareImage onCreate catch  " + e.getMessage());
        }
        SSDKLog.m645b().mo29786w("Facebook FacebookOfficialHelper onCreate");
        this.f95b = CallbackManager.Factory.create();
        this.f94a = new ShareDialog(this.activity);
        this.f94a.registerCallback(this.f95b, this);
        mo10194a(this.f98e, this.f99f);
    }

    /* renamed from: a */
    public void mo10194a(Bitmap bitmap, String str) {
        SharePhotoContent sharePhotoContent;
        try {
            SSDKLog.m645b().mo29786w("Facebook FacebookOfficialHelper shareImageOfficial");
            if (bitmap != null) {
                SharePhoto build = new SharePhoto.Builder().setBitmap(bitmap).build();
                if (TextUtils.isEmpty(str)) {
                    sharePhotoContent = new SharePhotoContent.Builder().addPhoto(build).build();
                } else {
                    sharePhotoContent = ((SharePhotoContent.Builder) new SharePhotoContent.Builder().addPhoto(build).setShareHashtag(new ShareHashtag.Builder().setHashtag(str).build())).build();
                }
                if (ShareDialog.canShow(SharePhotoContent.class)) {
                    if (this.f94a != null) {
                        this.f94a.show(sharePhotoContent);
                        SSDKLog.m645b().mo29786w("Facebook FacebookOfficialHelper shareImageOfficial shareDialog.show");
                    } else if (this.f96c != null) {
                        SSDKLog.m645b().mo29786w("Facebook doShare shareImageOfficial shareDialog is null ");
                        this.f96c.onError(this.f97d, 9, new Throwable("shareDialog is null"));
                        finish();
                    }
                } else if (this.f96c != null) {
                    SSDKLog.m645b().mo29786w("Facebook doShare shareImageOfficial ShareDialog.canShow(SharePhotoContent.class) is false, are you login first? ");
                    this.f96c.onError(this.f97d, 9, new Throwable("ShareDialog.canShow(SharePhotoContent.class) is false, are you login first?"));
                    finish();
                }
            } else if (this.f96c != null) {
                SSDKLog.m645b().mo29786w("Facebook doShare shareImageOfficial set bitmap image is error, please check ");
                this.f96c.onError(this.f97d, 9, new Throwable("set bitmap image is error, please check"));
                finish();
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29786w("Facebook doShare shareImageOfficial shareImageOfficial catch ");
            PlatformActionListener platformActionListener = this.f96c;
            if (platformActionListener != null) {
                platformActionListener.onError(this.f97d, 9, th);
            }
            finish();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.f95b.onActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
        SSDKLog.m645b().mo29786w("Facebook doShare  FacebookOfficialShareImage onActivityResult ");
    }

    /* renamed from: a */
    public void onSuccess(Sharer.Result result) {
        PlatformActionListener platformActionListener = this.f96c;
        if (platformActionListener != null) {
            platformActionListener.onComplete(this.f97d, 9, (HashMap<String, Object>) null);
        }
        SSDKLog.m645b().mo29786w("Facebook doShare  FacebookOfficialShareImage onSuccess ");
        finish();
    }

    public void onCancel() {
        PlatformActionListener platformActionListener = this.f96c;
        if (platformActionListener != null) {
            platformActionListener.onCancel(this.f97d, 9);
        }
        SSDKLog.m645b().mo29786w("Facebook doShare  FacebookOfficialShareImage onCancel ");
        finish();
    }

    public void onError(FacebookException facebookException) {
        PlatformActionListener platformActionListener = this.f96c;
        if (platformActionListener != null) {
            platformActionListener.onError(this.f97d, 9, facebookException);
        }
        SSDKLog.m645b().mo29786w("Facebook doShare  FacebookOfficialShareImage onError ");
        finish();
    }

    public void onResume() {
        super.onResume();
        SSDKLog.m645b().mo29786w("Facebook doShare  FacebookOfficialShareImage onResume ");
    }

    public void onPause() {
        super.onPause();
        SSDKLog.m645b().mo29786w("Facebook doShare  FacebookOfficialShareImage onPause ");
    }

    public void onStop() {
        super.onStop();
        SSDKLog.m645b().mo29786w("Facebook doShare  FacebookOfficialShareImage onStop ");
    }

    public void onDestroy() {
        super.onDestroy();
        SSDKLog.m645b().mo29786w("Facebook doShare  FacebookOfficialShareImage onDestroy ");
    }
}
