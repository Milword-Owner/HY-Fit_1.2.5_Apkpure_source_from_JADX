package kotlin.random;

import kotlin.Metadata;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B7\b\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u000e\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo21895d2 = {"Lkotlin/random/XorWowRandom;", "Lkotlin/random/Random;", "seed1", "", "seed2", "(II)V", "x", "y", "z", "w", "v", "addend", "(IIIIII)V", "nextBits", "bitCount", "nextInt", "kotlin-stdlib"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: XorWowRandom.kt */
public final class XorWowRandom extends Random {
    private int addend;

    /* renamed from: v */
    private int f2772v;

    /* renamed from: w */
    private int f2773w;

    /* renamed from: x */
    private int f2774x;

    /* renamed from: y */
    private int f2775y;

    /* renamed from: z */
    private int f2776z;

    public XorWowRandom(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f2774x = i;
        this.f2775y = i2;
        this.f2776z = i3;
        this.f2773w = i4;
        this.f2772v = i5;
        this.addend = i6;
        if (((((this.f2774x | this.f2775y) | this.f2776z) | this.f2773w) | this.f2772v) != 0) {
            for (int i7 = 0; i7 < 64; i7++) {
                nextInt();
            }
            return;
        }
        throw new IllegalArgumentException("Initial state must have at least one non-zero element.".toString());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XorWowRandom(int r8, int r9) {
        /*
            r7 = this;
            r5 = r8 ^ -1
            int r0 = r8 << 10
            int r1 = r9 >>> 4
            r6 = r0 ^ r1
            r3 = 0
            r4 = 0
            r0 = r7
            r1 = r8
            r2 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.random.XorWowRandom.<init>(int, int):void");
    }

    public int nextInt() {
        int i = this.f2774x;
        int i2 = i ^ (i >>> 2);
        this.f2774x = this.f2775y;
        this.f2775y = this.f2776z;
        this.f2776z = this.f2773w;
        int i3 = this.f2772v;
        this.f2773w = i3;
        int i4 = ((i2 ^ (i2 << 1)) ^ i3) ^ (i3 << 4);
        this.f2772v = i4;
        this.addend += 362437;
        return i4 + this.addend;
    }

    public int nextBits(int i) {
        return RandomKt.takeUpperBits(nextInt(), i);
    }
}
