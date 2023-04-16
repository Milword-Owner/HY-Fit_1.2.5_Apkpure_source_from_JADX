package com.huayu.tzc.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b(\b\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\u0006HÆ\u0003J\t\u0010(\u001a\u00020\tHÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0006HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003JY\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u0006HÆ\u0001J\u0013\u0010-\u001a\u00020\t2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u00020\u0003HÖ\u0001J\t\u00100\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\n\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u001a\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0015\"\u0004\b!\u0010\u0017R\u001a\u0010\f\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017¨\u00061"}, mo21895d2 = {"Lcom/huayu/tzc/bean/MsgHistory;", "", "app_type", "", "chatid", "content", "", "create_date", "flag", "", "msgtype", "receive", "sender", "(IILjava/lang/String;Ljava/lang/String;ZILjava/lang/String;Ljava/lang/String;)V", "getApp_type", "()I", "setApp_type", "(I)V", "getChatid", "setChatid", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getCreate_date", "setCreate_date", "getFlag", "()Z", "setFlag", "(Z)V", "getMsgtype", "setMsgtype", "getReceive", "setReceive", "getSender", "setSender", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: MsgHistory.kt */
public final class MsgHistory {
    private int app_type;
    private int chatid;
    @NotNull
    private String content;
    @NotNull
    private String create_date;
    private boolean flag;
    private int msgtype;
    @NotNull
    private String receive;
    @NotNull
    private String sender;

    public static /* synthetic */ MsgHistory copy$default(MsgHistory msgHistory, int i, int i2, String str, String str2, boolean z, int i3, String str3, String str4, int i4, Object obj) {
        MsgHistory msgHistory2 = msgHistory;
        int i5 = i4;
        return msgHistory.copy((i5 & 1) != 0 ? msgHistory2.app_type : i, (i5 & 2) != 0 ? msgHistory2.chatid : i2, (i5 & 4) != 0 ? msgHistory2.content : str, (i5 & 8) != 0 ? msgHistory2.create_date : str2, (i5 & 16) != 0 ? msgHistory2.flag : z, (i5 & 32) != 0 ? msgHistory2.msgtype : i3, (i5 & 64) != 0 ? msgHistory2.receive : str3, (i5 & 128) != 0 ? msgHistory2.sender : str4);
    }

    public final int component1() {
        return this.app_type;
    }

    public final int component2() {
        return this.chatid;
    }

    @NotNull
    public final String component3() {
        return this.content;
    }

    @NotNull
    public final String component4() {
        return this.create_date;
    }

    public final boolean component5() {
        return this.flag;
    }

    public final int component6() {
        return this.msgtype;
    }

    @NotNull
    public final String component7() {
        return this.receive;
    }

    @NotNull
    public final String component8() {
        return this.sender;
    }

    @NotNull
    public final MsgHistory copy(int i, int i2, @NotNull String str, @NotNull String str2, boolean z, int i3, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "content");
        Intrinsics.checkParameterIsNotNull(str2, "create_date");
        String str5 = str3;
        Intrinsics.checkParameterIsNotNull(str5, "receive");
        String str6 = str4;
        Intrinsics.checkParameterIsNotNull(str6, "sender");
        return new MsgHistory(i, i2, str, str2, z, i3, str5, str6);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MsgHistory)) {
            return false;
        }
        MsgHistory msgHistory = (MsgHistory) obj;
        return this.app_type == msgHistory.app_type && this.chatid == msgHistory.chatid && Intrinsics.areEqual((Object) this.content, (Object) msgHistory.content) && Intrinsics.areEqual((Object) this.create_date, (Object) msgHistory.create_date) && this.flag == msgHistory.flag && this.msgtype == msgHistory.msgtype && Intrinsics.areEqual((Object) this.receive, (Object) msgHistory.receive) && Intrinsics.areEqual((Object) this.sender, (Object) msgHistory.sender);
    }

    public int hashCode() {
        int i = ((this.app_type * 31) + this.chatid) * 31;
        String str = this.content;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.create_date;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.flag;
        if (z) {
            z = true;
        }
        int i3 = (((hashCode2 + (z ? 1 : 0)) * 31) + this.msgtype) * 31;
        String str3 = this.receive;
        int hashCode3 = (i3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.sender;
        if (str4 != null) {
            i2 = str4.hashCode();
        }
        return hashCode3 + i2;
    }

    @NotNull
    public String toString() {
        return "MsgHistory(app_type=" + this.app_type + ", chatid=" + this.chatid + ", content=" + this.content + ", create_date=" + this.create_date + ", flag=" + this.flag + ", msgtype=" + this.msgtype + ", receive=" + this.receive + ", sender=" + this.sender + ")";
    }

    public MsgHistory(int i, int i2, @NotNull String str, @NotNull String str2, boolean z, int i3, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "content");
        Intrinsics.checkParameterIsNotNull(str2, "create_date");
        Intrinsics.checkParameterIsNotNull(str3, "receive");
        Intrinsics.checkParameterIsNotNull(str4, "sender");
        this.app_type = i;
        this.chatid = i2;
        this.content = str;
        this.create_date = str2;
        this.flag = z;
        this.msgtype = i3;
        this.receive = str3;
        this.sender = str4;
    }

    public final int getApp_type() {
        return this.app_type;
    }

    public final void setApp_type(int i) {
        this.app_type = i;
    }

    public final int getChatid() {
        return this.chatid;
    }

    public final void setChatid(int i) {
        this.chatid = i;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }

    public final void setContent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    @NotNull
    public final String getCreate_date() {
        return this.create_date;
    }

    public final void setCreate_date(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.create_date = str;
    }

    public final boolean getFlag() {
        return this.flag;
    }

    public final void setFlag(boolean z) {
        this.flag = z;
    }

    public final int getMsgtype() {
        return this.msgtype;
    }

    public final void setMsgtype(int i) {
        this.msgtype = i;
    }

    @NotNull
    public final String getReceive() {
        return this.receive;
    }

    public final void setReceive(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.receive = str;
    }

    @NotNull
    public final String getSender() {
        return this.sender;
    }

    public final void setSender(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.sender = str;
    }
}
