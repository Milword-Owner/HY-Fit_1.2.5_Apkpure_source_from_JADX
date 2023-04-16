package com.facebook.internal.instrument;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.internal.Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class InstrumentUtility {
    public static final String CRASH_REPORT_PREFIX = "crash_log_";
    public static final String ERROR_REPORT_PREFIX = "error_log_";
    private static final String FBSDK_PREFIX = "com.facebook";
    private static final String INSTRUMENT_DIR = "instrument";

    @Nullable
    public static String getCause(Throwable th) {
        if (th == null) {
            return null;
        }
        if (th.getCause() == null) {
            return th.toString();
        }
        return th.getCause().toString();
    }

    @Nullable
    public static String getStackTrace(Throwable th) {
        Throwable th2 = null;
        if (th == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        while (true) {
            Throwable th3 = th2;
            th2 = th;
            Throwable th4 = th3;
            if (th2 != null && th2 != th4) {
                for (StackTraceElement stackTraceElement : th2.getStackTrace()) {
                    jSONArray.put(stackTraceElement.toString());
                }
                th = th2.getCause();
            }
        }
        return jSONArray.toString();
    }

    public static boolean isSDKRelatedException(@Nullable Throwable th) {
        if (th == null) {
            return false;
        }
        Throwable th2 = null;
        while (true) {
            Throwable th3 = th2;
            th2 = th;
            Throwable th4 = th3;
            if (th2 == null || th2 == th4) {
                return false;
            }
            for (StackTraceElement className : th2.getStackTrace()) {
                if (className.getClassName().startsWith(FBSDK_PREFIX)) {
                    return true;
                }
            }
            th = th2.getCause();
        }
        return false;
    }

    public static File[] listCrashReportFiles() {
        File instrumentReportDir = getInstrumentReportDir();
        if (instrumentReportDir == null) {
            return new File[0];
        }
        File[] listFiles = instrumentReportDir.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.matches(String.format("^%s[0-9]+.json$", new Object[]{InstrumentUtility.CRASH_REPORT_PREFIX}));
            }
        });
        return listFiles != null ? listFiles : new File[0];
    }

    @Nullable
    public static JSONObject readFile(@Nullable String str, boolean z) {
        File instrumentReportDir = getInstrumentReportDir();
        if (!(instrumentReportDir == null || str == null)) {
            try {
                return new JSONObject(Utility.readStreamToString(new FileInputStream(new File(instrumentReportDir, str))));
            } catch (Exception unused) {
                if (z) {
                    deleteFile(str);
                }
            }
        }
        return null;
    }

    public static void writeFile(@Nullable String str, @Nullable String str2) {
        File instrumentReportDir = getInstrumentReportDir();
        if (instrumentReportDir != null && str != null && str2 != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(instrumentReportDir, str));
                fileOutputStream.write(str2.getBytes());
                fileOutputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static boolean deleteFile(@Nullable String str) {
        File instrumentReportDir = getInstrumentReportDir();
        if (instrumentReportDir == null || str == null) {
            return false;
        }
        return new File(instrumentReportDir, str).delete();
    }

    public static void sendReports(String str, JSONArray jSONArray, GraphRequest.Callback callback) {
        if (jSONArray.length() != 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(str, jSONArray.toString());
                GraphRequest.newPostRequest((AccessToken) null, String.format("%s/instruments", new Object[]{FacebookSdk.getApplicationId()}), jSONObject, callback).executeAsync();
            } catch (JSONException unused) {
            }
        }
    }

    @Nullable
    public static File getInstrumentReportDir() {
        File file = new File(FacebookSdk.getApplicationContext().getCacheDir(), INSTRUMENT_DIR);
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        return null;
    }
}
