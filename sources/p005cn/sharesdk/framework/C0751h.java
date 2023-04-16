package p005cn.sharesdk.framework;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import androidx.core.view.ViewCompat;
import com.mob.commons.eventrecoder.EventRecorder;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import p005cn.sharesdk.framework.authorize.C0697b;
import p005cn.sharesdk.framework.authorize.C0701f;
import p005cn.sharesdk.framework.loopshare.LoopSharePasswordListener;
import p005cn.sharesdk.framework.loopshare.LoopShareResultListener;
import p005cn.sharesdk.framework.loopshare.MobLinkAPI;
import p005cn.sharesdk.framework.loopshare.MoblinkActionListener;
import p005cn.sharesdk.framework.loopshare.watermark.C0765c;
import p005cn.sharesdk.framework.loopshare.watermark.FirstPic;
import p005cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener;
import p005cn.sharesdk.framework.loopshare.watermark.WaterMarkListener;
import p005cn.sharesdk.framework.p007b.C0724d;
import p005cn.sharesdk.framework.p007b.p009b.C0715a;
import p005cn.sharesdk.framework.p007b.p009b.C0717c;
import p005cn.sharesdk.framework.p007b.p009b.C0718d;
import p005cn.sharesdk.framework.utils.C0813i;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

/* renamed from: cn.sharesdk.framework.h */
/* compiled from: ShareSDKCore */
public class C0751h {
    /* renamed from: a */
    public static ArrayList<Platform> m381a() {
        ArrayList<Platform> f = m400f();
        m390a(f);
        return f;
    }

    /* renamed from: f */
    private static ArrayList<Platform> m400f() {
        ArrayList<Platform> arrayList = new ArrayList<>();
        for (String importClass : new String[]{"cn.sharesdk.douban.Douban", "cn.sharesdk.evernote.Evernote", "cn.sharesdk.facebook.Facebook", "cn.sharesdk.renren.Renren", "cn.sharesdk.sina.weibo.SinaWeibo", "cn.sharesdk.kaixin.KaiXin", "cn.sharesdk.linkedin.LinkedIn", "cn.sharesdk.system.email.Email", "cn.sharesdk.system.text.ShortMessage", "cn.sharesdk.tencent.qq.QQ", "cn.sharesdk.tencent.qzone.QZone", "cn.sharesdk.tencent.weibo.TencentWeibo", "cn.sharesdk.twitter.Twitter", "cn.sharesdk.wechat.friends.Wechat", "cn.sharesdk.wechat.moments.WechatMoments", "cn.sharesdk.wechat.favorite.WechatFavorite", "cn.sharesdk.youdao.YouDao", "cn.sharesdk.google.GooglePlus", "cn.sharesdk.foursquare.FourSquare", "cn.sharesdk.pinterest.Pinterest", "cn.sharesdk.flickr.Flickr", "cn.sharesdk.tumblr.Tumblr", "cn.sharesdk.dropbox.Dropbox", "cn.sharesdk.vkontakte.VKontakte", "cn.sharesdk.instagram.Instagram", "cn.sharesdk.yixin.friends.Yixin", "cn.sharesdk.yixin.moments.YixinMoments", "cn.sharesdk.mingdao.Mingdao", "cn.sharesdk.line.Line", "cn.sharesdk.kakao.story.KakaoStory", "cn.sharesdk.kakao.talk.KakaoTalk", "cn.sharesdk.whatsapp.WhatsApp", "cn.sharesdk.pocket.Pocket", "cn.sharesdk.instapaper.Instapaper", "cn.sharesdk.facebookmessenger.FacebookMessenger", "cn.sharesdk.alipay.friends.Alipay", "cn.sharesdk.alipay.moments.AlipayMoments", "cn.sharesdk.dingding.friends.Dingding", "cn.sharesdk.youtube.Youtube", "cn.sharesdk.meipai.Meipai", "cn.sharesdk.telegram.Telegram", "cn.sharesdk.cmcc.Cmcc", "cn.sharesdk.reddit.Reddit", "cn.sharesdk.telecom.Telecom", "cn.sharesdk.accountkit.Accountkit", "cn.sharesdk.douyin.Douyin", "cn.sharesdk.wework.Wework", "cn.sharesdk.oasis.Oasis", "cn.sharesdk.hwaccount.HWAccount", "cn.sharesdk.xmaccount.XMAccount", "cn.sharesdk.snapchat.Snapchat", "cn.sharesdk.littleredbook.Littleredbook", "cn.sharesdk.kuaishou.Kuaishou", "cn.sharesdk.watermelonvideo.Watermelonvideo", "cn.sharesdk.tiktok.Tiktok"}) {
            try {
                arrayList.add((Platform) ReflectHelper.newInstance(ReflectHelper.importClass(importClass), new Object[0]));
            } catch (Throwable unused) {
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m390a(ArrayList<Platform> arrayList) {
        if (arrayList != null) {
            Collections.sort(arrayList, new Comparator<Platform>() {
                /* renamed from: a */
                public int compare(Platform platform, Platform platform2) {
                    int platformId;
                    int platformId2;
                    if (platform.getSortId() != platform2.getSortId()) {
                        platformId = platform.getSortId();
                        platformId2 = platform2.getSortId();
                    } else {
                        platformId = platform.getPlatformId();
                        platformId2 = platform2.getPlatformId();
                    }
                    return platformId - platformId2;
                }
            });
        }
    }

    /* renamed from: a */
    public static void m384a(Activity activity) {
        C0697b c = C0697b.m141c();
        if (c != null) {
            c.mo10539a(activity);
        }
    }

    /* renamed from: b */
    public static Activity m395b() {
        return C0697b.m141c().mo10542b();
    }

    /* renamed from: a */
    public static void m393a(boolean z) {
        C0697b c = C0697b.m141c();
        if (c != null) {
            c.mo10540a(z);
        }
    }

    /* renamed from: c */
    public static boolean m397c() {
        return C0697b.m141c().mo10541a();
    }

    /* renamed from: b */
    public static void m396b(boolean z) {
        C0701f c = C0701f.m152c();
        if (c != null) {
            c.mo10548a(z);
        }
    }

    /* renamed from: d */
    public static boolean m398d() {
        return C0701f.m152c().mo10549a();
    }

    /* renamed from: a */
    public static void m385a(Context context, ReadQrImageListener readQrImageListener) {
        FirstPic.m473a(context, readQrImageListener);
    }

    /* renamed from: a */
    public static void m389a(String str, String str2, String str3, String str4, WaterMarkListener waterMarkListener) {
        C0765c.m483a(str, str2, str3, str4, waterMarkListener);
    }

    /* renamed from: a */
    public static Bitmap m380a(String str, int i, int i2) {
        C0813i.m698a();
        return C0813i.m697a(str, i, i2, "UTF_8", "H", "2", ViewCompat.MEASURED_STATE_MASK, -1);
    }

    /* renamed from: a */
    public static void m391a(HashMap<String, Object> hashMap, MoblinkActionListener moblinkActionListener) {
        try {
            MobLinkAPI.m450a();
            MobLinkAPI.m455a(hashMap, moblinkActionListener);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("ShareSDKCore mobLinkGetMobID " + th, new Object[0]);
        }
    }

    /* renamed from: a */
    public static void m392a(HashMap<String, Object> hashMap, String str, LoopSharePasswordListener loopSharePasswordListener) {
        try {
            MobLinkAPI.m450a();
            MobLinkAPI.m456a(hashMap, str, loopSharePasswordListener);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("ShareSDKCore preparePassWord " + th, new Object[0]);
        }
    }

    /* renamed from: a */
    public static void m394a(boolean z, LoopSharePasswordListener loopSharePasswordListener) {
        try {
            MobLinkAPI.m450a();
            MobLinkAPI.m457a(z, loopSharePasswordListener);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("ShareSDKCore readPassWord " + th, new Object[0]);
        }
    }

    /* renamed from: a */
    public static void m387a(LoopShareResultListener loopShareResultListener) {
        try {
            MobLinkAPI.m450a();
            MobLinkAPI.m459b(loopShareResultListener);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e("ShareSDKCore prepareLoopShare " + th, new Object[0]);
        }
    }

    /* renamed from: e */
    public static HashMap<String, Object> m399e() {
        MobLinkAPI.m450a();
        return MobLinkAPI.m461d();
    }

    /* renamed from: a */
    public static void m386a(Handler handler) {
        C0724d a = C0724d.m310a();
        if (a != null) {
            a.mo10639a(handler);
            a.mo10741e();
        }
    }

    /* renamed from: a */
    public static void m383a(int i, Platform platform) {
        C0718d dVar = new C0718d();
        if (i == 1) {
            dVar.f245a = "SHARESDK_ENTER_SHAREMENU";
        } else if (i == 2) {
            dVar.f245a = "SHARESDK_CANCEL_SHAREMENU";
        } else if (i == 3) {
            dVar.f245a = "SHARESDK_EDIT_SHARE";
        } else if (i == 4) {
            dVar.f245a = "SHARESDK_FAILED_SHARE";
        } else if (i == 5) {
            dVar.f245a = "SHARESDK_CANCEL_SHARE";
        }
        if (platform != null) {
            dVar.f246b = platform.getPlatformId();
        }
        C0724d a = C0724d.m310a();
        if (a != null) {
            a.mo10641a((C0717c) dVar);
        }
    }

    /* renamed from: a */
    public static void m388a(String str, int i) {
        C0724d a = C0724d.m310a();
        if (a != null) {
            C0715a aVar = new C0715a();
            aVar.f228b = str;
            aVar.f227a = i;
            a.mo10641a((C0717c) aVar);
        }
    }

    /* renamed from: a */
    public static HashMap<Integer, HashMap<String, Object>> m382a(HashMap<String, Object> hashMap) {
        int i;
        HashMap<Integer, HashMap<String, Object>> hashMap2 = null;
        if (hashMap != null && hashMap.size() > 0) {
            ArrayList arrayList = (ArrayList) hashMap.get("fakelist");
            if (arrayList == null) {
                return null;
            }
            hashMap2 = new HashMap<>();
            EventRecorder.addBegin(OnekeyShare.SHARESDK_TAG, "parseDevInfo");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                HashMap hashMap3 = (HashMap) it.next();
                if (hashMap3 != null) {
                    try {
                        i = ResHelper.parseInt(String.valueOf(hashMap3.get("snsplat")));
                    } catch (Throwable th) {
                        SSDKLog.m645b().mo29787w(th);
                        i = -1;
                    }
                    if (i != -1) {
                        hashMap2.put(Integer.valueOf(i), hashMap3);
                    }
                }
            }
            EventRecorder.addEnd(OnekeyShare.SHARESDK_TAG, "parseDevInfo");
        }
        return hashMap2;
    }
}
