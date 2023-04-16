package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SafeParcelable.Class(creator = "CredentialPickerConfigCreator")
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class CredentialPickerConfig extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<CredentialPickerConfig> CREATOR = new zze();
    @SafeParcelable.Field(getter = "shouldShowCancelButton", mo19514id = 2)
    private final boolean mShowCancelButton;
    @SafeParcelable.Field(mo19514id = 1000)
    private final int zzv;
    @SafeParcelable.Field(getter = "shouldShowAddAccountButton", mo19514id = 1)
    private final boolean zzw;
    @SafeParcelable.Field(getter = "isForNewAccount", mo19514id = 3)
    @Deprecated
    private final boolean zzx;
    @SafeParcelable.Field(getter = "getPromptInternalId", mo19514id = 4)
    private final int zzy;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
    public @interface Prompt {
        public static final int CONTINUE = 1;
        public static final int SIGN_IN = 2;
        public static final int SIGN_UP = 3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: int} */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
    /* Code decompiled incorrectly, please refer to instructions dump. */
    CredentialPickerConfig(@com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 1000) int r2, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 1) boolean r3, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 2) boolean r4, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 3) boolean r5, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 4) int r6) {
        /*
            r1 = this;
            r1.<init>()
            r1.zzv = r2
            r1.zzw = r3
            r1.mShowCancelButton = r4
            r3 = 1
            r4 = 3
            r0 = 2
            if (r2 >= r0) goto L_0x0016
            r1.zzx = r5
            if (r5 == 0) goto L_0x0013
            r3 = 3
        L_0x0013:
            r1.zzy = r3
            return
        L_0x0016:
            if (r6 != r4) goto L_0x0019
            goto L_0x001a
        L_0x0019:
            r3 = 0
        L_0x001a:
            r1.zzx = r3
            r1.zzy = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.credentials.CredentialPickerConfig.<init>(int, boolean, boolean, boolean, int):void");
    }

    /* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public boolean mShowCancelButton = true;
        /* access modifiers changed from: private */
        public boolean zzw = false;
        /* access modifiers changed from: private */
        public int zzz = 1;

        public Builder setShowAddAccountButton(boolean z) {
            this.zzw = z;
            return this;
        }

        public Builder setShowCancelButton(boolean z) {
            this.mShowCancelButton = z;
            return this;
        }

        public Builder setPrompt(int i) {
            this.zzz = i;
            return this;
        }

        @Deprecated
        public Builder setForNewAccount(boolean z) {
            this.zzz = z ? 3 : 1;
            return this;
        }

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig(this);
        }
    }

    private CredentialPickerConfig(Builder builder) {
        this(2, builder.zzw, builder.mShowCancelButton, false, builder.zzz);
    }

    public final boolean shouldShowAddAccountButton() {
        return this.zzw;
    }

    public final boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    @Deprecated
    public final boolean isForNewAccount() {
        return this.zzy == 3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, shouldShowAddAccountButton());
        SafeParcelWriter.writeBoolean(parcel, 2, shouldShowCancelButton());
        SafeParcelWriter.writeBoolean(parcel, 3, isForNewAccount());
        SafeParcelWriter.writeInt(parcel, 4, this.zzy);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
