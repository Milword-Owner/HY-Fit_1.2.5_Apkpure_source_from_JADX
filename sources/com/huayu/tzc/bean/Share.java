package com.huayu.tzc.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\t\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015¨\u0006 "}, mo21895d2 = {"Lcom/huayu/tzc/bean/Share;", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "itemType", "", "imgUrl", "", "(ILjava/lang/String;)V", "member", "Lcom/huayu/tzc/bean/Member;", "time", "(ILcom/huayu/tzc/bean/Member;Ljava/lang/String;)V", "historyInfo", "Lcom/huayu/tzc/bean/HistoryInfo;", "(ILcom/huayu/tzc/bean/HistoryInfo;)V", "getHistoryInfo", "()Lcom/huayu/tzc/bean/HistoryInfo;", "setHistoryInfo", "(Lcom/huayu/tzc/bean/HistoryInfo;)V", "getImgUrl", "()Ljava/lang/String;", "setImgUrl", "(Ljava/lang/String;)V", "getItemType", "()I", "setItemType", "(I)V", "getMember", "()Lcom/huayu/tzc/bean/Member;", "setMember", "(Lcom/huayu/tzc/bean/Member;)V", "getTime", "setTime", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Share.kt */
public final class Share implements MultiItemEntity {
    @Nullable
    private HistoryInfo historyInfo;
    @NotNull
    private String imgUrl = "";
    private int itemType;
    @Nullable
    private Member member;
    @NotNull
    private String time = "";

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    @Nullable
    public final HistoryInfo getHistoryInfo() {
        return this.historyInfo;
    }

    public final void setHistoryInfo(@Nullable HistoryInfo historyInfo2) {
        this.historyInfo = historyInfo2;
    }

    @NotNull
    public final String getImgUrl() {
        return this.imgUrl;
    }

    public final void setImgUrl(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.imgUrl = str;
    }

    @Nullable
    public final Member getMember() {
        return this.member;
    }

    public final void setMember(@Nullable Member member2) {
        this.member = member2;
    }

    @NotNull
    public final String getTime() {
        return this.time;
    }

    public final void setTime(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.time = str;
    }

    public Share(int i, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "imgUrl");
        setItemType(i);
        this.imgUrl = str;
    }

    public Share(int i, @NotNull Member member2, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(member2, "member");
        Intrinsics.checkParameterIsNotNull(str, "time");
        setItemType(i);
        this.member = member2;
        this.time = str;
    }

    public Share(int i, @NotNull HistoryInfo historyInfo2) {
        Intrinsics.checkParameterIsNotNull(historyInfo2, "historyInfo");
        setItemType(i);
        this.historyInfo = historyInfo2;
    }
}
