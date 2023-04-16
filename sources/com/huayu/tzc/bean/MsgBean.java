package com.huayu.tzc.bean;

import com.huayu.tzc.base.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\"\u0018\u0000 )2\u00020\u0001:\u0001)B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nB)\b\u0016\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0002\u0010\u000eB)\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\u0010J\u000e\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0004R\u001a\u0010\t\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\r\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0018\"\u0004\b\"\u0010\u001aR\u001a\u0010\f\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R\u001a\u0010\u000f\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0012\"\u0004\b&\u0010\u0014¨\u0006*"}, mo21895d2 = {"Lcom/huayu/tzc/bean/MsgBean;", "", "()V", "from", "", "to", "contentText", "msgtype", "", "app_type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "msg", "type", "chatid", "(Ljava/lang/String;III)V", "uid", "(ILjava/lang/String;II)V", "getApp_type", "()I", "setApp_type", "(I)V", "getChatid", "setChatid", "getContentText", "()Ljava/lang/String;", "setContentText", "(Ljava/lang/String;)V", "getFrom", "setFrom", "getMsg", "setMsg", "getMsgtype", "setMsgtype", "getTo", "setTo", "getType", "setType", "getUid", "setUid", "replaceImg", "avatar", "Companion", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: MsgBean.kt */
public final class MsgBean {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int MSG_DATE = 2;
    public static final int MSG_LEFT = 0;
    public static final int MSG_RIGHT = 1;
    private int app_type;
    private int chatid = -1;
    @Nullable
    private String contentText;
    @Nullable
    private String from;
    @Nullable
    private String msg;
    private int msgtype;
    @Nullable

    /* renamed from: to */
    private String f1687to;
    private int type;
    private int uid;

    @Nullable
    public final String getMsg() {
        return this.msg;
    }

    public final void setMsg(@Nullable String str) {
        this.msg = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    @Nullable
    public final String getFrom() {
        return this.from;
    }

    public final void setFrom(@Nullable String str) {
        this.from = str;
    }

    @Nullable
    public final String getTo() {
        return this.f1687to;
    }

    public final void setTo(@Nullable String str) {
        this.f1687to = str;
    }

    @Nullable
    public final String getContentText() {
        return this.contentText;
    }

    public final void setContentText(@Nullable String str) {
        this.contentText = str;
    }

    public final int getMsgtype() {
        return this.msgtype;
    }

    public final void setMsgtype(int i) {
        this.msgtype = i;
    }

    public final int getUid() {
        return this.uid;
    }

    public final void setUid(int i) {
        this.uid = i;
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
    public final String replaceImg(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "avatar");
        return StringsKt.replace$default(str, Constant.BASE64_TITLE, "", false, 4, (Object) null);
    }

    public MsgBean() {
    }

    public MsgBean(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, int i2) {
        this.from = str;
        this.f1687to = str2;
        this.contentText = str3;
        this.msgtype = i;
        this.app_type = i2;
    }

    public MsgBean(@Nullable String str, int i, int i2, int i3) {
        this.msg = str;
        this.type = i;
        this.msgtype = i2;
        this.chatid = i3;
    }

    public MsgBean(int i, @Nullable String str, int i2, int i3) {
        this.uid = i;
        this.msg = str;
        this.type = i2;
        this.msgtype = i3;
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo21895d2 = {"Lcom/huayu/tzc/bean/MsgBean$Companion;", "", "()V", "MSG_DATE", "", "MSG_LEFT", "MSG_RIGHT", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: MsgBean.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
