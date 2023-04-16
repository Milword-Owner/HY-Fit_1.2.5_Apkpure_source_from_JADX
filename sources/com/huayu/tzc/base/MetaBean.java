package com.huayu.tzc.base;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, mo21895d2 = {"Lcom/huayu/tzc/base/MetaBean;", "", "success", "", "message", "", "(ZLjava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "getSuccess", "()Z", "setSuccess", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseBean.kt */
public final class MetaBean {
    @Nullable
    private String message;
    private boolean success;

    public static /* synthetic */ MetaBean copy$default(MetaBean metaBean, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = metaBean.success;
        }
        if ((i & 2) != 0) {
            str = metaBean.message;
        }
        return metaBean.copy(z, str);
    }

    public final boolean component1() {
        return this.success;
    }

    @Nullable
    public final String component2() {
        return this.message;
    }

    @NotNull
    public final MetaBean copy(boolean z, @Nullable String str) {
        return new MetaBean(z, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetaBean)) {
            return false;
        }
        MetaBean metaBean = (MetaBean) obj;
        return this.success == metaBean.success && Intrinsics.areEqual((Object) this.message, (Object) metaBean.message);
    }

    public int hashCode() {
        boolean z = this.success;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        String str = this.message;
        return i + (str != null ? str.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "MetaBean(success=" + this.success + ", message=" + this.message + ")";
    }

    public MetaBean(boolean z, @Nullable String str) {
        this.success = z;
        this.message = str;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final void setSuccess(boolean z) {
        this.success = z;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(@Nullable String str) {
        this.message = str;
    }
}
