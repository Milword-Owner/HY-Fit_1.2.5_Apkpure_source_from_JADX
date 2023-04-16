package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzau implements Parcelable.Creator<SessionInsertRequest> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new SessionInsertRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Session session = null;
        ArrayList<DataSet> arrayList = null;
        ArrayList<DataPoint> arrayList2 = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                session = (Session) SafeParcelReader.createParcelable(parcel, readHeader, Session.CREATOR);
            } else if (fieldId == 2) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataSet.CREATOR);
            } else if (fieldId == 3) {
                arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, DataPoint.CREATOR);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SessionInsertRequest(session, (List<DataSet>) arrayList, (List<DataPoint>) arrayList2, iBinder);
    }
}
