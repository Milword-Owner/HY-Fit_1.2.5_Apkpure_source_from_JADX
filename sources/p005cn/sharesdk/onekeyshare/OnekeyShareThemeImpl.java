package p005cn.sharesdk.onekeyshare;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.facebook.internal.ServerProtocol;
import com.mob.MobSDK;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import p005cn.sharesdk.facebook.Facebook;
import p005cn.sharesdk.framework.CustomPlatform;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.ShareSDK;

/* renamed from: cn.sharesdk.onekeyshare.OnekeyShareThemeImpl */
public abstract class OnekeyShareThemeImpl implements PlatformActionListener, Handler.Callback {
    public PlatformActionListener callback = this;
    protected Context context;
    protected ArrayList<CustomerLogo> customerLogos;
    protected ShareContentCustomizeCallback customizeCallback;
    protected boolean dialogMode;
    protected boolean disableSSO;
    protected HashMap<String, String> hiddenPlatforms;
    protected HashMap<String, Object> shareParamsMap;
    protected boolean silent;

    /* access modifiers changed from: protected */
    public abstract void showEditPage(Context context2, Platform platform, Platform.ShareParams shareParams);

    /* access modifiers changed from: protected */
    public abstract void showPlatformPage(Context context2);

    public final void setDialogMode(boolean z) {
        this.dialogMode = z;
    }

    public final void setShareParamsMap(HashMap<String, Object> hashMap) {
        this.shareParamsMap = hashMap;
    }

    public final void setSilent(boolean z) {
        this.silent = z;
    }

    public final void setCustomerLogos(ArrayList<CustomerLogo> arrayList) {
        this.customerLogos = arrayList;
    }

    public final void setHiddenPlatforms(HashMap<String, String> hashMap) {
        this.hiddenPlatforms = hashMap;
    }

    public final void setPlatformActionListener(PlatformActionListener platformActionListener) {
        if (platformActionListener == null) {
            platformActionListener = this;
        }
        this.callback = platformActionListener;
    }

    public final void setShareContentCustomizeCallback(ShareContentCustomizeCallback shareContentCustomizeCallback) {
        this.customizeCallback = shareContentCustomizeCallback;
    }

    public final void disableSSO() {
        this.disableSSO = true;
    }

    public final void show(Context context2) {
        this.context = context2;
        if (this.shareParamsMap.containsKey("platform")) {
            Platform platform = null;
            try {
                platform = ShareSDK.getPlatform(String.valueOf(this.shareParamsMap.get("platform")));
            } catch (Throwable unused) {
            }
            boolean z = platform instanceof CustomPlatform;
            boolean isUseClientToShare = isUseClientToShare(platform);
            if (this.silent || z || isUseClientToShare) {
                shareSilently(platform);
            } else {
                prepareForEditPage(platform);
            }
        } else {
            showPlatformPage(context2);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isUseClientToShare(Platform platform) {
        String name = platform.getName();
        if ("SinaWeibo".equals(name) || "Wechat".equals(name) || "WechatMoments".equals(name) || "WechatFavorite".equals(name) || "ShortMessage".equals(name) || "Email".equals(name) || "Qzone".equals(name) || "QQ".equals(name) || "Pinterest".equals(name) || "Instagram".equals(name) || "Yixin".equals(name) || "YixinMoments".equals(name) || "QZone".equals(name) || "Mingdao".equals(name) || "Line".equals(name) || "KakaoStory".equals(name) || "KakaoTalk".equals(name) || "Bluetooth".equals(name) || "WhatsApp".equals(name) || "BaiduTieba".equals(name) || "Laiwang".equals(name) || "LaiwangMoments".equals(name) || "Alipay".equals(name) || "AlipayMoments".equals(name) || "FacebookMessenger".equals(name) || "GooglePlus".equals(name) || "Dingding".equals(name) || "Youtube".equals(name) || "Meipai".equals(name) || "Telegram".equals(name) || "Douyin".equals(name) || "Oasis".equals(name)) {
            return true;
        }
        if ("Evernote".equals(name)) {
            if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(platform.getDevinfo("ShareByAppClient"))) {
                return true;
            }
            return false;
        } else if (Facebook.NAME.equals(name)) {
            if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(platform.getDevinfo("ShareByAppClient")) && platform.isClientValid()) {
                return true;
            }
            if (!this.shareParamsMap.containsKey("url") || TextUtils.isEmpty((String) this.shareParamsMap.get("url"))) {
                return false;
            }
            return true;
        } else if (!"LinkedIn".equals(name) || !ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(platform.getDevinfo("ShareByAppClient")) || !platform.isClientValid()) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final void shareSilently(Platform platform) {
        Platform.ShareParams shareDataToShareParams;
        if (formateShareData(platform) && (shareDataToShareParams = shareDataToShareParams(platform)) != null) {
            HashMap<String, Object> hashMap = this.shareParamsMap;
            if (hashMap != null) {
                if (!Boolean.valueOf(hashMap.containsKey("disappearsharetoast") ? ((Boolean) this.shareParamsMap.get("disappearsharetoast")).booleanValue() : false).booleanValue()) {
                    toast("ssdk_oks_sharing");
                } else {
                    Log.d(OnekeyShare.SHARESDK_TAG, "isDispear is false");
                }
            }
            ShareContentCustomizeCallback shareContentCustomizeCallback = this.customizeCallback;
            if (shareContentCustomizeCallback != null) {
                shareContentCustomizeCallback.onShare(platform, shareDataToShareParams);
                Log.d(OnekeyShare.SHARESDK_TAG, "customizeCallback.onShare(platform, sp)");
            }
            boolean z = this.disableSSO;
            if (z) {
                platform.SSOSetting(z);
                Log.d(OnekeyShare.SHARESDK_TAG, "platform.SSOSetting(disableSSO)");
            }
            platform.setPlatformActionListener(this.callback);
            platform.share(shareDataToShareParams);
            this.callback = null;
            this.customizeCallback = null;
        }
    }

    private void prepareForEditPage(Platform platform) {
        Platform.ShareParams shareDataToShareParams;
        if (formateShareData(platform) && (shareDataToShareParams = shareDataToShareParams(platform)) != null) {
            ShareSDK.logDemoEvent(3, platform);
            shareDataToShareParams.setOpenCustomEven(true);
            ShareContentCustomizeCallback shareContentCustomizeCallback = this.customizeCallback;
            if (shareContentCustomizeCallback != null) {
                shareContentCustomizeCallback.onShare(platform, shareDataToShareParams);
            }
            showEditPage(this.context, platform, shareDataToShareParams);
            this.customizeCallback = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01aa, code lost:
        if (r1 != false) goto L_0x0295;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01f0, code lost:
        if (r1 != false) goto L_0x0295;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0248, code lost:
        if (r1 != false) goto L_0x0295;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0291, code lost:
        if (r1 != false) goto L_0x0295;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean formateShareData(p005cn.sharesdk.framework.Platform r13) {
        /*
            r12 = this;
            java.lang.String r0 = r13.getName()
            java.lang.String r1 = "Alipay"
            boolean r1 = r1.equals(r0)
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0019
            java.lang.String r1 = "AlipayMoments"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0017
            goto L_0x0019
        L_0x0017:
            r1 = 0
            goto L_0x001a
        L_0x0019:
            r1 = 1
        L_0x001a:
            if (r1 == 0) goto L_0x0028
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x0028
            java.lang.String r13 = "ssdk_alipay_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x0028:
            java.lang.String r1 = "KakaoTalk"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003c
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x003c
            java.lang.String r13 = "ssdk_kakaotalk_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x003c:
            java.lang.String r1 = "KakaoStory"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0050
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x0050
            java.lang.String r13 = "ssdk_kakaostory_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x0050:
            java.lang.String r1 = "Line"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0064
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x0064
            java.lang.String r13 = "ssdk_line_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x0064:
            java.lang.String r1 = "WhatsApp"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0078
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x0078
            java.lang.String r13 = "ssdk_whatsapp_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x0078:
            java.lang.String r1 = "Pinterest"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x008c
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x008c
            java.lang.String r13 = "ssdk_pinterest_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x008c:
            java.lang.String r1 = "Instagram"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x00a0
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x00a0
            java.lang.String r13 = "ssdk_instagram_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x00a0:
            java.lang.String r1 = "QZone"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x00b4
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x00b4
            java.lang.String r13 = "ssdk_qq_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x00b4:
            java.lang.String r1 = "Laiwang"
            boolean r1 = r1.equals(r0)
            java.lang.String r4 = "LaiwangMoments"
            boolean r4 = r4.equals(r0)
            if (r1 != 0) goto L_0x00c4
            if (r4 == 0) goto L_0x00d0
        L_0x00c4:
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x00d0
            java.lang.String r13 = "ssdk_laiwang_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x00d0:
            java.lang.String r1 = "YixinMoments"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x00e3
            java.lang.String r1 = "Yixin"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x00e1
            goto L_0x00e3
        L_0x00e1:
            r1 = 0
            goto L_0x00e4
        L_0x00e3:
            r1 = 1
        L_0x00e4:
            if (r1 == 0) goto L_0x00f2
            boolean r1 = r13.isClientValid()
            if (r1 != 0) goto L_0x00f2
            java.lang.String r13 = "ssdk_yixin_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x00f2:
            java.lang.String r1 = "WechatFavorite"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x010d
            java.lang.String r1 = "Wechat"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x010d
            java.lang.String r1 = "WechatMoments"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x010b
            goto L_0x010d
        L_0x010b:
            r1 = 0
            goto L_0x010e
        L_0x010d:
            r1 = 1
        L_0x010e:
            if (r1 == 0) goto L_0x011c
            boolean r4 = r13.isClientValid()
            if (r4 != 0) goto L_0x011c
            java.lang.String r13 = "ssdk_wechat_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x011c:
            java.lang.String r4 = "FacebookMessenger"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0130
            boolean r4 = r13.isClientValid()
            if (r4 != 0) goto L_0x0130
            java.lang.String r13 = "ssdk_facebookmessenger_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x0130:
            java.lang.String r4 = "Telegram"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0144
            boolean r13 = r13.isClientValid()
            if (r13 != 0) goto L_0x0144
            java.lang.String r13 = "ssdk_telegram_client_inavailable"
            r12.toast(r13)
            return r3
        L_0x0144:
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.String r0 = "shareType"
            boolean r13 = r13.containsKey(r0)
            if (r13 != 0) goto L_0x029e
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.String r3 = "imagePath"
            java.lang.Object r13 = r13.get(r3)
            java.lang.String r13 = java.lang.String.valueOf(r13)
            r3 = 9
            java.lang.String r4 = ".gif"
            r5 = 5
            r6 = 4
            r7 = 2
            java.lang.String r8 = "musicUrl"
            java.lang.String r9 = "url"
            if (r13 == 0) goto L_0x01ae
            java.io.File r10 = new java.io.File
            r10.<init>(r13)
            boolean r10 = r10.exists()
            if (r10 == 0) goto L_0x01ae
            boolean r13 = r13.endsWith(r4)
            if (r13 == 0) goto L_0x017e
            if (r1 == 0) goto L_0x017e
        L_0x017a:
            r5 = 9
            goto L_0x0295
        L_0x017e:
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            boolean r13 = r13.containsKey(r9)
            if (r13 == 0) goto L_0x01f7
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.Object r13 = r13.get(r9)
            java.lang.String r13 = (java.lang.String) r13
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x01f7
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            boolean r13 = r13.containsKey(r8)
            if (r13 == 0) goto L_0x01f4
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.Object r13 = r13.get(r8)
            java.lang.String r13 = (java.lang.String) r13
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x01f4
            if (r1 == 0) goto L_0x01f4
            goto L_0x0295
        L_0x01ae:
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.String r10 = "viewToShare"
            java.lang.Object r13 = r13.get(r10)
            java.lang.Object r13 = com.mob.tools.utils.ResHelper.forceCast(r13)
            android.graphics.Bitmap r13 = (android.graphics.Bitmap) r13
            if (r13 == 0) goto L_0x01fa
            boolean r13 = r13.isRecycled()
            if (r13 != 0) goto L_0x01fa
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            boolean r13 = r13.containsKey(r9)
            if (r13 == 0) goto L_0x01f7
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.Object r13 = r13.get(r9)
            java.lang.String r13 = (java.lang.String) r13
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x01f7
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            boolean r13 = r13.containsKey(r8)
            if (r13 == 0) goto L_0x01f4
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.Object r13 = r13.get(r8)
            java.lang.String r13 = (java.lang.String) r13
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x01f4
            if (r1 == 0) goto L_0x01f4
            goto L_0x0295
        L_0x01f4:
            r5 = 4
            goto L_0x0295
        L_0x01f7:
            r5 = 2
            goto L_0x0295
        L_0x01fa:
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.String r10 = "imageUrl"
            java.lang.Object r13 = r13.get(r10)
            if (r13 == 0) goto L_0x024b
            java.lang.String r10 = java.lang.String.valueOf(r13)
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 != 0) goto L_0x024b
            java.lang.String r13 = java.lang.String.valueOf(r13)
            boolean r13 = r13.endsWith(r4)
            if (r13 == 0) goto L_0x021c
            if (r1 == 0) goto L_0x021c
            goto L_0x017a
        L_0x021c:
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            boolean r13 = r13.containsKey(r9)
            if (r13 == 0) goto L_0x01f7
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.Object r13 = r13.get(r9)
            java.lang.String r13 = (java.lang.String) r13
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x01f7
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            boolean r13 = r13.containsKey(r8)
            if (r13 == 0) goto L_0x01f4
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.Object r13 = r13.get(r8)
            java.lang.String r13 = (java.lang.String) r13
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x01f4
            if (r1 == 0) goto L_0x01f4
            goto L_0x0295
        L_0x024b:
            java.util.HashMap<java.lang.String, java.lang.Object> r10 = r12.shareParamsMap
            java.lang.String r11 = "imageData"
            java.lang.Object r10 = r10.get(r11)
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            if (r10 == 0) goto L_0x0294
            java.lang.String r13 = java.lang.String.valueOf(r13)
            boolean r13 = r13.endsWith(r4)
            if (r13 == 0) goto L_0x0265
            if (r1 == 0) goto L_0x0265
            goto L_0x017a
        L_0x0265:
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            boolean r13 = r13.containsKey(r9)
            if (r13 == 0) goto L_0x01f7
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.Object r13 = r13.get(r9)
            java.lang.String r13 = (java.lang.String) r13
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x01f7
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            boolean r13 = r13.containsKey(r8)
            if (r13 == 0) goto L_0x01f4
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.Object r13 = r13.get(r8)
            java.lang.String r13 = (java.lang.String) r13
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x01f4
            if (r1 == 0) goto L_0x01f4
            goto L_0x0295
        L_0x0294:
            r5 = 1
        L_0x0295:
            java.util.HashMap<java.lang.String, java.lang.Object> r13 = r12.shareParamsMap
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            r13.put(r0, r1)
        L_0x029e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl.formateShareData(cn.sharesdk.framework.Platform):boolean");
    }

    /* access modifiers changed from: package-private */
    public final Platform.ShareParams shareDataToShareParams(Platform platform) {
        HashMap<String, Object> hashMap;
        if (platform == null || (hashMap = this.shareParamsMap) == null) {
            toast("ssdk_oks_share_failed");
            return null;
        }
        try {
            Bitmap bitmap = (Bitmap) ResHelper.forceCast(this.shareParamsMap.get("viewToShare"));
            if (TextUtils.isEmpty((String) ResHelper.forceCast(hashMap.get("imagePath"))) && bitmap != null && !bitmap.isRecycled()) {
                String cachePath = ResHelper.getCachePath(MobSDK.getContext(), "screenshot");
                File file = new File(cachePath, String.valueOf(System.currentTimeMillis()) + ".jpg");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                this.shareParamsMap.put("imagePath", file.getAbsolutePath());
            }
            return new Platform.ShareParams(this.shareParamsMap);
        } catch (Throwable th) {
            th.printStackTrace();
            toast("ssdk_oks_share_failed");
            return null;
        }
    }

    private void toast(final String str) {
        UIHandler.sendEmptyMessage(0, new Handler.Callback() {
            public boolean handleMessage(Message message) {
                int stringRes = ResHelper.getStringRes(OnekeyShareThemeImpl.this.context, str);
                if (stringRes > 0) {
                    Toast.makeText(OnekeyShareThemeImpl.this.context, stringRes, 0).show();
                } else {
                    Toast.makeText(OnekeyShareThemeImpl.this.context, str, 0).show();
                }
                return false;
            }
        });
    }

    public final void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Message message = new Message();
        message.arg1 = 1;
        message.arg2 = i;
        message.obj = platform;
        UIHandler.sendMessage(message, this);
    }

    public final void onError(Platform platform, int i, Throwable th) {
        th.printStackTrace();
        Message message = new Message();
        message.arg1 = 2;
        message.arg2 = i;
        message.obj = th;
        UIHandler.sendMessage(message, this);
        ShareSDK.logDemoEvent(4, platform);
    }

    public final void onCancel(Platform platform, int i) {
        Message message = new Message();
        message.arg1 = 3;
        message.arg2 = i;
        message.obj = platform;
        UIHandler.sendMessage(message, this);
        ShareSDK.logDemoEvent(5, platform);
    }

    public final boolean handleMessage(Message message) {
        int i = message.arg1;
        if (i == 1) {
            int stringRes = ResHelper.getStringRes(this.context, "ssdk_oks_share_completed");
            if (stringRes <= 0) {
                return false;
            }
            toast(this.context.getString(stringRes));
            return false;
        } else if (i == 2) {
            String simpleName = message.obj.getClass().getSimpleName();
            if ("WechatClientNotExistException".equals(simpleName) || "WechatTimelineNotSupportedException".equals(simpleName) || "WechatFavoriteNotSupportedException".equals(simpleName)) {
                toast("ssdk_wechat_client_inavailable");
                return false;
            } else if ("GooglePlusClientNotExistException".equals(simpleName)) {
                toast("ssdk_google_plus_client_inavailable");
                return false;
            } else if ("QQClientNotExistException".equals(simpleName)) {
                toast("ssdk_qq_client_inavailable");
                return false;
            } else if ("YixinClientNotExistException".equals(simpleName) || "YixinTimelineNotSupportedException".equals(simpleName)) {
                toast("ssdk_yixin_client_inavailable");
                return false;
            } else if ("KakaoTalkClientNotExistException".equals(simpleName)) {
                toast("ssdk_kakaotalk_client_inavailable");
                return false;
            } else if ("KakaoStoryClientNotExistException".equals(simpleName)) {
                toast("ssdk_kakaostory_client_inavailable");
                return false;
            } else if ("WhatsAppClientNotExistException".equals(simpleName)) {
                toast("ssdk_whatsapp_client_inavailable");
                return false;
            } else if ("FacebookMessengerClientNotExistException".equals(simpleName)) {
                toast("ssdk_facebookmessenger_client_inavailable");
                return false;
            } else {
                toast("ssdk_oks_share_failed");
                return false;
            }
        } else if (i != 3) {
            return false;
        } else {
            toast("ssdk_oks_share_canceled");
            return false;
        }
    }
}
