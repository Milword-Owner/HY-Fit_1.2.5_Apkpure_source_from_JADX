package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Session {

    /* renamed from: a */
    private volatile long f894a = 0;

    /* renamed from: b */
    private volatile long f895b = 0;

    /* renamed from: c */
    private volatile long f896c = 0;

    /* renamed from: d */
    private volatile long f897d = 0;

    /* renamed from: e */
    private volatile long f898e = 0;

    /* renamed from: f */
    private volatile int f899f = 0;

    /* renamed from: g */
    private List<C0873a> f900g = new ArrayList();

    /* renamed from: h */
    private volatile JSONObject f901h = null;

    public void reset() {
        this.f894a = 0;
        this.f895b = 0;
        this.f896c = 0;
        this.f897d = 0;
        this.f899f = 0;
        this.f900g.clear();
    }

    public void setTrackStartTime(long j) {
        if (this.f896c <= 0) {
            this.f896c = j;
        }
    }

    public long getTrackStartTime() {
        return this.f896c;
    }

    public long getTrackEndTime() {
        return this.f897d;
    }

    public void setTrackEndTime(long j) {
        this.f897d = j;
    }

    public void setInvokeType(int i) {
        this.f899f = i;
    }

    public void addPageView(String str, String str2, String str3, long j, long j2, boolean z, ExtraInfo extraInfo, boolean z2, String str4) {
        m783a(this.f900g, new C0873a(str, str2, str3, j, j2, z, extraInfo, z2, str4));
    }

    public void addPageView(C0873a aVar) {
        m783a(this.f900g, aVar);
    }

    /* renamed from: a */
    private void m783a(List<C0873a> list, C0873a aVar) {
        if (list != null && aVar != null) {
            try {
                int size = list.size();
                if (size == 0) {
                    list.add(aVar);
                    return;
                }
                C0873a aVar2 = list.get(size - 1);
                if (!TextUtils.isEmpty(aVar2.f902a)) {
                    if (!TextUtils.isEmpty(aVar.f902a)) {
                        if (aVar2.f902a.equals(aVar.f902a)) {
                            if (aVar2.f907f != aVar.f907f) {
                                if (aVar2.f907f) {
                                    aVar2.mo11392a(aVar);
                                    return;
                                }
                                return;
                            }
                        }
                        list.add(aVar);
                        return;
                    }
                }
                list.add(aVar);
            } catch (Exception unused) {
            }
        }
    }

    public void setStartTime(long j) {
        if (this.f894a <= 0) {
            this.f894a = j;
            this.f898e = j;
        }
    }

    public long getStartTime() {
        return this.f894a;
    }

    public boolean hasStart() {
        return this.f894a > 0;
    }

    public boolean hasEnd() {
        return this.f895b > 0;
    }

    public void setEndTime(long j) {
        this.f895b = j;
    }

    public void setLaunchInfo(JSONObject jSONObject) {
        this.f901h = jSONObject;
    }

    public JSONObject constructJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", this.f894a);
            jSONObject.put("e", this.f895b);
            jSONObject.put("i", this.f898e);
            jSONObject.put("c", 1);
            jSONObject.put(Config.SESSTION_TRACK_START_TIME, this.f896c == 0 ? this.f894a : this.f896c);
            jSONObject.put(Config.SESSTION_TRACK_END_TIME, this.f897d == 0 ? this.f895b : this.f897d);
            jSONObject.put(Config.SESSTION_TRIGGER_CATEGORY, this.f899f);
            if (!(this.f901h == null || this.f901h.length() == 0)) {
                jSONObject.put(Config.LAUNCH, this.f901h);
            }
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.f900g.size(); i++) {
                jSONArray.put(getPVJson(this.f900g.get(i), this.f894a));
            }
            jSONObject.put("p", jSONArray);
            jSONObject.put(Config.f780PY, DataCore.instance().getSessionPy());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public JSONObject getPageSessionHead() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", this.f894a);
            jSONObject.put("e", this.f895b);
            jSONObject.put("i", this.f898e);
            jSONObject.put("c", 1);
            jSONObject.put(Config.SESSTION_TRACK_START_TIME, this.f896c == 0 ? this.f894a : this.f896c);
            jSONObject.put(Config.SESSTION_TRACK_END_TIME, this.f897d == 0 ? this.f895b : this.f897d);
            jSONObject.put(Config.SESSTION_TRIGGER_CATEGORY, this.f899f);
            jSONObject.put(Config.f780PY, DataCore.instance().getSessionPy());
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public String toString() {
        return constructJSONObject().toString();
    }

    public static JSONObject getPVJson(C0873a aVar, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", aVar.mo11391a());
            jSONObject.put("d", aVar.mo11394c());
            long d = aVar.mo11395d() - j;
            if (d < 0) {
                d = 0;
            }
            jSONObject.put("ps", d);
            jSONObject.put("t", aVar.mo11393b());
            int i = 1;
            jSONObject.put("at", aVar.mo11397f() ? 1 : 0);
            JSONObject e = aVar.mo11396e();
            if (!(e == null || e.length() == 0)) {
                jSONObject.put("ext", e);
            }
            if (!aVar.f909h) {
                i = 0;
            }
            jSONObject.put("h5", i);
            jSONObject.put(Config.f780PY, aVar.mo11398g());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* renamed from: com.baidu.mobstat.Session$a */
    static class C0873a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public String f902a;

        /* renamed from: b */
        private String f903b;

        /* renamed from: c */
        private String f904c;

        /* renamed from: d */
        private long f905d;

        /* renamed from: e */
        private long f906e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public boolean f907f;

        /* renamed from: g */
        private JSONObject f908g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public boolean f909h;

        /* renamed from: i */
        private String f910i;

        public C0873a(String str, String str2, String str3, long j, long j2, boolean z, ExtraInfo extraInfo, boolean z2, String str4) {
            this.f903b = str;
            this.f904c = str2;
            this.f902a = str3;
            this.f905d = j;
            this.f906e = j2;
            this.f907f = z;
            this.f910i = str4;
            this.f908g = extraInfo != null ? extraInfo.dumpToJson() : new JSONObject();
            this.f909h = z2;
        }

        /* renamed from: a */
        public String mo11391a() {
            return this.f903b;
        }

        /* renamed from: b */
        public String mo11393b() {
            return this.f904c;
        }

        /* renamed from: c */
        public long mo11394c() {
            return this.f905d;
        }

        /* renamed from: d */
        public long mo11395d() {
            return this.f906e;
        }

        /* renamed from: e */
        public JSONObject mo11396e() {
            return this.f908g;
        }

        /* renamed from: f */
        public boolean mo11397f() {
            return this.f907f;
        }

        /* renamed from: g */
        public String mo11398g() {
            return this.f910i;
        }

        /* renamed from: a */
        public void mo11392a(C0873a aVar) {
            this.f902a = aVar.f902a;
            this.f903b = aVar.f903b;
            this.f904c = aVar.f904c;
            this.f905d = aVar.f905d;
            this.f906e = aVar.f906e;
            this.f907f = aVar.f907f;
            this.f908g = aVar.f908g;
            this.f909h = aVar.f909h;
            this.f910i = aVar.f910i;
        }
    }
}
