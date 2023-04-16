package com.huayu.tzc.http;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.baidubce.http.Headers;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.bean.FitBit;
import com.huayu.tzc.bean.LoginBean;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.MsgHistory;
import com.huayu.tzc.bean.Trend;
import com.huayu.tzc.bean.Tweet;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.bean.Version;
import com.huayu.tzc.bean.VipBean;
import com.huayu.tzc.utils.BaseApplication;
import com.tencent.mmkv.MMKV;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 M2\u00020\u0001:\u0003LMNB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\n\u001a\u00020\u0006J*\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006J&\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\n\u001a\u00020\u0013J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0006J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\"\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006J\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00050\u0004J\u001c\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0006J*\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u00042\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013J\u0018\u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0\u00050\u0004J\"\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u001d0\u00042\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0013J\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u001d0\u00042\u0006\u0010'\u001a\u00020\u0013J\u0012\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00050\u0004J\b\u0010)\u001a\u00020*H\u0002J\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u00050\u00042\u0006\u0010\n\u001a\u00020\u0013J8\u0010-\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\"0\u00050\u00042\u0006\u0010\n\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0006J\"\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030\u001d0\u00042\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u0013J\u0012\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002060\u00050\u0004J\u0012\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080\u00050\u0004J\u001a\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00050\u00042\u0006\u0010\n\u001a\u00020\u0013J\u001a\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\n\u001a\u00020\u0013J&\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0\u00050\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006J\u001c\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\"\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010?\u001a\u00020\u00062\u0006\u0010@\u001a\u00020\u0006J\u001a\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010B\u001a\u00020\u0006J\u001a\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001c\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001c\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001c\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001a\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0\u00050\u00042\u0006\u0010I\u001a\u00020\u0006J\u001c\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001a\u0010K\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010B\u001a\u00020\u0006¨\u0006O"}, mo21895d2 = {"Lcom/huayu/tzc/http/Api;", "", "()V", "addMem", "Lio/reactivex/Observable;", "Lcom/huayu/tzc/base/BaseBean;", "", "requestBody", "Lokhttp3/RequestBody;", "batchDelete", "id", "bindThird", "uid", "phone", "password", "checkCode", "email", "code", "deleteMeasures", "", "deleteMem", "fitFat", "fitWeight", "forget", "mobile", "getAppVersion", "Lcom/huayu/tzc/bean/Version;", "getCode", "getMeasures", "Lcom/huayu/tzc/base/BaseListBean;", "Lcom/huayu/tzc/bean/Measure;", "page", "pageSize", "getMemList", "", "Lcom/huayu/tzc/bean/Member;", "getMsgHistory", "Lcom/huayu/tzc/bean/MsgHistory;", "getNewMsg", "max_chatid", "getNotReadCount", "getOkHttpClient", "Lokhttp3/OkHttpClient;", "getStatus", "", "getTrend", "Lcom/huayu/tzc/bean/Trend;", "startTime", "endTime", "type", "getTweetList", "Lcom/huayu/tzc/bean/Tweet;", "rows", "getUserInfo", "Lcom/huayu/tzc/bean/User;", "getVipMsg", "Lcom/huayu/tzc/bean/VipBean;", "giveUpCount", "giveUpStatus", "login", "Lcom/huayu/tzc/bean/LoginBean;", "midUnit", "modifyPass", "oldPass", "newPass", "modifyUpgrade", "purchase", "register", "reporting", "reportingData", "sendMsg", "suppleMentUser", "thirdLogin", "userId", "updateMem", "upgradeVip", "CacheNetworkInterceptor", "Companion", "RqInterceptor", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Api.kt */
public final class Api {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final TypeAdapter<String> STRING = new Api$Companion$STRING$1();
    private static final long TIMEOUT = 60;
    /* access modifiers changed from: private */
    public static volatile Api instance;
    private static ApiService service;

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000bR\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo21895d2 = {"Lcom/huayu/tzc/http/Api$Companion;", "", "()V", "STRING", "Lcom/google/gson/TypeAdapter;", "", "getSTRING", "()Lcom/google/gson/TypeAdapter;", "TIMEOUT", "", "instance", "Lcom/huayu/tzc/http/Api;", "service", "Lcom/huayu/tzc/http/ApiService;", "getInstance", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: Api.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Api getInstance() {
            Api access$getInstance$cp = Api.instance;
            if (access$getInstance$cp == null) {
                synchronized (this) {
                    access$getInstance$cp = Api.instance;
                    if (access$getInstance$cp == null) {
                        access$getInstance$cp = new Api();
                        Api.instance = access$getInstance$cp;
                    }
                }
            }
            return access$getInstance$cp;
        }

        @NotNull
        public final TypeAdapter<String> getSTRING() {
            return Api.STRING;
        }
    }

    public Api() {
        Retrofit build = new Retrofit.Builder().baseUrl("http://mallapi.tenswall.com:8080/").addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeHierarchyAdapter(String.class, STRING).create())).client(getOkHttpClient()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Retrofit.Builder()\n     …e())\n            .build()");
        service = (ApiService) build.create(ApiService.class);
    }

    private final OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor(Api$getOkHttpClient$1.INSTANCE).setLevel(HttpLoggingInterceptor.Level.BASIC));
        BaseApplication instance2 = BaseApplication.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance2, "BaseApplication.getInstance()");
        OkHttpClient build = addInterceptor.cache(new Cache(new File(instance2.getExternalCacheDir(), "test_cache"), Config.FULL_TRACE_LOG_LIMIT)).connectTimeout(TIMEOUT, TimeUnit.SECONDS).addInterceptor(new RqInterceptor()).addNetworkInterceptor(new CacheNetworkInterceptor()).readTimeout(TIMEOUT, TimeUnit.SECONDS).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OkHttpClient.Builder() /…NDS)\n            .build()");
        return build;
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, mo21895d2 = {"Lcom/huayu/tzc/http/Api$RqInterceptor;", "Lokhttp3/Interceptor;", "()V", "getFitToken", "", "getToken", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: Api.kt */
    private static final class RqInterceptor implements Interceptor {
        @NotNull
        public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
            Intrinsics.checkParameterIsNotNull(chain, "chain");
            Request request = chain.request();
            if (NetworkUtils.isConnected()) {
                Request.Builder addHeader = chain.request().newBuilder().addHeader("Connection", "Keep-Alive").addHeader("Accept-Language", "zh-CN,en-US;q=0.9").addHeader(Headers.CONTENT_TYPE, "application/x-www-form-urlencoded").addHeader("X-Token", getToken());
                Response proceed = chain.proceed(addHeader.addHeader(Headers.AUTHORIZATION, "Bearer " + getFitToken()).build());
                ResponseBody peekBody = proceed.peekBody(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
                Log.e("TAG", "response: " + proceed);
                if (peekBody != null) {
                    String string = peekBody.string();
                    Log.e(String.valueOf(proceed.code()) + "  response ---", string);
                    if (proceed.code() != 200) {
                        throw new ApiException(new Throwable(string), proceed.code());
                    }
                }
                Response.Builder removeHeader = proceed.newBuilder().removeHeader("Pragma").removeHeader(Headers.CACHE_CONTROL);
                Response build = removeHeader.header(Headers.CACHE_CONTROL, "public, max-age=" + 3).build();
                Intrinsics.checkExpressionValueIsNotNull(build, "response.newBuilder()\n  …                 .build()");
                return build;
            }
            Response.Builder removeHeader2 = chain.proceed(request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()).newBuilder().removeHeader("Pragma").removeHeader(Headers.CACHE_CONTROL);
            Response build2 = removeHeader2.header(Headers.CACHE_CONTROL, "public, only-if-cached, max-stale=" + 259200).build();
            Intrinsics.checkExpressionValueIsNotNull(build2, "response.newBuilder()\n  …                 .build()");
            return build2;
        }

        private final String getToken() {
            String decodeString = MMKV.defaultMMKV().decodeString("token", "");
            Intrinsics.checkExpressionValueIsNotNull(decodeString, "MMKV.defaultMMKV().decodeString(\"token\",\"\")");
            return decodeString;
        }

        private final String getFitToken() {
            return ((FitBit) MMKV.defaultMMKV().decodeParcelable("fitbit", FitBit.class, new FitBit())).getFitToken();
        }
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, mo21895d2 = {"Lcom/huayu/tzc/http/Api$CacheNetworkInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: Api.kt */
    public static final class CacheNetworkInterceptor implements Interceptor {
        @NotNull
        public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
            Intrinsics.checkParameterIsNotNull(chain, "chain");
            Response build = chain.proceed(chain.request()).newBuilder().removeHeader("Pragma").addHeader(Headers.CACHE_CONTROL, "max-age=60").build();
            Intrinsics.checkExpressionValueIsNotNull(build, "chain.proceed(chain.requ…\n                .build()");
            return build;
        }
    }

    @NotNull
    public final Observable<BaseBean<LoginBean>> login(@Nullable String str, @Nullable String str2) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        if (str == null) {
            Intrinsics.throwNpe();
        }
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        return apiService.login(str, str2);
    }

    @NotNull
    public final Observable<BaseBean<String>> register(@NotNull RequestBody requestBody) {
        Intrinsics.checkParameterIsNotNull(requestBody, "requestBody");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.register(requestBody);
    }

    @NotNull
    public final Observable<BaseBean<String>> forget(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "mobile");
        Intrinsics.checkParameterIsNotNull(str2, "password");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.forget(str, str2);
    }

    @NotNull
    public final Observable<BaseBean<String>> getCode(@Nullable String str) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getCode(str);
    }

    @NotNull
    public final Observable<BaseBean<String>> checkCode(@Nullable String str, @Nullable String str2) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.checkCode(str, str2);
    }

    @NotNull
    public final Observable<BaseBean<LoginBean>> thirdLogin(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "userId");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.thirdLogin(1, str);
    }

    @NotNull
    public final Observable<BaseBean<String>> bindThird(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, Config.CUSTOM_USER_ID);
        Intrinsics.checkParameterIsNotNull(str2, "phone");
        Intrinsics.checkParameterIsNotNull(str3, "password");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.bindThird(1, str, str2, str3);
    }

    @NotNull
    public final Observable<BaseBean<List<Member>>> getMemList() {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getMemList();
    }

    @NotNull
    public final Observable<BaseBean<String>> addMem(@NotNull RequestBody requestBody) {
        Intrinsics.checkParameterIsNotNull(requestBody, "requestBody");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.addMem(requestBody);
    }

    @NotNull
    public final Observable<BaseBean<User>> getUserInfo() {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getUserInfo();
    }

    @NotNull
    public final Observable<BaseBean<String>> suppleMentUser(@Nullable RequestBody requestBody) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.suppleMentUser(requestBody);
    }

    @NotNull
    public final Observable<BaseBean<String>> updateMem(@Nullable RequestBody requestBody) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.updateMem(requestBody);
    }

    @NotNull
    public final Observable<BaseBean<String>> deleteMem(@Nullable String str) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.deleteMem(str);
    }

    @NotNull
    public final Observable<BaseBean<Version>> getAppVersion() {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getAppVersion();
    }

    @NotNull
    public final Observable<BaseBean<Boolean>> getStatus(int i) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getStatus(i);
    }

    @NotNull
    public final Observable<BaseBean<String>> giveUpStatus(int i) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.giveUpStatus(i);
    }

    @NotNull
    public final Observable<BaseBean<Integer>> giveUpCount(int i) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.giveUpCount(i);
    }

    @NotNull
    public final Observable<BaseBean<String>> reporting(@Nullable RequestBody requestBody) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        if (requestBody == null) {
            Intrinsics.throwNpe();
        }
        return apiService.reporting(requestBody);
    }

    @NotNull
    public final Observable<BaseBean<String>> reportingData(@Nullable RequestBody requestBody) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        if (requestBody == null) {
            Intrinsics.throwNpe();
        }
        return apiService.reportingData(requestBody);
    }

    @NotNull
    public final Observable<BaseListBean<MsgHistory>> getMsgHistory(int i, int i2) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getMsgHistory(i, i2);
    }

    @NotNull
    public final Observable<BaseBean<String>> midUnit(@Nullable RequestBody requestBody) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        if (requestBody == null) {
            Intrinsics.throwNpe();
        }
        return apiService.midUnit(requestBody);
    }

    @NotNull
    public final Observable<String> fitFat(@NotNull String str, @NotNull RequestBody requestBody) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(requestBody, "requestBody");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.fitFat(str, requestBody);
    }

    @NotNull
    public final Observable<String> fitWeight(@NotNull String str, @NotNull RequestBody requestBody) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(requestBody, "requestBody");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.fitWeight(str, requestBody);
    }

    @NotNull
    public final Observable<BaseListBean<Measure>> getMeasures(int i, int i2, int i3) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getMeasures(i, i2, i3);
    }

    @NotNull
    public final Observable<BaseBean<String>> deleteMeasures(int i) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.deleteMeasures(i);
    }

    @NotNull
    public final Observable<BaseBean<String>> batchDelete(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.batchDelete(str);
    }

    @NotNull
    public final Observable<BaseBean<List<Trend>>> getTrend(int i, @NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "startTime");
        Intrinsics.checkParameterIsNotNull(str2, "endTime");
        Intrinsics.checkParameterIsNotNull(str3, "type");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getTrend(i, str, str2, str3);
    }

    @NotNull
    public final Observable<BaseBean<String>> sendMsg(@Nullable RequestBody requestBody) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        if (requestBody == null) {
            Intrinsics.throwNpe();
        }
        return apiService.sendMessage(requestBody);
    }

    @NotNull
    public final Observable<BaseListBean<MsgHistory>> getNewMsg(int i) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getNewMsg(1, 99, i);
    }

    @NotNull
    public final Observable<BaseBean<String>> modifyPass(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "oldPass");
        Intrinsics.checkParameterIsNotNull(str2, "newPass");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.modifyPass(str, str2);
    }

    @NotNull
    public final Observable<BaseBean<VipBean>> getVipMsg() {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getVipMsg();
    }

    @NotNull
    public final Observable<BaseBean<String>> upgradeVip(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "purchase");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.upgradeVip(str, 2);
    }

    @NotNull
    public final Observable<BaseBean<String>> modifyUpgrade(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "purchase");
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.modifyUpgrade(str);
    }

    @NotNull
    public final Observable<BaseListBean<Tweet>> getTweetList(int i, int i2) {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getTweetList(i, i2);
    }

    @NotNull
    public final Observable<BaseBean<Integer>> getNotReadCount() {
        ApiService apiService = service;
        if (apiService == null) {
            Intrinsics.throwNpe();
        }
        return apiService.getNotReadCount();
    }
}
