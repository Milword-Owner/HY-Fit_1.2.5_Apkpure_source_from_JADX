package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001fB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u000fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0012J\u001b\u0010)\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0012J\u001b\u00100\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u000fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0012J\u001b\u00109\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0012J\u001b\u0010>\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020\u0003H\b¢\u0006\u0004\bD\u0010\u0005J\u0010\u0010E\u001a\u00020FH\b¢\u0006\u0004\bG\u0010HJ\u0010\u0010I\u001a\u00020JH\b¢\u0006\u0004\bK\u0010LJ\u0010\u0010M\u001a\u00020\rH\b¢\u0006\u0004\bN\u0010OJ\u0010\u0010P\u001a\u00020QH\b¢\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020UH\b¢\u0006\u0004\bV\u0010WJ\u000f\u0010X\u001a\u00020YH\u0016¢\u0006\u0004\bZ\u0010[J\u0013\u0010\\\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b]\u0010\u0005J\u0013\u0010^\u001a\u00020\u0010H\bø\u0001\u0000¢\u0006\u0004\b_\u0010OJ\u0013\u0010`\u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\ba\u0010SJ\u0013\u0010b\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\bc\u0010WJ\u001b\u0010d\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\be\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006g"}, mo21895d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "data$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: UByte.kt */
public final class UByte implements Comparable<UByte> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UByte m3021boximpl(byte b) {
        return new UByte(b);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private int m3022compareTo7apg3OU(byte b) {
        return m3023compareTo7apg3OU(this.data, b);
    }

    @PublishedApi
    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m3027constructorimpl(byte b) {
        return b;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m3033equalsimpl(byte b, @Nullable Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).m3068unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m3034equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m3035hashCodeimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m3056toByteimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m3057toDoubleimpl(byte b) {
        return (double) (b & MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m3058toFloatimpl(byte b) {
        return (float) (b & MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m3059toIntimpl(byte b) {
        return b & MAX_VALUE;
    }

    @InlineOnly
    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m3060toLongimpl(byte b) {
        return ((long) b) & 255;
    }

    @InlineOnly
    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m3061toShortimpl(byte b) {
        return (short) (((short) b) & 255);
    }

    @InlineOnly
    /* renamed from: toUByte-impl  reason: not valid java name */
    private static final byte m3063toUByteimpl(byte b) {
        return b;
    }

    public boolean equals(Object obj) {
        return m3033equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m3035hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m3062toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ byte m3068unboximpl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m3022compareTo7apg3OU(((UByte) obj).m3068unboximpl());
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, mo21895d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: UByte.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static int m3023compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare((int) b & MAX_VALUE, (int) b2 & MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m3026compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare((int) b & MAX_VALUE, (int) s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static final int m3025compareToWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.uintCompare(UInt.m3094constructorimpl(b & MAX_VALUE), i);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m3024compareToVKZWuLQ(byte b, long j) {
        return UnsignedKt.ulongCompare(ULong.m3163constructorimpl(((long) b) & 255), j);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m3043plus7apg3OU(byte b, byte b2) {
        return UInt.m3094constructorimpl(UInt.m3094constructorimpl(b & MAX_VALUE) + UInt.m3094constructorimpl(b2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m3046plusxj2QHRw(byte b, short s) {
        return UInt.m3094constructorimpl(UInt.m3094constructorimpl(b & MAX_VALUE) + UInt.m3094constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m3045plusWZ4Q5Ns(byte b, int i) {
        return UInt.m3094constructorimpl(UInt.m3094constructorimpl(b & MAX_VALUE) + i);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m3044plusVKZWuLQ(byte b, long j) {
        return ULong.m3163constructorimpl(ULong.m3163constructorimpl(((long) b) & 255) + j);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m3038minus7apg3OU(byte b, byte b2) {
        return UInt.m3094constructorimpl(UInt.m3094constructorimpl(b & MAX_VALUE) - UInt.m3094constructorimpl(b2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m3041minusxj2QHRw(byte b, short s) {
        return UInt.m3094constructorimpl(UInt.m3094constructorimpl(b & MAX_VALUE) - UInt.m3094constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m3040minusWZ4Q5Ns(byte b, int i) {
        return UInt.m3094constructorimpl(UInt.m3094constructorimpl(b & MAX_VALUE) - i);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m3039minusVKZWuLQ(byte b, long j) {
        return ULong.m3163constructorimpl(ULong.m3163constructorimpl(((long) b) & 255) - j);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m3052times7apg3OU(byte b, byte b2) {
        return UInt.m3094constructorimpl(UInt.m3094constructorimpl(b & MAX_VALUE) * UInt.m3094constructorimpl(b2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m3055timesxj2QHRw(byte b, short s) {
        return UInt.m3094constructorimpl(UInt.m3094constructorimpl(b & MAX_VALUE) * UInt.m3094constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m3054timesWZ4Q5Ns(byte b, int i) {
        return UInt.m3094constructorimpl(UInt.m3094constructorimpl(b & MAX_VALUE) * i);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m3053timesVKZWuLQ(byte b, long j) {
        return ULong.m3163constructorimpl(ULong.m3163constructorimpl(((long) b) & 255) * j);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m3029div7apg3OU(byte b, byte b2) {
        return UnsignedKt.m3320uintDivideJ1ME1BU(UInt.m3094constructorimpl(b & MAX_VALUE), UInt.m3094constructorimpl(b2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m3032divxj2QHRw(byte b, short s) {
        return UnsignedKt.m3320uintDivideJ1ME1BU(UInt.m3094constructorimpl(b & MAX_VALUE), UInt.m3094constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m3031divWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m3320uintDivideJ1ME1BU(UInt.m3094constructorimpl(b & MAX_VALUE), i);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m3030divVKZWuLQ(byte b, long j) {
        return UnsignedKt.m3322ulongDivideeb3DHEI(ULong.m3163constructorimpl(((long) b) & 255), j);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m3048rem7apg3OU(byte b, byte b2) {
        return UnsignedKt.m3321uintRemainderJ1ME1BU(UInt.m3094constructorimpl(b & MAX_VALUE), UInt.m3094constructorimpl(b2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m3051remxj2QHRw(byte b, short s) {
        return UnsignedKt.m3321uintRemainderJ1ME1BU(UInt.m3094constructorimpl(b & MAX_VALUE), UInt.m3094constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m3050remWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m3321uintRemainderJ1ME1BU(UInt.m3094constructorimpl(b & MAX_VALUE), i);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m3049remVKZWuLQ(byte b, long j) {
        return UnsignedKt.m3323ulongRemaindereb3DHEI(ULong.m3163constructorimpl(((long) b) & 255), j);
    }

    @InlineOnly
    /* renamed from: inc-impl  reason: not valid java name */
    private static final byte m3036incimpl(byte b) {
        return m3027constructorimpl((byte) (b + 1));
    }

    @InlineOnly
    /* renamed from: dec-impl  reason: not valid java name */
    private static final byte m3028decimpl(byte b) {
        return m3027constructorimpl((byte) (b - 1));
    }

    @InlineOnly
    /* renamed from: rangeTo-7apg3OU  reason: not valid java name */
    private static final UIntRange m3047rangeTo7apg3OU(byte b, byte b2) {
        return new UIntRange(UInt.m3094constructorimpl(b & MAX_VALUE), UInt.m3094constructorimpl(b2 & MAX_VALUE), (DefaultConstructorMarker) null);
    }

    @InlineOnly
    /* renamed from: and-7apg3OU  reason: not valid java name */
    private static final byte m3020and7apg3OU(byte b, byte b2) {
        return m3027constructorimpl((byte) (b & b2));
    }

    @InlineOnly
    /* renamed from: or-7apg3OU  reason: not valid java name */
    private static final byte m3042or7apg3OU(byte b, byte b2) {
        return m3027constructorimpl((byte) (b | b2));
    }

    @InlineOnly
    /* renamed from: xor-7apg3OU  reason: not valid java name */
    private static final byte m3067xor7apg3OU(byte b, byte b2) {
        return m3027constructorimpl((byte) (b ^ b2));
    }

    @InlineOnly
    /* renamed from: inv-impl  reason: not valid java name */
    private static final byte m3037invimpl(byte b) {
        return m3027constructorimpl((byte) (b ^ -1));
    }

    @InlineOnly
    /* renamed from: toUShort-impl  reason: not valid java name */
    private static final short m3066toUShortimpl(byte b) {
        return UShort.m3260constructorimpl((short) (((short) b) & 255));
    }

    @InlineOnly
    /* renamed from: toUInt-impl  reason: not valid java name */
    private static final int m3064toUIntimpl(byte b) {
        return UInt.m3094constructorimpl(b & MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: toULong-impl  reason: not valid java name */
    private static final long m3065toULongimpl(byte b) {
        return ULong.m3163constructorimpl(((long) b) & 255);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m3062toStringimpl(byte b) {
        return String.valueOf(b & MAX_VALUE);
    }
}
