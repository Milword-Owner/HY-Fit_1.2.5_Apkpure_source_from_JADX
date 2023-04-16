package p005cn.sharesdk.framework.loopshare;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import cn.sharesdk.loopshare.ActionListener;
import cn.sharesdk.loopshare.MobLink;
import cn.sharesdk.loopshare.RestoreSceneListener;
import cn.sharesdk.loopshare.Scene;
import cn.sharesdk.loopshare.beans.SceneData;
import cn.sharesdk.loopshare.utils.b;
import com.baidu.mobstat.Config;
import com.facebook.internal.NativeProtocol;
import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p005cn.sharesdk.framework.ShareSDK;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.loopshare.MobLinkAPI */
public class MobLinkAPI {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static MoblinkActionListener f355a;

    /* renamed from: b */
    private static LoopShareResultListener f356b;

    /* renamed from: c */
    private static LoopSharePasswordListener f357c;

    /* renamed from: d */
    private static volatile MobLinkAPI f358d;

    /* renamed from: a */
    public static MobLinkAPI m450a() {
        synchronized (MobLinkAPI.class) {
            if (f358d == null) {
                synchronized (MobLinkAPI.class) {
                    if (f358d == null) {
                        f358d = new MobLinkAPI();
                    }
                }
            }
        }
        return f358d;
    }

    /* renamed from: b */
    public static LoopShareResultListener m458b() {
        return f356b;
    }

    /* renamed from: a */
    public static void m454a(LoopShareResultListener loopShareResultListener) {
        f356b = loopShareResultListener;
    }

    /* renamed from: c */
    public static LoopSharePasswordListener m460c() {
        return f357c;
    }

    /* renamed from: a */
    public static void m453a(LoopSharePasswordListener loopSharePasswordListener) {
        f357c = loopSharePasswordListener;
    }

    /* renamed from: a */
    public static void m455a(HashMap<String, Object> hashMap, MoblinkActionListener moblinkActionListener) {
        if (moblinkActionListener != null) {
            try {
                f355a = moblinkActionListener;
                Scene scene = new Scene();
                scene.setPath(String.valueOf(hashMap.get(Config.FEED_LIST_ITEM_PATH)));
                if (hashMap.get(NativeProtocol.WEB_DIALOG_PARAMS) instanceof HashMap) {
                    scene.setParams((HashMap) hashMap.get(NativeProtocol.WEB_DIALOG_PARAMS));
                } else if (f355a != null) {
                    f355a.onError(new Throwable("setLoopshareCustomParams方法中 params 为key的时候，value需要为HashMap类型"));
                    return;
                }
                MobLink.getMobID(scene, new ActionListener<String>() {
                    public void onResult(String str) {
                        if (MobLinkAPI.f355a != null) {
                            MobLinkAPI.f355a.onResult(str);
                            MoblinkActionListener unused = MobLinkAPI.f355a = null;
                        }
                    }

                    public void onError(Throwable th) {
                        if (MobLinkAPI.f355a != null) {
                            MobLinkAPI.f355a.onError(th);
                            MoblinkActionListener unused = MobLinkAPI.f355a = null;
                        }
                    }
                });
            } catch (Throwable th) {
                NLog b = SSDKLog.m645b();
                b.mo29771e("LoopShare MobLinkAPI mobLinkGetMobID" + th, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    private static String m452a(String str) {
        Matcher matcher = Pattern.compile("#(.*?)#").matcher(str);
        return matcher.find() ? matcher.group(1) : "";
    }

    /* renamed from: a */
    public static void m457a(boolean z, LoopSharePasswordListener loopSharePasswordListener) {
        if (loopSharePasswordListener != null) {
            m453a(loopSharePasswordListener);
            String f = m463f();
            NLog b = SSDKLog.m645b();
            b.mo29768d("LoopShare MobLinkAPI readPassWord clipContent: " + f, new Object[0]);
            try {
                if (!TextUtils.isEmpty(f)) {
                    String a = m452a(f);
                    if (TextUtils.isEmpty(a)) {
                        SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI readPassWord Regular match string is null ", new Object[0]);
                        if (m460c() != null) {
                            m460c().onError(new Throwable("readPassWord Regular match string is error"));
                            return;
                        }
                        return;
                    }
                    NLog b2 = SSDKLog.m645b();
                    b2.mo29768d("LoopShare MobLinkAPI readPassWord read mobId is: " + a, new Object[0]);
                    if (z) {
                        m464g();
                    }
                    b.a(a, new ActionListener<SceneData>() {
                        public void onResult(SceneData sceneData) {
                            MobLinkAPI.m460c().onResult(sceneData.a().params);
                            SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI readPassWord onResult is ok", new Object[0]);
                        }

                        public void onError(Throwable th) {
                            MobLinkAPI.m460c().onError(th);
                            NLog b = SSDKLog.m645b();
                            b.mo29768d("LoopShare MobLinkAPI readPassWord onError: " + th, new Object[0]);
                        }
                    });
                } else if (m460c() != null) {
                    m460c().onError(new Throwable("The clipboard is empty"));
                }
            } catch (Throwable th) {
                if (m460c() != null) {
                    m460c().onError(new Throwable("readPassWord catch: " + th));
                }
            }
        } else {
            SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI readPassWord listener is null", new Object[0]);
        }
    }

    /* renamed from: f */
    private static String m463f() {
        ClipboardManager clipboardManager;
        try {
            if (Build.VERSION.SDK_INT >= 11 && (clipboardManager = (ClipboardManager) MobSDK.getContext().getSystemService("clipboard")) != null && clipboardManager.hasPrimaryClip() && clipboardManager.getPrimaryClip().getItemCount() > 0) {
                CharSequence text = clipboardManager.getPrimaryClip().getItemAt(0).getText();
                if (!TextUtils.isEmpty(text)) {
                    return String.valueOf(text);
                }
                return null;
            }
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("LoopShare MobLinkAPI getClipContent catch: " + th, new Object[0]);
        }
        return null;
    }

    /* renamed from: g */
    private static void m464g() {
        ClipboardManager clipboardManager;
        try {
            if (Build.VERSION.SDK_INT >= 11 && (clipboardManager = (ClipboardManager) MobSDK.getContext().getSystemService("clipboard")) != null) {
                clipboardManager.setPrimaryClip(clipboardManager.getPrimaryClip());
                clipboardManager.setText((CharSequence) null);
            }
            SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI clearClipboard is ok", new Object[0]);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("LoopShare MobLinkAPI clearClipboard catch: " + th, new Object[0]);
        }
    }

    /* renamed from: a */
    public static void m456a(HashMap<String, Object> hashMap, final String str, LoopSharePasswordListener loopSharePasswordListener) {
        if (loopSharePasswordListener != null) {
            try {
                m453a(loopSharePasswordListener);
                ShareSDK.mobLinkGetMobID(hashMap, new MoblinkActionListener() {
                    public void onResult(Object obj) {
                        String str;
                        if (obj != null) {
                            if (!TextUtils.isEmpty(str)) {
                                str = str + "#" + obj + "#";
                            } else {
                                str = "#" + obj + "#";
                            }
                            MobLinkAPI.m460c().onResult(str);
                            SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI preparePassWord callback is ok", new Object[0]);
                            try {
                                if (Build.VERSION.SDK_INT >= 11) {
                                    Context context = MobSDK.getContext();
                                    MobSDK.getContext();
                                    ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("CLI_LAB_A", str));
                                    SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI preparePassWord ClipData is ok", new Object[0]);
                                }
                            } catch (Throwable th) {
                                SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI preparePassWord ClipData catch: " + th, new Object[0]);
                            }
                        } else {
                            MobLinkAPI.m460c().onError(new Throwable("mobId is null"));
                        }
                    }

                    public void onError(Throwable th) {
                        MobLinkAPI.m460c().onError(th);
                    }
                });
                SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI preparePassWord is OK", new Object[0]);
            } catch (Throwable th) {
                NLog b = SSDKLog.m645b();
                b.mo29771e("LoopShare MobLinkAPI preparePassWord " + th, new Object[0]);
            }
        } else {
            SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI preparePassWord listener is null", new Object[0]);
        }
    }

    /* renamed from: b */
    public static void m459b(LoopShareResultListener loopShareResultListener) {
        if (loopShareResultListener != null) {
            try {
                m454a(loopShareResultListener);
            } catch (Throwable th) {
                NLog b = SSDKLog.m645b();
                b.mo29771e("LoopShare MobLinkAPI prepareLoopShare " + th, new Object[0]);
                return;
            }
        }
        MobLink.registerSpecifiedSchemeListener("sdfwe435fdsr34656uthfwer32ufeh439==", new SceneListener());
        SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI prepareLoopShare is OK", new Object[0]);
    }

    /* renamed from: d */
    public static HashMap<String, Object> m461d() {
        try {
            String trim = new C0761a(MobSDK.getContext(), "sharesdk_moblink_sp").mo10768b("share_restore_extra", (Object) null).toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                return new Hashon().fromJson(trim);
            }
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("LoopShare MobLinkAPI getCustomDataFromLoopShare " + th, new Object[0]);
        }
        return new HashMap<>();
    }

    /* renamed from: cn.sharesdk.framework.loopshare.MobLinkAPI$SceneListener */
    private static class SceneListener implements RestoreSceneListener {
        public void completeRestore(Scene scene) {
        }

        public void notFoundScene(Scene scene) {
        }

        private SceneListener() {
        }

        public Class<? extends Activity> willRestoreScene(Scene scene) {
            SSDKLog.m645b().mo29768d("LoopShare MobLinkAPI willRestoreScene ==> RestoreTempActivity", new Object[0]);
            return RestoreTempActivity.class;
        }
    }
}
