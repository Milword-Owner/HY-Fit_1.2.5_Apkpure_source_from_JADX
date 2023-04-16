package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SafeParcelable.Class(creator = "CredentialRequestCreator")
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class CredentialRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CredentialRequest> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "isPasswordLoginSupported", mo19514id = 1)
    private final boolean zzaa;
    @SafeParcelable.Field(getter = "getAccountTypes", mo19514id = 2)
    private final String[] zzab;
    @SafeParcelable.Field(getter = "getCredentialPickerConfig", mo19514id = 3)
    private final CredentialPickerConfig zzac;
    @SafeParcelable.Field(getter = "getCredentialHintPickerConfig", mo19514id = 4)
    private final CredentialPickerConfig zzad;
    @SafeParcelable.Field(getter = "isIdTokenRequested", mo19514id = 5)
    private final boolean zzae;
    @SafeParcelable.Field(getter = "getServerClientId", mo19514id = 6)
    @Nullable
    private final String zzaf;
    @SafeParcelable.Field(getter = "getIdTokenNonce", mo19514id = 7)
    @Nullable
    private final String zzag;
    @SafeParcelable.Field(getter = "getRequireUserMediation", mo19514id = 8)
    private final boolean zzah;
    @SafeParcelable.Field(mo19514id = 1000)
    private final int zzv;

    @SafeParcelable.Constructor
    CredentialRequest(@SafeParcelable.Param(mo19517id = 1000) int i, @SafeParcelable.Param(mo19517id = 1) boolean z, @SafeParcelable.Param(mo19517id = 2) String[] strArr, @SafeParcelable.Param(mo19517id = 3) @Nullable CredentialPickerConfig credentialPickerConfig, @SafeParcelable.Param(mo19517id = 4) @Nullable CredentialPickerConfig credentialPickerConfig2, @SafeParcelable.Param(mo19517id = 5) boolean z2, @SafeParcelable.Param(mo19517id = 6) @Nullable String str, @SafeParcelable.Param(mo19517id = 7) @Nullable String str2, @SafeParcelable.Param(mo19517id = 8) boolean z3) {
        this.zzv = i;
        this.zzaa = z;
        this.zzab = (String[]) Preconditions.checkNotNull(strArr);
        this.zzac = credentialPickerConfig == null ? new CredentialPickerConfig.Builder().build() : credentialPickerConfig;
        this.zzad = credentialPickerConfig2 == null ? new CredentialPickerConfig.Builder().build() : credentialPickerConfig2;
        if (i < 3) {
            this.zzae = true;
            this.zzaf = null;
            this.zzag = null;
        } else {
            this.zzae = z2;
            this.zzaf = str;
            this.zzag = str2;
        }
        this.zzah = z3;
    }

    /* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzaa;
        /* access modifiers changed from: private */
        public String[] zzab;
        /* access modifiers changed from: private */
        public CredentialPickerConfig zzac;
        /* access modifiers changed from: private */
        public CredentialPickerConfig zzad;
        /* access modifiers changed from: private */
        public boolean zzae = false;
        /* access modifiers changed from: private */
        @Nullable
        public String zzaf = null;
        /* access modifiers changed from: private */
        @Nullable
        public String zzag;
        private boolean zzah = false;

        @Deprecated
        public final Builder setSupportsPasswordLogin(boolean z) {
            return setPasswordLoginSupported(z);
        }

        public final Builder setPasswordLoginSupported(boolean z) {
            this.zzaa = z;
            return this;
        }

        public final Builder setAccountTypes(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this.zzab = strArr;
            return this;
        }

        public final Builder setCredentialPickerConfig(CredentialPickerConfig credentialPickerConfig) {
            this.zzac = credentialPickerConfig;
            return this;
        }

        public final Builder setCredentialHintPickerConfig(CredentialPickerConfig credentialPickerConfig) {
            this.zzad = credentialPickerConfig;
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

        public final CredentialRequest build() {
            if (this.zzab == null) {
                this.zzab = new String[0];
            }
            if (this.zzaa || this.zzab.length != 0) {
                return new CredentialRequest(this);
            }
            throw new IllegalStateException("At least one authentication method must be specified");
        }
    }

    private CredentialRequest(Builder builder) {
        this(4, builder.zzaa, builder.zzab, builder.zzac, builder.zzad, builder.zzae, builder.zzaf, builder.zzag, false);
    }

    @Deprecated
    public final boolean getSupportsPasswordLogin() {
        return isPasswordLoginSupported();
    }

    public final boolean isPasswordLoginSupported() {
        return this.zzaa;
    }

    @NonNull
    public final String[] getAccountTypes() {
        return this.zzab;
    }

    @NonNull
    public final Set<String> getAccountTypesSet() {
        return new HashSet(Arrays.asList(this.zzab));
    }

    @NonNull
    public final CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzac;
    }

    @NonNull
    public final CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzad;
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
        SafeParcelWriter.writeBoolean(parcel, 1, isPasswordLoginSupported());
        SafeParcelWriter.writeStringArray(parcel, 2, getAccountTypes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getCredentialPickerConfig(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getCredentialHintPickerConfig(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 5, isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, getIdTokenNonce(), false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzah);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
