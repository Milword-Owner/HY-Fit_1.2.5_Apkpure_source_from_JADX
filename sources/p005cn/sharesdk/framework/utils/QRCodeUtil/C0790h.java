package p005cn.sharesdk.framework.utils.QRCodeUtil;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.h */
/* compiled from: GenericGFPoly */
final class C0790h {

    /* renamed from: a */
    private final C0789g f518a;

    /* renamed from: b */
    private final int[] f519b;

    C0790h(C0789g gVar, int[] iArr) {
        if (iArr.length != 0) {
            this.f518a = gVar;
            int length = iArr.length;
            if (length <= 1 || iArr[0] != 0) {
                this.f519b = iArr;
                return;
            }
            int i = 1;
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i == length) {
                this.f519b = new int[]{0};
                return;
            }
            this.f519b = new int[(length - i)];
            int[] iArr2 = this.f519b;
            System.arraycopy(iArr, i, iArr2, 0, iArr2.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int[] mo10869a() {
        return this.f519b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo10870b() {
        return this.f519b.length - 1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo10872c() {
        return this.f519b[0] == 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo10866a(int i) {
        int[] iArr = this.f519b;
        return iArr[(iArr.length - 1) - i];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0790h mo10868a(C0790h hVar) {
        if (!this.f518a.equals(hVar.f518a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (mo10872c()) {
            return hVar;
        } else {
            if (hVar.mo10872c()) {
                return this;
            }
            int[] iArr = this.f519b;
            int[] iArr2 = hVar.f519b;
            if (iArr.length > iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr4, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr4[i] = C0789g.m585b(iArr[i - length], iArr2[i]);
            }
            return new C0790h(this.f518a, iArr4);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C0790h mo10871b(C0790h hVar) {
        if (!this.f518a.equals(hVar.f518a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (mo10872c() || hVar.mo10872c()) {
            return this.f518a.mo10859a();
        } else {
            int[] iArr = this.f519b;
            int length = iArr.length;
            int[] iArr2 = hVar.f519b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = C0789g.m585b(iArr3[i4], this.f518a.mo10864c(i2, iArr2[i3]));
                }
            }
            return new C0790h(this.f518a, iArr3);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0790h mo10867a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.f518a.mo10859a();
        } else {
            int length = this.f519b.length;
            int[] iArr = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.f518a.mo10864c(this.f519b[i3], i2);
            }
            return new C0790h(this.f518a, iArr);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public C0790h[] mo10873c(C0790h hVar) {
        if (!this.f518a.equals(hVar.f518a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (!hVar.mo10872c()) {
            C0790h a = this.f518a.mo10859a();
            int c = this.f518a.mo10863c(hVar.mo10866a(hVar.mo10870b()));
            C0790h hVar2 = a;
            C0790h hVar3 = this;
            while (hVar3.mo10870b() >= hVar.mo10870b() && !hVar3.mo10872c()) {
                int b = hVar3.mo10870b() - hVar.mo10870b();
                int c2 = this.f518a.mo10864c(hVar3.mo10866a(hVar3.mo10870b()), c);
                C0790h a2 = hVar.mo10867a(b, c2);
                hVar2 = hVar2.mo10868a(this.f518a.mo10860a(b, c2));
                hVar3 = hVar3.mo10868a(a2);
            }
            return new C0790h[]{hVar2, hVar3};
        } else {
            throw new IllegalArgumentException("Divide by 0");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(mo10870b() * 8);
        for (int b = mo10870b(); b >= 0; b--) {
            int a = mo10866a(b);
            if (a != 0) {
                if (a < 0) {
                    sb.append(" - ");
                    a = -a;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (b == 0 || a != 1) {
                    int b2 = this.f518a.mo10862b(a);
                    if (b2 == 0) {
                        sb.append('1');
                    } else if (b2 == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(b2);
                    }
                }
                if (b != 0) {
                    if (b == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(b);
                    }
                }
            }
        }
        return sb.toString();
    }
}
