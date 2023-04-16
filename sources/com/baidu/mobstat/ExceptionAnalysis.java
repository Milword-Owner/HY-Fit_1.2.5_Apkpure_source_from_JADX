package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExceptionAnalysis {

    /* renamed from: a */
    private static ExceptionAnalysis f804a = new ExceptionAnalysis();

    /* renamed from: b */
    private boolean f805b = false;

    /* renamed from: c */
    private Context f806c;

    /* renamed from: d */
    private HeadObject f807d = new HeadObject();

    /* renamed from: e */
    private String f808e;

    /* renamed from: f */
    private List<String> f809f;

    /* renamed from: g */
    private boolean f810g = true;
    public Callback mCallback;

    public interface Callback {
        void onCallback(JSONObject jSONObject);
    }

    public static ExceptionAnalysis getInstance() {
        return f804a;
    }

    private ExceptionAnalysis() {
    }

    public ExceptionAnalysis(Callback callback) {
        this.mCallback = callback;
    }

    public void openExceptionAnalysis(Context context, boolean z) {
        if (context != null) {
            this.f806c = context.getApplicationContext();
        }
        if (this.f806c != null && !this.f805b) {
            this.f805b = true;
            C0877ab.m819a().mo11429a(this.f806c);
            if (!z) {
                NativeCrashHandler.init(this.f806c);
            }
        }
    }

    public void saveCrashInfo(Context context, Throwable th, boolean z) {
        int i;
        if (context != null) {
            this.f806c = context.getApplicationContext();
        }
        if (this.f806c != null) {
            String th2 = th.toString();
            String str = "";
            if (!TextUtils.isEmpty(th2)) {
                try {
                    String[] split = th2.split(Config.TRACE_TODAY_VISIT_SPLIT);
                    str = split.length > 1 ? split[0] : th2;
                } catch (Exception unused) {
                }
            }
            String str2 = TextUtils.isEmpty(str) ? th2 : str;
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            String obj = stringWriter.toString();
            if (!z) {
                i = th instanceof Exception ? 11 : th instanceof Error ? 12 : 13;
            } else {
                i = 0;
            }
            saveCrashInfo(this.f806c, System.currentTimeMillis(), obj, str2, 0, i);
        }
    }

    public void saveCrashInfo(Context context, long j, String str, String str2, int i, int i2) {
        BDStatCore.instance().autoTrackSessionEndTime(context);
        if (context != null && str != null && !str.trim().equals("")) {
            try {
                StringBuilder sb = new StringBuilder(str);
                if (!TextUtils.isEmpty(this.f808e)) {
                    sb.append("\n");
                    sb.append("ExtraInfo:");
                    sb.append(this.f808e);
                }
                String appVersionName = CooperService.instance().getAppVersionName(context);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("t", j);
                jSONObject.put("c", sb.toString());
                jSONObject.put("y", str2);
                jSONObject.put("v", appVersionName);
                jSONObject.put("ct", i);
                jSONObject.put("mem", m754a(context));
                jSONObject.put(Config.EXCEPTION_CRASH_CHANNEL, i2);
                jSONObject.put("sv", "4.0.3.6");
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                JSONObject jSONObject2 = new JSONObject();
                this.f807d.installHeader(context, jSONObject2);
                jSONObject2.put("ss", 0);
                jSONObject2.put(Config.SEQUENCE_INDEX, 0);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(Config.HEADER_PART, jSONObject2);
                jSONObject3.put(Config.PRINCIPAL_PART, new JSONArray());
                jSONObject3.put(Config.EVENT_PART, new JSONArray());
                jSONObject3.put(Config.EXCEPTION_PART, jSONArray);
                jSONObject3.put(Config.TRACE_PART, m753a());
                if (this.mCallback != null) {
                    this.mCallback.onCallback(jSONObject3);
                }
                C0980bn.m1347a(context, Config.PREFIX_SEND_DATA + System.currentTimeMillis(), jSONObject3.toString(), false);
                C0955bb.m1194c().mo11624a("dump exception, exception: " + str);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private JSONObject m753a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.TRACE_APPLICATION_SESSION, 0);
        } catch (Exception unused) {
        }
        try {
            jSONObject.put(Config.TRACE_FAILED_CNT, 0);
        } catch (Exception unused2) {
        }
        return jSONObject;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private JSONObject m754a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return null;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        JSONObject jSONObject = new JSONObject();
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                jSONObject.put(Config.EXCEPTION_MEMORY_TOTAL, memoryInfo.totalMem);
            }
            jSONObject.put(Config.EXCEPTION_MEMORY_FREE, memoryInfo.availMem);
            jSONObject.put(Config.EXCEPTION_MEMORY_LOW, memoryInfo.lowMemory ? 1 : 0);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public void setCrashExtraInfo(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.length() > 256) {
                str = str.substring(0, 256);
            }
            this.f808e = str;
        }
    }

    public void setFilterPackageList(List<String> list) {
        if (list != null && list.size() > 0) {
            this.f809f = list;
        }
    }

    public void setEnableSend(boolean z) {
        this.f810g = z;
    }
}
