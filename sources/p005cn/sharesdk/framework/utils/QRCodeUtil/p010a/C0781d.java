package p005cn.sharesdk.framework.utils.QRCodeUtil.p010a;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.a.d */
/* compiled from: MaskUtil */
final class C0781d {
    /* renamed from: a */
    static int m532a(C0778b bVar) {
        return m533a(bVar, true) + m533a(bVar, false);
    }

    /* renamed from: b */
    static int m537b(C0778b bVar) {
        byte[][] c = bVar.mo10826c();
        int b = bVar.mo10825b();
        int a = bVar.mo10821a();
        int i = 0;
        int i2 = 0;
        while (i < a - 1) {
            byte[] bArr = c[i];
            int i3 = i2;
            int i4 = 0;
            while (i4 < b - 1) {
                byte b2 = bArr[i4];
                int i5 = i4 + 1;
                if (b2 == bArr[i5]) {
                    int i6 = i + 1;
                    if (b2 == c[i6][i4] && b2 == c[i6][i5]) {
                        i3++;
                    }
                }
                i4 = i5;
            }
            i++;
            i2 = i3;
        }
        return i2 * 3;
    }

    /* renamed from: c */
    static int m538c(C0778b bVar) {
        byte[][] c = bVar.mo10826c();
        int b = bVar.mo10825b();
        int a = bVar.mo10821a();
        int i = 0;
        int i2 = 0;
        while (i < a) {
            int i3 = i2;
            for (int i4 = 0; i4 < b; i4++) {
                byte[] bArr = c[i];
                int i5 = i4 + 6;
                if (i5 < b && bArr[i4] == 1 && bArr[i4 + 1] == 0 && bArr[i4 + 2] == 1 && bArr[i4 + 3] == 1 && bArr[i4 + 4] == 1 && bArr[i4 + 5] == 0 && bArr[i5] == 1 && (m535a(bArr, i4 - 4, i4) || m535a(bArr, i4 + 7, i4 + 11))) {
                    i3++;
                }
                int i6 = i + 6;
                if (i6 < a && c[i][i4] == 1 && c[i + 1][i4] == 0 && c[i + 2][i4] == 1 && c[i + 3][i4] == 1 && c[i + 4][i4] == 1 && c[i + 5][i4] == 0 && c[i6][i4] == 1 && (m536a(c, i4, i - 4, i) || m536a(c, i4, i + 7, i + 11))) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        return i2 * 40;
    }

    /* renamed from: a */
    private static boolean m535a(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, bArr.length);
        for (int max = Math.max(i, 0); max < min; max++) {
            if (bArr[max] == 1) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private static boolean m536a(byte[][] bArr, int i, int i2, int i3) {
        int min = Math.min(i3, bArr.length);
        for (int max = Math.max(i2, 0); max < min; max++) {
            if (bArr[max][i] == 1) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: d */
    static int m539d(C0778b bVar) {
        byte[][] c = bVar.mo10826c();
        int b = bVar.mo10825b();
        int a = bVar.mo10821a();
        int i = 0;
        int i2 = 0;
        while (i < a) {
            byte[] bArr = c[i];
            int i3 = i2;
            for (int i4 = 0; i4 < b; i4++) {
                if (bArr[i4] == 1) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        int a2 = bVar.mo10821a() * bVar.mo10825b();
        return ((Math.abs((i2 * 2) - a2) * 10) / a2) * 10;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        r3 = r3 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        r1 = r3 & 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        if (r1 != 0) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002b, code lost:
        r1 = r1 & 1;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean m534a(int r1, int r2, int r3) {
        /*
            r0 = 1
            switch(r1) {
                case 0: goto L_0x0041;
                case 1: goto L_0x0042;
                case 2: goto L_0x003e;
                case 3: goto L_0x003a;
                case 4: goto L_0x0035;
                case 5: goto L_0x002d;
                case 6: goto L_0x0024;
                case 7: goto L_0x001b;
                default: goto L_0x0004;
            }
        L_0x0004:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "Invalid mask pattern: "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x001b:
            int r1 = r3 * r2
            int r1 = r1 % 3
            int r3 = r3 + r2
            r2 = r3 & 1
            int r1 = r1 + r2
            goto L_0x002b
        L_0x0024:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
        L_0x002b:
            r1 = r1 & r0
            goto L_0x0044
        L_0x002d:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
            goto L_0x0044
        L_0x0035:
            int r3 = r3 / 2
            int r2 = r2 / 3
            goto L_0x0041
        L_0x003a:
            int r3 = r3 + r2
            int r1 = r3 % 3
            goto L_0x0044
        L_0x003e:
            int r1 = r2 % 3
            goto L_0x0044
        L_0x0041:
            int r3 = r3 + r2
        L_0x0042:
            r1 = r3 & 1
        L_0x0044:
            if (r1 != 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r0 = 0
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.utils.QRCodeUtil.p010a.C0781d.m534a(int, int, int):boolean");
    }

    /* renamed from: a */
    private static int m533a(C0778b bVar, boolean z) {
        int a = z ? bVar.mo10821a() : bVar.mo10825b();
        int b = z ? bVar.mo10825b() : bVar.mo10821a();
        byte[][] c = bVar.mo10826c();
        int i = 0;
        for (int i2 = 0; i2 < a; i2++) {
            int i3 = i;
            int i4 = 0;
            byte b2 = -1;
            for (int i5 = 0; i5 < b; i5++) {
                byte b3 = z ? c[i2][i5] : c[i5][i2];
                if (b3 == b2) {
                    i4++;
                } else {
                    if (i4 >= 5) {
                        i3 += (i4 - 5) + 3;
                    }
                    i4 = 1;
                    b2 = b3;
                }
            }
            if (i4 >= 5) {
                i3 += (i4 - 5) + 3;
            }
            i = i3;
        }
        return i;
    }
}
