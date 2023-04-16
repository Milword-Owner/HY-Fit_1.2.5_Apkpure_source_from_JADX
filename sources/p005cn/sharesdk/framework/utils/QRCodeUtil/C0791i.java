package p005cn.sharesdk.framework.utils.QRCodeUtil;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.i */
/* compiled from: Mode */
public enum C0791i {
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    HANZI(new int[]{8, 10, 12}, 13);
    

    /* renamed from: k */
    private final int[] f531k;

    /* renamed from: l */
    private final int f532l;

    private C0791i(int[] iArr, int i) {
        this.f531k = iArr;
        this.f532l = i;
    }

    /* renamed from: a */
    public int mo10876a(C0796n nVar) {
        int a = nVar.mo10895a();
        return this.f531k[a <= 9 ? 0 : a <= 26 ? (char) 1 : 2];
    }

    /* renamed from: a */
    public int mo10875a() {
        return this.f532l;
    }
}
