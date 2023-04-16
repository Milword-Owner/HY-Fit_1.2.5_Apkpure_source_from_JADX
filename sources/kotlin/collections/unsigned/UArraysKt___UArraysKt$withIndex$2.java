package kotlin.collections.unsigned;

import kotlin.Metadata;
import kotlin.ULongArray;
import kotlin.collections.ULongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo21895d2 = {"<anonymous>", "Lkotlin/collections/ULongIterator;", "invoke"}, mo21896k = 3, mo21897mv = {1, 1, 16})
/* compiled from: _UArrays.kt */
final class UArraysKt___UArraysKt$withIndex$2 extends Lambda implements Function0<ULongIterator> {
    final /* synthetic */ long[] $this_withIndex;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UArraysKt___UArraysKt$withIndex$2(long[] jArr) {
        super(0);
        this.$this_withIndex = jArr;
    }

    @NotNull
    public final ULongIterator invoke() {
        return ULongArray.m3218iteratorimpl(this.$this_withIndex);
    }
}
