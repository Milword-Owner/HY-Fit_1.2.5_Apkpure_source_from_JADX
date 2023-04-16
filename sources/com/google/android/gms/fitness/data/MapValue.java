package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ShowFirstParty
@SafeParcelable.Class(creator = "MapValueCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class MapValue extends AbstractSafeParcelable implements ReflectedParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<MapValue> CREATOR = new zzy();
    @SafeParcelable.Field(getter = "getFormat", mo19514id = 1)
    private final int format;
    @SafeParcelable.Field(getter = "getValue", mo19514id = 2)
    private final float value;

    @SafeParcelable.Constructor
    public MapValue(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) float f) {
        this.format = i;
        this.value = f;
    }

    @RecentlyNonNull
    public static MapValue zza(float f) {
        return new MapValue(2, f);
    }

    public final float asFloat() {
        Preconditions.checkState(this.format == 2, "Value is not in float format");
        return this.value;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MapValue)) {
            return false;
        }
        MapValue mapValue = (MapValue) obj;
        int i = this.format;
        if (i == mapValue.format) {
            if (i != 2) {
                return this.value == mapValue.value;
            }
            if (asFloat() == mapValue.asFloat()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (int) this.value;
    }

    @RecentlyNonNull
    public String toString() {
        if (this.format != 2) {
            return "unknown";
        }
        return Float.toString(asFloat());
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.format);
        SafeParcelWriter.writeFloat(parcel, 2, this.value);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
