package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.text.TextUtils;
import android.view.View;
import com.baidu.mobstat.C0970bj;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.bd */
public class C0957bd implements C0970bj.C0973b {
    /* renamed from: a */
    public void mo11637a(View view, boolean z, Activity activity) {
        View view2 = view;
        Activity activity2 = activity;
        if (activity2 != null && view2 != null) {
            C0913aq.m985a(view2, activity2);
            if (C0956bc.m1198c().mo11630b() && z) {
                C0956bc c = C0956bc.m1198c();
                c.mo11624a("OnEvent view:" + view.getClass().getName() + "; content:" + C0968bi.m1289h(view) + "; activity:" + activity.getClass().getName());
            }
            if (C0963bg.m1227c().mo11630b()) {
                C0963bg c2 = C0963bg.m1227c();
                c2.mo11624a("OnEvent view:" + view.getClass().getName() + "; content:" + C0968bi.m1289h(view) + "; activity:" + activity.getClass().getName());
            }
            JSONArray a = C0968bi.m1253a(activity2, view2);
            String f = C0968bi.m1285f(view);
            Map<String, String> g = C0968bi.m1287g(view);
            String a2 = C0968bi.m1245a(view);
            Context applicationContext = activity.getApplicationContext();
            long currentTimeMillis = System.currentTimeMillis();
            JSONArray jSONArray = new JSONArray();
            String name = activity.getClass().getName();
            if (z) {
                BDStatCore.instance().onEvent(applicationContext, "", a2, 1, currentTimeMillis, a, jSONArray, name, "", f, g);
            }
            JSONObject a3 = m1202a(activity2, view2, C0949ay.m1153a().mo11619b());
            String a4 = m1201a(activity2, view2);
            String a5 = C0968bi.m1248a(view2, true);
            JSONArray jSONArray2 = new JSONArray();
            Map<String, String> b = C0968bi.m1268b(C0968bi.m1242a(view2, activity2), false);
            String str = "";
            if (!TextUtils.isEmpty(a4) && b != null && b.size() > 0 && !TextUtils.isEmpty(b.get("content"))) {
                str = b.get("content");
            }
            C0939av.m1093a().mo11591a(applicationContext, "", a5, str, 1, currentTimeMillis, name, a, "", jSONArray, f, g, a3, a4, jSONArray2);
        }
    }

    /* renamed from: a */
    private JSONObject m1202a(Activity activity, View view, PointF pointF) {
        if (pointF == null) {
            return null;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        float f = pointF.x - ((float) iArr[0]);
        float f2 = pointF.y - ((float) iArr[1]);
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        float b = C0884ag.m839b(activity, f);
        float b2 = C0884ag.m839b(activity, f2);
        float a = (float) C0884ag.m838a(activity, (float) C0968bi.m1296n(view));
        float a2 = (float) C0884ag.m838a(activity, (float) C0968bi.m1297o(view));
        if (a == 0.0f || a2 == 0.0f) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator('.');
            decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            jSONObject.put(Config.EVENT_HEAT_X, decimalFormat.format((double) b));
            jSONObject.put("y", decimalFormat.format((double) b2));
            jSONObject.put(Config.EVENT_HEAT_XP, decimalFormat.format((double) ((b * 100.0f) / a)));
            jSONObject.put(Config.EVENT_HEAT_YP, decimalFormat.format((double) ((b2 * 100.0f) / a2)));
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r5 = com.baidu.mobstat.C0968bi.m1242a(r5, r4);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m1201a(android.app.Activity r4, android.view.View r5) {
        /*
            r3 = this;
            java.lang.String r0 = ""
            if (r4 == 0) goto L_0x0023
            if (r5 != 0) goto L_0x0007
            goto L_0x0023
        L_0x0007:
            android.view.View r5 = com.baidu.mobstat.C0968bi.m1242a((android.view.View) r5, (android.app.Activity) r4)
            android.view.View r1 = com.baidu.mobstat.C0968bi.m1295m(r5)
            if (r1 != 0) goto L_0x0012
            return r0
        L_0x0012:
            com.baidu.mobstat.as r2 = com.baidu.mobstat.C0915as.m997a()
            java.lang.String r4 = r2.mo11548a((android.app.Activity) r4, (android.view.View) r5, (android.view.View) r1)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            r4 = r0
        L_0x0022:
            return r4
        L_0x0023:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0957bd.m1201a(android.app.Activity, android.view.View):java.lang.String");
    }
}
