package com.huayu.tzc.http;

import android.util.Log;
import kotlin.Metadata;
import okhttp3.logging.HttpLoggingInterceptor;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo21895d2 = {"<anonymous>", "", "message", "", "kotlin.jvm.PlatformType", "log"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: Api.kt */
final class Api$getOkHttpClient$1 implements HttpLoggingInterceptor.Logger {
    public static final Api$getOkHttpClient$1 INSTANCE = new Api$getOkHttpClient$1();

    Api$getOkHttpClient$1() {
    }

    public final void log(String str) {
        Log.e("TAG", "log: " + str);
    }
}
