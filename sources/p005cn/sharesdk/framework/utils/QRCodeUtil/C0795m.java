package p005cn.sharesdk.framework.utils.QRCodeUtil;

import java.util.ArrayList;
import java.util.List;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.m */
/* compiled from: ReedSolomonEncoder */
public final class C0795m {

    /* renamed from: a */
    private final C0789g f577a;

    /* renamed from: b */
    private final List<C0790h> f578b = new ArrayList();

    public C0795m(C0789g gVar) {
        this.f577a = gVar;
        this.f578b.add(new C0790h(gVar, new int[]{1}));
    }

    /* renamed from: a */
    private C0790h m630a(int i) {
        if (i >= this.f578b.size()) {
            List<C0790h> list = this.f578b;
            C0790h hVar = list.get(list.size() - 1);
            for (int size = this.f578b.size(); size <= i; size++) {
                C0789g gVar = this.f577a;
                hVar = hVar.mo10871b(new C0790h(gVar, new int[]{1, gVar.mo10858a((size - 1) + gVar.mo10861b())}));
                this.f578b.add(hVar);
            }
        }
        return this.f578b.get(i);
    }

    /* renamed from: a */
    public void mo10894a(int[] iArr, int i) {
        if (i != 0) {
            int length = iArr.length - i;
            if (length > 0) {
                C0790h a = m630a(i);
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] a2 = new C0790h(this.f577a, iArr2).mo10867a(i, 1).mo10873c(a)[1].mo10869a();
                int length2 = i - a2.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    iArr[length + i2] = 0;
                }
                System.arraycopy(a2, 0, iArr, length + length2, a2.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
