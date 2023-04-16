package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "HintRequestCreator")
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class HintRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<HintRequest> CREATOR = new zzj();
    @SafeParcelable.Field(getter = "getAccountTypes", mo19514id = 4)
    private final String[] zzab;
    @SafeParcelable.Field(getter = "isIdTokenRequested", mo19514id = 5)
    private final boolean zzae;
    @SafeParcelable.Field(getter = "getServerClientId", mo19514id = 6)
    @Nullable
    private final String zzaf;
    @SafeParcelable.Field(getter = "getIdTokenNonce", mo19514id = 7)
    @Nullable
    private final String zzag;
    @SafeParcelable.Field(getter = "getHintPickerConfig", mo19514id = 1)
    private final CredentialPickerConfig zzai;
    @SafeParcelable.Field(getter = "isEmailAddressIdentifierSupported", mo19514id = 2)
    private final boolean zzaj;
    @SafeParcelable.Field(getter = "isPhoneNumberIdentifierSupported", mo19514id = 3)
    private final boolean zzak;
    @SafeParcelable.Field(mo19514id = 1000)
    private final int zzv;

    @SafeParcelable.Constructor
    HintRequest(@SafeParcelable.Param(mo19517id = 1000) int i, @SafeParcelable.Param(mo19517id = 1) CredentialPickerConfig credentialPickerConfig, @SafeParcelable.Param(mo19517id = 2) boolean z, @SafeParcelable.Param(mo19517id = 3) boolean z2, @SafeParcelable.Param(mo19517id = 4) String[] strArr, @SafeParcelable.Param(mo19517id = 5) boolean z3, @SafeParcelable.Param(mo19517id = 6) @Nullable String str, @SafeParcelable.Param(mo19517id = 7) @Nullable String str2) {
        this.zzv = i;
        this.zzai = (CredentialPickerConfig) Preconditions.checkNotNull(credentialPickerConfig);
        this.zzaj = z;
        this.zzak = z2;
        this.zzab = (String[]) Preconditions.checkNotNull(strArr);
        if (this.zzv < 2) {
            this.zzae = true;
            this.zzaf = null;
            this.zzag = null;
            return;
        }
        this.zzae = z3;
        this.zzaf = str;
        this.zzag = str2;
    }

    /* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public String[] zzab;
        /* access modifiers changed from: private */
        public boolean zzae = false;
        /* access modifiers changed from: private */
        @Nullable
        public String zzaf;
        /* access modifiers changed from: private */
        @Nullable
        public String zzag;
        /* access modifiers changed from: private */
        public CredentialPickerConfig zzai = new CredentialPickerConfig.Builder().build();
        /* access modifiers changed from: private */
        public boolean zzaj;
        /* access modifiers changed from: private */
        public boolean zzak;

        public final Builder setEmailAddressIdentifierSupported(boolean z) {
            this.zzaj = z;
            return this;
        }

        public final Builder setPhoneNumberIdentifierSupported(boolean z) {
            this.zzak = z;
            return this;
        }

        public final Builder setAccountTypes(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this.zzab = strArr;
            return this;
        }

        public final Builder setHintPickerConfig(@NonNull CredentialPickerConfig credentialPickerConfig) {
            this.zzai = (CredentialPickerConfig) Preconditions.checkNotNull(credentialPickerConfig);
            return this;
        }

        public final Builder setIdTokenRequested(boolean z) {
            this.zzae = z;
            return this;
        }

        public final Builder setServerClientId(@Nullable String str) {
            this.zzaf = str;
            return this;
        }

        public final Builder setIdTokenNonce(@Nullable String str) {
            this.zzag = str;
            return this;
        }

        public final HintRequest build() {
            if (this.zzab == null) {
                this.zzab = new String[0];
            }
            if (this.zzaj || this.zzak || this.zzab.length != 0) {
                return new HintRequest(this);
            }
            throw new IllegalStateException("At least one authentication method must be specified");
        }
    }

    private HintRequest(Builder builder) {
        this(2, builder.zzai, builder.zzaj, builder.zzak, builder.zzab, builder.zzae, builder.zzaf, builder.zzag);
    }

    @NonNull
    public final CredentialPickerConfig getHintPickerConfig() {
        return this.zzai;
    }

    public final boolean isEmailAddressIdentifierSupported() {
        return this.zzaj;
    }

    @NonNull
    public final String[] getAccountTypes() {
        return this.zzab;
    }

    public final boolean isIdTokenRequested() {
        return this.zzae;
    }

    @Nullable
    public final String getServerClientId() {
        return this.zzaf;
    }

    @Nullable
    public final String getIdTokenNonce() {
        return this.zzag;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getHintPickerConfig(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, isEmailAddressIdentifierSupported());
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzak);
        SafeParcelWriter.writeStringArray(parcel, 4, getAccountTypes(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, getIdTokenNonce(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
