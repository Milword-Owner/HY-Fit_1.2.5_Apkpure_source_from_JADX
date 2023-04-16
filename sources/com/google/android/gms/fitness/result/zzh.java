package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzae;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzh implements Parcelable.Creator<SessionReadResult> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new SessionReadResult[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<Session> arrayList = null;
        ArrayList<zzae> arrayList2 = null;
        Status status = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, Session.CREATOR);
            } else if (fieldId == 2) {
                arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, zzae.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                status = (Status) SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SessionReadResult(arrayList, arrayList2, status);
    }
}
