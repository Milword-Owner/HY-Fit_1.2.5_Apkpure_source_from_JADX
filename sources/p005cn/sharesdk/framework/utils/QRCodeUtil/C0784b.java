package p005cn.sharesdk.framework.utils.QRCodeUtil;

import java.util.Arrays;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.b */
/* compiled from: BitArray */
public final class C0784b implements Cloneable {

    /* renamed from: a */
    private int[] f445a;

    /* renamed from: b */
    private int f446b;

    public C0784b() {
        this.f446b = 0;
        this.f445a = new int[1];
    }

    C0784b(int[] iArr, int i) {
        this.f445a = iArr;
        this.f446b = i;
    }

    /* renamed from: a */
    public int mo10835a() {
        return this.f446b;
    }

    /* renamed from: b */
    public int mo10841b() {
        return (this.f446b + 7) / 8;
    }

    /* renamed from: b */
    private void m566b(int i) {
        if (i > this.f445a.length * 32) {
            int[] c = m567c(i);
            int[] iArr = this.f445a;
            System.arraycopy(iArr, 0, c, 0, iArr.length);
            this.f445a = c;
        }
    }

    /* renamed from: a */
    public boolean mo10840a(int i) {
        return ((1 << (i & 31)) & this.f445a[i / 32]) != 0;
    }

    /* renamed from: a */
    public void mo10839a(boolean z) {
        m566b(this.f446b + 1);
        if (z) {
            int[] iArr = this.f445a;
            int i = this.f446b;
            int i2 = i / 32;
            iArr[i2] = (1 << (i & 31)) | iArr[i2];
        }
        this.f446b++;
    }

    /* renamed from: a */
    public void mo10836a(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        m566b(this.f446b + i2);
        while (i2 > 0) {
            boolean z = true;
            if (((i >> (i2 - 1)) & 1) != 1) {
                z = false;
            }
            mo10839a(z);
            i2--;
        }
    }

    /* renamed from: a */
    public void mo10838a(C0784b bVar) {
        int i = bVar.f446b;
        m566b(this.f446b + i);
        for (int i2 = 0; i2 < i; i2++) {
            mo10839a(bVar.mo10840a(i2));
        }
    }

    /* renamed from: b */
    public void mo10842b(C0784b bVar) {
        if (this.f445a.length == bVar.f445a.length) {
            int i = 0;
            while (true) {
                int[] iArr = this.f445a;
                if (i < iArr.length) {
                    iArr[i] = iArr[i] ^ bVar.f445a[i];
                    i++;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Sizes don't match");
        }
    }

    /* renamed from: a */
    public void mo10837a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        int i5 = 0;
        while (i5 < i3) {
            int i6 = i4;
            int i7 = 0;
            for (int i8 = 0; i8 < 8; i8++) {
                if (mo10840a(i6)) {
                    i7 |= 1 << (7 - i8);
                }
                i6++;
            }
            bArr[i2 + i5] = (byte) i7;
            i5++;
            i4 = i6;
        }
    }

    /* renamed from: c */
    private static int[] m567c(int i) {
        return new int[((i + 31) / 32)];
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0784b)) {
            return false;
        }
        C0784b bVar = (C0784b) obj;
        if (this.f446b != bVar.f446b || !Arrays.equals(this.f445a, bVar.f445a)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f446b * 31) + Arrays.hashCode(this.f445a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.f446b);
        for (int i = 0; i < this.f446b; i++) {
            if ((i & 7) == 0) {
                sb.append(' ');
            }
            sb.append(mo10840a(i) ? 'X' : '.');
        }
        return sb.toString();
    }

    /* renamed from: c */
    public C0784b clone() {
        return new C0784b((int[]) this.f445a.clone(), this.f446b);
    }
}
