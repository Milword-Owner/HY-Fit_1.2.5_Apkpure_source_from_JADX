package com.huayu.tzc.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0012\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J*\u0010\u0015\u001a\u00020(2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u00032\b\u0010%\u001a\u0004\u0018\u00010\u0005J4\u0010\u0015\u001a\u00020(2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u00032\b\u0010%\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005JG\u0010\u0015\u001a\u00020(2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u00032\b\u0010%\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r¢\u0006\u0002\u0010)R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\t\"\u0004\b\u0015\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\t\"\u0004\b\u001e\u0010\u000bR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\t\"\u0004\b'\u0010\u000b¨\u0006*"}, mo21895d2 = {"Lcom/huayu/tzc/bean/HistoryInfo;", "", "img", "", "name", "", "(ILjava/lang/String;)V", "color", "getColor", "()Ljava/lang/String;", "setColor", "(Ljava/lang/String;)V", "colors", "", "getColors", "()[Ljava/lang/String;", "setColors", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "content", "getContent", "setContent", "getImg", "()I", "setImg", "(I)V", "level", "getLevel", "setLevel", "getName", "setName", "num", "", "getNum", "()F", "setNum", "(F)V", "state", "getState", "setState", "", "(Ljava/lang/String;FILjava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: HistoryInfo.kt */
public final class HistoryInfo {
    @Nullable
    private String color;
    @NotNull
    private String[] colors = {"#56A3FA", "#0EDAAE", "#FAD65B", "#F8A139", "#F66B89"};
    @Nullable
    private String content;
    private int img;
    private int level;
    @NotNull
    private String name;
    private float num;
    @Nullable
    private String state;

    public HistoryInfo(int i, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        this.img = i;
        this.name = str;
    }

    public final int getImg() {
        return this.img;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setImg(int i) {
        this.img = i;
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    @Nullable
    public final String getContent() {
        return this.content;
    }

    public final void setContent(@Nullable String str) {
        this.content = str;
    }

    public final float getNum() {
        return this.num;
    }

    public final void setNum(float f) {
        this.num = f;
    }

    public final int getLevel() {
        return this.level;
    }

    public final void setLevel(int i) {
        this.level = i;
    }

    @Nullable
    public final String getState() {
        return this.state;
    }

    public final void setState(@Nullable String str) {
        this.state = str;
    }

    @Nullable
    public final String getColor() {
        return this.color;
    }

    public final void setColor(@Nullable String str) {
        this.color = str;
    }

    @NotNull
    public final String[] getColors() {
        return this.colors;
    }

    public final void setColors(@NotNull String[] strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "<set-?>");
        this.colors = strArr;
    }

    public final void setContent(@Nullable String str, float f, int i, @Nullable String str2) {
        this.state = str2;
        this.level = i;
        this.content = str;
        this.num = f;
    }

    public final void setContent(@Nullable String str, float f, int i, @Nullable String str2, @Nullable String str3) {
        this.state = str2;
        this.level = i;
        this.content = str;
        this.num = f;
        this.color = str3;
    }

    public final void setContent(@Nullable String str, float f, int i, @Nullable String str2, @Nullable String str3, @NotNull String[] strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "colors");
        this.state = str2;
        this.level = i;
        this.content = str;
        this.num = f;
        this.color = str3;
        this.colors = strArr;
    }
}
