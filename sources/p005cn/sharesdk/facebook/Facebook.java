package p005cn.sharesdk.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.authorize.AuthorizeListener;
import p005cn.sharesdk.framework.p007b.p009b.C0720f;
import p005cn.sharesdk.framework.utils.C0808f;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.facebook.Facebook */
public class Facebook extends Platform {
    public static final String NAME = "Facebook";
    public static final String PARAMS_HASHTAG = "params_Hashtag";
    public static final String PARAMS_LINKURL = "params_linkurl";
    public static final String PARAMS_QUOTE = "params_Quote";

    /* renamed from: a */
    private String f77a;

    /* renamed from: b */
    private String f78b;

    /* renamed from: c */
    private boolean f79c;

    /* renamed from: d */
    private boolean f80d;

    /* renamed from: e */
    private boolean f81e;

    /* renamed from: cn.sharesdk.facebook.Facebook$ShareParams */
    public static class ShareParams extends Platform.ShareParams {
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    public int getPlatformId() {
        return 10;
    }

    public int getVersion() {
        return 2;
    }

    public boolean hasShareCallback() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void initDevInfo(String str) {
        this.f77a = getDevinfo("ConsumerKey");
        this.f78b = getDevinfo("RedirectUrl");
        this.f79c = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(getDevinfo("ShareByAppClient"));
        this.f81e = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(getDevinfo("BypassApproval"));
        NLog b = SSDKLog.m645b();
        b.mo29786w("Facebook initDevInfo ShareByAppClient value is: " + getDevinfo("ShareByAppClient"));
        if (!TextUtils.isEmpty(getDevinfo("OfficialVersion"))) {
            this.f80d = true;
            NLog b2 = SSDKLog.m645b();
            b2.mo29786w("Facebook Official value is: " + getDevinfo("Official"));
            return;
        }
        this.f80d = false;
    }

    public String getName() {
        return NAME;
    }

    /* access modifiers changed from: protected */
    public void setNetworkDevinfo() {
        this.f77a = getNetworkDevinfo("api_key", "ConsumerKey");
        this.f78b = getNetworkDevinfo(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, "RedirectUrl");
        if (TextUtils.isEmpty(this.f78b)) {
            this.f78b = ServerProtocol.DIALOG_REDIRECT_URI;
        }
    }

    /* access modifiers changed from: protected */
    public void doAuthorize(String[] strArr) {
        if (this.f80d) {
            try {
                SSDKLog.m645b().mo29786w("Facebook doAuthorize by official");
                new C0666b(this.listener, this).show(MobSDK.getContext(), (Intent) null);
                SSDKLog.m645b().mo29786w("Facebook doAuthorize ");
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 1, th);
                }
                NLog b = SSDKLog.m645b();
                b.mo29786w("Facebook doAuthorize catch: " + th);
            }
        } else {
            SSDKLog.m645b().mo29786w("Facebook doAuthorize by origianl");
            final C0674h a = C0674h.m65a((Platform) this);
            a.mo10214a(this.f77a);
            a.mo10220c(this.f78b);
            a.mo10216a(strArr);
            a.mo10213a((AuthorizeListener) new AuthorizeListener() {
                public void onError(Throwable th) {
                    if (Facebook.this.listener != null) {
                        Facebook.this.listener.onError(Facebook.this, 1, th);
                    }
                    NLog b = SSDKLog.m645b();
                    b.mo29786w("Facebook doAuthorize by origianl onError " + th);
                }

                public void onComplete(Bundle bundle) {
                    SSDKLog.m645b().mo29786w("Facebook doAuthorize by origianl onComplete ");
                    String string = bundle.getString("oauth_token");
                    int i = bundle.getInt("oauth_token_expires");
                    if (i == 0) {
                        try {
                            i = ResHelper.parseInt(String.valueOf(bundle.get(AccessToken.EXPIRES_IN_KEY)));
                        } catch (Throwable th) {
                            SSDKLog.m645b().mo29769d(th);
                            i = 0;
                        }
                    }
                    if (TextUtils.isEmpty(string)) {
                        string = bundle.getString("access_token");
                    }
                    Facebook.this.f149db.putToken(string);
                    Facebook.this.f149db.putExpiresIn((long) i);
                    a.mo10215a(string, String.valueOf(i));
                    Facebook.this.afterRegister(1, (Object) null);
                }

                public void onCancel() {
                    if (Facebook.this.listener != null) {
                        Facebook.this.listener.onCancel(Facebook.this, 1);
                    }
                    SSDKLog.m645b().mo29786w("Facebook doAuthorize by origianl onCancel ");
                }
            }, isSSODisable());
        }
    }

    public boolean isClientValid() {
        C0674h a = C0674h.m65a((Platform) this);
        a.mo10214a(this.f77a);
        return a.mo10219b();
    }

    /* access modifiers changed from: protected */
    public boolean checkAuthorize(int i, Object obj) {
        SSDKLog.m645b().mo29786w("Facebook checkAuthorize ");
        NLog b = SSDKLog.m645b();
        b.mo29786w("Facebook checkAuthorize action == " + String.valueOf(i));
        NLog b2 = SSDKLog.m645b();
        b2.mo29786w("Facebook checkAuthorize shareByAppClient == " + String.valueOf(this.f79c));
        NLog b3 = SSDKLog.m645b();
        b3.mo29786w("Facebook checkAuthorize isClientValid == " + String.valueOf(isClientValid()));
        if (i == 9 && this.f79c && isClientValid()) {
            SSDKLog.m645b().mo29786w("Facebook checkAuthorize ACTION_SHARE return true");
            return true;
        } else if (i == 6) {
            SSDKLog.m645b().mo29786w("Facebook checkAuthorize ACTION_FOLLOWING_USER return true");
            return true;
        } else {
            if (isAuthValid()) {
                SSDKLog.m645b().mo29786w("Facebook checkAuthorize isAuthValid return true");
                C0674h a = C0674h.m65a((Platform) this);
                a.mo10214a(this.f77a);
                String token = this.f149db.getToken();
                String valueOf = String.valueOf(this.f149db.getExpiresIn());
                if (!(token == null || valueOf == null)) {
                    a.mo10215a(token, valueOf);
                    if (a.mo10217a()) {
                        return true;
                    }
                }
            } else if ((obj instanceof Platform.ShareParams) && ((Platform.ShareParams) obj).getShareType() == 4) {
                SSDKLog.m645b().mo29786w("Facebook checkAuthorize SHARE_WEBPAGE return true");
                return true;
            }
            innerAuthorize(i, obj);
            SSDKLog.m645b().mo29786w("Facebook checkAuthorize return false");
            return false;
        }
    }

    /* renamed from: a */
    private void m19a(Platform platform, Platform.ShareParams shareParams, PlatformActionListener platformActionListener) {
        try {
            C0808f fVar = new C0808f();
            fVar.mo10935a("com.facebook.katana", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias");
            if (shareParams.getShareType() != 6) {
                fVar.mo10932a(shareParams, platform);
            } else if (!TextUtils.isEmpty(shareParams.getFilePath())) {
                fVar.mo10934a(shareParams.getFilePath(), platform, platformActionListener);
            } else if (platformActionListener != null) {
                platformActionListener.onError(platform, 9, new Throwable("Share type is VIDEO, But FilePath is null"));
                return;
            } else {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("ShareParams", shareParams);
            platformActionListener.onComplete(platform, 9, hashMap);
        } catch (Throwable th) {
            if (platformActionListener != null) {
                platformActionListener.onError(platform, 9, th);
            }
            SSDKLog.m645b().mo29770d(th, "Facebook share byPassShare catch ", new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doShare(final p005cn.sharesdk.framework.Platform.ShareParams r14) {
        /*
            r13 = this;
            com.mob.tools.log.NLog r0 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            java.lang.String r1 = "Facebook doShare"
            r0.mo29786w((java.lang.String) r1)
            cn.sharesdk.facebook.h r0 = p005cn.sharesdk.facebook.C0674h.m65a((p005cn.sharesdk.framework.Platform) r13)
            java.lang.String r1 = r13.f77a
            r0.mo10214a((java.lang.String) r1)
            r1 = 9
            java.lang.String r2 = r14.getText()     // Catch:{ Throwable -> 0x047b }
            r3 = 0
            r13.getShortLintk(r2, r3)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = r14.getImagePath()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r4 = r14.getImageUrl()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r5 = r14.getUrl()     // Catch:{ Throwable -> 0x047b }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Throwable -> 0x047b }
            r6.<init>()     // Catch:{ Throwable -> 0x047b }
            java.lang.String[] r7 = r14.getImageArray()     // Catch:{ Throwable -> 0x047b }
            if (r7 != 0) goto L_0x0034
            goto L_0x003c
        L_0x0034:
            java.lang.String[] r6 = r14.getImageArray()     // Catch:{ Throwable -> 0x047b }
            java.util.List r6 = java.util.Arrays.asList(r6)     // Catch:{ Throwable -> 0x047b }
        L_0x003c:
            boolean r7 = r13.f81e     // Catch:{ Throwable -> 0x047b }
            if (r7 == 0) goto L_0x006f
            boolean r0 = r13.f79c     // Catch:{ Throwable -> 0x047b }
            if (r0 == 0) goto L_0x0055
            com.mob.tools.log.NLog r0 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "Facebook bypassApproval "
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r0.mo29768d(r2, r3)     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener     // Catch:{ Throwable -> 0x047b }
            r13.m19a((p005cn.sharesdk.framework.Platform) r13, (p005cn.sharesdk.framework.Platform.ShareParams) r14, (p005cn.sharesdk.framework.PlatformActionListener) r0)     // Catch:{ Throwable -> 0x047b }
            goto L_0x006e
        L_0x0055:
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.String r0 = "Set share bypassApproval but no client or ShareByAppClient is false"
            if (r14 == 0) goto L_0x0065
            java.lang.Throwable r14 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            r14.<init>(r0)     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r2 = r13.listener     // Catch:{ Throwable -> 0x047b }
            r2.onError(r13, r1, r14)     // Catch:{ Throwable -> 0x047b }
        L_0x0065:
            com.mob.tools.log.NLog r14 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r14.mo29768d(r0, r2)     // Catch:{ Throwable -> 0x047b }
        L_0x006e:
            return
        L_0x006f:
            boolean r7 = r13.f79c     // Catch:{ Throwable -> 0x047b }
            if (r7 == 0) goto L_0x03da
            com.mob.tools.log.NLog r7 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r8 = "Facebook doShare 应用邀请功能"
            r7.mo29786w((java.lang.String) r8)     // Catch:{ Throwable -> 0x047b }
            int r7 = r14.getShareType()     // Catch:{ Throwable -> 0x047b }
            r8 = 7
            r9 = 0
            if (r7 != r8) goto L_0x009b
            cn.sharesdk.facebook.i r0 = new cn.sharesdk.facebook.i     // Catch:{ Throwable -> 0x047b }
            r0.<init>()     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r2 = r13.listener     // Catch:{ Throwable -> 0x047b }
            r0.mo10229a(r2, r13, r14)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r14 = r13.f77a     // Catch:{ Throwable -> 0x047b }
            r0.mo10230a(r14)     // Catch:{ Throwable -> 0x047b }
            android.content.Context r14 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x047b }
            r0.show(r14, r9)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x009b:
            boolean r7 = r13.f80d     // Catch:{ Throwable -> 0x047b }
            r8 = 1
            if (r7 != r8) goto L_0x029a
            com.mob.tools.log.NLog r6 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = "Facebook FacebookOfficialHelper shareImageOfficiall"
            r6.mo29786w((java.lang.String) r7)     // Catch:{ Throwable -> 0x047b }
            int r6 = r14.getShareType()     // Catch:{ Throwable -> 0x047b }
            r7 = 2
            if (r6 != r7) goto L_0x01d4
            com.mob.tools.log.NLog r6 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = "Facebook share by official SHARE_IMAGE"
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r6.mo29768d(r7, r8)     // Catch:{ Throwable -> 0x047b }
            android.graphics.Bitmap r6 = r14.getImageData()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = r14.getHashtag()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r8 = r14.getImagePath()     // Catch:{ Throwable -> 0x047b }
            if (r6 == 0) goto L_0x00e9
            com.mob.tools.log.NLog r14 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r0 = "Facebook share by official that picImageData"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r14.mo29768d(r0, r2)     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.facebook.c r14 = new cn.sharesdk.facebook.c     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener     // Catch:{ Throwable -> 0x047b }
            r14.<init>(r13, r0)     // Catch:{ Throwable -> 0x047b }
            r14.mo10193a((android.graphics.Bitmap) r6)     // Catch:{ Throwable -> 0x047b }
            r14.mo10196a((java.lang.String) r7)     // Catch:{ Throwable -> 0x047b }
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x047b }
            r14.show(r0, r9)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x00e9:
            boolean r6 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Throwable -> 0x047b }
            if (r6 != 0) goto L_0x013d
            com.mob.tools.log.NLog r14 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r0 = "Facebook share by official that ImagePath"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0118 }
            r14.mo29768d(r0, r2)     // Catch:{ Throwable -> 0x0118 }
            java.io.FileInputStream r14 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0118 }
            r14.<init>(r8)     // Catch:{ Throwable -> 0x0118 }
            android.graphics.Bitmap r14 = android.graphics.BitmapFactory.decodeStream(r14)     // Catch:{ Throwable -> 0x0118 }
            cn.sharesdk.facebook.c r0 = new cn.sharesdk.facebook.c     // Catch:{ Throwable -> 0x0118 }
            cn.sharesdk.framework.PlatformActionListener r2 = r13.listener     // Catch:{ Throwable -> 0x0118 }
            r0.<init>(r13, r2)     // Catch:{ Throwable -> 0x0118 }
            r0.mo10193a((android.graphics.Bitmap) r14)     // Catch:{ Throwable -> 0x0118 }
            r0.mo10196a((java.lang.String) r7)     // Catch:{ Throwable -> 0x0118 }
            android.content.Context r14 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0118 }
            r0.show(r14, r9)     // Catch:{ Throwable -> 0x0118 }
            goto L_0x013c
        L_0x0118:
            r14 = move-exception
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r0 == 0) goto L_0x013c
            com.mob.tools.log.NLog r0 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x047b }
            r2.<init>()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r4 = "Facebook share image by imagePath catch: "
            r2.append(r4)     // Catch:{ Throwable -> 0x047b }
            r2.append(r14)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x047b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r0.mo29768d(r2, r3)     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener     // Catch:{ Throwable -> 0x047b }
            r0.onError(r13, r1, r14)     // Catch:{ Throwable -> 0x047b }
        L_0x013c:
            return
        L_0x013d:
            boolean r6 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x047b }
            if (r6 != 0) goto L_0x01ba
            com.mob.tools.log.NLog r14 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x0195 }
            java.lang.String r0 = "Facebook share by official that imageUrl"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0195 }
            r14.mo29768d(r0, r2)     // Catch:{ Throwable -> 0x0195 }
            android.content.Context r14 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0174 }
            java.lang.String r14 = com.mob.tools.utils.BitmapHelper.downloadBitmap(r14, r4)     // Catch:{ Throwable -> 0x0174 }
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0195 }
            r0.<init>(r14)     // Catch:{ Throwable -> 0x0195 }
            android.graphics.Bitmap r14 = android.graphics.BitmapFactory.decodeStream(r0)     // Catch:{ Throwable -> 0x0195 }
            cn.sharesdk.facebook.c r0 = new cn.sharesdk.facebook.c     // Catch:{ Throwable -> 0x0195 }
            cn.sharesdk.framework.PlatformActionListener r2 = r13.listener     // Catch:{ Throwable -> 0x0195 }
            r0.<init>(r13, r2)     // Catch:{ Throwable -> 0x0195 }
            r0.mo10193a((android.graphics.Bitmap) r14)     // Catch:{ Throwable -> 0x0195 }
            r0.mo10196a((java.lang.String) r7)     // Catch:{ Throwable -> 0x0195 }
            android.content.Context r14 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0195 }
            r0.show(r14, r9)     // Catch:{ Throwable -> 0x0195 }
            return
        L_0x0174:
            r14 = move-exception
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener     // Catch:{ Throwable -> 0x0195 }
            if (r0 == 0) goto L_0x0194
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x0195 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0195 }
            r2.<init>()     // Catch:{ Throwable -> 0x0195 }
            java.lang.String r4 = "Picture download catch: "
            r2.append(r4)     // Catch:{ Throwable -> 0x0195 }
            r2.append(r14)     // Catch:{ Throwable -> 0x0195 }
            java.lang.String r14 = r2.toString()     // Catch:{ Throwable -> 0x0195 }
            r0.<init>(r14)     // Catch:{ Throwable -> 0x0195 }
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x0195 }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x0195 }
        L_0x0194:
            return
        L_0x0195:
            r14 = move-exception
            com.mob.tools.log.NLog r0 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x047b }
            r2.<init>()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r4 = "Facebook share image by imageUrl catch: "
            r2.append(r4)     // Catch:{ Throwable -> 0x047b }
            r2.append(r14)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x047b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r0.mo29768d(r2, r3)     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r0 == 0) goto L_0x01b9
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener     // Catch:{ Throwable -> 0x047b }
            r0.onError(r13, r1, r14)     // Catch:{ Throwable -> 0x047b }
        L_0x01b9:
            return
        L_0x01ba:
            cn.sharesdk.framework.PlatformActionListener r3 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r3 == 0) goto L_0x03da
            com.mob.tools.log.NLog r14 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r0 = "Facebook doShare official please set imageData params"
            r14.mo29786w((java.lang.String) r0)     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "please set imageData or imagePath or imageUrl params"
            r0.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x01d4:
            int r6 = r14.getShareType()     // Catch:{ Throwable -> 0x047b }
            r7 = 6
            if (r6 != r7) goto L_0x021f
            com.mob.tools.log.NLog r6 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = "Facebook share by official that SHARE_VIDEO"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r6.mo29768d(r7, r3)     // Catch:{ Throwable -> 0x047b }
            android.net.Uri r3 = r14.getVideoUri()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r6 = r14.getHashtag()     // Catch:{ Throwable -> 0x047b }
            if (r3 == 0) goto L_0x0205
            cn.sharesdk.facebook.d r14 = new cn.sharesdk.facebook.d     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener     // Catch:{ Throwable -> 0x047b }
            r14.<init>(r13, r0)     // Catch:{ Throwable -> 0x047b }
            r14.mo10197a((android.net.Uri) r3)     // Catch:{ Throwable -> 0x047b }
            r14.mo10200a((java.lang.String) r6)     // Catch:{ Throwable -> 0x047b }
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x047b }
            r14.show(r0, r9)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x0205:
            cn.sharesdk.framework.PlatformActionListener r3 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r3 == 0) goto L_0x03da
            com.mob.tools.log.NLog r14 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r0 = "Facebook doShare official please set video uri"
            r14.mo29786w((java.lang.String) r0)     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "please set video uri"
            r0.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x021f:
            int r6 = r14.getShareType()     // Catch:{ Throwable -> 0x047b }
            r7 = 4
            if (r6 != r7) goto L_0x0280
            com.mob.tools.log.NLog r6 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = "Facebook share by official that SHARE_WEBPAGE"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r6.mo29768d(r7, r3)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r3 = r14.getUrl()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r6 = r14.getQuote()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = r14.getHashtag()     // Catch:{ Throwable -> 0x047b }
            boolean r8 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x047b }
            if (r8 != 0) goto L_0x0266
            cn.sharesdk.facebook.e r14 = new cn.sharesdk.facebook.e     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener     // Catch:{ Throwable -> 0x047b }
            r14.<init>(r13, r0)     // Catch:{ Throwable -> 0x047b }
            android.content.Intent r0 = new android.content.Intent     // Catch:{ Throwable -> 0x047b }
            r0.<init>()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "params_linkurl"
            r0.putExtra(r2, r3)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "params_Quote"
            r0.putExtra(r2, r6)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "params_Hashtag"
            r0.putExtra(r2, r7)     // Catch:{ Throwable -> 0x047b }
            android.content.Context r2 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x047b }
            r14.show(r2, r0)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x0266:
            cn.sharesdk.framework.PlatformActionListener r3 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r3 == 0) goto L_0x03da
            com.mob.tools.log.NLog r14 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r0 = "Facebook doShare official please set webpage url"
            r14.mo29786w((java.lang.String) r0)     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "please set webpage url"
            r0.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x0280:
            cn.sharesdk.framework.PlatformActionListener r3 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r3 == 0) goto L_0x03da
            com.mob.tools.log.NLog r14 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r0 = "Facebook doShare official please set share Type"
            r14.mo29786w((java.lang.String) r0)     // Catch:{ Throwable -> 0x047b }
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "please set share Type"
            r0.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x029a:
            com.mob.tools.log.NLog r5 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = "Facebook share by primordial"
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r5.mo29768d(r7, r8)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r5 = "images"
            if (r6 == 0) goto L_0x0324
            int r7 = r6.size()     // Catch:{ Throwable -> 0x047b }
            if (r7 > 0) goto L_0x02b0
            goto L_0x0324
        L_0x02b0:
            java.util.Iterator r2 = r6.iterator()     // Catch:{ Throwable -> 0x047b }
        L_0x02b4:
            boolean r4 = r2.hasNext()     // Catch:{ Throwable -> 0x047b }
            if (r4 == 0) goto L_0x03d4
            java.lang.Object r4 = r2.next()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = "http"
            boolean r7 = r4.startsWith(r7)     // Catch:{ Throwable -> 0x047b }
            if (r7 == 0) goto L_0x02d3
            android.content.Context r7 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r4 = com.mob.tools.utils.BitmapHelper.downloadBitmap(r7, r4)     // Catch:{ Throwable -> 0x047b }
            r6.set(r3, r4)     // Catch:{ Throwable -> 0x047b }
        L_0x02d3:
            java.io.File r7 = new java.io.File     // Catch:{ Throwable -> 0x047b }
            r7.<init>(r4)     // Catch:{ Throwable -> 0x047b }
            boolean r8 = r7.exists()     // Catch:{ Throwable -> 0x047b }
            if (r8 == 0) goto L_0x0321
            java.lang.String r8 = "/data/"
            boolean r8 = r4.startsWith(r8)     // Catch:{ Throwable -> 0x047b }
            if (r8 == 0) goto L_0x0321
            r6.remove(r4)     // Catch:{ Throwable -> 0x047b }
            android.content.Context r8 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r8 = com.mob.tools.utils.ResHelper.getCachePath(r8, r5)     // Catch:{ Throwable -> 0x047b }
            java.io.File r9 = new java.io.File     // Catch:{ Throwable -> 0x047b }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x047b }
            r10.<init>()     // Catch:{ Throwable -> 0x047b }
            long r11 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x047b }
            r10.append(r11)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r11 = r7.getName()     // Catch:{ Throwable -> 0x047b }
            r10.append(r11)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r10 = r10.toString()     // Catch:{ Throwable -> 0x047b }
            r9.<init>(r8, r10)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r8 = r9.getAbsolutePath()     // Catch:{ Throwable -> 0x047b }
            r9.createNewFile()     // Catch:{ Throwable -> 0x047b }
            boolean r4 = com.mob.tools.utils.ResHelper.copyFile((java.lang.String) r4, (java.lang.String) r8)     // Catch:{ Throwable -> 0x047b }
            if (r4 == 0) goto L_0x0321
            java.lang.String r4 = r7.getAbsolutePath()     // Catch:{ Throwable -> 0x047b }
            r6.add(r4)     // Catch:{ Throwable -> 0x047b }
        L_0x0321:
            int r3 = r3 + 1
            goto L_0x02b4
        L_0x0324:
            boolean r7 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x047b }
            if (r7 != 0) goto L_0x0335
            java.io.File r7 = new java.io.File     // Catch:{ Throwable -> 0x047b }
            r7.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            boolean r7 = r7.exists()     // Catch:{ Throwable -> 0x047b }
            if (r7 != 0) goto L_0x03bc
        L_0x0335:
            android.graphics.Bitmap r7 = r14.getImageData()     // Catch:{ Throwable -> 0x047b }
            if (r7 == 0) goto L_0x0394
            boolean r8 = r7.isRecycled()     // Catch:{ Throwable -> 0x047b }
            if (r8 != 0) goto L_0x0394
            android.content.Context r2 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = com.mob.tools.utils.ResHelper.getCachePath(r2, r5)     // Catch:{ Throwable -> 0x047b }
            java.io.File r4 = new java.io.File     // Catch:{ Throwable -> 0x047b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x047b }
            r5.<init>()     // Catch:{ Throwable -> 0x047b }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x047b }
            r5.append(r8)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r8 = ".png"
            r5.append(r8)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x047b }
            r4.<init>(r2, r5)     // Catch:{ Throwable -> 0x047b }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x047b }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x047b }
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Throwable -> 0x047b }
            r8 = 100
            r7.compress(r5, r8, r2)     // Catch:{ Throwable -> 0x047b }
            r2.flush()     // Catch:{ Throwable -> 0x047b }
            r2.close()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = r4.getAbsolutePath()     // Catch:{ Throwable -> 0x047b }
            com.mob.tools.log.NLog r4 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x047b }
            r5.<init>()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = "Facebook share by primordial imagepath: "
            r5.append(r7)     // Catch:{ Throwable -> 0x047b }
            r5.append(r2)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x047b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r4.mo29768d(r5, r3)     // Catch:{ Throwable -> 0x047b }
            goto L_0x03bc
        L_0x0394:
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x047b }
            if (r5 != 0) goto L_0x03bc
            android.content.Context r2 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = com.mob.tools.utils.BitmapHelper.downloadBitmap(r2, r4)     // Catch:{ Throwable -> 0x047b }
            com.mob.tools.log.NLog r4 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x047b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x047b }
            r5.<init>()     // Catch:{ Throwable -> 0x047b }
            java.lang.String r7 = "Facebook share by primordial dowanload imagepath: "
            r5.append(r7)     // Catch:{ Throwable -> 0x047b }
            r5.append(r2)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x047b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x047b }
            r4.mo29768d(r5, r3)     // Catch:{ Throwable -> 0x047b }
        L_0x03bc:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x047b }
            if (r3 != 0) goto L_0x03d4
            r6.add(r2)     // Catch:{ Throwable -> 0x047b }
            int r2 = r6.size()     // Catch:{ Throwable -> 0x047b }
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Throwable -> 0x047b }
            java.lang.Object[] r2 = r6.toArray(r2)     // Catch:{ Throwable -> 0x047b }
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch:{ Throwable -> 0x047b }
            r14.setImageArray(r2)     // Catch:{ Throwable -> 0x047b }
        L_0x03d4:
            cn.sharesdk.framework.PlatformActionListener r2 = r13.listener     // Catch:{ Throwable -> 0x047b }
            r0.mo10212a((p005cn.sharesdk.framework.PlatformActionListener) r2, (p005cn.sharesdk.framework.Platform.ShareParams) r14)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x03da:
            boolean r3 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Throwable -> 0x047b }
            if (r3 != 0) goto L_0x0407
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x047b }
            if (r3 == 0) goto L_0x03fe
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x047b }
            if (r3 != 0) goto L_0x03fe
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x047b }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            boolean r3 = r3.exists()     // Catch:{ Throwable -> 0x047b }
            if (r3 == 0) goto L_0x03fe
            java.lang.String r2 = r13.uploadImageToFileServer((java.lang.String) r2)     // Catch:{ Throwable -> 0x047b }
            r14.setImageUrl(r2)     // Catch:{ Throwable -> 0x047b }
        L_0x03fe:
            cn.sharesdk.facebook.Facebook$2 r2 = new cn.sharesdk.facebook.Facebook$2     // Catch:{ Throwable -> 0x047b }
            r2.<init>(r14)     // Catch:{ Throwable -> 0x047b }
            r0.mo10211a((p005cn.sharesdk.framework.Platform.ShareParams) r14, (p005cn.sharesdk.framework.PlatformActionListener) r2)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x0407:
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x047b }
            java.lang.String r3 = "Please install the facebook client"
            if (r0 != 0) goto L_0x0429
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x047b }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x047b }
            if (r0 == 0) goto L_0x0429
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r14 == 0) goto L_0x0428
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            r0.<init>(r3)     // Catch:{ Throwable -> 0x047b }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x047b }
        L_0x0428:
            return
        L_0x0429:
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x047b }
            if (r0 != 0) goto L_0x043e
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r14 == 0) goto L_0x046a
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            r0.<init>(r3)     // Catch:{ Throwable -> 0x047b }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x043e:
            java.lang.String r14 = r14.getFilePath()     // Catch:{ Throwable -> 0x047b }
            boolean r14 = android.text.TextUtils.isEmpty(r14)     // Catch:{ Throwable -> 0x047b }
            if (r14 != 0) goto L_0x0459
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r14 == 0) goto L_0x046a
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "Share video only supports facebook client, please install facebook client"
            r0.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x0459:
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r14 == 0) goto L_0x046a
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "Share parameter error, please check"
            r0.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x047b }
            return
        L_0x046a:
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            if (r14 == 0) goto L_0x047a
            cn.sharesdk.framework.PlatformActionListener r14 = r13.listener     // Catch:{ Throwable -> 0x047b }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ Throwable -> 0x047b }
            java.lang.String r2 = "response is null"
            r0.<init>(r2)     // Catch:{ Throwable -> 0x047b }
            r14.onError(r13, r1, r0)     // Catch:{ Throwable -> 0x047b }
        L_0x047a:
            return
        L_0x047b:
            r14 = move-exception
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener
            if (r0 == 0) goto L_0x0485
            cn.sharesdk.framework.PlatformActionListener r0 = r13.listener
            r0.onError(r13, r1, r14)
        L_0x0485:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.facebook.Facebook.doShare(cn.sharesdk.framework.Platform$ShareParams):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void userInfor(java.lang.String r18) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            java.lang.String r2 = "picture"
            java.lang.String r3 = "token_for_business"
            java.lang.String r4 = "gender"
            java.lang.String r5 = "end_date"
            java.lang.String r6 = "year"
            java.lang.String r7 = "school"
            java.lang.String r8 = "birthday"
            java.lang.String r9 = "name"
            cn.sharesdk.facebook.h r10 = p005cn.sharesdk.facebook.C0674h.m65a((p005cn.sharesdk.framework.Platform) r17)
            r11 = 8
            java.util.HashMap r10 = r10.mo10218b(r0)     // Catch:{ Throwable -> 0x0302 }
            if (r10 == 0) goto L_0x02f1
            int r12 = r10.size()     // Catch:{ Throwable -> 0x0302 }
            if (r12 > 0) goto L_0x0028
            goto L_0x02f1
        L_0x0028:
            java.lang.String r12 = "error_code"
            boolean r12 = r10.containsKey(r12)     // Catch:{ Throwable -> 0x0302 }
            if (r12 != 0) goto L_0x02d9
            java.lang.String r12 = "error"
            boolean r12 = r10.containsKey(r12)     // Catch:{ Throwable -> 0x0302 }
            if (r12 == 0) goto L_0x003a
            goto L_0x02d9
        L_0x003a:
            if (r0 != 0) goto L_0x02cf
            cn.sharesdk.framework.PlatformDb r0 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r12 = "id"
            java.lang.Object r12 = r10.get(r12)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ Throwable -> 0x0302 }
            r0.putUserId(r12)     // Catch:{ Throwable -> 0x0302 }
            cn.sharesdk.framework.PlatformDb r0 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r12 = "nickname"
            java.lang.Object r13 = r10.get(r9)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ Throwable -> 0x0302 }
            r0.put(r12, r13)     // Catch:{ Throwable -> 0x0302 }
            cn.sharesdk.framework.PlatformDb r0 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r12 = "male"
            java.lang.Object r13 = r10.get(r4)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ Throwable -> 0x0302 }
            boolean r12 = r12.equals(r13)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r13 = "0"
            java.lang.String r14 = "1"
            if (r12 == 0) goto L_0x0072
            r12 = r13
            goto L_0x0073
        L_0x0072:
            r12 = r14
        L_0x0073:
            r0.put(r4, r12)     // Catch:{ Throwable -> 0x0302 }
            cn.sharesdk.framework.PlatformDb r0 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.Object r4 = r10.get(r3)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Throwable -> 0x0302 }
            r0.put(r3, r4)     // Catch:{ Throwable -> 0x0302 }
            boolean r0 = r10.containsKey(r2)     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x008e
            java.lang.Object r0 = r10.get(r2)     // Catch:{ Throwable -> 0x0302 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0302 }
            goto L_0x008f
        L_0x008e:
            r0 = 0
        L_0x008f:
            if (r0 == 0) goto L_0x00ac
            java.lang.String r2 = "data"
            java.lang.Object r0 = r0.get(r2)     // Catch:{ Throwable -> 0x0302 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x00ac
            cn.sharesdk.framework.PlatformDb r2 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r4 = "icon"
            java.lang.String r12 = "url"
            java.lang.Object r0 = r0.get(r12)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0302 }
            r2.put(r4, r0)     // Catch:{ Throwable -> 0x0302 }
        L_0x00ac:
            r2 = 0
            r4 = 1
            boolean r0 = r10.containsKey(r8)     // Catch:{ Throwable -> 0x00f2 }
            if (r0 == 0) goto L_0x00fa
            java.lang.Object r0 = r10.get(r8)     // Catch:{ Throwable -> 0x00f2 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x00f2 }
            java.lang.String r12 = "/"
            java.lang.String[] r0 = r0.split(r12)     // Catch:{ Throwable -> 0x00f2 }
            java.util.Calendar r12 = java.util.Calendar.getInstance()     // Catch:{ Throwable -> 0x00f2 }
            r15 = 2
            r16 = r0[r15]     // Catch:{ Throwable -> 0x00f2 }
            int r3 = com.mob.tools.utils.ResHelper.parseInt(r16)     // Catch:{ Throwable -> 0x00f2 }
            r12.set(r4, r3)     // Catch:{ Throwable -> 0x00f2 }
            r3 = r0[r2]     // Catch:{ Throwable -> 0x00f2 }
            int r3 = com.mob.tools.utils.ResHelper.parseInt(r3)     // Catch:{ Throwable -> 0x00f2 }
            int r3 = r3 - r4
            r12.set(r15, r3)     // Catch:{ Throwable -> 0x00f2 }
            r3 = 5
            r0 = r0[r4]     // Catch:{ Throwable -> 0x00f2 }
            int r0 = com.mob.tools.utils.ResHelper.parseInt(r0)     // Catch:{ Throwable -> 0x00f2 }
            r12.set(r3, r0)     // Catch:{ Throwable -> 0x00f2 }
            cn.sharesdk.framework.PlatformDb r0 = r1.f149db     // Catch:{ Throwable -> 0x00f2 }
            long r15 = r12.getTimeInMillis()     // Catch:{ Throwable -> 0x00f2 }
            java.lang.String r3 = java.lang.String.valueOf(r15)     // Catch:{ Throwable -> 0x00f2 }
            r0.put(r8, r3)     // Catch:{ Throwable -> 0x00f2 }
            goto L_0x00fa
        L_0x00f2:
            r0 = move-exception
            com.mob.tools.log.NLog r3 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x0302 }
            r3.mo29769d(r0)     // Catch:{ Throwable -> 0x0302 }
        L_0x00fa:
            cn.sharesdk.framework.PlatformDb r0 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r3 = "secretType"
            java.lang.String r8 = "true"
            java.lang.String r12 = "verified"
            java.lang.Object r12 = r10.get(r12)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ Throwable -> 0x0302 }
            boolean r8 = r8.equals(r12)     // Catch:{ Throwable -> 0x0302 }
            if (r8 == 0) goto L_0x0111
            goto L_0x0112
        L_0x0111:
            r14 = r13
        L_0x0112:
            r0.put(r3, r14)     // Catch:{ Throwable -> 0x0302 }
            cn.sharesdk.framework.PlatformDb r0 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r3 = "snsUserUrl"
            java.lang.String r8 = "link"
            java.lang.Object r8 = r10.get(r8)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Throwable -> 0x0302 }
            r0.put(r3, r8)     // Catch:{ Throwable -> 0x0302 }
            cn.sharesdk.framework.PlatformDb r0 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r3 = "resume"
            java.lang.String r8 = "link"
            java.lang.Object r8 = r10.get(r8)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Throwable -> 0x0302 }
            r0.put(r3, r8)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r0 = "education"
            boolean r0 = r10.containsKey(r0)     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x0149
            java.lang.String r0 = "education"
            java.lang.Object r0 = r10.get(r0)     // Catch:{ Throwable -> 0x0302 }
            r3 = r0
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ Throwable -> 0x0302 }
            goto L_0x014a
        L_0x0149:
            r3 = 0
        L_0x014a:
            if (r3 == 0) goto L_0x01e4
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0302 }
            r8.<init>()     // Catch:{ Throwable -> 0x0302 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x0302 }
        L_0x0155:
            boolean r0 = r3.hasNext()     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x01c1
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x0302 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0302 }
            java.util.HashMap r12 = new java.util.HashMap     // Catch:{ Throwable -> 0x0302 }
            r12.<init>()     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r13 = "school_type"
            java.lang.Integer r14 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0302 }
            r12.put(r13, r14)     // Catch:{ Throwable -> 0x0302 }
            boolean r13 = r0.containsKey(r7)     // Catch:{ Throwable -> 0x0302 }
            if (r13 == 0) goto L_0x017c
            java.lang.Object r13 = r0.get(r7)     // Catch:{ Throwable -> 0x0302 }
            java.util.HashMap r13 = (java.util.HashMap) r13     // Catch:{ Throwable -> 0x0302 }
            goto L_0x017d
        L_0x017c:
            r13 = 0
        L_0x017d:
            if (r13 == 0) goto L_0x018a
            java.lang.Object r13 = r13.get(r9)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ Throwable -> 0x0302 }
            r12.put(r7, r13)     // Catch:{ Throwable -> 0x0302 }
        L_0x018a:
            boolean r13 = r0.containsKey(r6)     // Catch:{ Throwable -> 0x01ac }
            if (r13 == 0) goto L_0x0197
            java.lang.Object r0 = r0.get(r6)     // Catch:{ Throwable -> 0x01ac }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x01ac }
            goto L_0x0198
        L_0x0197:
            r0 = 0
        L_0x0198:
            java.lang.Object r0 = r0.get(r9)     // Catch:{ Throwable -> 0x01ac }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x01ac }
            int r0 = com.mob.tools.utils.ResHelper.parseInt(r0)     // Catch:{ Throwable -> 0x01ac }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x01ac }
            r12.put(r6, r0)     // Catch:{ Throwable -> 0x01ac }
            goto L_0x01b4
        L_0x01ac:
            r0 = move-exception
            com.mob.tools.log.NLog r13 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x0302 }
            r13.mo29769d(r0)     // Catch:{ Throwable -> 0x0302 }
        L_0x01b4:
            java.lang.String r0 = "background"
            java.lang.Integer r13 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0302 }
            r12.put(r0, r13)     // Catch:{ Throwable -> 0x0302 }
            r8.add(r12)     // Catch:{ Throwable -> 0x0302 }
            goto L_0x0155
        L_0x01c1:
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x0302 }
            r0.<init>()     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r3 = "list"
            r0.put(r3, r8)     // Catch:{ Throwable -> 0x0302 }
            com.mob.tools.utils.Hashon r3 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x0302 }
            r3.<init>()     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r0 = r3.fromHashMap(r0)     // Catch:{ Throwable -> 0x0302 }
            cn.sharesdk.framework.PlatformDb r3 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r6 = "educationJSONArrayStr"
            int r7 = r0.length()     // Catch:{ Throwable -> 0x0302 }
            int r7 = r7 - r4
            java.lang.String r0 = r0.substring(r11, r7)     // Catch:{ Throwable -> 0x0302 }
            r3.put(r6, r0)     // Catch:{ Throwable -> 0x0302 }
        L_0x01e4:
            java.lang.String r0 = "work"
            boolean r0 = r10.containsKey(r0)     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x01f6
            java.lang.String r0 = "work"
            java.lang.Object r0 = r10.get(r0)     // Catch:{ Throwable -> 0x0302 }
            r3 = r0
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ Throwable -> 0x0302 }
            goto L_0x01f7
        L_0x01f6:
            r3 = 0
        L_0x01f7:
            if (r3 == 0) goto L_0x02cf
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0302 }
            r6.<init>()     // Catch:{ Throwable -> 0x0302 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x0302 }
        L_0x0202:
            boolean r0 = r3.hasNext()     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x02ac
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x0302 }
            r7 = r0
            java.util.HashMap r7 = (java.util.HashMap) r7     // Catch:{ Throwable -> 0x0302 }
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ Throwable -> 0x0302 }
            r8.<init>()     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r0 = "employer"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Throwable -> 0x0302 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x022b
            java.lang.String r12 = "company"
            java.lang.Object r0 = r0.get(r9)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0302 }
            r8.put(r12, r0)     // Catch:{ Throwable -> 0x0302 }
        L_0x022b:
            java.lang.String r0 = "position"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Throwable -> 0x0302 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x0242
            java.lang.String r12 = "position"
            java.lang.Object r0 = r0.get(r9)     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0302 }
            r8.put(r12, r0)     // Catch:{ Throwable -> 0x0302 }
        L_0x0242:
            java.lang.String r0 = "start_date"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Throwable -> 0x026b }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x026b }
            java.lang.String r12 = "-"
            java.lang.String[] r0 = r0.split(r12)     // Catch:{ Throwable -> 0x026b }
            r12 = r0[r2]     // Catch:{ Throwable -> 0x026b }
            int r12 = com.mob.tools.utils.ResHelper.parseInt(r12)     // Catch:{ Throwable -> 0x026b }
            int r12 = r12 * 100
            r0 = r0[r4]     // Catch:{ Throwable -> 0x026b }
            int r0 = com.mob.tools.utils.ResHelper.parseInt(r0)     // Catch:{ Throwable -> 0x026b }
            int r12 = r12 + r0
            java.lang.String r0 = "start_date"
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ Throwable -> 0x026b }
            r8.put(r0, r12)     // Catch:{ Throwable -> 0x026b }
            goto L_0x0273
        L_0x026b:
            r0 = move-exception
            com.mob.tools.log.NLog r12 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x0302 }
            r12.mo29769d(r0)     // Catch:{ Throwable -> 0x0302 }
        L_0x0273:
            java.lang.Object r0 = r7.get(r5)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0298 }
            java.lang.String r7 = "-"
            java.lang.String[] r0 = r0.split(r7)     // Catch:{ Throwable -> 0x0298 }
            r7 = r0[r2]     // Catch:{ Throwable -> 0x0298 }
            int r7 = com.mob.tools.utils.ResHelper.parseInt(r7)     // Catch:{ Throwable -> 0x0298 }
            int r7 = r7 * 100
            r0 = r0[r4]     // Catch:{ Throwable -> 0x0298 }
            int r0 = com.mob.tools.utils.ResHelper.parseInt(r0)     // Catch:{ Throwable -> 0x0298 }
            int r7 = r7 + r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r7)     // Catch:{ Throwable -> 0x0298 }
            r8.put(r5, r0)     // Catch:{ Throwable -> 0x0298 }
            goto L_0x02a7
        L_0x0298:
            r0 = move-exception
            com.mob.tools.log.NLog r7 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x0302 }
            r7.mo29769d(r0)     // Catch:{ Throwable -> 0x0302 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)     // Catch:{ Throwable -> 0x0302 }
            r8.put(r5, r0)     // Catch:{ Throwable -> 0x0302 }
        L_0x02a7:
            r6.add(r8)     // Catch:{ Throwable -> 0x0302 }
            goto L_0x0202
        L_0x02ac:
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x0302 }
            r0.<init>()     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r2 = "list"
            r0.put(r2, r6)     // Catch:{ Throwable -> 0x0302 }
            com.mob.tools.utils.Hashon r2 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x0302 }
            r2.<init>()     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r0 = r2.fromHashMap(r0)     // Catch:{ Throwable -> 0x0302 }
            cn.sharesdk.framework.PlatformDb r2 = r1.f149db     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r3 = "workJSONArrayStr"
            int r5 = r0.length()     // Catch:{ Throwable -> 0x0302 }
            int r5 = r5 - r4
            java.lang.String r0 = r0.substring(r11, r5)     // Catch:{ Throwable -> 0x0302 }
            r2.put(r3, r0)     // Catch:{ Throwable -> 0x0302 }
        L_0x02cf:
            cn.sharesdk.framework.PlatformActionListener r0 = r1.listener     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x030c
            cn.sharesdk.framework.PlatformActionListener r0 = r1.listener     // Catch:{ Throwable -> 0x0302 }
            r0.onComplete(r1, r11, r10)     // Catch:{ Throwable -> 0x0302 }
            goto L_0x030c
        L_0x02d9:
            cn.sharesdk.framework.PlatformActionListener r0 = r1.listener     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x02f0
            com.mob.tools.utils.Hashon r0 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x0302 }
            r0.<init>()     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r0 = r0.fromHashMap(r10)     // Catch:{ Throwable -> 0x0302 }
            cn.sharesdk.framework.PlatformActionListener r2 = r1.listener     // Catch:{ Throwable -> 0x0302 }
            java.lang.Throwable r3 = new java.lang.Throwable     // Catch:{ Throwable -> 0x0302 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0302 }
            r2.onError(r1, r11, r3)     // Catch:{ Throwable -> 0x0302 }
        L_0x02f0:
            return
        L_0x02f1:
            cn.sharesdk.framework.PlatformActionListener r0 = r1.listener     // Catch:{ Throwable -> 0x0302 }
            if (r0 == 0) goto L_0x0301
            cn.sharesdk.framework.PlatformActionListener r0 = r1.listener     // Catch:{ Throwable -> 0x0302 }
            java.lang.Throwable r2 = new java.lang.Throwable     // Catch:{ Throwable -> 0x0302 }
            java.lang.String r3 = "response is null"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0302 }
            r0.onError(r1, r11, r2)     // Catch:{ Throwable -> 0x0302 }
        L_0x0301:
            return
        L_0x0302:
            r0 = move-exception
            cn.sharesdk.framework.PlatformActionListener r2 = r1.listener
            if (r2 == 0) goto L_0x030c
            cn.sharesdk.framework.PlatformActionListener r2 = r1.listener
            r2.onError(r1, r11, r0)
        L_0x030c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.facebook.Facebook.userInfor(java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public C0720f.C0721a filterShareContent(Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        String str;
        C0720f.C0721a aVar = new C0720f.C0721a();
        aVar.f260b = shareParams.getText();
        if (hashMap != null) {
            if (hashMap != null && hashMap.containsKey(ShareConstants.FEED_SOURCE_PARAM)) {
                aVar.f262d.add(String.valueOf(hashMap.get(ShareConstants.FEED_SOURCE_PARAM)));
            } else if (4 == shareParams.getShareType()) {
                aVar.f262d.add(shareParams.getImageUrl());
                String titleUrl = shareParams.getTitleUrl();
                if (TextUtils.isEmpty(titleUrl)) {
                    titleUrl = shareParams.getUrl();
                }
                aVar.f261c.add(titleUrl);
            }
            Object obj = hashMap.get(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID);
            if (obj == null) {
                str = null;
            } else {
                str = String.valueOf(obj);
            }
            aVar.f259a = str;
            aVar.f265g = hashMap;
        }
        return aVar;
    }

    /* access modifiers changed from: protected */
    public void follow(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    /* access modifiers changed from: protected */
    public void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    /* access modifiers changed from: protected */
    public void getFriendList(int i, int i2, String str) {
        try {
            HashMap<String, Object> a = C0674h.m65a((Platform) this).mo10209a(i, i2 * i, str);
            if (a != null) {
                if (a.size() > 0) {
                    if (!a.containsKey(NativeProtocol.BRIDGE_ARG_ERROR_CODE)) {
                        if (!a.containsKey("error")) {
                            if (this.listener != null) {
                                this.listener.onComplete(this, 2, a);
                                return;
                            }
                            return;
                        }
                    }
                    if (this.listener != null) {
                        this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(a)));
                        return;
                    }
                    return;
                }
            }
            if (this.listener != null) {
                this.listener.onError(this, 2, new Throwable("response is null"));
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 2, th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        try {
            HashMap<String, Object> a = C0674h.m65a((Platform) this).mo10210a(str, str2, hashMap, hashMap2);
            if (a != null) {
                if (a.size() > 0) {
                    if (!a.containsKey(NativeProtocol.BRIDGE_ARG_ERROR_CODE)) {
                        if (!a.containsKey("error")) {
                            if (this.listener != null) {
                                this.listener.onComplete(this, i, a);
                                return;
                            }
                            return;
                        }
                    }
                    if (this.listener != null) {
                        this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a)));
                        return;
                    }
                    return;
                }
            }
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable("response is null"));
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, i, th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowings(int i, int i2, String str) {
        try {
            HashMap<String, Object> a = C0674h.m65a((Platform) this).mo10209a(i, i2, str);
            if (a != null) {
                if (a.size() > 0) {
                    if (!a.containsKey(NativeProtocol.BRIDGE_ARG_ERROR_CODE)) {
                        if (!a.containsKey("error")) {
                            a.put("current_limit", Integer.valueOf(i));
                            a.put("current_cursor", Integer.valueOf(i2));
                            return filterFriendshipInfo(2, a);
                        }
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v63, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v95, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: java.util.HashMap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v96, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: java.util.HashMap} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0229  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0233  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0236  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.lang.Object> filterFriendshipInfo(int r21, java.util.HashMap<java.lang.String, java.lang.Object> r22) {
        /*
            r20 = this;
            r0 = r22
            java.lang.String r1 = "end_date"
            java.lang.String r2 = "birthday"
            java.lang.String r3 = "data"
            java.lang.Object r4 = r0.get(r3)
            r5 = 0
            if (r4 != 0) goto L_0x0010
            return r5
        L_0x0010:
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            java.lang.String r7 = "type"
            java.lang.String r8 = "FOLLOWING"
            r6.put(r7, r8)
            int r7 = r20.getPlatformId()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.String r8 = "snsplat"
            r6.put(r8, r7)
            r7 = r20
            cn.sharesdk.framework.PlatformDb r8 = r7.f149db
            java.lang.String r8 = r8.getUserId()
            java.lang.String r9 = "snsuid"
            r6.put(r9, r8)
            java.lang.String r8 = "current_cursor"
            java.lang.Object r8 = r0.get(r8)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            java.lang.String r10 = "current_limit"
            java.lang.Object r0 = r0.get(r10)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r10 = r0.intValue()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            int r0 = r4.size()
            if (r0 > 0) goto L_0x005c
            return r5
        L_0x005c:
            java.util.Iterator r4 = r4.iterator()
        L_0x0060:
            boolean r0 = r4.hasNext()
            java.lang.String r12 = "list"
            if (r0 == 0) goto L_0x031f
            java.lang.Object r0 = r4.next()
            r13 = r0
            java.util.HashMap r13 = (java.util.HashMap) r13
            if (r13 != 0) goto L_0x0072
            goto L_0x0060
        L_0x0072:
            java.util.HashMap r14 = new java.util.HashMap
            r14.<init>()
            java.lang.String r0 = "id"
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r14.put(r9, r0)
            java.lang.String r15 = "name"
            java.lang.Object r0 = r13.get(r15)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r5 = "nickname"
            r14.put(r5, r0)
            java.lang.String r0 = "gender"
            java.lang.Object r5 = r13.get(r0)
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r22 = r4
            java.lang.String r4 = "male"
            boolean r4 = r4.equals(r5)
            java.lang.String r5 = "0"
            java.lang.String r16 = "1"
            if (r4 == 0) goto L_0x00ad
            r4 = r5
            goto L_0x00af
        L_0x00ad:
            r4 = r16
        L_0x00af:
            r14.put(r0, r4)
            java.lang.String r0 = "verified"
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r4 = "true"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x00c6
            r5 = r16
        L_0x00c6:
            java.lang.String r0 = "secretType"
            r14.put(r0, r5)
            java.lang.String r0 = "link"
            java.lang.Object r4 = r13.get(r0)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r5 = "snsUserUrl"
            r14.put(r5, r4)
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r4 = "resume"
            r14.put(r4, r0)
            java.lang.String r0 = "picture"
            boolean r4 = r13.containsKey(r0)
            if (r4 == 0) goto L_0x00f7
            java.lang.Object r0 = r13.get(r0)
            r5 = r0
            java.util.HashMap r5 = (java.util.HashMap) r5
            goto L_0x00f8
        L_0x00f7:
            r5 = 0
        L_0x00f8:
            if (r5 == 0) goto L_0x011a
            boolean r0 = r5.containsKey(r3)
            if (r0 == 0) goto L_0x0108
            java.lang.Object r0 = r5.get(r3)
            r5 = r0
            java.util.HashMap r5 = (java.util.HashMap) r5
            goto L_0x0109
        L_0x0108:
            r5 = 0
        L_0x0109:
            if (r5 == 0) goto L_0x011a
            java.lang.String r0 = "url"
            java.lang.Object r0 = r5.get(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r4 = "icon"
            r14.put(r4, r0)
        L_0x011a:
            r5 = 1
            boolean r0 = r13.containsKey(r2)     // Catch:{ Throwable -> 0x0169 }
            if (r0 == 0) goto L_0x0166
            java.lang.Object r0 = r13.get(r2)     // Catch:{ Throwable -> 0x0169 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0169 }
            java.lang.String r4 = "/"
            java.lang.String[] r0 = r0.split(r4)     // Catch:{ Throwable -> 0x0169 }
            java.util.Calendar r4 = java.util.Calendar.getInstance()     // Catch:{ Throwable -> 0x0169 }
            r17 = 2
            r17 = r0[r17]     // Catch:{ Throwable -> 0x0169 }
            r18 = r3
            int r3 = com.mob.tools.utils.ResHelper.parseInt(r17)     // Catch:{ Throwable -> 0x0164 }
            r4.set(r5, r3)     // Catch:{ Throwable -> 0x0164 }
            r3 = 2
            r16 = 0
            r17 = r0[r16]     // Catch:{ Throwable -> 0x0164 }
            int r17 = com.mob.tools.utils.ResHelper.parseInt(r17)     // Catch:{ Throwable -> 0x0164 }
            int r7 = r17 + -1
            r4.set(r3, r7)     // Catch:{ Throwable -> 0x0164 }
            r3 = 5
            r0 = r0[r5]     // Catch:{ Throwable -> 0x0164 }
            int r0 = com.mob.tools.utils.ResHelper.parseInt(r0)     // Catch:{ Throwable -> 0x0164 }
            r4.set(r3, r0)     // Catch:{ Throwable -> 0x0164 }
            long r3 = r4.getTimeInMillis()     // Catch:{ Throwable -> 0x0164 }
            java.lang.String r0 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x0164 }
            r14.put(r2, r0)     // Catch:{ Throwable -> 0x0164 }
            goto L_0x0173
        L_0x0164:
            r0 = move-exception
            goto L_0x016c
        L_0x0166:
            r18 = r3
            goto L_0x0173
        L_0x0169:
            r0 = move-exception
            r18 = r3
        L_0x016c:
            com.mob.tools.log.NLog r3 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            r3.mo29769d(r0)
        L_0x0173:
            java.lang.String r0 = "education"
            boolean r3 = r13.containsKey(r0)
            if (r3 == 0) goto L_0x0182
            java.lang.Object r0 = r13.get(r0)
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            goto L_0x0183
        L_0x0182:
            r0 = 0
        L_0x0183:
            if (r0 == 0) goto L_0x021f
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r4 = r0.iterator()
        L_0x018e:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x01fa
            java.lang.Object r0 = r4.next()
            java.util.HashMap r0 = (java.util.HashMap) r0
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            r16 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r16)
            r19 = r2
            java.lang.String r2 = "school_type"
            r7.put(r2, r5)
            java.lang.String r2 = "school"
            java.lang.Object r2 = r0.get(r2)
            java.util.HashMap r2 = (java.util.HashMap) r2
            if (r2 == 0) goto L_0x01c3
            java.lang.Object r2 = r2.get(r15)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r5 = "school"
            r7.put(r5, r2)
        L_0x01c3:
            java.lang.String r2 = "year"
            java.lang.Object r0 = r0.get(r2)     // Catch:{ Throwable -> 0x01e1 }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x01e1 }
            java.lang.String r2 = "year"
            java.lang.Object r0 = r0.get(r15)     // Catch:{ Throwable -> 0x01e1 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x01e1 }
            int r0 = com.mob.tools.utils.ResHelper.parseInt(r0)     // Catch:{ Throwable -> 0x01e1 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x01e1 }
            r7.put(r2, r0)     // Catch:{ Throwable -> 0x01e1 }
            goto L_0x01e9
        L_0x01e1:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            r2.mo29769d(r0)
        L_0x01e9:
            r2 = 0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
            java.lang.String r2 = "background"
            r7.put(r2, r0)
            r3.add(r7)
            r2 = r19
            r5 = 1
            goto L_0x018e
        L_0x01fa:
            r19 = r2
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r0.put(r12, r3)
            com.mob.tools.utils.Hashon r2 = new com.mob.tools.utils.Hashon
            r2.<init>()
            java.lang.String r0 = r2.fromHashMap(r0)
            r2 = 8
            int r3 = r0.length()
            r4 = 1
            int r3 = r3 - r4
            java.lang.String r0 = r0.substring(r2, r3)
            java.lang.String r2 = "educationJSONArrayStr"
            r14.put(r2, r0)
            goto L_0x0221
        L_0x021f:
            r19 = r2
        L_0x0221:
            java.lang.String r0 = "work"
            boolean r0 = r13.containsKey(r0)
            if (r0 == 0) goto L_0x0233
            java.lang.String r0 = "work"
            java.lang.Object r0 = r13.get(r0)
            r5 = r0
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            goto L_0x0234
        L_0x0233:
            r5 = 0
        L_0x0234:
            if (r5 == 0) goto L_0x0311
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r3 = r5.iterator()
        L_0x023f:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x02ef
            java.lang.Object r0 = r3.next()
            r4 = r0
            java.util.HashMap r4 = (java.util.HashMap) r4
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            java.lang.String r0 = "employer"
            java.lang.Object r0 = r4.get(r0)
            java.util.HashMap r0 = (java.util.HashMap) r0
            if (r0 == 0) goto L_0x0268
            java.lang.Object r0 = r0.get(r15)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r7 = "company"
            r5.put(r7, r0)
        L_0x0268:
            java.lang.String r0 = "position"
            java.lang.Object r0 = r4.get(r0)
            java.util.HashMap r0 = (java.util.HashMap) r0
            if (r0 == 0) goto L_0x027f
            java.lang.Object r0 = r0.get(r15)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r7 = "position"
            r5.put(r7, r0)
        L_0x027f:
            java.lang.String r0 = "start_date"
            java.lang.Object r0 = r4.get(r0)     // Catch:{ Throwable -> 0x02aa }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x02aa }
            java.lang.String r7 = "-"
            java.lang.String[] r0 = r0.split(r7)     // Catch:{ Throwable -> 0x02aa }
            r7 = 0
            r13 = r0[r7]     // Catch:{ Throwable -> 0x02aa }
            int r7 = com.mob.tools.utils.ResHelper.parseInt(r13)     // Catch:{ Throwable -> 0x02aa }
            int r7 = r7 * 100
            r13 = 1
            r0 = r0[r13]     // Catch:{ Throwable -> 0x02aa }
            int r0 = com.mob.tools.utils.ResHelper.parseInt(r0)     // Catch:{ Throwable -> 0x02aa }
            int r7 = r7 + r0
            java.lang.String r0 = "start_date"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Throwable -> 0x02aa }
            r5.put(r0, r7)     // Catch:{ Throwable -> 0x02aa }
            goto L_0x02b2
        L_0x02aa:
            r0 = move-exception
            com.mob.tools.log.NLog r7 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            r7.mo29769d(r0)
        L_0x02b2:
            java.lang.Object r0 = r4.get(r1)     // Catch:{ Throwable -> 0x02da }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x02da }
            java.lang.String r4 = "-"
            java.lang.String[] r0 = r0.split(r4)     // Catch:{ Throwable -> 0x02da }
            r4 = 0
            r7 = r0[r4]     // Catch:{ Throwable -> 0x02da }
            int r4 = com.mob.tools.utils.ResHelper.parseInt(r7)     // Catch:{ Throwable -> 0x02da }
            int r4 = r4 * 100
            r7 = 1
            r0 = r0[r7]     // Catch:{ Throwable -> 0x02da }
            int r0 = com.mob.tools.utils.ResHelper.parseInt(r0)     // Catch:{ Throwable -> 0x02da }
            int r4 = r4 + r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x02da }
            r5.put(r1, r0)     // Catch:{ Throwable -> 0x02da }
            r4 = 0
            goto L_0x02ea
        L_0x02da:
            r0 = move-exception
            com.mob.tools.log.NLog r4 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            r4.mo29769d(r0)
            r4 = 0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            r5.put(r1, r0)
        L_0x02ea:
            r2.add(r5)
            goto L_0x023f
        L_0x02ef:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r0.put(r12, r2)
            com.mob.tools.utils.Hashon r2 = new com.mob.tools.utils.Hashon
            r2.<init>()
            java.lang.String r0 = r2.fromHashMap(r0)
            r2 = 8
            int r3 = r0.length()
            r4 = 1
            int r3 = r3 - r4
            java.lang.String r0 = r0.substring(r2, r3)
            java.lang.String r2 = "workJSONArrayStr"
            r14.put(r2, r0)
        L_0x0311:
            r11.add(r14)
            r5 = 0
            r7 = r20
            r4 = r22
            r3 = r18
            r2 = r19
            goto L_0x0060
        L_0x031f:
            int r0 = r11.size()
            if (r0 > 0) goto L_0x0327
            r1 = 0
            return r1
        L_0x0327:
            int r0 = r11.size()
            if (r10 < r0) goto L_0x0330
            java.lang.String r0 = "_true"
            goto L_0x0332
        L_0x0330:
            java.lang.String r0 = "_false"
        L_0x0332:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            int r2 = r11.size()
            int r8 = r8 + r2
            r1.append(r8)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "nextCursor"
            r6.put(r1, r0)
            r6.put(r12, r11)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.facebook.Facebook.filterFriendshipInfo(int, java.util.HashMap):java.util.HashMap");
    }
}
