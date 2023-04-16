package p005cn.sharesdk.framework.utils.QRCodeUtil;

import java.util.Map;
import p005cn.sharesdk.framework.utils.QRCodeUtil.p010a.C0778b;
import p005cn.sharesdk.framework.utils.QRCodeUtil.p010a.C0779c;
import p005cn.sharesdk.framework.utils.QRCodeUtil.p010a.C0783f;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.l */
/* compiled from: QRCodeWriter */
public final class C0794l implements Writer {
    public C0785c encode(String str, C0776a aVar, int i, int i2) throws WriterException {
        return encode(str, aVar, i, i2, (Map<C0787e, ?>) null);
    }

    public C0785c encode(String str, C0776a aVar, int i, int i2, Map<C0787e, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (aVar != C0776a.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + aVar);
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        } else {
            C0788f fVar = C0788f.L;
            int i3 = 4;
            if (map != null) {
                if (map.containsKey(C0787e.ERROR_CORRECTION)) {
                    fVar = C0788f.valueOf(map.get(C0787e.ERROR_CORRECTION).toString());
                }
                if (map.containsKey(C0787e.MARGIN)) {
                    i3 = Integer.parseInt(map.get(C0787e.MARGIN).toString());
                }
            }
            return m629a(C0779c.m514a(str, fVar, map), i, i2, i3);
        }
    }

    /* renamed from: a */
    private static C0785c m629a(C0783f fVar, int i, int i2, int i3) {
        C0778b a = fVar.mo10828a();
        if (a != null) {
            int b = a.mo10825b();
            int a2 = a.mo10821a();
            int i4 = i3 * 2;
            int i5 = b + i4;
            int i6 = i4 + a2;
            int max = Math.max(i, i5);
            int max2 = Math.max(i2, i6);
            int min = Math.min(max / i5, max2 / i6);
            int i7 = (max - (b * min)) / 2;
            int i8 = (max2 - (a2 * min)) / 2;
            C0785c cVar = new C0785c(max, max2);
            int i9 = 0;
            while (i9 < a2) {
                int i10 = i7;
                int i11 = 0;
                while (i11 < b) {
                    if (a.mo10820a(i11, i9) == 1) {
                        cVar.mo10850a(i10, i8, min, min);
                    }
                    i11++;
                    i10 += min;
                }
                i9++;
                i8 += min;
            }
            return cVar;
        }
        throw new IllegalStateException();
    }
}
