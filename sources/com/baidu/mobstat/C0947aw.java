package com.baidu.mobstat;

import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.aw */
public class C0947aw {

    /* renamed from: c */
    private static final C0947aw f1176c = new C0947aw();

    /* renamed from: a */
    private boolean f1177a = false;

    /* renamed from: b */
    private volatile boolean f1178b;

    /* renamed from: a */
    public static C0947aw m1142a() {
        return f1176c;
    }

    /* renamed from: a */
    public void mo11612a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = (JSONObject) new JSONObject(str).opt(MessengerShareContentUtility.WEBVIEW_RATIO_FULL);
                boolean z = false;
                if ((jSONObject != null ? jSONObject.optInt("close") : 0) != 0) {
                    z = true;
                }
                this.f1177a = z;
            } catch (Exception unused) {
            }
            this.f1178b = true;
        }
    }

    /* renamed from: b */
    public boolean mo11613b() {
        return this.f1177a;
    }
}
