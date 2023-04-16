package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo21895d2 = {"<anonymous>", "", "S", "T", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo21896k = 3, mo21897mv = {1, 1, 16})
@DebugMetadata(mo21913c = "kotlin.sequences.SequencesKt___SequencesKt$scanReduce$1", mo21914f = "_Sequences.kt", mo21915i = {0, 0, 0, 1, 1, 1}, mo21916l = {1492, 1495}, mo21917m = "invokeSuspend", mo21918n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator"}, mo21919s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$scanReduce$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $operation;
    final /* synthetic */ Sequence $this_scanReduce;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private SequenceScope f2781p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$scanReduce$1(Sequence sequence, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_scanReduce = sequence;
        this.$operation = function2;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SequencesKt___SequencesKt$scanReduce$1 sequencesKt___SequencesKt$scanReduce$1 = new SequencesKt___SequencesKt$scanReduce$1(this.$this_scanReduce, this.$operation, continuation);
        sequencesKt___SequencesKt$scanReduce$1.f2781p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$scanReduce$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SequencesKt___SequencesKt$scanReduce$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005f  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0033
            if (r1 == r3) goto L_0x0024
            if (r1 != r2) goto L_0x001c
            java.lang.Object r1 = r7.L$2
            java.lang.Object r3 = r7.L$1
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r7.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0058
        L_0x001c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0024:
            java.lang.Object r1 = r7.L$2
            java.lang.Object r3 = r7.L$1
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r7.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r3
            goto L_0x0057
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.sequences.SequenceScope r4 = r7.f2781p$
            kotlin.sequences.Sequence r8 = r7.$this_scanReduce
            java.util.Iterator r8 = r8.iterator()
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x0078
            java.lang.Object r1 = r8.next()
            r7.L$0 = r4
            r7.L$1 = r8
            r7.L$2 = r1
            r7.label = r3
            java.lang.Object r3 = r4.yield(r1, r7)
            if (r3 != r0) goto L_0x0057
            return r0
        L_0x0057:
            r3 = r8
        L_0x0058:
            r8 = r7
        L_0x0059:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0078
            kotlin.jvm.functions.Function2 r5 = r8.$operation
            java.lang.Object r6 = r3.next()
            java.lang.Object r1 = r5.invoke(r1, r6)
            r8.L$0 = r4
            r8.L$1 = r3
            r8.L$2 = r1
            r8.label = r2
            java.lang.Object r5 = r4.yield(r1, r8)
            if (r5 != r0) goto L_0x0059
            return r0
        L_0x0078:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$scanReduce$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
