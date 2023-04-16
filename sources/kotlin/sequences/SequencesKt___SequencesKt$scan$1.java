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

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo21895d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo21896k = 3, mo21897mv = {1, 1, 16})
@DebugMetadata(mo21913c = "kotlin.sequences.SequencesKt___SequencesKt$scan$1", mo21914f = "_Sequences.kt", mo21915i = {0, 1, 1, 1}, mo21916l = {1433, 1437}, mo21917m = "invokeSuspend", mo21918n = {"$this$sequence", "$this$sequence", "accumulator", "element"}, mo21919s = {"L$0", "L$0", "L$1", "L$2"})
/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$scan$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $initial;
    final /* synthetic */ Function2 $operation;
    final /* synthetic */ Sequence $this_scan;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private SequenceScope f2779p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$scan$1(Sequence sequence, Object obj, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_scan = sequence;
        this.$initial = obj;
        this.$operation = function2;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SequencesKt___SequencesKt$scan$1 sequencesKt___SequencesKt$scan$1 = new SequencesKt___SequencesKt$scan$1(this.$this_scan, this.$initial, this.$operation, continuation);
        sequencesKt___SequencesKt$scan$1.f2779p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$scan$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SequencesKt___SequencesKt$scan$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0057  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0032
            if (r1 == r3) goto L_0x002a
            if (r1 != r2) goto L_0x0022
            java.lang.Object r1 = r8.L$3
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r3 = r8.L$2
            java.lang.Object r3 = r8.L$1
            java.lang.Object r4 = r8.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r8
            r7 = r3
            r3 = r0
            r0 = r7
            goto L_0x0051
        L_0x0022:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x002a:
            java.lang.Object r1 = r8.L$0
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0044
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.sequences.SequenceScope r1 = r8.f2779p$
            java.lang.Object r9 = r8.$initial
            r8.L$0 = r1
            r8.label = r3
            java.lang.Object r9 = r1.yield(r9, r8)
            if (r9 != r0) goto L_0x0044
            return r0
        L_0x0044:
            java.lang.Object r9 = r8.$initial
            kotlin.sequences.Sequence r3 = r8.$this_scan
            java.util.Iterator r3 = r3.iterator()
            r4 = r1
            r1 = r3
            r3 = r0
            r0 = r9
            r9 = r8
        L_0x0051:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0072
            java.lang.Object r5 = r1.next()
            kotlin.jvm.functions.Function2 r6 = r9.$operation
            java.lang.Object r0 = r6.invoke(r0, r5)
            r9.L$0 = r4
            r9.L$1 = r0
            r9.L$2 = r5
            r9.L$3 = r1
            r9.label = r2
            java.lang.Object r5 = r4.yield(r0, r9)
            if (r5 != r3) goto L_0x0051
            return r3
        L_0x0072:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$scan$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
