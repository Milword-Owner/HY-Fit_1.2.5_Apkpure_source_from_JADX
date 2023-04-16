package com.huayu.tzc.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BC\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010\fR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u0004R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006#"}, mo21895d2 = {"Lcom/huayu/tzc/bean/TzContent;", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "itemType", "", "(I)V", "content", "", "unit", "value", "", "color", "title", "(ILjava/lang/String;Ljava/lang/String;FLjava/lang/String;Ljava/lang/String;)V", "getColor", "()Ljava/lang/String;", "setColor", "(Ljava/lang/String;)V", "getContent", "setContent", "isShow", "", "()Z", "setShow", "(Z)V", "getItemType", "()I", "setItemType", "getTitle", "setTitle", "getUnit", "setUnit", "getValue", "()F", "setValue", "(F)V", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: TzContent.kt */
public final class TzContent implements MultiItemEntity {
    @Nullable
    private String color;
    @Nullable
    private String content;
    private boolean isShow;
    private int itemType;
    @Nullable
    private String title;
    @Nullable
    private String unit;
    private float value;

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    @Nullable
    public final String getContent() {
        return this.content;
    }

    public final void setContent(@Nullable String str) {
        this.content = str;
    }

    @Nullable
    public final String getUnit() {
        return this.unit;
    }

    public final void setUnit(@Nullable String str) {
        this.unit = str;
    }

    public final float getValue() {
        return this.value;
    }

    public final void setValue(float f) {
        this.value = f;
    }

    @Nullable
    public final String getColor() {
        return this.color;
    }

    public final void setColor(@Nullable String str) {
        this.color = str;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    public final boolean isShow() {
        return this.isShow;
    }

    public final void setShow(boolean z) {
        this.isShow = z;
    }

    public TzContent(int i) {
        setItemType(i);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ TzContent(int r5, java.lang.String r6, java.lang.String r7, float r8, java.lang.String r9, java.lang.String r10, int r11, kotlin.jvm.internal.DefaultConstructorMarker r12) {
        /*
            r4 = this;
            r12 = r11 & 1
            if (r12 == 0) goto L_0x0005
            r5 = 1
        L_0x0005:
            r12 = r11 & 2
            java.lang.String r0 = ""
            if (r12 == 0) goto L_0x000d
            r12 = r0
            goto L_0x000e
        L_0x000d:
            r12 = r6
        L_0x000e:
            r6 = r11 & 4
            if (r6 == 0) goto L_0x0014
            r1 = r0
            goto L_0x0015
        L_0x0014:
            r1 = r7
        L_0x0015:
            r6 = r11 & 8
            if (r6 == 0) goto L_0x001c
            r8 = 0
            r2 = 0
            goto L_0x001d
        L_0x001c:
            r2 = r8
        L_0x001d:
            r6 = r11 & 16
            if (r6 == 0) goto L_0x0023
            r3 = r0
            goto L_0x0024
        L_0x0023:
            r3 = r9
        L_0x0024:
            r6 = r11 & 32
            if (r6 == 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            r0 = r10
        L_0x002a:
            r6 = r4
            r7 = r5
            r8 = r12
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huayu.tzc.bean.TzContent.<init>(int, java.lang.String, java.lang.String, float, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public TzContent(int i, @NotNull String str, @NotNull String str2, float f, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "content");
        Intrinsics.checkParameterIsNotNull(str2, "unit");
        Intrinsics.checkParameterIsNotNull(str3, "color");
        Intrinsics.checkParameterIsNotNull(str4, "title");
        setItemType(i);
        this.content = str;
        this.unit = str2;
        this.value = f;
        this.color = str3;
        this.title = str4;
    }
}
