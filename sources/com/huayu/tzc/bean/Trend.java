package com.huayu.tzc.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, mo21895d2 = {"Lcom/huayu/tzc/bean/Trend;", "", "recordid", "", "measuredate", "", "content", "(ILjava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getMeasuredate", "setMeasuredate", "getRecordid", "()I", "setRecordid", "(I)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Trend.kt */
public final class Trend {
    @NotNull
    private String content;
    @NotNull
    private String measuredate;
    private int recordid;

    public static /* synthetic */ Trend copy$default(Trend trend, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = trend.recordid;
        }
        if ((i2 & 2) != 0) {
            str = trend.measuredate;
        }
        if ((i2 & 4) != 0) {
            str2 = trend.content;
        }
        return trend.copy(i, str, str2);
    }

    public final int component1() {
        return this.recordid;
    }

    @NotNull
    public final String component2() {
        return this.measuredate;
    }

    @NotNull
    public final String component3() {
        return this.content;
    }

    @NotNull
    public final Trend copy(int i, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "measuredate");
        Intrinsics.checkParameterIsNotNull(str2, "content");
        return new Trend(i, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trend)) {
            return false;
        }
        Trend trend = (Trend) obj;
        return this.recordid == trend.recordid && Intrinsics.areEqual((Object) this.measuredate, (Object) trend.measuredate) && Intrinsics.areEqual((Object) this.content, (Object) trend.content);
    }

    public int hashCode() {
        int i = this.recordid * 31;
        String str = this.measuredate;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.content;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return hashCode + i2;
    }

    @NotNull
    public String toString() {
        return "Trend(recordid=" + this.recordid + ", measuredate=" + this.measuredate + ", content=" + this.content + ")";
    }

    public Trend(int i, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "measuredate");
        Intrinsics.checkParameterIsNotNull(str2, "content");
        this.recordid = i;
        this.measuredate = str;
        this.content = str2;
    }

    public final int getRecordid() {
        return this.recordid;
    }

    public final void setRecordid(int i) {
        this.recordid = i;
    }

    @NotNull
    public final String getMeasuredate() {
        return this.measuredate;
    }

    public final void setMeasuredate(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.measuredate = str;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }

    public final void setContent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }
}
