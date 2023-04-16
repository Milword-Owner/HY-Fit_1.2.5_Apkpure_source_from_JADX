package p005cn.sharesdk.framework.utils.QRCodeUtil.p010a;

import java.lang.reflect.Array;
import java.util.Arrays;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.a.b */
/* compiled from: ByteMatrix */
public final class C0778b {

    /* renamed from: a */
    private final byte[][] f431a;

    /* renamed from: b */
    private final int f432b;

    /* renamed from: c */
    private final int f433c;

    public C0778b(int i, int i2) {
        this.f431a = (byte[][]) Array.newInstance(byte.class, new int[]{i2, i});
        this.f432b = i;
        this.f433c = i2;
    }

    /* renamed from: a */
    public int mo10821a() {
        return this.f433c;
    }

    /* renamed from: b */
    public int mo10825b() {
        return this.f432b;
    }

    /* renamed from: a */
    public byte mo10820a(int i, int i2) {
        return this.f431a[i2][i];
    }

    /* renamed from: c */
    public byte[][] mo10826c() {
        return this.f431a;
    }

    /* renamed from: a */
    public void mo10823a(int i, int i2, int i3) {
        this.f431a[i2][i] = (byte) i3;
    }

    /* renamed from: a */
    public void mo10824a(int i, int i2, boolean z) {
        this.f431a[i2][i] = z ? (byte) 1 : 0;
    }

    /* renamed from: a */
    public void mo10822a(byte b) {
        for (byte[] fill : this.f431a) {
            Arrays.fill(fill, b);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.f432b * 2 * this.f433c) + 2);
        for (int i = 0; i < this.f433c; i++) {
            byte[] bArr = this.f431a[i];
            for (int i2 = 0; i2 < this.f432b; i2++) {
                byte b = bArr[i2];
                if (b == 0) {
                    sb.append(" 0");
                } else if (b != 1) {
                    sb.append("\t");
                } else {
                    sb.append(" 1");
                }
            }
            sb.append(10);
        }
        return sb.toString();
    }
}
