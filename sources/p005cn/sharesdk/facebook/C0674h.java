package p005cn.sharesdk.facebook;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import com.baidubce.BceConfig;
import com.facebook.GraphRequest;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import p005cn.sharesdk.framework.C0734e;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.ShareSDK;
import p005cn.sharesdk.framework.authorize.AuthorizeListener;
import p005cn.sharesdk.framework.authorize.C0698c;
import p005cn.sharesdk.framework.authorize.C0699d;
import p005cn.sharesdk.framework.authorize.C0700e;
import p005cn.sharesdk.framework.authorize.C0702g;
import p005cn.sharesdk.framework.authorize.SSOListener;
import p005cn.sharesdk.framework.p006a.C0693b;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.facebook.h */
/* compiled from: FbHelper */
public class C0674h extends C0734e {

    /* renamed from: b */
    private static final String[] f113b = {"email", "public_profile"};

    /* renamed from: c */
    private static C0674h f114c;

    /* renamed from: d */
    private String f115d;

    /* renamed from: e */
    private String f116e;

    /* renamed from: f */
    private long f117f;

    /* renamed from: g */
    private String f118g;

    /* renamed from: h */
    private C0693b f119h = C0693b.m120a();

    /* renamed from: i */
    private String[] f120i;

    /* renamed from: j */
    private String f121j;

    /* renamed from: a */
    public static C0674h m65a(Platform platform) {
        if (f114c == null) {
            f114c = new C0674h(platform);
        }
        return f114c;
    }

    private C0674h(Platform platform) {
        super(platform);
    }

    /* renamed from: a */
    public void mo10214a(String str) {
        this.f118g = str;
    }

    /* renamed from: a */
    public void mo10215a(String str, String str2) {
        this.f116e = str;
        if (str2 != null && !str2.equals("0")) {
            try {
                this.f117f = System.currentTimeMillis() + (Long.valueOf(str2).longValue() * 1000);
            } catch (Throwable th) {
                SSDKLog.m645b().mo29769d(th);
            }
        }
    }

    /* renamed from: a */
    public boolean mo10217a() {
        return this.f116e != null && (this.f117f == 0 || System.currentTimeMillis() < this.f117f);
    }

    public String getAuthorizeUrl() {
        Bundle bundle = new Bundle();
        bundle.putString("app_id", this.f118g);
        bundle.putString("client_id", this.f118g);
        bundle.putString(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, ServerProtocol.DIALOG_REREQUEST_AUTH_TYPE);
        bundle.putString(ServerProtocol.DIALOG_PARAM_DEFAULT_AUDIENCE, NativeProtocol.AUDIENCE_FRIENDS);
        bundle.putString(ServerProtocol.DIALOG_PARAM_DISPLAY, "touch");
        bundle.putString("fbapp_pres", "1");
        bundle.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, this.f121j);
        bundle.putString(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, "token,signed_request");
        bundle.putString(ServerProtocol.DIALOG_PARAM_RETURN_SCOPES, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        bundle.putString(ServerProtocol.DIALOG_PARAM_SDK_VERSION, Constants.PLATFORM);
        bundle.putString("sdk_version", "5.4.0");
        bundle.putString(ServerProtocol.DIALOG_PARAM_STATE, "{\"challenge\":\"G/I5SknMfRmyvIr/q5bFJNwIqI8=\"}");
        bundle.putString("title", "Log In");
        bundle.putString("type", "user_agent");
        String[] strArr = this.f120i;
        if (strArr == null) {
            strArr = f113b;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(str);
            i++;
        }
        this.f115d = "https://m.facebook.com/v4.0/dialog/oauth" + "?" + ResHelper.encodeUrl(bundle);
        ShareSDK.logApiEvent("/dialog/oauth", mo10656c());
        SSDKLog.m645b().mo29786w("FbHelper===> " + this.f115d);
        return this.f115d;
    }

    /* renamed from: a */
    public void mo10216a(String[] strArr) {
        this.f120i = strArr;
    }

    public C0698c getAuthorizeWebviewClient(C0702g gVar) {
        return new C0672g(gVar);
    }

    public String getRedirectUri() {
        return this.f121j;
    }

    /* renamed from: b */
    public boolean mo10219b() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
        intent.putExtra("client_id", this.f118g);
        String[] strArr = this.f120i;
        if (strArr != null && strArr.length > 0) {
            intent.putExtra("scope", TextUtils.join(",", strArr));
        }
        ResolveInfo resolveActivity = MobSDK.getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        try {
            for (Signature charsString : MobSDK.getContext().getPackageManager().getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures) {
                if ("30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2".equals(charsString.toCharsString())) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public C0700e getSSOProcessor(C0699d dVar) {
        C0671f fVar = new C0671f(dVar);
        fVar.mo10546a(32525);
        String str = this.f118g;
        String[] strArr = this.f120i;
        if (strArr == null) {
            strArr = f113b;
        }
        fVar.mo10205a(str, strArr);
        return fVar;
    }

    /* renamed from: a */
    public void mo10213a(final AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            mo10655b(authorizeListener);
        } else {
            mo10654a(new SSOListener() {
                public void onFailed(Throwable th) {
                    SSDKLog.m645b().mo29769d(th);
                    C0674h.this.mo10655b(authorizeListener);
                }

                public void onComplete(Bundle bundle) {
                    authorizeListener.onComplete(bundle);
                }

                public void onCancel() {
                    authorizeListener.onCancel();
                }
            });
        }
    }

    /* renamed from: a */
    public void mo10211a(Platform.ShareParams shareParams, PlatformActionListener platformActionListener) throws Throwable {
        String imageUrl = shareParams.getImageUrl();
        String title = shareParams.getTitle();
        String text = shareParams.getText();
        String musicUrl = shareParams.getMusicUrl();
        String url = shareParams.getUrl();
        String titleUrl = shareParams.getTitleUrl();
        if (!TextUtils.isEmpty(titleUrl)) {
            titleUrl = this.f305a.getShortLintk(titleUrl, false);
            shareParams.setTitleUrl(titleUrl);
        } else if (!TextUtils.isEmpty(url)) {
            titleUrl = this.f305a.getShortLintk(url, false);
            shareParams.setUrl(titleUrl);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("https://www.facebook.com/dialog/feed?");
        sb.append("app_id=");
        sb.append(this.f118g);
        sb.append("&redirect_uri=fbconnect://success");
        sb.append("&link=");
        sb.append(Data.urlEncode(titleUrl, "utf-8"));
        if (!TextUtils.isEmpty(shareParams.getQuote())) {
            sb.append("&quote=");
            sb.append(Data.urlEncode(shareParams.getQuote(), "utf-8"));
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            sb.append("&picture=");
            sb.append(Data.urlEncode(imageUrl, "utf-8"));
        }
        if (!TextUtils.isEmpty(title)) {
            sb.append("&caption=");
            sb.append(Data.urlEncode(title, "utf-8"));
        }
        if (!TextUtils.isEmpty(text)) {
            sb.append("&description=");
            sb.append(Data.urlEncode(text, "utf-8"));
        }
        if (!TextUtils.isEmpty(musicUrl)) {
            sb.append("&source=");
            sb.append(Data.urlEncode(musicUrl, "utf-8"));
            if (!TextUtils.isEmpty(text)) {
                sb.append("&name=");
                sb.append(Data.urlEncode(text, "utf-8"));
            }
        }
        C0678k kVar = new C0678k();
        kVar.mo10235a(sb.toString());
        kVar.mo10234a(platformActionListener);
        kVar.show(MobSDK.getContext(), (Intent) null);
    }

    /* renamed from: b */
    public HashMap<String, Object> mo10218b(String str) throws Throwable {
        String str2;
        if (str != null) {
            str2 = BceConfig.BOS_DELIMITER + str;
        } else {
            str2 = "/me";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.f116e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair(GraphRequest.FIELDS_PARAM, "id,name,first_name,middle_name,last_name,gender,locale,languages,link,age_range,third_party_id,installed,timezone,updated_time,verified,birthday,currency,devices,education,email,hometown,interested_in,location,political,payment_pricepoints,favorite_athletes,favorite_teams,picture.type(large),quotes,relationship_status,religion,significant_other,video_upload_limits,website,work"));
        String a = this.f119h.mo10493a("https://graph.facebook.com/v7.0" + str2, arrayList, "get_user_info", mo10656c());
        SSDKLog.m645b().mo29775i("facebook helper getUser", new Object[0]);
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    /* renamed from: a */
    public HashMap<String, Object> mo10209a(int i, int i2, String str) throws Throwable {
        String str2 = str != null ? str : "me";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.f116e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair("limit", String.valueOf(i)));
        arrayList.add(new KVPair("offset", String.valueOf(i2)));
        arrayList.add(new KVPair(GraphRequest.FIELDS_PARAM, "id,name,first_name,middle_name,last_name,gender,locale,languages,link,age_range,third_party_id,installed,timezone,updated_time,verified,birthday,cover,currency,devices,education,email,hometown,interested_in,location,political,payment_pricepoints,favorite_athletes,favorite_teams,picture,quotes,relationship_status,religion,significant_other,video_upload_limits,website,work"));
        String str3 = !TextUtils.isEmpty(str) ? "/taggable_friends" : "/friends";
        String a = this.f119h.mo10493a("https://graph.facebook.com/v7.0/" + str2 + str3, arrayList, NativeProtocol.AUDIENCE_FRIENDS, mo10656c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    /* renamed from: a */
    public HashMap<String, Object> mo10210a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) throws Throwable {
        KVPair kVPair;
        String str3;
        if (str2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (hashMap != null && hashMap.size() > 0) {
            for (Map.Entry next : hashMap.entrySet()) {
                arrayList.add(new KVPair((String) next.getKey(), String.valueOf(next.getValue())));
            }
        }
        arrayList.add(new KVPair("access_token", this.f116e));
        arrayList.add(new KVPair("format", "json"));
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            KVPair kVPair2 = null;
            for (Map.Entry next2 : hashMap2.entrySet()) {
                kVPair2 = new KVPair((String) next2.getKey(), next2.getValue());
            }
            kVPair = kVPair2;
        }
        if ("GET".equals(str2.toUpperCase())) {
            str3 = this.f119h.httpGet(str, arrayList, (ArrayList<KVPair<String>>) null, (NetworkHelper.NetworkTimeOut) null);
        } else {
            str3 = "POST".equals(str2.toUpperCase()) ? this.f119h.httpPost(str, (ArrayList<KVPair<String>>) arrayList, (KVPair<String>) kVPair, (ArrayList<KVPair<String>>) null, (NetworkHelper.NetworkTimeOut) null) : null;
        }
        if (str3 == null || str3.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(str3);
    }

    /* renamed from: c */
    public void mo10220c(String str) {
        this.f121j = str;
    }

    /* renamed from: a */
    public void mo10212a(PlatformActionListener platformActionListener, Platform.ShareParams shareParams) {
        SSDKLog.m645b().mo29768d("Facebook share by primordial appClientShare", new Object[0]);
        Intent intent = new Intent();
        intent.putExtra(ShareConstants.TITLE, shareParams.getTitle());
        C0677j jVar = new C0677j();
        jVar.mo10232a(platformActionListener, this.f305a, shareParams, this.f118g);
        jVar.show(MobSDK.getContext(), intent);
    }
}
