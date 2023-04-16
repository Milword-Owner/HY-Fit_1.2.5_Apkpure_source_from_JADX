package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.baidu.mobstat.C0976bl;
import com.facebook.internal.ServerProtocol;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CooperService implements ICooperService {

    /* renamed from: a */
    private static CooperService f783a;

    /* renamed from: b */
    private HeadObject f784b = new HeadObject();

    public String getMTJSDKVersion() {
        return "4.0.3.6";
    }

    public int getTagValue() {
        return 1;
    }

    public void setZid(String str) {
    }

    public static synchronized CooperService instance() {
        CooperService cooperService;
        synchronized (CooperService.class) {
            if (f783a == null) {
                f783a = new CooperService();
            }
            cooperService = f783a;
        }
        return cooperService;
    }

    public HeadObject getHeadObject() {
        return this.f784b;
    }

    public String getHost() {
        return Config.LOG_SEND_URL;
    }

    public void installHeader(Context context, JSONObject jSONObject) {
        this.f784b.installHeader(context, jSONObject);
    }

    public JSONObject getHeaderExt(Context context) {
        String k = C0982bp.m1357a().mo11701k(context);
        if (!TextUtils.isEmpty(k)) {
            try {
                return new JSONObject(k);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public void setHeaderExt(Context context, ExtraInfo extraInfo) {
        String str;
        JSONObject jSONObject = new JSONObject();
        if (extraInfo != null) {
            jSONObject = extraInfo.dumpToJson();
        }
        this.f784b.setHeaderExt(jSONObject);
        C0982bp.m1357a().mo11693g(context, jSONObject.toString());
        if (extraInfo != null) {
            str = "Set global ExtraInfo: " + jSONObject;
        } else {
            str = "Clear global ExtraInfo";
        }
        C0955bb.m1194c().mo11624a(str);
    }

    public JSONObject getPushId(Context context) {
        String l = C0982bp.m1357a().mo11703l(context);
        if (!TextUtils.isEmpty(l)) {
            try {
                return new JSONObject(l);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public void setPushId(Context context, String str, String str2, String str3) {
        String str4;
        JSONObject pushId = getPushId(context);
        if (pushId == null) {
            pushId = new JSONObject();
        }
        try {
            if (!TextUtils.isEmpty(str3)) {
                pushId.put(str, str3);
            } else {
                pushId.remove(str);
            }
        } catch (Exception unused) {
        }
        this.f784b.setPushInfo(pushId);
        C0982bp.m1357a().mo11696h(context, pushId.toString());
        if (str3 != null) {
            str4 = "Set platform:" + str2 + " pushId: " + str3;
        } else {
            str4 = "Clear platform:" + str2 + " pushId";
        }
        C0955bb.m1194c().mo11624a(str4);
    }

    public void setStartType(boolean z) {
        this.f784b.setStartType(z);
    }

    /* renamed from: a */
    private static String m726a(Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return Config.DEF_MAC_ID;
        }
        String l = C0991bw.m1463l(context);
        if (!TextUtils.isEmpty(l)) {
            return l.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        return Config.DEF_MAC_ID;
    }

    /* renamed from: b */
    private static String m728b(Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        String k = C0991bw.m1461k(context);
        return !TextUtils.isEmpty(k) ? k.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "") : k;
    }

    public String getMacAddress(Context context, boolean z) {
        String replace = Config.DEF_MAC_ID.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        if (!z && Build.VERSION.SDK_INT >= 23) {
            return getSecretValue(replace);
        }
        if (!TextUtils.isEmpty(this.f784b.f847s)) {
            return this.f784b.f847s;
        }
        String h = C0982bp.m1357a().mo11695h(context);
        if (!TextUtils.isEmpty(h)) {
            HeadObject headObject = this.f784b;
            headObject.f847s = h;
            return headObject.f847s;
        }
        String a = m727a(context, z);
        if (TextUtils.isEmpty(a) || replace.equals(a)) {
            HeadObject headObject2 = this.f784b;
            headObject2.f847s = "";
            return headObject2.f847s;
        }
        this.f784b.f847s = getSecretValue(a);
        C0982bp.m1357a().mo11690e(context, this.f784b.f847s);
        return this.f784b.f847s;
    }

    /* renamed from: a */
    private String m727a(Context context, boolean z) {
        String str;
        if (z) {
            str = m728b(context);
        } else {
            str = m726a(context);
        }
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public String getMacIdForTv(Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        if (!TextUtils.isEmpty(this.f784b.f848t)) {
            return this.f784b.f848t;
        }
        String j = C0982bp.m1357a().mo11699j(context);
        if (!TextUtils.isEmpty(j)) {
            HeadObject headObject = this.f784b;
            headObject.f848t = j;
            return headObject.f848t;
        }
        String i = C0991bw.m1456i(1, context);
        if (!TextUtils.isEmpty(i)) {
            this.f784b.f848t = i;
            C0982bp.m1357a().mo11692f(context, i);
            return this.f784b.f848t;
        }
        HeadObject headObject2 = this.f784b;
        headObject2.f848t = "";
        return headObject2.f848t;
    }

    public String getCUID(Context context, boolean z) {
        C0982bp.m1357a().mo11681b(context, "");
        if (this.f784b.f834f == null || "".equalsIgnoreCase(this.f784b.f834f)) {
            try {
                this.f784b.f834f = C0994bx.m1483a(context);
                Matcher matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(this.f784b.f834f);
                this.f784b.f834f = matcher.replaceAll("");
                this.f784b.f834f = getSecretValue(this.f784b.f834f);
            } catch (Exception unused) {
            }
        }
        if (z) {
            return this.f784b.f834f;
        }
        try {
            String str = this.f784b.f834f;
            if (!TextUtils.isEmpty(str)) {
                return new String(C0976bl.C0978b.m1332b(1, C0981bo.m1354a(str.getBytes())));
            }
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public String getDevicImei(Context context) {
        if (!C0989bu.m1416a().mo11724c()) {
            return "";
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0073, code lost:
        if (r5.length() > 30) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a9, code lost:
        if (r5.length() > 30) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getDeviceId(android.telephony.TelephonyManager r12, android.content.Context r13) {
        /*
            r11 = this;
            com.baidu.mobstat.HeadObject r0 = r11.f784b
            java.lang.String r0 = r0.f837i
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x000f
            com.baidu.mobstat.HeadObject r12 = r11.f784b
            java.lang.String r12 = r12.f837i
            return r12
        L_0x000f:
            com.baidu.mobstat.bp r0 = com.baidu.mobstat.C0982bp.m1357a()
            boolean r0 = r0.mo11698i(r13)
            if (r0 == 0) goto L_0x0026
            com.baidu.mobstat.HeadObject r12 = r11.f784b
            java.lang.String r13 = r11.getMacIdForTv(r13)
            r12.f837i = r13
            com.baidu.mobstat.HeadObject r12 = r11.f784b
            java.lang.String r12 = r12.f837i
            return r12
        L_0x0026:
            com.baidu.mobstat.bp r0 = com.baidu.mobstat.C0982bp.m1357a()
            java.lang.String r0 = r0.mo11715t(r13)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            java.lang.String r3 = ""
            if (r1 != 0) goto L_0x00c1
            r1 = 1
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x004c }
            byte[] r5 = r0.getBytes()     // Catch:{ Exception -> 0x004c }
            byte[] r5 = com.baidu.mobstat.C0981bo.m1354a(r5)     // Catch:{ Exception -> 0x004c }
            byte[] r5 = com.baidu.mobstat.C0976bl.C0978b.m1332b(r1, r5)     // Catch:{ Exception -> 0x004c }
            r4.<init>(r5)     // Catch:{ Exception -> 0x004c }
            r5 = r4
            r4 = 1
            goto L_0x0052
        L_0x004c:
            r4 = move-exception
            r4.printStackTrace()
            r5 = r3
            r4 = 0
        L_0x0052:
            r6 = 30
            java.lang.String r7 = "="
            java.lang.String r8 = "+"
            if (r4 == 0) goto L_0x0076
            boolean r4 = android.text.TextUtils.isEmpty(r5)
            if (r4 == 0) goto L_0x0063
        L_0x0060:
            r0 = r3
            r5 = r0
            goto L_0x00ac
        L_0x0063:
            boolean r4 = r5.contains(r8)
            if (r4 != 0) goto L_0x0060
            boolean r4 = r5.contains(r7)
            if (r4 != 0) goto L_0x0060
            int r4 = r5.length()
            if (r4 <= r6) goto L_0x00ac
            goto L_0x0060
        L_0x0076:
            r4 = 2
            java.lang.String r9 = new java.lang.String     // Catch:{ Exception -> 0x008b }
            byte[] r10 = r0.getBytes()     // Catch:{ Exception -> 0x008b }
            byte[] r10 = com.baidu.mobstat.C0981bo.m1354a(r10)     // Catch:{ Exception -> 0x008b }
            byte[] r4 = com.baidu.mobstat.C0976bl.C0978b.m1332b(r4, r10)     // Catch:{ Exception -> 0x008b }
            r9.<init>(r4)     // Catch:{ Exception -> 0x008b }
            r5 = r9
            r4 = 1
            goto L_0x0090
        L_0x008b:
            r4 = move-exception
            r4.printStackTrace()
            r4 = 0
        L_0x0090:
            if (r4 == 0) goto L_0x00ba
            boolean r4 = android.text.TextUtils.isEmpty(r5)
            if (r4 == 0) goto L_0x0099
            goto L_0x0060
        L_0x0099:
            boolean r4 = r5.contains(r8)
            if (r4 != 0) goto L_0x0060
            boolean r4 = r5.contains(r7)
            if (r4 != 0) goto L_0x0060
            int r4 = r5.length()
            if (r4 <= r6) goto L_0x00ac
            goto L_0x0060
        L_0x00ac:
            boolean r4 = android.text.TextUtils.isEmpty(r5)
            if (r4 != 0) goto L_0x00c1
            r11.filterCuid(r13, r5, r1)
            com.baidu.mobstat.HeadObject r12 = r11.f784b
            java.lang.String r12 = r12.f837i
            return r12
        L_0x00ba:
            com.baidu.mobstat.HeadObject r12 = r11.f784b
            r12.f837i = r0
            java.lang.String r12 = r12.f837i
            return r12
        L_0x00c1:
            if (r12 == 0) goto L_0x00e2
            com.baidu.mobstat.bu r1 = com.baidu.mobstat.C0989bu.m1416a()
            boolean r1 = r1.mo11724c()
            if (r1 == 0) goto L_0x00e2
            java.lang.String r1 = "\\s*|\t|\r|\n"
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)
            java.lang.String r12 = r12.getDeviceId()     // Catch:{ Exception -> 0x00e2 }
            if (r12 == 0) goto L_0x00e2
            java.util.regex.Matcher r12 = r1.matcher(r12)     // Catch:{ Exception -> 0x00e2 }
            java.lang.String r12 = r12.replaceAll(r3)     // Catch:{ Exception -> 0x00e2 }
            r0 = r12
        L_0x00e2:
            boolean r12 = android.text.TextUtils.isEmpty(r0)
            if (r12 != 0) goto L_0x00fe
            java.lang.String r12 = "000000000000000"
            boolean r12 = r0.equals(r12)
            if (r12 == 0) goto L_0x00f1
            goto L_0x00fe
        L_0x00f1:
            com.baidu.mobstat.HeadObject r12 = r11.f784b
            java.lang.String r13 = r11.getSecretValue(r0)
            r12.f837i = r13
            com.baidu.mobstat.HeadObject r12 = r11.f784b
            java.lang.String r12 = r12.f837i
            return r12
        L_0x00fe:
            java.lang.String r12 = m726a(r13)
            boolean r12 = r11.filterCuid(r13, r12, r2)
            if (r12 == 0) goto L_0x010d
            com.baidu.mobstat.HeadObject r12 = r11.f784b
            java.lang.String r12 = r12.f837i
            return r12
        L_0x010d:
            com.baidu.mobstat.HeadObject r12 = r11.f784b
            java.lang.String r12 = r12.f837i
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.CooperService.getDeviceId(android.telephony.TelephonyManager, android.content.Context):java.lang.String");
    }

    public boolean filterCuid(Context context, String str, boolean z) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String replace = str.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        if (!replace.equals(Config.DEF_MAC_ID.replace(Config.TRACE_TODAY_VISIT_SPLIT, ""))) {
            this.f784b.f837i = getSecretValue(replace);
            return true;
        } else if (TextUtils.isEmpty(this.f784b.f834f)) {
            this.f784b.f837i = getSecretValue(m729c(context));
            return true;
        } else {
            try {
                str2 = new String(C0976bl.C0978b.m1332b(1, C0981bo.m1354a(this.f784b.f834f.getBytes())));
            } catch (Exception e) {
                e.printStackTrace();
                str2 = "";
            }
            if (TextUtils.isEmpty(str2)) {
                this.f784b.f837i = getSecretValue(m729c(context));
                return true;
            }
            this.f784b.f837i = getSecretValue(replace);
            return z;
        }
    }

    /* renamed from: c */
    private String m729c(Context context) {
        String e = C0982bp.m1357a().mo11689e(context);
        if (!TextUtils.isEmpty(e) && !e.equals(Config.NULL_DEVICE_ID)) {
            return e;
        }
        String str = new Date().getTime() + "";
        String str2 = "hol" + str.hashCode() + "mes";
        C0982bp.m1357a().mo11676a(context, str2);
        return str2;
    }

    public String getPlainDeviceIdForCar(Context context) {
        String optUUID = CarUUID.optUUID(context);
        if (TextUtils.isEmpty(optUUID)) {
            optUUID = m729c(context);
        }
        return TextUtils.isEmpty(optUUID) ? "" : optUUID;
    }

    public String getAppChannel(Context context) {
        return m730d(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        if (r4.f784b.f840l.equals("") != false) goto L_0x0012;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m730d(android.content.Context r5) {
        /*
            r4 = this;
            com.baidu.mobstat.HeadObject r0 = r4.f784b     // Catch:{ Exception -> 0x0044 }
            java.lang.String r0 = r0.f840l     // Catch:{ Exception -> 0x0044 }
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x0012
            com.baidu.mobstat.HeadObject r0 = r4.f784b     // Catch:{ Exception -> 0x0044 }
            java.lang.String r0 = r0.f840l     // Catch:{ Exception -> 0x0044 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0044 }
            if (r0 == 0) goto L_0x0044
        L_0x0012:
            com.baidu.mobstat.bp r0 = com.baidu.mobstat.C0982bp.m1357a()     // Catch:{ Exception -> 0x0044 }
            boolean r0 = r0.mo11694g(r5)     // Catch:{ Exception -> 0x0044 }
            if (r0 == 0) goto L_0x0028
            com.baidu.mobstat.HeadObject r2 = r4.f784b     // Catch:{ Exception -> 0x0044 }
            com.baidu.mobstat.bp r3 = com.baidu.mobstat.C0982bp.m1357a()     // Catch:{ Exception -> 0x0044 }
            java.lang.String r3 = r3.mo11691f(r5)     // Catch:{ Exception -> 0x0044 }
            r2.f840l = r3     // Catch:{ Exception -> 0x0044 }
        L_0x0028:
            if (r0 == 0) goto L_0x003a
            com.baidu.mobstat.HeadObject r0 = r4.f784b     // Catch:{ Exception -> 0x0044 }
            java.lang.String r0 = r0.f840l     // Catch:{ Exception -> 0x0044 }
            if (r0 == 0) goto L_0x003a
            com.baidu.mobstat.HeadObject r0 = r4.f784b     // Catch:{ Exception -> 0x0044 }
            java.lang.String r0 = r0.f840l     // Catch:{ Exception -> 0x0044 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0044 }
            if (r0 == 0) goto L_0x0044
        L_0x003a:
            com.baidu.mobstat.HeadObject r0 = r4.f784b     // Catch:{ Exception -> 0x0044 }
            java.lang.String r1 = "BaiduMobAd_CHANNEL"
            java.lang.String r5 = com.baidu.mobstat.C0991bw.m1434a((android.content.Context) r5, (java.lang.String) r1)     // Catch:{ Exception -> 0x0044 }
            r0.f840l = r5     // Catch:{ Exception -> 0x0044 }
        L_0x0044:
            com.baidu.mobstat.HeadObject r5 = r4.f784b
            java.lang.String r5 = r5.f840l
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.CooperService.m730d(android.content.Context):java.lang.String");
    }

    public String getAppKey(Context context) {
        if (this.f784b.f833e == null) {
            this.f784b.f833e = C0991bw.m1434a(context, Config.APPKEY_META_NAME);
        }
        return this.f784b.f833e;
    }

    public int getAppVersionCode(Context context) {
        if (this.f784b.f835g == -1) {
            this.f784b.f835g = C0991bw.m1450f(context);
        }
        return this.f784b.f835g;
    }

    public String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(this.f784b.f836h)) {
            this.f784b.f836h = C0991bw.m1453g(context);
        }
        return this.f784b.f836h;
    }

    public void setAppVersionName(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f784b.f836h = str;
        }
    }

    public String getOperator(TelephonyManager telephonyManager) {
        if (TextUtils.isEmpty(this.f784b.f841m)) {
            this.f784b.f841m = telephonyManager.getNetworkOperator();
        }
        return this.f784b.f841m;
    }

    public String getLinkedWay(Context context) {
        if (TextUtils.isEmpty(this.f784b.f846r)) {
            this.f784b.f846r = C0991bw.m1471r(context);
        }
        return this.f784b.f846r;
    }

    public String getOSVersion() {
        if (TextUtils.isEmpty(this.f784b.f830b)) {
            this.f784b.f830b = Integer.toString(Build.VERSION.SDK_INT);
        }
        return this.f784b.f830b;
    }

    public String getOSSysVersion() {
        if (TextUtils.isEmpty(this.f784b.f831c)) {
            this.f784b.f831c = Build.VERSION.RELEASE;
        }
        return this.f784b.f831c;
    }

    public String getPhoneModel() {
        if (TextUtils.isEmpty(this.f784b.f842n)) {
            this.f784b.f842n = Build.MODEL;
        }
        return this.f784b.f842n;
    }

    public String getManufacturer() {
        if (TextUtils.isEmpty(this.f784b.f843o)) {
            this.f784b.f843o = Build.MANUFACTURER;
        }
        return this.f784b.f843o;
    }

    public boolean checkWifiLocationSetting(Context context) {
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(C0991bw.m1434a(context, Config.GET_WIFI_LOCATION));
    }

    public boolean checkGPSLocationSetting(Context context) {
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(C0991bw.m1434a(context, Config.GET_GPS_LOCATION));
    }

    public boolean checkCellLocationSetting(Context context) {
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(C0991bw.m1434a(context, Config.GET_CELL_LOCATION));
    }

    public String getSecretValue(String str) {
        return C0976bl.C0978b.m1333c(1, str.getBytes());
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void resetHeadSign() {
        this.f784b.f854z = instance().getUUID();
    }

    public void enableDeviceMac(Context context, boolean z) {
        C0982bp.m1357a().mo11687d(context, z);
    }

    public boolean isDeviceMacEnabled(Context context) {
        return C0982bp.m1357a().mo11706m(context);
    }

    public void setUserId(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        C0982bp.m1357a().mo11697i(context, str);
        this.f784b.setUserId(str);
        C0955bb.m1194c().mo11624a("Set user id " + str);
    }

    public String getUserId(Context context) {
        return C0982bp.m1357a().mo11712q(context);
    }

    public String getLastUserId(Context context) {
        return C0982bp.m1357a().mo11713r(context);
    }

    public void setLastUserId(Context context, String str) {
        C0982bp.m1357a().mo11700j(context, str);
    }

    public void setUserProperty(Context context, Map<String, String> map) {
        boolean z;
        JSONObject jSONObject = new JSONObject();
        if (map == null) {
            try {
                C0982bp.m1357a().mo11705m(context, "");
                this.f784b.setUserProperty("");
            } catch (Exception e) {
                C0955bb c = C0955bb.m1194c();
                c.mo11631c("[Exception] " + e.getMessage());
                e.printStackTrace();
                z = false;
            }
        } else if (map.size() > 100) {
            C0955bb.m1194c().mo11631c("[WARNING] setUserProperty failed,map size can not over 100 !");
        } else {
            z = true;
            for (Map.Entry next : map.entrySet()) {
                JSONArray jSONArray = new JSONArray();
                String str = (String) next.getKey();
                String str2 = (String) next.getValue();
                if (!TextUtils.isEmpty(str)) {
                    if (str2 != null) {
                        if (str.length() <= 256) {
                            if (TextUtils.isEmpty(str2) || str2.length() <= 256) {
                                jSONArray.put(str2);
                                jSONArray.put("1");
                                jSONObject.put(str, jSONArray);
                            }
                        }
                        C0955bb.m1194c().mo11631c("[WARNING] setUserProperty failed,key or value can not over 256 bytes !");
                        z = false;
                    }
                }
                C0955bb.m1194c().mo11631c("[WARNING] setUserProperty failed,key or value can not null !");
                z = false;
            }
            if (z) {
                C0982bp.m1357a().mo11705m(context, jSONObject.toString());
                this.f784b.setUserProperty(jSONObject.toString());
            }
        }
    }

    public void setHeaderPy(String str) {
        this.f784b.setHeaderPy(str);
    }
}
