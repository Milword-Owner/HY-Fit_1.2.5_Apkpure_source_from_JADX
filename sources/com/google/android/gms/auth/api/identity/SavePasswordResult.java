package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SavePasswordResultCreator")
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public class SavePasswordResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SavePasswordResult> CREATOR = new zzk();
    @SafeParcelable.Field(getter = "getPendingIntent", mo19514id = 1)
    private final PendingIntent zzbc;

    @SafeParcelable.Constructor
    public SavePasswordResult(@SafeParcelable.Param(mo19517id = 1) PendingIntent pendingIntent) {
        this.zzbc = (PendingIntent) Preconditions.checkNotNull(pendingIntent);
    }

    public PendingIntent getPendingIntent() {
        return this.zzbc;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzbc);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SavePasswordResult)) {
            return false;
        }
        return Objects.equal(this.zzbc, ((SavePasswordResult) obj).zzbc);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getPendingIntent(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
