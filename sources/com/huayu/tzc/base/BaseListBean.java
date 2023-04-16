package com.huayu.tzc.base;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0002HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0002HÆ\u0003JG\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0002HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020&HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\b\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\t\u001a\u00020\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006'"}, mo21895d2 = {"Lcom/huayu/tzc/base/BaseListBean;", "T", "", "footer", "page", "", "rows", "", "total", "totalpages", "(Ljava/lang/Object;ILjava/util/List;ILjava/lang/Object;)V", "getFooter", "()Ljava/lang/Object;", "setFooter", "(Ljava/lang/Object;)V", "getPage", "()I", "setPage", "(I)V", "getRows", "()Ljava/util/List;", "setRows", "(Ljava/util/List;)V", "getTotal", "setTotal", "getTotalpages", "setTotalpages", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseListBean.kt */
public final class BaseListBean<T> {
    @NotNull
    private Object footer;
    private int page;
    @NotNull
    private List<? extends T> rows;
    private int total;
    @NotNull
    private Object totalpages;

    public static /* synthetic */ BaseListBean copy$default(BaseListBean baseListBean, Object obj, int i, List<? extends T> list, int i2, Object obj2, int i3, Object obj3) {
        if ((i3 & 1) != 0) {
            obj = baseListBean.footer;
        }
        if ((i3 & 2) != 0) {
            i = baseListBean.page;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            list = baseListBean.rows;
        }
        List<? extends T> list2 = list;
        if ((i3 & 8) != 0) {
            i2 = baseListBean.total;
        }
        int i5 = i2;
        if ((i3 & 16) != 0) {
            obj2 = baseListBean.totalpages;
        }
        return baseListBean.copy(obj, i4, list2, i5, obj2);
    }

    @NotNull
    public final Object component1() {
        return this.footer;
    }

    public final int component2() {
        return this.page;
    }

    @NotNull
    public final List<T> component3() {
        return this.rows;
    }

    public final int component4() {
        return this.total;
    }

    @NotNull
    public final Object component5() {
        return this.totalpages;
    }

    @NotNull
    public final BaseListBean<T> copy(@NotNull Object obj, int i, @NotNull List<? extends T> list, int i2, @NotNull Object obj2) {
        Intrinsics.checkParameterIsNotNull(obj, "footer");
        Intrinsics.checkParameterIsNotNull(list, "rows");
        Intrinsics.checkParameterIsNotNull(obj2, "totalpages");
        return new BaseListBean(obj, i, list, i2, obj2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseListBean)) {
            return false;
        }
        BaseListBean baseListBean = (BaseListBean) obj;
        return Intrinsics.areEqual(this.footer, baseListBean.footer) && this.page == baseListBean.page && Intrinsics.areEqual((Object) this.rows, (Object) baseListBean.rows) && this.total == baseListBean.total && Intrinsics.areEqual(this.totalpages, baseListBean.totalpages);
    }

    public int hashCode() {
        Object obj = this.footer;
        int i = 0;
        int hashCode = (((obj != null ? obj.hashCode() : 0) * 31) + this.page) * 31;
        List<? extends T> list = this.rows;
        int hashCode2 = (((hashCode + (list != null ? list.hashCode() : 0)) * 31) + this.total) * 31;
        Object obj2 = this.totalpages;
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        return "BaseListBean(footer=" + this.footer + ", page=" + this.page + ", rows=" + this.rows + ", total=" + this.total + ", totalpages=" + this.totalpages + ")";
    }

    public BaseListBean(@NotNull Object obj, int i, @NotNull List<? extends T> list, int i2, @NotNull Object obj2) {
        Intrinsics.checkParameterIsNotNull(obj, "footer");
        Intrinsics.checkParameterIsNotNull(list, "rows");
        Intrinsics.checkParameterIsNotNull(obj2, "totalpages");
        this.footer = obj;
        this.page = i;
        this.rows = list;
        this.total = i2;
        this.totalpages = obj2;
    }

    @NotNull
    public final Object getFooter() {
        return this.footer;
    }

    public final void setFooter(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "<set-?>");
        this.footer = obj;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @NotNull
    public final List<T> getRows() {
        return this.rows;
    }

    public final void setRows(@NotNull List<? extends T> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.rows = list;
    }

    public final int getTotal() {
        return this.total;
    }

    public final void setTotal(int i) {
        this.total = i;
    }

    @NotNull
    public final Object getTotalpages() {
        return this.totalpages;
    }

    public final void setTotalpages(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "<set-?>");
        this.totalpages = obj;
    }
}
