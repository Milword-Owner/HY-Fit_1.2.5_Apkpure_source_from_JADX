package p005cn.sharesdk.framework.utils.QRCodeUtil;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.n */
/* compiled from: Version */
public final class C0796n {

    /* renamed from: a */
    private static final int[] f579a = {31892, 34236, 39577, 42195, 48118, 51042, 55367, 58893, 63784, 68472, 70749, 76311, 79154, 84390, 87683, 92361, 96236, 102084, 102881, 110507, 110734, 117786, 119615, 126325, 127568, 133589, 136944, 141498, 145311, 150283, 152622, 158308, 161089, 167017};

    /* renamed from: b */
    private static final C0796n[] f580b = m633d();

    /* renamed from: c */
    private final int f581c;

    /* renamed from: d */
    private final int[] f582d;

    /* renamed from: e */
    private final C0798b[] f583e;

    /* renamed from: f */
    private final int f584f;

    private C0796n(int i, int[] iArr, C0798b... bVarArr) {
        this.f581c = i;
        this.f582d = iArr;
        this.f583e = bVarArr;
        int a = bVarArr[0].mo10902a();
        int i2 = 0;
        for (C0797a aVar : bVarArr[0].mo10905d()) {
            i2 += aVar.mo10900a() * (aVar.mo10901b() + a);
        }
        this.f584f = i2;
    }

    /* renamed from: a */
    public int mo10895a() {
        return this.f581c;
    }

    /* renamed from: b */
    public int mo10897b() {
        return this.f584f;
    }

    /* renamed from: c */
    public int mo10898c() {
        return (this.f581c * 4) + 17;
    }

    /* renamed from: a */
    public C0798b mo10896a(C0788f fVar) {
        return this.f583e[fVar.ordinal()];
    }

    /* renamed from: a */
    public static C0796n m632a(int i) {
        if (i >= 1 && i <= 40) {
            return f580b[i - 1];
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.n$b */
    /* compiled from: Version */
    public static final class C0798b {

        /* renamed from: a */
        private final int f587a;

        /* renamed from: b */
        private final C0797a[] f588b;

        C0798b(int i, C0797a... aVarArr) {
            this.f587a = i;
            this.f588b = aVarArr;
        }

        /* renamed from: a */
        public int mo10902a() {
            return this.f587a;
        }

        /* renamed from: b */
        public int mo10903b() {
            int i = 0;
            for (C0797a a : this.f588b) {
                i += a.mo10900a();
            }
            return i;
        }

        /* renamed from: c */
        public int mo10904c() {
            return this.f587a * mo10903b();
        }

        /* renamed from: d */
        public C0797a[] mo10905d() {
            return this.f588b;
        }
    }

    /* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.n$a */
    /* compiled from: Version */
    public static final class C0797a {

        /* renamed from: a */
        private final int f585a;

        /* renamed from: b */
        private final int f586b;

        C0797a(int i, int i2) {
            this.f585a = i;
            this.f586b = i2;
        }

        /* renamed from: a */
        public int mo10900a() {
            return this.f585a;
        }

        /* renamed from: b */
        public int mo10901b() {
            return this.f586b;
        }
    }

    public String toString() {
        return String.valueOf(this.f581c);
    }

    /* renamed from: d */
    private static C0796n[] m633d() {
        return new C0796n[]{new C0796n(1, new int[0], new C0798b(7, new C0797a(1, 19)), new C0798b(10, new C0797a(1, 16)), new C0798b(13, new C0797a(1, 13)), new C0798b(17, new C0797a(1, 9))), new C0796n(2, new int[]{6, 18}, new C0798b(10, new C0797a(1, 34)), new C0798b(16, new C0797a(1, 28)), new C0798b(22, new C0797a(1, 22)), new C0798b(28, new C0797a(1, 16))), new C0796n(3, new int[]{6, 22}, new C0798b(15, new C0797a(1, 55)), new C0798b(26, new C0797a(1, 44)), new C0798b(18, new C0797a(2, 17)), new C0798b(22, new C0797a(2, 13))), new C0796n(4, new int[]{6, 26}, new C0798b(20, new C0797a(1, 80)), new C0798b(18, new C0797a(2, 32)), new C0798b(26, new C0797a(2, 24)), new C0798b(16, new C0797a(4, 9))), new C0796n(5, new int[]{6, 30}, new C0798b(26, new C0797a(1, 108)), new C0798b(24, new C0797a(2, 43)), new C0798b(18, new C0797a(2, 15), new C0797a(2, 16)), new C0798b(22, new C0797a(2, 11), new C0797a(2, 12))), new C0796n(6, new int[]{6, 34}, new C0798b(18, new C0797a(2, 68)), new C0798b(16, new C0797a(4, 27)), new C0798b(24, new C0797a(4, 19)), new C0798b(28, new C0797a(4, 15))), new C0796n(7, new int[]{6, 22, 38}, new C0798b(20, new C0797a(2, 78)), new C0798b(18, new C0797a(4, 31)), new C0798b(18, new C0797a(2, 14), new C0797a(4, 15)), new C0798b(26, new C0797a(4, 13), new C0797a(1, 14))), new C0796n(8, new int[]{6, 24, 42}, new C0798b(24, new C0797a(2, 97)), new C0798b(22, new C0797a(2, 38), new C0797a(2, 39)), new C0798b(22, new C0797a(4, 18), new C0797a(2, 19)), new C0798b(26, new C0797a(4, 14), new C0797a(2, 15))), new C0796n(9, new int[]{6, 26, 46}, new C0798b(30, new C0797a(2, 116)), new C0798b(22, new C0797a(3, 36), new C0797a(2, 37)), new C0798b(20, new C0797a(4, 16), new C0797a(4, 17)), new C0798b(24, new C0797a(4, 12), new C0797a(4, 13))), new C0796n(10, new int[]{6, 28, 50}, new C0798b(18, new C0797a(2, 68), new C0797a(2, 69)), new C0798b(26, new C0797a(4, 43), new C0797a(1, 44)), new C0798b(24, new C0797a(6, 19), new C0797a(2, 20)), new C0798b(28, new C0797a(6, 15), new C0797a(2, 16))), new C0796n(11, new int[]{6, 30, 54}, new C0798b(20, new C0797a(4, 81)), new C0798b(30, new C0797a(1, 50), new C0797a(4, 51)), new C0798b(28, new C0797a(4, 22), new C0797a(4, 23)), new C0798b(24, new C0797a(3, 12), new C0797a(8, 13))), new C0796n(12, new int[]{6, 32, 58}, new C0798b(24, new C0797a(2, 92), new C0797a(2, 93)), new C0798b(22, new C0797a(6, 36), new C0797a(2, 37)), new C0798b(26, new C0797a(4, 20), new C0797a(6, 21)), new C0798b(28, new C0797a(7, 14), new C0797a(4, 15))), new C0796n(13, new int[]{6, 34, 62}, new C0798b(26, new C0797a(4, 107)), new C0798b(22, new C0797a(8, 37), new C0797a(1, 38)), new C0798b(24, new C0797a(8, 20), new C0797a(4, 21)), new C0798b(22, new C0797a(12, 11), new C0797a(4, 12))), new C0796n(14, new int[]{6, 26, 46, 66}, new C0798b(30, new C0797a(3, 115), new C0797a(1, 116)), new C0798b(24, new C0797a(4, 40), new C0797a(5, 41)), new C0798b(20, new C0797a(11, 16), new C0797a(5, 17)), new C0798b(24, new C0797a(11, 12), new C0797a(5, 13))), new C0796n(15, new int[]{6, 26, 48, 70}, new C0798b(22, new C0797a(5, 87), new C0797a(1, 88)), new C0798b(24, new C0797a(5, 41), new C0797a(5, 42)), new C0798b(30, new C0797a(5, 24), new C0797a(7, 25)), new C0798b(24, new C0797a(11, 12), new C0797a(7, 13))), new C0796n(16, new int[]{6, 26, 50, 74}, new C0798b(24, new C0797a(5, 98), new C0797a(1, 99)), new C0798b(28, new C0797a(7, 45), new C0797a(3, 46)), new C0798b(24, new C0797a(15, 19), new C0797a(2, 20)), new C0798b(30, new C0797a(3, 15), new C0797a(13, 16))), new C0796n(17, new int[]{6, 30, 54, 78}, new C0798b(28, new C0797a(1, 107), new C0797a(5, 108)), new C0798b(28, new C0797a(10, 46), new C0797a(1, 47)), new C0798b(28, new C0797a(1, 22), new C0797a(15, 23)), new C0798b(28, new C0797a(2, 14), new C0797a(17, 15))), new C0796n(18, new int[]{6, 30, 56, 82}, new C0798b(30, new C0797a(5, 120), new C0797a(1, 121)), new C0798b(26, new C0797a(9, 43), new C0797a(4, 44)), new C0798b(28, new C0797a(17, 22), new C0797a(1, 23)), new C0798b(28, new C0797a(2, 14), new C0797a(19, 15))), new C0796n(19, new int[]{6, 30, 58, 86}, new C0798b(28, new C0797a(3, 113), new C0797a(4, 114)), new C0798b(26, new C0797a(3, 44), new C0797a(11, 45)), new C0798b(26, new C0797a(17, 21), new C0797a(4, 22)), new C0798b(26, new C0797a(9, 13), new C0797a(16, 14))), new C0796n(20, new int[]{6, 34, 62, 90}, new C0798b(28, new C0797a(3, 107), new C0797a(5, 108)), new C0798b(26, new C0797a(3, 41), new C0797a(13, 42)), new C0798b(30, new C0797a(15, 24), new C0797a(5, 25)), new C0798b(28, new C0797a(15, 15), new C0797a(10, 16))), new C0796n(21, new int[]{6, 28, 50, 72, 94}, new C0798b(28, new C0797a(4, 116), new C0797a(4, 117)), new C0798b(26, new C0797a(17, 42)), new C0798b(28, new C0797a(17, 22), new C0797a(6, 23)), new C0798b(30, new C0797a(19, 16), new C0797a(6, 17))), new C0796n(22, new int[]{6, 26, 50, 74, 98}, new C0798b(28, new C0797a(2, 111), new C0797a(7, 112)), new C0798b(28, new C0797a(17, 46)), new C0798b(30, new C0797a(7, 24), new C0797a(16, 25)), new C0798b(24, new C0797a(34, 13))), new C0796n(23, new int[]{6, 30, 54, 78, 102}, new C0798b(30, new C0797a(4, 121), new C0797a(5, 122)), new C0798b(28, new C0797a(4, 47), new C0797a(14, 48)), new C0798b(30, new C0797a(11, 24), new C0797a(14, 25)), new C0798b(30, new C0797a(16, 15), new C0797a(14, 16))), new C0796n(24, new int[]{6, 28, 54, 80, 106}, new C0798b(30, new C0797a(6, 117), new C0797a(4, 118)), new C0798b(28, new C0797a(6, 45), new C0797a(14, 46)), new C0798b(30, new C0797a(11, 24), new C0797a(16, 25)), new C0798b(30, new C0797a(30, 16), new C0797a(2, 17))), new C0796n(25, new int[]{6, 32, 58, 84, 110}, new C0798b(26, new C0797a(8, 106), new C0797a(4, 107)), new C0798b(28, new C0797a(8, 47), new C0797a(13, 48)), new C0798b(30, new C0797a(7, 24), new C0797a(22, 25)), new C0798b(30, new C0797a(22, 15), new C0797a(13, 16))), new C0796n(26, new int[]{6, 30, 58, 86, 114}, new C0798b(28, new C0797a(10, 114), new C0797a(2, 115)), new C0798b(28, new C0797a(19, 46), new C0797a(4, 47)), new C0798b(28, new C0797a(28, 22), new C0797a(6, 23)), new C0798b(30, new C0797a(33, 16), new C0797a(4, 17))), new C0796n(27, new int[]{6, 34, 62, 90, 118}, new C0798b(30, new C0797a(8, 122), new C0797a(4, 123)), new C0798b(28, new C0797a(22, 45), new C0797a(3, 46)), new C0798b(30, new C0797a(8, 23), new C0797a(26, 24)), new C0798b(30, new C0797a(12, 15), new C0797a(28, 16))), new C0796n(28, new int[]{6, 26, 50, 74, 98, 122}, new C0798b(30, new C0797a(3, 117), new C0797a(10, 118)), new C0798b(28, new C0797a(3, 45), new C0797a(23, 46)), new C0798b(30, new C0797a(4, 24), new C0797a(31, 25)), new C0798b(30, new C0797a(11, 15), new C0797a(31, 16))), new C0796n(29, new int[]{6, 30, 54, 78, 102, 126}, new C0798b(30, new C0797a(7, 116), new C0797a(7, 117)), new C0798b(28, new C0797a(21, 45), new C0797a(7, 46)), new C0798b(30, new C0797a(1, 23), new C0797a(37, 24)), new C0798b(30, new C0797a(19, 15), new C0797a(26, 16))), new C0796n(30, new int[]{6, 26, 52, 78, 104, 130}, new C0798b(30, new C0797a(5, 115), new C0797a(10, 116)), new C0798b(28, new C0797a(19, 47), new C0797a(10, 48)), new C0798b(30, new C0797a(15, 24), new C0797a(25, 25)), new C0798b(30, new C0797a(23, 15), new C0797a(25, 16))), new C0796n(31, new int[]{6, 30, 56, 82, 108, 134}, new C0798b(30, new C0797a(13, 115), new C0797a(3, 116)), new C0798b(28, new C0797a(2, 46), new C0797a(29, 47)), new C0798b(30, new C0797a(42, 24), new C0797a(1, 25)), new C0798b(30, new C0797a(23, 15), new C0797a(28, 16))), new C0796n(32, new int[]{6, 34, 60, 86, 112, 138}, new C0798b(30, new C0797a(17, 115)), new C0798b(28, new C0797a(10, 46), new C0797a(23, 47)), new C0798b(30, new C0797a(10, 24), new C0797a(35, 25)), new C0798b(30, new C0797a(19, 15), new C0797a(35, 16))), new C0796n(33, new int[]{6, 30, 58, 86, 114, 142}, new C0798b(30, new C0797a(17, 115), new C0797a(1, 116)), new C0798b(28, new C0797a(14, 46), new C0797a(21, 47)), new C0798b(30, new C0797a(29, 24), new C0797a(19, 25)), new C0798b(30, new C0797a(11, 15), new C0797a(46, 16))), new C0796n(34, new int[]{6, 34, 62, 90, 118, 146}, new C0798b(30, new C0797a(13, 115), new C0797a(6, 116)), new C0798b(28, new C0797a(14, 46), new C0797a(23, 47)), new C0798b(30, new C0797a(44, 24), new C0797a(7, 25)), new C0798b(30, new C0797a(59, 16), new C0797a(1, 17))), new C0796n(35, new int[]{6, 30, 54, 78, 102, 126, 150}, new C0798b(30, new C0797a(12, 121), new C0797a(7, 122)), new C0798b(28, new C0797a(12, 47), new C0797a(26, 48)), new C0798b(30, new C0797a(39, 24), new C0797a(14, 25)), new C0798b(30, new C0797a(22, 15), new C0797a(41, 16))), new C0796n(36, new int[]{6, 24, 50, 76, 102, 128, 154}, new C0798b(30, new C0797a(6, 121), new C0797a(14, 122)), new C0798b(28, new C0797a(6, 47), new C0797a(34, 48)), new C0798b(30, new C0797a(46, 24), new C0797a(10, 25)), new C0798b(30, new C0797a(2, 15), new C0797a(64, 16))), new C0796n(37, new int[]{6, 28, 54, 80, 106, 132, 158}, new C0798b(30, new C0797a(17, 122), new C0797a(4, 123)), new C0798b(28, new C0797a(29, 46), new C0797a(14, 47)), new C0798b(30, new C0797a(49, 24), new C0797a(10, 25)), new C0798b(30, new C0797a(24, 15), new C0797a(46, 16))), new C0796n(38, new int[]{6, 32, 58, 84, 110, 136, 162}, new C0798b(30, new C0797a(4, 122), new C0797a(18, 123)), new C0798b(28, new C0797a(13, 46), new C0797a(32, 47)), new C0798b(30, new C0797a(48, 24), new C0797a(14, 25)), new C0798b(30, new C0797a(42, 15), new C0797a(32, 16))), new C0796n(39, new int[]{6, 26, 54, 82, 110, 138, 166}, new C0798b(30, new C0797a(20, 117), new C0797a(4, 118)), new C0798b(28, new C0797a(40, 47), new C0797a(7, 48)), new C0798b(30, new C0797a(43, 24), new C0797a(22, 25)), new C0798b(30, new C0797a(10, 15), new C0797a(67, 16))), new C0796n(40, new int[]{6, 30, 58, 86, 114, 142, 170}, new C0798b(30, new C0797a(19, 118), new C0797a(6, 119)), new C0798b(28, new C0797a(18, 47), new C0797a(31, 48)), new C0798b(30, new C0797a(34, 24), new C0797a(34, 25)), new C0798b(30, new C0797a(20, 15), new C0797a(61, 16)))};
    }
}
