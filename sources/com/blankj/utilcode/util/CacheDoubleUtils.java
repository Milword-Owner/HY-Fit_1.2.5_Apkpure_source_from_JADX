package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.constant.CacheConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class CacheDoubleUtils implements CacheConstants {
    private static final Map<String, CacheDoubleUtils> CACHE_MAP = new HashMap();
    private final CacheDiskUtils mCacheDiskUtils;
    private final CacheMemoryUtils mCacheMemoryUtils;

    public static CacheDoubleUtils getInstance() {
        return getInstance(CacheMemoryUtils.getInstance(), CacheDiskUtils.getInstance());
    }

    public static CacheDoubleUtils getInstance(@NonNull CacheMemoryUtils cacheMemoryUtils, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (cacheMemoryUtils == null) {
            throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cacheDiskUtils != null) {
            String str = cacheDiskUtils.toString() + Config.replace + cacheMemoryUtils.toString();
            CacheDoubleUtils cacheDoubleUtils = CACHE_MAP.get(str);
            if (cacheDoubleUtils == null) {
                synchronized (CacheDoubleUtils.class) {
                    cacheDoubleUtils = CACHE_MAP.get(str);
                    if (cacheDoubleUtils == null) {
                        cacheDoubleUtils = new CacheDoubleUtils(cacheMemoryUtils, cacheDiskUtils);
                        CACHE_MAP.put(str, cacheDoubleUtils);
                    }
                }
            }
            return cacheDoubleUtils;
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    private CacheDoubleUtils(CacheMemoryUtils cacheMemoryUtils, CacheDiskUtils cacheDiskUtils) {
        this.mCacheMemoryUtils = cacheMemoryUtils;
        this.mCacheDiskUtils = cacheDiskUtils;
    }

    public void put(@NonNull String str, byte[] bArr) {
        if (str != null) {
            put(str, bArr, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, byte[] bArr, int i) {
        if (str != null) {
            this.mCacheMemoryUtils.put(str, bArr, i);
            this.mCacheDiskUtils.put(str, bArr, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public byte[] getBytes(@NonNull String str) {
        if (str != null) {
            return getBytes(str, (byte[]) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public byte[] getBytes(@NonNull String str, byte[] bArr) {
        if (str != null) {
            byte[] bArr2 = (byte[]) this.mCacheMemoryUtils.get(str);
            if (bArr2 != null) {
                return bArr2;
            }
            return this.mCacheDiskUtils.getBytes(str, bArr);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, String str2) {
        if (str != null) {
            put(str, str2, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, String str2, int i) {
        if (str != null) {
            this.mCacheMemoryUtils.put(str, str2, i);
            this.mCacheDiskUtils.put(str, str2, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public String getString(@NonNull String str) {
        if (str != null) {
            return getString(str, (String) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public String getString(@NonNull String str, String str2) {
        if (str != null) {
            String str3 = (String) this.mCacheMemoryUtils.get(str);
            if (str3 != null) {
                return str3;
            }
            return this.mCacheDiskUtils.getString(str, str2);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            put(str, jSONObject, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, JSONObject jSONObject, int i) {
        if (str != null) {
            this.mCacheMemoryUtils.put(str, jSONObject, i);
            this.mCacheDiskUtils.put(str, jSONObject, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public JSONObject getJSONObject(@NonNull String str) {
        if (str != null) {
            return getJSONObject(str, (JSONObject) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public JSONObject getJSONObject(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            JSONObject jSONObject2 = (JSONObject) this.mCacheMemoryUtils.get(str);
            if (jSONObject2 != null) {
                return jSONObject2;
            }
            return this.mCacheDiskUtils.getJSONObject(str, jSONObject);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            put(str, jSONArray, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, JSONArray jSONArray, int i) {
        if (str != null) {
            this.mCacheMemoryUtils.put(str, jSONArray, i);
            this.mCacheDiskUtils.put(str, jSONArray, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public JSONArray getJSONArray(@NonNull String str) {
        if (str != null) {
            return getJSONArray(str, (JSONArray) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public JSONArray getJSONArray(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            JSONArray jSONArray2 = (JSONArray) this.mCacheMemoryUtils.get(str);
            if (jSONArray2 != null) {
                return jSONArray2;
            }
            return this.mCacheDiskUtils.getJSONArray(str, jSONArray);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            put(str, bitmap, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, Bitmap bitmap, int i) {
        if (str != null) {
            this.mCacheMemoryUtils.put(str, bitmap, i);
            this.mCacheDiskUtils.put(str, bitmap, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public Bitmap getBitmap(@NonNull String str) {
        if (str != null) {
            return getBitmap(str, (Bitmap) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public Bitmap getBitmap(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            Bitmap bitmap2 = (Bitmap) this.mCacheMemoryUtils.get(str);
            if (bitmap2 != null) {
                return bitmap2;
            }
            return this.mCacheDiskUtils.getBitmap(str, bitmap);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, Drawable drawable) {
        if (str != null) {
            put(str, drawable, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, Drawable drawable, int i) {
        if (str != null) {
            this.mCacheMemoryUtils.put(str, drawable, i);
            this.mCacheDiskUtils.put(str, drawable, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public Drawable getDrawable(@NonNull String str) {
        if (str != null) {
            return getDrawable(str, (Drawable) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public Drawable getDrawable(@NonNull String str, Drawable drawable) {
        if (str != null) {
            Drawable drawable2 = (Drawable) this.mCacheMemoryUtils.get(str);
            if (drawable2 != null) {
                return drawable2;
            }
            return this.mCacheDiskUtils.getDrawable(str, drawable);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, Parcelable parcelable) {
        if (str != null) {
            put(str, parcelable, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, Parcelable parcelable, int i) {
        if (str != null) {
            this.mCacheMemoryUtils.put(str, parcelable, i);
            this.mCacheDiskUtils.put(str, parcelable, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (creator != null) {
            return getParcelable(str, creator, (Object) null);
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (creator != null) {
            T t2 = this.mCacheMemoryUtils.get(str);
            if (t2 != null) {
                return t2;
            }
            return this.mCacheDiskUtils.getParcelable(str, creator, t);
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public void put(@NonNull String str, Serializable serializable) {
        if (str != null) {
            put(str, serializable, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(@NonNull String str, Serializable serializable, int i) {
        if (str != null) {
            this.mCacheMemoryUtils.put(str, serializable, i);
            this.mCacheDiskUtils.put(str, serializable, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public Object getSerializable(@NonNull String str) {
        if (str != null) {
            return getSerializable(str, (Object) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public Object getSerializable(@NonNull String str, Object obj) {
        if (str != null) {
            Object obj2 = this.mCacheMemoryUtils.get(str);
            if (obj2 != null) {
                return obj2;
            }
            return this.mCacheDiskUtils.getSerializable(str, obj);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public long getCacheDiskSize() {
        return this.mCacheDiskUtils.getCacheSize();
    }

    public int getCacheDiskCount() {
        return this.mCacheDiskUtils.getCacheCount();
    }

    public int getCacheMemoryCount() {
        return this.mCacheMemoryUtils.getCacheCount();
    }

    public void remove(@NonNull String str) {
        if (str != null) {
            this.mCacheMemoryUtils.remove(str);
            this.mCacheDiskUtils.remove(str);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void clear() {
        this.mCacheMemoryUtils.clear();
        this.mCacheDiskUtils.clear();
    }
}
