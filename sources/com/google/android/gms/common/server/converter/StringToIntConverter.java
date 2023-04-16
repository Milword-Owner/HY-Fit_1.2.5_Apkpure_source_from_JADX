package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

@KeepForSdk
@SafeParcelable.Class(creator = "StringToIntConverterCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {
    public static final Parcelable.Creator<StringToIntConverter> CREATOR = new zad();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int zali;
    private final HashMap<String, Integer> zaqc;
    private final SparseArray<String> zaqd;
    @SafeParcelable.Field(getter = "getSerializedMap", mo19514id = 2)
    private final ArrayList<zaa> zaqe;

    @SafeParcelable.Constructor
    StringToIntConverter(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) ArrayList<zaa> arrayList) {
        this.zali = i;
        this.zaqc = new HashMap<>();
        this.zaqd = new SparseArray<>();
        this.zaqe = null;
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            i2++;
            zaa zaa2 = (zaa) obj;
            add(zaa2.zaqa, zaa2.zaqb);
        }
    }

    public final int zach() {
        return 7;
    }

    public final int zaci() {
        return 0;
    }

    @SafeParcelable.Class(creator = "StringToIntConverterEntryCreator")
    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    public static final class zaa extends AbstractSafeParcelable {
        public static final Parcelable.Creator<zaa> CREATOR = new zac();
        @SafeParcelable.VersionField(mo19520id = 1)
        private final int versionCode;
        @SafeParcelable.Field(mo19514id = 2)
        final String zaqa;
        @SafeParcelable.Field(mo19514id = 3)
        final int zaqb;

        @SafeParcelable.Constructor
        zaa(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) String str, @SafeParcelable.Param(mo19517id = 3) int i2) {
            this.versionCode = i;
            this.zaqa = str;
            this.zaqb = i2;
        }

        zaa(String str, int i) {
            this.versionCode = 1;
            this.zaqa = str;
            this.zaqb = i;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
            SafeParcelWriter.writeString(parcel, 2, this.zaqa, false);
            SafeParcelWriter.writeInt(parcel, 3, this.zaqb);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @KeepForSdk
    public StringToIntConverter() {
        this.zali = 1;
        this.zaqc = new HashMap<>();
        this.zaqd = new SparseArray<>();
        this.zaqe = null;
    }

    @KeepForSdk
    public final StringToIntConverter add(String str, int i) {
        this.zaqc.put(str, Integer.valueOf(i));
        this.zaqd.put(i, str);
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zali);
        ArrayList arrayList = new ArrayList();
        for (String next : this.zaqc.keySet()) {
            arrayList.add(new zaa(next, this.zaqc.get(next).intValue()));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ Object convertBack(Object obj) {
        String str = this.zaqd.get(((Integer) obj).intValue());
        return (str != null || !this.zaqc.containsKey("gms_unknown")) ? str : "gms_unknown";
    }

    public final /* synthetic */ Object convert(Object obj) {
        Integer num = this.zaqc.get((String) obj);
        return num == null ? this.zaqc.get("gms_unknown") : num;
    }
}
