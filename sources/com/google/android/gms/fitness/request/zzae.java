package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzae implements Parcelable.Creator<GoalsReadRequest> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoalsReadRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else if (fieldId == 2) {
                SafeParcelReader.readList(parcel, readHeader, arrayList, getClass().getClassLoader());
            } else if (fieldId == 3) {
                SafeParcelReader.readList(parcel, readHeader, arrayList2, getClass().getClassLoader());
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                SafeParcelReader.readList(parcel, readHeader, arrayList3, getClass().getClassLoader());
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GoalsReadRequest(iBinder, (List<DataType>) arrayList, (List<Integer>) arrayList2, (List<Integer>) arrayList3);
    }
}
