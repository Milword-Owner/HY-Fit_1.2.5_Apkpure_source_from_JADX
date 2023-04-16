package p005cn.sharesdk.framework.utils.QRCodeUtil;

import java.util.Arrays;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.c */
/* compiled from: BitMatrix */
public final class C0785c implements Cloneable {

    /* renamed from: a */
    private final int f447a;

    /* renamed from: b */
    private final int f448b;

    /* renamed from: c */
    private final int f449c;

    /* renamed from: d */
    private final int[] f450d;

    public C0785c(int i, int i2) {
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.f447a = i;
        this.f448b = i2;
        this.f449c = (i + 31) / 32;
        this.f450d = new int[(this.f449c * i2)];
    }

    private C0785c(int i, int i2, int i3, int[] iArr) {
        this.f447a = i;
        this.f448b = i2;
        this.f449c = i3;
        this.f450d = iArr;
    }

    /* renamed from: a */
    public boolean mo10851a(int i, int i2) {
        return ((this.f450d[(i2 * this.f449c) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    /* renamed from: a */
    public void mo10850a(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (i4 < 1 || i3 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            int i5 = i3 + i;
            int i6 = i4 + i2;
            if (i6 > this.f448b || i5 > this.f447a) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i2 < i6) {
                int i7 = this.f449c * i2;
                for (int i8 = i; i8 < i5; i8++) {
                    int[] iArr = this.f450d;
                    int i9 = (i8 / 32) + i7;
                    iArr[i9] = iArr[i9] | (1 << (i8 & 31));
                }
                i2++;
            }
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0785c)) {
            return false;
        }
        C0785c cVar = (C0785c) obj;
        if (this.f447a == cVar.f447a && this.f448b == cVar.f448b && this.f449c == cVar.f449c && Arrays.equals(this.f450d, cVar.f450d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.f447a;
        return (((((((i * 31) + i) * 31) + this.f448b) * 31) + this.f449c) * 31) + Arrays.hashCode(this.f450d);
    }

    public String toString() {
        return mo10849a("X ", "\t");
    }

    /* renamed from: a */
    public String mo10849a(String str, String str2) {
        return m577a(str, str2, "\n");
    }

    /* renamed from: a */
    private String m577a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.f448b * (this.f447a + 1));
        for (int i = 0; i < this.f448b; i++) {
            for (int i2 = 0; i2 < this.f447a; i2++) {
                sb.append(mo10851a(i2, i) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public C0785c clone() {
        return new C0785c(this.f447a, this.f448b, this.f449c, (int[]) this.f450d.clone());
    }
}
