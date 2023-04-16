package p005cn.sharesdk.framework.utils.QRCodeUtil.p010a;

import p005cn.sharesdk.framework.utils.QRCodeUtil.C0788f;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0791i;
import p005cn.sharesdk.framework.utils.QRCodeUtil.C0796n;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.a.f */
/* compiled from: QRCode */
public final class C0783f {

    /* renamed from: a */
    private C0791i f440a;

    /* renamed from: b */
    private C0788f f441b;

    /* renamed from: c */
    private C0796n f442c;

    /* renamed from: d */
    private int f443d = -1;

    /* renamed from: e */
    private C0778b f444e;

    /* renamed from: b */
    public static boolean m559b(int i) {
        return i >= 0 && i < 8;
    }

    /* renamed from: a */
    public C0778b mo10828a() {
        return this.f444e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.f440a);
        sb.append("\n ecLevel: ");
        sb.append(this.f441b);
        sb.append("\n version: ");
        sb.append(this.f442c);
        sb.append("\n maskPattern: ");
        sb.append(this.f443d);
        if (this.f444e == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.f444e);
        }
        sb.append(">>\n");
        return sb.toString();
    }

    /* renamed from: a */
    public void mo10832a(C0791i iVar) {
        this.f440a = iVar;
    }

    /* renamed from: a */
    public void mo10831a(C0788f fVar) {
        this.f441b = fVar;
    }

    /* renamed from: a */
    public void mo10833a(C0796n nVar) {
        this.f442c = nVar;
    }

    /* renamed from: a */
    public void mo10829a(int i) {
        this.f443d = i;
    }

    /* renamed from: a */
    public void mo10830a(C0778b bVar) {
        this.f444e = bVar;
    }
}
