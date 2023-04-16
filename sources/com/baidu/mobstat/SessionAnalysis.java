package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.Session;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SessionAnalysis {

    /* renamed from: a */
    private boolean f911a = false;

    /* renamed from: b */
    private Map<String, C0874a> f912b = new HashMap();

    /* renamed from: c */
    private C0874a f913c = new C0874a();

    /* renamed from: d */
    private C0874a f914d = new C0874a();

    /* renamed from: e */
    private boolean f915e = false;

    /* renamed from: f */
    private long f916f = 0;

    /* renamed from: g */
    private Session f917g = new Session();

    /* renamed from: h */
    private int f918h = 0;

    /* renamed from: i */
    private int f919i = 0;

    /* renamed from: j */
    private long f920j = 0;

    /* renamed from: k */
    private boolean f921k = true;

    /* renamed from: l */
    private LaunchInfo f922l;

    /* renamed from: m */
    private LaunchInfo f923m;
    public Callback mCallback;

    public interface Callback {
        void onCallback(JSONObject jSONObject);
    }

    public void doSendNewSessionLog(Context context) {
    }

    public SessionAnalysis() {
    }

    public SessionAnalysis(Callback callback) {
        this.mCallback = callback;
    }

    public void setSessionTimeOut(int i) {
        if (i < 1) {
            i = 30;
            C0955bb.m1194c().mo11627b("[WARNING] SessionTimeout should be between 1 and 600. Default value[30] is used");
        } else if (i > 600) {
            C0955bb.m1194c().mo11627b("[WARNING] SessionTimeout should be between 1 and 600. Default value[600] is used");
            i = 600;
        }
        this.f918h = i * 1000;
    }

    public int getSessionTimeOut() {
        if (this.f918h <= 0) {
            this.f918h = 30000;
        }
        return this.f918h;
    }

    public long getSessionStartTime() {
        return this.f917g.getStartTime();
    }

    public JSONObject getPageSessionHead() {
        return this.f917g.getPageSessionHead();
    }

    public int getSessionSize() {
        return this.f919i;
    }

    public void doSendLogCheck(Context context, long j) {
        long j2 = this.f916f;
        if (j2 > 0 && j - j2 > ((long) getSessionTimeOut())) {
            m798a(context, -1, false, false, 0);
        }
    }

    public void setAutoSend(boolean z) {
        this.f921k = z;
    }

    public void autoTrackLaunchInfo(LaunchInfo launchInfo, boolean z) {
        if (z) {
            this.f922l = launchInfo;
        } else {
            this.f923m = launchInfo;
        }
    }

    public void autoTrackSessionStartTime(Context context, long j) {
        if (context != null) {
            this.f917g.setTrackStartTime(j);
            this.f920j = j;
        }
    }

    public void autoTrackSessionEndTime(Context context, long j) {
        if (context != null) {
            this.f917g.setTrackEndTime(j);
            m796a(context);
        }
    }

    public void onSessionStart(Context context, long j, boolean z) {
        if (!this.f911a) {
            DataCore.instance().init(context);
            try {
                Thread.sleep(3000);
            } catch (Exception unused) {
            }
            LaunchInfo launchInfo = this.f922l;
            m798a(context, j, z, true, launchInfo != null ? launchInfo.getLaunchType(context) : 0);
            this.f911a = true;
        }
    }

    public void onPageStart(Context context, String str, int i, long j) {
        C0874a a;
        onSessionStart(context, j, false);
        if (!TextUtils.isEmpty(str) && (a = m795a(str)) != null) {
            if (a.f926c) {
                C0955bb c = C0955bb.m1194c();
                c.mo11631c("[WARNING] 遗漏StatService.onPageEnd(), 请检查邻近页面埋点: " + str);
            }
            if (!this.f915e) {
                m797a(context, this.f916f, j, i, 3);
                this.f915e = true;
            }
            a.f926c = true;
            a.f925b = j;
        }
    }

    public void onPageEnd(Context context, String str, String str2, String str3, long j, ExtraInfo extraInfo, boolean z) {
        C0874a a;
        String str4 = str;
        this.f915e = false;
        if (TextUtils.isEmpty(str) || (a = m795a(str4)) == null) {
            return;
        }
        if (!a.f926c) {
            C0955bb c = C0955bb.m1194c();
            c.mo11631c("[WARNING] 遗漏StatService.onPageStart(), 请检查邻近页面埋点: " + str4);
            return;
        }
        m799a(context, a.f924a, str, a.f925b, j, str2, "", str3, false, extraInfo, z);
        m800b(str4);
        this.f916f = j;
    }

    public void onPageStartAct(Context context, String str, long j, boolean z) {
        onSessionStart(context, j, false);
        if (!TextUtils.isEmpty(str)) {
            C0874a aVar = z ? this.f914d : this.f913c;
            if (aVar.f926c && !z) {
                C0955bb c = C0955bb.m1194c();
                c.mo11631c("[WARNING] 遗漏StatService.onPause(Activity), 请检查邻近页面埋点: " + str);
            }
            if (!this.f915e) {
                m797a(context, this.f916f, j, 1, 1);
                this.f915e = true;
            }
            aVar.f926c = true;
            aVar.f924a = str;
            aVar.f925b = j;
        }
    }

    public void onPageEndAct(Context context, String str, String str2, String str3, long j, boolean z, ExtraInfo extraInfo) {
        this.f915e = false;
        C0874a aVar = z ? this.f914d : this.f913c;
        if (aVar.f926c) {
            m799a(context, aVar.f924a, str, aVar.f925b, j, str2, str3, str, z, extraInfo, false);
            aVar.f926c = false;
            this.f916f = j;
        } else if (!z) {
            C0955bb c = C0955bb.m1194c();
            c.mo11631c("[WARNING] 遗漏StatService.onResume(Activity), 请检查邻近页面埋点: " + str);
        }
    }

    public void onPageStartFrag(Context context, String str, long j) {
        onSessionStart(context, j, false);
        if (!TextUtils.isEmpty(str)) {
            C0874a a = m795a(str);
            if (a.f926c) {
                C0955bb c = C0955bb.m1194c();
                c.mo11631c("[WARNING] 遗漏StatService.onPause(Fragment), 请检查邻近页面埋点: " + str);
            }
            m797a(context, this.f916f, j, 2, 2);
            a.f926c = true;
            a.f924a = str;
            a.f925b = j;
        }
    }

    public void onPageEndFrag(Context context, String str, String str2, String str3, long j) {
        C0874a a;
        String str4 = str;
        if (TextUtils.isEmpty(str) || (a = m795a(str4)) == null) {
            return;
        }
        if (!a.f926c) {
            C0955bb c = C0955bb.m1194c();
            c.mo11631c("[WARNING] 遗漏StatService.onResume(Fragment), 请检查邻近页面埋点: " + str4);
            return;
        }
        m799a(context, a.f924a, str, a.f925b, j, str2, str3, (String) null, false, (ExtraInfo) null, false);
        m800b(str4);
        this.f916f = j;
    }

    /* renamed from: a */
    private void m797a(Context context, long j, long j2, int i, int i2) {
        long j3 = j;
        int i3 = 0;
        if (j2 - j3 > ((long) getSessionTimeOut())) {
            if (j3 > 0) {
                if (2 == i2) {
                    this.f917g.setEndTime(j);
                }
                LaunchInfo launchInfo = this.f923m;
                if (launchInfo != null) {
                    Context context2 = context;
                    i3 = launchInfo.getLaunchType(context);
                } else {
                    Context context3 = context;
                }
                m798a(context, j2, false, false, i3);
            }
            this.f917g.setTrackStartTime(this.f920j);
            int i4 = i;
            this.f917g.setInvokeType(i);
        }
    }

    /* renamed from: a */
    private void m799a(Context context, String str, String str2, long j, long j2, String str3, String str4, String str5, boolean z, ExtraInfo extraInfo, boolean z2) {
        long j3 = j2;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && str.equals(str2)) {
            this.f917g.addPageView(new Session.C0873a(str3, str4, str5, j3 - j, j, z, extraInfo, z2, DataCore.instance().getPagePy()));
            this.f917g.setEndTime(j3);
            m796a(context);
            if (!TextUtils.isEmpty(DataCore.instance().getTempPyd())) {
                DataCore.instance().flush(context);
            }
        }
    }

    /* renamed from: a */
    private C0874a m795a(String str) {
        if (!this.f912b.containsKey(str)) {
            this.f912b.put(str, new C0874a(str));
        }
        return this.f912b.get(str);
    }

    /* renamed from: a */
    private void m798a(Context context, long j, boolean z, boolean z2, int i) {
        long j2;
        String str;
        if (this.f917g.hasEnd()) {
            DataCore.instance().putSession(this.f917g);
            DataCore.instance().flush(context);
            C0936au.m1090a(this.f917g.getPageSessionHead());
            this.f917g.setEndTime(0);
        }
        boolean z3 = j > 0;
        if (z3) {
            j2 = j;
        } else {
            j2 = this.f917g.getStartTime();
        }
        String str2 = "";
        if (i != 0) {
            if (z2) {
                LaunchInfo launchInfo = this.f922l;
                if (launchInfo != null) {
                    str2 = launchInfo.getPushContent();
                    str = this.f922l.getRefererPkgName();
                }
            } else {
                LaunchInfo launchInfo2 = this.f923m;
                if (launchInfo2 != null) {
                    str2 = launchInfo2.getPushContent();
                    str = this.f923m.getRefererPkgName();
                }
            }
            str = str2;
        } else {
            str = LaunchInfo.getLauncherHomePkgName(context);
        }
        JSONObject convertedJson = LaunchInfo.getConvertedJson(i, str, str2);
        if (z3) {
            this.f917g.reset();
            this.f917g.setStartTime(j);
            if (convertedJson != null) {
                this.f917g.setLaunchInfo(convertedJson);
            }
        }
        DataCore.instance().saveLogData(context, z3, z, j2, z2, convertedJson);
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCallback(DataCore.instance().getLogData());
        }
        if (z3 || this.f921k) {
            LogSender.instance().onSend(context);
        }
        clearLastSessionCache(context);
    }

    /* renamed from: a */
    private void m796a(Context context) {
        if (this.f917g.hasStart()) {
            String jSONObject = this.f917g.constructJSONObject().toString();
            this.f919i = jSONObject.getBytes().length;
            String u = C0991bw.m1474u(context);
            C0980bn.m1347a(context, u + Config.LAST_SESSION_FILE_NAME, jSONObject, false);
        }
    }

    public void clearLastSessionCache(Context context) {
        if (context != null) {
            String jSONObject = new JSONObject().toString();
            String u = C0991bw.m1474u(context);
            C0980bn.m1347a(context, u + Config.LAST_SESSION_FILE_NAME, jSONObject, false);
        }
    }

    /* renamed from: b */
    private void m800b(String str) {
        if (!TextUtils.isEmpty(str) && this.f912b.containsKey(str)) {
            this.f912b.remove(str);
        }
    }

    public boolean isSessionStart() {
        return this.f917g.getStartTime() > 0;
    }

    /* renamed from: com.baidu.mobstat.SessionAnalysis$a */
    static class C0874a {

        /* renamed from: a */
        String f924a;

        /* renamed from: b */
        long f925b;

        /* renamed from: c */
        boolean f926c = false;

        public C0874a() {
        }

        public C0874a(String str) {
            this.f924a = str;
        }
    }
}
