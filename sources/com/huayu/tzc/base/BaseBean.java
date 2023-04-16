package com.huayu.tzc.base;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J*\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u001e\u0010\u0003\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, mo21895d2 = {"Lcom/huayu/tzc/base/BaseBean;", "T", "", "data", "meta", "Lcom/huayu/tzc/base/MetaBean;", "(Ljava/lang/Object;Lcom/huayu/tzc/base/MetaBean;)V", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "getMeta", "()Lcom/huayu/tzc/base/MetaBean;", "setMeta", "(Lcom/huayu/tzc/base/MetaBean;)V", "component1", "component2", "copy", "(Ljava/lang/Object;Lcom/huayu/tzc/base/MetaBean;)Lcom/huayu/tzc/base/BaseBean;", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseBean.kt */
public final class BaseBean<T> {
    @Nullable
    private T data;
    @NotNull
    private MetaBean meta;

    public static /* synthetic */ BaseBean copy$default(BaseBean baseBean, T t, MetaBean metaBean, int i, Object obj) {
        if ((i & 1) != 0) {
            t = baseBean.data;
        }
        if ((i & 2) != 0) {
            metaBean = baseBean.meta;
        }
        return baseBean.copy(t, metaBean);
    }

    @Nullable
    public final T component1() {
        return this.data;
    }

    @NotNull
    public final MetaBean component2() {
        return this.meta;
    }

    @NotNull
    public final BaseBean<T> copy(@Nullable T t, @NotNull MetaBean metaBean) {
        Intrinsics.checkParameterIsNotNull(metaBean, "meta");
        return new BaseBean<>(t, metaBean);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseBean)) {
            return false;
        }
        BaseBean baseBean = (BaseBean) obj;
        return Intrinsics.areEqual((Object) this.data, (Object) baseBean.data) && Intrinsics.areEqual((Object) this.meta, (Object) baseBean.meta);
    }

    public int hashCode() {
        T t = this.data;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        MetaBean metaBean = this.meta;
        if (metaBean != null) {
            i = metaBean.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "BaseBean(data=" + this.data + ", meta=" + this.meta + ")";
    }

    public BaseBean(@Nullable T t, @NotNull MetaBean metaBean) {
        Intrinsics.checkParameterIsNotNull(metaBean, "meta");
        this.data = t;
        this.meta = metaBean;
    }

    @Nullable
    public final T getData() {
        return this.data;
    }

    public final void setData(@Nullable T t) {
        this.data = t;
    }

    @NotNull
    public final MetaBean getMeta() {
        return this.meta;
    }

    public final void setMeta(@NotNull MetaBean metaBean) {
        Intrinsics.checkParameterIsNotNull(metaBean, "<set-?>");
        this.meta = metaBean;
    }
}
