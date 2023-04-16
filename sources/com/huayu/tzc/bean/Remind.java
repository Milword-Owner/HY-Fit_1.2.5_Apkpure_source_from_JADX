package com.huayu.tzc.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0016"}, mo21895d2 = {"Lcom/huayu/tzc/bean/Remind;", "", "date", "", "isChecked", "", "(Ljava/lang/String;Z)V", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "()Z", "setChecked", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Remind.kt */
public final class Remind {
    @NotNull
    private String date;
    private boolean isChecked;

    public static /* synthetic */ Remind copy$default(Remind remind, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = remind.date;
        }
        if ((i & 2) != 0) {
            z = remind.isChecked;
        }
        return remind.copy(str, z);
    }

    @NotNull
    public final String component1() {
        return this.date;
    }

    public final boolean component2() {
        return this.isChecked;
    }

    @NotNull
    public final Remind copy(@NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "date");
        return new Remind(str, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Remind)) {
            return false;
        }
        Remind remind = (Remind) obj;
        return Intrinsics.areEqual((Object) this.date, (Object) remind.date) && this.isChecked == remind.isChecked;
    }

    public int hashCode() {
        String str = this.date;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isChecked;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    @NotNull
    public String toString() {
        return "Remind(date=" + this.date + ", isChecked=" + this.isChecked + ")";
    }

    public Remind(@NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "date");
        this.date = str;
        this.isChecked = z;
    }

    @NotNull
    public final String getDate() {
        return this.date;
    }

    public final boolean isChecked() {
        return this.isChecked;
    }

    public final void setChecked(boolean z) {
        this.isChecked = z;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.date = str;
    }
}
