package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.WebView;
import com.baidu.mobstat.C0890ai;
import com.baidu.mobstat.C0894aj;
import com.facebook.share.internal.ShareConstants;
import com.huayu.tzc.utils.DateUtil;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.al */
public class C0900al {

    /* renamed from: B */
    private static final C0900al f967B = new C0900al();

    /* renamed from: A */
    private C0958be f968A = C0958be.m1204a();

    /* renamed from: C */
    private Handler f969C = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 32:
                    C0900al.this.mo11500b();
                    return;
                case 33:
                    C0900al.this.mo11502c();
                    return;
                case 34:
                    C0900al.this.m903h();
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: D */
    private C0894aj.C0897a f970D = new C0894aj.C0897a() {
        /* renamed from: a */
        public void mo11465a() {
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc.m1198c().mo11624a("onGesture");
            }
            C0900al.this.m905i();
        }
    };

    /* renamed from: E */
    private boolean f971E = true;

    /* renamed from: F */
    private JSONArray f972F = new JSONArray();

    /* renamed from: G */
    private Object f973G = new Object();

    /* renamed from: a */
    private Context f974a;

    /* renamed from: b */
    private C0894aj f975b;

    /* renamed from: c */
    private C0890ai f976c;

    /* renamed from: d */
    private Activity f977d;

    /* renamed from: e */
    private C0906am f978e = new C0906am();
    /* access modifiers changed from: private */

    /* renamed from: f */
    public Handler f979f;

    /* renamed from: g */
    private HandlerThread f980g;

    /* renamed from: h */
    private Handler f981h;

    /* renamed from: i */
    private HandlerThread f982i = new HandlerThread("crawlerThread");

    /* renamed from: j */
    private volatile boolean f983j;

    /* renamed from: k */
    private volatile boolean f984k;

    /* renamed from: l */
    private volatile boolean f985l;

    /* renamed from: m */
    private volatile boolean f986m;

    /* renamed from: n */
    private volatile boolean f987n;

    /* renamed from: o */
    private volatile boolean f988o;

    /* renamed from: p */
    private volatile String f989p;

    /* renamed from: q */
    private volatile String f990q;

    /* renamed from: r */
    private volatile String f991r;

    /* renamed from: s */
    private long f992s;

    /* renamed from: t */
    private long f993t;

    /* renamed from: u */
    private long f994u;

    /* renamed from: v */
    private String f995v;

    /* renamed from: w */
    private boolean f996w;

    /* renamed from: x */
    private String f997x;

    /* renamed from: y */
    private JSONObject f998y = new JSONObject();

    /* renamed from: z */
    private JSONObject f999z = new JSONObject();

    /* renamed from: a */
    public void mo11494a(Activity activity) {
    }

    /* renamed from: a */
    public static C0900al m880a() {
        return f967B;
    }

    private C0900al() {
        this.f982i.start();
        this.f981h = new C0905c(this.f982i.getLooper());
        this.f980g = new HandlerThread("downloadThread");
        this.f980g.start();
        this.f979f = new C0903a(this.f980g.getLooper());
    }

    /* renamed from: com.baidu.mobstat.al$c */
    class C0905c extends Handler {
        public C0905c(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                C0900al.this.m913o();
            } else if (i == 2) {
                C0900al.this.m917s();
            }
        }
    }

    /* renamed from: com.baidu.mobstat.al$b */
    class C0904b implements C0890ai.C0891a {
        private C0904b() {
        }

        /* renamed from: a */
        public void mo11452a() {
            C0900al.this.m907j();
        }

        /* renamed from: b */
        public void mo11455b() {
            C0900al.this.m909k();
        }

        /* renamed from: a */
        public void mo11454a(boolean z) {
            C0900al.this.m891b(z);
        }

        /* renamed from: a */
        public void mo11453a(String str) {
            Message obtainMessage = C0900al.this.f979f.obtainMessage(24);
            Bundle bundle = new Bundle();
            bundle.putString("autoconfig.key", str);
            obtainMessage.setData(bundle);
            C0900al.this.f979f.sendMessage(obtainMessage);
        }
    }

    /* renamed from: com.baidu.mobstat.al$a */
    class C0903a extends Handler {
        public C0903a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 21:
                    C0900al.this.m910l();
                    return;
                case 22:
                    C0900al.this.m911m();
                    return;
                case 23:
                    C0900al.this.m912n();
                    return;
                case 24:
                    Bundle data = message.getData();
                    if (data != null) {
                        C0900al.this.m890b(data.getString("autoconfig.key"));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void mo11497a(String str) {
        this.f995v = str;
    }

    /* renamed from: a */
    public void mo11498a(boolean z) {
        this.f996w = z;
    }

    /* renamed from: a */
    public boolean mo11499a(Activity activity, Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            return false;
        }
        String scheme = data.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            return false;
        }
        try {
            String queryParameter = data.getQueryParameter("token");
            String queryParameter2 = data.getQueryParameter("time");
            if (!scheme.startsWith("mtj") || scheme.length() <= 3) {
                return false;
            }
            String substring = scheme.substring(3);
            if (TextUtils.isEmpty(substring) || !substring.equals(this.f995v.toLowerCase()) || TextUtils.isEmpty(queryParameter)) {
                return false;
            }
            this.f997x = queryParameter;
            String s = C0982bp.m1357a().mo11714s(activity);
            if (TextUtils.isEmpty(queryParameter2) || queryParameter2.equals(s)) {
                return false;
            }
            C0982bp.m1357a().mo11702k(activity, queryParameter2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: d */
    private void m895d(Activity activity) {
        Intent intent;
        if (activity != null && (intent = activity.getIntent()) != null) {
            boolean booleanExtra = intent.getBooleanExtra(MtjConfig.BAIDU_MTJ_PUSH_CALL, false);
            String stringExtra = intent.getStringExtra(MtjConfig.BAIDU_MTJ_PUSH_MSG);
            if (this.f971E) {
                LaunchInfo launchInfo = new LaunchInfo();
                if (booleanExtra) {
                    launchInfo.setPushInfo(C0968bi.m1283e(activity), stringExtra);
                }
                String g = C0968bi.m1286g(activity);
                if (!TextUtils.isEmpty(g)) {
                    launchInfo.setRefererPkgName(g);
                }
                BDStatCore.instance().autoTrackLaunchInfo(this.f974a, launchInfo, true);
            } else {
                LaunchInfo launchInfo2 = new LaunchInfo();
                if (booleanExtra) {
                    launchInfo2.setPushInfo(C0968bi.m1283e(activity), stringExtra);
                }
                String g2 = C0968bi.m1286g(activity);
                if (!TextUtils.isEmpty(g2)) {
                    launchInfo2.setRefererPkgName(g2);
                }
                BDStatCore.instance().autoTrackLaunchInfo(this.f974a, launchInfo2, false);
            }
            this.f971E = false;
        }
    }

    /* renamed from: b */
    public void mo11501b(Activity activity) {
        Intent intent;
        if (m915q()) {
            this.f974a = activity.getApplicationContext();
            if (!(activity == null || (intent = activity.getIntent()) == null || !mo11499a(activity, intent))) {
                m880a().m905i();
            }
            if (this.f977d != null) {
                mo11502c();
            }
            this.f977d = activity;
            m895d(activity);
            m920v();
            m918t();
            m919u();
            m887b(activity, true);
            m897e(activity);
            m902g();
            mo11495a(activity, true);
        }
    }

    /* renamed from: a */
    public void mo11495a(Activity activity, boolean z) {
        if (!(activity instanceof IIgnoreAutoEvent)) {
            if (z) {
                this.f968A.mo11639a(activity, true, this.f999z, this.f996w);
            } else {
                this.f968A.mo11638a(activity, true);
            }
        }
    }

    /* renamed from: c */
    public void mo11503c(Activity activity) {
        if (m915q()) {
            this.f977d = null;
            m887b(activity, false);
            m899f();
            mo11495a(activity, false);
        }
    }

    /* renamed from: a */
    public void mo11496a(WebView webView, String str, C0974bk bkVar) {
        if (TextUtils.isEmpty(this.f989p)) {
            this.f989p = C0980bn.m1345a(this.f974a, C0883af.f941a);
        }
        m888b(webView, this.f989p, bkVar);
        if (TextUtils.isEmpty(this.f990q)) {
            this.f990q = C0980bn.m1345a(this.f974a, C0883af.f942b);
        }
        m892c(webView, this.f990q, bkVar);
    }

    /* renamed from: e */
    private void m897e(Activity activity) {
        if (C0956bc.m1198c().mo11630b()) {
            C0956bc.m1198c().mo11624a("installConnectionTracker");
        }
        this.f975b = new C0894aj(this.f970D);
        this.f975b.mo11460a(activity);
    }

    /* renamed from: f */
    private void m899f() {
        if (C0956bc.m1198c().mo11630b()) {
            C0956bc.m1198c().mo11624a("uninstallConnectionTracker");
        }
        C0894aj ajVar = this.f975b;
        if (ajVar != null) {
            ajVar.mo11461b();
            this.f975b = null;
        }
    }

    /* renamed from: g */
    private void m902g() {
        if (!m914p() || !this.f983j) {
            mo11502c();
        } else {
            mo11500b();
        }
    }

    /* renamed from: b */
    public void mo11500b() {
        Activity activity = this.f977d;
        if (activity != null) {
            C0885ah.m848b(activity);
        }
    }

    /* renamed from: c */
    public void mo11502c() {
        Activity activity = this.f977d;
        if (activity != null) {
            C0885ah.m842a(activity);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public void m903h() {
        this.f991r = C0980bn.m1345a(this.f974a, C0883af.f943c);
        m894c(this.f991r);
        C0936au.m1091b(this.f991r);
        C0913aq.m987a(this.f991r);
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m905i() {
        if (!m914p()) {
            C0955bb.m1194c().mo11624a("autotrace: gesture success");
            mo11492a(0);
            if (!C0991bw.m1472s(this.f974a)) {
                C0955bb.m1194c().mo11624a("autotrace: network invalid, failed to connect to circle server");
                return;
            }
            this.f981h.sendMessage(this.f981h.obtainMessage(1));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public void m907j() {
        if (!this.f984k) {
            this.f979f.sendMessage(this.f979f.obtainMessage(21));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public void m909k() {
        this.f983j = true;
        if (m914p() && this.f983j) {
            this.f969C.sendMessage(this.f969C.obtainMessage(32));
            this.f981h.sendMessage(this.f981h.obtainMessage(2));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m891b(boolean z) {
        this.f983j = false;
        C0906am.m942b();
        this.f981h.removeMessages(2);
        this.f969C.sendMessage(this.f969C.obtainMessage(33));
    }

    /* access modifiers changed from: private */
    /* renamed from: l */
    public void m910l() {
        if (!this.f984k) {
            boolean a = C0962bf.m1225a(this.f974a, this.f995v, 0, true);
            this.f984k = true;
            if (a) {
                this.f989p = C0980bn.m1345a(this.f974a, C0883af.f941a);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: m */
    public void m911m() {
        if (!this.f985l) {
            boolean a = C0962bf.m1225a(this.f974a, this.f995v, 1, true);
            this.f985l = true;
            if (a) {
                this.f990q = C0980bn.m1345a(this.f974a, C0883af.f942b);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: n */
    public void m912n() {
        if (!this.f986m) {
            boolean a = C0962bf.m1225a(this.f974a, this.f995v, 2, true);
            this.f986m = true;
            if (a) {
                this.f969C.sendMessage(this.f969C.obtainMessage(34));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m890b(String str) {
        if (this.f974a != null && !TextUtils.isEmpty(str)) {
            C0982bp.m1357a().mo11684c(this.f974a, System.currentTimeMillis());
            C0980bn.m1347a(this.f974a, C0883af.f943c, str, false);
            this.f969C.sendMessage(this.f969C.obtainMessage(34));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: o */
    public void m913o() {
        C0955bb.m1194c().mo11624a("autotrace: start to connect");
        mo11492a(1);
        if (m914p()) {
            C0955bb.m1194c().mo11624a("autotrace: connect established, no need to duplicate connect");
            return;
        }
        String a = m881a(this.f974a);
        if (C0956bc.m1198c().mo11630b()) {
            String str = "url:";
            if (!TextUtils.isEmpty(a)) {
                str = str + a;
            }
            C0956bc.m1198c().mo11624a(str);
        }
        try {
            this.f976c = new C0890ai(URI.create(a), new C0904b());
        } catch (Exception unused) {
        }
    }

    /* renamed from: p */
    private boolean m914p() {
        C0890ai aiVar = this.f976c;
        return aiVar != null && aiVar.mo11451b();
    }

    /* renamed from: a */
    private String m881a(Context context) {
        ArrayList<Pair> arrayList = new ArrayList<>();
        arrayList.add(new Pair("appKey", "" + this.f995v));
        arrayList.add(new Pair("appVersion", C0991bw.m1453g(context)));
        arrayList.add(new Pair("appName", C0991bw.m1455h(context)));
        arrayList.add(new Pair("packageName", context.getPackageName()));
        arrayList.add(new Pair("sdkVersion", StatService.getSdkVersion()));
        arrayList.add(new Pair("deviceName", C0991bw.m1465m(context)));
        arrayList.add(new Pair("platform", "Android"));
        arrayList.add(new Pair("model", Build.MODEL));
        CooperService.instance().getCUID(context, false);
        arrayList.add(new Pair("cuid", ""));
        arrayList.add(new Pair("auto", "1"));
        if (!TextUtils.isEmpty(this.f997x)) {
            arrayList.add(new Pair("token", this.f997x));
        }
        StringBuilder sb = new StringBuilder();
        for (Pair pair : arrayList) {
            try {
                String encode = URLEncoder.encode(((String) pair.first).toString(), "UTF-8");
                String encode2 = URLEncoder.encode(((String) pair.second).toString(), "UTF-8");
                if (TextUtils.isEmpty(sb.toString())) {
                    sb.append(encode + "=" + encode2);
                } else {
                    sb.append("&" + encode + "=" + encode2);
                }
            } catch (Exception unused) {
            }
        }
        String str = "wss://mtjsocket.baidu.com/app?" + sb.toString();
        this.f997x = null;
        return str;
    }

    /* renamed from: q */
    private boolean m915q() {
        return !TextUtils.isEmpty(this.f995v);
    }

    /* renamed from: b */
    private void m888b(WebView webView, String str, C0974bk bkVar) {
        if (bkVar != null) {
            bkVar.mo11661a(webView, str);
        }
    }

    /* renamed from: c */
    private void m892c(WebView webView, String str, C0974bk bkVar) {
        if (bkVar != null) {
            C0974bk bkVar2 = bkVar;
            bkVar2.mo11660a(this.f977d, webView, str, m883a(this.f998y, m916r()), true);
        }
    }

    /* renamed from: r */
    private String m916r() {
        Activity activity = this.f977d;
        if (activity != null) {
            return activity.getClass().getName();
        }
        return null;
    }

    /* renamed from: b */
    private void m887b(Activity activity, boolean z) {
        if (!(activity instanceof IIgnoreAutoTrace)) {
            if (z) {
                BDStatCore.instance().onResume(activity, true);
            } else {
                BDStatCore.instance().onPause(activity, true, (ExtraInfo) null);
            }
        }
    }

    /* renamed from: d */
    public void mo11504d() {
        if (m914p()) {
            this.f976c.mo11449a();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: s */
    public void m917s() {
        if (m914p() && this.f983j) {
            JSONObject a = m882a(this.f978e.mo11509a(this.f977d));
            if (a != null) {
                if (C0956bc.m1198c().mo11630b()) {
                    C0956bc c = C0956bc.m1198c();
                    c.mo11624a("doSendSnapshot:" + a.toString());
                }
                try {
                    this.f976c.mo11450a(a);
                } catch (Exception unused) {
                }
            }
            this.f981h.sendMessageDelayed(this.f981h.obtainMessage(2), 2000);
        }
    }

    /* renamed from: a */
    private JSONObject m882a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            return jSONObject2;
        }
        try {
            jSONObject2.put("type", "upload");
            jSONObject2.put(ShareConstants.WEB_DIALOG_PARAM_DATA, jSONObject);
        } catch (Exception unused) {
        }
        return jSONObject2;
    }

    /* renamed from: t */
    private void m918t() {
        if (C0991bw.m1472s(this.f974a) && !this.f986m) {
            if (this.f994u == 0) {
                this.f994u = C0982bp.m1357a().mo11711p(this.f974a);
            }
            if (System.currentTimeMillis() - this.f994u > DateUtil.DAY_MILL_SECONDS) {
                this.f979f.sendMessage(this.f979f.obtainMessage(23));
            }
        }
    }

    /* renamed from: u */
    private void m919u() {
        if (C0991bw.m1472s(this.f974a) && !this.f985l) {
            if (!this.f987n) {
                this.f990q = C0980bn.m1345a(this.f974a, C0883af.f942b);
                this.f987n = true;
            }
            if (this.f992s == 0) {
                this.f992s = C0982bp.m1357a().mo11707n(this.f974a);
                this.f993t = C0982bp.m1357a().mo11709o(this.f974a);
            }
            if ((this.f987n && TextUtils.isEmpty(this.f990q)) || System.currentTimeMillis() - this.f992s > this.f993t) {
                this.f979f.sendMessage(this.f979f.obtainMessage(22));
            }
        }
    }

    /* renamed from: v */
    private void m920v() {
        if (!this.f988o) {
            if (TextUtils.isEmpty(this.f991r)) {
                m903h();
            }
            this.f988o = true;
        }
    }

    /* renamed from: c */
    private void m894c(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("meta");
                JSONArray jSONArray = (JSONArray) jSONObject.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                JSONArray jSONArray2 = new JSONArray();
                JSONArray jSONArray3 = new JSONArray();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i);
                    String str2 = (String) jSONObject3.opt("url");
                    if (!TextUtils.isEmpty((String) jSONObject3.opt("webLayout")) || !TextUtils.isEmpty(str2)) {
                        jSONArray2.put(jSONObject3);
                    } else {
                        jSONArray3.put(jSONObject3);
                    }
                }
                this.f998y.put("meta", jSONObject2);
                this.f998y.put(ShareConstants.WEB_DIALOG_PARAM_DATA, jSONArray2);
                this.f999z.put("meta", jSONObject2);
                this.f999z.put(ShareConstants.WEB_DIALOG_PARAM_DATA, jSONArray3);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private JSONObject m883a(JSONObject jSONObject, String str) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) jSONObject.get("meta");
            int i = jSONObject2.getInt("matchAll");
            JSONArray jSONArray = (JSONArray) jSONObject.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
            JSONArray jSONArray2 = new JSONArray();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                if (str.equals((String) jSONObject3.get("page"))) {
                    jSONArray2.put(jSONObject3);
                }
            }
            boolean z = true;
            if (i == 0) {
                if (i != 0 || jSONArray2.length() == 0) {
                    z = false;
                }
            }
            if (z) {
                JSONObject jSONObject4 = new JSONObject();
                try {
                    jSONObject4.put("meta", jSONObject2);
                    jSONObject4.put(ShareConstants.WEB_DIALOG_PARAM_DATA, jSONArray2);
                    return jSONObject4;
                } catch (Exception unused) {
                    return jSONObject4;
                }
            }
        } catch (Exception unused2) {
        }
        return null;
    }

    /* renamed from: a */
    public void mo11492a(int i) {
        mo11493a(i, "");
    }

    /* renamed from: a */
    public void mo11493a(int i, String str) {
        synchronized (this.f973G) {
            if (this.f974a != null) {
                if (str == null) {
                    str = "";
                }
                long currentTimeMillis = System.currentTimeMillis();
                boolean s = C0991bw.m1472s(this.f974a);
                StringBuilder sb = new StringBuilder();
                sb.append(s ? 1 : 0);
                sb.append("|");
                sb.append(str);
                String sb2 = sb.toString();
                this.f972F.put(i + Config.replace + currentTimeMillis + Config.replace + sb2);
                C0980bn.m1347a(this.f974a, C0883af.f944d, this.f972F.toString(), false);
            }
        }
    }

    /* renamed from: e */
    public JSONArray mo11505e() {
        synchronized (this.f973G) {
            if (this.f974a == null) {
                JSONArray jSONArray = new JSONArray();
                return jSONArray;
            }
            String a = C0980bn.m1345a(this.f974a, C0883af.f944d);
            JSONArray jSONArray2 = null;
            try {
                if (!TextUtils.isEmpty(a)) {
                    jSONArray2 = new JSONArray(a);
                }
            } catch (Exception unused) {
            }
            if (jSONArray2 == null) {
                jSONArray2 = new JSONArray();
            }
            this.f972F = new JSONArray();
            C0980bn.m1347a(this.f974a, C0883af.f944d, this.f972F.toString(), false);
            return jSONArray2;
        }
    }
}
