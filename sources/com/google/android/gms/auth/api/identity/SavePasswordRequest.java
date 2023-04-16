package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SavePasswordRequestCreator")
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public class SavePasswordRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SavePasswordRequest> CREATOR = new zzj();
    @SafeParcelable.Field(getter = "getSessionId", mo19514id = 2)
    @Nullable
    private final String zzau;
    @SafeParcelable.Field(getter = "getSignInPassword", mo19514id = 1)
    private final SignInPassword zzbe;

    /* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
    public static final class Builder {
        @Nullable
        private String zzau;
        private SignInPassword zzbe;

        public final Builder setSignInPassword(@NonNull SignInPassword signInPassword) {
            this.zzbe = signInPassword;
            return this;
        }

        public final Builder zzg(@NonNull String str) {
            this.zzau = str;
            return this;
        }

        public final SavePasswordRequest build() {
            return new SavePasswordRequest(this.zzbe, this.zzau);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zzc(SavePasswordRequest savePasswordRequest) {
        Preconditions.checkNotNull(savePasswordRequest);
        Builder signInPassword = builder().setSignInPassword(savePasswordRequest.getSignInPassword());
        String str = savePasswordRequest.zzau;
        if (str != null) {
            signInPassword.zzg(str);
        }
        return signInPassword;
    }

    @SafeParcelable.Constructor
    SavePasswordRequest(@SafeParcelable.Param(mo19517id = 1) SignInPassword signInPassword, @SafeParcelable.Param(mo19517id = 2) @Nullable String str) {
        this.zzbe = (SignInPassword) Preconditions.checkNotNull(signInPassword);
        this.zzau = str;
    }

    public SignInPassword getSignInPassword() {
        return this.zzbe;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzbe, this.zzau);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SavePasswordRequest)) {
            return false;
        }
        SavePasswordRequest savePasswordRequest = (SavePasswordRequest) obj;
        if (!Objects.equal(this.zzbe, savePasswordRequest.zzbe) || !Objects.equal(this.zzau, savePasswordRequest.zzau)) {
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getSignInPassword(), i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzau, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
