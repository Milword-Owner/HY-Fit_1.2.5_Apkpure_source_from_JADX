package p005cn.sharesdk.framework.utils.QRCodeUtil;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.g */
/* compiled from: GenericGF */
public final class C0789g {

    /* renamed from: a */
    public static final C0789g f503a = new C0789g(4201, 4096, 1);

    /* renamed from: b */
    public static final C0789g f504b = new C0789g(1033, 1024, 1);

    /* renamed from: c */
    public static final C0789g f505c = new C0789g(67, 64, 1);

    /* renamed from: d */
    public static final C0789g f506d = new C0789g(19, 16, 1);

    /* renamed from: e */
    public static final C0789g f507e = new C0789g(285, 256, 0);

    /* renamed from: f */
    public static final C0789g f508f = new C0789g(301, 256, 1);

    /* renamed from: g */
    public static final C0789g f509g = f508f;

    /* renamed from: h */
    public static final C0789g f510h = f505c;

    /* renamed from: i */
    private final int[] f511i;

    /* renamed from: j */
    private final int[] f512j;

    /* renamed from: k */
    private final C0790h f513k;

    /* renamed from: l */
    private final C0790h f514l;

    /* renamed from: m */
    private final int f515m;

    /* renamed from: n */
    private final int f516n;

    /* renamed from: o */
    private final int f517o;

    /* renamed from: b */
    static int m585b(int i, int i2) {
        return i ^ i2;
    }

    public C0789g(int i, int i2, int i3) {
        this.f516n = i;
        this.f515m = i2;
        this.f517o = i3;
        this.f511i = new int[i2];
        this.f512j = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.f511i[i5] = i4;
            i4 *= 2;
            if (i4 >= i2) {
                i4 = (i4 ^ i) & (i2 - 1);
            }
        }
        for (int i6 = 0; i6 < i2 - 1; i6++) {
            this.f512j[this.f511i[i6]] = i6;
        }
        this.f513k = new C0790h(this, new int[]{0});
        this.f514l = new C0790h(this, new int[]{1});
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0790h mo10859a() {
        return this.f513k;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0790h mo10860a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.f513k;
        } else {
            int[] iArr = new int[(i + 1)];
            iArr[0] = i2;
            return new C0790h(this, iArr);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo10858a(int i) {
        return this.f511i[i];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo10862b(int i) {
        if (i != 0) {
            return this.f512j[i];
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo10863c(int i) {
        if (i != 0) {
            return this.f511i[(this.f515m - this.f512j[i]) - 1];
        }
        throw new ArithmeticException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo10864c(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = this.f511i;
        int[] iArr2 = this.f512j;
        return iArr[(iArr2[i] + iArr2[i2]) % (this.f515m - 1)];
    }

    /* renamed from: b */
    public int mo10861b() {
        return this.f517o;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.f516n) + ',' + this.f515m + ')';
    }
}
