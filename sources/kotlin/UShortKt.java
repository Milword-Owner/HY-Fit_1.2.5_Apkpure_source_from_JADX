package kotlin;

import kotlin.internal.InlineOnly;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\bø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\bø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\bH\bø\u0001\u0000¢\u0006\u0002\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, mo21895d2 = {"toUShort", "Lkotlin/UShort;", "", "(B)S", "", "(I)S", "", "(J)S", "", "(S)S", "kotlin-stdlib"}, mo21896k = 2, mo21897mv = {1, 1, 16})
/* compiled from: UShort.kt */
public final class UShortKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short toUShort(byte b) {
        return UShort.m3260constructorimpl((short) b);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short toUShort(short s) {
        return UShort.m3260constructorimpl(s);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short toUShort(int i) {
        return UShort.m3260constructorimpl((short) i);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short toUShort(long j) {
        return UShort.m3260constructorimpl((short) ((int) j));
    }
}
