package p040uk.p041co.senab2.photoview2.log;

import android.util.Log;

/* renamed from: uk.co.senab2.photoview2.log.LoggerDefault */
public class LoggerDefault implements Logger {
    /* renamed from: v */
    public int mo35117v(String str, String str2) {
        return Log.v(str, str2);
    }

    /* renamed from: v */
    public int mo35118v(String str, String str2, Throwable th) {
        return Log.v(str, str2, th);
    }

    /* renamed from: d */
    public int mo35111d(String str, String str2) {
        return Log.d(str, str2);
    }

    /* renamed from: d */
    public int mo35112d(String str, String str2, Throwable th) {
        return Log.d(str, str2, th);
    }

    /* renamed from: i */
    public int mo35115i(String str, String str2) {
        return Log.i(str, str2);
    }

    /* renamed from: i */
    public int mo35116i(String str, String str2, Throwable th) {
        return Log.i(str, str2, th);
    }

    /* renamed from: w */
    public int mo35119w(String str, String str2) {
        return Log.w(str, str2);
    }

    /* renamed from: w */
    public int mo35120w(String str, String str2, Throwable th) {
        return Log.w(str, str2, th);
    }

    /* renamed from: e */
    public int mo35113e(String str, String str2) {
        return Log.e(str, str2);
    }

    /* renamed from: e */
    public int mo35114e(String str, String str2, Throwable th) {
        return Log.e(str, str2, th);
    }
}
