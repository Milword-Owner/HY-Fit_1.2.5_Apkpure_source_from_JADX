package p005cn.sharesdk.framework.utils.QRCodeUtil.p010a;

import p005cn.sharesdk.framework.utils.QRCodeUtil.C0784b;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0788f;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0796n;
import p005cn.sharesdk.framework.utils.QRCodeUtil.WriterException;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.a.e */
/* compiled from: MatrixUtil */
final class C0782e {

    /* renamed from: a */
    private static final int[][] f436a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};

    /* renamed from: b */
    private static final int[][] f437b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};

    /* renamed from: c */
    private static final int[][] f438c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};

    /* renamed from: d */
    private static final int[][] f439d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    /* renamed from: b */
    private static boolean m553b(int i) {
        return i == -1;
    }

    /* renamed from: a */
    static void m543a(C0778b bVar) {
        bVar.mo10822a((byte) -1);
    }

    /* renamed from: a */
    static void m545a(C0784b bVar, C0788f fVar, C0796n nVar, int i, C0778b bVar2) throws WriterException {
        m543a(bVar2);
        m548a(nVar, bVar2);
        m546a(fVar, i, bVar2);
        m552b(nVar, bVar2);
        m544a(bVar, i, bVar2);
    }

    /* renamed from: a */
    static void m548a(C0796n nVar, C0778b bVar) throws WriterException {
        m558d(bVar);
        m555c(bVar);
        m556c(nVar, bVar);
        m551b(bVar);
    }

    /* renamed from: a */
    static void m546a(C0788f fVar, int i, C0778b bVar) throws WriterException {
        C0784b bVar2 = new C0784b();
        m547a(fVar, i, bVar2);
        for (int i2 = 0; i2 < bVar2.mo10835a(); i2++) {
            boolean a = bVar2.mo10840a((bVar2.mo10835a() - 1) - i2);
            int[] iArr = f439d[i2];
            bVar.mo10824a(iArr[0], iArr[1], a);
            if (i2 < 8) {
                bVar.mo10824a((bVar.mo10825b() - i2) - 1, 8, a);
            } else {
                bVar.mo10824a(8, (bVar.mo10821a() - 7) + (i2 - 8), a);
            }
        }
    }

    /* renamed from: b */
    static void m552b(C0796n nVar, C0778b bVar) throws WriterException {
        if (nVar.mo10895a() >= 7) {
            C0784b bVar2 = new C0784b();
            m549a(nVar, bVar2);
            int i = 0;
            int i2 = 17;
            while (i < 6) {
                int i3 = i2;
                for (int i4 = 0; i4 < 3; i4++) {
                    boolean a = bVar2.mo10840a(i3);
                    i3--;
                    bVar.mo10824a(i, (bVar.mo10821a() - 11) + i4, a);
                    bVar.mo10824a((bVar.mo10821a() - 11) + i4, i, a);
                }
                i++;
                i2 = i3;
            }
        }
    }

    /* renamed from: a */
    static void m544a(C0784b bVar, int i, C0778b bVar2) throws WriterException {
        boolean z;
        int b = bVar2.mo10825b() - 1;
        int a = bVar2.mo10821a() - 1;
        int i2 = 0;
        int i3 = -1;
        while (b > 0) {
            if (b == 6) {
                b--;
            }
            while (a >= 0 && a < bVar2.mo10821a()) {
                int i4 = i2;
                for (int i5 = 0; i5 < 2; i5++) {
                    int i6 = b - i5;
                    if (m553b((int) bVar2.mo10820a(i6, a))) {
                        if (i4 < bVar.mo10835a()) {
                            z = bVar.mo10840a(i4);
                            i4++;
                        } else {
                            z = false;
                        }
                        if (i != -1 && C0781d.m534a(i, i6, a)) {
                            z = !z;
                        }
                        bVar2.mo10824a(i6, a, z);
                    }
                }
                a += i3;
                i2 = i4;
            }
            i3 = -i3;
            a += i3;
            b -= 2;
        }
        if (i2 != bVar.mo10835a()) {
            throw new WriterException("Not all bits consumed: " + i2 + '/' + bVar.mo10835a());
        }
    }

    /* renamed from: a */
    static int m540a(int i) {
        return 32 - Integer.numberOfLeadingZeros(i);
    }

    /* renamed from: a */
    static int m541a(int i, int i2) {
        if (i2 != 0) {
            int a = m540a(i2);
            int i3 = i << (a - 1);
            while (m540a(i3) >= a) {
                i3 ^= i2 << (m540a(i3) - a);
            }
            return i3;
        }
        throw new IllegalArgumentException("0 polynomial");
    }

    /* renamed from: a */
    static void m547a(C0788f fVar, int i, C0784b bVar) throws WriterException {
        if (C0783f.m559b(i)) {
            int a = (fVar.mo10857a() << 3) | i;
            bVar.mo10836a(a, 5);
            bVar.mo10836a(m541a(a, 1335), 10);
            C0784b bVar2 = new C0784b();
            bVar2.mo10836a(21522, 15);
            bVar.mo10842b(bVar2);
            if (bVar.mo10835a() != 15) {
                throw new WriterException("should not happen but we got: " + bVar.mo10835a());
            }
            return;
        }
        throw new WriterException("Invalid mask pattern");
    }

    /* renamed from: a */
    static void m549a(C0796n nVar, C0784b bVar) throws WriterException {
        bVar.mo10836a(nVar.mo10895a(), 6);
        bVar.mo10836a(m541a(nVar.mo10895a(), 7973), 12);
        if (bVar.mo10835a() != 18) {
            throw new WriterException("should not happen but we got: " + bVar.mo10835a());
        }
    }

    /* renamed from: b */
    private static void m551b(C0778b bVar) {
        int i = 8;
        while (i < bVar.mo10825b() - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (m553b((int) bVar.mo10820a(i, 6))) {
                bVar.mo10823a(i, 6, i3);
            }
            if (m553b((int) bVar.mo10820a(6, i))) {
                bVar.mo10823a(6, i, i3);
            }
            i = i2;
        }
    }

    /* renamed from: c */
    private static void m555c(C0778b bVar) throws WriterException {
        if (bVar.mo10820a(8, bVar.mo10821a() - 8) != 0) {
            bVar.mo10823a(8, bVar.mo10821a() - 8, 1);
            return;
        }
        throw new WriterException();
    }

    /* renamed from: a */
    private static void m542a(int i, int i2, C0778b bVar) throws WriterException {
        int i3 = 0;
        while (i3 < 8) {
            int i4 = i + i3;
            if (m553b((int) bVar.mo10820a(i4, i2))) {
                bVar.mo10823a(i4, i2, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    /* renamed from: b */
    private static void m550b(int i, int i2, C0778b bVar) throws WriterException {
        int i3 = 0;
        while (i3 < 7) {
            int i4 = i2 + i3;
            if (m553b((int) bVar.mo10820a(i, i4))) {
                bVar.mo10823a(i, i4, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    /* renamed from: c */
    private static void m554c(int i, int i2, C0778b bVar) {
        for (int i3 = 0; i3 < 5; i3++) {
            int[] iArr = f437b[i3];
            for (int i4 = 0; i4 < 5; i4++) {
                bVar.mo10823a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    /* renamed from: d */
    private static void m557d(int i, int i2, C0778b bVar) {
        for (int i3 = 0; i3 < 7; i3++) {
            int[] iArr = f436a[i3];
            for (int i4 = 0; i4 < 7; i4++) {
                bVar.mo10823a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    /* renamed from: d */
    private static void m558d(C0778b bVar) throws WriterException {
        int length = f436a[0].length;
        m557d(0, 0, bVar);
        m557d(bVar.mo10825b() - length, 0, bVar);
        m557d(0, bVar.mo10825b() - length, bVar);
        m542a(0, 7, bVar);
        m542a(bVar.mo10825b() - 8, 7, bVar);
        m542a(0, bVar.mo10825b() - 8, bVar);
        m550b(7, 0, bVar);
        m550b((bVar.mo10821a() - 7) - 1, 0, bVar);
        m550b(7, bVar.mo10821a() - 7, bVar);
    }

    /* renamed from: c */
    private static void m556c(C0796n nVar, C0778b bVar) {
        if (nVar.mo10895a() >= 2) {
            int[] iArr = f438c[nVar.mo10895a() - 1];
            for (int i : iArr) {
                if (i >= 0) {
                    for (int i2 : iArr) {
                        if (i2 >= 0 && m553b((int) bVar.mo10820a(i2, i))) {
                            m554c(i2 - 2, i - 2, bVar);
                        }
                    }
                }
            }
        }
    }
}
