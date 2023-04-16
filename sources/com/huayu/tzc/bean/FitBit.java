package com.huayu.tzc.bean;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0013\u0010\u0019\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0018HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001J\u0019\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010¨\u0006#"}, mo21895d2 = {"Lcom/huayu/tzc/bean/FitBit;", "Landroid/os/Parcelable;", "()V", "connectFitBit", "", "fitId", "", "fitToken", "(ZLjava/lang/String;Ljava/lang/String;)V", "getConnectFitBit", "()Z", "setConnectFitBit", "(Z)V", "getFitId", "()Ljava/lang/String;", "setFitId", "(Ljava/lang/String;)V", "getFitToken", "setFitToken", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
@Parcelize
/* compiled from: FitBit.kt */
public final class FitBit implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private boolean connectFitBit;
    @NotNull
    private String fitId;
    @NotNull
    private String fitToken;

    @Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new FitBit(parcel.readInt() != 0, parcel.readString(), parcel.readString());
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new FitBit[i];
        }
    }

    public static /* synthetic */ FitBit copy$default(FitBit fitBit, boolean z, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = fitBit.connectFitBit;
        }
        if ((i & 2) != 0) {
            str = fitBit.fitId;
        }
        if ((i & 4) != 0) {
            str2 = fitBit.fitToken;
        }
        return fitBit.copy(z, str, str2);
    }

    public final boolean component1() {
        return this.connectFitBit;
    }

    @NotNull
    public final String component2() {
        return this.fitId;
    }

    @NotNull
    public final String component3() {
        return this.fitToken;
    }

    @NotNull
    public final FitBit copy(boolean z, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "fitId");
        Intrinsics.checkParameterIsNotNull(str2, "fitToken");
        return new FitBit(z, str, str2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FitBit)) {
            return false;
        }
        FitBit fitBit = (FitBit) obj;
        return this.connectFitBit == fitBit.connectFitBit && Intrinsics.areEqual((Object) this.fitId, (Object) fitBit.fitId) && Intrinsics.areEqual((Object) this.fitToken, (Object) fitBit.fitToken);
    }

    public int hashCode() {
        boolean z = this.connectFitBit;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        String str = this.fitId;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.fitToken;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return hashCode + i2;
    }

    @NotNull
    public String toString() {
        return "FitBit(connectFitBit=" + this.connectFitBit + ", fitId=" + this.fitId + ", fitToken=" + this.fitToken + ")";
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeInt(this.connectFitBit ? 1 : 0);
        parcel.writeString(this.fitId);
        parcel.writeString(this.fitToken);
    }

    public FitBit(boolean z, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "fitId");
        Intrinsics.checkParameterIsNotNull(str2, "fitToken");
        this.connectFitBit = z;
        this.fitId = str;
        this.fitToken = str2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FitBit(boolean z, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, str, str2);
    }

    public final boolean getConnectFitBit() {
        return this.connectFitBit;
    }

    public final void setConnectFitBit(boolean z) {
        this.connectFitBit = z;
    }

    @NotNull
    public final String getFitId() {
        return this.fitId;
    }

    public final void setFitId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.fitId = str;
    }

    @NotNull
    public final String getFitToken() {
        return this.fitToken;
    }

    public final void setFitToken(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.fitToken = str;
    }

    public FitBit() {
        this(false, "", "");
    }
}
