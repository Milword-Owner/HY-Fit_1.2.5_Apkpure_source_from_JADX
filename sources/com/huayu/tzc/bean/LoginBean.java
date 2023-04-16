package com.huayu.tzc.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\bHÖ\u0001J\b\u0010\u001b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, mo21895d2 = {"Lcom/huayu/tzc/bean/LoginBean;", "", "createdate", "", "email", "expiredate", "token", "tokenid", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getCreatedate", "()Ljava/lang/String;", "getEmail", "getExpiredate", "getToken", "getTokenid", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: LoginBean.kt */
public final class LoginBean {
    @NotNull
    private final String createdate;
    @NotNull
    private final String email;
    @NotNull
    private final String expiredate;
    @NotNull
    private final String token;
    private final int tokenid;

    public static /* synthetic */ LoginBean copy$default(LoginBean loginBean, String str, String str2, String str3, String str4, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = loginBean.createdate;
        }
        if ((i2 & 2) != 0) {
            str2 = loginBean.email;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = loginBean.expiredate;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            str4 = loginBean.token;
        }
        String str7 = str4;
        if ((i2 & 16) != 0) {
            i = loginBean.tokenid;
        }
        return loginBean.copy(str, str5, str6, str7, i);
    }

    @NotNull
    public final String component1() {
        return this.createdate;
    }

    @NotNull
    public final String component2() {
        return this.email;
    }

    @NotNull
    public final String component3() {
        return this.expiredate;
    }

    @NotNull
    public final String component4() {
        return this.token;
    }

    public final int component5() {
        return this.tokenid;
    }

    @NotNull
    public final LoginBean copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, int i) {
        Intrinsics.checkParameterIsNotNull(str, "createdate");
        Intrinsics.checkParameterIsNotNull(str2, "email");
        Intrinsics.checkParameterIsNotNull(str3, "expiredate");
        Intrinsics.checkParameterIsNotNull(str4, "token");
        return new LoginBean(str, str2, str3, str4, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoginBean)) {
            return false;
        }
        LoginBean loginBean = (LoginBean) obj;
        return Intrinsics.areEqual((Object) this.createdate, (Object) loginBean.createdate) && Intrinsics.areEqual((Object) this.email, (Object) loginBean.email) && Intrinsics.areEqual((Object) this.expiredate, (Object) loginBean.expiredate) && Intrinsics.areEqual((Object) this.token, (Object) loginBean.token) && this.tokenid == loginBean.tokenid;
    }

    public int hashCode() {
        String str = this.createdate;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.email;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.expiredate;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.token;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return ((hashCode3 + i) * 31) + this.tokenid;
    }

    public LoginBean(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, int i) {
        Intrinsics.checkParameterIsNotNull(str, "createdate");
        Intrinsics.checkParameterIsNotNull(str2, "email");
        Intrinsics.checkParameterIsNotNull(str3, "expiredate");
        Intrinsics.checkParameterIsNotNull(str4, "token");
        this.createdate = str;
        this.email = str2;
        this.expiredate = str3;
        this.token = str4;
        this.tokenid = i;
    }

    @NotNull
    public final String getCreatedate() {
        return this.createdate;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    @NotNull
    public final String getExpiredate() {
        return this.expiredate;
    }

    @NotNull
    public final String getToken() {
        return this.token;
    }

    public final int getTokenid() {
        return this.tokenid;
    }

    @NotNull
    public String toString() {
        return "LoginBean(createdate='" + this.createdate + "', email='" + this.email + "', expiredate='" + this.expiredate + "', token='" + this.token + "', tokenid=" + this.tokenid + ')';
    }
}
