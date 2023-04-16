package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzp implements Parcelable.Creator<DataSourcesRequest> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataSourcesRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<DataType> arrayList = null;
        ArrayList<Integer> arrayList2 = null;
        IBinder iBinder = null;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataType.CREATOR);
            } else if (fieldId == 2) {
                arrayList2 = SafeParcelReader.createIntegerList(parcel, readHeader);
            } else if (fieldId == 3) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataSourcesRequest((List<DataType>) arrayList, (List<Integer>) arrayList2, z, iBinder);
    }
}
