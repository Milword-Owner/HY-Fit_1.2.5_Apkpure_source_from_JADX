package com.huayu.tzc.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006\u001f"}, mo21895d2 = {"Lcom/huayu/tzc/bean/Version;", "", "downurl", "", "dupdatecontent", "productid", "", "version", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getDownurl", "()Ljava/lang/String;", "setDownurl", "(Ljava/lang/String;)V", "getDupdatecontent", "setDupdatecontent", "getProductid", "()I", "setProductid", "(I)V", "getVersion", "setVersion", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Version.kt */
public final class Version {
    @NotNull
    private String downurl;
    @NotNull
    private String dupdatecontent;
    private int productid;
    @NotNull
    private String version;

    public static /* synthetic */ Version copy$default(Version version2, String str, String str2, int i, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = version2.downurl;
        }
        if ((i2 & 2) != 0) {
            str2 = version2.dupdatecontent;
        }
        if ((i2 & 4) != 0) {
            i = version2.productid;
        }
        if ((i2 & 8) != 0) {
            str3 = version2.version;
        }
        return version2.copy(str, str2, i, str3);
    }

    @NotNull
    public final String component1() {
        return this.downurl;
    }

    @NotNull
    public final String component2() {
        return this.dupdatecontent;
    }

    public final int component3() {
        return this.productid;
    }

    @NotNull
    public final String component4() {
        return this.version;
    }

    @NotNull
    public final Version copy(@NotNull String str, @NotNull String str2, int i, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "downurl");
        Intrinsics.checkParameterIsNotNull(str2, "dupdatecontent");
        Intrinsics.checkParameterIsNotNull(str3, "version");
        return new Version(str, str2, i, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Version)) {
            return false;
        }
        Version version2 = (Version) obj;
        return Intrinsics.areEqual((Object) this.downurl, (Object) version2.downurl) && Intrinsics.areEqual((Object) this.dupdatecontent, (Object) version2.dupdatecontent) && this.productid == version2.productid && Intrinsics.areEqual((Object) this.version, (Object) version2.version);
    }

    public int hashCode() {
        String str = this.downurl;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.dupdatecontent;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.productid) * 31;
        String str3 = this.version;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        return "Version(downurl=" + this.downurl + ", dupdatecontent=" + this.dupdatecontent + ", productid=" + this.productid + ", version=" + this.version + ")";
    }

    public Version(@NotNull String str, @NotNull String str2, int i, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "downurl");
        Intrinsics.checkParameterIsNotNull(str2, "dupdatecontent");
        Intrinsics.checkParameterIsNotNull(str3, "version");
        this.downurl = str;
        this.dupdatecontent = str2;
        this.productid = i;
        this.version = str3;
    }

    @NotNull
    public final String getDownurl() {
        return this.downurl;
    }

    public final void setDownurl(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.downurl = str;
    }

    @NotNull
    public final String getDupdatecontent() {
        return this.dupdatecontent;
    }

    public final void setDupdatecontent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.dupdatecontent = str;
    }

    public final int getProductid() {
        return this.productid;
    }

    public final void setProductid(int i) {
        this.productid = i;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    public final void setVersion(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.version = str;
    }
}
