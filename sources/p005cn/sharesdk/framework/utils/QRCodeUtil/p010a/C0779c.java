package p005cn.sharesdk.framework.utils.QRCodeUtil.p010a;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import kotlin.UByte;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0784b;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0786d;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0787e;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0788f;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0789g;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0791i;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0795m;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0796n;
import p005cn.sharesdk.framework.utils.QRCodeUtil.WriterException;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.a.c */
/* compiled from: Encoder */
public final class C0779c {

    /* renamed from: a */
    private static final int[] f434a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    /* renamed from: a */
    private static int m511a(C0778b bVar) {
        return C0781d.m532a(bVar) + C0781d.m537b(bVar) + C0781d.m538c(bVar) + C0781d.m539d(bVar);
    }

    /* renamed from: a */
    public static C0783f m514a(String str, C0788f fVar, Map<C0787e, ?> map) throws WriterException {
        C0796n nVar;
        C0786d a;
        boolean z = true;
        boolean z2 = map != null && map.containsKey(C0787e.CHARACTER_SET);
        String obj = z2 ? map.get(C0787e.CHARACTER_SET).toString() : "ISO-8859-1";
        C0791i a2 = m516a(str, obj);
        C0784b bVar = new C0784b();
        if (a2 == C0791i.BYTE && z2 && (a = C0786d.m582a(obj)) != null) {
            m522a(a, bVar);
        }
        if (map == null || !map.containsKey(C0787e.GS1_FORMAT)) {
            z = false;
        }
        if (z && Boolean.valueOf(map.get(C0787e.GS1_FORMAT).toString()).booleanValue()) {
            m523a(C0791i.FNC1_FIRST_POSITION, bVar);
        }
        m523a(a2, bVar);
        C0784b bVar2 = new C0784b();
        m527a(str, a2, bVar2, obj);
        if (map == null || !map.containsKey(C0787e.QR_VERSION)) {
            nVar = m518a(fVar, a2, bVar, bVar2);
        } else {
            nVar = C0796n.m632a(Integer.parseInt(map.get(C0787e.QR_VERSION).toString()));
            if (!m528a(m513a(a2, bVar, bVar2, nVar), nVar, fVar)) {
                throw new WriterException("Data too big for requested version");
            }
        }
        C0784b bVar3 = new C0784b();
        bVar3.mo10838a(bVar);
        m521a(a2 == C0791i.BYTE ? bVar2.mo10841b() : str.length(), nVar, a2, bVar3);
        bVar3.mo10838a(bVar2);
        C0796n.C0798b a3 = nVar.mo10896a(fVar);
        int b = nVar.mo10897b() - a3.mo10904c();
        m520a(b, bVar3);
        C0784b a4 = m515a(bVar3, nVar.mo10897b(), b, a3.mo10903b());
        C0783f fVar2 = new C0783f();
        fVar2.mo10831a(fVar);
        fVar2.mo10832a(a2);
        fVar2.mo10833a(nVar);
        int c = nVar.mo10898c();
        C0778b bVar4 = new C0778b(c, c);
        int a5 = m512a(a4, fVar, nVar, bVar4);
        fVar2.mo10829a(a5);
        C0782e.m545a(a4, fVar, nVar, a5, bVar4);
        fVar2.mo10830a(bVar4);
        return fVar2;
    }

    /* renamed from: a */
    private static C0796n m518a(C0788f fVar, C0791i iVar, C0784b bVar, C0784b bVar2) throws WriterException {
        return m517a(m513a(iVar, bVar, bVar2, m517a(m513a(iVar, bVar, bVar2, C0796n.m632a(1)), fVar)), fVar);
    }

    /* renamed from: a */
    private static int m513a(C0791i iVar, C0784b bVar, C0784b bVar2, C0796n nVar) {
        return bVar.mo10835a() + iVar.mo10876a(nVar) + bVar2.mo10835a();
    }

    /* renamed from: a */
    static int m510a(int i) {
        int[] iArr = f434a;
        if (i < iArr.length) {
            return iArr[i];
        }
        return -1;
    }

    /* renamed from: a */
    private static C0791i m516a(String str, String str2) {
        if ("Shift_JIS".equals(str2) && m529a(str)) {
            return C0791i.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else if (m510a((int) charAt) == -1) {
                return C0791i.BYTE;
            } else {
                z = true;
            }
        }
        if (z) {
            return C0791i.ALPHANUMERIC;
        }
        if (z2) {
            return C0791i.NUMERIC;
        }
        return C0791i.BYTE;
    }

    /* renamed from: a */
    private static boolean m529a(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                byte b = bytes[i] & UByte.MAX_VALUE;
                if ((b < 129 || b > 159) && (b < 224 || b > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static int m512a(C0784b bVar, C0788f fVar, C0796n nVar, C0778b bVar2) throws WriterException {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < 8; i3++) {
            C0782e.m545a(bVar, fVar, nVar, i3, bVar2);
            int a = m511a(bVar2);
            if (a < i) {
                i2 = i3;
                i = a;
            }
        }
        return i2;
    }

    /* renamed from: a */
    private static C0796n m517a(int i, C0788f fVar) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            C0796n a = C0796n.m632a(i2);
            if (m528a(i, a, fVar)) {
                return a;
            }
        }
        throw new WriterException("Data too big");
    }

    /* renamed from: a */
    private static boolean m528a(int i, C0796n nVar, C0788f fVar) {
        return nVar.mo10897b() - nVar.mo10896a(fVar).mo10904c() >= (i + 7) / 8;
    }

    /* renamed from: a */
    static void m520a(int i, C0784b bVar) throws WriterException {
        int i2 = i * 8;
        if (bVar.mo10835a() <= i2) {
            for (int i3 = 0; i3 < 4 && bVar.mo10835a() < i2; i3++) {
                bVar.mo10839a(false);
            }
            int a = bVar.mo10835a() & 7;
            if (a > 0) {
                while (a < 8) {
                    bVar.mo10839a(false);
                    a++;
                }
            }
            int b = i - bVar.mo10841b();
            for (int i4 = 0; i4 < b; i4++) {
                bVar.mo10836a((i4 & 1) == 0 ? 236 : 17, 8);
            }
            if (bVar.mo10835a() != i2) {
                throw new WriterException("Bits size does not equal capacity");
            }
            return;
        }
        throw new WriterException("data bits cannot fit in the QR Code" + bVar.mo10835a() + " > " + i2);
    }

    /* renamed from: a */
    static void m519a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 < i3) {
            int i5 = i % i3;
            int i6 = i3 - i5;
            int i7 = i / i3;
            int i8 = i7 + 1;
            int i9 = i2 / i3;
            int i10 = i9 + 1;
            int i11 = i7 - i9;
            int i12 = i8 - i10;
            if (i11 != i12) {
                throw new WriterException("EC bytes mismatch");
            } else if (i3 != i6 + i5) {
                throw new WriterException("RS blocks mismatch");
            } else if (i != ((i9 + i11) * i6) + ((i10 + i12) * i5)) {
                throw new WriterException("Total bytes mismatch");
            } else if (i4 < i6) {
                iArr[0] = i9;
                iArr2[0] = i11;
            } else {
                iArr[0] = i10;
                iArr2[0] = i12;
            }
        } else {
            throw new WriterException("Block ID too large");
        }
    }

    /* renamed from: a */
    static C0784b m515a(C0784b bVar, int i, int i2, int i3) throws WriterException {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (bVar.mo10841b() == i5) {
            ArrayList<C0777a> arrayList = new ArrayList<>(i6);
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            for (int i10 = 0; i10 < i6; i10++) {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                m519a(i, i2, i3, i10, iArr, iArr2);
                int i11 = iArr[0];
                byte[] bArr = new byte[i11];
                bVar.mo10837a(i7 * 8, bArr, 0, i11);
                byte[] a = m530a(bArr, iArr2[0]);
                arrayList.add(new C0777a(bArr, a));
                i8 = Math.max(i8, i11);
                i9 = Math.max(i9, a.length);
                i7 += iArr[0];
            }
            if (i5 == i7) {
                C0784b bVar2 = new C0784b();
                for (int i12 = 0; i12 < i8; i12++) {
                    for (C0777a a2 : arrayList) {
                        byte[] a3 = a2.mo10818a();
                        if (i12 < a3.length) {
                            bVar2.mo10836a(a3[i12], 8);
                        }
                    }
                }
                for (int i13 = 0; i13 < i9; i13++) {
                    for (C0777a b : arrayList) {
                        byte[] b2 = b.mo10819b();
                        if (i13 < b2.length) {
                            bVar2.mo10836a(b2[i13], 8);
                        }
                    }
                }
                if (i4 == bVar2.mo10841b()) {
                    return bVar2;
                }
                throw new WriterException("Interleaving error: " + i4 + " and " + bVar2.mo10841b() + " differ.");
            }
            throw new WriterException("Data bytes does not match offset");
        }
        throw new WriterException("Number of bits and data bytes does not match");
    }

    /* renamed from: a */
    static byte[] m530a(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[(length + i)];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & UByte.MAX_VALUE;
        }
        new C0795m(C0789g.f507e).mo10894a(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    /* renamed from: a */
    static void m523a(C0791i iVar, C0784b bVar) {
        bVar.mo10836a(iVar.mo10875a(), 4);
    }

    /* renamed from: a */
    static void m521a(int i, C0796n nVar, C0791i iVar, C0784b bVar) throws WriterException {
        int a = iVar.mo10876a(nVar);
        int i2 = 1 << a;
        if (i < i2) {
            bVar.mo10836a(i, a);
            return;
        }
        throw new WriterException(i + " is bigger than " + (i2 - 1));
    }

    /* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.a.c$1 */
    /* compiled from: Encoder */
    static /* synthetic */ class C07801 {

        /* renamed from: a */
        static final /* synthetic */ int[] f435a = new int[C0791i.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                cn.sharesdk.framework.utils.QRCodeUtil.i[] r0 = p005cn.sharesdk.framework.utils.QRCodeUtil.C0791i.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f435a = r0
                int[] r0 = f435a     // Catch:{ NoSuchFieldError -> 0x0014 }
                cn.sharesdk.framework.utils.QRCodeUtil.i r1 = p005cn.sharesdk.framework.utils.QRCodeUtil.C0791i.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f435a     // Catch:{ NoSuchFieldError -> 0x001f }
                cn.sharesdk.framework.utils.QRCodeUtil.i r1 = p005cn.sharesdk.framework.utils.QRCodeUtil.C0791i.ALPHANUMERIC     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f435a     // Catch:{ NoSuchFieldError -> 0x002a }
                cn.sharesdk.framework.utils.QRCodeUtil.i r1 = p005cn.sharesdk.framework.utils.QRCodeUtil.C0791i.BYTE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f435a     // Catch:{ NoSuchFieldError -> 0x0035 }
                cn.sharesdk.framework.utils.QRCodeUtil.i r1 = p005cn.sharesdk.framework.utils.QRCodeUtil.C0791i.KANJI     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.utils.QRCodeUtil.p010a.C0779c.C07801.<clinit>():void");
        }
    }

    /* renamed from: a */
    static void m527a(String str, C0791i iVar, C0784b bVar, String str2) throws WriterException {
        int i = C07801.f435a[iVar.ordinal()];
        if (i == 1) {
            m524a((CharSequence) str, bVar);
        } else if (i == 2) {
            m531b(str, bVar);
        } else if (i == 3) {
            m526a(str, bVar, str2);
        } else if (i == 4) {
            m525a(str, bVar);
        } else {
            throw new WriterException("Invalid mode: " + iVar);
        }
    }

    /* renamed from: a */
    static void m524a(CharSequence charSequence, C0784b bVar) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - '0';
            int i2 = i + 2;
            if (i2 < length) {
                bVar.mo10836a((charAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i2) - '0'), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    bVar.mo10836a((charAt * 10) + (charSequence.charAt(i) - '0'), 7);
                    i = i2;
                } else {
                    bVar.mo10836a(charAt, 4);
                }
            }
        }
    }

    /* renamed from: b */
    static void m531b(CharSequence charSequence, C0784b bVar) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int a = m510a((int) charSequence.charAt(i));
            if (a != -1) {
                int i2 = i + 1;
                if (i2 < length) {
                    int a2 = m510a((int) charSequence.charAt(i2));
                    if (a2 != -1) {
                        bVar.mo10836a((a * 45) + a2, 11);
                        i += 2;
                    } else {
                        throw new WriterException();
                    }
                } else {
                    bVar.mo10836a(a, 6);
                    i = i2;
                }
            } else {
                throw new WriterException();
            }
        }
    }

    /* renamed from: a */
    static void m526a(String str, C0784b bVar, String str2) throws WriterException {
        try {
            for (byte a : str.getBytes(str2)) {
                bVar.mo10836a(a, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException((Throwable) e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c A[LOOP:0: B:6:0x000f->B:19:0x003c, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004b A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void m525a(java.lang.String r6, p005cn.sharesdk.framework.utils.QRCodeUtil.C0784b r7) throws p005cn.sharesdk.framework.utils.QRCodeUtil.WriterException {
        /*
            java.lang.String r0 = "Shift_JIS"
            byte[] r6 = r6.getBytes(r0)     // Catch:{ UnsupportedEncodingException -> 0x005c }
            int r0 = r6.length
            int r0 = r0 % 2
            if (r0 != 0) goto L_0x0054
            int r0 = r6.length
            int r0 = r0 + -1
            r1 = 0
        L_0x000f:
            if (r1 >= r0) goto L_0x0053
            byte r2 = r6[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r1 + 1
            byte r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r2 = r2 | r3
            r3 = 33088(0x8140, float:4.6366E-41)
            r4 = -1
            if (r2 < r3) goto L_0x002b
            r5 = 40956(0x9ffc, float:5.7392E-41)
            if (r2 > r5) goto L_0x002b
        L_0x0029:
            int r2 = r2 - r3
            goto L_0x003a
        L_0x002b:
            r3 = 57408(0xe040, float:8.0446E-41)
            if (r2 < r3) goto L_0x0039
            r3 = 60351(0xebbf, float:8.457E-41)
            if (r2 > r3) goto L_0x0039
            r3 = 49472(0xc140, float:6.9325E-41)
            goto L_0x0029
        L_0x0039:
            r2 = -1
        L_0x003a:
            if (r2 == r4) goto L_0x004b
            int r3 = r2 >> 8
            int r3 = r3 * 192
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r3 + r2
            r2 = 13
            r7.mo10836a(r3, r2)
            int r1 = r1 + 2
            goto L_0x000f
        L_0x004b:
            cn.sharesdk.framework.utils.QRCodeUtil.WriterException r6 = new cn.sharesdk.framework.utils.QRCodeUtil.WriterException
            java.lang.String r7 = "Invalid byte sequence"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x0053:
            return
        L_0x0054:
            cn.sharesdk.framework.utils.QRCodeUtil.WriterException r6 = new cn.sharesdk.framework.utils.QRCodeUtil.WriterException
            java.lang.String r7 = "Kanji byte size not even"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x005c:
            r6 = move-exception
            cn.sharesdk.framework.utils.QRCodeUtil.WriterException r7 = new cn.sharesdk.framework.utils.QRCodeUtil.WriterException
            r7.<init>((java.lang.Throwable) r6)
            goto L_0x0064
        L_0x0063:
            throw r7
        L_0x0064:
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.utils.QRCodeUtil.p010a.C0779c.m525a(java.lang.String, cn.sharesdk.framework.utils.QRCodeUtil.b):void");
    }

    /* renamed from: a */
    private static void m522a(C0786d dVar, C0784b bVar) {
        bVar.mo10836a(C0791i.ECI.mo10875a(), 4);
        bVar.mo10836a(dVar.mo10856a(), 8);
    }
}
