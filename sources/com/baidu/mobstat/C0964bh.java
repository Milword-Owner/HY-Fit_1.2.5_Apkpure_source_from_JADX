package com.baidu.mobstat;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.baidubce.BceConfig;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.bh */
public class C0964bh {

    /* renamed from: a */
    private boolean f1236a;

    /* renamed from: b */
    private List<C0966b> f1237b = new ArrayList();

    /* renamed from: c */
    private String f1238c;

    /* renamed from: d */
    private C0970bj f1239d;

    /* renamed from: e */
    private boolean f1240e;

    /* renamed from: com.baidu.mobstat.bh$a */
    public interface C0965a {
        /* renamed from: a */
        void mo11647a(View view, boolean z);
    }

    public C0964bh(Activity activity, C0970bj bjVar, boolean z) {
        this.f1238c = activity.getClass().getName();
        this.f1239d = bjVar;
        this.f1240e = z;
    }

    /* renamed from: a */
    public void mo11646a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.f1236a = ((JSONObject) jSONObject.get("meta")).getInt("matchAll") != 0;
            } catch (Exception unused) {
            }
            if (!this.f1236a) {
                try {
                    JSONArray jSONArray = (JSONArray) jSONObject.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                        String optString = jSONObject2.optString("page");
                        String optString2 = jSONObject2.optString("layout");
                        int optInt = jSONObject2.optInt("contentAsLabel");
                        boolean z = jSONObject2.optInt("ignoreCellIndex") != 0;
                        if (this.f1238c.equals(optString)) {
                            this.f1237b.add(new C0966b(optString, optString2, z, optInt));
                        }
                    }
                } catch (Exception unused2) {
                }
            }
        }
    }

    /* renamed from: a */
    public void mo11645a(Activity activity) {
        List<C0966b> list;
        if (this.f1240e || this.f1236a || !((list = this.f1237b) == null || list.size() == 0)) {
            View a = C0968bi.m1241a(activity);
            m1230a(activity, a, (C0967c) null, a);
        }
    }

    /* renamed from: a */
    private void m1230a(Activity activity, View view, C0967c cVar, View view2) {
        if (view != null && !C0885ah.m847a(view) && !C0968bi.m1276c(activity, view)) {
            C0967c cVar2 = new C0967c(view, cVar, view2);
            if (cVar != null) {
                boolean b = this.f1236a ? C0968bi.m1269b(view, cVar2.mo11651c()) : m1231a(this.f1237b, cVar2.mo11648a(), cVar2.mo11650b());
                if (b || this.f1240e) {
                    if (C0956bc.m1198c().mo11630b() && b) {
                        C0956bc c = C0956bc.m1198c();
                        c.mo11624a("accumulate view:" + view.getClass().getName() + "; content:" + C0968bi.m1289h(view));
                    }
                    if (C0963bg.m1227c().mo11630b()) {
                        C0963bg c2 = C0963bg.m1227c();
                        c2.mo11624a("accumulate view:" + view.getClass().getName() + "; content:" + C0968bi.m1289h(view));
                    }
                    this.f1239d.mo11647a(view, b);
                }
            }
            if (!(view instanceof WebView) && (view instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    m1230a(activity, viewGroup.getChildAt(i), cVar2, view2);
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m1231a(List<C0966b> list, String str, String str2) {
        for (C0966b next : list) {
            String str3 = next.f1243c ? str2 : str;
            if (!TextUtils.isEmpty(str3) && str3.equals(next.f1242b)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: com.baidu.mobstat.bh$b */
    public class C0966b {

        /* renamed from: a */
        public String f1241a;

        /* renamed from: b */
        public String f1242b;

        /* renamed from: c */
        public boolean f1243c;

        /* renamed from: d */
        public int f1244d;

        public C0966b(String str, String str2, boolean z, int i) {
            this.f1241a = str;
            this.f1242b = str2;
            this.f1243c = z;
            this.f1244d = i;
        }
    }

    /* renamed from: com.baidu.mobstat.bh$c */
    static class C0967c {

        /* renamed from: a */
        public String f1246a;

        /* renamed from: b */
        public String f1247b;

        /* renamed from: c */
        public String f1248c;

        /* renamed from: d */
        public C0967c f1249d;

        public C0967c(View view, C0967c cVar, View view2) {
            this.f1249d = cVar;
            this.f1246a = C0968bi.m1293k(view);
            this.f1247b = C0968bi.m1263b(view);
            String c = C0968bi.m1271c(view);
            if (TextUtils.isEmpty(c)) {
                c = C0968bi.m1247a(view, mo11651c());
                if (TextUtils.isEmpty(c)) {
                    c = C0968bi.m1246a(view, view2);
                }
            }
            this.f1248c = c;
        }

        /* renamed from: a */
        public String mo11648a() {
            StringBuilder sb = new StringBuilder();
            for (C0967c cVar = this; cVar != null; cVar = cVar.f1249d) {
                sb.insert(0, cVar.mo11649a(false));
            }
            return sb.toString();
        }

        /* renamed from: b */
        public String mo11650b() {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (C0967c cVar = this; cVar != null; cVar = cVar.f1249d) {
                boolean z2 = true;
                if (!z) {
                    String c = cVar.mo11651c();
                    if ("ListView".equals(c) || "RecyclerView".equals(c) || "GridView".equals(c)) {
                        z = true;
                        sb.insert(0, cVar.mo11649a(z2));
                    }
                }
                z2 = false;
                sb.insert(0, cVar.mo11649a(z2));
            }
            return sb.toString();
        }

        /* renamed from: a */
        public String mo11649a(boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append(BceConfig.BOS_DELIMITER);
            sb.append(this.f1246a);
            if (!z) {
                sb.append("[");
                sb.append(this.f1248c);
                sb.append("]");
            }
            return sb.toString();
        }

        /* renamed from: c */
        public String mo11651c() {
            C0967c cVar = this.f1249d;
            return cVar == null ? "" : cVar.f1247b;
        }
    }
}
