package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.HexDumpUtils;
import com.google.android.gms.internal.fitness.zzko;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@SafeParcelable.Class(creator = "ValueCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class Value extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<Value> CREATOR = new zzal();
    @SafeParcelable.Field(getter = "getFormat", mo19514id = 1)
    private final int format;
    @SafeParcelable.Field(getter = "getValue", mo19514id = 3)
    private float value;
    @SafeParcelable.Field(getter = "isSet", mo19514id = 2)
    private boolean zzos;
    @SafeParcelable.Field(getter = "getStringValue", mo19514id = 4)
    @Nullable
    private String zzot;
    @SafeParcelable.Field(getter = "getMapValue", mo19514id = 5, type = "android.os.Bundle")
    @Nullable
    private Map<String, MapValue> zzou;
    @SafeParcelable.Field(getter = "getIntArrayValue", mo19514id = 6)
    @Nullable
    private int[] zzov;
    @SafeParcelable.Field(getter = "getFloatArrayValue", mo19514id = 7)
    @Nullable
    private float[] zzow;
    @SafeParcelable.Field(getter = "getBlob", mo19514id = 8)
    @Nullable
    private byte[] zzox;

    @ShowFirstParty
    public Value(int i) {
        this(i, false, 0.0f, (String) null, (Bundle) null, (int[]) null, (float[]) null, (byte[]) null);
    }

    @SafeParcelable.Constructor
    Value(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) boolean z, @SafeParcelable.Param(mo19517id = 3) float f, @SafeParcelable.Param(mo19517id = 4) @Nullable String str, @SafeParcelable.Param(mo19517id = 5) @Nullable Bundle bundle, @SafeParcelable.Param(mo19517id = 6) @Nullable int[] iArr, @SafeParcelable.Param(mo19517id = 7) @Nullable float[] fArr, @SafeParcelable.Param(mo19517id = 8) @Nullable byte[] bArr) {
        ArrayMap arrayMap;
        this.format = i;
        this.zzos = z;
        this.value = f;
        this.zzot = str;
        if (bundle == null) {
            arrayMap = null;
        } else {
            bundle.setClassLoader((ClassLoader) Preconditions.checkNotNull(MapValue.class.getClassLoader()));
            arrayMap = new ArrayMap(bundle.size());
            for (String str2 : bundle.keySet()) {
                arrayMap.put(str2, (MapValue) Preconditions.checkNotNull((MapValue) bundle.getParcelable(str2)));
            }
        }
        this.zzou = arrayMap;
        this.zzov = iArr;
        this.zzow = fArr;
        this.zzox = bArr;
    }

    @Deprecated
    public final void setInt(int i) {
        Preconditions.checkState(this.format == 1, "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
        this.zzos = true;
        this.value = Float.intBitsToFloat(i);
    }

    @Deprecated
    public final void setFloat(float f) {
        Preconditions.checkState(this.format == 2, "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
        this.zzos = true;
        this.value = f;
    }

    @Deprecated
    public final void setString(@RecentlyNonNull String str) {
        Preconditions.checkState(this.format == 3, "Attempting to set a string value to a field that is not in STRING format.  Please check the data type definition and use the right format.");
        this.zzos = true;
        this.zzot = str;
    }

    @Deprecated
    public final void setKeyValue(@RecentlyNonNull String str, float f) {
        Preconditions.checkState(this.format == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        this.zzos = true;
        if (this.zzou == null) {
            this.zzou = new HashMap();
        }
        this.zzou.put(str, MapValue.zza(f));
    }

    @Deprecated
    public final void clearKey(@RecentlyNonNull String str) {
        Preconditions.checkState(this.format == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        Map<String, MapValue> map = this.zzou;
        if (map != null) {
            map.remove(str);
        }
    }

    @ShowFirstParty
    @Deprecated
    public final void zza(@RecentlyNonNull Map<String, Float> map) {
        Preconditions.checkState(this.format == 4, "Attempting to set a float map value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        this.zzos = true;
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            hashMap.put((String) next.getKey(), MapValue.zza(((Float) next.getValue()).floatValue()));
        }
        this.zzou = hashMap;
    }

    @Deprecated
    public final void setActivity(@RecentlyNonNull String str) {
        setInt(zzko.zzo(str));
    }

    public final boolean isSet() {
        return this.zzos;
    }

    public final int getFormat() {
        return this.format;
    }

    public final int asInt() {
        boolean z = true;
        if (this.format != 1) {
            z = false;
        }
        Preconditions.checkState(z, "Value is not in int format");
        return Float.floatToRawIntBits(this.value);
    }

    public final float asFloat() {
        Preconditions.checkState(this.format == 2, "Value is not in float format");
        return this.value;
    }

    @RecentlyNonNull
    public final String asString() {
        Preconditions.checkState(this.format == 3, "Value is not in string format");
        String str = this.zzot;
        return str == null ? "" : str;
    }

    @RecentlyNullable
    public final Float getKeyValue(@RecentlyNonNull String str) {
        Preconditions.checkState(this.format == 4, "Value is not in float map format");
        Map<String, MapValue> map = this.zzou;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return Float.valueOf(this.zzou.get(str).asFloat());
    }

    @RecentlyNonNull
    public final String asActivity() {
        return zzko.getName(asInt());
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return false;
        }
        Value value2 = (Value) obj;
        int i = this.format;
        if (i == value2.format && this.zzos == value2.zzos) {
            if (i != 1) {
                if (i == 3) {
                    return Objects.equal(this.zzot, value2.zzot);
                }
                if (i == 4) {
                    return Objects.equal(this.zzou, value2.zzou);
                }
                if (i == 5) {
                    return Arrays.equals(this.zzov, value2.zzov);
                }
                if (i == 6) {
                    return Arrays.equals(this.zzow, value2.zzow);
                }
                if (i != 7) {
                    return this.value == value2.value;
                }
                return Arrays.equals(this.zzox, value2.zzox);
            } else if (asInt() == value2.asInt()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.value), this.zzot, this.zzou, this.zzov, this.zzow, this.zzox);
    }

    @RecentlyNonNull
    public final String toString() {
        String dump;
        if (!this.zzos) {
            return "unset";
        }
        switch (this.format) {
            case 1:
                return Integer.toString(asInt());
            case 2:
                return Float.toString(this.value);
            case 3:
                String str = this.zzot;
                if (str == null) {
                    return "";
                }
                return str;
            case 4:
                Map<String, MapValue> map = this.zzou;
                if (map == null) {
                    return "";
                }
                return new TreeMap(map).toString();
            case 5:
                return Arrays.toString(this.zzov);
            case 6:
                return Arrays.toString(this.zzow);
            case 7:
                byte[] bArr = this.zzox;
                if (bArr == null || (dump = HexDumpUtils.dump(bArr, 0, bArr.length, false)) == null) {
                    return "";
                }
                return dump;
            default:
                return "unknown";
        }
    }

    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        Bundle bundle;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getFormat());
        SafeParcelWriter.writeBoolean(parcel, 2, isSet());
        SafeParcelWriter.writeFloat(parcel, 3, this.value);
        SafeParcelWriter.writeString(parcel, 4, this.zzot, false);
        Map<String, MapValue> map = this.zzou;
        if (map == null) {
            bundle = null;
        } else {
            Bundle bundle2 = new Bundle(map.size());
            for (Map.Entry next : this.zzou.entrySet()) {
                bundle2.putParcelable((String) next.getKey(), (Parcelable) next.getValue());
            }
            bundle = bundle2;
        }
        SafeParcelWriter.writeBundle(parcel, 5, bundle, false);
        SafeParcelWriter.writeIntArray(parcel, 6, this.zzov, false);
        SafeParcelWriter.writeFloatArray(parcel, 7, this.zzow, false);
        SafeParcelWriter.writeByteArray(parcel, 8, this.zzox, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
