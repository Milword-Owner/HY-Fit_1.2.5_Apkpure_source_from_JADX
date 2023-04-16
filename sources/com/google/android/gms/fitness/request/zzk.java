package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzk implements Parcelable.Creator<DataDeleteRequest> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataDeleteRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        ArrayList<DataSource> arrayList = null;
        ArrayList<DataType> arrayList2 = null;
        ArrayList<Session> arrayList3 = null;
        IBinder iBinder = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 2:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 3:
                    arrayList = SafeParcelReader.createTypedList(parcel2, readHeader, DataSource.CREATOR);
                    break;
                case 4:
                    arrayList2 = SafeParcelReader.createTypedList(parcel2, readHeader, DataType.CREATOR);
                    break;
                case 5:
                    arrayList3 = SafeParcelReader.createTypedList(parcel2, readHeader, Session.CREATOR);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 7:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 8:
                    iBinder = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                case 10:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 11:
                    z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new DataDeleteRequest(j, j2, (List<DataSource>) arrayList, (List<DataType>) arrayList2, (List<Session>) arrayList3, z, z2, z3, z4, iBinder);
    }
}
