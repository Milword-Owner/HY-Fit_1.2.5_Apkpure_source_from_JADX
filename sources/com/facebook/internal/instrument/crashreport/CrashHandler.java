package com.facebook.internal.instrument.crashreport;

import android.os.Process;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.instrument.InstrumentData;
import com.facebook.internal.instrument.InstrumentUtility;
import java.io.File;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final int MAX_CRASH_REPORT_NUM = 5;
    private static final String TAG = "com.facebook.internal.instrument.crashreport.CrashHandler";
    @Nullable
    private static CrashHandler instance;
    private boolean mEndApplication = false;
    @Nullable
    private final Thread.UncaughtExceptionHandler mPreviousHandler;

    private CrashHandler(@Nullable Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.mPreviousHandler = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (InstrumentUtility.isSDKRelatedException(th)) {
            new InstrumentData(th, InstrumentData.Type.CrashReport).save();
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mPreviousHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
        if (this.mEndApplication) {
            killProcess();
        }
    }

    public static synchronized void enable() {
        synchronized (CrashHandler.class) {
            if (FacebookSdk.getAutoLogAppEventsEnabled()) {
                sendCrashReports();
            }
            if (instance != null) {
                Log.w(TAG, "Already enabled!");
                return;
            }
            instance = new CrashHandler(Thread.getDefaultUncaughtExceptionHandler());
            Thread.setDefaultUncaughtExceptionHandler(instance);
        }
    }

    public void endApplication() {
        this.mEndApplication = true;
    }

    private static void killProcess() {
        try {
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Throwable unused) {
        }
    }

    private static void sendCrashReports() {
        File[] listCrashReportFiles = InstrumentUtility.listCrashReportFiles();
        final ArrayList arrayList = new ArrayList();
        int i = 0;
        for (File instrumentData : listCrashReportFiles) {
            InstrumentData instrumentData2 = new InstrumentData(instrumentData);
            if (instrumentData2.isValid()) {
                arrayList.add(instrumentData2);
            }
        }
        Collections.sort(arrayList, new Comparator<InstrumentData>() {
            public int compare(InstrumentData instrumentData, InstrumentData instrumentData2) {
                return instrumentData.compareTo(instrumentData2);
            }
        });
        JSONArray jSONArray = new JSONArray();
        while (i < arrayList.size() && i < 5) {
            jSONArray.put(arrayList.get(i));
            i++;
        }
        InstrumentUtility.sendReports("crash_reports", jSONArray, new GraphRequest.Callback() {
            public void onCompleted(GraphResponse graphResponse) {
                try {
                    if (graphResponse.getError() == null && graphResponse.getJSONObject().getBoolean(GraphResponse.SUCCESS_KEY)) {
                        for (int i = 0; arrayList.size() > i; i++) {
                            ((InstrumentData) arrayList.get(i)).clear();
                        }
                    }
                } catch (JSONException unused) {
                }
            }
        });
    }
}
