package com.blankj.utilcode.util;

import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ApiUtils {
    private static final String TAG = "ApiUtils";
    private Map<Class, BaseApi> mApiMap;
    private Map<Class, Class> mInjectApiImplMap;

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    public @interface Api {
        boolean isMock() default false;
    }

    public static class BaseApi {
    }

    private void init() {
    }

    private ApiUtils() {
        this.mApiMap = new ConcurrentHashMap();
        this.mInjectApiImplMap = new HashMap();
        init();
    }

    private void registerImpl(Class cls) {
        this.mInjectApiImplMap.put(cls.getSuperclass(), cls);
    }

    public static <T extends BaseApi> T getApi(@NonNull Class<T> cls) {
        if (cls != null) {
            return (BaseApi) getInstance().getApiInner(cls);
        }
        throw new NullPointerException("Argument 'apiClass' of type Class<T> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void register(Class<? extends BaseApi> cls) {
        getInstance().registerImpl(cls);
    }

    public static String toString_() {
        return getInstance().toString();
    }

    public String toString() {
        return "ApiUtils: " + this.mInjectApiImplMap;
    }

    private static ApiUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    private <Result> Result getApiInner(Class cls) {
        Result result = (BaseApi) this.mApiMap.get(cls);
        if (result != null) {
            return result;
        }
        synchronized (cls) {
            Result result2 = (BaseApi) this.mApiMap.get(cls);
            if (result2 != null) {
                return result2;
            }
            Class cls2 = this.mInjectApiImplMap.get(cls);
            if (cls2 != null) {
                try {
                    Result result3 = (BaseApi) cls2.newInstance();
                    this.mApiMap.put(cls, result3);
                    return result3;
                } catch (Exception unused) {
                    Log.e(TAG, "The <" + cls2 + "> has no parameterless constructor.");
                    return null;
                }
            } else {
                Log.e(TAG, "The <" + cls + "> doesn't implement.");
                return null;
            }
        }
    }

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static final ApiUtils INSTANCE = new ApiUtils();

        private LazyHolder() {
        }
    }
}
