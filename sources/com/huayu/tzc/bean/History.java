package com.huayu.tzc.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\nB1\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\t\u0010!\u001a\u00020\tHÆ\u0003J\t\u0010\"\u001a\u00020\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\fHÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003J;\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001J\u0013\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020\u0004HÖ\u0001J\t\u0010+\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\r\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006,"}, mo21895d2 = {"Lcom/huayu/tzc/bean/History;", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "()V", "itemType", "", "time", "", "(ILjava/lang/String;)V", "measure", "Lcom/huayu/tzc/bean/Measure;", "(ILcom/huayu/tzc/bean/Measure;Ljava/lang/String;)V", "show", "", "select", "(Lcom/huayu/tzc/bean/Measure;ILjava/lang/String;ZZ)V", "getItemType", "()I", "setItemType", "(I)V", "getMeasure", "()Lcom/huayu/tzc/bean/Measure;", "setMeasure", "(Lcom/huayu/tzc/bean/Measure;)V", "getSelect", "()Z", "setSelect", "(Z)V", "getShow", "setShow", "getTime", "()Ljava/lang/String;", "setTime", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "toString", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: History.kt */
public final class History implements MultiItemEntity {
    private int itemType;
    @NotNull
    private Measure measure;
    private boolean select;
    private boolean show;
    @NotNull
    private String time;

    public static /* synthetic */ History copy$default(History history, Measure measure2, int i, String str, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            measure2 = history.measure;
        }
        if ((i2 & 2) != 0) {
            i = history.getItemType();
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            str = history.time;
        }
        String str2 = str;
        if ((i2 & 8) != 0) {
            z = history.show;
        }
        boolean z3 = z;
        if ((i2 & 16) != 0) {
            z2 = history.select;
        }
        return history.copy(measure2, i3, str2, z3, z2);
    }

    @NotNull
    public final Measure component1() {
        return this.measure;
    }

    public final int component2() {
        return getItemType();
    }

    @NotNull
    public final String component3() {
        return this.time;
    }

    public final boolean component4() {
        return this.show;
    }

    public final boolean component5() {
        return this.select;
    }

    @NotNull
    public final History copy(@NotNull Measure measure2, int i, @NotNull String str, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(measure2, "measure");
        Intrinsics.checkParameterIsNotNull(str, "time");
        return new History(measure2, i, str, z, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof History)) {
            return false;
        }
        History history = (History) obj;
        return Intrinsics.areEqual((Object) this.measure, (Object) history.measure) && getItemType() == history.getItemType() && Intrinsics.areEqual((Object) this.time, (Object) history.time) && this.show == history.show && this.select == history.select;
    }

    public int hashCode() {
        Measure measure2 = this.measure;
        int i = 0;
        int hashCode = (((measure2 != null ? measure2.hashCode() : 0) * 31) + getItemType()) * 31;
        String str = this.time;
        if (str != null) {
            i = str.hashCode();
        }
        int i2 = (hashCode + i) * 31;
        boolean z = this.show;
        if (z) {
            z = true;
        }
        int i3 = (i2 + (z ? 1 : 0)) * 31;
        boolean z2 = this.select;
        if (z2) {
            z2 = true;
        }
        return i3 + (z2 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        return "History(measure=" + this.measure + ", itemType=" + getItemType() + ", time=" + this.time + ", show=" + this.show + ", select=" + this.select + ")";
    }

    public History(@NotNull Measure measure2, int i, @NotNull String str, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(measure2, "measure");
        Intrinsics.checkParameterIsNotNull(str, "time");
        this.measure = measure2;
        this.itemType = i;
        this.time = str;
        this.show = z;
        this.select = z2;
    }

    @NotNull
    public final Measure getMeasure() {
        return this.measure;
    }

    public final void setMeasure(@NotNull Measure measure2) {
        Intrinsics.checkParameterIsNotNull(measure2, "<set-?>");
        this.measure = measure2;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    @NotNull
    public final String getTime() {
        return this.time;
    }

    public final void setTime(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.time = str;
    }

    public final boolean getShow() {
        return this.show;
    }

    public final void setShow(boolean z) {
        this.show = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ History(Measure measure2, int i, String str, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(measure2, i, str, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2);
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }

    public History() {
        this(new Measure(), 0, "", false, false, 24, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public History(int i, @NotNull String str) {
        this();
        Intrinsics.checkParameterIsNotNull(str, "time");
        setItemType(i);
        this.time = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public History(int i, @NotNull Measure measure2, @NotNull String str) {
        this();
        Intrinsics.checkParameterIsNotNull(measure2, "measure");
        Intrinsics.checkParameterIsNotNull(str, "time");
        setItemType(i);
        this.measure = measure2;
        this.time = str;
    }
}
