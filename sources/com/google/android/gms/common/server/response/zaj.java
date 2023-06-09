package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMappingDictionaryCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class zaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zaj> CREATOR = new zao();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int zali;
    private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zarj;
    @SafeParcelable.Field(getter = "getSerializedDictionary", mo19514id = 2)
    private final ArrayList<zam> zark;
    @SafeParcelable.Field(getter = "getRootClassName", mo19514id = 3)
    private final String zarl;

    @SafeParcelable.Constructor
    zaj(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) ArrayList<zam> arrayList, @SafeParcelable.Param(mo19517id = 3) String str) {
        this.zali = i;
        this.zark = null;
        HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> hashMap = new HashMap<>();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            zam zam = arrayList.get(i2);
            String str2 = zam.className;
            HashMap hashMap2 = new HashMap();
            int size2 = zam.zaro.size();
            for (int i3 = 0; i3 < size2; i3++) {
                zal zal = zam.zaro.get(i3);
                hashMap2.put(zal.zarm, zal.zarn);
            }
            hashMap.put(str2, hashMap2);
        }
        this.zarj = hashMap;
        this.zarl = (String) Preconditions.checkNotNull(str);
        zacp();
    }

    public final void zacp() {
        for (String str : this.zarj.keySet()) {
            Map map = this.zarj.get(str);
            for (String str2 : map.keySet()) {
                ((FastJsonResponse.Field) map.get(str2)).zaa(this);
            }
        }
    }

    public final void zacq() {
        for (String next : this.zarj.keySet()) {
            Map map = this.zarj.get(next);
            HashMap hashMap = new HashMap();
            for (String str : map.keySet()) {
                hashMap.put(str, ((FastJsonResponse.Field) map.get(str)).zacj());
            }
            this.zarj.put(next, hashMap);
        }
    }

    public zaj(Class<? extends FastJsonResponse> cls) {
        this.zali = 1;
        this.zark = null;
        this.zarj = new HashMap<>();
        this.zarl = cls.getCanonicalName();
    }

    public final void zaa(Class<? extends FastJsonResponse> cls, Map<String, FastJsonResponse.Field<?, ?>> map) {
        this.zarj.put(cls.getCanonicalName(), map);
    }

    public final Map<String, FastJsonResponse.Field<?, ?>> zai(String str) {
        return this.zarj.get(str);
    }

    public final boolean zaa(Class<? extends FastJsonResponse> cls) {
        return this.zarj.containsKey(cls.getCanonicalName());
    }

    public final String zacr() {
        return this.zarl;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String next : this.zarj.keySet()) {
            sb.append(next);
            sb.append(":\n");
            Map map = this.zarj.get(next);
            for (String str : map.keySet()) {
                sb.append("  ");
                sb.append(str);
                sb.append(": ");
                sb.append(map.get(str));
            }
        }
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zali);
        ArrayList arrayList = new ArrayList();
        for (String next : this.zarj.keySet()) {
            arrayList.add(new zam(next, this.zarj.get(next)));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.writeString(parcel, 3, this.zarl, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
