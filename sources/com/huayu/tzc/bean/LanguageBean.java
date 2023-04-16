package com.huayu.tzc.bean;

import com.facebook.appevents.internal.ViewHierarchyConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, mo21895d2 = {"Lcom/huayu/tzc/bean/LanguageBean;", "", "text", "", "checked", "", "(Ljava/lang/String;Z)V", "isChecked", "()Z", "setChecked", "(Z)V", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: LanguageBean.kt */
public final class LanguageBean {
    private boolean isChecked;
    @NotNull
    private String text;

    public LanguageBean(@NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, ViewHierarchyConstants.TEXT_KEY);
        this.text = str;
        this.isChecked = z;
    }

    @NotNull
    public final String getText() {
        return this.text;
    }

    public final void setText(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.text = str;
    }

    public final boolean isChecked() {
        return this.isChecked;
    }

    public final void setChecked(boolean z) {
        this.isChecked = z;
    }
}
