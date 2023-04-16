package com.huayu.tzc.utils;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000(\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\t\u001a\u00020\n\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0001¢\u0006\u0002\u0010\u000e\u001a<\u0010\t\u001a\u00020\n\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\b\b\u0002\u0010\r\u001a\u00020\u00012\u0014\b\u0004\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\n0\u0010H\b¢\u0006\u0002\u0010\u0011\"2\u0010\u0002\u001a\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0012"}, mo21895d2 = {"value", "", "lastClickTime", "T", "Landroid/view/View;", "getLastClickTime", "(Landroid/view/View;)J", "setLastClickTime", "(Landroid/view/View;J)V", "singleClick", "", "onClickListener", "Landroid/view/View$OnClickListener;", "time", "(Landroid/view/View;Landroid/view/View$OnClickListener;J)V", "block", "Lkotlin/Function1;", "(Landroid/view/View;JLkotlin/jvm/functions/Function1;)V", "app_release"}, mo21896k = 2, mo21897mv = {1, 1, 16})
/* compiled from: singleClick.kt */
public final class SingleClickKt {
    public static /* synthetic */ void singleClick$default(View view, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 800;
        }
        Intrinsics.checkParameterIsNotNull(view, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        view.setOnClickListener(new SingleClickKt$singleClick$1(view, j, function1));
    }

    public static final <T extends View> void singleClick(@NotNull T t, long j, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(t, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        t.setOnClickListener(new SingleClickKt$singleClick$1(t, j, function1));
    }

    public static /* synthetic */ void singleClick$default(View view, View.OnClickListener onClickListener, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 800;
        }
        singleClick(view, onClickListener, j);
    }

    public static final <T extends View> void singleClick(@NotNull T t, @NotNull View.OnClickListener onClickListener, long j) {
        Intrinsics.checkParameterIsNotNull(t, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(onClickListener, "onClickListener");
        t.setOnClickListener(new SingleClickKt$singleClick$2(t, j, onClickListener));
    }

    public static final <T extends View> void setLastClickTime(@NotNull T t, long j) {
        Intrinsics.checkParameterIsNotNull(t, "$this$lastClickTime");
        t.setTag(1766613352, Long.valueOf(j));
    }

    public static final <T extends View> long getLastClickTime(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "$this$lastClickTime");
        Object tag = t.getTag(1766613352);
        if (!(tag instanceof Long)) {
            tag = null;
        }
        Long l = (Long) tag;
        if (l != null) {
            return l.longValue();
        }
        return 0;
    }
}
