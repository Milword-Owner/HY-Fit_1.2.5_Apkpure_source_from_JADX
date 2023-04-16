package p005cn.sharesdk.framework.p007b.p009b;

import android.text.TextUtils;
import com.google.android.gms.fitness.FitnessStatusCodes;
import p005cn.sharesdk.framework.p007b.p008a.C0713e;

/* renamed from: cn.sharesdk.framework.b.b.e */
/* compiled from: ExitEvent */
public class C0719e extends C0717c {

    /* renamed from: b */
    private static int f248b;

    /* renamed from: c */
    private static long f249c;

    /* renamed from: a */
    public long f250a;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo10612a() {
        return "[EXT]";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo10614b() {
        return FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo10615c() {
        return 5;
    }

    /* renamed from: g */
    public boolean mo10620g() {
        C0713e a = C0713e.m196a();
        f248b = a.mo10606j("insertExitEventCount");
        f249c = a.mo10604i("lastInsertExitEventTime");
        return super.mo10620g();
    }

    /* renamed from: h */
    public void mo10621h() {
        super.mo10621h();
        C0713e a = C0713e.m196a();
        a.mo10584a("lastInsertExitEventTime", Long.valueOf(f249c));
        a.mo10583a("insertExitEventCount", f248b);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo10616d() {
        return (long) f248b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public long mo10617e() {
        return f249c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo10618f() {
        f248b++;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo10613a(long j) {
        f249c = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|');
        if (!TextUtils.isEmpty(this.f242l)) {
            sb.append(this.f242l);
        }
        sb.append('|');
        sb.append(Math.round(((float) this.f250a) / 1000.0f));
        return sb.toString();
    }
}
