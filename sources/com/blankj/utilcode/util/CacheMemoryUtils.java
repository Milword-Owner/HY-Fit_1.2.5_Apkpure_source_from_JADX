package com.blankj.utilcode.util;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;
import com.blankj.utilcode.constant.CacheConstants;
import java.util.HashMap;
import java.util.Map;

public final class CacheMemoryUtils implements CacheConstants {
    private static final Map<String, CacheMemoryUtils> CACHE_MAP = new HashMap();
    private static final int DEFAULT_MAX_COUNT = 256;
    private final String mCacheKey;
    private final LruCache<String, CacheValue> mMemoryCache;

    public static CacheMemoryUtils getInstance() {
        return getInstance(256);
    }

    public static CacheMemoryUtils getInstance(int i) {
        return getInstance(String.valueOf(i), i);
    }

    public static CacheMemoryUtils getInstance(String str, int i) {
        CacheMemoryUtils cacheMemoryUtils = CACHE_MAP.get(str);
        if (cacheMemoryUtils == null) {
            synchronized (CacheMemoryUtils.class) {
                cacheMemoryUtils = CACHE_MAP.get(str);
                if (cacheMemoryUtils == null) {
                    cacheMemoryUtils = new CacheMemoryUtils(str, new LruCache(i));
                    CACHE_MAP.put(str, cacheMemoryUtils);
                }
            }
        }
        return cacheMemoryUtils;
    }

    private CacheMemoryUtils(String str, LruCache<String, CacheValue> lruCache) {
        this.mCacheKey = str;
        this.mMemoryCache = lruCache;
    }

    public String toString() {
        return this.mCacheKey + "@" + Integer.toHexString(hashCode());
    }

    public void put(@NonNull String str, Object obj) {
        if (str != null) {
            put(str, obj, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, Object obj, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (obj != null) {
            this.mMemoryCache.put(str, new CacheValue(i < 0 ? -1 : System.currentTimeMillis() + ((long) (i * 1000)), obj));
        }
    }

    public <T> T get(@NonNull String str) {
        if (str != null) {
            return get(str, (Object) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public <T> T get(@NonNull String str, T t) {
        if (str != null) {
            CacheValue cacheValue = this.mMemoryCache.get(str);
            if (cacheValue == null) {
                return t;
            }
            if (cacheValue.dueTime == -1 || cacheValue.dueTime >= System.currentTimeMillis()) {
                return cacheValue.value;
            }
            this.mMemoryCache.remove(str);
            return t;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public int getCacheCount() {
        return this.mMemoryCache.size();
    }

    public Object remove(@NonNull String str) {
        if (str != null) {
            CacheValue remove = this.mMemoryCache.remove(str);
            if (remove == null) {
                return null;
            }
            return remove.value;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void clear() {
        this.mMemoryCache.evictAll();
    }

    private static final class CacheValue {
        long dueTime;
        Object value;

        CacheValue(long j, Object obj) {
            this.dueTime = j;
            this.value = obj;
        }
    }
}
